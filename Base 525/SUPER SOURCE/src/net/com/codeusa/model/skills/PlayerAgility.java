/*
 * @author Codeusa
 *
 * PlayerAgility.java
 *
 * Version 1.0
 *
 */

package net.com.codeusa.model.skills;

import net.com.codeusa.model.misc.*;
import net.com.codeusa.model.*;

public class PlayerAgility {

	Player p;

	/**
	 * Constructor
	 */
	public PlayerAgility(Player p) {
		this.p = p;
	}

	/**
	 * Once player has finished performing
	 * on the obstacle, player will be resetted
	 * to regular stats.
	 */
	public void resetPerforming() {
		if (p == null) {
			return;
		}
		PlayerMethods pm = new PlayerMethods(p);
		if (p.agilityDelay == 0) {
			p.appendExperience(getAgilityExp(), 16);
			pm.setAgilityPerforming(false);
			if (p.agilityType == 1 || p.agilityType == 3) {
				p.requestAnim(p.playerWeapon.getStandEmote(p.equipment[3]), 0);
			} else if (p.agilityType == 2) {
				p.setCoords(2473, 3420, 2);
			} else if (p.agilityType == 4) {
				p.setCoords(2486, 3421, 0);
			} else if (p.agilityType == 5) {
				p.setCoords(2486, 3428, 0);
			} else if (p.agilityType == 6) {
				p.requestAnim(p.playerWeapon.getStandEmote(p.equipment[3]), 0);
			}
			resetAnimations();
			pm.setAgilityDelay(0);
			pm.setAgilityType(0);
		}
	}

	void resetAnimations() {
		p.standEmote = 0x328;
		p.walkEmote = 0x333;
		p.runEmote = 0x338;
	}

	int getAgilityExp() {
		switch (p.agilityType) {

			case 1: return 1000;
			case 2: return 240;
			case 3: return 570;
			case 4: return 1210;
			case 5: return 805;
			case 6: return 1310;
		}
	return 0;
	}

}