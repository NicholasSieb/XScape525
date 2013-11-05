/*
 * Class Player
 *
 * Version 1.3
 *
 * Thursday, August 24, 2009
 *
 * Created by Palidino76  --> Codeusa --> Maffchew
 */

package net.com.codeusa.model;

import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.util.*;
import net.com.codeusa.test.TestWorldLoader;
import net.com.codeusa.model.misc.*;
import net.com.codeusa.model.games.*;
import net.com.codeusa.model.skills.*;
import net.com.codeusa.util.UserInput;
import net.com.codeusa.model.combat.*;
import net.com.codeusa.model.quest.*;
import net.com.codeusa.model.games.DuelArena;
import net.com.codeusa.*;
import net.com.codeusa.io.*;
import net.com.codeusa.model.combat.PlayerMagic;
import net.com.codeusa.model.games.BountyHunter;
import net.com.codeusa.net.PlayerSocket;
import net.com.codeusa.util.ByteVector;
import net.com.codeusa.util.Misc;
import net.com.codeusa.npcs.NPC;
import net.com.codeusa.model.items.*;
import net.com.codeusa.model.items.PlayerWeapon;
import net.com.codeusa.model.ptrade.PTrade;
import java.net.URI;
import net.com.codeusa.model.GE.GrandExchange;

public class Player implements Entity {

     public UserInput input = new UserInput(this);
	 
	 /*Mob damage*/
	public boolean fightingCorp = false;
	public int mobdamage = 0;
	 
    public boolean inLowBounty = false;
    public boolean inMedBounty = false;
    public boolean inHighBounty =
            false;
    public int bountyMaxPlayers = 100;
    public boolean bountyInterface = false;
	public int BhTimer;
    public int leftBhTimer = 0;
	public boolean swapAsNote = false;
	public boolean DFSSpecial;
	public long lastDFS;
	public int DFSDelay = -1;
    public int bhPickup;
    public int bhLeave;
    public int systemupdate;
    public int bhTarget = 0;
    public int switchSpells = 0;
    public int bhLeave1;
    public int stunned;
    public boolean inBounty = false;
	public boolean isInFunPk = false;
    public int serverMsg;
    public int pickedUp;
	public int cantpray;
		public int gameTimeSeconds = 0;
	public int gameTimeMinutes = 0;
	public int gameTimeHours = 0;
	public int gameTimeDays = 0;
public int FamiliarID = 0;

    public Player giveTo2;

    
    
     /* Degradable Armours and weapons*/
     public int statHammer;
     public int statHelm;
     public int statBody;
     public int statLegs;
     public int vestaLong;
	public int bhTime1 = 0;
public int bhTime2 = 0;

     /*ge variable*/
    public int currentSlot = 0;
	public int InFunPk = 0;


	public boolean wildWarned = false;

    public boolean splitChat = true;

    /*Miasmic Spell timer*/
    public int miasmicSpell;

	public boolean initialAttack;
	public boolean boltSpecial;
	public String nameSet;
	public int setDrop;

    public boolean onQuestTab = true;

    public List<Long> friends = new ArrayList<Long>(200);
    public List<Long> ignores = new ArrayList<Long>(100);

    public int InBounty = 0;

    public int allpvp = 0;

	public int KC;
	public int DC;

        public boolean updatez = false;
    public int proupdate = 0;
    public int updateTime = 0;

	public final String giveTag = "ServChat";

	public String chatTag2;
	public final String chatTag = "ServChat";
        public String clanRoom = "";
	public String chatName;
	public final String ACTIVE_CHAT_DEFAULT = "Server Chat";
	public String activeChat = ACTIVE_CHAT_DEFAULT;
	public final String ACTIVE_CHAT_OWNER_DEFAULT = "Public Channel";
	public String activeChatOwner = ACTIVE_CHAT_OWNER_DEFAULT;

	String[][] itemPrices = {{"Warrior helm", "536"}, {"Purple sweets", "12"}, {"Statius's warhammer", "620000"}, {"Dragon med helm", "605"}, {"Statius's platelegs", "500000"}, {"Statius's full helm", "200000"}, {"Statius's platebody", "550000"},  {"Black dragonhide", "150"}, {"Dragon med helm", "605"}, {"Vesta's longsword", "620000"}, {"Dragon bones", "30"}, {"Enchanted robe", "495"}, {"Dragonfire shield", "212000"}, {"Mystic robe top", "720"}, {"Enchanted top", "741"}, {"Enchanted hat", "89"}, {"Adamant knife", "2"}, {"Adamant knife(p)", "4"}, {"Draconic visage", "217000"}, {"Mystic hat", "92"},{"Mystic robe bottom", "480"}, {"Mystic boots", "59"}, {"Mystic gloves", "59"}, {"Adamant knife(p+)", "5"}, {"Adamant knife(p++)", "13"}, {"Adamant dart", "1"}, {"Adamant dart(p)", "3"}, {"Adamant dart(p+)", "4"}, {"Adamant dart(p++)", "12"}, {"Tzhaar-ket-em", "891"}, {"Crystal bow full", "9000"}, {"Crystal shield full", "9000"}, {"Rune chainbody", "315"}, {"Zamorakian spear", "14395"}, {"Saradomin staff", "800"}, {"Guthix staff", "800"}, {"Zamorak staff", "800"}, {"Wizard boots", "3319"}, {"Regen bracelet", "2570"}, {"Combat bracelet", "137"}, {"Combat bracelet(1)", "137"}, {"Combat bracelet(2)", "137"}, {"Combat bracelet(3)", "137"}, {"Combat bracelet(4)", "137"}, {"Splitbark body", "271"}, {"Splitbark boots", "28"}, {"Splitbark gauntlets", "28"}, {"Splitback helm", "59"}, {"Splitback legs", "240"}, {"Dwarven helmet", "358"}, {"Dragon med helm", "885"}, {"Dragon sq shield", "5630"}, {"Dragon dagger", "292"}, {"Dragon dagger(p)", "305"}, {"Dragon dagger(p+)", "312"}, {"Dragon spear", "451"}, {"Dragon spear(p)", "460"}, {"Dragon spear(p+)", "465"}, {"Dragon spear(p++)", "506"}, {"Dragon longsword", "956"}, {"Dragon battleaxe", "1200"}, {"Dragon mace", "298"}, {"Dragon chainbody", "50565"}, {"Dragon halberd", "1948"}, {"Dragon platelegs", "6323"}, {"Dragon plateskirt", "1979"}, {"Dragon platebody", "99230"}, {"Dragon axe", "16880"}, {"Dragon 2h sword", "8921"}, {"Dragon arrow(p)", "31"}, {"Dragon arrow(p+)", "32"}, {"Dragon arrow(p++)", "40"}, {"Dragon fire arrows", "26"}, {"Dragon full helm", "125349"}, {"Dragon dart", "6"}, {"Dragon dart(p)", "8"}, {"Dragon dart(p+)", "9"}, {"Dragon dart(p++)", "17"}, {"Rune arrow(p)", "4"}, {"Rune arrow(p+)", "5"}, {"Rune arrow(p++)", "13"}, {"Rune fire arrows", "2"}, {"Rune c'bow", "230"}, {"Rune dart", "2"}, {"Rune dart(p)", "4"}, {"Rune dart(p+)", "5"}, {"Rune dart(p++)", "13"}, {"Rune fire arrows", "2"}, {"Rune full helm (t)", "3160"}, {"Rune full helm(g)", "4792"}, {"Rune kiteshield (g)", "4307"}, {"Rune kiteshield (t)", "2838"}, {"Rune platebody (g)", "6296"}, {"Rune platebody (t)", "3995"}, {"Rune platelegs (g)", "6296"}, {"Rune platelegs (t)", "2076"}, {"Rune plateskirt (g)", "639"}, {"Rune plateskirt (t)", "569"}, {"Rune full helm", "211"}, {"Rune boots", "460"}, {"Rune kiteshield", "499"}, {"Rune med helm", "114"}, {"Rune sq shield", "230"}, {"Rune thrownaxe", "3"}, {"Gilded kiteshield", "4460"}, {"Guthix full helm", "38888"}, {"Guthix kiteshield", "3763"}, {"Guthix platebody", "6185"}, {"Guthix platelegs", "2935"}, {"Guthix plateskirt", "577"}, {"Saradomin full", "8430"}, {"Saradomin kite", "8776"}, {"Saradomin plate", "16213"}, {"Zamorak full helm", "5814"}, {"Zamorak kiteshield", "6221"}, {"Zamorak platebody", "11047"}, {"Adam full helm(g)", "1121"}, {"Adam full helm(t)", "601"}, {"Adam kiteshield (g)", "1159"}, {"Adam kiteshield (t)", "414"}, {"Adam platebody (g)", "2009"}, {"Adam platebody (t)", "908"}, {"Adam platelegs (g)", "792"}, {"Adam plateskirt (t)", "152"}, {"Adamant arrow", "1"}, {"Adamant arrow(p)", "3"}, {"Adamant arrow(p+)", "4"}, {"Adamant arrow(p++)", "12"}, {"Adamant fire arrows", "9"}, {"Adamant javelin", "1"}, {"Adamant javelin(p)", "3"}, {"Adamant javelin(p+)", "4"}, {"Adamant javelin(p++)", "12"}, {"Adamant chainbody", "27"}, {"Adamant boots", "20"}, {"Adamant full helm", "23"}, {"Adamant kiteshield", "31"}, {"Adamant platebody", "98"}, {"Adamant platelegs", "37"}, {"Adamant plateskirt", "37"}, {"Adamant med helm", "12"}, {"Adamant thrownaxe", "1"}, {"Adamant spear", "10"}, {"Adamant spear(p)", "20"}, {"Adamant spear(p+)", "25"}, {"Adamant spear(p++)", "66"}, {"Spined boots", "36"}, {"Spined gloves", "17"}, {"Spined body", "45"}, {"Spined chaps", "21"}, {"Spined helm", "358"}, {"Rock-shell boots", "11"}, {"Rock-shell gloves", "28"}, {"Rock-shell helm", "209"}, {"Rock-shell legs", "486"}, {"Rock-shell plate", "397"}, {"Skeletal boots", "194"}, {"Skeletal bottoms", "238"}, {"Skeletal gloves", "432"}, {"Skeletal helm", "58"}, {"Skeletal top", "267"}, {"Warrior ring", "1163"}, {"Toktz-xil-ul", "3"}, {"Toktz-ket-xil", "2108"}, {"Toktz-mej-tal", "1092"}, {"Toktz-xil-ak", "1231"}, {"Toktz-xil-ek", "739"}, {"Toktz-ket-em", "935"}, {"Obsidian cape", "1864"}, {"Granite body", "490"}, {"Granite helm", "274"}, {"Granite legs", "1999"}, {"Granite shield", "348"}, {"Amulet of defence", "7"}, {"Amulet of glory", "386"}, {"Amulet of glory(1)", "386"}, {"Amulet of glory(2)", "386"}, {"Amulet of glory(3)", "386"}, {"Amulet of glory(4)", "386"}, {"Amulet of glory(t)", "2090"}, {"Amulet of glory(t1)", "2090"}, {"Amulet of glory(t2)", "2090"}, {"Amulet of glory(t3)", "2090"}, {"Amulet of glory(t4)", "2090"}, {"Amulet glory(t2)", "2090"}, {"Amulet glory(t3)", "2090"}, {"Amulet glory(t4)", "2090"}, {"Amulet of magic", "5"}, {"Amulet of magic(t)", "1042"}, {"Amulet of power", "31"}, {"3rd age amulet", "561623"}, {"Strength amulet(t)", "5439"}, {"Antipoison(1)", "1"}, {"Antipoison(2)", "3"}, {"Antipoison(3)", "4"}, {"Antipoison(4)", "5"}, {"Antipoison+(1)", "23"}, {"Antipoison+(2)", "45"}, {"Antipoison+(3)", "68"}, {"Antipoison+(4)", "90"}, {"Antipoison++(1)", "37"}, {"Antipoison++(2)", "74"}, {"Antipoison++(3)", "110"}, {"Antipoison++(4)", "147"}, {"Super attack(1)", "2"}, {"Super attack(2)", "4"}, {"Super attack(3)", "5"}, {"Super strength(1)", "10"}, {"Super strength(2)", "20"}, {"Super strength(3)", "30"}, {"Super defence(1)", "2"}, {"Super defence(2)", "4"}, {"Super defence(3)", "5"}, {"Saradomin brew(1)", "7"}, {"Saradomin brew(2)", "15"}, {"Saradomin brew(3)", "22"}, {"Zamorak brew(1)", "12"}, {"Zamorak brew(2)", "24"}, {"Zamorak brew(3)", "36"}, {"Super restore(1)", "36"}, {"Super restore(2)", "72"}, {"Super restore(3)", "108"}, {"Super antipoison(1)", "3"}, {"Super antipoison(2)", "6"}, {"Super antipoison(3)", "9"}, {"Super antipoison(4)", "11"}, {"Prayer potion(1)", "21"}, {"Prayer potion(2)", "42"}, {"Prayer potion(3)", "64"}, {"3rd age mage hat", "406772"}, {"3rd age robe", "422061"}, {"3rd age robe top", "644626"}, {"3rd age full helmet", "559838"}, {"3rd age kiteshield", "848461"}, {"3rd age platebody", "849293"}, {"3rd age platelegs", "757609"}, {"3rd age range coif", "282814"}, {"3rd age range legs", "427202"}, {"3rd age range top", "501644"}, {"3rd age vambraces", "210575"}, {"Berserker helm", "773"}, {"Farseer helm", "536"}, {"Teacher wand", "432"}, {"Initiate cuisse", "47"}, {"Initiate hauberk", "59"}, {"Initiate sallet", "34"}, {"Proselyte cuisse", "61"}, {"Proselyte hauberk", "75"}, {"Proselyte sallet", "46"}, {"Proselyte tasset", "61"}, {"Bandos boots", "8567"}, {"Armadyl chestplate", "110821"}, {"Armadyl helmet", "48086"}, {"Armadyl plateskirt", "119685"}, {"Saradomin d'hide", "9908"}, {"Saradomin chaps", "1005"}, {"Saradomin bracers", "4112"}, {"Saradomin coif", "1172"}, {"Guthix chaps", "279"}, {"Guthix coif", "1044"}, {"Guthix dragonhide", "949"}, {"Guthix bracers", "138"}, {"Guthix cloak", "6229"}, {"Guthix crozier", "29"}, {"Guthix mitre", "972"}, {"Guthix mjolnir", "60"}, {"Guthix robe legs", "993"}, {"Guthix robe top", "1095"}, {"Guthix stole", "144"}, {"Zamorak cloak", "10332"}, {"Zamorak crozier", "54"}, {"Zamorak mitre", "1382"}, {"Zamorak mjolnir", "91"}, {"Zamorak robe legs", "1626"}, {"Zamorak robe top", "1967"}, {"Zamorak stole", "864"}, {"Saradomin cloak", "9206"}, {"Saradomin crozier", "51"}, {"Saradomin mitre", "1634"}, {"Saradomin mjolnir", "113"}, {"Saradomin robe legs", "1071"}, {"Saradomin robe top", "1242"}, {"Saradomin stole", "650"}, {"Bunny ears", "170376"}, {"Easter egg", "46282"}, {"Pumpkin", "53056"}, {"Rune knife(p)", "7"}, {"Rune knife(p+)", "8"}, {"Rune knife(p++)", "16"}, {"Blessed spirit shield", "41387"}, {"Holy elixir", "60000"}, {"Spirit shield", "626"}, {"Vesta's longsword", "63180"}, {"Armadyl godsword", "742520"},{"Dragon claws", "990000"}, {"Bandos godsword", "255000"}, {"Zamorak godsword", "368000"}, {"Saradomin godsword", "476000"}, {"Saradomin sword", "89000"}, {"Abyssal whip", "27900"}, {"Dragon dagger(p++)", "400"}, {"Granite maul", "500"}, {"Dragon boots", "3500"}, {"Dragon scimitar", "1000"}, {"Rune platelegs", "540"}, {"Rune platebody", "537"}, {"Rune plateskirt", "493"}, {"Bandos chestplate", "236000"}, {"Bandos tassets", "197000"}, {"Helm of neitiznot", "570"}, {"Berserker ring", "25000"}, {"Amulet of fury", "31000"}, {"Berserker helm", "470"}, {"Gilded platebody", "11000"}, {"Gilded platelegs", "17000"}, {"Gilded plateskirt", "1630"}, {"Gilded full helm", "7700"}, {"Zamorak platelegs", "13000"}, {"Zamorak plateskirt", "1560"}, {"Saradomin platelegs", "13000"}, {"Saradomin plateskirt", "1120"}, {"dharok's helm", "29000"}, {"Dharok's greataxe", "3000"}, {"Dharok's platebody", "3000"}, {"Dharok's platelegs", "3000"}, {"Verac's helm", "42000"}, {"Verac's flail", "1500"}, {"Verac's brassard", "3000"}, {"Verac's plateskirt", "3000"}, {"Torag's helm", "5900"}, {"Torag's platebody", "3000"}, {"Torag's platelegs", "3000"}, {"Torag's hammers", "1000"}, {"Guthan's helm", "3000"}, {"Guthan's warspear", "50000"}, {"Guthan's platebody", "3000"}, {"Guthan's chainskirt", "3000"}, {"Karil's coif", "950"}, {"Karil's crossbow", "3200"}, {"Karil's leathertop", "26000"}, {"Karil's leatherskirt", "5600"}, {"Bolt rack", "4"}, {"Ahrim's hood", "3000"}, {"Ahrim's staff", "3000"}, {"Ahrim's robetop", "25000"}, {"Ahrim's robeskirt", "27000"}, {"Ranger boots", "67000"}, {"Infinity boots", "13000"}, {"Archers ring", "12000"}, {"Seers ring", "9000"}, {"Arcane spirit shield", "3500000"}, {"Divine spirit shield", "2500000"}, {"Elysian spirit shield", "4000000"}, {"Spectral spirit shield", "2500000"}, {"Ghostly hood", "3"}, {"Ghostly robe", "3"}, {"Amulet of strength", "21"}, {"Tzhaar-ket-om", "2350"}, {"Berserker necklace", "3600"}, {"Ava's accumulator", "10"}, {"Rune knife", "4"}, {"Dragon bolts (e)", "46"}, {"Dragon arrow", "48"}, {"black d'hide chaps", "63"}, {"Unholy book", "20"}, {"Rune arrow", "4"}, {"Magic shortbow", "10"}, {"Dark bow", "37000"}, {"Rune crossbow", "230"}, {"Diamond bolts (e)", "5"}, {"Black d'hide vamb", "52"}, {"Black d'hide body", "89"}, {"Zamorak bracers", "1210"}, {"Zamorak d'hide", "4790"}, {"Zamorak chaps", "3230"}, {"Zamorak coif", "1890"}, {"Morrigan's leather body", "21000"}, {"Morrigan's leather chaps", "9000"}, {"Morrigan's coif", "4790"}, {"Morrigan's javelin", "150"}, {"Morrigan's throwing axe", "100"}, {"Robin hood hat", "7000"}, {"Dragonfire shield", "246000"}, {"Master wand", "4500"}, {"Infinity top", "3740"}, {"Infinity hat", "10000"}, {"Infinity bottoms", "3890"}, {"Infinity gloves", "2490"}, {"Mage's book", "15700"}, {"Ancient staff", "670"}, {"Zuriel's robe top", "5000"}, {"Zuriel's robe bottom", "6000"}, {"Zuriel's hood", "9000"}, {"Zuriel's staff", "15400"}, {"Water rune", "4"}, {"Blood rune", "4"}, {"Death rune", "4"}, {"Earth rune", "4"}, {"Soul rune", "4"}, {"Astral rune", "4"}, {"Fire rune", "4"}, {"Air rune", "4"}, {"Nature rune", "4"}, {"Cosmic rune", "4"}, {"Chaos rune", "4"}, {"Body rune", "4"}, {"Mind rune", "4"}, {"Zamorak cape", "1"}, {"Saradomin cape", "1"}, {"Guthix cape", "1"}, {"Prayer potion(4)", "100"}, {"Super defence(4)", "12"}, {"Super attack(4)", "16"}, {"Super strength(4)", "59"}, {"Super restore(4)", "156"}, {"Saradomin brew(4)", "62"}, {"Ranging potion(4)", "24"}, {"Manta ray", "9"}, {"Shark", "7"}, {"Cooked karambwan", "8"}, {"Tuna potato", "10"}, {"Barrelchest anchor", "2300"}, {"Fighter hat", "5000"}, {"Fighter torso", "20000"}, {"Penance gloves", "2000"}, {"Penance skirt", "2000"}, {"Penguin mask", "10000"}, {"Green h'ween mask", "250000"}, {"Red h'ween mask", "750000"}, {"Blue h'ween mask", "500000"}, {"Skeleton mask", "10000"}, {"Sheep mask", "10000"}, {"Bat mask", "10000"}, {"Cat mask", "10000"}, {"Wolf mask", "10000"}, {"Santa costume top", "1000"}, {"Santa costume legs", "1000"}, {"Santa costume gloves", "500"}, {"Santa hat", "100000"}, {"Santa costume boots", "500"}, {"Red partyhat", "3317000"}, {"Yellow partyhat", "3077000"}, {"Blue partyhat", "4563000"}, {"Green partyhat", "2978000"}, {"Purple partyhat", "2923000"}, {"White partyhat", "3764000"}, {"Silly jester hat", "100"}, {"Silly jester top", "100"}, {"Silly jester tights", "100"}, {"Silly jester boots", "100"}, {"Silly jester stick", "100"}, {"scythe", "10000"}, {"Dragon chain armour set (l)", "58000"}, {"Dragon chain armour set (sk)", "55000"}, {"Rune armour set (l)", "2000"}, {"Rune armour set (sk)", "2000"}, {"Splitbark armour set", "2000"}, {"Black trimmed armour set (l)", "10000"}, {"Black trimmed armour set (sk)", "10000"}, {"Black gold-trimmed armour set (l)", "10000"}, {"Black gold-trimmed armour set (sk)", "10000"}, {"Rune gold-trimmed armour set (l)", "15000"}, {"Rune gold-trimmed armour set (sk)", "15000"}, {"Guthix armour set (l)", "51771"}, {"Guthix armour set (sk)", "49413"}, {"Saradomin armour set (l)", "46419"}, {"Saradomin armour set (sk)", "34539"}, {"Zamorak armour set (l)", "36082"}, {"Zamorak armour set (sk)", "30000"}, {"Gilded armour set (l)", "40160"}, {"Gilded armour set (sk)", "30000"}, {"Blue dragon egg", "3000"}, {"Blue dragonhide", "75"}, {"Black dragon egg", "100000"}, {"Black dragonhide", "150"}, {"Dragon bones", "100"}, {"", ""}};

