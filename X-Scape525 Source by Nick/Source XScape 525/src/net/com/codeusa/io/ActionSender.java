package net.com.codeusa.io;

import net.com.codeusa.model.items.*;
import net.com.codeusa.model.Player;
import net.com.codeusa.*;
import net.com.codeusa.model.Player;
import net.com.codeusa.Engine;
import net.com.codeusa.util.ByteVector;
import net.com.codeusa.util.Misc;
import java.util.Date;
import java.text.DateFormat;
import net.com.codeusa.world.mapdata.MapData;
import java.util.Locale;
import net.com.codeusa.util.Misc;
import net.com.codeusa.model.items.*;
import net.com.codeusa.model.Player;
import net.com.codeusa.*;
import net.com.codeusa.util.Misc;
import net.com.codeusa.util.ByteVector;
import java.io.*;
import net.com.codeusa.model.Player;
import net.com.codeusa.util.ByteVector;
import net.com.codeusa.util.Misc;

/**
 *
 * @author codeusa - updated by d3v0n
 */

public class ActionSender {

    private ByteVector stream = new ByteVector(10000, 10000);
public void systemUpdate(Player p, int time) {
	    p.stream.createFrame(8);
	    p.stream.writeWordBigEndianA(time);
	}

	public void dialogue(Player p, int npc, int animation, String name, int lines, String one, String two, String three, String four) {

		int interfaceNumber = (lines + 240);

		showChatboxInterface(p, interfaceNumber);
		animateInterfaceId(p, animation, interfaceNumber, 2);
		setNPCId(p, npc, interfaceNumber, 2);
		setString(p, name, interfaceNumber, 3);

		if (lines >= 1) {
			setString(p, one, interfaceNumber, 4);
			if (lines >= 2) {
				setString(p, two, interfaceNumber, 5);
				if (lines >= 3) {
					setString(p, three, interfaceNumber, 6);
					if (lines >= 4) {
						setString(p, four, interfaceNumber, 7);
					}
				}
			}
		}
	}

	public void shake(Player p, int amount) {
		p.getByteVector().createFrame(12);
		p.getByteVector().writeByte(amount);
		p.getByteVector().writeByte(amount);
		p.getByteVector().writeByte(amount);
		p.getByteVector().writeByte(amount);
		p.getByteVector().writeWord(amount);
	}
public void setPlayerHead(Player p, int interfaceId, int childId) {
		if(p == null || p.stream == null || p.disconnected[0]) {
			return;
		}
        	p.stream.createFrame(101);
        	p.stream.writeWord(interfaceId);
        	p.stream.writeWord(childId);
        }
	public void setNPCId(Player p, int npcId, int interfaceId, int childId) {
		if (p == null || p.stream == null || p.disconnected[0]) {
			return;
		}
		p.getByteVector().createFrame(6);
		p.getByteVector().writeWordBigEndian(interfaceId);
		p.getByteVector().writeWordBigEndian(childId);
		p.getByteVector().writeWordBigEndian(npcId);
	}

	public void animateInterfaceId(Player p, int emoteId, int interfaceId, int childId) {
		if (p == null || p.stream == null || p.disconnected[0]) {
			return;
		}
		p.getByteVector().createFrame(245);
		p.getByteVector().writeWordBigEndian(interfaceId);
		p.getByteVector().writeWordBigEndian(childId);
		p.getByteVector().writeWord(emoteId);
	}

	public void addSoundEffect(Player player, int soundId, int bytes, int delay, int distance) {
		player.getByteVector().createFrame(119);
		player.getByteVector().writeWord(soundId);
		player.getByteVector().writeByte(bytes);
		player.getByteVector().writeWord(delay);
		if (distance == 0) {
			return;
		}
		/**
		 * Commented out due to error
		 *
		for (Player player2 : Server.engine.players) {
			if (player2 == null || player == null) {
				continue;
			}
			if (Misc.getDistance(player.absX, player.absY, player2.absX, player2.absY) > distance) {
				return;
			}
			player2.getActionSender().addSoundEffect(player2, soundId, bytes, delay, 0);
		}
		 *
		 */
	}

    public void addObject(Player p, int objectId, int height, int objectX, int objectY, int face, int type) {
	sendCoords(p, (objectX - ((p.mapRegionX - 6) * 8)), (objectY - ((p.mapRegionY - 6) * 8)));
	int ot = ((type << 2) + (face & 3));
	p.getByteVector().createFrame(30);
	p.getByteVector().writeWordBigEndian(objectId);
	p.getByteVector().writeByteA(0);
	p.getByteVector().writeByteC(ot);
    }

    public void connecttofserver(Player p) {
        if(p == null || p.getByteVector() == null || p.disconnected[0]){
            return;
        }
        p.getByteVector().createFrame(115);
        p.getByteVector().writeByte(2);
    }

    public void sendSentPrivateMessage(Player p, long name, String message) {
        byte[] bytes = new byte[message.length()];
	Misc.encryptPlayerChat(bytes, 0, 0, message.length(), message.getBytes());
	p.getByteVector().createFrameVarSize(89);
	p.getByteVector().writeQWord(name);
	p.getByteVector().writeByte(message.length());
	p.getByteVector().writeBytes(bytes, bytes.length, 0);
	p.getByteVector().endFrameVarSize();
    }

    private static int messageCounter = 1;
    public void sendReceivedPrivateMessage(Player p, long name, int rights, String message) {
        int id = messageCounter++;
	if(id > 16000000) {
            id = 1;
	}
	byte[] bytes = new byte[message.length()+1];
	bytes[0] = (byte) message.length();
	Misc.encryptPlayerChat(bytes, 0, 1, message.length(), message.getBytes());
	p.getByteVector().createFrameVarSize(178);
	p.getByteVector().writeQWord(name);
	p.getByteVector().writeWord(1);
	p.getByteVector().writeByte(((id << 16) & 0xFF));
        p.getByteVector().writeByte(((id << 8 ) & 0xFF));
        p.getByteVector().writeByte(((id      ) & 0xFF));
	p.getByteVector().writeByte(rights);
	p.getByteVector().writeBytes(bytes, bytes.length, 0);
	p.getByteVector().endFrameVarSize();
    }

