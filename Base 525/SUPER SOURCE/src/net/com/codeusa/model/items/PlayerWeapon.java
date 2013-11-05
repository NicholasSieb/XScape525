/*
 * Class PlayerWeapon
 *
 * Version 1.0
 *
 * Saturday, August 23, 2008
 *
 * Created by Palidino76
 *
 * Revamped by Zach 93 / Zachhh
 *
 */

package net.com.codeusa.model.items;

import net.com.codeusa.Engine;
import net.com.codeusa.model.Player;

public class PlayerWeapon {

	public Player p;

	public PlayerWeapon(Player p) {
		this.p = p;
	}

	public void setWeapon() {
		if (p == null) {
			return;
		}

		//Variables

		p.walkEmote = getWalkEmote(p.equipment[3]);
		p.runEmote = getRunEmote(p.equipment[3]);
		p.standEmote = getStandEmote(p.equipment[3]);
		String weapon = Engine.items.getItemName(p.equipment[3]);
		int id = p.equipment[3];

		//Interface setting

		if (id == -1) {
			p.getActionSender().setTab(p, 73, 92);
            		p.getActionSender().setString(p, weapon, 92, 0);
			p.weaponType = 0;
		} else if (weapon.equals("Abyssal whip")) {
			p.getActionSender().setTab(p, 73, 93);
			p.getActionSender().setString(p, weapon, 93, 0);
			p.weaponType = 1;
		} else if (weapon.equals("Granite maul") || weapon.equals("Torags hammers")  || weapon.contains("Statius's warhammer")  || weapon.contains("Statius' warhammer (deg)") || weapon.equals("Tzhaar-ket-om")) {
			p.getActionSender().setTab(p, 73, 76);
			p.getActionSender().setString(p, weapon, 76, 0);
			p.weaponType = 2;
		} else if (weapon.equals("Verac's flail") || weapon.endsWith("mace") || weapon.equals("Barrelchest anchor")) {
			p.getActionSender().setTab(p, 73, 88);
			p.getActionSender().setString(p, weapon, 88, 0);
			p.weaponType = 3;
		} else if (weapon.endsWith("crossbow") || weapon.endsWith("c'bow")) {
			p.getActionSender().setTab(p, 73, 79);
 			p.getActionSender().setString(p, weapon, 79, 0);
			p.weaponType = 4;
		} else if (weapon.contains("bow") || weapon.equals("Seercull")) {
			p.getActionSender().setTab(p, 73, 77);
			p.getActionSender().setString(p, weapon, 77, 0);
			p.weaponType = 4;
		} else if (weapon.contains("staff") || weapon.contains("Staff")) {
			p.getActionSender().setTab(p, 73, 90);
			p.getActionSender().setString(p, weapon, 90, 0);
			p.weaponType = 5;
		} else if (weapon.endsWith("dart") || weapon.endsWith("knife") || weapon.endsWith("thrownaxe")) {
			p.getActionSender().setTab(p, 73, 91);
			p.getActionSender().setString(p, weapon, 91, 0);
			p.weaponType = 6;
		} else if (weapon.contains("dagger")) {
			p.getActionSender().setTab(p, 73, 89);
			p.getActionSender().setString(p, weapon, 89, 0);
			p.weaponType = 7;
		} else if (weapon.endsWith("pickaxe")) {
			p.getActionSender().setTab(p, 73, 83);
			p.getActionSender().setString(p, weapon, 83, 0);
			p.weaponType = 7;
		} else if (weapon.endsWith("battleaxe") || weapon.contains("Dharok")) {
			p.getActionSender().setTab(p, 73, 75);
			p.getActionSender().setString(p, weapon, 75, 0);
			p.weaponType = 8;
		} else if (weapon.endsWith("halberd")) {
			p.getActionSender().setTab(p, 73, 84);
			p.getActionSender().setString(p, weapon, 84, 0);
			p.weaponType = 9;
		} else if (weapon.endsWith("spear")) {
			p.getActionSender().setTab(p, 73, 87);
			p.getActionSender().setString(p, weapon, 87, 0);
			p.weaponType = 10;
		} else if (weapon.endsWith("claws")) {
			p.getActionSender().setTab(p, 73, 78);
			p.getActionSender().setString(p, weapon, 78, 0);
			p.weaponType = 11;
		} else if (weapon.contains("2h") || weapon.contains("godsword") || weapon.equals("Saradomin sword")) {
			p.getActionSender().setTab(p, 73, 82);
			p.getActionSender().setString(p, weapon, 82, 0);
			p.weaponType = 7;
		} else if (weapon.contains("sword") || weapon.contains("scimitar")) {
			p.getActionSender().setTab(p, 73, 81);
			p.getActionSender().setString(p, weapon, 81, 0);
			p.weaponType = 12;
		}
		checkSpecials(p);
	}

