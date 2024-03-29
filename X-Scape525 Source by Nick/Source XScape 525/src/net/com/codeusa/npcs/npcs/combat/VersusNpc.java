package net.com.codeusa.npcs.combat;

import net.com.codeusa.*;
import net.com.codeusa.util.*;
import net.com.codeusa.npcs.*;

/**
 * @author codeusa
 */

public class VersusNpc {

	NPC n;

	/**
	 * Class constructor
	 * @param n the npc which the constructor should be created for.
	 */
	public VersusNpc(NPC n) {
		this.n = n;
	}

	/**
	 * NPC vs NPC combat system melee.
	 */
	public void appendMelee() {
		NPC n1 = Server.engine.npcs[n.npcEnemy];
		if (n == null || n1 == null) {
			resetAttack();
			return;
		}
		if (n.faceToRequest != n.npcEnemy) {
			n.requestFaceTo(n.npcEnemy);
		}
		for (int i = 0; i < n.npcMagics.length; i++) {
			if (n.npcType == n.npcMagics[i]) {
				if (Misc.getDistance(n.absX, n.absY, n1.absX, n1.absY) <= 7) {
					appendMagic();
					return;
				}
			}
		}
		if (Misc.getDistance(n.absX, n.absY, n1.absX, n1.absY) > 1) {
			return;
		}
		if (n.attackDelay == 0) {
			n.requestAnim(getCombatAnim(), 0);
			n1.appendHit(1, 0);
			setAttackDelay(getCombatDelay());
		}
	}

	/**
	 * NPC vs NPC combat system magic.
	 */
	public void appendMagic() {
		NPC n1 = Server.engine.npcs[n.npcEnemy];
		if (n == null || n1 == null) {
			resetAttack();
			return;
		}
		if (n.faceToRequest != n.npcEnemy) {
			n.requestFaceTo(n.npcEnemy);
		}
		if (Misc.getDistance(n.absX, n.absY, n1.absX, n1.absY) > 10) {
			return;
		}
		if (n.attackDelay == 0) {
			n.requestAnim(getCombatAnim(), 0);
			setDamageDelay(2);
			setAttackDelay(getCombatDelay());
		}
	}

	/**
	 * If NPC uses magic and hit delay reaches 0 this method gets activated.
	 */
	public void addDamageToNpcEvent() {
		NPC n1 = Server.engine.npcs[n.npcEnemy];
		if (n == null || n1 == null) {
			resetAttack();
			return;
		}
		if (Misc.getDistance(n.absX, n.absY, n1.absX, n1.absY) > 7) {
			return;
		}
		if (n.damageDelay == 0) {
			n1.requestGFX(getCombatGraphic(), 0);
			n.requestAnim(getCombatAnim(), 0);
			n1.appendHit(getHit(), 0);
			setDamageDelay(-1);
		}
	}

	int getHit() {
		switch (n.npcType) {

			case 2253: return 40;
			case 6257: return Misc.random(17);

			default:
				return 1;
		}
	}

	/**
	 * Resets attack of npc vs npc.
	 */
	public void resetAttack() {
		n.npcEnemy = 0;
		n.attackingNpc = false;
	}

	/**
	 * Holds values to get starting graphic of npc.
	 */
	int getCombatGraphic() {
		switch (n.npcType) {

			case 6257:
				return 76;

			case 2253:
				return 76;

			default:
				return -1;
		}
	}

	/**
	 * Holds values to get combat delay of npc.
	 */
	int getCombatDelay() {
		switch (n.npcType) {

			case 3846:
				return 8;

			case 6257:
				return 7;

			case 2253:
				return 6;

			default:
				return 7;
		}
	}

	/**
	 * Holds values and sets combat animation for the npc.
	 */
	int getCombatAnim() {
		switch (n.npcType) {

			case 6257:
				return 811;

			case 3846:
				return 3980;

			case 2253:
				return 811;

			case 158:
				return 1833;

			default:
				return 422;
		}
	}

	/**
	 * Sets the damage delay to a value
	 * @param damageDelay the delay which is going to be setted.
	 */
	void setDamageDelay(int damageDelay) {
		n.damageDelay = damageDelay;
	}

	/**
	 * sets the attack delay to a value.
	 * @param attackDelay the delay which is gonna be setted through a method.
	 */
	void setAttackDelay(int attackDelay) {
		n.attackDelay = attackDelay;
	}

}