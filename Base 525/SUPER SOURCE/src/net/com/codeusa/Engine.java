/*
 * Class Engine
 *
 * Version 1.0
 *
 * Thursday, August 14, 2008
 *
 * Created by Palidino76 - edited by d3v0n
 */

package net.com.codeusa;

import java.net.Socket;
import net.com.codeusa.net.codec.*;
import javax.swing.JFrame;
import net.com.codeusa.model.Player;
import net.com.codeusa.model.items.*;
import net.com.codeusa.model.update.*;
import net.com.codeusa.model.games.*;
import net.com.codeusa.world.npcs.Movement;
import net.com.codeusa.world.mapdata.MapData;
import net.com.codeusa.world.items.Items;
import net.com.codeusa.io.*;
import net.com.codeusa.world.clipInfo;
import net.com.codeusa.model.games.BountyHunter;
import net.com.codeusa.model.combat.PlayerMagic;
import net.com.codeusa.util.Misc;
import net.com.codeusa.npcs.NPC;
import net.com.codeusa.npcs.update.*;
//import net.com.codeusa.util.clipInfo;
import net.com.codeusa.npcs.loading.*;
import net.com.codeusa.model.GE.GrandExchangeLoader;
import net.com.codeusa.model.GE.geList;
import net.com.codeusa.model.GE.geLoader;
import net.com.codeusa.clanchat.ClanChat;

import net.com.codeusa.npcs.SmartNPC;



public class Engine implements Runnable {
    public static int updateTime = -1;
 /**
     * Shops
     */
public static ShopHandler shopHandler = new ShopHandler();
public static Shops     shops                  = new Shops();
    /**
     * Loads and stores map data for map regions.
     */
    public static MapData mapData = new MapData();

     public static BountyHunter BountyHunter = new BountyHunter();

     public static PlayerMagic playermagic = new PlayerMagic();

     public static geLoader geLoader = new geLoader();

public static clipInfo clipInfo = new clipInfo();

      public static BountyHunter bountyhunter = new BountyHunter();
    
    //public static clipInfo clipInfo = new clipInfo();
    /**
     * Max players allowed.
     */
    public static int maxPlayers = 500;
    /**
     * When a new player logs in, a new player is created and stored in this array.
     * <p>The server cannot exceed 2000 players.
     * The id 0 cannot be used as it is not handled by the client corrently.
     
}

        /**
        GUI
        */
        public void Startup() {
                Gui Startupp = new Gui();
                Startupp.setVisible(true); //makes it visible.
                Startupp.setResizable(false);
        }
        
        
        
    /***/
    public static Player[] players = new Player[1500];
    /**
     * Max id an NPC can have loaded out of the npclist config.
     */
    public static int maxListedNPCs = 10000;
    /**
     * When a new NPC is created, it is stored in this array.
     * <p>There can not be more than 5000 NPCs spawned at a time.
     * The id 0 cannot be used as it is not handled by the client corrently.
     */
    public static NPC[] npcs = new NPC[10000];
    /**
     * Contains NPC data such as respawn times, names, examines, etc.
     */

	public static int maxSN = 1000;
	public static SmartNPC[] SN = new SmartNPC[maxSN];

    public static NPCList[] npcLists = new NPCList[maxListedNPCs];
    /**
      * Clan warshandler
      */
    public static ClanWars clanWars = new ClanWars();

   public static ClanChat clanChat = new ClanChat();
    /**
     * The class thread.
     */
    private Thread engineThread = new Thread(this);
    /**
     * Set true if the engine should run.
     */
    private boolean engineRunning = true;
    /**
     * Handles packets recieved from the server.
     */
    public static Packets packets = new Packets();
    /**
     * Contains a method for every known frame.
     */
    public static ActionSender actionSender = new ActionSender();
    /**
     * Handles player movement.
     */
    public static PlayerMovement playerMovement = new PlayerMovement();
    /**
     * Contains all item information.
     */
    public static Items items;
    /**
     * Handles player updates.
     */
    public static PlayerUpdate playerUpdate = new PlayerUpdate();
    /**
     * Handles NPC movement.
     */
    public static NPCMovement npcMovement = new NPCMovement();
    /**
     * Handles NPC updates.
     */
    public static NPCUpdate npcUpdate = new NPCUpdate();
    /**
     * Handles players items.
     */
    public static PlayerItems playerItems = new PlayerItems();
    /**
     * Handles file saving and loading.
     */
    public static FileManager fileManager = new FileManager();
    public static PlayerBank playerBank = new PlayerBank();
    /**
     * Handles thread
     */
    public Thread neccesaryThread;

