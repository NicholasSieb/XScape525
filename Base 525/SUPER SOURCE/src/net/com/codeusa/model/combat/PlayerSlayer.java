package net.com.codeusa.model.combat;

import net.com.codeusa.*;
import net.com.codeusa.model.*;

/**
 * @author Codeusa
 */

public class PlayerSlayer {

	Player p;

	/**
	 * class constructor
	 * @param p the player which the constructor should be created for.
	 */
	public PlayerSlayer(Player p) {
		this.p = p;
	}

	/**
	 * Slayer monster picking which is gonna be slayed by the player.
	 */
	public void appendTargetPicking() {
		if (p == null) {
			return;
		}
		if (!p.slayerTask) {
			for (int i = 0; i < p.slayerArray.length; i++) {
				p.slayerAmount = getRandom(p.slayerArray[i]);
			}
			setSlayerAmount(p.slayerAmount);
			setSlayerTask(true);
			p.getActionSender().sendMessage(p, "Your current slayer task has been updated.");
			p.getActionSender().sendMessage(p, "Your task is to kill "+p.slayerAmount+" "+Server.engine.getNPCName(getRandomSlayer())+".");
			//setSlayerType(p.slayerType);
		} else {
			p.getActionSender().sendMessage(p, "You currently already have a slayer task.");
			p.getActionSender().sendMessage(p, "Please finish your slayer task.");
		}
	}

	/**
	 * Sets slayer task.
	 * @param slayerTask the slayer task variable used to check if player already has slayer task.
	 */
	public void setSlayerTask(boolean slayerTask) {
		p.slayerTask = slayerTask;
	}

	/**
	 * Loops through a range and picks a random value.
	 * @param range the range within.
	 */
    	private int getRandom(int range) {
        	return (int)(Math.random() * (range + 1));
    	}

	/**
	 * Loops through values which are in a array (integer based)
	 */
	public int getRandomSlayer() {
		return p.slayerType[(int)(Math.random()*p.slayerType.length)];
	}

	public void setSlayerAmount(int slayerAmount) {
		p.slayerAmount1 = slayerAmount;
	}

	public void setSlayerType(int slayerType) {
		p.slayerType1 = slayerType;
	}

}