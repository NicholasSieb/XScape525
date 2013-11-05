/*
 * Class NPCAttack
 *
 * Version 1.0
 *
 * Friday, August 22, 2008
 *
 * Created by Palidino76
 */

package net.com.codeusa.net.packethandler;

import net.com.codeusa.model.combat.*;
import net.com.codeusa.npcs.*;
import net.com.codeusa.*;
import net.com.codeusa.model.Player;

public class NPCAttack implements Packet {
    /**
     * Handles selecting the attack option on NPCs.
     * @param p The Player which the frame should be handled for.
     * @param packetId The packet id this belongs to.
     * @param packetSize The amount of bytes being recieved for this packet.
     */
    public void handlePacket(Player p, int packetId, int packetSize) {
        if (p == null || p.stream == null) {
            return;
        }
        p.attackNpc = p.stream.readUnsignedWord();
	NPC n = Engine.npcs[p.attackNpc];
	if (n.npcType == 4284) {
		if (n.spawnedFor != p.playerId) {
			p.getActionSender().sendMessage(p, "It's not after you!");
			p.attackNpc = 0;
			return;
		}
	}
	if (p.nonMultiPlace()) {
		if (n.killingCount > 0 && n.playerIndex != p.playerId) {
			p.attackNpc = 0;
			p.getActionSender().sendMessage(p, "Someone else is already fighting that.");
			return;
		}
	}
	PlayerCombat playCb = new PlayerCombat(p);
	playCb.setNpcAttack(true);
    }
}
