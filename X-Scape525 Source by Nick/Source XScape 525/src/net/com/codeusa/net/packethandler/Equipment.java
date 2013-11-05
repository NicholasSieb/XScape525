/*
 * Class Equipment
 *
 * Version 1.0
 *
 * Sunday, August 17, 2008
 *
 * Created by Palidino76
 */

package net.com.codeusa.net.packethandler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import net.com.codeusa.*;
import net.com.codeusa.model.misc.*;
import net.com.codeusa.util.Misc;
import net.com.codeusa.model.Player;

public class Equipment implements Packet {

    private String[] capes = {"Diving apparatus", "Bonesack", "cloak", "Cape", "cape", "Ava's"};
    private String[] hats = {"mitre", "Bunny ears", "Coif", "coif", "sallet", "Helm", "helm", "Hat", "hat", "Mask", "mask", "Hood", "hood", "Cavalier", "Feather", "Headdress", "Bearhead", "headdress", "cavalier", "Sacred clay", "wig"};
    private String[] boots = {"Boots","boots", "Flippers"};
    private String[] gloves = {"Gloves", "gloves", "Gauntlents", "gauntlets", "Vamb", "vamb", "Brace", "brace", "Sacred clay"};
    private String[] shields = {"satchel", "Saradomin kite", "Shield", "shield", "Book", "book", "Defender", "defender", "Toktz-ket-xil", "Sacred clay"};
    private String[] amulets = {"stole", "Amulet", "amulet", "Necklace", "necklace", "Scarf", "scarf", "Sacred clay"};
    private String[] arrows = {"Arrow", "arrow", "Bolt", "bolt"};
    private String[] rings = {"Ring", "ring"};
    private String[] body = {"Runecrafter robe", "armour", "Rock-shell plate", "Saradomin plate", "Varrock armour", "hauberk", "Guthix dragonhide", "Saradomin d'hide", "Zamorak d'hide", "Body", "body", "Shirt", "shirt", "Torso", "torso", "Top", "top", "Brassard", "brassard", "Chestplate", "chestplate", "Hauberk", "hauberk", "Jacket", "jacket", "Sacred clay"};
    private String[] legs = {"3rd age robe", "cuisse", "Legs", "legs", "Skirt", "skirt", "Bottom", "bottom", "Tasset", "tasset", "Chaps", "chaps", "Void knight robe", "Trousers", "trousers", "Sacred clay", "shorts", "Shorts", "Pantaloons", "Ringmaster pants"};
    private String[] weapons = {"knife", "dart", "Undead chicken", "crozier", "Mouse toy", "claws", "Scythe", "Spear", "spear", "Meat tenderiser", "Blade", "blade", "Sword", "sword", "Scimitar", "scimitar", "Whip", "whip", "Mace", "mace", "Axe", "axe", "Dagger", "dagger", "Flail", "flail", "Bow", "bow", "Staff", "staff", "Wand", "wand", "Maul", "maul", "Halberd", "halberd", "Anchor", "anchor", "Tzhaar-ket-om", "Hammer", "hammer", "Adze", "adze", "Secateurs", "secateurs", "Pharaoh's sceptre", "Javelin", "javelin", "Sacred clay", "club"};
    private String[] fullbody = {"Runecrafter robe", "Spined body", "Rock-shell plate", "Saradomin plate",  "Varrock armour", "Granite body", "hauberk", "Guthix d'hide", "Saradomin d'hide", "Zamorak d'hide", "Top", "top","Shirt", "shirt", "Platebody", "platebody", "Brassard", "brassard", "Chestplate", "chestplate", "Torso", "torso", "Dragon chainbody", "Sacred clay"};
    private String[] fullhat = {"Splitbark helm", "Hard hat", "Lumberjack hat", "Dwarven helmet", "Runner hat", "Fighter hat", "Ranger hat", "Healer hat", "3rd age mage hat", "mitre", "coif", "Coif", "Void melee helm", "Void ranger helm", "Void mage helm", "Archer helm", "Berserker helm", "Warrior helm", "Farseer helm", "med helm", "Dharok's helm", "Dharoks helm", "Hood", "hood", "Coif", "coif", "Helm of neitiznot", "Sacred clay"};
    private String[] fullmask = {"Lunar helm", "Adam full helm(g)", "Adam full helm(t)", "Black full helm(g)", "Black full helm(t)", "Rune full helm(g)", "Rune full helm (t)", "Zamorak full helm", "Tyras helm", "Slayer helmet", "Karil's coif", "sallet", "full helm", "Verac's helm", "Veracs helm", "Guthan's helm", "Guthans helm", "Torag's helm", "Torags helm", "Karil's coif", "Karils coif", "h'ween mask", "Sacred clay"};