    /**
     * Constructs a new Engine and loads item and NPC data from configs.
     */
    public Engine() {

	LoadSmartNPC LSN = new LoadSmartNPC();
		
        items = new Items(this);
        LoadNPCLists lnl = new LoadNPCLists();
        lnl = null;
        engineThread.start();
    }

    /**
     * Returns 4 coordinates to get wilderness coords.
     */
    public static boolean inWilderness(Player p) {
	return p.absX >= 3038 && p.absX <= 3138 && p.absY >= 3524 && p.absY <= 3584;
    }

    /**
     * The thread's process.
     * <p>This processes at a rate of 100 milliseconds, and the processing time
     * is subtracted from the amount of time it sleeps to keep at a rate of 100
     * milliseonds. Packets are checked every 100 milliseconds for faster response time,
     * and most other updates are processed every 600 milliseconds.
     */
    public void run() {
	//addStaticObject(id, height, x, y, face, type);
        long lastEntityUpdate = 0;
	long lastEntityUpdate2 = 0;
        long curTime;
  Startup();        while (engineRunning) {
            curTime = System.currentTimeMillis();
            connectAwaitingPlayers();
            packets.parseIncomingPackets();
		if (curTime - lastEntityUpdate2 >= 1000) {
			for (Player p : players) {
				if (p == null || !p.online) {
					continue;
				}
			}
			lastEntityUpdate2 = curTime;
		}
            if (curTime - lastEntityUpdate >= 600) {
                if(updateTime > 0) {
		    updateTime--;
		}
		if(updateTime == 0) {
		    try {
		        Server.GrandExchangeLoader.saveOffers();
		    } catch(Exception e) { }
		    System.exit(0);
		}
                items.process();
	clanWars.process();
                for (Player p : players) {
                    // Proccess and player movement, removing disconnected players.
                    if (p == null || !p.online) {
                        continue;
                    }
                    if (p.disconnected[0] && p.disconnected[1]) {
                        removePlayer(p.playerId);
                        continue;
                    }
                    p.process();
                    playerMovement.getNextPlayerMovement(p);
                }
                for (Player p: players) {
                    // Update players.
                    if (p == null || !p.online) {
                        continue;
                    }
                    playerUpdate.update(p);
                }
                for (Player p : players) {
                    // Reset masks and send bytes.
                    if (p == null || !p.online) {
                        continue;
                    }
                    playerUpdate.clearUpdateReqs(p);
                    Server.socketListener.writeBuffer(p);
                }
                for (NPC n : npcs) {
                    if (n == null) {
                        continue;
                    }
                    npcUpdate.clearUpdateMasks(n);
                }
                for (NPC n : npcs) {
                    if (n == null) {
                        continue;
                    }
                    n.process();
                    if (!n.isDead) {
                        if (n.randomWalk) {
                            npcMovement.randomWalk(n);
                        }
                    } else {
                        if(!n.deadEmoteDone)
                        {
                            n.deadEmoteDone = true;
                            n.combatDelay = n.getDeathDelay1();
                        }
                        else if(n.deadEmoteDone && !n.hiddenNPC && n.combatDelay <= 0)
                        {
                            n.hiddenNPC = true;
                        }
                        else if(n.hiddenNPC && n.respawnDelay <= 0)
                        {
                            npcs[n.npcId] = null;
                            rebuildNPCs();
                            if(n.needsRespawn)
                            {
                                newNPC(n.npcType, n.makeX, n.makeY, n.heightLevel, n.moveRangeX1, n.moveRangeY1, 
                                n.moveRangeX2, n.moveRangeY2, true, 0);
                            }
			    
                        }
                    }
                    lastEntityUpdate = curTime;
                }
            }
            try {
                Thread.sleep(100 - (System.currentTimeMillis() - curTime));
            } catch (Exception e) {
            }
        }
    }

    /**
     * Assign a player to the connection.
     * <p>When a new connection is recieved, a new Player class is created which waits for the run method
     * to run the Login class to finish the connection. If the id is 0, or the server is full, no
     * connection is made and the socket is closed.
     * @param socket The socket the new player is connected to.
     * @param id The id which the new player will be created at.
     */
    public void addConnection(Socket socket, int id) {
        if (id == 0) {
            Server.socketListener.removeSocket(id);
            return;
        }
        players[id] = new Player(socket, id);
    }

