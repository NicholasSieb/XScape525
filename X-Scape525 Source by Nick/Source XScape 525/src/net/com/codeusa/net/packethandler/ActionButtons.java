/*
 * Class ActionButtons
 *
 * Version 1.0
 *
 * Monday, August 18, 2008
 *
 * Created by Palidino76 Edited by Codeusa edited again by X
 */

package net.com.codeusa.net.packethandler;

import net.com.codeusa.*;
import net.com.codeusa.model.quest.*;
import net.com.codeusa.net.packethandler.*;
import net.com.codeusa.model.games.*;
import net.com.codeusa.model.combat.*;
import net.com.codeusa.model.combat.PlayerMagic;
import net.com.codeusa.model.Player;
import net.com.codeusa.util.Misc;
import net.com.codeusa.Server;
import java.net.URI;


public class ActionButtons implements Packet {
    /**
     * Handles buttons on interfaces.
     * @param p The Player which the frame should be handled for.
     * @param packetId The packet id this belongs to.
     * @param packetSize The amount of bytes being recieved for this packet.
     */

	public int color = 1; //Used for clothes stuff

    public void handlePacket(Player p, int packetId, int packetSize) {
        if (p == null || p.stream == null) {
            return;
        }
        int interfaceId = p.stream.readUnsignedWord();
        int buttonId = p.stream.readUnsignedWord();
        int buttonId2 = 0;
if (packetId == 233 || packetId == 21 || packetId == 169 || packetId == 232 || packetId == 214 || packetId == 90 || packetId == 173 || packetId == 133 || packetId == 226 || packetId == 102) {
buttonId2 = p.stream.readUnsignedWord();
}
        if (packetId == 173) {
            buttonId2 = p.stream.readUnsignedWord();
        }
        PlayerMagic playerMagic = new PlayerMagic(p);
	int warriorLevel = p.getLevelForXP(0) + p.getLevelForXP(2);
	QuestDevelopment quest = new QuestDevelopment(p);
	FightCave fCave = new FightCave(p);
	DuelArena duelArena = new DuelArena(p);
        switch (interfaceId) {

	case 523:
		if (buttonId == 100) {
			p.getActionSender().setTab(p, 79, p.spellbook);
		}
	break;
case 378:
	if (buttonId == 140) {
		p.getActionSender().setWindowPane(p, 548);
			if (p.firstLog == 0) {
				p.getActionSender().showInterface(p, 771);
             			p.getActionSender().animateInterfaceId(p, 9835, 771, 79);
             			p.getActionSender().setPlayerHead(p, 771, 79);
			}
	}
	break;
        case 771:
if (buttonId == 49) {
p.look[0] = 3;
p.look[1] = 10;
p.look[2] = 18;
p.look[3] = 26;
p.look[4] = 33;
p.look[5] = 36;
p.look[6] = 42;
p.gender = 0;
}
if (buttonId == 52) {
p.look[0] = 48; // Hair
p.look[1] = 1000; // Beard
p.look[2] = 57; // Torso
p.look[3] = 64; // Arms
p.look[4] = 68; // Bracelets
p.look[5] = 77; // Legs
p.look[6] = 80; // Shoes
p.gender = 1;
}
if (buttonId == 92 && p.gender < 1) {
p.look[0] = p.look[0] - 1;
if (p.look[0] < 0)
p.look[0] = 260;
if (p.look[0] > 8 && p.look[0] < 91)
p.look[0] = 8;
if (p.look[0] > 97 && p.look[0] < 246)
p.look[0] = 97;
if (p.look[0] > 260 && p.look[0] < 1000)
p.look[0] = 260;
}
if (buttonId == 92 && p.gender > 0) {
p.look[0] = p.look[0] - 1;
if (p.look[0] < 45)
p.look[0] = 280;
if (p.look[0] > 55 && p.look[0] < 135)
p.look[0] = 55;
if (p.look[0] > 144 && p.look[0] < 269)
p.look[0] = 144;
if (p.look[0] > 280 && p.look[0] < 1000)
p.look[0] = 45;
}
if (buttonId == 93 && p.gender < 1) {
p.look[0] = p.look[0] + 1;
if (p.look[0] > 8 && p.look[0] < 91)
p.look[0] = 91;
if (p.look[0] > 97 && p.look[0] < 246)
p.look[0] = 246;
if (p.look[0] > 260 && p.look[0] < 1000)
p.look[0] = 0;
}
if (buttonId == 93 && p.gender > 0) {
p.look[0] = p.look[0] + 1;
if (p.look[0] > 55 && p.look[0] < 135)
p.look[0] = 135;
if (p.look[0] > 144 && p.look[0] < 269)
p.look[0] = 269;
if (p.look[0] > 280 && p.look[0] < 1000)
p.look[0] = 45;
}
if (buttonId == 97) {
p.look[1] = p.look[1] - 1;
if (p.look[1] < 10)
p.look[1] = 17;
}
if (buttonId == 98) {
p.look[1] = p.look[1] + 1;
if (p.look[1] > 17)
p.look[1] = 10;

}
if (buttonId == 100) {
p.color[0] = 20;
}
if (buttonId == 101) {
p.color[0] = 19;
}
if (buttonId == 102) {
p.color[0] = 10;
}
if (buttonId == 103) {
p.color[0] = 18;
}
if (buttonId == 104) {
p.color[0] = 4;
}
if (buttonId == 105) {
p.color[0] = 5;
}
if (buttonId == 106) {
p.color[0] = 15;
}
if (buttonId == 107) {
p.color[0] = 7;
}
if (buttonId == 108) {
p.color[0] = 26;
}
if (buttonId == 109) {
p.color[0] = 6;
}
if (buttonId == 110) {
p.color[0] = 21;
}
if (buttonId == 111) {
p.color[0] = 9;
}
if (buttonId == 112) {
p.color[0] = 22;
}
if (buttonId == 113) {
p.color[0] = 17;
}
if (buttonId == 114) {
p.color[0] = 8;
}
if (buttonId == 115) {
p.color[0] = 16;
}
if (buttonId == 116) {
p.color[0] = 11;
}
if (buttonId == 117) {
p.color[0] = 24;
}
if (buttonId == 118) {
p.color[0] = 23;
}
if (buttonId == 119) {
p.color[0] = 3;
}
if (buttonId == 120) {
p.color[0] = 2;
}
if (buttonId == 121) {
p.color[0] = 1;
}
if (buttonId == 122) {
p.color[0] = 14;
}
if (buttonId == 123) {
p.color[0] = 13;
}
if (buttonId == 124) {
p.color[0] = 12;
}
if (buttonId == 158) {
p.color[4] = 7;
}
if (buttonId == 151) {
p.color[4] = 8;
}
if (buttonId == 152) {
p.color[4] = 1;
}
if (buttonId == 153) {
p.color[4] = 2;
}
if (buttonId == 154) {
p.color[4] = 3;
}
if (buttonId == 155) {
p.color[4] = 4;
}
if (buttonId == 156) {
p.color[4] = 5;
}
if (buttonId == 157) {
p.color[4] = 6;
}
	    if (buttonId == 307) { //BOOT-COLOR
				p.color[3] = 6;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }
	    if (buttonId == 308) { //BOOT-COLOR
				p.color[3] = 1;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }
	    if (buttonId == 309) { //BOOT-COLOR
				p.color[3] = 2;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }
	    if (buttonId == 310) { //BOOT-COLOR
				p.color[3] = 3;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }
	    if (buttonId == 311) { //BOOT-COLOR
				p.color[3] = 4;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }
	    if (buttonId == 312) { //BOOT-COLOR
				p.color[3] = 5;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }

	    if (buttonId == 263) { //LEG-COLOR
				p.color[2] = 0;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }
	    if (buttonId == 258) { //LEG-COLOR
				p.color[2] = 1;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }
	    if (buttonId == 275) { //LEG-COLOR
				p.color[2] = 2;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }
	    if (buttonId == 251) { //LEG-COLOR
				p.color[2] = 3;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }
	    if (buttonId == 271) { //LEG-COLOR
				p.color[2] = 4;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }
	    if (buttonId == 257) { //LEG-COLOR
				p.color[2] = 5;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }
	    if (buttonId == 272) { //LEG-COLOR
				p.color[2] = 6;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }
	    if (buttonId == 255) { //LEG-COLOR
				p.color[2] = 7;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }
	    if (buttonId == 265) { //LEG-COLOR
				p.color[2] = 8;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }
	    if (buttonId == 262) { //LEG-COLOR
				p.color[2] = 9;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }
	    if (buttonId == 259) { //LEG-COLOR
				p.color[2] = 10;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }
	    if (buttonId == 268) { //LEG-COLOR
				p.color[2] = 11;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }
	    if (buttonId == 254) { //LEG-COLOR
				p.color[2] = 12;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }
	    if (buttonId == 253) { //LEG-COLOR
				p.color[2] = 13;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }
	    if (buttonId == 260) { //LEG-COLOR
				p.color[2] = 14;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }
	    if (buttonId == 267) { //LEG-COLOR
				p.color[2] = 15;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }
	    if (buttonId == 276) { //LEG-COLOR
				p.color[2] = 16;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }
	    if (buttonId == 274) { //LEG-COLOR
				p.color[2] = 17;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }
	    if (buttonId == 273) { //LEG-COLOR
				p.color[2] = 18;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }
	    if (buttonId == 256) { //LEG-COLOR
				p.color[2] = 19;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }
	    if (buttonId == 266) { //LEG-COLOR
				p.color[2] = 20;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }
	    if (buttonId == 264) { //LEG-COLOR
				p.color[2] = 21;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }
	    if (buttonId == 252) { //LEG-COLOR
				p.color[2] = 22;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }
	    if (buttonId == 250) { //LEG-COLOR
				p.color[2] = 23;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }
	    if (buttonId == 249) { //LEG-COLOR
				p.color[2] = 24;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }
	    if (buttonId == 261) { //LEG-COLOR
				p.color[2] = 25;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }
	    if (buttonId == 248) { //LEG-COLOR
				p.color[2] = 26;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }
	    if (buttonId == 270) { //LEG-COLOR
				p.color[2] = 27;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }
	    if (buttonId == 269) { //LEG-COLOR
				p.color[2] = 28;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }

	    if (buttonId == 198) { //TOP-COLOR
				p.color[1] = 0;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }
	    if (buttonId == 216) { //TOP-COLOR
				p.color[1] = 1;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }
	    if (buttonId == 191) { //TOP-COLOR
				p.color[1] = 2;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }
	    if (buttonId == 212) { //TOP-COLOR
				p.color[1] = 3;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }
	    if (buttonId == 197) { //TOP-COLOR
				p.color[1] = 4;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }
	    if (buttonId == 213) { //TOP-COLOR
				p.color[1] = 5;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }
	    if (buttonId == 195) { //TOP-COLOR
				p.color[1] = 6;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }
	    if (buttonId == 206) { //TOP-COLOR
				p.color[1] = 7;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }
	    if (buttonId == 202) { //TOP-COLOR
				p.color[1] = 8;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }
	    if (buttonId == 199) { //TOP-COLOR
				p.color[1] = 9;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }
	    if (buttonId == 209) { //TOP-COLOR
				p.color[1] = 10;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }
	    if (buttonId == 194) { //TOP-COLOR
				p.color[1] = 11;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }
	    if (buttonId == 193) { //TOP-COLOR
				p.color[1] = 12;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }
	    if (buttonId == 200) { //TOP-COLOR
				p.color[1] = 13;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }
	    if (buttonId == 208) { //TOP-COLOR
				p.color[1] = 14;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }
	    if (buttonId == 203) { //TOP-COLOR
				p.color[1] = 15;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }
	    if (buttonId == 217) { //TOP-COLOR
				p.color[1] = 16;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }
	    if (buttonId == 215) { //TOP-COLOR
				p.color[1] = 17;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }
	    if (buttonId == 214) { //TOP-COLOR
				p.color[1] = 18;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }
	    if (buttonId == 196) { //TOP-COLOR
				p.color[1] = 19;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }
	    if (buttonId == 207) { //TOP-COLOR
				p.color[1] = 20;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }
	    if (buttonId == 205) { //TOP-COLOR
				p.color[1] = 21;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }
	    if (buttonId == 192) { //TOP-COLOR
				p.color[1] = 22;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }
	    if (buttonId == 190) { //TOP-COLOR
				p.color[1] = 23;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }
	    if (buttonId == 189) { //TOP-COLOR
				p.color[1] = 24;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }
	    if (buttonId == 201) { //TOP-COLOR
				p.color[1] = 25;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }
	    if (buttonId == 204) { //TOP-COLOR
				p.color[1] = 26;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }
	    if (buttonId == 211) { //TOP-COLOR
				p.color[1] = 27;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }
	    if (buttonId == 210) { //TOP-COLOR
				p.color[1] = 28;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
	    }

	    if (buttonId == 362) {
				p.getActionSender().removeShownInterface(p);
				p.firstLog = 1;
	     }

				p.appearanceUpdateReq = true;
				p.updateReq = true;

               break;
	case 259:
            if (buttonId == 2) {//veng runes
            Engine.playerItems.addItem(p, 560, 200);
            Engine.playerItems.addItem(p, 9075, 400);
            Engine.playerItems.addItem(p, 557, 1000);
                        }
            if (buttonId == 3) {//barrage runes
            Engine.playerItems.addItem(p, 560, 4000);
            Engine.playerItems.addItem(p, 565, 2000);
            Engine.playerItems.addItem(p, 555, 6000);
            }
            if (buttonId == 4){//food
            if(p.InBounty == 1) {
            p.getActionSender().sendMessage(p, "You cannot use this command inside Bounty Hunter");
            return;
            }
            if (p.attackedBy != null) {
            p.getActionSender().sendMessage(p, "You cannot use this command while in combat.");
            return;
            }
            if (p.wildernessZone(p.absX, p.absY)) {
            p.getActionSender().sendMessage(p, "You cannot use this command while inside of the wilderness.");
            return;
            }
            Engine.playerItems.addItem(p, 379, 28);
            }
            if (buttonId == 5){//pots
            if(p.InBounty == 1) {
            p.getActionSender().sendMessage(p, "You cannot use this command inside Bounty Hunter");
            return;
            }
            if (p.attackedBy != null) {
            p.getActionSender().sendMessage(p, "You cannot use this command while in combat.");
            return;
            }
            if (p.wildernessZone(p.absX, p.absY)) {
            p.getActionSender().sendMessage(p, "You cannot use this command while inside of the wilderness.");
            return;
            }
            Engine.playerItems.addItem(p, 3024, 1);
            Engine.playerItems.addItem(p, 145, 1);
            Engine.playerItems.addItem(p, 6685, 1);
            Engine.playerItems.addItem(p, 157, 1);
            Engine.playerItems.addItem(p, 2444, 1);
            Engine.playerItems.addItem(p, 2434, 1);
            }

            if (buttonId == 25){//Donators cape
            if (p.Donator == 0 && p.rights == 0) {
        p.getActionSender().showChatboxInterface(p, 243);
        p.getActionSender().animateInterfaceId(p, 9835, 243, 2);
        p.getActionSender().setNPCId(p, 6537, 243, 2);
        p.getActionSender().setString(p, "IaliIscape", 243, 3);
        p.getActionSender().setString(p, "Commands located in this section,", 243, 4);
        p.getActionSender().setString(p, "Are for Donators,pm a admin", 243, 5);
        p.getActionSender().setString(p, "To buy donator for $10. ", 243, 6);
        } else {
            for (Player pl : Server.engine.players) {
            if (pl != null) {
            try {
            Engine.playerItems.addItem(p, 4375, 1);
            pl.message("<img=1>Lets all thank "+p.username+" once again for donating.<img=1>");
            } catch (Exception e) {
            }
            }
            }
            }
            }
            if (buttonId == 21){//Recharge prayer
                if(p.InBounty == 1) {
            p.getActionSender().sendMessage(p, "You cannot use this command inside Bounty Hunter");
            return;
            }
            if (p.attackedBy != null) {
            p.getActionSender().sendMessage(p, "You cannot use this command while in combat.");
            return;
            }
            if (p.wildernessZone(p.absX, p.absY)) {
            p.getActionSender().sendMessage(p, "You cannot use this command while inside of the wilderness.");
            return;
            }
            if (p.Donator == 0 && p.rights == 0) {
        p.getActionSender().showChatboxInterface(p, 243);
        p.getActionSender().animateInterfaceId(p, 9835, 243, 2);
        p.getActionSender().setNPCId(p, 6537, 243, 2);
        p.getActionSender().setString(p, "X-Scape", 243, 3);
        p.getActionSender().setString(p, "Commands located in this section,", 243, 4);
        p.getActionSender().setString(p, "Are for Donators,pm X", 243, 5);
        p.getActionSender().setString(p, "To buy donator for $5. ", 243, 6);
        } else {
            p.skillLvl[5] = p.getLevelForXP(5);
            p.getActionSender().setSkillLvl(p, 5);
            p.message("<img=1>Thanks once again "+p.username+"  for donating.<img=1>");
            }
            }
	    if (buttonId == 20) { //gdz
                     if(p.InBounty == 1) {
		p.getActionSender().sendMessage(p, "You cannot teleport out of Bounty Hunter");
		return;
	}
				if (p.attackedBy != null) {
					p.getActionSender().sendMessage(p, "You cannot use this command while in combat.");
					return;
				}
				if (p.jailed > 0) {
					p.getActionSender().sendMessage(p, "You are jailed!");
					return;
				}
				p.teleportTo(3289, 3887, 0, 4, 0, 8939, 8941, 1678, 0, 1679, 0);
                }
            if (buttonId == 22){//Recharge special
                if(p.InBounty == 1) {
            p.getActionSender().sendMessage(p, "You cannot use this command inside Bounty Hunter");
            return;
            }
            if (p.attackedBy != null) {
            p.getActionSender().sendMessage(p, "You cannot use this command while in combat.");
            return;
            }
            if (p.wildernessZone(p.absX, p.absY)) {
            p.getActionSender().sendMessage(p, "You cannot use this command while inside of the wilderness.");
            return;
            }
            if (p.Donator == 0 && p.rights == 0) {
        p.getActionSender().showChatboxInterface(p, 243);
        p.getActionSender().animateInterfaceId(p, 9835, 243, 2);
        p.getActionSender().setNPCId(p, 6537, 243, 2);
        p.getActionSender().setString(p, "X-Scape", 243, 3);
        p.getActionSender().setString(p, "Commands located in this section,", 243, 4);
        p.getActionSender().setString(p, "Are for Donators,pm X", 243, 5);
        p.getActionSender().setString(p, "To buy donator for $5. ", 243, 6);
        } else {
            p.specAmount = 1000;
            p.getActionSender().setConfig2(p, 300, 1000);
            p.restoreSpecialTimer = 60;
            p.message("<img=1>Thanks once again "+p.username+"  for donating.<img=1>");
            }
            }
            if (buttonId == 23){//open bank
                if (p.jailed > 0) {
                p.getActionSender().sendMessage(p, "You are jailed!");
                return;
                }
                if(p.InBounty == 1) {
            p.getActionSender().sendMessage(p, "You cannot use this command inside Bounty Hunter");
            return;
            }
            if (p.attackedBy != null) {
            p.getActionSender().sendMessage(p, "You cannot use this command while in combat.");
            return;
            }
            if (p.wildernessZone(p.absX, p.absY)) {
            p.getActionSender().sendMessage(p, "You cannot use this command while inside of the wilderness.");
            return;
            }
            if (p.Donator == 0 && p.rights == 0) {
        p.getActionSender().showChatboxInterface(p, 243);
        p.getActionSender().animateInterfaceId(p, 9835, 243, 2);
        p.getActionSender().setNPCId(p, 6537, 243, 2);
        p.getActionSender().setString(p, "XScape", 243, 3);
        p.getActionSender().setString(p, "Commands located in this section,", 243, 4);
        p.getActionSender().setString(p, "Are for Donators,pm X", 243, 5);
        p.getActionSender().setString(p, "To buy donator for $5. ", 243, 6);
        } else {
            p.openBank();
            p.message("<img=1>Thanks once again "+p.username+"  for donating.<img=1>");
            }
            }
            if (buttonId == 24){//open shop
                if (p.jailed > 0) {
                p.getActionSender().sendMessage(p, "You are jailed!");
                return;
                }
                if(p.InBounty == 1) {
            p.getActionSender().sendMessage(p, "You cannot use this command inside Bounty Hunter");
            return;
            }
            if (p.attackedBy != null) {
            p.getActionSender().sendMessage(p, "You cannot use this command while in combat.");
            return;
            }
            if (p.wildernessZone(p.absX, p.absY)) {
            p.getActionSender().sendMessage(p, "You cannot use this command while inside of the wilderness.");
            return;
            }
            if (p.Donator == 0 && p.rights == 0) {
        p.getActionSender().showChatboxInterface(p, 243);
        p.getActionSender().animateInterfaceId(p, 9835, 243, 2);
        p.getActionSender().setNPCId(p, 6537, 243, 2);
        p.getActionSender().setString(p, "X-Scape", 243, 3);
        p.getActionSender().setString(p, "Commands located in this section,", 243, 4);
        p.getActionSender().setString(p, "Are for Donators,pm X", 243, 5);
        p.getActionSender().setString(p, "To buy donator for $5. ", 243, 6);
        } else {
            Server.engine.shopHandler.openshop(p, 1);
            p.message("<img=1>Thanks once again "+p.username+"  for donating.<img=1>");
            }
            }
            if (buttonId == 26){//Respect
            if (p.Donator == 0 && p.rights == 0) {
        p.getActionSender().showChatboxInterface(p, 243);
        p.getActionSender().animateInterfaceId(p, 9835, 243, 2);
        p.getActionSender().setNPCId(p, 6537, 243, 2);
        p.getActionSender().setString(p, "X-Scape", 243, 3);
        p.getActionSender().setString(p, "Commands located in this section,", 243, 4);
        p.getActionSender().setString(p, "Are for Donators,pm X", 243, 5);
        p.getActionSender().setString(p, "To buy donator for $5. ", 243, 6);
        } else {
            for (Player pl : Server.engine.players) {
            if (pl != null) {
            try {
            pl.message("<img=1>RESPECT to "+p.username+" for helping to keep server running..<img=1>");
            } catch (Exception e) {
            }
            }
            }
            }
            }


		if (buttonId == 8) {
			p.getActionSender().setTab(p, 75, 274);
                        p.getActionSender().setString(p, "", 274, 1);
	p.getActionSender().setString(p, "", 274, 2);
	p.getActionSender().setString(p, "", 274, 3);
	p.getActionSender().setString(p, "", 274, 4);
	p.getActionSender().setString(p, "", 274, 5);
	p.getActionSender().setString(p, "", 274, 6);
	p.getActionSender().setString(p, "", 274, 7);
	p.getActionSender().setString(p, "", 274, 8);
	p.getActionSender().setString(p, "", 274, 9);
	p.getActionSender().setString(p, "", 274, 10);
	p.getActionSender().setString(p, "", 274, 11);
	p.getActionSender().setString(p, "", 274, 12);
	p.getActionSender().setString(p, "<col=33FF33><img=1>Home<img=1>", 274, 13);
	p.getActionSender().setString(p, "<col=33FF33>Bounty Hunter", 274, 14);
	p.getActionSender().setString(p, "<col=33FF33>Kills and Deaths", 274, 15);
        p.getActionSender().setString(p, "<col=33FF33>X HAS A HUGE COCK", 274, 16);
        p.getActionSender().setString(p, "<col=43C6DB>clan Wars", 274, 17);
        p.getActionSender().setString(p, "<col=43C6DB>Corperal Beast", 274, 18);
        p.getActionSender().setString(p, "<col=43C6DB>Thieve for Money", 274, 19);
        p.getActionSender().setString(p, "<col=43C6DB>", 274, 20);
        p.getActionSender().setString(p, "<col=43C6DB>Alternate pvp world with drops", 274, 21);
        p.getActionSender().setString(p, "<col=43C6DB>X's pking zone", 274, 22);
        p.getActionSender().setString(p, "<col=43C6DB>Varrock 1v1 pk", 274, 23);
        p.getActionSender().setString(p, "<col=43C6DB>Dragons PK (23 wild)", 274, 24);
        p.getActionSender().setString(p, "", 274, 25);
        p.getActionSender().setString(p, "<col=307D7E>Toggle XP click this to train", 274, 26);
        p.getActionSender().setString(p, "<col=307D7E>Backup Account", 274, 27);
        p.getActionSender().setString(p, "<col=307D7E>Sell Inventory", 274, 28);
        p.getActionSender().setString(p, "<img=1>Staffzone<img=1>", 274, 29);
        p.getActionSender().setString(p, "RETURN from pvp world", 274, 30);
        p.getActionSender().setString(p, "", 274, 31);
        p.getActionSender().setString(p, "donorzone ::info", 274, 32);
        p.getActionSender().setString(p, "", 274, 33);
        p.getActionSender().setString(p, "", 274, 34);
        p.getActionSender().setString(p, "", 274, 35);
        p.getActionSender().setString(p, "", 274, 36);
        p.getActionSender().setString(p, "", 274, 37);
        p.getActionSender().setString(p, "", 274, 38);
        p.getActionSender().setString(p, "", 274, 39);
        p.getActionSender().setString(p, "", 274, 40);
        p.getActionSender().setString(p, "", 274, 41);
        p.getActionSender().setString(p, "", 274, 42);
        p.getActionSender().setString(p, "", 274, 43);
        p.getActionSender().setString(p, "", 274, 44);
        p.getActionSender().setString(p, "", 274, 45);
        p.getActionSender().setString(p, "", 274, 46);
        p.getActionSender().setString(p, "", 274, 47);
        p.getActionSender().setString(p, "", 274, 48);
        p.getActionSender().setString(p, "", 274, 49);
        p.getActionSender().setString(p, "", 274, 50);
        p.getActionSender().setString(p, "", 274, 51);
        p.getActionSender().setString(p, "", 274, 52);
        p.getActionSender().setString(p, "", 274, 53);
        p.getActionSender().setString(p, "", 274, 54);
        p.getActionSender().setString(p, "", 274, 55);
        p.getActionSender().setString(p, "", 274, 56);
        p.getActionSender().setString(p, "", 274, 57);
        p.getActionSender().setString(p, "", 274, 58);
        p.getActionSender().setString(p, "", 274, 59);
        p.getActionSender().setString(p, "", 274, 60);
        p.getActionSender().setString(p, "", 274, 61);
        p.getActionSender().setString(p, "", 274, 62);
        p.getActionSender().setString(p, "", 274, 63);
        p.getActionSender().setString(p, "", 274, 64);
        p.getActionSender().setString(p, "", 274, 65);
        p.getActionSender().setString(p, "", 274, 66);
        p.getActionSender().setString(p, "", 274, 67);
        p.getActionSender().setString(p, "", 274, 68);
        p.getActionSender().setString(p, "", 274, 69);
        p.getActionSender().setString(p, "", 274, 70);
        p.getActionSender().setString(p, "", 274, 71);
        p.getActionSender().setString(p, "", 274, 72);
        p.getActionSender().setString(p, "", 274, 73);
        p.getActionSender().setString(p, "", 274, 74);
        p.getActionSender().setString(p, "", 274, 75);
        p.getActionSender().setString(p, "", 274, 76);
        p.getActionSender().setString(p, "", 274, 77);
        p.getActionSender().setString(p, "", 274, 78);
        p.getActionSender().setString(p, "", 274, 79);
        p.getActionSender().setString(p, "", 274, 80);
        p.getActionSender().setString(p, "", 274, 81);
        p.getActionSender().setString(p, "", 274, 82);
        p.getActionSender().setString(p, "", 274, 83);
        p.getActionSender().setString(p, "", 274, 84);
        p.getActionSender().setString(p, "", 274, 85);
        p.getActionSender().setString(p, "", 274, 86);
        p.getActionSender().setString(p, "", 274, 87);
        p.getActionSender().setString(p, "", 274, 88);
        p.getActionSender().setString(p, "", 274, 89);
        p.getActionSender().setString(p, "", 274, 90);
        p.getActionSender().setString(p, "", 274, 91);
        p.getActionSender().setString(p, "", 274, 92);
        p.getActionSender().setString(p, "", 274, 93);
        p.getActionSender().setString(p, "", 274, 94);
        p.getActionSender().setString(p, "", 274, 95);
        p.getActionSender().setString(p, "", 274, 96);
        p.getActionSender().setString(p, "", 274, 97);
        p.getActionSender().setString(p, "", 274, 98);
        p.getActionSender().setString(p, "", 274, 99);
        p.getActionSender().setString(p, "", 274, 100);
        p.getActionSender().setString(p, "", 274, 101);
        p.getActionSender().setString(p, "", 274, 102);
        p.getActionSender().setString(p, "", 274, 103);
        p.getActionSender().setString(p, "", 274, 104);
        p.getActionSender().setString(p, "", 274, 105);
        p.getActionSender().setString(p, "", 274, 106);
        p.getActionSender().setString(p, "", 274, 107);
        p.getActionSender().setString(p, "", 274, 108);
        p.getActionSender().setString(p, "", 274, 109);
        p.getActionSender().setString(p, "", 274, 110);
        p.getActionSender().setString(p, "", 274, 111);
        p.getActionSender().setString(p, "", 274, 112);
        p.getActionSender().setString(p, "", 274, 113);
        p.getActionSender().setString(p, "", 274, 114);
        p.getActionSender().setString(p, "", 274, 115);
        p.getActionSender().setString(p, "", 274, 116);
        p.getActionSender().setString(p, "", 274, 117);
        p.getActionSender().setString(p, "", 274, 118);
        p.getActionSender().setString(p, "", 274, 119);
        p.getActionSender().setString(p, "", 274, 120);
        p.getActionSender().setString(p, "", 274, 121);
        p.getActionSender().setString(p, "", 274, 122);
        p.getActionSender().setString(p, "", 274, 123);
        p.getActionSender().setString(p, "", 274, 124);
        p.getActionSender().setString(p, "", 274, 125);
        p.getActionSender().setString(p, "", 274, 126);
        p.getActionSender().setString(p, "", 274, 127);
        p.getActionSender().setString(p, "", 274, 128);
        p.getActionSender().setString(p, "", 274, 129);
        p.getActionSender().setString(p, "", 274, 130);
        p.getActionSender().setString(p, "", 274, 131);
        p.getActionSender().setString(p, "", 274, 132);
        p.getActionSender().setString(p, "", 274, 133);
        p.getActionSender().setString(p, "", 274, 134);
        p.getActionSender().setString(p, "", 274, 135);
        p.getActionSender().setString(p, "", 274, 136);
        p.getActionSender().setString(p, "", 274, 137);
        p.getActionSender().setString(p, "", 274, 138);
        p.getActionSender().setString(p, "", 274, 139);
        p.getActionSender().setString(p, "", 274, 140);
        p.getActionSender().setString(p, "", 274, 141);
        p.getActionSender().setString(p, "", 274, 142);
        p.getActionSender().setString(p, "", 274, 143);
        p.getActionSender().setString(p, "", 274, 144);
        p.getActionSender().setString(p, "", 274, 145);
        p.getActionSender().setString(p, "", 274, 146);
        p.getActionSender().setString(p, "", 274, 147);
        p.getActionSender().setString(p, "", 274, 148);
        p.getActionSender().setString(p, "", 274, 149);
        p.getActionSender().setString(p, "", 274, 150);
        p.getActionSender().setString(p, "", 274, 151);
        p.getActionSender().setString(p, "", 274, 152);
        p.getActionSender().setString(p, "", 274, 153);
        p.getActionSender().setString(p, "", 274, 154);
        p.getActionSender().setString(p, "", 274, 155);
        p.getActionSender().setString(p, "", 274, 156);
        p.getActionSender().setString(p, "", 274, 157);
        p.getActionSender().setString(p, "", 274, 158);
        p.getActionSender().setString(p, "", 274, 159);
        p.getActionSender().setString(p, "", 274, 160);
        p.getActionSender().setString(p, "", 274, 161);
        p.getActionSender().setString(p, "", 274, 162);
        p.getActionSender().setString(p, "", 274, 163);
        p.getActionSender().setString(p, "", 274, 164);
        p.getActionSender().setString(p, "", 274, 165);
        p.getActionSender().setString(p, "", 274, 163);
		}
	break;
case 589:
        		if(buttonId == 9) {
        			p.getActionSender().showInterface(p, 590);
        		}
        		break;
case 591:
p.appearanceUpdateReq = true;
p.updateReq = true;
switch(buttonId) {

case 182:
color = 1;
break;

case 183:
color = 1;
break;

case 184:
color = 2;
break;

case 180:
p.getActionSender().removeShownInterface(p);
color = 1;
break;

//start torsos
case 185:
p.look[2] = 111;
break;
case 186:
p.look[2] = 116;
break;
case 187:
p.look[2] = 114;
break;
case 188:
p.look[2] = 115;
break;
case 189:
p.look[2] = 112;
break;
case 190:
p.look[2] = 113;
break;
case 191:
p.look[2] = 18;
break;
case 192:
p.look[2] = 19;
break;
case 193:
p.look[2] = 20;
break;
case 194:
p.look[2] = 21;
break;
case 195:
p.look[2] = 22;
break;
case 196:
p.look[2] = 23;
break;
case 197:
p.look[2] = 24;
break;
case 198:
p.look[2] = 25;
break;
//end torsos

//start sleeves
case 199:
p.look[3] = 105;
break;
case 200:
p.look[3] = 108;
break;
case 201:
p.look[3] = 107;
break;
case 202:
p.look[3] = 106;
break;
case 203:
p.look[3] = 109;
break;
case 204:
p.look[3] = 110;
break;
case 205:
p.look[3] = 28;
break;
case 206:
p.look[3] = 26;
break;
case 207:
p.look[3] = 27;
break;
case 208:
p.look[3] = 29;
break;
case 209:
p.look[3] = 30;
break;
case 210:
p.look[3] = 31;
break;
//end sleeves

//start legs
case 211:
p.look[5] = 36;
break;
case 212:
p.look[5] = 85;
break;
case 213:
p.look[5] = 37;
break;
case 214:
p.look[5] = 41;
break;
case 215:
p.look[5] = 89;
break;
case 216:
p.look[5] = 90;
break;
case 217:
p.look[5] = 39;
break;
case 218:
p.look[5] = 88;
break;
case 219:
p.look[5] = 87;
break;
case 220:
p.look[5] = 38;
break;
case 221:
p.look[5] = 86;
break;

case 280://black
p.color[color] = 16;
break;
case 279://dark grey
p.color[color] = color;
break;
case 278://taupe
p.color[color] = 17;
break;
case 277://light grey
p.color[color] = 18;
break;
case 276://beige
p.color[color] = color + 4;
break;
case 275://navy blue
p.color[color] = color + 2;
break;
case 274://indigo
p.color[color] = 27;
break;
case 273://violet
p.color[color] = 28;
break;
case 272://mauve
p.color[color] = color + 9;
break;
case 270://light blue
p.color[color] = 20;
break;
case 271://pale blue
p.color[color] = color + 13;
break;
case 269://dark blue
p.color[color] = color + 6;
break;
case 268://royal blue
p.color[color] = 21;
break;
case 267://dark green
p.color[color] = 26;
break;
case 266://forest green
p.color[color] = color == 2 ? 0 : 15;
break;
case 265://sea green
p.color[color] = color + 7;
break;
case 264://mint green
p.color[color] = 25;
break;
case 263://moss green
p.color[color] = color + 12;
break;
case 262://gold
p.color[color] = color + 8;
break;

case 281:
color = 1;
break;

default:
Misc.println("Unhandled char screen button: "  + buttonId + ":" + buttonId2);
break;
}
break;




case 592:
p.appearanceUpdateReq = true;
p.updateReq = true;
if(buttonId == 168) {//bald
p.look[0] = 45;
}
if(buttonId == 169) {//bun
p.look[0] = 46;
}
if(buttonId == 170) {//dreadlocks
p.look[0] = 47;
}
if(buttonId == 171) {//long
p.look[0] = 48;
}
if(buttonId == 172) {//short
p.look[0] = 49;
}
if(buttonId == 173) {//pigtails
p.look[0] = 50;
}
if(buttonId == 174) {//crew cut
p.look[0] = 51;
}
if(buttonId == 175) {//close cropped
p.look[0] = 52;
}
if(buttonId == 176) {//wild spikes
p.look[0] = 53;
}
if(buttonId == 177) {//spikes
p.look[0] = 54;
}
if(buttonId == 178) {
p.look[0] = 135;
}
if(buttonId == 179) {
p.look[0] = 136;
}
if(buttonId == 180) {
p.look[0] = 137;
}
if(buttonId == 181) {
p.look[0] = 138;
}
if(buttonId == 182) {
p.look[0] = 139;
}
if(buttonId == 183) {
p.look[0] = 140;
}
if(buttonId == 184) {
p.look[0] = 141;
}
if(buttonId == 185) {
p.look[0] = 142;
}
if(buttonId == 186) {
p.look[0] = 143;
}
if(buttonId == 187) {
p.look[0] = 144;
}
break;



case 596:
if(buttonId == 245) {
p.color[0] = 1;
}
if(buttonId == 228) {
p.color[0] = 4;
}
if(buttonId == 229) {
p.color[0] = 5;
}
if(buttonId == 233) {
p.color[0] = 6;
}
if(buttonId == 231) {
p.color[0] = 7;
}
if(buttonId == 235) {
p.color[0] = 9;
}
if(buttonId == 226) {
p.color[0] = 10;
}
if(buttonId == 240) {
p.color[0] = 11;
}
if(buttonId == 243) {
p.color[0] = 3;
}
if(buttonId == 247) {
p.color[0] = 13;
}
if(buttonId == 230) {
p.color[0] = 15;
}
if(buttonId == 239) {
p.color[0] = 16;
}
if(buttonId == 227) {
p.color[0] = 18;
}
if(buttonId == 225) {
p.color[0] = 19;
}
if(buttonId == 224) {
p.color[0] = 20;
}
if(buttonId == 234) {
p.color[0] = 21;
}
if(buttonId == 236) {
p.color[0] = 22;
}
if(buttonId == 242) {
p.color[0] = 23;
}
if(buttonId == 241) {
p.color[0] = 24;
}
if(buttonId == 232) {
p.color[0] = 25;
}
if(buttonId == 237) {
p.color[0] = 17;
}
if(buttonId == 246) {
p.color[0] = 14;
}
if(buttonId == 244) {
p.color[0] = 2;
}
if(buttonId == 248) {
p.color[0] = 12;
}
if(buttonId == 238) {
p.color[0] = 8;
}
if(buttonId == 160) {
p.getActionSender().removeShownInterface(p);
}
if(buttonId == 167) {//bald
p.look[0] = 0;
}
if(buttonId == 168) {//dreadlocks
p.look[0] = 1;
}
if(buttonId == 169) {//long hair
p.look[0] = 2;
}
if(buttonId == 170) {//long hair
p.look[0] = 3;
}
if(buttonId == 171) {//crew cut
p.look[0] = 4;
}
if(buttonId == 172) {//crew cut
p.look[0] = 5;
}
if(buttonId == 173) {//crew cut
p.look[0] = 6;
}
if(buttonId == 174) {//Wild spikes
p.look[0] = 7;
}
if(buttonId == 175) {//Spikes
p.look[0] = 8;
}
if(buttonId == 176) {//Mohawk
p.look[0] = 91;
}
if(buttonId == 177) {
p.look[0] = 92;
}
if(buttonId == 178) {
p.look[0] = 93;
}
if(buttonId == 179) {
p.look[0] = 94;
}
if(buttonId == 180) {
p.look[0] = 95;
}
if(buttonId == 181) {
p.look[0] = 96;
}
if(buttonId == 182) {
p.look[0] = 97;
}
if(buttonId == 183) { //Goatee
p.look[1] = 10;
}
if(buttonId == 184) { //Long Beard
p.look[1] = 11;
}
if(buttonId == 185) { //Med Beard
p.look[1] = 12;
}
if(buttonId == 186) { //Mustouche
p.look[1] = 13;
}
if(buttonId == 187) { //Clean Shaven
p.look[1] = 14;
}
if(buttonId == 188) { //Short Beard
p.look[1] = 15;
}
if(buttonId == 189) { //Short Full
p.look[1] = 16;
}
if(buttonId == 190) { //Split
p.look[1] = 17;
}
if(buttonId == 191) { //Handle Bar
p.look[1] = 98;
}
if(buttonId == 192) { //Mutton
p.look[1] = 99;
}
if(buttonId == 193) { //Full Mutton
p.look[1] = 100;
}
if(buttonId == 194) { //Full Mustouche
p.look[1] = 101;
}
if(buttonId == 195) { //Waxed
p.look[1] = 102;
}
if(buttonId == 196) { //Dali
p.look[1] = 103;
}
if(buttonId == 197) { //Visier
p.look[1] = 104;
}
else if(buttonId != 160 || buttonId !=167 || buttonId !=168 || buttonId !=169 || buttonId !=170 || buttonId !=171 || buttonId !=172 || buttonId !=173 || buttonId !=174 || buttonId !=175 || buttonId !=176 || buttonId !=177 || buttonId !=178 || buttonId !=179 || buttonId !=180 || buttonId !=181 || buttonId !=182) {
Misc.println("Unhandled hair button: "  + buttonId);
}
p.appearanceUpdateReq = true;
p.updateReq = true;

break;
	case 662:
		/**
		 * Summoning IDS
		 */
		if (buttonId == 50) {
			if (p.summonedFamiliar)
				Server.engine.rebuildNPCs();
			if (p.summonedFamiliar)
				p.callFamiliar = true;
		}
		if (buttonId == 52) {
			if (p.summonedFamiliar)
				p.familiarDissMiss = true;
		}
	break;
        case 107:
				if(p.items[buttonId2] == 995) {
				    p.getActionSender().sendMessage(p, "You can't offer money!");
				    return;
				}
				p.GrandExchange.offerItem(buttonId2);
			break;
        case 105:
			switch(buttonId) {
			    case 194:
				Object[] o = new Object[]{"Grand Exchange Item Search"};
			        p.getActionSender().setGeSearch(p, o);
				break;
			}
			p.GrandExchange.handleButtons(buttonId);
			break;
        case 387:
            /*
             * Equipment tab.
             */
            if (buttonId == 55) {
                p.setEquipmentBonus();
                p.getActionSender().showInterface(p, 667);
                p.getActionSender().setInventory(p, 149);
                p.getActionSender().setItems(p, -1327, 64209, 93, p.items, p.itemsN);
                p.getActionSender().setItems(p, -1328, 64208, 94, p.equipment, p.equipmentN);
            }
            break;

	case 271:
		int prayer = -1;
		String name = "";
		if (buttonId == 5) { //Thick Skin
			prayer = 0;
			name = "Thick Skin";
			if (!p.usingPrayer(prayer)) p.switchPrayers(new int[] {5, 13, 25, 26}, prayer);
		}
		if (buttonId == 5) { //Thick Skin
			prayer = 0;
			name = "Thick Skin";
			if (!p.usingPrayer(prayer)) p.switchPrayers(new int[] {5, 13, 25, 26}, prayer);
		}
		if (buttonId == 7) { //Burst of Strength
			prayer = 1;
			name = "Burst of Strength";
			if (!p.usingPrayer(prayer)) p.switchPrayers(new int[] {3, 4, 6, 11, 12, 14, 20, 21, 25, 26}, prayer);
		}
		if (buttonId == 9) { //Clarity of Thought
			prayer = 2;
			name = "Clarity of Thought";
			if (!p.usingPrayer(prayer)) p.switchPrayers(new int[] {3, 4, 7, 11, 12, 15, 20, 21, 25, 26}, prayer);
		}
		if (buttonId == 11) { //Sharp Eye
			prayer = 3;
			name = "Sharp Eye";
			if (!p.usingPrayer(prayer)) p.switchPrayers(new int[] {1, 2, 4, 6, 7, 11, 12, 14, 15, 20, 21, 25, 26}, prayer);
		}
		if (buttonId == 13) { //Mystic Will
			prayer = 4;
			name = "Mystic Will";
			if (!p.usingPrayer(prayer)) p.switchPrayers(new int[] {1, 2, 3, 6, 7, 11, 12, 14, 15, 20, 21, 25, 26}, prayer);
		}
		if (buttonId == 15) { //Rock Skin
			prayer = 5;
			name = "Rock Skin";
			if (!p.usingPrayer(prayer)) p.switchPrayers(new int[] {0, 13, 25, 26}, prayer);
		}
		if (buttonId == 17) { //Superhuman Strength
			prayer = 6;
			name = "Superhuman Strength";
			if (!p.usingPrayer(prayer)) p.switchPrayers(new int[] {1, 3, 4, 11, 12, 14, 20, 21, 25, 26}, prayer);
		}
		if (buttonId == 19) { //Improved Reflexes
			prayer = 7;
			name= "Improved Reflexes";
			if (!p.usingPrayer(prayer)) p.switchPrayers(new int[] {2, 3, 4, 11, 12, 15, 20, 21, 25, 26}, prayer);
		}
		if (buttonId == 21) { //Rapid Restore
			prayer = 8;
			name = "Rapid Restore";
		}
		if (buttonId == 23) { //Rapid Heal
			prayer = 9;
			name = "Rapid Heal";
		}
		if (buttonId == 25) { //Protect Item
                    if (p.bhPickup > 0) {
                        p.getActionSender().sendMessage(p, "You cannot use this prayer while you have a penalty.");
                        p.togglePrayer(10, 0);
                        return;
                    }   else {
			prayer = 10;
			name = "Protect Item";
		}
                }
		if (buttonId == 27) { //Hawk Eye
			prayer = 11;
			name = "Hawk Eye";
			if (!p.usingPrayer(prayer)) p.switchPrayers(new int[] {1, 2, 3, 4, 6, 7, 12, 14, 15, 20, 21, 25, 26}, prayer);
		}
		if (buttonId == 29) { //Mystic Lore
			prayer = 12;
			name = "Mystic Lore";
			if (!p.usingPrayer(prayer)) p.switchPrayers(new int[] {1, 2, 3, 4, 6, 7, 11, 14, 15, 20, 21, 25, 26}, prayer);
		}
		if (buttonId == 31) { //Steel Skin
			prayer = 13;
			name = "Steel Skin";
			if (!p.usingPrayer(prayer)) p.switchPrayers(new int[] {0, 5, 25, 26}, prayer);
		}
		if (buttonId == 33) { //Ultimate Strength
			prayer = 14;
			name = "Ultimate Strength";
			if (!p.usingPrayer(prayer)) p.switchPrayers(new int[] {1, 3, 4, 6, 11, 12, 20, 21, 25, 26}, prayer);
		}
		if (buttonId == 35) { //Incredible Reflexes
			prayer = 15;
			name = "Incredible Reflexes";
			if (!p.usingPrayer(prayer)) p.switchPrayers(new int[] {2, 3, 4, 7, 11, 12, 20, 21, 25, 26}, prayer);
		}
		if (buttonId == 53) { //Protect from Summoning
			prayer = 16;
			name = "Protect from Summoning";
			if (!p.usingPrayer(prayer)) {
				p.switchPrayers(new int[] {22, 23, 24}, prayer);
				if (p.usingPrayer(17)) {
					p.headIconPrayer = 10;
				} else if (p.usingPrayer(18)) {
					p.headIconPrayer = 9;
				} else if (p.usingPrayer(19)) {
					p.headIconPrayer = 8;
				} else {
					p.headIconPrayer = 7;
				}
			} else {
				if (p.usingPrayer(17)) {
					p.headIconPrayer = 2;
				} else if (p.usingPrayer(18)) {
					p.headIconPrayer = 1;
				} else if (p.usingPrayer(19)) {
					p.headIconPrayer = 0;
				} else {
					p.headIconPrayer = -1;
				}
			}
		}
		if (buttonId == 37) { //Protect from Magic
			prayer = 17;
			name = "Deflect Mage";
			if (p.prayOff == 0 || p.bhPickup > 0 || p.inBounty == false) {
			} else {
                        p.getActionSender().sendMessage(p, "Your Prayer has been disabled in bounty hunter(unless you have pen).");
                        p.togglePrayer(17, 0);
			return;
			}
			if (!p.usingPrayer(prayer)) {
				p.switchPrayers(new int[] {18, 19, 22, 23, 24}, prayer);
				if (p.usingPrayer(16)) {
					p.headIconPrayer = 10;
				} else {
					p.headIconPrayer = 2;
				}
			} else {
				if (p.usingPrayer(16)) {
					p.headIconPrayer = 7;
				} else {
					p.headIconPrayer = -1;
				}
			}
		}
		if (buttonId == 39) { //Protect from Missiles
			prayer = 18;
			name = "Deflect Range";
			if (p.prayOff == 0 || p.bhPickup > 0 || p.inBounty == false) {
			} else {
                        p.getActionSender().sendMessage(p, "Your Prayer has been disabled in bounty hunter(unless you have pen).");
                        p.togglePrayer(18, 0);
			return;
			}
			if (!p.usingPrayer(prayer)) {
				p.switchPrayers(new int[] {17, 19, 22, 23, 24}, prayer);
				if (p.usingPrayer(16)) {
					p.headIconPrayer = 9;
				} else {
					p.headIconPrayer = 1;
				}
			} else {
				if (p.usingPrayer(16)) {
					p.headIconPrayer = 7;
				} else {
					p.headIconPrayer = -1;
				}
			}
		}
		if (buttonId == 41) { //Protect from Melee
			prayer = 19;
			name = "Deflect Melee";
			if (p.prayOff == 0 || p.bhPickup > 0 || p.inBounty == false) {
			} else {
                        p.getActionSender().sendMessage(p, "Your Protect from Melee, Missle, and Magic Prayers have been temporarily");
                        p.getActionSender().sendMessage(p, "disabled in bounty hunter(unless you have a pickup penalty).");
                        p.togglePrayer(19, 0);
			return;
			}
			if (!p.usingPrayer(prayer)) {
				p.switchPrayers(new int[] {17, 18, 22, 23, 24}, prayer);
				if (p.usingPrayer(16)) {
					p.headIconPrayer = 8;
				} else {
					p.headIconPrayer = 0;
				}
			} else {
				if (p.usingPrayer(16)) {
					p.headIconPrayer = 7;
				} else {
					p.headIconPrayer = -1;
				}
			}
		}
		if (buttonId == 43) { //Eagle Eye
			prayer = 20;
			name = "Leech Range";
			if (!p.usingPrayer(prayer)) p.switchPrayers(new int[] {1, 2, 3, 4, 6, 7, 11, 12, 14, 15, 25, 26}, prayer);
		}
		if (buttonId == 45) { //Mystic Might
			prayer = 21;
			name = "Leech Mage";
			if (!p.usingPrayer(prayer)) p.switchPrayers(new int[] {1, 2, 3, 4, 6, 7, 11, 12, 14, 15, 25, 26}, prayer);
		}
		if (buttonId == 47) { //Retribution
			prayer = 22;
			name = "wrath";
			if (!p.usingPrayer(prayer)) {
				p.switchPrayers(new int[] {16, 17, 18, 19, 23, 24}, prayer);
				p.headIconPrayer = 3;
			} else {
				p.headIconPrayer = -1;
			}
		}
		if (buttonId == 49) { //Redemption
			prayer = 23;
			name = "Redemption";
			if (!p.usingPrayer(prayer)) {
				p.switchPrayers(new int[] {16, 17, 18, 19, 22, 24}, prayer);
				p.headIconPrayer = 5;
			} else {
				p.headIconPrayer = -1;
			}
		}
		if (buttonId == 51) { //Smite
			prayer = 24;
			name = "Soul Split";
			if (!p.usingPrayer(prayer)) {
				p.switchPrayers(new int[] {16, 17, 18, 19, 22, 23}, prayer);
				p.headIconPrayer = 4;
			} else {
				p.headIconPrayer = -1;
			}
		}
		if (buttonId == 55) { //Chivalry
			prayer = 25;
			name = "Chivalry";
			if (!p.usingPrayer(prayer)) p.switchPrayers(new int[] {0, 1, 2, 3, 4, 5, 6, 7, 11, 12, 13, 14, 15, 20, 21, 26}, prayer);
		}
		if (buttonId == 57) { //Turmoil
			prayer = 26;
			name = "Turmoil";
				p.requestAnim(7660,1);
			if (!p.usingPrayer(prayer)) p.switchPrayers(new int[] {0, 1, 2, 3, 4, 5, 6, 7, 11, 12, 13, 14, 15, 20, 21, 25}, prayer);
}
		if (!p.canPray(prayer)) {
			p.headIconPrayer = -1;
			if (p.getLevelForXP(5) < p.prayers[prayer][0] && p.skillLvl[5] > 0) {
				p.getActionSender().dialogue(p, -1, -1, "", 1, "You need a <col=000080>Prayer</col> level of "+(int)p.prayers[prayer][0]+" to use <col=000080>"+name, "", "", "");
			}
			break;
		}
		if (prayer == 25 && p.getLevelForXP(1) < 1) {
			p.getActionSender().dialogue(p, -1, -1, "", 1, "You need a <col=000080>Defence</col> level of 1 to use <col=000080>"+name, "", "", "");
			break;
		}
		if (prayer == 26 && p.getLevelForXP(1) < 30) {
			p.getActionSender().dialogue(p, -1, -1, "", 1, "You need a <col=000080>Defence</col> level of 30 to use <col=000080>"+name, "", "", "");
			break;
		}
		p.togglePrayer(prayer, p.usingPrayer(prayer) ? 0 : 1);
		if (p.usingPrayer(prayer)) {
			p.prayerSounds(prayer);
		} else {
			p.getActionSender().addSoundEffect(p, 2663, 1, 0, 0);
		}
		p.updateReq = true;
		p.appearanceUpdateReq = true;
	break;
            case 620:
            case 621:
                Server.engine.shopHandler.handleoption(p,interfaceId,buttonId,buttonId2,packetId);
                break;


        case 750:
            /*
             * Running button next to minimap.
             */
        case 261:
            /*
             * Settings tab.
             */
            if (buttonId == 1) {
                if (!p.isRunning) {
                    p.isRunning = true;
                    p.getActionSender().setConfig(p, 173, 1);
                } else {
                    p.isRunning = false;
                    p.getActionSender().setConfig(p, 173, 0);
                }
            } else if (buttonId == 14) {
                p.getActionSender().showInterface(p, 742);
            } else if (buttonId == 16) {
                p.getActionSender().showInterface(p, 743);
            }
            else if (buttonId == 3) {
        p.splitChat = !p.splitChat;
        p.getActionSender().setConfig(p, 287, p.splitChat ? 1 : 0);
    if(p.splitChat) {
        p.stream.createFrameVarSizeWord(152);
        p.stream.writeString("s");
        p.stream.writeDWord(83);
        p.stream.endFrameVarSize();
    }
}
            break;

        case 464:
            /*
             * Emote tab.
             */
            if (buttonId == 2) // Yes
                p.requestAnim(855, 0);
            else if (buttonId == 3) // No
                p.requestAnim(856, 0);
            else if (buttonId == 4) // Bow
                p.requestAnim(858, 0);
            else if (buttonId == 5) // Angry
                p.requestAnim(859, 0);
            else if (buttonId == 6)  // Think
                p.requestAnim(857, 0);
            else if (buttonId == 7) // Wave
                p.requestAnim(863, 0);
            else if (buttonId == 8) // Shrug
                p.requestAnim(2113, 0);
            else if (buttonId == 9) // Cheer
                p.requestAnim(862, 0);
            else if (buttonId == 10) // Beckon
                p.requestAnim(864, 0);
            else if (buttonId == 11) // Laugh
                p.requestAnim(861, 0);
            else if (buttonId == 12) // Joy jump
                p.requestAnim(2109, 0);
            else if (buttonId == 13) // Yawn
                p.requestAnim(2111, 0);
            else if (buttonId == 14) // Dance
                p.requestAnim(866, 0);
            else if (buttonId == 15) // Jig
                p.requestAnim(2106, 0);
            else if (buttonId == 16) // Spin
                p.requestAnim(2107, 0);
            else if (buttonId == 17) // Headbang
                p.requestAnim(2108, 0);
            else if (buttonId == 18) // Cry
                p.requestAnim(860, 0);
            else if (buttonId == 19) { // Blow kiss
                p.requestGFX(574, 0);
                p.requestAnim(0x558, 0);
            } else if (buttonId == 20) // Panic
                p.requestAnim(2105, 0);
            else if (buttonId == 21) // Raspberry
                p.requestAnim(2110, 0);
            else if (buttonId == 22) // Clap
                p.requestAnim(865, 0);
            else if (buttonId == 23) // Salute
                p.requestAnim(2112, 0);
            else if (buttonId == 24) // Goblin bow
                p.requestAnim(0x84F, 0);
            else if (buttonId == 25) // Goblin salute
                p.requestAnim(0x850, 0);
            else if (buttonId == 26) // Glass box
                p.requestAnim(1131, 0);
            else if (buttonId == 27) // Climb rope
                p.requestAnim(1130, 0);
            else if (buttonId == 28) // Lean
                p.requestAnim(1129, 0);
            else if (buttonId == 29) // Glass wall
                p.requestAnim(1128, 0);
            else if (buttonId == 30) // Head slap
                p.requestAnim(4275, 0);
            else if (buttonId == 31) // Stomp
                p.requestAnim(1745, 0);
            else if (buttonId == 32) // Flap
                p.requestAnim(4280, 0);
            else if (buttonId == 33) // Idea
                p.requestAnim(4276, 0);
            else if (buttonId == 34) // Zombie walk
                p.requestAnim(3544, 0);
            else if (buttonId == 35) // Dombie dance
                p.requestAnim(3543, 0);
            else if (buttonId == 36) { // Zombie hand grab
                p.requestGFX(1244, 0);
                p.requestAnim(7272, 0);
            } else if (buttonId == 37) // Scared
                p.requestAnim(2836, 0);
            else if (buttonId == 38) // Rabbit hop
                p.requestAnim(6111, 0);
            else if (buttonId == 39) { // Skillcape emotes
			if (!p.skillCapeEquiped()) {
				p.getActionSender().sendMessage(p, "You need a skill cape equiped to perform this animation.");
				return;
			}
			if (p.animClickDelay > 0) {
				return;
			}
			if (p.equipment[1] == 9747 || p.equipment[1] == 10639 || p.equipment[1] == 9748) { //attack
                    		p.requestGFX(823,1);
	    	    		p.requestAnim(4959,1);
                	}
			if (p.equipment[1] == 9753 || p.equipment[1] == 10641 || p.equipment[1] == 9754) { //Defence
                    		p.requestGFX(824,1);
	    			p.requestAnim(4961,1);
                	}
			if (p.equipment[1] == 9750 || p.equipment[1] == 10640 || p.equipment[1] == 9751) { //Strength
                    		p.requestGFX(828,1);
	    			p.requestAnim(4981,1);
                	}
			if (p.equipment[1] == 9768 || p.equipment[1] == 10647 || p.equipment[1] == 9769) { //Hitpoints
                    		p.requestGFX(833,1);
	    			p.requestAnim(4971,1);
                	}
			if (p.equipment[1] == 9756 || p.equipment[1] == 10642 || p.equipment[1] == 9757) { //Range
                    		p.requestGFX(832,1);
	    			p.requestAnim(4973,1);
                	}
			if (p.equipment[1] == 9759 || p.equipment[1] == 10643 || p.equipment[1] == 9760) { //Prayer
                    		p.requestGFX(829,1);
	    			p.requestAnim(4979,1);
                	}
			if (p.equipment[1] == 9762 || p.equipment[1] == 10644 || p.equipment[1] == 9763) { //Magic
                    		p.requestGFX(813,1);
	    			p.requestAnim(4939,1);
                	}
			if (p.equipment[1] == 9801 || p.equipment[1] == 10658 || p.equipment[1] == 9802) { //Cooking
                    		p.requestGFX(821,1);
	    			p.requestAnim(4955,1);
                	}
			if (p.equipment[1] == 9807 || p.equipment[1] == 10660 || p.equipment[1] == 9808) { //Woodcutting
                    		p.requestGFX(822,1);
	    			p.requestAnim(4957,1);
                	}
			if (p.equipment[1] == 9783 || p.equipment[1] == 10652 || p.equipment[1] == 9784) { //Fletching
                    		p.requestGFX(812, 1);
	    			p.requestAnim(4937, 1);
                	}
			if (p.equipment[1] == 9798 || p.equipment[1] == 10657 || p.equipment[1] == 9799) { //Fishing
                   		p.requestGFX(819,1);
	    			p.requestAnim(4951,1);
                	}
			if (p.equipment[1] == 9804 || p.equipment[1] == 10659 || p.equipment[1] == 9805) { //Firemaking
                    		p.requestGFX(831,1);
	   			p.requestAnim(4975,1);
                	}
			if (p.equipment[1] == 9780 || p.equipment[1] == 10651 || p.equipment[1] == 9781) { //Crafting
                    		p.requestGFX(818,1);
	   			p.requestAnim(4949,1);
                	}
			if (p.equipment[1] == 9795 || p.equipment[1] == 10656 || p.equipment[1] == 9796) { //Smithing
                    		p.requestGFX(815,1);
	    			p.requestAnim(4943,1);
                	}
			if (p.equipment[1] == 9792 || p.equipment[1] == 10655 || p.equipment[1] == 9793) { //Mining
                    		p.requestGFX(814,1);
	    			p.requestAnim(4941,1);
                	}
			if (p.equipment[1] == 9774 || p.equipment[1] == 10649 || p.equipment[1] == 9775) { //Herblore
                    		p.requestGFX(835,1);
	    			p.requestAnim(4969,1);
                	}
			if (p.equipment[1] == 9771 || p.equipment[1] == 10648 || p.equipment[1] == 9772) { //Agility
                    		p.requestGFX(830,1);
	    			p.requestAnim(4977,1);
                	}
			if (p.equipment[1] == 9777 || p.equipment[1] == 10650 || p.equipment[1] == 9778) { //Theiving
                    		p.requestGFX(826,1);
	    			p.requestAnim(4965,1);
                	}
			if (p.equipment[1] == 9786 || p.equipment[1] == 10653 || p.equipment[1] == 9787) { //Slayer
                    		p.requestGFX(1656, 1);
	   			p.requestAnim(4967, 1);
                	}
			if (p.equipment[1] == 9810 || p.equipment[1] == 10661 || p.equipment[1] == 9811) { //Farming
                    		p.requestGFX(825,1);
	    			p.requestAnim(4963,1);
                	}
			if (p.equipment[1] == 9765 || p.equipment[1] == 10645 || p.equipment[1] == 9766) { //Runecrafting
                    		p.requestGFX(817,1);
	    			p.requestAnim(4947,1);
                	}
			if (p.equipment[1] == 9789 || p.equipment[1] == 10654 || p.equipment[1] == 9790) { //Construction
                   		 p.requestGFX(820,1);
	    			p.requestAnim(4953,1);
                	}
			if (p.equipment[1] == 12524 || p.equipment[1] == 12169 || p.equipment[1] == 12170) { //Summoning
                    		p.requestGFX(1515,1);
	    			p.requestAnim(8525,1);
                	}
			if (p.equipment[1] == 9948 || p.equipment[1] == 10646 || p.equipment[1] == 9949) { // hunter
            			p.requestGFX(907, 0);
            			p.requestAnim(5158, 0);
          		}
			if (p.equipment[1] == 9813 || p.equipment[1] == 10662) { //Quest
                    		p.requestGFX(816,1);
	    			p.requestAnim(4945,1);
                	}
			p.animClickDelay = 10;
	    }
            else if (buttonId == 40) // Snowman dance
                p.requestAnim(7531, 0);
            else if (buttonId == 41) { // Air guitar
                p.requestAnim(2414, 0);
                p.requestGFX(1537, 0);
            } else if (buttonId == 42)  { // Safety first
                p.requestAnim(8770, 0);
                p.requestGFX(1553, 0);
            }
        break;
	case 335:
	case 334:
	case 336:
		p.pTrade.buttons.handleButton(interfaceId, packetId, buttonId, buttonId2);
	break;

	case 75:
	case 76:
	case 77:
	case 78:
	case 79:
	case 80:
	case 81:
	case 82:
	case 83:
	case 84:
	case 85:
	case 86:
	case 87:
	case 88:
	case 89:
	case 91:
	case 92:
	case 93:
		if (buttonId == 2) {
			p.fightStyle = 1;
		}
		if (buttonId == 3) {
			p.fightStyle = 2;
		}
		if (buttonId == 4) {
			p.fightStyle = 3;
		}
		if (buttonId == 5) {
			p.fightStyle = 4;
		}
		if (buttonId == 24 || buttonId == 26 || buttonId == 27) {
			p.autoRetaliate ^= true;
			p.getActionSender().setConfig(p, 172, p.autoRetaliate ? 0 : 1);
		}
		if (buttonId == 8 || buttonId == 10 || buttonId == 11) {
			if (p.specAmount == 0) {
				p.getActionSender().sendMessage(p, "You do not have enough special energy.");
				p.usingSpecial = false;
				p.getActionSender().setConfig(p, 301, 0);
				break;
			}
			p.usingSpecial ^= true;
			if (p.equipment[3] == 4153) {
				if (p.attacking != null) {
					if (p.specAmount >= 500) {
						p.combatDelay = 0;
						p.usingSpecial = true;
						p.getActionSender().setConfig(p, 301, 1);
						p.attackPlayer();
					}
				}
			}
			p.getActionSender().setConfig(p, 301, p.usingSpecial ? 1 : 0);
		}

            	Misc.println("[" + p.username + "] Unhandled button: " + interfaceId + ", " + buttonId + ":" + buttonId2);
	break;

    case 274:
                if (buttonId == 3) {
                    p.getActionSender().setTab(p, 75, 259);
                    p.getActionSender().setString(p, "<col=33FF33>FREE PLAYERS", 259, 1);
                    p.getActionSender().setString(p, "<col=FFFFFF>Veng runes", 259, 2);
                    p.getActionSender().setString(p, "<col=FFFFFF>Barrage runes", 259, 3);
                    p.getActionSender().setString(p, "<col=FFFFFF>Food", 259, 4);
                    p.getActionSender().setString(p, "<col=FFFFFF>Pots", 259, 5);
                    p.getActionSender().setString(p, "<col=33FF33>DONATORS", 259, 6);
                    p.getActionSender().setString(p, "", 259, 7);
                    p.getActionSender().setString(p, "", 259, 8);
                    p.getActionSender().setString(p, "<col=33FF33>X COMMANDS", 259, 9);
                    p.getActionSender().setString(p, "", 259, 10);
                    p.getActionSender().setString(p, "", 259, 11);
                    p.getActionSender().setString(p, "", 259, 12);
                    p.getActionSender().setString(p, "", 259, 13);
                    p.getActionSender().setString(p, "", 259, 14);
                    p.getActionSender().setString(p, "", 259, 15);
                    p.getActionSender().setString(p, "", 259, 16);
                    p.getActionSender().setString(p, "", 259, 17);
                    p.getActionSender().setString(p, "", 259, 18);
                    p.getActionSender().setString(p, "", 259, 19);
                    p.getActionSender().setString(p, "<col=FFFFFF>GDZ FFA", 259, 20);
                    p.getActionSender().setString(p, "<col=FFFFFF>Recharge Prayer", 259, 21);
                    p.getActionSender().setString(p, "<col=FFFFFF>Recharge Spec", 259, 22);
                    p.getActionSender().setString(p, "<col=FFFFFF>Open Bank", 259, 23);
                    p.getActionSender().setString(p, "<col=FFFFFF>Open Shop", 259, 24);
                    p.getActionSender().setString(p, "<col=FFFFFF>CAPE", 259, 25);
                    p.getActionSender().setString(p, "<col=FFFFFF>Respect", 259, 26);
                    p.getActionSender().setString(p, "", 259, 27);
                    p.getActionSender().setString(p, "", 259, 28);
                    p.getActionSender().setString(p, "", 259, 29);



                }
                if (buttonId == 26) {
                 p.getExperience ^= true;
				p.getActionSender().sendMessage(p, "You will now receive "+(p.getExperience ? "" : "no ")+"experience while in combat.");
			}
                if (buttonId == 17) {
                  if(p.InBounty == 1) {
		p.getActionSender().sendMessage(p, "You cannot teleport out of Bounty Hunter");
		return;
	}
				if (p.attackedBy != null) {
					p.getActionSender().sendMessage(p, "You cannot use this command while in combat.");
					return;
				}
				if (p.jailed > 0) {
					p.getActionSender().sendMessage(p, "You are jailed!");
					return;
				}
				p.teleportTo(3271, 3683, 4, 4, 0, 8939, 8941, 1678, 0, 1679, 0);
                                p.getActionSender().sendMessage(p, "<img=1>Welcome to CLANWARS SAFE PK enter the white portal!<img=1>");
                }

                if (buttonId == 13) {//home
                     if(p.InBounty == 1) {
		p.getActionSender().sendMessage(p, "You cannot teleport out of Bounty Hunter");
		return;
	}
				if (p.attackedBy != null) {
					p.getActionSender().sendMessage(p, "You cannot use this command while in combat.");
					return;
				}
				if (p.jailed > 0) {
					p.getActionSender().sendMessage(p, "You are jailed!");
					return;
				}
				p.teleportTo(2590, 3416, 0, 4, 0, 8939, 8941, 1678, 0, 1679, 0);
                }
                if (buttonId == 15) {
                    double KDR = ((double)p.KC)/((double)p.DC);
				p.requestForceChat2("I have killed "+p.KC+" nubz and died "+p.DC+" times.");
                }
		if (buttonId == 16) {
                     if(p.InBounty == 1) {
		p.getActionSender().sendMessage(p, "You cannot teleport out of Bounty Hunter");
		return;
	}
				if (p.attackedBy != null) {
					p.getActionSender().sendMessage(p, "You cannot use this command while in combat.");
					return;
				}
				if (p.jailed > 0) {
					p.getActionSender().sendMessage(p, "You are jailed!");
					return;
				}
				p.teleportTo(3289, 3887, 0, 4, 0, 8939, 8941, 1678, 0, 1679, 0);
                }

                if (buttonId == 27) {
                    try {
					p.message("Saving backup...");
					Engine.fileManager.saveBackup(p);
					p.message("Backup saved.");
				} catch (Exception e) {
					p.message("Error saving.");
				}
                }
                if (buttonId == 28) {
                  if (p.attackedBy != null) {
					p.getActionSender().sendMessage(p, "You cannot use this command while in combat.");
					return;
				}
				if (p.jailed > 0) {
					p.getActionSender().sendMessage(p, "You are jailed!");
					return;
				}
					int inventory = (28 - Engine.playerItems.freeSlotCount(p));
	int totalPrice = 0;
	for(int i = 0; i < inventory; i++){
		int itemID = Engine.playerItems.getItemID(p, i);
		int amount = Engine.playerItems.invItemCount(p, itemID);
		int sellPrice = 0;
		if(Engine.items.isUntradable(itemID) || (itemID == 995) ||itemID == 555 || itemID == 556 || itemID == 557 || itemID == 558 || itemID == 559 || itemID == 560 | itemID == 9075 || itemID == 561 || itemID == 562 || itemID == 563 || itemID == 565 || itemID == 566 || itemID == 553 || itemID == 567 ||itemID == 146 || itemID == 3025 || itemID == 158 || itemID == 170 || itemID == 140 || itemID == 164 || itemID == 145 || itemID == 3024 || itemID == 157 || itemID == 169 || itemID == 139 || itemID == 163|| itemID == 2434|| itemID == 2444|| itemID == 6685){
			p.getActionSender().sendMessage(p, "Item " + (i+1) + " cannot be sold.");
			p.getActionSender().addSoundEffect(p, 4039, 1, 0, 0);
              		}


		else{
			if (totalPrice < 0 || totalPrice > 2146000000) {
			return;
			} else {
			sellPrice = (p.getItemValue(itemID) * amount);
			Engine.playerItems.deleteItem(p, itemID, i, amount);
			Engine.playerItems.addItem(p, 995, sellPrice);
			}
		}
		totalPrice += sellPrice;
	}
	if (totalPrice < 0 || totalPrice > 2146000000) {
	p.getActionSender().sendMessage(p, "Your sale could not be completed, please try again.");
	return;
	}
	p.getActionSender().addSoundEffect(p, 4042, 1, 0, 0);
	p.getActionSender().sendMessage(p, "You have just sold your items for <col=336600>"+totalPrice+" coins.");
                }
                if (buttonId == 19) {
		if(p.InBounty == 1) {
		p.getActionSender().sendMessage(p, "You cannot teleport out of Bounty Hunter");
		return;
	}
				if (p.attackedBy != null) {
					p.getActionSender().sendMessage(p, "You cannot use this command while in combat.");
					return;
				}
				if (p.jailed > 0) {
					p.getActionSender().sendMessage(p, "You are jailed!");
					return;
				}
				p.teleportTo(2663, 3307, 0, 4, 0, 8939, 8941, 1678, 0, 1679, 0);
                                p.getActionSender().sendMessage(p, "Welcome to the thieveing area!");
                }

                if (buttonId == 20) {//kq
                    if(p.InBounty == 1) {
		p.getActionSender().sendMessage(p, "You cannot teleport out of Bounty Hunter");
		return;
	}
				if (p.attackedBy != null) {
					p.getActionSender().sendMessage(p, "You cannot use this command while in combat.");
					return;
				}
				if (p.jailed > 0) {
					p.getActionSender().sendMessage(p, "You are jailed!");
					return;
				}
				p.teleportTo(3485, 9509, 0, 4, 0, 8939, 8941, 1678, 0, 1679, 0);
                    p.getActionSender().sendMessage(p, "Welcome to Edgevile 1v1 pk area!");
	}

                if (buttonId == 21) {//pvp
            if(p.InBounty == 1) {
            p.getActionSender().sendMessage(p, "You cannot teleport out of Bounty Hunter");
            return;
            }
            if (p.attackedBy != null) {
            p.getActionSender().sendMessage(p, "You cannot use this command while in combat.");
            return;
            }
            if (p.jailed > 0) {
            p.getActionSender().sendMessage(p, "You are jailed!");
            return;
            }
                p.teleportTo(p.absX, p.absY, 4, 4, 0, 8939, 8941, 1576, 0, 1577, 0); //Change absx & absy 
                      p.getActionSender().showInterface(p, 255);
                     p.getActionSender().setString(p, "Welcome to the alternate pvp world, this is dangerous so watch the fuck out", 255, 3); //Change TEXT
            }
                if (buttonId == 30) {//return
                   if(p.InBounty == 1) {
		p.getActionSender().sendMessage(p, "You cannot teleport out of Bounty Hunter");
		return;
	}
                   p.getActionSender().removeOverlay(p);
                   p.teleportTo(p.absX, p.absY, 0, 4, 0, 8939, 8941, 1678, 0, 1679, 0);
                           p.getActionSender().sendMessage(p, "welcome back from pvp!");
	p.getActionSender().removeShownInterface(p);
                }
                if (buttonId == 32) {//donor
        if (Server.socketListener.getAddress(p.socket.socket).equals("123123123123")){
        Server.engine.shopHandler.openshop(p, 8);
        }
        if (p.Donator == 0 && p.rights == 0) {
        p.getActionSender().showChatboxInterface(p, 243);
        p.getActionSender().animateInterfaceId(p, 9835, 243, 2);
        p.getActionSender().setNPCId(p, 1703, 243, 2);
        p.getActionSender().setString(p, "Runescape", 243, 3);
        p.getActionSender().setString(p, "This is a DONATOR only zone! This zone has items things as", 243, 4);
        p.getActionSender().setString(p, "shops special bosses and sercrets. PM a admin ", 243, 5);
        p.getActionSender().setString(p, "To buy donator for $10. ", 243, 6);
        } else {

                   p.getActionSender().removeOverlay(p);
                   p.teleportTo(2567, 9672, 0, 4, 0, 8939, 8941, 1678, 0, 1679, 0);
                           p.getActionSender().sendMessage(p, "thanks for donating");
	p.getActionSender().removeShownInterface(p);
                }
}
				if (buttonId == 22) {//ardy multi
				if(p.InBounty == 1) {
		p.getActionSender().sendMessage(p, "You cannot teleport out of Bounty Hunter");
		return;
	}
				if (p.attackedBy != null) {
					p.getActionSender().sendMessage(p, "You cannot use this command while in combat.");
					return;
				}
				if (p.jailed > 0) {
					p.getActionSender().sendMessage(p, "You are jailed!");
					return;
				}
				p.teleportTo(3269, 3167, 4, 4, 0, 8939, 8941, 1678, 0, 1679, 0);
                                 p.getActionSender().sendMessage(p, "Welcome to X's pkingzone!");
                                 p.getActionSender().sendMessage(p, "As a gift you will u get extra ep until u die hehe");
                                }


				if (buttonId == 18) {//Corp
				if(p.InBounty == 1) {
		p.getActionSender().sendMessage(p, "You cannot teleport out of Bounty Hunter");
		return;
	}
				if (p.attackedBy != null) {
					p.getActionSender().sendMessage(p, "You cannot use this command while in combat.");
					return;
				}
				if (p.jailed > 0) {
					p.getActionSender().sendMessage(p, "You are jailed!");
					return;
				}
				p.teleportTo(2897, 3618, 0, 4, 0, 8939, 8941, 1678, 0, 1679, 0);
                }
				if (buttonId == 24) {//GreenDragsPK PK
					if(p.InBounty == 1) {
		p.getActionSender().sendMessage(p, "You cannot teleport out of Bounty Hunter");
		return;
	}
				if (p.attackedBy != null) {
					p.getActionSender().sendMessage(p, "You cannot use this command while in combat.");
					return;
				}
				if (p.jailed > 0) {
					p.getActionSender().sendMessage(p, "You are jailed!");
					return;
				}
				p.teleportTo(3356, 3698, 0, 4, 0, 8939, 8941, 1678, 0, 1679, 0);
                }

				if (buttonId == 23) {// varrock 1v1
				if(p.InBounty == 1) {
		p.getActionSender().sendMessage(p, "You cannot teleport out of Bounty Hunter");
		return;
	}
				if (p.attackedBy != null) {
					p.getActionSender().sendMessage(p, "You cannot use this command while in combat.");
					return;
				}
				if (p.jailed > 0) {
					p.getActionSender().sendMessage(p, "You are jailed!");
					return;
				}
				p.teleportTo(3212, 3424, 4, 4, 0, 8939, 8941, 1678, 0, 1679, 0);
                }

                if (buttonId == 14) {//BH
                     if(p.InBounty == 1) {
		p.getActionSender().sendMessage(p, "You cannot teleport out of Bounty Hunter");
		return;
	}
				if (p.attackedBy != null) {
					p.getActionSender().sendMessage(p, "You cannot use this command while in combat.");
					return;
				}
				if (p.jailed > 0) {
					p.getActionSender().sendMessage(p, "You are jailed!");
					return;
				}
				p.teleportTo(3165, 3680, 4, 4, 0, 8939, 8941, 1678, 0, 1679, 0);
                }
                if (buttonId == 29) {//staffzone
                     if(p.rights <= 0) {
		p.getActionSender().sendMessage(p, "You cannot teleport here");
		return;
	}
                     if(p.InBounty == 1) {
		p.getActionSender().sendMessage(p, "You cannot teleport out of Bounty Hunter");
		return;
	}
				if (p.attackedBy != null) {
					p.getActionSender().sendMessage(p, "You cannot use this command while in combat.");
					return;
				}
				if (p.jailed > 0) {
					p.getActionSender().sendMessage(p, "You are jailed!");
					return;
				}
			p.teleportTo(2723, 3283, 0, 4, 0, 8939, 8941, 1576, 0, 1577, 0);
                }

           case 205:
                if (buttonId == 101){
                    p.look[0] = 3;
				p.look[1] = 10;
        			p.look[2] = 18;
        			p.look[3] = 26;
        			p.look[4] = 33;
        			p.look[5] = 36;
        			p.look[6] = 42;
                    		p.gender = 0;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
                }
                if (buttonId == 103){
                    p.look[0] = 48; // Hair
                    		p.look[1] = 1000; // Beard
                    		p.look[2] = 57; // Torso
                    		p.look[3] = 64; // Arms
                    		p.look[4] = 68; // Bracelets
                    		p.look[5] = 77; // Legs
                    		p.look[6] = 80; // Shoes
                    		p.gender = 1;
				p.appearanceUpdateReq = true;
				p.updateReq = true;
                }
                if (buttonId == 88){
                    p.getActionSender().removeShownInterface(p);

                }
                if (buttonId == 100){
                    p.color[4] = 7;
                    p.appearanceUpdateReq = true;
                    p.updateReq = true;
                }
                break;
	case 90:
		for (int i = 0; i < p.regularStaffs.length; i++) {
			if (p.equipment[3] == p.regularStaffs[i]) {
				if (buttonId == 4) {
					p.getActionSender().setTab(p, 73, 319);
				}
			}
		}
		for (int a = 0; a < p.otherStaffs.length; a++) {
			if (p.equipment[3] == p.otherStaffs[a]) {
				if (buttonId == 4) {
					p.getActionSender().setTab(p, 73, 388);
				}
			}
		}
	break;

        case 192:
	case 193:
	case 430:

		playerMagic.magic(interfaceId, buttonId);
	break;

	case 319:
		p.autocast = true;
		p.autocastSpellbook = 192;
		switch (buttonId) {
			case 0: p.autocastSpell = 1; break;
			case 1: p.autocastSpell = 4; break;
			case 2: p.autocastSpell = 6; break;
			case 3: p.autocastSpell = 8; break;
			case 4: p.autocastSpell = 10; break;
			case 5: p.autocastSpell = 14; break;
			case 6: p.autocastSpell = 17; break;
			case 7: p.autocastSpell = 20; break;
			case 8: p.autocastSpell = 24; break;
			case 9: p.autocastSpell = 27; break;
			case 10: p.autocastSpell = 33; break;
			case 11: p.autocastSpell = 38; break;
			case 12: p.autocastSpell = 45; break;
			case 13: p.autocastSpell = 48; break;
			case 14: p.autocastSpell = 52; break;
			case 15: p.autocastSpell = 55; break;
		}
		playerMagic.updateAttributes(p.autocastSpellbook, p.autocastSpell);
		if (!playerMagic.hasLevel(p.autocastSpellbook, p.autocastSpell) || !playerMagic.hasRunes(p.autocastSpellbook, p.autocastSpell)) {
			p.autocast = false;
			p.autocastSpell = -1;
			p.autocastSpellbook = -1;
		} else {
			p.playerWeapon.setWeapon();
			p.getActionSender().setTab(p, 73, 90);
		}
	break;
	case 388:
		p.autocast = true;
		p.autocastSpellbook = 193;
		switch (buttonId) {
			case 2: p.autocastSpell = 4; break;
			case 3: p.autocastSpell = 0; break;
			case 6: p.autocastSpell = 6; break;
			case 7: p.autocastSpell = 2; break;
			case 10: p.autocastSpell = 5; break;
			case 11: p.autocastSpell = 1; break;
			case 14: p.autocastSpell = 7; break;
			case 15: p.autocastSpell = 3; break;
		}
		playerMagic.updateAttributes(p.autocastSpellbook, p.autocastSpell);
		if (!playerMagic.hasLevel(p.autocastSpellbook, p.autocastSpell) || !playerMagic.hasRunes(p.autocastSpellbook, p.autocastSpell)) {
			p.autocast = false;
			p.autocastSpell = -1;
			p.autocastSpellbook = -1;
		} else {
			p.playerWeapon.setWeapon();
			p.getActionSender().setTab(p, 73, 90);
		}
	break;

	case 182:
		if (p.attackedBy == null && p.logoutTimer == 0) {
			try {
				p.usedLogout = true;
				Engine.fileManager.saveCharacter(p);
				p.getActionSender().logout(p);
			} catch (Exception e) {
			}
		} else {
			p.getActionSender().sendMessage(p, "You cannot logout while in combat.");
		}
	break;

        case 548:
            break;
case 746:
if(buttonId == 49) //music tab
p.getActionSender().setInterface(p, 1, 746, 71, 187);
else if (buttonId == 48) //emote tab
p.getActionSender().setInterface(p, 1, 746, 71, 464);
else if (buttonId == 47) //setting tab
p.getActionSender().setInterface(p, 1, 746, 71, 261);
else if (buttonId == 46) //clan chat
p.getActionSender().setInterface(p, 1, 746, 71, 589);
else if (buttonId == 45) //ignore tab
p.getActionSender().setInterface(p, 1, 746, 71, 551);
else if (buttonId == 44) //friends list tab
p.getActionSender().setInterface(p, 1, 746, 71, 550);
else if (buttonId == 42) //magic tab
p.getActionSender().setInterface(p, 1, 746, 71, 192);
else if (buttonId == 41) //prayer tab
p.getActionSender().setInterface(p, 1, 746, 71, 271);
else if (buttonId == 40) //Equipment tab
p.getActionSender().setInterface(p, 1, 746, 71, 387);
else if (buttonId == 39) //Inventory tab
p.getActionSender().setInterface(p, 1, 746, 71, 149);
else if (buttonId == 38) //quest tab
p.getActionSender().setInterface(p, 1, 746, 71, 274);
else if (buttonId == 37) //Skill tab
p.getActionSender().setInterface(p, 1, 746, 71, 320);
else if (buttonId == 36) {//Attack tab
p.playerWeapon.setWeapon();
p.getActionSender().setInterface(p, 1, 746, 71, 92);
}
else if (buttonId == 35) //sum tab
p.getActionSender().setInterface(p, 1, 746, 71, 92);
else if (buttonId == 12) //Logout.
p.getActionSender().setInterface(p, 1, 746, 71, 182);
		break;
	case 637:
            Misc.println("[" + p.username + "] Unhandled button: " + interfaceId + ", " + buttonId + ":" + buttonId2);
	    if (buttonId == 14) {
		Player pl = Server.engine.players[p.duelFriend];
		if (p != null && pl != null) {
			p.getActionSender().removeShownInterface(p);
			pl.getActionSender().removeShownInterface(pl);
			p.getDuelClass().resetDuelSettings1();
	        	pl.getDuelClass().resetDuelSettings1();
		}
	    }
	    if (buttonId == 83) {/* Accept */
		Player pl = Server.engine.players[p.duelFriend];
		//if (p != null && pl != null) {
			p.acceptScreen1 = true;
			p.getActionSender().sendMessage(p, "Accepted duel.");
		}
	   // }
	    if (buttonId == 86) {/* Decline */
		Player pl = Server.engine.players[p.duelFriend];
		if (p != null && pl != null) {
			p.getActionSender().removeShownInterface(p);
			pl.getActionSender().removeShownInterface(pl);
			p.getDuelClass().resetDuelSettings1();
	        	pl.getDuelClass().resetDuelSettings1();
		}
	    }
	    break;
	case 640:
	    if (buttonId == 18) {
		p.getActionSender().removeShownInterface(p);
		p.teleportTo(3087 + Misc.random(2), 3518 + Misc.random(2), 0, 4, 0, 8939, 8941, 1576, 0, 1577, 0);
	    }
	    if (buttonId == 19) {
		p.getActionSender().removeShownInterface(p);
		p.teleportTo(3243 + Misc.random(8), 3533 + Misc.random(8), 0, 4, 0, 8939, 8941, 1576, 0, 1577, 0);
	    }
	    if (buttonId == 20) {
		p.getActionSender().removeShownInterface(p);
		p.teleportTo(3091 + Misc.random(2), 3963 + Misc.random(2), 0, 4, 0, 8939, 8941, 1576, 0, 1577, 0);
	    }
	break;

    case 763:
            /*
             * Inventory interface with banking.
             */
            if (buttonId == 0) {
                if (packetId == 233)		//1
                    Engine.playerBank.bankItem(p, buttonId2, 1);
                else if (packetId == 21)	//5
                    Engine.playerBank.bankItem(p, buttonId2, 5);
                else if (packetId == 169)	//10
                    Engine.playerBank.bankItem(p, buttonId2, 10);
				else if (packetId == 214)	//lastX
					Engine.playerBank.bankItem(p, buttonId2, p.bankX);
                else if (packetId == 232)	//all
                    Engine.playerBank.bankItem(p, buttonId2, Engine.playerItems.invItemCount(p, p.items[buttonId2]));
				else if (packetId == 173)	//X
					p.input.request(3, buttonId2);
				else if (packetId == 90)	//examine
				    p.getActionSender().sendMessage(p, Engine.items.getItemDescription(p.items[buttonId2]));
            }
            break;
     
           
            case 762:
            /*
             * Bank interface.
             */
            if (buttonId == 73) {
                if (packetId == 233)	//1
                    Engine.playerBank.bankWithdraw(p, buttonId2, 1);
                else if (packetId == 21)	//5
                    Engine.playerBank.bankWithdraw(p, buttonId2, 5);
                else if (packetId == 169)	//10
                    Engine.playerBank.bankWithdraw(p, buttonId2, 10);
				else if (packetId == 214)	//lastX
					Engine.playerBank.bankWithdraw(p, buttonId2, p.bankX);
				else if (packetId == 173) {	//X
					p.input.request(2, buttonId2);
				} else if (packetId == 232)	//all
                    Engine.playerBank.bankWithdraw(p, buttonId2, p.bankItemsN[buttonId2]);
				else if (packetId == 133)	//all but one
					Engine.playerBank.bankWithdraw(p, buttonId2, p.bankItemsN[buttonId2] -1);
				else if (packetId == 90)	//examine
				    p.getActionSender().sendMessage(p, Engine.items.getItemDescription(p.bankItems[buttonId2]));
                break;
            } else if (buttonId == 22) {
				//p.getActionSender().restoreTabs(p); This is mine, close interface button, you might have other methods to show inventory again
			} else if (buttonId == 16) {
				p.withdrawNote = !p.withdrawNote;
			} else if (buttonId == 14) {
				p.insertMode = !p.insertMode;
			} else if (buttonId == 41 || buttonId == 39 || buttonId == 37 || buttonId == 35 || buttonId == 33 || buttonId == 31 || buttonId == 29 || buttonId == 27 || buttonId == 25) { //Tab buttons
				if(packetId == 21) { //Collapse
					Engine.playerBank.collapseTab(p, Engine.playerBank.getArrayIndex(buttonId)); //This method will be added later, dont worry
				} else if(packetId == 233) { //View tab
					p.viewingBankTab = Engine.playerBank.getArrayIndex(buttonId);
				}
			}
            break;

        default:
            Misc.println("[" + p.username + "] Unhandled button: " + interfaceId + ", " + buttonId + ":" + buttonId2);
            break;
        }
    }
}