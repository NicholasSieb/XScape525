/*
 * Class GroundItem
 *
 * Version 1.0
 *
 * Tuesday, August 19, 2008
 *
 * Created by Palidino76
 */

package net.com.codeusa.world.items;

public class GroundItem {
    /**
     * The items id.
     */
    public int itemId = -1;
    /**
     * The items amount.
     */
    public int itemAmt = 0;
    /**
     * Prevents duping
     */
    public int pickupCount;
    /**
     * The items absolute x position.
     */
    public int itemX = 0;
    /**
     * The items absolute y position.
     */
    public int itemY = 0;
    /**
     * The items height.
     */
    public int itemHeight = 0;
    /**
     * The items delay before being removed.
     */
    public int itemGroundTime = 400;
    /**
     * The items original owner.
     */
    public String itemDroppedBy = "";
    /**
     * The items index in the ground items array.
     */
    public int index = 0;

    /**
     * Creates a new GroundItem.
     */
    public GroundItem(int _index, int id, int amt, int x, int y, int h, String owner) {
        index = _index;
        itemId = id;
        itemAmt = amt;
        itemX = x;
        itemY = y;
        itemHeight = h;
        itemDroppedBy = owner;
    }
}