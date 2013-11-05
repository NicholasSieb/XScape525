/*
 * Class PlayerItems
 *
 * Version 1.0
 *
 * Thursday, August 21, 2008
 *
 * Created by Palidino76
 */

package net.com.codeusa.model.items;

import net.com.codeusa.Server;
import net.com.codeusa.Engine;
import net.com.codeusa.model.Player;
import net.com.codeusa.world.items.GroundItem;

public class PlayerItems {
    /**
     * Private referance to the Engine class.
     */
    private Engine ge;

    /**
     * Constructs a new PlayerItems class.
     */
    public PlayerItems() {
        ge = Server.engine;
    }

    public int itemPrice(int item) {
	if(Engine.items.itemLists[item].shopValue > -1) {
            return Engine.items.itemLists[item].shopValue;
	}
	return 0;
    }

  public boolean haveItem2(Player paramPlayer, int paramInt1, int paramInt2) {
    int i = 0;
    for (int j = 0; j < paramPlayer.items.length; ++j)
      if (paramPlayer.items[j] == paramInt1) {
        if (paramPlayer.itemsN[j] >= paramInt2)
          return true;

        ++i;
      }
    return (i >= paramInt2);
  }

    /**
     * Adds an item to the specified player.
     * <p>Adds an item to Player p if that player has enough space. If it doesnt,
     * it will return false and give the error message to the player,
     * "Not enough space in your inventory."
     * @param p The player to add the item to.
     * @param itemId The item to give the player.
     * @param itemAmt The amount of itemId to give the player.
     * @return Returns true if the item was successfully added.
     */
    public boolean addItem(Player p, int itemId, int itemAmt) {
        if (itemId <= -1 || itemAmt <= 0 || p == null) {
            return false;
        }
        if (!ge.items.stackable(itemId) && !ge.items.noted(itemId)) {
            for (int i = 0; i < p.items.length; i++) {
                int index = findInvSlot(p);
                if (index >= 0 && itemAmt != 0) {
                    itemAmt--;
                    p.items[index] = itemId;
                    p.itemsN[index] = 1;
                } else {
                    break;
                }
            }
            p.getActionSender().setItems(p, 149, 0, 93, p.items, p.itemsN);
            if (itemAmt == 0) {
                return true;
            } else {
                p.getActionSender().sendMessage(p, "Not enough space in your inventory.");
                return false;
            }
        } else if (ge.items.stackable(itemId) || ge.items.noted(itemId)) {
            if (invItemCount(p, itemId) > 0) {
                if ((itemAmt + invItemCount(p, itemId)) <= ge.items.maxItemAmount) {
                    p.itemsN[getItemSlot(p, itemId)] += itemAmt;
                    p.getActionSender().setItems(p, 149, 0, 93, p.items, p.itemsN);
                    return true;
                } else {
                    p.itemsN[getItemSlot(p, itemId)] = ge.items.maxItemAmount;
                    p.getActionSender().setItems(p, 149, 0, 93, p.items, p.itemsN);
                    return true;
                }
            } else if (invItemCount(p, itemId) <= 0) {
                int index = findInvSlot(p);
                if (index >= 0) {
                    p.items[index] = itemId;
                    p.itemsN[index] = itemAmt;
                    p.getActionSender().setItems(p, 149, 0, 93, p.items, p.itemsN);
                    return true;
                } else {
                    p.getActionSender().sendMessage(p, "Not enough space in your inventory.");
                    return false;
                }
            }
        }
        p.getActionSender().sendMessage(p, "Not enough space in your inventory.");
        return false;
    }

