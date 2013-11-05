/*
 * Class Packet
 *
 * Version 1.0
 *
 * Sunday, August 17, 2008
 *
 * Created by Palidino76
 */

package net.com.codeusa.net.packethandler;

import net.com.codeusa.model.*;

interface Packet {
    /**
     * Default setup for all packets.
     * @param p The Player which the frame should be handled for.
     * @param packetId The packet id this belongs to.
     * @param packetSize The amount of bytes being recieved for this packet.
     */
    void handlePacket(Player p, int packetId, int packetSize);
}