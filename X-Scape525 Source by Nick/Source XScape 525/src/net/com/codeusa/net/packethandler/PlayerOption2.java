package net.com.codeusa.net.packethandler;

import net.com.codeusa.*;
import net.com.codeusa.model.Player;
import net.com.codeusa.util.Misc;
import net.com.codeusa.io.PacketManager;

public class PlayerOption2 implements Packet {
    /**
     * Handles the second player option.
     * @param p The Player which the frame should be handled for.
     * @param packetId The packet id this belongs to.
     * @param packetSize The amount of bytes being recieved for this packet.
     */
    public void handlePacket(Player p, int packetId, int packetSize) {
        if (p == null || p.stream == null) {
            return;
        }
			/*if (p.gameTimeMinutes <= 2) {
			p.getActionSender().sendMessage(p, "You cannot trade for another 2 mins.");
			}
			else */if (!p.playerOption2) {
            int playerId = p.stream.readUnsignedWord();
            if (playerId <= 0 || playerId >= Engine.players.length || Engine.players[playerId] == null) {
                return;
            }
            p.clickId = playerId;
            p.clickX = Engine.players[playerId].absX;
            p.clickY = Engine.players[playerId].absY;
            if (Misc.getDistance(p.absX, p.absY, p.clickX, p.clickY) > 30) {
                return;
            }
            p.playerOption2 = true;
        }
        if (Engine.players[p.clickId] == null) {
            p.playerOption2 = false;
            return;
        }
        if (Misc.getDistance(p.absX, p.absY, p.clickX, p.clickY) > 1) {
            return;
        }

	  Player p2 = Engine.players[p.clickId];
		if (p2.pTrade.getPartner() == p) {
        } else {
            p.getActionSender().sendMessage(p, "Initiating Shady Deal...");
            p.getActionSender().sendMessage(p2, p.username.substring(0, 1).toUpperCase() + p.username.substring(1) + ":tradereq:");
        }
        p.requestFaceTo(65535);
        p.pTrade.tradePlayer(p2);
        p.playerOption2 = false;
      }
    }