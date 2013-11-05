package net.com.codeusa.model.combat;

import net.com.codeusa.*;
import net.com.codeusa.model.*;

/**
 *
 * @author Codeusa
 */

public class PrayerSystem {

	Player p;

	/**
	 * class constructor.
	 * @param p the player which the class should be created for.
	 */
	public PrayerSystem(Player p) {
		this.p = p;
	}

	/**
	 * Retribution.
	 * @param mHit the max hit of retribution.
	 */
	public void appendRetributionEffect(int mHit) {
		if (p == null) {
			return;
		}
		for (Player pl : Server.engine.players) {
			if (pl != null) {
				if (pl.distanceToPoint(p.absX, p.absY) <= 3) {
					pl.appendHit(mHit, 0);
				}
			}
		}
	}

}