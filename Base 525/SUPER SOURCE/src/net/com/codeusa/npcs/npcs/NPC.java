/*
 * Class NPC
 *
 * Version 1.0
 *
 * Monday, August 18, 2008
 *
 * Created by Palidino76
 */

package net.com.codeusa.npcs;

import net.com.codeusa.*;
import net.com.codeusa.util.*;
import net.com.codeusa.npcs.loading.*;
import net.com.codeusa.model.*;
import net.com.codeusa.npcs.combat.*;

public class NPC {
/* Death - made by ShedYourBlood */
    public int deathshow = -1;
    public int RandomMess = Misc.random(8);
/*Npc's Facing Direction's. By: Tokyomewmew. */
	    public int faceType; // Facing direction's
    /**
     * Stray dog variables
     */
    public int dogBossy;
    public boolean followingBossy;
    /**
     * Killing count 1v1 at NPCs
     */
    public int killingCount;
    public boolean playerTele;
    public int[] kalphiteMonsters = {
	1153, 1154, 1155, 1156, 1157, 1158, 1160
    };
    public int[] godWarsBosses = {
	6203, 6260, 6247
    };
    public int[] fightCaveNpcs = {
	2734, 2735, 2736, 2737, 2738, 2739, 2740, 2741, 2742, 2743, 2744
    };
    public int[] someNpcs = {
	2745, 4284
    };
    public boolean spawnedHealers;
    public int projectileShowDelay = -1;
    public int[] largeNpcs = {
	50, 1158, 1160, 3847, 6222, 6203, 6260, 6247, 
    };
    /**
     * Summoning
     */
    public int[] summoningMonsters = {
	6829, 6825, 6841, 6806, 7361, 6853, 6855, 6857, 6859, 6861, 6863, 7355, 7357, 7359, 7341, 7329, 7339, 7349, 7375, 7343,
    };
    /**
     * 1 V 1
     */
    public int spawnedFor;
    /**
     * An array with NPC ids of npcs which are animated armours.
     */
    public int[] animatedArmours = {
	4284
    };
    /**
     * An array with NPC ids of npcs which check on combat levels for agression
     */
    public int[] combatNpcs = {
	6625, 1153, 1154, 1156, 6998, 6999, 6729, 6625
    };
    /**
     * An array with NPC ids of npcs which heal themself.
     */
    public int[] healingNpcs = {
	6729, 6625, 6691
    };
    public int walkX, walkY;
    public boolean revenantRangedAttk;
    public int size;
    /**
     * The delay for gfx appearing on KQ.
     */
    public int changeAnimDelay;
    public int kqGfxDelay = -1, kqDeathDelay = -1, kqDeathDelay1 = -1;
    public int brokenArmour;
    public boolean kalphRanged;
    public int[] agressionNpcs = {
	50, 2745, 6203, 6204, 6208, 907, 6222, 6220, 1158, 1160, 1153, 1154, 1155, 1156, 1157,
	5363, 53, 54, 55, 2744, 2743, 6223, 6225, 6227, 6260, 6261, 6998, 6999, 7770, 7771, 134,
	2734, 2735, 107, 5902, 3847, 6729, 6625, 6691, 6247, 6250, 2739, 2740, 2745, 6252, 6248, 6265, 6261, 2746,
	5253, 6261, 6263, 6265, 5417, 5421, 5420,8133
    };
    /**
     * NPC vs NPC variables
     */
    public int[] npcMagics = {
	2253, 6257
    };
    public int damageDelay = -1;
    public int attackDelay;
    public boolean attackingNpc;
    public int npcEnemy;
    /**
     * Array with demon types npcs.
     */
    public int[] demonTypes = {
	82, 83, 84, 6203, 6204, 6208
    };
    public boolean appendDrop, appendDrop1;
    /**
     * Range/mage hit delay.
     */
    public boolean mageType, rangeType;
    public int rockAppear = -1;
    public int farCastDelay = -1;
    /**
     * A simple array with npc ids in it which are mage npcs.
     */
    public int[] mageNpcs = {
	50, 2743, 2744, 2745, 5363, 55, 53, 907, 6222, 6220, 1158, 1160, 6203, 6208, 6223, 6225, 6227, 6998, 6999, 5902, 3847, 6729, 6625, 6691,
	6250, 2739, 2740, 2745, 6252, 1472, 6263, 6265,8133
    };
    /**
     * Defence
     */
    public int meleeDef;
    /**
     * Death delay
     */
    public int deathDelay;
    /**
     * Player index (enemy) (combat)
     */
    public int playerIndex;
    /**
     * Attacking Player.
     */
    public boolean underAttack;
    /**
     * Atk delay
     */
    public int atkDelay;
    /**
     * Used to make 1v1.
     */
    public int battleCDelay;
    public int battleCount;
    /**
     * The position this NPC is stored in the npc array in the Engine class.
     */
    public int npcId = -1;
    /**
     * The NPC's id, such as 50 for king black dragon.
     */
    public int npcType = 0;
    /**
     * The name of this NPC.
     */
    public String name = "";
    /**
     * If it should hit 0 HP, how long it should take to respawn.
     */
    public int respawnDelay = 60;
    /**
     * Its combat level, used for calculating its accuracy and defence.
     */
    public int combatLevel = 0;
    /**
     * Max hitpoints it can have.
     */
    public int maxHP = 0;
    /**
     * The basic max hit it can have, with an attack method you  can get more specific.
     */
    public int maxHit = 0;
    /**
     * Attack type, 0 for melee, 1 for range, 2 for magic.
     */
    public int atkType = 0;
    /**
     * The weakness, same setup as atkType.
     */
    public int weakness = 0;
    /**
     * The current HP this NPC has.
     */
    public int currentHP = 0;
    /**
     * The direction this NPC is moving in.
     */
    public int direction = -1;
    /**
     * The height level its at.
     */
    public int heightLevel = 0;
    /**
     * Request for updating facing.
     */
    public boolean faceToUpdateReq = false;
    public int faceToRequest = -1;
    /**
     * If the NPC is dead or not.
     */
    public boolean isDead = false;
    /**
     * If the death emote has been requested, move on to the next part of the death process.
     */
    public boolean deadEmoteDone = false;
    /**
     * Hide the NPC until it is ready to respawn.
     */
    public boolean hiddenNPC = false;
    /**
     * Absolute positioning.
     */
    public int absX = 0, absY = 0;
    /**
     * If false, the NPC will not respawn if it dies.
     */
    public boolean needsRespawn = false;
    /**
     * If set to true, this NPC will randomly walk around.
     */
    public boolean randomWalk = true;
    /**
     * If an update is needed.
     */
    public boolean updateReq = false;
    /**
     * If the NPC should speak.
     */
    public boolean speakTextUpdateReq = false;
    public String speakText = "";
    /**
     * Hit requests.
     */
    public boolean hit1UpdateReq = false;
    public boolean hit2UpdateReq = false;
    public int hitDiff1 = 0;
    public int posionHit1 = 0;
    public int hitDiff2 = 0;
    public int posionHit2 = 0;
    /**
     * Animation request.
     */
    public boolean animUpdateReq = false;
    public int animRequest = 65535;
    public int animDelay = 0;
    /**
     * Graphic request.
     */
    public boolean gfxUpdateReq = false;
    public int gfxRequest = 65535;
    public int gfxDelay = 0;
    /**
     * Facing request.
     */
    public boolean faceCoordsUpdateReq = false;
    public int faceCoordsX = -1;
    public int faceCoordsY = -1;
    /**
     * The area this NPC can randomly walk around.
     */
    public int moveRangeX1 = 0;
    public int moveRangeY1 = 0;
    public int moveRangeX2 = 0;
    public int moveRangeY2 = 0;
    /**
     * What should be added onto the absolute positioning if this NPC moves.
     */
    public int moveX = 0;
    public int moveY = 0;
    /**
     * The original position the NPC spawned at.
     */
    public int makeX = 0;
    public int makeY = 0;
    /**
     * Delay before the NPC can attack again.
     */
    public int combatDelay = 0;