    public void setHintIcon(Player p, int targetType, int targetId, int arrowId, int playerModel) {
        if (p == null || p.stream == null || p.disconnected[0]) {
            return;
        }
        p.stream.createFrame(227);
        int offset = p.stream.outOffset;
        p.stream.writeByte(targetType);
        p.stream.writeByte(arrowId);
        if (targetType == 1 || targetType == 10) {
            p.stream.writeWord(targetId);
            p.stream.outOffset += 3;
        }
        p.stream.writeWord(playerModel);
        for (int i = (p.stream.outOffset - offset); i < 9; i++) {
            p.stream.writeByte(0);
        }
    }


    public void sendFriend(Player p, long name, int world) {
        p.getByteVector().createFrameVarSize(154);
	p.getByteVector().writeQWord(name);
	p.getByteVector().writeWord(world);
	p.getByteVector().writeByte(1);
	if(world != 0) {
            if(world == 66) {
                p.getByteVector().writeString("Online");
	    } else {
                p.getByteVector().writeString("IaliIscape " + world);
	    }
	}
	p.getByteVector().endFrameVarSize();
    }

    public void sendIgnores(Player p, long[] ignores) {
        p.getByteVector().createFrameVarSizeWord(240);
	for(long ignore : ignores) {
            p.getByteVector().writeQWord(ignore);
	}
	p.getByteVector().endFrameVarSizeWord();
    }

    public void addStaticObject(int objectId, int height, int objectX, int objectY, int face, int type) {
	for (Player p : Server.engine.players) {
		if (p == null)
			continue;
		if (p.heightLevel == height)
			addObject(p, objectId, height, objectX, objectY, 0, type);
	}
    }

    public void addLists(Player p) {
    	if(p == null || p.stream == null || p.disconnected[0])
    		return;
    	p.getByteVector().createFrame(115);
    	p.getByteVector().writeByte(2);
    }

    public void addInterfaceItem(Player p, int interfaceid, int child, int itemsize, int itemid) {
	if (p == null || p.stream == null)
		return;
	int inter = ((interfaceid * 65536) + child);
	p.getByteVector().createFrame(35);
	p.getByteVector().writeDWord_v2(inter);
	p.getByteVector().writeDWordBigEndian(itemsize);
	p.getByteVector().writeWordBigEndianA(itemid);
    }

    public void createStaticGraphic(Player p, int graphicId, int absX, int absY) {
	if(p == null || p.stream == null || p.disconnected[0])
		return;
	sendCoords(p, (absX - ((p.mapRegionX - 6) * 8)), (absY - ((p.mapRegionY - 6) * 8)));
	p.getByteVector().createFrame(248);
	p.getByteVector().writeByte(0);
	p.getByteVector().writeWord(graphicId);
	p.getByteVector().writeByte(0);
	p.getByteVector().writeWord(0);
    }

    /**
     * Set either fullscreen or normal.
     * @param p The Player which the frame should be created for.
     * @param set The frame set, 548 for the default setup.
     */
    public void setWindowPane(Player p, int set) {
        if (p == null || p.stream == null || p.disconnected[0]) {
            return;
        }
        p.getByteVector().createFrame(239);
        p.getByteVector().writeWord(set);
        p.getByteVector().writeByteA(0);
    }

    /**
     * Logs a player out.
     * @param p The Player which the frame should be created for.
     */
    public void logout(Player p) {
        if (p == null || p.stream == null || p.disconnected[0]) {
            return;
        }
        p.getByteVector().createFrame(104);
    }
/**
     * Sets item options allowed
     * @param p The Player which the frame should be created for.
     * @param set The access mask
     * @param window The window or child interface id
     * @param inter The main interface id
     * @param off The item offset to start with
     * @param len The item count to set
     */
    public void setAccessMask(Player p, int set, int window, int inter, int off, int len) {
        if (p == null || p.stream == null || p.disconnected[0]) {
            return;
        }
        p.stream.createFrame(223);
        p.stream.writeWord(len);
        p.stream.writeWordBigEndianA(off);
        p.stream.writeWordBigEndian(window);
        p.stream.writeWordBigEndian(inter);
        p.stream.writeWordBigEndian(set);
        p.stream.writeWordBigEndian(0);
    }

    /**
     * Runs an pScript2 script
     * @param p The Player which the frame should be created for.
     * @param id The script id
     * @param o The script arguments
     * @param valstring The argument types
     */
    public void runScript(Player p, int id, Object[] o, String valstring) {
        if (valstring.length() != o.length) {
            throw new IllegalArgumentException("Argument array size mismatch");
        }
        p.stream.createFrameVarSizeWord(152);
        p.stream.writeString(valstring);
        int j = 0;
        for (int i = (valstring.length() - 1); i >= 0; i--) {
            if (valstring.charAt(i) == 115) {
                p.stream.writeString((String) o[j]);
            } else {
                p.stream.writeDWord((Integer) o[j]);
            }
            j++;
        }
        p.stream.writeDWord(id);
        p.stream.endFrameVarSize();
    }
    /**
     * Display an interface.
     * <p>The various ids determines how the interface is displayed, from an overlay, to covering the chatbox, etc.
     * @param p The Player which the frame should be created for.
     * @param showId Sets the interface such as an overlay, etc.
     * @param windowId What type of window you used, default should be 548.
     * @param interfaceId Where to display it on the screen.
     * @param childId The interface id to display.
     */
    public void setInterface(Player p, int showId, int windowId, int interfaceId, int childId) {
        if (p == null || p.stream == null || p.disconnected[0]) {
            return;
        }
        p.getByteVector().createFrame(93);
        p.getByteVector().writeWord(childId);
        p.getByteVector().writeByteA(showId);
        p.getByteVector().writeWord(windowId);
        p.getByteVector().writeWord(interfaceId);
    }

    /**
     * Set a players click option.
     * <p>The slot cannot be below 0 and cannot be above 8.
     * @param p The Player which the frame should be created for.
     * @param option The string to set the option to.
     * @param slot The position to set the option on the player.
     * @param top Should the option appear first on the list without changing slot
     */
    public void setPlayerOption(Player p, String option, int slot, boolean top) {
        if (p == null || p.stream == null || p.disconnected[0]) {
            return;
        }
	p.getByteVector().createFrameVarSize(252);
	p.getByteVector().writeByteC(top ? 1 : 0);
	p.getByteVector().writeString(option);
	p.getByteVector().writeByteC(slot);
        p.getByteVector().endFrameVarSize();
    }

