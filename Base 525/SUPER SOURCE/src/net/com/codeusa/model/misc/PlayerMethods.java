/*
 * @author Codeusa
 *
 * PlayerMethods.java
 *
 * Version 1.0
 *
 */

package net.com.codeusa.model.misc;

import net.com.codeusa.model.*;

public class PlayerMethods {

	Player p;

	
	/**
	 * Constructor
	 * @param p The Player class.
	 */
	public PlayerMethods(Player p) {
		this.p = p;
	}

	/**
	 * Set & get methods.
	 */
	 public void setNPCDamageDelay(int damageDelay) {
		p.damageNPCDelay = damageDelay;
	}
	public void setAttackPlayer(boolean attackingPlayer) {
		p.attackingPlayer = attackingPlayer;
	}

	public void setCombatDelay(int combatDelay) {
		p.combatDelay = combatDelay;
	}

	public void setDamageDelay(int damageDelay) {
		p.damageDelay = damageDelay;
	}

	public void specialAttack(boolean b1) {
		p.expectSpec = b1;
	}

	public void setSecondSpecDelay(int secondSpecDelay) {
		p.secondSpecDelay = secondSpecDelay;
	}

	public void setSpecDelay(int specDelay) {
		p.specDelay = specDelay;
	}

	public void setRangeDamageDelay(int rangeDmgDelay) {
		p.rangeDmgDelay = rangeDmgDelay;
	}
	public void setRangeDamageDelay2(int rangeDmgDelay2) {
		p.rangeDmgDelay2 = rangeDmgDelay2;
	}



	public void setAgilityDelay(int agilityDelay) {
		p.agilityDelay = agilityDelay;
	}

	public void setAgilityPerforming(boolean agilityPerforming) {
		p.agilityPerforming = agilityPerforming;
	}

	public void setAgilityType(int agilityType) {
		p.agilityType = agilityType;
	}

}