    /**
     * Deletes an item from Player p.
     * <p>Deletes amount of itemId from p at the selected index in the players inventory, if the item
     * id at that position is not the same it will automatically find the items index.
     * @param p The player to apply the item changes to.
     * @param itemId The item id to delete.
     * @param index the position in the inventory to delete the item.
     * @param amount The amount of that item to delete.
     * @return Returns true if the item was successfully deleted.
     */
      public boolean deleteItem(Player p, int itemId, int amount) {
		return deleteItem(p,itemId,getItemSlot(p,itemId),amount);
	}
	  public boolean deleteItem(Player p, int itemId, int index, int amount) {
        if (itemId <= -1 || amount <= 0 || p == null || index < 0 || index > 27) {
            return false;
        }
        if ((p.items[index] == itemId) && (!ge.items.stackable(itemId) && !ge.items.noted(itemId))) {
            if (amount == 1 && p.items[index] == itemId) {
                p.items[index] = -1;
                p.itemsN[index] = 0;
                p.getActionSender().setItems(p, 149, 0, 93, p.items, p.itemsN);
                return true;
            } else {
                for (int i = 0; i < p.items.length; i++) {
                    if (p.items[i] == itemId && amount != 0) {
                        amount--;
                        p.items[i] = -1;
                        p.itemsN[i] = 0;
                    }
                }
                p.getActionSender().setItems(p, 149, 0, 93, p.items, p.itemsN);
                if (amount == 0) {
                    return true;
                }
            }
        }
        if ((p.items[index] == itemId) && (ge.items.stackable(itemId) || ge.items.noted(itemId))) {
            if (p.items[index] != itemId) {
                index = getItemSlot(p, itemId);
            }
            if (p.itemsN[index] > amount) {
                p.itemsN[index] -= amount;
                p.getActionSender().setItems(p, 149, 0, 93, p.items, p.itemsN);
                return true;
            } else {
                p.items[index] = -1;
                p.itemsN[index] = 0;
                p.getActionSender().setItems(p, 149, 0, 93, p.items, p.itemsN);
                return true;
            }
        }
        return false;
    }

          /**
     * Used to check if the player has item.
     */
    public boolean hasPlayerItem(Player player, int itemId) {
	if (player == null)
		return false;
	for (int i = 0; i < player.items.length; i++)
		if (player.items[i] == itemId)
			return true;
	return false;
    }

    int playerItemCount;

    /**
     * Used to check if the player has an item which is stacked. (Amount checking + item)
     */
    public boolean hasPlayerItemAmount(Player player, int itemId, int itemStack) {
	if (player == null)
		return false;
	playerItemCount = 0;
	for (int i = 0; i < player.items.length; i++) {
		if (player.items[i] == itemId)
			playerItemCount = player.itemsN[i];
		if (playerItemCount >= itemStack)
			return true;
	}
	return false;
    }

    /**
     * Used to check what item is in a specified slot.
     */
    public int getItemID(Player player, int slotNumber) {
	return player.items[slotNumber];
    }

    public boolean haveItem(Player p, int itemID) {
		return haveItem(p,itemID,1);
	}
	public boolean haveItem(Player p, int itemID, int amount) {
	    //itemID++;
	    int found = 0;
        for (int i = 0; i < p.items.length; i++) {
            if (p.items[i] == itemID) {
		    if(p.itemsN[i] >= amount)
				return true;
		    else
			    found++;
            }
        }
	if(found >= amount)
		return true;
        return false;
    }

	/**
     * Returns the first index itemId is at, or -1 if the item isn't found.
     * @param p The player to apply the item changes to.
     * @param itemId The item id to check for.
     * @return Returns the items slot.
     */
    public int getItemSlot(Player p, int itemId) {
        if (p == null) {
            return 0;
        }
        int itemAmt = 0;
        for (int i = 0; i < p.items.length; i++) {
            if (p.items[i] == itemId) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns an empty inventory slot.
     * @param p The player to check.
     * @return Returns the index of a free inventory slot, or -1 if there are none.
     */
    public int findInvSlot(Player p) {
        if (p == null) {
            return -1;
        }
        for (int i = 0; i < p.items.length; i++) {
            if (p.items[i] == -1) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Gets the number of open inventory slots.
     * @param p The player to check.
     * @return Returns the number of empty inventory slots.
     */
    public int freeSlotCount(Player p) {
        if (p == null) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < p.items.length; i++) {
            if (p.items[i] == -1) {
                count++;
            }
        }
        return count;
    }

    /**
     * Returns the number of the specified item in the players
     * inventory.
     * @param p The player to check.
     * @param itemId The item id to count.
     * @return Returns the number of itemId in the players inventory.
     */
    public int invItemCount(Player p, int itemId) {
        if (p == null || itemId <= 0) {
            return 0;
        }
        int itemAmt = 0;
        for (int i = 0; i < p.items.length; i++) {
            if (p.items[i] == itemId) {
                if (p.items[i] == itemId && (ge.items.stackable(itemId) || ge.items.noted(itemId))) {
                    itemAmt += p.itemsN[i];
                } else {
                    itemAmt++;
                }
            }
        }
        return itemAmt;
    }
}