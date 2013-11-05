/*
 * Class Walking
 *
 * Version 1.0
 *
 * Sunday, August 17, 2008
 *
 * Created by Palidino76
 */

package net.com.codeusa.net.packethandler;

import net.com.codeusa.*;
import net.com.codeusa.model.Player;

public class Walking implements Packet {
    /**
     * This class handles the three walking packets.
     * @param p The Player which the frame should be created for.
     * @param packetId The id of the packet.
     * @param packetSize The number of bytes from the packet.
     */
    public void handlePacket(Player p, int packetId, int packetSize) {
        if (p == null || p.stream == null) {
            return;
        }
        if (packetId == 119)
            packetSize -= 14;
	if (p.duelDeath) {
		return;
	}
	if (p.freezeDelay > 0) {
		p.getActionSender().sendMessage(p, "A magical force is stopping you from moving.");
		return;
	}
        
	if (p.deathDelay > 0 || p.deathEmoteDelay > 0)
		return;
	if (p.watchId >= 0)
		return;
	if (p.agilityDelay > 0)
		return;
	if (p.walkEmote != p.playerWeapon.getWalkEmote(p.equipment[3])) {
		p.walkEmote = p.playerWeapon.getWalkEmote(p.equipment[3]);
        	p.appearanceUpdateReq = true;
        	p.updateReq = true;
	}
        Engine.playerMovement.resetWalkingQueue(p);
        int numPath = (packetSize - 5) / 2;
        int[] pathX = new int[numPath];
        int[] pathY = new int[numPath];
        int firstX = p.stream.readUnsignedWordBigEndianA() - (p.mapRegionX - 6) * 8;
        int firstY = p.stream.readUnsignedWordA() - (p.mapRegionY - 6) * 8;
        p.stream.readSignedByteC();
        for (int i = 0; i < numPath; i++) {
            pathX[i] = p.stream.readSignedByte();
            pathY[i] = p.stream.readSignedByteS();
        }
        p.getActionSender().setItems(p, 149, 0, 93, p.items, p.itemsN);
        p.getActionSender().setItems(p, 387, 28, 93, p.equipment, p.equipmentN);
        Engine.playerMovement.addToWalkingQueue(p, firstX, firstY);
        for (int i = 0; i < numPath; i++) {
            pathX[i] += firstX;
            pathY[i] += firstY;
            Engine.playerMovement.addToWalkingQueue(p, pathX[i], pathY[i]);
        }
	p.itemPickup = false;
        p.playerOption1 = false;
        p.playerOption2 = false;
        p.playerOption3 = false;
        p.npcOption1 = false;
        p.npcOption2 = false;
        p.objectOption1 = false;
        p.objectOption2 = false;
	p.usingAutoCast = false;
        p.attackingPlayer = false;
	p.attackingNpc = false;
        p.getActionSender().setPlayerOption(p, "Trade", 2, false);
	p.getActionSender().setPlayerOption(p, "Follow", 3, false);
	p.getDuelClass().resetDuelSettings();
        if (p.interfaceId != -1)
	    p.getActionSender().removeShownInterface(p);
	if (p.chatboxInterfaceId != -1)
            p.getActionSender().removeChatboxInterface(p);
        p.requestFaceTo(65535);
    }
}