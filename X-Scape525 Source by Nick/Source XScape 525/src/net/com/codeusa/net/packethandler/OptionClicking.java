package net.com.codeusa.net.packethandler;

import net.com.codeusa.util.Misc;
import net.com.codeusa.model.*;
import net.com.codeusa.model.skills.*;

/**
 * @author Winterlove <unknown> <none>
 */

public class OptionClicking {

	Player player;

	/**
	 * Handles the option clicking (Interface)
	 * @param player the player which the constructor should be created for.
	 */
	public OptionClicking(Player player) {
		this.player = player;
		/**
		 * Stream methods for packet 63
		 */
		int somejunk = player.stream.readUnsignedWord();
		player.optionId = player.stream.readSignedWordBigEndian();
		/**
		 * Calls PlayerFletching's class constructor
		 */
		PlayerFletching fletch = new PlayerFletching(player);
		if (player.optionId <= 0)
			return;

		switch (player.optionId) {

			case 16: //Option one
				if (player.fletchType == 1) {
					fletch.setFletching(true);
					fletch.setFletchingDelay(2);
					fletch.setFletchingAmount(1);
					fletch.setDeletedItem(1511);
					fletch.setItemAdded(48);
					fletch.setExperienceReceived(120);
				}
			break;

			case 15: //Option five
				if (player.fletchType == 1) {
					fletch.setFletching(true);
					fletch.setFletchingDelay(2);
					fletch.setFletchingAmount(5);
					fletch.setDeletedItem(1511);
					fletch.setItemAdded(48);
					fletch.setExperienceReceived(120);
				}
			break;

			case 14: //Option ten
				if (player.fletchType == 1) {
					fletch.setFletching(true);
					fletch.setFletchingDelay(2);
					fletch.setFletchingAmount(10);
					fletch.setDeletedItem(1511);
					fletch.setItemAdded(48);
					fletch.setExperienceReceived(120);
				}
			break;

			case 13: //Option X
				if (player.fletchType == 1) {
					fletch.setFletching(true);
					fletch.setFletchingDelay(2);
					fletch.setFletchingAmount(27);
					fletch.setDeletedItem(1511);
					fletch.setItemAdded(48);
					fletch.setExperienceReceived(120);
				}
			break;

			case 12: //Option one
				if (player.fletchType == 1) {
					fletch.setFletching(true);
					fletch.setFletchingDelay(2);
					fletch.setFletchingAmount(1);
					fletch.setDeletedItem(1511);
					fletch.setItemAdded(50);
					fletch.setExperienceReceived(80);
				}
			break;

			case 11: //Option five
				if (player.fletchType == 1) {
					fletch.setFletching(true);
					fletch.setFletchingDelay(2);
					fletch.setFletchingAmount(5);
					fletch.setDeletedItem(1511);
					fletch.setItemAdded(50);
					fletch.setExperienceReceived(80);
				}
				if (player.fletchType == 2) {
					fletch.setFletching(true);
					fletch.setFletchingDelay(2);
					fletch.setFletchingAmount(1);
					fletch.setDeletedItem(1521);
					fletch.setItemAdded(56);
					fletch.setExperienceReceived(890);
				}
				if (player.fletchType == 6) {
					fletch.setFletching(true);
					fletch.setFletchingDelay(2);
					fletch.setFletchingAmount(1);
					fletch.setDeletedItem(1513);
					fletch.setItemAdded(70);
					fletch.setExperienceReceived(890);
				}
			break;

			case 10: //Option ten
				if (player.fletchType == 1) {
					fletch.setFletching(true);
					fletch.setFletchingDelay(2);
					fletch.setFletchingAmount(10);
					fletch.setDeletedItem(1511);
					fletch.setItemAdded(50);
					fletch.setExperienceReceived(80);
				}
				if (player.fletchType == 2) {
					fletch.setFletching(true);
					fletch.setFletchingDelay(2);
					fletch.setFletchingAmount(5);
					fletch.setDeletedItem(1521);
					fletch.setItemAdded(56);
					fletch.setExperienceReceived(890);
				}
				if (player.fletchType == 6) {
					fletch.setFletching(true);
					fletch.setFletchingDelay(2);
					fletch.setFletchingAmount(5);
					fletch.setDeletedItem(1513);
					fletch.setItemAdded(70);
					fletch.setExperienceReceived(890);
				}
			break;

			case 9: //Option X
				if (player.fletchType == 1) {
					fletch.setFletching(true);
					fletch.setFletchingDelay(2);
					fletch.setFletchingAmount(27);
					fletch.setDeletedItem(1511);
					fletch.setItemAdded(50);
					fletch.setExperienceReceived(80);
				}
				if (player.fletchType == 2) {
					fletch.setFletching(true);
					fletch.setFletchingDelay(2);
					fletch.setFletchingAmount(10);
					fletch.setDeletedItem(1521);
					fletch.setItemAdded(56);
					fletch.setExperienceReceived(890);
				}
				if (player.fletchType == 6) {
					fletch.setFletching(true);
					fletch.setFletchingDelay(2);
					fletch.setFletchingAmount(10);
					fletch.setDeletedItem(1513);
					fletch.setItemAdded(70);
					fletch.setExperienceReceived(890);
				}
			break;

			case 8: //Option one
				if (player.fletchType == 1) {
					fletch.setFletching(true);
					fletch.setFletchingDelay(2);
					fletch.setFletchingAmount(1);
					fletch.setDeletedItem(1511);
					fletch.setItemAdded(52);
					fletch.setExperienceReceived(35);
				}
				if (player.fletchType == 2) {
					fletch.setFletching(true);
					fletch.setFletchingDelay(2);
					fletch.setFletchingAmount(27);
					fletch.setDeletedItem(1521);
					fletch.setItemAdded(56);
					fletch.setExperienceReceived(890);
				}
				if (player.fletchType == 6) {
					fletch.setFletching(true);
					fletch.setFletchingDelay(2);
					fletch.setFletchingAmount(27);
					fletch.setDeletedItem(1513);
					fletch.setItemAdded(70);
					fletch.setExperienceReceived(890);
				}
			break;

			case 7: //Option five
				if (player.fletchType == 1) {
					fletch.setFletching(true);
					fletch.setFletchingDelay(2);
					fletch.setFletchingAmount(5);
					fletch.setDeletedItem(1511);
					fletch.setItemAdded(52);
					fletch.setExperienceReceived(35);
				}
				if (player.fletchType == 2) {
					fletch.setFletching(true);
					fletch.setFletchingDelay(2);
					fletch.setFletchingAmount(1);
					fletch.setDeletedItem(1521);
					fletch.setItemAdded(54);
					fletch.setExperienceReceived(180);
				}
				if (player.fletchType == 6) {
					fletch.setFletching(true);
					fletch.setFletchingDelay(2);
					fletch.setFletchingAmount(1);
					fletch.setDeletedItem(1513);
					fletch.setItemAdded(72);
					fletch.setExperienceReceived(890);
				}
			break;

			case 6: //Option ten
				if (player.fletchType == 1) {
					fletch.setFletching(true);
					fletch.setFletchingDelay(2);
					fletch.setFletchingAmount(10);
					fletch.setDeletedItem(1511);
					fletch.setItemAdded(52);
					fletch.setExperienceReceived(35);
				}
				if (player.fletchType == 2) {
					fletch.setFletching(true);
					fletch.setFletchingDelay(2);
					fletch.setFletchingAmount(5);
					fletch.setDeletedItem(1521);
					fletch.setItemAdded(54);
					fletch.setExperienceReceived(180);
				}
				if (player.fletchType == 6) {
					fletch.setFletching(true);
					fletch.setFletchingDelay(2);
					fletch.setFletchingAmount(5);
					fletch.setDeletedItem(1513);
					fletch.setItemAdded(72);
					fletch.setExperienceReceived(890);
				}
			break;
			case 2:
				if (player.switchSpells != 0) {
					player.spellbook = 192;
					player.getActionSender().setTab(player, 79, 192);
					player.switchSpells = 0;
				}
				if (player.spellbookSwap && player.switchSpells == 0) {
					player.getActionSender().setTab(player, 79, 192);
					player.usedSpellbookSwap = true;
				}
				player.getActionSender().removeChatboxInterface(player);
			break;
			case 3:
				if (player.switchSpells != 0) {
					player.spellbook = 193;
					player.getActionSender().setTab(player, 79, 193);
					player.switchSpells = 0;
				}
				if (player.spellbookSwap && player.switchSpells == 0) {
					player.getActionSender().setTab(player, 79, 193);
					player.usedSpellbookSwap = true;
				}
				player.getActionSender().removeChatboxInterface(player);
			break;
			case 4:
				if (player.switchSpells != 0 && player.getLevelForXP(1) >= 40) {
					player.spellbook = 430;
					player.getActionSender().setTab(player, 79, 430);
					player.switchSpells = 0;
				} else if (player.switchSpells != 0 && player.getLevelForXP(1) < 40) {
				player.getActionSender().sendMessage(player, "You need a defence level of 40 or more to use the Lunar Spellbook.");
				player.getActionSender().removeChatboxInterface(player);
				return;
				}
				if (player.spellbookSwap && player.switchSpells == 0) {
					player.spellbookSwap = false;
					player.usedSpellbookSwap = false;
				}
				player.getActionSender().removeChatboxInterface(player);
			break;



		}
		if (player.optionArray[0] == false) player.getActionSender().removeChatboxInterface(player);
		System.out.println("option type: "+player.optionId);
		player.optionArray[0] = false;
	}

}