    /**
     * Setting p configs.
     * <p>This is used for setting prayers, running, etc.
     * @param p The Player which the frame should be created for.
     * @param id The config id to set.
     * @param set What to set the config.
     */
    public void setConfig(Player p, int id, int set) {
        if (p == null || p.stream == null || p.disconnected[0]) {
            return;
        }
	p.getByteVector().createFrame(100);
        p.getByteVector().writeWordA(id);
        p.getByteVector().writeByteA(set);
    }

    public void setConfig2(Player p, int id, int set) {
        if (p == null || p.stream == null || p.disconnected[0]) {
            return;
        }
        p.getByteVector().createFrame(161);
        p.getByteVector().writeWord(id);
        p.getByteVector().writeDWord_v1(set);
    }

    public void setBankOptions(Player p) {
        if (p == null || p.stream == null || p.disconnected[0]) {
            return;
        }
        p.getByteVector().createFrame(223);
        p.getByteVector().writeWord(496);
        p.getByteVector().writeWordBigEndianA(0);
        p.getByteVector().writeWordBigEndian(73);
        p.getByteVector().writeWordBigEndian(762);
        p.getByteVector().writeWordBigEndian(1278);
        p.getByteVector().writeWordBigEndian(20);
        p.getByteVector().createFrame(223);
        p.getByteVector().writeWord(27);
        p.getByteVector().writeWordBigEndianA(0);
        p.getByteVector().writeWordBigEndian(0);
        p.getByteVector().writeWordBigEndian(763);
        p.getByteVector().writeWordBigEndian(1150);
        p.getByteVector().writeWordBigEndian(18);
    }

 /**
     * Creates a projectile. Can be used for magic, range etc.
     * @param p The Player which the frame should be created for.
     * @param offsetY The distance between caster and enemy Y
     * @param offsetX The distance between caster and enemy X
     * @param angle The starting place of the projectile
     * @param speed The speed minus the distance making it set.
     * @param gfxMoving The moving graphic ID
     * @param startHeight The starting height
     * @param endHeight The destination height
     * @param lockon The NPC the missile is locked onto.
     * @param npc if this is an npc and not a player set to true
     * Made by Lumby http://**************
     * */
	public void addProjectile(Player p, int casterY, int casterX, int offsetY, int offsetX, int angle, int speed, int gfxMoving, int startHeight, int endHeight, int lockon, boolean npc) {
		if (p == null || p.stream == null) {
			return;
		}
		for (Player pl : Server.engine.players) {
			if (pl != null) {
				if (pl.heightLevel == p.heightLevel) {
					sendCoords(pl, (casterX - ((pl.mapRegionX - 6) * 8)) - 3, (casterY - ((pl.mapRegionY - 6) * 8)) - 2);
					pl.getByteVector().createFrame(112);
					pl.getByteVector().writeByte(angle);
					pl.getByteVector().writeByte(offsetX);
					pl.getByteVector().writeByte(offsetY);
					pl.getByteVector().writeWordBigEndian(lockon);
					pl.getByteVector().writeWord(gfxMoving);
					pl.getByteVector().writeByte(startHeight);
					pl.getByteVector().writeByte(endHeight);
					pl.getByteVector().writeWord(51);
					pl.getByteVector().writeWord(speed);
					pl.getByteVector().writeByte(16);
					pl.getByteVector().writeByte(64);
				}
			}
		}
	}

	public void slopedProjectile(Player p, int casterY, int casterX, int offsetY, int offsetX, int angle, int speed, int gfxMoving, int startHeight, int endHeight, int lockon, int slope) {
		if (p == null || p.stream == null) {
			return;
		}
		for (Player pl : Server.engine.players) {
			if (pl != null) {
				if (pl.heightLevel == p.heightLevel) {
					sendCoords(pl, (casterX - ((pl.mapRegionX - 6) * 8)) - 3, (casterY - ((pl.mapRegionY - 6) * 8)) - 2);
					pl.getByteVector().createFrame(112);
					pl.getByteVector().writeByte(angle);
					pl.getByteVector().writeByte(offsetX);
					pl.getByteVector().writeByte(offsetY);
					pl.getByteVector().writeWord(lockon);
					pl.getByteVector().writeWord(gfxMoving);
					pl.getByteVector().writeByte(startHeight);
					pl.getByteVector().writeByte(endHeight);
					pl.getByteVector().writeWord(51);
					pl.getByteVector().writeWord(speed);
					pl.getByteVector().writeByte(slope);
					pl.getByteVector().writeByte(64);
				}
			}
		}
	}

	public void timedSlopedProjectile(Player p, int casterY, int casterX, int offsetY, int offsetX, int angle, int speed, int gfxMoving, int startHeight, int endHeight, int lockon, int slope, int time) {
		if (p == null || p.stream == null) {
			return;
		}
		for (Player pl : Server.engine.players) {
			if (pl != null) {
				if (pl.heightLevel == p.heightLevel) {
					sendCoords(pl, (casterX - ((pl.mapRegionX - 6) * 8)) - 3, (casterY - ((pl.mapRegionY - 6) * 8)) - 2);
					pl.getByteVector().createFrame(112);
					pl.getByteVector().writeByte(angle);
					pl.getByteVector().writeByte(offsetX);
					pl.getByteVector().writeByte(offsetY);
					pl.getByteVector().writeWord(lockon);
					pl.getByteVector().writeWord(gfxMoving);
					pl.getByteVector().writeByte(startHeight);
					pl.getByteVector().writeByte(endHeight);
					pl.getByteVector().writeWord(time);
					pl.getByteVector().writeWord(speed);
					pl.getByteVector().writeByte(slope);
					pl.getByteVector().writeByte(64);
				}
			}
		}
	}

    /**
     * Set the run energy on the p.
     * @param p The Player which the frame should be created for.
     */
    public void setEnergy(Player p) {
        if (p == null || p.stream == null || p.disconnected[0]) {
            return;
        }
	p.getByteVector().createFrame(99);
 	p.getByteVector().writeByte(p.runEnergy);
    }