    /**
     * Constructs a new NPC class.
     * @param type The type of NPC.
     * @param index The position the NPC is at.
     */
    public NPC(int type, int index) {
        npcType = type;
        npcId = index;
	size = 0;
    }

    /**
     * This method is called every 600 milliseconds.
     */
    public void process() {
setFacing();
if (npcType == 2862) {
if(RandomMess == 0)
{
       requestText("Now is the time you die...");
}
if(RandomMess == 1)
{
       requestText("Let me escort you to Hell. ");
}
if(RandomMess == 2)
{
       requestText("I have come for you! ");
}
if(RandomMess == 3)
{
       requestText("You are mine! ");
}
if(RandomMess == 4)
{
       requestText("Muahahahahaha. ");
}
if(RandomMess == 5)
{
       requestText("There is no escape. ");
}
if(RandomMess == 6)
{
       requestText("Beware Mortals. You travel with me now.");
}
if(RandomMess == 7)
{
       requestText("I claim you as my own. ");
}
if(RandomMess == 8)
{
       requestText("Your time here is over. ");
}
	requestAnim(392, 0);
	deathshow = 6;
	}
		if (deathshow >= 0 && npcType == 2862) {
	    isDead = true;
	    deathshow--;
	}

        if (respawnDelay > 0 && isDead) {
            respawnDelay--;
        }
	for (int n = 0; n < godWarsBosses.length; n++) {
		if (npcType == godWarsBosses[n]) {
			if (underAttack)
				requestText(getRandomGWDText());
		}
	}
	if (npcType == 2745) {
		if (currentHP >= 1) {
			if (!spawnedHealers) {
				if (currentHP <= 180) {
					for (int i = 0; i < 3; i++)
						Server.engine.newNPC(2746, 2400 + Misc.random(2), 5144 + Misc.random(2), heightLevel, 0, 0, 0, 0, false, spawnedFor);
					spawnedHealers = true;
				}
			}
		}
	}
	if (npcType == 2746) {
		if (currentHP >= 1) {
			for (int i = 0; i < 10000; i++) {
				if (this == null || currentHP <= 0)
					return;
				if (Server.engine.npcs[i] == null)
					continue;
				if (Misc.random(20) == 5) {
					if (Server.engine.npcs[i].npcType == 2745 && heightLevel == Server.engine.npcs[i].heightLevel) {
						if (currentHP <= 0 || this == null || heightLevel != Server.engine.npcs[i].heightLevel)
							return;
						if (Server.engine.npcs[i].currentHP > 316) {
							Server.engine.npcs[i].currentHP = 316;
							return;
						}
						if (Server.engine.npcs[i].currentHP <= 315) {
							Server.engine.npcs[i].requestGFX(444, 100);
							Server.engine.npcs[i].currentHP += Misc.random(25);
						}
					}
				}
			}
		}
	}
	if (projectileShowDelay > 0)
	    projectileShowDelay--;
	if (projectileShowDelay == 0) {
	    NpcCombat npcAttack1 = new NpcCombat(this);
	    npcAttack1.createNPCProjectile();
	}
	if (changeAnimDelay > 0)
	    changeAnimDelay--;
	if (damageDelay > 0)
	    damageDelay--;
	if (damageDelay == 0) {
	    VersusNpc npcAttack = new VersusNpc(this);
	    npcAttack.addDamageToNpcEvent();
	}
	if (kqGfxDelay > 0) {
	    kqGfxDelay--;
	}
	if (kqGfxDelay == 0) {
	    absX++;
	    absY += 2;
	    heightLevel = 0;
	    npcType = 1161;
	    Server.engine.rebuildNPCs();
	    requestGFX(1055, 0);
	    kqGfxDelay = -1;
	}
	if (kqDeathDelay > 0) {
	    kqDeathDelay--;
	}
	if (kqDeathDelay == 0) {
	    requestAnim(6242, 0);
	    kqGfxDelay = 3;
	    kqDeathDelay = -1;
	}
	if (attackDelay > 0) {
	    attackDelay--;
	}
	if (attackDelay == 0 && attackingNpc) {
	    VersusNpc versusNpc = new VersusNpc(this);
	    versusNpc.appendMelee();
	}
	if (farCastDelay > 0) {
	    farCastDelay--;
	}
	if (farCastDelay == 0) {
	    NpcCombat npcCb2 = new NpcCombat(this);
	    npcCb2.appendFarcastDamage();
	}
	if (rockAppear > 0) {
	    rockAppear--;
	}
	if (rockAppear == 0) {
	    NpcCombat npcCombat = new NpcCombat(this);
	    npcCombat.appendRock();
	}
	if (deathDelay > 0) {
	    deathDelay--;
	}
	if (currentHP <= 0 && !isDead) {
	    isDead = true;
	    deathDelay = 6;
	}
	if (isDead && deathDelay == 0) {
	    appendDeath();
	}
        if (combatDelay > 0) {
            combatDelay--;
        }
	if (battleCDelay > 0) {
	    battleCDelay--;
	}
	if (battleCDelay == 0) {
	    battleCount = 0;
	    battleCDelay = -1;
	}
	if (atkDelay > 0) {
	    atkDelay--;
	}
	if (underAttack) {
	    NpcCombat npcCb = new NpcCombat(this);
	    npcCb.appendAttackPlayer();
	}
	for (int s = 0; s < summoningMonsters.length; s++) {
		if (npcType == summoningMonsters[s]) {
			Player p = Server.engine.players[spawnedFor];
			if (p == null || this == null || Server.engine.npcs[spawnedFor] == null || p.disconnected[0]) {
				return;
			}
			NpcCombat combat = new NpcCombat(this);
			if (!p.familiarDissMiss) {
				if (Misc.getDistance(absX, absY, p.absX, p.absY) > 9 || p.callFamiliar) {
					absX = p.absX - 1;
					absY = p.absY;
					heightLevel = p.heightLevel;
					Server.engine.rebuildNPCs();
					p.callFamiliar = false;
				}
				if (Misc.random(10) == 7)
					requestText("summoning comeing soon");
				combat.appendPlayerFollowing(p);
				requestFaceCoords(p.absX, p.absY);
				requestFaceTo(spawnedFor);
			} else if (p.familiarDissMiss || p == null || p.disconnected[0]) {
				absX = 0;
				absY = 0;
				heightLevel = 0;
				p.summonDrainDelay = -1;
				Server.engine.npcs[spawnedFor] = null;
				Server.engine.rebuildNPCs();
				spawnedFor = 0;
				p.summonedFamiliar = false;
				p.familiarDissMiss = false;
			}
		}
	}
	for (int j = 0; j < healingNpcs.length; j++) {
		if (npcType == healingNpcs[j]) {
			if (currentHP <= getCurrentNpcHP()) {
				if (Misc.random(10) == 5)
					currentHP += Misc.random(20);
			}
		}
	}
	if (npcType == 7771) {
		if (brokenArmour == 0) {
			requestGFX(1629, 0);
			npcType = 7770;
			Server.engine.rebuildNPCs();
			brokenArmour = -1;
		}
	}
	for (int i = 0; i < 10000; i++) {
		if (Server.engine.npcs[i] == null)
			continue;
		VersusNpc versNpc = new VersusNpc(this);
		if (npcType == 158) {
			if (Server.engine.npcs[i].npcType == 158) {
				if (npcEnemy > 0 || Server.engine.npcs[i].currentHP <= 0 || currentHP <= 0 || this == null || Server.engine.npcs[i] == null) {
					versNpc.resetAttack();
					return;
				}
				randomWalk = false;
				npcEnemy = i;
				attackingNpc = true;
				Server.engine.npcs[i].randomWalk = false;
			}
		}
		if (npcType == 3846) {
			if (Server.engine.npcs[i].npcType == 2253) {
				if (npcEnemy > 0 || Server.engine.npcs[i].currentHP <= 0 || currentHP <= 0 || this == null || Server.engine.npcs[i] == null) {
					versNpc.resetAttack();
					return;
				}
				randomWalk = false;
				npcEnemy = i;
				attackingNpc = true;
				Server.engine.npcs[i].randomWalk = false;
			}
		}
		if (npcType == 2253) {
			if (Server.engine.npcs[i].npcType == 3846) {
				if (Misc.getDistance(absX, absY, Server.engine.npcs[i].absX, Server.engine.npcs[i].absY) <= 7) {
					if (npcEnemy > 0 || Server.engine.npcs[i].currentHP <= 0 || currentHP <= 0 || this == null || Server.engine.npcs[i] == null) {
						versNpc.resetAttack();
						return;
					}
					randomWalk = false;
					npcEnemy = i;
					attackingNpc = true;
					Server.engine.npcs[i].randomWalk = false;
				}
			}
		}
		if (npcType == 6257) {
			if (Server.engine.npcs[i].npcType == 6212) {
				if (Misc.getDistance(absX, absY, Server.engine.npcs[i].absX, Server.engine.npcs[i].absY) <= 10) {
					if (npcEnemy > 0 || Server.engine.npcs[i].currentHP <= 0 || currentHP <= 0 || this == null || Server.engine.npcs[i] == null) {
						versNpc.resetAttack();
						return;
					}
					randomWalk = false;
					npcEnemy = i;
					attackingNpc = true;
					Server.engine.npcs[i].randomWalk = false;
				}
			}
		}
		if (npcType == 6210) {
			if (Server.engine.npcs[i].npcType == 6257) {
				if (Misc.getDistance(absX, absY, Server.engine.npcs[i].absX, Server.engine.npcs[i].absY) <= 10) {
					if (npcEnemy > 0 || Server.engine.npcs[i].currentHP <= 0 || currentHP <= 0 || this == null || Server.engine.npcs[i] == null) {
						versNpc.resetAttack();
						return;
					}
					randomWalk = false;
					npcEnemy = i;
					attackingNpc = true;
					Server.engine.npcs[i].randomWalk = false;
				}
			}
		}
	}
	for (int npc = 0; npc < someNpcs.length; npc++) {
		if (npcType == someNpcs[npc]) {
			Player player = Server.engine.players[spawnedFor];
			if (player == null || this == null) {
				spawnedFor = 0;
				underAttack = false;
				return;
			}
			underAttack = true;
		}
	}
	for (Player p : Server.engine.players) {
		if (p != null && this != null) {
			if (Misc.getDistance(absX, absY, p.absX, p.absY) <= getDistance() && p.heightLevel == heightLevel) {
				for (int a = 0; a < animatedArmours.length; a++) {
					if (npcType == animatedArmours[a]) {
						if (p == null || this == null || p.disconnected[0]) {
							underAttack = false;
							spawnedFor = 0;
							return;
						}
						if (currentHP >= 1 && spawnedFor > 0)
							underAttack = true;
					}
				}
				for (int i = 0; i < agressionNpcs.length; i++) {
					if (npcType == agressionNpcs[i]) {
						if (p == null || this == null || p.disconnected[0]) {
							underAttack = false;
							playerIndex = 0;
							return;
						}
						if (currentHP >= 1 && p.skillLvl[3] >= 1) {
							playerIndex = p.playerId;
							for (int z = 0; z < combatNpcs.length; z++) {
								if (npcType == combatNpcs[z]) {
									if (!underAttack) {
										if (p.combatLevel <= getCombatCheck())
											underAttack = true;
										else
											underAttack = false;
									}
								} else {
									underAttack = true;
								}
							}
							randomWalk = false;
						}
					}
				}
			}
		}
	}
	if (npcType == 0) {
		if (Misc.random(10) == 5) {
			requestText("Welcome! T A L K to M E~~Commands are in quest tab! ::home ::shops ::jad ::commands ::help");
		}
	}
	
    }
    /**
     * Death system.
     */
    public void appendDeath() {
	if (deathDelay == 0 && isDead) {
		if (npcType == 2736 || npcType == 2737) {
			Server.engine.newNPC(2738, absX, absY, heightLevel, 0, 0, 0, 0, false, spawnedFor);
			Server.engine.newNPC(2738, absX + 1, absY, heightLevel, 0, 0, 0, 0, false, spawnedFor);
		}
		if (npcType == 5421) {
			Server.engine.newNPC(5420, absX, absY, heightLevel, 0, 0, 0, 0, false, 0);
			needsRespawn = false;
			deathDelay = -1;
			return;
		}
		if (npcType == 1158 || npcType == 1161) {
			Server.engine.newNPC(1160, absX--, absY - 2, heightLevel, 0, 0, 0, 0, false, 0);
			needsRespawn = false;
			deathDelay = -1;
			return;
		}
		if (npcType == 2783 || npcType == 1615) {
			killingCount = 0;
		}
		appendSlayer();
		appendNpcDrops();
		if (npcType == 5420) {
			npcType = 5421;
			Server.engine.rebuildNPCs();
			respawnDelay = 200;
			needsRespawn = true;
			deathDelay = -1;
		}
		if (npcType == 1160) {
			npcType = 1158;
			Server.engine.rebuildNPCs();
			respawnDelay = 200;
			needsRespawn = true;
			deathDelay = -1;
		}
		deathDelay = -1;
		return;
	}
    }

/*This will loop though NPC face coords. By: Tokyomewmew. */
public void setFacing() {
if (faceType == 0) { // If face id equals 0
	    requestFaceCoords(absX, absY + 1); // faces north
} else if (faceType == -1) { // If face id equals -1
	    requestFaceCoords(absX, absY - 1); // faces south
} else if (faceType == -2) { // If face id equals -2
	    requestFaceCoords(absX + 1, absY); // faces east
} else if (faceType == -3) { // If face id equals -3
	    requestFaceCoords(absX - 1, absY); // faces west
} else if (faceType == -4) { // If face id equals -4
	    requestFaceCoords(absX - 1, absY + 1); // faces NW
} else if (faceType == -5) { // If face id equals -5
	    requestFaceCoords(absX + 1, absY + 1); // faces NE
} else if (faceType == -6) { // If face id equals -6
	    requestFaceCoords(absX - 1, absY - 1); // faces SW
} else if (faceType == -7) { // If face id equals -7
	    requestFaceCoords(absX + 1, absY - 1); // faces SE
} else if (faceType == -8) { // If face id equals -8
	    requestFaceCoords(absX, absY); // default facing
}
} // end of method

