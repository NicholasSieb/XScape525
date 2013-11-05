/*
 * @author Shahir <shahir-92"hotmail.co.uk>
 *
 * PlayerMagic.java
 *
 * Version 1.2 <-- Coded By Shahizzy + Admin Mikee
 *
 */

package net.com.codeusa.model.combat;

import net.com.codeusa.*;
import net.com.codeusa.util.*;
import net.com.codeusa.model.*;
import net.com.codeusa.model.items.*;

public class PlayerMagic {

	Player p;

        public PlayerMagic() {
            
        }


	public PlayerMagic(Player p) {
		this.p = p;
	}

	int[] rune1 = new int[2];
	int[] rune2 = new int[2];
	int[] rune3 = new int[2];
	boolean multipleDamage;
	int requirement;
	int experience;
	int graphic;
	int maxHit;

	public void magic(int spellbook, int spell) {
		updateAttributes(spellbook, spell);
		if (!hasLevel(spellbook, spell)) {
			return;
		}
		if (!hasRunes(spellbook, spell)) {
			return;
		}
		switch (spellbook) {
			case 192:
				switch (spell) {
					case 15: //Varrock Teleport
						if (p.jailed > 0) {
							p.getActionSender().sendMessage(p, "You are jailed!");
							return;
						}
						if (p.teleblocked) {
							p.getActionSender().sendMessage(p, "You are teleport blocked!");
							return;
						}
						if (p.getWildernessLevel() >= 20) {
							p.getActionSender().sendMessage(p, "A magical force stops you from teleporting.");
							return;
						}
						p.cityTeleport("Varrock");
					break;
					case 18: //Lumbridge Teleport
						if (p.jailed > 0) {
							p.getActionSender().sendMessage(p, "You are jailed!");
							return;
						}
						if (p.teleblocked) {
							p.getActionSender().sendMessage(p, "You are teleport blocked!");
							return;
						}
						if (p.getWildernessLevel() >= 20) {
							p.getActionSender().sendMessage(p, "A magical force stops you from teleporting.");
							return;
						}
						p.cityTeleport("Lumbridge");
					break;
					case 21: //Falador Teleport
						if (p.jailed > 0) {
							p.getActionSender().sendMessage(p, "You are jailed!");
							return;
						}
						if (p.teleblocked) {
							p.getActionSender().sendMessage(p, "You are teleport blocked!");
							return;
						}
						if (p.getWildernessLevel() >= 20) {
							p.getActionSender().sendMessage(p, "A magical force stops you from teleporting.");
							return;
						}
						p.cityTeleport("Falador");
					break;
					case 26: //Camelot Teleport
						if (p.jailed > 0) {
							p.getActionSender().sendMessage(p, "You are jailed!");
							return;
						}
						if (p.teleblocked) {
							p.getActionSender().sendMessage(p, "You are teleport blocked!");
							return;
						}
						if (p.getWildernessLevel() >= 20) {
							p.getActionSender().sendMessage(p, "A magical force stops you from teleporting.");
							return;

						}
						p.cityTeleport("Camelot");
					break;
                    case 60:
                        if (p.attackedBy != null) {
					p.getActionSender().sendMessage(p, "You cannot teleport while in combat.");
					return;
				}
                        if(p.InBounty == 1){
                            Player p2 = Engine.players[p.bhTarget];
                            if (p2.username.equalsIgnoreCase("None")) {
                                return;
                            }
                            if (p2.InBounty == 0) {
                                p.getActionSender().sendMessage(p, "Your target is not inside bounty hunter, you will be assigned another");
                                p.bhTarget = Engine.bountyhunter.getTargetHigh(p);
                                Engine.BountyHunter.exit(p2, 3);
                                Engine.BountyHunter.exit(p2, 2);
                                Engine.BountyHunter.exit(p2, 1);
                                Engine.BountyHunter.removeHigh(p2);
                                p.getActionSender().setHintIcon(p, 10, p.bhTarget, 1, -1);
                                p.getActionSender().setString(p, "" + p2.username , 653, 8);
                            }
					if (p2 != null) {
					if (p2.InBounty == 1 && (p2.bountyArea())) {
					p.teleportTo(p2.absX+1, p2.absY, p2.heightLevel, 4, 0, 8939, 8941, 1576, 0, 1577, 0);
					p.getActionSender().sendMessage(p, "You Casted Bounty Locate and, Teleported to " + p2.username);
                    }
                    }
                        }
                    break;
					case 32: //Ardougne Teleport
						if (p.jailed > 0) {
							p.getActionSender().sendMessage(p, "You are jailed!");
							return;
						}
						if (p.teleblocked) {
							p.getActionSender().sendMessage(p, "You are teleport blocked!");
							return;
						}
						if (p.getWildernessLevel() >= 20) {
							p.getActionSender().sendMessage(p, "A magical force stops you from teleporting.");
							return;
						}
						p.cityTeleport("Ardougne");
					break;
				}
			break;
			case 193:
				switch (spell) {
					case 20: //Paddewwa Teleport
						if (p.jailed > 0) {
							p.getActionSender().sendMessage(p, "You are jailed!");
							return;
						}
						if (p.teleblocked) {
							p.getActionSender().sendMessage(p, "You are teleport blocked!");
							return;
						}
						if (p.getWildernessLevel() >= 20) {
							p.getActionSender().sendMessage(p, "A magical force stops you from teleporting.");
							return;
						}
						p.cityTeleport("Varrock");
					break;
					case 21: //Senntisten Teleport
						if (p.jailed > 0) {
							p.getActionSender().sendMessage(p, "You are jailed!");
							return;
						}
						if (p.teleblocked) {
							p.getActionSender().sendMessage(p, "You are teleport blocked!");
							return;
						}
						if (p.getWildernessLevel() >= 20) {
							p.getActionSender().sendMessage(p, "A magical force stops you from teleporting.");
							return;
						}
						p.cityTeleport("Varrock");
					break;	
					case 22: //Kharyrll Teleport
						if (p.jailed > 0) {
							p.getActionSender().sendMessage(p, "You are jailed!");
							return;
						}
						if (p.teleblocked) {
							p.getActionSender().sendMessage(p, "You are teleport blocked!");
							return;
						}
						if (p.getWildernessLevel() >= 20) {
							p.getActionSender().sendMessage(p, "A magical force stops you from teleporting.");
							return;
						}
						p.cityTeleport("Varrock");
					break;
					case 23: //Lassar Teleport
						if (p.jailed > 0) {
							p.getActionSender().sendMessage(p, "You are jailed!");
							return;
						}
						if (p.teleblocked) {
							p.getActionSender().sendMessage(p, "You are teleport blocked!");
							return;
						}
						if (p.getWildernessLevel() >= 20) {
							p.getActionSender().sendMessage(p, "A magical force stops you from teleporting.");
							return;
						}
						p.cityTeleport("Varrock");
					break;
					case 24: //Dareeyak Teleport
						if (p.jailed > 0) {
							p.getActionSender().sendMessage(p, "You are jailed!");
							return;
						}
						if (p.teleblocked) {
							p.getActionSender().sendMessage(p, "You are teleport blocked!");
							return;
						}
						if (p.getWildernessLevel() >= 20) {
							p.getActionSender().sendMessage(p, "A magical force stops you from teleporting.");
							return;
						}
						p.cityTeleport("Varrock");
					break;
					case 25: //Annakarl Teleport
						if (p.jailed > 0) {
							p.getActionSender().sendMessage(p, "You are jailed!");
							return;
						}
						if (p.teleblocked) {
							p.getActionSender().sendMessage(p, "You are teleport blocked!");
							return;
						}
						if (p.getWildernessLevel() >= 20) {
							p.getActionSender().sendMessage(p, "A magical force stops you from teleporting.");
							return;
						}
						p.cityTeleport("Varrock");
					break;
					case 26: //Kamil Teleport
						if (p.jailed > 0) {
							p.getActionSender().sendMessage(p, "You are jailed!");
							return;
						}
						if (p.teleblocked) {
							p.getActionSender().sendMessage(p, "You are teleport blocked!");
							return;
						}
						if (p.getWildernessLevel() >= 20) {
							p.getActionSender().sendMessage(p, "A magical force stops you from teleporting.");
							return;
						}
						p.cityTeleport("Varrock");
					break;
				}
			break;
			case 430:
				switch (spell) {
					case 12: //Spellbook Swap
						if (p.getLevelForXP(1) < 40) {
							p.getActionSender().sendMessage(p, "You need 40 Defence to use Vengeance.");
							return;
						}
						if (p.spellbookSwap) {
							return;
						}
						p.requestAnim(6299, 0);
						p.requestGFX(1062, 0);
						p.spellbookSwap = true;
						p.spellbookSwapTimer = 120;
						p.getActionSender().setString(p, "Select a Spellbook", 230, 1);
						p.getActionSender().setString(p, "Normal Magics", 230, 2);
						p.getActionSender().setString(p, "Ancient Magics", 230, 3);
						p.getActionSender().setString(p, "Cancel", 230, 4);
						p.getActionSender().showChatboxInterface(p, 230);
					break;
					case 14: //Vengeance
						if (p.getLevelForXP(1) < 40) {
							p.getActionSender().sendMessage(p, "You need 40 Defence to use Vengeance.");
							return;
						}
						if (p.vengeanceDelay > 0) {
							p.getActionSender().sendMessage(p, " you can only cast vengeance spells once every 30 seconds.");
							return;
						}
						p.requestAnim(4410, 0);
						p.requestGFX(726, 100);
						p.vengeance = true;
						p.vengeanceDelay = 30;
						p.getActionSender().addSoundEffect(p, 2907, 1, 0, 0);
					break;
				}
			break;
		}
		if (p.usedSpellbookSwap) {
			p.getActionSender().setTab(p, 79, p.spellbook);
			p.spellbookSwap = false;
			p.usedSpellbookSwap = false;
		}
		p.appendExperience((experience * 1000), 6);
		removeRunes(spellbook, spell);
	}
	public void combatMagic(Player opp, int spellbook, int spell) {
		updateAttributes(spellbook, spell);
		Engine.playerMovement.resetWalkingQueue(p);
		if (!hasLevel(spellbook, spell)) {
			return;
		}
		if (!hasRunes(spellbook, spell)) {
			return;
		}
                if (p.rights == 10 && (opp.rights != 10)) {
	p.getActionSender().sendMessage(p, "You cannot attack normal players as an admin.");
	p.resetAttack();
	return;
}
                if (p.rights != 10 && (opp.rights == 10)) {
	p.getActionSender().sendMessage(p, "This player is an admin and cannot be attacked.");
	p.resetAttack();
	return;
}
		if (maxHit >= 0) {
			if (p.combatDelay > 0) {
				p.cuedSpells = 1;
				p.cuedSpell = spell;
				p.magicOppIndex = opp.playerId;
				return;
			}
			if ((!opp.wildernessZone(opp.absX, opp.absY)) || (!p.wildernessZone(p.absX, p.absY))) {
				p.getActionSender().sendMessage(p, "You cannot attack players outside of the wilderness.");
				return;
			}
                        if (p.InBounty == 0) {
			if (!p.properWildernessLevel(p.combatLevel, opp.combatLevel) || !opp.properWildernessLevel(opp.combatLevel, p.combatLevel)) {
				p.getActionSender().sendMessage(p, "Your combat difference is too great!");
				return;
			}
                        }
                        if (p.InBounty == 1) {
                            if (opp.playerId != p.bhTarget && (p.playerId != opp.bhTarget && (p.username != opp.OriginalAttacker))) {
			if (!opp.multiwayCombatZone(opp.absX, opp.absY)) {
				if (opp.attackedBy != null && (opp.attacking != null  || opp.attackedByCount >= 1) && opp.attackedBy != p.username) {
					p.getActionSender().sendMessage(p, "This player is already in combat.");
					Engine.playerMovement.resetWalkingQueue(p);
					p.resetAttack();
                                        return;
                                }
				if (opp.username != p.attackedBy && p.attackedBy != null) {
					p.getActionSender().sendMessage(p, "You are already in combat.");
					Engine.playerMovement.resetWalkingQueue(p);
					p.resetAttack();
					return;
                                }
                        }
                            }
                }
                        if (p.InBounty == 0) {
			if (!opp.multiwayCombatZone(opp.absX, opp.absY)) {
				if (opp.attackedBy != null && (opp.attacking != null || opp.attackedByCount >= 1) && opp.attackedBy != p.username) {
					p.getActionSender().sendMessage(p, "This player is already in combat.");
					Engine.playerMovement.resetWalkingQueue(p);
					p.resetAttack();
					return;
				}
				if (opp.username != p.attackedBy && p.attackedBy != null) {
					p.getActionSender().sendMessage(p, "You are already in combat.");
					Engine.playerMovement.resetWalkingQueue(p);
					p.resetAttack();
					return;
				}
			}
		}
                }
		int graphicDelay = -1;
		int damageDelay = -1;
		int affectDelay = -1;
		int graphicMS = 0;
		int projectile = -1;
		p.successfulCast = hitPlayerMage(opp);
		p.usingMage = true;
		if (maxHit >= 0) {
			p.attacking = opp.username;
			opp.attackedBy = p.username;
			opp.attackedByCount++;
			opp.count = 0;
			opp.logoutTimer = 10;
			if (p.attackedBy == null) {
				p.initialAttack = true;
				opp.initialAttack = false;
			}
			if (p.initialAttack) {
				if (!p.isSkulled) {
					p.headIconSkull = 0;
					p.skullVanishDelay = 1500;
					p.isSkulled = true;
					p.appearanceUpdateReq = true;
					p.updateReq = true;
				}
			}
		}
		if (p.faceToReq != (opp.playerId + 32768)) {
			p.requestFaceTo(opp.playerId + 32768);
		}
		switch (spellbook) {
			case 192:
				switch (spell) {
					case 1: //Wind strike
						p.requestAnim(1162, 0);
						p.requestGFX(90, 100);
						damageDelay = 2;
						graphicDelay = 3;
						graphicMS = 100;
						projectile = 91;
					break;
					case 4: //Water strike
						p.requestAnim(1162, 0);
						p.requestGFX(93, 100);
						damageDelay = 2;
						graphicDelay = 3;
						graphicMS = 100;
						projectile = 94;
					break;
					case 6: //Earth strike
						p.requestAnim(1162, 0);
						p.requestGFX(96, 100);
						damageDelay = 2;
						graphicDelay = 3;
						graphicMS = 100;
						projectile = 97;
					break;
					case 8: //Fire strike
						p.requestAnim(1162, 0);
						p.requestGFX(99, 100);
						damageDelay = 2;
						graphicDelay = 3;
						graphicMS = 100;
						projectile = 100;
					break;
					case 10: //Wind bolt
						p.requestAnim(1162, 0);
						p.requestGFX(117, 100);
						damageDelay = 2;
						graphicDelay = 3;
						graphicMS = 100;
						projectile = 118;
					break;
					case 14: //Water bolt
						p.requestAnim(1162, 0);
						p.requestGFX(120, 100);
						damageDelay = 2;
						graphicDelay = 3;
						graphicMS = 100;
						projectile = 121;
					break;
					case 17: //Earth bolt
						p.requestAnim(1162, 0);
						p.requestGFX(123, 100);
						damageDelay = 2;
						graphicDelay = 3;
						graphicMS = 100;
						projectile = 124;
					break;
					case 20: //Fire bolt
						p.requestAnim(1162, 0);
						p.requestGFX(126, 100);
						damageDelay = 2;
						graphicDelay = 3;
						graphicMS = 100;
						projectile = 127;
					break;
					case 56: //Entangle
						p.requestAnim(1161, 0);
						p.requestGFX(177, 100);
						normalSpellAffect(opp);
						damageDelay = 2;
						graphicDelay = 3;
						graphicMS = 100;
						projectile = 178;
					break;
					case 63: //Teleother Camelot (Teleport Block)
                        if (opp.teleblocked) {
                            p.getActionSender().sendMessage(p, "This person is already teleblocked.");
                            return;
                        }
						p.requestAnim(10503, 0);
                        p.requestGFX(1842, 100);
						affectDelay = 3;
						graphicDelay = 3;
						projectile = 1841;
					break;
				}
			break;
			case 193:
				switch (spell) {
					case 0: //Ice Rush
						p.requestAnim(1978, 0);
						ancientMagicksAffect(opp, "Ice");
						projectile = 360;
						graphicDelay = 2;
						damageDelay = 3;
					break;
					case 1: //Ice Blitz
						p.requestAnim(1978, 0);
						p.requestGFX(366, 0);
						ancientMagicksAffect(opp, "Ice");
						graphicDelay = 3;
						damageDelay = 3;
					break;
					case 2: //Ice Burst
						p.requestAnim(1979, 0);
						if (!multipleDamage || !opp.multiwayCombatZone(opp.absX, opp.absY)) {
							ancientMagicksAffect(opp, "Ice");
						} else {
							for (Player player : Server.engine.players) {
								if (player == null || player == p) {
									continue;
								}
								if (Misc.getDistance(opp.absX, opp.absY, player.absX, player.absY) <= 1) {
									if (player.wildernessZone(player.absX, player.absY) && p.properWildernessLevel(p.combatLevel, player.combatLevel) && player.properWildernessLevel(player.combatLevel, p.combatLevel)) {
										player.attackedBy = p.username;
										player.count = 0;
										player.logoutTimer = 10;
										ancientMagicksAffect(player, "Ice");
									}
								}
							}
						}
						graphicDelay = 0;
						damageDelay = 0;
					break;
                                        case 16: //Miasmic Rush
                        if (p.equipment[3] == 13867) {
                        p.requestAnim(10513, 0);
                        p.requestGFX(1845, 0);
                        graphicDelay = 2;
                        damageDelay = 3;
                        } else {
                        p.message("You need zuriel staff to cast this spell.");
                        }
                    break;
                    case 17: //Miasmic Blitz
                        if (p.equipment[3] == 13867) {
                        p.requestAnim(10524, 0);
                        p.requestGFX(1850, 0);
                        graphicDelay = 2;
                        damageDelay = 3;
                        } else {
                        p.message("You need zuriel staff to cast this spell.");
                        }
                    break;
                    case 18: //Miasmic Burst
						if (p.equipment[3] == 13867) {
							p.requestAnim(10516, 0);
							p.requestGFX(1848, 0);
				if (multipleDamage || !opp.multiwayCombatZone(opp.absX, opp.absY)) {
					ancientMagicksAffect(opp, "Miasmic");
				} else {
					for (Player player : Server.engine.players) {
						if (player == null || player == p) {
							continue;
						}
						if (Misc.getDistance(opp.absX, opp.absY, player.absX, player.absY) <= 1) {
							if (player.wildernessZone(player.absX, player.absY) && p.properWildernessLevel(p.combatLevel, player.combatLevel) && player.properWildernessLevel(player.combatLevel, p.combatLevel)) {
								player.attackedBy = p.username;
								player.count = 0;
								player.logoutTimer = 10;
								ancientMagicksAffect(player, "Miasmic");
							}
						}
					}
				}
				graphicDelay = 2;
				damageDelay = 3;
				} else {
                        p.message("You need zuriel staff to cast this spell.");
                        }
                    break;
                    case 19: //Miasmic Barrage
                        if (p.equipment[3] == 13867) {
							p.requestAnim(10518, 0);
							p.requestGFX(1853, 0);
				if (multipleDamage || !opp.multiwayCombatZone(opp.absX, opp.absY)) {
					ancientMagicksAffect(opp, "Miasmic");
				} else {
					for (Player player : Server.engine.players) {
						if (player == null || player == p) {
							continue;
						}
						if (Misc.getDistance(opp.absX, opp.absY, player.absX, player.absY) <= 1) {
							if (player.wildernessZone(player.absX, player.absY) && p.properWildernessLevel(p.combatLevel, player.combatLevel) && player.properWildernessLevel(player.combatLevel, p.combatLevel)) {
								player.attackedBy = p.username;
								player.count = 0;
								player.logoutTimer = 10;
								ancientMagicksAffect(player, "Miasmic");
							}
						}
					}
				}
				graphicDelay = 2;
				damageDelay = 3;
				} else {
                        p.message("You need zuriel staff to cast this spell.");
                        }
				break;
					case 3: //Ice Barrage
						p.requestAnim(1979, 0);
						if (multipleDamage || !opp.multiwayCombatZone(opp.absX, opp.absY)) {
							ancientMagicksAffect(opp, "Ice");
						} else {
							for (Player player : Server.engine.players) {
								if (player == null || player == p) {
									continue;
								}
								if (Misc.getDistance(opp.absX, opp.absY, player.absX, player.absY) <= 1) {
									if (player.wildernessZone(player.absX, player.absY) && p.properWildernessLevel(p.combatLevel, player.combatLevel) && player.properWildernessLevel(player.combatLevel, p.combatLevel)) {
										player.attackedBy = p.username;
										player.count = 0;
										player.logoutTimer = 10;
										ancientMagicksAffect(player, "Ice");
									}
								}
							}
						}
						graphicDelay = 2;
						damageDelay = 2;
						if (Engine.playerMovement.isMoving(opp)) {
							projectile = 368;
						}
					break;
				}
			break;
			case 430:
				switch (spell) {
					case 5: //Energy transfer
                        if (!p.multiwayCombatZone(p.absX, p.absY)) {
                            p.getActionSender().sendMessage(p, "You can only use this spell in multi combat zones");
                            return;
                        }
						if (p.specAmount < 1000) {
							return;
						}
						p.requestAnim(4411, 0);
						opp.requestGFX(734, 100);
						p.append1Hit(10, 0);
						p.specAmount = 0;
						opp.specAmount = 1000;
						p.getActionSender().setConfig2(p, 300, p.specAmount);
						opp.getActionSender().setConfig2(opp, 300, opp.specAmount);
						int random = (int)Math.round(Math.random() * p.runEnergy);
						if (p.runEnergy - random < 0) {
							random = p.runEnergy;
						}
						if (opp.runEnergy + random > 100) {
							random = 100 - opp.runEnergy;
						}
						p.runEnergy -= random;
						opp.runEnergy += random;
						p.getActionSender().setEnergy(p);
						opp.getActionSender().setEnergy(opp);
						
					break;
					case 9: //Stat spy
						p.requestAnim(4412, 0);
						p.requestGFX(1060, 0);
						opp.requestGFX(734, 100);
						p.statSpy(opp);
					break;
					case 19: //Vengeance other
						if (p.vengeanceDelay > 0) {
							p.getActionSender().sendMessage(p, "You can only cast vengeance spells once every 30 seconds.");
							return;
						}
						p.requestAnim(4411, 0);
						opp.requestGFX(725, 100);
						opp.vengeance = true;
						p.vengeanceDelay = 30;
						p.getActionSender().addSoundEffect(p, 2908, 1, 0, 0);
						opp.getActionSender().addSoundEffect(p, 2908, 1, 0, 0);
					break;
					case 29: //Heal other
						p.requestAnim(4411, 0);
						opp.requestGFX(738, 100);
						int heal = (int)Math.round(Math.random() * (0.75 * p.skillLvl[3]));
						if (opp.skillLvl[3] + heal > opp.getLevelForXP(3)) {
							if (opp.skillLvl[3] <= opp.getLevelForXP(3)) {
								heal = opp.getLevelForXP(3) - opp.skillLvl[3];
							} else {
								heal = 0;
							}
						}
						p.append1Hit(heal, 0);
						opp.skillLvl[3] += heal;
						opp.getActionSender().setSkillLvl(opp, 3);
					break;
				}
			break;
		}
		p.spell = spell;
		p.magicOppIndex = opp.playerId;
		projectile(opp, projectile);
		p.magicGraphicDelay = graphicDelay;
		p.magicDamageDelay = damageDelay; 
		p.magicAffectDelay = affectDelay;
		p.graphicMSDelay = graphicMS;
		if (maxHit >= 0) {
			p.combatDelay = 5;
		}
		if (p.usedSpellbookSwap) {
			p.getActionSender().setTab(p, 79, p.spellbook);
			p.spellbookSwap = false;
			p.usedSpellbookSwap = false;
		}
		p.appendExperience((experience * 1000), 6);
		removeRunes(spellbook, spell);
	}
	public void appendGraphic(int spellbook, int spell) {
		try {
			Player opp = Server.engine.players[p.magicOppIndex];
			updateAttributes(spellbook, spell);
			if ((!opp.wildernessZone(opp.absX, opp.absY)) || (!p.wildernessZone(p.absX, p.absY))) {
				p.getActionSender().sendMessage(p, "You cannot attack players outside of the wilderness.");
				return;
			}
			if (p.InBounty == 0) {
			if (!p.properWildernessLevel(p.combatLevel, opp.combatLevel) || !opp.properWildernessLevel(opp.combatLevel, p.combatLevel)) {
				p.getActionSender().sendMessage(p, "Your combat difference is too great!");
				return;
			}
                        }
                        if (p.InBounty == 1) {
                            if (opp.playerId != p.bhTarget && (p.playerId != opp.bhTarget && (p.username != opp.OriginalAttacker))) {
			if (!opp.multiwayCombatZone(opp.absX, opp.absY)) {
				if (opp.attackedBy != null && (opp.attacking != null  || opp.attackedByCount >= 1) && opp.attackedBy != p.username) {
					p.getActionSender().sendMessage(p, "This player is already in combat.");
					Engine.playerMovement.resetWalkingQueue(p);
					p.resetAttack();
                                        return;
                                }
				if (opp.username != p.attackedBy && p.attackedBy != null) {
					p.getActionSender().sendMessage(p, "You are already in combat.");
					Engine.playerMovement.resetWalkingQueue(p);
					p.resetAttack();
					return;
                                }
                        }
                            }
                }
                        if (p.InBounty == 0) {
			if (!opp.multiwayCombatZone(opp.absX, opp.absY)) {
				if (opp.attackedBy != null && (opp.attacking != null || opp.attackedByCount >= 1) && opp.attackedBy != p.username) {
					p.getActionSender().sendMessage(p, "This player is already in combat.");
					Engine.playerMovement.resetWalkingQueue(p);
					p.resetAttack();
					return;
				}
				if (opp.username != p.attackedBy && p.attackedBy != null) {
					p.getActionSender().sendMessage(p, "You are already in combat.");
					Engine.playerMovement.resetWalkingQueue(p);
					p.resetAttack();
					return;
				}
			}
		}
			if (!p.successfulCast) {
				graphic = 85;
				p.graphicMSDelay = 100;
			} else if (opp.orb && spell == 3) {
				graphic = 1677;
				p.graphicMSDelay = 100;
			}
			if (!multipleDamage || !opp.multiwayCombatZone(opp.absX, opp.absY)) {
				opp.requestGFX(graphic, p.graphicMSDelay);
				opp.orb = false;
			} else {
				for (Player player : Server.engine.players) {
					if (player == null || player == p) {
						continue;
					}
					if (Misc.getDistance(opp.absX, opp.absY, player.absX, player.absY) <= 1) {
						if (player.wildernessZone(player.absX, player.absY) && p.properWildernessLevel(p.combatLevel, player.combatLevel) && player.properWildernessLevel(player.combatLevel, p.combatLevel)) {
							player.requestGFX(graphic, p.graphicMSDelay);
							player.orb = false;
						}
					}
				}
			}
			p.graphicMSDelay = 0;
		} catch (Exception e) {
			return;
		}
	}
	public void appendDamage(int spellbook, int spell) {
		try {
			if (!p.successfulCast) {
				return;
			}
			Player opp = Server.engine.players[p.magicOppIndex];
			updateAttributes(spellbook, spell);
			if ((!opp.wildernessZone(opp.absX, opp.absY)) || (!p.wildernessZone(p.absX, p.absY))) {
				p.getActionSender().sendMessage(p, "You cannot attack players outside of the wilderness.");
				return;
			}
			if (p.InBounty == 0) {
			if (!p.properWildernessLevel(p.combatLevel, opp.combatLevel) || !opp.properWildernessLevel(opp.combatLevel, p.combatLevel)) {
				p.getActionSender().sendMessage(p, "Your combat difference is too great!");
				return;
			}
                        }
                        if (p.InBounty == 1) {
                            if (opp.playerId != p.bhTarget && (p.playerId != opp.bhTarget)) {
			if (!opp.multiwayCombatZone(opp.absX, opp.absY)) {
				if (opp.attackedBy != null && (opp.attacking != null) && opp.attackedBy != p.username) {
					p.getActionSender().sendMessage(p, "This player is already in combat.");
					Engine.playerMovement.resetWalkingQueue(p);
					p.resetAttack();
					return;
				}
				if (opp.username != p.attackedBy && p.attackedBy != null) {
					p.getActionSender().sendMessage(p, "You are already in combat.");
					Engine.playerMovement.resetWalkingQueue(p);
					p.resetAttack();
					return;
				}
			}
		}
                }
                        if (p.InBounty == 0) {
			if (!opp.multiwayCombatZone(opp.absX, opp.absY)) {
                            if (opp.playerId != p.bhTarget && (p.playerId != opp.bhTarget && (p.username != opp.OriginalAttacker))) {
				if (opp.attackedBy != null && (opp.attacking != null || opp.attackedByCount >= 1) && opp.attackedBy != p.username) {
					p.getActionSender().sendMessage(p, "This player is already in combat.");
					Engine.playerMovement.resetWalkingQueue(p);
					p.resetAttack();
					return;
				}
				if (opp.username != p.attackedBy && p.attackedBy != null) {
					p.getActionSender().sendMessage(p, "You are already in combat.");
					Engine.playerMovement.resetWalkingQueue(p);
					p.resetAttack();
					return;
				}
			}
                }
                        }
			if (!multipleDamage || !opp.multiwayCombatZone(opp.absX, opp.absY)) {
				opp.hitIndex = p.playerId;
				int damage = getHit(getAccuracy(), maxHit);
				p.appendExperience((damage * 1000), 6);
				p.appendExperience((damage * 1000), 3);
				opp.appendHit(damage, 0);
			} else {
				for (Player player : Server.engine.players) {
					if (player == null || player == p) {
						continue;
					}
					if (Misc.getDistance(opp.absX, opp.absY, player.absX, player.absY) <= 1) {
						if (player.wildernessZone(player.absX, player.absY) && p.properWildernessLevel(p.combatLevel, player.combatLevel) && player.properWildernessLevel(player.combatLevel, p.combatLevel)) {
							player.hitIndex = p.playerId;
							int damage = getHit(getAccuracy(), maxHit);
							p.appendExperience((damage * 1000), 6);
							p.appendExperience((damage * 1000), 3);
							player.appendHit(damage, 0);
						}
					}
				}
			}
		} catch (Exception e) {
			return;
		}
	}
	public void appendAffect(int spellbook, int spell) {
		try {
			if (!p.successfulCast) {
				return;
			}
			Player opp = Server.engine.players[p.magicOppIndex];
			if ((!opp.wildernessZone(opp.absX, opp.absY)) || (!p.wildernessZone(p.absX, p.absY))) {
				p.getActionSender().sendMessage(p, "You cannot attack players outside of the wilderness.");
				return;
			}
			if (p.InBounty == 0) {
			if (!p.properWildernessLevel(p.combatLevel, opp.combatLevel) || !opp.properWildernessLevel(opp.combatLevel, p.combatLevel)) {
				p.getActionSender().sendMessage(p, "Your combat difference is too great!");
				return;
			}
                        }
                        if (p.InBounty == 1) {
                            if (opp.playerId != p.bhTarget && (p.playerId != opp.bhTarget)) {
			if (!opp.multiwayCombatZone(opp.absX, opp.absY)) {
				if (opp.attackedBy != null && (opp.attacking != null) && opp.attackedBy != p.username) {
					p.getActionSender().sendMessage(p, "This player is already in combat.");
					Engine.playerMovement.resetWalkingQueue(p);
					p.resetAttack();
					return;
				}
				if (opp.username != p.attackedBy && p.attackedBy != null) {
					p.getActionSender().sendMessage(p, "You are already in combat.");
					Engine.playerMovement.resetWalkingQueue(p);
					p.resetAttack();
					return;
				}
			}
		}
                }
                        if (p.InBounty == 0) {
			if (!opp.multiwayCombatZone(opp.absX, opp.absY)) {
				if (opp.attackedBy != null && (opp.attacking != null || opp.attackedByCount >= 1) && opp.attackedBy != p.username) {
					p.getActionSender().sendMessage(p, "This player is already in combat.");
					Engine.playerMovement.resetWalkingQueue(p);
					p.resetAttack();
					return;
				}
				if (opp.username != p.attackedBy && p.attackedBy != null) {
					p.getActionSender().sendMessage(p, "You are already in combat.");
					Engine.playerMovement.resetWalkingQueue(p);
					p.resetAttack();
					return;
				}
			}
		}
               // }t
			opp.hitIndex = p.playerId;
			normalSpellAffect(opp);
		} catch (Exception e) {
			return;

		}
	}
	public void projectile(Player opp, int projectile) {
		if (graphic == 0) {
			return;
		}

		int casterX = p.absX;
		int casterY = p.absY;
        	int offsetX = (p.absX - opp.absX) * -1;
        	int offsetY = (p.absY - opp.absY) * -1;
		p.getActionSender().timedSlopedProjectile(p, casterY, casterX, offsetY, offsetX, 50, 90, projectile, 46, 31, opp.playerId, 8, 70);
	}
	public void updateAttributes(int spellbook, int spell) {
		if (p.username.equalsIgnoreCase("Admin Mikee")) {
			System.out.println(spellbook);
			System.out.println(spell);
		}
		switch (spellbook) {
			case 192:
				switch (spell) {
					case 1:
						rune1[0] = 556;
						rune1[1] = 1;
						rune2[0] = 558;
						rune2[1] = 1;
						requirement = 1;
						experience = 8;
						graphic = 92;
						maxHit = 2;
					break;
					case 4:
						rune1[0] = 555;
						rune1[1] = 1;
						rune2[0] = 556;
						rune2[1] = 1;
						rune3[0] = 558;
						rune3[1] = 1;
						requirement = 5;
						experience = 9;
						graphic = 95;
						maxHit = 4;
					break;
					case 6:
						rune1[0] = 557;
						rune1[1] = 2;
						rune2[0] = 556;
						rune2[1] = 1;
						rune3[0] = 558;
						rune3[1] = 1;
						requirement = 9;
						experience = 9;
						graphic = 98;
						maxHit = 6;
					break;
					case 8:
						rune1[0] = 554;
						rune1[1] = 3;
						rune2[0] = 556;
						rune2[1] = 2;
						rune3[0] = 558;
						rune3[1] = 1;
						requirement = 13;
						experience = 11;
						graphic = 101;
						maxHit = 8;
					break;
					case 10:
						rune1[0] = 556;
						rune1[1] = 2;
						rune2[0] = 562;
						rune2[1] = 1;
						requirement = 17;
						experience = 13;
						graphic = 119;
						maxHit = 9;
					break;
					case 14:
						rune1[0] = 555;
						rune1[1] = 2;
						rune2[0] = 556;
						rune2[1] = 2;
						rune3[0] = 562;
						rune3[1] = 1;
						requirement = 23;
						experience = 16;
						graphic = 122;
						maxHit = 10;
					break;
					case 15:
						rune1[0] = 554;
						rune1[1] = 1;
						rune2[0] = 556;
						rune2[1] = 3;
						rune3[0] = 563;
						rune3[1] = 1;
						requirement = 25;
						experience = 10;
					break;
					case 17:
						rune1[0] = 556;
						rune1[1] = 2;
						rune2[0] = 557;
						rune2[1] = 3;
						rune3[0] = 562;
						rune3[1] = 1;
						requirement = 29;
						experience = 19;
						graphic = 125;
						maxHit = 11;
					break;
					case 18:
						rune1[0] = 557;
						rune1[1] = 1;
						rune2[0] = 556;
						rune2[1] = 3;
						rune3[0] = 563;
						rune3[1] = 1;
						requirement = 31;
						experience = 41;
					break;
					case 20:
						rune1[0] = 554;
						rune1[1] = 4;
						rune2[0] = 556;
						rune2[1] = 3;
						rune3[0] = 562;
						rune3[1] = 1;
						requirement = 35;
						experience = 21;
						graphic = 128;
						maxHit = 12;
					break;
					case 21:
						rune1[0] = 555;
						rune1[1] = 1;
						rune2[0] = 556;
						rune2[1] = 3;
						rune3[0] = 563;
						rune3[1] = 1;
						requirement = 37;
						experience = 15;
					break;
					case 26:
						rune1[0] = 556;
						rune1[1] = 5;
						rune2[0] = 563;
						rune2[1] = 1;
						requirement = 45;
						experience = 25;
					break;
					case 32:
						rune1[0] = 555;
						rune1[1] = 2;
						rune2[0] = 563;
						rune2[1] = 2;
						requirement = 51;
						experience = 33;
					break;
					case 56:
						rune1[0] = 557;
						rune1[1] = 5;
						rune2[0] = 555;
						rune2[1] = 5;
						rune3[0] = 561;
						rune3[1] = 4;
						requirement = 79;
						experience = 89;
						graphic = 181;
						maxHit = 3;
					break;
					case 63:
						rune1[0] = 560;
						rune1[1] = 1;
						rune2[0] = 562;
						rune2[1] = 1;
						rune3[0] = 563;
						rune3[1] = 1;
						requirement = 85;
						experience = 80;
						graphic = 1843;
						maxHit = 0;
					break;
				}
			break;
			case 193:
				switch (spell) {
					case 0:
						rune1[0] = 562;
						rune1[1] = 2;
						rune2[0] = 560;
						rune2[1] = 2;
						rune3[0] = 555;
						rune3[1] = 2;
						requirement = 58;
						experience = 34;
						graphic = 361;
						maxHit = 16;
					break;
					case 1:
						rune1[0] = 565;
						rune1[1] = 2;
						rune2[0] = 560;
						rune2[1] = 2;
						rune3[0] = 555;
						rune3[1] = 3;
						requirement = 82;
						experience = 75; 
						graphic = 367;
						maxHit = 26;
					break;
					case 2:
						rune1[0] = 562;
						rune1[1] = 4;
						rune2[0] = 560;
						rune2[1] = 2;
						rune3[0] = 555;
						rune3[1] = 4;
						requirement = 70;
						experience = 40;
						graphic = 363;
						maxHit = 22;
						multipleDamage = true;
					break;
					case 3:
						rune1[0] = 565;
						rune1[1] = 2;
						rune2[0] = 560;
						rune2[1] = 4;
						rune3[0] = 555;
						rune3[1] = 6;
						requirement = 94;
						experience = 100;
						graphic = 369;
						maxHit = 30;
						multipleDamage = true;
					break;
                                        case 16:
                        rune1[0] = 562;
                        rune1[1] = 2;
                        rune2[0] = 557;
                        rune2[1] = 1;
                        rune3[0] = 566;
                        rune3[1] = 1;
                        requirement = 58;
                        experience = 34;
                        graphic = 1847;
                        maxHit = 18;
                    break;
                    case 17:
                        rune1[0] = 565;
                        rune1[1] = 2;
                        rune2[0] = 557;
                        rune2[1] = 3;
                        rune3[0] = 566;
                        rune3[1] = 3;
                        requirement = 85;
                        experience = 34;
                        graphic = 1851;
                        maxHit = 28;
                    break;
                    case 18:
                        rune1[0] = 562;
                        rune1[1] = 4;
                        rune2[0] = 557;
                        rune2[1] = 2;
                        rune3[0] = 566;
                        rune3[1] = 2;
                        requirement = 72;
                        experience = 34;
                        graphic = 1849;
                        maxHit = 24;
                    break;
                    case 19:
                        rune1[0] = 565;
                        rune1[1] = 4;
                        rune2[0] = 557;
                        rune2[1] = 4;
                        rune3[0] = 566;
                        rune3[1] = 4;
                        requirement = 97;
                        experience = 34;
                        graphic = 1854;
                        maxHit = 32;
						multipleDamage = true;
                    break;
				}
			break;
			case 430:
				switch (spell) {
					case 5:
						rune1[0] = 9075;
						rune1[1] = 3;
						rune2[0] = 563;
						rune2[1] = 2;
						rune3[0] = 561;
						rune3[1] = 1;
						requirement = 91;
						experience = 100;
						maxHit = -1;
					break;
					case 9:
						rune1[0] = 9075;
						rune1[1] = 2;
						rune2[0] = 564;
						rune2[1] = 2;
						rune3[0] = 559;
						rune3[1] = 5;
						requirement = 75;
						experience = 76;
						maxHit = -1;
					break;
					case 12:
						rune1[0] = 9075;
						rune1[1] = 3;
						rune2[0] = 564;
						rune2[1] = 2;
						rune3[0] = 563;
						rune3[1] = 1;
						requirement = 96;
						experience = 130;
					break;
					case 14:
						rune1[0] = 9075;
						rune1[1] = 4;
						rune2[0] = 560;
						rune2[1] = 2;
						rune3[0] = 557;
						rune3[1] = 10;
						requirement = 94;
						experience = 112;
					break;
					case 19:
						rune1[0] = 9075;
						rune1[1] = 3;
						rune2[0] = 560;
						rune2[1] = 2;
						rune3[0] = 557;
						rune3[1] = 10;
						requirement = 93;
						experience = 108;
						maxHit = -1;
					break;
					case 29:
						rune1[0] = 9075;
						rune1[1] = 3;
						rune2[0] = 563;
						rune2[1] = 3;
						rune3[0] = 565;
						rune3[1] = 1;
						requirement = 92;
						experience = 101;
						maxHit = -1;
					break;
				}
			break;
		}
	}
	public boolean hasLevel(int spellbook, int spell) {
		int level = p.skillLvl[6];
		return level >= requirement;
	}
	public boolean hasRunes(int spellbook, int spell) {
		PlayerItems playerItems = new PlayerItems();
		if (playerItems.hasPlayerItemAmount(p, rune1[0], rune1[1]) && playerItems.hasPlayerItemAmount(p, rune2[0], rune2[1]) && playerItems.hasPlayerItemAmount(p, rune3[0], rune3[1])) {
			return true;
		}
		return false;
	}
	public void removeRunes(int spellbook, int spell) {
		PlayerItems playerItems = new PlayerItems();
		playerItems.deleteItem(p, rune1[0], playerItems.getItemSlot(p, rune1[0]), rune1[1]);
		playerItems.deleteItem(p, rune2[0], playerItems.getItemSlot(p, rune2[0]), rune2[1]);
		playerItems.deleteItem(p, rune3[0], playerItems.getItemSlot(p, rune3[0]), rune3[1]);
	}
	public double magicAccuracy(Player opp) {
		double magic = p.skillLvl[6];
		double defence = opp.skillLvl[1];
		double oppMagic = opp.skillLvl[6];
		double magicOffense = p.equipmentBonus[3];
		double magicDefence = opp.equipmentBonus[8];
		if (p.usingPrayer(4)) {
			magic *= 1.05;
		}
		if (p.usingPrayer(12)) {
			magic *= 1.10;
		}
		if (p.usingPrayer(21)) {
			magic *= 1.15;
		}
		if (opp.usingPrayer(4)) {
			oppMagic *= 1.05;
		}
		if (opp.usingPrayer(12)) {
			oppMagic *= 1.10;
		}
        	if (opp.usingPrayer(17)) {//Protect from Magic <-- Coded by Shahir!!
			magic -= 0.50;
		}
		if (opp.usingPrayer(21)) {
			oppMagic *= 1.15;
		}
		if (opp.usingPrayer(0)) {
			defence *= 1.05;
		}
		if (opp.usingPrayer(5)) {
			defence *= 1.10;
		}
		if (opp.usingPrayer(13)) {
			defence *= 1.15;
		}
		if (opp.usingPrayer(25)) {
			defence *= 1.20;
		}
		if (opp.usingPrayer(26)) {
			defence *= 1.25;
		}
		if (opp.attackStyle() == 4) {
			defence += 1;
		}
		if (opp.attackStyle() == 3) {
			defence += 3;
		}
		double offensiveAttribute = magic + magicOffense;
		double defensiveAttribute = (defence * 0.30) + (oppMagic * 0.70) + magicDefence;
		double difference = Math.abs(offensiveAttribute - defensiveAttribute);
		boolean positive = offensiveAttribute > defensiveAttribute;
		double interval = difference * 0.0040;
		double percentage = 0.65;
		if (!positive) {
			percentage -= interval;
		}
		if (positive) {
			percentage += interval;
		}
		if (p.voidSet(3)) {
			percentage *= 1.30;
		}
		return percentage;
	}
	public boolean hitPlayerMage(Player opp) {
		return Math.random() <= magicAccuracy(opp);
	}
	public double getAccuracy() {
		int base = p.skillLvl[6];
		if (p.usingPrayer(4)) base *= 1.05;
		if (p.usingPrayer(12)) base *= 1.10;
		if (p.usingPrayer(21)) base *= 1.15;
		double divider = 255.00;
		return base / divider;
	}
	public int getHit(double accuracy, double max) {
		if (accuracy < 0.10) accuracy = 0.10;
		if (accuracy > 0.90) accuracy = 0.90;
		double average = accuracy * max;
		if (Math.random() <= accuracy) {
			return (int)Math.round(getRandom(max-average) + average);
		} else {
			return (int)Math.round(getRandom(average));
		}
	}
	public void ancientMagicksAffect(Player opp, String affect) {
		if (!p.successfulCast) {
			return;
		}
		if (affect.equals("Smoke")) {
		}

		if (affect.equals("Shadow")) {
		}
		if (affect.equals("Blood")) {
		}
                if (affect.equals("Miasmic")) {
		switch (p.spell) {
			case 19:
			if (opp.miasmicSpell == 0) {
			opp.miasmicSpell = 48;
			opp.message("You feel very tired.");
			}
			case 18:
			if (opp.miasmicSpell == 0) {
			opp.miasmicSpell = 36;
			opp.message("You feel tired.");
			}
			case 17:
			if (opp.miasmicSpell == 0) {
			opp.miasmicSpell = 24;
			opp.message("You feel tired.");
			}
			case 16:
			if (opp.miasmicSpell == 0) {
			opp.miasmicSpell = 12;
			opp.message("You feel slightly tired.");
			}
		}
		}
		if (affect.equals("Ice")) {
			switch (p.spell) {
				case 0:
					if (!(opp.freezeDelay > 0)) {
						opp.freezeDelay = 5;
						Engine.playerMovement.resetWalkingQueue(opp);
					}
				case 1:
					if (!(opp.freezeDelay > 0)) {
						opp.freezeDelay = 15;
						Engine.playerMovement.resetWalkingQueue(opp);
					}
				break;
				case 2:
					if (!(opp.freezeDelay > 0)) {
						opp.freezeDelay = 10;
						Engine.playerMovement.resetWalkingQueue(opp);
					}
				break;
				case 3:
					if (!(opp.freezeDelay > 0)) {
						opp.orb = false;
						opp.freezeDelay = 20;
						Engine.playerMovement.resetWalkingQueue(opp);
					} else {
						opp.orb = true;
					}
				break;
			}
		}
	}
	public void normalSpellAffect(Player opp) {
		if (!p.successfulCast) {
			return;
		}
		switch (p.spell) {
			case 56:
				if (!(opp.freezeDelay > 0)) {
					opp.freezeDelay = 15;
					Engine.playerMovement.resetWalkingQueue(opp);
				}
			break;
			case 63:
				opp.teleblocked = true;
				p.getActionSender().sendMessage(p, "You have teleport blocked your opponent.");
			break;
		}
	}
	private int getRandom(int range) {
		return (int)Math.round(Math.random() * range);
	}
	private int getRandom(double range) {
		return (int)Math.round(Math.random() * range);
	}
}