	public int getRunEmote(int id) {
		String weapon = Engine.items.getItemName(id);
		if (weapon.contains("2h") || weapon.contains("godsword") || weapon.equals("Saradomin sword")) {
			return 7039;
		} else if (id == 10887) {
			return 5868;
		} else if (id == 4755) {
			return 1831;
		} else if (id == 4734) {
			return 2077;
		} else if ((weapon.contains("crozier") || weapon.contains("spear") || weapon.contains("staff") || weapon.contains("Staff") || weapon.contains("halberd") || weapon.contains("wand")) && id != 2415 && id != 2416 && id != 2417) {
			return 1210;
		} else if (weapon.equals("Abyssal whip")) {
			return 1661;
                } else if (weapon.equals("Gnomecopter")) {
			return 2939;
		} else if (id == 4153) {
			return 1664;
		}
		return 0x338;
	}

	public int getWalkEmote(int id) {
		String weapon = Engine.items.getItemName(id);
		if (id == 10887) {
			return 5867;
		} else if (id == 4718 || id == 6528) {
			return 2064;
		} else if (id == 4755) {
			return 2060;
		} else if (id == 4734) {
			return 2076;
		} else if (id == 4153) {
			return 1663;
		} else if (weapon.equals("Abyssal whip")) {
			return 1660;
                } else if (weapon.equals("Gnomecopter")) {
			return 2939;
		} else if (weapon.contains("2h") || weapon.contains("godsword") || weapon.equals("Saradomin sword")) {
			return 7046;
		} else if ((weapon.contains("crozier") || weapon.contains("spear") || weapon.contains("Staff") || weapon.contains("staff") || weapon.contains("halberd") || weapon.contains("wand")) && id != 2415 && id != 2416 && id != 2417) {
			return 1146;
		}
		return 0x333;
	}

	public int getStandEmote(int id) {
		String weapon = Engine.items.getItemName(id);
		if (id == 4718 || id == 6528) {
			return 2065;
		} else if (id == 10887) {
			return 5869;
		} else if (id == 4755) {
			return 2061;
		} else if (id == 4734) {
			return 2074;
		} else if ((weapon.contains("crozier") || weapon.contains("spear") || weapon.contains("Staff") || weapon.contains("staff") || weapon.contains("halberd") || weapon.contains("wand") || id == 1305) && id != 2415 && id != 2416 && id != 2417) {
			return 813;
		} else if (weapon.contains("2h") || weapon.contains("godsword") || weapon.equals("Saradomin sword")) {
			return 7047;
		} else if (weapon.equals("Abyssal whip")) {
			return 10080;
                } else if (weapon.equals("Gnomecopter")) {
			return 2939;
        	} else if (weapon.equals("Master wand")) {
			return 813;
        	} else if (weapon.equals("Ancient staff")) {
			return 813;
		} else if (id == 4153) {
			return 1662;
		}
		return 0x328;
	}

	public void checkSpecials(Player p) {

		//Weapon variable

		String weapon = Engine.items.getItemName(p.equipment[3]);

		//Checking for special attack option

		if (weapon.equals("Abyssal whip")) {
			p.getActionSender().setInterfaceConfig(p, 93, 10, false);
		} else if (weapon.contains("Dragon claws")) {
			p.getActionSender().setInterfaceConfig(p, 78, 12, false);
		} else if (weapon.contains("Dragon dagger") || weapon.contains("Drag dagger")) {
			p.getActionSender().setInterfaceConfig(p, 89, 12, false);
		} else if (weapon.contains("Dragon") && (weapon.contains("scimitar") || weapon.contains("longsword")) || weapon.equals("Vesta's longsword")) {
			p.getActionSender().setInterfaceConfig(p, 81, 12, false);
		} else if (weapon.contains("Dragon 2h") || weapon.contains("godsword") || weapon.equals("Saradomin sword")) {
			p.getActionSender().setInterfaceConfig(p, 82, 12, false);
		} else if ((weapon.contains("Magic") || weapon.contains("Dark")) && weapon.contains("bow")) {
			p.getActionSender().setInterfaceConfig(p, 77, 13, false);
		} else if (weapon.equals("Dragon mace") || weapon.equals("Barrelchest anchor")) {
			p.getActionSender().setInterfaceConfig(p, 88, 12, false);
		} else if (weapon.contains("battleaxe")) {
			p.getActionSender().setInterfaceConfig(p, 75, 12, false);
		} else if (weapon.equals("Granite maul")) {
			p.getActionSender().setInterfaceConfig(p, 76, 10, false);
                } else if (weapon.equals("Statius's warhammer")) {
			p.getActionSender().setInterfaceConfig(p, 76, 10, false);
                } else if (weapon.equals("Statius' warhammer (deg)")) {
			p.getActionSender().setInterfaceConfig(p, 76, 10, false);
		} else if (weapon.equals("Dragon halberd")) {
			p.getActionSender().setInterfaceConfig(p, 84, 10, false);
		}
	}

	public void update() {

		//Turning off specials

		p.usingSpecial = false;
		p.getActionSender().setConfig(p, 301, 0);

		//Still need to fix fightStyle updating
	}
}