    /**
     * Slayer experience giving if player has slayer task.
     */
    public void appendSlayer() {
	Player p = Server.engine.players[playerIndex];
	if (p == null) {
		playerIndex = 0;
		resetAttack();
		return;
	}
	for (int i = 0; i < p.slayerType.length; i++) {
		if (!p.slayerTask && npcType != p.slayerType[i]) {
			return;
		}
	}
	p.slayerAmount--;
	if (p.slayerAmount <= 0) {
		p.slayerAmount = 0;
		p.getActionSender().sendMessage(p, "You have finished your slayer task. Please return to your slayer master.");
		p.slayerTask = false;
	}
	p.appendExperience(1000, 18);
    }

    /**
     * Npc drops items if player killed npc.
     */
    public void appendNpcDrops() {
	Player p = Server.engine.players[playerIndex];
	if (p == null) {
		playerIndex = 0;
		resetAttack();
		return;
	}
	Player s = Server.engine.players[spawnedFor];
	if (spawnedFor > 0) {
		if (s == null || spawnedFor == 0) {
			resetAttack();
			spawnedFor = 0;
			return;
		}
	}
	switch (npcType) {
		case 8133:
			Engine.items.createGroundItem(532, 1, absX, absY, heightLevel, p.username);
			Engine.items.createGroundItem(NpcDrops.getCorpDrop(), 1, absX, absY, heightLevel, p.username);
		break;
		case 6210:
			Engine.items.createGroundItem(538, 1, absX, absY, heightLevel, p.username);
			Engine.items.createGroundItem(NpcDrops.getHellhoundDrop(), 1, absX, absY, heightLevel, p.username);
		break;

		case 6269:	
		case 6261:
		case 6263:
		case 6265:
			p.godWarsKills[1]++;
			p.getActionSender().setString(p, ""+p.godWarsKills[1]+"", 598, 7);
		break;

		case 6218:
			p.godWarsKills[3]++;
			p.getActionSender().setString(p, ""+p.godWarsKills[3]+"", 601, 9);
			p.getActionSender().setString(p, ""+p.godWarsKills[3]+"", 598, 10);
		break;

		case 6257:
			p.godWarsKills[2]++;
			if (p.addZamorakCheckEventGodWars())
				p.getActionSender().setString(p, ""+p.godWarsKills[2]+"", 598, 9);
			else
				p.getActionSender().setString(p, ""+p.godWarsKills[2]+"", 601, 8);
		break;

		case 5253:
			if (Misc.random(10) == 5)
				Server.engine.items.createGroundItem(10581, 1, absX, absY, heightLevel, p.username);
		break;

		case 2783:
			Engine.items.createGroundItem(532, 1, absX, absY, heightLevel, p.username);
			Engine.items.createGroundItem(NpcDrops.getDarkBeastDrop(), 1, absX, absY, heightLevel, p.username);
		break;

		case 2734:
		case 2735:
		case 2736:
		case 2737:
		case 2738:
		case 2739:
		case 2740:
		case 2741:
		case 2742:
		case 2743:
		case 2744:
			p.waveCount++;
			p.waveDelay = 20;
		break;

		case 6220:
		case 6229:
		case 6230:
		case 6231:
		case 6232:
		case 6233:
			p.godWarsKills[0]++;
			p.getActionSender().setString(p, ""+p.godWarsKills[0]+"", 601, 6);
		break;

		case 2745:
			p.setCoords(2440, 5171, 0);
			Server.engine.playerItems.addItem(p, 6570, 1);
			Server.engine.playerItems.addItem(p, 6529, 8029);
			p.getActionSender().sendMessage(s, "Congratiolations, You've beaten Tztok-Jad.");
			p.requestAnim(862, 0);
		break;

		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 100:
			Engine.items.createGroundItem(526, 1, absX, absY, heightLevel, p.username);
			Engine.items.createGroundItem(995, Misc.random(10), absX, absY, heightLevel, p.username);
		break;

		case 9:
			Engine.items.createGroundItem(526, 1, absX, absY, heightLevel, p.username);
			Engine.items.createGroundItem(1139, 1, absX, absY, heightLevel, p.username);
		break;
               case 1158:
			Engine.items.createGroundItem(536, 1, absX, absY, heightLevel, p.username);
			Engine.items.createGroundItem(4151, 1, absX, absY, heightLevel, p.username);
			Engine.items.createGroundItem(NpcDrops.getQueenDrop(), 1, absX, absY, heightLevel, p.username);
		break;
		case 50:
			Engine.items.createGroundItem(536, 1, absX, absY, heightLevel, p.username);
			Engine.items.createGroundItem(1747, 1, absX, absY, heightLevel, p.username);
			Engine.items.createGroundItem(NpcDrops.getKbdDrop(), 1, absX, absY, heightLevel, p.username);
		break;
		case 53:
		case 54:
                case 55:
			Engine.items.createGroundItem(536, 1, absX, absY, heightLevel, p.username);
			Engine.items.createGroundItem(1751, 1, absX, absY, heightLevel, p.username);
			Engine.items.createGroundItem(NpcDrops.getBlueDrop(), 1, absX, absY, heightLevel, p.username);
		break;
		case 81:
			Engine.items.createGroundItem(526, 1, absX, absY, heightLevel, p.username);
			Engine.items.createGroundItem(1739, 1, absX, absY, heightLevel, p.username);
			Engine.items.createGroundItem(2132, 1, absX, absY, heightLevel, p.username);
		break;

		case 107:
			Engine.items.createGroundItem(5319, 1, absX, absY, heightLevel, p.username);
		break;

		case 6729:
			if (Misc.random(2) == 1)
				Engine.items.createGroundItem(995, Misc.random(1623), absX, absY, heightLevel, p.username);
			else
				Engine.items.createGroundItem(NpcDrops.getRevenantOrkDrop(), 1, absX, absY, heightLevel, p.username);
		break;

		case 4291:
		case 4292:
			if (p.defenderId == 0 || p.defenderId == 1)//Bronze
				Engine.items.createGroundItem(NpcDrops.getBronzeDefDrop(), 1, absX, absY, heightLevel, p.username);
			if (p.defenderId == 2)//Iron
				Engine.items.createGroundItem(NpcDrops.getIronDefDrop(), 1, absX, absY, heightLevel, p.username);
			/*if (p.defenderId == 3)//Steel
				Engine.items.createGroundItem(NpcDrops.getSteelDefDrop(), 1, absX, absY, heightLevel, p.username);
			if (p.defenderId == 4)//Black
				Engine.items.createGroundItem(NpcDrops.getBlackDefDrop(), 1, absX, absY, heightLevel, p.username);
			if (p.defenderId == 5)//Mithril
				Engine.items.createGroundItem(NpcDrops.getMithDefDrop(), 1, absX, absY, heightLevel, p.username);
			if (p.defenderId == 6)//Adamantite
				Engine.items.createGroundItem(NpcDrops.getAddyDefDrop(), 1, absX, absY, heightLevel, p.username);
			if (p.defenderId == 7)//Rune
				Engine.items.createGroundItem(NpcDrops.getRuneDefDrop(), 1, absX, absY, heightLevel, p.username);*/
			Engine.items.createGroundItem(532, 1, absX, absY, heightLevel, p.username);
		break;

		case 134:
			/**
			 * Npc drops nothing
			 */
		break;

		case 2614:
			Engine.items.createGroundItem(6529, Misc.random(129), absX, absY, heightLevel, p.username);
		break;

		case 1160:
			Engine.items.createGroundItem(NpcDrops.getQueenDrop(), 1, absX, absY, heightLevel, p.username);
		break;
/*start of barrows*/

		case 2025:
			Engine.items.createGroundItem(592, 1, absX, absY, heightLevel, p.username);
			Engine.items.createGroundItem(NpcDrops.getahrimDrop(), 1, absX, absY, heightLevel, p.username);
		break;

		case 2026:
			Engine.items.createGroundItem(592, 1, absX, absY, heightLevel, p.username);
			Engine.items.createGroundItem(NpcDrops.getdhDrop(), 1, absX, absY, heightLevel, p.username);
		break;

		case 2027:
			Engine.items.createGroundItem(592, 1, absX, absY, heightLevel, p.username);
			Engine.items.createGroundItem(NpcDrops.getguthDrop(), 1, absX, absY, heightLevel, p.username);
		break;

		case 2028:
			Engine.items.createGroundItem(592, 1, absX, absY, heightLevel, p.username);
			Engine.items.createGroundItem(NpcDrops.getkarilDrop(), 1, absX, absY, heightLevel, p.username);
		break;

		case 2029:
			Engine.items.createGroundItem(592, 1, absX, absY, heightLevel, p.username);
			Engine.items.createGroundItem(NpcDrops.gettoragDrop(), 1, absX, absY, heightLevel, p.username);
		break;

		case 2030:
			Engine.items.createGroundItem(592, 1, absX, absY, heightLevel, p.username);
			Engine.items.createGroundItem(NpcDrops.getveracDrop(), 1, absX, absY, heightLevel, p.username);
		break;
		case 9000:
			Engine.items.createGroundItem(592, 1, absX, absY, heightLevel, p.username);
			Engine.items.createGroundItem(NpcDrops.gettdDrop(), 1, absX, absY, heightLevel, p.username);
		break;
		case 7134:
			Engine.items.createGroundItem(995, 10000000, absX, absY, heightLevel, p.username);
			Engine.items.createGroundItem(NpcDrops.getborkDrop(), 1, absX, absY, heightLevel, p.username);
		break;
/*end of barrows*/

		case 6212:
			Engine.items.createGroundItem(995, 100000, absX, absY, heightLevel, p.username);
			Engine.items.createGroundItem(526, 1, absX, absY, heightLevel, p.username);
			Engine.items.createGroundItem(NpcDrops.getwarewolfDrop(), 1, absX, absY, heightLevel, p.username);
		break;

		case 5363:
			Engine.items.createGroundItem(536, 1, absX, absY, heightLevel, p.username);
			Engine.items.createGroundItem(NpcDrops.getMithDragDrop(), 1, absX, absY, heightLevel, p.username);
		break;

		case 6204:
		case 6208:
			p.godWarsKills[3]++;
			p.getActionSender().setString(p, ""+p.godWarsKills[3]+"", 601, 9);
			p.getActionSender().setString(p, ""+p.godWarsKills[3]+"", 598, 10);
		break;

		case 6203:
			Engine.items.createGroundItem(NpcDrops.getKrilTsusarothDrop(), 1, absX, absY, heightLevel, p.username);
			Engine.items.createGroundItem(592, 1, absX, absY, heightLevel, p.username);
			p.godWarsKills[3]++;
			p.getActionSender().setString(p, ""+p.godWarsKills[3]+"", 601, 9);
			p.getActionSender().setString(p, ""+p.godWarsKills[3]+"", 598, 10);
		break;

		case 6222:
			Engine.items.createGroundItem(NpcDrops.getKreeArraDrop(), 1, absX, absY, heightLevel, p.username);
		break;

		case 6260:
			Engine.items.createGroundItem(NpcDrops.getGraardorDrop(), 1, absX, absY, heightLevel, p.username);
			p.godWarsKills[1]++;
			p.getActionSender().setString(p, ""+p.godWarsKills[1]+"", 601, 7);
		break;

		case 6247:
			Engine.items.createGroundItem(526, 1, absX, absY, heightLevel, p.username);
			if (Misc.random(1) == 0)
				Engine.items.createGroundItem(995, Misc.random(20000), absX, absY, heightLevel, p.username);
			else if (Misc.random(1) == 1)
				Engine.items.createGroundItem(561, Misc.random(105), absX, absY, heightLevel, p.username);
			else
				Engine.items.createGroundItem(NpcDrops.getZilyanaDrop(), 1, absX, absY, heightLevel, p.username);
		break;

		case 7770:
			Engine.items.createGroundItem(6529, 1000, absX, absY, heightLevel, p.username);
			Engine.items.createGroundItem(532, 1, absX, absY, heightLevel, p.username);
		break;

		case 4284:
			Engine.items.createGroundItem(1127, 1, absX, absY + 1, heightLevel, s.username);
			Engine.items.createGroundItem(1079, 1, absX, absY + 1, heightLevel, s.username);
			Engine.items.createGroundItem(1163, 1, absX, absY + 1, heightLevel, s.username);
			Engine.items.createGroundItem(8851, 78, absX, absY + 1, heightLevel, s.username);
		break;

		default:
			Engine.items.createGroundItem(526, 1, absX, absY, heightLevel, p.username);
	}
	playerIndex = 0;
    }