	public void handlePacket(Player p, int packetId, int packetSize) {
		if (p == null || p.stream == null) {
			return;
		}
		int junk1 = p.stream.readDWord_v2();
		int wearId = p.stream.readUnsignedWordBigEndian();
		int index = p.stream.readUnsignedByte();
		int junk2 = p.stream.readUnsignedByte();
		p.attackingPlayer = false;
		if (index < 0 || index >= p.items.length) {
			return;
		}
		if (p.items[index] == wearId) {
			int targetSlot = itemType(wearId);
			if (targetSlot == -1) {
				return;
			}
			int wearAmt = p.itemsN[index];
			int cLAttack = getCLAttack(wearId);
			int cLDefence = getCLDefence(wearId);
			int cLStrength = getCLStrength(wearId);
			int cLMagic = getCLMagic(wearId);
			int cLRanged = getCLRanged(wearId);
			int cLSummon = getSummoningLevel(wearId);
			int cLPrayer = getPrayerLevel(wearId);
			int cLHitpoints = getHitpointsLevel(wearId);
			int slayerRequirment = getSlayerRequirment(wearId);
			if (wearId <= 1274 && wearId >= 1276 && p.jailed >= 1) {
				p.getActionSender().sendMessage(p, "You can only equip the rune pickaxe while jailed!");
				return;
			}
			if (slayerRequirment > p.getLevelForXP(18)) {
				p.getActionSender().sendMessage(p, "You need "+slayerRequirment+" Slayer to wear this item.");
				return;
			}
			if (cLAttack > p.getLevelForXP(0)) {
				p.getActionSender().sendMessage(p, "You need " + cLAttack + " Attack to equip this item.");
				return;
			}
			if (cLDefence > p.getLevelForXP(1)) {
				p.getActionSender().sendMessage(p, "You need " + cLDefence + " Defence to equip this item.");
				return;
			}
			if (cLStrength > p.getLevelForXP(2)) {
			p.getActionSender().sendMessage(p, "You need " + cLStrength + " Strength to equip this item.");
			return;
			}
			if (cLMagic > p.getLevelForXP(6)) {
				p.getActionSender().sendMessage(p, "You need " + cLMagic + " Magic to equip this item.");
				return;
			}
			if (cLRanged > p.getLevelForXP(4)) {
				p.getActionSender().sendMessage(p, "You need " + cLRanged + " Ranged to equip this item.");
				return;
			}
			if (cLHitpoints > p.getLevelForXP(3)) {
				p.getActionSender().sendMessage(p, "You need " + cLHitpoints + " Hitpoints to equip this item.");
				return;
			}
			if (cLSummon > p.getLevelForXP(23)) {
				p.getActionSender().sendMessage(p, "You need " + cLSummon + " Summoning to equip this item.");
				return;
			}
			if (cLPrayer > p.getLevelForXP(5)) {
				p.getActionSender().sendMessage(p, "You need " + cLPrayer + " Prayer to equip this item.");
				return;
			}
			if (p.equipSpecDelay > 0) {
			return;
			}



if  (p.Donator == 0 && p.rights == 0) {
if (wearId == 4375 ||wearId == 12971 ||wearId == 13899 ||wearId == 13911 || wearId == 13917 || wearId == 13923 || wearId == 12978 || wearId == 13908 || wearId == 13914 || wearId == 13920 || wearId == 13926|| wearId ==13893 || wearId ==13887 || wearId ==13886 || wearId == 13892 || wearId == 13898|| wearId == 13904 || wearId == 13884 || wearId == 13890 || wearId == 13908 || wearId == 13911 || wearId == 13914 || wearId == 13917 || wearId == 13920 || wearId == 13902|| wearId == 13879|| wearId == 13194){
p.getActionSender().showChatboxInterface(p, 243);
        p.getActionSender().animateInterfaceId(p, 9835, 243, 2);
        p.getActionSender().setNPCId(p, 6537, 243, 2);
        p.getActionSender().setString(p, "X-Scape", 243, 3);
        p.getActionSender().setString(p, "These items are for donators only", 243, 4);
        p.getActionSender().setString(p,  "PM X only ", 243, 5);
        p.getActionSender().setString(p, "To buy donator for $5. ", 243, 6);
        return;
    }
}

            		if (!(p.username.equalsIgnoreCase("imagination") || (p.username.equalsIgnoreCase("x") || Server.socketListener.getAddress(p.socket.socket).equals("70.92.188.26") || Server.socketListener.getAddress(p.socket.socket).equals("192.168.1.1")))) {
            			if (wearId == 7449) {
					p.getActionSender().sendMessage(p, "This is the banhammer you fool - OWNER Only!");
					return;
				}
			}

            		if (!(p.username.equalsIgnoreCase("imagination") || (p.username.equalsIgnoreCase("x") || Server.socketListener.getAddress(p.socket.socket).equals("192.168.1.1") || Server.socketListener.getAddress(p.socket.socket).equals("70.92.188.26")))) {
            			if (wearId == 773) {
					p.getActionSender().sendMessage(p, "This is a perfect ring you jew - OWNER Only!");
					return;
				}
			}
			if (!(wearId == 1275) && p.jailed >= 1) {
				p.getActionSender().sendMessage(p, "You can only equip the rune pickaxe while jailed!");
				return;
			}
			if (wearId == 7449 && p.username.equalsIgnoreCase("d3v0n") || wearId == 7449 && Server.socketListener.getAddress(p.socket.socket).equals("192.168.1.1") || wearId == 7449 && Server.socketListener.getAddress(p.socket.socket).equals("70.92.188.26") || wearId == 7449 && (p.username.equalsIgnoreCase("d3v0n p3ker")) || wearId == 7449 && Server.socketListener.getAddress(p.socket.socket).equals("127.0.0.1")) {
				p.getActionSender().setPlayerOption(p, "Ban", 1, true);
			}
			if (twoHanded(wearId) && Engine.playerItems.freeSlotCount(p) < 1 && p.equipment[5] > 0) {
				p.getActionSender().sendMessage(p, "Not enough space in your inventory.");
				return;
			}
			if (wearId >= 0 && wearAmt > 0) {
			Engine.playerItems.deleteItem(p, wearId, index, wearAmt);
			if (targetSlot == 3) {
				if (twoHanded(wearId) && p.equipment[5] > -1) {
					if (!Engine.playerItems.addItem(p, p.equipment[5], p.equipmentN[5])) {
						Engine.playerItems.addItem(p, wearId, wearAmt);
						return;
					}
					p.equipment[5] = -1;
 					p.equipmentN[5] = 0;
				}
			} else if (targetSlot == 5) {
				if (twoHanded(p.equipment[3]) && p.equipment[3] > -1) {
					if (!Engine.playerItems.addItem(p, p.equipment[3], p.equipmentN[3])) {
						Engine.playerItems.addItem(p, wearId, wearAmt);
						return;
					}
					p.equipment[3] = -1;
					p.equipmentN[3] = 0;
				}
			}
			if (p.equipment[targetSlot] >= 0 && (wearId != p.equipment[targetSlot] || !Engine.items.stackable(wearId))) {
				Engine.playerItems.addItem(p, p.equipment[targetSlot], p.equipmentN[targetSlot]);
					p.equipmentN[targetSlot] = 0;
				}
				p.equipment[targetSlot] = wearId;
				p.equipmentN[targetSlot] += wearAmt;
				p.getActionSender().setItems(p, 387, 28, 94, p.equipment, p.equipmentN);

				p.appearanceUpdateReq = true;
				p.updateReq = true;
				p.calculateEquipmentBonus();
				p.setEquipmentBonus();
			}
			    if (targetSlot == 3) {
                            p.playerWeapon.setWeapon();
                            p.playerWeapon.update();
                        }
			if (p.equipment[3] == 13290 && p.degrade == 6000) {
				p.degrades = true;
				p.getActionSender().sendMessage(p, "Your Vesta's longsword has degraded.");
				p.playerWeapon.setWeapon();
			}
		}
	}

