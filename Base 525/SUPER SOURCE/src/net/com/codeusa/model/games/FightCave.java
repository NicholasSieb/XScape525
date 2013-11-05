package net.com.codeusa.model.games;

import net.com.codeusa.Server;
import net.com.codeusa.npcs.NPC;
import net.com.codeusa.model.Player;

/**
 * @author Codeusa
 */

public class FightCave {

	Player player;

	/**
	 * Class constructor
	 */
	public FightCave(Player player) {
		this.player = player;
	}

	/**
	 * Fight cave System
	 */
	public void fightSystem() {
		switch (player.waveCount) {

			case 0:
				player.waveType[0] = 2735;
				player.waveType[1] = 2394;
				player.waveType[2] = 5142;
				player.waveType[3] = player.heightLevel;
				player.waveType[4] = 1;
			break;

			case 1:
				player.waveType[0] = 2735;
				player.waveType[1] = 2389;
				player.waveType[2] = 5141;
				player.waveType[3] = player.heightLevel;
				player.waveType[4] = 2;
			break;

			case 2:
				player.waveType[0] = 2737;
				player.waveType[1] = 2389;
				player.waveType[2] = 5141;
				player.waveType[3] = player.heightLevel;
				player.waveType[4] = 3;
			break;

			case 5:
				player.waveType[0] = 2739;
				player.waveType[1] = 2389;
				player.waveType[2] = 5141;
				player.waveType[3] = player.heightLevel;
				player.waveType[4] = 4;
			break;

			case 6:
				player.waveType[0] = 2741;
				player.waveType[1] = 2389;
				player.waveType[2] = 5141;
				player.waveType[3] = player.heightLevel;
				player.waveType[4] = 5;
			break;

			case 7:
				player.waveType[0] = 2743;
				player.waveType[1] = 2389;
				player.waveType[2] = 5141;
				player.waveType[3] = player.heightLevel;
				player.waveType[4] = 6;
			break;

			case 8:
				player.waveType[0] = 2744;
				player.waveType[1] = 2389;
				player.waveType[2] = 5141;
				player.waveType[3] = player.heightLevel;
				player.waveType[4] = 7;
			break;

			case 9:
				player.waveType[0] = 2745;
				player.waveType[1] = 2389;
				player.waveType[2] = 5141;
				player.waveType[3] = player.heightLevel;
				player.waveType[4] = 8;
			break;
		}
		if (hasNeededKills())
			Server.engine.newNPC(player.waveType[0], player.waveType[1], player.waveType[2], player.waveType[3], 0, 0, 0, 0, false, player.playerId);
		player.waveDelay = -1;
	}

	boolean hasNeededKills() {
		switch (player.waveType[4]) {

			case 1:
				if (player.waveCount == 0)
					return true;

			case 2:
				if (player.waveCount == 1)
					return true;

			case 3:
				if (player.waveCount == 2)
					return true;

			case 4:
				if (player.waveCount == 5)
					return true;

			case 5:
				if (player.waveCount == 6)
					return true;

			case 6:
				if (player.waveCount == 7)
					return true;

			case 7:
				if (player.waveCount == 8)
					return true;

			case 8:
				if (player.waveCount == 9)
					return true;

			default:
				return false;
		}
	}

	/**
	 * Gets height for Fight cave (Checking for an available height level)
	 */
	public int getCaveHeight() {
		for (Player p : Server.engine.players) {
			if (p != null) {
				if (p.heightLevel == player.heightLevel)
					player.heightLevel += 4;
			}
		}
		return player.heightLevel;
	}

	/**
	 * Checks if players are in the Fight cave.
	 */
	public boolean playersInCave() {
		for (Player p : Server.engine.players) {
			if (p != null) {
				if (p.inJadCave())
					return true;
			}
		}
		return false;
	}

	/**
	 * Kill of NPCs in Fight cave.
	 */
	public void deleteFightNPCs() {
		for (int i = 0; i < 10000; i++) {
			if (Server.engine.npcs[i] == null)
				continue;
			NPC n = Server.engine.npcs[i];
			if (n.spawnedFor == player.playerId) {
				n.absX = 0;
				n.absY = 0;
				n.currentHP = 0;
				Server.engine.npcs[i] = null;
			}
		}
	}

}