    /**
     * Setting a tab.
     * @param p The Player which the frame should be created for.
     * @param tabId Which tab to display the interface on.
     * @param childId The interface to display on the tab.
     */
    public void setTab(Player p, int tabId, int childId) {
        setInterface(p, 1, childId == 137 ? 752 : 548, tabId, childId);
    }

    /**
     * Set the overlay to be displayed.
     * @param p The Player which the frame should be created for.
     * @param childId The interface id to display as an overlay.
     */
    public void setOverlay(Player p, int childId) {
        setInterface(p, 1, 548, 5, childId);
    }

    /**
     * Remove any overlays that might be set.
     * @param p The Player which the frame should be created for.
     */
    public void removeOverlay(Player p) {
        setInterface(p, 1, 548, 5, 56);
    }

    /**
     * Display an interface on the main area in the screen.
     * @param p The Player which the frame should be created for.
     * @param childId the interface id to display.
     */
    public void showInterface(Player p, int childId) {
        setInterface(p, 0, 548, 8, childId);
        p.interfaceId = childId;
    }

    /**
     * Remove an interface on the main screen.
     * @param p The Player which the frame should be created for.
     */
    public void removeShownInterface(Player p) {
        setInterface(p, 1, 548, 8, 56);
        p.interfaceId = -1;
    }

    /**
     * Display an interface on the chatbox.
     * @param p The Player which the frame should be created for.
     * @param childId The interface to display on the chatbox.
     */
    public void showChatboxInterface(Player p, int childId) {
        setInterface(p, 0, 752, 12, childId);
        p.chatboxInterfaceId = childId;
    }

    /**
     * Set the chatbox back removing any interfaces on it.
     * @param p The Player which the frame should be created for.
     */
    public void removeChatboxInterface(Player p) {
        setTab(p, 68, 752);
        p.getByteVector().createFrame(246);
        p.getByteVector().writeWord(752);
        p.getByteVector().writeWord(12);
        p.chatboxInterfaceId = -1;
    }

    /**
     * Set the inventory.
     * @param p The Player which the frame should be created for.
     * @param childId The interface to display on the inventory.
     */
	public void setInventory(Player p, int childId) {
		setInterface(p, 0, 548, 69, childId);
		p.hideTabs(p);
	}
	public void restoreInventory(Player p) {
		setInterface(p, 1, 548, 69, 56);
		p.restoreTabs(p);
	}
        public void restoreTabs(Player p) {
        for (int b = 16; b <= 21; b++) {
            p.getActionSender().setInterfaceConfig(p, 548, b, false);
        }
        for (int a = 32; a <= 38; a++) {
            p.getActionSender().setInterfaceConfig(p, 548, a, false);
        }
        p.calculateEquipmentBonus();
        p.getActionSender().setInterfaceConfig(p, 548, 14, false);
        p.getActionSender().setInterfaceConfig(p, 548, 31, false);
        p.getActionSender().setInterfaceConfig(p, 548, 63, false);
        p.getActionSender().setInterfaceConfig(p, 548, 72, false);
    }
        public void resetGe(Player p, int slot) {
		p.stream.createFrame(137);
		p.stream.writeUnsignedByte(slot); // Item slot
		p.stream.writeUnsignedByte(0);
    	}

    	public void setGe(Player p, int slot, int progress, int item, int price, int amount, int currentAmount) {
		p.stream.createFrame(137);
		p.stream.writeUnsignedByte(slot); // Item slot
		p.stream.writeUnsignedByte(progress); // 5 = confirm/abort, 3 = in progress //-1 for sell progress, -3 for sell finish,
		p.stream.writeWord(item); //Item id
		p.stream.writeDWord(price); //Item Price
		p.stream.writeDWord(amount); // itemAmount
		p.stream.writeDWord(currentAmount); // amount bought/sold
		p.stream.writeDWord(price * currentAmount); // amount spent
    	}

        public void setGeOnLogin(final Player p) {

	}

        public void setGeSearch(Player p, Object[] o) {
	setConfig1(p, 1109, -1);
	setConfig1(p, 1112, 0);
	setConfig1(p, 1113, 0);
	setInterface(p, 6, 752, 6, 389);
        p.getByteVector().createFrameVarSizeWord(152);
        p.getByteVector().writeString("s");
	String valstring = "s";
        int j = 0;
        for (int i = (valstring.length() - 1); i >= 0; i--) {
            if (valstring.charAt(i) == 115) {
                p.stream.writeString((String) o[j]);
            } else {
                p.stream.writeDWord((Integer) o[j]);
            }
            j++;
        }
        p.stream.writeDWord(570);
        p.stream.endFrameVarSize();
    }

        public void setConfig1(Player p, int id, int set) {
        if (p == null || p.stream == null || p.disconnected[0]) {
            return;
        }
		p.stream.createFrame(100);
        p.stream.writeWordA(id);
        p.stream.writeByteA(set);
    }
        public void setItemSlot(Player p, int slot, int item, int amount) {
	    if(amount == 0) {
		return;
	    }
	    switch(slot) {
		case 0:
		    p.getActionSender().setItems(p, -1, -1757, 523, new int[] {item}, new int[] {amount});
		    break;
		case 1:
		    p.getActionSender().setItems(p, -1, -1758, 524, new int[] {item}, new int[] {amount});
		    break;
		case 2:
		    p.getActionSender().setItems(p, -1, -1759, 525, new int[] {item}, new int[] {amount});
		    break;
		case 3:
		    p.getActionSender().setItems(p, -1, -1760, 526, new int[] {item}, new int[] {amount});
		    break;
		case 4:
		    p.getActionSender().setItems(p, -1, -1761, 527, new int[] {item}, new int[] {amount});
		    break;
		case 5:
		    p.getActionSender().setItems(p, -1, -1762, 528, new int[] {item}, new int[] {amount});
		    break;
	    }
	}

