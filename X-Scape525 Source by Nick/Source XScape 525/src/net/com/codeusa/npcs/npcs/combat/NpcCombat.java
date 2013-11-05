package net.com.codeusa.npcs.combat;

import net.com.codeusa.*;
import net.com.codeusa.util.*;
import net.com.codeusa.model.games.*;
import net.com.codeusa.model.*;
import net.com.codeusa.npcs.*;

/**
 * @author Codeusa
 */

public class NpcCombat {

	NPC n;

	/**
	 * A variable used for multiple attacks (Projectiles)
	 */
	int size;

	/**
	 * Class constructor.
	 * @param n the npc which the constructor should be made for.
	 */
	public NpcCombat(NPC n) {
		this.n = n;	
	}

	/**
	 * NVP system.
	 */	
	public void appendAttackPlayer() {
		Player p = Server.engine.players[n.playerIndex];
		if (n.npcType == 2783 || n.npcType == 1615) {
			if (Misc.getDistance(n.absX, n.absY, p.absX, p.absY) > 7) {
				n.resetAttack();
				n.killingCount = 0;
				n.requestFaceTo(65535);
				n.randomWalk = true;
				n.playerIndex = 0;
				return;
			}
		}
		if (p == null || n == null|| n.isDead || n.currentHP <= 0 || p.skillLvl[3] <= 0 || p.disconnected[0]) {
			n.resetAttack();
			return;
		}
		Player playerz = Server.engine.players[n.spawnedFor];
		for (int i = 0; i < n.animatedArmours.length; i++) {
			if (playerz != null && n.npcType == n.animatedArmours[i]) {
				if (playerz.isDead || playerz.deathDelay > 0 || playerz == null) {
					n.spawnedFor = 0;
					n.underAttack = false;
					n = null;
					return;
				}
			}
		}
		try {
			if (n.faceToRequest != n.playerIndex + 32768) {
				n.requestFaceCoords(p.absX, p.absY);
				n.requestFaceTo(n.playerIndex + 32768);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (n.npcType == 1615 || n.npcType == 2783) {
			if (n.killingCount <= 0)
				n.killingCount++;
		}
		for (int i = 0; i < n.mageNpcs.length; i++) {
			if (n.npcType == n.mageNpcs[i]) {
				if (n.npcType == n.mageNpcs[i]) {
					if (Misc.getDistance(n.absX, n.absY, p.absX, p.absY) <= 2 && n.heightLevel == p.heightLevel) {
						if (getRandom(2) == 1) {
							appendMagePlayer();
							return;
						} else {
							if (n.npcType == 6208 || n.npcType == 907 || n.npcType == 6220 || n.npcType == 6223 || n.npcType == 6225 || n.npcType == 6227) {
								appendMagePlayer();
								return;
							}
							if (n.npcType != 1158 && n.npcType != 1160 && n.npcType != 3847 && n.npcType != 6263 && n.npcType != 6265)
								appendPlayerFollowing(p);
							continue;
						}
					} else {
						appendMagePlayer();
						return;
					}
				} else {
					if (n.npcType != 1158 && n.npcType != 1160 && n.npcType != 3847)
						appendPlayerFollowing(p);
					continue;
				}
			}
		}
		int npcDamage = getNpcMaxhit();
		if (n.npcType != 6248 && n.npcType != 6247 && n.npcType != 6203 && n.npcType != 6208 && n.npcType != 6204 && n.npcType != 6260) {
			if (Misc.getDistance(n.absX, n.absY, p.absX, p.absY) > 5 && n.heightLevel == p.heightLevel || p.inClan())
				return;
			if (n.heightLevel != p.heightLevel)
				return;
			if (Misc.getDistance(n.absX, n.absY, p.absX, p.absY) <= 5 && n.heightLevel == p.heightLevel && n.npcType != 6247) {
				if (n.npcType != 1158 && n.npcType != 1160 && n.npcType != 3847)
					appendPlayerFollowing(p);
				if (Misc.getDistance(n.absX, n.absY, p.absX, p.absY) > 1 && n.heightLevel == p.heightLevel || p.inClan())
					return;
			}
		}
		if (n.npcType == 6247 || n.npcType == 6248) {
			if (p.saraChamber()) {
				appendPlayerFollowing(p);
				if (Misc.getDistance(n.absX, n.absY, p.absX, p.absY) > 2)
					return;
			} else {
				n.randomWalk = true;
				return;
			}
		}
		if (n.npcType == 6203 || n.npcType == 6204 || n.npcType == 6208) {
			if (p.zammyChamber()) {
				appendPlayerFollowing(p);
				if (Misc.getDistance(n.absX, n.absY, p.absX, p.absY) > 2)
					return;
			} else {
				n.randomWalk = true;
				return;
			}
		}
		if (n.npcType == 6260 || n.npcType == 6261 || n.npcType == 6263 || n.npcType == 6265) {
			if (p.graardorChamber()) {
				appendPlayerFollowing(p);
				if (Misc.getDistance(n.absX, n.absY, p.absX, p.absY) > 2)
					return;
			} else {
				n.randomWalk = true;
				return;
			}
		}
		if (n.atkDelay == 0) {
			if (n.npcType != 1615) {
				if (n.npcType == 1160 && n.npcType != 6247) {
					if (Misc.random(2) == 1 && n.currentHP <= 89) {
						n.requestAnim(6235, 0);
						if (!p.isPoisoned) {
							p.isPoisoned = true;
							p.poisonDelay = 45;
							p.poisonHitCount = 0;
							p.getActionSender().sendMessage(p, "You have been poisoned!");
						}
						return;
					} else {
						n.requestAnim(getAttackAnim(), 0);
					}
				} else {
					if (n.npcType != 6247)
						n.requestAnim(getAttackAnim(), 0);
				}
			} else {
				if (Misc.random(1) == 1) {
					p.requestGFX(409, 0);
					n.requestAnim(getMageAnim(), 0);
					if (Misc.random(2) == 1)
						p.setCoords(p.absX+1, p.absY, p.heightLevel);
					else
						p.setCoords(p.absX, p.absY-1, p.heightLevel);
					n.absX = p.absX-1;
					n.absY = p.absY;
					n.heightLevel = p.heightLevel;
					Server.engine.rebuildNPCs();
					setAtkDelay(getCombatDelay());
					return;
				} else {
					n.requestAnim(getAttackAnim(), 0);
				}
			}
			n.changeAnimDelay = getChangeAnimDelay();
			if (Misc.random(1) == 1 && n.npcType == 6247) {
				n.requestAnim(6967, 0);
				for (Player player : Server.engine.players) {
					if (player != null) {
						if (Misc.getDistance(n.absX, n.absY, p.absX, p.absY) > 1)
							return;
						if (player.disturbSara) {
							if (!player.magicPrayer)
								player.appendNPCHit(getRandom(31), 0);
							else
								player.appendNPCHit(0, 0);
						}
					}
				}
				return;
			} else if (Misc.random(1) != 1 && n.npcType == 6247) {
				n.requestAnim(getAttackAnim(), 0);
			}
			if (p.usingPrayer(19)) {
				p.appendNPCHit(0, 0);
			} else {
				p.appendNPCHit(getRandom(npcDamage), 0);
			}
			if (n.npcType == 2734 || n.npcType == 2735) {
				p.skillLvl[5]--;
				p.getActionSender().setSkillLvl(p, 5);
			}
			if (p.vengeance) {
				n.appendHit((int)((npcDamage / 4) * 3), 0);
				p.requestForceChat("Taste vengeance!");
				p.vengeance = false;
			}
			if (p.equipment[5] != -1) {
				p.requestAnim(p.getBlockAnim(), 0);
			} else {
				p.requestAnim(p.getBlockAnim1(), 0);
			}
			setAtkDelay(getCombatDelay());
		}
	}

	/**
	 * NVP system (Mage)
	 */
	public void appendMagePlayer() {
		Player p = Server.engine.players[n.playerIndex];
		if (p == null || n == null|| n.isDead || n.currentHP <= 0 || p.skillLvl[3] <= 0 || p.disconnected[0]) {
			n.resetAttack();
			return;
		}
		int size = 0;
		int casterX = n.absX;
		int casterY = n.absY;
		int offsetY = (n.absY - p.absY) * -1;
		int offsetX = (n.absX - p.absX) * -1;
		if (n.npcType == 6247 || n.npcType == 6250) {
			if (!p.saraChamber())
				return;
		}
		if (n.npcType == 6203 || n.npcType == 6204) {
			if (!p.zammyChamber())
				return;
			appendPlayerFollowing(p);
		}
		if (n.npcType == 6260 || n.npcType == 6263 || n.npcType == 6265) {
			if (!p.graardorChamber())
				return;
			appendPlayerFollowing(p);
		}
		if (p.heightLevel != n.heightLevel)
			return;
		if (Misc.getDistance(n.absX, n.absY, p.absX, p.absY) > getMagicDistance() && p.heightLevel == n.heightLevel) {
			if (n.npcType != 6208) {
				return;
			} else {
				if (Misc.getDistance(n.absX, n.absY, p.absX, p.absY) <= 7)
					appendPlayerFollowing(p);
				else
					return;
			}
		}
		try {
			if (n.faceToRequest != n.playerIndex + 32768) {
				n.requestFaceCoords(p.absX, p.absY);
				n.requestFaceTo(n.playerIndex + 32768);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		n.changeAnimDelay = getChangeAnimDelay();
		if (n.atkDelay == 0) {
			switch (n.npcType) {

				case 6265:
					p.getActionSender().addProjectile(p, casterY, casterX, offsetY, offsetX, 50, 82, 1206, 43, 31, p.playerId, true);
					n.farCastDelay = 3;
				break;

				case 6263:
					p.getActionSender().addProjectile(p, casterY, casterX, offsetY, offsetX, 50, 82, 1203, 43, 31, p.playerId, true);
					n.farCastDelay = 3;
				break;
								case 8133:				
				if (getRandom(1) == 1) {
					p.getActionSender().addProjectile(p, casterY, casterX, offsetY, offsetX, 50, 82, 1823, 43, 31, p.playerId, true);
					n.requestAnim(10057, 0);
					n.switchCorpAttack(true, false);
				} else {
					p.getActionSender().addProjectile(p, casterY, casterX, offsetY, offsetX, 50, 82, 1825, 43, 31, p.playerId, true);
					n.requestAnim(10053, 0);
					n.switchCorpAttack(false, true);
				}
				n.farCastDelay = 3;
				break;

				case 1472:
					n.requestGFX(161, 100);
					n.requestAnim(getMageAnim(), 0);
					p.getActionSender().addProjectile(p, casterY, casterX, offsetY, offsetX, 50, 82, 162, 43, 31, p.playerId, true);
					n.farCastDelay = 3;
				break;

				case 6252:
					n.requestGFX(24, 100);
					n.requestAnim(getMageAnim(), 0);
					p.getActionSender().addProjectile(p, casterY, casterX, offsetY, offsetX, 50, 82, 15, 43, 31, p.playerId, true);
					n.farCastDelay = 3;
				break;

				case 6250:
					n.requestAnim(getMageAnim(), 0);
					p.getActionSender().addProjectile(p, casterY, casterX, offsetY, offsetX, 50, 82, 1623, 36, 31, p.playerId, true);
					n.farCastDelay = 3;
				break;

				case 6691:
					if (Misc.random(2) == 1) {
						p.getActionSender().addProjectile(p, casterY, casterX, offsetY, offsetX, 50, 82, 159, 36, 31, p.playerId, true);
						n.revenantRangedAttk = false;
					} else {
						p.getActionSender().addProjectile(p, casterY, casterX, offsetY, offsetX, 50, 82, 27, 36, 31, p.playerId, true);
						n.revenantRangedAttk = true;
					}
					n.requestAnim(getMageAnim(), 0);
					n.farCastDelay = 3;
				break;

				case 6625:
					if (Misc.random(2) == 1) {
						p.getActionSender().addProjectile(p, casterY, casterX, offsetY, offsetX, 50, 82, 159, 36, 31, p.playerId, true);
						n.revenantRangedAttk = false;
					} else {
						p.getActionSender().addProjectile(p, casterY, casterX, offsetY, offsetX, 50, 82, 27, 36, 31, p.playerId, true);
						n.revenantRangedAttk = true;
					}
					n.requestAnim(getMageAnim(), 0);
					n.farCastDelay = 3;
				break;

				case 6729:
					n.requestGFX(158, 100);
					n.requestAnim(getMageAnim(), 0);
					p.getActionSender().addProjectile(p, casterY, casterX, offsetY, offsetX, 50, 82, 159, 36, 31, p.playerId, true);
					n.farCastDelay = 3;
				break;

				case 3847:
					n.requestGFX(161, 100);
					n.requestAnim(getMageAnim(), 0);
					p.getActionSender().addProjectile(p, casterY + 1, casterX + 1, offsetY - 1, offsetX - 1, 50, 82, 162, 68, 31, p.playerId, true);
					n.farCastDelay = 3;
				break;

				case 5902:
					n.requestAnim(getMageAnim(), 0);
					p.getActionSender().addProjectile(p, casterY + 1, casterX + 1, offsetY - 1, offsetX - 1, 50, 82, 162, 68, 31, p.playerId, true);
					n.farCastDelay = 3;
				break;

				case 6998:
				case 6999:
					n.requestAnim(getMageAnim(), 0);
					if (Misc.random(1) == 1) {
						p.getActionSender().addProjectile(p, casterY + 1, casterX + 1, offsetY - 1, offsetX - 1, 50, 82, 395, 56, 31, p.playerId, true);
						n.revenantRangedAttk = false;
					} else {
						p.getActionSender().addProjectile(p, casterY + 1, casterX + 1, offsetY - 1, offsetX - 1, 50, 82, 27, 54, 31, p.playerId, true);
						n.revenantRangedAttk = true;
					}
					n.farCastDelay = 3;
				break;

				case 6233:
				case 6232:
				case 6223:
				case 6225:
				case 6227:
					n.requestAnim(getMageAnim(), 0);
					p.getActionSender().addProjectile(p, casterY, casterX, offsetY - 1, offsetX - 1, 50, 82, 1190, 57, 31, p.playerId, true);
					n.farCastDelay = 3;
				break;

				case 6260:
					for (Player pl : Server.engine.players) {
						if (pl != null) {
							if (Misc.getDistance(n.absX, n.absY, pl.absX, pl.absY) <= 8 && n.heightLevel == p.heightLevel) {
								int offsetY2 = (n.absY - pl.absY) * -1;
								int offsetX2 = (n.absX - pl.absX) * -1;
								pl.getActionSender().addProjectile(pl, casterY + 1, casterX + 1, offsetY2 - 1, offsetX2 - 1, 50, 82, 288, 68, 31, p.playerId, true);
							}
						}
					}
					if (Misc.random(2) == 1)
						n.requestAnim(getBlockAnim(), 0);
					else
						n.requestAnim(7063, 0);
					n.farCastDelay = 3;
				break;

				case 2739:
				case 2740:
					n.requestAnim(getMageAnim(), 0);
					p.getActionSender().addProjectile(p, casterY + 1, casterX + 1, offsetY - 1, offsetY - 1, 50, 82, 1617, 100, 31, p.playerId, true);
					n.farCastDelay = 3;
				break;

				case 2743:
				case 2744:
					n.requestGFX(1622, 0);
					n.requestAnim(getMageAnim(), 0);
					p.getActionSender().addProjectile(p, casterY + 1, casterX + 1, offsetY - 1, offsetY - 1, 50, 82, 1623, 78, 31, p.playerId, true);
					n.farCastDelay = 3;
				break;

				case 6203:
					n.requestGFX(1212, 0);
					n.requestAnim(getMageAnim(), 0);
					p.getActionSender().addProjectile(p, casterY + 1, casterX + 1, offsetY - 1, offsetX - 1, 50, 82, 1213, 78, 31, p.playerId, true);
					n.farCastDelay = 3;
				break;

				case 6208:
					n.requestAnim(getMageAnim(), 0);
					p.getActionSender().addProjectile(p, casterY + 1, casterX + 1, offsetY - 1, offsetX - 1, 50, 82, 1213, 78, 31, p.playerId, true);
					n.farCastDelay = 3;
				break;

				case 1158:
				case 1160:
					if (getRandom(1) == 1) {
						n.requestGFX(getMageGFX(), 0);
						n.requestAnim(getMageAnim(), 0);
						p.getActionSender().addProjectile(p, casterY + 1, casterX + 1, offsetY - 1, offsetX - 1, 50, 82, 280, 78, 31, p.playerId, true);
						n.kalphRanged = false;
						n.farCastDelay = 3;
					} else {
						for (Player pl : Server.engine.players) {
							if (pl != null) {
								if (Misc.getDistance(n.absX, n.absY, pl.absX, pl.absY) <= 11 && n.heightLevel == p.heightLevel) {
									int offsetY1 = (n.absY - pl.absY) * -1;
									int offsetX1 = (n.absX - pl.absX) * -1;
									if (n.npcType == 1158)
										n.requestAnim(1172, 0);
									else
										n.requestAnim(1171, 0);
									if (n.npcType == 1158)
										pl.getActionSender().addProjectile(pl, casterY + 1, casterX + 1, offsetY1 - 1, offsetX1 - 1, 50, 82, 288, 43, 31, p.playerId, true);
									else
										pl.getActionSender().addProjectile(pl, casterY + 1, casterX + 1, offsetY1 - 1, offsetX1 - 1, 50, 82, 289, 80, 31, p.playerId, true);
									n.kalphRanged = true;
								}
							}
						}
						n.farCastDelay = 3;
					}
				break;

				case 6220:
					n.requestGFX(getMageGFX(), 100);
					n.requestAnim(getMageAnim(), 0);
					p.getActionSender().addProjectile(p, casterY, casterX, offsetY, offsetX, 50, 95, 249, 46, 31, p.playerId, true);
					n.farCastDelay = 3;
				break;

				case 6222:
					n.requestAnim(getMageAnim(), 0);
					if (Misc.random(1) == 1) {
						n.playerTele = true;
						multiRangeAttk(1198);
					} else {
						multiRangeAttk(1197);
					}
					n.farCastDelay = 4;
				break;

				case 50:
					int randomInt = Misc.random(2);
					if (randomInt == 0) {
						p.getActionSender().addProjectile(p, casterY + 1, casterX + 1, offsetY, offsetY, 50, 82, 395, 78, 31, p.playerId, true);
					}
					else if (randomInt == 1) {
						p.getActionSender().addProjectile(p, casterY + 1, casterX + 1, offsetY, offsetY, 50, 82, 394, 78, 31, p.playerId, true);
					}
					else if (randomInt == 2) {
						p.getActionSender().addProjectile(p, casterY + 1, casterX + 1, offsetY, offsetY, 50, 82, 393, 78, 31, p.playerId, true);
					} else {
						n.requestGFX(1, 0);
					}
					n.requestAnim(getMageAnim(), 0);
					n.farCastDelay = 2;
						
				break;
				case 53:
				case 55:
				case 5363:
					n.requestGFX(1, 0);
					n.requestAnim(getMageAnim(), 0);
					n.farCastDelay = 2;
				break;

				case 2745:
					if (getRandom(1) == 1) {
						n.requestGFX(1625, 0);
						n.requestAnim(9276, 0);
						n.rockAppear = 2;
						n.farCastDelay = 4;
						n.switchJadAttack(true, false); /* @param 1 ranged @param 2 mage */
					} else {
						n.requestGFX(1626, 100);
						n.requestAnim(9300, 0);
						n.projectileShowDelay = 1;
						n.farCastDelay = 5;
						n.switchJadAttack(false, true);
					}
				break;

				default:
					n.requestGFX(getMageGFX(), 0);
					n.requestAnim(getMageAnim(), 0);
				break;
			}
			setAtkDelay(7);
		}
	}

	/**
	 * Once farcast delay reaches 0 this method gets activated.
	 */
	public void appendFarcastDamage() {
		Player p = Server.engine.players[n.playerIndex];
		if (p == null || n == null|| n.isDead || n.currentHP <= 0 || p.skillLvl[3] <= 0 || p.disconnected[0]) {
			n.resetAttack();
			return;
		}
		if (p.heightLevel != n.heightLevel)
			return;
		if (n.farCastDelay == 0) {
			if (n.npcType == 6263) {
				if (p == null || n == null || n.currentHP <= 0 || p.skillLvl[3] <= 0) {
					n.farCastDelay = -1;
					return;
				}
				if (p.magicPrayer) {
					p.appendNPCHit(0, 0);
				} else {
					p.requestGFX(1204, 100);
					p.appendNPCHit(getRandom(22), 0);
				}
				n.farCastDelay = -1;
			}
			if (n.npcType == 6265) {
				if (p == null || n == null || n.currentHP <= 0 || p.skillLvl[3] <= 0) {
					n.farCastDelay = -1;
					return;
				}
				if (p.rangedPrayer) {
					p.appendNPCHit(0, 0);
				} else {
					p.appendNPCHit(getRandom(22), 0);
				}
				n.farCastDelay = -1;
			}
			if (n.npcType == 1472) {
				if (p == null || n == null || n.currentHP <= 0 || p.skillLvl[3] <= 0) {
					n.farCastDelay = -1;
					return;
				}
				if (p.magicPrayer) {
					p.appendNPCHit(0, 0);
				} else {
					p.requestGFX(163, 100);
					p.appendNPCHit(getRandom(34), 0);
				}
				n.farCastDelay = -1;
			}
			if (n.npcType == 6252) {
				if (p == null || n == null || n.currentHP <= 0 || p.skillLvl[3] <= 0) {
					n.farCastDelay = -1;
					return;
				}
				if (p.rangedPrayer)
					p.appendNPCHit(0, 0);
				else
					p.appendNPCHit(getRandom(21), 0);
				n.farCastDelay = -1;
			}
			if (n.npcType == 2739 || n.npcType == 2740) {
				if (p == null || n == null || n.currentHP <= 0 || p.skillLvl[3] <= 0) {
					n.farCastDelay = -1;
					return;
				}
				if (p.rangedPrayer)
					p.appendNPCHit(0, 0);
				else
					p.appendNPCHit(getRandom(15), 0);
				n.farCastDelay = -1;
			}
			if (n.npcType == 6250) {
				if (p == null || n == null || n.currentHP <= 0 || p.skillLvl[3] <= 0) {
					n.farCastDelay = -1;
					return;
				}
				if (p.magicPrayer)
					p.appendNPCHit(0, 0);
				else
					p.appendNPCHit(getRandom(25), 0);
				n.farCastDelay = -1;
			}
			if (n.npcType == 6625 || n.npcType == 6691) {
				if (p == null || n == null || n.currentHP <= 0 || p.skillLvl[3] <= 0) {
					n.farCastDelay = -1;
					return;
				}
				if (!n.revenantRangedAttk)
					p.requestGFX(160, 100);
				if (Misc.random(2) == 1) {
					if (p.magicPrayer)
						p.appendNPCHit(0, 0);
					else
						p.appendNPCHit(getRandom(38), 0);
					if (!p.magicPrayer)
						p.freezeDelay = 30;
				} else {
					p.requestGFX(85, 100);
				}
				if (p.freezeDelay > 0)
					appendPlayerFollowing(p);
				n.farCastDelay = -1;
			}
			if (n.npcType == 6729) {
				if (p == null || n == null || n.currentHP <= 0 || p.skillLvl[3] <= 0) {
					n.farCastDelay = -1;
					return;
				}
				p.requestGFX(160, 100);
				if (p.magicPrayer)
					p.appendNPCHit(0, 0);
				else
					p.appendNPCHit(getRandom(40), 0);
				if (!p.magicPrayer)
					p.freezeDelay = 30;
				if (p.freezeDelay > 0)
					appendPlayerFollowing(p);
				n.farCastDelay = -1;
			}
			if (n.npcType == 3847) {
				if (p == null || n == null || n.currentHP <= 0 || p.skillLvl[3] <= 0) {
					n.farCastDelay = -1;
					return;
				}
				p.requestGFX(163, 100);
				if (p.magicPrayer)
					p.appendNPCHit(0, 0);
				else
					p.appendNPCHit(getRandom(32), 0);
				n.farCastDelay = -1;
			}
			if (n.npcType == 5902) {
				if (p == null || n == null || n.currentHP <= 0 || p.skillLvl[3] <= 0) {
					n.farCastDelay = -1;
					return;
				}
				p.requestGFX(163, 100);
				p.appendNPCHit(getRandom(24), 0);
				n.farCastDelay = -1;
			}
			if (n.npcType == 6998 || n.npcType == 6999) {
				if (p == null || n == null || n.currentHP <= 0 || p.skillLvl[3] <= 0) {
					n.farCastDelay = -1;
					return;
				}
				if (!n.revenantRangedAttk) {
					if (p.equipment[3] == 11284 || p.equipment[3] == 1540 || p.magicPrayer)
						p.appendNPCHit(getRandom(12), 0);
					else
						p.appendNPCHit(getRandom(70), 0);
				} else {
					if (p.rangedPrayer)
						p.appendNPCHit(0, 0);
					else
						p.appendNPCHit(14, 0);
				}
				n.farCastDelay = -1;
			}
			if (n.npcType == 6260) {
				if (p == null || n == null || n.currentHP <= 0 || p.skillLvl[3] <= 0) {
					n.farCastDelay = -1;
					return;
				}
				for (Player pl : Server.engine.players) {
					if (pl != null) {
						if (Misc.getDistance(n.absX, n.absY, pl.absX, pl.absY) <= 11 && n.heightLevel == p.heightLevel) {
							pl.appendNPCHit(getRandom(31), 0);
						}
					}
				}
				n.farCastDelay = -1;
			}
			if (n.npcType == 6223 || n.npcType == 6225 || n.npcType == 6227 || n.npcType == 6232 || n.npcType == 6233) {
				if (p == null || n == null || n.currentHP <= 0 || p.skillLvl[3] <= 0) {
					n.farCastDelay = -1;
					return;
				}
				if (p.rangedPrayer)
					p.appendNPCHit(0, 0);
				else
					p.appendNPCHit(getRandom(25), 0);
				n.farCastDelay = -1;
			}
			if (n.npcType == 2743 || n.npcType == 2744) {
				if (p == null || n == null || n.currentHP <= 0 || p.skillLvl[3] <= 0) {
					n.farCastDelay = -1;
					return;
				}
				if (p.magicPrayer)
					p.appendNPCHit(0, 0);
				else
					p.appendNPCHit(getRandom(49), 0);
				n.farCastDelay = -1;
			}
						int size = 0;
			int casterX = n.absX;
			int casterY = n.absY;
			int offsetY = (n.absY - p.absY) * -1;
			int offsetX = (n.absX - p.absX) * -1;
			if (n.npcType == 8133) {
				if (p == null || n == null || n.currentHP <= 0 || p.skillLvl[3] <= 0) {
					n.farCastDelay = -1;
					return;
				}
				if (p.usingPrayer(16)) {//magic prayer
					p.appendNPCHit(0, 0);
				}
				for (Player pl : Server.engine.players) {
					if (pl != null) {
						if (Misc.getDistance(n.absX, n.absY, pl.absX, pl.absY) <= 15 && n.heightLevel == p.heightLevel) {
						if (n.rangeType) {
						if (!pl.usingPrayer(18)) {
							pl.appendNPCHit(getRandom(42), 0); 
							} else {
							pl.appendNPCHit(0, 0);
							}
						}
						if(getRandom(1) == 2) {
						int hit = getRandom(65);
							pl.message("The beast drains your life!");
							pl.appendNPCHit(hit, 0); 
							pl.requestGFX(607,0);
							n.currentHP += hit;
						} else {
						if (n.mageType) {
						if (!pl.usingPrayer(17)) {
							pl.appendNPCHit(getRandom(62), 0); 
							} else {
							pl.appendNPCHit(0, 0);
							}
						}
					}
				}
				}
				n.farCastDelay = -1;
			}
			}
			if (n.npcType == 6203) {
				if (p == null || n == null || n.currentHP <= 0 || p.skillLvl[3] <= 0) {
					n.farCastDelay = -1;
					return;
				}
				if (p.magicPrayer)
					p.appendNPCHit(0, 0);
				else
					p.appendNPCHit(getRandom(27), 0);
				n.farCastDelay = -1;
			}
			if (n.npcType == 6208) {
				if (p == null || n == null || n.currentHP <= 0 || p.skillLvl[3] <= 0) {
					n.farCastDelay = -1;
					return;
				}
				if (p.magicPrayer)
					p.appendNPCHit(0, 0);
				else
					p.appendNPCHit(getRandom(18), 0);
				p.requestGFX(1211, 0);
				n.farCastDelay = -1;
			}
			if (n.npcType == 1158 || n.npcType == 1160) {
				if (p == null || n == null || n.currentHP <= 0 || p.skillLvl[3] <= 0) {
					n.farCastDelay = -1;
					return;
				}
				if (n.kalphRanged) {
					for (Player pl : Server.engine.players) {
						if (pl != null) {
							if (Misc.getDistance(n.absX, n.absY, pl.absX, pl.absY) <= 11 && n.heightLevel == p.heightLevel) {
								if (pl.rangedPrayer)
									pl.appendNPCHit(0, 0);
								else
									pl.appendNPCHit(getRandom(31), 0);
							}
						}
					}
				} else {
					if (p.magicPrayer)
						p.appendNPCHit(0, 0);
					else
						p.appendNPCHit(getRandom(31), 0);
					p.requestGFX(281, 0);
				}
				n.farCastDelay = -1;
			}
			if (n.npcType == 6220) {
				if (p == null || n == null || n.currentHP <= 0 || p.skillLvl[3] <= 0) {
					n.farCastDelay = -1;
					return;
				}
				if (!p.rangedPrayer) {
					p.appendNPCHit(getRandom(23), 0);
				} else {
					p.appendNPCHit(0, 0);
				}
				n.farCastDelay = -1;
			}
			if (n.npcType == 907) {
				if (p == null || n == null || n.currentHP <= 0 || p.skillLvl[3] <= 0) {
					n.farCastDelay = -1;
					return;
				}
				if (Misc.random(2) == 1) {
					if (!p.magicPrayer) {
						p.requestGFX(76, 0);
						p.appendNPCHit(getRandom(10), 0);
					} else {
						p.requestGFX(76, 0);
						p.appendNPCHit(0, 0);
					}
				} else {
					p.appendNPCHit(0, 0);
					p.requestGFX(85, 100);
				}
				n.farCastDelay = -1;
					
			}
			if (n.npcType == 5363) {
				if (p == null || n == null || n.currentHP <= 0 || p.skillLvl[3] <= 0) {
					n.farCastDelay = -1;
					return;
				}
				if (p.equipment[5] != 1540 && p.equipment[5] != 11284) {
					p.appendNPCHit(getRandom(55), 0);
				} else {
					p.appendNPCHit(getRandom(24), 0);
				}
				n.farCastDelay = -1;
			}
			if (n.npcType == 53) {
				if (p == null || n == null || n.currentHP <= 0 || p.skillLvl[3] <= 0) {
					n.farCastDelay = -1;
					return;
				}
				if (p.equipment[5] != 1540 && p.equipment[5] != 11284) {
					p.appendNPCHit(getRandom(54), 0);
				} else {
					p.appendNPCHit(getRandom(13), 0);
				}
				n.farCastDelay = -1;
			}
			if (n.npcType == 55) {
				if (p == null || n == null || n.currentHP <= 0 || p.skillLvl[3] <= 0) {
					n.farCastDelay = -1;
					return;
				}
				if (p.equipment[5] != 1540 && p.equipment[5] != 11284) {
					p.appendNPCHit(getRandom(49), 0);
				} else {
					p.appendNPCHit(getRandom(12), 0);
				}
				n.farCastDelay = -1;
			}
			if (n.npcType == 50) {
				if (p == null || n == null || n.currentHP <= 0 || p.skillLvl[3] <= 0) {
					n.farCastDelay = -1;
					return;
				}
				if (p.equipment[3] != 1540 && p.equipment[3] != 11283)
					p.appendNPCHit(getRandom(17), 0);
				else 
					p.appendNPCHit(getRandom(60), 0);
				n.farCastDelay = -1;
			}
			if (n.npcType == 6222) {
				try {
					for (Player pl : Server.engine.players) {
						if (pl != null) {
							if (pl.armadylChamber()) {
								if (p == null || n == null || n.currentHP <= 0 || p.skillLvl[3] <= 0) {
									n.farCastDelay = -1;
									return;
								}
								if (!pl.rangedPrayer) {
									pl.appendNPCHit(getRandom(71), 0);
								} else {
									pl.appendNPCHit(getRandom(29), 0);
								}
							}
						}
					}
				} catch (Exception e) {
				}
				n.farCastDelay = -1;
			}
			if (n.npcType == 2745) {
				if (p == null || n == null || n.currentHP <= 0 || p.skillLvl[3] <= 0) {
					n.farCastDelay = -1;
					return;
				}
				if (n.rangeType) {
					if (Misc.getDistance(n.absX, n.absY, p.absX, p.absY) <= 9) {
						if (!p.rangedPrayer) {
							p.requestGFX(157, 0);
							p.appendNPCHit(getRandom(97), 0);
						} else {
							p.appendNPCHit(0, 0);
						}
					}	
				} else {
					if (!p.magicPrayer) {
						p.requestGFX(157, 0);
						p.appendNPCHit(getRandom(97), 0);
					} else {
						p.appendNPCHit(0, 0);
					}
					n.farCastDelay = -1;
				}
			}		
			if (p.equipment[5] != -1) {
				p.requestAnim(p.getBlockAnim(), 0);
			} else {
				p.requestAnim(p.getBlockAnim1(), 0);
			n.farCastDelay = -1;
			}
		}
	}

	public void appendRock() {
		Player p = Engine.players[n.spawnedFor];
		if (p == null || n == null|| n.isDead || n.currentHP <= 0 || p.skillLvl[3] <= 0 || p.disconnected[0]) {
			n.playerIndex = 0;
			n.resetAttack();
			return;
		}
		if (n.rockAppear == 0) {
			p.attackNpc = 9;
			if (Misc.getDistance(n.absX, n.absY, p.absX, p.absY) <= 12) {
				p.requestGFX(451, 0);
				n.rockAppear = -1;
			}
		}
	}

	public void createNPCProjectile() {
		Player p = Engine.players[n.spawnedFor];
		if (p == null || n == null|| n.isDead || n.currentHP <= 0 || p.skillLvl[3] <= 0 || p.disconnected[0]) {
			n.playerIndex = 0;
			n.resetAttack();
			return;
		}
		int casterX = n.absX;
		int casterY = n.absY;
		int offsetY = (n.absY - p.absY) * -1;
		int offsetX = (n.absX - p.absX) * -1;
		if (n.projectileShowDelay == 0) {
			switch (n.npcType) {

				case 2745:
					p.getActionSender().addProjectile(p, casterY + 1, casterX + 1, offsetY - 1, offsetX - 1, 50, 120, 1627, 90, 31, n.playerIndex, true);
				break;
			}
			n.projectileShowDelay = -1;
		}
	}

	/**
	 * Holds values by checking npc ids and getting npc attacking delays.
	 */
	public int getCombatDelay() {
		switch (n.npcType) {
			
			case 8133: return 8;
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
				return 4;
			case 9: return 5;
			case 18: return 5;
			case 50: return 4;
			case 53: return 5;
			case 55: return 4;
			case 81: return 5;
			case 100: return 5;
			case 107: return 6;
			case 134: return 5;
			case 907: return 5;
			case 6210: return 6;
			case 1610: return 5;
			case 1615: return 4;
			case 1153: return 5;
			case 1154: return 5;
			case 1155: return 6;
			case 1156: return 5;
			case 1157: return 4;
			case 1158: return 6;
			case 1160: return 5;
			case 1472: return 6;
			case 3847: return 6;
			case 6218: return 7;
			case 4284: return 6;
			case 2614: return 8;
			case 2734: return 6;
			case 2735: return 6;
			case 2736: return 8;
			case 2737: return 7;
			case 2739: return 6;
			case 2740: return 6;
			case 2741: return 7;
			case 2783: return 7;
			case 5363: return 4;
			case 6208: return 7;
			case 6203: return 6;
			case 6204: return 5;
			case 6220: return 4;
			case 6222: return 4;
			case 6223: return 4;
			case 6225: return 4;
			case 6227: return 4;
			case 6247: return 7;
			case 6250: return 7;
			case 6252: return 4;
			case 6260: return 7;
			case 6261: return 6;
			case 6263: return 5;
			case 2745: return 8;
			case 2744: return 5;
			case 2743: return 5;
			case 7770: return 5;
			case 7771: return 5;
			case 6625: return 6;
			case 6691: return 8;
			case 6729: return 7;
			case 6998: return 5;
			case 6999: return 5;
			case 5902: return 7;
			case 4291: return 7;
			case 4992: return 7;

			default:
				return 6;
		}
	}

	/**
	 * Holds values by checking npc ids and getting npc animations.
	 */

public int getAnimation(int ID) {
		switch (ID) {
		case 4153: 
			return 1665;

		case 11694: 
			return 7041;

		case 11696: 
			return 7041;

		case 11698: 
			return 7041;

		case 11700: 
			return 7041;

		case 7158: 
			return 7041;

		case 3204: 
			return 1665;


		case 1305: 
			return 451;


		case 4587: 
			return 451;

		case 4151: 
			return 1658;
		
		case 5698: 
			return 402;
		
		
		default:
			return 422;
		
		}
			}

	public int getAttackAnim() {
		if (n.npcType == 8133) {
			return 10057;
		}
		if (n.npcType == 7 || n.npcType == 9) {
			return 412;
		}
		else if (n.npcType == 1610) {
			return 9454;
		}
		else if (n.npcType == 5417) {
			return 5625;
		}
		else if (n.npcType == 5420) {
			return 422;
		}
		else if (n.npcType == 5421) {
			return 5617;
		}
		else if (n.npcType == 9000) {
			return 10922;
		}
		else if (n.npcType == 7134) {
			return 8754;
		}
		else if (n.npcType == 6210) {
			return 6579;
		}
		else if (n.npcType == 6212) {
			return 6536;
		}
		else if (n.npcType == 6218) {
			return 4300;
		}
		else if (n.npcType == 5253) {
			return 5457;
		}
		else if (n.npcType == 82 || n.npcType == 83 || n.npcType == 84 || n.npcType == 1472) {
			return 64;
		}
		else if (n.npcType == 6248) {
			return 6376;
		}
		else if (n.npcType == 2734 || n.npcType == 2735) {
			return 9232;
		}
		else if (n.npcType == 2736 || n.npcType == 2737 || n.npcType == 2738) {
			return 9233;
		}
		else if (n.npcType == 4284) {
			if (Misc.random(2) == 1)
				return 412;
			else
				return 451;
		}
		else if (n.npcType == 2739 || n.npcType == 2740) {
			return 9245;
		}
		else if (n.npcType == 2741 || n.npcType == 2742 || n.npcType == 2746) {
			return 9252;
		}
		else if (n.npcType == 4291 || n.npcType == 4292 || n.npcType == 6269) {
			return 4652;
		}
		else if (n.npcType == 6625) {
			return 7397;
		}
		else if (n.npcType == 2739 || n.npcType == 2740) {
			return 9245;
		}
		else if (n.npcType == 6247) {
			return 6964;
		}
		else if (n.npcType == 6250) {
			return 7018;
		}
		else if (n.npcType == 6691) {
			return 7467;
		}
		else if (n.npcType == 6729) {
			return 7411;
		}
		else if (n.npcType == 18) {
			return 451;
		}
		else if (n.npcType == 81) {
			return 5849;
		}
		else if (n.npcType == 3847) {
			return 3991;
		}
		else if (n.npcType == 5902) {
			return 6318;
		}
		else if (n.npcType == 100) {
			return 6184;
		}
		else if (n.npcType == 107) {
			return 6254;
		}
		else if (n.npcType == 134) {
			return 5327;
		}
		else if (n.npcType == 2614) {
			return 9286;
		}
		else if (n.npcType == 1615) {
			return 1537;
		}
		else if (n.npcType == 2734 || n.npcType == 2614) {
			return 9232;
		}
		else if (n.npcType == 2783) {
			return 2731;
		}
		else if (n.npcType == 6222) {
			return 6977;
		}
		else if (n.npcType == 6223 || n.npcType == 6225 || n.npcType == 6227 || n.npcType == 6232 || n.npcType == 6233) {
			return 6951;
		}
		else if (n.npcType == 50 || n.npcType == 5363 || n.npcType == 53 || n.npcType == 55) {
			if (getRandom(2) == 1)
				return 91;
			else
				return 80;
		}
		else if (n.npcType == 6203 || n.npcType == 6204) {
			return 6945;
		}
		else if (n.npcType == 1153 || n.npcType == 1154 || n.npcType == 1155 || n.npcType == 1156 || n.npcType == 1157) {
			return 6224;
		}
		else if (n.npcType == 1158) {
			if (Misc.random(2) == 1)
				return 1185;
			else
				return 6241;
		}
		else if (n.npcType == 1160) {
			return 1178;
		}
		else if (n.npcType == 907) {
			return 811;
		}
		else if (n.npcType == 6260) {
			if (Misc.random(1) == 1)
				return 7060;
			else
				return 7063;
		}
		else if (n.npcType == 6261 || n.npcType == 6263 || n.npcType == 6265) {
			return 6154;
		}
		else if (n.npcType == 2744 || n.npcType == 2743) {
			return 9265;
		}
		else if (n.npcType == 6998 || n.npcType == 6999) {
			return 8591;
		}
		else if (n.npcType == 7770 || n.npcType == 7771) {
			return 9336;
		}
		else if (n.npcType == 2745) {
			return 9277;
		} else {
			return 422;
		}
	}

	/**
	 * Holds values by checking npc ids and getting npc animations.
	 */
	public int getMageAnim() {
	if (n.npcType == 8133) {
			return 9276;
		}
		if (n.npcType == 50 || n.npcType == 5363 || n.npcType == 53 || n.npcType == 55) {
			return 82;
		}
		else if (n.npcType == 5421) {
			return 5618;
		}
		else if (n.npcType == 1472) {
			return 69;
		}
		else if (n.npcType == 2739 || n.npcType == 2740) {
			return 9243;
		}
		else if (n.npcType == 6250) {
			return 7019;
		}
		else if (n.npcType == 6691) {
			return 7469;
		}
		else if (n.npcType == 6625) {
			return -1;
		}
		else if (n.npcType == 6729) {
			return 7413;
		}
		else if (n.npcType == 1615) {
			return 1536;
		}
		else if (n.npcType == 3847) {
			return 3992;
		}
		else if (n.npcType == 5902) {
			return 6318;
		}
		else if (n.npcType == 1158) {
			return 6240;
		}
		else if (n.npcType == 1160) {
			return 1171;
		}
		else if (n.npcType == 2783) {
			return 2732;
		}
		else if (n.npcType == 6203 || n.npcType == 6208) {
			return 6947;
		}
		else if (n.npcType == 6220) {
			return 426;
		}
		else if (n.npcType == 6223 || n.npcType == 6225 || n.npcType == 6227 || n.npcType == 6232 || n.npcType == 6233) {
			return 6953;
		}
		else if (n.npcType == 6222) {
			return 6976;
		}
		else if (n.npcType == 907) {
			return 811;
		}
		else if (n.npcType == 2745) {
			return 9276;
		}
		else if (n.npcType == 2744 || n.npcType == 2743) {
			return 9266;
		}
		else if (n.npcType == 6208) {
			return 6947;
		}
		else if (n.npcType == 6998 || n.npcType == 6999) {
			return 8594;
		}
		else if (n.npcType == 6260) {
			return 7063;
		} else {
			return 711;
		}
	}

	/**
	 * Holds values by checking npc ids and getting npc animations.
	 */
	public int getMageGFX() {
	if (n.npcType == 8133) {
			return 1625;
		}
		if (n.npcType == 1158) {
			return 278;
		}
		if (n.npcType == 1160) {
			return 279;
		}
		if (n.npcType == 6220) {
			return 250;
		}
		if (n.npcType == 2745) {
			return 1625;
		} else {
			return -1;
		}
	}

	/**
	 * Holds values by checking npc ids and getting npc animations.
	 */
	public int getDeathAnim() {
	if (n.npcType == 9000) {
			return 10919;
		}
if (n.npcType == 7134) {
			return 8752;
		}
		if (n.npcType == 2745) {
			return 9279;
		} else if (n.npcType == 1610) {
			return 9458;
		} else if (n.npcType == 5420) {
			return 2304;
		} else if (n.npcType == 5417) {
			return 5628;
		} else if (n.npcType == 5421) {
			return 5619;
		} else if (n.npcType == 6218) {
			return 4302;
		} else if (n.npcType == 6210) {
			return 6576;
		} else if (n.npcType == 6212) {
			return 6537;
		} else if (n.npcType == 5253) {
			return 5457;
		} else if (n.npcType == 82 || n.npcType == 83 || n.npcType == 84 || n.npcType == 1472) {
			return 67;
		} else if (n.npcType == 6248) {
			return 6377;
		} else if (n.npcType == 2734 || n.npcType == 2735) {
			return 9230;
		} else if (n.npcType == 2736 || n.npcType == 2737 || n.npcType == 2738) {
			return 9234;
		} else if (n.npcType == 2741 || n.npcType == 2742 || n.npcType == 2746) {
			return 9257;
		} else if (n.npcType == 2739 || n.npcType == 2740) {
			return 9239;
		} else if (n.npcType == 4291 || n.npcType == 4292 || n.npcType == 6269) {
			return 4653;
		} else if (n.npcType == 4284) {
			return 2304;
		} else if (n.npcType == 6250) {
			return 7016;
		} else if (n.npcType == 6247) {
			return 6965;
		} else if (n.npcType == 6691) {
			return 7468;
		} else if (n.npcType == 6625) {
			return 7398;
		} else if (n.npcType == 2783) {
			return 2733;
		} else if (n.npcType == 3846) {
			return 3979;
		} else if (n.npcType == 6729) {
			return 7412;
		} else if (n.npcType == 81) {
			return 5851;
		} else if (n.npcType == 3847) {
			return 3993;
		} else if (n.npcType == 100) {
			return 6182;
		} else if (n.npcType == 107) {
			return 6256;
		} else if (n.npcType == 134) {
			return 5329;
		} else if (n.npcType == 2734 || n.npcType == 2614) {
			return 9230;
		} else if (n.npcType == 2614) {
			return 9288;
		} else if (n.npcType == 6261 || n.npcType == 6263 || n.npcType == 6265) {
			return 6156;
		} else if (n.npcType == 1615) {
			return 1538;
		} else if (n.npcType == 1153 || n.npcType == 1154 || n.npcType == 1155 || n.npcType == 1156 || n.npcType == 1157) {
			return 6228;
		} else if (n.npcType == 1158) {
			return 6242;
		} else if (n.npcType == 1160) {
			return 6233;
		} else if (n.npcType == 5902) {
			return 6322;
		} else if (n.npcType == 6223 || n.npcType == 6225 || n.npcType == 6227 || n.npcType == 6232 || n.npcType == 6233) {
			return 6956;
		} else if (n.npcType == 6222) {
			return 6975;
		} else if (n.npcType == 2744 || n.npcType == 2743) {
			return 9269;
		} else if (n.npcType == 6203 || n.npcType == 6204 || n.npcType == 6208) {
			return 6946;
		} else if (n.npcType == 6260) {
			return 7062;
		} else if (n.npcType == 6998 || n.npcType == 6999) {
			return 8593;
		} else if (n.npcType == 7770 || n.npcType == 7771) {
			return 9332;
		} else if (n.npcType == 50 || n.npcType == 5363 || n.npcType == 53 || n.npcType == 55) {
			return 92;
		} else {
			if (getRandom(2) == 1)
				return 2304;
			else
				return 7197;
		}
	}

	/**
	 * Holds values by checking npc ids and getting npc animations.
	 */
	public int getBlockAnim() {
	if (n.npcType == 9000) {
			return 10919;
		}
	if (n.npcType == 7134) {
			return 8753;
		}
		if (n.npcType == 50 || n.npcType == 5363 || n.npcType == 53 || n.npcType == 55) {
			return 89;
		}
		else if (n.npcType == 1610) {
			return 9455;
		}
		else if (n.npcType == 9001) {
			return 10919;
		}
		else if (n.npcType == 5421) {
			return 5618;
		}
		else if (n.npcType == 5417) {
			return 5626;
		}
		else if (n.npcType == 6218) {
			return 4301;
		}
		else if (n.npcType == 6212) {
			return 6538;
		}
		else if (n.npcType == 6210) {
			return 6578;
		}
		else if (n.npcType == 5253) {
			return 5455;
		}
		else if (n.npcType == 82 || n.npcType == 83 || n.npcType == 84 || n.npcType == 1472) {
			return 65;
		}
		else if (n.npcType == 6248) {
			return 6375;
		}
		else if (n.npcType == 2734 || n.npcType == 2735) {
			return 9231;
		}
		else if (n.npcType == 2736 || n.npcType == 2737 || n.npcType == 2738) {
			return 9235;
		}
		else if (n.npcType == 2741 || n.npcType == 2742 || n.npcType == 2746) {
			return 9253;
		}
		else if (n.npcType == 2739 || n.npcType == 2740) {
			return 9242;
		}
		else if (n.npcType == 4291 || n.npcType == 4292 || n.npcType == 6269) {
			return 4651;
		}
		else if (n.npcType == 4284) {
			return 404;
		}
		else if (n.npcType == 6250) {
			return 7017;
		}
		else if (n.npcType == 6247) {
			return 6966;
		}
		else if (n.npcType == 6625) {
			return 7397;
		}
		else if (n.npcType == 6729) {
			return 7413;
		}
		else if (n.npcType == 81) {
			return 5850;
		}
		else if (n.npcType == 3847) {
			return 3990;
		}
		else if (n.npcType == 2783) {
			return 2732;
		}
		else if (n.npcType == 100) {
			return 6183;
		}
		else if (n.npcType == 107) {
			return 6255;
		}
		else if (n.npcType == 18) {
			return 404;
		}
		else if (n.npcType == 2614) {
			return 9287;
		}
		else if (n.npcType == 2734 || n.npcType == 2614) {
			return 9231;
		}
		else if (n.npcType == 134) {
			return 5328;
		}
		else if (n.npcType == 5902) {
			return 6321;
		}
		else if (n.npcType == 6261 || n.npcType == 6263 || n.npcType == 6265) {
			return 6155;
		}
		else if (n.npcType == 1615) {
			return 1536;
		}
		else if (n.npcType == 7) {
			return 404;
		}
		else if (n.npcType == 9) {
			return 1156;
		}
		else if (n.npcType == 6223 || n.npcType == 6225 || n.npcType == 6227 || n.npcType == 6232 || n.npcType == 6233) {
			return 6952;
		}
		else if (n.npcType == 6222) {
			return 6974;
		}
		else if (n.npcType == 1153 || n.npcType == 1154 || n.npcType == 1155 || n.npcType == 1156 || n.npcType == 1157) {
			return 6227;
		}
		else if (n.npcType == 1158) {
			return 6227;
		}
		else if (n.npcType == 1160) {
			return 6237;
		}
		else if (n.npcType == 907) {
			return 424;
		}
		else if (n.npcType == 2744) {
			return 9268;
		}
		else if (n.npcType == 6203 || n.npcType == 6204 ||  n.npcType == 6208) {
			return 6944;
		}
		else if (n.npcType == 6260) {
			return 7061;
		}
		else if (n.npcType == 2744 || n.npcType == 2743) {
			return 9268;
		}
		else if (n.npcType == 6998 || n.npcType == 6999) {
			return 8592;
		}
		else if (n.npcType == 7770 || n.npcType == 7771) {
			return 9335;
		}
		else if (n.npcType == 2745) {
			return 9278;
		} else {
			return 2063;
		}
	}

	/**
	 * Gets maxhit by using if statements with npc ids.
	 */
	int getNpcMaxhit() {
	if (n.npcType == 8133) {
			return 62;
		}
		if (n.npcType == 2745) {
			return 97;
		}
		else if (n.npcType == 1610) {
			return 11;
		}
		else if (n.npcType == 5420) {
			return 15;
		}
		else if (n.npcType == 5621) {
			return 12;
		}
		else if (n.npcType == 1472) {
			return 30;
		}
		else if (n.npcType == 6269) {
			return 9;
		}
		else if (n.npcType == 6218) {
			return 21;
		}
		else if (n.npcType == 6210) {
			return 15;
		}
		else if (n.npcType == 6212) {
			return 18;
		}
		else if (n.npcType == 5253) {
			return 19;
		}
		else if (n.npcType == 6248) {
			return 20;
		}
		else if (n.npcType == 2746) {
			return 10;
		}
		else if (n.npcType == 2734 || n.npcType == 2735) {
			return 2;
		}
		else if (n.npcType == 2736 || n.npcType == 2737 || n.npcType == 2738) {
			return 4;
		}
		else if (n.npcType == 2741 || n.npcType == 2742) {
			return 16;
		}
		else if (n.npcType == 2739 || n.npcType == 2740) {
			return 10;
		}
		else if (n.npcType == 4291) {
			return 5;
		}
		else if (n.npcType == 4292) {
			return 8;
		}
		else if (n.npcType == 4284) {
			return 12;
		}
		else if (n.npcType == 6250) {
			return 21;
		}
		else if (n.npcType == 6247) {
			return 25;
		}
		else if (n.npcType == 6625) {
			return 25;
		}
		else if (n.npcType == 6691) {
			return 30;
		}
		else if (n.npcType == 6729) {
			return 24;
		}
		else if (n.npcType == 107) {
			return 3;
		}
		else if (n.npcType == 18) {
			return 2;
		}
		else if (n.npcType == 3847) {
			return 24;
		}
		else if (n.npcType == 2783) {
			return 17;
		}
		else if (n.npcType == 2614) {
			return 21;
		}
		else if (n.npcType == 134) {
			return 8;
		}
		else if (n.npcType == 6261) {
			return 21;
		}
		else if (n.npcType == 6223 || n.npcType == 6225 || n.npcType == 6227 || n.npcType == 6232 || n.npcType == 6233) {
			return 25;
		}
		else if (n.npcType == 1615) {
			return 14;
		}
		else if (n.npcType == 9) {
			return 3;
		}
		else if (n.npcType == 6204) {
			return 15;
		}
		else if (n.npcType == 1153) {
			return 4;
		}
		else if (n.npcType == 1154) {
			return 9;
		}
		else if (n.npcType == 1155) {
			return 16;
		}
		else if (n.npcType == 1156) {
			return 4;
		}
		else if (n.npcType == 1157) {
			return 16;
		}
		else if (n.npcType == 5902) {
			return 24;
		}
		else if (n.npcType == 1158) {
			return 24;
		}
		else if (n.npcType == 1158) {
			return 24;
		}
		else if (n.npcType == 1160) {
			return 22;
		}
		else if (n.npcType == 6222) {
			return 29;
		}
		else if (n.npcType == 50) {
			return 28;
		}
		else if (n.npcType == 5363) {
			return 30;
		}
		else if (n.npcType == 53) {
			return 23;
		}
		else if (n.npcType == 55) {
			return 14;
		}
		else if (n.npcType == 2744) {
			return 48;
		}
		else if (n.npcType == 6260) {
			return 61;
		}
		else if (n.npcType == 6998 || n.npcType == 6999) {
			return 16;
		}
		else if (n.npcType == 7770 || n.npcType == 7771) {
			return 25;
		}
		else if (n.npcType == 6203) {
			return 45;
		} else {
			return 1;
		}
	}

	void multiRangeAttk(int projectileId) {
		Player p = Server.engine.players[n.playerIndex];
		if (p == null || n == null|| n.isDead || n.currentHP <= 0 || p.skillLvl[3] <= 0 || p.disconnected[0]) {
			n.playerIndex = 0;
			n.resetAttack();
			return;
		}
		for (Player pl : Server.engine.players) {
			if (pl != null) {
				int casterX = n.absX;
				int casterY = n.absY;
				int offsetY1 = (n.absY - pl.absY) * -1;
				int offsetX1 = (n.absX - pl.absX) * -1;
				if (Misc.getDistance(n.absX, n.absY, pl.absX, pl.absY) <= 6 && pl.heightLevel == n.heightLevel) {
					pl.getActionSender().addProjectile(pl, casterY + 1, casterX + 1, offsetY1 - 1, offsetX1 - 1, 50, 82, projectileId, 68, 31, p.playerId, true);
				}
			}
		}
	}

	int getMagicDistance() {
		switch (n.npcType) {

		
			case 8133:
				return 20;
			case 2745:
				return 10;

			case 6625:
				return 10;
			case 6729:
				return 12;

			case 6203:
			case 6208:
				return 9;

			case 1158:
			case 1160:
				return 11;

			default:
				return 7;
		}
	}

    	public void appendPlayerFollowing(Player p) {
        	if(p == null) {
            		return;
        	}
        	int pX = p.absX;
        	int pY = p.absY;
        	if(pY < n.absY && pX == n.absX) {
            		n.moveX = 0;
            		n.moveY = Server.engine.npcMovement.getMove(n.absY, pY + 1);
        	}
        	else if(pY > n.absY && pX == n.absX) {
           		n.moveX = 0;
            		n.moveY = Server.engine.npcMovement.getMove(n.absY, pY - 1);
        	}
        	else if(pX < n.absX && pY == n.absY) {
            		n.moveX = Server.engine.npcMovement.getMove(n.absX, pX + 1);
            		n.moveY = 0;
        	}
        	else if(pX > n.absX && pY == n.absY) {
            		n.moveX = Server.engine.npcMovement.getMove(n.absX, pX - 1);
            		n.moveY = 0;
        	}
       		else if(pX < n.absX && pY < n.absY) {
            		n.moveX = Server.engine.npcMovement.getMove(n.absX, pX + 1);
            		n.moveY = Server.engine.npcMovement.getMove(n.absY, pY + 1);
        	}
        	else if(pX > n.absX && pY > n.absY) {
            		n.moveX = Server.engine.npcMovement.getMove(n.absX, pX - 1);
            		n.moveY = Server.engine.npcMovement.getMove(n.absY, pY - 1);
        	}
        	else if(pX < n.absX && pY > n.absY) {
            		n.moveX = Server.engine.npcMovement.getMove(n.absX, pX + 1);
            		n.moveY = Server.engine.npcMovement.getMove(n.absY, pY - 1);
        	}
        	else if(pX > n.absX && pY < n.absY) {
           		n.moveX = Server.engine.npcMovement.getMove(n.absX, pX - 1);
           		n.moveY = Server.engine.npcMovement.getMove(n.absY, pY + 1);
        	}
		Server.engine.npcMovement.getNextNPCMovement(n);
    	}

	int getChangeAnimDelay() {
		switch (n.npcType) {

			case 2741:
			case 2742:
				return 1;

			case 2745:
				return 3;
			
			case 8133:
			case 1158:
			case 1160:
				return 2;

			default:
				return -1;
		}
	}

	/**
	 * Sets atk delay for npc.
	 * @param atkDelay
	 */

	public void setAtkDelay(int atkDelay) {
		n.atkDelay = atkDelay;
	}

    	private int getRandom(int range) {
        	return (int)(Math.random() * (range + 1));
    	}

}