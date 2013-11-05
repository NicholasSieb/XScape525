package net.com.codeusa.net.packethandler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import net.com.codeusa.*;
import net.com.codeusa.model.Player;
import net.com.codeusa.model.items.PlayerItems;
import net.com.codeusa.Server;
import net.com.codeusa.Engine;
import net.com.codeusa.model.combat.*;
import net.com.codeusa.util.Misc;
import net.com.codeusa.util.UserInput;
import net.com.codeusa.world.items.ItemList;

/**
 *
 * @author Codeusa <Codeusa@live.com>
 */

public class ItemOnItem implements Packet {

	/**
	 * Handles item on item packet.
	 * @param player p The player which the packet will be created for.
	 * @param packetId the packet id which is activated (Which handles this class)
	 * @param packetSize the amount of bytes being received for the packet.
	 */
	public void handlePacket(Player player, int packetId, int packetSize) {
		if (player == null)
			return;
		int usedWith = player.stream.readSignedWordBigEndian();
        	int itemUsed = player.stream.readSignedWordA();
		PlayerItems pi = new PlayerItems();

//DFS by maffchew
if(itemUsed == 11286) {
	switch(usedWith) {
	case 1540:
	if(Server.engine.playerItems.hasPlayerItemAmount(player,1540,1) &&
	Server.engine.playerItems.hasPlayerItemAmount(player,11286,1)) {
	Engine.playerItems.deleteItem(player,1540,pi.getItemSlot(player,1540),1);
	Engine.playerItems.deleteItem(player,11286,pi.getItemSlot(player,11286),1);
	pi.addItem(player,11283,1);
	}
	break;
	}
	}
        if(itemUsed == 14472 ||itemUsed == 14474 ||itemUsed == 14476) {
	switch(usedWith) {
        case 14472:
        case 14474:
        case 14476:
	if(Server.engine.playerItems.hasPlayerItemAmount(player,14472,1) &&
	Server.engine.playerItems.hasPlayerItemAmount(player,14474,1)&&
	Server.engine.playerItems.hasPlayerItemAmount(player,14476,1)) {
	Engine.playerItems.deleteItem(player,14472,pi.getItemSlot(player,14472),1);
	Engine.playerItems.deleteItem(player,14474,pi.getItemSlot(player,14474),1);
        Engine.playerItems.deleteItem(player,14476,pi.getItemSlot(player,14476),1);
	pi.addItem(player,14479,1);
	}
	break;
	}
	}
//Spirit Shields, coded by maffchew
if(itemUsed == 13754) {
	switch(usedWith) {
	case 13734:
	if(Server.engine.playerItems.hasPlayerItemAmount(player,13754,1) &&
	Server.engine.playerItems.hasPlayerItemAmount(player,13734,1)) {
	Engine.playerItems.deleteItem(player,13754,pi.getItemSlot(player,13754),1);
	Engine.playerItems.deleteItem(player,13734,pi.getItemSlot(player,13734),1);
	pi.addItem(player,13736,1);
	}
	break;
	}
	}
	if(itemUsed == 13734) {
	switch(usedWith) {
	case 13746:
	if(Server.engine.playerItems.hasPlayerItemAmount(player,13734,1) &&
	Server.engine.playerItems.hasPlayerItemAmount(player,13746,1)) {
	Engine.playerItems.deleteItem(player,13734,pi.getItemSlot(player,13734),1);
	Engine.playerItems.deleteItem(player,13746,pi.getItemSlot(player,13746),1);
	pi.addItem(player,13738,1);
	}
	break;
	case 13748:
	if(Server.engine.playerItems.hasPlayerItemAmount(player,13734,1) &&
	Server.engine.playerItems.hasPlayerItemAmount(player,13748,1)) {
	Engine.playerItems.deleteItem(player,13734,pi.getItemSlot(player,13734),1);
	Engine.playerItems.deleteItem(player,13748,pi.getItemSlot(player,13748),1);
	pi.addItem(player,13740,1);
	}
	break;
	case 13750:
	if(Server.engine.playerItems.hasPlayerItemAmount(player,13734,1) &&
	Server.engine.playerItems.hasPlayerItemAmount(player,13750,1)) {
	Engine.playerItems.deleteItem(player,13734,pi.getItemSlot(player,13734),1);
	Engine.playerItems.deleteItem(player,13750,pi.getItemSlot(player,13750),1);
	pi.addItem(player,13742,1);
	}
	break;
	case 13752:
	if(Server.engine.playerItems.hasPlayerItemAmount(player,13734,1) &&
	Server.engine.playerItems.hasPlayerItemAmount(player,13752,1)) {
	Engine.playerItems.deleteItem(player,13734,pi.getItemSlot(player,13734),1);
	Engine.playerItems.deleteItem(player,13752,pi.getItemSlot(player,13752),1);
	pi.addItem(player,13744,1);
	}
	break;
	}
	}
	/*if(itemUsed == 1755) {//armour sets by d3v0n
	switch(usedWith) {
	case 11938://gilded legs
	if(Server.engine.playerItems.hasPlayerItemAmount(player,1755,1) &&
	Server.engine.playerItems.hasPlayerItemAmount(player,11938,1)) {
	Engine.playerItems.deleteItem(player,11938,pi.getItemSlot(player,11938),1);
	pi.addItem(player,3486,1);
        pi.addItem(player,3481,1);
        pi.addItem(player,3483,1);
	pi.addItem(player,3488,1);
	}
	break;
        case 11940://gilded skirt
	if(Server.engine.playerItems.hasPlayerItemAmount(player,1755,1) &&
	Server.engine.playerItems.hasPlayerItemAmount(player,11940,1)) {
	Engine.playerItems.deleteItem(player,11940,pi.getItemSlot(player,11940),1);
	pi.addItem(player,3486,1);
        pi.addItem(player,3481,1);
        pi.addItem(player,3485,1);
	pi.addItem(player,3488,1);
	}
	break;
	case 11930://zammy legs
	if(Server.engine.playerItems.hasPlayerItemAmount(player,1755,1) &&
	Server.engine.playerItems.hasPlayerItemAmount(player,11930,1)) {
	Engine.playerItems.deleteItem(player,11930,pi.getItemSlot(player,11930),1);
	pi.addItem(player,2657,1);
        pi.addItem(player,2653,1);
        pi.addItem(player,2655,1);
	pi.addItem(player,2659,1);
	}
	break;
        case 11936://zammy skirt
	if(Server.engine.playerItems.hasPlayerItemAmount(player,1755,1) &&
	Server.engine.playerItems.hasPlayerItemAmount(player,11936,1)) {
	Engine.playerItems.deleteItem(player,11936,pi.getItemSlot(player,11936),1);
	pi.addItem(player,2657,1);
        pi.addItem(player,2653,1);
        pi.addItem(player,3478,1);
	pi.addItem(player,2659,1);
	}
	break;
        case 11928://sara legs
	if(Server.engine.playerItems.hasPlayerItemAmount(player,1755,1) &&
	Server.engine.playerItems.hasPlayerItemAmount(player,11928,1)) {
	Engine.playerItems.deleteItem(player,11928,pi.getItemSlot(player,11928),1);
	pi.addItem(player,2665,1);
        pi.addItem(player,2661,1);
        pi.addItem(player,2663,1);
	pi.addItem(player,2667,1);
	}
	break;
        case 11934://sara skirt
	if(Server.engine.playerItems.hasPlayerItemAmount(player,1755,1) &&
	Server.engine.playerItems.hasPlayerItemAmount(player,11934,1)) {
	Engine.playerItems.deleteItem(player,11934,pi.getItemSlot(player,11934),1);
	pi.addItem(player,2665,1);
        pi.addItem(player,3479,1);
        pi.addItem(player,2661,1);
	pi.addItem(player,2667,1);
	}
	break;
        case 11926://guthix legs
	if(Server.engine.playerItems.hasPlayerItemAmount(player,1755,1) &&
	Server.engine.playerItems.hasPlayerItemAmount(player,11926,1)) {
	Engine.playerItems.deleteItem(player,11926,pi.getItemSlot(player,11926),1);
	pi.addItem(player,2673,1);
        pi.addItem(player,2671,1);
        pi.addItem(player,2669,1);
	pi.addItem(player,2675,1);
	}
	break;
        case 11932://guthix skirt
	if(Server.engine.playerItems.hasPlayerItemAmount(player,1755,1) &&
	Server.engine.playerItems.hasPlayerItemAmount(player,11932,1)) {
	Engine.playerItems.deleteItem(player,11932,pi.getItemSlot(player,11932),1);
	pi.addItem(player,2673,1);
        pi.addItem(player,3480,1);
        pi.addItem(player,2669,1);
	pi.addItem(player,2675,1);
	}
	break;
        case 11898://rune g legs
	if(Server.engine.playerItems.hasPlayerItemAmount(player,1755,1) &&
	Server.engine.playerItems.hasPlayerItemAmount(player,11898,1)) {
	Engine.playerItems.deleteItem(player,11898,pi.getItemSlot(player,11898),1);
	pi.addItem(player,2619,1);
        pi.addItem(player,2615,1);
        pi.addItem(player,2617,1);
	pi.addItem(player,2621,1);
	}
	break;
        case 11900://rune g skirt
	if(Server.engine.playerItems.hasPlayerItemAmount(player,1755,1) &&
	Server.engine.playerItems.hasPlayerItemAmount(player,11900,1)) {
	Engine.playerItems.deleteItem(player,11900,pi.getItemSlot(player,11900),1);
	pi.addItem(player,2619,1);
        pi.addItem(player,2615,1);
        pi.addItem(player,3476,1);
	pi.addItem(player,2629,1);
	}
	break;
        case 11882://black g legs
	if(Server.engine.playerItems.hasPlayerItemAmount(player,1755,1) &&
	Server.engine.playerItems.hasPlayerItemAmount(player,11882,1)) {
	Engine.playerItems.deleteItem(player,11882,pi.getItemSlot(player,11882),1);
	pi.addItem(player,2595,1);
        pi.addItem(player,2591,1);
        pi.addItem(player,2593,1);
	pi.addItem(player,2597,1);
	}
	break;
        case 11884://black g skirt
	if(Server.engine.playerItems.hasPlayerItemAmount(player,1755,1) &&
	Server.engine.playerItems.hasPlayerItemAmount(player,11884,1)) {
	Engine.playerItems.deleteItem(player,11884,pi.getItemSlot(player,11884),1);
	pi.addItem(player,2595,1);
        pi.addItem(player,2591,1);
        pi.addItem(player,3473,1);
	pi.addItem(player,2597,1);
	}
	break;
        case 11878://black t legs
	if(Server.engine.playerItems.hasPlayerItemAmount(player,1755,1) &&
	Server.engine.playerItems.hasPlayerItemAmount(player,11878,1)) {
	Engine.playerItems.deleteItem(player,11878,pi.getItemSlot(player,11878),1);
	pi.addItem(player,2587,1);
        pi.addItem(player,2583,1);
        pi.addItem(player,2585,1);
	pi.addItem(player,2589,1);
	}
	break;
        case 11880://black t skirt
	if(Server.engine.playerItems.hasPlayerItemAmount(player,1755,1) &&
	Server.engine.playerItems.hasPlayerItemAmount(player,11880,1)) {
	Engine.playerItems.deleteItem(player,11880,pi.getItemSlot(player,11880),1);
	pi.addItem(player,2587,1);
        pi.addItem(player,2583,1);
        pi.addItem(player,3472,1);
	pi.addItem(player,2589,1);
	}
	break;
        case 11876://splitbark
	if(Server.engine.playerItems.hasPlayerItemAmount(player,1755,1) &&
	Server.engine.playerItems.hasPlayerItemAmount(player,11876,1)) {
	Engine.playerItems.deleteItem(player,11876,pi.getItemSlot(player,11876),1);
	pi.addItem(player,3385,1);
        pi.addItem(player,3387,1);
        pi.addItem(player,3393,1);
	pi.addItem(player,3391,1);
        pi.addItem(player,3389,1);
	}
	break;
        case 11838://rune legs
	if(Server.engine.playerItems.hasPlayerItemAmount(player,1755,1) &&
	Server.engine.playerItems.hasPlayerItemAmount(player,11838,1)) {
	Engine.playerItems.deleteItem(player,11838,pi.getItemSlot(player,11838),1);
	pi.addItem(player,1163,1);
        pi.addItem(player,1127,1);
        pi.addItem(player,1079,1);
	pi.addItem(player,1201,1);
	}
	break;
        case 11840://rune legs
	if(Server.engine.playerItems.hasPlayerItemAmount(player,1755,1) &&
	Server.engine.playerItems.hasPlayerItemAmount(player,11840,1)) {
	Engine.playerItems.deleteItem(player,11840,pi.getItemSlot(player,11840),1);
	pi.addItem(player,1163,1);
        pi.addItem(player,1127,1);
        pi.addItem(player,1093,1);
	pi.addItem(player,1201,1);
	}
	break;
        case 11842://dragon legs
	if(Server.engine.playerItems.hasPlayerItemAmount(player,1755,1) &&
	Server.engine.playerItems.hasPlayerItemAmount(player,11842,1)) {
	Engine.playerItems.deleteItem(player,11842,pi.getItemSlot(player,11842),1);
	pi.addItem(player,1149,1);
        pi.addItem(player,3140,1);
        pi.addItem(player,4087,1);
	pi.addItem(player,1187,1);
	}
	break;
        case 11844://dragon skrit
	if(Server.engine.playerItems.hasPlayerItemAmount(player,1755,1) &&
	Server.engine.playerItems.hasPlayerItemAmount(player,11844,1)) {
	Engine.playerItems.deleteItem(player,11844,pi.getItemSlot(player,11844),1);
	pi.addItem(player,1149,1);
        pi.addItem(player,3140,1);
        pi.addItem(player,4585,1);
	pi.addItem(player,1187,1);
	}
	break;
        }
	}*/
                System.out.println("used with: "+usedWith+" itemUsed: "+itemUsed);
	}

}

