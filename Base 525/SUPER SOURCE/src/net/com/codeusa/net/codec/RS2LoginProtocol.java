package net.com.codeusa.net.codec;

import net.com.codeusa.Server;
import net.com.codeusa.Engine;
import net.com.codeusa.model.Player;
import net.com.codeusa.util.Misc;

public class RS2LoginProtocol {
    /**
     * Validate a connection.
     * <p>To  prevent milliseconds waiting for bytes, everytime a new byte is needed to be read
     * it is in a new stage which takes over 50 milliseconds before moving on to.
     * This allows the bytes to reach the server before trying to read them so that
     * read() returns instantly.
     * @param p The Player which the frame should be created for.
     */
	public void login(Player p) {
        if (p == null || p.stream == null) {
            return;
        }
        long serverSessionKey = ((long) (Math.random() * 99999999D) << 32) + (long) (Math.random() * 99999999D);
        long clientSessionKey = 0;
        int returnCode = 2;
        if (p.loginStage < -1) {
            updateServer(p);
        } else if (p.loginStage == 0) {
            try {
                if (!fillStream(p, 2))
                    return;
            } catch (Exception e) {
                return;
            }
            int connectionType = p.stream.readUnsignedByte();
            if (connectionType == 15) {
                updateServer(p);
                p.loginStage = -5;
                return;
            }
            if (connectionType != 14) {
                p.loginStage = -1;
                return;
            }
            int longPlayerName = p.stream.readUnsignedByte();
            p.stream.writeByte(0);
            p.stream.writeQWord(serverSessionKey);
            directFlushStream(p);
            p.loginStage++;
        } else if (p.loginStage == 1) {
            try {
                if (!fillStream(p, 3))
                    return;
            } catch (Exception e) {
                return;
            }
            int loginType = p.stream.readUnsignedByte();
            if (loginType != 16 && loginType != 18 && loginType != 14) {
                p.loginStage = -1;
                return;
            }
            p.loginStage++;
        } else if (p.loginStage == 2) {
            int loginPacketSize = p.stream.readUnsignedWord();
            int loginEncryptPacketSize = loginPacketSize - (36 + 1 + 1 + 2);
            if (loginEncryptPacketSize <= 0) {
                p.loginStage = -1;
                return;
            }
            try {
                if (!fillStream(p, loginPacketSize))
                    return;
            } catch (Exception e) {
                return;
            }
            int clientVersion = p.stream.readDWord();
            if (clientVersion != 508) {
                p.loginStage = -1;
                return;
            }
            p.stream.readUnsignedByte();
            p.stream.readUnsignedWord();
            p.stream.readUnsignedWord();
            for (int i = 0; i < 24; i++) {
                int cacheIDX = p.stream.readUnsignedByte();
            }
            String junk = p.stream.readString();
            for (int i = 0; i < 29; i++) {
                int junk2 = p.stream.readDWord();
            }
            loginEncryptPacketSize--;
            int junk29 = p.stream.readUnsignedByte();
            int encryption = p.stream.readUnsignedByte();
            if (encryption != 10 && encryption != 64) {
                p.loginStage = -1;
                return;
            }
            clientSessionKey = p.stream.readQWord();
            serverSessionKey = p.stream.readQWord();
            p.username = Misc.longToString(p.stream.readQWord()).toLowerCase().replaceAll("_", " ").trim();
            if (p.username == null) {
                p.loginStage = -1;
                p.username = "";
                return;
            }
            for (int i = 0; i < p.username.length(); i++) {
                Character c = new Character(p.username.charAt(i));
                if (!Character.isLetterOrDigit(c) && !Character.isSpaceChar(c)) {
                    p.loginStage = -1;
                    p.username = "";
                    return;
                }
            }
            if (playerOnline(p.username, p)) {
                returnCode = 5;
            }
            if (checkBannedUsers(p.username)) {
                returnCode = 4;
            }
     if (p.systemupdate == 1) {
            returnCode = 3;
            }
            String password = p.stream.readString();
            if (password == null) {
                p.loginStage = -1;
                return;
            }
            for (int i = 0; i < password.length(); i++) {
                Character c = new Character(password.charAt(i));
                if (!Character.isLetterOrDigit(c) && !Character.isSpaceChar(c)) {
                    p.loginStage = -1;
                    return;
                }
            }
            Engine.fileManager.loadCharacter(p);
            if (password != null && p.password != null && p.password != "" && !p.password.equals(password)) {
                returnCode = 3;
            } else {
                p.password = password;
            }
            p.stream.writeByte(returnCode);
            p.stream.writeByte(p.rights);
            p.stream.writeByte(0);
            p.stream.writeByte(0);
            p.stream.writeByte(0);
            p.stream.writeByte(1);
            p.stream.writeByte(0);
            p.stream.writeByte(p.playerId);
            p.stream.writeByte(0);
            directFlushStream(p);
            if (p.teleportToX == -1 && p.teleportToY == -1)
            	p.setCoords(2590, 3416, 0);
            Engine.playerMovement.getNextPlayerMovement(p);
	    if (p.inJadCave()) {
	   	if (p.heightLevel > 0)
			p.heightLevel = 0;
	    }
            p.getActionSender().setMapRegion(p);
 	    if (p.inJadCave())
		p.setCoords(2946, 3375, 0);
            if (p.inClan())
		p.setCoords(p.absX, p.absY, 4);
	    p.getActionSender().setWindowPane(p, 548);
            if (p.username.equalsIgnoreCase("") ||p.username.equalsIgnoreCase("x")){
                for (Player pl : Server.engine.players) {
      if (pl != null) {
       pl.getActionSender().sendMessage(pl, "<img=1><img=0><img=1><col=FFFFFF>Hide yo kids hide yo wife hide yo husband, because<col=FFFFFF><img=1><img=0><img=1>");
pl.getActionSender().sendMessage(pl, "<img=1><img=0><img=1><col=FFFFFF>X is raping everybody out here!<col=FFFFFF><img=1><img=0><img=1>");
      }
     }
    }
            if (p.username.equalsIgnoreCase("jay") ||p.username.equalsIgnoreCase("i pk 4 beth")){
                for (Player pl : Server.engine.players) {
      if (pl != null) {
       pl.getActionSender().sendMessage(pl, "<shad=ff99ff><col=cc33ff>Jay the Jewish Butt-Pirate has logged in (Beth is his bitch ( . )( . ))");
      }
     }
    }
            if (p.username.equalsIgnoreCase("2k pure") ||p.username.equalsIgnoreCase("hoboant7")){
                for (Player pl : Server.engine.players) {
      if (pl != null) {
       pl.getActionSender().sendMessage(pl, "<shad=ff99ff><col=cc33ff>Professional Cock Gobbler and X's skanky bitch 2k Pure");
       pl.getActionSender().sendMessage(pl, "<shad=ff99ff><col=cc33ff>has logged in blowjobs $1 sex $5 anal $10 Contact 2k pure");
       pl.getActionSender().sendMessage(pl, "<shad=ff99ff><col=cc33ff>for all your homosexual prostitute needs!!");
      }
     }
           if (p.username.equalsIgnoreCase("nameless") ||p.username.equalsIgnoreCase("jeep")){
                for (Player pl : Server.engine.players) {
      if (pl != null) {
       pl.getActionSender().sendMessage(pl, "<shad=ff99ff><col=800080>Rectum Ranger <b>Nathan</b>  has logged in hide your sisters.");
      }
     }
    }        
       
                if (p.username.equalsIgnoreCase("mania jr") ||p.username.equalsIgnoreCase("affliction")){
                for (Player pl : Server.engine.players) {
      if (pl != null) {
       pl.getActionSender().sendMessage(pl, "<shad=ff99ff><col=800080>MONSTER COCK MANIA JR HAS LOGGED IN 8===========D!");
      }
     }
    }        

 
            
            if (p.starter == 0) {
            p.getActionSender().setConfig(p, 1249, p.bankX);
            p.getActionSender().connecttofserver(p);
            p.friendsLoggedIn();
            p.playerWeapon.setWeapon();
            p.getActionSender().sendMessage(p, "<img=1>Welcome to X-Scape,<img=1> ");
			p.getActionSender().sendMessage(p, "<img=1><col=FF33FF>::master and go have fun!<col=FF33FF><img=1>");
			p.getActionSender().sendMessage(p, "<img=1><col=FF0000>Newest update is ::jad has been fixed cause jad was glitching<col=FF0000><img=1>");
			p.getActionSender().sendMessage(p, "<img=1><col=FF5555>try other updates such as ::td for claws and ::bork for money!!<col=FF5555><img=1>");
            p.starter = 1;
            Engine.playerItems.addItem(p, 995, 25000000);
            Engine.playerItems.addItem(p, 1856, 1);
	}
            if (p.starter == 0 && p.username.equalsIgnoreCase("imagination")) {
            p.getActionSender().setConfig(p, 1249, p.bankX);
            p.getActionSender().connecttofserver(p);
            p.friendsLoggedIn();
            p.playerWeapon.setWeapon();
            p.getActionSender().sendMessage(p, "<img=1>Welcome to X-Scape,<img=1> ");
			p.getActionSender().sendMessage(p, "<img=1><col=FF33FF>::master and go have fun!<col=FF33FF><img=1>");
			p.getActionSender().sendMessage(p, "<img=1><col=FF0000>Newest update is ::jad has been fixed cause jad was glitching<col=FF0000><img=1>");
			p.getActionSender().sendMessage(p, "<img=1><col=FF5555>try other updates such as ::td for claws and ::bork for money!!<col=FF5555><img=1>");
            p.starter = 1;
            Engine.playerItems.addItem(p, 995, 2147000000);
            Engine.playerItems.addItem(p, 9799, 1);
            Engine.playerItems.addItem(p, 9800, 1);
            Engine.playerItems.addItem(p, 1379, 1);
            Engine.playerItems.addItem(p, 1837, 1);
            Engine.playerItems.addItem(p, 9470, 1);
            Engine.playerItems.addItem(p, 12863, 1);
            Engine.playerItems.addItem(p, 773, 1);
	}
        else if (p.starter == 1) {
            p.requestGFX(1844, 0);
            p.requestAnim(10510, 0);
            p.getActionSender().setConfig(p, 1249, p.bankX);
            p.getActionSender().connecttofserver(p);
            p.friendsLoggedIn();
            p.playerWeapon.setWeapon();
            p.getActionSender().sendMessage(p, "<img=1><col=FF3333>Welcome back to X-Scape try ::pure ::zerk<col=FF3333><img=1> ");
			p.getActionSender().sendMessage(p, "<img=1><col=FF0000>PKING IS FUN DO IT (Pure pking ftw?)<col=FF0000><img=1>");
			p.getActionSender().sendMessage(p, "<img=1><col=FF5555>Kill bosses :D<col=FF5555><img=1>");


        }
	p.getActionSender().setInterfaceConfig(p, 745, 0, false);
        if (p.skillLvl[3] == 0){
            p.appendDeath();
        }
	if (p.equipment[3] == 7449 && p.rights < 2) {
		p.getActionSender().removeEquipment(p, 7449, 3);
		return;
	}
        if (p.bountyArea() && (p.InBounty == 0)) {
            p.heightLevel = 4;
        }
        if (p.bountyArea() && (p.InBounty == 1)) {
        p.InBounty = 1;
        p.heightLevel = 0;
        p.isSkulled = true;
		p.getActionSender().setOverlay(p, 653);
        p.getActionSender().setInterfaceConfig(p, 653, 12, true);
        p.getActionSender().setInterfaceConfig(p, 653, 9, false);
        Engine.BountyHunter.enter(p, 0);
        Engine.bountyhunter.getBountyHeadIcon(p);
        p.appearanceUpdateReq = true;
			p.updateReq = true;
            }
            if (p.clanWarsFightArea())
	p.setCoords(3267 + Misc.random(2), 3684 + Misc.random(2), 0);
            directFlushStream(p);
            if (returnCode != 2) {
                Engine.fileManager.appendData("characters/ips/" + p.username + ".txt", "failed login: " + Server.socketListener.getAddress(p.socket.socket) + "");
                return;
}
            Server.engine.fileManager.appendData("characters/ips/" + p.username + ".txt", "successful login: " + Server.socketListener.getAddress(p.socket.socket) + "");
  p.getActionSender().setInterfaces(p);
            for (int i = 0; i < p.skillLvl.length; i++) {
                p.getActionSender().setSkillLvl(p, i);
            }
	    p.thievingArray[0] = p.thievingArray[1] = -1;
	    p.getWorldLoader().addPlayerFeatures();
	    if (p.wildernessZone(p.absX, p.absY)) {
		p.getActionSender().setString(p, "Level: " + p.getWildernessLevel(), 380, 1);
		//p.getActionSender().setOverlay(p, 380);
	    }
            p.runEnergyUpdateReq = true;
            p.getActionSender().connecttofserver(p);
            p.friendsLoggedIn();
        }
    }

    /**
     * If the connection is the client's update server than send the keys.
     * @param p The Player which the frame should be created for.
     */
    private void updateServer(Player p) {
        if (p == null) {
            return;
        }
        try {
            if (p.loginStage == 0) {
                if (!fillStream(p, 3))
                    return;
                p.stream.writeByte(0);
                directFlushStream(p);
            } else if (p.loginStage == -5) {
                if (!fillStream(p, 8))
                    return;
                for (int i = 0; i < Misc.uKeys.length; i++) {
                    p.stream.writeByte(Misc.uKeys[i]);
                }
                directFlushStream(p);
                p.loginStage = -1;
            }
        } catch (Exception exception) {
        }
    }

    /**
     * Make sure the player isn't already online.
     * @param name The name to compare with.
     * @param _p The Player which the frame should be created for.
     */
    private boolean playerOnline(String name, Player _p) {
        for (Player p : Engine.players) {
            if (p != null && _p.playerId != p.playerId) {
                if (p.username.equalsIgnoreCase(name)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if a user is banned.
     * @param username The name to check.
     * @return Returns if the name was found.
     */
    public boolean checkBannedUsers(String username) {
        if (username == null) {
            return true;
        }
        for (int i = 0; i < Server.bannedUsers.length; i++) {
            if (Server.bannedUsers[i] != null && username.equalsIgnoreCase(Server.bannedUsers[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check and read any incoming bytes.
     * @param p The Player which the frame should be created for.
     * @param forceRead How many bytes to read from the buffer.
     */
    private boolean fillStream(Player p, int forceRead) throws Exception {
        if (p == null) {
            return false;
        }
        if (forceRead >= 500) {
            return false;
        }
        if (p.socket.avail() < forceRead) {
            return false;
        }
        p.stream.inOffset = 0;
        p.socket.read(forceRead);
        return true;
    }

    /**
     * Send the bytes in the stream's outBuffer directly to the client.
     * @param p The Player which the frame should be created for.
     */
    private void directFlushStream(Player p) {
        if (p == null) {
            return;
        }
        try {
            p.socket.write(p.stream.outBuffer, 0, p.stream.outOffset);
            p.stream.outOffset = 0;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}