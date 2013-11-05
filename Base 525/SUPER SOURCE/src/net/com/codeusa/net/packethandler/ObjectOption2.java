/*
 * Class ObjectOption1
 *
 * Version 1.0
 *
 * Saturday, August 23, 2008
 *
 * Created by Palidino76
 */

package net.com.codeusa.net.packethandler;

import net.com.codeusa.Engine;
import net.com.codeusa.model.Player;
import net.com.codeusa.util.Misc;

public class ObjectOption2 implements Packet {
    /*
     * make sure to document EVERY coordinate to go with each object unless an un-important object(wilderness ditch lol).
     * This will prevent people from spawning an object client side and actually using it.
     * So make sure to include with the id, objectX == # && objectY == #
    */

    /**
     * Handles the second option on objects.
     * @param p The Player which the frame should be handled for.
     * @param packetId The packet id this belongs to.
     * @param packetSize The amount of bytes being recieved for this packet.
     */
    public void handlePacket(Player p, int packetId, int packetSize) {
        if (p == null || p.stream == null) {
            return;
        }
        if (!p.objectOption2) {
            p.clickY = p.stream.readUnsignedWordA();
            p.clickId = p.stream.readUnsignedWordBigEndian();
            p.clickX = p.stream.readUnsignedWordBigEndianA();
            if (Misc.getDistance(p.absX, p.absY, p.clickX, p.clickY) > 30) {
                return;
            }
            p.objectOption2 = true;
        }
        int distance = Misc.getDistance(p.clickX, p.clickY, p.absX, p.absY);
        if (p.walkDir != -1 || p.runDir != -1 || distance > objectSize(p.clickId)) {
            return;
        }
        p.objectOption2 = false;
	if (p.clickId == 4705) {
		p.thievingArray[2] = p.skillLvl[17];
		if (p.thievingArray[3] > 0)
			return;
	}
        switch (p.clickId) {

		case 4705:
			p.thievingArray[1] = 1;
			p.requestAnim(833, 0);
			p.getActionSender().sendMessage(p, "You attempt to steal something..");
			p.thievingArray[3] = 1;
			p.thievingArray[0] = 1;
		break;

		case 4706:
			p.thievingArray[1] = 2;
			p.requestAnim(833, 0);
			p.getActionSender().sendMessage(p, "You attempt to steal something..");
			p.thievingArray[3] = 1;
			p.thievingArray[0] = 1;
		break;

		case 28716:
			if (p.skillLvl[23] != p.getLevelForXP(23)) {
				p.requestAnim(645, 0);
				p.skillLvl[23] = p.getLevelForXP(23);
				p.getActionSender().setSkillLvl(p, 23);
				p.getActionSender().sendMessage(p, "You restore your summoning skill points.");
			} else {
				p.getActionSender().sendMessage(p, "Your summoning skill points are full already.");
			}
		break;
 //start of thief stalls
            case 34385://gem
                if (p.getLevelForXP(17) > 74){
                    Engine.playerItems.addItem(p, 995, 150000);
                    p.appendExperience(10000, 17);
                    p.requestAnim(833, 0);
                    p.clickDelay = 4;
        }  else  {
             p.getActionSender().sendMessage(p, "you need 75 thief to use this stall.");

        }

        break;
        case 34386://spice
                if (p.getLevelForXP(17) > 59){
                    Engine.playerItems.addItem(p, 995, 100000);
                    p.appendExperience(8100, 17);
                    p.requestAnim(833, 0);
                    p.clickDelay = 4;
        }  else  {
             p.getActionSender().sendMessage(p, "you need 60 thief to use this stall.");

        }

        break;
        case 34382://silver
                if (p.getLevelForXP(17) > 49){
                    Engine.playerItems.addItem(p, 995, 75000);
                    p.appendExperience(5400, 17);
                    p.requestAnim(833, 0);
                    p.clickDelay = 4;
        }  else  {
             p.getActionSender().sendMessage(p, "you need 50 thief to use this stall.");

        }

        break;
        case 34387://furr
                if (p.getLevelForXP(17) > 34){
                    Engine.playerItems.addItem(p, 995, 50000);
                    p.appendExperience(3600, 17);
                    p.requestAnim(833, 0);
                    p.clickDelay = 4;
        }  else  {
             p.getActionSender().sendMessage(p, "you need 35 thief to use this stall.");

        }

        break;
        case 34383://silk
                if (p.getLevelForXP(17) > 19){
                    Engine.playerItems.addItem(p, 995, 25000);
                    p.appendExperience(2400, 17);
                    p.requestAnim(833, 0);
                    p.clickDelay = 4;
        }  else  {
             p.getActionSender().sendMessage(p, "you need 20 thief to use this stall.");

        }

        break;
        case 34384://food
                if (p.getLevelForXP(17) > 0){
                    Engine.playerItems.addItem(p, 995, 10000);
                    p.appendExperience(500, 17);
                    p.requestAnim(833, 0);
                    p.clickDelay = 4;
        }  else  {
             p.getActionSender().sendMessage(p, "you need 1  thief to use this stall.");

        }

        break;
		/**
		 * Bank Access.
		 */
                case 28089:
                case 2213:
		case 2693:
		case 11402:
		case 36786:
		case 27663:
		case 26972:
		case 11758:
		case 19230:
		case 25808:
		case 34752:
		case 35647:
			p.openBank();
		break;

		case 36956:
			p.getActionSender().setInterface(p, 0, 548, 8, 311);
		break;
        default:
            Misc.println("[" + p.username + "] Unhandled object 2: " + p.clickId);
            break;
        }
    }

    private int objectSize(int id) {
        switch (id) {
        default:
            return 1;
        }
    }
}