	String[][] killPrices = {{"Fighter torso", "10"}, {"Fire cape", "5"}, {"Dragon claws", "100"}, {"Vesta's longsword", "15"}, {"Statius's warhammer", "15"}, {"Rune defender", "5"}, {"Statius's platelegs", "15"}, {"Statius's full helm", "15"}, {"Statius's platebody", "15"}, {"Vesta's chainbody", "15"}, {"Vesta's plateskirt", "15"}, {"Vesta's chainbody", "49000"}, {"Void knight robe", "5"}, {"Void knight top", "5"}, {"Void knight gloves", "5"}, {"Void melee helm", "15"}, {"Void ranger helm", "15"}, {"Void mage helm", "15"}};

	int[][] otherPrices = {{6572, 20000}, {4089, 203},{4090, 203}, {4091, 513}, {4092, 513},{4093, 313}, {4094, 313},{4095, 902},{4096, 902},{4097, 402},{4098, 402}, {12480, 100000}, {11286, 150000}, {1632, 2500}, {1624, 120}, {1622, 250}, {1620, 400}, {1618, 550}, {6571, 20000}, {1631, 2500}, {1623, 120}, {1621, 250}, {1619, 400}, {1617, 550}, {13736, 35000}, {13737, 35000}, {13734, 1000}, {13735, 1000}, {7456, 3}, {7457, 5}, {7458, 50}, {7459, 10}, {7460, 20}, {7461, 1000}, {7462, 2500}, {10554, 2000}, {10553, 2000}, {13109, 10000}, {13110, 10000}, {1053, 250000}, {1054, 250000}, {1055, 500000}, {1056, 500000}, {1057, 750000}, {1058, 750000}, {9925, 7500}, {13107, 10000}, {13108, 10000}, {13111, 10000}, {13112, 10000}, {13113, 10000}, {13114, 10000}, {13115, 10000}, {13116, 10000}, {14595, 1000}, {14602, 500}, {14603, 1000}, {14605, 500}, {1050, 100000}, {1051, 100000}, {10836, 100}, {10837, 100}, {10838, 100}, {10839, 100}, {10840, 100}, {1419, 10000}, {8848, 50}, {8849, 100}, {3842, 20}, {861, 9}, {5698, 400}, {5699, 400}, {4716, 29000}, {4717, 29000}};

	int[][] killRequirments = {{7462, 5}};
    public int firstLog = 0;
   


	public int getItemValue(int item) {
		if (item == 995) {
			return 1;
		}
		int value = 0;
		for (String[] s : itemPrices) {
			String name = Engine.items.getItemName(item);
			if (name.equals(s[0])) {
				value = 100 * Integer.parseInt(s[1]);
			}
		}
		for (int[] i : otherPrices) {
			if (item == i[0]) {
				value = 100 * i[1];
			}
		}
		if (Engine.items.stackable(item) && value == 0) {
			value = 1;
		}
		return value;
	}

	public int getKillRequirment(int item) {
		int requirment = 0;
		for (int[] i : killRequirments) {
			if (item == i[0]) {
				requirment = i[1];
			}
		}
		return requirment;
	}

	public int getKillCost(int item) {
		int cost = 0;
		for (String[] s : killPrices) {
			String name = Engine.items.getItemName(item);
			if (name.equals(s[0])) {
				cost = Integer.parseInt(s[1]);
			}
		}
		return cost;
	}

	public double PVPPotential;

	public int totalKills;
	public int kills;

	public void message(String message) {
		getActionSender().sendMessage(this, message);
	}

	public boolean teleblocked;
	public int teleblockTimer = 500;

        public int infinate;

	public boolean mining;
	public int miningTimer = 16;

	public boolean teletab;
	public int leverTeleportX;
	public int leverTeleportY;
	public int leverTeleportH;
	public int leverTeleportDelay = -1;

        public int autocastSpell;
        public int autocastSpellbook;
        public boolean autocast;

	public boolean hitOne;
	public boolean hitTwo;
	public boolean hitThree;
	public boolean hitFour;
	public double hit1;
	public double hit2;
	public double hit3;
	public double hit4;

	public int prayOff = 0;

	public int specials;

	public int spendingExperience = 6000000;

	public int degrade = 6000;
	public boolean degrades = degrade < 6000;

	//Combat variables

	public double rangedMax;

	public int attackedByCount;
	public String attacking;
	public String attackedBy;
        public String OriginalAttacker;
	public String lastMsg;

        public int enemyId;

	public int spell;
	public int spell2;
	public int cuedSpell;
	public int cuedSpells;
	public int magicOppIndex;
	public int graphicMSDelay;
	public int magicGraphicDelay = -1;
	public int magicDamageDelay = -1;
	public int magicAffectDelay = -1;
	public boolean successfulCast;
	public boolean usingMage;
	public boolean orb;

	public int weapon;
	public int strengthBonus;
	public int oppIndex;
	public int hitIndex;

	public boolean getExperience = false;

	public PTrade pTrade;

	public void statSpy(Player other) {
		int[] strings = {1, 25, 13, 5, 37, 49, 61, 45, 69, 65, 33, 57, 53, 21, 9, 29, 17, 41, 77, 81, 73, 85, 89, 93};
		getActionSender().setString(this, other.username.substring(0, 1).toUpperCase() + other.username.substring(1), 523, 99);
		for (int i = 0; i < strings.length; i++) {
			getActionSender().setString(this, ""+other.skillLvl[i], 523, strings[i]);
			getActionSender().setString(this, ""+other.getLevelForXP(i), 523, strings[i] + 1);
		}
		getActionSender().setTab(this, 79, 523);
	}

public void objects() {
getActionSender().createGlobalObject(4388, 0, 3200, 3200, 0, 10); //portal at 3200,3200
getActionSender().createGlobalObject(4388, 0, 3200, 3200, 0, 10); //portal at 3200,3200
}
	public void clearItem(int item) {
		for (int i = 0; i < bankItems.length; i++) {
			if (item == bankItems[i]) {
				bankItems[i] = -1;
				bankItemsN[i] = 0;
			}
		}
		for (int i = 0; i < equipment.length; i++) {
			if (item == equipment[i]) {
				equipment[i] = -1;
				equipmentN[i] = 0;
			}
		}
		for (int i = 0; i < items.length; i++) {
			if (item == items[i]) {
				items[i] = -1;
				itemsN[i] = 0;
			}
		}	}

        public void clearInvo(String item) {
		String name = item;
		for (int i = 0; i < items.length; i++) {
			if (name.equals(Engine.items.getItemName(items[i]))) {
				items[i] = -1;
				itemsN[i] = 0;
			}
                }
        }

         public void clearNulls(String item) {
		String name = item;
		for (int i = 0; i < items.length; i++) {
			if (items[1] < 1) {
				items[1] = -1;
				itemsN[1] = 0;
			}
                }
        }

        public void clearBank(String item) {
		String name = item;
		for (int i = 0; i < bankItems.length; i++) {
			if (name.equals(Engine.items.getItemName(bankItems[i]))) {
				bankItems[i] = -1;
				bankItemsN[i] = 0;
                        }
		}
	}

        public void clearEquipment(String item) {
		String name = item;
		for (int i = 0; i < equipment.length; i++) {
			if (name.equals(Engine.items.getItemName(equipment[i]))) {
				equipment[i] = -1;
				equipmentN[i] = 0;
			}
		}
	}

	public void clearItem(String item) {
		String name = item;
		for (int i = 0; i < bankItems.length; i++) {
			if (name.equals(Engine.items.getItemName(bankItems[i]))) {
				bankItems[i] = -1;
				bankItemsN[i] = 0;
			}
		}
		for (int i = 0; i < equipment.length; i++) {
			if (name.equals(Engine.items.getItemName(equipment[i]))) {
				equipment[i] = -1;
				equipmentN[i] = 0;
			}
		}
		for (int i = 0; i < items.length; i++) {
			if (name.equals(Engine.items.getItemName(items[i]))) {
				items[i] = -1;
				itemsN[i] = 0;
			}
		}
	}

        public GrandExchange GrandExchange;

	public boolean lever;
	public void leverTeleport(String location) {
		if (teleblocked) {
			getActionSender().sendMessage(this, "You are teleport blocked!");
			return;
		}
		if (jailed > 0) {
			getActionSender().sendMessage(this, "You are jailed!");
			return;
		}
		int x = absX;
		int y = absY;
		int h = heightLevel;
		if (location != null) {
			if (location.equals("Deep Wilderness")) {
				x = 3153;
				y = 3923;
			}
			if (location.equals("Ardougne Lever")) { //Changed To Edge
				x = 3090;//x = 2561;
				y = 3474;//y = 3311;
			}
            		if (location.equals("Kbd Lever")) {
				x = 2273;
				y = 4680;
			}
            		if (location.equals("Home")) {
				x = 3087;
				y = 3491;
				h = 0;
			}
			if (location.equals("Mage Bank (Inside)")) {
				x = 2539;
				y = 4712;
			}
			if (location.equals("Mage Bank (Outside)")) {
				x = 3090;
				y = 3956;
			}
			if (location.equals("Mage Arena (Inside)")) {
				x = 3105;
				y = 3951;
			}
			if (location.equals("Mage Arena (Outside)")) {
				x = 3105;
				y = 3956;
			}
		}
		getActionSender().removeShownInterface(this);
		requestAnim(2140, 0);
		leverTeleportX = x;
		leverTeleportY = y;
		leverTeleportH = h;
		leverTeleportDelay = 2;
		lever = true;
	}

	public void teletab(String city) {
		int x = absX;
		int y = absY;
		int h = heightLevel;
		int r = 0;
		teletab = true;
        if (teleblocked) {
				getActionSender().sendMessage(this, "You are teleport blocked!");
				return;
			}
		if (getWildernessLevel() >= 20) {
		getActionSender().sendMessage(this, "A magical force stops you from teleporting.");
		return;
	}
		if (this.InBounty == 1) {
            this.getActionSender().sendMessage (this, "You cannot use this inside bounty hunter");
            return;
        }
		if (city != null) {
			if (city.equals("Varrock")) {
				x = 3210;
				y = 3421;
				r = 4;
			}
			if (city.equals("Lumbridge")) {
				x = 3221;
				y = 3218;
				r = 2;
			}
			if (city.equals("Falador")) {
				x = 2964;
				y = 3378;
				r = 3;
			}
			if (city.equals("Camelot")) {
				x = 2756;
				y = 3476;
				r = 3;
			}
			if (city.equals("Ardougne")) {
				x = 2660;
				y = 3301;
				r = 4;
			}
			if (city.equals("WatchTower")) {
				x = 2547;
				y = 3112;
				r = 1;
			}
		}
		getActionSender().removeShownInterface(this);
		x += getRandom(r);
		y += getRandom(r);
		teletabTo(x, y, h, 3, 0, 9597, 4071, 1680, 0, 678, 0);
	}


	public void cityTeleport(String city) {
		int x = absX;
		int y = absY;
		int h = heightLevel;
		int r = 0;
						if (jailed > 0) {
							getActionSender().sendMessage(this, "You are jailed!");
							return;
						}
						if (teleblocked) {
							getActionSender().sendMessage(this, "You are teleport blocked!");
							return;
						}
						if (getWildernessLevel() >= 20) {
							getActionSender().sendMessage(this, "A magical force stops you from teleporting.");
							return;
						}
		if (city != null) {
			if (city.equals("Varrock")) {
				x = 3210;
				y = 3421;
				r = 4;
			}
			if (city.equals("Lumbridge")) {
				x = 3221;
				y = 3218;
				r = 2;
			}
			if (city.equals("Falador")) {
				x = 2964;
				y = 3378;
				r = 3;
			}
			if (city.equals("Camelot")) {
				x = 2756;
				y = 3476;
				r = 3;
			}
			if (city.equals("Ardougne")) {
				x = 2660;
				y = 3301;
				r = 4;
			}
		}
		getActionSender().removeShownInterface(this);
		x += getRandom(r);
		y += getRandom(r);
		teleportTo(x, y, h, 4, 0, 8939, 8941, 1576, 0, 1577, 0);
	}
	public boolean isPVP() {
		return heightLevel == 4;
	}
	public boolean hotZone(int x, int y) {
		if ((x >= 3205 && x <= 3222 && y >= 3420 && y <= 3438) || //Varrock
		(x >= 3231 && x <= 3238 && y >= 3212 && y <= 3225) || //Lumbridge
		(x >= 2949 && x <= 2978 && y >= 3367 && y <= 3391) || //Falador
		(x >= 2741 && x <= 2774 && y >= 3464 && y <= 3481) || //Camelot
		(x >= 3156 && x <= 3199 && y >= 3412 && y <= 3487) || //West bank
                (x >= 2759 && x <= 2875 && y >= 5512 && y <= 5546) ||
		(x >= 2550 && x <= 2696 && y >= 3274 && y <= 3390)) { //Ardougne
			return true;
		}
		return false;
	}

    public boolean DoorArea(int x, int y) {
		if ((x >= 3124 && x <= 3124 && y >= 3665 && y <= 3665) ||
		(x >= 3138 && x <= 3138 && y >= 3669 && y <= 3669) ||
		(x >= 3146 && x <= 3146 && y >= 3681 && y <= 3681) ||
		(x >= 3163 && x <= 3163 && y >= 3696 && y <= 3696) ||
        (x >= 3180 && x <= 3180 && y >= 3708 && y <= 3708) ||
        (x >= 3181 && x <= 3181 && y >= 3720 && y <= 3720) ||
        (x >= 3171 && x <= 3171 && y >= 3737 && y <= 3737) ||
	(x >= 3101 && x <= 3101 && y >= 3682 && y <= 3682) ||
        (x >= 3170 && x <= 3170 && y >= 3746 && y <= 3746) ||
        (x >= 3163 && x <= 3163 && y >= 3753 && y <= 3753) ||
        (x >= 3147 && x <= 3147 && y >= 3758 && y <= 3758) ||
        (x >= 3135 && x <= 3135 && y >= 3758 && y <= 3758) ||
        (x >= 3121 && x <= 3121 && y >= 3754 && y <= 3754) ||
        (x >= 3110 && x <= 3110 && y >= 3747 && y <= 3747) ||
        (x >= 3091 && x <= 3091 && y >= 3735 && y <= 3735) ||
        (x >= 3086 && x <= 3086 && y >= 3717 && y <= 3717) ||
        (x >= 3091 && x <= 3091 && y >= 3706 && y <= 3706) ||
        (x >= 3096 && x <= 3096 && y >= 3692 && y <= 3692) ||
        (x >= 3108 && x <= 3108 && y >= 3670 && y <= 3670) ||
		(x >= 3171 && x <= 3171 && y >= 3701 && y <= 3701)) {
			return true;
		}
		return false;
	}	    

    public boolean Safezone(int x, int y) {
		if ((x >= 3091 && x <= 3098 && y >= 3488 && y <= 3499) ||
			(x >= 3135 && x <= 3192 && y >= 3652 && y <= 3702) ||
			(x >= 3179 && x <= 3194 && y >= 3432 && y <= 3446) ||
			(x >= 3250 && x <= 3257 && y >= 3416 && y <= 3423) ||
			(x >= 2943 && x <= 2947 && y >= 2946 && y <= 3373) ||
			(x >= 2943 && x <= 2949 && y >= 3368 && y <= 3368) ||
			(x >= 3009 && x <= 3018 && y >= 3353 && y <= 3358) ||
			(x >= 3009 && x <= 3022 && y >= 3353 && y <= 3356) ||
			(x >= 2649 && x <= 2658 && y >= 3280 && y <= 3287) ||
			(x >= 2612 && x <= 2621 && y >= 3330 && y <= 3335) ||
			(x >= 3201 && x <= 3229 && y >= 3217 && y <= 3220) ||
			(x >= 3201 && x <= 3226 && y >= 3209 && y <= 3228) ||
			(x >= 3201 && x <= 3225 && y >= 3208 && y <= 3229) ||
			(x >= 3201 && x <= 3224 && y >= 3207 && y <= 3230) ||
			(x >= 3201 && x <= 3223 && y >= 3206 && y <= 3231) ||
			(x >= 3201 && x <= 3222 && y >= 3205 && y <= 3232) ||
			(x >= 3201 && x <= 3221 && y >= 3204 && y <= 3233) ||
			(x >= 3201 && x <= 3220 && y >= 3203 && y <= 3234) ||
			(x >= 3201 && x <= 3213 && y >= 3202 && y <= 3235) ||
			(x >= 3201 && x <= 3212 && y >= 3201 && y <= 3236) ||
			(x >= 3201 && x <= 3203 && y >= 3202 && y <= 3235) ||
			(x >= 3201 && x <= 3202 && y >= 3203 && y <= 3234) ||
 			(x >= 2526 && x <= 2550 && y >= 4709 && y <= 4725) ||
                        (x >= 2358 && x <= 2438 && y >= 3066 && y <= 3142) ||
                        (x <= 2756 && x >= 2875 && y <= 5512 && y >= 5624) ||
                        (x >= 3201 && x <= 3201 && y >= 3204 && y <= 3233)) {
			return true;
		}
		return false;
	}

	public void Welcome() {
	if (this == null || this.disconnected[0]) {
		return;
	}
		requestAnim(7392, 0);
		clearItem("null");
		getActionSender().setWindowPane(this, 549);
		getActionSender().setInterface(this, 1, 549, 2, 378);
		getActionSender().setInterface(this, 1, 549, 3, 17);
		getActionSender().setString(this, "Message of the Week", 17, 0);
		getActionSender().setString(this, "Welcome to X-Scape", 17, 3);
		getActionSender().setString(this, "join forums and advertise", 378, 37);
		getActionSender().setString(this, ";]", 378, 39);
		getActionSender().setString(this, "Please vote", 378, 94);
		getActionSender().setString(this, "You can buy X donator from X only", 378, 93);
		getActionSender().setString(this, "0", 378, 96);
		getActionSender().setString(this, "Welcome to X-Scape", 378, 115);
		getActionSender().setString(this, "You last logged in yesterday from:  " + 	Server.socketListener.getAddress(socket.socket) + "", 378, 116);
	}

	public String getKillMessage(String player) {
		String[][] selection = {{"You have defeated ", "."},
					{"You have defeated ", "."}};
		int index = (int)Math.floor(Math.random() * 9);
		return selection[index][0] + player + selection[index][1];
	}

    public void friendsLoggedIn() {
    for(Long friend : friends) {
        getActionSender().sendFriend(this, friend, getWorld(friend));
    }
    long[] array = new long[ignores.size()];
    int i = 0;
    for(Long ignore : ignores) {
        if(ignore != null)
            array[i++] = ignore;
    }
    getActionSender().sendIgnores(this, array);
    long me = Misc.stringToLong(username);
    for(Player client : Engine.players) {
        if(client == null) continue;
        if(client.friends.contains(me)) {
            client.getActionSender().sendFriend(client, me, 66);
        }
    }
}

public int getWorld(long friend) {
    for(Player client : Engine.players) {
        if(client != null && client.online) {
            if(Misc.stringToLong(client.username) == friend) {
                return 66;
            }
        }
    }
    return 0;
}

	public boolean multiwayCombatZone(int x, int y) {
			if ((x > 3072 && x < 3107 && y > 3401 && y < 3448) ||
			(x > 2946 && x < 3004 && y > 3333 && y < 3424) ||
			(x > 3193 && x < 3332 && y > 3665 && y < 3752) ||
			(x > 3203 && x < 3331 && y > 3519 && y < 3666) ||
			(x > 3134 && x < 3328 && y > 3519 && y < 3658) ||
			(x > 2945 && x < 2961 && y > 3812 && y < 3828) ||
			(x > 3145 && x < 3217 && y > 3904 && y < 3966) ||
			(x > 2982 && x < 3010 && y > 3913 && y < 3929) ||
			(x > 2757 && x < 2875 && y > 5512 && y < 5624) ||
			(x > 3203 && x < 3392 && y > 3904 && y < 4031) ||
			(x > 3149 && x < 3331 && y > 3799 && y < 3850) ||
			(x > 3064 && x < 3391 && y > 3864 && y < 3903) ||
			(x > 2550 && x < 2696 && y > 3274 && y < 3390) ||
                        (x > 2756 && x < 2875 && y > 5536 && y < 5627) ||
                        (x > 3006 && x < 3072 && y > 3601 && y < 3713)) {
				
                            return true;
			}
		return false;
	}

	public boolean timerStarted;
	public long PVPTimer;

