	package net.com.codeusa.net.packethandler;

import net.com.codeusa.Engine;
import net.com.codeusa.model.Player;

/**
 * @author Codeusa <codeusa@live.com> <codeusa.net>
 */

public class ItemOnObject {

	/**
	 * Class constructor
	 */
	public ItemOnObject() {
	}

	/**
	 * Item on object
	 */
	public void createPacket(Player player) {
        	int coordY = player.getByteVector().readSignedWordBigEndian();
        	int itemId = player.getByteVector().readUnsignedWord();
        	int junk1 = player.getByteVector().readUnsignedWord();
        	int junk2 = player.getByteVector().readUnsignedWord();
        	int junk3 = player.getByteVector().readUnsignedWord();
        	int objectId = player.getByteVector().readUnsignedWordA();
        	int coordX = player.getByteVector().readUnsignedWord();
		if (itemId == 1591 && objectId == 31534) {
			if (player.absX == 3033 && player.absY == 2985 && player.heightLevel == 0) {
					Engine.playerItems.deleteItem(player, 1591, Engine.playerItems.getItemSlot(player, 1591), 1);
					player.playerWalk(3033, 2986, 6132, 0);
					player.getActionSender().sendMessage(player, "You unlock the door and quickly walk through.");
			}
		return;
		}
		player.getActionSender().sendMessage(player, "itemId: "+itemId);
		player.getActionSender().sendMessage(player, "objectId "+objectId);
	}

}