        public void resetItemSlot(Player p, int slot) {
	    int item = -1;
	    int amount = 0;
	    switch(slot) {
		case 0:
		    p.getActionSender().setItems(p, -1, -1757, 523, new int[] {item}, new int[] {amount});
		    break;
		case 1:
		    p.getActionSender().setItems(p, -1, -1758, 524, new int[] {item}, new int[] {amount});
		    break;
		case 2:
		    p.getActionSender().setItems(p, -1, -1759, 525, new int[] {item}, new int[] {amount});
		    break;
		case 3:
		    p.getActionSender().setItems(p, -1, -1760, 526, new int[] {item}, new int[] {amount});
		    break;
		case 4:
		    p.getActionSender().setItems(p, -1, -1761, 527, new int[] {item}, new int[] {amount});
		    break;
		case 5:
		    p.getActionSender().setItems(p, -1, -1762, 528, new int[] {item}, new int[] {amount});
		    break;
	    }
	}


	public void packet137(Player p, int slot) {
		p.getByteVector().createFrame(137);
		p.getByteVector().writeUnsignedByte(slot); // Item slot
		p.getByteVector().writeUnsignedByte(10); // 5 = confirm/abort, 3 = in progress //-1 for sell progress, -3 for sell finish,
		p.getByteVector().writeWord(0); //Item id
		p.getByteVector().writeDWord(0); //Item Price
		p.getByteVector().writeDWord(0); // itemAmount
		p.getByteVector().writeDWord(0); // amount bought/sold
		p.getByteVector().writeDWord(0); // amount spent
	}
	public void packet190(Player p, int id) {
		p.getByteVector().createFrame(190);
		p.getByteVector().writeWord(id);
	}

	public void itemOnInterface(Player p, int interfaceid, int child, int itemsize, int itemid) {
		int inter = ((interfaceid*65536) + child);
		p.getByteVector().createFrame(35);
		p.getByteVector().writeDWord_v2(inter);
		p.getByteVector().writeDWordBigEndian(itemsize);
		p.getByteVector().writeWordBigEndianA(itemid);
	}

    public void hideTabs(Player p) {
        for (int b = 16; b <= 21; b++) {
            p.getActionSender().setInterfaceConfig(p, 548, b, true);
        }
        for (int a = 32; a <= 38; a++) {
            p.getActionSender().setInterfaceConfig(p, 548, a, true);
        }
        p.calculateEquipmentBonus();
        p.getActionSender().setInterfaceConfig(p, 548, 14, true);
        p.getActionSender().setInterfaceConfig(p, 548, 31, true);
        p.getActionSender().setInterfaceConfig(p, 548, 63, true);
        p.getActionSender().setInterfaceConfig(p, 548, 72, true);
    }

