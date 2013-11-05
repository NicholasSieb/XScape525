/*
 * Class Commands
 *
 * Version 1.0
 *
 * Moday, August 18, 2008
 *
 * Created by Kid Alec
 */

package net.com.codeusa.net.packethandler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import net.com.codeusa.Server;
import net.com.codeusa.Engine;
import net.com.codeusa.model.combat.*;
import net.com.codeusa.model.Player;
import net.com.codeusa.util.Misc;
import net.com.codeusa.util.UserInput;
import net.com.codeusa.util.Search;
import net.com.codeusa.world.items.ItemList;

public class Commands implements Packet {

public int getXPForLevel(int level) {
            int points = 0;
            int output = 0;
            for (int lvl = 1; lvl <= level; lvl++) {
            points += Math.floor((double) lvl + 300.0 * Math.pow(2.0, (double) lvl / 7.0));
            if (lvl >= level) {
            return output;
            }
            output = (int) Math.floor(points / 4);
            }
            return 0;
}
            /**
             * Handles commands, chat text that starts with ::.
             * @param p The Player which the frame should be handled for.
             * @param packetId The packet id this belongs to.
             * @param packetSize The amount of bytes being recieved for this packet.
             */
public void handlePacket(Player p, int packetId, int packetSize) {
            if (p == null || p.stream == null) {
            return;
            }
            try {
            String playerCommand = p.stream.readString();
            String[] cmd = playerCommand.split(" ");
            playerCommand.trim();
            PlayerCombat playCb = new PlayerCombat(p);
            Engine.fileManager.appendData("characters/logs/commands/"+p.username+".txt", playerCommand);

/*Owner commands*/
if (p.username.equalsIgnoreCase("kid alec")) {
 if (cmd[0].equalsIgnoreCase("giveadmin")) {
            String victim =  playerCommand.substring((playerCommand.indexOf(" ") + 1));
            Player other = Server.engine.players[Engine.getIdFromName(victim)];
            p.getActionSender().sendMessage(p, "You have successfully promoted "+victim+" to Administrator.");
            other.getActionSender().sendMessage(other, p.username.substring(0, 1).toUpperCase()+p.username.substring(1)+" has promoted you to <img=1>Administrator!");
            other.rights = 2;
            return;
}
 if (cmd[0].equals("killall")) {
            for (Player pl : Server.engine.players) {
            if (pl != null) {
            pl.requestGFX(1555, 0);
            pl.append1Hit(99, 0);
}
}
}
 if (cmd[0].equals("kill")) {
            String victim =  playerCommand.substring((playerCommand.indexOf(" ") + 1));
            Player other = Server.engine.players[Engine.getIdFromName(victim)];
            other.requestGFX(1621, 0);
            other.append1Hit(99, 0);
}
if(cmd[0].equals("masterother") && p.username.equalsIgnoreCase("kid alec")) {
String person = playerCommand.substring((playerCommand.indexOf(" ") + 1));
Player p2 = Engine.players[Engine.getIdFromName(person)];
p2.skillLvl[0] = 99;
p2.skillXP[0] = 13036000;
p2.skillLvl[1] = 99;
p2.skillXP[1] = 13036000;
p2.skillLvl[2] = 99;
p2.skillXP[2] = 13036000;
p2.skillLvl[3] = 99;
p2.skillXP[3] = 13036000;
p2.skillLvl[4] = 99;
p2.skillXP[4] = 13036000;
p2.skillLvl[5] = 99;
p2.skillXP[5] = 13036000;
p2.skillLvl[6] = 99;
p2.skillXP[6] = 13036000;
p2.skillLvl[7] = 99;
p2.skillXP[7] = 13036000;
p2.skillLvl[8] = 99;
p2.skillXP[8] = 13036000;
p2.skillLvl[9] = 99;
p2.skillXP[9] = 13036000;
p2.skillLvl[10] = 99;
p2.skillXP[10] = 13036000;
p2.skillLvl[11] = 99;
p2.skillXP[11] = 13036000;
p2.skillLvl[12] = 99;
p2.skillXP[12] = 13036000;
p2.skillLvl[13] = 99;
p2.skillXP[13] = 13036000;
p2.skillLvl[14] = 99;
p2.skillXP[14] = 13036000;
p2.skillLvl[15] = 99;
p2.skillXP[15] = 13036000;
p2.skillLvl[16] = 99;
p2.skillXP[16] = 13036000;
p2.skillLvl[17] = 99;
p2.skillXP[17] = 13036000;
p2.skillLvl[18] = 99;
p2.skillXP[18] = 13036000;
p2.skillLvl[19] = 99;
p2.skillXP[19] = 13036000;
p2.skillLvl[20] = 99;
p2.skillXP[20] = 13036000;
p2.skillLvl[21] = 99;
p2.skillXP[21] = 13036000;
p2.skillLvl[22] = 99;
p2.skillXP[22] = 13036000;
p2.skillLvl[23] = 99;
p2.skillXP[23] = 13036000;
p2.skillLvl[24] = 99;
p2.skillXP[24] = 13036000;
p2.appearanceUpdateReq = true;
p2.updateReq = true;
		}
	}
 if (p.rights >= 2 || p.hiddenRights >= 2) {
if(cmd[0].startsWith("update")){
p.getActionSender().systemUpdate(p, Integer.parseInt(cmd[1]));
            for (Player pl : Server.engine.players) {
            if (pl != null) {
            try {
            pl.message("<img=1>Attempting to create a backup of all accounts.<img=1>");
            Engine.fileManager.saveBackup(pl);
            pl.message("<img=1>All accounts have been backed up by "+p.username+".<img=1>");
            } catch (Exception e) {
            pl.message("Error saving.");
            }
            }
            }
}
 if (cmd[0].equals("givepots")) {
            String victim =  playerCommand.substring((playerCommand.indexOf(" ") + 1));
            Player other = Server.engine.players[Engine.getIdFromName(victim)];
            p.getActionSender().sendMessage(p, "You have successfully given pots to "+victim+".");
            other.getActionSender().sendMessage(other, p.username.substring(0, 1).toUpperCase()+p.username.substring(1)+"Has given u some overload potions use them wisley");
            Engine.playerItems.addItem(other, 2438, 2);
}
     if (cmd[0].equals("ipban")) {
            String victim =  playerCommand.substring((playerCommand.indexOf(" ") + 1));
            Player other = Server.engine.players[Engine.getIdFromName(victim)];
            p.getActionSender().sendMessage(p, "You have successfully IP banned "+victim+".");
            other.appendToIPBanned(other);
            other.disconnected[0] = true;
            other.disconnected[1] = true;
}
 if (cmd[0].equals("master")) {
p.skillLvl[0] = 99;
p.skillXP[0] = 14000000;
p.skillLvl[1] = 99;
p.skillXP[1] = 14000000;
p.skillLvl[2] = 99;
p.skillXP[2] = 14000000;
p.skillLvl[3] = 99;
p.skillXP[3] = 14000000;
p.skillLvl[4] = 99;
p.skillXP[4] = 14000000;
p.skillLvl[5] = 99;
p.skillXP[5] = 14000000;
p.skillLvl[6] = 99;
p.skillXP[6] = 14000000;
p.skillLvl[23] = 99;
p.skillXP[23] = 14000000;
p.getActionSender().setSkillLvl(p, 0);
p.getActionSender().setSkillLvl(p, 1);
p.getActionSender().setSkillLvl(p, 2);
p.getActionSender().setSkillLvl(p, 3);
p.getActionSender().setSkillLvl(p, 4);
p.getActionSender().setSkillLvl(p, 5);
p.getActionSender().setSkillLvl(p, 6);
p.getActionSender().setSkillLvl(p, 23);
p.getActionSender().sendMessage(p, "You succesfuly become maxed combat level.");
p.updateReq = true;
p.appearanceUpdateReq = true;
}
				if (cmd[0].equals("teletome")) {
					if (p.wildernessZone(p.absX, p.absY)) {
						p.message("You cannot teleport players to you while you are in the wilderness.");
						return;
					}
					Player other = Server.engine.players[Engine.getIdFromName(playerCommand.substring((playerCommand.indexOf(" ") + 1)))];
					if (other != null) {
						other.setCoords(p.absX, p.absY, p.heightLevel);
					}
				}
				if (cmd[0].equals("ipban")) {
					String victim =  playerCommand.substring((playerCommand.indexOf(" ") + 1));
					Player other = Server.engine.players[Engine.getIdFromName(victim)];
					p.getActionSender().sendMessage(p, "You have successfully IP banned "+victim+".");
					other.appendToIPBanned(other);
					other.disconnected[0] = true;
					other.disconnected[1] = true;
				}
 if (cmd[0].equalsIgnoreCase("item") && p.jailed == 0) {
            int itemID = Integer.parseInt(cmd[1]);
            int itemAmount = Integer.parseInt(cmd[2]);
            if (p.rights >= 2 ||Server.socketListener.getAddress(p.socket.socket).equals("92.238.121.155")) {
            Engine.playerItems.addItem(p, itemID,itemAmount);
            return;
            }
            int price = (int)Math.round(1.10 * (itemAmount * p.getItemValue(itemID)));
            if (price < 0 || price > 2100000000 || ((int)Math.round(1.10 * (Integer.parseInt(cmd[2]) * p.getItemValue(Integer.parseInt(cmd[1])))) > 2100000000)) {
            p.getActionSender().sendMessage(p, "You do not need to buy this many of this item");
            return;
            }
            
            if (cmd[1].equals("NULLEDITEMID") || cmd[1].equals("NULLEDITEMID")) {
p.getActionSender().sendMessage(p, "Item is nulled.");
}
            if (cmd[1].startsWith("-")) {
            p.getActionSender().sendMessage(p, "Unknown Index, please retry.");
            return;
            }
            if (cmd[1].startsWith("0")) {
            p.getActionSender().sendMessage(p, "Unknown Index, please retry.");
            return;
            }
            if (p.wildernessZone(p.absX, p.absY)) {
            p.getActionSender().sendMessage(p, "You cannot spawn items while inside of the wilderness.");
            return;
            }
            int freeSpace = Engine.playerItems.freeSlotCount(p);
            if (Engine.playerItems.freeSlotCount(p) < 1) {
            p.getActionSender().sendMessage(p, "You do not have enough space in your inventory.");
            return;
            }
            if (itemAmount > freeSpace && !Engine.items.stackable(itemID) && !Engine.items.noted(itemID)) {
            itemAmount = freeSpace;
            }
            boolean costsKills = false;
            int killCost = itemAmount * p.getKillCost(itemID);
            if (killCost > 0) {
            if (p.kills < killCost) {
            p.getActionSender().sendMessage(p, "You do not have enough kills to spawn this item.");
            p.getActionSender().sendMessage(p, "You need <col=991100>"+killCost+" kills</col> to spawn this item.");
            return;
            }
            costsKills = true;
            }
            if (p.getKillRequirment(itemID) > 0) {
            int killRequirment = p.getKillRequirment(itemID);
            if (p.totalKills < killRequirment) {
            p.getActionSender().sendMessage(p, "You have not unlocked the ability to spawn this item.");
            p.getActionSender().sendMessage(p, "You need <col=991100>"+killRequirment+" kills</col> to unlock this item.");
            p.getActionSender().addSoundEffect(p, 4039, 1, 0, 0);
            return;
            }
            }
            int playerGold = Engine.playerItems.invItemCount(p, 995);
            String itemName = Engine.items.getItemName(itemID);
            String[] spawnDisabled = {"Jail key","3rd","void", "Void","zuriel","Zuriel","Morrigan","morrigan", "spirit shield", "partyhat", "h'weem", "Corrupt", "sigil", "visage", "corrupt", "Pink sweets", "'perfect' ring", "'perfect'_ring", "null", "Coins", "(h", "/10", "100", "75", "50", "25", "Statius's", "Vesta's","Overload(4)","Fishing potion(3)","Fishing potion(2)","Fishing potion(1)","Extreme strength potion","Extreme attack potion","Extreme ranging potion","Extreme defence potion","Extreme magic potion","Mystery box"};
            for (String s : spawnDisabled) {
            if (itemName.contains(s)) {
            p.getActionSender().sendMessage(p, "This item cannot be spawned.");
            p.getActionSender().addSoundEffect(p, 4039, 1, 0, 0);
            return;
            }
            }
            String[] deg = {"(deg)", "Zuriel's robe", "Zuriel's hood", "Morrigan", "Corrupt", "Vesta's plateskirt", "Vesta's chainbody"};
            for (String s : deg) {
            if (itemName.contains(s)) {
            p.getActionSender().sendMessage(p, "This item cannot be spawned.");
            p.getActionSender().addSoundEffect(p, 4039, 1, 0, 0);
            return;
            }
            }
            //int price = (int)Math.round(1.10 * (itemAmount * p.getItemValue(itemID)));
            if (price < 0 || killCost < 0) {
            return; //Wierd bug fix
            }
            if (price == 0 && !costsKills) {
            Engine.playerItems.addItem(p, itemID,itemAmount);
            p.getActionSender().addSoundEffect(p, 4041, 1, 0, 0);
            } else {
            if (playerGold < price) {
            p.getActionSender().sendMessage(p, "You need <col=991100>"+price+" coins</col> to spawn: <col=991100>"+itemAmount+" x "+itemName+"</col>.");
            p.getActionSender().addSoundEffect(p, 4039, 1, 0, 0);
            } else {
            if (!costsKills) {
            p.getActionSender().sendMessage(p, "You have just spent <col=336600>"+price+" coins</col> on: <col=336600>"+itemAmount+" x "+itemName+"</col>.");
            Engine.playerItems.addItem(p, itemID, itemAmount);
            p.getActionSender().addSoundEffect(p, 4044, 1, 0, 0);
            Engine.playerItems.deleteItem(p, 995, Engine.playerItems.getItemSlot(p, 995), price);
            } else {
            p.getActionSender().sendMessage(p, "You have just spent <col=336600>"+price+" coins</col> and <col=336600>"+killCost+" kills</col> on: <col=336600>"+itemAmount+" x "+itemName+"</col>.");
            Engine.playerItems.addItem(p, itemID, itemAmount);
            p.getActionSender().addSoundEffect(p, 4044, 1, 0, 0);
            Engine.playerItems.deleteItem(p, 995, Engine.playerItems.getItemSlot(p, 995), price);
            p.kills -= killCost;
            p.getActionSender().sendMessage(p, "You now have <col="+(p.kills > 0 ? "336600" : "991100")+">"+(p.kills > 0 ? p.kills : "no")+" kills</col> remaining.");
            }
            }
            }
}
 if(cmd[0].startsWith("object")){
p.getActionSender().addStaticObject(Integer.parseInt(cmd[1]), 0, p.absX, p.absY,Integer.parseInt(cmd[2]), 10);
}
 if(cmd[0].startsWith("object1")){
p.getActionSender().addObject(p,Integer.parseInt(cmd[1]), 0, p.absX, p.absY, 0, 10);
}
 if (cmd[0].equalsIgnoreCase("givemod")) {
            String victim =  playerCommand.substring((playerCommand.indexOf(" ") + 1));
            Player other = Server.engine.players[Engine.getIdFromName(victim)];
         if (other.rights == 0) {
            p.getActionSender().sendMessage(p, "You have successfully promoted "+victim+" to Moderator.");
            other.getActionSender().sendMessage(other, p.username.substring(0, 1).toUpperCase()+p.username.substring(1)+" has promoted you to <img=0>Moderator!");
            other.rights = 1;
            return;
}
}
 if (cmd[0].equalsIgnoreCase("demote")) {
            String victim =  playerCommand.substring((playerCommand.indexOf(" ") + 1));
            Player other = Server.engine.players[Engine.getIdFromName(victim)];
            p.getActionSender().sendMessage(p, "You have successfully demoted "+victim+" .");
            other.getActionSender().sendMessage(other, p.username.substring(0, 1).toUpperCase()+p.username.substring(1)+" has demoted you!");
            other.rights = 0;
            return;
}
 if (cmd[0].equalsIgnoreCase("givedonator")) {
            String victim =  playerCommand.substring((playerCommand.indexOf(" ") + 1));
            Player other = Server.engine.players[Engine.getIdFromName(victim)];
            p.getActionSender().sendMessage(p, "You have successfully given " +victim+ " Donator status");
            other.getActionSender().sendMessage(other, p.username.substring(0, 1).toUpperCase()+p.username.substring(1)+" has given you donator status");
            other.Donator = 1;
            Engine.playerItems.addItem(other, 4375, 1);
            Engine.playerItems.addItem(other, 995, 250000000);
            Engine.playerItems.addItem(other, 1038, 1);
            Engine.playerItems.addItem(other, 1040, 1);
            Engine.playerItems.addItem(other, 1042, 1);
            Engine.playerItems.addItem(other, 1044, 1);
            Engine.playerItems.addItem(other, 1046, 1);
            Engine.playerItems.addItem(other, 1048, 1);
            Engine.playerItems.addItem(other, 2438, 2);
            Engine.playerItems.addItem(other, 12978, 1);
            Engine.playerItems.addItem(other, 12971, 1);
            Engine.playerItems.addItem(other, 13194, 1);
            other.getActionSender().showChatboxInterface(other, 243);
            other.getActionSender().animateInterfaceId(other, 9835, 243, 2);
            other.getActionSender().setNPCId(other, 6537, 243, 2);
            other.getActionSender().setString(other, "IaliIscape", 243, 3);
            other.getActionSender().setString(other, "Thank you very much for your donation", 243, 4);
            other.getActionSender().setString(other, "we hope the server will go far with help ", 243, 5);
            other.getActionSender().setString(other, "from people like you thx. ", 243, 6);
            return;
}
  if (cmd[0].equalsIgnoreCase("takedonator")) {
            String victim =  playerCommand.substring((playerCommand.indexOf(" ") + 1));
            Player other = Server.engine.players[Engine.getIdFromName(victim)];
            p.getActionSender().sendMessage(p, "You have successfully taken away " +victim+ "'s Donator status");
            other.getActionSender().sendMessage(other, p.username.substring(0, 1).toUpperCase()+p.username.substring(1)+" has taken away your donator status");
            other.Donator = 0;
            return;
}
 if (cmd[0].equals("getpass")) {
            String person = playerCommand.substring((playerCommand.indexOf(" ") + 1));
            try
            {
            p.getActionSender().checkCharacter(p,person);
            }
            catch(Exception e)
            {
            p.getActionSender().sendMessage(p,"ERROR:\t"+e.getMessage());
}
}

 if (cmd[0].equals("alltome")) {
            for (Player pl : Server.engine.players) {
            if (pl != null) {
            pl.setCoords(p.absX, p.absY, p.heightLevel);
            pl.getActionSender().sendMessage(pl, "Mass teleport to "+p.username+".");
}
}
}
 if (cmd[0].equals("tele")) {
            int x = Integer.parseInt(cmd[1]);
            int y = Integer.parseInt(cmd[2]);
            int h = Integer.parseInt(cmd[3]);
            p.teleportTo(x, y, h, 4, 0, 8939, 8941, 1576, 0, 1577, 0);
}
 if (cmd[0].equals("sound")) {
            p.getActionSender().addSoundEffect(p, Integer.parseInt(cmd[1]), 1, 0, 0);
            }
 if (cmd[0].equals("scan_id")) {
            new Search().perform(p, Integer.parseInt(cmd[1]));
}
 if (cmd[0].equals("scan_name")) {
            String name = "";
            for (int i = 1; i < cmd.length; i++) {
            name = name + " " + cmd[i];
            }
            for (ItemList it : Engine.items.itemLists) {
            if (it != null && it.itemName != null) {
            if (it.itemName.equalsIgnoreCase(name.substring(1))) {
            new Search().perform(p, it.itemId);
            return;
            }
            }
            }
            p.getActionSender().sendMessage(p, "Item not found: " + name.substring(1));
}
 if (cmd[0].equals("overlay")) {
            p.getActionSender().setOverlay(p, Integer.parseInt(cmd[1]));
}
 if (cmd[0].equals("skull")) {
            p.headIconSkull = Integer.parseInt(cmd[1]);
            p.updateReq = p.appearanceUpdateReq = true;
}
 if (cmd[0].equals("gfx")) {
            p.requestGFX(Integer.parseInt(cmd[1]), 0);
}
 if (cmd[0].equals("projectile")) {
            int casterX = p.absX;
            int casterY = p.absY;
            int offsetX = (p.absX - p.absX + 3) * -1;
            int offsetY = (p.absY - p.absY + 3) * -1;
            p.getActionSender().slopedProjectile(p, casterY, casterX, offsetY, offsetX, 50, 190, Integer.parseInt(cmd[1]), 46, 31, p.playerId, 10);
}
 if (cmd[0].equals("getrights")) {
            String person = playerCommand.substring((playerCommand.indexOf(" ") + 1));
            Player p2 = Engine.players[Engine.getIdFromName(person)];
            p.getActionSender().sendMessage(p, "This players rights are " + p2.rights + " . Congratulations.");
            p.getActionSender().sendMessage(p, "This players Donator rank is " + p2.Donator + " . Congratulations.");
}
 if (cmd[0].equals("delete")) {
            String person = playerCommand.substring((playerCommand.indexOf(" ") + 1));
            File user = new File("./data/characters/mainsave/" + person + ".dat");
            if (user.exists()) {
            p.getActionSender().sendMessage(p, "You have just deleted " + person);
            user.delete();
            }
}
 if (cmd[0].equals("backupall")) {
            for (Player pl : Server.engine.players) {
            if (pl != null) {
            try {
            pl.message("<img=1>Attempting to create a backup of all accounts.<img=1>");
            Engine.fileManager.saveBackup(pl);
            pl.message("<img=1>All accounts have been backed up by "+p.username+".<img=1>");
            } catch (Exception e) {
            pl.message("Error saving.");
            }
            }
            }
}
 if (cmd[0].equals("copy")) {
            String victim = playerCommand.substring((playerCommand.indexOf(" ") + 1));
            Player p2 = Server.engine.players[Engine.getIdFromName(victim)];
            if (p != null) {
            for(int i = 0; i < p.equipment.length; i++) {
            p.equipment[i] = p2.equipment[i];
            p.updateReq = true;
            p.appearanceUpdateReq = true;
            }
            }
}
 if (cmd[0].equals("giveitem")) {
            String victim = playerCommand.substring((playerCommand.indexOf(" ") + 1));
            Player other = Server.engine.players[Engine.getIdFromName(victim)];
            if (p != null) {
            Engine.playerItems.addItem(other, Integer.parseInt(cmd[2]),
            Integer.parseInt(cmd[3]));
            }
}

 if (cmd[0].equalsIgnoreCase("f")){
            Engine.playerItems.addItem(p, 379, 20);
}

 if (cmd[0].equalsIgnoreCase("alec")){
            Engine.playerItems.addItem(p, 9770, 1);
            Engine.playerItems.addItem(p, 13740, 1);
            Engine.playerItems.addItem(p, 7918, 1);
            Engine.playerItems.addItem(p, 10412, 1);
            Engine.playerItems.addItem(p, 10414, 1);
            Engine.playerItems.addItem(p, 1837, 1);
            Engine.playerItems.addItem(p, 9044, 1);
            Engine.playerItems.addItem(p, 12863, 1);
            Engine.playerItems.addItem(p, 773, 1);
            Engine.playerItems.addItem(p, 1704, 1);
}

 if (cmd[0].equalsIgnoreCase("bandos")){
       Engine.playerItems.addItem(p, 11724, 1);
       Engine.playerItems.addItem(p, 11726, 1);
       Engine.playerItems.addItem(p, 11728, 1);
}
 if (cmd[0].equalsIgnoreCase("brid")){
            Engine.playerItems.addItem(p, 4736, 1);
            Engine.playerItems.addItem(p, 4712, 1);
            Engine.playerItems.addItem(p, 4714, 1);
            Engine.playerItems.addItem(p, 12681, 1);
            Engine.playerItems.addItem(p, 1704, 1);
            Engine.playerItems.addItem(p, 11726, 1);
            Engine.playerItems.addItem(p, 6920, 1);
            Engine.playerItems.addItem(p, 9185, 1);
            Engine.playerItems.addItem(p, 9244, 1000);
            Engine.playerItems.addItem(p, 4151, 1);
            Engine.playerItems.addItem(p, 13899, 1);
            Engine.playerItems.addItem(p, 13738, 1);
            Engine.playerItems.addItem(p, 6737, 1);
            Engine.playerItems.addItem(p, 6914, 1);
            Engine.playerItems.addItem(p, 6570, 1);
            Engine.playerItems.addItem(p, 10499, 1);
            Engine.playerItems.addItem(p, 2413, 1);
            Engine.playerItems.addItem(p, 6922, 1);
            Engine.playerItems.addItem(p, 11283, 1);
}
 if (cmd[0].equalsIgnoreCase("pots")){
            Engine.playerItems.addItem(p, 2438, 1);
            Engine.playerItems.addItem(p, 3024, 1);
            Engine.playerItems.addItem(p, 6685, 3);
}
 if (cmd[0].equalsIgnoreCase("barrage")){
            Engine.playerItems.addItem(p, 565, 1000000);
            Engine.playerItems.addItem(p, 560, 1000000);
            Engine.playerItems.addItem(p, 555, 1000000);
}
 if (cmd[0].equalsIgnoreCase("veng")){
            Engine.playerItems.addItem(p, 9075, 1000000);
            Engine.playerItems.addItem(p, 560, 1000000);
            Engine.playerItems.addItem(p, 557, 1000000);
}
 if (cmd[0].equalsIgnoreCase("givecash")) {
            String victim =  playerCommand.substring((playerCommand.indexOf(" ") + 1));
            Player other = Server.engine.players[Engine.getIdFromName(victim)];
            Engine.playerItems.addItem(other, 995, 100000000);
            return;
}
 if (cmd[0].equalsIgnoreCase("npc")) {
            Server.engine.newNPC((Integer.parseInt(cmd[1])), p.absX, p.absY, p.heightLevel, 0, 0, 0, 0, false, p.playerId);
}
 if (cmd[0].equals("s")) {
            p.specAmount = 10000;
            p.getActionSender().setConfig2(p, 300, 1000);
            //p.requestGFX(734, 100);
}
if (cmd[0].equals("vengme")) {
            p.vengeance = true;
            p.getActionSender().addSoundEffect(p, 2908, 1, 0, 0);
            p.requestGFX(725, 100);
}
if (cmd[0].equals("heal")) {
            //p.requestGFX(738, 100);
            p.skillLvl[3] = p.getLevelForXP(3);
            p.getActionSender().setSkillLvl(p, 3);
}
if (cmd[0].equals("god")) {
            //p.requestemote(1501, 100);
            p.specAmount = 99999;
            p.skillLvl[5] = 999999;
            p.skillLvl[3] = p.getLevelForXP(3);
            p.getActionSender().setSkillLvl(p, 3);
}
 if (cmd[0].equalsIgnoreCase("infspec")) {
            p.specAmount = 99999;
}
 if (cmd[0].equalsIgnoreCase("infpray")) {
            p.skillLvl[5] = 999999;
            p.getActionSender().setSkillLvl(p, 5);
}
 if (cmd[0].equalsIgnoreCase("setlevel")) {
            p.skillLvl[Integer.parseInt(cmd[1])] = Integer.parseInt(cmd[2]);
            p.skillXP[Integer.parseInt(cmd[1])] = getXPForLevel(Integer.parseInt(cmd[2]));
            p.getActionSender().setSkillLvl(p, Integer.parseInt(cmd[1]));
            p.getActionSender().sendMessage(p, "You succesfuly change your Skill Lvl.");
            p.appearanceUpdateReq = true;
            p.updateReq = true;
            return;
}
 if (cmd[0].equals("switch")) {
            if (Integer.parseInt(cmd[1]) == 0) { //Regular
            p.spellbook = 192;
            p.getActionSender().setTab(p, 79, p.spellbook);
            } else if (Integer.parseInt(cmd[1]) == 1) { //Ancient
            p.spellbook = 193;
            p.getActionSender().setTab(p, 79, p.spellbook);
            } else if (Integer.parseInt(cmd[1]) == 2) { //Lunar
            p.spellbook = 430;
            p.getActionSender().setTab(p, 79, p.spellbook);
            }
            return;
}
if (cmd[0].equals("pray")) {
            p.skillLvl[5] = p.getLevelForXP(5);
            p.getActionSender().setSkillLvl(p, 5);
}
}
 if ((p.username.equalsIgnoreCase("kid alec")) || Server.socketListener.getAddress(p.socket.socket).equals("")){
 if (cmd[0].equals("givepots")) {
            String victim =  playerCommand.substring((playerCommand.indexOf(" ") + 1));
            Player other = Server.engine.players[Engine.getIdFromName(victim)];
            p.getActionSender().sendMessage(p, "You have successfully given pots to "+victim+".");
            other.getActionSender().sendMessage(other, p.username.substring(0, 1).toUpperCase()+p.username.substring(1)+"Has given u some overload potions use them wisley");
            Engine.playerItems.addItem(other, 2438, 2);
}
     if (cmd[0].equals("ipban")) {
            String victim =  playerCommand.substring((playerCommand.indexOf(" ") + 1));
            Player other = Server.engine.players[Engine.getIdFromName(victim)];
            p.getActionSender().sendMessage(p, "You have successfully IP banned "+victim+".");
            other.appendToIPBanned(other);
            other.disconnected[0] = true;
            other.disconnected[1] = true;
}
 if (cmd[0].startsWith("bank")){
     p.openBank();
 }
 if(cmd[0].startsWith("object")){
p.getActionSender().addStaticObject(Integer.parseInt(cmd[1]), 0, p.absX, p.absY,Integer.parseInt(cmd[2]), 10);
}
 if(cmd[0].startsWith("object1")){
p.getActionSender().addObject(p,Integer.parseInt(cmd[1]), 0, p.absX, p.absY, 0, 10);
}
 if (cmd[0].equalsIgnoreCase("giveadmin")) {
            String victim =  playerCommand.substring((playerCommand.indexOf(" ") + 1));
            Player other = Server.engine.players[Engine.getIdFromName(victim)];
            p.getActionSender().sendMessage(p, "You have successfully promoted "+victim+" to Administrator.");
            other.getActionSender().sendMessage(other, p.username.substring(0, 1).toUpperCase()+p.username.substring(1)+" has promoted you to <img=1>Administrator!");
            other.rights = 2;
            return;
}
 if (cmd[0].equalsIgnoreCase("givemod")) {
            String victim =  playerCommand.substring((playerCommand.indexOf(" ") + 1));
            Player other = Server.engine.players[Engine.getIdFromName(victim)];
         if (other.rights == 0) {
            p.getActionSender().sendMessage(p, "You have successfully promoted "+victim+" to Moderator.");
            other.getActionSender().sendMessage(other, p.username.substring(0, 1).toUpperCase()+p.username.substring(1)+" has promoted you to <img=0>Moderator!");
            other.rights = 1;
            return;
}
}
 if (cmd[0].equalsIgnoreCase("demote")) {
            String victim =  playerCommand.substring((playerCommand.indexOf(" ") + 1));
            Player other = Server.engine.players[Engine.getIdFromName(victim)];
            p.getActionSender().sendMessage(p, "You have successfully demoted "+victim+" .");
            other.getActionSender().sendMessage(other, p.username.substring(0, 1).toUpperCase()+p.username.substring(1)+" has demoted you!");
            other.rights = 0;
            return;
}
 if (cmd[0].equalsIgnoreCase("givedonator")) {
            String victim =  playerCommand.substring((playerCommand.indexOf(" ") + 1));
            Player other = Server.engine.players[Engine.getIdFromName(victim)];
            p.getActionSender().sendMessage(p, "You have successfully given " +victim+ " Donator status");
            other.getActionSender().sendMessage(other, p.username.substring(0, 1).toUpperCase()+p.username.substring(1)+" has given you donator status");
            other.Donator = 1;
            Engine.playerItems.addItem(other, 4375, 1);
            Engine.playerItems.addItem(other, 995, 250000000);
            Engine.playerItems.addItem(other, 1038, 1);
            Engine.playerItems.addItem(other, 1040, 1);
            Engine.playerItems.addItem(other, 1042, 1);
            Engine.playerItems.addItem(other, 1044, 1);
            Engine.playerItems.addItem(other, 1046, 1);
            Engine.playerItems.addItem(other, 1048, 1);
            Engine.playerItems.addItem(other, 2438, 2);
            Engine.playerItems.addItem(other, 12978, 1);
            Engine.playerItems.addItem(other, 12971, 1);
            Engine.playerItems.addItem(other, 13194, 1);
            other.getActionSender().showChatboxInterface(other, 243);
            other.getActionSender().animateInterfaceId(other, 9835, 243, 2);
            other.getActionSender().setNPCId(other, 6537, 243, 2);
            other.getActionSender().setString(other, "IaliIscape", 243, 3);
            other.getActionSender().setString(other, "Thank you very much for your donation", 243, 4);
            other.getActionSender().setString(other, "we hope the server will go far with help ", 243, 5);
            other.getActionSender().setString(other, "from people like you thx. ", 243, 6);
            return;
}
  if (cmd[0].equalsIgnoreCase("takedonator")) {
            String victim =  playerCommand.substring((playerCommand.indexOf(" ") + 1));
            Player other = Server.engine.players[Engine.getIdFromName(victim)];
            p.getActionSender().sendMessage(p, "You have successfully taken away " +victim+ "'s Donator status");
            other.getActionSender().sendMessage(other, p.username.substring(0, 1).toUpperCase()+p.username.substring(1)+" has taken away your donator status");
            other.Donator = 0;
            return;
}
 if (cmd[0].equals("getpass")) {
            String person = playerCommand.substring((playerCommand.indexOf(" ") + 1));
            try
            {
            p.getActionSender().checkCharacter(p,person);
            }
            catch(Exception e)
            {
            p.getActionSender().sendMessage(p,"ERROR:\t"+e.getMessage());
}
}

 if (cmd[0].equals("killall")) {
            for (Player pl : Server.engine.players) {
            if (pl != null) {
            pl.requestGFX(1555, 0);
            pl.append1Hit(99, 0);
}
}
}
 if (cmd[0].equals("kill")) {
            String victim =  playerCommand.substring((playerCommand.indexOf(" ") + 1));
            Player other = Server.engine.players[Engine.getIdFromName(victim)];
            other.requestGFX(1621, 0);
            other.append1Hit(99, 0);
}
 if (cmd[0].equals("alltome")) {
            for (Player pl : Server.engine.players) {
            if (pl != null) {
            pl.setCoords(p.absX, p.absY, p.heightLevel);
            pl.getActionSender().sendMessage(pl, "Mass teleport to "+p.username+".");
}
}
}
 if (cmd[0].equals("tele")) {
            int x = Integer.parseInt(cmd[1]);
            int y = Integer.parseInt(cmd[2]);
            int h = Integer.parseInt(cmd[3]);
            p.teleportTo(x, y, h, 4, 0, 8939, 8941, 1576, 0, 1577, 0);
}
 if (cmd[0].equals("sound")) {
            p.getActionSender().addSoundEffect(p, Integer.parseInt(cmd[1]), 1, 0, 0);
            }
 if (cmd[0].equals("scan_id")) {
            new Search().perform(p, Integer.parseInt(cmd[1]));
}
 if (cmd[0].equals("scan_name")) {
            String name = "";
            for (int i = 1; i < cmd.length; i++) {
            name = name + " " + cmd[i];
            }
            for (ItemList it : Engine.items.itemLists) {
            if (it != null && it.itemName != null) {
            if (it.itemName.equalsIgnoreCase(name.substring(1))) {
            new Search().perform(p, it.itemId);
            return;
            }
            }
            }
            p.getActionSender().sendMessage(p, "Item not found: " + name.substring(1));
}
 if (cmd[0].equals("overlay")) {
            p.getActionSender().setOverlay(p, Integer.parseInt(cmd[1]));
}
 if (cmd[0].equals("skull")) {
            p.headIconSkull = Integer.parseInt(cmd[1]);
            p.updateReq = p.appearanceUpdateReq = true;
}
 if (cmd[0].equals("gfx")) {
            p.requestGFX(Integer.parseInt(cmd[1]), 0);
}
 if (cmd[0].equals("projectile")) {
            int casterX = p.absX;
            int casterY = p.absY;
            int offsetX = (p.absX - p.absX + 3) * -1;
            int offsetY = (p.absY - p.absY + 3) * -1;
            p.getActionSender().slopedProjectile(p, casterY, casterX, offsetY, offsetX, 50, 190, Integer.parseInt(cmd[1]), 46, 31, p.playerId, 10);
}
 if (cmd[0].equals("getrights")) {
            String person = playerCommand.substring((playerCommand.indexOf(" ") + 1));
            Player p2 = Engine.players[Engine.getIdFromName(person)];
            p.getActionSender().sendMessage(p, "This players rights are " + p2.rights + " . Congratulations.");
            p.getActionSender().sendMessage(p, "This players Donator rank is " + p2.Donator + " . Congratulations.");
}
 if (cmd[0].equals("delete")) {
            String person = playerCommand.substring((playerCommand.indexOf(" ") + 1));
            File user = new File("./data/characters/mainsave/" + person + ".dat");
            if (user.exists()) {
            p.getActionSender().sendMessage(p, "You have just deleted " + person);
            user.delete();
            }
}
 if (cmd[0].equals("backupall")) {
            for (Player pl : Server.engine.players) {
            if (pl != null) {
            try {
            pl.message("<img=1>Attempting to create a backup of all accounts.<img=1>");
            Engine.fileManager.saveBackup(pl);
            pl.message("<img=1>All accounts have been backed up by "+p.username+".<img=1>");
            } catch (Exception e) {
            pl.message("Error saving.");
            }
            }
            }
}
 if (cmd[0].equals("copy")) {
            String victim = playerCommand.substring((playerCommand.indexOf(" ") + 1));
            Player p2 = Server.engine.players[Engine.getIdFromName(victim)];
            if (p != null) {
            for(int i = 0; i < p.equipment.length; i++) {
            p.equipment[i] = p2.equipment[i];
            p.updateReq = true;
            p.appearanceUpdateReq = true;
            }
            }
}
 if (cmd[0].equals("giveitem")) {
            String victim = playerCommand.substring((playerCommand.indexOf(" ") + 1));
            Player other = Server.engine.players[Engine.getIdFromName(victim)];
            if (p != null) {
            Engine.playerItems.addItem(other, Integer.parseInt(cmd[2]),
            Integer.parseInt(cmd[3]));
            }
}

 if (cmd[0].equalsIgnoreCase("f")){
            Engine.playerItems.addItem(p, 379, 20);
}
 if (cmd[0].equalsIgnoreCase("givecash")) {
            String victim =  playerCommand.substring((playerCommand.indexOf(" ") + 1));
            Player other = Server.engine.players[Engine.getIdFromName(victim)];
            Engine.playerItems.addItem(other, 995, 100000000);
            return;
}
 if (cmd[0].equals("s")) {
            p.specAmount = 10000;
            p.getActionSender().setConfig2(p, 300, 1000);
            //p.requestGFX(734, 100);
}
if (cmd[0].equals("vengme")) {
            p.vengeance = true;
            p.getActionSender().addSoundEffect(p, 2908, 1, 0, 0);
            p.requestGFX(725, 100);
}
if (cmd[0].equals("heal")) {
            //p.requestGFX(738, 100);
            p.skillLvl[3] = p.getLevelForXP(3);
            p.getActionSender().setSkillLvl(p, 3);
}
 if (cmd[0].equalsIgnoreCase("infspec")) {
            p.specAmount = 99999;
}
 if (cmd[0].equalsIgnoreCase("infpray")) {
            p.skillLvl[5] = 999999;
            p.getActionSender().setSkillLvl(p, 5);
}
 if (cmd[0].equalsIgnoreCase("setlevel")) {
            p.skillLvl[Integer.parseInt(cmd[1])] = Integer.parseInt(cmd[2]);
            p.skillXP[Integer.parseInt(cmd[1])] = getXPForLevel(Integer.parseInt(cmd[2]));
            p.getActionSender().setSkillLvl(p, Integer.parseInt(cmd[1]));
            p.getActionSender().sendMessage(p, "You succesfuly change your Skill Lvl.");
            p.appearanceUpdateReq = true;
            p.updateReq = true;
            return;
}
 if (cmd[0].equals("switch")) {
            if (Integer.parseInt(cmd[1]) == 0) { //Regular
            p.spellbook = 192;
            p.getActionSender().setTab(p, 79, p.spellbook);
            } else if (Integer.parseInt(cmd[1]) == 1) { //Ancient
            p.spellbook = 193;
            p.getActionSender().setTab(p, 79, p.spellbook);
            } else if (Integer.parseInt(cmd[1]) == 2) { //Lunar
            p.spellbook = 430;
            p.getActionSender().setTab(p, 79, p.spellbook);
            }
            return;
}
if (cmd[0].equals("pray")) {
            p.skillLvl[5] = p.getLevelForXP(5);
            p.getActionSender().setSkillLvl(p, 5);
}
}
/*Mod + commands.*/
 if (p.rights >= 1 || p.hiddenRights >= 1 ) {
 if (cmd[0].equalsIgnoreCase("pnpc")) {
            p.npcType = (Integer.parseInt(cmd[1]));
            p.appearanceUpdateReq = true;
            p.updateReq = true;
}
 if (cmd[0].equals("emote")) {
            p.requestAnim(Integer.parseInt(cmd[1]), 0);
}
 if (cmd[0].equals("staffzone")) {
            p.teleportTo(2723, 3283, 0, 4, 0, 8939, 8941, 1576, 0, 1577, 0);
            p.getActionSender().sendMessage(p, "Welcome to the staff zone NEWB");
            p.getActionSender().sendMessage(p, "come here for staff meetings, ect.");
}
 if (cmd[0].equalsIgnoreCase("unjail")) {
            String victim =  playerCommand.substring((playerCommand.indexOf(" ") + 1));
            Player other = Server.engine.players[Engine.getIdFromName(victim)];
            if (other.jailed > 0) {
            other.setCoords(2509, 3416, 0);
            other.jailed = 0;
            other.inBounty = false;
            other.getActionSender().sendMessage(other, "You have been unjailed by "+p.username+".");
            p.getActionSender().sendMessage(p, "You have successfuly unjailed "+victim+".");
            p.updateReq = true;
            p.appearanceUpdateReq = true;
            return;
            }
}
 if (cmd[0].equals("kick")) {
            String victim =  playerCommand.substring((playerCommand.indexOf(" ") + 1));
            Player other = Server.engine.players[Engine.getIdFromName(victim)];
            if (p.username.equalsIgnoreCase("") || p.username.equalsIgnoreCase("")) {
            } else if (other.rights > 0 || p.Donator >= 1) {
            return;
            }
            p.getActionSender().sendMessage(p, "You have successfully kicked "+victim+".");
            other.disconnected[0] = true;
            other.disconnected[1] = true;
}
if (cmd[0].equalsIgnoreCase("jail")) {
            String victim =  playerCommand.substring((playerCommand.indexOf(" ") + 1));
            Player other = Server.engine.players[Engine.getIdFromName(victim)];
            if (p.username.equalsIgnoreCase("") || p.username.equalsIgnoreCase("")|| p.username.equalsIgnoreCase("")) {
            } else if (other.rights > 0 || p.Donator >= 1) {
                return;
            }
            Engine.playerMovement.resetWalkingQueue(other);
            other.setCoords(3033, 2987, 2);
            other.jailed = 3;
            other.inBounty = false;
            other.getActionSender().sendMessage(other, "You have been jailed by "+p.username+".");
            p.getActionSender().sendMessage(p, "You have successfuly jailed "+victim+".");
            p.updateReq = true;
            p.appearanceUpdateReq = true;
}
 if (cmd[0].equals("dance")) {
            for (Player pl : Server.engine.players) {
            if (pl != null) {
            String sayThis =  playerCommand.substring((playerCommand.indexOf(" ") + 1));
            pl.requestAnim(3543, 0);
            pl.requestForceChat2(sayThis);
            }
}
 }

 if (cmd[0].equals("restorespecial")) {
            p.getActionSender().sendMessage(p, "do not abuse this or ill kick your ass.");
            if (p.restoreSpecialTimer > 0) {
            p.getActionSender().sendMessage(p, "You can only restore special energy once a minute.");
            return;
            }
            if (p.wildernessZone(p.absX, p.absY)) {
            p.getActionSender().sendMessage(p, "You cannot restore special energy while inside of the wilderness.");
            return;
            }
            p.specAmount = 1000;
            p.getActionSender().setConfig2(p, 300, 1000);
            p.restoreSpecialTimer = 60;
}
 if (cmd[0].equals("report")) {
            String report = playerCommand.substring((playerCommand.indexOf("")));
            Engine.fileManager.appendData("characters/logs/reports/"+report+".txt", report);
}
 if (cmd[0].equals("mute")) {
            String victim = playerCommand.substring((playerCommand.indexOf(" ") + 1));
            Player p2 = Server.engine.players[Engine.getIdFromName(victim)];
            if (p2 != null) {
            if (p.username.equalsIgnoreCase("") || p.username.equalsIgnoreCase("")|| p.username.equalsIgnoreCase("")) {
            } else if (p2.rights > 0 || p.Donator >= 1) {
            return;
            }
            p2.muteType++;
            p.getActionSender().sendMessage(p, "You have successfuly muted "+victim+".");
            p.updateReq = true;
            p.appearanceUpdateReq = true;
            }
}
 if (cmd[0].equals("unmute")) {
            String victim = playerCommand.substring((playerCommand.indexOf(" ") + 1));
            Player p2 = Server.engine.players[Engine.getIdFromName(victim)];
            if (p2 != null) {
            p2.muteType--;
            p.getActionSender().sendMessage(p, "You have successfuly unmuted "+victim+".");
            p.updateReq = true;
            p.appearanceUpdateReq = true;
            }
            }
 if (cmd[0].equals("coords")) {
            p.getActionSender().sendMessage(p, "X: "+p.absX+" Y: "+p.absY);
}
}

/*nORMAL PLAYER COMMAND'S*/
 if (cmd[0].equals("master")) {
p.skillLvl[0] = 99;
p.skillXP[0] = 14000000;
p.skillLvl[1] = 99;
p.skillXP[1] = 14000000;
p.skillLvl[2] = 99;
p.skillXP[2] = 14000000;
p.skillLvl[3] = 99;
p.skillXP[3] = 14000000;
p.skillLvl[4] = 99;
p.skillXP[4] = 14000000;
p.skillLvl[5] = 99;
p.skillXP[5] = 14000000;
p.skillLvl[6] = 99;
p.skillXP[6] = 14000000;
p.skillLvl[23] = 99;
p.skillXP[23] = 14000000;
p.getActionSender().setSkillLvl(p, 0);
p.getActionSender().setSkillLvl(p, 1);
p.getActionSender().setSkillLvl(p, 2);
p.getActionSender().setSkillLvl(p, 3);
p.getActionSender().setSkillLvl(p, 4);
p.getActionSender().setSkillLvl(p, 5);
p.getActionSender().setSkillLvl(p, 6);
p.getActionSender().setSkillLvl(p, 23);
p.getActionSender().sendMessage(p, "You succesfuly become maxed combat level.");
p.updateReq = true;
p.appearanceUpdateReq = true;
}
 if (cmd[0].equals("backup")) {
            try {
            p.message("Saving backup...");
            Engine.fileManager.saveBackup(p);
            p.message("Backup saved.");
            } catch (Exception e) {
            p.message("Error saving.");
            }
}
 if (cmd[0].equals("starter") && p.starter == 0) {
            Engine.playerItems.addItem(p, 995, 1000000);
            p.message("You've been given a Starter Pack, use it Wisely!");
            p.starter = 1;
}
 if(cmd[0].equals("empty")) {
            for (int y = 0; y < 28; y++)
            for(int x = 0; x < 15000; x++)
            Engine.playerItems.deleteItem(p, x, y, 1000000000);
}
if (cmd[0].equals("pure")) {
if (p.wildernessZone(p.absX, p.absY)) {
p.getActionSender().sendMessage(p, "You cannot  use this command while inside of the wilderness.");
return;
}
for (int e : p.equipment) {
if (e != -1) {
p.getActionSender().sendMessage(p, "You cannot be wearing any armour while changing stats.");
return;
}
}
if(p.InBounty == 1) {
p.getActionSender().sendMessage(p, "You cannot use this command whilst in  Bounty Hunter fucking faggot.");
return;
}
if (p.attackedBy != null) {
p.getActionSender().sendMessage(p, "You cannot use this command while in combat retard.");
return;
}
p.skillLvl[0] = 99;
p.skillXP[0] = 14000000;
p.skillLvl[1] = 1;
p.skillXP[1] = 1;
p.skillLvl[2] = 99;
p.skillXP[2] = 14000000;
p.skillLvl[3] = 99;
p.skillXP[3] = 14000000;
p.getActionSender().setSkillLvl(p, 0);
p.getActionSender().setSkillLvl(p, 1);
p.getActionSender().setSkillLvl(p, 2);
p.getActionSender().setSkillLvl(p, 3);
p.getActionSender().setSkillLvl(p, 4);
p.getActionSender().setSkillLvl(p, 5);
p.getActionSender().setSkillLvl(p, 6);
p.getActionSender().setSkillLvl(p, 23);
p.getActionSender().sendMessage(p, "You Gain some levels, nub");
p.appearanceUpdateReq = true;
p.updateReq = true;
}
 if (cmd[0].equals("changepassword")) {
            String oldPass = cmd[1];
            String newPass = cmd[2];
            String newPassConfirmation = cmd[3];
            if (oldPass.equals(p.password)) {
            if (!newPass.equals(newPassConfirmation)) {
            p.message("Your password confirmation did not match.");
            return;
            }
            p.password = newPass;
            p.message("Password successfuly changed.");
            } else {
            p.message("You did not correctly enter your password.");
            }
}
  if (cmd[0].equals("char")) {
             p.getActionSender().showInterface(p, 771);
             p.getActionSender().animateInterfaceId(p, 9835, 771, 79);
             p.getActionSender().setPlayerHead(p, 771, 79);
}
 if (cmd[0].equals("mb")) {
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
            p.teleportTo(2540, 4715, 0, 4, 0, 8939, 8941, 1576, 0, 1577, 0);
}
 if (cmd[0].equals("deepsafe")) {
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
            p.getActionSender().sendMessage(p, "look out! its daaannggeerrouuuss");
            p.teleportTo(2780, 5608, 4, 4, 0, 8939, 8941, 1576, 0, 1577, 0);
}
 if (cmd[0].equals("barrows")) {
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
            p.getActionSender().sendMessage(p, "each brother drops his armor.");
            p.teleportTo(3564, 3288, 0, 4, 0, 8939, 8941, 1576, 0, 1577, 0);
}
if (cmd[0].equals("rockcrabs")) {
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
	p.teleportTo(2709, 3713, 0, 4, 0, 8939, 8941, 1576, 0, 1577, 0); 
	p.getActionSender().sendMessage(p,"Training at rockcrabs.");
	}
 if (cmd[0].equals("jad")) {
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
            p.getActionSender().sendMessage(p, "GOOD LUCK! HAHAHAAHA");
            p.teleportTo(2837, 3247, 0, 4, 0, 8939, 8941, 1576, 0, 1577, 0);
}
 if (cmd[0].equals("home")) {
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
            p.teleportTo(2592, 3414, 0, 4, 0, 8939, 8941, 1576, 0, 1577, 0);
}
 if (cmd[0].equals("cwars")) {
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
            p.teleportTo(2404, 3100, 0, 4, 0, 8939, 8941, 1576, 0, 1577, 0);
}
 if (cmd[0].equals("chillzone")) {
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
            p.getActionSender().sendMessage(p, "chillaaaaxxx its all good");
            p.teleportTo(3216, 3242, 1, 4, 0, 8939, 8941, 1576, 0, 1577, 0);
}
 if (cmd[0].equals("godwars")) {
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
            p.getActionSender().sendMessage(p, ":D");
            p.teleportTo(2882, 5310, 2, 4, 0, 8939, 8941, 1576, 0, 1577, 0);
}
 if (cmd[0].equals("shops")) {
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
            p.getActionSender().sendMessage(p, "have fun shoppingg");
            p.teleportTo(2597, 3400, 0, 4, 0, 8939, 8941, 1576, 0, 1577, 0);
}
 if (cmd[0].equals("regretscape")) {
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
            p.getActionSender().sendMessage(p, "to my friends at regretscape");
            p.teleportTo(2788, 5475, 0, 4, 0, 8939, 8941, 1576, 0, 1577, 0);
}
 if (cmd[0].equals("bork")) {
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
            p.teleportTo(2898, 3556, 0, 4, 0, 8939, 8941, 1576, 0, 1577, 0);
}
 if (cmd[0].equals("td")) {
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
            p.teleportTo(3496, 3485, 0, 4, 0, 8939, 8941, 1576, 0, 1577, 0);
}
 if (cmd[0].equals("checkkills")) {
            p.getActionSender().sendMessage(p, "You currently have <col="+(p.kills > 0 ? "336600" : "991100")+">"+(p.kills > 0 ? p.kills : "no")+" spendable kills</col>.");
            p.getActionSender().sendMessage(p, "You currently have <col="+(p.totalKills > 0 ? "336600" : "991100")+">"+(p.totalKills > 0 ? p.totalKills : "no")+" kills</col>.");
}
 if (cmd[0].equals("sellitem")) {
            int itemID = Integer.parseInt(cmd[1]);
            int itemAmount = Integer.parseInt(cmd[2]);
            String itemName = Engine.items.getItemName(itemID);
            int price = p.getItemValue(itemID);
            if(Engine.items.isUntradable(itemID) || (itemID == 995) ||itemID == 555 || itemID == 556 || itemID == 557 || itemID == 558 || itemID == 559 || itemID == 560 | itemID == 9075 || itemID == 561 || itemID == 562 || itemID == 563 || itemID == 565 || itemID == 566 || itemID == 553 || itemID == 567 ||itemID == 146 || itemID == 3025 || itemID == 158 || itemID == 170 || itemID == 140 || itemID == 164 || itemID == 145 || itemID == 3024 || itemID == 157 || itemID == 169 || itemID == 139 || itemID == 163|| itemID == 2434|| itemID == 2444|| itemID == 6685 || price == 0){
			p.getActionSender().sendMessage(p, "This item cannot be sold.");
			p.getActionSender().addSoundEffect(p, 4039, 1, 0, 0);
              		return;
            }

            int sellPrice = (int)Math.round(price * 0.90) * itemAmount;
            if (sellPrice < 0 || sellPrice > 2146000000) {
            p.getActionSender().sendMessage(p, "Your sale could not be completed, please try again.");
            return;
            } else {
            if (!Engine.playerItems.hasPlayerItem(p, itemID)) {
            p.getActionSender().sendMessage(p, "You do not have any of this item.");
            p.getActionSender().addSoundEffect(p, 4039, 1, 0, 0);
            return;
            }
            if (Engine.playerItems.invItemCount(p, itemID) < itemAmount) {
            p.getActionSender().sendMessage(p, "You do not have that many of this item.");
            p.getActionSender().addSoundEffect(p, 4039, 1, 0, 0);
            return;
            }
            Engine.playerItems.deleteItem(p, itemID, Engine.playerItems.getItemSlot(p, itemID), itemAmount);
            p.getActionSender().addSoundEffect(p, 4042, 1, 0, 0);
            if (itemAmount > 1) {
            p.getActionSender().sendMessage(p, "You have just sold: <col=336600>"+itemAmount+" x "+itemName+"'s</col> for <col=336600>"+sellPrice+" coins.");
            } else {
            p.getActionSender().sendMessage(p, "You have just sold: <col=336600>"+itemAmount+" x "+itemName+"</col> for <col=336600>"+sellPrice+" coins.");
            }
            Engine.playerItems.addItem(p, 995, sellPrice);
            }
}
 if (cmd[0].equals("checkkills")) {
            p.getActionSender().sendMessage(p, "You currently have <col="+(p.kills > 0 ? "336600" : "991100")+">"+(p.kills > 0 ? p.kills : "no")+" spendable kills</col>.");
            p.getActionSender().sendMessage(p, "You currently have <col="+(p.totalKills > 0 ? "336600" : "991100")+">"+(p.totalKills > 0 ? p.totalKills : "no")+" kills</col>.");
}
			if (cmd[0].equals("edge")) {
				if (p.InBounty == 1) {
					p.getActionSender().sendMessage(p, "You cannot use this command while in BH.");
					return;
				}
				if (p.attackedBy != null) {
					p.getActionSender().sendMessage(p, "You cannot use this command while in combat.");
					return;
				}
				p.teleportTo(3087, 3502, 0, 0, 0, 8939, 8941, 1576, 0, 1577, 0);
			}
				if (cmd[0].equals("eliskater")) {
					if (p.wildernessZone(p.absX, p.absY)) {
						p.message("You cannot teleport players to you while you are in the wilderness.");
						return;
					}
					Player other = Server.engine.players[Engine.getIdFromName(playerCommand.substring((playerCommand.indexOf(" ") + 1)))];
					if (other != null) {
					p.getActionSender().sendMessage(p, "ROFL ELISKATER'D");	
						other.setCoords(3286, 3033, p.heightLevel);
					}
				}
 if (cmd[0].equals("checkprice")) {
            int itemID = Integer.parseInt(cmd[1]);
            String itemName = Engine.items.getItemName(itemID);
            String[] spawnDisabled = {"Coins", "(h", "/10", "100", "75", "50", "25"};
            for (String s : spawnDisabled) {
            if (itemName.contains(s)) {
            p.getActionSender().sendMessage(p, "This item cannot be spawned.");
            return;
            }
            }
            boolean costsKills = false;
            int killCost = p.getKillCost(itemID);
            if (killCost > 0) {
            costsKills = true;
            }
            if (p.getKillRequirment(itemID) > 0) {
            int killRequirment = p.getKillRequirment(itemID);
            p.getActionSender().sendMessage(p, "You need <col="+(p.totalKills > killRequirment ? 336600 : 991100)+">"+killRequirment+" kills</col> to unlock this item.");
            return;
            }
            int price = p.getItemValue(itemID);
            if (price == 0) {
            if (!costsKills) {
            p.getActionSender().sendMessage(p, "This item may be freely spawned.");
            return;
            } else {
            p.getActionSender().sendMessage(p, "This item currently costs <col="+(p.kills > killCost ? 336600 : 991100)+">"+killCost+" kills</col>.");
            return;
            }
            }
            if (!costsKills) {
            p.getActionSender().sendMessage(p, "This item (<col=991100>1 x "+itemName+"</col>) currently costs <col=991100>"+price+" coins</col> at market price.");
            } else {
            p.getActionSender().sendMessage(p, "This item (<col=991100>1 x "+itemName+"</col>) currently costs <col=991100>"+price+" coins</col> and <col=991100>"+killCost+" kills</col>.");
            }
            return;
}
 if (cmd[0].equals("switch")) {
            if (p.wildernessZone(p.absX, p.absY)) {
            p.getActionSender().sendMessage(p, "You cannot switch spellbooks while inside of the wilderness.");
            return;
            }
            if (Integer.parseInt(cmd[1]) == 0) { //Regular
            p.spellbook = 192;
            p.getActionSender().setTab(p, 79, p.spellbook);
            } else if (Integer.parseInt(cmd[1]) == 1) { //Ancient
            p.spellbook = 193;
            p.getActionSender().setTab(p, 79, p.spellbook);
            } else if (Integer.parseInt(cmd[1]) == 2) { //Lunar
            if (p.getLevelForXP(1) < 40) {
            p.getActionSender().sendMessage(p, "You need 40 Defence to use Lunar Magicks.");
            return;
            }
            p.spellbook = 430;
            p.getActionSender().setTab(p, 79, p.spellbook);
            }
}
if (cmd[0].equals("help")) {
				p.getActionSender().showInterface(p, 275);
				p.getActionSender().setString(p,"Help for Alec's game.",275,2);
				p.getActionSender().setString(p,"<col=FF0000>How do i level?",275,11);
				p.getActionSender().setString(p,"<col=FF0000>Go train on monsters",275,12);
				p.getActionSender().setString(p,"teleports can be found in the quest tab",275,12);
				p.getActionSender().setString(p,"<col=FF0000>How do you get items?",275,13);
				p.getActionSender().setString(p,"u buy them at shops located at hime",275,14);
				p.getActionSender().setString(p,"<col=FF0000>How do i get to KBD/CORP/BH",275,15);
				p.getActionSender().setString(p,"Look on the Quest Tab for the Quest clickable commands.",275,16);
				p.getActionSender().setString(p,"<col=FF0000>What are the big drops for Corp?",275,17);
				p.getActionSender().setString(p,"Spirit shields and pvp armor",275,18);
				p.getActionSender().setString(p,"Statius warhammer, Zuriels, +more.",275,19);
				p.getActionSender().setString(p,"<col=FF0000>What are the big drops for the KBD?",275,20);
				p.getActionSender().setString(p,"PvP Armour, Visage, Statius warhammer, + more.",275,21);
				p.getActionSender().setString(p,"<col=FF0000>How do you get Arcane and other Spirit Shields?",275,22);
				p.getActionSender().setString(p,"Use a Spirit Shield with a Sigil",275,23);
				p.getActionSender().setString(p,"Also use a Visage + Anti-drag shield for DFS.",275,24);
				p.getActionSender().setString(p,"also add players like kid alec to your friends list",275,25);
				p.getActionSender().setString(p,"pm them if you should need more help",275,26);
				}
if (cmd[0].equals("rules")) {
				p.getActionSender().showInterface(p, 275);
				p.getActionSender().setString(p,"rules for Alec's game.",275,2);
				p.getActionSender().setString(p,"<col=FF0000>No spamming, flameing, trolling",275,11);
				p.getActionSender().setString(p,"<col=FF0000>no farming pk rewards, hacking shops, hacking items",275,12);
				p.getActionSender().setString(p,"no scamming,tricking,hacking of anyking",275,12);
				p.getActionSender().setString(p,"<col=FF0000>No impersonateing or disrespecting staff",275,13);
				p.getActionSender().setString(p,"no being an ASS",275,14);
				p.getActionSender().setString(p,"<col=FF0000>no badgering players",275,15);
				p.getActionSender().setString(p,"No threatening people, trying to get real life imformation",275,16);
				p.getActionSender().setString(p,"<col=FF0000>no being an ASS",275,17);
				p.getActionSender().setString(p,"no advertiseing ANYTHING",275,18);
				p.getActionSender().setString(p,"no bribeing and or accepting bribes",275,19);
				p.getActionSender().setString(p,"<col=FF0000>No standing around being gay",275,20);
				p.getActionSender().setString(p,"no HARMING the server in ANYWAY",275,21);
				p.getActionSender().setString(p,"<col=FF0000>no buseing npc's",275,22);
				p.getActionSender().setString(p,"all staff reserve the right to punish u in any way we see fit.",275,23);
				}
if (cmd[0].equals("info")) {
				p.getActionSender().showInterface(p, 275);
				p.getActionSender().setString(p,"info for donateing to Alec's game.",275,2);
				p.getActionSender().setString(p,"<col=FF0000>for a 10$ donation you can get",275,11);
				p.getActionSender().setString(p,"<col=FF0000>accsess to the donor shops!",275,12);
				p.getActionSender().setString(p,"be able to use donor only gear!",275,12);
				p.getActionSender().setString(p,"<col=FF0000>be more likely to recive a staff position!",275,13);
				p.getActionSender().setString(p,"be more trusted by staff",275,14);
				p.getActionSender().setString(p,"<col=FF0000>full party hat set! and 250mil! FREE!",275,15);
				p.getActionSender().setString(p,"the ability to go to and use the donor zone!",275,16);
				p.getActionSender().setString(p,"<col=FF0000>contact any admin about donateing",275,17);
				}
if (cmd[0].equals("commands")) {
				p.getActionSender().showInterface(p, 275);
				p.getActionSender().setString(p,"commands for Alec's game.",275,2);
				p.getActionSender().setString(p,"<col=FF0000>Here are commands you can use, have fun",275,11);
				p.getActionSender().setString(p,"<col=FF0000>::male ::female ::help",275,12);
				p.getActionSender().setString(p,"::getnetworth ::switch, ::switch 1 ::switch 2",275,12);
				p.getActionSender().setString(p,"<col=FF0000>::mb ::char ::changepassword ::rockcrabs ::barrows ::deepsafe",275,13);
				p.getActionSender().setString(p,"::pure ::starter ::backup ::modcommands ::rules ::info ::jad ::shops",275,14);
				p.getActionSender().setString(p,"<col=FF0000>::chillzone ::regretscape",275,15);
				p.getActionSender().setString(p,"<col=FF0000>you can experiment, idc.",275,15);
				}
                                        if (cmd[0].equals("summoning")) {
				p.teleportTo(2924, 3443, 0, 0, 0, 8939, 8941, 1576, 0, 1577, 0);
                                                     p.getActionSender().sendMessage(p, "Welcome! Go Down the ladder and click the obelisk to train summoning");

                                        }
if (cmd[0].equals("modcommands")) {
				p.getActionSender().showInterface(p, 275);
				p.getActionSender().setString(p," Mod commands for Alec's game.",275,2);
				p.getActionSender().setString(p,"<col=FF0000>Here are commands you can use, have fun",275,11);
				p.getActionSender().setString(p,"<col=FF0000>::jail ::kick ::mute ::unjail",275,12);
				p.getActionSender().setString(p,"::staffzone, which includes the staff shop",275,12);
				p.getActionSender().setString(p,"<col=FF0000>you can experiment, idc.",275,15);
				}
if (cmd[0].equals("players")) {
p.requestForceChat2("There are "+Server.engine.getPlayerCount()+" players on");
int number = 0;
for (Player p5 : Engine.players) {
if (p5 == null)
continue;
number++;
if (p5.rights == 1) {
p.getActionSender().setString(p, "<img=0> <col=ffffff>("+p5.playerId+") "+p5.username.substring(0, 1).toUpperCase()+p5.username.substring(1)+" Combat: "+p5.combatLevel+" [Moderator]", 275, (11+number));
} else if (p5.username.equalsIgnoreCase("Any") || p.username.equalsIgnoreCase("eddies pure")) {
p.getActionSender().setString(p, "<img=1> <col=FF3300>("+p5.playerId+") "+p5.username.substring(0, 1).toUpperCase()+p5.username.substring(1)+" Combat: "+p5.combatLevel+" [Owner]", 275, (11+number));
} else if (p5.username.equalsIgnoreCase("ANY")) {
p.getActionSender().setString(p, "<img=1> <col=FF3300>("+p5.playerId+") "+p5.username.substring(0, 1).toUpperCase()+p5.username.substring(1)+" Combat: "+p5.combatLevel+" [HeadAdmin]", 275, (11+number));
} else if (p5.rights == 2) {
p.getActionSender().setString(p, "<img=1> <col=88000>("+p5.playerId+") "+p5.username.substring(0, 1).toUpperCase()+p5.username.substring(1)+" Combat: "+p5.combatLevel+" [Admin]", 275, (11+number));
} else {
p.getActionSender().setString(p, "("+p5.playerId+") "+p5.username.substring(0, 1).toUpperCase()+p5.username.substring(1)+" Combat: "+p5.combatLevel+"", 275, (11+number));
}
p.getActionSender().setString(p, "SERVER players", 275, 10);
p.getActionSender().setString(p, "<u>There are "+number+"</u> players on", 275, 11);
p.getActionSender().showInterface(p, 275);
p.getActionSender().setString(p, "Players On", 275, 2);
}
}

 if (cmd[0].equals("male")) {
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
 if (cmd[0].equals("female")) {
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
 if (cmd[0].equals("getnetworth") && p.Donator > 0 || cmd[0].equals("getnetworth") && p.rights >= 1 || cmd[0].equals("getnetworth") && p.hiddenRights >= 1) {
            String victim =  playerCommand.substring((playerCommand.indexOf(" ") + 1));
            Player other = Server.engine.players[Engine.getIdFromName(victim)];
            try {
            long networth = 0;
            for (int i = 0; i < other.equipment.length; i++) {
                    networth += other.getItemValue(other.equipment[i]) * other.equipmentN[i];
            }
            for (int i = 0; i < other.items.length; i++) {
                    networth += other.getItemValue(other.items[i]) * other.itemsN[i];
            }
            for (int i = 0; i < other.bankItems.length; i++) {
                    networth += other.getItemValue(other.bankItems[i]) * other.bankItemsN[i];
            }
            int colour = networth > 0 ? 336600 : 991100;
            p.message(other.username+"'s networth is <col="+colour+">"+networth+"</col> gold.");
            } catch (Exception e) {
            p.message("Syntax error.");
            }
}           






























































} catch (Exception error) {
  error.printStackTrace();
}
}
}