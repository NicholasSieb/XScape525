/*
 * Class Items
 *
 * Version 1.0
 *
 * Sunday, August 17, 2008
 *
 * Created by Palidino76
 */

package net.com.codeusa.world.items;

import java.io.*;
import net.com.codeusa.Engine;
import net.com.codeusa.model.Player;
import net.com.codeusa.io.*;
import net.com.codeusa.util.Misc;
import net.com.codeusa.model.items.BankUtils;

public class Items {
    public int maxItemAmount = Integer.MAX_VALUE;
    public int maxBankSize = 352;
    public int maxListedItems = 15000;
    public ItemList[] itemLists = new ItemList[maxListedItems];
    public GroundItem[] groundItems = new GroundItem[1000];
    private int untradable[] = {13884, 13886, 13890, 13892, 13896, 13898, 773, 2412, 2413, 2414, 2415, 2416, 2417, 4212, 4224, 10547, 10553, 10555, 10549, 10550, 10548, 10547, 9747,9748,9749,9750,9751,9752,9753,9754,9755,9756,9757,9758,9759,9760,9761,9762,9763,9764,9765,9766,9767,9768,9769,9770,9771,9772,9773,9774,9775,9776,9777,9778,9779,9780,9781,9782,9783,9784,9785,9786,9787,9788,9789,9790,9791,9792,9793,9794,9795,9796,9797,9798,9799,9800,9801,9802,9803,9804,9805,9806,9807,9808,9809,9810,9811,9812,9813,9814,9948,9949,9950,12169,12170,12171,3840, 3842, 3844, 6570, 7462, 7461, 7460, 7459, 7458, 7457, 7456, 7455, 7454, 7453, 8839, 8840, 8842, 8844, 8845, 8846, 8847, 8848, 8849, 8850, 11663, 11664, 11665, 10551, 13290,11212, 7559, 7557, 7555, 7561, 6439, 6437, 6435, 6433, 6431, 6429, 6427, 6425, 6423};

    private Engine engine;
    private ActionSender actionSender;

    /**
     * Constructs a new Items class.
     * @param game The engine to get data from.
     */
    public Items(Engine game) {
        engine = game;
        actionSender = engine.actionSender;
        loadItemList();
        loadItemLists();
    }