    /**
     * Set interface defaults at login.
     * @param p The Player which the frame should be created for.
     */
    public void setInterfaces(Player p) {
        if (p == null || p.disconnected[0]) {
            return;
        }
        setTab(p, 7, 754);
        setTab(p, 6, 745);
        setTab(p, 11, 751); // Chat options
        setTab(p, 68, 752); // Chatbox
        setTab(p, 64, 748); // HP bar
        setTab(p, 65, 749); // Prayer bar
        setTab(p, 66, 750); // Energy bar
        setTab(p, 67, 747);
        setConfig(p, 1160, -1);
        setTab(p, 8, 137); // Playername on chat
        setTab(p, 73,  92); // Attack tab
        setTab(p, 74, 320); // Skill tab
        setTab(p, 75, 274); // Quest tab
        setString(p, "", 274, 1);
	setString(p, "", 274, 2);
	setString(p, "", 274, 3);
	setString(p, "", 274, 4);
	setString(p, "", 274, 5);
	setString(p, "", 274, 6);
	setString(p, "", 274, 7);
	setString(p, "", 274, 8);
	setString(p, "", 274, 9);
	setString(p, "", 274, 10);
	setString(p, "", 274, 11);
	setString(p, "", 274, 12);
	setString(p, "<col=FF0080><img=1>Home<img=1>", 274, 13);
	setString(p, "<col=FF0080>Bounty Hunter", 274, 14);
	setString(p, "<col=FF0080>Kills and Deaths", 274, 15);
        setString(p, "", 274, 16);
        setString(p, "<col=43C6DB>fun pk", 274, 17);
        setString(p, "<col=43C6DB>Corperal Beast", 274, 18);
        setString(p, "<col=43C6DB>Thieve !", 274, 19);
        setString(p, "<col=43C6DB>Kalphite Queen!!", 274, 20);
        setString(p, "<col=43C6DB>Pvp!", 274, 21);
        setString(p, "<col=43C6DB>Desert pk, alecs zone", 274, 22);
        setString(p, "<col=43C6DB>Varrock 1v1 pk", 274, 23);
        setString(p, "<col=43C6DB>Dragons PK (23 wild)", 274, 24);
        setString(p, "", 274, 25);
        setString(p, "<col=307D7E>Toggle XP", 274, 26);
        setString(p, "<col=307D7E>Backup Account", 274, 27);
        setString(p, "<col=307D7E>Sell Inventory", 274, 28);
        setString(p, "<img=1>staffzone<img=1>", 274, 29);
        setString(p, "RETURN from pvp world", 274, 30);
        setString(p, "", 274, 31);
        setString(p, "donator zone ::info", 274, 32);
        setString(p, "", 274, 33);
        setString(p, "", 274, 34);
        setString(p, "", 274, 35);
        setString(p, "", 274, 36);
        setString(p, "", 274, 37);
        setString(p, "", 274, 38);
        setString(p, "", 274, 39);
        setString(p, "", 274, 40);
        setString(p, "", 274, 41);
        setString(p, "", 274, 42);
        setString(p, "", 274, 43);
        setString(p, "", 274, 44);
        setString(p, "", 274, 45);
        setString(p, "", 274, 46);
        setString(p, "", 274, 47);
        setString(p, "", 274, 48);
        setString(p, "", 274, 49);
        setString(p, "", 274, 50);
        setString(p, "", 274, 51);
        setString(p, "", 274, 52);
        setString(p, "", 274, 53);
        setString(p, "", 274, 34);
        setString(p, "", 274, 55);
        setString(p, "", 274, 56);
        setString(p, "", 274, 57);
        setString(p, "", 274, 58);
        setString(p, "", 274, 59);
        setString(p, "", 274, 60);
        setString(p, "", 274, 61);
        setString(p, "", 274, 62);
        setString(p, "", 274, 63);
        setString(p, "", 274, 64);
        setString(p, "", 274, 65);
        setString(p, "", 274, 66);
        setString(p, "", 274, 67);
        setString(p, "", 274, 68);
        setString(p, "", 274, 69);
        setString(p, "", 274, 70);
        setString(p, "", 274, 71);
        setString(p, "", 274, 72);
        setString(p, "", 274, 73);
        setString(p, "", 274, 74);
        setString(p, "", 274, 75);
        setString(p, "", 274, 76);
        setString(p, "", 274, 77);
        setString(p, "", 274, 78);
        setString(p, "", 274, 79);
        setString(p, "", 274, 80);
        setString(p, "", 274, 81);
        setString(p, "", 274, 82);
        setString(p, "", 274, 83);
        setString(p, "", 274, 84);
        setString(p, "", 274, 85);
        setString(p, "", 274, 86);
        setString(p, "", 274, 87);
        setString(p, "", 274, 88);
        setString(p, "", 274, 89);
        setString(p, "", 274, 90);
        setString(p, "", 274, 91);
        setString(p, "", 274, 92);
        setString(p, "", 274, 93);
        setString(p, "", 274, 94);
        setString(p, "", 274, 95);
        setString(p, "", 274, 96);
        setString(p, "", 274, 97);
        setString(p, "", 274, 98);
        setString(p, "", 274, 99);
        setString(p, "", 274, 100);
        setString(p, "", 274, 101);
        setString(p, "", 274, 102);
        setString(p, "", 274, 103);
        setString(p, "", 274, 104);
        setString(p, "", 274, 105);
        setString(p, "", 274, 106);
        setString(p, "", 274, 107);
        setString(p, "", 274, 108);
        setString(p, "", 274, 109);
        setString(p, "", 274, 110);
        setString(p, "", 274, 111);
        setString(p, "", 274, 112);
        setString(p, "", 274, 113);
        setString(p, "", 274, 114);
        setString(p, "", 274, 115);
        setString(p, "", 274, 116);
        setString(p, "", 274, 117);
        setString(p, "", 274, 118);
        setString(p, "", 274, 119);
        setString(p, "", 274, 120);
        setString(p, "", 274, 121);
        setString(p, "", 274, 122);
        setString(p, "", 274, 123);
        setString(p, "", 274, 124);
        setString(p, "", 274, 125);
        setString(p, "", 274, 126);
        setString(p, "", 274, 127);
        setString(p, "", 274, 128);
        setString(p, "", 274, 129);
        setString(p, "", 274, 130);
        setString(p, "", 274, 131);
        setString(p, "", 274, 132);
        setString(p, "", 274, 133);
        setString(p, "", 274, 134);
        setString(p, "", 274, 135);
        setString(p, "", 274, 136);
        setString(p, "", 274, 137);
        setString(p, "", 274, 138);
        setString(p, "", 274, 139);
        setString(p, "", 274, 140);
        setString(p, "", 274, 141);
        setString(p, "", 274, 142);
        setString(p, "", 274, 143);
        setString(p, "", 274, 144);
        setString(p, "", 274, 145);
        setString(p, "", 274, 146);
        setString(p, "", 274, 147);
        setString(p, "", 274, 148);
        setString(p, "", 274, 149);
        setString(p, "", 274, 150);
        setString(p, "", 274, 151);
        setString(p, "", 274, 152);
        setString(p, "", 274, 153);
        setString(p, "", 274, 154);
        setString(p, "", 274, 155);
        setString(p, "", 274, 156);
        setString(p, "", 274, 157);
        setString(p, "", 274, 158);
        setString(p, "", 274, 159);
        setString(p, "", 274, 160);
        setString(p, "", 274, 161);
        setString(p, "", 274, 162);
        setString(p, "", 274, 163);
        setString(p, "", 274, 164);
        setString(p, "", 274, 165);
        setString(p, "", 274, 163);
        setTab(p, 76, 149); // Inventory tab
        setTab(p, 77, 387); // Equipment tab
        setTab(p, 78, 271); // Prayer tab
        setTab(p, 79, p.spellbook); // Magic tab
        //setTab(p, 80, 662); // Summoning tab <- disabled
        setTab(p, 81, 550); // Friend tab
        setTab(p, 82, 551); // Ignore tab
        setTab(p, 83, 589); // Clan tab
        setTab(p, 84, 261); // Setting tab
        setTab(p, 85, 464); // Emote tab
        setTab(p, 86, 187); // Music tab
        setTab(p, 87, 182); // Logout tab
    }

    public void setFullScreenInterfaces(Player p) {
        if (p == null || p.disconnected[0]) {
            return;
        }
	setInterface(p, 1, 549, 0, 746);
	setInterface(p, 1, 746, 13, 748); //energy orb
	setInterface(p, 1, 746, 14, 749); //energy orb
	setInterface(p, 1, 746, 15, 750); //energy orb
	//setInterface(p, 1, 746, 16, 747); //summing orb
	setInterface(p, 1, 746, 71, 149);  //Inventory tab
	setInterface(p, 1, 746, 18, 751); //things below chatbox
	setInterface(p, 1, 752, 8, 137); //chatbox
	setInterface(p, 1, 746, 65, 752); //chatbox 752
    }

    /**
     * Send coordinates, used with other getActionSender().
     * @param p The Player which the frame should be created for.
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    public void sendCoords(Player p, int x, int y) {
        if (p == null || p.stream == null || p.disconnected[0]) {
            return;
        }
        p.getByteVector().createFrame(177);
        p.getByteVector().writeByte(y);
        p.getByteVector().writeByteS(x);
    }

    /**
     * Creates an item on the ground at itemX and itemY.
     * @param p The Player which the frame should be created for.
     * @param itemId The item id to be displayed.
     * @param itemAmt The amount the item stack size is.
     * @param itemX The absolute x coordinate to display the item.
     * @param itemY The absolute y coordinate to display the item.
     * @param itemHeight The height level to set the item.
     */
    public void createGroundItem(Player p, int itemId, int itemAmt, int itemX, int itemY, int itemHeight) {
        if (p == null || p.stream == null || p.disconnected[0]) {
            return;
        }
        if (Misc.getDistance(itemX, itemY, p.absX, p.absY) <= 60 && p.heightLevel == itemHeight) {
            sendCoords(p, (itemX - ((p.mapRegionX - 6) * 8)), (itemY - ((p.mapRegionY - 6) * 8)));
            p.getByteVector().createFrame(25);
            p.getByteVector().writeWordBigEndianA(itemAmt);
            p.getByteVector().writeByte(0);
            p.getByteVector().writeWordBigEndianA(itemId);
        }
    }

