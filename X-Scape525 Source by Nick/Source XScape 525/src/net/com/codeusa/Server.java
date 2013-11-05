/*
 * Class Server
 *
 * Version 1.0
 *
 * Thursday, August 14, 2008
 *
 * Created by Palidino76-edited by d3v0n
 */

package net.com.codeusa;

import java.io.*;
import net.com.codeusa.model.games.ClanWars;
import net.com.codeusa.net.SocketListener;
import net.com.codeusa.model.PlayerSave;
import net.com.codeusa.util.Misc;
import net.com.codeusa.world.mapdata.MapData;
import net.com.codeusa.model.GE.GrandExchangeLoader;

public class Server {
    /**
      * Clan wars class 
      */
     public static ClanWars clanWars;
    
     /**
      * Loads grand exchange
      */
     public static GrandExchangeLoader GrandExchangeLoader;

    /**
     * Clan wait delay
     */
    public static int clanWaitDelay, clanFightDelay;
    /**
     * The engine used to update almost everything, such as players, items, and NPCs.
     */
    public static Engine engine;
    /**
     * Listens for incoming connections and accepts them.
     */
    public static SocketListener socketListener;
    /**
     * Save character files.
     */
    private static PlayerSave playerSave;
    /**
     * Banned accounts list.
     */
    public static String[] bannedUsers = new String[1000];

    /**
     * Main method for running the server.
     * <p>While specifying port 0 will select a random open port, it is not suggested as
     * you will not be informed on what port was selected. If you pick a port already
     * in use, the server will shut down.
     * @param args The port to run the server on, or 0 for a random port.
     */

  public static MapData mapData = null;

    public static void main(String[] args) {
        try {
            socketListener = new SocketListener(Integer.parseInt(args[0]));
        } catch (Exception e) {
            /*
             * If this happens then the specified port is most likely already in use.
             */
            e.printStackTrace();
            return;
        }
        GrandExchangeLoader = new GrandExchangeLoader();
        mapData = new MapData();
        mapData.loadRegions();
        loadBannedUsers();
        engine = new Engine();
        playerSave = new PlayerSave();
        socketListener.run();
        GrandExchangeLoader.loadOffers();
    }

    /**
     * Loads all banned users at startup.
     */
    public static void loadBannedUsers() {
        int index = 0;
        try {
            BufferedReader in = new BufferedReader(new FileReader("./data/banned/bannedusers.dat"));
            String loggedUser = null;
            while ((loggedUser = in.readLine()) != null) {
                bannedUsers[index] = loggedUser;
                index++;
            }
        } catch (Exception e) {
            Misc.println("Error loading banned users list.");
        }
    }
}