    /**
     * Request an animation for this NPC.
     * @param animId The amination to perform.
     * @param animD The delay before doing the animation.
     */
    public void requestAnim(int animId, int animD) {
        animRequest = animId;
        animDelay = animD;
        animUpdateReq = true;
        updateReq = true;
    }

    /**
     * Request text for this NPC.
     * @param message The message to make the NPC say.
     */
    public void requestText(String message) {
        speakText = message;
        animUpdateReq = true;
        speakTextUpdateReq = true;
    }

    /**
     * Request an graphic for this NPC.
     * @param gfxId The graphic to perform.
     * @param gfxD The delay or height or the gfx depending on the value.
     */
    public void requestGFX(int gfxId, int gfxD) {
        if (gfxD == 100) {
            gfxD = 6553600;
        }
        gfxRequest = gfxId;
        gfxDelay = gfxD;
        gfxUpdateReq = true;
        updateReq = true;
    }

    /**
     * Request this NPC faces two coordinates.
     * @param x The x coordinate to face.
     * @param y The y coordinate to face.
     */
    public void requestFaceCoords(int x, int y) {
        faceCoordsX = 2 * x + 1;
        faceCoordsY = 2 * y + 1;
        faceCoordsUpdateReq = true;
        updateReq = true;
    }

    /**
     * Request this NPC faces another NPC or player.
     * @param faceId The target to face.
     */
    public void requestFaceTo(int faceId) {
        faceToRequest = faceId;
        faceToUpdateReq = true;
        updateReq = true;
    }

