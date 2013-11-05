/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.com.codeusa.clanchat;

import java.util.Enumeration;
import java.util.Hashtable;
import net.com.codeusa.Engine;
import net.com.codeusa.model.Player;
import net.com.codeusa.util.Misc;

/**
 *
 * @author Gravediggah
 */
public class Room {

    private String name;
    private String owner;
    private static int messageCounter = 1;
    private Hashtable users = new Hashtable();
    private Hashtable banned = new Hashtable();

    public Room(String name, String owner) {
        this.name = name;
        this.owner = owner;
        users.put(owner, 7);
        if (Misc.player(owner) != null) {
            Misc.player(owner).clanRoom = this.name;
        }
        this.updateRoom();
    }

    public void kick(Player p, String player) {
        if (p != null) {
            if (p.username.equalsIgnoreCase(owner)) {
                if (users.containsKey(player)) {
                    //users.remove(player);
                    if (Misc.player(player) != null) {
                        this.leave(Misc.player(player));
                        Engine.clanChat.clear(Misc.player(player));
                        Misc.player(player).getActionSender().sendMessage(Misc.player(player), "[" + this.name + "] You have been kicked from this chat.");
                    } else {
                        users.remove(player);
                    }
                    updateRoom();
                }
            }
        }
    }
    
    public void setRank(Player p, String player, int rank) {
        if (p != null) {
            if (p.username.equalsIgnoreCase(owner)) {
                if (users.containsKey(player)) {
                    //users.remove(player);
                    if (Misc.player(player) != null) {
                    	if(users.containsKey(player)) {
	                    	users.remove(player);
	                    	users.put(player, rank);
	                        Misc.player(player).getActionSender().sendMessage(Misc.player(player), "[" + this.name + "] " + Misc.upperFirst(owner) + " changed your rank to '" + rankName(rank) + "'.");
                    	}
                    } else {
                        users.remove(player);
                    }
                    updateRoom();
                }
            }
        }
    }
    
    public String rankName(int rank) {
    	switch(rank) {
    	case 0:
    		return "Not in clan";
    	case 1:
    		return "Recruit";
    	case 2:
    		return "Corporal";
    	case 3:
    		return "Sergeant";
    	case 4:
    		return "Lieutenant";
    	case 5:
    		return "Captain";
    	case 6:
    		return "General";
    	}
    	return "None";
    }

    public void ban(Player p, String player) {
        if (p != null) {
            if (p.username.equalsIgnoreCase(owner)) {
                if (users.containsKey(player)) {
                    if (Misc.player(player) != null) {
                        this.leave(Misc.player(player));
                        Engine.clanChat.clear(Misc.player(player));
                        Misc.player(player).getActionSender().sendMessage(Misc.player(player), "[" + this.name + "] You have been banned from this chat.");
                        this.banned.put(player, name);
                    } else {
                        users.remove(player);
                    }
                    updateRoom();
                }
            }
        }
    }

    public void unban(Player p, String player) {
        if (p != null) {
            if (p.username.equalsIgnoreCase(owner)) {
                if (banned.containsKey(player)) {
                    //users.remove(player);
                    if (Misc.player(player) != null) {
                        Misc.player(player).getActionSender().sendMessage(Misc.player(player), "[" + this.name + "] You have been unbanned from this chat.");
                        this.banned.remove(player);
                    } else {
                        banned.remove(player);
                    }
                    updateRoom();
                }
            }
        }
    }

    public static int getMessageCounter() {
        return messageCounter;
    }