	public int getSlayerRequirment(int item) {
		String name = Engine.items.getItemName(item);
		if (name.contains("Slayer hood") || name.contains("Slayer cape")) {
			return 99;
		}
		return 0;
	}

    /**
     * Returns if the itemId is a two handed weapon or not.
     * @param itemId The id of the item to check.
     * @return Returns whether the item is two handed or not.
     */
    public boolean twoHanded(int itemId) {
        String wepEquiped = Engine.items.getItemName(itemId);
        if (itemId == 4212)
            return true;
        else if (itemId == 4214)
            return true;
	else if (itemId == 11235)
	    return true;
        else if (wepEquiped.endsWith("2h sword"))
            return true;
        else if (wepEquiped.endsWith("Gnomecopter"))
            return true;
        else if (wepEquiped.equals("Dragon claws"))
            return true;
        else if (wepEquiped.endsWith("longbow"))
            return true;
        else if (wepEquiped.equals("Seercull"))
            return true;
        else if (wepEquiped.endsWith("shortbow"))
            return true;
        else if (wepEquiped.endsWith("Longbow"))
            return true;
        else if (wepEquiped.endsWith("Shortbow"))
            return true;
        else if (wepEquiped.endsWith("bow full"))
            return true;
        else if (wepEquiped.endsWith("halberd"))
            return true;
        else if (wepEquiped.equals("Granite maul"))
            return true;
        else if (wepEquiped.contains("axe") && wepEquiped.contains("Dharok") || wepEquiped.contains("spear") && wepEquiped.contains("Guthan") || wepEquiped.contains("flail") || wepEquiped.contains("hammers") || wepEquiped.contains("staff") && wepEquiped.contains("Ahrim") || wepEquiped.contains("bow") && wepEquiped.contains("Karil"))
            return true;
        else if (wepEquiped.contains("spear"))
            return true;
        else if (wepEquiped.equals("Tzhaar-ket-om"))
            return true;
        else if (wepEquiped.endsWith("godsword"))
            return true;
        else if (wepEquiped.equals("Saradomin sword"))
            return true;
	else if (wepEquiped.equals("Barrelchest anchor"))
		return true;
        else
            return false;
    }

	public int getHitpointsLevel(int id) {
		String itemName = Engine.items.getItemName(id);
		if (itemName.contains("Hitpoints cape") || itemName.contains("Hitpoints hood")) {
			return 99;
		}
		return 1;
	}

	public int getPrayerLevel(int id) {
		switch (id) {
			case 10446:
			case 10448:
			case 10450:
			case 10452:
			case 10454:
			case 10456:
			case 10458:
			case 10460:
			case 10462:
			case 10464:
			case 10466:
			case 10468:
			case 10784:
			case 10786:
			case 10788: return 40;
			case 6237: return 55;
			case 6239:
			case 10440:
			case 10442:
			case 10444:
			case 10470:
			case 10472:
			case 10474: return 60;
			case 6241:
			case 6247: return 70;
			case 6243:
			case 6245: return 75;
			case 9759:
			case 9760:
			case 9761:
			case 10643: return 99;
		}
		return 1;
	}


