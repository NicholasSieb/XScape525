package net.com.codeusa.test;

import net.com.codeusa.*;
import net.com.codeusa.model.Player;

/**
 * @author Codeusa <Codeusa@live.coml> <codeusa.net>
 */

public class TestWorldLoader {

	Player player;

	/**
	 * Class constructor
	 */
	public TestWorldLoader(Player player) {
		this.player = player;
	}

	/**
	 * Load player features at Login.
	 */
        public void addPlayerFeatures() {
                getOwner().Welcome();
                Engine.fileManager.appendData("connection accounts/"+Server.socketListener.getAddress(getOwner().socket.socket)+".txt", getOwner().username);
                getOwner().getActionSender().sendMessage(getOwner(), "::commands, ::help. ::backup");
                getOwner().getActionSender().setOverlay(getOwner(), 209);
                getOwner().getActionSender().setString(getOwner(), "", 209, 2);
                getOwner().getActionSender().setString(getOwner(), "", 209, 3);
                getOwner().getActionSender().setString(getOwner(), "", 209, 4);
                getOwner().getActionSender().setString(getOwner(), "", 209, 5);
                personalMessages();
                getOwner().getActionSender().setItems(getOwner(), 149, 0, 93, getOwner().items, getOwner().itemsN);
                getOwner().getActionSender().setItems(getOwner(), 387, 28, 93, getOwner().equipment, getOwner().equipmentN);
if (getOwner().wildernessZone(getOwner().absX, getOwner().absY)) {
                getOwner().getActionSender().setPlayerOption(getOwner(), "Attack", 1, true);
} else if (getOwner().equipment[3] == 7449) {
                getOwner().getActionSender().setPlayerOption(getOwner(), "Ban", 1, true);
} else {
                getOwner().getActionSender().setPlayerOption(getOwner(), "Null", 1, true);
}
                getOwner().getActionSender().setPlayerOption(getOwner(), "Trade", 2, false);
                getOwner().getActionSender().setPlayerOption(getOwner(), "Follow", 3, false);
                getOwner().getActionSender().setConfig(getOwner(), 173, 0);
                getOwner().getActionSender().setConfig(getOwner(), 313, -1);
                getOwner().getActionSender().setConfig(getOwner(), 465, -1);
                getOwner().getActionSender().setConfig(getOwner(), 802, -1);
                getOwner().getActionSender().setConfig(getOwner(), 1085, 249852);
                getOwner().getActionSender().setConfig2(getOwner(),  300, getOwner().specAmount);
if (getOwner().skillLvl[3] <= 0) {
                getOwner().dropAllStuff();
}
if (getOwner().castleArea())
                getOwner().setCoords(2440, 3093, 0);
if (getOwner().godWarsDung()) {
if (getOwner().zammyChamber() || getOwner().addZamorakCheckEventGodWars())
                getOwner().getActionSender().setOverlay(getOwner(), 598);
else
                getOwner().getActionSender().setOverlay(getOwner(), 601);
}
if (getOwner().jailed == 3) {//makesl jail full even though random bh tele
                getOwner().setCoords(getOwner().absX, getOwner().absY, 2);
}
if (getOwner().jailed == 2) {
                getOwner().setCoords(getOwner().absX, getOwner().absY, 1);
}
if (getOwner().duelFight())
                getOwner().setCoords(3377, 3270, 0);
if (getOwner().rights == 2)
                getOwner().getQuestClass().setQuestColors();
                getOwner().getActionSender().addLists(getOwner());
                getOwner().getActionSender().setConfig(getOwner(), 172, 0);
                getOwner().playerWeapon.setWeapon();
                getOwner().calculateEquipmentBonus();
                getOwner().updatePlayer(true);
                setPlayerStatus(true);
}

	/**
	 * If player is online or not.
	 * @param online if player is online.
	 */
	void setPlayerStatus(boolean online) {
		getOwner().online = online;
	}

	Player getOwner() {
		return player;
	}

	public void personalMessages() {
	}

}