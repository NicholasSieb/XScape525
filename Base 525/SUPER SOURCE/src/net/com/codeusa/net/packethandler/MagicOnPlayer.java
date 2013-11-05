package net.com.codeusa.net.packethandler;

import net.com.codeusa.*;
import net.com.codeusa.model.Player;
import net.com.codeusa.model.combat.PlayerMagic;
import net.com.codeusa.model.misc.PlayerMethods;

/*
 *
 * By Zach Noel <z_noel@hotmail.com>
 *
 */

public class MagicOnPlayer {

	Player p;

	public MagicOnPlayer(Player p) {
		this.p = p;
		PlayerMagic playerMagic = new PlayerMagic(p);
		PlayerMethods playerMethods = new PlayerMethods(p);

		p.enemyIndex = p.stream.readSignedWordBigEndian();
		int oppIndex = p.stream.readSignedWordBigEndian();
		int spellbook = p.stream.readUnsignedWord();
		Player opp = Engine.players[oppIndex];
		p.clickId = p.stream.readUnsignedWord();

		if (p.isDead || opp.isDead) {
			return;
		}
		playerMethods.setAttackPlayer(false);
		playerMagic.combatMagic(opp, spellbook, p.clickId);
	}

}