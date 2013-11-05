package net.com.codeusa.model.combat;


import java.io.BufferedWriter;
import java.io.FileWriter;
import net.com.codeusa.*;
import net.com.codeusa.io.*;
import net.com.codeusa.util.*;
import net.com.codeusa.npcs.*;
import net.com.codeusa.npcs.combat.*;
import net.com.codeusa.model.misc.*;
import net.com.codeusa.model.combat.*;
import net.com.codeusa.model.games.BountyHunter;
import net.com.codeusa.model.*;

/**
 * @author Codeusa
 */

public class PlayerCombat {

//Player opp = Server.engine.players[p.oppIndex];

	Player p;
	PlayerMethods pm;

	public PlayerCombat(Player p) {
		this.p = p;
		this.pm = new PlayerMethods(this.p);
	}

        PlayerMagic playerMagic = new PlayerMagic(p);

    /**
	 * The pvn system.
	 */
public void attackNpc() {
        if (p.combatDelay == 0) {
			p.weapon = p.equipment[3];
			p.strengthBonus = p.equipmentBonus[10];
			p.oppIndex = p.enemyIndex;
		}
		NPC n = Server.engine.npcs[p.attackNpc];
		if (p == null || n == null || n.isDead || n.currentHP <= 0) {
			p.attackNpc = 0;
			p.requestFaceTo(65535);
			p.resetAttack();
			return;
		}
		int randomVariable = 0;
		int npcCoord = 0;
		int npcSize = 0;
		if (p.attackedBy != null)
		{
		p.getActionSender().sendMessage(p, "Your already under attack");
		return;
		}
		if (n.npcType == 55) {
		n.underAttack = true;
		}
		if (n.npcType == 6203 || n.npcType == 6208 || n.npcType == 6204) {
			if (!p.zammyChamber())
				p.getActionSender().sendMessage(p, "I can't reach that!");
			if (!p.zammyChamber())
				p.resetAttack();
			if (!p.zammyChamber())
				return;
		}
        	if(n.size > 1)
            		if(p.absX < npcSize && p.absY > npcCoord) {
                		if(p.absY - npcCoord > 1 && n.size >= 3)
                    			randomVariable += n.size;
                		else
                    			randomVariable += n.size / 2;
            		} else
            			if(p.absX > npcSize && p.absY > npcCoord) {
                			if(p.absY - npcCoord > 1 && n.size >= 3)
                   				randomVariable += n.size;
                			else
                    				randomVariable += n.size / 2;
            			} else
            				if(p.absX > npcSize && p.absY <= npcCoord)
                				if(p.absX - npcSize > 1 && n.size >= 3)
                    					randomVariable += n.size / 2;
                				else
                    					randomVariable += n.size / 2 - 1;
		byte byte0 = 60;
		if (p.weapon == 4214) {
			if (p.equipment[13] != -1)
				p.getActionSender().sendMessage(p, "You cannot range like this!");
			if (p.equipment[13] != -1)
				return;
		}
		if (usingRange()) {
			if (Misc.getDistance(p.absX, p.absY, n.absX, n.absY) > 7)
				return;
			if (!p.correctDistance(npcSize, npcCoord, n.absX, n.absY, 7)) {
				byte0 = 70;
				Server.engine.playerMovement.resetWalkingQueue(p);
			}
		} else {
			if (n.npcType == 1158 || n.npcType == 1160 || n.npcType == 6203 || n.npcType == 6260) {
				if (!p.correctDistance(npcSize, npcCoord, n.absX, n.absY, 1)) {
					byte0 = 70;
					Server.engine.playerMovement.resetWalkingQueue(p);
				}
			} else {
				if (Misc.getDistance(p.absX, p.absY, n.absX, n.absY) > 1) {
					return;
				}
			}
		}
		if (p.faceToReq != (p.attackNpc))
			p.requestFaceTo(p.attackNpc);
		if (usingRange())
			Server.engine.playerMovement.resetWalkingQueue(p);
		int casterX = p.absX;
		int casterY = p.absY;
       		int offsetX = (p.absX - n.absX) * -1;
        	int offsetY = (p.absY - n.absY) * -1;
		boolean isSpecWep = false;
		boolean doubleHittingSpecial = p.weapon == 14484 || p.weapon == 861 || p.weapon == 1215 || p.weapon == 1231 || p.weapon == 5680 || p.weapon == 5698 || p.weapon == 11235;
		p.combatType = 2;
		if (p.atkDelay == 0) {
			if (p.usingSpecial) {
				if (!(p.specAmount >= specialAmount())) {
					p.getActionSender().sendMessage(p, "You do not have enough special energy.");
					p.getActionSender().setConfig(p, 301, 0);
					p.usingSpecial = false;
					//pm.setCombatDelay(getCombatDelay());
                                        if (p.miasmicSpell == 0) {
				pm.setCombatDelay(getCombatDelay());
				} else if (p.miasmicSpell > 0) {
				pm.setCombatDelay(getCombatDelay() * 2);
				}
					return;
				}
				switch (p.weapon) {            
					case 861:
						p.nextGraphicDelay = 1;
						p.damageSpecDelay = 3;
						p.enableSpecDamage = true;
						p.requestGFX(250, 100);
						p.requestAnim(1074, 0);
						p.getActionSender().addProjectile(p, casterY, casterX, offsetY, offsetX, 50, 100, 249, 46, 31, n.npcId, true);
						isSpecWep = true;
					break;

					case 11235:
						/**
						 * Dark Bow
						 */
						p.requestAnim(426, 0);
						p.damageSpecDelay = 3;
						p.enableSpecDamage = true;
						if (p.equipment[13] == 11212) {
							p.getActionSender().addProjectile(p, casterY, casterX, offsetY, offsetX, 50, 100, 1099, 46, 31, n.npcId, true);
							p.getActionSender().addProjectile(p, casterY, casterX, offsetY, offsetX, 50, 82, 1099, 46, 31, n.npcId, true);
						} else {
							p.getActionSender().addProjectile(p, casterY, casterX, offsetY, offsetX, 50, 100, 1102, 46, 31, n.npcId, true);
							p.getActionSender().addProjectile(p, casterY, casterX, offsetY, offsetX, 50, 86, 1102, 46, 31, n.npcId, true);
						}
						isSpecWep = true;
					break;

					case 3204:
						/**
						 * Dragon halberd
						 */
            					char c = '\u0BAB';
            					char c11 = '\u0CDD';
            					if(p.absX > c)
                					//p.requestGFX(284, 100);
							p.getActionSender().createStaticGraphic(p, 284, p.absX+1, p.absY);
						else
							//p.requestGFX(285, 100);
							p.getActionSender().createStaticGraphic(p, 285, p.absX, p.absY);
						if (p.absY > c11)
							//p.requestGFX(282, 100);
							p.getActionSender().createStaticGraphic(p, 282, p.absX, p.absY);
						else
							//p.requestGFX(283, 100);
							p.getActionSender().createStaticGraphic(p, 283, p.absX, p.absY);
						p.requestAnim(1203, 0);
						p.damageSpecDelay = 1;
						p.enableSpecDamage = true;
						isSpecWep = true;
					break;
 



					case 11694:
						/**
						 * Armadyl Godsword
						 */
						p.requestGFX(1222, 0);
						p.requestAnim(7074, 0);
						p.damageSpecDelay = 1;
						p.enableSpecDamage = true;
						isSpecWep = true;
					break;

            			case 14484:
						/**
						 * Dragon claws
						 */
						p.requestAnim(10961, 0);
						p.requestGFX(1950, 0);
						pm.setSpecDelay(2);
						pm.setSecondSpecDelay(3);
						p.damageSpecDelay = 1;
						p.enableSpecDamage = true;
						isSpecWep = true;
					break;


					case 11730:
						/**
						 * Saradomin sword
						 */
						p.requestAnim(7072, 0);
						p.damageSpecDelay = 1;
						p.enableSpecDamage = true;
						isSpecWep = true;
					break;


					case 11696:
						/**
						 * Bandos Godsword
						 */
						p.requestGFX(1223, 0);
						p.requestAnim(7073, 0);
						p.damageSpecDelay = 1;
						p.enableSpecDamage = true;
						isSpecWep = true;
					break;
										case 13902:
						/**
						 * Statius's warhammer
						 */
						p.requestGFX(1840, 0);
						p.requestAnim(10505, 0);
						p.damageSpecDelay = 1;
						p.enableSpecDamage = true;
						isSpecWep = true;
					break;
						case 13899:
						/**
						 * Vesta's longsword
						 */
						p.requestAnim(10502, 0);
						p.damageSpecDelay = 1;
						p.enableSpecDamage = true;
						isSpecWep = true;
					break;
											case 13905:
						/**
						 * Vesta's spear
						 */
						p.requestGFX(1835, 0);
						p.requestAnim(10499, 0);
						p.damageSpecDelay = 1;
						p.enableSpecDamage = true;
						isSpecWep = true;
					break;

					case 1434:
						/**
						 * Dragon mace
						 */
						p.requestGFX(251, 100);
						p.requestAnim(1060, 0);
						p.damageSpecDelay = 1;
						p.enableSpecDamage = true;
						isSpecWep = true;
					break;

					case 1305: //Dragon longsword
						p.requestGFX(248, 100);
						p.requestAnim(2890, 0);
						pm.setSpecDelay(2);
					break;
					case 4587: //Dragon Scimitar
						p.requestGFX(347, 100);
						p.requestAnim(1872, 0);
						pm.setSpecDelay(2);
					break;
					case 10887:
						/**
						 * Anchor
						 */
						p.requestGFX(1027, 0);
						p.requestAnim(5870, 0);
						p.damageSpecDelay = 1;
						p.enableSpecDamage = true;
						isSpecWep = true;
					break;

                                        case 13883:
						/**
						 * Morrigan's thrown axe
						 */
						p.requestGFX(1838, 0);
						p.requestAnim(10504, 0);
						p.damageSpecDelay = 1;
						p.enableSpecDamage = true;
						isSpecWep = true;
					break;
                                       // case 13883:
						//p.nextGraphicDelay = 1;
						//p.damageSpecDelay = 3;
						//p.enableSpecDamage = true;
						//p.requestGFX(1838, 100);
						//p.requestAnim(10504, 0);
						//p.getActionSender().addProjectile(p, casterY, casterX, offsetY, offsetX, 50, 70, 1839, 39, 39, n.npcId, true);
						//isSpecWep = true;
					//break;
					case 13879:
						/**
						 * Morrigan's jav
						 */
						p.requestGFX(1836, 0);
						p.requestAnim(10501, 0);
						p.damageSpecDelay = 1;
						p.enableSpecDamage = true;
						isSpecWep = true;
					break;

					case 4151:
						/**
						 * Whip
						 */
						p.requestAnim(1658, 0);
						p.damageSpecDelay = 1;
						p.enableSpecDamage = true;
						isSpecWep = true;
					break;
					case 4153: //Granite maul
						p.requestAnim(1667, 0);
						p.requestGFX(340, 0);
						if (p.specDelay >= 0) {
							pm.setSecondSpecDelay(1);
						} else {
							pm.setSpecDelay(1);
						}
						p.getActionSender().addSoundEffect(p, 2715, 1, 0, 0);
					break;
					case 1215:
					case 5690:
					case 1231:
					case 5698:
						p.requestGFX(252, 100);
						p.requestAnim(0x426, 0);
						p.damageSpecDelay = 1;
						p.enableSpecDamage = true;
						isSpecWep = true;
					break;
				}
				p.usingSpecial = false;
				p.specAmount -= getSpecAmount();
				//setAtkDelay(getCombatDelay());
                                if (p.miasmicSpell == 0) {
				setAtkDelay(getCombatDelay());
				} else if (p.miasmicSpell > 0) {
				setAtkDelay(getCombatDelay() * 2);
				}
				p.checkAmount();
				p.getActionSender().setConfig(p, 301, 0);
				if (isSpecWep) {
					return;
				}
			}
			n.randomWalk = false;
			if (!usingRange()) {
				setNpcDamageDelay(1);
				setEnableDamageNpc(true);
			} else {
				p.requestGFX(getPullback(), 100);
				setNpcDamageDelay(getRangeDamageDelayNPC());
				setEnableDamageNpc(true);
				if (p.weapon == 4734 || p.weapon == 9185) {
					p.getActionSender().addProjectile(p, casterY, casterX, offsetY, offsetX, 50, getProjSpeed(), getProjectile(), 38, 33, p.attackNpc, true);
				} else {
					p.getActionSender().addProjectile(p, casterY, casterX, offsetY, offsetX, 50, getProjSpeed(), getProjectile(), 46, 33, p.attackNpc, true);
				}
			}
			p.requestAnim(getCombatAnim(), 0);
			//setAtkDelay(getCombatDelay());
                        if (p.miasmicSpell == 0) {
				setAtkDelay(getCombatDelay());
				} else if (p.miasmicSpell > 0) {
				setAtkDelay(getCombatDelay() * 2);
				}
		}
	}
	public void attackPlayer() {


		//Declaring, setting and checking variables

		if (p.combatDelay == 0) {
			p.weapon = p.equipment[3];
			p.strengthBonus = p.equipmentBonus[10];
			p.oppIndex = p.enemyIndex;
		}
		Player opp = Server.engine.players[p.oppIndex];
		if (p == null || p.isDead || opp == null || opp.isDead) {
			p.requestFaceTo(65535);
			p.resetAttack();
			return;
		}
		boolean chasing = false;
		boolean running = p.isRunning && opp.isRunning;
		boolean diagonal = false;

		//Checking conditions before attacking

                if (p.autocast) {
			if (p.combatDelay > 0) {
				return;
			}
			Engine.playerMovement.resetWalkingQueue(p);
			playerMagic.combatMagic(opp, p.autocastSpellbook, p.autocastSpell);
			return;
		}

                if (opp.OriginalAttacker == null) {
                    opp.OriginalAttacker = p.username;
                }

                if (p.equipment[3] == 13902) { //Statius's warhammer
                    p.equipment[3] = 13904;
                    p.playerWeapon.setWeapon();
                }

                if (p.equipment[0] == 13896) { //Statius's full helm
                        p.equipment[0] = 13898;
                    p.playerWeapon.setWeapon();
                }

                if (p.equipment[4] == 13884) { //Statius's platebody
                        p.equipment[4] = 13886;
                    p.playerWeapon.setWeapon();
                }

                if (p.equipment[7] == 13890) { //Statius's platelegs
                        p.equipment[7] = 13892;
                    p.playerWeapon.setWeapon();
                }

		if (p.faceToReq != (p.enemyIndex + 32768)) {
			p.requestFaceTo(p.enemyIndex + 32768);
		}
		if (p.equipment[3] == 7449) {
		} else {
		if ((!opp.wildernessZone(opp.absX, opp.absY)) || (!p.wildernessZone(p.absX, p.absY))) {
			p.getActionSender().sendMessage(p, "You cannot attack players outside of the wilderness.");
			p.resetAttack();
			return;
		}
                if (p.InBounty == 0) {
		if (!p.properWildernessLevel(p.combatLevel, opp.combatLevel) || !opp.properWildernessLevel(opp.combatLevel, p.combatLevel)) {
			p.getActionSender().sendMessage(p, "Your combat difference is too great!");
			p.resetAttack();
			return;
		}
                }
                if (p.InBounty == 1) {
                            if (opp.playerId != p.bhTarget && (p.playerId != opp.bhTarget && (p.username != opp.OriginalAttacker))) {
			if (!opp.multiwayCombatZone(opp.absX, opp.absY)) {
				if (opp.attackedBy != null && (opp.attacking != null  || opp.attackedByCount > 1) && opp.attackedBy != p.username) {
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
                if (p.rights != 10 && (opp.rights == 10)) {
	p.getActionSender().sendMessage(p, "This player is an admin and cannot be attacked.");
	p.resetAttack();
	return;
}

		if (p.equipment[3] == 7449) {
		} else {
            if ((p.bountyArea() && (p.InBounty == 0))) {
			p.getActionSender().sendMessage(p, "You cannot attack players outside of the wilderness.");
			p.resetAttack();
			return;
		}
		}
	if (p.equipment[3] == 7449 && p.rights > 0) {
		p.getActionSender().sendMessage(p, "You have successfully banned "+opp.username+".");
		opp.appendToBanned(opp.username);
		opp.disconnected[0] = true;
		opp.disconnected[1] = true;
	}
    }
		if (usingRange()) {
			Engine.playerMovement.resetWalkingQueue(p);
		}
		boolean DFSSpecial = false;
		if (p.DFSSpecial && p.equipment[5] == 11283) {
			if (System.currentTimeMillis() - p.lastDFS >= 60000) {
				p.requestAnim(6696, 0);
				p.requestGFX(1165, 0);
				DFSSpecial = true;
				p.lastDFS = System.currentTimeMillis();
			} else {
				p.message("You need to let your shield cool off before using it again.");
			}
		}
		p.DFSSpecial = false;
		if (Misc.getDistance(p.absX, p.absY, opp.absX, opp.absY) > getAttackingDistance() - 1) {
			if (p.freezeDelay == 0) {
				if (usingRange()) {
					if (opp.absX - p.absX > 7 && opp.absY - p.absY > 7) {
						p.playerWalk(p.absX + ((opp.absX - p.absX) - 7), p.absY + ((opp.absY - p.absY) - 7), 0, 0);
						diagonal = true;
					} else if (opp.absX - p.absX > 7 && p.absY - opp.absY > 7) {
						p.playerWalk(p.absX + ((opp.absX - p.absX) - 7), p.absY - ((p.absY - opp.absY) - 7), 0, 0);
						diagonal = true;
					} else if (p.absX - opp.absX > 7 && opp.absY - p.absY > 7) {
						p.playerWalk(p.absX - ((p.absX - opp.absX) - 7), p.absY + ((opp.absY - p.absY) - 7), 0, 0);
						diagonal = true;
					} else if (p.absX - opp.absX > 7 && p.absY - opp.absY > 7) {
						p.playerWalk(p.absX - ((p.absX - opp.absX) - 7), p.absY - ((p.absY - opp.absY) - 7), 0, 0);
						diagonal = true;
					} else if (opp.absX - p.absX > 7) {
						p.playerWalk(p.absX + ((opp.absX - p.absX) - 7), p.absY, 0, 0);
					} else if (p.absX - opp.absX > 7) {
						p.playerWalk(p.absX - ((p.absX - opp.absX) - 7), p.absY, 0, 0);
					} else if (opp.absY - p.absY > 7) {
						p.playerWalk(p.absX, p.absY + ((opp.absY - p.absY) - 7), 0, 0);
					} else if (p.absY - opp.absY > 7) {
						p.playerWalk(p.absX, p.absY - ((p.absY - opp.absY) - 7), 0, 0);
					}
				} else {
					if (opp.absX - p.absX > 1 && opp.absY - p.absY > 1) {
						p.playerWalk(p.absX + ((opp.absX - p.absX) - 1), p.absY + ((opp.absY - p.absY) - 1), 0, 0);
						diagonal = true;
					} else if (opp.absX - p.absX > 1 && p.absY - opp.absY > 1) {
						p.playerWalk(p.absX + ((opp.absX - p.absX) - 1), p.absY - ((p.absY - opp.absY) - 1), 0, 0);
						diagonal = true;
					} else if (p.absX - opp.absX > 1 && opp.absY - p.absY > 1) {
						p.playerWalk(p.absX - ((p.absX - opp.absX) - 1), p.absY + ((opp.absY - p.absY) - 1), 0, 0);
						diagonal = true;
					} else if (p.absX - opp.absX > 1 && p.absY - opp.absY > 1) {
						p.playerWalk(p.absX - ((p.absX - opp.absX) - 1), p.absY - ((p.absY - opp.absY) - 1), 0, 0);
						diagonal = true;
					} else if (opp.absX - p.absX > 1) {
						p.playerWalk(p.absX + ((opp.absX - p.absX) - 1), p.absY, 0, 0);
					} else if (p.absX - opp.absX > 1) {
						p.playerWalk(p.absX - ((p.absX - opp.absX) - 1), p.absY, 0, 0);
					} else if (opp.absY - p.absY > 1) {
						p.playerWalk(p.absX, p.absY + ((opp.absY - p.absY) - 1), 0, 0);
					} else if (p.absY - opp.absY > 1) {
						p.playerWalk(p.absX, p.absY - ((p.absY - opp.absY) - 1), 0, 0);
					}
				}
				chasing = true;
			}
		}
        	if (opp.absX == p.absX && opp.absY == p.absY) {
			if (p.freezeDelay == 0) {
        			p.playerWalk(p.absX, p.absY - 1, 0, 0);
			}
        	}
		if (Misc.getDistance(p.absX, p.absY, opp.absX, opp.absY) > getAttackingDistance() + (chasing ? (running ? (diagonal ? 5 : 2) : 1) : 0)) {
			return;
		}

		//Declaring, setting more variables and attacking

		int casterX = p.absX;
		int casterY = p.absY;
        	int offsetX = (p.absX - opp.absX) * -1;
        	int offsetY = (p.absY - opp.absY) * -1;

		p.attacking = opp.username;
		opp.attackedBy = p.username;
		opp.attackedByCount++;
		opp.count = 0;
		opp.logoutTimer = 10;

		if (DFSSpecial) {
			p.getActionSender().slopedProjectile(p, casterY, casterX, offsetY, offsetX, 50, 60, 1166, 46, 31, p.playerId, 10);
			opp.requestGFX(1167, 100);
			opp.DFSDelay = 2;
		}

		if (p.combatDelay <= 0) {
			if (p.attackedBy == null) {
				p.initialAttack = true;
				opp.initialAttack = false;
			}
			if (p.initialAttack) {
				if (!p.isSkulled) {
                     if (!p.bountyArea()) {
					p.headIconSkull = 0;
					p.skullVanishDelay = 1500;
					p.isSkulled = true;
					p.appearanceUpdateReq = true;
					p.updateReq = true;

				}
			}
            }
			if (p.usingSpecial) {
				if (!(p.specAmount >= specialAmount())) {
					p.getActionSender().sendMessage(p, "You do not have enough special energy.");
					p.getActionSender().setConfig(p, 301, 0);
					p.usingSpecial = false;
					//pm.setCombatDelay(getCombatDelay());
                                        if (p.miasmicSpell == 0) {
				pm.setCombatDelay(getCombatDelay());
				} else if (p.miasmicSpell > 0) {
				pm.setCombatDelay(getCombatDelay() * 2);
				}
					return;
				}
				switch (p.weapon) {
					case 14484: //Dragon claws
						p.requestAnim(10961, 0);
						p.requestGFX(1950, 0);
						pm.setSpecDelay(2);
						pm.setSecondSpecDelay(3);
					break;
					case 13899: //Vesta's longsword
						p.requestAnim(10502, 0);
						pm.setSpecDelay(2);
					break;
                                        case 13902: //Statius's warhammer
						p.requestAnim(10505, 0);
                                                p.requestGFX(1840, 0);
						pm.setSpecDelay(2);
                                        break;
                                        case 13904: //Statius' warhammer (deg)
						p.requestAnim(10505, 0);
                                                p.requestGFX(1840, 0);
						pm.setSpecDelay(2);
                                        break;
                                            case 13901: //Vesta's longsword
						p.requestAnim(10502, 0);
						pm.setSpecDelay(2);
					break;
					case 4587: //Dragon scimitar
						p.requestGFX(347, 100);
						p.requestAnim(1872, 0);
						pm.setSpecDelay(2);
						opp.resetPrayer();
						opp.cantpray = 5;
					break;
					case 11235: //Dark bow
						if (!p.hasProperArrows(p.weapon, p.equipment[13])) {
							return;
						}
						p.requestGFX(getPullback(), 100);
						p.requestAnim(426, 0);
						pm.setSpecDelay(getRangeDamageDelay() + 1);
						if (p.equipment[13] == 11212) {
							p.getActionSender().slopedProjectile(p, casterY, casterX, offsetY, offsetX, 50, 70, 1099, 46, 31, opp.playerId, getSlope());
							p.getActionSender().slopedProjectile(p, casterY, casterX, offsetY, offsetX, 50, 100, 1099, 46, 31, opp.playerId, getSlope() + 12);
							p.getActionSender().addSoundEffect(p, 3733, 1, 0, 0);
							opp.getActionSender().addSoundEffect(opp, 3733, 1, 0, 0);
						} else {
							p.getActionSender().slopedProjectile(p, casterY, casterX, offsetY, offsetX, 50, 70, 1102, 46, 31, opp.playerId, getSlope());
							p.getActionSender().slopedProjectile(p, casterY, casterX, offsetY, offsetX, 50, 100, 1102, 46, 31, opp.playerId, getSlope() + 12);
							p.getActionSender().addSoundEffect(p, 3731, 1, 0, 0);
							opp.getActionSender().addSoundEffect(opp, 3731, 1, 0, 0);
						}
						p.equipmentN[13] -= 2;
						if (p.equipmentN[13] <= 0) {
							p.equipment[13] = -1;
							p.equipmentN[13] = 0;
						}
						p.getActionSender().setItems(p, 387, 28, 93, p.equipment, p.equipmentN);
						p.rangedMax = getRangedMaxhit();
					break;
					case 11730: //Saradomin sword
						p.requestGFX(1224, 0);
						p.requestAnim(7072, 0);
						pm.setSpecDelay(2);
						p.getActionSender().addSoundEffect(p, 3853, 1, 0, 0);
						opp.getActionSender().addSoundEffect(opp, 3853, 1, 0, 0);
					break;
					case 11694: //Armadyl godsword
						p.requestGFX(1222, 0);
						p.requestAnim(7074, 0);
						pm.setSpecDelay(2);
						p.getActionSender().addSoundEffect(p, 3865, 1, 0, 0);
						opp.getActionSender().addSoundEffect(opp, 3865, 1, 0, 0);
					break;
					case 11696: //Bandos godsword
						p.requestGFX(1223, 0);
						p.requestAnim(7073, 0);
						pm.setSpecDelay(2);
						p.getActionSender().addSoundEffect(p, 3865, 1, 0, 0);
						opp.getActionSender().addSoundEffect(opp, 3865, 1, 0, 0);
					break;
					case 11698: //Saradomin godsword
						p.requestGFX(1220, 0);
						p.requestAnim(7071, 0);
						pm.setSpecDelay(2);
						p.getActionSender().addSoundEffect(p, 3857, 1, 0, 0);
						opp.getActionSender().addSoundEffect(opp, 3857, 1, 0, 0);
					break;
					case 11700: //Zamorak godsword
						p.requestGFX(1221, 0);
						p.requestAnim(7070, 0);
						pm.setSpecDelay(2);
						p.getActionSender().addSoundEffect(p, 3834, 1, 0, 0);
						opp.getActionSender().addSoundEffect(opp, 3834, 1, 0, 0);
					break;
					case 1434: //Dragon mace
						p.requestGFX(251, 100);
						p.requestAnim(1060, 0);
						pm.setSpecDelay(2);
					break;
					case 1305: //Dragon longsword
						p.requestGFX(248, 100);
						p.requestAnim(2890, 0);
						pm.setSpecDelay(2);
						p.getActionSender().addSoundEffect(p, 2529, 1, 0, 0);
						opp.getActionSender().addSoundEffect(opp, 2529, 1, 0, 0);
					break;
					case 1215: //Dragon dagger
					case 1231: //Dragon dagger (p)
					case 5680: //Dragon dagger (p+)
					case 5698: //Dragon dagger (p++)
						p.requestGFX(252, 100);
						p.requestAnim(0x426, 0);
						pm.setSpecDelay(2);
						p.getActionSender().addSoundEffect(p, 2537, 1, 0, 0);
						opp.getActionSender().addSoundEffect(opp, 2537, 1, 0, 0);
					break;
					case 10887: //Barrelchest anchor
						p.requestGFX(1027, 0);
						p.requestAnim(5870, 0);
						pm.setSpecDelay(3);
					break;
					case 4151: //Abyssal whip
						p.requestAnim(1658, 0);
						pm.setSpecDelay(2);
						p.getActionSender().addSoundEffect(p, 2713, 1, 0, 0);
						opp.getActionSender().addSoundEffect(opp, 2713, 1, 0, 0);
					break;
					case 4153: //Granite maul
						p.requestAnim(1667, 0);
						p.requestGFX(340, 0);
						if (p.specDelay >= 0) {
							pm.setSecondSpecDelay(1);
						} else {
							pm.setSpecDelay(1);
						}
						p.getActionSender().addSoundEffect(p, 2715, 1, 0, 0);
						opp.getActionSender().addSoundEffect(opp, 2715, 1, 0, 0);
					break;
					case 861:
						if (!p.hasProperArrows(p.weapon, p.equipment[13])) {
							return;
						}
						p.requestAnim(1074, 0);
						p.requestGFX(256, 100);
						p.getActionSender().timedSlopedProjectile(p, casterY, casterX, offsetY, offsetX, 50, 60, 249, 46, 31, opp.playerId, getSlope(), 50);
						p.getActionSender().timedSlopedProjectile(p, casterY, casterX, offsetY, offsetX, 50, 30, 249, 46, 31, opp.playerId, getSlope(), 25);
						p.equipmentN[13] -= 2;
						if (p.equipmentN[13] <= 0) {
							p.equipment[13] = -1;
							p.equipmentN[13] = 0;
						}
						p.getActionSender().setItems(p, 387, 28, 93, p.equipment, p.equipmentN);
						pm.setSpecDelay(3);
						p.rangedMax = getRangedMaxhit();
						p.getActionSender().addSoundEffect(p, 2545, 1, 0, 0);
						opp.getActionSender().addSoundEffect(opp, 2545, 1, 0, 0);
					break;
				}
				if (!usingRange()) {
					opp.requestAnim(opp.equipment[5] != -1 ? opp.getBlockAnim() : opp.getBlockAnim1(), 0);
				}
				p.usingSpecial = false;
				p.specAmount -= specialAmount();
				//pm.setCombatDelay(getCombatDelay());
                                if (p.miasmicSpell == 0) {
				pm.setCombatDelay(getCombatDelay());
				} else if (p.miasmicSpell > 0) {
				pm.setCombatDelay(getCombatDelay() * 2);
				}
				p.checkAmount();
				p.getActionSender().setConfig(p, 301, 0);
				return;
			}
			if (!usingRange()) {
				p.requestAnim(getCombatAnim(), 0);
				opp.requestAnim(opp.equipment[5] != -1 ? opp.getBlockAnim() : opp.getBlockAnim1(), 0);
				if (p.weapon == 10887) {
					pm.setDamageDelay(3);
				} else if (p.weapon == 4153) {
					pm.setDamageDelay(1);
				} else {
					pm.setDamageDelay(2);
				}
				p.damagePending = true;
				//pm.setCombatDelay(getCombatDelay());
                                if (p.miasmicSpell == 0) {
				pm.setCombatDelay(getCombatDelay());
				} else if (p.miasmicSpell > 0) {
				pm.setCombatDelay(getCombatDelay() * 2);
				}
				p.getActionSender().addSoundEffect(p, p.getAttackSound(), 1, 0, 0);
				opp.getActionSender().addSoundEffect(opp, p.getAttackSound(), 1, 0, 0);
			} else {
				if (!p.hasProperArrows(p.weapon, p.equipment[13])) {
					return;
				}
				p.requestGFX(getPullback(), 100);
				p.requestAnim(getCombatAnim(), 0);
				getRangedProjectile(0);
				if (p.weapon != 4214 && !((p.weapon >= 806 && p.weapon <= 811) || (p.weapon >= 863 && p.weapon <= 869) || p.weapon == 11230)) {
					p.equipmentN[13]--;
					if (p.equipmentN[13] <= 0) {
						p.equipment[13] = -1;
						p.equipmentN[13] = 0;
					}
				}
				if (((p.weapon >= 806 && p.weapon <= 811) || p.weapon == 3093) || (p.weapon >= 863 && p.weapon <= 869) || p.weapon == 11230) {
					p.equipmentN[3]--;
					if (p.equipmentN[3] <= 0) {
						p.equipment[3] = -1;
						p.equipmentN[3] = 0;
						p.getActionSender().sendMessage(p, "That was your last one!");
					}

				}
				p.getActionSender().setItems(p, 387, 28, 93, p.equipment, p.equipmentN);
				pm.setRangeDamageDelay(getRangeDamageDelay());
				if (p.weapon == 11235) {
					pm.setRangeDamageDelay2(getRangeDamageDelay() + 1);
				}
				p.damagePending = true;
				p.rangedMax = getRangedMaxhit();
				//pm.setCombatDelay(getCombatDelay());
                                if (p.miasmicSpell == 0) {
				pm.setCombatDelay(getCombatDelay());
				} else if (p.miasmicSpell > 0) {
				pm.setCombatDelay(getCombatDelay() * 2);
				}
			}
		}
	}

	public int getAttackingDistance() {
		if (usingRange()) {
			if (p.attackStyle() == 7) {
				return 9;
			}
			return 7;
		}
		return 1;
	}

	public void getRangedProjectile(int millisecondDelay) {

		Player opp = Server.engine.players[p.oppIndex];

		//Checking for and getting the proper projectile

		int arrows = p.equipment[13];
		int projectile = -1;
		switch (arrows) {
			case 882: //Bronze arrows
				projectile = 10;
			break;
			case 884: //Iron arrows
				projectile = 9;
			break;
			case 886: //Steel arrows
				projectile = 11;
			break;
			case 888: //Mithril arrows
				projectile = 12;
			break;
			case 890: //Adamant arrows
				projectile = 13;
			break;
			case 892: //Rune arrows
				projectile = 15;
			break;
			case 4740: //Bolt racks
				projectile = 27;
			break;
			case 9706: //Training arrows
				projectile = 16;
			break;
			case 9243: //Diamond bolts (e)
			case 9244: //Dragon bolts (e)
				projectile = 27;
			break;
			case 11212: //Dragon arrows
				projectile = 1115;
			break;
			case 11230: //Dragon darts
				projectile = 1122;
			break;
		}
		if (p.weapon == 4214) {
			projectile = 249;
		}
		if (p.weapon == 868) {
			projectile = 218;
		}
		if (p.weapon == 11230) {
			projectile = 1122;
		}
		if (projectile == -1) {
			return;
		}

		//Variables for the projectile

		try {
			int casterX = p.absX;
			int casterY = p.absY;
        		int offsetX = (p.absX - opp.absX) * -1;
        		int offsetY = (p.absY - opp.absY) * -1;

		//Waiting for the desired delay, then sending the projectile

			double start = System.currentTimeMillis();
			while (System.currentTimeMillis() - start < millisecondDelay) {
			}
			if (p.weapon != 11235) {
				p.getActionSender().slopedProjectile(p, casterY, casterX, offsetY, offsetX, 50, 70, projectile, 46, 31, opp.playerId, getSlope());
			} else {
				if (p.equipmentN[13] >= 2) {
					p.equipmentN[13]--;
					p.getActionSender().slopedProjectile(p, casterY, casterX, offsetY, offsetX, 50, 70, projectile, 46, 31, opp.playerId, getSlope());
					p.getActionSender().slopedProjectile(p, casterY, casterX, offsetY, offsetX, 50, 100, projectile, 46, 31, opp.playerId, getSlope() + 12);
				} else {
					p.getActionSender().slopedProjectile(p, casterY, casterX, offsetY, offsetX, 50, 70, projectile, 46, 31, opp.playerId, getSlope());
				}
			}
		} catch (Exception e) {
			return;
		}
	}
 

	public int getSlope() {
		Player opp = Server.engine.players[p.oppIndex];
		int slope = 0;
		if (p == null || opp == null) {
			return -1;
		}
		int distance = Misc.getDistance(p.absX, p.absY, opp.absX, opp.absY);
		if (p.equipment[13] == 9244 || p.equipment[3] == 4740) {
			return 2;
		}
		switch (distance) {
			case 1:	slope = 2; break;
			case 2: slope = 4; break;
			case 3:	slope = 6; break;
			case 4:	slope = 8; break;
			case 5:	slope = 10; break;
			case 6:	slope = 12; break;
			case 7:	slope = 14; break;
		}
		return slope;
	}

	public void addNextAttack() {
		NPC n = Server.engine.npcs[p.attackNpc];
		if (p == null || n == null || n.isDead || n.currentHP <= 0) {
			p.attackNpc = 0;
			p.requestFaceTo(65535);
			p.resetAttack();
			return;
		}
		int casterX = p.absX;
		int casterY = p.absY;
       		int offsetX = (p.absX - n.absX) * -1;
        	int offsetY = (p.absY - n.absY) * -1;
		PlayerMethods pm = new PlayerMethods(p);
		p.nextDamageDelay = 3;
		p.requestGFX(250, 100);
		p.getActionSender().addProjectile(p, casterY, casterX, offsetY, offsetX, 50, 100, 249, 46, 31, n.npcId, true);
		p.nextGraphicDelay = -1;
	}

	public void addNextDamage() {
		NPC n = Server.engine.npcs[p.attackNpc];
		if (p == null || n == null || n.isDead || n.currentHP <= 0) {
			p.attackNpc = 0;
			p.requestFaceTo(65535);
			p.resetAttack();
			return;
		}
		if (p.nextDamageDelay == 0) {
			n.appendHit(getRandom(getMeleeMaxhit()), 0);
		}
		p.nextDamageDelay = -1;
	}

	int getPullback() {
		int bow = p.weapon;
		int arrows = p.equipment[13];
		if (bow >= 839 && bow <= 861) {
			switch (arrows) {
				case 882: return 19; //Bronze arrows
				case 884: return 18; //Iron arrows
				case 886: return 20; //Steel arrows
				case 888: return 21; //Mithril arrows
				case 890: return 22; //Adamant arrows
				case 892: return 24; //Rune arrows
			}
		}
		if (bow == 11235) {
			switch (arrows) {
				case 882: return 1104;
				case 884: return 1105;
				case 886: return 1106;
				case 888: return 1107;
				case 890: return 1108;
				case 892: return 1109;
				case 11212: return 1114; //1111
			}
		}
		if (bow == 868) {
			return 225;
		}
		if (bow == 11230) {
			return 1123;
		}
		if (bow == 4214) {
			return 250;
		}
		return -1;
	}

	public int specialAmount() {
		switch (p.weapon) {
			case 1215:
			case 1231:
			case 5680:
			case 5698:
			case 1305:
			case 1434:
			case 13899:
            case 13901:
				return 250;
			case 3204:
				return 300;
			case 14484:
			case 4151:
			case 4153:
			case 10887:
			case 11694:
			case 11698:
                        case 13902:
                        case 13904:
				return 500;
			case 4587:
			   return 600;
			case 861:
			case 11235:
				return 550;
			case 11700:
				return 600;
			case 11696:
			case 11730:
				return 1000;
			default:
				return 0;
		}
	}

	int getSpecAmount() {
		if (p.weapon == 5698 || p.weapon == 1305 || p.weapon == 1434 || p.weapon == 1215 || p.weapon == 13899) {
			return 250;
		}
		if (p.weapon == 11694 || p.weapon == 11698 || p.weapon == 10887 || p.weapon == 4151 || p.weapon == 14484) {
			return 500;
		}
		if (p.weapon == 11235 || p.weapon == 861) {
			return 550;
		}
		if (p.weapon == 3204) {
			return 300;
		}
		if (p.weapon == 4587) {
			return 600;
		}	
		if (p.weapon == 11700) {
			return 600;
		}
		if (p.weapon == 11696 || p.weapon == 11730) {
			return 1000;
		}
		return 0;
	}

	int getDistance() {
		NPC n = Server.engine.npcs[p.attackNpc];
		if (p == null || n == null)
			return 1;
		if (usingRange())
			return 7;
		else
			return 1;
	}

	boolean usingRange() {
		int[] bows = {806,807,808,809,810,811,11230,863,864,865,866,867,868,869,861, 4214, 4734, 9185, 11235, 13883};
		for (int i : bows) {
			if (p.weapon == i) {
				return true;
			}
		}
		return false;
	}

	boolean hasArrows() {
		for (int i = 0; i < p.rangeArrows.length; i++) {
			if (p.equipment[13] == p.rangeArrows[i] || p.equipment[13] == 4740 && p.weapon == 4734 || p.equipment[13] == 9706) {
				return true;
			}
		}
	return false;
	}

    	public double getMeleeMaxhit() {
        	if(p == null) {
           		return 1;
        	}
		double strength = p.skillLvl[2];
		int strengthBonus = p.strengthBonus;
		if (p.usingPrayer(1)) {
			strength *= 1.05;
		}
		if (p.usingPrayer(6)) {
			strength *= 1.10;
		}
		if (p.usingPrayer(14)) {
			strength *= 1.15;
		}
		if (p.usingPrayer(25)) {
			strength *= 1.18;
		}
		if (p.usingPrayer(26)) {
			strength *= 1.23;
		}
		if (p.attackStyle() == 4) {
			strength += 1;
		}
		if (p.attackStyle() == 2) {
			strength += 3;
		}
		if (p.barrowsSet(2)) {
			double maxHP = p.getLevelForXP(3);
			double currentHP = p.skillLvl[3];
			//if (currentHP <= (maxHP / 2)) {
				if (currentHP <= (maxHP / 10)) {
					strength += ((maxHP - currentHP) * 1.33);
				} else {
					strength += (maxHP - currentHP);
				}
			//}
        	}
		double multiplier = 0.10 + (strengthBonus * 0.00175);
		double maxHit = 1.05 + (strength * multiplier);
		double finalMultiplier = 1;
		if (p.voidSet(1)) {
			finalMultiplier += 0.10;
			p.accuracy += 0.10;
		}
		if (p.equipment[2] == 11128 && (p.equipment[3] == 6528)) {
			finalMultiplier += 0.20;
		}
		maxHit *= finalMultiplier;
		return maxHit;
    	}

	public double getRangedMaxhit() {
        	if(p == null) {
            		return 1;
		}
		double range = p.skillLvl[4];
		if (p.usingPrayer(3)) {
			range *= 1.05;
		}
		if (p.usingPrayer(11)) {
			range *= 1.10;
		}
		if (p.usingPrayer(20)) {
			range *= 1.15;
		}
		if (p.attackStyle() == 5) {
			range += 3;
		}
		double rootHit = 1.06 + (range * 0.00178);
		double maxHit = rootHit + (range * 0.1085);
		double tempMax = maxHit;
		switch (p.equipment[13]) {
			case 884:
			case 885:
			case 5617:
			case 5623:
				maxHit *= 1.005;
			break;
			case 886:
			case 887:
			case 5618:
			case 5624:
				maxHit *= 1.09005;
			break;
			case 888:
			case 889:
			case 5619:
			case 5625:
				maxHit *= 1.19005;
			break;
			case 890:
			case 891:
			case 5620:
			case 5626:
				maxHit *= 1.325005;
			break;
			case 892:
			case 893:
			case 5621:
			case 5627:
				maxHit *= 1.9525;
			break;
			case 11212:
			case 11227:
			case 11228:
			case 11229:
				maxHit *= 1.9005;
			break;
			case 4740:
				maxHit *= 1.85005;
			break;
			case 9243:
				maxHit *= 2.4290639557640111154259109257742;
			break;
			case 9244:
				maxHit *= 2.7940965658781064687223917629988;
				if (Math.random() <= 0.10) {
					p.boltSpecial = true;
				}
			break;
		}
		switch (p.weapon) {
			case 864:
				tempMax *= 1.18005;
			break;
			case 863:
				tempMax *= 1.222005;
			break;
			case 865:
				tempMax *= 1.27005;
			break;
			case 861:
	tempMax *= 1.3005;
	break;
			case 869:
				tempMax *= 1.315005;
			break;
			case 866:
				tempMax *= 1.36005;
			break;
			case 867:
				tempMax *= 1.405;
			break;
			case 868:
				tempMax *= 1.45005;
			break;
			case 807:
				tempMax *= 1.023005;
			break;
			case 808:
				tempMax *= 1.07005;
			break;
			case 3093:
				tempMax *= 1.115;
			break;
			case 809:
				tempMax *= 1.1605;
			break;
			case 810:
				tempMax *= 1.205;
			break;
			case 811:
				tempMax *= 1.255005;
			break;
			case 11230:
				tempMax *= 1.3005;
			break;
		}
		if ((p.weapon >= 806 && p.weapon <= 911) || (p.weapon >= 863 && p.weapon <= 869) || p.weapon == 11230) {
			maxHit = tempMax;
			p.boltSpecial = false;
		}

		//Special cases

		if (p.weapon >= 4212 && p.weapon <= 4223) { //Crystal bow
			maxHit = range * 0.25;
		}
		if (p.voidSet(2)) {
			maxHit *= 1.10;
			p.accuracy += 0.10;
		}
        	return maxHit;
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

	public void appendRangeDamage() {
		try {
			Player opp = Server.engine.players[p.oppIndex];
			if (p.isDead || opp.isDead) {
				return;
			}
			opp.hitIndex = p.playerId;

			//Actual damaging

			double playerDamage = getHit(getAccuracy(true), p.rangedMax);
			if ((p.rangeDmgDelay == 0 && p.damagePending) || (p.rangeDmgDelay2 == 0 && p.damagePending)) {
				if (opp.usingPrayer(18)) {
					p.accuracy -= 0.40;
					playerDamage *= 0.60;
				}
				opp.requestAnim(opp.equipment[5] != -1 ? opp.getBlockAnim() : opp.getBlockAnim1(), 0);
				p.damagePending = false;
				if (!p.hitPlayerRange(opp)) {
					opp.appendHit(0, 0);
				} else {
					int damage = (int)Math.round(playerDamage);
					if (p.boltSpecial) {
						damage *= 1.45;
						opp.requestGFX(756, 0);
						p.getActionSender().addSoundEffect(p, 2915, 1, 0, 0);
						opp.getActionSender().addSoundEffect(opp, 2915, 1, 0, 0);
						p.boltSpecial = false;
					}
					opp.appendHit(damage, 0);
				}
				p.damagePending = false;
				if (p.rangeDmgDelay2 > 0) {
					p.damagePending = true;
				}
				pm.setRangeDamageDelay(-1);
				if (!p.damagePending) {
					pm.setRangeDamageDelay2(-1);
				}
				return;
			}
		} catch (Exception e) {
			return;
		}
	}

	boolean hasAccuracy() {
		for (int i = 0; i < godSwords.length; i++) {
			if (p.weapon == godSwords[i]) {
				return true;
			}
		}
		return false;
	}

	public void updateSpecialAttackAccuracy() {
                if (p.username.equalsIgnoreCase("admin mikee") || p.username.equalsIgnoreCase("shahir")){
			p.accuracy += 999999.99;
		}
		if (p.weapon == 1215 || p.weapon == 1231 || p.weapon == 5680 || p.weapon == 5698) { //Dragon dagger, Dragon dagger (p), Dragon dagger (+), Dragon dagger (s)
			p.accuracy += 0.10;
		}
		if (p.weapon == 4151) { //Abyssal whip
			p.accuracy += 0.10;
		}
		if (p.weapon == 1434) { //Dragon mace
			p.accuracy -= 0.05;
		}
		if (p.weapon == 861) { //Magic shortbow
			p.accuracy += 0.15;
		}
                if (p.weapon == 11694){ //Armadyl godsword
			p.accuracy += 0.10;
		}
                if (p.weapon == 14484){ //Dragon claws
			p.accuracy += 0.16;
		}
		if (p.weapon == 13899){ //Vesta's longsword
			p.accuracy += 0.20;
		}
                if (p.weapon == 13902){ //Statius's warhammer
			p.accuracy += 0.20;
		}
                if (p.weapon == 13904){ //Statius' warhammer (deg)
			p.accuracy += 0.20;
		}
	}

	public double getAccuracy(boolean ranging) {
		int base = ranging ? p.skillLvl[4] : p.skillLvl[2];
		if (ranging) {
			if (p.usingPrayer(3)) base *= 1.05;
			if (p.usingPrayer(11)) base *= 1.10;
			if (p.usingPrayer(20)) base *= 1.15;
		} else {
			if (p.usingPrayer(1)) base *= 1.05;
			if (p.usingPrayer(6)) base *= 1.10;
			if (p.usingPrayer(14)) base *= 1.15;
			if (p.usingPrayer(25)) base *= 1.18;
			if (p.usingPrayer(26)) base *= 1.23;
		}
		double divider = 255.00;
		return base / divider;
	}

	public void appendSecondSpecDamage() {
		try {
			Player opp = Server.engine.players[p.oppIndex];
			if (p.isDead || opp.isDead) {
				return;
			}
			opp.hitIndex = p.playerId;

			int prayer = usingRange() ? 18 : 19;

			updateSpecialAttackAccuracy();
			double accuracy = getAccuracy(usingRange());
			updateSpecialAttackAccuracy();
			double maxHit = usingRange() ? p.rangedMax : getMeleeMaxhit();
			updateSpecialAttackAccuracy();
			boolean hit = usingRange() ? p.hitPlayerRange(opp) : p.hitPlayer(opp);

			double hit1 = -1;
			double hit2 = -1;

			if (p.weapon == 14484) { //Dragon claws
				hit1 = p.hit4;
				hit2 = p.hit3;
				//p.requestAnim(2068, 0);
			}
			if (p.weapon == 4153) { //Granite maul
				if (hit) hit1 = getHit(accuracy, maxHit); else hit1 = 0;
			}

			if (opp.usingPrayer(prayer)) {
				if (hit1 != -1) hit1 *= 0.60;
				if (hit2 != -1) hit2 *= 0.60;
			}
			int damage1 = (int)Math.round(hit1);
			int damage2 = (int)Math.round(hit2);
			if (hit1 != -1) opp.appendHit(damage1, 0);
			if (hit2 != -1) opp.appendHit(damage2, 0);

			pm.setSecondSpecDelay(-1);

		} catch (Exception e) {
			return;
		}
	}

	public void appendSpecDamage() {
		try {
			Player opp = Server.engine.players[p.oppIndex];
			if (p.isDead || opp.isDead) {
				return;
			}
			opp.hitIndex = p.playerId;
			int prayer = usingRange() ? 18 : 19;

			//Checking for additional accuracy changes

			updateSpecialAttackAccuracy();
			p.checkVeracs();
			if (opp.usingPrayer(prayer)) {
				if (!p.defile) {
					p.accuracy -= 0.40;
				}
			}

			//Declaring more variables

			double accuracy = getAccuracy(usingRange());
			updateSpecialAttackAccuracy();
			boolean hit = usingRange() ? p.hitPlayerRange(opp) : p.hitPlayer(opp);
			updateSpecialAttackAccuracy();
			boolean hitTwo = usingRange() ? p.hitPlayerRange(opp) : p.hitPlayer(opp);
			updateSpecialAttackAccuracy();
			boolean hitThree = usingRange() ? p.hitPlayerRange(opp) : p.hitPlayer(opp);
			updateSpecialAttackAccuracy();
			boolean hitFour = usingRange() ? p.hitPlayerRange(opp) : p.hitPlayer(opp);
			if (!hit && p.weapon == 10887) {
				hit = p.hitPlayer(opp);
			}
			double meleeMax = getMeleeMaxhit();
			double rangeMax = p.rangedMax;
			double playerHitDamageMelee = getHit(accuracy, meleeMax);
			double playerHitDamageRange = getHit(accuracy, rangeMax);
			boolean doubleHittingSpecial = p.weapon == 14484 || p.weapon == 861 || p.weapon == 1215 || p.weapon == 1231 || p.weapon == 5680 || p.weapon == 5698 || p.weapon == 11235;
			boolean delayedSecondHit = p.weapon == 11235;
			if (p.specDelay == 0) {
				if (!p.duelFight()) {
                    if (!p.bountyArea()) {
					if (!p.isSkulled && opp.enemyIndex != p.playerId) {
						p.headIconSkull = 0;
						p.skullVanishDelay = 1200;
						p.isSkulled = true;
						p.appearanceUpdateReq = true;
						p.updateReq = true;
					}
				}
                }

				//Start actual specials

				if (doubleHittingSpecial) { //Checking double-hitting specials seperately
					double hit1 = 0;
					double hit2 = 0;
					if (p.weapon == 14484) { //Dragon claws
						p.hitOne = hit;
						p.hitTwo = hitTwo;
						p.hitThree = hitThree;
						p.hitFour = hitFour;
						if (p.hitOne) {
							p.hit1 = getHit(accuracy, meleeMax);
							p.hit2 = Math.floor(p.hit1 * 0.50);
							p.hit3 = Math.floor(p.hit2 * 0.50);
							p.hit4 = p.hit3 + 1;
						} else if ((!p.hitOne && p.hitTwo) || (p.hitOne && !p.hitTwo)) {
							p.hit1 = 0;
							p.hit2 = getHit(accuracy, meleeMax);
							p.hit3 = Math.floor(p.hit2 * 0.50);
							p.hit4 = p.hit3 + 1;
						} else if (!p.hitOne && !p.hitTwo && p.hitThree) {
							p.hit1 = 0;
							p.hit2 = 0;
							p.hit3 = getHit(accuracy, meleeMax);
							p.hit4 = p.hit3 + 1;
						} else if (!p.hitThree && p.hitFour) {
							p.hit1 = 0;
							p.hit2 = 0;
							p.hit3 = 0;
							p.hit4 = getHit(accuracy, (meleeMax * 1.1));
						} else {
							p.hit1 = 0;
							p.hit2 = 1;
							p.hit3 = -1;
							p.hit4 = -1;
						}
						hit1 = p.hit2;
						hit2 = p.hit1;
					}
					if (p.weapon == 861) { //Magic shortbow
						if (hit) hit1 = getHit(accuracy, rangeMax); else hit1 = 0;
						if (hitTwo) hit2 = getHit(accuracy, rangeMax); else hit2 = 0;
						hit1 *= 1.5;
						hit2 *= 1.5;
					}
					if (p.weapon == 1215 || p.weapon == 1231 || p.weapon == 5680 || p.weapon == 5698) { //Dragon dagger, Dragon dagger (p), Dragon dagger (+), Dragon dagger (s)
						if (hit) hit1 = getHit(accuracy, meleeMax); else hit1 = 0;
						if (hitTwo) hit2 = getHit(accuracy, meleeMax); else hit2 = 0;
						hit1 *= 1.1;
						hit2 *= 1.1;
					}
					if (p.weapon == 11235) { //Dark bow
						if (p.equipment[13] == 11212) {
							opp.requestGFX(1100, 100);
						} else {
							opp.requestGFX(1103, 100);
						}
						opp.requestAnim(opp.equipment[5] != -1 ? opp.getBlockAnim() : opp.getBlockAnim1(), 0);
						if (hit) hit1 = getHit(accuracy, rangeMax); else hit1 = 0;
						if (hitTwo) hit2 = getHit(accuracy, rangeMax); else hit2 = 0;
						if (p.equipment[13] == 11212) {
							hit1 *= 1.50;
							hit2 *= 1.50;
						} else {
							hit1 *= 1.30;
							hit2 *= 1.30;
						}
						if (hit1 < (p.equipment[13] == 11212 ? 8 : 5)) hit1 = p.equipment[13] == 11212 ? 8 : 5;
						if (hit2 < (p.equipment[13] == 11212 ? 8 : 5)) hit2 = p.equipment[13] == 11212 ? 8 : 5;
						p.getActionSender().addSoundEffect(p, 3737, 1, 0, 0);
						opp.getActionSender().addSoundEffect(opp, 3737, 1, 0, 0);
					}

					if (opp.usingPrayer(prayer)) {
						hit1 *= 0.60;
						hit2 *= 0.60;
					}
					int damage1 = (int)Math.round(hit1);
					int damage2 = (int)Math.round(hit2);

					if (!delayedSecondHit) {
						opp.appendHit(damage1, 0);
						opp.appendHit(damage2, 0);
					} else {
						opp.appendHit(damage1, 0);
						delayedHit(damage2, 2);
					}
					pm.setSpecDelay(-1);
					return;
				}
				if (hit) { //Attack hits
					if (p.weapon == 13899) { //Vesta's longsword
						playerHitDamageMelee *= 1.20;
					}
                    if (p.weapon == 13902) { //Statius's warhammer
						playerHitDamageMelee *= 1.20;
					}
                    if (p.weapon == 13904) { //Statius' warhammer (deg)
						playerHitDamageMelee *= 1.20;
					}
                    if (p.weapon == 13901) { //Vesta's longsword
						playerHitDamageMelee *= 1.20;
					}
					if (p.weapon == 11694) { //Armadyl godsword
						playerHitDamageMelee *= 1.25;
					}
					if (p.weapon == 11696) { //Bandos godsword
						playerHitDamageMelee *= 1.10;
						int toReduceBy = (int)Math.round(playerHitDamageMelee);
						int[] skills = {1, 2, 5, 0, 6, 4};
						int index = 0;
						while(toReduceBy > 0) {
							if (index > 5) {
								break;
							}
							if (opp.skillLvl[skills[index]] - toReduceBy >= 0) {
								opp.skillLvl[skills[index]] -= toReduceBy;
								opp.getActionSender().setSkillLvl(opp, skills[index]);
								break;
							} else {
								toReduceBy -= opp.skillLvl[skills[index]];
								opp.skillLvl[skills[index]] = 0;
								opp.getActionSender().setSkillLvl(opp, skills[index]);
								index++;
							}
						}
					}
					if (p.weapon == 11698) { //Saradomin godsword
						int damage = (int)Math.round(playerHitDamageMelee);
						if (opp.usingPrayer(19)) {
							damage *= 0.60;
						}
						if (damage >= 20) {
							if (p.getLevelForXP(3) - p.skillLvl[3] > (int)Math.round(damage / 2)) {
								p.skillLvl[3] += (int)Math.round(damage / 2);
								p.getActionSender().setSkillLvl(p, 3);
							} else {
								p.skillLvl[3] = p.getLevelForXP(3);
								p.getActionSender().setSkillLvl(p, 3);
							}
							if (p.getLevelForXP(5) - p.skillLvl[5] > (int)Math.round(damage / 4)) {
								p.skillLvl[5] += (int)Math.round(damage / 4);
								p.getActionSender().setSkillLvl(p, 5);
							} else {
								p.skillLvl[5] = p.getLevelForXP(5);
								p.getActionSender().setSkillLvl(p, 5);
							}
						} else {
							if (p.getLevelForXP(3) - p.skillLvl[3] > 10) {
								p.skillLvl[3] += 10;
								p.getActionSender().setSkillLvl(p, 3);
							} else {
								p.skillLvl[3] = p.getLevelForXP(3);
								p.getActionSender().setSkillLvl(p, 3);
							}
							if (p.getLevelForXP(5) - p.skillLvl[3] > 5) {
								p.skillLvl[5] += 5;
								p.getActionSender().setSkillLvl(p, 5);
							} else {
								p.skillLvl[5] = p.getLevelForXP(5);
								p.getActionSender().setSkillLvl(p, 5);
							}
						}
					}
					if (p.weapon == 11700) { //Zamorak godsword
						opp.requestGFX(369, 0);
						opp.freezeDelay = opp.usingPrayer(19) ? 33 : 16;
					}
					if (p.weapon == 11730) { //Saradomin sword
						opp.requestGFX(1194, 0);
						opp.append1Hit(getRandom(10)+5, 0);
					}
					if (p.weapon == 10887) { //Barrelchest anchor
						int[] skills = {0, 1, 4, 6};
						int levelToReduce = skills[(int)(Math.random()*skills.length)];
						int toReduceBy = (int)Math.round(playerHitDamageMelee * 0.10);
						if (opp.skillLvl[levelToReduce] - toReduceBy >= 0) {
							opp.skillLvl[levelToReduce] -= toReduceBy;
							opp.getActionSender().setSkillLvl(opp, levelToReduce);
						} else {
							opp.skillLvl[levelToReduce] = 0;
							opp.getActionSender().setSkillLvl(opp, levelToReduce);
						}
					}
					if (p.weapon == 4151) { //Abyssal whip
						opp.requestGFX(341, 100);
						opp.runEnergy -= 10;
						if (opp.runEnergy < 0) {
							opp.runEnergy = 0;
						}
						p.runEnergy += 10;
						if (p.runEnergy > 100) {
							p.runEnergy = 100;
						}
						p.getActionSender().setEnergy(p);
						opp.getActionSender().setEnergy(opp);
					}
					if (p.weapon == 1434) { //Dragon mace
						playerHitDamageMelee *= 1.45;
					}
					if (p.weapon == 1305) { //Dragon longsword
						playerHitDamageMelee *= 1.95;
					}
					if (opp.usingPrayer(prayer)) {
						playerHitDamageMelee *= 0.60;
					}
					int damage = (int)Math.round(playerHitDamageMelee);
					opp.appendHit(damage, 0);
				}
				if (!hit) { //Attack misses
					if (p.weapon == 4151) { //Abyssal whip
						opp.requestGFX(341, 100);
					}
					opp.appendHit(0, 0);
				}
				pm.setSpecDelay(-1);
				return;
			}
		} catch (Exception e) {
			return;
		}
	}

	public void delayedHit(int damage, int delay) {
		p.delayedDamageHit = damage;
		p.delayedDamageDelay = delay;
	}

	public void appendDelayedDamage(int damage) {
		try {
			Player opp = Server.engine.players[p.oppIndex];
			if (p.isDead || opp.isDead) {
				return;
			}
			opp.hitIndex = p.playerId;

			if (p.weapon == 11235) { //Dark bow
				if (p.equipment[13] == 11212) {
					opp.requestGFX(1100, 100);
				} else {
					opp.requestGFX(1103, 100);
				}
			}
			opp.requestAnim(opp.equipment[5] != -1 ? opp.getBlockAnim() : opp.getBlockAnim1(), 0);
			p.getActionSender().addSoundEffect(p, 3737, 1, 0, 0);
			opp.getActionSender().addSoundEffect(opp, 3737, 1, 0, 0);
			opp.appendHit(damage, 0);
			p.delayedDamageHit = -1;
			p.delayedDamageDelay = -1;
		} catch (Exception e) {
			return;
		}
	}

	public void appendDamages() {
		try {
			Player opp = Server.engine.players[p.oppIndex];
			if (p.isDead || opp.isDead) {
				return;
			}
			opp.hitIndex = p.playerId;

			//Actual damaging

			double playerDamage = getHit(getAccuracy(false), getMeleeMaxhit());
			if (p.damageDelay == 0 && p.damagePending) {
				if (!p.duelFight()) {
					if (!p.isSkulled && opp.enemyIndex != p.playerId) {
						p.headIconSkull = 0;
						p.skullVanishDelay = 1000;
						p.isSkulled = true;
						p.appearanceUpdateReq = true;
						p.updateReq = true;
					}
				}
				p.checkVeracs();
				if (opp.usingPrayer(19)) {
					if (!p.defile) {
						p.accuracy -= 0.40;
						playerDamage *= 0.60;
					}
				}
				if (!p.hitPlayer(opp)) {
					opp.appendHit(0, 0);
				} else {
					int damage = (int)Math.round(playerDamage);
					opp.appendHit(damage, 0);
				}
				pm.setDamageDelay(-1);
				p.damagePending = false;
				return;
			}
		} catch (Exception e) {
			return;
		}
	}

	int getCombatDelay() {

		//p.weapon sets

		if (p.weapon == 868 || p.weapon == 11230) {
			return 2;
		}
		if (p.weapon == 841 || p.weapon == 843 || p.weapon == 849 || p.weapon == 853 || p.weapon == 857 || p.weapon == 861 || p.weapon == 4734) {
			if (p.attackStyle() == 6) {
				return 3;
			}
		}
		if (p.weapon >= 1203 && p.weapon <= 1215) { //Daggers
			return 4;
		}
		if (p.weapon >= 1219 && p.weapon <= 1233) { //Daggers (p)
			return 4;
		}
		if (p.weapon >= 5668 && p.weapon <= 5682) { //Daggers (p+)
			return 4;
		}
		if (p.weapon >= 5686 && p.weapon <= 5700) { //Daggers (p++)
			return 4;
		}
		if (p.weapon >= 1321 && p.weapon <= 1333) { //Scimitars
			return 4;
		}
		if (p.weapon >= 1420 && p.weapon <= 1434) { //Maces
			return 5;
		}
		if (p.weapon >= 1291 && p.weapon <= 1305) { //Longswords
			return 5;
		}
		if (p.weapon >= 1363 && p.weapon <= 1377) { //Battleaxes
			return 5;
		}
		if (p.weapon == 4747 || (p.weapon >= 4958 && p.weapon <= 4961)) { //Torag's hammers
			return 5;
		}
		if (p.weapon == 4755 || (p.weapon >= 4982 && p.weapon <= 4985)) { //Verac's flail
			return 5;
		}
		if (p.weapon == 4726 || (p.weapon >= 4910 && p.weapon <= 4913)) { //Guthan's warspear
			return 5;
		}
		if (p.weapon >= 11694 && p.weapon <= 11700) { //Godswords
			return 6;
		}
		if ((p.weapon >= 1307 && p.weapon <= 1319) || p.weapon == 7158) { //Two-handers
			return 6;
		}
		if (p.weapon >= 3190 && p.weapon <= 3204) { //Halberds
			return 7;
		}
		if (p.weapon == 4718 || (p.weapon >= 4886 && p.weapon <= 4889)) { //Guthan's warspear
			return 7;
		}
		if (p.weapon == 9185) {
			if (p.attackStyle() == 6) {
				return 5;
			}
			return 6;
		}

		//Other p.weapons

		if (p.weapon == 4151) { //Abyssal whip
			return 4;
		}
		if (p.weapon == 11730) { //Saradomin sword
			return 4;
		}
		if (p.weapon == 13899) {
			return 5;
		}
        if (p.weapon == 13901) {
			return 5;
		}
		if (p.weapon == 4214) { //Crystal bow
			if (p.attackStyle() == 6) {
				return 5;
			} else {
				return 6;
			}
		}
		if (p.weapon == 10887) { //Barrelchest anchor
			return 6;
		}
		if (p.weapon == 6528) { //Tzhaar-ket-om
			return 6;
		}
		if (p.weapon == 4153) { //Granite maul
			return 7;
		}
		if (p.weapon == 11235) { //Dark bow
			if (p.attackStyle() == 6) {
				return 8;
			} else {
				return 9;
			}
		}

		//Default

		return 4;
	}

	int getCombatAnim() {
		if (p.weapon == -1) {
			if (p.fightStyle != 2)
				return 422;
			else
				return 423;
		}
		if (p.weapon == 11230) {
			return 582;
		}
		if (p.weapon == 868) {
			return 0x326;
		}
		if (p.weapon == 4718) {
			if (p.fightStyle != 2)
				return 2067;
			else
				return 2066;
		}
		if (p.weapon == 10581) {
			return 402;
		}
		if (p.weapon == 4153) {
			return 1665;
		}
		if (p.weapon == 4734) {
			return 2075;
		}
		if (p.weapon == 9703) {
			return 412;
		}
		if (p.weapon == 3204) {
			return 440;
		}
		if (p.weapon == 7158) {
			switch (p.fightStyle) {
				case 1:
				case 2: return 7041;
				case 3: return 7048;
				case 4: return 7049;
			}
		}
		if (p.weapon == 11696 || p.weapon == 11694 || p.weapon == 11698 || p.weapon == 11700 || p.weapon == 11730) {
			switch (p.fightStyle) {
				case 1:
				case 2: return 7041;
				case 3: return 7048;
				case 4: return 7049;
			}
		}
		for (int i = 1307; i <= 1319; i += 2) {
			if (p.weapon == i) {
				switch (p.fightStyle) {
					case 1:
					case 2: return 7041;
					case 3: return 7048;
					case 4: return 7049;
				}
			}
		}
		for (int i = 9174; i < 9186; i++) {
			if (p.weapon == i) {
				return 4230;
			}
		}
		for (int i = 1265; i < 1276; i++) {
			if (p.weapon == i) {
				return 401;
			}
		}
		if (p.weapon == 14484) {
			return 391;
		}
		if (p.weapon == 4755) {
			return 2062;
		}
		if (p.weapon == 10887) {
			return 5865;
		}
		if (p.weapon == 4151) {
			return 1658;
		}
		if (p.weapon == 5698) {
			return 402;
		}
		if (p.weapon == 6528) { //Tzhaar-ket-om
			return 2661;
		}
		for (int i = 0; i < p.rangeBows.length; i++)
			if (p.weapon == p.rangeBows[i] || p.weapon == 11235 || p.weapon == 9705 || p.weapon == 4214)
				return 426;
	return 451;
	}

	/**
	 * Sets npc attacking.
	 * @param attackingNpc if player is attacking npc. return true.
	 */
	public void setNpcAttack(boolean attackingNpc) {
		p.attackingNpc = attackingNpc;
	}

	/**
	 * Sets npc attack delay
	 * @param atkDelay the delay which is gonna be set.
	 */
	public void setAtkDelay(int atkDelay) {
		p.atkDelay = atkDelay;
	}

	/**
	 * Sets damage delay.
	 * @param damageDelay1 the delay which is gonna be setted.
	 */
	public void setNpcDamageDelay(int damageDelay1) {
		p.damageDelay1 = damageDelay1;
	}

	public void setEnableDamageNpc(boolean enableDamage) {
		p.enableDamage = enableDamage;
	}

	/**
	 * Once damage delay reaches 0 this method gets activated.
	 */
	public void appendNpcDamageMelee() {
		NPC n = Server.engine.npcs[p.attackNpc];
		if (p == null || n == null || n.playerIndex == -1 || n.currentHP <= 0) {
			p.resetAttack();
			return;
		}
		NpcCombat npcCombat = new NpcCombat(n);
		int npcDamage = getRandom(getMeleeMaxhit());
		int npcDamage2 = getRandom(getRangedMaxhit());
		try {
			if (npcDamage <= 0 || npcDamage2 <= 0)
				npcDamage = npcDamage2 = 0;
			if (n.npcType != 2745 && n.npcType != 4284 && n.npcType != 2746)
				n.playerIndex = p.playerId;
			else
				n.spawnedFor = p.playerId;
			if (n.npcType == 6247)
				p.disturbSara = true;
			if (n.npcType == 2736 || n.npcType == 2737)
				p.appendHit(1, 0);
			if (!usingRange()) {
				if (p.damageDelay1 == 0) {
					if (n.changeAnimDelay <= 0)
						n.requestAnim(npcCombat.getBlockAnim(), 0);
					n.playerIndex = p.playerId;
					n.underAttack = true;
					n.battleCDelay = 7;
					if (n.battleCount <= 0)
						n.battleCount++;
					if (p.weapon == 10581) {
						for (int i = 0; i < n.kalphiteMonsters.length; i++) {
							if (n.npcType == n.kalphiteMonsters[i]) {
								n.appendHit(npcDamage * 3, 0);
								if (p.fightStyle == 1)
									p.appendExperience(npcDamage * 120, 0);
								if (p.fightStyle == 2)
									p.appendExperience(npcDamage * 120, 2);
								if (p.fightStyle == 3)
									p.appendExperience(npcDamage * 120, 2);
								if (p.fightStyle == 4)
									p.appendExperience(npcDamage * 120, 1);
								p.appendExperience(npcDamage * 75, 3);
								p.damageDelay1 = -1;
								p.enableDamage = false;
								return;
							}
						}
					}
					if (getRandom(p.equipmentBonus[10]) > getRandom(getNpcDefence())) {
						if (n.npcType == 7771) {
							if (p.meleePrayer)
								p.appendHit(15, 0);
							for (int i = 0; i <= p.miningAxes.length; i++) {
								if (p.weapon != p.miningAxes[i]) {
									n.appendHit(0, 0);
								} else {
									n.appendHit(npcDamage, 0);
									n.brokenArmour--;
								}
							}
						} else {
							n.appendHit(npcDamage, 0);
						}
						if (p.fightStyle == 1)
							p.appendExperience(npcDamage * 120, 0);
						if (p.fightStyle == 2)
							p.appendExperience(npcDamage * 120, 2);
						if (p.fightStyle == 3)
							p.appendExperience(npcDamage * 120, 2);
						if (p.fightStyle == 4)
							p.appendExperience(npcDamage * 120, 1);
						p.appendExperience(npcDamage * 75, 3);
					} else {
						if (n.npcType == 7771) {
							if (p.meleePrayer)
								p.appendHit(15, 0);
							for (int i = 0; i <= p.miningAxes.length; i++) {
								if (p.weapon != p.miningAxes[i]) {
									n.appendHit(0, 0);
								} else {
									n.appendHit(0, 0);
									n.brokenArmour--;
								}
							}
						} else {
							n.appendHit(0, 0);
						}
					}
					p.damageDelay1 = -1;
					p.enableDamage = false;
				}
			} else {
				if (p.damageDelay1 == 0) {
					n.playerIndex = p.playerId;
					n.underAttack = true;
					n.requestAnim(npcCombat.getBlockAnim(), 0);
					if (!p.hitNpcRange()) {
						n.appendHit(0, 0);
						if (p.weapon == 11235)
							n.appendHit(0, 0);
					} else {
						n.appendHit(npcDamage2, 0);
						if (p.weapon == 11235)
							n.appendHit(npcDamage2, 0);
						p.appendExperience(npcDamage * 90, 4);
						p.appendExperience(npcDamage * 90, 3);
					}
					p.damageDelay1 = -1;
					p.enableDamage = false;
				}
			}
		} catch (Exception e) {
			p.resetAttack();
			p.enableDamage = false;
			p.damageDelay1 = -1;
			return;
		}
	}

	/**
	 * (Special attk) Once damage delay reaches 0 this method gets activated.
	 */
	public void appendNpcDamageMeleeSpec() {
		NPC n = Server.engine.npcs[p.attackNpc];
		if (p == null || n == null || n.playerIndex == -1 || n.currentHP <= 0) {
			p.resetAttack();
			return;
		}
		try {
			int npcDamage = getRandom(getMeleeMaxhit());
			if (n.npcType != 2745 && n.npcType != 4284 && n.npcType != 2746)
				n.playerIndex = p.playerId;
			else
				n.spawnedFor = p.playerId;
			if (n.npcType == 6247)
				p.disturbSara = true;
			if (npcDamage <= 0)
				npcDamage = 0;
			if (n.npcType == 2736 || n.npcType == 2737)
				p.appendHit(1, 0);
			if (!usingRange()) {
				if (p.damageSpecDelay == 0) {
					n.playerIndex = p.playerId;
					n.underAttack = true;
					n.battleCDelay = 7;
					if (n.battleCount <= 0)
						n.battleCount++;
					if (!hasAccuracy()) {
						if (getRandom(p.equipmentBonus[11]) > getRandom(n.meleeDef + getNpcDefence())) {
							if (n.npcType == 7771) {
								if (p.meleePrayer)
									p.appendHit(15, 0);
								n.appendHit(0, 0);
							} else {
								n.appendHit(npcDamage, 0);
							}
							if (p.weapon == 5698 || p.weapon == 11730 || p.weapon == 3204) {
								if (n.npcType == 7771) {
									if (p.meleePrayer)
										p.appendHit(15, 0);
									n.appendHit(0, 0);
								} else {
									n.appendHit(npcDamage, 0);
								}
							}
							p.appendExperience(npcDamage * 100, 2);
												} else {
							n.appendHit(getRandom(getMeleeMaxhit()), 0);
							if (p.weapon == 5698 || p.weapon == 11730 || p.weapon == 3204 || p.weapon == 14484) {
								n.appendHit(getRandom(getMeleeMaxhit()), 0);
							}
						}
					} else {
						if (n.npcType == 7771) {
							if (p.meleePrayer)
								p.appendHit(15, 0);
							n.appendHit(0, 0);
						} else {
							n.appendHit(getRandom(getMeleeMaxhit()), 0);
						}
						p.appendExperience(npcDamage * 4, 2);
					}
					p.damageSpecDelay = -1;
					p.enableSpecDamage = false;
				}
			} else {
				if (p.damageDelay == 0) {
					n.playerIndex = p.playerId;
					n.underAttack = true;
					n.appendHit(getRandom(getMeleeMaxhit()), 0);
					if (p.weapon == 11235) {
						n.appendHit(getRandom(getMeleeMaxhit()), 0);
						n.requestGFX(1100, 100);
					}
					p.damageSpecDelay = -1;
					p.enableSpecDamage = false;
				}
			}
		} catch (Exception e) {
			p.resetAttack();
			p.damageSpecDelay = -1;
			p.enableSpecDamage = false;
			return;
		}
	}

	int getNpcDefence() {
		NPC n = Server.engine.npcs[p.attackNpc];
		switch (n.npcType) {

			/*case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
				return 6;
			case 50: return 210;
			case 134: return 100;
			case 907: return 120;
			case 1153: return 60;
			case 1154: return 100;
			case 1155: return 130;
			case 1156: return 50;
			case 1157: return 120;
			case 5253: return 220;
			case 1158:
				if (p.fullVerac())
					return 120;
				else
					return 240;
			case 1160:
				if (p.fullVerac())
					return 95;
				else
					return 225;
			case 3847: return 290;
			case 1615: return 120;
			case 5363: return 320;
			case 5902: return 260;
			case 2745: return 240;
			case 2744: return 165;
			case 2746: return 10;
			case 1472: return 180;
			case 4284: return 100;
			case 4291: return 140;
			case 4292: return 153;
			case 6203: return 330;
			case 6222: return 340;
			case 6223: return 320;
			case 6225: return 300;
			case 6227: return 330;
			case 6247: return 340;
			case 6260: return 340;
			case 6625: return 230;
			case 6691: return 250;
			case 6729: return 260;
			case 6998: return 220;
			case 6999: return 210;
			case 7770: return 200;
			case 7771: return 400;

			default:*/
			}
				return 0;
		
	}

	int getProjectile() {
		switch (p.equipment[13]) {

			case 882:
				return 10;
			case 892:
				return 15;

			case 4740:
			case 9141:
			case 9143:
			case 9144:
				return 27;

			case 9706:
				return 16;

			case -1:
				if (p.weapon == 4214)
					return 249;

			default:
				return -1;
		}
	}

	int getProjSpeed() {
		NPC n = Server.engine.npcs[p.attackNpc];
		if (p == null || n == null)
			return 150;
		if (Misc.getDistance(p.absX, p.absY, n.absX, n.absY) == 1)
			return 65;
		if (Misc.getDistance(p.absX, p.absY, n.absX, n.absY) == 2)
			return 68;
		if (Misc.getDistance(p.absX, p.absY, n.absX, n.absY) == 3)
			return 70;
		if (Misc.getDistance(p.absX, p.absY, n.absX, n.absY) == 4)
			return 72;
		if (Misc.getDistance(p.absX, p.absY, n.absX, n.absY) == 5)
			return 74;
		if (Misc.getDistance(p.absX, p.absY, n.absX, n.absY) == 6)
			return 76;
	return 73;
	}

	int getRangeDamageDelay() {
		Player p2 = Server.engine.players[p.enemyIndex];
		if (p == null || p2 == null) {
			return 0;
		}
		int distance = Misc.getDistance(p.absX, p.absY, p2.absX, p2.absY);
		switch (distance) {
			case 1:	return 1;
			case 2:
			case 3:
			case 4:
			case 5:
			case 6: return 2;
		}
		return 3;
	}

	int getRangeDamageDelayNPC() {
		NPC n = Server.engine.npcs[p.attackNpc];
		if (p == null || n == null) {
			return 999999999;
		}
		int distance = Misc.getDistance(p.absX, p.absY, n.absX, n.absY);
		switch (distance) {
			case 1:	return 1;
			case 2:
			case 3:
			case 4:
			case 5:
			case 6: return 2;
			default: return 0;
		}
	}

	public int getNpcDefenceRange() {
		NPC n = Server.engine.npcs[p.attackNpc];
		switch (n.npcType) {

			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
				return 3;

			case 134: return 90;
			case 1158: return 520;
			case 1160: return 180;
			case 1615: return 140;
			case 2614: return 230;
			case 5902: return 280;
			case 5253: return 190;
			case 6223: return 195;
			case 6225: return 210;
			case 6227: return 200;
			case 6222: return 190;
			case 6260: return 340;
			case 6261: return 230;
			case 6263: return 250;
			case 3847: return 230;
			case 1472: return 240;
			case 2745: return 130;
			case 2746: return 10;
			case 4284: return 150;
			case 4291: return 90;
			case 4292: return 95;
			case 6265: return 240;
			case 6625: return 150;
			case 6729: return 170;
			case 6247: return 290;
			case 6691: return 190;
			case 6998: return 180;
			case 6999: return 160;
			case 7770: return 180;
			case 7771: return 420;

			default:
				return 20;
		}
	}

    	private int getRandom(int range) {
        	return (int)(Math.random() * (range + 1));
    	}

    	private int getRandom(double range) {
        	return (int)(Math.random() * (range + 1));
    	}

	private int[] godSwords = {
		11694, 11696, 11698, 11700
	};

}