    /**
     * Removes an item on the ground at itemX and itemY.
     * @param p The Player which the frame should be created for.
     * @param itemId The item id to remove.
     * @param itemX The absolute x coordinate to remove the item.
     * @param itemY The absolute y coordinate to remove the item.
     * @param itemHeight The height level toe remove the item at.
     */
    public void removeGroundItem(Player p, int itemId, int itemX, int itemY, int itemHeight) {
        if (p == null || p.stream == null || p.disconnected[0]) {
            return;
        }
        if(Misc.getDistance(itemX, itemY, p.absX, p.absY) <= 60 && p.heightLevel == itemHeight) {
            sendCoords(p, (itemX - ((p.mapRegionX - 6) * 8)), (itemY - ((p.mapRegionY - 6) * 8)));
            p.getByteVector().createFrame(201);
            p.getByteVector().writeByte(0);
            p.getByteVector().writeWord(itemId);
        }
    }

    /**
     * Remove Player Equipment
     * @param p The player which the frame should be created for.
     * @param index the index of the player
     */
     public void removeEquipment(Player p, int itemId, int index) {
        if(p == null || itemId <= 0 || index < 0 || index > 13 || p.stream == null)
        {
            return;
        }
        PlayerItems pi = new PlayerItems();
        if(!pi.addItem(p, p.equipment[index], p.equipmentN[index]))
        {
            sendMessage(p, "Not enough space in your inventory.");
            return;
        }
        p.equipment[index] = -1;
        p.equipmentN[index] = 0;
        p.getByteVector().createFrameVarSizeWord(135);
        p.getByteVector().writeByte(1);
        p.getByteVector().writeByte(131);
        p.getByteVector().writeByte(0);
        p.getByteVector().writeByte(28);
        p.getByteVector().writeWord(28);
        p.getByteVector().writeByte(index);
        p.getByteVector().writeWord(0);
        p.getByteVector().writeByte(0);
        p.getByteVector().endFrameVarSizeWord();
        p.appearanceUpdateReq = true;
        p.updateReq = true;
        p.calculateEquipmentBonus();
        PlayerWeapon pweapon = new PlayerWeapon(p);
        pweapon.setWeapon();
    }

    /**
     * Send players stat.
     * @param p The Player which the frame should be created for.
     * @param lvlId The stat id to send.
     */
    public void setSkillLvl(Player p, int lvlId) {
        if (p == null || p.stream == null || p.disconnected[0]) {
            return;
        }
        p.getByteVector().createFrame(217);
        p.getByteVector().writeByteC(p.skillLvl[lvlId]);
        p.getByteVector().writeDWord_v2(p.skillXP[lvlId]);
        p.getByteVector().writeByteC(lvlId);
    }

    public void checkCharacter(Player p, String name) {
    stream.inOffset = 0;
        try {
            FileInputStream in = new FileInputStream("./data/characters/mainsave/" + name + ".dat");
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
                {
                    sendMessage(p, Misc.longToString(Long.parseLong(line.substring(9))));
                }
                if (line.startsWith("ip:"))
                {
                    sendMessage(p, line.substring(3));
                }
            }
        }
        catch (Exception e) {
        }
    }

    /**
     * Set item display on an interface.
     * @param p The Player which the frame should be created for.
     * @param interfaceId The interface to display the items on.
     * @param childId The child interface on the main interface.
     * @param itemArray The item id array to set on the interface.
     * @param itemAmt The item array to go with the itemArray.
     */
    public void setItems(Player p, int interfaceId, int childId, int type, int[] itemArray, int[] itemAmt) {
        if (p == null || p.stream == null || p.disconnected[0]) {
            return;
        }
        p.getByteVector().createFrameVarSizeWord(255);
        p.getByteVector().writeWord(interfaceId);
        p.getByteVector().writeWord(childId);
        p.getByteVector().writeWord(type);
        p.getByteVector().writeWord(itemArray.length);
        for (int i = 0; i < itemArray.length; i++) {
            if (itemAmt[i] > 254) {
                p.getByteVector().writeByteS(255);
                p.getByteVector().writeDWord_v2(itemAmt[i]);
            } else {
                p.getByteVector().writeByteS(itemAmt[i]);
            }
            p.getByteVector().writeWordBigEndian(itemArray[i] + 1);
        }
        p.getByteVector().endFrameVarSizeWord();
    }

    /**
     * Set interface configs.
     * <p>This is used to do things such as hiding and displaying the special attack bar.
     * @param p The Player which the frame should be created for.
     * @param interfaceId The interface to the set the config with.
     * @param childId The child that belongs to the interface to change.
     * @param 1 for true, 0 for false.
     */
    public void setInterfaceConfig(Player p, int interfaceId, int childId, boolean set) {
        if (p == null || p.stream == null || p.disconnected[0]) {
            return;
        }
        p.getByteVector().createFrame(59);
        p.getByteVector().writeByteC(set ? 1 : 0);
        p.getByteVector().writeWord(childId);
        p.getByteVector().writeWord(interfaceId);
    }

    /**
     * Display a message in the chatbox.
     * @param p The Player which the frame should be created for.
     * @param s The message to display in the chatbox.
     */
    public void sendMessage(Player p, String s) {
        if (p == null || p.stream == null || p.disconnected[0]) {
            return;
        }
        p.getByteVector().createFrameVarSize(218);
        p.getByteVector().writeString(s);
        p.getByteVector().endFrameVarSize();
    }

    /**
     * Set a string on an interface.
     * @param p The Player which the frame should be created for.
     * @param str The string to set on the interface.
     * @param interfaceId The interface to set the text on.
     * @param childId The interface's child to set the text on.
     */
    public void setString(Player p, String str, int interfaceId, int childId) {
        if (p == null || p.stream == null || p.disconnected[0]) {
            return;
        }
        int sSize = str.length() + 5;
        p.getByteVector().createFrame(179);
        p.getByteVector().writeByte(sSize / 256);
        p.getByteVector().writeByte(sSize % 256);
        p.getByteVector().writeString(str);
        p.getByteVector().writeWord(childId);
        p.getByteVector().writeWord(interfaceId);
    }

    /**
     * Send this player's updated coordinates.
     * @param p The Player which the frame should be created for.
     */

    public void updateMovement(Player p) {
        if (p == null || p.stream == null || p.disconnected[0]) {
            return;
        }
        p.getByteVector().createFrameVarSizeWord(216);
        p.getByteVector().initBitAccess();
        p.getByteVector().writeBits(1, 1);
        if (p.runDir == -1) {
            p.getByteVector().writeBits(2, 1);
            p.getByteVector().writeBits(3, p.walkDir);
            p.getByteVector().writeBits(1, p.updateReq ? 1 : 0);
        } else {
            p.getByteVector().writeBits(2, 2);
            p.getByteVector().writeBits(3, p.runDir);
            p.getByteVector().writeBits(3, p.walkDir);
            p.getByteVector().writeBits(1, p.updateReq ? 1 : 0);
            if (p.runEnergy > 0) {
                p.runEnergyUpdateReq = true;
                p.runEnergy--;
            } else {
                p.isRunning = false;
            }
        }
    }

    /**
     * Tell the p this player isn't moving.
     * @param p The Player which the frame should be created for.
     */
    public void noMovement(Player p) {
        if (p == null || p.stream == null || p.disconnected[0]) {
            return;
        }
        p.getByteVector().createFrameVarSizeWord(216);
        p.getByteVector().initBitAccess();
        p.getByteVector().writeBits(1, p.updateReq ? 1 : 0);
        if (p.updateReq) {
            p.getByteVector().writeBits(2, 0);
        }
    }

    /**
     * Changes the coordinates this player is standing at.
     * @param p The Player which the frame should be created for.
     */
    public void teleport(Player p) {
        if (p == null || p.stream == null || p.disconnected[0]) {
            return;
        }
        p.getByteVector().createFrameVarSizeWord(216);
        p.getByteVector().initBitAccess();
        p.getByteVector().writeBits(1, 1);
        p.getByteVector().writeBits(2, 3);
        p.getByteVector().writeBits(7, p.currentX);
        p.getByteVector().writeBits(1, 1);
        p.getByteVector().writeBits(2, p.heightLevel);
        p.getByteVector().writeBits(1, p.updateReq ? 1 : 0);
        p.getByteVector().writeBits(7, p.currentY);
    }