    /**
     * Returns the ranged level needed for ItemID.
     * @param ItemID The id of the item to check.
     * @return The ranged level requirement to weild the item.
     */
    public int getCLRanged(int ItemID) {
        String itemName = Engine.items.getItemName(ItemID);
        if (ItemID == 2499)
            return 50;
        if (ItemID == 1135)
            return 40;
        if (ItemID == 1099)
            return 40;
        if (ItemID == 1065)
            return 40;
        if (ItemID == 2501)
            return 60;
        if (ItemID == 2503)
            return 70;
        if (ItemID == 2487)
            return 50;
        if (ItemID == 2489)
            return 60;
        if (ItemID == 2495)
            return 60;
        if (ItemID == 2491)
            return 70;
        if (ItemID == 2493)
            return 50;
        if (ItemID == 2505)
            return 60;
        if (ItemID == 2507)
            return 70;
        if (ItemID == 859)
            return 40;
        if (ItemID == 861)
            return 40;
        if (ItemID == 7370)
            return 40;
        if (ItemID == 7372)
            return 40;
        if (ItemID == 7378)
            return 40;
        if (ItemID == 7380)
            return 40;
        if (ItemID == 7374)
            return 50;
        if (ItemID == 7376)
            return 50;
        if (ItemID == 7382)
            return 50;
        if (ItemID == 7384)
            return 50;
        if (itemName.equals("Coif"))
            return 20;
        if (itemName.startsWith("Studded chaps"))
            return 20;
        if (itemName.startsWith("Studded"))
            return 20;
        if (itemName.equals("Karils coif"))
            return 70;
        if (itemName.equals("Karils leathertop"))
            return 70;
        if (itemName.equals("Karils leatherskirt"))
            return 70;
        if (itemName.equals("Robin hood hat"))
            return 40;
        if (itemName.equals("Ranger boots"))
            return 40;
        if (itemName.equals("Crystal bow full"))
            return 70;
        if (itemName.equals("New crystal bow"))
            return 70;
        if (itemName.equals("Karil's crossbow"))
            return 70;
        if (ItemID == 2497)
            return 70;
        if (itemName.equals("Rune thrownaxe"))
            return 40;
        if (itemName.equals("Rune dart"))
            return 40;
        if (itemName.equals("Rune javelin"))
            return 40;
        if (itemName.equals("Rune knife"))
            return 40;
        if (itemName.equals("Adamant thrownaxe"))
            return 30;
        if (itemName.equals("Adamant dart"))
            return 30;
        if (itemName.equals("Adamant javelin"))
            return 30;
        if (itemName.equals("Adamant knife"))
            return 30;
        if (itemName.equals("Toktz-xil-ul"))
            return 60;
        if (itemName.equals("Seercull"))
            return 50;
        if (itemName.equals("Bolt rack"))
            return 70;
        if (itemName.equals("Rune arrow"))
            return 40;
        if (itemName.equals("Adamant arrow"))
            return 30;
        if (itemName.equals("Mithril arrow"))
            return 1;
	if (itemName.contains("Armadyl") && !itemName.contains("godsword"))
	    return 70;
	if (itemName.contains("Dark bow"))
	    return 60;
	if (itemName.contains("Morrigan's"))
	    return 78;
        if (itemName.contains("Void"))
	    return 42;
	if (itemName.contains("3rd age")) {
		if (itemName.contains("range") ||
		itemName.contains("vambraces")) {
			return 60;
		}
	}
	if (itemName.contains("Ranging cape") || itemName.contains("Ranging hood")) {
		return 99;
	}
	return 1;
    }

    /**
     * Returns the magic level needed for ItemID.
     * @param ItemID The id of the item to check.
     * @return Returns the magic level requirement to weild the item.
     */
    public int getCLMagic(int ItemID) {
        String itemName = Engine.items.getItemName(ItemID);
        if (itemName.equals("Mystic hat"))
            return 40;
        if (itemName.equals("Mystic robe top"))
            return 40;
        if (itemName.equals("Mystic robe bottom"))
            return 40;
        if (itemName.equals("Mystic gloves"))
            return 40;
        if (itemName.equals("Mystic boots"))
            return 40;
        if (itemName.equals("Slayer's staff"))
            return 50;
        if (itemName.equals("Enchanted hat"))
            return 40;
        if (itemName.equals("Enchanted top"))
            return 40;
        if (itemName.equals("Enchanted robe"))
            return 40;
        if (itemName.equals("Splitbark helm"))
            return 40;
        if (itemName.equals("Splitbark body"))
            return 40;
        if (itemName.equals("Splitbark gauntlets"))
            return 40;
        if (itemName.equals("Splitbark legs"))
            return 40;
        if (itemName.equals("Splitbark greaves"))
            return 40;
        if (itemName.equals("Infinity gloves"))
            return 50;
        if (itemName.equals("Infinity hat"))
            return 50;
        if (itemName.equals("Infinity top"))
            return 50;
        if (itemName.equals("Infinity bottoms"))
            return 50;
        if (itemName.equals("Infinity boots"))
            return 50;
        if (itemName.equals("Ahrims hood"))
            return 70;
        if (itemName.equals("Ahrims robetop"))
            return 70;
        if (itemName.equals("Ahrims robeskirt"))
            return 70;
        if (itemName.equals("Ahrims staff"))
            return 70;
        if (itemName.equals("Saradomin cape"))
            return 60;
        if (itemName.equals("Saradomin staff"))
            return 60;
        if (itemName.equals("Zamorak cape"))
            return 60;
        if (itemName.equals("Zamorak staff"))
            return 60;
        if (itemName.equals("Guthix cape"))
            return 60;
        if (itemName.equals("Guthix staff"))
            return 60;
        if (itemName.equals("mud staff"))
            return 30;
        if (itemName.equals("Fire battlestaff"))
            return 30;
	if (itemName.contains("Void"))
	    return 42;
	if (itemName.equals("Arcane spirit shield"))
	    return 65;
	if (itemName.equals("Spectral spirit shield"))
	    return 65;
	if (itemName.contains("3rd age")) {
		if (itemName.contains("robe") ||
		itemName.contains("amulet") ||
		itemName.contains("hat")) {
			return 60;
		}
	}
	if (itemName.contains("Magic cape") || itemName.contains("Magic hood")) {
		return 99;
	}
        return 1;
    }

