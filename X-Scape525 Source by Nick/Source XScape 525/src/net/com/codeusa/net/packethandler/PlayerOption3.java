/*
 * Class PlayerOption3
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

public class PlayerOption3 implements Packet {
    /**
     * Handles the third player option.
     * @param p The Player which the frame should be handled for.
     * @param packetId The packet id this belongs to.
     * @param packetSize The amount of bytes being recieved for this packet.
     */
    public void handlePacket(Player p, int packetId, int packetSize) {
        if (p == null || p.stream == null) {
            return;
        }
        p.duelFriend = p.stream.readUnsignedWordBigEndianA();
	Player player = Server.engine.players[p.duelFriend];
	if (p == null || player == null) {
		p.duelFriend = 0;
		return;
	}
	if (p.inDuelArena()) {
		p.getActionSender().sendMessage(p, "Sending duel request..");
		player.getActionSender().sendMessage(player, p.username + ":duelfriend:");
		player.duelFriend = p.playerId;
	}
    }
}