    /**
     * Checks if an item is tradable or not.
     * @param The item to check.
     * @return Returns if the itemId is tradable.
     */
    public boolean isUntradable(int item) {
        for (int i = 0; i < untradable.length; i++) {
            if (untradable[i] == item) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method is called every 600 milliseconds.
     */
    public void process() {
	try {
        for (GroundItem g : groundItems) {
            if (g == null) {
                continue;
            } else if (g.itemId > -1 && g.itemAmt > 0) {
                g.itemGroundTime--;
                if (g.itemGroundTime == 230) {
                    if (!isUntradable(g.itemId) && !g.itemDroppedBy.equals("")) {
                        actionSender.removeGroundItem(engine.players[engine.getIdFromName(g.itemDroppedBy)], g.itemId, g.itemX, g.itemY, g.itemHeight);
                        createGlobalItem(g.itemId, g.itemAmt, g.itemX, g.itemY, g.itemHeight);
                    }
                } else if(g.itemGroundTime <= 231) {
                    if (isUntradable(g.itemId)) {
                        actionSender.removeGroundItem(engine.players[engine.getIdFromName(g.itemDroppedBy)], g.itemId, g.itemX, g.itemY, g.itemHeight);
                    } else {
                        removeGlobalItem(g.itemId, g.itemX, g.itemY, g.itemHeight);
                    }
                    discardItem(g);
                }
            } else if (g.itemId < 0) {
                discardItem(g);
            } else if (g.itemAmt <= 0) {
                discardItem(g);
            }
        }
	} catch (Exception e) {
	}
    }

    /**
     * Remove a ground item.
     * @param g The ground item to discard.
     */
    public void discardItem(GroundItem g) {
        groundItems[g.index] = null;
    }

    /**
     * Removes an item for everyone within distance.
     */
    public void removeGlobalItem(int id, int x, int y, int height) {
        if (id < 0 || id >= maxListedItems) {
            return;
        }
        for (Player p : engine.players) {
            if (p == null) {
                continue;
            }
            actionSender.removeGroundItem(p, id, x, y, height);
        }
    }

    /**
     * Creates an item on the ground for everyone within distance.
     */
    public void createGlobalItem(int id, int amt, int x, int y, int height) {
        if (id < 0 || amt < 0 || id >= maxListedItems) {
            return;
        }
        for (Player p : engine.players) {
            if (p == null) {
                continue;
            }
            actionSender.createGroundItem(p, id, amt, x, y, height);
        }
    }

    /**
     * Creates a new ground item.
     */
    public void createGroundItem(int id, int amt, int x, int y, int height, String owner) {
        if (id < 0 || amt < 0 || id >= maxListedItems) {
            return;
        }
        /*
         * Set the owner to "" for an item everyone can see.
         */
        int index = -1;
        for (int i = 0; i < groundItems.length; i++) {
            if (groundItems[i] == null) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            Misc.println("Max number of items spawned.");
            return;
        }
        groundItems[index] = new GroundItem(index, id, amt, x, y, height, owner);
        if (groundItems[index].itemDroppedBy.equals("")) {
            createGlobalItem(id, amt, x, y, height);
        } else {
            actionSender.createGroundItem(engine.players[engine.getIdFromName(owner)], id, amt, x, y, height);
        }
    }

    /**
     * Checks if an item exists at the params.
     * @param itemId The item id to look for.
     * @param itemX The x coordinate to look for the item.
     * @param itemY The y coordinate to look for the item.
     * @param height The height level to look for the item.
     * @return Returns the ground item index, or -1 if the item isnt found.
     */
    public int itemExists(int itemId, int itemX, int itemY, int height) {
        if (itemId < 0 || itemId >= maxListedItems) {
            return -1;
        }
        for (GroundItem g : groundItems) {
            if (g == null) {
                continue;
            }
            if (g.itemId == itemId && g.itemX == itemX && g.itemY == itemY && g.itemHeight == height) {
                return g.index;
            }
        }
        return -1;
    }

    /**
     * Finds an item at the params and removes it.
     * @param itemId The item id to look for.
     * @param itemX The x coordinate of the item.
     * @param itemY The y coordinate of the item.
     * @param height The height level of the item.
     * @param Returns true if the item was successfully found and removed.
     */
    public boolean itemPickedup(int itemId, int itemX, int itemY, int height) {
        if (itemId < 0 || itemId >= maxListedItems) {
            return false;
        }
        int amt = 0;
        for (GroundItem g : groundItems) {
            if (g == null) {
                continue;
            }
            if (g.itemId == itemId && g.itemX == itemX && g.itemY == itemY && g.itemHeight == height) {
                amt = g.itemAmt;
                if ((g.itemGroundTime <= 230 || g.itemDroppedBy.equals("")) && !isUntradable(g.itemId)) {
                    removeGlobalItem(g.itemId, g.itemX, g.itemY, g.itemHeight);
                } else {
                    actionSender.removeGroundItem(engine.players[engine.getIdFromName(g.itemDroppedBy)], g.itemId, g.itemX, g.itemY, g.itemHeight);
                }
                discardItem(g);
                return true;
            }
        }
        return false;
    }

    /**
     * Loads other item lists.
     */
    private void loadItemLists() {
        int itemId = -1, counter = 0;
        String name = null;
        try {
            BufferedReader in = new BufferedReader(new FileReader("./data/items/stackable.dat"));
            while ((name = in.readLine()) != null) {
                itemId = Integer.parseInt(name);
                if (itemId != -1) {
                    if (itemLists[itemId] != null) {
                        itemLists[itemId].itemStackable = true;
                    }
                }
            }
            in.close();
            in = null;
        } catch (Exception e) {
            Misc.println("Error loading stackable list.");
        }
        try {
            BufferedReader in = new BufferedReader(new FileReader("./data/items/equipment.dat"));
            while ((name = in.readLine()) != null) {
                itemId = Integer.parseInt(name.substring(0, name.indexOf(":")));
                int equipId = Integer.parseInt(name.substring(name.indexOf(":") + 1));
                if (itemLists[itemId] != null) {
                    itemLists[itemId].equipId = equipId;
                }
            }
            in.close();
            in = null;
        } catch (Exception e) {
            Misc.println("Error loading equipment list.");
        }
    }

    /**
     * Returns if the itemId is stackable.
     */
    public boolean notedAndStackable(int itemId) {
	String itemName = getItemName(itemId);
	String item2Name = getItemName(itemId - 1);
	if(itemName.startsWith(item2Name) && itemName.endsWith(item2Name)){
	    itemLists[itemId].itemStackable = true;
	    return true;
	}

        return false;
    }

    public boolean stackable(int itemId) {
        if (itemId < 0 || itemId >= maxListedItems) {
            return false;
        }
        if (itemLists[itemId] != null) {
            return (itemLists[itemId].itemStackable);
        }
        return false;
    }

    /**
     * Returns if the itemId is noted.
     */
    public boolean noted(int itemId) {
        return new BankUtils().isNote(itemId);
    }

    /**
     * Returns the name of itemId.
     */
    public String getItemName(int itemId) {
        if (itemId == -1 || itemId >= maxListedItems) {
            return new String("Unarmed");
        }
        if (itemLists[itemId] != null) {
            return (itemLists[itemId].itemName);
        }
        return new String("null");
    }

    /**
     * Returns the equipment mask of itemId.
     */
    public int getEquipId(int itemId) {
        if (itemId < 0 || itemId >= maxListedItems) {
            return 0;
        }
        if (itemLists[itemId] != null) {
            return (itemLists[itemId].equipId);
        }
        return 0;
    }

    /**
     * Returns the description of itemId.
     */
    public String getItemDescription(int itemId) {
        if (itemId == -1 || itemId >= maxListedItems) {
            return new String("An item.");
        }
        if (itemLists[itemId] != null) {
            return (itemLists[itemId].itemDescription);
        }
        return new String("Item " + itemId);
    }

    /**
     * Returns the value of itemId.
     */
    public int getItemValue(int itemId) {
        if (itemId < 0 || itemId >= maxListedItems) {
            return 0;
        }
        if (itemLists[itemId] != null) {
            return (itemLists[itemId].lowAlch);
        }
        return 1;
    }

    /**
     * Load item data from a file.
     */
    private boolean loadItemList() {
        String line = "", token = "", token2 = "", token2_2 = "", token3[] = new String[10];
        BufferedReader list = null;
        try {
            list = new BufferedReader(new FileReader("./data/items/items.cfg"));
            line = list.readLine().trim();
        } catch (Exception e) {
            Misc.println("Error loading item list.");
            return false;
        }
        while (line != null) {
            int spot = line.indexOf("=");
            if (spot > -1) {
                token = line.substring(0, spot).trim();
                token2 = line.substring(spot + 1).trim();
                token2_2 = token2.replaceAll("\t\t", "\t");
                token2_2 = token2_2.replaceAll("\t\t", "\t");
                token2_2 = token2_2.replaceAll("\t\t", "\t");
                token3 = token2_2.split("\t");
                if (token.equals("item")) {
                    int[] bonuses = new int[12];
                    for (int i = 0; i < 12; i++) {
                        if (token3[(6 + i)] != null) {
                            bonuses[i] = Integer.parseInt(token3[(6 + i)]);
                        } else {
                            break;
                        }
                    }
                    newItemList(Integer.parseInt(token3[0]), token3[1].replaceAll("_", " "), token3[2].replaceAll("_", " "), Integer.parseInt(token3[4]), Integer.parseInt(token3[4]), Integer.parseInt(token3[6]), bonuses);
                }
            } else {
                if (line.equals("[ENDOFITEMLIST]")) {
                    try {
                        list.close();
                    } catch (Exception exception) {
                    }
                    list = null;
                    return true;
                }
            }
            try {
                line = list.readLine().trim();
            } catch (Exception exception1) {
                try {
                    list.close();
                } catch (Exception exception) {
                }
                list = null;
                return true;
            }
        }
        return false;
    }

    /**
     * Sets a new item list.
     */
    private void newItemList(int ItemId, String ItemName, String ItemDescription, int ShopValue, int LowAlch, int HighAlch, int Bonuses[]) {
        if (ItemId > maxListedItems) {
            Misc.println("maxListedItems to low.");
            return;
        }
        itemLists[ItemId] = new ItemList(ItemId, ItemName, ItemDescription, ShopValue, LowAlch, Bonuses);
    }
}