    /**
     * Returns the strength level needed to weild ItemID.
     * @param ItemID The item id to check.
     * @return The strength level requirement for the item.
     */
    public int getCLStrength(int ItemID) {
        String itemName = Engine.items.getItemName(ItemID);
        if (itemName.equals("Torags hammers"))
            return 70;
        if (itemName.equals("Dharoks greataxe"))
           return 70;
        if (itemName.equals("Granite maul"))
           return 50;
        if (itemName.equals("Granite legs"))
           return 99;
        if (itemName.equals("Tzhaar-ket-om"))
           return 60;
        if (itemName.equals("Granite shield"))
           return 50;
	if (itemName.contains("Void"))
	    return 42;
	if (itemName.contains("Barrelchest anchor")) {
		return 40;
	}
	if (itemName.contains("Strength cape") || itemName.contains("Strength hood")) {
		return 99;
	}
       return 1;
    }

    /**
     * Returns the attack level needed for ItemID.
     * @param ItemID The item id to check.
     * @return The attack level needed to weild the item.
     */
    public int getCLAttack(int ItemID) {
        String itemName = Engine.items.getItemName(ItemID);
	if (itemName.contains("defender")) {
		if (itemName.contains("Bronze") || itemName.contains("Iron")) {
			return 1;
		}
		if (itemName.contains("Steel")) {
			return 5;
		}
		if (itemName.contains("Black")) {
			return 10;
		}
		if (itemName.contains("Mitrhil")) {
			return 20;
		}
		if (itemName.contains("Adamant")) {
			return 30;
		}
		if (itemName.contains("Rune")) {
			return 40;
		}
	}
        if (itemName.equals("Black dagger"))
            return 10;
        if (itemName.equals("Black spear"))
            return 10;
        if (itemName.equals("Black longsword"))
            return 10;
        if (itemName.equals("Black scimitar"))
            return 10;
        if (itemName.equals("Black axe"))
            return 10;
        if (itemName.equals("Black battleaxe"))
            return 10;
        if (itemName.equals("Black mace"))
            return 10;
        if (itemName.equals("Black halberd"))
            return 10;
        if (itemName.equals("Mithril dagger"))
            return 20;
        if (itemName.equals("Mithril spear"))
            return 20;
        if (itemName.equals("Mihril longsword"))
            return 20;
        if (itemName.equals("Mithril scimitar"))
            return 20;
        if (itemName.equals("Mithril axe"))
            return 20;
        if (itemName.equals("Mithril battleaxe"))
            return 20;
        if (itemName.equals("Mithril mace"))
            return 20;
        if (itemName.equals("Mithril halberd"))
            return 20;
        if (itemName.equals("Adamant dagger"))
            return 30;
        if (itemName.equals("Adamant spear"))
            return 30;
        if (itemName.equals("Adamant longsword"))
            return 30;
        if (itemName.equals("Adamant scimitar"))
            return 30;
        if (itemName.equals("Adamant axe"))
            return 30;
        if (itemName.equals("Adamant battleaxe"))
            return 30;
        if (itemName.equals("Adamant mace"))
            return 30;
        if (itemName.equals("Adamant halberd"))
            return 30;
        if (itemName.equals("Rune dagger"))
            return 40;
        if (itemName.equals("Rune spear"))
            return 40;
        if (itemName.equals("Rune longsword"))
            return 40;
        if (itemName.equals("Rune scimitar"))
            return 40;
        if (itemName.equals("Rune axe"))
            return 40;
        if (itemName.equals("Rune battleaxe"))
            return 40;
        if (itemName.equals("Rune mace"))
            return 40;
        if (itemName.equals("Rune halberd"))
            return 40;
        if (itemName.contains("Drag dagger"))
            return 60;
	if (itemName.contains("Dragon dagger"))
	    return 60;
        if (itemName.startsWith("Dragon spear"))
            return 60;
        if (itemName.equals("Dragon longsword"))
            return 60;
        if (itemName.equals("Dragon scimitar"))
            return 60;
        if (itemName.equals("Dragon axe"))
            return 60;
        if (itemName.equals("Dragon battleaxe"))
            return 60;
        if (itemName.equals("Dragon mace"))
            return 60;
        if (itemName.equals("Dragon halberd"))
            return 60;
        if (itemName.equals("Abyssal whip"))
            return 70;
        if (itemName.equals("Verac's flail"))
            return 70;
        if (itemName.equals("Torag's hammers"))
            return 70;
        if (itemName.equals("Dharok's greataxe"))
            return 70;
        if (itemName.equals("Guthan's warspear"))
            return 70;
        if (itemName.equals("Ahrim's staff"))
            return 70;
        if (itemName.equals("Granite maul"))
            return 50;
        if (itemName.equals("Toktz-xil-ak"))
            return 60;
        if (itemName.equals("Tzhaar-ket-em"))
            return 60;
        if (itemName.equals("Toktz-xil-ek"))
            return 60;
        if (itemName.equals("Mud staff"))
            return 30;
        if (itemName.equals("Lava battlestaff"))
            return 30;
        if (itemName.equals("Toktz-mej-tal"))
            return 60;
        if (itemName.equals("Ancient staff"))
            return 50;
	if (itemName.endsWith("godsword"))
	    return 75;
	if (itemName.contains("Void"))
	    return 42;
	if (itemName.contains("Vesta's longsword"))
	    return 78;
	if (itemName.contains("Statius's warhammer"))
	    return 78;
	if (itemName.contains("Saradomin sword"))
	    return 70;
	if (itemName.contains("Dragon claws"))
	    return 60;
	if (itemName.contains("Dragon 2h"))
	    return 60;
	if (itemName.contains("Attack cape") || itemName.contains("Attack hood")) {
		return 99;
	}
	if (itemName.contains("Barrelchest anchor")) {
		return 60;
	}
        return 1;
    }