	public boolean wildernessZone(int x, int y ) {
		if (castleArea()){
                    return absX >= 2358 && absX <= 2438 && absY >= 3066 && absY <= 3142;
                }
                if (!isPVP()) {
			return (x >= 3042 && x <= 3395 && y >= 3523 && y <= 4000);
		}
		if (isPVP()) {
			if ((x >= 3091 && x <= 3098 && y >= 3488 && y <= 3499) ||
			(x >= 3135 && x <= 3192 && y >= 3652 && y <= 3702) ||
			(x >= 3179 && x <= 3194 && y >= 3432 && y <= 3446) ||
             		(x >= 2526 && x <= 2551 && y >= 4709 && y <= 4726) ||
			(x >= 3250 && x <= 3257 && y >= 3416 && y <= 3423) ||
			(x >= 2943 && x <= 2947 && y >= 2946 && y <= 3373) ||
			(x >= 2943 && x <= 2949 && y >= 3368 && y <= 3368) ||
			(x >= 3009 && x <= 3018 && y >= 3353 && y <= 3358) ||
			(x >= 3009 && x <= 3022 && y >= 3353 && y <= 3356) ||
			(x >= 2721 && x <= 2730 && y >= 3490 && y <= 3493) ||
			(x >= 2724 && x <= 2727 && y >= 3487 && y <= 3489) ||
			(x >= 2649 && x <= 2658 && y >= 3280 && y <= 3287) ||
			(x >= 2612 && x <= 2621 && y >= 3330 && y <= 3335) ||
			(x >= 3201 && x <= 3229 && y >= 3217 && y <= 3220) ||
			(x >= 3201 && x <= 3226 && y >= 3209 && y <= 3228) ||
			(x >= 3201 && x <= 3225 && y >= 3208 && y <= 3229) ||
			(x >= 3201 && x <= 3224 && y >= 3207 && y <= 3230) ||
			(x >= 3201 && x <= 3223 && y >= 3206 && y <= 3231) ||
			(x >= 3201 && x <= 3222 && y >= 3205 && y <= 3232) ||
			(x >= 3201 && x <= 3221 && y >= 3204 && y <= 3233) ||
			(x >= 3201 && x <= 3220 && y >= 3203 && y <= 3234) ||
			(x >= 3201 && x <= 3213 && y >= 3202 && y <= 3235) ||
			(x >= 3201 && x <= 3212 && y >= 3201 && y <= 3236) ||
			(x >= 3201 && x <= 3203 && y >= 3202 && y <= 3235) ||
			(x >= 3201 && x <= 3202 && y >= 3203 && y <= 3234) ||
 			(x >= 2526 && x <= 2550 && y >= 4709 && y <= 4725) ||
 			(x >= 2806 && x <= 2812 && y >= 3438 && y <= 3445) ||//Catherby Bank
 			(x >= 2609 && x <= 2616 && y >= 3088 && y <= 3097) ||//Yanille Bank
 			(x >= 3265 && x <= 3272 && y >= 3161 && y <= 3173) ||//Al Karid Bank
			(x >= 3147 && x <= 3181 && y >= 3473 && y <= 3506) ||
                        (x >= 3264 && x <= 3279 && y >= 3672 && y <= 3695) ||
                        (x >= 2756 && x <= 2875 && y >= 5508 && y <= 5511) ||
                        (x >= 3201 && x <= 3201 && y >= 3204 && y <= 3233)) {
		if ((attacking == null && attackedBy == null) || (timerStarted && System.currentTimeMillis() - PVPTimer >= 10000)) {
if (timerStarted) {
removeWilderness();
timerStarted = false;
attackedBy = null;
attacking = null;
}
return false;
} else if (!timerStarted && (attacking == null || attackedBy != null || attacking != null || attackedBy == null)) {
PVPTimer = System.currentTimeMillis();
timerStarted = true;
}

}
return true;
}
return false;
}



public int getWildernessLevel() {
int level = 0;
if (absY >= 3525 && absY <= 3527) {
level = 1;
} else if (absY >= 3526 && absY <= 3535) {
level = 2;
} else {
level = 3 + (int)Math.ceil((absY - 3536) / 8);
}
if (level < 0 || absY < 3525) {
level = 0;
}
if (level < 0 || absX < 2945) {
level = 0;
}
if (level < 0 || absY > 4000) {
level = 0;
}
if (level < 0 || absX > 3390) {
level = 0;
}
        if (!isPVP()) {
            return level;
        }
        if (isPVP()) {
            double base = 5 + (combatLevel * 0.10);
            int total = (int)Math.round(base) + level;
            if (wildernessZone(absX, absY)) {
                return total;
            } else {
                return 0;
            }
        }
		return level;	
	}

	public boolean properWildernessLevel(int thisCombat, int opponentCombat) {
		int difference = thisCombat >= opponentCombat ? thisCombat - opponentCombat : opponentCombat - thisCombat;
		return getWildernessLevel() >= difference;
	}

    public void restoreTabs(Player p) {
        for (int b = 16; b <= 21; b++) {
            p.getActionSender().setInterfaceConfig(p, 548, b, false);
        }

        for (int a = 32; a <= 38; a++) {
            p.getActionSender().setInterfaceConfig(p, 548, a, false);
        }
        p.calculateEquipmentBonus();

        p.getActionSender().setInterfaceConfig(p, 548, 14, false);
        p.getActionSender().setInterfaceConfig(p, 548, 31, false);
        p.getActionSender().setInterfaceConfig(p, 548, 63, false);

        p.getActionSender().setInterfaceConfig(p, 548, 72, false);
    }
    public void hideTabs(Player p) {
        for (int b = 16; b <= 21; b++) {
            p.getActionSender().setInterfaceConfig(p, 548, b, true);
        }

        for (int a = 32; a <= 38; a++) {
            p.getActionSender().setInterfaceConfig(p, 548, a, true);
        }
        p.calculateEquipmentBonus();

        p.getActionSender().setInterfaceConfig(p, 548, 14, true);
        p.getActionSender().setInterfaceConfig(p, 548, 31, true);
        p.getActionSender().setInterfaceConfig(p, 548, 63, true);

        p.getActionSender().setInterfaceConfig(p, 548, 72, true);
    }

    /**
      * Player count in clan.
      */
    public static int blackCount, whiteCount;
    /**
      * Clan wars teams
      */
    public static boolean blackTeam, whiteTeam;
    /**
      * Clan wars handler
      */
    public ClanWars clanWars = Engine.clanWars;
    /**
     * Wilderness level
     */
    public int wildLevel;
    /**
     * If player updated the Wilderness level.
     */
    public boolean updatedLevel;
    public int savedLevel;
    /**
     * Thieving.
     */
    public int[] thievingArray = new int[4];
    public int maxArrays = 10;
    public boolean[] optionArray = new boolean[maxArrays];
    public TestWorldLoader worldLoader = new TestWorldLoader(this);
    /**
     * Quest variables
     */
    public int questId;
    public int questStage;
    public QuestDevelopment quest = new QuestDevelopment(this);
    /**
     * Has entered defence room Warrior guild.
     */
    public boolean enteredDefenceRoom;
    /**
     * Prevents XLogging.
     */
    public int combatType;
    /**
     * The delay for making a fire.
     */
    public int[] firemaking = new int[4];
    /** 
     * Next graphic creating delay for MSB Special attack
     */
    public int nextDamageDelay = -1;
    public int nextGraphicDelay = -1;
    /**
     * Item ids of which are not spawnable.
     */
    public int[] economyItems = {
	11696, 11698, 11700, 11694, 11730, 3140, 11718, 11720, 11722, 11724, 11726, 11728,
	11690, 11702, 11704, 11706, 11708, 10581, 10566, 10637, 385, 391, 2440, 2434, 6685,
	11235, 4151, 12670, 12671, 4153
    };
    /**
     * Option variable
     */
    public int optionId;
    /**
     * Call Warrior guild class
     */
    public WarriorGuild warriorGuild = new WarriorGuild(this);
    /**
     * Defender dropping types variable
     */
    public int defenderId;

    /**
     * Wilderness Levels
     */
    public int wildernessLevel;

    /**
     * Summoning variables
     */
    public int summonTeleDelay = -1;
    public int summonDrainDelay = -1;
    public boolean callFamiliar;
    public boolean familiarDissMiss;
    public boolean summonedFamiliar;

    /**
     * Warrior Guild variables
     */
    public int[] randomItemIds = {
	8843, 8844, 8845, 8846, 8847, 8848, 8849, 8850
    };
    public String warriorArmour;

    /**
     * autoCast Variables
     */
    public int[] regularStaffs = {
	1381
    };
    public int[] otherStaffs = {
	4675
    };
    public int autoCastDmgDelay = -1;
    public int autoCastDelay;
    public int[] autoCast = new int[3];
    public boolean castAuto;
    public boolean usingAutoCast;
    /**
     * If player is disturbing commander zilyana.
     */
    public boolean disturbSara;
    /**
     * Death Delays
     */
    public int deathEmoteDelay = -1;
    /**
     * Yell delay
     */
    public int massYellDelay = 0;

    /**
     * Crystal bow shots.
     */
    public int crystalShots;

    /**
     * Fight Cave variables
     */
    public int neededKills;
    public int[] waveType = new int[5];
    public int waveCount;
    public int waveDelay = -1;
    public FightCave fCave = new FightCave(this);

    /**
     * Fletching variables
     */
    public int deletedItem, addedItem;
    public int fletchAmt;
    public boolean isFletching;
    public int[] fletching;
    public int fletchDelay;
    public int fletchType, fletchExp;
    PlayerFletching fletchingClass = new PlayerFletching(this);

    public int equipSpecDelay;
    public int[] miningAxes = {
	1265, 1267, 1269, 1271, 1273, 1275
    };
    public boolean isBanking;

     /**
     * Dueling variables
     */
    public int countDelay = -1;
    public int countType = -1;
    public boolean duelDeath;
    public boolean acceptDuel;
    public boolean acceptScreen1, acceptScreen2;
    public DuelArena duelArena = new DuelArena(this);
    public int duelFriend;
    public boolean duelScreen1, duelScreen2;

    public int explodeType;
    public int explodeDelay = -1;

    public int[] godWarsKills = new int[5];

    public int watchId = -1;

    public int spellType;

    public int playerStart;

    public boolean muteExpect, muteExpect2;
    public int muteType;
    public int[] hugeNpcs = {
	50, 1155, 1157, 1158, 1160, 2745, 6222, 6203, 8133
    };

    /**
     * Woodcutting variables
     */
    public int cutDelay;
    public boolean isWoodcutting;

    /**
     * Dueling variables
     */
    public boolean isBusy;
    public int duelEnemy;
    public boolean inDuelFight;

    /**
     * Pet variables
     */
    public int petKeeper;
    public boolean summonedPet;

    /**
     * Mage Arena variables
     */
    public int kolodionDelay;
    public boolean arenaActive;

    /**
     * Slayer variables
     */
    public int slayerAmount1;
    public int slayerType1;
    public int[] slayerType = {
	1615, 5363, 55, 54
    };
    public boolean slayerTask;
    public int[] slayerArray = {
	1, 2, 3, 4
    };
    public int slayerAmount;

    /**
     * Pvn variables
     */
    public int damageSpecDelay = -1;
    public boolean enableSpecDamage;
    public int damageDelay1 = -1;
    public boolean enableDamage;
    public int atkDelay;
    public boolean attackingNpc;
    public int attackNpc;

    /**
     * Clan wars variables.
     */
    public boolean blackClan;
    public boolean whiteClan;

    /**
     * Thieving variables.
     */
    public int pickPocketDelay;

    /**
     * This variable is added to add facing if player is gonna pickpocket.
     */
    public int npcClick2;

    /**
     * Mining variables.
     */
    public int rockId;
    public boolean isMining;
    public int receiveOreDelay;
    public int miningDelay;

    /**
     * Wilderness variables.
     */
    public int wildyLevel;

	public int statDelay = 100; //Stat update delay
	public int hpDelay = 100; //HP update delay

    /**
     * Emote clicking delay.
     */ 
    public int animClickDelay;
	public boolean usingPrayer;
	public int buryDelay;
	public int drainDelay;
	public boolean rangedPrayer;
	public boolean meleePrayer;
	public boolean magicPrayer;
	public boolean retriPrayer;
	public boolean redempPrayer;

	//Prayer

	public double[][] prayers = {{1, 0, 5}, {4, 0, 5}, {7, 0, 5}, {8, 0, 5}, {9, 0, 5}, {10, 0, 10}, {13, 0, 10}, {16, 0, 10}, {19, 0, 1.67}, {22, 0, 3.33}, {25, 0, 3.33}, {26, 0, 10}, {27, 0, 10}, {28, 0, 20}, {31, 0, 20}, {34, 0, 20}, {36, 0, 20}, {37, 0, 20}, {40, 0, 20}, {43, 0, 20}, {44, 0, 20}, {45, 0, 20}, {46, 0, 5}, {49, 0, 10}, {52, 0, 30}, {60, 0, 38.33}, {70, 0, 38.33}};
	public int headIconPrayer = -1;
	public double drainCount = 0;

	public boolean canPray(int prayer) {
		if (skillLvl[5] > 0 && getLevelForXP(5) >= prayers[prayer][0]) {
			return true;
		}
		return false;
	}

	public boolean usingPrayer(int prayer) {
		return prayers[prayer][1] == 1;
	}

	public boolean usingPrayer() {
		int i = 0;
		while (i <= 26) {
			if (prayers[i][1] == 1) return true;
			i++;
		}
		return false;
	}

	public void togglePrayer(int prayer, int toggle) {
		if (cantpray > 0) {
	    return;
	}
		int[] configuration = {83, 84, 85, 862, 863, 86, 87, 88, 89, 90, 91, 864, 865, 92, 93, 94, 1168, 95, 96, 97, 866, 867, 98, 99, 100, 1052, 1053};
		prayers[prayer][1] = toggle;
		getActionSender().setConfig(this, configuration[prayer], toggle);
	}

	public double prayerDrain() {
		int i = 0;
		double drainPerMinute = 0;
		while (i <= 26) {
			if (usingPrayer(i)) drainPerMinute += prayers[i][2];
			i++;
		}
		drainPerMinute *= 1 + (equipmentBonus[11] / 30);
		return drainPerMinute / 100;
	}

	public void switchPrayers(int[] prayers, int prayer) {
		if (!canPray(prayer)) {
			return;
		}
		for (int i : prayers) {
			if (usingPrayer(i)) {
				togglePrayer(i, 0);
			}
		}
	}

	public void prayerSounds(int prayer) {
		int sound = 0;
		switch (prayer) {
			case 0: sound = 2690; break;
			case 1: sound = 2688; break;
			case 2: sound = 2664; break;
			case 3: sound = 2685; break;
			case 4: sound = 2668; break;
			case 5: sound = 2684; break;
			case 6: sound = 2689; break;
			case 7: sound = 2662; break;
			case 8: sound = 2679; break;
			case 9: sound = 2678; break;
			case 10: sound = 0; break;
			case 11: sound = 2666; break;
			case 12: sound = 2670; break;
			case 13: sound = 2687; break;
			case 14: sound = 2691; break;
			case 15: sound = 2667; break;
			case 16: sound = 0; break;
			case 17: sound = 2675; break;
			case 18: sound = 2677; break;
			case 19: sound = 2676; break;
			case 20: sound = 2665; break;
			case 21: sound = 2669; break;
			case 22: sound = 2682; break;
			case 23: sound = 2680; break;
			case 24: sound = 2686; break;
			case 25: sound = 3826; break;
			case 26: sound = 3825; break;
		}
		if (sound != 0) {
			getActionSender().addSoundEffect(this, sound, 1, 0, 0);
		}
	}

	public void resetPrayer() {
		int i = 0;
		while (i <= 26) {
			togglePrayer(i, 0);
			i++;
		}
		drainCount = 0;
		headIconPrayer = -1;
		updateReq = true;
		appearanceUpdateReq = true;
	}
				

    /**
     * Magic combat variables
     */
    public int freezeDelay;
    public int vengeanceDelay;
    public boolean vengeance;
    public int spellbookSwapTimer;
    public boolean spellbookSwap;
    public boolean usedSpellbookSwap;
    public int mageDelay;

    /**
     * Agility Variables
     */
    public int[] agilityX = {
	2476, 2475, 2474, 2473, 2472, 2471
    };
    public int[] agilityY = {
	3426
    };
    public boolean agilityPerforming;
    public int agilityDelay;
    public int agilityType;

    /**
     * Combat variables
     */
    public boolean fadeAway;
    public int enemyFadeAwayDelay = -1;
    public int fightStyle = 1;
    public int[] strangeItems = {
	6570
    };
    public int battleCDelay;
    public int battleCount;
    public int poisonDelay;
    public int poisonHitCount;
    public boolean isPoisoned;
    public int headIconSkull = -1;
    public boolean isSkulled;
    public int skullVanishDelay;
    public int rangeDmgDelay = -1;
    public int rangeDmgDelay2 = -1;
    
    public int[] rangeBows = {
	841, 843, 845, 847, 849, 851, 853,
	855, 857, 859, 861, 9174, 9176, 9177,
	9179, 9181, 9183, 9185
    };
    public int[] rangeArrows = {
	882, 884, 886, 888, 890, 892
    };
    public int[] godSwords = {
	11694, 11696, 11698, 11700
    };
    public int myBonus;
    public int meleeDef;
    public int waitDeathDelay = -1;
    public boolean randomVariable;
    public int deathDelay = -1;
    public int Donator = 0;
    public boolean isDead;
    public int specDelay = -1;
    public int secondSpecDelay = -1;
    public int delayedDamageDelay = -1;
    public int delayedDamageHit = -1;
    public boolean expectSpec;
    public boolean autoRetaliate = true;
    public int specFillDelay = 50;
    public boolean usingSpecial;
    public int specAmount;
    public int damageDelay;
    public boolean damagePending;
    public int combatDelay;
    public int enemyIndex;
    public boolean attackingPlayer;

    /**
     * Player's index.
     */
    public int playerId = 0;
    /**
     * Class for storing and converting bytes.
     */
    public ByteVector stream = new ByteVector(500, 5000);
    /**
     * Player's socket for handling most io operations.
     */
    public PlayerSocket socket;
    /**
     * Set to true if the player has finished the login stage.
     */
    public boolean online = false;
    /**
     * Player's username.
     */
    public String username = "null";
    /**
     * Player's password.
     */
    public String password = "";
    /**
     * Player's rights.
     */
    public int rights = 0;
    /**
     * Player's Hidden rights.
     */
    public int hiddenRights = 0;
    /**
     * Player's starter.
     */
    public int starter = 0;
    /**
     * Player's Jailed Status.
     */
    public int jailed = 0;
    /**
     * 0 = In MageBank Normal World; 1 = MageBank PvP World.
     */
    public int mbPvP = 0;
    /**
     * 1 set to true means socket disconnected but logged in, both for removing the player.
     */
    public boolean[] disconnected = new boolean[2];
    /**
     * The region this player is in.
     */
    public int mapRegionX = 0;
    public int mapRegionY = 0;
    /**
     * The position this player is at in the map region.
     */
    public int currentX = 0;
    public int currentY = 0;
    /**
     * Absolute coordinates this player is at.
     */
    public int absX = 0;
    public int absY = 0;
    /**
     * The height level this player is at.
     */
    public int heightLevel = 0;
    /**
     * Storing players spellbook
     */
	public int spellbook = 192;
    /**
     * If either are above -1 then the player is in motion.
     */
    public int walkDir = -1;
    public int runDir = -1;
    /**
     * True if the player is running, false if it isn't.
     */
    public boolean isRunning = false;
    /**
     * Set to true if the player has entered a new map region.
     */
    public boolean mapRegionDidChange = false;
    /**
     * Set to true if teleported.
     */
    public boolean didTeleport = false;
    /**
     * Set Absolute coordinates to these.
     */
    public int teleportToX = -1;
    public int teleportToY = -1;
    /**
     * True if the player is Reqing an update.
     */
    public boolean updateReq = false;
    /**
     * Max number of steps this player can have pending.
     */
    public int walkingQueueSize = 50;
    public int wQueueReadPtr = 0;
    public int wQueueWritePtr = 0;
    /**
     * Positions the player is Reqing to walk to.
     */
    public int[] walkingQueueX = new int[walkingQueueSize];
    public int[] walkingQueueY = new int[walkingQueueSize];
    public int[] walkingQueue = new int[walkingQueueSize];
    /**
     * All the players within distance.
     */
    public Player[] playerList = new Player[Engine.players.length];
    /**
     * All the players stored in distance.
     */
    public byte[] playersInList = new byte[Engine.players.length];
    public int playerListSize = 0;
    /**
     * True if chatting is Reqing to be sent.
     */
    public boolean chatTextUpdateReq = false;
    public String chatText = "";
    public int chatTextEffects = 0;
    /**
     * True if an appearance update is needed.
     */
    public boolean appearanceUpdateReq = false;
    /**
     * Animation data.
     */
    public boolean animUpdateReq = false;
    public int animReq = -1;
    public int animDelay = 0;
    /**
     * GFX data.
     */
    public boolean gfxUpdateReq = false;
    public int gfxReq = -1;
    public int gfxDelay = 0;
    /**
     * Player and NPC facing data.
     */
    public boolean faceToUpdateReq = false;
    public int faceToReq = -1;
    /**
     * Damage data.
     */
    public boolean hit1UpdateReq = false;
    public boolean hit2UpdateReq = false;
    public int hitDiff1 = 0;
    public int hitDiff2 = 0;
    public int poisonHit1 = 0;
    public int poisonHit2 = 0;
    /**
     * Skill level data.
     */
    public int[] skillLvl = new int[25];
    public int[] skillXP = new int[25];
    public int combatLevel = 0;
    /**
     * Equipment data.
     */
    public int[] equipment = new int[14];
    public int[] equipmentN = new int[14];
    public int[] equipmentBonus = new int[12];
    /**
     * Player appearance.
     */
    public int[] color = new int[5];
    public int[] look = new int[7];
    public int npcType = -1;
    public int gender = 0;
    /**
     * Player emotes.
     */
    public int runEmote = 0x338;
    public int walkEmote = 0x333;
    public int standEmote = 0x328;
    /**
     * All the NPCs within distance.
     */
    public NPC[] npcList = new NPC[Engine.npcs.length];
    /**
     * All the npcs stored in distance.
     */
    public byte[] npcsInList = new byte[Engine.npcs.length];
    public int npcListSize = 0;
    /**
     * Rebuild the entire NPC list.
     */
    public boolean rebuildNPCList = false;
    /**
     * An array storing all the players items.
     */
    public int[] items = new int[28];
    public int[] itemsN = new int[28];
    /**
     * Open interfaces, use these to confirm an interface is open when trying to use one.
     */
    public int interfaceId = -1;
    public int chatboxInterfaceId = -1;
    /**
     * The current position in the login stage.
     */
    public int loginStage = 0;
    /**
     * Click x position.
     */
    public int clickX = 0;
    /**
     * Click y position.
     */
    public int clickY = 0;
    /**
     * Click id.
     */
    public int clickId = 0;
    /**
     * Eat delay.
     */
	public int eatDelay;
	public int drinkDelay;
    /**
     * True if the player is trying to pickup an item.
     */
    public boolean itemPickup = false;
    /**
     * Set run energy.
     */
    public boolean runEnergyUpdateReq = false;
    /**
     * Amount of current run energy.
     */
    public int runEnergy = 9999;
	//NPC
	public boolean npcDamagePending = false;
	public int damageNPCDelay = -1;
	public boolean onlyOnce = false;
	public boolean onlyOnceK = false;
		public int OOTimer = 0;
	
