package net.com.codeusa.model.skills;

import net.com.codeusa.*;
import net.com.codeusa.model.*;

/*
 *
 * @author Codeusa
 */

public class PlayerMining {

	/**
	 * Simple variable to call Player class by using constructor
	 * which has the parameter called p.
	 */
	Player p;

	/**
	 * Class constructor. (PlayerMining)
	 * @param p the player which the mining system should be created for.
	 */
	public PlayerMining(Player p) {
		this.p = p;
	}

	/**
	 * The mining system.
	 */
	public void appendMiningSystem() {
		if (p == null)
			return;

		appendMiningMessage();
		if (p.skillLvl[14] < getRequiredLevel())
			return;

		if (!hasPickaxe())
			return;

		if (p.miningDelay > 0)
			return;

		if (p.miningDelay == 0) {
			p.requestAnim(getMiningAnimation(), 0);
			setOreDelay(7);
			setMiningDelay(7);
		}
	}

	/**
	 * Once mining delay hits 0, this method gets activated
	 * and player receives the ore when system done reading the method.
	 * It also clears all mining variables.
	 */
	public void appendReceiveOre() {
		if (p.miningDelay <= 0) {
			if (!p.isMining) {
				clearMiningVariables();
				return;
			}
			appendOre(getOreId());
			p.appendExperience(getExperience(), 14);
			clearMiningVariables();
		}
	}

	/**
	 * Creates a message if player doenst have which is needed.
	 */
	void appendMiningMessage() {
		clearMiningVariables();
		if (!hasPickaxe())
			p.getActionSender().sendMessage(p, "You need a pickaxe to mine this ore.");
		if (p.skillLvl[14] < getRequiredLevel())
			p.getActionSender().sendMessage(p, "You need a mining level of "+getMiningLevel()+" to mine this ore.");
	}
	
	/**
	 * Resets all mining variables.
	 */
	void clearMiningVariables() {
		setMining(false);
		setMiningDelay(0);
	}

	/**
	 * Holds values to fill 'you do not have required lvl' message.
	 */
	int getMiningLevel() {
		switch (p.rockId) {

			case 11959: return 1;
		}
	return 0;
	}

	/**
	 * This method is used to check if player has a pickaxe.
	 */
	boolean hasPickaxe() {
		for (int i = 1265; i < 1276; i++)
			if (p.equipment[3] == i)
				return true;
	return false;
	}

	/**
	 * Simple integer based method which handles values
	 * to get a mining animation basing off player equipment (weapon)
	 */
	int getMiningAnimation() {
		switch (p.equipment[3]) {
	
			case 1265: return 625;

			case 1275: return 624;
		}
	return -1;
	}

	/**
	 * Another simple integer based method which handles values
	 * to get mining level which is required to mine th rock.
	 */
	int getRequiredLevel() {
		if (p.rockId == 11959) {
			return 1;
		}
	return 0;
	}

	/**
	 * Returns a item id which is gonna be added to the player's inventory.
	 */
	int getOreId() {
		if (p.rockId <= -1)
			return 0;

		if (p.rockId == 11959)
			return 438;

	return 0;	
	}

	/**
	 * Used to get experience amount for mining the rock.
	 */
	int getExperience() {
		if (p.rockId <= -1)
			return 0;

		if (p.rockId == 11959)
			return 20;

	return 0;
	}

	/**
	 * Adds an item to the player's inventory. Item id based on the parameter.
	 * @param itemId the item id which the player will receive.
	 */
	void appendOre(int itemId) {
		Engine.playerItems.addItem(p, itemId, 1);
	}

	/**
	 * Method to make 'isMining' variable in player class returns true or false.
	 * @param isMining if the player is gonna mine or not.
	 */
	public void setMining(boolean isMining) {
		p.isMining = isMining;
	}

	/**
	 * Method to make mining delay in player class returns a number to countdown.
	 * @param miningDelay the delay which is gonna be setted.
	 */
	public void setMiningDelay(int miningDelay) {
		p.miningDelay = miningDelay;
	}

	/**
	 * Method to make receiving ore delay in player class returns a number to countdown.
	 * @param oreDelay the delay which is gonna be setted.
	 */
	public void setOreDelay(int oreDelay) {
		p.receiveOreDelay = oreDelay;
	}

}