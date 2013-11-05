/*
 * Class DropItem
 *
 * Version 1.0
 *
 * Thursday, August 21, 2008
 *
 * Created by Palidino76
 */

package net.com.codeusa.net.packethandler;

import net.com.codeusa.model.*;
import net.com.codeusa.model.games.*;
import net.com.codeusa.model.followers.*;
import net.com.codeusa.*;

public class DropItem implements Packet {
    /**
     * Handles dropping items in your inventory.
     * @param p The Player which the frame should be handled for.
     * @param packetId The packet id this belongs to.
     * @param packetSize The amount of bytes being recieved for this packet.
     */
    public void handlePacket(Player p, int packetId, int packetSize) {
        if (p == null || p.stream == null || p.isDead) {
            return;
        }
        int junk = p.stream.readDWord();
        int itemSlot = p.stream.readUnsignedWordBigEndianA();
        int itemId = p.stream.readUnsignedWord();
        if (itemSlot < 0 || itemSlot >= p.items.length || p.items[itemSlot] != itemId) {
            return;
        }
        if (p.attackedBy != null) {
            p.getActionSender().sendMessage(p, "You cannot drop items while in combat");
            return;
        }
	if (itemId == 702) {
		p.append1Hit(25, 2);
		Engine.playerItems.deleteItem(p, itemId, itemSlot, p.itemsN[itemSlot]);
		return;
	}
	if (itemId == 13290) {
		p.getActionSender().sendMessage(p, "Your Vesta's longsword shatters as it hits the ground.");
		Engine.playerItems.deleteItem(p, itemId, itemSlot, p.itemsN[itemSlot]);
		p.degrade = 6000;
		p.degrades = false;
		return;
	}
	p.getActionSender().addSoundEffect(p, 2739, 1, 0, 0);
	Followers follow = new Followers(p);
	WarriorGuild wGuild = new WarriorGuild(p);
	boolean failedDropping = false;
	    switch (itemId) {

		case 1163:
		case 1127:
		case 1079:
			if (p.absX == 2857 && p.absY == 3537 && p.heightLevel == 0 || p.absX == 2851 && p.absY == 3537 && p.heightLevel == 0) {
				p.warriorArmour = "Rune";
				if (wGuild.hasCorrectEquipment()) {
					p.requestAnim(827, 0);
					wGuild.removeWarriorEquipment();
					Server.engine.newNPC(4284, p.absX, p.absY - 1, p.heightLevel, 0, 0, 0, 0, false, p.playerId);
				} else {
					p.getActionSender().sendMessage(p, "You need a full Rune armour to go further with this process.");
					failedDropping = true;
				}
				p.warriorArmour = "";
			}
		break;



		case 6570:
		case 10566:
			p.getActionSender().sendMessage(p, "The fire cape vanished into the ground.");
		break;
	    }
	    if (itemId != 702 && !wGuild.animatedRoom() && itemId != 12047 && itemId != 12790 && itemId != 12063 && itemId != 12025 && !failedDropping && itemId != 6570 && itemId != 10566)
            	//Engine.items.createGroundItem(itemId, p.itemsN[itemSlot], p.absX, p.absY, p.heightLevel, p.username);
	    if (!failedDropping)
            	Engine.playerItems.deleteItem(p, itemId, itemSlot, p.itemsN[itemSlot]);
	    failedDropping = false;
        }
    

    boolean nearEntranceKQ(Player player) {
	return player.absX >= 3508 && player.absX <= 3511 && player.absY >= 9496 && player.absY <= 9499 && player.heightLevel == 2;
    }
}