    /**
     * Resets attack of npc.
     */
    public void resetAttack() {
	battleCount = 0;
	underAttack = false;
    }

    /**
     * Switch jad attack types.
     * @param mageType the mage attack type.
     * @param rangeType the range attack type.
     */
    public void switchJadAttack(boolean rangeType, boolean mageType) {
	this.rangeType = rangeType;
 	this.mageType = mageType;
    }
	/**
     * Switch corp attack types.
     * @param mageType the mage attack type.
     * @param rangeType the range attack type.
     */
    public void switchCorpAttack(boolean rangeType, boolean mageType) {
	this.rangeType = rangeType;
 	this.mageType = mageType;
    }

    /**
     * Add damage to this NPC.
     * @param damage To amount of damage.
     * @param posion 0 for normal damage, 1 for posion.
     */
    public void appendHit(int damage, int posion) {
        if (damage > currentHP) {
            damage = currentHP;
        }
        currentHP -= damage;
	NpcCombat npcCb1 = new NpcCombat(this);
        if (currentHP <= 0 && npcType != 1158) {
	    requestAnim(npcCb1.getDeathAnim(), 0);
            currentHP = 0;
	    deathDelay = 8;
            isDead = true;
        } else if (currentHP <= 0 && npcType == 1158) {
	    currentHP = 0;
	    deathDelay = 15;
	    kqDeathDelay = 4;
	    isDead = true;
	}
        if (!hit1UpdateReq) {
            hitDiff1 = damage;
            posionHit1 = posion;
            hit1UpdateReq = true;
        } else {
            hitDiff2 = damage;
            posionHit2 = posion;
            hit2UpdateReq = true;
        }
        updateReq = true;
    }