    /**
     * Delay before run energy can increase.
     */
    public int runEnergyDelay = 0;
    /**
     * Clicked the first option on a player.
     */
    public boolean playerOption1 = false;
    /**
     * Clicked the second option on a player.
     */
    public boolean playerOption2 = false;
    /**
     * Clicked the third option on a player.
     */
    public boolean playerOption3 = false;
    /**
     * Clicked the first option on a NPC.
     */
    public boolean npcOption1 = false;
    /**
     * Clicked the first option on an object.
     */
    public boolean objectOption1 = false;
    /**
     * Setting the prayer system effects.
     */
    public PrayerSystem prayerSystem = new PrayerSystem(this);
    /**
     * Setting the players weapon.
     */
    public PlayerWeapon playerWeapon = new PlayerWeapon(this);
    /**
     * Clicked the second option on an object.
     */
    public boolean objectOption2 = false;
    /**
     * Clicked the second option on a NPC.
     */
    public boolean npcOption2 = false;
    /**
     * Forced chat.
     */
    public String forceChat = "";
    public boolean forceChatUpdateReq = false;
    /**
     * Teleporting variables.
     */
    public int teleX = -1;
    public int teleY = -1;
    public int teleH = -1;
    public int teleDelay = -1;
    public int teleFinishGFX = 0;
    public int teleFinishGFXHeight = 0;
    public int teleFinishAnim = 0;
    /**
     * Delay before recieving packets.
     */
    public int clickDelay = -1;
    public long loginTimeout = System.currentTimeMillis();

    /**
	 * Arrays holding the items + their amounts of your bank
	 */
    public int[] bankItems = new int[Engine.playerBank.SIZE];
    public int[] bankItemsN = new int[Engine.playerBank.SIZE];
	/**
	 * The slot at which each bank tab starts at
	 */
	public int[] tabStartSlot = new int[11];
	/**
	 * The amount of items that you have last withrawn/deposited with the X function
	 */
	public int bankX = 150;
	/**
	 * The tab you are viewing, used to store items in it when depositing
	 */
	public int viewingBankTab = 10;
	/**
	 * If you are withrawing items as a note
	 */
	public boolean withdrawNote;
	/**
	 * If you are using insert mode
	 */
	public boolean insertMode;

    /**
     * Constructs a new Player.
     * @param socket The socket this Player belongs to.
     * @param id The index this player is at.
     */
    public Player(Socket socket, int id) {
        this.socket = new PlayerSocket(this, socket);
        playerId = id;
	look[0] = 0;
	look[1] = 10;
	look[2] = 18;
	look[3] = 26;
	look[4] = 33;
	look[5] = 36;
	look[6] = 42;
        for (int i = 0; i < skillLvl.length; i++) {
	    skillLvl[i] = 1;
	    skillXP[i] = 0;
	    skillLvl[3] = 10;
	    skillXP[3] = 1155;
        }
        for (int i = 0; i < items.length; i++) {
            items[i] = -1;
	    itemsN[i] = 0;
        }
        for (int i = 0; i < equipment.length; i++) {
            equipment[i] = -1;
        }
        for (int i = 0; i < bankItems.length; i++) {
            bankItems[i] = -1;
        }
pTrade = new PTrade(this);
GrandExchange = new GrandExchange (this);
    }

    public boolean skillCapeEquiped() {
	for (int i = 10639; i < 10663; i++) {
		for (int j = 9747; j < 9812; j++) {
			if (equipment[1] == i || equipment[1] == j || equipment[1] == 12169 || equipment[1] == 12170) { 
				return true;
			}
		}
	}
    return false;
    }
	public int timeToInput = -1;
	public int logoutTimer;
	public int restoreSpecialTimer;


	public void attackPlayer() {
		PlayerCombat pc = new PlayerCombat(this);
		pc.attackPlayer();
	}

	public int count;
	public boolean usedLogout;

        /**
     * This method is called every 600 milliseconds.
     * <p>While this is good for for changing integers, this
     * should not be abused. Things that can be handled else where should
     * be done in that way, such as clicking the accept button in trade
     * should update in the ActionsButton class rather than Reqing
     * an update for the process to handle.
     */
	public void process() {
if (systemupdate == 1) {
disconnected[0] = true;
}
if (systemupdate > 1) {
	    	systemupdate--;
		}
           	if(timeToInput > 0) {
			timeToInput--;
		}
			if(OOTimer > 0)
			OOTimer--;
		   if(OOTimer == 1) {
			OOTimer = 0;
			onlyOnce = false;
			}
			if (damageNPCDelay > 0) {
				damageNPCDelay--;
			}
			if(fightingCorp) {
			mobdamage = 0;
			}
			/*if (attackedBy != null) {
            clearItem("null");
            Engine.playerItems.deleteItem(this, 995, Engine.playerItems.getItemSlot(this, 995), 2147000000);
           // getActionSender().sendMessage(this, "You just dropped your cash while fighting!");
            return;
            }*/
            		if (proupdate > 0) {
	    	proupdate--;
	    	updatez = true;
		}
		if (proupdate > 1) {
		this.getActionSender().setOverlay(this, 524);
		this.getActionSender().setString(this, "Update!", 524, 1);
		this.getActionSender().setString(this, "Update in "+proupdate+" seconds", 524, 2);
		//this.getActionSender().sendMessage(this, "Update.");
		}
		if (updatez) {
		this.getActionSender().setOverlay(this, 524);
		this.getActionSender().setString(this, "Update!", 524, 1);
		this.getActionSender().setString(this, "Update in "+proupdate+" seconds", 524, 2);
		//this.getActionSender().sendMessage(this, "Update.");
		}
		if (proupdate == 0 && updatez == true) {
			System.out.println("Update Complete.");
			this.disconnected[50] = true;


		}
			if(gameTimeSeconds < 121)
		{
		gameTimeSeconds++;
		}
		if(gameTimeSeconds >= 120)
		{
		gameTimeSeconds-=120;
		gameTimeMinutes++;
		}	
				if (cantpray > 0) {
	    cantpray--;
		}
			if (miasmicSpell > 0) {
			miasmicSpell--;
		}
           if (attackedBy != null) {
            if (equipment[3] == 13904 && (statHammer < 1800)) { //Statius' warhammer (deg)
                statHammer++;
            }
            if (equipment[0] == 13898 && (statHelm < 1800)) { //Statius' full helm (deg)
                statHelm++;
            }
            if (equipment[4] == 13886 && (statBody < 1800)) { //Statius' platebody (Deg)
                statBody++;
            }
            if (equipment[7] == 13892 && (statLegs < 1800)) { //Statius' platelegs (Deg)
                statLegs++;
            }
            }
            if (attackedBy == null) {
            OriginalAttacker = null;
            }
            if (serverMsg == 100) {
            getActionSender().sendMessage(this, "<img=1><img=1><col=FF9900>Welcome to X-Scape<col=FF9900>");
            getActionSender().sendMessage(this, "<img=1><img=1><col=00CC00>Vote and Advertise!!!!!!!!!!!<col=00CC00");
            getActionSender().sendMessage(this, "<img=1><img=1><col=0033FF>Corp now drops sigils and Godwars Armor.<col=0033FF>");
            getActionSender().sendMessage(this, "<col=660099>Everyone please read the guide book or use the help, rules, and info commands<col=660099>");
             for (Player pl : Server.engine.players) {
            if (pl != null) {
            try {
            
            Engine.fileManager.saveBackup(pl);
            } catch (Exception e) {
            pl.message("Error saving.");
            }
            }
            }
        }
        if (serverMsg == 0) {
            serverMsg = 150;
        }
        if (serverMsg > 0) {
        serverMsg--;
        }
        if (stunned > 0) {
            stunned--;
        }
	if (leftBhTimer > 0) {
			leftBhTimer--;
		}
        if (bhLeave1 > 0) {
			bhLeave1--;
		}
       if(bhPickup < 1 && (InBounty == 1)) {
        bhPickup--;
        this.getActionSender().setString(this, "", 653, 11);
        this.getActionSender().setString(this, "", 653, 10);
        }
        if(bhPickup > 0 && (InBounty == 1)) {
        bhPickup--;
        this.getActionSender().setString(this, + bhPickup +" Sec", 653, 11);
        this.getActionSender().setString(this, "Can't leave for:", 653, 10);
        }
if(bhLeave > 0 && (InBounty == 1)) {
        bhLeave--;
        this.getActionSender().setString(this, + bhLeave +" Sec", 653, 11);
        this.getActionSender().setString(this, "Pickup penalty:", 653, 10);
        }
	updatePlayerList();
        if (logoutTimer > 0) {
			logoutTimer--;
		}
		if (restoreSpecialTimer > 0) {
			restoreSpecialTimer--;
		}
		if (DFSDelay > 0) {
			DFSDelay--;
		} else if (DFSDelay == 0) {
			append1Hit((int)Math.round(Math.random() * 25), 0);
			DFSDelay = -1;
		}
        if(InBounty == 1){
             getActionSender().setHintIcon(this, 10, this.bhTarget, 1, -1);
            Player p9 = Engine.players[this.bhTarget];
		    if(p9 != null) {
	    	   	this.getActionSender().setString(this, "" + p9.username , 653, 8);
                    if (p9.rights == 10) {
                        this.bhTarget = Engine.BountyHunter.getTargetHigh(this);
                        getActionSender().sendMessage(this, "Your target was an administrator and you have been re-asigned one.");
                    }
		    } else {
	    		this.getActionSender().setString(this, "None" , 653, 8);
                        this.bhTarget = Engine.BountyHunter.getTargetHigh(this);
		    }
        }
		if (leverTeleportDelay > 0) {
			leverTeleportDelay--;
		} else if (leverTeleportDelay == 0) {
			teleportTo(leverTeleportX, leverTeleportY, leverTeleportH, 4, 0, 8939, 8941, 1576, 0, 1577, 0);
			lever = false;
			leverTeleportDelay--;
		}
		if (teleblocked && teleblockTimer > 0) {
			if (teleblockTimer == 500) {
				getActionSender().sendMessage(this, "You have been teleport blocked!");
				if (usingPrayer(17)) {
					teleblockTimer = 250;
				}
			}
			teleblockTimer--;
		}
		if (teleblockTimer == 0) {
			teleblocked = false;
			teleblockTimer = 500;
			getActionSender().sendMessage(this, "The teleport block has worn off.");
		}
		if (magicGraphicDelay > 0) {
			magicGraphicDelay--;
		} else if (magicGraphicDelay == 0) {
			PlayerMagic playerMagic = new PlayerMagic(this);
			playerMagic.appendGraphic(spellbook, spell);
			magicGraphicDelay--;
		}
		if (magicDamageDelay > 0) {
			magicDamageDelay--;
		} else if (magicDamageDelay == 0) {
			PlayerMagic playerMagic = new PlayerMagic(this);
			playerMagic.appendDamage(spellbook, spell);
			magicDamageDelay--;
		}
		if (magicAffectDelay > 0) {
			magicAffectDelay--;
		} else if (magicAffectDelay == 0) {
			PlayerMagic playerMagic = new PlayerMagic(this);
			playerMagic.appendAffect(spellbook, spell);
			magicAffectDelay--;
		}
		if (cuedSpells > 0 && combatDelay == 0) {
			try {
				Player opp = Server.engine.players[magicOppIndex];
				PlayerMagic playerMagic = new PlayerMagic(this);
				playerMagic.combatMagic(opp, spellbook, cuedSpell);
				cuedSpells = 0;
			} catch (Exception e) {
			}
		}

		if (usingPrayer()) {
			drainCount += prayerDrain();
			if (!isDead) {
				if (drainCount >= 1) {
					skillLvl[5]--;
					getActionSender().setSkillLvl(this, 5);
					drainCount--;
				}
			}
			if (skillLvl[5] <= 0) {
				skillLvl[5] = 0;
				getActionSender().addSoundEffect(this, 2672, 1, 0, 0);
				getActionSender().setSkillLvl(this, 5);
				getActionSender().sendMessage(this, "You have run out of Prayer points; you must recharge at an altar.");
				resetPrayer();
			}
		}
	if (disconnected[0] && (usedLogout || attackedBy == null)) {
		try {
			if (heightLevel != 0) heightLevel = 0;
			Engine.fileManager.saveCharacter(this);
		} catch (Exception e) {
		}
		if (pTrade.getPartner() != null) {
			pTrade.declineTrade();
		}
		disconnected[1] = true;
	}
	if (nextGraphicDelay > 0)
	    nextGraphicDelay--;
	if (nextGraphicDelay == 0) {
	    PlayerCombat playerAttacking = new PlayerCombat(this);
	    playerAttacking.addNextAttack();
	}
	if (nextDamageDelay > 0)
	    nextDamageDelay--;
	if (nextDamageDelay == 0) {
	    PlayerCombat playerAttacking = new PlayerCombat(this);
	    playerAttacking.addNextDamage();
	}
	if (deathEmoteDelay > 0) {
	    deathEmoteDelay--;
	}
	if (deathEmoteDelay == 0) {
       // Server.engine.newNPC(1552, this.absX-1, this.absY, this.heightLevel, 0, 0, 0, 0, false, 0); // Santa appears on death
		requestAnim(9055, 0); //836, 2261
		if (usingPrayer(22)) {
			requestGFX(437, 0);
			if (multiwayCombatZone(absX, absY)) {
				try {
					for (Player player : Server.engine.players) {
						if (Misc.getDistance(absX, absY, player.absX, player.absY) <= 1) {
							player.append1Hit(getRandom((int)Math.floor(getLevelForXP(5) * 0.25)), 0);
						}
					}
				} catch (Exception e) {
				}
			} else {
				try {
					Server.engine.players[hitIndex].append1Hit(getRandom((int)Math.floor(getLevelForXP(5) * 0.25)), 0);
				} catch (Exception e) {
				}
			}
		}

		isDead = true;
		deathDelay = 4;
		deathEmoteDelay = -1;
	}
        if (clickDelay > 0) {
            clickDelay--;
        }
        if (teleDelay > 0) {
            teleDelay--;
        }
	if (equipSpecDelay > 0) {
	    equipSpecDelay--;
	}
	if (countDelay > 0) {
	    countDelay--;
	}
	if (explodeDelay > 0) {
	    explodeDelay--;
	}
	if (explodeDelay == 0) {
	    appendPotion();
	}
	if (rangeDmgDelay > 0) {
	    rangeDmgDelay--;
	}
	if (rangeDmgDelay == 0) {
	    PlayerCombat PC2 = new PlayerCombat(this);
	    PC2.appendRangeDamage();
	}
	if (rangeDmgDelay2 > 0) {
	    rangeDmgDelay2--;
	}
	if (rangeDmgDelay2 == 0) {
	    PlayerCombat PC2 = new PlayerCombat(this);
	    PC2.appendRangeDamage();
	}
	if (damageSpecDelay > 0) {
	    damageSpecDelay--;
	}
	if (damageSpecDelay == 0 && enableSpecDamage) {
	    PlayerCombat playCb2 = new PlayerCombat(this);
	    playCb2.appendNpcDamageMeleeSpec();
	}
	if (damageDelay1 > 0) {
	    damageDelay1--;
	}
	if (damageDelay1 == 0 && enableDamage) {
	    PlayerCombat playerCb = new PlayerCombat(this);
	    playerCb.appendNpcDamageMelee();
	}
	if (atkDelay > 0) {
	    atkDelay--;
	}
	if (atkDelay == 0 && attackingNpc) {
	    PlayerCombat playCb = new PlayerCombat(this);
	    playCb.attackNpc();
	}
	if (battleCDelay > 0) {
	    battleCDelay--;
	}
	if (battleCDelay == 0) {
	    battleCount = 0;
	    battleCDelay = -1;
	}
	if (skullVanishDelay > 0) {
	    skullVanishDelay--;
	}
	if (skullVanishDelay == 0 && isSkulled  && (InBounty == 0)) {
		headIconSkull = -1;
		isSkulled = false;
		skullVanishDelay = 0;
		appearanceUpdateReq = true;
		updateReq = true;
	}
	if (eatDelay > 0) {
		eatDelay--;
	}
	if (drinkDelay > 0) {
		drinkDelay--;
	}

	if (statDelay > 0) {
		statDelay -= usingPrayer(8) ? 2 : 1;
	} else {
		updateStats();
	}
	if (hpDelay > 0) {
		hpDelay -= usingPrayer(9) ? 2 : 1;
	} else {
		restoreHP();
	}
	if (poisonDelay > 0) {
	    poisonDelay--;
	}
	if (specFillDelay > 0) {
		if (specAmount < 1000) {
			specFillDelay--;
		} else {
			specFillDelay = 50;
		}
	} else {
		if (specAmount < 1000) {
			if (specAmount - 100 > 900) {
				specAmount = 1000;
			} else {
				specAmount += 100;
			}
			getActionSender().setConfig2(this, 300, specAmount);
		}
		specFillDelay = 50;
	}
	if (animClickDelay > 0) {
	    animClickDelay--;
	}
	if (deathDelay > 0) {
	    deathDelay--;
	}
	if (waitDeathDelay > 0) {
	    waitDeathDelay--;
	}

	/*if (skillLvl[3] <= 0 && waitDeathDelay == -1 && !randomVariable) {
	    waitDeathDelay = 3;
	    resetAttack();
	   deathEmoteDelay = 3;
	   if (duelFight())
		duelDeath = true;
	    randomVariable = true;
	   isDead = true;
	}*/
	if (deathDelay == 0 && isDead) {
	    appendDeath();
	}
	if (combatDelay > 0) {
	    combatDelay--;
	}
	if (attackingPlayer) {
		attackPlayer();
	}
	if (damageDelay > 0) {
	    damageDelay--;
	}
	if (damageDelay == 0) {
	    PlayerCombat PC = new PlayerCombat(this);
	    PC.appendDamages();
	}
	if (mageDelay > 0) {
	    mageDelay--;
	}
	if (specDelay > 0) {
	    specDelay--;
	}
	if (specDelay == 0) {
	    PlayerCombat PC1 = new PlayerCombat(this);
	    PC1.appendSpecDamage();
	}
	if (secondSpecDelay > 0) {
	    secondSpecDelay--;
	}
	if (secondSpecDelay == 0) {
	    PlayerCombat PC1 = new PlayerCombat(this);
	    PC1.appendSecondSpecDamage();
	}
	if (delayedDamageDelay > 0) {
		delayedDamageDelay--;
	}
	if (delayedDamageDelay == 0) {
	    PlayerCombat PC1 = new PlayerCombat(this);
	    PC1.appendDelayedDamage(delayedDamageHit);
	}
        if (runEnergyDelay > 0) {
            runEnergyDelay--;
        } else {
            if (runEnergy < 100) {
                runEnergy++;
                runEnergyUpdateReq = true;
            }
            runEnergyDelay = 2;
        }
        if (itemPickup) {
            Engine.packets.pickupItem.handlePacket(this, 0, 0);
        }
        if (playerOption1) {
            Engine.packets.playerOption1.handlePacket(this, 0, 0);
        }
        if (playerOption2) {
            Engine.packets.playerOption2.handlePacket(this, 0, 0);
        }
        if (playerOption3) {
            Engine.packets.playerOption3.handlePacket(this, 0, 0);
        }
        if (npcOption1) {
            Engine.packets.npcOption1.handlePacket(this, 0, 0);
        }
        if (npcOption2) {
            Engine.packets.npcOption2.handlePacket(this, 0, 0);
        }
        if (objectOption1) {
            Engine.packets.objectOption1.handlePacket(this, 0, 0);
        }
        if (objectOption2) {
            Engine.packets.objectOption2.handlePacket(this, 0, 0);
        }
        if (runEnergyUpdateReq) {
            getActionSender().setEnergy(this);
            runEnergyUpdateReq = false;
        }
        appendWilderness();
        if (teleDelay == 0) {
            teleDelay = -1;
		if (teletab) {
			requestAnim(teleFinishAnim, 0);
			requestGFX(teleFinishGFX, teleFinishGFXHeight);
			teleDelay = 2;
			teleFinishAnim = playerWeapon.getStandEmote(equipment[3]);
			teleFinishGFX = -1;
		} else {
			setCoords(teleX, teleY, teleH);
			if (teleFinishAnim != -1) {
				requestAnim(teleFinishAnim, 0);
			}
			if (teleFinishGFX != -1) {
				requestGFX(teleFinishGFX, teleFinishGFXHeight);
			}
		}
	if (!teletab) {
            teleX = teleY = -1;
	}
	teletab = false;
        }
        if (clickDelay == 0) {
            clickDelay = -1;
        }
if (freezeDelay > 0) {
			freezeDelay--;
    } else if (vengeanceDelay > 0) {
			vengeanceDelay--;
    } else if (spellbookSwapTimer > 0) {
			spellbookSwapTimer--;
		} else if (spellbookSwap) {
			getActionSender().setTab(this, 79, spellbook);
			spellbookSwap = false;
			usedSpellbookSwap = false;
	} else if (attackedBy != null) {
			count++;
            if (InBounty == 1) {
            if (count == 4) {
				attackedBy = null;
				attackedByCount--;
				count = 0;
			}
    }
			if (count == 10) {
				attackedBy = null;
				attackedByCount--;
				count = 0;
			}
    }
   
    }

