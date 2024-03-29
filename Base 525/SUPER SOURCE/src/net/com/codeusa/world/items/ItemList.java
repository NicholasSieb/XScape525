/*
 * Class ItemList
 *
 * Version 1.0
 *
 * Sunday, August 17, 2008
 *
 * Created by Palidino76
 */

package net.com.codeusa.world.items;

public class ItemList {
    /**
     * The id of this item.
     */
    public int itemId = -1;
    /**
     * The items name.
     */
    public String itemName = "";
    /**
     * The items description.
     */
    public String itemDescription = "";
    /**
     * The shop value.
     */
    public int shopValue = 0;
    /**
     * The base value.
     */
    public int lowAlch = 0;
    /**
     * The id for the player mask.
     */
    public int equipId = 0;
    /**
     * If the item is stackable.
     */
    public boolean itemStackable = false;
    /**
     * If the item is notable.
     */
    public boolean itemIsNote = false;
    /**
     * The items bonuses.
     */
    public int[] bonuses = new int[12];

    /**
     * Constructs a new ItemList.
     */
    public ItemList(int _itemId, String name, String des, int sv, int la, int[] b) {
        itemId = _itemId;
        itemName = name;
        itemDescription = des;
        shopValue = sv;
        lowAlch = la;
        bonuses = b;
    }
}