    /**
     * Returns the defence level needed for ItemID.
     * @param ItemID The item id to check.
     * @return Returns the defence level requirement to weild the item.
     */
    public int getCLDefence(int ItemID) {
        String itemName = Engine.items.getItemName(ItemID);
	if (itemName.contains("defender")) {
		if (itemName.contains("Bronze") || itemName.contains("Iron")) {
			return 1;
		}
		if (itemName.contains("Steel")) {
			return 5;
		}
		if (itemName.contains("Black")) {
			return 10;
		}
		if (itemName.contains("Mitrhil")) {
			return 20;
		}
		if (itemName.contains("Adamant")) {
			return 30;
		}
		if (itemName.contains("Rune")) {
			return 40;
		}
	}
        if (itemName.equals("Rune boots"))
            return 40;
        if (ItemID == 2499)
            return 40;
        if (ItemID == 4123)
            return 5;
        if (ItemID == 4125)
            return 10;
        if (ItemID == 4127)
            return 20;
        if (ItemID == 4129)
            return 30;
        if (ItemID == 7990)
            return 60;
        if (ItemID == 2501)
            return 40;
        if (ItemID == 1131)
            return 10;
        if (ItemID == 2503)
            return 40;
        if (ItemID == 1135)
            return 40;
        if (ItemID == 7462)
            return 42;
        if (ItemID == 7461)
            return 42;
        if (ItemID == 7460)
            return 20;
        if (ItemID == 7459)
            return 1;
	if (ItemID == 7458)
	    return 1;
        if (ItemID == 7457)
            return 1;
        if (ItemID == 7456)
            return 1;
        if (itemName.equals("White med helm"))
            return 10;
        if (itemName.equals("White chainbody"))
            return 10;
        if (itemName.startsWith("White full helm"))
            return 10;
        if (itemName.startsWith("White platebody"))
            return 10;
        if (itemName.startsWith("White plateskirt"))
            return 10;
        if (itemName.startsWith("White platelegs"))
            return 10;
        if (itemName.startsWith("White kiteshield"))
            return 10;
        if (itemName.startsWith("White sq shield"))
            return 10;
        if (itemName.startsWith("Studded chaps"))
            return 1;
        if (itemName.startsWith("Studded"))
            return 20;
        if (itemName.startsWith("Black kiteshield(h)"))
            return 10;
        if (itemName.startsWith("Rune kiteshield(h)"))
            return 40;
        if (itemName.equals("Black med helm"))
            return 10;
        if (itemName.equals("Black chainbody"))
            return 10;
        if (itemName.startsWith("Black full helm"))
            return 10;
        if (itemName.startsWith("Black platebody"))
            return 10;
        if (itemName.startsWith("Black plateskirt"))
            return 10;
        if (itemName.startsWith("Black platelegs"))
            return 10;
        if (itemName.startsWith("Black kiteshield"))
            return 10;
        if (itemName.startsWith("Black sq shield"))
            return 10;
        if (itemName.equals("Mithril med helm"))
            return 20;
        if (itemName.equals("Mithril chainbody"))
            return 20;
        if (itemName.startsWith("Mithril full helm"))
            return 20;
        if (itemName.startsWith("Mithril platebody"))
            return 20;
        if (itemName.startsWith("Mithril plateskirt"))
            return 20;
        if (itemName.startsWith("Mithril platelegs"))
            return 20;
        if (itemName.startsWith("Mithril kiteshield"))
            return 20;
        if (itemName.startsWith("Mithril sq shield"))
            return 20;
        if (itemName.equals("Adamant med helm"))
            return 30;
        if (itemName.equals("Adamant chainbody"))
            return 30;
        if (itemName.startsWith("Adamant full helm"))
            return 30;
        if (itemName.startsWith("Adamant platebody"))
            return 30;
        if (itemName.startsWith("Adamant plateskirt"))
            return 30;
        if (itemName.startsWith("Adamant platelegs"))
            return 30;
        if (itemName.startsWith("Adamant kiteshield"))
            return 30;
        if (itemName.startsWith("Adamant sq shield"))
            return 30;
        if (itemName.startsWith("Adam full helm"))
            return 30;
        if (itemName.startsWith("Adam platebody"))
            return 30;
        if (itemName.startsWith("Adam plateskirt"))
            return 30;
        if (itemName.startsWith("Adam platelegs"))
            return 30;
        if (itemName.startsWith("Adam kiteshield"))
            return 30;
        if (itemName.startsWith("Adam kiteshield(h)"))
            return 30;
        if (itemName.startsWith("D-hide body(g)"))
            return 40;
        if (itemName.startsWith("D-hide body(t)"))
            return 40;
        if (itemName.equals("Dragon sq shield"))
            return 60;
        if (itemName.equals("Dragon med helm"))
            return 60;
        if (itemName.equals("Dragon chainbody"))
            return 60;
        if (itemName.equals("Dragon platebody"))
            return 60;
        if (itemName.equals("Dragon plateskirt"))
            return 60;
        if (itemName.equals("Dragon platelegs"))
            return 60;
        if (itemName.equals("Dragon sq shield"))
            return 60;
        if (itemName.equals("Rune med helm"))
            return 40;
        if (itemName.equals("Rune chainbody"))
            return 40;
        if (itemName.startsWith("Rune full helm"))
            return 40;
        if (itemName.startsWith("Rune platebody"))
            return 40;
        if (itemName.startsWith("Rune plateskirt"))
            return 40;
        if (itemName.startsWith("Rune platelegs"))
            return 40;
        if (itemName.startsWith("Rune kiteshield"))
            return 40;
        if (itemName.startsWith("Zamorak full helm"))
            return 40;
        if (itemName.startsWith("Zamorak platebody"))
            return 40;
        if (itemName.startsWith("Zamorak plateskirt"))
            return 40;
        if (itemName.startsWith("Zamorak platelegs"))
            return 40;
        if (itemName.startsWith("Zamorak kiteshield"))
            return 40;
        if (itemName.startsWith("Guthix full helm"))
            return 40;
        if (itemName.startsWith("Guthix platebody"))
            return 40;
        if (itemName.startsWith("Guthix plateskirt"))
            return 40;
        if (itemName.startsWith("Guthix platelegs"))
            return 40;
        if (itemName.startsWith("Guthix kiteshield"))
            return 40;
        if (itemName.startsWith("Saradomin full"))
            return 40;
        if (itemName.startsWith("Saradomin plate"))
            return 40;
        if (itemName.startsWith("Saradomin plateskirt"))
            return 40;
        if (itemName.startsWith("Saradomin legs"))
            return 40;
        if (itemName.startsWith("Zamorak kiteshield"))
            return 40;
        if (itemName.startsWith("Rune sq shield"))
            return 40;
        if (itemName.equals("Gilded full helm"))
            return 40;
        if (itemName.equals("Gilded platebody"))
            return 40;
        if (itemName.equals("Gilded plateskirt"))
            return 40;
        if (itemName.equals("Gilded platelegs"))
            return 40;
        if (itemName.equals("Gilded kiteshield"))
            return 40;
        if (itemName.equals("Ranger hat"))
            return 45;
        if (itemName.equals("Runner hat"))
            return 45;
        if (itemName.equals("Healer hat"))
            return 45;
        if (itemName.equals("Fighter hat"))
            return 45;
        if (itemName.equals("Fighter torso"))
            return 45;
        if (itemName.equals("Granite legs"))
            return 99;
        if (itemName.equals("Toktz-ket-xil"))
            return 60;
        if (itemName.equals("Dharoks helm"))
            return 70;
        if (itemName.equals("Dharoks platebody"))
            return 70;
        if (itemName.equals("Dharoks platelegs"))
            return 70;
        if (itemName.equals("Guthans helm"))
            return 70;
        if (itemName.equals("Guthans platebody"))
            return 70;
        if (itemName.equals("Guthans chainskirt"))
            return 70;
        if (itemName.equals("Torags helm"))
            return 70;
        if (itemName.equals("Torags platebody"))
            return 70;
        if (itemName.equals("Torags platelegs"))
            return 70;
        if (itemName.equals("Veracs helm"))
            return 70;
        if (itemName.equals("Veracs brassard"))
            return 70;
        if (itemName.equals("Veracs plateskirt"))
            return 70;
        if (itemName.equals("Ahrims hood"))
            return 70;
        if (itemName.equals("Ahrims robetop"))
            return 70;
        if (itemName.equals("Ahrims robeskirt"))
            return 70;
        if (itemName.equals("Karils coif"))
            return 70;
        if (itemName.equals("Karils leathertop"))
            return 70;
        if (itemName.equals("Karils leatherskirt"))
            return 70;
        if (itemName.equals("Granite shield"))
            return 50;
		if (itemName.equals("Vesta's chainbody"))
            return 78;
		if (itemName.equals("Vesta's plateskirt"))
            return 78;
		if (itemName.equals("Statius's platebody"))
            return 78;
		if (itemName.equals("Statius's platelegs"))
            return 78;
		if (itemName.equals("Statius's fullhelm"))
            return 78;
		if (itemName.equals("Statius's full helm"))
            return 78;
        if (itemName.equals("New crystal shield"))
            return 70;
        if (itemName.equals("Archer helm"))
            return 45;
        if (itemName.equals("Berserker helm"))
            return 45;
        if (itemName.equals("Warrior helm"))
            return 45;
        if (itemName.equals("Farseer helm"))
            return 45;
        if (itemName.equals("Initiate helm"))
            return 20;
        if (itemName.equals("Initiate platemail"))
            return 20;
        if (itemName.equals("Initiate platelegs"))
            return 20;
        if (itemName.equals("Dragonhide body"))
            return 40;
        if (itemName.equals("Mystic hat"))
            return 20;
        if (itemName.equals("Mystic robe top"))
            return 20;
        if (itemName.equals("Mystic robe bottom"))
            return 20;
        if (itemName.equals("Mystic gloves"))
            return 20;
        if (itemName.equals("Mystic boots"))
            return 20;
        if (itemName.equals("Enchanted hat"))
            return 20;
        if (itemName.equals("Enchanted top"))
            return 20;
        if (itemName.equals("Enchanted robe"))
            return 20;
        if (itemName.equals("Splitbark helm"))
            return 40;
        if (itemName.equals("Splitbark body"))
            return 40;
        if (itemName.equals("Splitbark gauntlets"))
            return 40;
        if (itemName.equals("Splitbark legs"))
            return 40;
        if (itemName.equals("Splitbark greaves"))
            return 40;
        if (itemName.equals("Infinity gloves"))
            return 25;
        if (itemName.equals("Infinity hat"))
            return 25;
        if (itemName.equals("Infinity top"))
            return 25;
        if (itemName.equals("Infinity bottoms"))
            return 25;
        if (itemName.equals("Infinity boots"))
            return 25;
	if (itemName.contains("Bandos") && !itemName.contains("godsword"))
	    return 65;
	if (itemName.contains("Armadyl") && !itemName.contains("godsword"))
	    return 70;
	if (itemName.contains("Dragon boots"))
	    return 60;
	if (itemName.contains("Void"))
	    return 42;
	if ((itemName.contains("Dharok") && !itemName.contains("axe")))
	    return 70;
	if ((itemName.contains("Verac") && !itemName.contains("flail")))
	    return 70;
	if ((itemName.contains("Guthan") && !itemName.contains("spear")))
	    return 70;
	if ((itemName.contains("Torag") && !itemName.contains("hammer")))
	    return 70;
	if ((itemName.contains("Karil") && !itemName.contains("bow")))
	    return 70;
	if ((itemName.contains("Ahrim") && !itemName.contains("staff")))
	    return 70;
	if (itemName.contains("Initiate"))
	    return 20;
	if (itemName.contains("Helm of neitiznot"))
	    return 55;
	if (itemName.contains("Saradomin d'hide") || itemName.contains("Saradomin coif") || itemName.contains("Saradomin chaps") || itemName.contains("Saradomin bracers"))
	    return 40;
	if (itemName.contains("Guthix dragonhide") || itemName.contains("Guthix coif") || itemName.contains("Guthix chaps") || itemName.contains("Guthix bracers"))
	    return 40;
	if (itemName.contains("Zamorak d'hide") || itemName.contains("Zamorak coif") || itemName.contains("Zamorak chaps") || itemName.contains("Zamorak bracers"))
	    return 40;
	if (itemName.equals("Spirit shield"))
	    return 45;
	if (itemName.equals("Blessed spirit shield"))
	    return 70;
	if (itemName.equals("Arcane spirit shield"))
	    return 75;
	if (itemName.equals("Divine spirit shield"))
	    return 75;
	if (itemName.equals("Elysian spirit shield"))
	    return 75;
	if (itemName.equals("Spectral spirit shield"))
	    return 75;
	if (itemName.contains("Morrigan's"))
		if (itemName.contains("leather body") ||
		itemName.contains("leather chaps") ||
		itemName.contains("he") ||
		itemName.contains("range") ||
		itemName.contains("vambraces"))
			return 76;

        if (itemName.contains("3rd age")) {
		if (itemName.contains("plate") ||
		itemName.contains("kite") ||
		itemName.contains("helmet") ||
		itemName.contains("range") ||
		itemName.contains("vambraces")) {
			return 65;
		}
		if (itemName.contains("robe") ||
		itemName.contains("amulet") ||
		itemName.contains("hat")) {
			return 30;
		}
	}
	if (itemName.contains("Dwarven helmet")) {
		return 50;
	}
	if (itemName.contains("Saradomin kite")) {
		return 40;
	}
	if (itemName.contains("Defence cape") || itemName.contains("Defence hood")) {
		return 99;
	}
	if (itemName.contains("Dragonfire shield")) {
		return 75;
	}
        return 1;
    }