    /**
     * Add damage to this NPC.
     * @param damage To amount of damage.
     * @param posion 0 for normal damage, 1 for posion.
     */
    public void append1Hit(int damage, int posion) {
        if (damage > currentHP) {
            damage = currentHP;
        }
        currentHP -= damage;
	NpcCombat npcCb1 = new NpcCombat(this);
        if (currentHP <= 0) {
	    requestAnim(npcCb1.getDeathAnim(), 0);
            currentHP = 0;
	    deathDelay = 8;
            isDead = true;
        }
        if (!hit2UpdateReq) {
            hitDiff2 = damage;
            posionHit2 = posion;
            hit2UpdateReq = true;
        } else {
            hitDiff1 = damage;
            posionHit1 = posion;
            hit1UpdateReq = true;
        }
        updateReq = true;
    }

    public int getDeathDelay1() {
	switch (npcType) {

		case 1472:
			return 4;

		case 2745:
			return 6;

		case 6247:
			return 4;

		case 6625:
			return 2;

		case 6729:
			return 4;

		case 1158:
			return 16;

		case 1160:
			return 6;

		default:
			return 3;
	}
    }

    int getCombatCheck() {
	switch (npcType) {

		case 6729:
			return 121;

		case 6625:
			return 102;

		case 6998:
		case 6999:
			return 136;

		case 1153:
		case 1156:
			return 40;

		case 1154:
			return 100;

		default:
			return 2;
	}
    }

