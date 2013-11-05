/*
 * Class NPCList
 *
 * Version 1.0
 *
 * Monday, August 18, 2008
 *
 * Created by Palidino76
 */

package net.com.codeusa.npcs.loading;

public class NPCList {

    public int npcType = -1;

    public String npcName = "";
 
    public String examine = "Hmmm...";

    public int combatLevel = 0;

    public int maxHP = 0;

    public int maxHit = 0;

    public int atkType = 0;

    public int meleeDef = 0;

    public int weakness = 0;

    public int respawnDelay = 60;

    public int size;

    public NPCList(int id, String name, String view, int cb, int hp, int maxhit, int atk, int weak, int defence, int spawnTime, int size) {
        npcType = id;
        npcName = name;
        examine = view;
        combatLevel = cb;
        maxHP = hp;
        maxHit = maxhit;
        atkType = atk;
        weakness = weak;
	meleeDef = defence;
        respawnDelay = spawnTime;
	this.size = size;
    }
}