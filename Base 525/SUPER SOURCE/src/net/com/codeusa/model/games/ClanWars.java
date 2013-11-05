package net.com.codeusa.model.games;

import net.com.codeusa.Server;
import net.com.codeusa.util.Misc;
import net.com.codeusa.model.Player;
/**
 *
 * @author Codeusa
 */

public class ClanWars {

	public ClanWars() {
		Server.clanWaitDelay = 20;
		Server.clanFightDelay = 10;
	}

	public static void process() {
		if (Server.clanWaitDelay > 0)
			Server.clanWaitDelay--;
		if (Server.clanFightDelay > 0)
			Server.clanFightDelay--;
		for (Player pl : Server.engine.players) {
			if (pl != null) {
				if (Server.clanFightDelay == 0) {
					if (pl.clanWarsFightArea()) {
						pl.blackTeam = false;
						pl.whiteTeam = false;
						pl.whiteCount = pl.blackCount = 0;
						pl.setCoords(3266 + Misc.random(3), 3683 + Misc.random(3), 0);
						pl.getActionSender().removeOverlay(pl);
					}
				}
			}
			Server.clanFightDelay = 140;
		}
		for (Player p : Server.engine.players) {
			if (p != null) {
				if (Server.clanWaitDelay == 0) {
					if (p.blackTeam) {
						p.setCoords(3299 + Misc.random(3), 3725 + Misc.random(3), 0);
					}
					if (p.whiteTeam) {
						p.setCoords(3299 + Misc.random(3), 3725 + Misc.random(3), 0);
					}
					if (p.whiteTeam || p.blackTeam) {
						p.getActionSender().setString(p, ""+Server.engine.getWhiteClanPlayerCount(p.whiteCount)+"", 265, 6);
						p.getActionSender().setString(p, ""+Server.engine.getBlackClanPlayerCount(p.blackCount)+"", 265, 7);
						p.getActionSender().setOverlay(p, 265);
					}
					return;
				}
			}
 			Server.clanWaitDelay = 150;
		}
	}
}