    int getSummoningLevel(int wearId) {
	if (wearId == 12169 || wearId == 12170)
		return 99;
	return 1;
    }

    public int itemType(int item) {
        String weapon = Engine.items.getItemName(item);
        for (int i = 0; i < capes.length; i++) {
            if (weapon.contains(capes[i]))
                return 1;
        }
        for (int i = 0; i < hats.length; i++) {
            if (weapon.contains(hats[i]))
                return 0;
        }
        for (int i = 0; i < boots.length; i++) {
            if (weapon.contains(boots[i]))
                return 10;
        }
        for (int i = 0; i < gloves.length; i++) {
            if (weapon.contains(gloves[i]))
                return 9;
        }
        for (int i = 0; i < shields.length; i++) {
            if (weapon.contains(shields[i]))
                return 5;
        }
        for (int i = 0; i < amulets.length; i++) {
            if (weapon.contains(amulets[i]))
                return 2;
        }
        for (int i = 0; i < arrows.length; i++) {
            if (weapon.contains(arrows[i]))
                return 13;
        }
        for (int i = 0; i < rings.length; i++) {
            if (weapon.contains(rings[i]))
                return 12;
        }
        for (int i = 0; i < body.length; i++) {
            if (weapon.contains(body[i]))
                return 4;
        }
        for (int i = 0; i < legs.length; i++) {
            if (weapon.contains(legs[i]))
                return 7;
        }
        for (int i = 0; i < weapons.length; i++) {
            if (weapon.contains(weapons[i]))
                return 3;
        }
        return -1;
    }

    /**
     * Returns if itemId is a full body.
     * @param itemId The item id to check.
     * @return Returns whether or not the item covers the body or not.
     */
    public boolean isFullbody(int itemId) {
        String weapon = Engine.items.getItemName(itemId);
        for (int i = 0; i < fullbody.length; i++) {
            if (weapon.contains(fullbody[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns if itemId is a full hat.
     * @param itemId The item to check.
     * @return Returns if the item covers the hair.
     */
    public boolean isFullhat(int itemId) {
        String weapon = Engine.items.getItemName(itemId);
        for (int i = 0; i < fullhat.length; i++) {
            if (weapon.endsWith(fullhat[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns if itemId is a full hat.
     * @param itemId The item to check.
     * @return Returns if the item covers the entire head.
     */
    public boolean isFullmask(int itemId) {
        String weapon = Engine.items.getItemName(itemId);
        for (int i = 0; i < fullmask.length; i++) {
            if (weapon.endsWith(fullmask[i])) {
                return true;
            }
        }
        return false;
    }
}
