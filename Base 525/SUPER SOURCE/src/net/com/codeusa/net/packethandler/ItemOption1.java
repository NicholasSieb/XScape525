/*
 * Class ItemOption1
 *
 * Version 1.0
 *
 * Thursday, August 21, 2008
 *
 * Created by Palidino76
 */

package net.com.codeusa.net.packethandler;

import net.com.codeusa.Engine;
import net.com.codeusa.*;
import net.com.codeusa.model.misc.*;
import net.com.codeusa.model.Player;
import net.com.codeusa.model.misc.PlayerMethods;
import net.com.codeusa.util.Misc;

public class ItemOption1 implements Packet {
    /**
     * Handles first item options, excluding things such as eating and drinking.
     * @param p The Player which the frame should be handled for.
     * @param packetId The packet id this belongs to.
     * @param packetSize The amount of bytes being recieved for this packet.
     */
    public void handlePacket(Player p, int packetId, int packetSize) {
        if (p == null || p.stream == null) {
            return;
        }
        int itemSlot = p.stream.readUnsignedWordBigEndianA();
        int interfaceId = p.stream.readUnsignedWord();
        int junk = p.stream.readUnsignedWord();
        int itemId = p.stream.readUnsignedWord();
        if (itemSlot < 0 || itemId < 0) {
            return;
        }
        switch (interfaceId) {
case 12175: //Spirt Wolf
            if (!p.summonedFamiliar) {
                p.summonDrainDelay = 12;
                p.getActionSender().setTab(p, 80, 663);
                p.getActionSender().animateInterfaceId(p, 9850, 663, 3);
                p.summonedFamiliar = true;
                p.getActionSender().sendMessage(p, "You summon a Spirit wolf.");
                Server.engine.newNPC(6829, p.absX-2, p.absY, p.heightLevel, 0, 0, 0, 0, false, p.playerId);
                Engine.playerItems.deleteItem(p, 12047, itemSlot, 1); // Summoning: Spirt Wolf
            } else {
                p.getActionSender().sendMessage(p, "You cannot summon another familiar.");
            }
        break;
        
        case 12171: //Dead Fowl
            if (!p.summonedFamiliar) {
                p.summonDrainDelay = 12;
                p.getActionSender().setTab(p, 80, 663);
                p.summonedFamiliar = true;
                Engine.playerItems.deleteItem(p, 12043, itemSlot, 1); // Summoning: Spirt Wolf
                p.getActionSender().sendMessage(p, "You summon a Dead Fowl.");
                p.getActionSender().setTab(p, 80, 663);
                p.getActionSender().animateInterfaceId(p, 9850, 663, 3);
                p.getActionSender().setNPCId(p, 6825, 663, 3);
                Server.engine.newNPC(6825, p.absX-2, p.absY, p.heightLevel, 0, 0, 0, 0, false, p.playerId);
            } else {
                p.getActionSender().sendMessage(p, "You cannot summon another familiar.");
            }
        break;
        
        
            case 12187: //Spirit Spider
            if (!p.summonedFamiliar) {
                p.summonDrainDelay = 12;
                p.getActionSender().setTab(p, 80, 663);
                p.summonedFamiliar = true;
                Engine.playerItems.deleteItem(p, 12059, itemSlot, 1); // Summoning: Spirt Wolf
                p.getActionSender().sendMessage(p, "You summon a Spirit Spider.");
                p.getActionSender().setTab(p, 80, 663);
                p.getActionSender().animateInterfaceId(p, 9850, 663, 3);
                p.getActionSender().setNPCId(p, 6841, 663, 3);
                Server.engine.newNPC(6841, p.absX-2, p.absY, p.heightLevel, 0, 0, 0, 0, false, p.playerId);
            } else {
                p.getActionSender().sendMessage(p, "You cannot summon another familiar.");
            }
        break;
        
            case 11891: //Thorny Snail
            if (!p.summonedFamiliar) {
                p.summonDrainDelay = 12;
                p.getActionSender().setTab(p, 80, 663);
                p.summonedFamiliar = true;
                Engine.playerItems.deleteItem(p, 12019, itemSlot, 1); // Summoning: Spirt Wolf
                p.getActionSender().sendMessage(p, "You summon a Thorny Snail.");
                p.getActionSender().setTab(p, 80, 663);
                p.getActionSender().animateInterfaceId(p, 9850, 663, 3);
                p.getActionSender().setNPCId(p, 6806, 663, 3);
                Server.engine.newNPC(6806, p.absX-2, p.absY, p.heightLevel, 0, 0, 0, 0, false, p.playerId);
            } else {
                p.getActionSender().sendMessage(p, "You cannot summon another familiar.");
            }
        break;
        
            case 12936: //Spirit Tz-Kih
            if (!p.summonedFamiliar) {
                p.summonDrainDelay = 12;
                p.getActionSender().setTab(p, 80, 663);
                p.summonedFamiliar = true;
                Engine.playerItems.deleteItem(p, 12808, itemSlot, 1); // Summoning: Spirt Wolf
                p.getActionSender().sendMessage(p, "You summon a Spirit Tz-Kih.");
                p.getActionSender().setTab(p, 80, 663);
                p.getActionSender().animateInterfaceId(p, 9850, 663, 3);
                p.getActionSender().setNPCId(p, 7361, 663, 3);
                Server.engine.newNPC(7361, p.absX-2, p.absY, p.heightLevel, 0, 0, 0, 0, false, p.playerId);
            } else {
                p.getActionSender().sendMessage(p, "You cannot summon another familiar.");
            }
        break;
        
            case 12201: //Bronze Minotour
            if (!p.summonedFamiliar) {
                p.summonDrainDelay = 12;
                p.getActionSender().setTab(p, 80, 663);
                p.summonedFamiliar = true;
                Engine.playerItems.deleteItem(p, 12073, itemSlot, 1); // Summoning: Spirt Wolf
                p.getActionSender().sendMessage(p, "You summon a Spirit Tz-Kih.");
                p.getActionSender().setTab(p, 80, 663);
                p.getActionSender().animateInterfaceId(p, 9850, 663, 3);
                p.getActionSender().setNPCId(p, 6853, 663, 3);
                Server.engine.newNPC(6853, p.absX-2, p.absY, p.heightLevel, 0, 0, 0, 0, false, p.playerId);
            } else {
                p.getActionSender().sendMessage(p, "You cannot summon another familiar.");
            }
        break;
        
            case 12203: //Iron Minotour
            if (!p.summonedFamiliar) {
                p.summonDrainDelay = 12;
                p.getActionSender().setTab(p, 80, 663);
                p.summonedFamiliar = true;
                Engine.playerItems.deleteItem(p, 12075, itemSlot, 1); // Summoning: Spirt Wolf
                p.getActionSender().sendMessage(p, "You summon a Spirit Tz-Kih.");
                p.getActionSender().setTab(p, 80, 663);
                p.getActionSender().animateInterfaceId(p, 9850, 663, 3);
                p.getActionSender().setNPCId(p, 6855, 663, 3);
                Server.engine.newNPC(6855, p.absX-2, p.absY, p.heightLevel, 0, 0, 0, 0, false, p.playerId);
            } else {
                p.getActionSender().sendMessage(p, "You cannot summon another familiar.");
            }
        break;
        
            case 12205: //Steel Minotour
            if (!p.summonedFamiliar) {
                p.summonDrainDelay = 12;
                p.getActionSender().setTab(p, 80, 663);
                p.summonedFamiliar = true;
                Engine.playerItems.deleteItem(p, 12077, itemSlot, 1); // Summoning: Spirt Wolf
                p.getActionSender().sendMessage(p, "You summon a Spirit Tz-Kih.");
                p.getActionSender().setTab(p, 80, 663);
                p.getActionSender().animateInterfaceId(p, 9850, 663, 3);
                p.getActionSender().setNPCId(p, 6857, 663, 3);
                Server.engine.newNPC(6857, p.absX-2, p.absY, p.heightLevel, 0, 0, 0, 0, false, p.playerId);
            } else {
                p.getActionSender().sendMessage(p, "You cannot summon another familiar.");
            }
        break;
        
            case 12207: //Mithril Minotour
            if (!p.summonedFamiliar) {
                p.summonDrainDelay = 12;
                p.getActionSender().setTab(p, 80, 663);
                p.summonedFamiliar = true;
                Engine.playerItems.deleteItem(p, 12079, itemSlot, 1); // Summoning: Spirt Wolf
                p.getActionSender().sendMessage(p, "You summon a Spirit Tz-Kih.");
                p.getActionSender().setTab(p, 80, 663);
                p.getActionSender().animateInterfaceId(p, 9850, 663, 3);
                p.getActionSender().setNPCId(p, 6859, 663, 3);
                Server.engine.newNPC(6859, p.absX-2, p.absY, p.heightLevel, 0, 0, 0, 0, false, p.playerId);
            } else {
                p.getActionSender().sendMessage(p, "You cannot summon another familiar.");
            }
        break;
        
            case 12209: //Adamant Minotour
            if (!p.summonedFamiliar) {
                p.summonDrainDelay = 12;
                p.getActionSender().setTab(p, 80, 663);
                p.summonedFamiliar = true;
                Engine.playerItems.deleteItem(p, 12081, itemSlot, 1); // Summoning: Spirt Wolf
                p.getActionSender().sendMessage(p, "You summon a Spirit Tz-Kih.");
                p.getActionSender().setTab(p, 80, 663);
                p.getActionSender().animateInterfaceId(p, 9850, 663, 3);
                p.getActionSender().setNPCId(p, 6861, 663, 3);
                Server.engine.newNPC(6861, p.absX-2, p.absY, p.heightLevel, 0, 0, 0, 0, false, p.playerId);
            } else {
                p.getActionSender().sendMessage(p, "You cannot summon another familiar.");
            }
        break;
        
            case 12930: //Fire Titan
            if (!p.summonedFamiliar) {
                p.summonDrainDelay = 12;
                p.getActionSender().setTab(p, 80, 663);
                p.summonedFamiliar = true;
                Engine.playerItems.deleteItem(p, 12802, itemSlot, 1); // Summoning: Spirt Wolf
                p.getActionSender().sendMessage(p, "You summon a Spirit Tz-Kih.");
                p.getActionSender().setTab(p, 80, 663);
                p.getActionSender().animateInterfaceId(p, 9850, 663, 3);
                p.getActionSender().setNPCId(p, 7355, 663, 3);
                Server.engine.newNPC(7335, p.absX-2, p.absY, p.heightLevel, 0, 0, 0, 0, false, p.playerId);
            } else {
                p.getActionSender().sendMessage(p, "You cannot summon another familiar.");
            }
        break;
        
            case 12934: //Ice Titan
            if (!p.summonedFamiliar) {
                p.summonDrainDelay = 12;
                p.getActionSender().setTab(p, 80, 663);
                p.summonedFamiliar = true;
                Engine.playerItems.deleteItem(p, 12806, itemSlot, 1); // Summoning: Spirt Wolf
                p.getActionSender().sendMessage(p, "You summon a Spirit Tz-Kih.");
                p.getActionSender().setTab(p, 80, 663);
                p.getActionSender().animateInterfaceId(p, 9850, 663, 3);
                p.getActionSender().setNPCId(p, 7359, 663, 3);
                Server.engine.newNPC(7359, p.absX-2, p.absY, p.heightLevel, 0, 0, 0, 0, false, p.playerId);
            } else {
                p.getActionSender().sendMessage(p, "You cannot summon another familiar.");
            }
        break;
        
            case 12660: //Lava Titan
            if (!p.summonedFamiliar) {
                p.summonDrainDelay = 12;
                p.getActionSender().setTab(p, 80, 663);
                p.summonedFamiliar = true;
                Engine.playerItems.deleteItem(p, 12788, itemSlot, 1); // Summoning: Spirt Wolf
                p.getActionSender().sendMessage(p, "You summon a Spirit Tz-Kih.");
                p.getActionSender().setTab(p, 80, 663);
                p.getActionSender().animateInterfaceId(p, 9850, 663, 3);
                p.getActionSender().setNPCId(p, 7341, 663, 3);
                Server.engine.newNPC(7341, p.absX-2, p.absY, p.heightLevel, 0, 0, 0, 0, false, p.playerId);
            } else {
                p.getActionSender().sendMessage(p, "You cannot summon another familiar.");
            }
        break;
        
            case 12648: //Swamp Titan
            if (!p.summonedFamiliar) {
                p.summonDrainDelay = 12;
                p.getActionSender().setTab(p, 80, 663);
                p.summonedFamiliar = true;
                Engine.playerItems.deleteItem(p, 12776, itemSlot, 1); // Summoning: Spirt Wolf
                p.getActionSender().sendMessage(p, "You summon a Spirit Tz-Kih.");
                p.getActionSender().setTab(p, 80, 663);
                p.getActionSender().animateInterfaceId(p, 9850, 663, 3);
                p.getActionSender().setNPCId(p, 7329, 663, 3);
                Server.engine.newNPC(7329, p.absX-2, p.absY, p.heightLevel, 0, 0, 0, 0, false, p.playerId);
            } else {
                p.getActionSender().sendMessage(p, "You cannot summon another familiar.");
            }
        break;
        
            case 12658: //Geyser Titan
            if (!p.summonedFamiliar) {
                p.summonDrainDelay = 12;
                p.getActionSender().setTab(p, 80, 663);
                p.summonedFamiliar = true;
                Engine.playerItems.deleteItem(p, 12786, itemSlot, 1); // Summoning: Spirt Wolf
                p.getActionSender().sendMessage(p, "You summon a Spirit Tz-Kih.");
                p.getActionSender().setTab(p, 80, 663);
                p.getActionSender().animateInterfaceId(p, 9850, 663, 3);
                p.getActionSender().setNPCId(p, 7339, 663, 3);
                Server.engine.newNPC(7339, p.absX-2, p.absY, p.heightLevel, 0, 0, 0, 0, false, p.playerId);
            } else {
                p.getActionSender().sendMessage(p, "You cannot summon another familiar.");
            }
        break;
        
            case 12668: //Abyssal Titan
            if (!p.summonedFamiliar) {
                p.summonDrainDelay = 12;
                p.getActionSender().setTab(p, 80, 663);
                p.summonedFamiliar = true;
                Engine.playerItems.deleteItem(p, 12796, itemSlot, 1); // Summoning: Spirt Wolf
                p.getActionSender().sendMessage(p, "You summon a Spirit Tz-Kih.");
                p.getActionSender().setTab(p, 80, 663);
                p.getActionSender().animateInterfaceId(p, 9850, 663, 3);
                p.getActionSender().setNPCId(p, 7349, 663, 3);
                Server.engine.newNPC(7349, p.absX-2, p.absY, p.heightLevel, 0, 0, 0, 0, false, p.playerId);
            } else {
                p.getActionSender().sendMessage(p, "You cannot summon another familiar.");
            }
        break;
        
            case 12950: //Iron Titan
            if (!p.summonedFamiliar) {
                p.summonDrainDelay = 12;
                p.getActionSender().setTab(p, 80, 663);
                p.summonedFamiliar = true;
                Engine.playerItems.deleteItem(p, 12822, itemSlot, 1); // Summoning: Spirt Wolf
                p.getActionSender().sendMessage(p, "You summon a Spirit Tz-Kih.");
                p.getActionSender().setTab(p, 80, 663);
                p.getActionSender().animateInterfaceId(p, 9850, 663, 3);
                p.getActionSender().setNPCId(p, 7375, 663, 3);
                Server.engine.newNPC(7375, p.absX-2, p.absY, p.heightLevel, 0, 0, 0, 0, false, p.playerId);
            } else {
                p.getActionSender().sendMessage(p, "You cannot summon another familiar.");
            }
        break;
        
            case 12662: //Steel Titan
            if (!p.summonedFamiliar) {
                p.summonDrainDelay = 12;
                p.getActionSender().setTab(p, 80, 663);
                p.summonedFamiliar = true;
                Engine.playerItems.deleteItem(p, 12790, itemSlot, 1); // Summoning: Spirt Wolf
                p.getActionSender().sendMessage(p, "You summon a Spirit Tz-Kih.");
                p.getActionSender().setTab(p, 80, 663);
                p.getActionSender().animateInterfaceId(p, 9850, 663, 3);
                p.getActionSender().setNPCId(p, 7343, 663, 3);
                Server.engine.newNPC(7343, p.absX-2, p.absY, p.heightLevel, 0, 0, 0, 0, false, p.playerId);
            } else {
                p.getActionSender().sendMessage(p, "You cannot summon another familiar.");
            }
        break;
        case 387: // Unequip item.
            if (itemSlot < p.equipment.length && p.equipment[itemSlot] == itemId) {
                if (!Engine.playerItems.addItem(p, p.equipment[itemSlot], p.equipmentN[itemSlot])) {
                    break;
                }
		if (itemId == 4031) {
			p.convertPlayerToNpc(-1);
			p.updatePlayerAppearance(p.playerWeapon.getWalkEmote(p.equipment[3]), p.playerWeapon.getStandEmote(p.equipment[3]),
				p.playerWeapon.getRunEmote(p.equipment[3]));
			p.updatePlayer(true);
		}
		
                if (itemId == 7449) {
			if (p.wildernessZone(p.absX, p.absY)) {
				p.getActionSender().setPlayerOption(p, "Attack", 1, true);
            		} else {
				p.getActionSender().setPlayerOption(p, "Null", 1, true);
			}
		}
		if (itemId == 4675 || itemId == 9084) {
			p.getActionSender().setTab(p, 79, 192);

		}
		if (itemId == 4037 || itemId == 4039) {
			Server.engine.items.createGroundItem(itemId, p.itemsN[itemSlot], p.absX, p.absY, p.heightLevel, "");
			Server.engine.playerItems.deleteItem(p, itemId, Server.engine.playerItems.getItemSlot(p, itemId), 1);
		}
		if (itemId == 3053) {
			p.getActionSender().sendMessage(p, "You cannot un-equip your battlestaff while during the mage arena minigame.");
			return;
		}
		if (p.usingAutoCast) {
			p.castAuto = false;
			p.usingAutoCast = false;
			p.autoCastDelay = 0;
		}
                p.equipment[itemSlot] = -1;
                p.equipmentN[itemSlot] = 0;
	    	PlayerMethods pm = new PlayerMethods(p);
	    	pm.setAttackPlayer(false);
                p.getActionSender().setItems(p, 387, 28, 94, p.equipment, p.equipmentN);
                p.playerWeapon.setWeapon();
                p.appearanceUpdateReq = true;
                p.updateReq = true;
                p.calculateEquipmentBonus();
            }
            break;
        default:
            Misc.println("[" + p.username + "] Item option 1: " + interfaceId);
            break;
        }
    }
}
