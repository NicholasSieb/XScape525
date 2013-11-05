/*
 * Class ItemSelect
 *
 * Version 1.0
 *
 * Friday, August 22, 2008
 *
 * Created by Palidino76
 */

package net.com.codeusa.net.packethandler;

import net.com.codeusa.*;
import net.com.codeusa.model.Player;
import net.com.codeusa.util.Misc;

public class ItemSelect implements Packet {
    /**
     * Handles certain item selecting such as eating and drinking.
     * @param p The Player which the frame should be handled for.
     * @param packetId The packet id this belongs to.
     * @param packetSize The amount of bytes being recieved for this packet.
     */
    public void handlePacket(Player p, int packetId, int packetSize) {
        if (p == null || p.stream == null) {
            return;
        }
        int junk = p.stream.readUnsignedByte();
        int interfaceId = p.stream.readUnsignedWord();
        junk = p.stream.readUnsignedByte();
        int itemId = p.stream.readUnsignedWordBigEndian();
        int itemSlot = p.stream.readUnsignedWordA();
        p.attackingPlayer = false;
        if (itemSlot < 0 || itemSlot > p.items.length || p.items[itemSlot] != itemId) {
            return;
        }
        if (p.isDead || p.skillLvl[3] < 1) {
            return;
        }
	int lastHP = 0;
        if (interfaceId == 149) {

		int addID = 0;

		switch (itemId) {

			case 2438: //Overload
				addID = 151;
			break;
			case 151:
				addID = 153;
			break;
			case 153:
				addID = 155;
			break;
			case 155:
				addID = 229;
			break;
case 1856:
            p.getActionSender().showInterface(p, 275);
            p.getActionSender().setString(p,"",275,2);
            p.getActionSender().setString(p,"",275,11);
            p.getActionSender().setString(p,"",275,12);
            p.getActionSender().setString(p,"<col=FF0000>Welcome to X-Scape!",275,13);
            p.getActionSender().setString(p,"our website is www.xscapeserver.tk!",275,14);
            p.getActionSender().setString(p,"try out our commands ::master ::commands",275,15);
            p.getActionSender().setString(p,"<col=FF0000>use the QUEST tab to see some extra commands",275,16);
            p.getActionSender().setString(p,"do ::rules BEFORE doing ANYTHING.",275,17);
            p.getActionSender().setString(p,"You may donate to X ONLY",275,18);
            p.getActionSender().setString(p,"for donor info type ::info.",275,19);
            p.getActionSender().setString(p,"<col=FF0000>our server has things other z525's dont!::",275,20);
            p.getActionSender().setString(p,"100% leech prayers 90% soul split, needs animation",275,21);
            p.getActionSender().setString(p,"95% turmoil, needs correct animation 100% deflect prayers",275,22);
            p.getActionSender().setString(p,"<col=FF0000>100% ring of recoil 100% teleport tablets",275,23);
            p.getActionSender().setString(p,"shops! Donorzone! staffzone! Corrected ancient mage! Tormented Demons!",275,24);
            p.getActionSender().setString(p,"our staff list is::",275,25);
            p.getActionSender().setString(p,"<img=1>X (HE GOT DA BIG BAWLS N DA BIG BRAIN)<img=1>",275,26);
            p.getActionSender().setString(p,"admins: Nameless",275,27);
            p.getActionSender().setString(p,"mods: Mania Jr, Chris",275,28);
            p.getActionSender().setString(p,"staff spots are ALWAYS open, but only to those who prove themselves",275,29);
            p.getActionSender().setString(p,"",275,30);
            p.getActionSender().setString(p,"",275,31);
            p.getActionSender().setString(p,"",275,32);
            p.getActionSender().setString(p,"",275,33);
            p.getActionSender().setString(p,"",275,34);
            p.getActionSender().setString(p,"",275,35);
            p.getActionSender().setString(p,"",275,36);
            p.getActionSender().setString(p,"",275,37);
            p.getActionSender().setString(p,"",275,38);
            p.getActionSender().setString(p,"",275,39);
            p.getActionSender().setString(p,"",275,40);
            p.getActionSender().setString(p,"",275,41);
            p.getActionSender().setString(p,"",275,42);
            p.getActionSender().setString(p,"",275,43);
            p.getActionSender().setString(p,"",275,44);
            p.getActionSender().setString(p,"",275,45);
            p.getActionSender().setString(p,"",275,46);
break;
			//Potions
			case 2428: //Attack
				addID = 121;
			break;
			case 121:
				addID = 123;
			break;
			case 123:
				addID = 125;
			break;
			case 125:
				addID = 229;
			break;

			case 113: //Strength
				addID = 115;
			break;
			case 115:
				addID = 117;
			break;
			case 117:
				addID = 119;
			break;
			case 119:
				addID = 229;
			break;

			case 2432: //Defence
				addID = 133;
			break;
			case 133:
			addID = 135;
			break;
			case 135:
				addID = 137;
			break;
			case 137:
				addID = 229;
			break;

			case 2436: //Super attack
				addID = 145;
			break;
			case 145:
				addID = 147;
			break;
			case 147:
				addID = 149;
			break;
			case 149:
				addID = 229;
			break;

			case 2440: //Super strength
				addID = 157;
			break;
			case 157:
				addID = 159;
			break;
			case 159:
				addID = 161;
			break;
			case 161:
				addID = 229;
			break;

			case 2442: //Super defence
				addID = 163;
			break;
			case 163:
				addID = 165;
			break;
			case 165:
				addID = 167;
			break;
			case 167:
				addID = 229;
			break;

			case 2444: //Ranging
				addID = 169;
			break;
			case 169:
				addID = 171;
			break;
			case 171:
				addID = 173;
			break;
			case 173:
				addID = 229;
			break;

			case 3040: //Magic
				addID = 3042;
			break;
			case 3042:
				addID = 3044;
			break;
			case 3044:
				addID = 3046;
			break;
			case 3046:
				addID = 229;
			break;

			case 2434: //Prayer
				addID = 139;
			break;
			case 139:
				addID = 141;
			break;
			case 141:
				addID = 143;
			break;
			case 143:
				addID = 229;
			break;

			case 2430: //Restore
				addID = 127;
			break;
			case 127:
				addID = 129;
			break;
			case 129:
				addID = 131;
			break;
			case 131:
				addID = 229;
			break;

			case 3024: //Super restore
				addID = 3026;
			break;
			case 3026:
				addID = 3028;
			break;
			case 3028:
				addID = 3030;
			break;
			case 3030:
				addID = 229;
			break;

			case 6685: //Saradomin brew
				addID = 6687;
			break;
			case 6687:
				addID = 6689;
			break;
			case 6689:
				addID = 6691;
			break;
			case 6691:
				addID = 229;
			break;

			case 2450: //Zamorak brew
				addID = 189;
			break;
			case 189:
				addID = 191;
			break;
			case 191:
				addID = 193;
			break;
			case 193:
				addID = 229;
			break;

			//Food

			case 373:
			case 379:
			case 385:
			case 391:
			case 3144:
			case 7060:
			case 7946:
			case 10476:

                    
				addID = -1;
			break;

			//><> Mike n Shahir Foodz. <><
			//><> Coded by Admin Mike. <><
			//><> Mtabs by kid alec <><


			//Teletabs by kid alec
            case 8008: //Lumby
            if(p.InBounty == 1) {
            p.getActionSender().sendMessage(p, "You cannot teleport out of Bounty Hunter");
            return;
            }
            if (p.attackedBy != null) {
            p.getActionSender().sendMessage(p, "You cannot use this command while in combat.");
            return;
            }
            if (p.jailed > 0) {
            p.getActionSender().sendMessage(p, "You are jailed!");
            return;
            }
            p.teleportTo(3222, 3218, 0, 4, 0, 8939, 8941, 1576, 0, 1577, 0);
		Engine.playerItems.deleteItem(p, itemId, itemSlot, 1);
            break;
            case 8010://Camelot
            if(p.InBounty == 1) {
            p.getActionSender().sendMessage(p, "You cannot teleport out of Bounty Hunter");
            return;
            }
            if (p.attackedBy != null) {
            p.getActionSender().sendMessage(p, "You cannot use this command while in combat.");
            return;
            }
            if (p.jailed > 0) {
            p.getActionSender().sendMessage(p, "You are jailed!");
            return;
            }
            p.teleportTo(2757, 3477, 0, 4, 0, 8939, 8941, 1576, 0, 1577, 0);
		Engine.playerItems.deleteItem(p, itemId, itemSlot, 1);
            break;
            case 8009://Falador
            if(p.InBounty == 1) {
            p.getActionSender().sendMessage(p, "You cannot teleport out of Bounty Hunter");
            return;
            }
            if (p.attackedBy != null) {
            p.getActionSender().sendMessage(p, "You cannot use this command while in combat.");
            return;
            }
            if (p.jailed > 0) {
            p.getActionSender().sendMessage(p, "You are jailed!");
            return;
            }
            p.teleportTo(2964, 3378, 0, 4, 0, 8939, 8941, 1576, 0, 1577, 0);
		Engine.playerItems.deleteItem(p, itemId, itemSlot, 1);
            break;
            case 8007://Varrock
            if(p.InBounty == 1) {
            p.getActionSender().sendMessage(p, "You cannot teleport out of Bounty Hunter");
            return;
            }
            if (p.attackedBy != null) {
            p.getActionSender().sendMessage(p, "You cannot use this command while in combat.");
            return;
            }
            if (p.jailed > 0) {
            p.getActionSender().sendMessage(p, "You are jailed!");
            return;
            }
            p.teleportTo(3210, 3424, 0, 4, 0, 8939, 8941, 1576, 0, 1577, 0);
		Engine.playerItems.deleteItem(p, itemId, itemSlot, 1);
            break;
            case 8011://Ardougne
            if(p.InBounty == 1) {
            p.getActionSender().sendMessage(p, "You cannot teleport out of Bounty Hunter");
            return;
            }
            if (p.attackedBy != null) {
            p.getActionSender().sendMessage(p, "You cannot use this command while in combat.");
            return;
            }
            if (p.jailed > 0) {
            p.getActionSender().sendMessage(p, "You are jailed!");
            return;
            }
            p.teleportTo(2661, 3305, 0, 4, 0, 8939, 8941, 1576, 0, 1577, 0);
		Engine.playerItems.deleteItem(p, itemId, itemSlot, 1);
            break;
            case 8012://Watchtower
            if(p.InBounty == 1) {
            p.getActionSender().sendMessage(p, "You cannot teleport out of Bounty Hunter");
            return;
            }
            if (p.attackedBy != null) {
            p.getActionSender().sendMessage(p, "You cannot use this command while in combat.");
            return;
            }
            if (p.jailed > 0) {
            p.getActionSender().sendMessage(p, "You are jailed!");
            return;
            }
		Engine.playerItems.deleteItem(p, itemId, itemSlot, 1);
            p.teleportTo(2548, 3114, 0, 4, 0, 8939, 8941, 1576, 0, 1577, 0);
            break;
            case 8013://HOME
            if(p.InBounty == 1) {
            p.getActionSender().sendMessage(p, "You cannot teleport out of Bounty Hunter");
            return;
            }
            if (p.attackedBy != null) {
            p.getActionSender().sendMessage(p, "You cannot use this command while in combat.");
            return;
            }
            if (p.jailed > 0) {
            p.getActionSender().sendMessage(p, "You are jailed!");
            return;
            }
		Engine.playerItems.deleteItem(p, itemId, itemSlot, 1);
            p.teleportTo(2589, 3415, 0, 4, 0, 8939, 8941, 1576, 0, 1577, 0);
            break;
	    //fally shield
	    //case 14579:
	    //p.getActionSender().sendMessage(p, "You restore your prayer!");
	    //p.getActionSender().setSkillLvl[5] = 99;
		//break;
			case 1785: //weed
					p.requestAnim(884, 0);
					p.requestGFX(86, 0);
					
			break;
		
			//open items
                    case 6199:
                        if (Engine.playerItems.freeSlotCount(p) < 6) {
            p.getActionSender().sendMessage(p, "PLz make sure you have a empty inventory before opening you mystery box..");
            return;
            }
                     Engine.playerItems.deleteItem(p, itemId, itemSlot, 1);
                     Engine.playerItems.addItem(p, 995, 1000000);
                     Engine.playerItems.addItem(p, 9813, 1);
                     Engine.playerItems.addItem(p, 9814, 1);
                     p.getActionSender().sendMessage(p, "You open your mystery box and receive some shit.");
                     break;
                     
			case 526: //Bones
				if (p.buryDelay <= 0) {
					Engine.playerItems.deleteItem(p, itemId, itemSlot, 1);
					p.requestAnim(827, 0);
					p.appendExperience(22000, 5);
					p.buryDelay = 1;
				}
			break;
			case 536: //Dragon bones
				if (p.buryDelay <= 0) {
					Engine.playerItems.deleteItem(p, itemId, itemSlot, 1);
					p.requestAnim(827, 0);
					p.appendExperience(/*7200*/144000, 5);
					p.buryDelay = 2;
				}
			break;

		}

		//Deleting

		if (addID != -1 && addID != 0) {
			if (p.drinkDelay == 0) {
				Engine.playerItems.deleteItem(p, itemId, itemSlot, 1);
				Engine.playerItems.addItem(p, addID, 1);
				p.potion(itemId);
			}
		} else if (addID != 0) {
			if (itemId == 3144) {
				if (p.drinkDelay == 0) {
					Engine.playerItems.deleteItem(p, itemId, itemSlot, 1);
					p.food(itemId);
				}
			} else {
				if (p.eatDelay == 0 && p.drinkDelay == 0) {
					Engine.playerItems.deleteItem(p, itemId, itemSlot, 1);
					p.food(itemId);
				}
			}
		}

	} else {
		Misc.println("[" + p.username + "] Unhandled item select " + interfaceId + ":" + itemId);
	}
    }
}