    public int getNoclipPositions() {
	switch (npcType) {

		case 1153: return 1;
		case 1154: return 1;
		case 1155: return 2;
		case 1156: return 1;
		case 1157: return 2;
		case 1158: return 3;
		case 1160: return 3;

		default:
			return 0;
	}
    }

    int getCurrentNpcHP() {
	switch (npcType) {

		case 6691:
			return 100;

		case 6625:
			return 44;

		case 6729:
			return 52;

		default:
			return 0;
	}
    }

    String getRandomGWDText() {
	switch (npcType) {

		case 6203:
			if (Misc.random(50) == 25)
				return "Attack them, You dogs!";
			else if (Misc.random(50) == 24)
				return "Kill them!";
			else if (Misc.random(50) == 23)
				return "Flay them all!";
			else if (Misc.random(50) == 22)
				return "Rend them dimb from limb!";
			else if (Misc.random(50) == 21)
				return "YAARRRRRRR!!";
			else if (Misc.random(50) == 20)
				return "No mercy!";
			else if (Misc.random(50) == 19)
				return "Forward!";
			else if (Misc.random(50) == 18)
				return "Zamorak curse them!";
		break;

		case 6260:
			if (Misc.random(50) == 25)
				return "For the glory of the Big High War God!";
			else if (Misc.random(50) == 24)
				return "GRAAAAAAAAAAAR!";
			else if (Misc.random(50) == 23)
				return "All glory to Bandos!";
			else if (Misc.random(50) == 22)
				return "Crush them underfoot!";
			else if (Misc.random(50) == 21)
				return "Split their skulls!";
			else if (Misc.random(50) == 20)
				return "Death to our enemies!";
			else if (Misc.random(50) == 19)
				return "Break their bones!";
			else if (Misc.random(50) == 18)
				return "We feast on the bones of our enemies";
			else if (Misc.random(50) == 17)
				return "Braargh!";
		break;

		case 6247:
			if (Misc.random(50) == 25)
				return "Saradomin lend me strength!";
			else if (Misc.random(50) == 24)
				return "Forward! Our allies are with us!";
			else if (Misc.random(50) == 23)
				return "All praise Saradomin!";
			else if (Misc.random(50) == 22)
				return "Saradomin is with us!";
			else if (Misc.random(50) == 21)
				return "Good will always triumph!";
			else if (Misc.random(50) == 20)
				return "Death to the enemies of the light!";
			else if (Misc.random(50) == 19)
				return "Attack! Find the godsword!";
			else if (Misc.random(50) == 18)
				return "In the name of Saradomin!";
		break;
	}
	return "";
    }

