/*
 * Class FileManager
 *
 * Version 1.0
 *
 * Friday, August 22, 2008
 *
 * Created by Palidino76
 */

package net.com.codeusa.io;

import java.io.*;
import net.com.codeusa.Server;
import net.com.codeusa.model.Player;
import net.com.codeusa.util.ByteVector;
import net.com.codeusa.util.Misc;

public class FileManager {
/**
* Byte buffer for storing bytes to be loaded or saved.
*/
private ByteVector stream = new ByteVector(20000, 20000);

/**
* Saves a character file.
* @param p The player to save.
*/
public void saveCharacter(Player p) throws Exception {
if (p == null) {
return; 
}
stream.outOffset = 0;
stream.writeString("username:" + p.username);
stream.writeString("password:" + Misc.stringToLong(p.password));
stream.writeString("ip:" + Server.socketListener.getAddress(p.socket.socket));
stream.writeString("rights:" + p.rights);
stream.writeString("firstlog:" + p.firstLog);
stream.writeString("starter:" + p.starter);
stream.writeString("jailed:" + p.jailed);
stream.writeString("chatName: " +p.chatName);
for (int i = 0; i < p.tabStartSlot.length; i++) {
        stream.writeString("tab" + i + ":" + p.tabStartSlot[i]);
}
stream.writeString("KC: " +p.KC);
stream.writeString("DC: " +p.DC);
stream.writeString("PVP potential: " +p.PVPPotential);
stream.writeString("kills: "+p.kills);
stream.writeString("total kills: "+p.totalKills);
stream.writeString("sExperience: "+p.spendingExperience);
stream.writeString("Last killed: "+p.lastKilled);
stream.writeString("degrade: "+p.degrade);
stream.writeString("absx:" + p.absX);
stream.writeString("absy:" + p.absY);
stream.writeString("height:" + p.heightLevel);
stream.writeString("InBounty:" + p.InBounty);
stream.writeString("bhPickup:" + p.bhPickup);
stream.writeString("pickedUp:" + p.pickedUp);
stream.writeString("bhLeave:" + p.bhLeave);
stream.writeString("bhLeave1:" + p.bhLeave1);
stream.writeString("leftBhTimer:" + p.leftBhTimer);
stream.writeString("runenergy:" + p.runEnergy);
stream.writeString("gender:" + p.gender);
stream.writeString("Donator:" + p.Donator);
stream.writeString("statHammer:" + p.statHammer);
stream.writeString("statHelm:" + p.statHelm);
stream.writeString("statBody:" + p.statBody);
stream.writeString("statLegs:" + p.statLegs);
for (int i = 0; i < 200; i++) {
if (i < p.friends.size()) {
stream.writeString("friend" + i + ":" + p.friends.get(i));
}
}
for (int i = 0; i < 100; i++) {
if (i < p.ignores.size()) {
stream.writeString("ignores" + i + ":" + p.ignores.get(i));
}
}
for (int i = 0; i < p.tabStartSlot.length; i++) {
stream.writeString("tab" + i + ":" + p.tabStartSlot[i]);
}
stream.writeString("bankx:" + p.bankX);
stream.writeString("note:" + (p.withdrawNote ? 1 : 0));
stream.writeString("insert:" + (p.insertMode ? 1 : 0));
for (int i = 0; i < p.look.length; i++) {
stream.writeString("look" + i + ":" + p.look[i]);
}
for (int i = 0; i < p.color.length; i++) {
if (p.color[i] > 0)
stream.writeString("color" + i + ":" + p.color[i]);
}
for (int i = 0; i < p.skillLvl.length; i++) {
stream.writeString("skill" + i + ":" + p.skillLvl[i] + "," + p.skillXP[i]);
}
for (int i = 0; i < p.equipment.length; i++) {
if (p.equipment[i] > -1 && p.equipmentN[i] > 0)
stream.writeString("equipment" + i + ":" + p.equipment[i] + "," + p.equipmentN[i]);
}
for (int i = 0; i < p.items.length; i++) {
if (p.items[i] > -1 && p.itemsN[i] > 0)
stream.writeString("item" + i + ":" + p.items[i] + "," + p.itemsN[i]);
}
for (int i = 0; i < p.bankItems.length; i++) {
if (p.bankItems[i] > -1 && p.bankItemsN[i] > 0)
stream.writeString("bankitem" + i + ":" + p.bankItems[i] + "," + p.bankItemsN[i]);
}
stream.writeString("mute:"+p.muteType);
stream.writeString("specAmount:" + p.specAmount);
stream.writeString("spellbook:" + p.spellbook);
stream.writeString("Xlog:" + p.combatType);
stream.writeString("quests:" + p.questStage);
for (int i = 0; i < p.godWarsKills.length; i++) {
stream.writeString("godWars" + i + ":" + p.godWarsKills[i]);
}
stream.writeString("null");
FileOutputStream out = new FileOutputStream("./data/characters/mainsave/" + p.username + ".dat");
out.write(stream.outBuffer, 0, stream.outOffset);
out.flush();
out.close();
out = null;
}

public void saveBackup(Player p) throws Exception {
if (p == null) {
return; 
}
stream.outOffset = 0;
stream.writeString("username:" + p.username);
stream.writeString("password:" + Misc.stringToLong(p.password));
stream.writeString("ip:" + Server.socketListener.getAddress(p.socket.socket));
stream.writeString("rights:" + p.rights);
stream.writeString("firstlog:" + p.firstLog);
stream.writeString("starter:" + p.starter);
stream.writeString("jailed:" + p.jailed);
stream.writeString("chatName: " +p.chatName);
stream.writeString("KC: " +p.KC);
stream.writeString("DC: " +p.DC);
stream.writeString("PVP potential: " +p.PVPPotential);
stream.writeString("kills: "+p.kills);
stream.writeString("total kills: "+p.totalKills);
stream.writeString("sExperience: "+p.spendingExperience);
stream.writeString("Last killed: "+p.lastKilled);
stream.writeString("degrade: "+p.degrade);
stream.writeString("absx:" + p.absX);
stream.writeString("absy:" + p.absY);
stream.writeString("height:" + p.heightLevel);
stream.writeString("InBounty:" + p.InBounty);
stream.writeString("bhPickup:" + p.bhPickup);
stream.writeString("pickedUp:" + p.pickedUp);
stream.writeString("bhLeave:" + p.bhLeave);
stream.writeString("bhLeave1:" + p.bhLeave1);
stream.writeString("leftBhTimer:" + p.leftBhTimer);
stream.writeString("runenergy:" + p.runEnergy);
stream.writeString("gender:" + p.gender);
stream.writeString("Donator:" + p.Donator);
stream.writeString("statHammer:" + p.statHammer);
stream.writeString("statHelm:" + p.statHelm);
stream.writeString("statBody:" + p.statBody);
stream.writeString("statLegs:" + p.statLegs);
for (int i = 0; i < 200; i++) {
if (i < p.friends.size()) {
stream.writeString("friend" + i + ":" + p.friends.get(i));
}
}
for (int i = 0; i < 100; i++) {
if (i < p.ignores.size()) {
stream.writeString("ignores" + i + ":" + p.ignores.get(i));
}
}
for (int i = 0; i < p.tabStartSlot.length; i++) {
stream.writeString("tab" + i + ":" + p.tabStartSlot[i]);
}
stream.writeString("bankx:" + p.bankX);
stream.writeString("note:" + (p.withdrawNote ? 1 : 0));
stream.writeString("insert:" + (p.insertMode ? 1 : 0));
for (int i = 0; i < p.look.length; i++) {
stream.writeString("look" + i + ":" + p.look[i]);
}
for (int i = 0; i < p.color.length; i++) {
if (p.color[i] > 0)
stream.writeString("color" + i + ":" + p.color[i]);
}
for (int i = 0; i < p.skillLvl.length; i++) {
stream.writeString("skill" + i + ":" + p.skillLvl[i] + "," + p.skillXP[i]);
}
for (int i = 0; i < p.equipment.length; i++) {
if (p.equipment[i] > -1 && p.equipmentN[i] > 0)
stream.writeString("equipment" + i + ":" + p.equipment[i] + "," + p.equipmentN[i]);
}
for (int i = 0; i < p.items.length; i++) {
if (p.items[i] > -1 && p.itemsN[i] > 0)
stream.writeString("item" + i + ":" + p.items[i] + "," + p.itemsN[i]);
}
for (int i = 0; i < p.bankItems.length; i++) {
if (p.bankItems[i] > -1 && p.bankItemsN[i] > 0)
stream.writeString("bankitem" + i + ":" + p.bankItems[i] + "," + p.bankItemsN[i]);
}
stream.writeString("mute:"+p.muteType);
stream.writeString("specAmount:" + p.specAmount);
stream.writeString("spellbook:" + p.spellbook);
stream.writeString("Xlog:" + p.combatType);
stream.writeString("quests:" + p.questStage);
for (int i = 0; i < p.godWarsKills.length; i++) {
stream.writeString("godWars" + i + ":" + p.godWarsKills[i]);
}
stream.writeString("null");
FileOutputStream out = new FileOutputStream("./data/characters/backups/" + p.username + ".dat");
out.write(stream.outBuffer, 0, stream.outOffset);
out.flush();
out.close();
out = null;
}

/**
* Loads a character file.
* @param p The player to save.
*/

public void loadCharacter(Player p) {
stream.inOffset = 0;
try {
FileInputStream in = new FileInputStream("./data/characters/mainsave/" + p.username + ".dat");
in.read(stream.inBuffer);
in.close();
in = null;
} catch (Exception e) {
return;
}
String line;
try {
while ((line = stream.readString()) != null && line.length() > 0 && !line.equals("null")) {
if (line.startsWith("password:"))
p.password = Misc.longToString(Long.parseLong(line.substring(9)));
else if (line.startsWith("rights:"))
p.rights = Integer.parseInt(line.substring(7));
else if (line.startsWith("firstlog:"))
p.firstLog = Integer.parseInt(line.substring(9));
else if (line.startsWith("tagname:"))
p.chatTag2 = line.substring(9);
else if (line.startsWith("starter:"))
p.starter = Integer.parseInt(line.substring(8));
else if (line.startsWith("jailed:"))
p.jailed = Integer.parseInt(line.substring(7));
else if (line.startsWith("KC: "))
p.KC = Integer.parseInt(line.substring(4));
else if (line.startsWith("DC: "))
p.DC = Integer.parseInt(line.substring(4));
else if (line.startsWith("PVP potential: "))
p.PVPPotential = Double.parseDouble(line.substring(15));
else if (line.startsWith("kills: "))
p.kills = Integer.parseInt(line.substring(7));
else if (line.startsWith("total kills: "))
p.totalKills = Integer.parseInt(line.substring(13));
else if (line.startsWith("sExperience: "))
p.spendingExperience = Integer.parseInt(line.substring(13));
else if (line.startsWith("Last killed: "))
p.lastKilled = line.substring(13);
else if (line.startsWith("degrade: ")) {
p.degrade = Integer.parseInt(line.substring(9));
p.degrades = p.degrade < 6000;
}
else if (line.startsWith("absx:"))
p.teleportToX = Integer.parseInt(line.substring(5));
else if (line.startsWith("absy:"))
p.teleportToY = Integer.parseInt(line.substring(5));
else if (line.startsWith("height:"))
p.heightLevel = 0; //Any height level different than 0 crashes the client upon login
else if (line.startsWith("runenergy:"))
p.runEnergy = Integer.parseInt(line.substring(10));
else if (line.startsWith("gender:"))
p.gender = Integer.parseInt(line.substring(7));
else if (line.startsWith("look"))
p.look[Integer.parseInt(line.substring(4, 5))] = Integer.parseInt(line.substring(6));
else if (line.startsWith("Donator:"))
p.Donator = Integer.parseInt(line.substring(8));
else if (line.startsWith("statHammer:"))
p.statHammer = Integer.parseInt(line.substring(11));
else if (line.startsWith("statHelm:"))
p.statHelm = Integer.parseInt(line.substring(9));
else if (line.startsWith("statBody:"))
p.statBody = Integer.parseInt(line.substring(9));
else if (line.startsWith("statLegs:"))
p.statLegs = Integer.parseInt(line.substring(9));
else if (line.startsWith("color"))
p.color[Integer.parseInt(line.substring(5, 6))] = Integer.parseInt(line.substring(7));
else if (line.startsWith("skill")) {
p.skillLvl[Integer.parseInt(line.substring(5, line.indexOf(":")))] = Integer.parseInt(line.substring(line.indexOf(":") + 1, 
line.indexOf(",")));
p.skillXP[Integer.parseInt(line.substring(5, line.indexOf(":")))] = Integer.parseInt(line.substring(line.indexOf(",") + 1));
} else if (line.startsWith("equipment")) {
p.equipment[Integer.parseInt(line.substring(9, line.indexOf(":")))] = Integer.parseInt(line.substring(line.indexOf(":") + 1, 
line.indexOf(",")));
p.equipmentN[Integer.parseInt(line.substring(9, line.indexOf(":")))] = Integer.parseInt(line.substring(line.indexOf(",") + 1));
} else if (line.startsWith("item")) {
p.items[Integer.parseInt(line.substring(4, line.indexOf(":")))] = Integer.parseInt(line.substring(line.indexOf(":") + 1, 
line.indexOf(",")));
p.itemsN[Integer.parseInt(line.substring(4, line.indexOf(":")))] = Integer.parseInt(line.substring(line.indexOf(",") + 1));
} else if(line.startsWith("friend")) {
long friendName = Long.parseLong(line.substring(line.indexOf(":")+1));
p.friends.add(friendName);
} else if(line.startsWith("ignore")) {
long ignoreName = Long.parseLong(line.substring(line.indexOf(":")+1));
p.ignores.add(ignoreName);
} else if (line.startsWith("bankitem")) {
p.bankItems[Integer.parseInt(line.substring(8, line.indexOf(":")))] = Integer.parseInt(line.substring(line.indexOf(":") + 1, 
line.indexOf(",")));
p.bankItemsN[Integer.parseInt(line.substring(8, line.indexOf(":")))] = Integer.parseInt(line.substring(line.indexOf(",") + 1));
}
else if(line.startsWith("mute:"))
p.muteType = Integer.parseInt(line.substring(5));
else if(line.startsWith("spellbook:"))
p.spellbook = Integer.parseInt(line.substring(10));
else if (line.startsWith("specAmount:"))
p.specAmount = Integer.parseInt(line.substring(11));
else if (line.startsWith("Xlog:"))
p.combatType = Integer.parseInt(line.substring(5));
else if (line.startsWith("InBounty:"))
p.InBounty = Integer.parseInt(line.substring(9));
else if (line.startsWith("bhPickup:"))
p.bhPickup = Integer.parseInt(line.substring(9));
else if (line.startsWith("chatName: "))
p.chatName = line.substring(10);
else if (line.startsWith("pickedUp:"))
p.pickedUp = Integer.parseInt(line.substring(9));
else if (line.startsWith("bhLeave:"))
p.bhLeave = Integer.parseInt(line.substring(8));
else if (line.startsWith("bhLeave1:"))
p.bhLeave1 = Integer.parseInt(line.substring(9));
else if (line.startsWith("leftBhTimer:"))
p.leftBhTimer = Integer.parseInt(line.substring(12));
else if (line.startsWith("bankx:"))
p.bankX = Integer.parseInt(line.substring(6));
else if (line.startsWith("note:"))
p.withdrawNote = Integer.parseInt(line.substring(5)) == 1 ? true : false;
else if (line.startsWith("insert:"))
p.insertMode = Integer.parseInt(line.substring(7)) == 1 ? true : false;
else if (line.startsWith("tab"))
p.tabStartSlot[Integer.parseInt(line.substring(3, line.indexOf(":")))] = Integer.parseInt(line.substring(line.indexOf(":") + 1));
else if(line.startsWith("quests:"))
p.questStage = Integer.parseInt(line.substring(7));
else if (line.startsWith("godWars")) {
p.godWarsKills[Integer.parseInt(line.substring(7, line.indexOf(":")))] = Integer.parseInt(line.substring(line.indexOf(":") + 1, 
line.indexOf(",")));
}
}
} catch (Exception e) {
}
}
/*public void loadbackup(Player p) {
stream.inOffset = 0;
try {
FileInputStream in = new FileInputStream("./data/characters/backups/" + p.username + ".dat");
in.read(stream.inBuffer);
in.close();
in = null;
} catch (Exception e) {
return;
}
String line;
try {
while ((line = stream.readString()) != null && line.length() > 0 && !line.equals("null")) {
if (line.startsWith("password:"))
p.password = Misc.longToString(Long.parseLong(line.substring(9)));
else if (line.startsWith("rights:"))
p.rights = Integer.parseInt(line.substring(7));
else if (line.startsWith("firstlog:"))
p.firstLog = Integer.parseInt(line.substring(9));
else if (line.startsWith("tagname:"))
p.chatTag2 = line.substring(9);
else if (line.startsWith("starter:"))
p.starter = Integer.parseInt(line.substring(8));
else if (line.startsWith("jailed:"))
p.jailed = Integer.parseInt(line.substring(7));
else if (line.startsWith("KC: "))
p.KC = Integer.parseInt(line.substring(4));
else if (line.startsWith("DC: "))
p.DC = Integer.parseInt(line.substring(4));
else if (line.startsWith("PVP potential: "))
p.PVPPotential = Double.parseDouble(line.substring(15));
else if (line.startsWith("kills: "))
p.kills = Integer.parseInt(line.substring(7));
else if (line.startsWith("total kills: "))
p.totalKills = Integer.parseInt(line.substring(13));
else if (line.startsWith("sExperience: "))
p.spendingExperience = Integer.parseInt(line.substring(13));
else if (line.startsWith("Last killed: "))
p.lastKilled = line.substring(13);
else if (line.startsWith("degrade: ")) {
p.degrade = Integer.parseInt(line.substring(9));
p.degrades = p.degrade < 6000;
}
else if (line.startsWith("absx:"))
p.teleportToX = Integer.parseInt(line.substring(5));
else if (line.startsWith("absy:"))
p.teleportToY = Integer.parseInt(line.substring(5));
else if (line.startsWith("height:"))
p.heightLevel = 0; //Any height level different than 0 crashes the client upon login
else if (line.startsWith("runenergy:"))
p.runEnergy = Integer.parseInt(line.substring(10));
else if (line.startsWith("gender:"))
p.gender = Integer.parseInt(line.substring(7));
else if (line.startsWith("look"))
p.look[Integer.parseInt(line.substring(4, 5))] = Integer.parseInt(line.substring(6));
else if (line.startsWith("Donator:"))
p.Donator = Integer.parseInt(line.substring(8));
else if (line.startsWith("statHammer:"))
p.statHammer = Integer.parseInt(line.substring(11));
else if (line.startsWith("statHelm:"))
p.statHelm = Integer.parseInt(line.substring(9));
else if (line.startsWith("statBody:"))
p.statBody = Integer.parseInt(line.substring(9));
else if (line.startsWith("statLegs:"))
p.statLegs = Integer.parseInt(line.substring(9));
else if (line.startsWith("color"))
p.color[Integer.parseInt(line.substring(5, 6))] = Integer.parseInt(line.substring(7));
else if (line.startsWith("skill")) {
p.skillLvl[Integer.parseInt(line.substring(5, line.indexOf(":")))] = Integer.parseInt(line.substring(line.indexOf(":") + 1,
line.indexOf(",")));
p.skillXP[Integer.parseInt(line.substring(5, line.indexOf(":")))] = Integer.parseInt(line.substring(line.indexOf(",") + 1));
} else if (line.startsWith("equipment")) {
p.equipment[Integer.parseInt(line.substring(9, line.indexOf(":")))] = Integer.parseInt(line.substring(line.indexOf(":") + 1,
line.indexOf(",")));
p.equipmentN[Integer.parseInt(line.substring(9, line.indexOf(":")))] = Integer.parseInt(line.substring(line.indexOf(",") + 1));
} else if (line.startsWith("item")) {
p.items[Integer.parseInt(line.substring(4, line.indexOf(":")))] = Integer.parseInt(line.substring(line.indexOf(":") + 1,
line.indexOf(",")));
p.itemsN[Integer.parseInt(line.substring(4, line.indexOf(":")))] = Integer.parseInt(line.substring(line.indexOf(",") + 1));
} else if(line.startsWith("friend")) {
long friendName = Long.parseLong(line.substring(line.indexOf(":")+1));
p.friends.add(friendName);
} else if(line.startsWith("ignore")) {
long ignoreName = Long.parseLong(line.substring(line.indexOf(":")+1));
p.ignores.add(ignoreName);
} else if (line.startsWith("bankitem")) {
p.bankItems[Integer.parseInt(line.substring(8, line.indexOf(":")))] = Integer.parseInt(line.substring(line.indexOf(":") + 1,
line.indexOf(",")));
p.bankItemsN[Integer.parseInt(line.substring(8, line.indexOf(":")))] = Integer.parseInt(line.substring(line.indexOf(",") + 1));
}
else if(line.startsWith("mute:"))
p.muteType = Integer.parseInt(line.substring(5));
else if(line.startsWith("spellbook:"))
p.spellbook = Integer.parseInt(line.substring(10));
else if (line.startsWith("specAmount:"))
p.specAmount = Integer.parseInt(line.substring(11));
else if (line.startsWith("Xlog:"))
p.combatType = Integer.parseInt(line.substring(5));
else if (line.startsWith("InBounty:"))
p.InBounty = Integer.parseInt(line.substring(9));
else if (line.startsWith("bhPickup:"))
p.bhPickup = Integer.parseInt(line.substring(9));
else if (line.startsWith("chatName: "))
p.chatName = line.substring(10);
else if (line.startsWith("pickedUp:"))
p.pickedUp = Integer.parseInt(line.substring(9));
else if (line.startsWith("bhLeave:"))
p.bhLeave = Integer.parseInt(line.substring(8));
else if (line.startsWith("bhLeave1:"))
p.bhLeave1 = Integer.parseInt(line.substring(9));
else if (line.startsWith("leftBhTimer:"))
p.leftBhTimer = Integer.parseInt(line.substring(12));
else if (line.startsWith("bankx:"))
p.bankX = Integer.parseInt(line.substring(6));
else if (line.startsWith("note:"))
p.withdrawNote = Integer.parseInt(line.substring(5)) == 1 ? true : false;
else if (line.startsWith("insert:"))
p.insertMode = Integer.parseInt(line.substring(7)) == 1 ? true : false;
else if (line.startsWith("tab"))
p.tabStartSlot[Integer.parseInt(line.substring(3, line.indexOf(":")))] = Integer.parseInt(line.substring(line.indexOf(":") + 1));
else if(line.startsWith("quests:"))
p.questStage = Integer.parseInt(line.substring(7));
else if (line.startsWith("godWars")) {
p.godWarsKills[Integer.parseInt(line.substring(7, line.indexOf(":")))] = Integer.parseInt(line.substring(line.indexOf(":") + 1,
line.indexOf(",")));
}
}
} catch (Exception e) {
}
}*/
public void appendData(String file, String text) {
BufferedWriter bw = null;
try {
FileWriter fileWriter = new FileWriter("./data/" + file, true);
bw = new BufferedWriter(fileWriter);
bw.write(text);
bw.newLine();
bw.flush();
bw.close();
fileWriter = null;
bw = null;
} catch (Exception exception) {
Misc.println("Critical error while writing data: " + file);
}
}
}