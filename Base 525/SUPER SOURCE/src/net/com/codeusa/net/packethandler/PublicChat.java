/*
 * Class PublicChat
 *
 * Version 1.3
 *
 * Sunday, September 13, 2009
 *
 * Created by RelentlessGames
 */

package net.com.codeusa.net.packethandler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import net.com.codeusa.Engine;
import net.com.codeusa.Server;
import net.com.codeusa.model.Player;
import net.com.codeusa.Engine;
import net.com.codeusa.util.Misc;

public class PublicChat implements Packet {
    /**
     * Handles player chatting.
     * @param p The Player which the frame should be handled for.
     * @param packetId The packet id this belongs to.
     * @param packetSize The amount of bytes being recieved for this packet.
     */
public void handlePacket(Player p, int packetId, int packetSize) {
if (p == null || p.stream == null) {
return;
}
p.chatTextEffects = p.stream.readUnsignedWord();
int numChars = p.stream.readUnsignedByte();
p.chatText = Misc.decryptPlayerChat(p.stream, numChars);
if (p.muteType > 0) {
p.getActionSender().sendMessage(p, "You are muted and cannot talk you scumbag.");
return;
}
String[] censoredWords = {"343433421111d"};
for (String c : censoredWords) {
if (p.username.equals("") || p.username.equals("")) {
} else {
if (p.chatText.contains(c)) {
Engine.fileManager.appendData("characters/chatlogs/"+p.username+".txt", p.username+": "+p.chatText);
p.getActionSender().sendMessage(p, "Your message has been logged, Cursing comes with consiquenses.");
return;
}
}
}
if (p.chatText.startsWith("/") && p.chatText.contains("<") && p.chatText.contains(">")) {
return;
}
if (p.chatText.equalsIgnoreCase(p.lastMsg)) {
return;
}
if (p.chatText.contains("^")) {
return;
}
if (p.rights < 1) {
if (p.chatText.contains("free") || p.chatText.contains("staff") || p.chatText.contains("unjail") || p.chatText.contains("fuck") || (p.chatText.contains("join") || (p.chatText.contains(".com") || (p.chatText.contains(".net") || (p.chatText.contains(".webs") || (p.chatText.contains("gay"))))))) {
p.requestForceChat2("can someone give me a cock so i can lick it?");
p.getActionSender().sendMessage(p, "Please do <col=880000>NOT<col=1> ask for an unban, unjail, unmute, un-password glitch in game.");
p.getActionSender().sendMessage(p, "Post on the forum and it will be looked at as soon as our staff has spare time.");
return;
}
}
if (p.chatText.startsWith("/")) {
try {
String chatText = Misc.decryptPlayerChat(p.stream, numChars);
if (p.clanRoom.length() > 0) {
Engine.clanChat.sendMessage(p, chatText.substring(1));

}
String chat = p.activeChat;
for (Player player : Server.engine.players) {
if (player != null) {

if (player.activeChatOwner.equals(p.activeChatOwner)) {
if (p.rights == 0 && p.Donator == 0) {
p.getActionSender().showChatboxInterface(p, 243);
p.getActionSender().animateInterfaceId(p, 9835, 243, 2);
p.getActionSender().setNPCId(p, 6537, 243, 2);
p.getActionSender().setString(p, "IaliIscape", 243, 3);
p.getActionSender().setString(p, "Yell is for donator's and staff only,", 243, 4);
p.getActionSender().setString(p, "talk to a admin about ", 243, 5);
p.getActionSender().setString(p, "buying donator for $10. ", 243, 6);
} else if (p.rights == 0 && (p.Donator == 1)) {
player.getActionSender().sendMessage(player, "[<col=0000ff>"+p.chatTag+"</col>] "+p.username.substring(0, 1).toUpperCase()+p.username.substring(1)+": <col=880000>"+p.chatText.substring(1) + ":clan:");
} else if (p.username.equals("kid alec")) {
player.getActionSender().sendMessage(player, "[<col=0000ff>"+p.chatTag+"</col>] <img=1>"+p.username.substring(0, 1).toUpperCase()+p.username.substring(1)+": <col=880000>"+p.chatText.substring(1) +":clan:");
} else if (p.username.equals("kid alec")) {
player.getActionSender().sendMessage(player, "[<col=0000ff>"+p.chatTag+"</col>] <img=1>"+p.username.substring(0, 1).toUpperCase()+p.username.substring(1)+": <col=880000>"+p.chatText.substring(1) +":clan:");
} else if (p.rights == 1) {
player.getActionSender().sendMessage(player, "[<col=0000ff>"+p.chatTag+"</col>] <img=0>"+p.username.substring(0, 1).toUpperCase()+p.username.substring(1)+": <col=880000>"+p.chatText.substring(1) +":clan:");
} else if (p.rights >= 2) {
player.getActionSender().sendMessage(player, "[<col=0000ff>"+p.chatTag+"</col>] <img=1>"+p.username.substring(0, 1).toUpperCase()+p.username.substring(1)+": <col=880000>"+p.chatText.substring(1) +":clan:");
}

}
}
}
} catch (Exception e) {
return;
}
return;
}
p.lastMsg = p.chatText;
p.chatTextUpdateReq = true;
p.updateReq = true;
Engine.fileManager.appendData("ChatLogs/" + Misc.getNormalDate() + ".txt", "[" + Misc.getDate() + "] "+p.username.substring(0, 1).toUpperCase()+p.username.substring(1)+": "+p.chatText);
}
}