	public void potion(int potionID) {
		int root = 0;
		int toAdd = 0;
		int i = 0;
		switch (potionID) {
			case 2436:
			case 145:
			case 147:
			case 149:
				root = getLevelForXP(0);
				toAdd = 5 + (int)Math.round(root * 0.1414141414);
				if ((skillLvl[0] + toAdd) > (root + toAdd)) {
					skillLvl[0] = root + toAdd;
				} else {
					skillLvl[0] += toAdd;
				}
				getActionSender().setSkillLvl(this, 0);
			break;
                case 2438:
		case 151:
		case 153:
		case 155:
			while (i <= 5) {
					if (i == 4) {
						i++;
					}
					root = getLevelForXP(i);
					if (i == 5) {
						toAdd = 9;
						if (skillLvl[i] + toAdd > root) {
							if (skillLvl[i] < root) {
								skillLvl[i] = root;
							}
						} else {
							skillLvl[i] += toAdd;
						}
						break;
					}
					if (i == 16 || i == 3) {
						toAdd = -1 * (2 + (int)Math.round(root * 0.0909090909));
						if (i == 3) {
							append1Hit(-1 * toAdd, 0);
						} else {
							if ((skillLvl[i] + toAdd) < 0) {
								skillLvl[i] = 0;
							} else {
								skillLvl[i] += toAdd;
							}
						}
					}
					if (i == 16 || i == 3) {
						toAdd = -1 * (2 + (int)Math.round(root * 0.0909090909));
						if (i == 3) {
							append1Hit(-1 * toAdd, 0);
						} else {
							if ((skillLvl[i] + toAdd) < 0) {
								skillLvl[i] = 0;
							} else {
								skillLvl[i] += toAdd;
							}
						}
					}
					if (i == 16 || i == 3) {
						toAdd = -1 * (2 + (int)Math.round(root * 0.0909090909));
						if (i == 3) {
							append1Hit(-1 * toAdd, 0);
						} else {
							if ((skillLvl[i] + toAdd) < 0) {
								skillLvl[i] = 0;
							} else {
								skillLvl[i] += toAdd;
							}
						}
					} else {
						toAdd = 2 + (int)Math.round(root * (i == 0 ? 0.1919191919 : 0.1111111111));
						if (skillLvl[i] + toAdd > root + toAdd) {
							skillLvl[i] = root + toAdd;
						} else {
							skillLvl[i] += toAdd;
						}
					}
					getActionSender().setSkillLvl(this, i);
					i++;
				}
		        for (int o = 0; o < 3; o++) {
				root = getLevelForXP(o);
				toAdd = (int) Math.round(root * .26);
				if ((skillLvl[o] + toAdd) > (root + toAdd)) {
					skillLvl[o] = root + toAdd;
				} else {
					skillLvl[o] += toAdd;
				}
				getActionSender().setSkillLvl(this, o);
			}
			root = getLevelForXP(4);
			toAdd = (int) Math.round(root * .23);
			if ((skillLvl[4] + toAdd) > (root + toAdd)) {
				skillLvl[4] = root + toAdd;
			} else {
				skillLvl[4] += toAdd;
			}
			getActionSender().setSkillLvl(this, 4);
			root = getLevelForXP(6);
			toAdd = (int) Math.round(root * .4);
			if ((skillLvl[6] + toAdd) > (root + toAdd)) {
				skillLvl[6] = root + toAdd;
			} else {
				skillLvl[6] += toAdd;
			}
			getActionSender().setSkillLvl(this, 6);
			getActionSender().setSkillLvl(this, 4);
			break;

			case 2440:
			case 157:
			case 159:
			case 161:
				root = getLevelForXP(2);
				toAdd = 5 + (int)Math.round(root * 0.1414141414);
				if ((skillLvl[2] + toAdd) > (root + toAdd)) {
					skillLvl[2] = root + toAdd;
				} else {
					skillLvl[2] += toAdd;
				}
				getActionSender().setSkillLvl(this, 2);
			break;

			case 2442:
			case 163:
			case 165:
			case 167:
				root = getLevelForXP(1);
				toAdd = 5 + (int)Math.round(root * 0.1414141414);
				if ((skillLvl[1] + toAdd) > (root + toAdd)) {
					skillLvl[1] = root + toAdd;
				} else {
					skillLvl[1] += toAdd;
				}
				getActionSender().setSkillLvl(this, 1);
			break;

			case 2444:
			case 169:
			case 171:
			case 173:
				root = getLevelForXP(4);
				toAdd = 4 + (int)Math.round(root * 0.1509090909);
				if ((skillLvl[4] + toAdd) > (root + toAdd)) {
					skillLvl[4] = root + toAdd;
				} else {
					skillLvl[4] += toAdd;
				}
				getActionSender().setSkillLvl(this, 4);
			break;

			case 3040:
			case 3042:
			case 3044:
			case 3046:
				if (skillLvl[6] + 4 > getLevelForXP(6) + 4) {
					skillLvl[6] = getLevelForXP(6) + 4;
				} else {
					skillLvl[6] += 4;
				}
				getActionSender().setSkillLvl(this, 6);
			break;

			case 2428:
			case 121:
			case 123:
			case 125:
				root = getLevelForXP(0);
				toAdd = 3 + (int)Math.round(root * 0.0909090909);
				if ((skillLvl[0] + toAdd) > (root + toAdd)) {
					skillLvl[0] = root + toAdd;
				} else {
					skillLvl[0] += toAdd;
				}
				getActionSender().setSkillLvl(this, 0);
			break;

			case 113:
			case 115:
			case 117:
			case 119:
				root = getLevelForXP(2);
				toAdd = 3 + (int)Math.round(root * 0.0909090909);
				if ((skillLvl[2] + toAdd) > (root + toAdd)) {
					skillLvl[2] = root + toAdd;
				} else {
					skillLvl[2] += toAdd;
				}
				getActionSender().setSkillLvl(this, 2);
			break;
                    case 14249://extreme range
                    case 14251:
                    case 14253:
                    case 14255:
                        root = getLevelForXP(4);
				toAdd = 4 + (int)Math.round(root * 0.19191919);
				if ((skillLvl[0] + toAdd) > root + toAdd) {

						skillLvl[4] = root + toAdd;

				} else {
					skillLvl[4] += toAdd;
				}
				getActionSender().setSkillLvl(this, 4);
                                break;
                    case 14229://extreme attack
                    case 14231:
                    case 14233:
                    case 14235:
                        root = getLevelForXP(0);
				toAdd = 0 + (int)Math.round(root * 0.25252525);
				if ((skillLvl[0] + toAdd) > root + toAdd) {

						skillLvl[0] = root + toAdd;

				} else {
					skillLvl[0] += toAdd;
				}
				getActionSender().setSkillLvl(this, 0);
                                break;
                    case 14259://extreme def
                    case 14261:
                    case 14263:
                    case 14265:
                        root = getLevelForXP(1);
				toAdd = 1 + (int)Math.round(root * 0.25252525);
				if ((skillLvl[1] + toAdd) > root + toAdd) {

						skillLvl[1] = root + toAdd;

				} else {
					skillLvl[1] += toAdd;
				}
				getActionSender().setSkillLvl(this, 1);
                                break;
                    case 14239://extreme str
                    case 14241:
                    case 14243:
                    case 14245:
                        root = getLevelForXP(2);
				toAdd = 2 + (int)Math.round(root * 0.25252525);
				if ((skillLvl[0] + toAdd) > root + toAdd) {

						skillLvl[2] = root + toAdd;

				} else {
					skillLvl[2] += toAdd;
				}
				getActionSender().setSkillLvl(this, 2);
                                break;
                    case 14269://extreme mage
                    case 14271:
                    case 14273:
                    case 14275:
                        root = getLevelForXP(6);
				toAdd = 6 + (int)Math.round(root * 0.25252525);
				if ((skillLvl[6] + toAdd) > root + toAdd) {

						skillLvl[0] = root + toAdd;

				} else {
					skillLvl[6] += toAdd;
				}
				getActionSender().setSkillLvl(this, 6);
                                break;
			case 2432:
			case 133:
			case 135:
			case 137:
				root = getLevelForXP(1);
				toAdd = 3 + (int)Math.round(root * 0.0909090909);
				if ((skillLvl[1] + toAdd) > (root + toAdd)) {
					skillLvl[1] = root + toAdd;
				} else {
					skillLvl[1] += toAdd;
				}
				getActionSender().setSkillLvl(this, 1);
			break;

			case 2434:
			case 139:
			case 141:
			case 143:
				root = getLevelForXP(5);
				toAdd = 7 + (int)Math.round(root * 0.2424242424);
				if ((skillLvl[5] + toAdd) > root) {
					if (skillLvl[5] < root) {
						skillLvl[5] = root;
					}
				} else {
					skillLvl[5] += toAdd;
				}
				getActionSender().setSkillLvl(this, 5);
			break;

			case 2430:
			case 127:
			case 129:
			case 131:
				while (i <= 6) {
					if (i == 3 || i == 5) {
						i++;
					}
					root = getLevelForXP(i);
					toAdd = 10 + (int)Math.round(root * 0.2929292929);
					if ((skillLvl[i] + toAdd) > root) {
						if (skillLvl[i] < root) {
							skillLvl[i] = root;
						}
					} else {
						skillLvl[i] += toAdd;
					}
					getActionSender().setSkillLvl(this, i);
					i++;
				}
			break;

			case 3024:
			case 3026:
			case 3028:
			case 3030:
				while (i <= 23) {
					if (i == 3) {
						i++;
					}
					root = getLevelForXP(i);
					toAdd = 8 + (int)Math.round(root * 0.2424242424);
					if ((skillLvl[i] + toAdd) > root) {
						if (skillLvl[i] < root) {
							skillLvl[i] = root;
						}
					} else {
						skillLvl[i] += toAdd;
					}
					getActionSender().setSkillLvl(this, i);
					i++;
				}
			break;

			case 6685:
			case 6687:
			case 6689:
			case 6691:
				while (i <= 6) {
					if (i == 5) {
						i++;
					}
					root = getLevelForXP(i);
					if (i == 0 || i == 2 || i == 4 || i == 6) {
						toAdd = -1 * (2 + (int)Math.round(root * 0.0707070707));
						if ((skillLvl[i] + toAdd) < 0) {
							skillLvl[i] = 0;
						} else {
							skillLvl[i] += toAdd;
						}
					} else {
						toAdd = 2 + (int)Math.round(root * (i == 1 ? 0.1919191919 : 0.1414141414));
						if (skillLvl[i] + toAdd > root + toAdd) {
							skillLvl[i] = root + toAdd;
						} else {
							skillLvl[i] += toAdd;
						}
					}
					getActionSender().setSkillLvl(this, i);
					i++;
				}
			break;

			case 2450:
			case 189:
			case 191:
			case 193:
				while (i <= 5) {
					if (i == 4) {
						i++;
					}
					root = getLevelForXP(i);
					if (i == 5) {
						toAdd = 9;
						if (skillLvl[i] + toAdd > root) {
							if (skillLvl[i] < root) {
								skillLvl[i] = root;
							}
						} else {
							skillLvl[i] += toAdd;
						}
						break;
					}
					if (i == 1 || i == 3) {
						toAdd = -1 * (2 + (int)Math.round(root * 0.0909090909));
						if (i == 3) {
							append1Hit(-1 * toAdd, 0);
						} else {
							if ((skillLvl[i] + toAdd) < 0) {
								skillLvl[i] = 0;
							} else {
								skillLvl[i] += toAdd;
							}
						}
					} else {
						toAdd = 2 + (int)Math.round(root * (i == 0 ? 0.1919191919 : 0.1111111111));
						if (skillLvl[i] + toAdd > root + toAdd) {
							skillLvl[i] = root + toAdd;
						} else {
							skillLvl[i] += toAdd;
						}
					}
					getActionSender().setSkillLvl(this, i);
					i++;
				}
			break;
		}
		requestAnim(829, 0);
		getActionSender().addSoundEffect(this, 2401, 1, 0, 0);
		combatDelay = 6;
		eatDelay = 3;
		drinkDelay = 3;
	}

	public void food(int foodID) {
		int healAmount = 0;
		int toDelayCombat = 6;
		int toDelayEat = 3;
		switch (foodID) {
			case 10476:
				healAmount = 1 + Misc.random(2);
				runEnergy += runEnergy * .10;
                                runEnergyUpdateReq = true;
				if (runEnergy + (runEnergy * .10) > 100) {
				runEnergy = 100;
				}
			break;
			case 373: healAmount = 14; break;
			case 379: healAmount = 23; break;
			case 385: healAmount = 20; break;
			case 391: healAmount = 22; break;
			case 3144:
				healAmount = 18;
				toDelayCombat = combatDelay;
				drinkDelay = 3;
			break;
			case 7060: healAmount = 22; break;
			case 7946: healAmount = 16; break;
			case 4564: healAmount = 40; break;
                    
		}
		requestAnim(829, 0);
		getActionSender().addSoundEffect(this, 2393, 1, 0, 0);
		updateHP(healAmount, true);
		getActionSender().sendMessage(this, "You eat the "+Engine.items.getItemName(foodID)+".");
		combatDelay = toDelayCombat;
		eatDelay = toDelayEat;
	}

	public String lastKilled = "";
	public boolean receivesPVPDrop(Player p, Player opp) {
		if (Server.socketListener.getAddress(p.socket.socket).equals(Server.socketListener.getAddress(opp.socket.socket))) {
			return false;
		}
		return true;
	}
	public void msgToAll(String msg)
	{
	for(Player ap : Server.engine.players)
	{
	if(ap != null)
	{
	getActionSender().sendMessage(ap,msg);
	}
	}
	}

public void appendDeath() {
Server.engine.newNPC(2862, this.absX-1, this.absY, this.heightLevel, 0, 0, 0, 0, false, 0);
if (deathDelay == 0 && isDead) {
Player opp = Server.engine.players[hitIndex];

try {
if (rights < 4) {
dropStuff(opp.username);
opp.kills++;
opp.totalKills++;

DC++;
opp.KC++;
}
getActionSender().sendMessage(opp, getKillMessage(username));
if (receivesPVPDrop(this, opp)) {
   getActionSender().sendMessage(opp, getKillMessage(username));
opp.kills++;
opp.totalKills++;

DC++;
opp.KC++;
}
if (InBounty == 0) {
getActionSender().sendMessage(opp, getKillMessage(username));
}
/*if (Server.socketListener.getAddress(socket.socket).equals(opp.lastKilled)) {
Engine.fileManager.appendData("characters/logs/flagged.txt", opp.username);
}*/
opp.lastKilled = Server.socketListener.getAddress(socket.socket);
Engine.fileManager.appendData("characters/logs/kills/"+opp.username+".txt", username);
} catch (Exception e) {
}

for (int i = 0; i < skillLvl.length; i++) {
skillLvl[i] = getLevelForXP(i);
getActionSender().setSkillLvl(this, i);
}
for (int i = 0; i < 1000; i++) {
requestAnim(playerWeapon.getStandEmote(equipment[3]), 0);
}

if (opp != null) {
opp.attacking = null;
opp.attackedBy = null;
opp.oppIndex = 0;
opp.hitIndex = 0;
opp.resetAttack();
opp.requestFaceTo(65535);
}
attacking = null;
attackedBy = null;
oppIndex = 0;
hitIndex = 0;
resetAttack();

if(InBounty == 1) {
int i = heightLevel;
if(opp != null) {
if (opp.playerId == bhTarget || (playerId == opp.bhTarget)) {
opp.getActionSender().setInterfaceConfig(opp, 653, 9, false);
getActionSender().sendMessage(opp, "You killed "+username+". They were your target, so your Hunter PvP rating increases!");
leftBhTimer = 120;
this.InBounty = 0;
//opp.bhTarget = Engine.BountyHunter.getTargetHigh(opp);
Engine.BountyHunter.removeHigh(this);
Engine.BountyHunter.exit(this, 3);
Engine.BountyHunter.exit(this, 2);
Engine.BountyHunter.exit(this, 1);
}

if(InBounty == 1) {
if(opp != null) {
if (opp.playerId != bhTarget || (playerId != opp.bhTarget)) {
opp.bhLeave = 180;
opp.getActionSender().setInterfaceConfig(opp, 653, 9, false);
getActionSender().sendMessage(opp, "You killed "+username+". They were not your target, so your Rougue PvP rating increases!");
getActionSender().sendMessage(opp, "This means that you got the pickup penalty, pick up anything and you cant leave!");
leftBhTimer = 120;
this.InBounty = 0;
this.bhLeave = 0;
this.bhPickup = 0;
Engine.BountyHunter.removeHigh(this);
Engine.BountyHunter.exit(this, 3);
Engine.BountyHunter.exit(this, 2);
Engine.BountyHunter.exit(this, 1);
}
} else {
//opp.bhTarget = Engine.BountyHunter.getTargetHigh(this);
}
Player p3 = Engine.players[opp.bhTarget];
if(p3 != null) {
opp.getActionSender().setString(opp, "" + p3.username , 653, 8);
} else {
opp.getActionSender().setString(opp, "None" , 653, 8);
}
}
getActionSender().removeOverlay(this);
headIconSkull = -1;
appearanceUpdateReq = updateReq = true;
if(i == 0) {
Engine.BountyHunter.removeLow(this);
} else if(i == 0) {
Engine.BountyHunter.removeMid(this);
} else {
Engine.BountyHunter.removeHigh(this);
}
setCoords(3172, 3675, 0);
} else {
Engine.BountyHunter.exit(this, 3);
Engine.BountyHunter.exit(this, 2);
Engine.BountyHunter.exit(this, 1);
setCoords(3661, 3497, 0);
}
}


if (inClan() && isPVP()) setCoords (2809, 5511, 4); else setCoords(2592, 3414, 0);
getActionSender().sendMessage(this, "daaayyyuuuummmm nica got jumped");
getActionSender().sendMessage(this, "If you died in PvP, you must run back for your Un-tradables.");
deathDelay = -1;
isDead = false;

magicGraphicDelay = -1;
magicDamageDelay = -1;

initialAttack = false;
isSkulled = false;
headIconSkull = -1;
skullVanishDelay = 0;

isPoisoned = false;
poisonHitCount = 0;
poisonDelay = 0;

specAmount = 1000;
getActionSender().setConfig2(this, 300, 1000);

resetPrayer();

getActionSender().removeOverlay(this);

appearanceUpdateReq = true;
updateReq = true;
  try {

			
                        Engine.fileManager.saveBackup(this);
		} catch (Exception e) {
                    getActionSender().sendMessage(this, "Error saving.");
		}



		}
          
        }

		



	public void resetAttack() {
		attackingPlayer = false;
		enemyIndex = 0;
		oppIndex = 0;
		hitOne = false;
		hitTwo = false;
		hitThree = false;
		hitFour = false;
		hit1 = 0;
		hit2 = 0;
		hit3 = 0;
		hit4 = 0;
		specDelay = -1;
		secondSpecDelay = -1;
	}

    /**
     * Get xp based on your level.
     * @param skillId The skill level to get the level based off XP for.
     * @return Returns the level based on the amount of XP the specified skill has.
     */
    public int getLevelForXP(int skillId) {
        int exp = skillXP[skillId];
        int points = 0;
        int output = 0;
        for (int lvl = 1; lvl < 100; lvl++) {
            points += Math.floor((double)lvl + 300.0 * Math.pow(2.0, (double)lvl / 7.0));
            output = (int)Math.floor(points / 4);
            if ((output - 1) >= exp) {
                return lvl;
            }
        }
        return 99;
    }

    /**
     * Calculates equipment bonus.
     */
    public void calculateEquipmentBonus() {
        for (int i = 0; i < equipmentBonus.length; i++) {
            equipmentBonus[i] = 0;
        }
        for (int i = 0; i < equipment.length; i++) {
            if (equipment[i] > -1) {
                for (int j = 0; j < Engine.items.maxListedItems; j++) {
                    if (Engine.items.itemLists[j] != null) {
                        if (Engine.items.itemLists[j].itemId == equipment[i]) {
                            for (int k = 0; k < equipmentBonus.length; k++) {
                                equipmentBonus[k] += Engine.items.itemLists[j].bonuses[k];
                            }
                            break;
                        }
                    }
                }
            }
        }
    }

    /**
     * Writes the equipment bonus.
     */
    public void setEquipmentBonus() {
        String[] bonusNames = Misc.bonusNames;
        int id = 35;
        for (int i = 0; i < equipmentBonus.length; i++) {
            getActionSender().setString(this, bonusNames[i] + ": " + (equipmentBonus[i] >= 0 ? "+" : "") + equipmentBonus[i], 667, id++);
            if (id == 45) {
                id = 47;
            }
        }
        getActionSender().setString(this, "Summoning: +0", 667, 45);
    }

	public void openBank() {
		getActionSender().setConfig2(this, 563, 4194304); //???
		getActionSender().setConfig(this, 115, withdrawNote ? 1 : 0);
		getActionSender().setConfig(this, 305, insertMode ? 1 : 0);
		Engine.playerBank.sendTabConfig(this);
		getActionSender().showInterface(this, 762);
		getActionSender().setInventory(this, 763);
		getActionSender().setBankOptions(this);
		getActionSender().setItems(this, -1, 64207, 95, bankItems, bankItemsN);
		getActionSender().setItems(this, -1, 64209, 93, items, itemsN);
		//getActionSender().hideTabs(this); code to hide inventory & tabs
		getActionSender().setInterfaceConfig(this, 762, 18, true); //Hide some buttons
		getActionSender().setInterfaceConfig(this, 762, 19, true); //Remove it
		getActionSender().setInterfaceConfig(this, 762, 23, true); //If ya want
		viewingBankTab = 10;																																																				getActionSender().setString(this, "Bank of X-Scape", 762, 24);
		getActionSender().setString(this, ""+Engine.playerBank.getFreeBankSlot(this), 762, 97);
		getActionSender().setString(this, ""+Engine.playerBank.SIZE, 762, 98);
	}

