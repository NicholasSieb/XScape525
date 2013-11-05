/*
 * Class ObjectOption1
 *
 * Version 2.1
 *
 * Friday, August 22, 2008
 *
 * Originally Created By: Palidino76/Revised By: Codeusa/Completed By: Shahir/Admin Mikee
 */

package net.com.codeusa.net.packethandler;

import net.com.codeusa.*;
import net.com.codeusa.model.misc.*;
import net.com.codeusa.model.Player;
import net.com.codeusa.model.skills.*;
import net.com.codeusa.model.games.*;
import net.com.codeusa.util.Misc;

public class ObjectOption1 implements Packet {

    /*
     * make sure to document EVERY coordinate to go with each object unless an un-important object(wilderness ditch lol).
     * This will prevent people from spawning an object client side and actually using it.
     * So make sure to include with the id, objectX == # && objectY == #
    */

    /**
     * Handles the first option on objects.
     * @param p The Player which the frame should be handled for.
     * @param packetId The packet id this belongs to.
     * @param packetSize The amount of bytes being recieved for this packet.
     */
    public void handlePacket(Player p, int packetId, int packetSize) {
        if (p == null || p.stream == null) {
            return;
        }
        if (!p.objectOption1) {
            p.clickX = p.stream.readUnsignedWordBigEndian();
            p.clickId = p.stream.readUnsignedWord();
            p.clickY = p.stream.readUnsignedWordBigEndianA();
            if (Misc.getDistance(p.absX, p.absY, p.clickX, p.clickY) > 3 && p.clickId != 26303) {
                return;
            }
            p.objectOption1 = true;
        }
        p.objectOption1 = false;
	PlayerMining playerMining = new PlayerMining(p);
	PlayerMethods pm = new PlayerMethods(p);
	FightCave fCave = new FightCave(p);
	System.out.println("x "+p.clickX+" y: "+p.clickY);
        switch (p.clickId) {
       
            case 38700:
                p.setCoords(3267, 3684, 4);
            break;
            case 38698:

		p.setCoords(2815, 5511, 4);

                p.getActionSender().sendMessage(p, "<img=1>WELCOME TO CLANWARS safe pk<img=1>");

                break;
case 4008:
			if (p.skillLvl[5] != p.getLevelForXP(5)) {
				p.requestAnim(645, 0);
				p.skillLvl[5] = p.getLevelForXP(5);
				p.getActionSender().sendMessage(p, "You restore your prayer points.");
			} else {
				p.getActionSender().sendMessage(p, "Your prayer points are still full.");
			}
			p.getActionSender().setSkillLvl(p, 5);
			p.appearanceUpdateReq = true;
			p.updateReq = true;
		break;

		case 1814: //Ardougne Lever
			p.leverTeleport("Deep Wilderness");
		break;
		case 1815: //Deep Wilderness
			p.leverTeleport("Ardougne Lever");
		break;
		case 5959: //Mage Bank (Outside)
			p.leverTeleport("Mage Bank (Inside)");
		break;
		case 5960: //Mage Bank (Inside)
			p.leverTeleport("Mage Bank (Outside)");
		break;
		case 9706: //Mage Arena (inside)
			if (p.teleblocked) {
            			p.getActionSender().sendMessage(p, "You are teleport blocked!");
				return;
			}
			if (p.absX == 3105 && p.absY == 3956) {
				p.setCoords(3105, 3951, 0);
			}
		break;
		case 9707: //Mage Arena (outside)
			if (p.teleblocked) {
            			p.getActionSender().sendMessage(p, "You are teleport blocked!");
				return;
			}
			if (p.absX == 3105 && p.absY == 3951) {
				p.setCoords(3105, 3956, 0);
			}
			if (p.clickX == 3369 && p.clickY == 3489) {//Admin Zone 1
				if (p.rights == 2) {
				p.teleportTo(2542, 3031, 0, 4, 0, 8939, 8941, 1678, 0, 1679, 0);
				} else {
				p.getActionSender().sendMessage(p, "You need to be an Administrator to use this feature.");
				}
			}
			if (p.clickX == 2543 && p.clickY == 3031) {//Admin Zone 2
				if (p.rights == 2) {
				p.teleportTo(3369, 3490, 0, 4, 0, 8939, 8941, 1678, 0, 1679, 0);
				} else {
				p.getActionSender().sendMessage(p, "You need to be an Administrator to use this feature.");
				}
			}
		break;
		case 1816:
            		p.getActionSender().sendMessage(p, "Warning: the only exit from here is the lever!");
            		p.leverTeleport("Kbd Lever");
		break;
		case 1817:
            		p.leverTeleport("Home");
		break;
            case 2878:
                p.setCoords(2509, 4690, 0);
                break;
            case 2879:
                p.setCoords(2541, 4717, 0);
                break;
            case 2875://guthix cape
                p.requestAnim(645, 0);
                p.requestForceChat("Hail Guthix the all mighy god!");
                Engine.playerItems.addItem(p, 2413, 1);
                break;
            case 2873://saradomin cape
                p.requestAnim(645, 0);
                p.requestForceChat("Hail Saradomin the all mighy god!");
                Engine.playerItems.addItem(p, 2412, 1);
                break;
            case 2874://zam cape
                p.requestAnim(645, 0);
                p.requestForceChat("Hail zamorak the all mighy god!");
                Engine.playerItems.addItem(p, 2414, 1);
                break;
            case 35543:
			if (p.absX == 3304 && p.absY == 3117 && p.heightLevel == 0) {
				p.setCoords(3304, 3115, 0);
			} else if (p.absX == 3304 && p.absY == 3115 && p.heightLevel == 0) {
				p.setCoords(3304, 3117, 0);
			}
		break;
		case 3782:
		case 3783://Corp
		if (p.absX == 2896 && p.absY == 3619 && p.heightLevel == 0 && p.absX == 2896 && p.absY == 3618 && p.heightLevel == 0) {
				p.setCoords(2897, 3618, 0);
			} else if (p.absX == 2897 && p.absY == 3618 && p.heightLevel == 4) {
				p.setCoords(2896, 3618, 0);
			}
			break;
		case 10529:
			if (p.absX == 3445 && p.absY == 3554 && p.heightLevel == 2)
				p.setCoords(3445, 3555, 2);
			else if (p.absX == 3445 && p.absY == 3555 && p.heightLevel == 2)
				p.setCoords(3445, 3554, 2);
		break;

		case 28214:
			p.blackTeam = false;
			p.whiteTeam = false;
			p.setCoords(3266 + Misc.random(3), 3683 + Misc.random(3), 0);
			p.getActionSender().sendMessage(p, "You stepped into the portal and left your clan mates.");
		break;

		case 28213:
			if (p.absX == 3270 && p.absY == 3675 || p.absX == 3271 && p.absY == 3675 || p.absX == 3272 && p.absY == 3675 ||
				p.absX == 3273 && p.absY == 3675) {
				p.whiteTeam = false;
				p.blackTeam = true;
				p.getActionSender().sendMessage(p, "Welcome to the Black clan.");
				p.getActionSender().sendMessage(p, "The game starts in " + Server.clanWaitDelay / 2 + " seconds.");
				p.getActionSender().sendMessage(p, "The game ends in " + Server.clanFightDelay / 2 + " seconds.");
			} else if (p.absX == 3271 && p.absY == 3692 || p.absX == 3272 && p.absY == 3692 || p.absX == 3273 && p.absY == 3692) {
				p.blackTeam = false;
				p.whiteTeam = true;
				p.getActionSender().sendMessage(p, "Welcome to the White clan.");
				p.getActionSender().sendMessage(p, "The game starts in " + Server.clanWaitDelay / 2 + " seconds.");
				p.getActionSender().sendMessage(p, "The game ends in " + Server.clanFightDelay / 2 + " seconds.");
			}
		break;

		case 14109:
			//p.setCoords(2449, 5178, 0);
			p.getActionSender().sendMessage(p, "Tzhaar is disabled for a few edits.");
		break;

		case 26293:
			p.getActionSender().sendMessage(p, "You've failed to climb up the rope.");
		break;

		case 23610:
			p.setCoords(3508, 9493, 0);
		break;

		/**
		 * Start Jail Escape Route
	 	 */
		case 31539:
			if (p.absX == 3032 && p.absY == 2985 && p.heightLevel == 2) {
			p.setCoords(3031, 2985, 2);
			}
			if (p.absX == 3031 && p.absY == 2985 && p.heightLevel == 2) {
			p.setCoords(3032, 2985, 2);
			}
			if (p.absX == 3034 && p.absY == 2985 && p.heightLevel == 1) {
			p.setCoords(3033, 2985, 1);
			}
			if (p.absX == 3033 && p.absY == 2985 && p.heightLevel == 1) {
			p.setCoords(3034, 2985, 1);
			}
			if (p.absX == 3032 && p.absY == 2977 && p.heightLevel == 1) {
			p.setCoords(3033, 2977, 1);
			}
			if (p.absX == 3033 && p.absY == 2977 && p.heightLevel == 1) {
			p.setCoords(3032, 2977, 1);
			}
			if (p.absX == 3030 && p.absY == 2982 && p.heightLevel == 1) {
			p.setCoords(3030, 2981, 1);
			}
			if (p.absX == 3030 && p.absY == 2981 && p.heightLevel == 1) {
			p.setCoords(3030, 2982, 1);
			}
			if (p.absX == 3033 && p.absY == 2980 && p.heightLevel == 0) {
			p.setCoords(3033, 2979, 0);
			}
			if (p.absX == 3033 && p.absY == 2979 && p.heightLevel == 0) {
			p.setCoords(3033, 2980, 0);
			}
		break;

		case 31454:
			if (p.absX == 3034 && p.absY == 2979 && p.heightLevel == 2) {
			p.setCoords(3035, 2979, 2);
			}
			if (p.absX == 3035 && p.absY == 2979 && p.heightLevel == 2) {
			p.setCoords(3034, 2979, 2);
			}
		break;

		case 31534://Cell With Broken Wall
			/*if (p.absX == 3033 && p.absY == 2985 && p.heightLevel == 0) {
				if (p.jailed == 0) {*/
					p.getActionSender().sendMessage(p, "The door is locked.");
					/*return;
				}
			p.setCoords(3033, 2986, 0);
			}
			if (p.absX == 3033 && p.absY == 2986 && p.heightLevel == 0) {
			p.setCoords(3033, 2985, 0);
			}*/
		break;

		case 31546://Pile of Rubble
			if (p.absX == 3033 && p.absY == 2988 && p.heightLevel == 0) {
			}
		break;

		case 31455://Front Door
			if (p.jailed > 0) {
				if (p.absX == 3033 && p.absY == 2975 && p.heightLevel == 0) {
				p.getActionSender().sendMessage(p, "This door is locked!");
				}
				if (p.absX == 3033 && p.absY == 2974 && p.heightLevel == 0) {
				p.getActionSender().sendMessage(p, "This door is locked!");
				}
			} else if (p.jailed == 0) {
				if (p.absX == 3033 && p.absY == 2975 && p.heightLevel == 0) {
				p.getActionSender().sendMessage(p, "Come back soon.");
				p.setCoords(3033, 2974, 0);
				}
				if (p.absX == 3033 && p.absY == 2974 && p.heightLevel == 0) {
				p.getActionSender().sendMessage(p, "Welcome to Relentless525 high security prison.");
				p.setCoords(3033, 2975, 0);
				}
			}
		break;

		case 31651://Dock[where you make your escape]
			if (p.absX == 3050 && p.absY == 2979 && p.heightLevel == 0) {
			p.playerWalk(3051, 2979, 6132, 0);
			return;
			} else if (p.absX == 3051 && p.absY == 2979 && p.heightLevel == 0) {
			p.jailed = 0;
			p.teleportTo(2792, 3414, 0, 4, 0, 8939, 8941, 1678, 0, 1679, 0);
			}
		break;

		case 31530:
			if (p.absX >= 3036 && p.absX <= 3039 && p.absY >= 2980  && p.absY <= 2983 && p.heightLevel == 2) {
			p.setCoords(p.absX, p.absY, 1);
				if (p.jailed > 0) {
					p.jailed = 2;
				}
			}
			if (p.absX >= 3034 && p.absX <= 3035 && p.absY >= 2980  && p.absY <= 2981 && p.heightLevel == 1) {
			p.setCoords(3034, 2980, 0);
				if (p.jailed > 0) {
					p.jailed = 1;
				}
			}
		break;


		case 31529:
			if (p.absX >= 3036 && p.absX <= 3039 && p.absY >= 2980  && p.absY <= 2983 && p.heightLevel == 1) {
			p.setCoords(p.absX, p.absY, 2);
				if (p.jailed > 0) {
					p.jailed = 3;
				}
			}
			if (p.absX >= 3032 && p.absX <= 3034 && p.absY >= 2980  && p.absY <= 2982 && p.heightLevel == 0) {
			p.setCoords(3034, 2980, 1);
				if (p.jailed > 0) {
					p.jailed = 2;
				}
			}
		break;
		/**
		 * End Jail Escape Route
	 	 */
                 //start of thief stalls
            case 34385:
                if (p.getLevelForXP(17) < 74){
                    Engine.playerItems.addItem(p, 995, 150000);
                    p.appendExperience(10000, 17);
                    p.requestAnim(888, 0);
        }  else  {
             p.getActionSender().sendMessage(p, "you need 75 thief to use this stall.");

        }

        break;


		/**
		 * Start Donator/Moderator/Administrator Zones
	 	 */
		case 2832://Admin Zone Battlements
			if (p.rights == 2) {
				if (p.clickX == 2507 && p.clickY == 3011 || p.clickX == 2507 && p.clickY == 3012) {
					if (p.absX == 2506 && p.absY == 3011 || p.absX == 2506 && p.absY == 3012) {
						p.setCoords(p.absX+2, p.absY, 0);
						p.getActionSender().sendMessage(p, "Welcome to Relentless Admin Zone.");
						return;
					}
					if (p.absX == 2508 && p.absY == 3011 || p.absX == 2508 && p.absY == 3012) {
						p.setCoords(p.absX-2, p.absY, 0);
						p.getActionSender().sendMessage(p, "You exit the Admin Zone.");
					}
				}
			} else {
				p.getActionSender().sendMessage(p, "You need to be an Administrator to enter the Admin Zone.");
			}
		break;

		case 2830:
		case 2831://Admin Zone Bridge Gap
			if (p.rights > 1) {
					if (p.absX == 2530 && p.absY == 3026 || p.absX == 2531 && p.absY == 3026) {
						p.playerWalk(p.absX, p.absY+3, 6132, 0);
						return;
					}
					if (p.absX == 2530 && p.absY == 3029 || p.absX == 2531 && p.absY == 3029) {
						p.playerWalk(p.absX, p.absY-3, 6132, 0);
						return;
					}
			}
			if (p.rights < 2) {
				p.getActionSender().sendMessage(p, "You need to be a Administrator to use this feature.");
			}
		break;

		case 2787:
		case 2786://Mod Zone Entrance
			if (p.rights > 0) {
				if (p.clickX == 2549 && p.clickY == 3028 || p.clickX == 2550 && p.clickY == 3028) {
					if (p.absX == 2549 && p.absY == 3028 || p.absX == 2550 && p.absY == 3028) {
						p.getActionSender().sendMessage(p, "Welcome to Relentless Mod Zone");
						p.playerWalk(p.absX, p.absY-1, 6132, 0);
						return;
					}
					if (p.absX == 2549 && p.absY == 3027 || p.absX == 2550 && p.absY == 3027) {
						p.getActionSender().sendMessage(p, "You exit the Mod Zone.");
						p.playerWalk(p.absX, p.absY+1, 6132, 0);
					}
				}
			}
			if (p.rights == 0) {
				p.getActionSender().sendMessage(p, "You need to be a Moderator to enter the Mod Zone.");
			}
		break;

		case 2788:
		case 2789://Donator Zone Entrance
			if (p.Donator > 0 || p.rights > 0) {
				if (p.clickX == 2504 && p.clickY == 3063 || p.clickX == 2504 && p.clickY == 3062) {
					if (p.absX == 2504 && p.absY == 3063 || p.absX == 2504 && p.absY == 3062) {
						p.getActionSender().sendMessage(p, "Welcome to Relentless Donator Zone");
						p.playerWalk(p.absX-1, p.absY, 6132, 0);
						return;
					}
					if (p.absX == 2503 && p.absY == 3063 || p.absX == 2503 && p.absY == 3062) {
						p.getActionSender().sendMessage(p, "You exit the Donator Zone.");
						p.playerWalk(p.absX+1, p.absY, 6132, 0);
					}
				}
			}
			if (p.rights == 0 && p.Donator == 0) {
				p.getActionSender().sendMessage(p, "You need to be a Donator to enter the Donator Zone.");
			}
		break;

		case 2804://Donator Zone Cave
		case 2810://Mod Zone Cave
				if (p.rights > 0) {
					if (p.absX == 2529 && p.absY == 3013 || p.absX == 2529 && p.absY == 3012) {
						p.setCoords(2506, 3039, 0);
						return;
					}
					if (p.absX == 2506 && p.absY > 3036 && p.absX == 2506 && p.absY < 3041) {
						p.setCoords(2529, 3013, 0);
					}
				}
			if (p.rights == 0) {
				p.getActionSender().sendMessage(p, "You need to be a Moderator to use this feature.");
			}
		break;

		/**
		 * End Donator/Moderator/Administrator Zones
	 	 */

		case 9356:
			p.setCoords(2399, 5156, p.playerId * 4);
			p.waveDelay = 20;
			p.waveCount = 0;
		break;

		case 3831:
			p.setCoords(3483, 9509, 2);
		break;

		case 15116:
			p.setCoords(3227, 3103, 0);
		break;

		case 3829:
			p.setCoords(2509, 3847, 0);
		break;

		case 15647:
			if (p.absX == 2842 && p.absY == 3541 && p.heightLevel == 1)
				p.setCoords(2842, 3540, 1);
			else if (p.absX == 2842 && p.absY == 3540 && p.heightLevel == 1)
				p.setCoords(2842, 3541, 1);
		break;

		case 1530:
			if (p.absX == 2838 && p.absY == 3539 && p.heightLevel == 1)
				p.setCoords(2838, 3538, 1);
			else if (p.absX == 2838 && p.absY == 3538 && p.heightLevel == 1)
				p.setCoords(2838, 3539, 1);
		break;

		case 1738:
			if (p.absX == 2841 && p.absY == 3538 && p.heightLevel == 0)
				p.setCoords(2840, 3539, 1);
			else if (p.absX == 2840 && p.absY == 3539 && p.heightLevel == 2)
				p.setCoords(2841, 3538, 0);
		break;

		case 15644:
			if (p.absX == 2855 && p.absY == 3546 && p.heightLevel == 0)
				p.setCoords(2855, 3545, 0);
			else if (p.absX == 2855 && p.absY == 3545 && p.heightLevel == 0)
				p.setCoords(2855, 3546, 0);
			//Height 2
			if (p.absX == 2846 && p.absY == 3541 && p.heightLevel == 2) {
				if (Server.engine.playerItems.hasPlayerItemAmount(p, 8851, 100))
					p.setCoords(2847, 3541, 2);
				else
					p.getActionSender().sendMessage(p, "You need atleast 100 Warrior guild tokens to enter.");
			}
			if (p.absX == 2847 && p.absY == 3541 && p.heightLevel == 2)
				p.setCoords(2846, 3541, 2);
          		Misc.println("[" + p.username + "] Unhandled object 1: " + p.clickId);
		break;

		case 15641:
			if (p.absX == 2854 && p.absY == 3546 && p.heightLevel == 0)
				p.setCoords(2854, 3545, 0);
			else if (p.absX == 2854 && p.absY == 3545 && p.heightLevel == 0)
				p.setCoords(2854, 3546, 0);
			//Height 2
			if (p.absX == 2846 && p.absY == 3540 && p.heightLevel == 2) {
				if (Server.engine.playerItems.hasPlayerItemAmount(p, 8851, 100))
					p.setCoords(2847, 3540, 2);
				else
					p.getActionSender().sendMessage(p, "You need atleast 100 Warrior guild tokens to enter.");
			}
			if (p.absX == 2847 && p.absY == 3540 && p.heightLevel == 2)
				p.setCoords(2846, 3540, 2);
          		Misc.println("[" + p.username + "] Unhandled object 1: " + p.clickId);
		break;

		case 3832:
			p.setCoords(3483, 9509, 2);
		break;

		case 9368:
			if (p.rights >= 1) {
				if (p.absX == 2399 && p.absY == 5169 && p.heightLevel == 0) {
					p.setCoords(2399, 5167, fCave.getCaveHeight());
					p.getActionSender().sendMessage(p, "You enter the Fight Cave.");
					p.waveDelay = 20;
				} else if (p.absX == 2399 && p.absY == 5167) {
					p.setCoords(2399, 5169, 0);
					p.getActionSender().sendMessage(p, "You leave the Fight Cave.");
				}
			} else {
				p.getActionSender().sendMessage(p, "Sorry, This feature is moderator+ only.");
			}
		break;



		/**
		 * Enter Bandos stronghold
	 	 */
		case 26384:
			if (p.getLevelForXP(2) > 69) {
				if (p.absX == 2851 && p.absY == 5333) {
					p.setCoords(2850, 5333, 2);
				} else if (p.absX == 2850 && p.absY == 5333) {
					p.setCoords(2851, 5333, 2);
				}
			} else {
				p.getActionSender().sendMessage(p, "You need a Strength level of 70 to enter Bandos's Stronghold.");
			}
		break;

		/**
		 * Enter saradomin part
		 */
		case 26444:
			if (p.absX == 2912 && p.absY == 5300) {
				p.setCoords(2914, 5300, 1);
			}
		break;

		/**
		 * Enter saradomin part 2
		 */
		case 26445:
			if (p.absX == 2920 && p.absY == 5276) {
				p.setCoords(2920, 5274, 0);
			}
		break;

		case 26427:
			if (p.absX == 2908 && p.absY == 5265) {
				p.setCoords(2907, 5265, 0);
			} else if (p.absX == 2907 && p.absY == 5265) {
				p.setCoords(2908, 5265, 0);
			}
		break;

		/**
		 * Enter Tsusaroth's chamber
		 */
		case 26428:
			if (p.absX == 2925 && p.absY == 5332) {
				if (p.godWarsKills[3] >= 10)
					p.setCoords(2925, 5331, 2);
				else
					p.getActionSender().sendMessage(p, "You need atleast 10 Zamorak kills to enter this chamber.");
			}
			if (p.absX == 2925 && p.absY == 5331) {
				p.setCoords(2925, 5332, 2);
			}
			p.getActionSender().setOverlay(p, 598);
		break;

		/**
		 * Enter/Exit Zamorak's fortress.
		 */
		case 26439:
			if (p.skillLvl[3] > 69) {
				if (p.absX == 2885 && p.absY == 5345) {
					p.getActionSender().setOverlay(p, 601);
					p.setCoords(2885, 5332, 2);
				} else	if (p.absX == 2885 && p.absY == 5332) {
					p.getActionSender().setOverlay(p, 598);
					p.setCoords(2885, 5345, 2);
				}
			} else {
				p.getActionSender().sendMessage(p, "You need atleast a hitpoint level of 70 to climb off the bridge.");
			}
		break;

		/**
		 * Enter/Exit Armadyl's Eyrie.
		 */
		case 26303:
			if (p.equipment[3] == 9185) {
				if (p.skillLvl[4] > 69) {
					if (Misc.getDistance(p.absX, p.absY, p.clickX, p.clickY) <= 11) {
						if (p.absX == 2871 && p.absY == 5269) {
							p.setCoords(2872, 5279, 2);
							p.getActionSender().sendMessage(p, "You leave Armadyl's Eyrie.");
						} else {
							p.setCoords(2871, 5269, 2);
							p.getActionSender().sendMessage(p, "You enter Armadyl's Eyrie.");
						}

					}
				} else {
					p.getActionSender().sendMessage(p, "You need a ranged level of 70 to enter Armadyl's Eyrie.");
				}
			} else {
				p.getActionSender().sendMessage(p, "You need a runite crossbow to enter Armadyl's Eyrie.");
			}
		break;

		/**
		 * Bank Access.
		 */
                case 28089:
                case 2213:
		case 2693:
		case 4483:
		case 11402:
		case 36786:
		case 27663:
		case 26972:
		case 11758:
		case 19230:
		case 25808:
		case 34752:

                case 35647:

			p.openBank();
		break;

		/**
		 * Zone Restricted Bank Access.
		 */
		case 2827:
			if (p.clickX == 2513 && p.clickY == 3040) {//Donor Zone Bank
				if (p.rights > 0) {
				p.openBank();
				} else {
				p.getActionSender().sendMessage(p, "You need to be a Donator to use this feature.");
				}
			}
			if (p.clickX == 2549 && p.clickY == 3012) {//Mod Zone Bank
				if (p.rights > 0 || p.Donator > 0) {
				p.openBank();
				} else {
				p.getActionSender().sendMessage(p, "You need to be a Moderator to use this feature.");
				}
			}
			if (p.clickX == 2511 && p.clickY == 3024) {//Admin Zone 1
				if (p.rights == 2) {
				p.openBank();
				} else {
				p.getActionSender().sendMessage(p, "You need to be an Administrator to use this feature.");
				}
			}
			if (p.clickX == 3345 && p.clickY == 3508) {//Admin Zone 2
				if (p.rights == 2) {
				p.openBank();
				} else {
				p.getActionSender().sendMessage(p, "You need to be an Administrator to use this feature.");
				}
			}
		break;

		case 36776:
			p.setCoords(p.absX, p.absY, 2);
		break;

		case 36778:
			p.setCoords(p.absX, p.absY, 0);
		break;

		/**
		 * Mage arena
		 */


		case 733:
			if (p.clickX == 3095 && p.clickY == 3957) {
				if (p.absX == 3095 && p.absY == 3957)
					p.setCoords(3094, 3957, 0);
				else if (p.absX == 3094 && p.absY == 3957)
					p.setCoords(3095, 3957, 0);
			}
			if (p.clickX == 3092 && p.clickY == 3957) {
				if (p.absX == 3093 && p.absY == 3957)
					p.setCoords(3092, 3957, 0);
				else if (p.absX == 3092 && p.absY == 3957)
					p.setCoords(3093, 3957, 0);
			}
			if (p.clickX == 3105 && p.clickY == 3958) {
				if (p.absX == 3105 && p.absY == 3959)
					p.setCoords(3105, 3957, 0);
				else if (p.absX == 3105 && p.absY == 3957)
					p.setCoords(3105, 3959, 0);
			}
			if (p.clickX == 3106 && p.clickY == 3958) {
				if (p.absX == 3106 && p.absY == 3957)
					p.setCoords(3106, 3959, 0);
				else if (p.absX == 3106 && p.absY == 3959)
					p.setCoords(3106, 3957, 0);
			}
			if (p.clickX == 3158 && p.clickY == 3951) {
				if (p.absX == 3158 && p.absY == 3952)
					p.setCoords(3158, 3950, 0);
				else if (p.absX == 3158 && p.absY == 3950)
					p.setCoords(3158, 3952, 0);
			}
		break;

		case 1597:
			if (p.clickX == 2947 && p.clickY == 3904) {
				if (p.absX == 2947 && p.absY == 3903)
					p.setCoords(2947, 3904, 0);
				else if (p.absX == 2947 && p.absY == 3904)
					p.setCoords(2947, 3903, 0);
			}
			if (p.clickX == 3224 && p.clickY == 3904) {
				if (p.absX == 3224 && p.absY == 3903)
					p.setCoords(3224, 3904, 0);
				else if (p.absX == 3224 && p.absY == 3904)
					p.setCoords(3224, 3903, 0);
			}
			if (p.clickX == 3336 && p.clickY == 3896) {
				if (p.absX == 3336 && p.absY == 3896)
					p.setCoords(3336, 3895, 0);
				else if (p.absX == 3336 && p.absY == 3895)
					p.setCoords(3336, 3896, 0);
			}
			if (p.clickX == 3008 && p.clickY == 3850) {
				if (p.absX == 3007 && p.absY == 3850)
					p.setCoords(3008, 3850, 0);
				else if (p.absX == 3008 && p.absY == 3850)
					p.setCoords(3007, 3850, 0);
			}
		break;

        case 1765:
            if (p.absX == 3017 && p.absY == 3850) {
                p.setCoords(3069, 10257, 0);
            }
        break;

        case 32015:
            if (p.absX == 3069 && p.absY == 10257) {
                p.setCoords(3017, 3850, 0);
            }
        break;

		case 1596:
			if (p.clickX == 2948 && p.clickY == 3904) {
				if (p.absX == 2948 && p.absY == 3903)
					p.setCoords(2948, 3904, 0);
				else if (p.absX == 2948 && p.absY == 3904)
					p.setCoords(2948, 3903, 0);
			}
			if (p.clickX == 3225 && p.clickY == 3904) {
				if (p.absX == 3225 && p.absY == 3903)
					p.setCoords(3225, 3904, 0);
				else if (p.absX == 3225 && p.absY == 3904)
					p.setCoords(3225, 3903, 0);
			}
			if (p.clickX == 3337 && p.clickY == 3896) {
				if (p.absX == 3337 && p.absY == 3896)
					p.setCoords(3337, 3895, 0);
				else if (p.absX == 3337 && p.absY == 3895)
					p.setCoords(3337, 3896, 0);
			}
			if (p.clickX == 3008 && p.clickY == 3849) {
				if (p.absX == 3007 && p.absY == 3849)
					p.setCoords(3008, 3849, 0);
				else if (p.absX == 3008 && p.absY == 3849)
					p.setCoords(3007, 3849, 0);
			}
		break;

		/**
		 * God wars
		 */
		case 26425:
			/**
			 * Bandos door
			 */
			if (!p.graardorChamber()) {
				p.setCoords(2865, 5354, 2);
			} else {
				p.setCoords(2863, 5354, 2);
			}
		break;

		case 26426:
			if (!p.armadylChamber()) {
				if (p.godWarsKills[0] >= 2)
					p.setCoords(2839, 5296, 2);
				else
					p.getActionSender().sendMessage(p, "You atleast need 2 Armadyl kills to enter this Chamber.");
			} else {
				p.setCoords(2839, 5295, 2);
			}
		break;

		case 28140:
			p.setCoords(3266 + Misc.random(2), 3692 + Misc.random(2), 0);
		break;

		case 23271:
			if (p.freezeDelay > 0) break;
			p.crossDitch();
		break;

		case 26289:
		case 26288:
		case 24343:
		case 27661:
			if (p.skillLvl[5] != p.getLevelForXP(5)) {
				p.requestAnim(645, 0);
				p.skillLvl[5] = p.getLevelForXP(5);
				p.getActionSender().sendMessage(p, "You restore your prayer points.");
			} else {
				p.getActionSender().sendMessage(p, "Your prayer points are still full.");
			}
			p.getActionSender().setSkillLvl(p, 5);
			p.appearanceUpdateReq = true;
			p.updateReq = true;
		break;

        case 28121:
		if (Engine.playerItems.invItemCount(p, 995) >= 1) {
		p.getActionSender().sendMessage(p, "There is no need to bring coins into Bounty Hunter");
		return;
                }
		if (p.leftBhTimer > 0)
        {
                     p.getActionSender().sendMessage(p, "You must wait another " +p.leftBhTimer+" seconds before re-entering");
                     return;
        }
    if (p.rights <= 1 && p.combatLevel <= 106 || p.rights <= 1 && p.combatLevel >= 139){
                p.getActionSender().sendMessage(p, "You must be between 107-138 to enter.");
                return;
            }

		Engine.BountyHunter.enter(p, 0);
        Engine.BountyHunter.teleEnter(p, 0);
        p.bhLeave1 = 60;
        Engine.bountyhunter.getBountyHeadIcon(p);

		break;
     case 28120:
		if (Engine.playerItems.invItemCount(p, 995) >= 1) {
		p.getActionSender().sendMessage(p, "There is no need to bring coins into Bounty Hunter");
		}
		if (p.leftBhTimer > 0)
        {
                     p.getActionSender().sendMessage(p, "You must wait another " +p.leftBhTimer+" seconds before re-entering");
                     return;
        }
    if (p.rights <= 1 && p.combatLevel <= 49 || p.rights <= 1 && p.combatLevel >= 108){
                p.getActionSender().sendMessage(p, "You must be between 50-107 to enter.");
                return;
            }

		Engine.BountyHunter.enter(p, 0);
        Engine.BountyHunter.teleEnter(p, 0);
        p.bhLeave1 = 60;
        Engine.bountyhunter.getBountyHeadIcon(p);

		break;
	    case 28122:
		if (p.skillLvl[3] == 0) {
		return;
}
            if (!p.DoorArea(p.absX, p.absY)) {
            return;
            }
            if (p.bhPickup > 0)
        {
                     p.getActionSender().sendMessage(p, "You are currently penned and cannot leave.");
                     return;
        }
		int ii = p.heightLevel;
		if(ii != 0) {
		    Engine.BountyHunter.exit(p, 1);
            p.InBounty = 0;
		} else if(ii == 0) {
		    Engine.BountyHunter.exit(p, 2);
            p.InBounty = 0;
		} else if(ii == 0) {
		    Engine.BountyHunter.exit(p, 3);
            p.InBounty = 0;
		}
        p.getActionSender().setConfig2(p, 300, 1000);
        p.skillLvl[0] = p.getLevelForXP(0);
		p.getActionSender().setSkillLvl(p, 0);
        p.skillLvl[1] = p.getLevelForXP(1);
        p.resetPrayer();
		p.getActionSender().setSkillLvl(p, 1);
        p.skillLvl[2] = p.getLevelForXP(2);
		p.getActionSender().setSkillLvl(p, 2);
        p.skillLvl[3] = p.getLevelForXP(3);
		p.getActionSender().setSkillLvl(p, 3);
        p.skillLvl[4] = p.getLevelForXP(4);
		p.getActionSender().setSkillLvl(p, 4);
        p.skillLvl[5] = p.getLevelForXP(5);
		p.getActionSender().setSkillLvl(p, 5);
        p.skillLvl[6] = p.getLevelForXP(6);
		p.getActionSender().setSkillLvl(p, 6);
        p.appearanceUpdateReq = true;
        p.updateReq = true;
        p.runEnergy = 100;
        p.leftBhTimer = 30;
        p.headIconSkull = -1;
        p.specAmount = 1000;
		break;


        default:
            Misc.println("[" + p.username + "] Unhandled object 1: " + p.clickId);
            break;
        }
    }

    private int objectSize(int id) {
        switch (id) {
        default:
            return 1;
        }
    }
}