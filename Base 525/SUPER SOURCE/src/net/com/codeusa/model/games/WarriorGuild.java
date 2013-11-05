package net.com.codeusa.model.games;

import net.com.codeusa.model.Player;
import net.com.codeusa.model.items.PlayerItems;

/**
 * @author Codeusa
 */

public class WarriorGuild {

	Player player;

	/**
	 * Class constructor
	 * @param player the player which the constructor should be made for.
	 */
	public WarriorGuild(Player player) {
		this.player = player;
	}

	/**
	 * Defence minigame of warrior guild.
	 */
	public void addWarriorDefenceEvent() {
		if (getOwner() == null || !onCorrectCoordinates())
			return;
		int offsetX = (getOwner().absX - getOwner().absX) * -1;
		int offsetY = (getOwner().absY + 9 - getOwner().absX) * -1;
		getOwner().getActionSender().addProjectile(getOwner(), 9, 0, offsetY, offsetX,
			50, 80, 249, 55, 31, getOwner().playerId, true); //Creates projectile
	}

	/**
 	 * Checking if player has the needed armour to create animated armour.
	 */
	public boolean hasCorrectEquipment() {
		PlayerItems pi = new PlayerItems();
		if (player.warriorArmour.equals("Rune")) {
			if (pi.hasPlayerItem(player, 1163) && pi.hasPlayerItem(player, 1127) && pi.hasPlayerItem(player, 1079))
				return true;
		}
		return false;
	}

	/**
	 * Selects a defender which is going to be dropped by Cyclops.
	 */
	public void selectDefenderTarget(int playerDefenderId) {
		PlayerItems pi = new PlayerItems();
		for (int i = 8844; i <= 8850; i++) {
			if (playerDefenderId == 0 && !pi.hasPlayerItem(player, i)) {//Bronze process
				playerDefenderId = 1;
				player.getActionSender().sendMessage(player, "Your next Cyclops killing task has been updated.");
				player.getActionSender().sendMessage(player, "The cyclops You kill will currently drop now Bronze defenders.");
			}
		}
		if (playerDefenderId == 1 && pi.hasPlayerItem(player, 8844)) {//Iron process
			playerDefenderId = 2;
			player.getActionSender().sendMessage(player, "Your next Cyclops killing task has been updated.");
			player.getActionSender().sendMessage(player, "The cyclops You kill will currently drop now Iron defenders.");
		}
		if (playerDefenderId == 2 && pi.hasPlayerItem(player, 8845)) {//Steel process
			playerDefenderId = 3;
			player.getActionSender().sendMessage(player, "Your next Cyclops killing task has been updated.");
			player.getActionSender().sendMessage(player, "The cyclops You kill will currently drop now Steel defenders.");
		}
		if (playerDefenderId == 3 && pi.hasPlayerItem(player, 8846)) {//Black process
			playerDefenderId = 4;
			player.getActionSender().sendMessage(player, "Your next Cyclops killing task has been updated.");
			player.getActionSender().sendMessage(player, "The cyclops You kill will currently drop now Black defenders.");
		}
		if (playerDefenderId == 4 && pi.hasPlayerItem(player, 8847)) {
			playerDefenderId = 5;
			player.getActionSender().sendMessage(player, "Your next Cyclops killing task has been updated.");
			player.getActionSender().sendMessage(player, "The cyclops You kill will currently drop now Mithril defenders.");
		}
		if (playerDefenderId == 5 && !pi.hasPlayerItem(player, 8848)) {
			playerDefenderId = 6;
			player.getActionSender().sendMessage(player, "Your next Cyclops killing task has been updated.");
			player.getActionSender().sendMessage(player, "The cyclops You kill will currently drop now Adamantite defenders.");
		}
		if (playerDefenderId == 6 && pi.hasPlayerItem(player, 8849)) {
			playerDefenderId = 7;
			player.getActionSender().sendMessage(player, "Your next Cyclops killing task has been updated.");
			player.getActionSender().sendMessage(player, "The cyclops You kill will currently drop now Rune defenders.");
		}
	}

	/**
	 * Removes armour.
	 */
	public void removeWarriorEquipment() {
		PlayerItems pi = new PlayerItems();
		if (player.warriorArmour.equals("Rune")) {
			pi.deleteItem(player, 1127, pi.getItemSlot(player, 1127), 1);
			pi.deleteItem(player, 1079, pi.getItemSlot(player, 1079), 1);
			pi.deleteItem(player, 1163, pi.getItemSlot(player, 1163), 1);
		}
	}

	Player getOwner() {
		return player;
	}

	boolean onCorrectCoordinates() {
		return getOwner().absX == 2842 && getOwner().absY == 3545 && getOwner().heightLevel == 1;
	}

	/**
	 * Players cannot pick up their items which are dropped by other players in the room where animated armours are made.
	 */
	public boolean animatedRoom() {
		return player.absX >= 2849 && player.absX <= 2861 && player.absY >= 3534 && player.absY <= 3545;
	}

}