	public void updateHP(int difference, boolean heal) {
		if (skillLvl[3] == 0) {
			return;
		}
		if (heal) {
			skillLvl[3] += difference;
			if (skillLvl[3] > getLevelForXP(3)) {
				skillLvl[3] = getLevelForXP(3);
			}
		} else if (!heal) {
			skillLvl[3] -= difference;
			if (skillLvl[3] <= 0) {
				skillLvl[3] = 0;
				deathEmoteDelay = 3;
				isDead = true;
				attacking = null;
				attackedBy = null;
				Engine.playerMovement.resetWalkingQueue(this);
				requestFaceTo(65535);
			}
		}
		getActionSender().setSkillLvl(this, 3);
		if ((skillLvl[3] <= Math.round(getLevelForXP(3) * 0.10) && skillLvl[3] > 0) && usingPrayer(23)) {
			requestGFX(436, 0);
			getActionSender().addSoundEffect(this, 2681, 1, 0, 0);
			skillLvl[3] += Math.round(getLevelForXP(5) * 0.25);
			skillLvl[5] = 0;
			getActionSender().setSkillLvl(this, 5);
		}
	}

	/*public void jailrubble() {
			if (spellbookSwapTimer == 0) {
			if (absX == 3033 && absY == 2988 && heightLevel == 0) {
			getActionSender().sendMessage(this, "You search the rubble...");
			int findGear = Misc.random(20);
			if (findGear == 15) {
			getActionSender().sendMessage(this, "and find some diving gear!");
			Engine.playerItems.addItem(p, 7535, 1);
			Engine.playerItems.addItem(p, 7534, 1);
			} else {
			getActionSender().sendMessage(this, "but find nothing.");
			}
			}
			spellbookSwapTimer = 5;
			} else {
			return;
			}
	}*/

	/*public void jaildoor() {
			if (spellbookSwapTimer == 0) {
			getActionSender().sendMessage(this, "You attempt to unlock the door...");
			int unlock = Misc.random(20);
			if (absX == 3033 && absY == 2985 && heightLevel == 0 && unlock == 15) {
			setCoords(3033, 2986, 0);
			} else if (unlock <= 14 || unlock >= 16) {
			getActionSender().sendMessage(this, "but you fail.");
			return;
			}
			if (absX == 3033 && absY == 2986 && heightLevel == 0) {
			setCoords(3033, 2985, 0);
			}
			getActionSender().sendMessage(this, "and successfully unlock it then walk through.");
			spellbookSwapTimer = 5;
			} else {
			return;
			}
	}*/

    /**
     * Teleports a player.
     * @param x The x coordinate to teleport to.
     * @param y The y coordinate to teleport to.
     * @param height The height level to teleport to.
     * @param delay The delay before the teleport is done.
     * @param distance The offset you can teleport to, such as 1 for a max offset of x +/- 1 and y +/- 1.
     * @param emoteStart The emote to do right away.
     * @param emoteEnd The emote to do after teleporting.
     * @param gfxStart The graphic to do right away.
     * @param gfxStartH The height level to create the start graphic at.
     * @param gfxEnd The graphic to do after the teleport is done.
     * @param gfxEndH The finish graphic height.
     */
    public void teleportTo(int x, int y, int height, int delay, int distance, int emoteStart, int emoteEnd, int gfxStart, 
            int gfxStartH, int gfxEnd, int gfxEndH) {
	if (isDead) {
			return;
		}
	if (attackedBy != null) {	
		return;
		}
	if (teleblocked) {
		getActionSender().sendMessage(this, "You are teleport blocked!");
		return;
	}
	if (jailed > 0) {
		getActionSender().sendMessage(this, "You are jailed!");
		return;
	}
	/*if (getWildernessLevel() >= 20 && !lever) {
        if (!bountyArea()) {
		getActionSender().sendMessage(this, "A magical force stops you from teleporting.");
		return;
	}*/
        itemPickup = false;
        playerOption1 = false;
        playerOption2 = false;
        playerOption3 = false;
        npcOption1 = false;
        npcOption2 = false;
        objectOption1 = false;
        objectOption2 = false;
        attackingPlayer = false;
        clickDelay = delay + 2;
        teleDelay = delay;
        if (distance > 0) {
            int xType = Misc.random(1);
            int yType = Misc.random(1);
            int xOffset = Misc.random(distance);
            int yOffset = Misc.random(distance);
            if (xType == 1)
                x += -xOffset;
            else
                x += xOffset;
            if(yType == 1)
                y += -yOffset;
            else
                y += yOffset;
        }
        teleX = x;
        teleY = y;
	teleH = height;
        Engine.playerMovement.resetWalkingQueue(this);
        requestAnim(emoteStart, 0);
        //requestGFX(gfxStart, gfxStartH);
        requestGFX(1745,0);
		//teleFinishGFX = gfxEnd;
		teleFinishGFX = 1747;
        teleFinishGFXHeight = 0;//gfxEndH
        teleFinishAnim = emoteEnd;
    }
	public void teletabTo(int x, int y, int height, int delay, int distance, int emoteStart, int emoteEnd, int gfxStart, 
            int gfxStartH, int gfxEnd, int gfxEndH) {
	if (isDead) {
			return;
		}
	if (teleblocked) {
		getActionSender().sendMessage(this, "You are teleport blocked!");
		return;
	}
	if (jailed > 0) {
		getActionSender().sendMessage(this, "You are jailed!");
		return;
	}
	/*if (getWildernessLevel() >= 20 && !lever) {
        if (!bountyArea()) {
		getActionSender().sendMessage(this, "A magical force stops you from teleporting.");
		return;
	}*/
        itemPickup = false;
        playerOption1 = false;
        playerOption2 = false;
        playerOption3 = false;
        npcOption1 = false;
        npcOption2 = false;
        objectOption1 = false;
        objectOption2 = false;
        attackingPlayer = false;
        clickDelay = delay + 2;
        teleDelay = delay;
        if (distance > 0) {
            int xType = Misc.random(1);
            int yType = Misc.random(1);
            int xOffset = Misc.random(distance);
            int yOffset = Misc.random(distance);
            if (xType == 1)
                x += -xOffset;
            else
                x += xOffset;
            if(yType == 1)
                y += -yOffset;
            else
                y += yOffset;
        }
        teleX = x;
        teleY = y;
	teleH = height;
        Engine.playerMovement.resetWalkingQueue(this);
        requestAnim(emoteStart, 0);
        //requestGFX(gfxStart, gfxStartH);
        requestGFX(1745,0);
		//teleFinishGFX = gfxEnd;
		teleFinishGFX = 1747;
        teleFinishGFXHeight = 0;//gfxEndH
        teleFinishAnim = emoteEnd;
    }

    /**
     * Set the player's coordinates.
     * @param x The x coordinate to teleport to.
     * @param y The y coordinate to teleport to.
     */
    public void setCoords(int x, int y, int height) {
        teleportToX = x;
        teleportToY = y;
        heightLevel = height;
        didTeleport = true;
	loadStaticObjects();
    }

    /**
     * Req an animation for this player.
     * @param animId The amination to perform.
     * @param animD The delay before doing the animation.
     */
    public void requestAnim(int animId, int animD) {
        animReq = animId;
        animDelay = animD;
        animUpdateReq = true;
        updateReq = true;
    }

    /**
     * Req an graphic for this player.
     * @param gfxId The graphic to perform.
     * @param gfxD The delay or height or the gfx depending on the value.
     */
    public void requestGFX(int gfxId, int gfxD) {
        if (gfxD >= 100) {
            gfxD += 6553500;
        }
        gfxReq = gfxId;
        gfxDelay = gfxD;
        gfxUpdateReq = true;
        updateReq = true;
    }

    /**
     * Req this player faces NPC or player.
     * @param faceId The target to face.
     */
    public void requestFaceTo(int faceId) {
        faceToReq = faceId;
        faceToUpdateReq = true;
        updateReq = true;
    }

	int poisonDamage;
	int poisonIntrevals = 0;
	public int getStartingPoisonDamage(Player p2) {
		String name = "";
		int damage = 0;
		boolean usingRange = false;
		if ((p2.equipment[3] >= 839 && p2.equipment[3] <= 861) || (p2.equipment[3] >= 9174 && p2.equipment[3] <= 9185) || p2.equipment[3] == 11235) { //Ranged
			name = Engine.items.getItemName(equipment[13]);
			usingRange = true;
		} else { //Melee
			name = Engine.items.getItemName(p2.equipment[3]);
		}
		if (name.contains("(p++)")) {
			damage = usingRange ? 4 : 6;
		}
		if (name.contains("(p+)")) {
			damage = usingRange ? 3 : 5;
		}
		if (name.contains("(p)")) {
			damage = usingRange ? 2 : 4;
		}
		return damage;
	}

	public int totalDamageRecoiled;

	public void appendNPCHit(int damage, int poison) {
        	if (damage > skillLvl[3]) {
			damage = skillLvl[3];
		}
		updateHP(damage, false);
		if (!hit1UpdateReq) {
			hitDiff1 = damage;
			poisonHit1 = poison;
			hit1UpdateReq = true;
		} else {
			hitDiff2 = damage;
			poisonHit2 = poison;
			hit2UpdateReq = true;
		}
		updateReq = true;
	}

	public void appendHit(int damage, int poison) {
		Player opp = Server.engine.players[hitIndex];
		if (opp != null) {
			if (opp.isDead) {
				return;
			}
        		if (damage > skillLvl[3]) {
				damage = skillLvl[3];
			}
			updateHP(damage, false);
			if (autoRetaliate) {
				enemyIndex = hitIndex;
				attackingPlayer = true;
			}
			int[] skill = {-1, -1, -1};
			if (!usingMage) {
				switch (opp.attackStyle()) {
					case 1:
						skill[0] = 0;
						skill[1] = 0;
						skill[2] = 0;
					break;
					case 2:
						skill[0] = 2;
						skill[1] = 2;
						skill[2] = 2;
					break;
					case 3:
						skill[0] = 1;
						skill[1] = 1;
						skill[2] = 1;
					break;
					case 4:
						skill[0] = 0;
						skill[1] = 1;
						skill[2] = 2;
					break;
					case 5:
					case 6:
						skill[0] = 4;
						skill[1] = 4;
						skill[2] = 4;
					break;
					case 7:
						opp.appendExperience((damage * 500), 4);
						opp.appendExperience((damage * 500), 1);
						opp.appendExperience((damage * 500), 3);
					break;
				}
				for (int i : skill) {
					if (i != -1) {
						opp.appendExperience((damage * 500), i);
					}
				}
				opp.appendExperience((damage * 500), 3);
			}
			
    if (opp.usingPrayer(24)) { //soulsplit effect
        int toHitpoints = (int)Math.floor(damage / 5);
        int toPrayer = (int)Math.floor(damage / 5);
	int casterX = opp.absX;
        int casterY = opp.absY;
        int offsetX = (opp.playerId) * playerId;
        int offsetY = (opp.playerId) * playerId;
        if (opp.skillLvl[3] + toHitpoints > opp.getLevelForXP(3))
            toHitpoints = opp.getLevelForXP(3) - opp.skillLvl[3];
        opp.getActionSender().slopedProjectile(opp, casterY, casterX, offsetY, offsetX, 50, 0, 384, 46, 31, opp.playerId, 1);
        getActionSender().slopedProjectile(opp, casterY, casterX, offsetY, offsetX, 50, 0, 384, 46, 31, playerId, 1);
			opp.requestAnim(14, 0);
			requestGFX(1314, 0);
        if (skillLvl[3] - damage <= 0) {
            if ((int)Math.floor(damage / 4) + opp.skillLvl[3] <= opp.getLevelForXP(3))
                toHitpoints = (int)Math.floor(damage / 4);
            else
                toHitpoints = opp.getLevelForXP(3) - opp.skillLvl[3];
        }     
        opp.getActionSender().slopedProjectile(opp, casterY, casterX, offsetY, offsetX, 50, 0, 384, 46, 31, opp.playerId, 1);
        getActionSender().slopedProjectile(opp, casterY, casterX, offsetY, offsetX, 50, 0, 384, 46, 31, playerId, 1);
			opp.requestAnim(14, 0);
			requestGFX(1314, 0);       
        opp.skillLvl[3] += toHitpoints;
        opp.getActionSender().setSkillLvl(this, 3);
        if (skillLvl[5] - toPrayer < 0)
            toPrayer = getLevelForXP(5) - skillLvl[5];
        skillLvl[5] -= toPrayer;
        getActionSender().setSkillLvl(this, 5);
        opp.getActionSender().slopedProjectile(opp, casterY, casterX, offsetY, offsetX, 50, 0, 384, 46, 31, opp.playerId, 1);
        getActionSender().slopedProjectile(opp, casterY, casterX, offsetY, offsetX, 50, 0, 384, 46, 31, playerId, 1);
    }
                   if (opp.equipment[5] == 13742) {//Elysian Effect
                        if ((Misc.random(4) == 2 || Misc.random(4) == 3 || Misc.random(4) == 4 || Misc.random(4) == 0)) {
                            double damages = damage;
                            double damageDeduction = ((double)damages)/((double)4);
                            damage = damage-((int)Math.round(damageDeduction));
	getActionSender().sendMessage(this, "Your unbreakable shield absorbs some of the blow.");
                        }
                    }
                    if (opp.equipment[5] == 13740) {//Divine Effect
                            double damages2 = damage;
                            double prayer = opp.skillLvl[5];
                            double possibleDamageDeduction = ((double)damages2)/((double)3.33);//30% of Damage Inflicted
                            double actualDamageDeduction;
                            if ((prayer * 2) < possibleDamageDeduction) {
                            actualDamageDeduction = (prayer * 2);//Partial Effect(Not enough prayer points)
								getActionSender().sendMessage(this, "Your faithful prayers partially protect you.");
                            } else {
                            actualDamageDeduction = possibleDamageDeduction;//Full effect
															getActionSender().sendMessage(this, "Your faithful prayers protect you with their great strength.");
                            }
                            double prayerDeduction = ((double)actualDamageDeduction)/((double)2);//Half of the damage deducted
                            damage = damage-((int)Math.round(actualDamageDeduction));
                            opp.skillLvl[5] = opp.skillLvl[5]-((int)Math.round(prayerDeduction));
                            opp.getActionSender().setSkillLvl(opp, 5);
                    }
			if (opp.usingPrayer(14) && poison == 0) { //Leech STR
			opp.requestAnim(14, 0);
					skillLvl[2] -= 0.10;
					getActionSender().setSkillLvl(this, 2);
				}
			if (opp.usingPrayer(20) && poison == 0) { //Leech RNG
			opp.requestAnim(14, 0);
					skillLvl[4] -= 0.10;
					getActionSender().setSkillLvl(this, 4);
				}
			if (opp.usingPrayer(21) && poison == 0) { //Leech MAGE
			opp.requestAnim(14, 0);
					skillLvl[6] -= 0.10;
					getActionSender().setSkillLvl(this, 6);
				}
			if (opp.usingPrayer(13) && poison == 0) { //Leech DEF
			opp.requestAnim(14, 0);
					skillLvl[1] -= 0.10;
					getActionSender().setSkillLvl(this, 1);
				}
			if (opp.usingPrayer(15) && poison == 0) { //Leech ATT
			opp.requestAnim(14, 0);
					skillLvl[0] -= 0.10;
					getActionSender().setSkillLvl(this, 0);
				}
			if (usingPrayer(17) && poison == 0) { //Deflect Magic (HITS)
				if (damage > 0) {
					if (skillLvl[3] > 0) {
					int recoil = 1 + ((int)Math.floor(damage * 0.10));
					opp.append1Hit(recoil, 0);
				} else {
					opp.append1Hit(0, 0);
				}
			}
		}
			if (usingPrayer(18) && poison == 0) { //Deflect Range (HITS)
				if (damage > 0) {
					if (skillLvl[3] > 0) {
					int recoil = 1 + ((int)Math.floor(damage * 0.10));
					opp.append1Hit(recoil, 0);
				} else {
					opp.append1Hit(0, 0);
				}
			}
		}
			if (usingPrayer(19) && poison == 0) { //Deflect Melee (HITS)
				if (damage > 0) {
					if (skillLvl[3] > 0) {
					int recoil = 1 + ((int)Math.floor(damage * 0.10));
					opp.append1Hit(recoil, 0);
				} else {
					opp.append1Hit(0, 0);
				}
			}
		}
			if (vengeance && poison == 0) { //Vengeance
				if (damage > 0) {
					if (skillLvl[3] > 0) {
						opp.append1Hit(((int)Math.floor(damage * 0.75)), 0);
					} else {
						opp.append1Hit(0, 0);
					}
					requestForceChat("Taste vengeance!");
					vengeance = false;
				}
			}
			if (equipment[12] == 2550 && poison == 0) { //Ring of recoil
				if (damage > 0) {
					if (skillLvl[3] > 0) {
						int recoil = 1 + ((int)Math.floor(damage * 0.10));
						opp.append1Hit(recoil, 0);
						totalDamageRecoiled += recoil;
						if (totalDamageRecoiled >= 40) {
							equipment[12] = -1;
							equipmentN[12] = 0;
							getActionSender().setItems(this, 387, 28, 93, equipment, equipmentN);
							getActionSender().sendMessage(this, "Your ring of recoil has shattered.");
							totalDamageRecoiled = 0;
						}
					} else {
						opp.append1Hit(0, 0);
					}
				}
			}
			if (opp.barrowsSet(3) && poison == 0) { //Guthans affect
				if (Math.random() <= 0.25) {
					opp.updateHP(damage, true);
					requestGFX(398, 0);
				}	
			}
			if (!hit1UpdateReq) {
				hitDiff1 = damage;
				poisonHit1 = poison;
				hit1UpdateReq = true;
			} else {
				hitDiff2 = damage;
				poisonHit2 = poison;
				hit2UpdateReq = true;
			}
			updateReq = true;
		}
	}

    /**
     * Append damage.
     */
    public void append1Hit(int damage, int poison) {
        if (damage > skillLvl[3]) {
            damage = skillLvl[3];
        }
        updateHP(damage, false);
	if (!hit1UpdateReq) {
		hitDiff1 = damage;
		poisonHit1 = poison;
		hit1UpdateReq = true;
	} else {
		hitDiff2 = damage;
		poisonHit2 = poison;
		hit2UpdateReq = true;
	}
        updateReq = true;
    }

    /**
     * Block anims.
     */
    public int getBlockAnim() {
	if (equipment[3] == 4031) {
		return 221;
	}
	if (equipment[5] == 8850) {
		return 4177;
	}
    return 1156;
    }

    /**
     * Block anims.
     */
    public int getBlockAnim1() {
	if (equipment[3] == 4031) {
		return 221;
	}
	if (equipment[3] == 4151) {
		return 1659;
	}
	if (equipment[3] == 4718 ||
	    equipment[3] == -1) {
		return 424;
	}
	if (equipment[3] == 4755) {
		return 2063;
	}
	if (equipment[3] == 10887) {
		return 5866;
	}
	if (equipment[3] == 4153) {
		return 1666;
	}
	if (equipment[3] == 11694 ||
	    equipment[3] == 11696 ||
	    equipment[3] == 11698 ||
	    equipment[3] == 11700 ||
	    equipment[3] == 1307 ||
	    equipment[3] == 1309 || 
            equipment[3] == 1311 || 
	    equipment[3] == 1313 || 
	    equipment[3] == 1315 ||
	    equipment[3] == 1317 ||
	    equipment[3] == 1319 ||
	    equipment[3] == 7158 ||
	    equipment[3] == 11730) {
		return 7050;
	}
	if (equipment[3] == 3204) {
		return 420;
	}
    return 404;
    }

    /**
     * Force chat text.
     */
    public void requestForceChat(String s) {
        forceChat = s;
        forceChatUpdateReq = true;
        updateReq = true;
    }
		public boolean quickChat;
	public void requestForceChat2(String s) {
		quickChat = true;
		chatTextUpdateReq = true;
		chatText = s;
		updateReq = true;
	}

    public int getArmourDef() {
	switch (equipment[4]) {

		case 1127: return 80;
		case 4712: return 120;
		case 4757: return 140;
		case 4720: return 140;
		case 3140: return 100;
		case 10551: return 70;
		case 11720: return 215;
		case 11724: return 220;
	}
    return 10;
    }

    public int getRangeArmourDef() {
	switch (equipment[4]) {

		case 1135: return 115;
		case 2499: return 140;
		case 2501: return 165;
		case 2503: return 190;
		case 11720: return 200;
	}
    return 40;
    }

    public int getRangeBonus() {
	switch (equipment[3]) {

		case 841: return 65;
		case 843: return 80;
		case 845: return 85;
		case 847: return 90;
		case 849: return 95;
		case 851: return 100;
		case 853: return 120;
		case 855: return 130;
		case 857: return 150;
		case 859: return 165;
		case 861: return 180;
		case 9185: return 280;
		case 11235: return 160;
	}
    return 60;
    }

	public boolean defile;

	public void checkVeracs() {
		if (barrowsSet(6)) {
			if (Math.random() <= 0.15) {
				defile = true;
			}
		} else {
			defile = false;
		}
	}

	public double accuracy = 1;

	public double meleeAccuracy(Player opp) {
		if (opp == null || this.isDead || opp.isDead || this.disconnected[0] || opp.disconnected[0]) {
			return 1;
		}
		double attack = skillLvl[0];
		double defence = opp.skillLvl[1];
		int activeAttackBonus = weaponStyle(equipment[3]) - 1;
		int activeDefenceBonus = activeAttackBonus + 5;
		double attackBonus = equipmentBonus[activeAttackBonus];
		double defenceBonus = opp.equipmentBonus[activeDefenceBonus];
		if (usingPrayer(2)) {
			attack *= 1.05;
		}
		if (usingPrayer(7)) {
			attack *= 1.10;
		}
		if (usingPrayer(15)) {
			attack *= 1.15;
		}
		if (usingPrayer(25)) {
			attack *= 1.15;
		}
		if (usingPrayer(26)) {
			attack *= 1.20;
		}
		if (attackStyle() == 4) {
			attack += 1;
		}
		if (attackStyle() == 1) {
			attack += 3;
		}
		if (opp.usingPrayer(0)) {
			defence *= 1.05;
		}
		if (opp.usingPrayer(5)) {
			defence *= 1.10;
		}
		if (opp.usingPrayer(13)) {
			defence *= 1.15;
		}
		if (opp.usingPrayer(25)) {
			defence *= 1.20;
		}
		if (opp.usingPrayer(26)) {
			defence *= 1.25;
		}
		if (opp.attackStyle() == 4) {
			defence += 1;
		}
		if (opp.attackStyle() == 3) {
			defence += 3;
		}
		if (attackBonus < 0) {
			attackBonus = 0;
		}
		if (defenceBonus < 0) {
			defenceBonus = 0;
		}
		if (defile) {
			defenceBonus = 0;
		}
		double offensiveAttribute = (attack * 1.5) + attackBonus;
		double defensiveAttribute = (defence * 1.5) + defenceBonus;
		double difference = Math.abs(offensiveAttribute - defensiveAttribute);
		boolean positive = offensiveAttribute > defensiveAttribute;
		double interval = difference * 0.0015;
		double percentage = 0.55;
		if (!positive) {
			percentage -= interval;
		}
		if (positive) {
			percentage += interval;
		}
		if (accuracy != 1) {
			percentage *= accuracy;
			accuracy = 1;
		}
		return percentage;
	}

