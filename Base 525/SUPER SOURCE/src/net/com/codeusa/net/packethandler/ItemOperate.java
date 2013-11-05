/*
 * Class ItemOperate
 *
 * Version 1.2
 *
 * Thursday, August 21, 2008
 *
 * Created by Palidino76 <-- Palis Sucks
 */

package net.com.codeusa.net.packethandler;

import net.com.codeusa.model.Player;
import net.com.codeusa.util.Misc;

public class ItemOperate implements Packet {
    /**
     * Handles operating equipped items.
     * @param p The Player which the frame should be handled for.
     * @param packetId The packet id this belongs to.
     * @param packetSize The amount of bytes being recieved for this packet.
     */
    public void handlePacket(Player p, int packetId, int packetSize) {
        if (p == null || p.stream == null) {
            return;
        }
        int junk = p.stream.readDWord();
        int itemId = p.stream.readUnsignedWordA();
        int itemSlot = p.stream.readUnsignedWordBigEndianA();
        if (itemSlot < 0 || itemSlot >= p.equipment.length || p.equipment[itemSlot] != itemId) {
            return;
        }
        switch (itemId) {
	case 11283: p.DFSSpecial = true; break;
            case 11694:
                if (p.attackedBy != null) {
            p.getActionSender().sendMessage(p, "You cannot use this command while in combat retard.");
            return;
            }
        p.requestGFX(1222, 0);
	p.requestAnim(7074, 0);        
        break;
            case 14484:
                p.requestAnim(10961, 0);
                p.requestGFX(1950, 0);
                break;
        default:
            Misc.println("[" + p.username + "] Operate item: " + itemId);
            break;
        }
    }
}
