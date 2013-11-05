package net.com.codeusa.model.games;

import net.com.codeusa.*;
import net.com.codeusa.util.*;
import net.com.codeusa.model.*;
import net.com.codeusa.model.games.*;

/**
 * author Shassan  <rune-server.org>
 */

public class DuelArena {

	Player p;

	/**
	 * class constructor
	 */
	public DuelArena(Player p) {
		this.p = p;
	}

	/**
	 * Initializes duel accepting.
	 */
	public void initializeDuelAcception() {
		Player player = Engine.players[p.duelFriend];
		if (p == null || player == null) {
			p.duelFriend = 0;
			return;
		}
		if (!p.duelScreen1 && !p.duelScreen2) {
			player.duelFriend = p.playerId;
		}
		if (!p.duelScreen1 && !p.duelScreen2) {
			p.getActionSender().showInterface(p, 637);
			player.getActionSender().showInterface(player, 637);
			p.getActionSender().setString(p, ""+player.username+"", 637, 16);
			player.getActionSender().setString(player, ""+p.username+"", 637, 16);
			p.getActionSender().setString(p, ""+player.combatLevel+"", 637, 18);
			player.getActionSender().setString(player, ""+p.combatLevel+"", 637, 18);
			p.duelScreen1 = p.duelScreen2 = true;
			player.duelScreen1 = player.duelScreen2 = true;
		}
		
	}

	/**
	 * Activates every 600MS
	 */
	public void process() {
		if (p.duelScreen1) {
			Player player = Engine.players[p.duelFriend];
			if (player != null) {
				if (player == null) {
					p.duelFriend = 0;
					return;
				}
				if (!p.acceptDuel && p.acceptScreen1 && !player.acceptScreen1) {
					player.getActionSender().sendMessage(player, "<img=0>The other player has accepted.<img=0>");
					p.acceptDuel = true;
				}
				if (p.acceptDuel && p.acceptScreen1 && player.acceptScreen1) {
					p.setCoords(3380 + Misc.random(5), 3232 + Misc.random(5), 0);
					player.setCoords(3369 + Misc.random(6), 3232 + Misc.random(6), 0);
					p.countDelay = player.countDelay = 3;
					p.countType = player.countType = 3;
					p.getActionSender().removeShownInterface(p);
					player.getActionSender().removeShownInterface(player);
					player.duelFriend = p.playerId;
					p.acceptDuel = player.acceptDuel = p.acceptScreen1 = player.acceptScreen1 = false;
				}
			}
		}
	}

	/**
	 * Resets dueling.
	 */
	public void resetDuelSettings() {
		p.acceptDuel = p.acceptScreen1 = p.acceptScreen2 = false;
		p.duelScreen1 = p.duelScreen2 = false;
	}

	/**
	 * Resets dueling.
	 */
	public void resetDuelSettings1() {
		p.duelFriend = 0;
		p.acceptDuel = p.acceptScreen1 = p.acceptScreen2 = false;
		p.duelScreen1 = p.duelScreen2 = false;
	}

}