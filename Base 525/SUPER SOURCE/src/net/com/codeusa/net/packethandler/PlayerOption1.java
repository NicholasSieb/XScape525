package net.com.codeusa.net.packethandler;

import net.com.codeusa.*;
import net.com.codeusa.model.misc.*;
import net.com.codeusa.model.combat.*;
import net.com.codeusa.model.games.*;
import net.com.codeusa.model.Player;
import net.com.codeusa.util.Misc;

public class PlayerOption1 implements Packet {

	public void handlePacket(Player p, int packetId, int packetSize) {
		if (p == null || p.stream == null) {
			return;
		}
		PlayerMethods playerMethods = new PlayerMethods(p);

                try {
		if (p.inDuelArena()) {
			Player player = Server.engine.players[p.duelFriend];
			if (player == null)
				return;
			if (p.inDuelArena() && player.inDuelArena()) {
				p.getDuelClass().initializeDuelAcception();
				return;
			}
		}
	} catch (Exception e) {
		e.printStackTrace();
	}

		//PvP system

		p.enemyIndex = p.stream.readUnsignedWordBigEndian();
		Player opp = Engine.players[p.enemyIndex];

		if (p == null || opp == null || p.isDead) {
			p.resetAttack();
			p.requestFaceTo(65535);
			return;
		}
		p.usingMage = false;
		playerMethods.setAttackPlayer(true);
	}
}
