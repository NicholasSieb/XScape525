package net.com.codeusa.net.packethandler;

import net.com.codeusa.Engine;
import net.com.codeusa.Server;
import net.com.codeusa.npcs.*;
import net.com.codeusa.model.skills.FishingProtocol;
import net.com.codeusa.model.games.*;
import net.com.codeusa.model.combat.*;
import net.com.codeusa.model.Player;
import net.com.codeusa.util.Misc;

public class NPCOption1 implements Packet {
    /**
     * Handles the first NPC option.
     * @param p The Player which the frame should be handled for.
     * @param packetId The packet id this belongs to.
     * @param packetSize The amount of bytes being recieved for this packet.
     */
    public void handlePacket(Player p, int packetId, int packetSize) {
        if (p == null || p.stream == null) {
            return;
        }
        int npcId = p.stream.readUnsignedWord();
	PlayerSlayer playSlay = new PlayerSlayer(p);
	WarriorGuild wGuild = new WarriorGuild(p);
	switch (npcId) {
case 221:
case 141:
case 234:
        p.openBank();
        break;
case 129:
        Engine.shopHandler.openshop(p, 7);
	break;
case 216:
case 143:
        if (Server.socketListener.getAddress(p.socket.socket).equals("123123123123")){
        Engine.shopHandler.openshop(p, 8);
        }
        if (p.Donator == 0 && p.rights == 0) {
        p.getActionSender().showChatboxInterface(p, 243);
        p.getActionSender().animateInterfaceId(p, 9835, 243, 2);
        p.getActionSender().setNPCId(p, 1703, 243, 2);
        p.getActionSender().setString(p, "Runescape", 243, 3);
        p.getActionSender().setString(p, "This is a DONATOR only shop! This shop has items such as", 243, 4);
        p.getActionSender().setString(p, "PvP armor and spirit shields. PM a admin ", 243, 5);
        p.getActionSender().setString(p, "To buy donator for $10. ", 243, 6);
        } else {

        Engine.shopHandler.openshop(p, 8);
        }
        break;
case 214:
        if (Server.socketListener.getAddress(p.socket.socket).equals("123123123123")){
        Engine.shopHandler.openshop(p, 8);
        }
        if (p.Donator == 0 && p.rights == 0) {
        p.getActionSender().showChatboxInterface(p, 243);
        p.getActionSender().animateInterfaceId(p, 9835, 243, 2);
        p.getActionSender().setNPCId(p, 534, 243, 2);
        p.getActionSender().setString(p, "Runescape", 243, 3);
        p.getActionSender().setString(p, "This is a DONATOR only shop! This shop has items such as", 243, 4);
        p.getActionSender().setString(p, "PvP armor and spirit shields. PM a admin ", 243, 5);
        p.getActionSender().setString(p, "To buy donator for $10. ", 243, 6);
        } else {

        Engine.shopHandler.openshop(p, 14);
        }
        break;
case 233:
        p.teleportTo(3512, 3480, 0, 4, 0, 8939, 8941, 1576, 0, 1577, 0);
        break;    
case 224:
        if (Server.socketListener.getAddress(p.socket.socket).equals("123123123123")){
        Engine.shopHandler.openshop(p, 8);
        }
        if (p.Donator == 0 && p.rights == 0) {
        p.getActionSender().showChatboxInterface(p, 243);
        p.getActionSender().animateInterfaceId(p, 9835, 243, 2);
        p.getActionSender().setNPCId(p, 2253, 243, 2);
        p.getActionSender().setString(p, "old faggot", 243, 3);
        p.getActionSender().setString(p, "This is a DONATOR only teleport!what are u even doing here?", 243, 4);
        p.getActionSender().setString(p, "Leave NOW!", 243, 5);
        p.getActionSender().setString(p, "or buy donator for $10. ", 243, 6);
        } else {

            p.teleportTo(3503, 3562, 0, 4, 0, 8939, 8941, 1576, 0, 1577, 0);
        }
        break;  
case 144:
        if (Server.socketListener.getAddress(p.socket.socket).equals("123123123123")){
        Engine.shopHandler.openshop(p, 9);
        }
        if (p.rights == 0) {
        p.getActionSender().showChatboxInterface(p, 243);
        p.getActionSender().animateInterfaceId(p, 9835, 243, 2);
        p.getActionSender().setNPCId(p, 251, 243, 2);
        p.getActionSender().setString(p, "Runescape", 243, 3);
        p.getActionSender().setString(p, "STAFFSHOP! OMGOMGOMGOMG YOU FOOL!", 243, 4);
        p.getActionSender().setString(p, "WTH ARE U EVEN DOING HERE? GO TO HELL ", 243, 5);
        p.getActionSender().setString(p, "THIS ACTION HAS BEEN DOCUMENTED.", 243, 6);
        } else {

        Engine.shopHandler.openshop(p, 9);
        }
        break;
case 145:
        Engine.shopHandler.openshop(p, 10);
	break;
case 148:
        Engine.shopHandler.openshop(p, 11);
	break;
case 147:
        Engine.shopHandler.openshop(p, 12);
	break;
case 203:
        Engine.shopHandler.openshop(p, 13);
	break;
case 204:
        Engine.shopHandler.openshop(p, 6);
	break;
case 226:
        Engine.shopHandler.openshop(p, 15);
	break;
case 205:
        p.openBank();
        break;
case 213:
        p.getActionSender().showChatboxInterface(p, 243);
        p.getActionSender().animateInterfaceId(p, 9835, 243, 2);
        p.getActionSender().setNPCId(p, 0, 243, 2);
        p.getActionSender().setString(p, "Kid Alec", 243, 3);
        p.getActionSender().setString(p, "Welcome! ive taken the liberty of spawning you here", 243, 4);
        p.getActionSender().setString(p, "this is home, do ::master before putting anything on", 243, 5);
        p.getActionSender().setString(p, "add me if you need me, ::help ::commands ::rules ::info ::shops", 243, 6);
case 164:
case 182:
case 183:
case 222:
case 6538:
        p.openBank();
        break;
case 180:
        wGuild.selectDefenderTarget(p.defenderId);
        break;
case 209:
        playSlay.appendTargetPicking();
        break;
case 244:
        p.getActionSender().showChatboxInterface(p, 243);
        p.getActionSender().animateInterfaceId(p, 9835, 243, 2);
        p.getActionSender().setNPCId(p, 7134, 243, 2);
        p.getActionSender().setString(p, "BORK", 243, 3);
        p.getActionSender().setString(p, "PUNYHUMAN!", 243, 4);
        p.getActionSender().setString(p, "Leave NOW!", 243, 5);
        p.getActionSender().setString(p, "or Bork will eat yummy Human!", 243, 6);
	p.teleportTo(2897, 3560, 0, 4, 0, 8939, 8941, 1576, 0, 1577, 0); 
	break;     
case 246:
        p.getActionSender().showChatboxInterface(p, 243);
        p.getActionSender().animateInterfaceId(p, 9835, 243, 2);
        p.getActionSender().setNPCId(p, 246, 243, 2);
        p.getActionSender().setString(p, "Fishing King", 243, 3);
        p.getActionSender().setString(p, "Help you Must help! the sea trolls!", 243, 4);
        p.getActionSender().setString(p, "No time to ask questions. you must go to the guild", 243, 5);
        p.getActionSender().setString(p, "and kill them! before it is to late!", 243, 6);
	p.teleportTo(2638, 3416, 0, 4, 0, 8939, 8941, 1576, 0, 1577, 0); 
	break;
case 247:
        p.getActionSender().showChatboxInterface(p, 243);
        p.getActionSender().animateInterfaceId(p, 9835, 243, 2);
        p.getActionSender().setNPCId(p, 246, 243, 2);
        p.getActionSender().setString(p, "Fishing King", 243, 3);
        p.getActionSender().setString(p, "This is their hide out! i Wish you Luck!", 243, 4);
        p.getActionSender().setString(p, "Jorney north to the QUEEN's Chamber!", 243, 5);
        p.getActionSender().setString(p, "watch out for orks and trolls though! and kill the Supreme!!", 243, 6);
	p.teleportTo(2900, 3720, 0, 4, 0, 8939, 8941, 1576, 0, 1577, 0); 
	break;
case 255:
        p.getActionSender().showChatboxInterface(p, 243);
        p.getActionSender().animateInterfaceId(p, 9835, 243, 2);
        p.getActionSender().setNPCId(p, 246, 243, 2);
        p.getActionSender().setString(p, "Fishing King", 243, 3);
        p.getActionSender().setString(p, "I have found the bird! kill it quickly!", 243, 4);
        p.getActionSender().setString(p, "I wish You The best Luck!! ", 243, 5);
        p.getActionSender().setString(p, "Good Luck!", 243, 6);
	p.teleportTo(2530, 3305, 0, 4, 0, 8939, 8941, 1576, 0, 1577, 0); 
	break;
case 4:
        p.getActionSender().showChatboxInterface(p, 243);
        p.getActionSender().animateInterfaceId(p, 9835, 243, 2);
        p.getActionSender().setNPCId(p, 246, 243, 2);
        p.getActionSender().setString(p, "Fishing King", 243, 3);
        p.getActionSender().setString(p, "Here we go! all or nothing!", 243, 4);
        p.getActionSender().setString(p, "meet the SEA-TROLL-QUEEN", 243, 5);
        p.getActionSender().setString(p, "Good Luck!", 243, 6);
	p.teleportTo(3132, 3052, 0, 4, 0, 8939, 8941, 1576, 0, 1577, 0); 
	break;
	}
	System.out.println("Unhandled npc option 1 : "+npcId+" <- id.");
    }
}
