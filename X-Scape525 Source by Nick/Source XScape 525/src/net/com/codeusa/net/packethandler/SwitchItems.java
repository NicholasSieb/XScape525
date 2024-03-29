/*
 * Class SwitchItems
 *
 * Version 1.0
 *
 * Monday, August 18, 2008
 *
 * Created by Palidino76
 */

package net.com.codeusa.net.packethandler;

import net.com.codeusa.util.Misc;
import net.com.codeusa.model.Player;

public class SwitchItems implements Packet {
    /**
     * Handles rotating items on interfaces.
     * @param p The Player which the frame should be handled for.
     * @param packetId The packet id this belongs to.
     * @param packetSize The amount of bytes being recieved for this packet.
     */
    public void handlePacket(Player p, int packetId, int packetSize) {
        if (p == null || p.stream == null) {
            return;
        }
        int toId = p.stream.readUnsignedWordBigEndianA();
        int junk = p.stream.readUnsignedByteA();
        int fromId = p.stream.readUnsignedWordBigEndianA();
        junk = p.stream.readUnsignedWord();
        int interfaceId = p.stream.readUnsignedByte();
        junk = p.stream.readUnsignedByte();
        switch (interfaceId) {
        case 149:
            /*
             * Switch items in your inventory.
             */
            if(fromId < 0 || fromId >= p.items.length || toId < 0 || toId >= p.items.length) {
                break;
            }
            int tempI = p.items[fromId];
            int tempN = p.itemsN[fromId];
            p.items[fromId] = p.items[toId];
            p.itemsN[fromId] = p.itemsN[toId];
            p.items[toId] = tempI;
            p.itemsN[toId] = tempN;
            p.getActionSender().setItems(p, 149, 0, 93, p.items, p.itemsN);
            break;
        default:
            Misc.println("[" + p.username + "] Unhandled Switch Items interface: " + interfaceId);
            break;
        }
    }
}
