package net.com.codeusa.model.items;

import java.util.LinkedList;
import net.com.codeusa.Engine;
import net.com.codeusa.world.items.ItemList;
import net.com.codeusa.world.items.Items;

/**
 *
 * @author Gravediggah, Edited by Axed
 */
public class BankUtils {
    public BankUtils() {

    }

    public boolean isNote(int itemId) {
        String description = Engine.items.getItemDescription(itemId);
        return description.toLowerCase().startsWith("swap");
    }

    public boolean canBeNoted(int itemId) {
        return (findNote(itemId)>-1);
    }

    public int findNote(int itemId) {
        for(ItemList i : Engine.items.itemLists) {
            if (i !=null) {
                if (i.itemDescription.toLowerCase().startsWith("swap") &&
                        i.itemName.equals(Engine.items.getItemName(itemId))) {
                        return i.itemId;
                }
            }
        }
        return -1;
    }

    public int findUnNote(int itemId) {
        for(ItemList i : Engine.items.itemLists) {
            if (i !=null) {
                if (!i.itemDescription.toLowerCase().startsWith("swap") &&
                        i.itemName.equals(Engine.items.getItemName(itemId))) {
                        return i.itemId;
                }
            }
        }
        return -1;
    }
}