    /**
     * Run the login class to finish a new connection.
     * <p>Loops through all the players looking for any that are not online.
     * Because the socket can't cause the server to process slower, there
     * are multiple login stages, and the player will go through the login class
     * two or three times before fully logging in.
     */
    public void connectAwaitingPlayers() {
        RS2LoginProtocol login = null;
        for (Player p : players) {
            if (p == null || p.online) {
                continue;
            }
            if (login == null)
                login = new RS2LoginProtocol();
            	login.login(p);
            if (!p.online && p.loginStage == -1 || (System.currentTimeMillis() - p.loginTimeout) >= 15000) {
                /*
                 * Remove the player if he failed to connect or it was the update server.
                 */
                removePlayer(p.playerId);
            }	
        }
    }

    /**
     * Discard a player with the specified id in the player array.
     * <p>This method should only be called when the player is ready to be removed.
     * This nulls closes the players socket and then nulls the player.
     * @param id The position in the player array that should be removed.
     */
    public void removePlayer(int id) {
        if (players[id] == null) {
            return;
        }
        /*if (players[id].online) {
            try {
                fileManager.saveCharacter(players[id]);
            } catch (Exception e) {
            }
        }*/
        players[id].destruct();
        players[id] = null;
        Server.socketListener.removeSocket(id);
    }

    /**
     * Check player count.
     */
    public static int getPlayerCount() {
        int count = 0;

        for (Player p : players) {
            if (p != null) {
                count++;
            }
        }
        return count;
    }

    /**
      * get player count that are in a clan.
      */
    public static int getBlackClanPlayerCount(int blackCount) {
	for (Player p : players) {
		if (p != null) {
			if (p.clanWarsFightArea()) {
				if (p.blackTeam)
					blackCount++;
			}
		}
	}
	return blackCount;
    }

/*Method By: Tokyomewmew. */
public static int newNPC2(int type, int absX, int absY, int face, int height, int mRX1, int mRY1, int mRX2, int mRY2, boolean needsRespawn, int playerIndex) { // The start of the method
	    int index = -1; // Index int
	    for (int i = 1; i < npcs.length; i++) { // Loops through all npc ids
if (npcs[i] == null) { // If the npc is null
	    index = i; // Index
	    break; // Break
} // End of loop
} // End of null pointer
if (index == -1) { // If the Index is -1
	    Misc.println("Max number of NPCs spawned."); // Prints a message
	    return -1; // Set's the return of the index to -1
} // end of statement
	    NPC n = npcs[index] = new NPC(type, index); // Int n
if (n.currentHP <= 0) { // If the npc's hp is 0
	    n.currentHP = 100; // Set's the npc's hp to 100
} // End of statement
	    n.absX = absX; // Npc's X abs
	    n.absY = absY; // Npc's Y abs
	    n.makeX = absX; // Npc's  X abs again?
	    n.makeY = absY; // Npc's Y abs again?
	    n.faceType = face; // Npc's Facing direction
	    n.heightLevel = height; // Npc Height level
	    n.moveRangeX1 = mRX1; // SE Walking X coordinate
	    n.moveRangeY1 = mRY1; //  SE Walking Y coordinate
	    n.moveRangeX2 = mRX2; // NW Walking X coordinate
	    n.moveRangeY2 = mRY2; // NW Walking Y coordinate
	    n.needsRespawn = needsRespawn; // Npc needs a respawn
	    NPCList nl = npcLists[type]; // Calls code from another file.
if (nl != null) { // if the npclistID is null
	    n.name = nl.npcName; // Npc's name
	    n.combatLevel = nl.combatLevel; // Npc's combat level
	    n.maxHP = nl.maxHP; // Npc's MAX HP
	    n.currentHP = n.maxHP; // Npc's current hp
	    n.maxHit = nl.maxHit; // Npc's MAX hit
	    n.atkType = nl.atkType; // Npc's attack type
	    n.weakness = nl.weakness; // Npc's weakness
	    n.respawnDelay = nl.respawnDelay; // Npc respawn delay
	    n.size = nl.size; // Npc's size (square's)
} // end of null pointer
	    return index; // Set's the return to the index
} // end of method

    /**
      * get player count that are in a clan.
      */
    public static int getWhiteClanPlayerCount(int whiteCount) {
	for (Player p : players) {
		if (p != null) {
			if (p.clanWarsFightArea()) {
				if (p.whiteTeam)
					whiteCount++;
			}
		}
	}
	return whiteCount;
    }

