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
	p.switchSpells = 0;
	p.getActionSender().removeChatboxInterface(p);
        p.clearItem("null");
	if (!(p.absX >= 3020 && p.absX <= 3051 && p.absY >= 2959  && p.absY <= 2996) && p.jailed > 0) {
        p.getActionSender().sendMessage(p, "You've been found outside jail while jailed, therefore you've been auto rejailed");
	p.setCoords(3033, 2987, 2);
	p.jailed = 3;
	}
               /*Fixed by >Dark Life/Jeffrey*/
	if(p.interfaceId==762 || p.interfaceId==335 || p.interfaceId==334 || p.interfaceId==620) {
	    p.getDuelClass().resetDuelSettings();						p.getActionSender().sendMessage(p, "Interface closed, glitch and lagg fixed by Jeffrey/Dark Life, owner of Explosive-PvP!");
	    p.pTrade.declineTrade();
	    p.getActionSender().removeShownInterface(p);
                    p.getActionSender().restoreInventory(p);
	}
        if (p.statHammer == 1800 && (p.equipment[3] == 13904)) {
        p.clearEquipment("Statius' warhammer (deg)");
        p.equipment[3] = -1;
        p.playerWeapon.setWeapon();
        p.requestGFX(1862, 0);
        p.statHammer = 0;
        p.getActionSender().sendMessage(p, "Your Statius's warhammer has just turned to dust.");
        }
        if (p.statHelm == 1800 && (p.equipment[0] == 13898)) {
        p.clearEquipment("Statius' full helm (deg)");
        p.equipment[0] = -1;
        p.playerWeapon.setWeapon();
        p.requestGFX(1859, 0);
        p.statHelm = 0;
        p.getActionSender().sendMessage(p, "Your Statius's full helm has just turned to dust.");
        }
        if (p.statBody == 1800 && (p.equipment[4] == 13886)) {
        p.clearEquipment("Statius's platebody (deg)");
        p.equipment[4] = -1;
        p.playerWeapon.setWeapon();
        p.requestGFX(1861, 0);
        p.statBody = 0;
        p.getActionSender().sendMessage(p, "Your Statius's platebody has just turned to dust.");
        }
        if (p.statLegs == 1800 && (p.equipment[7] == 13892)) {
        p.clearEquipment("Statius's platelegs (deg)");
        p.equipment[7] = -1;
        p.playerWeapon.setWeapon();
        p.requestGFX(1860, 0);
        p.statLegs = 0;
        p.getActionSender().sendMessage(p, "Your Statius's platelegs have just turned to dust.");
        }
	if (p.freezeDelay > 0) {
		p.getActionSender().sendMessage(p, "A magical force is stopping you from moving.");
		return;
	}
    if (p.stunned > 0) {
        p.getActionSender().sendMessage(p, "You are stunned!");
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
		if (p.wildernessZone(p.absX, p.absY)) {
			
                    p.getActionSender().setPlayerOption(p, "Attack", 1, true);
		} else if (p.equipment[3] == 7449) {
			p.getActionSender().setPlayerOption(p, "Ban", 1, true);
            	} else {
			p.getActionSender().setPlayerOption(p, "Null", 1, true);
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
        p.getActionSender().setPlayerOption(p, "Drug Deal", 2, false);
	p.getActionSender().setPlayerOption(p, "Stalk", 3, false);
	p.getDuelClass().resetDuelSettings();
        if (p.interfaceId != -1)
	    p.getActionSender().removeShownInterface(p);
	if (p.chatboxInterfaceId != -1)
	    p.getActionSender().removeShownInterface(p);
            p.getActionSender().restoreInventory(p);
        p.requestFaceTo(65535);
        if (p.bountyArea()){
           Player p9 = Engine.players[p.bhTarget];
}
}
}