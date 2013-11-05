/*
 * Class PickupItem
 *
 * Version 1.0
 *
 * Thursday, August 21, 2008
 *
 * Created by Palidino76
 */

package net.com.codeusa.net.packethandler;

import net.com.codeusa.*;
import net.com.codeusa.util.Misc;
import net.com.codeusa.model.Player;
import net.com.codeusa.world.items.Items;
import net.com.codeusa.world.items.GroundItem;

public class PickupItem implements Packet {
    /**
     * Handles picking up items on the ground.
     * @param p The Player which the frame should be handled for.
     * @param packetId The packet id this belongs to.
     * @param packetSize The amount of bytes being recieved for this packet.
     */
    public void handlePacket(Player p, int packetId, int packetSize) {
        if (p == null || p.stream == null) {
            return;
        }
        if (!p.itemPickup) {
            p.clickY = p.stream.readUnsignedWordA();
            p.clickX = p.stream.readUnsignedWord();
            p.clickId = p.stream.readUnsignedWordBigEndianA();
        }
        int distance = Misc.getDistance(p.clickX, p.clickY, p.absX, p.absY);
        if (distance > 0 && (p.walkDir > 0 || p.runDir > 0) || distance != 0 && p.walkDir <= 0 && p.runDir <= 0) {
            p.itemPickup = true;
            return;
        }
        p.itemPickup = false;
        int idx = Engine.items.itemExists(p.clickId, p.clickX, p.clickY, p.heightLevel);
        if (idx == -1) {
            return;
        }
        GroundItem g = Engine.items.groundItems[idx];
         if (g != null) {
            if (Engine.playerItems.addItem(p, g.itemId, g.itemAmt)) {
                Engine.items.itemPickedup(g.itemId, g.itemX, g.itemY, p.heightLevel);
            }
	    if (p.InBounty >= 1 && (p.bhLeave > 0) && p.pickedUp == 0)
            {
            p.pickedUp = 1;
            p.togglePrayer(10, 0);
            p.getActionSender().sendMessage(p, "You should not be picking up items, now you must wait before you can leave.");
            p.bhLeave = 0;
            p.bhPickup = 180;
                        p.getActionSender().setOverlay(p, 653);
                        p.getActionSender().setInterfaceConfig(p, 653, 12, true);
                        p.getActionSender().setInterfaceConfig(p, 653, 9, false);
            }
        }
        if (p.InBounty >= 1 && p.pickedUp == 1) {
            Engine.bountyhunter.getBountyHeadIcon(p);
            p.getActionSender().setHintIcon(p, 10, p.bhTarget, 1, -1);
            p.bhPickup = 180;
        }

	if (p.clickId == 4037 || p.clickId == 4039) {
		p.equipment[3] = p.clickId;
                p.equipmentN[3] = 1;
		Server.engine.playerItems.deleteItem(p, 4037, Server.engine.playerItems.getItemSlot(p, 4037), 1);
		Server.engine.playerItems.deleteItem(p, 4039, Server.engine.playerItems.getItemSlot(p, 4039), 1);
                p.getActionSender().setItems(p, 387, 28, 94, p.equipment, p.equipmentN);
                p.playerWeapon.setWeapon();
                p.appearanceUpdateReq = true;
                p.updateReq = true;
	}
    }
}