	public boolean hitPlayer(Player opp) {
		return Math.random() <= meleeAccuracy(opp);
	}
	public double rangeAccuracy(Player opp) {
		if (opp == null || this.isDead || opp.isDead || this.disconnected[0] || opp.disconnected[0]) {
			return 1;
		}
		double range = skillLvl[4];
		double defence = opp.skillLvl[1];
		double rangeBonus = equipmentBonus[4];
		double defenceBonus = opp.equipmentBonus[9];
		if (usingPrayer(3)) {
			range *= 1.05;
		}
		if (usingPrayer(11)) {
			range *= 1.10;
		}
		if (usingPrayer(20)) {
			range *= 1.15;
		}
		if (attackStyle() == 5) {
			range += 1;
		}
		if (opp.usingPrayer(0)) {
			defence *= 1.05;
		}
		if (opp.usingPrayer(5)) {
			defence *= 1.10;
		}
		if (opp.usingPrayer(13)) {
			defence *= 1.15;
		}
		if (opp.usingPrayer(25)) {
			defence *= 1.20;
		}
		if (opp.usingPrayer(26)) {
			defence *= 1.25;
		}
		if (opp.attackStyle() == 4) {
			defence += 1;
		}
		if (opp.attackStyle() == 3) {
			defence += 3;
		}
		if (rangeBonus < 0) {
			rangeBonus = 0;
		}
		if (defenceBonus < 0) {
			defenceBonus = 0;
		}
		double offensiveAttribute = range + rangeBonus;
		double defensiveAttribute = defence + defenceBonus;
		double difference = Math.abs(offensiveAttribute - defensiveAttribute);
		boolean positive = offensiveAttribute > defensiveAttribute;
		double interval = difference * 0.00175;
		double percentage = 0.50;
		if (!positive) {
			percentage -= interval;
		}
		if (positive) {
			percentage += interval;
		}
		if (accuracy != 1) {
			percentage *= accuracy;
			accuracy = 1;
		}
		return percentage;
	}

	public boolean hitPlayerRange(Player opp) {	
		return Math.random() <= rangeAccuracy(opp);
	}

	public boolean barrowsSet(int setID) {
		String helmet = Engine.items.getItemName(equipment[0]);
		String platebody = Engine.items.getItemName(equipment[4]);
		String weapon = Engine.items.getItemName(equipment[3]);
		String platelegs = Engine.items.getItemName(equipment[7]);
		String set = "";
		switch (setID) {
			case 1:	//Ahrim's
				set = "Ahrim";
			break;
			case 2: //Dharok's
				set = "Dharok";
			break;
			case 3: //Guthan's
				set = "Guthan";
			break;
			case 4: //Karil's
				set = "Karil";
			break;
			case 5: //Torag's
				set = "Torag";
			break;
			case 6: //Verac's
				set = "Verac";
			break;
		}
		boolean hasHelmet = helmet.contains(set);
		boolean hasPlatebody = platebody.contains(set);
		boolean hasWeapon = weapon.contains(set);
		boolean hasPlatelegs = platelegs.contains(set);
		if (hasHelmet && hasPlatebody && hasWeapon && hasPlatelegs) {
			return true;
		}
		return false;
	}
	public boolean voidSet(int setID) {
		String helmet = Engine.items.getItemName(equipment[0]);
		String set = "";
		switch (setID) {
			case 1: //Melee
				set = "Void melee";
			break;
			case 2: //Range
				set = "Void ranger";
			break;
			case 3: //Mage
				set = "Void mage";
			break;
		}
		boolean hasHelmet = helmet.contains(set);
		boolean hasTop = equipment[4] == 8839;
		boolean hasGloves = equipment[9] == 8842;
		boolean hasBottom = equipment[7] == 8840;
		if (hasHelmet && hasTop && hasGloves && hasBottom) {
			return true;
		}
		return false;
	}

	public boolean hasProperArrows(int bow, int arrows) {
		if (usingSpecial) {
			if (weapon == 861 || weapon == 11235) {
				if (equipmentN[13] <= 1) {
					getActionSender().sendMessage(this, "You don't have enough ammo!");
					return false;
				}
			}
		}
		if (bow >= 839 && bow <= 861) {
			switch (arrows) {
				case 882:
				case 884:
				case 886:
				case 888:
				case 890:
				case 892: return true;
			}
		}
		if (bow == 11235) {
			switch (arrows) {
				case 882:
				case 884:
				case 886:
				case 888:
				case 890:
				case 892:
				case 11212:
					if (equipmentN[13] > 1) {
						return true;
					} else {
						getActionSender().sendMessage(this, "You don't have enough ammo!");
						return false;
					}
			}
		}
		if (bow == 4214 || bow == 868 || bow == 11230) {
			return true;
		}
		if (bow == 9185) {
			switch (arrows) {
				case 9243:
				case 9244: return true;
			}
		}
		if (bow == 4734) {
			if (arrows == 4740) return true;
		}
		return false;
	}

    public boolean hitNpcRange() {
	PlayerCombat playCb = new PlayerCombat(this);
	if (getRandom(equipmentBonus[8] + getRangeBonus()) > getRandom(playCb.getNpcDefenceRange())) {
		return true;
	}
    return false;
    }

    private int getRandom(int range) {
        return (int)(Math.random() * (range + 1));
    }

    /**
     * Gives experience to a player.
     * @param amount The amount which will gives to the player
     * @param skillId the skill Id of the player which the exp will go to.
     */
    public void appendExperience(int amount, int skillId) {
	if (!getExperience) {
		return;
	}
	int oldLvl = getLevelForXP(skillId);
	if (skillXP[skillId] > 200000000) {
		return;
	}
	skillXP[skillId] += amount;
	int newLvl = getLevelForXP(skillId);
	if (oldLvl < newLvl) {
		skillLvl[skillId] += (newLvl - oldLvl);
		switch (skillId) {

			case 0:
				getActionSender().sendMessage(this, "Congratiolations, You have just advanced a new Attack level!");
			break;

			case 1:
				getActionSender().sendMessage(this, "Congratiolations, You have just advanced a new Defence level!");
			break;

			case 2:
				getActionSender().sendMessage(this, "Congratiolations, You have just advanced a new Strength level!");
			break;

			case 3:
				getActionSender().sendMessage(this, "Congratiolations, You have just advanced a new hitpoints level!");
			break;

			case 4:
				getActionSender().sendMessage(this, "Congratiolations, You have just advanced a new Ranged level!");
			break;

			case 5:
				getActionSender().sendMessage(this, "Congratiolations, You have just advanced a new Prayer level!");
			break;

			case 6:
				getActionSender().sendMessage(this, "Congratiolations, You have just advanced a new Magic level!");
			break;

			case 7:
				getActionSender().sendMessage(this, "Congratiolations, You have just advanced a new Cooking level!");
			break;

			case 8:
				getActionSender().sendMessage(this, "Congratiolations, You have just advanced a new Woodcutting level!");
			break;

			case 16:
				getActionSender().sendMessage(this, "Congratiolations, You have just advanced a new Agility level!");
			break;

			case 17:
				getActionSender().sendMessage(this, "Congratiolations, You have just advanced a new Thieving level!");
			break;

			case 18:
				getActionSender().sendMessage(this, "Congratiolations, You have just advanced a new Slayer level!");
			break;

			case 19:
				getActionSender().sendMessage(this, "Congratiolations, You have just advanced a new Farming level!");
			break;

			case 20:
				getActionSender().sendMessage(this, "Congratiolations, You have just advanced a new Runecrafting level!");
			break;

			default:
				getActionSender().sendMessage(this, "This level is unknown, Please report this to an administrator.");
			break;
		}
		requestGFX(1635, 0);
		appearanceUpdateReq = true;
		updateReq = true;
	}
	getActionSender().setSkillLvl(this, skillId);
    }

    /**
     * Checks special amount and gets config.
     */
     public void checkAmount() {
	int amount = specAmount;
	getActionSender().setConfig2(this, 300, amount);
    }

    /**
     * Walking request.
     */
	public void playerWalk(int x, int y, int emote, int delay) {
		int firstX = x - (mapRegionX - 6) * 8;
		int firstY = y - (mapRegionY - 6) * 8;
		Engine.playerMovement.resetWalkingQueue(this);
		Engine.playerMovement.addToWalkingQueue(this, firstX, firstY);
		appearanceUpdateReq = true;
		updateReq = true;
	}

    public boolean inWilderness() {
	return absX >= 3136 && absX <= 3350 && absY >= 3525 && absY <= 3597;
    }

    /**
     * Cross wilderness ditch
     */
    public void crossDitch() {
	int y = 3520;
	if (!wildernessZone(absX, absY)) {
		y = 3523;
	}
	playerWalk(absX, y, 6132, 0);
        appearanceUpdateReq = true;
        updateReq = true;
    }


    /**
     * Boolean which returns coords ids to get safezone of bounty hunter place.
     */
    public boolean pkArenaPart1() {
	return absX >= 3136 && absX <= 3192 && absY >= 3651 && absY <= 3701;
    }
    public boolean pkArenaPart2() {
	return absX >= 3136 && absX <= 3192 && absY >= 3651 && absY <= 3701;
    }

    public boolean inPkArena() {
	return pkArenaPart1() && pkArenaPart2();
    }

	void updateStats() {
		if (isDead) {
			return;
		}
		int skill = 0;
		while (skill <= 22) {
			if (skill == 3 || skill == 5) {
				skill++;
			}
			if (skillLvl[skill] > getLevelForXP(skill)) {
				skillLvl[skill]--;
			}
			if (skillLvl[skill] < getLevelForXP(skill)) {
				skillLvl[skill]++;
			}
			statDelay = 100;
			getActionSender().setSkillLvl(this, skill);
			skill++;
		}
	}
	void restoreHP() {
		if (isDead) {
			return;
		}
		if (skillLvl[3] > getLevelForXP(3)) {
			skillLvl[3]--;
		}
		if (skillLvl[3] < getLevelForXP(3)) {
			skillLvl[3] += equipment[9] == 11133 ? 2 : 1;
		}
		hpDelay = 100;
		getActionSender().setSkillLvl(this, 3);
	}

    /**
     * Unequip and delete all items player has.
     */
    public void dropAllStuff() {
	if (this == null)
		return;
	PlayerItems pi = new PlayerItems();
        for(int i = 0; i < items.length; i++)
        {
            if(items[i] >= 0)
            {
                if(Engine.items.isUntradable((items[i])))
                {
                    Engine.playerItems.deleteItem(this, items[i], pi.getItemSlot(this, items[i]), itemsN[i]);
                }
                else
                {
                    Engine.playerItems.deleteItem(this, items[i], pi.getItemSlot(this, items[i]), itemsN[i]);
                }
            }
        }
        for(int i = 0; i < equipment.length; i++)
        {
            if(equipment[i] >= 0)
            {
		getActionSender().removeEquipment(this, equipment[i], i);
                if(Engine.items.isUntradable((items[0])))
                {
                    Engine.playerItems.deleteItem(this, items[0], pi.getItemSlot(this, items[0]), itemsN[0]);

                }
                else
                {
                    Engine.playerItems.deleteItem(this, items[0], pi.getItemSlot(this, items[0]), itemsN[0]);
                }
            }
        }
	combatType = 0;
    }

	public boolean arrayContains(int[] array, int value) {
		for (int i : array) {
			if (value == i) {
				return true;
			}
		}
		return false;
	}

	public int[] removeValueFromArray(int[] array, int value) {
		int index = -1;
		for (int i = 0; i < array.length; i++) {
			if (value == array[i]) {
				index = i;
			}
		}
		if (index != -1) {
			array[index] = 0;
		}
		return array;
	}

