package net.com.codeusa.model.skills;

import net.com.codeusa.model.Player;

/**
 * @author Codeusa
 */

public class FishingProtocol implements Fishing {

	Player player;

	public FishingProtocol(Player player) {
		this.player = player;
	}
	/**
	 * Fishing attempting.
	 */
	public void handleAttempting() {
		getOwner().getActionSender().sendMessage(getOwner(), "hi.");
	}

	Player getOwner() {
		return player;
	}

}