/*
 * Class LoadNPCLists
 *
 * Version 1.0
 *
 * Monday, August 18, 2008
 *
 * Created by Palidino76
 */

package net.com.codeusa.npcs.loading;

import java.io.*;
import net.com.codeusa.Engine;
import net.com.codeusa.util.Misc;

public class LoadNPCLists {

    public LoadNPCLists() {
        loadNPCList();
        loadNPCs();
    }

    /*Loads NPCs which will be spawned. By: Tokyomewmew. */
private boolean loadNPCs() {
	    String line = "", token = "", token2 = "", token2_2 = "", token3[] = new String[10];
	    BufferedReader cfgFile = null;
try {
	    cfgFile = new BufferedReader(new FileReader("./data/npcs/npcspawn.cfg"));
	    line = cfgFile.readLine().trim();
} catch (Exception e) {
	    Misc.println("Error loading NPCs.");
	    line = token = token2 = token2_2 = null;
	    token3 = null;
	    return false;
}
	    while(line != null) {
	    int index = line.indexOf("=");
if (index > -1) {
	    token = line.substring(0, index).trim();
	    token2 = line.substring(index + 1).trim();
	    token2_2 = token2.replaceAll("\t\t", "\t");
	    token3 = token2_2.split("\t");
if (token.equals("spawn")) {
	    Engine.newNPC2(Integer.parseInt(token3[0]), Integer.parseInt(token3[1]), Integer.parseInt(token3[2]), Integer.parseInt(token3[3]), Integer.parseInt(token3[4]), Integer.parseInt(token3[5]), Integer.parseInt(token3[6]), Integer.parseInt(token3[7]), Integer.parseInt(token3[8]), true, 0);
}
} else if (line.equals("[ENDOFSPAWNLIST]")) {
try {
	    cfgFile.close();
} catch (Exception ioe) {
}
	    cfgFile = null;
	    line = token = token2 = token2_2 = null;
	    token3 = null;
	    return true;
}
try {
	    line = cfgFile.readLine();
} catch (Exception ioe) {
	    line = null;
	    line = token = token2 = token2_2 = null;
	    token3 = null;
	    cfgFile = null;
	    return false;
}
}
try {
	    cfgFile.close();
	    cfgFile = null;
} catch (Exception ioe) {
} // end of catach exception
	    return false;
} // end of methodstep 4: final step, layout of your npcspawn.cfg

    private boolean loadNPCList() {
        String line = "", token = "", token2 = "", token2_2 = "", token3[] = new String[10];
        BufferedReader list = null;
        try {
            list = new BufferedReader(new FileReader("./data/npcs/npclist.cfg"));
            line = list.readLine().trim();
        } catch (Exception e) {
            Misc.println("Error loading NPC Lists.");
            return false;
        }
        while (line != null) {
            int spot = line.indexOf("=");
            if (spot > -1) {
                token = line.substring(0, spot).trim();
                token2 = line.substring(spot + 1).trim();
                token2_2 = token2.replaceAll("\t\t", "\t");
                token2_2 = token2_2.replaceAll("\t\t", "\t");
                token2_2 = token2_2.replaceAll("\t\t", "\t");
                token3 = token2_2.split("\t");
                if (token.equals("npc")) {
                    newNPCList(Integer.parseInt(token3[0]), Integer.parseInt(token3[1]), Integer.parseInt(token3[2]), 
                    Integer.parseInt(token3[3]), Integer.parseInt(token3[4]), Integer.parseInt(token3[5]), 
                    Integer.parseInt(token3[6]), Integer.parseInt(token3[7]), Integer.parseInt(token3[8]), token3[9].replaceAll("_", " "), token3[10].replaceAll("_", " "));
                }
            } else {
                if (line.equals("[ENDOFNPCLIST]")) {
                    try {
                        list.close();
                    } catch (Exception ioexception) {
                    }
                    list = null;
                    return true;
                }
            }
            try {
                line = list.readLine().trim();
            } catch (Exception ioexception1) {
                try {
                    list.close();
                } catch (Exception ioexception) {
                }
                list = null;
                return true;
            }
        }
        return false;
    }

    private void newNPCList(int npcType, int cbLevel, int maxHP, int maxHit, int atkType, int weakness, int defence, int spawnTime, int size, String name, String examine) {
        if (npcType >= Engine.npcs.length) {
            Misc.println("maxListedNPCs size is to low.");
            return;
        }
        Engine.npcLists[npcType] = new NPCList(npcType, name, examine, cbLevel, maxHP, maxHit, atkType, weakness, defence, spawnTime, size);
    }

}