    public static void setMessageCounter(int messageCounter) {
        Room.messageCounter = messageCounter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Hashtable getUsers() {
        return users;
    }

    public void setUsers(Hashtable users) {
        this.users = users;
    }

    public void join(Player p) {
        if (this.banned.containsKey(p.username)) {
            p.getActionSender().sendMessage(p, "[" + Misc.upperFirst(this.name) + "] You have been banned from this chat.");
        } else {
            if (p.clanRoom.length() == 0) {
                if (this.owner.equalsIgnoreCase(p.username)) {
                    this.users.put(p.username, 7);
                } else {
                    this.users.put(p.username, 1);
                }
                p.clanRoom = this.name;
                updateRoom();
            } else {
                Engine.clanChat.leave(p);
                if (this.owner.equalsIgnoreCase(p.username)) {
                    this.users.put(p.username, 7);
                } else {
                    this.users.put(p.username, 1);
                }
                p.clanRoom = this.name;
                updateRoom();
            }
        }
    }

    public void leave(Player p) {
        this.users.remove(p.username);
        updateRoom();
    }

    public void updateRoom() {
        updateUsers();

        if (users.size() > 0) {
            Enumeration e = users.keys();
            while (e.hasMoreElements()) {
                Object o = e.nextElement();
                String userName = String.valueOf(o);
                if (Misc.player(userName) != null) {
                    update(Misc.player(userName));
                }
            }
        }

    }

    public void updateUsers() {
        if (users.size() > 0) {
            Enumeration e = users.keys();
            while (e.hasMoreElements()) {
                Object o = e.nextElement();
                int userRights = Integer.parseInt(String.valueOf(users.get(o)));
                String userName = String.valueOf(o);
                if (Misc.player(userName) == null) {
                    users.remove(userName);
                } else if (!Misc.player(userName).clanRoom.equalsIgnoreCase(this.name)) {
                    users.remove(userName);
                }
            }
        }
    }

    public void sendMessage(Player p, String message) {
        if (users.size() > 0) {
            Enumeration e = users.keys();
            while (e.hasMoreElements()) {
                Object o = e.nextElement();
                String userName = String.valueOf(o);
                if (Misc.player(userName) != null) {
                    this.sendMessage(Misc.player(userName), p.username, message);
                }
            }
        }
    }

    public void update(Player p) {
        if (users.size() > 0) {
            //Start packet
            p.getByteVector().createFrameVarSizeWord(82);
            p.getByteVector().writeQWord(Misc.stringToLong(this.owner)); // Chatroom owner
            p.getByteVector().writeQWord(Misc.stringToLong(this.name)); //Chatroom name
            p.getByteVector().writeByte(1);
            p.getByteVector().writeByte(users.size());
            //
            Enumeration e = users.keys();
            while (e.hasMoreElements()) {
                Object o = e.nextElement();
                int userRights = Integer.parseInt(String.valueOf(users.get(o)));
                String userName = String.valueOf(o);
                //Update packet
                p.getByteVector().writeQWord(Misc.stringToLong(userName));
                p.getByteVector().writeRShort(66); // World ID;
                p.getByteVector().writeByte(userRights);
                p.getByteVector().writeString("RuneScape 66");
            //
            }
            //End Packet
            p.getByteVector().endFrameVarSize();
        //
        }
    }

    public void sendMessage(Player p, String from, String message) {
        p.getByteVector().createFrameVarSize(229);
        p.getByteVector().writeQWord(Misc.stringToLong(from)); // Player Username
        p.getByteVector().writeByte(1);
        p.getByteVector().writeQWord(Misc.stringToLong(this.name)); //Room name
        p.getByteVector().writeRShort(1);
        byte[] bytes = new byte[message.length() + 1];
        bytes[0] = (byte) message.length();
        Misc.encryptPlayerChat(bytes, 0, 1, message.length(), message.getBytes());
        p.getByteVector().writeBytes(
                new byte[]{
                	(byte) ((messageCounter << 16) & 0xFF),
                    (byte) ((messageCounter << 8) & 0xFF),
                    (byte) (messageCounter & 0xFF)}, 3, 0);
        messageCounter++;
        if (Misc.player(from) != null) {
            p.getByteVector().writeByte(Misc.player(from).rights);
        } else {
            p.getByteVector().writeByte(0);
        }
        p.getByteVector().writeBytes(bytes, bytes.length, 0);
        p.getByteVector().endFrameVarSize();
    }
}