/**
* Creates a Object.
* @param Objectid The Id of the Object to spawn.
* @param Heigh The Height to spawn the Object on.
* @param ObjectX The AbsX to spawn the Object on.
* @param ObjectY The AbsY to spawn the Object on.
* @param Face The Position for the OBject to face
* @param Type Object Type
* */

public void createObject(Player p, int objectId, int height, int objectX, int objectY, int face, int type) {
sendCoords(p, (objectX - ((p.mapRegionX - 6) * 8)), (objectY - ((p.mapRegionY - 6) * 8)));
int ot = ((type << 2) + (face & 3));
p.stream.createFrame(30);
p.stream.writeWordBigEndian(objectId);
p.stream.writeByteA(0);
p.stream.writeByteC(ot);

}

/**
* Creates a GlobalObject.
* @param Objectid The Id of the Object to spawn.
* @param Heigh The Height to spawn the Object on.
* @param ObjectX The AbsX to spawn the Object on.
* @param ObjectY The AbsY to spawn the Object on.
* @param Face The Position for the OBject to face
* @param Type Object Type
* */

public void createGlobalObject(int objectId, int height, int objectX, int objectY, int face, int type) {
for (Player p : Engine.players) {
if (p == null) {
continue;
}
createObject(p, objectId, height, objectX, objectY, face, type);
createObject(p, 2213, 0, 3170, 9752, -2, 11);
createObject(p, 2213, 0, 3180, 9760, -1, 11);
createObject(p, 4008, 0, 2593, 3416, -1, 10);
}
}
    /**
     * Send the map region and other positioning info to the p.
     * @param p The Player which the frame should be created for.
     */
    public void setMapRegion(Player p) {
        if (p == null || p.stream == null || p.disconnected[0]) {
            return;
        }
        p.getByteVector().createFrameVarSizeWord(142);
        p.getByteVector().writeWordA(p.mapRegionX);
        p.getByteVector().writeWordBigEndianA(p.currentY);
        p.getByteVector().writeWordA(p.currentX);
        boolean forceSend = true;
        if ((((p.mapRegionX / 8) == 48) || ((p.mapRegionX / 8) == 49)) && ((p.mapRegionY / 8) == 48)) {
            forceSend = false;
        }
        if (((p.mapRegionX / 8) == 48) && ((p.mapRegionY / 8) == 148)) {
            forceSend = false;
        }
        for (int xCalc = (p.mapRegionX - 6) / 8; xCalc <= ((p.mapRegionX + 6) / 8); xCalc++) {
            for (int yCalc = (p.mapRegionY - 6) / 8; yCalc <= ((p.mapRegionY + 6) / 8); yCalc++) {
       	int region = yCalc + (xCalc << 1786653352);
                	if (forceSend || ((yCalc != 49) && (yCalc != 149) && (yCalc != 147) && (xCalc != 50) && ((xCalc != 49) || (yCalc != 47)))) {
                    	int[] mapData = Engine.mapData.getMapData(region);
		if (mapData == null) {
			p.setCoords(3254, 3420, 0);
			sendMessage(p, "You got teleported to varrock due to Mapdata missing.");
			return;
		}
                    p.getByteVector().writeDWord(mapData[0]);
                    p.getByteVector().writeDWord(mapData[1]);
                    p.getByteVector().writeDWord(mapData[2]);
                    p.getByteVector().writeDWord(mapData[3]);
                }
            }
        }
        p.getByteVector().writeByteC(p.heightLevel);
        p.getByteVector().writeWord(p.mapRegionY);
        p.getByteVector().endFrameVarSizeWord();
    }
}