	public int[][] removeValueFromArray(int[][] array, int value) {
		int indexI = -1;
		int indexJ = -1;
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				if (value == array[i][j]) {
					indexI = i;
					indexJ = j;
				}
			}
		}
		if (indexI != -1 && indexJ != -1) {
			array[indexI][indexJ] = -1;
		}
		return array;
	}

	void dropStuff(String opp) {
		try {
                        int[] clanwarsItems = new int[items.length + equipment.length];
                        /*if (inClan() && receivesPVPDrop(this, Server.engine.players[Server.engine.getIdFromName(opp)])) {
                        PVPDrop(opp, clanwarsItems);
                        return;
			}activate this if u want clan wars drops makes sure to disable the 1 below*/
                    if (inClan()) {
                        return;
			}
			int amountOfKeptItems = isSkulled ? (usingPrayer(10) ? 1 : 0) : (usingPrayer(10) ? 4 : 3);
			int[] allItems = new int[items.length + equipment.length];
			int[] allItemsN = new int[itemsN.length + equipmentN.length];
			int[] keptItems = new int[amountOfKeptItems];
			int[] toAdd = new int[keptItems.length];
			System.arraycopy(items, 0, allItems, 0, items.length);
			System.arraycopy(equipment, 0, allItems, items.length, equipment.length);
			System.arraycopy(itemsN, 0, allItemsN, 0, itemsN.length);
			System.arraycopy(equipmentN, 0, allItemsN, itemsN.length, equipmentN.length);
			for (int i = 0; i < keptItems.length; i++) {
				int index = 0;
				int standing = 0;
				for (int j = 0; j < allItems.length; j++) {
					if (allItems[j] < 1) {
						continue;
					}
					int price = Engine.items.itemLists[allItems[j]].shopValue;
					if (price > standing) {
						index = j;
						standing = price;
					}
				}
				keptItems[i] = allItems[index];
				toAdd[i] = allItems[index];
				allItemsN[index]--;
				if (allItemsN[index] == 0) {
					allItems[index] = 0;
				}
			}
			for(int i = 0; i < items.length; i++) {
				if(items[i] > 0) {
					if (!arrayContains(keptItems, items[i])) {
						if (Engine.items.isUntradable((items[i]))) {
							if (items[i] != 13899) {
								Engine.items.createGroundItem(items[i], itemsN[i], absX, absY, heightLevel, username);
							} else {
								getActionSender().sendMessage(this, "Your Vesta's longsword shatters as it hits the ground.");
								degrade = 6000;
								degrades = false;
							}
							Engine.playerItems.deleteItem(this, items[i], Engine.playerItems.getItemSlot(this, items[i]), itemsN[i]);
						} else {
							if (!isPVP()) {
								Engine.items.createGroundItem(items[i], itemsN[i], absX, absY, heightLevel, opp);
							}
						}
					} else {
						keptItems = removeValueFromArray(keptItems, items[i]);
					}
					Engine.playerItems.deleteItem(this, items[i], Engine.playerItems.getItemSlot(this, items[i]), itemsN[i]);
				}
			}
			for(int i = 0; i < equipment.length; i++) {
				if (equipment[i] > 0) {
					getActionSender().removeEquipment(this, equipment[i], i);
					if (!arrayContains(keptItems, items[0])) {
						if(Engine.items.isUntradable((items[0]))) {
							if (items[0] != 13899) {
								Engine.items.createGroundItem(items[0], itemsN[0], absX, absY, heightLevel, username);
							} else {
								getActionSender().sendMessage(this, "Your Vesta's longsword shatters as it hits the ground.");
								degrade = 6000;
								degrades = false;
							}
							Engine.playerItems.deleteItem(this, items[0], Engine.playerItems.getItemSlot(this, items[0]), itemsN[0]);
						} else {
							if (!isPVP()) {
								Engine.items.createGroundItem(items[0], itemsN[0], absX, absY, heightLevel, opp);
							}
                                                        
						}
					} else {
						keptItems = removeValueFromArray(keptItems, items[0]);
					}
					Engine.playerItems.deleteItem(this, items[0], Engine.playerItems.getItemSlot(this, items[0]), itemsN[0]);
				}
			}
			for (int i : toAdd) {
				if (i > 0) {
					Engine.playerItems.addItem(this, i, 1);
				}
			}
			Engine.items.createGroundItem(526, 1, absX, absY, heightLevel, opp);
			int[] lostItems = allItems;
			if (isPVP() && receivesPVPDrop(this, Server.engine.players[Server.engine.getIdFromName(opp)])) {
				PVPDrop(opp, lostItems);
			}

                       
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Null drop: "+opp);
			return;
		}
	}

	public int totalValue(int[] items) {
		int totalValue = 0;
		try {
			int[] allItems = new int[items.length + equipment.length];
			System.arraycopy(items, 0, allItems, 0, items.length);
			System.arraycopy(equipment, 0, allItems, items.length, equipment.length);

			for (int i : allItems) {
				totalValue += getItemValue(i);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return totalValue;
	}
	

	void PVPDrop(String player, int[] lostItems) {
		Player dropFor = Server.engine.players[Server.engine.getIdFromName(player)];
		try {
			if (dropFor.setDrop != 0) {
				Engine.items.createGroundItem(dropFor.setDrop, 1, absX, absY, heightLevel, player);
				dropFor.setDrop = 0;
			}
			if (Double.isInfinite(dropFor.PVPPotential) || Double.isNaN(dropFor.PVPPotential)) {
				dropFor.PVPPotential = 0.0;
			}
			double chance = 0.00;
			int randomDrops = 0;
			for (int i : lostItems) {
				if (Math.random() <= 0.75) {
					lostItems = removeValueFromArray(lostItems, i);
				} else {
					if (getItemValue(i) >= 3000) {
						randomDrops++;
					} else if (Math.random() <= 0.25) {
						randomDrops++;
					}
				}

			}
			for (int i : lostItems) {
				if (i == 0 || Engine.items.isUntradable(i)) {
					continue;
				}
				Engine.items.createGroundItem(i, 1, absX, absY, heightLevel, player);
			}
			int[] potentialDrops = {1145,1147,2605,2613,2619,2627,2657,2673,3385,3486,3749,3751,3753,3755,4716,4724,4745,4753,5574,6128,6131,6137,10350,10589,10606,10828,11200,11335,1073,1079,1091,1093,1123,1127,2599,2601,2607,2609,2615,2617,2623,2625,2653,2655,2661,2669,2671,3474,3476,3480,3481,3483,3485,3670,3476,3674,3676,4087,4585,4720,4722,4728,4749,4751,4759,5575,5576,6129,6617,10346,10348,11720,11722,11724,1199,1201,2603,2611,2621,2629,2659,2667,2675,3488,10352,11726,11728,11730,11720,11718,11694,11696,11698,11700,11283,1187,1215,1231,1249,1263,1305,1377,1434,14484,3140,3204,4087,3587,5680,5698,6739,7158,9244,11212,11217,11227,11228,11229,11230,11231,11233,11234,11732,4091,4093,4095,4097,4099,4101,4103,4105,4107,4109,4111,4113,4115,4117,6916,6918,6920,6922,6924,4708,4710,4712,4714,4755,4757,4726,4730,4747,4732,4734,4735,4738,4740,4718,2491,2497,2503,10370,10386,6764,10368,10372,10374,10444,10450,10456,10460,10468,10474,10786,2663,3479,4151,6762,10384,10386,10388,10390,10440,10446,10452,10458,10464,10470,10784,6760,10376,10378,10380,10382,10442,10448,1454,10462,10466,10472,9672,9674,9676,9678,3122,4153,6809,10564,10330,10332,10334,10336,10338,10340,10342,10344,6731,6733,6735,6737,9185,1704,1706,1708,1710,1712,1725,1727,1729,1731,6585,10354,10356,10358,10360,10362,10364,10366,10719,10736,10738,6889,4129,4131,2579,6914,6912,139,141,143,169,171,173,175,177,179,2430,2442,2444,3040,3042,3044,3046,145,147,149,157,159,161,163,165,167,181,183,185,2436,2440,2448,3024,3026,3028,3030,6568,6524,6526,6523,6525,6522,6527,6528,11126,11124,11122,11120,11118,11133,13899,6199,6199,6199,6199,6199,6199,6199,14249,14249,14249}; //PVP Drops
			for (int i = 0; i < randomDrops; i++) {
				int item = potentialDrops[(int)Math.floor(Math.random() * potentialDrops.length)];
				chance = getPVPDropPercentage(item, totalValue(lostItems), dropFor.PVPPotential);
				if (Math.random() <= chance) {
					Engine.items.createGroundItem(item, 1, absX, absY, heightLevel, player);
					dropFor.PVPPotential -= ((1.00 - chance) / 10);
				} else {
					dropFor.PVPPotential += ((1.00 - chance) / 10);
				}
			}
		} catch (Exception e) {
		}
	}

	double getPVPDropPercentage(int item, int risk, double potential) {
		double base = 0.10 + potential * (hotZone(absX, absY) ? 3.00 : 1.50);
		double ratio = getItemValue(item) / 1457000;
		double value = base / ratio;
		if (value > 0.95) {
			value = 0.95;
		}
		return value;
	}

    void drainPrayer() {
	if (usingPrayer) {
		if (drainDelay == 0) {
			if (skillLvl[5] <= 1) {
				resetPrayer();
				skillLvl[5]--;
				getActionSender().setSkillLvl(this, 5);
				appearanceUpdateReq = true;
				updateReq = true;
				getActionSender().sendMessage(this, "You are out of prayer points. Please restore them at an prayer altar.");
				return;
			}
			skillLvl[5]--;
			getActionSender().setSkillLvl(this, 5);
		}
		drainDelay = 8;
		appearanceUpdateReq = true;
		updateReq = true;
	}
    }

    public void appendWilderness() {
	wildyLevel = getWildernessLevel();
	if (savedLevel != wildyLevel) {
		savedLevel = wildyLevel;
		if (wildyLevel > 0) {
            if (bountyArea() && (InBounty == 0)) {
                removeWilderness();
            }
			addWilderness();
		} else {
			removeWilderness();
		}
	}
    }
    public void removeWilderness() {
	
        getActionSender().setPlayerOption(this, "Null", 1, true);
	getActionSender().removeOverlay(this);
	getActionSender().setInterfaceConfig(this, 745, 6, true);
	getActionSender().setInterfaceConfig(this, 745, 3, false);
        getActionSender().setInterfaceConfig(this, 745, 1, true);
    }
    public void addWilderness() {
	getActionSender().setPlayerOption(this, "Attack", 1, true);
        if (InBounty == 0){
        
            if (!isPVP()) {
		getActionSender().setOverlay(this, 381);
                
	} else {
		getActionSender().setInterfaceConfig(this, 745, 6, false);
		getActionSender().setInterfaceConfig(this, 745, 3, true);
                getActionSender().setOverlay(this, 381);
		int max = combatLevel + wildyLevel;
		if (max > 126) {
			max = 126;
		}
		int min = (combatLevel - wildyLevel) + (((combatLevel % 10 >= 5) && ((combatLevel - wildyLevel) % 10 < 5) || (combatLevel >= 114)) ? 2 : 1);
		if (min < 3) {
			min = 3;
		}
		String range = min+" - "+max;
		getActionSender().setString(this, range, 745, 1);
	}
        }
        
    if (multiicon()){
    getActionSender().setInterfaceConfig(this, 745, 1, false);

    } else {
     getActionSender().setInterfaceConfig(this, 745, 1, true);
     getActionSender().setInterfaceConfig(this, 745, 6, false);
    }
    }
    
	
    public void updatePlayerList() {
		if (!onQuestTab) {
			return;
		} else {
			getActionSender().setString(this, "<col=33FF33>Players Online: " + Engine.getPlayerCount(), 274, 12);
		}
	}

public void OpenLatestVideo() {
try {
			java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
			desktop.browse(new URI("http://www.youtube.com/watch?v=H1iVYLyjE2M"));
					getActionSender().sendMessage(this, "This weeks best video has been opened in your browser");
		} catch (Exception e) {
					message("Syntax error.");
				}
	}

    public int wildernessLevels(int coordY) {
		wildyLevel = (coordY - 3254 / 7);
		if (wildyLevel < 1)
			wildyLevel = 1;
		return wildyLevel;
    }

    public boolean inEdge() {
	return absX >= 3040 && absX <= 3125 && absY >= 3523 && absY <= 3551;
    }


    /**
     * Dharok's equipment check.
     */
    public boolean fullDharok() {
	return equipment[3] == 4718 && equipment[4] == 4720 && equipment[7] == 4722 && equipment[0] == 4716;
    }

    /**
     * Verac's equipment check.
     */
    public boolean fullVerac() {
	return equipment[3] == 4755 && equipment[4] == 4757 && equipment[7] == 4759 && equipment[0] == 4753;
    }

   /**
    * Guthan equipment chck
    */
   public boolean hasGuthanEquipment() {
	return equipment[3] == 4726 && equipment[4] == 4728 && equipment[7] == 4730 && equipment[0] == 4724;
   }

    /**
     * Safezone.
     */
    public boolean inClan() {
 	return absX >= 2756 && absY >= 5508 && absX <= 2875 && absY <= 5627 ||absX >= 3264 && absY >= 3672 && absX <= 3279 && absY <= 3695 ;
    }

    /**
     * clan wars arena
     */
    public boolean inClanFight() {
	return absX >= 3258 && absY >= 3710 && absX <= 3330 && absY <= 3842;
    }

    public void addClanWarsScreen() {
	getActionSender().setString(this, ""+Server.engine.getWhiteClanPlayerCount(whiteCount)+"", 265, 6);
	getActionSender().setString(this, ""+Server.engine.getBlackClanPlayerCount(blackCount)+"", 265, 7);
	getActionSender().setOverlay(this, 265);
    }

    public int distanceToPoint(int pointX, int pointY) {
	return (int) Math.sqrt(Math.pow(absX - pointX, 2) + Math.pow(absY - pointY, 2));
    }

    public boolean fullVoidKnightRange() {
	return equipment[0] == 11675 && equipment[4] == 8839 && equipment[7] == 8840;
    }

    public boolean fullArmadyl() {
	return equipment[0] == 11718 && equipment[4] == 11720 && equipment[7] == 11722;
    }

    public boolean saraChamber() {
	return absX >= 2889 && absX <= 2907 && absY >= 5258 && absY <= 5276;
    }

    public boolean zammyChamber() {
	return absX >= 2919 && absX <= 2935 && absY >= 5319 && absY <= 5330;
    }

    public boolean graardorChamber() {
	return absX >= 2864 && absX <= 2876 && absY >= 5351 && absY <= 5369;
    }

    public boolean armadylChamber() {
	return absX >= 2823 && absX <= 2843 && absY >= 5295 && absY <= 5310;
    }

    public boolean armadylChamber1() {
	return absX >= 2825 && absX <= 2841 && absY >= 5297 && absY <= 5307;
    }

    public boolean inMageArena() {
	return absX >= 3079 && absX <= 3126 && absY >= 3906 && absY <= 3951;
    }

    public boolean inDuelArena() {
	return absX >= 3349 && absX <= 3388 && absY >= 3262 && absY <= 3280;
    }

    public boolean godWarsDung() {
	return absX >= 2819 && absX <= 2946 && absY >= 5254 && absY <= 5362;
    }

    public boolean inJadCave() {
	return absX >= 2375 && absX <= 2425 && absY >= 5128 && absY <= 5169;
    }
public boolean castleArea() {
	return absX >= 2358 && absX <= 2438 && absY >= 3066 && absY <= 3142;
    }
    /**
     * Sets the strings for kill counts for god wars dungeon.
     */
    public void setGodWarsStrings() {
	if (zammyChamber() || addZamorakCheckEventGodWars())
		for (int i = 7; i <  11; i++)
			for (int k = 0; k < 5; k++)
 				if (this != null)
					getActionSender().setString(this, ""+godWarsKills[k]+"", 598, i);
	else
		for (int s = 7; s <  11; s++)
			for (int d = 0; d < 5; d++)
				if (this != null)
					getActionSender().setString(this, ""+godWarsKills[d]+"", 601, s);
    }

    public ActionSender getActionSender() {
	return Engine.actionSender;
    }

    public DuelArena getDuelClass() {
	return duelArena;
    }

    public FightCave getFightClass() {
	return fCave;
    }

    public WarriorGuild getWarriorClass() {
	return warriorGuild;
    }

    public QuestDevelopment getQuestClass() {
	return quest;
    }

    public TestWorldLoader getWorldLoader() {
	return worldLoader;
    }

    public ByteVector getByteVector() {
	return stream;
    }

    public boolean duelFight() {
	return absX >= 3361 && absX <= 3392 && absY >= 3223 && absY <= 3241;
    }

    public boolean slayerTower() {
	return absX >= 3402 && absX <= 3458 && absY >= 3529 && absY <= 3581;
    }
public boolean multiicon() {
	return ((absX >= 3072 && absX <= 3107 && absY >= 3401 && absY <= 3448) ||
			(absX >= 2946 && absX <= 3004 && absY >= 3333 && absY <= 3424) ||
			(absX >= 3193 && absX <= 3332 && absY >= 3665 && absY <= 3752) ||
			(absX >= 3203 && absX <= 3331 && absY >= 3519 && absY <= 3666) ||
			(absX >= 3134 && absX <= 3328 && absY >= 3519 && absY <= 3658) ||
			(absX >= 2945 && absX <= 2961 && absY >= 3812 && absY <= 3828) ||
			(absX >= 3145 && absX <= 3217 && absY >= 3904 && absY <= 3966) ||
			(absX >= 2982 && absX <= 3010 && absY >= 3913 && absY <= 3929) ||
			(absX >= 3203 && absX <= 3392 && absY >= 3904 && absY <= 4031) ||
			(absX >= 3149 && absX <= 3331 && absY >= 3799 && absY <= 3850) ||
			(absX >= 3064 && absX <= 3391 && absY >= 3864 && absY <= 3903) ||
			(absX >= 2550 && absX <= 2696 && absY >= 3274 && absY <= 3390) ||
                        (absX >= 2756 && absX <= 2875 && absY >= 5536 && absY <= 5627) ||
                        (absX >= 3006 && absX <= 3072 && absY >= 3601 && absY <= 3713));
    }
    public void appendPotion() {
	if (explodeDelay == 0) {
		if (explodeType == 1) {
			appendHit(25, 0);
			requestForceChat("Ow!");
			getActionSender().sendMessage(this, "The unidentified liquid potion exploded!");
		}
		explodeType = 0;
		explodeDelay = -1;
	}
    }

    public boolean castleLobby() {
	return absX >= 2435 && absX <= 2449 && absY >= 3079 && absY <= 3099;
    }

    

    public boolean correctDistance(int j, int k, int l, int i1, int j1)
    {
        for(int k1 = 0; k1 <= j1; k1++)
        {
            for(int l1 = 0; l1 <= j1; l1++)
            {
                if(j + k1 == l && (k + l1 == i1 || k - l1 == i1 || k == i1))
                    return true;
                if(j - k1 == l && (k + l1 == i1 || k - l1 == i1 || k == i1))
                    return true;
                if(j == l && (k + l1 == i1 || k - l1 == i1 || k == i1))
                    return true;
            }

        }

        return false;
    }

    public int getLoginSpecial() {
	if (specAmount <= 0) {
		return 0;
	} else {
		return specAmount;
        }
    }

    public int getLoginSpellbook() {
	if (spellbook <= 0) {
		return 192;
	} else {
		return spellbook;
	}
    }
	
    public PlayerFletching getFletchingClass() {
	return fletchingClass;
    }

    /**
     * Edgeville 1 V 1
     */
    public boolean inEdgePk() {
	return absX >= 3040 && absX <= 3118 && absY >= 3522 && absY <= 3553;
    }

    /**
     * Checking if combat level difference is correct for attacking in wild.
     */
    public boolean combatDifference(int cbLvl) {
	return wildLevel >= combatLevel && combatLevel + cbLvl >= wildLevel || cbLvl < combatLevel && combatLevel - wildLevel <= cbLvl || cbLvl == combatLevel;
    }

    /** 
     * Update the wilderness levels.
     */
    void addWildernessUpdating() {
	getActionSender().setString(this, "Level: "+getWildernessLevel(), 380, 1);
	if (updatedLevel != wildernessZone(absX, absY)) {
		updatedLevel = wildernessZone(absX, absY);
		if (updatedLevel && !inClan() && !clanWarsFightArea()) {
			getActionSender().setOverlay(this, 381);
		} else {
			wildyLevel = 0;
			getActionSender().removeOverlay(this);
		}
	}
    }
    public String getMessageString(int economyId) {
	switch (economyId) {

		case 4153:
			return "Granite mauls are now disabled, Please kill Gargoyles in the Slayer tower.";

		case 4151:
		case 11235:
			return "Abyssal whips and Dark bows are disabled, Please go to Slayer tower.";

		case 2440:
		case 2434:
		case 6685:
			return "You cannot spawn the super strength potion, Prayer potion and Brews anymore, Please steal potions from the stalls at home.";

		case 385:
		case 391:
			return "You cannot spawn food anymore, Please steal food from the stalls at home.";

		case 10581:
			return "Please go to the Slayer Tower for a Keris dagger.";

		case 11690:
		case 11702:
		case 11704:
		case 11706:
		case 11708:
			return "You can only get a God hilt or Godsword blade by Defeating the Generals of the Gods in the God wars Dungeon.";

		case 11718:
		case 11720:
		case 11722:
		case 12670:
		case 12671:
			return "You can only get a piece of Armadyl armour by Visitting Armadyl's Eyrie in the Godwars dungeon.";

		case 11724:
		case 11726:
		case 11728:
			return "You can only get a piece of Bandos armour by Visitting Bandos's Stronghold in the Godwars dungeon.";

		case 3140:
			return "You can only get a Dragon chainbody by Killing the Kalphite Queen in her Kalphite Lair.";

		case 6570:
		case 10566:
		case 10637:
			return "Please visit The fight cave for a Fire cape.";

		case 11694:
		case 11696:
		case 11698:
		case 11700:
			return "Please visit the Godwars dungeon to get this item.";

		default:
			return "";
	}
    }

    /**
     * Loads objects at Map region loading.
     */
    public void loadStaticObjects() {
    }


    /**
     * Zammys part of god wars area check boolean
     */
    public boolean addZamorakCheckEventGodWars() {
	return absX >= 2879 && absX <= 2945 && absY >= 5342 && absY <= 5364;
    }

			
    /**
     * Update the player.
     */
    public void updatePlayer(boolean updateIsNeccesary) {
	appearanceUpdateReq = updateReq = updateIsNeccesary;
    }

    public int getX() {
	return absX;
    }

    public int getY() {
	return absY;
    }

    public int getHeight() {
	return heightLevel;
    }

    public int getPlayerId() {
	return playerId;
    }

    public void convertPlayerToNpc(int npcType) {
	this.npcType = npcType;
    }

    public void updatePlayerAppearance(int walkAnim, int standAnim, int runAnim) {
	standEmote = standAnim;
	walkEmote = walkAnim;
	runEmote = runAnim;
    }

    public void appendToIPBanned(Player player) {
	BufferedWriter bw = null;
	try {
		bw = new BufferedWriter(new FileWriter("./data/banned/bannedhosts.dat", true));
		bw.write(Server.socketListener.getAddress(player.socket.socket));
		bw.newLine();
		bw.flush();
		Server.socketListener.loadBannedHosts();
		player.disconnected[0] = true;
		player.disconnected[1] = true;
	} catch (IOException ioe) {
	} finally {
		if (bw != null) {
			try {
			bw.close();
	} catch (IOException ioe2) {
	}
	}
	}
    }
    public void appendToBanned(String player) {
	BufferedWriter bw = null;
	try {
		bw = new BufferedWriter(new FileWriter("./data/banned/bannedusers.dat", true));
		bw.write(player);
		bw.newLine();
		bw.flush();
		Server.loadBannedUsers();
	} catch (IOException ioe) {
	} finally {
		if (bw != null) {
			try {
			bw.close();
	} catch (IOException ioe2) {
	}
	}
	}
    }

    String Directory = "./data/banned/bannedusers.dat";
    String MaxBan[] = new String[1000];
    String UserThatBan = new String();
    int Ban = 0;

    public void UnBanPlayer(String Player) {
	try {
	BufferedReader dataStream = new BufferedReader(new FileReader("./data/banned/bannedusers.dat"));
	while ((UserThatBan = dataStream.readLine()) != null) {
	if(UserThatBan.equalsIgnoreCase(Player)) {
	} else
		if(!UserThatBan.equalsIgnoreCase(Player)) {
		MaxBan[Ban] = UserThatBan;
		Ban++;
	}
	}
	} catch(Exception e) {
		e.printStackTrace();
	}
	BufferedWriter bw = null;
	try {
	bw = new BufferedWriter(new FileWriter(Directory, false));
		bw.write("");
		bw.flush();
		bw.close();
	} catch(Exception e) {
		e.printStackTrace();
	}
	try {
		bw = new BufferedWriter(new FileWriter("./data/banned/bannedusers.dat", true));
		for(int a = 0; a < MaxBan.length; a++) {
		if(MaxBan[a] != null) {
		bw.write(MaxBan[a]);
		bw.newLine();
		bw.flush();
	}
	}
	bw.close();
	} catch(Exception e) {
		e.printStackTrace();
	}
	Server.loadBannedUsers();
    }

    public boolean nonMultiPlace() {
	return absX >= 3400 && absX <= 3457 && absY >= 3527 && absY <=  3579 && heightLevel == 2;
    }

	public int neededSpecialAmount() {
		switch (equipment[3]) {
			case 1215:
			case 1231:
			case 5680:
			case 5698:
			case 1305:
			case 1434:
				return 250;
			case 10887:
			case 11694:
			case 11698:
			case 4151:
				return 500;
			case 11235:
				return 550;
			case 11700:
				return 600;
			case 11696:
			case 11730:
				return 1000;
			default:
				return 0;
		}
	}

	public int weaponType;

	public int attackStyle() {

		/*
		 * Melee weapon styles
		 * 1 : accurate
		 * 2 : agressive
		 * 3 : defensive
		 * 4 : controlled
		 *
		 * Ranged weapon styles
		 * 5 : accurate
		 * 6 : rapid
		 * 7 : longrange
		 */

		switch (weaponType) {
			case 0: return fightStyle;
			case 1: return fightStyle;
			case 2:
				if (fightStyle == 1) {
					return 1;
				}
				if (fightStyle == 3) {
					return 2;
				}
				if (fightStyle == 2) {
					return 3;
				}
			case 3:
				if (fightStyle == 1) {
					return 1;
				}
				if (fightStyle == 2) {
					return 2;
				}
				if (fightStyle == 3) {
					return 4;
				}
				if (fightStyle == 4) {
					return 3;
				}
			case 4:
				if (fightStyle == 1) {
					return 5;
				}
				if (fightStyle == 3) {
					return 6;
				}
				if (fightStyle == 2) {
					return 7;
				}
			case 5: break;
			case 6: return fightStyle;
			case 7:
				if (fightStyle == 1) {
					return 1;
				}
				if (fightStyle == 2 || fightStyle == 3) {
					return 2;
				}
				if (fightStyle == 4) {
					return 3;
				}
			case 8:
				if (fightStyle == 1) {
					return 1;
				}
				if (fightStyle == 4 || fightStyle == 3) {
					return 2;
				}
				if (fightStyle == 2) {
					return 3;
				}
			case 9:
				if (fightStyle == 1) {
					return 4;
				}
				if (fightStyle == 2) {
					return 2;
				}
				if (fightStyle == 3) {
					return 3;
				}
			case 10:
				if (fightStyle == 1) {
					return 1;
				}
				if (fightStyle == 2) {
					return 2;
				}
				if (fightStyle == 3) {
					return 4;
				}
				if (fightStyle == 4) {
					return 3;
				}
			case 11:
				if (fightStyle == 1) {
					return 1;
				}
				if (fightStyle == 4) {
					return 2;
				}
				if (fightStyle == 3) {
					return 4;
				}
				if (fightStyle == 2) {
					return 3;
				}
			case 12:
				if (fightStyle == 1 || fightStyle == 2) {
					return fightStyle;
				}
				if (fightStyle == 3) {
					return 4;
				}
				if (fightStyle == 4) {
					return 3;
				}
		}
		return 8;
	}

	public int weaponStyle(int weaponID) {
		switch (weaponID) {
			case 4151: return 2;
			case 4153: return 3;
			case 1277:
			case 1279:
			case 1281:
			case 1283:
			case 1285:
			case 1287:
			case 1289:
			case 1291:
			case 1293:
			case 1295:
			case 1297:
			case 1299:
			case 1301:
			case 1303:
			case 1305:
			case 1329:
			case 1331:
			case 1337:
			case 4587:
				if (fightStyle != 3) {
					return 2;
				} else {
					return 1;
				}
			case 1434:
			case 4755:
			case 10887:
				if (fightStyle != 3) {
					return 3;
				} else {
					return 1;
				}
			case 1215:
			case 1231:
			case 5680:
			case 5698:
				if (fightStyle != 3) {
					return 1;
				} else {
					return 2;
				}
			case 1307:
			case 1309:
			case 1311:
			case 1313:
			case 1315:
			case 1317:
			case 1319:
			case 1349:
			case 1351:
			case 1353:
			case 1355:
			case 1357:
			case 1359:
			case 1361:
			case 1363:
			case 1365:
			case 1367:
			case 1369:
			case 1371:
			case 1373:
			case 1375:
			case 1377:
			case 4718:
			case 6739:
			case 7158:
			case 11694:
			case 11696:
			case 11698:
			case 11700:
			case 11730:
				if (fightStyle != 3) {
					return 2;
				} else {
					return 3;
				}
		}
		return 1;
	}

	public int getAttackSound() {
		String weapon = Engine.items.getItemName(equipment[3]);
		if (weapon.equals("Abyssal whip")) {
			return 2720;
		}
		if (weapon.contains("godsword") || weapon.equals("Saradomin sword")) {
			switch (fightStyle) {
				case 1: 
				case 2:
				case 4: return 3846;
				case 3: return 3882;
			}
		}
		if (weapon.equals("Granite maul")) {
			return 2714;
		}
		return -1;
	}

	public int getSpecialAttackSound() {
		return -1;
	}

     public ClanWars getClanWarsHandler() {
	return clanWars;
     }

     public boolean clanWarsFightArea() {
	return absX >= 3263 && absX <= 3327 && absY >= 3713 && absY <= 3840;
     }

     public boolean bountyArea(){
		return absX >= 3085 && absX <= 3201 && absY >= 3662 && absY <= 3802;
	}
	public boolean castlePk(){
		return absX >= 2968 && absX <= 3071 && absY >= 3523 && absY <= 3671;
	}
    /**
     * Prepare player for removal.
     */
    public void destruct() {
        long me = Misc.stringToLong(username);
    for(Player client : Engine.players) {
        if(client == null) continue;
        if(client.friends.contains(me)) {
            client.getActionSender().sendFriend(client, me, 0);
        }
    }
        stream = null;
        try {
		//Server.socketListener.connectionsList.remove(Server.socketListener.getAddress(this.socket.socket));
            socket.input.close();
            socket.output.close();
            socket.socket.close();
        } catch (Exception e) {
        }
        socket.input = null;
        socket.output = null;
        socket.socket = null;
        socket = null;
        absX = absY = mapRegionX = mapRegionY = -1;
    }
}