    int getDistance() {
	switch (npcType) {

		case 5417: return 15;
		case 5420: return 15;
		case 5421: return 15;
		case 50: return 18;
		case 2745: return 12;
		case 1155: return 5;
		case 1157: return 6;
		case 1158: return 9;
		case 1160: return 8;
		case 6222: return 6;
		case 6223: return 7;
		case 6225: return 8;
		case 6227: return 8;
		case 6204: return 7;
		case 6206: return 5; 
		case 6208: return 8;
		case 2734:
		case 2735:
		case 2736:
		case 2737:
		case 2738:
		case 2739:
		case 2740:
		case 2741:
		case 2742:
		case 2743:
		case 2744:
			return 13;
		case 2746: return 15;
		case 6203: return 10;
		case 6248: return 20;
		case 6250: return 20;
		case 6252: return 20;
		case 6260: return 20;
		case 6261: return 20;
		case 6263: return 20;
		case 6265: return 20;
		case 5902: return 7;
		case 6247: return 18;
		case 6625: return 13;
		case 6691: return 10;
		case 6729: return 12;
		case 6998: return 12;
		case 6999: return 11;

		default:
			return 3;
	}
    }

    public boolean inHomeArea() {
	return absX >= 2494 && absX <= 2521 && absY >= 3482 && absY <= 3875;
    }

    public static final int[] CONSTANTS = {
	2842, 2841, 2840, 2839, 2838, 2837, 2836, 2835, 2834, 2833, 2832, 2831, 2830,
	2829, 2828, 2827, 2826, 2825, 2824
    };
}