    /**
     * This method tells every player to re-add any NPCs within distance.
     * <p>By calling this method, with the next NPC update it will discard the NPC list and
     * re-loop through every NPC and re-add them if they fit the specifications.
     */
    public void rebuildNPCs() {
        for (Player p : players) {
            if (p == null) {
                continue;
            }
            p.rebuildNPCList = true;
        }
    }

    /**
     * Returns a players id based on their username, or 0 if the player is not online.
     * <p>This will check if a player is online based on searching every characters username,
     * and comparing it to playerName. Because index 0 is not used and won't throw an
     * ArrayoutofBounds exception error, 0 is returned if the username isn't found.
     * @param playerName The name to compare with the other online player names.
     * @return Returns the index of the player with that username, or 0 if the username isn't found.
     */
public static int getIdFromName(String playerName) {
playerName.replaceAll("_", " ");
for (Player p : players) {
if (p == null) {
continue;
}
if (p.username.equalsIgnoreCase(playerName)) {
return p.playerId;
}
}
return 0;
}

	public int getNPCIndex(int NPC) {
		for (SmartNPC s : SN) {
			if (NPC == s.ID) {
				return s.index;
			}
		}
		return -1;
	}

    /**
     * Returns the description of npcId.
     * @param npcId The NPC to get the examine for.
     * @return Returns the examine.
     */
    public String getNPCDescription(int npcId) {
        if (npcId == -1 || npcId >= maxListedNPCs) {
            return new String("An NPC.");
        }
        if (npcLists[npcId] != null) {
            return (npcLists[npcId].examine);
        }
        return new String("NPC " + npcId);
    }

    public String getNPCName(int npcType)
    {
        if(npcType > maxListedNPCs)
        {
            return new String("NPCType = " + npcType);
        }
        else if(npcLists[npcType] != null)
        {
            return npcLists[npcType].npcName;
        }
        else
        {
            return new String("NPCType = " + npcType);
        }
    }

    /**
     * Spawn a new NPC.
     * <p>This will create a new visible NPC, with the default values from its list and the parameters.
     * @param type The type of NPC this is, such as 50 for the King black dragon.
     * @param absX The absolute x coordinate to spawn the NPC.
     * @param absY The absolute y coordinate to spawn the NPC.
     * @param height The height level to set the NPC.
     * @param mRXY1 The distance it can walk west; must be lower than absX to have any effect.
     * @param mRY1 The distance it can walk south; must be lower than absY to have any effect.
     * @param mRX2 The distance it can walk east; must be higher than absX to have any effect.
     * @param mRY2 The distance it can walk north; must be higher than absY to have any effect.
     * @param needsRespawn Set to true if the NPC should respawn after it dies.
     * @return Returns the index the NPC was placed at.
     */
    public static int newNPC(int type, int absX, int absY, int height, int mRX1, int mRY1, int mRX2, int mRY2, boolean needsRespawn, int playerIndex) {
        int index = -1;
        for (int i = 1; i < npcs.length; i++) {
            if (npcs[i] == null) {
                index = i;
                break;
           }
        }
        if (index == -1) {
            Misc.println("Max number of NPCs spawned.");
            return -1;
        }
        NPC n = npcs[index] = new NPC(type, index);
	if (n.currentHP <= 0) {
		n.currentHP = 100;
	}
	if (type == 7771) {
		n.brokenArmour = 8;
	}
        n.absX = absX;
        n.absY = absY;
        n.makeX = absX;
        n.makeY = absY;
        n.heightLevel = height;
        n.moveRangeX1 = mRX1;
        n.moveRangeY1 = mRY1;
        n.moveRangeX2 = mRX2;
        n.moveRangeY2 = mRY2;
        n.needsRespawn = needsRespawn;
	for (int i = 0; i < n.summoningMonsters.length; i++) {
		if (type == 4284 || type == n.summoningMonsters[i])
			n.spawnedFor = playerIndex;
	}
	if (type == 4284)
		n.underAttack = true;
	if (type == 4284)
		n.requestText("YES! I'M ALIVE!");
        NPCList nl = npcLists[type];
        if (nl != null) {
            n.name = nl.npcName;
            n.combatLevel = nl.combatLevel;
            n.maxHP = nl.maxHP;
            n.currentHP = n.maxHP;
            n.maxHit = nl.maxHit;
            n.atkType = nl.atkType;
            n.weakness = nl.weakness;
            n.respawnDelay = nl.respawnDelay;
	    n.size = nl.size;
        }
        return index;
    }

	public static void newSmartNPC(int[] args) {
	}

}