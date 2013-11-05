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

import net.com.codeusa.*;
import net.com.codeusa.model.misc.*;
import net.com.codeusa.model.Player;
import net.com.codeusa.util.Misc;
import net.com.codeusa.model.items.PlayerItems;

public class ItemOption2 implements Packet {
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
	PlayerItems pi = new PlayerItems();
        if (itemSlot < 0 || itemId < 0) {
            return;
        }
	System.out.println(interfaceId);
	System.out.println(itemId);
    }
}
