package net.com.codeusa.model.skills;

import net.com.codeusa.*;
import net.com.codeusa.model.*;
import net.com.codeusa.model.items.*;

/**
 * @author Codeusa
 */

public class PlayerFletching {

	Player player;

	/**
	 * Class constructor
	 */
	public PlayerFletching(Player player) {
		this.player = player;
	}

	/**
	 * Fletching Engine.
	 */
	public void appendFletchingEngine() {
		if (player == null || player.fletchDelay > 0)
			return;

		/**
		 * calls PlayerItems constructor to load methods.
		 */
		PlayerItems pi = new PlayerItems();
		/**
		 * If player is done with his fletching amount it will return.
		 * same for if player ran out of logs.
		 */
		if (!pi.hasPlayerItem(player, getFletchingLog()) || player.fletchAmt <= 0) {
			resetFletchingEngine();
			return;
		}
		/**
		 * If player's fletch delay reaches 0 or is below 0, The system will start again.
		 * else if the fletch delay is above 0 it will return.
		 */
		if (player.fletchDelay <= 0) {
			player.requestAnim(1248, 0);
			pi.deleteItem(player, getFletchingLog(), pi.getItemSlot(player, getFletchingLog()), 1);
			pi.addItem(player, getFinishedItem(), getReceivedAmount());
			player.appendExperience(getExperienceReceived(), 9);
			player.fletchAmt--;
			setFletchingDelay(5);
		}
	}

	/**
	 * The amount of bows that are added.
 	 */
	int getReceivedAmount() {
		return Server.engine.playerItems.hasPlayerItem(player, 1511) && getFinishedItem() == 52 ? 15 : 1;
	}

	/**
	 * How much experience must be added.
	 */
	int getExperienceReceived() {
		return player.fletchExp;
	}

	/**
	 * Gets logs id which is going to be fletched.
	 * no parameter.
	 */
	int getFletchingLog() {
		return player.deletedItem;
	}

	/**
	 * Gets finished bow id which is fletched of logs.
	 * no parameter.
	 */
	int getFinishedItem() {
		return player.addedItem;
	}

	/**
	 * Resets fletching variables
	 */
	void resetFletchingEngine() {
		setFletching(false);
		setFletchingAmount(0);
		setFletchingDelay(0);
	}

	/**
	 * Sets log id which is going to be fletched.
	 * @param deletedItem the deleted log id.
	 */
	public void setDeletedItem(int deletedItem) {
		player.deletedItem = deletedItem;
	}

	/**
	 * Sets bow id which is fletched of logs.
	 * @param addedItem the bow id.
	 */
	public void setItemAdded(int addedItem) {
		player.addedItem = addedItem;
	}

	/**
	 * Experience added.
	 * @param fletchExp the amount of experience thats going to be added for fletching skill.
	 */
	public void setExperienceReceived(int fletchExp) {
		player.fletchExp = fletchExp;
	}

	/**
	 * Sets fletching variables
	 * @param fletchDelay the fletching delay of fletching logs.
	 * @param fletchAmount the amount of logs which are going to be fletched.
	 * @param fletchLog the log id thats going to be fletched.
	 * @param fletchBow the bow id thats fletched of logs
	 */
	public void setFletchingDelay(int fletchDelay) {
		player.fletchDelay = fletchDelay;
	}

	/**
	 * Sets fletching amount.
	 * @param fletchAmount the amount which is going to be setted.
	 */
	public void setFletchingAmount(int fletchAmount) {
		player.fletchAmt = fletchAmount;
	}

	/**
	 * Sets if fletching variable
	 * @param isFletching if player is fletching.
	 */
	public void setFletching(boolean isFletching) {
		player.isFletching = isFletching;
	}

}