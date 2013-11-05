package net.com.codeusa.model.skills;

import net.com.codeusa.*;
import net.com.codeusa.model.*;
import net.com.codeusa.util.Misc;

/**
 *
 * @author Codeusa <codeusa@live.com> <rune-server.org>
 */

public class PlayerThieving {

	/**
	 * Used to call the Player's class constructor.
	 */
	Player p;

	/**
	 * Constructor.
	 * @param p the player which the constructor should be build for.
	 */
	public PlayerThieving(Player p) {
		this.p = p;
	}

	/**
	 * Stall system
	 */
	public void addStallThievingEvent() {
		if (p == null)
			return;
		if (p.thievingArray[0] == 0) {
			p.appendExperience(getExperienceAddedAmount(), 17);
			p.getActionSender().sendMessage(p, getStallThievingMessage());
			Server.engine.playerItems.addItem(p, getThievedItem(), 1);
			for (int i = 0; i < p.thievingArray.length; i++)
				p.thievingArray[i] = -1;
		}
	}

	int getExperienceAddedAmount() {
		switch (p.thievingArray[1]) {

			case 1:
				return 80;

			case 2:
				return 85;

			default:
				return 0;	
		}
	}

	int getThievedItem() {
		int randomInteger = Misc.random(5);
		switch (p.thievingArray[1]) {

			case 1:
				if (randomInteger == 0)
					return 385;
				else if (randomInteger == 1)
					return 391;
				else
					return 7946;

			case 2:
				if (randomInteger == 0)
					return 2440;
				else if (randomInteger == 1)
					return 2434;
				else
					return 6685;

			default:
				return -1;
		}
	}

	String getStallThievingMessage() {
		switch (p.thievingArray[1]) {

			case 1:
				return "You succesfully thieved some fish.";

			case 2:
				return "You succesfully thieved a potion.";
                    case 34385:
                        return "you succesfully thieved some gold coin's.";
		}
		return "";
	}

	/**
	 * Pickpocket system.
	 */
	public void appendPickpocket() {
		if (p == null)
			return;

		appendThievingMessage();
		if (p.skillLvl[18] < getRequiredLevel())
			return;

		if (p.faceToReq != (p.npcClick2 + 32768))
			p.requestFaceTo(p.npcClick2 + 32768);

		if (p.pickPocketDelay <= 0) {
			setPickpocketDelay(7);
			appendCoins(getRandom(100));
			p.requestAnim(881, 0);
			p.getActionSender().sendMessage(p, "You succesfully pickpocketed the man.");
		}
	}

	/**
	 * Creates a player message if player doenst have the requirement
	 * to pickpocket.
	 */
	void appendThievingMessage() {
		clearThievingVariables();
		if (p.skillLvl[18] < getRequiredLevel())
			p.getActionSender().sendMessage(p, "You need a thieving level of "+getRequiredLevel()+" to pickpocket this man.");
	}

	/**
	 * Resets thieving.
	 */
	void clearThievingVariables() {
		if (p == null)
			return;
		setPickpocketDelay(0);
	}

	/**
	 * Returns values to check if player has required thieving level.
	 */
	int getRequiredLevel() {
		switch (p.npcClick2) {

			case 2: return 1;
                    case 34385: return 75;
		}
	return 0;
	}

	/**
	 * Adds coins into the players inventory.
	 * @param amount the amount of coins which will be added.
	 */
	void appendCoins(int amount) {
		Engine.playerItems.addItem(p, 995, amount);

	}

	/**
	 * Sets the pickpocket delay to count down if the value which must be returned is above 0.
	 * @param pickPocketDelay
	 */
	public void setPickpocketDelay(int pickPocketDelay) {
		p.pickPocketDelay = pickPocketDelay;
	}

	/**
	 * Returns a value by picking a random one. (Java system)
	 * @param range the max value of random picking.
	 */
	int getRandom(int range) {
		return (int)(Math.random() * (range + 1));
	}

}