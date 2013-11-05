package net.com.codeusa.net.packethandler;

import net.com.codeusa.model.Player;
import net.com.codeusa.npcs.NPC;

/**
 * @author Codeusa <codeusa@live.com> <codeusa.net>
 */

public class ItemOnNPC {

	/**
	 * Class constructor
	 */
	public ItemOnNPC() {
		//Nothing
	}

	public void addItemOnNPCEvent(Player player) {
		int itemId = player.stream.readUnsignedWordA();
		int npcSlot = player.stream.readUnsignedWordA();
		int randomIndex = player.stream.readUnsignedWordA();
		int interfaceId = player.stream.readUnsignedWordA();
		player.getActionSender().sendMessage(player, "itemid: "+itemId);
		player.getActionSender().sendMessage(player, "npcslot: "+npcSlot);
		player.getActionSender().sendMessage(player, "index: "+randomIndex);
		player.getActionSender().sendMessage(player, "interface: "+interfaceId);
		if (interfaceId == 3214) {
		}
	}

}