/*
 * Class NPCOption2
 *
 * Version 1.0
 *
 * Saturday, August 23, 2008
 *
 * Created by Palidino76
 */

package net.com.codeusa.net.packethandler;

import net.com.codeusa.Engine;
import net.com.codeusa.npcs.*;
import net.com.codeusa.model.skills.*;
import net.com.codeusa.model.Player;
import net.com.codeusa.util.Misc;

public class NPCOption2 implements Packet {
    /**
     * Handles the second NPC option.
     * @param p The Player which the frame should be handled for.
     * @param packetId The packet id this belongs to.
     * @param packetSize The amount of bytes being recieved for this packet.
     */
    public int npcType = 0;
    public void handlePacket(Player p, int packetId, int packetSize) {
        if (p == null || p.stream == null) {
            return;
        }
        p.npcClick2 = p.stream.readUnsignedWordBigEndianA();
	NPC n = Engine.npcs[p.npcClick2];
	if (n == null) {
		return;
	}
	if (Misc.getDistance(p.absX, p.absY, n.absX, n.absY) > 2) {
		return;
	}
	PlayerThieving playerThieving = new PlayerThieving(p);
            Misc.println("[" + p.username + "] Unhandled npc option 2: " + p.npcClick2);
        switch (p.npcClick2) {
            case 163:
            case 164:
            case 3:
                case 162:
		case 498:
		case 2:
		case 5:
		case 6:
		case 11:
		case 19:
		case 14:
		case 6538:
		case 18:
		case 196:
		case 65:
            case 16:
			p.openBank();
		break;
				case 44:
		p.openBank();
		break;
            

            
        default:
            Misc.println("[" + p.username + "] Unhandled npc option 2: " + p.npcClick2);
            break;
        }
    }
}