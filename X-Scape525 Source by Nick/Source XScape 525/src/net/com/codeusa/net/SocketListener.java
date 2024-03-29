/*
 * Class SocketListener
 *
 * Version 1.0
 *
 * Thursday, August 14, 2008
 *
 * Created by Pikachuz
 */

package net.com.codeusa.net;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.util.*;
import net.com.codeusa.Server;
import java.util.ArrayList;
import java.util.List;
import net.com.codeusa.Engine;
import net.com.codeusa.util.Misc;

public final class SocketListener extends ConnectionManager {

//public static List connectionsList = new ArrayList();

    /**
     * Set true if the socket listener should run.
     */
    private boolean serverRunning = true;
    /**
     * Server socket which intercepts incoming connections.
     */
    private ServerSocket serverChannel;
    /**
     * Array of all banned addresses.
     */
    private String[] bannedHosts = new String[500];

    /**
     * Constructs a new SocketListener.
     * @param port The port to run the server on.
     */
    public SocketListener(int port) throws Exception {
        serverChannel = new ServerSocket(port, 100);
        Misc.println("Starting server on port: " + port);
        loadBannedHosts();
    }

/*public int totalConnections(String connection) {
		int count = 0;
		for(Object o : connectionsList.toArray()) {
			if(o.toString().equals(connection)) {
				count++;
			}
		}
		return count;
	}
*/
    /**
     * The thread's process.
     * <p>The serverChannel listens for incoming connections, and accepts them.
     * The timeout is set to 1 to prevent i/o blocking.
     */
    public void run() {
        Socket socket = null;
        while (serverRunning) {
            try {
//if(!connectionsList.contains(getAddress(socket)))
		//connectionsList.add(getAddress(socket));
                socket = serverChannel.accept();
                socket.setSoTimeout(1);
                socket.setTcpNoDelay(true);
                appendConnection(getAddress(socket));
		      if (!checkBanned(getAddress(socket))) {
                    Misc.println("Connection recieved from: " + getAddress(socket));
                }  
                if (checkBanned(getAddress(socket))) {
                    socket.close();
                    continue;
                }
		/*if(connectionsList.contains(getAddress(socket))) {
		socket.close();
		continue;
		}*/
                int id = getFreeId();
                sockets[id] = socket;
                Server.engine.addConnection(socket, id);
            } catch (Exception e) {
            }
        }
    }

    /**
     * Logs every connection, extremely vital for reporting connections that are trying to 
     * flood the server.
     */
    public void appendConnection(String host) {
        Engine.fileManager.appendData("connection dates/" + host + ".txt", "[" + Misc.getDate() + "] " + host + ": connection recieved.");
    }

    /**
     * Checks to make sure the host isnt IP banned.
     */
    public boolean checkBanned(String hostName) {
        if (hostName == null) {
            return true;
        }
        for (int i = 0; i < bannedHosts.length; i++) {
            if (bannedHosts[i] != null && (hostName.startsWith(bannedHosts[i]) || hostName.equals(bannedHosts[i]))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Loads all banned hosts at startup.
     */
    public void loadBannedHosts() {
        int index = 0;
        try {
            BufferedReader in = new BufferedReader(new FileReader("./data/banned/bannedhosts.dat"));
            String loggedIPs = null;
            while ((loggedIPs = in.readLine()) != null) {
                bannedHosts[index] = loggedIPs;
                index++;
            }
        } catch (Exception e) {
            Misc.println("Error loading banned hosts list.");
        }
    }
}