package net.com.codeusa;
import net.com.codeusa.model.Player;
import net.com.codeusa.model.items.PlayerItems;
import net.com.codeusa.Engine;
import net.com.codeusa.io.ActionSender;
/**
 * Created by IntelliJ IDEA.
 * User:     Serenty
 * Date:     3-march-2009
 * Project:  Boneyard
 * Time:     19:51:15
 *converted by Owner pkin from runelocus
 */
public class ShopHandler {
 
    Player p;
    PlayerItems pi = new PlayerItems();
    public int maxItems = 40;
    public int[] items = new int[maxItems];
    public int[] itemsN = new int[maxItems];
    public int[] maxItemAmount = new int[maxItems];
    public boolean generalStore = false;
    public long lastRestock = System.currentTimeMillis();
    int shopid = 0;
    public boolean mainstock = true;
    /***************************************************************************************************************/
    /*                              DO NOT TOUCH THIS!                                                             */
    /***************************************************************************************************************/
    public ShopHandler() {
 
        for (int i = 0; i < 40; i++) {
            items[i] = -1;
        }
        this.items = items;
        this.itemsN = itemsN;
        maxItemAmount = itemsN;
    }
    public void process(Player p) {
        if (System.currentTimeMillis() - lastRestock >= 60000) {
            clearSlots(p);
            for (int i = 0; i < items.length; i++) {
                if (itemsN[i] < maxItemAmount[i]) itemsN[i]++;
            }
            lastRestock = System.currentTimeMillis();
        }
    }
    public void sell(Player p, int item, int amnt){
            PlayerItems pi = new PlayerItems();
            boolean shopShouldBuy = generalStore || isItemOnShop(item);
            if (!shopShouldBuy) {
                p.getActionSender().sendMessage(p, "You cannot sell this item to this shop.");
                return;
            }
            if (item == 995) {
                p.getActionSender().sendMessage(p, "You can't sell coins to a shop");
                return;
            }
            if (item == 146 || item == 3025 || item == 158 || item == 170 || item == 140 || item == 164 || item == 145 || item == 3024 || item == 157 || item == 169 || item == 139 || item == 163) {
                p.getActionSender().sendMessage(p, "You can not sell the pots from the ::pots command.");
                return;
            }
            if (item == 555 || item == 556 || item == 557 || item == 558 || item == 559 || item == 560 | item == 9075 || item == 561 || item == 562 || item == 563 || item == 565 || item == 566 || item == 553 || item == 567) {
                p.getActionSender().sendMessage(p, "You can not sell the runes from any rune commands.");
                return;
            }
            if (item == 8839 || item == 8840 || item == 8841 || item == 8842 || item == 8843 || item == 8844 || item == 11663 || item == 11664 || item == 11665 || item == 10551 || item == 8850 || item == 7462 || item == 6570) {
                p.getActionSender().sendMessage(p, "You cannot sell this item.");
                return;
            }
            int free = findFreeSlot();
            if (!isItemOnShop(item) && generalStore) {
                if (free == -1) {
                    p.getActionSender().sendMessage(p, "This shop is full.");
                    return;
                }
                items[free] = item;
                itemsN[free] = 0;
            }
            int slot = findItemSlot(item);
        if(amnt<=pi.invItemCount(p,item))
        {
            if (itemStacks(item)) {
                items[slot] = item;
                itemsN[slot] += amnt;
                pi.deleteItem(p, item, pi.getItemSlot(p, item), amnt);
                    pi.addItem(p, 995, amnt*itemValue(item));
            } else {
                items[slot] = item;
                for (int notused = amnt; notused > 0; notused--) {
                    pi.deleteItem(p, item, pi.getItemSlot(p, item), 1);
                    pi.addItem(p, 995, itemValue(item));
                    itemsN[slot]++;
                }
            }
        }
        else
        {
            amnt = pi.invItemCount(p,item);
            if (itemStacks(item)) {
                items[slot] = item;
                itemsN[slot] += amnt;
                pi.deleteItem(p, item, pi.getItemSlot(p, item), amnt);
                    pi.addItem(p, 995, amnt*itemValue(item));
            } else {
                items[slot] = item;
                for (int notused = amnt; notused > 0; notused--) {
                    pi.deleteItem(p, item, pi.getItemSlot(p, item), 1);
                    pi.addItem(p, 995, itemValue(item));
                    itemsN[slot]++;
                }
            }
        }
 
            sendShopItems(p);
            sendPlayerInventory(p);
        }
    public void buy(Player p, int item, int amnt) {
        clearSlots(p);
        int slot = findItemSlot(item);
        if (slot == -1) {
            return;
        }
        if (itemsN[slot] < amnt) amnt = itemsN[slot];
        if (amnt == 0) {
            items[slot] = -1;
 
           p.getActionSender().sendMessage(p, "That item's stock has run out.");
            return;
        }
        if (itemStacks(item)) {                                                 // item stackable, easier...
            if (pi.freeSlotCount(p) < 1) {
                p.getActionSender().sendMessage(p, "Not enough space on inventory");
                return;
            }
            if (pi.invItemCount(p, 995) < (amnt * itemValue(item))) {
                p.getActionSender().sendMessage(p, "Not enough coins to buy that many.");
                return;
            }
            pi.deleteItem(p, 995, pi.getItemSlot(p, 995), amnt * itemValue(item));
            pi.addItem(p, item, amnt);
            itemsN[slot] -= amnt;
        } else {                                                                // item not stackable
            for (int i = amnt; i > 0; i--) {
                int price = itemValue(item);
                clearSlots(p);
                if (pi.invItemCount(p, 995) < price) {
                    p.getActionSender().sendMessage(p, "Not enough coins to buy that many.");
                    break;
                }
                if (pi.findInvSlot(p) == -1) {
                    p.getActionSender().sendMessage(p, "Not enough space on inventory");
                    break;
                }
                if (itemsN[slot] < 1) {
                    clearSlots(p);
                     p.getActionSender().sendMessage(p, "The shop has run out of stock from this item!");
                    break;
                }
                if (itemsN[slot] == 1) {
                    pi.deleteItem(p, 995, pi.getItemSlot(p, 995), price);
                    pi.addItem(p, item, 1);
                    items[slot] = -1;
                    break;
                }
                itemsN[slot]--;
                pi.deleteItem(p, 995, pi.getItemSlot(p, 995), price);
                pi.addItem(p, item, 1);
                clearSlots(p);
            }
 
        }
        sendShopItems(p);
        sendPlayerInventory(p);
        clearSlots(p);
    }
    public void buystock(Player p, int item, int amnt) {
        if (itemStacks(item)) {                                                 // item stackable, easier...
            if (pi.freeSlotCount(p) < 1) {
                p.getActionSender().sendMessage(p, "Not enough space on inventory");
                return;
            }
            if (pi.invItemCount(p, 995) < (amnt * itemValue(item))) {
                p.getActionSender().sendMessage(p, "Not enough coins to buy that many.");
                return;
            }
            pi.deleteItem(p, 995, pi.getItemSlot(p, 995), amnt * itemValue(item));
            pi.addItem(p, item, amnt);
        } else {
            // item not stackable
            for (int i = amnt; i > 0; i--) {
                int price = itemValue(item);
                if (pi.invItemCount(p, 995) < price) {
                    p.getActionSender().sendMessage(p, "Not enough coins to buy that many.");
                    break;
                }
                if (pi.findInvSlot(p) == -1) {
                    p.getActionSender().sendMessage(p, "Not enough space on inventory");
                    break;
                }
                pi.deleteItem(p, 995, pi.getItemSlot(p, 995), price);
                pi.addItem(p, item, 1);
            }
        }
        //sendShopItems(p);
        sendPlayerInventory(p);
    }
    public int findItemSlot(int item) {
        for (int i = 0; i < items.length; i++)
            if (item == items[i]) return i;
        return -1;
    }
    public int findFreeSlot() {
        for (int i = 0; i < items.length; i++)
            if (items[i] == -1) return i;
        return -1;
    }
    public void clearSlots(Player p) {
        for (int i = 0; i < items.length; i++)
            if (items[i] < 1) {
                items[i] = -1;
            } else {
            }
    }
    public void sendShopItems(Player p) {
        p.getActionSender().setItems(p, -1, 64271, 31, items, itemsN);
    }
    public boolean isItemOnShop(int item) {
        return findItemSlot(item) != -1;
    }
    public void sendPlayerInventory(Player p) {
        p.getActionSender().setItems(p, -1, 64209, 93, p.items, p.itemsN);
    }
    public int itemValue(int item) {
        return Engine.items.itemLists[item].shopValue;
    }
    public boolean itemStacks(int item) {
        return Engine.items.itemLists[item].itemStackable;
    }
    public void handleoption(Player p, int interfaceId, int buttonId, int buttonId2, int packetId) {
        switch (interfaceId) {
            case 620:
                if (buttonId == 25) {
                    p.getActionSender().setInterfaceConfig(p, 620, 23, false);
                    p.getActionSender().setInterfaceConfig(p, 620, 24, true);
                    p.getActionSender().setInterfaceConfig(p, 620, 29, false);
                    p.getActionSender().setInterfaceConfig(p, 620, 25, true);
                    p.getActionSender().setInterfaceConfig(p, 620, 27, true);
                    p.getActionSender().setInterfaceConfig(p, 620, 26, false);
                    p.getActionSender().setAccessMask(p, 1278, 23, 620, 0, 40);
                    mainstock = true;
                }
                if (buttonId == 26) {
                    p.getActionSender().setInterfaceConfig(p, 620, 23, true);
                    p.getActionSender().setInterfaceConfig(p, 620, 24, false);
                    p.getActionSender().setInterfaceConfig(p, 620, 29, true);
                    p.getActionSender().setInterfaceConfig(p, 620, 25, false);
                    p.getActionSender().setInterfaceConfig(p, 620, 27, false);
                    p.getActionSender().setInterfaceConfig(p, 620, 26, true);
                    p.getActionSender().setAccessMask(p, 1278, 24, 620, 0, 40);
                    mainstock = false;
                }
                PlayerItems pi = new PlayerItems();
                if (buttonId == 23) {
                    int itemid = returnItemId(shopid, buttonId2);
                    switch (packetId) {
                        case 233:
                            /* Value. */
                            p.getActionSender().sendMessage(p, "this item costs " + Engine.items.getItemValue(itemid) + " coins.");
                            break;
                        case 21:
                            /* Buy 1. */
                            buystock(p, itemid, 1);
                            break;
                        case 169:
                            /* Buy 5. */
                            buystock(p, itemid, 5);
                            break;
                        case 214:
                            /* Buy 10. */
                            buystock(p, itemid, 10);
                            break;
                        case 173:
                            /* Buy 50. */
                            buystock(p, itemid, 50);
                            break;
                        case 232:
                            /*Examine. */
                            p.getActionSender().sendMessage(p, Engine.items.getItemDescription(itemid));
                            break;
                    }
                }
                if (buttonId == 24) {
                    int itemid = items[buttonId2];
 
                    switch (packetId) {
                        case 233:
                            /* Value. */
                            p.getActionSender().sendMessage(p, "this item costs " + Engine.items.getItemValue(itemid) + " coins.");
                            break;
                        case 21:
                            /* Buy 1. */
                            buy(p, itemid, 1);
                            break;
                        case 169:
                            /* Buy 5. */
                            buy(p, itemid, 5);
                            break;
                        case 214:
                            /* Buy 10. */
                            buy(p, itemid, 10);
                            break;
                        case 173:
                            /* Buy 50. */
                            buy(p, itemid, 50);
                            break;
                        case 90:
                            /*Examine. */
                            p.getActionSender().sendMessage(p, Engine.items.getItemDescription(itemid));
                            break;
                    }
 
                }
                break;
            case 621:
                if (buttonId == 0) {
                    int itemid = p.items[buttonId2];
                    switch (packetId) {
                        case 233:
                            /*Value.*/
                            p.getActionSender().sendMessage(p, "this item is wourth " + Engine.items.getItemValue(itemid) + " coins.");
                            break;
                        case 21:
                            /*
                            * Buy 1.
                            */
                            sell(p, itemid, 1);
                            break;
                        case 169:
                            /*
                            * Buy 5.
                            */
                            sell(p, itemid, 5);
                            break;
                        case 214:
                            /*
                            * Buy 10.
                            */
                            sell(p, itemid, 10);
                            break;
                        case 173:
                            /*
                            * Buy 50.
                            */
                            sell(p, itemid, 50);
                            break;
                        case 90:
                            /*
                            * Examine.
                            */
                            p.getActionSender().sendMessage(p, Engine.items.getItemDescription(itemid));
                            break;
                    }
                }
                break;
        }
    }
    public void shopopen(Player p, int shopId) {
        p.getActionSender().removeShownInterface(p);
        p.getActionSender().showInterface(p, 620); //Shop
        p.getActionSender().setInventory(p, 621);
        p.getActionSender().setTab(p, 80, 621);//Inv
        shopid = shopId;
        Object[] setshopparams = new Object[]{shopId, 93};
        int shi = 621 << 16;
        int ship = (620 << 16) + 24;
        Object[] invparams = new Object[]{"", "", "", "", "Sell X", "Sell 10", "Sell 5", "Sell 1", "Value", -1, 0, 7, 4, 93, shi};
        Object[] shopparams = new Object[]{"", "", "", "", "Buy X", "Buy 10", "Buy 5", "Buy 1", "Value", -1, 0, 4, 10, 31, ship};
        p.getActionSender().runScript(p, 25, setshopparams, "vg");//Loads main stock items
        p.getActionSender().runScript(p, 150, invparams, "IviiiIsssssssss");
        p.getActionSender().runScript(p, 150, shopparams, "IviiiIsssssssss");
        p.getActionSender().setAccessMask(p, 1278, 0, 621, 0, 28);
        if (mainstock) {
            p.getActionSender().setInterfaceConfig(p, 620, 23, false);
            p.getActionSender().setInterfaceConfig(p, 620, 24, true);
            p.getActionSender().setInterfaceConfig(p, 620, 29, false);
            p.getActionSender().setInterfaceConfig(p, 620, 25, true);
            p.getActionSender().setInterfaceConfig(p, 620, 27, true);
            p.getActionSender().setInterfaceConfig(p, 620, 26, false);
            p.getActionSender().setAccessMask(p, 1278, 23, 620, 0, 40);
        } else {
            p.getActionSender().setInterfaceConfig(p, 620, 23, true);
            p.getActionSender().setInterfaceConfig(p, 620, 24, false);
            p.getActionSender().setInterfaceConfig(p, 620, 29, true);
            p.getActionSender().setInterfaceConfig(p, 620, 25, false);
            p.getActionSender().setInterfaceConfig(p, 620, 27, false);
            p.getActionSender().setInterfaceConfig(p, 620, 26, true);
            p.getActionSender().setAccessMask(p, 1278, 24, 620, 0, 40);
        }
    }
    /***************************************************************************************************************/
    /*                              ONLY EDIT THIS!                                                                */
    /***************************************************************************************************************/
 
    public int returnItemId(int shopid, int buttonId2) {
        switch (shopid) {
            case 868:
                switch (buttonId2) {
                    case 0:return 1931;
                    case 1:return 1935;
                    case 2:return 1735;
                    case 3:return 1925;
                    case 4:return 1923;
                    case 5:return 1887;
                    case 6:return 590;
                    case 7:return 1755;
                    case 8:return 2347;
                    case 9:return 550;
                    case 10:return 9003;
                }
                break;
            case 2:
                switch (buttonId2) {
                    case 0:return 1931;
                    case 1:return 1935;
                    case 2:return 1735;
                    case 3:return 1925;
                    case 4:return 1923;
                    case 5:return 1887;
                    case 6:return 590;
                    case 7:return 1755;
                    case 8:return 2347;
                    case 9:return 550;
                    case 10:return 9003;
                }
                break;
        }
        return -1;
 
    }
    public void openshop(Player p, int shopid) {
        switch (shopid) {
            case 1:
                shopopen(p, 868);
                generalStore = true;
                items  = Engine.shops.Generalshoplumb;
                itemsN = Engine.shops.GeneralshoplumbN;
                p.getActionSender().setItems(p, -1, 64209, 93, p.items, p.itemsN);//Shop Inventory
                p.getActionSender().setItems(p, -1, 64271, 31, items, itemsN);//Main Stock
            break;
            case 2:
                shopopen(p, 868);
                generalStore = true;
                items  = Engine.shops.Generalshopfalador;
                itemsN = Engine.shops.GeneralshopfaladorN;
                p.getActionSender().setItems(p, -1, 64209, 93, p.items, p.itemsN);//Shop Inventory
                p.getActionSender().setItems(p, -1, 64271, 31, items, itemsN);//Main Stock
            break;
           case 3:
                shopopen(p, 868);              // shop id 868 general store.
                generalStore = true;
                items  = Engine.shops.Generalshopvarrock;
                itemsN = Engine.shops.GeneralshopvarrockN;
                p.getActionSender().setItems(p, -1, 64209, 93, p.items, p.itemsN);//Shop Inventory
                p.getActionSender().setItems(p, -1, 64271, 31, items, itemsN);//Main Stock           //set to 32 to be in playerstock
            break;
            case 4: // Shop Case
               shopopen(p, 730); // Crafting shop
                items = Engine.shops.CraftingShop; // Declaring this from Shops.java
                itemsN = Engine.shops.CraftingShopN; // Declaring this from Shops.java
                p.getActionSender().setItems(p, -1, 64209, 93, p.items, p.itemsN); 
                p.getActionSender().setItems(p, -1, 64271, 31, items, itemsN);
             break;
            case 5: // Shop Case
               shopopen(p, 730); // Crafting shop
                items = Engine.shops.pkshop; // Declaring this from Shops.java
                itemsN = Engine.shops.pkshopN; // Declaring this from Shops.java
                p.getActionSender().setItems(p, 1, 11694, 93, p.items, p.itemsN); 
                p.getActionSender().setItems(p, -1, 64271, 31, items, itemsN);
                p.getActionSender().setString(p, "                                         Skill cape shop", 278, 88);
             break;
            case 6: // Shop Case
               shopopen(p, 730); // Crafting shop
                items = Engine.shops.foodshop; // Declaring this from Shops.java
                itemsN = Engine.shops.foodshopN; // Declaring this from Shops.java
                p.getActionSender().setItems(p, -1, 64209, 93, p.items, p.itemsN); 
                p.getActionSender().setItems(p, -1, 64271, 31, items, itemsN);
             break;
            case 7: // Shop Case
               shopopen(p, 730); // skillcape shop
                items = Engine.shops.scapeshop; // Declaring this from Shops.java
                itemsN = Engine.shops.scapeshopN; // Declaring this from Shops.java
                p.getActionSender().setItems(p, -1, 64209, 93, p.items, p.itemsN); 
                p.getActionSender().setItems(p, -1, 64271, 31, items, itemsN);
             break;
            case 8: // Shop Case
               shopopen(p, 730); // donor shop
                items = Engine.shops.donorshop; // Declaring this from Shops.java
                itemsN = Engine.shops.donorshopN; // Declaring this from Shops.java
                p.getActionSender().setItems(p, -1, 64209, 93, p.items, p.itemsN); 
                p.getActionSender().setItems(p, -1, 64271, 31, items, itemsN);
             break;
            case 9: // Shop Case
               shopopen(p, 730); // staff shop
                items = Engine.shops.staffshop; // Declaring this from Shops.java
                itemsN = Engine.shops.staffshopN; // Declaring this from Shops.java
                p.getActionSender().setItems(p, -1, 64209, 93, p.items, p.itemsN); 
                p.getActionSender().setItems(p, -1, 64271, 31, items, itemsN);
             break;
            case 10: // Shop Case
               shopopen(p, 730); // mage shop
                items = Engine.shops.mageshop; // Declaring this from Shops.java
                itemsN = Engine.shops.mageshopN; // Declaring this from Shops.java
                p.getActionSender().setItems(p, -1, 64209, 93, p.items, p.itemsN); 
                p.getActionSender().setItems(p, -1, 64271, 31, items, itemsN);
             break;
            case 11: // Shop Case
               shopopen(p, 730); // range shop
                items = Engine.shops.rangeshop; // Declaring this from Shops.java
                itemsN = Engine.shops.rangeshopN; // Declaring this from Shops.java
                p.getActionSender().setItems(p, -1, 64209, 93, p.items, p.itemsN); 
                p.getActionSender().setItems(p, -1, 64271, 31, items, itemsN);
             break;
            case 12: // Shop Case
               shopopen(p, 730); // melee shop
                items = Engine.shops.meleeshop; // Declaring this from Shops.java
                itemsN = Engine.shops.meleeshopN; // Declaring this from Shops.java
                p.getActionSender().setItems(p, -1, 64209, 93, p.items, p.itemsN); 
                p.getActionSender().setItems(p, -1, 64271, 31, items, itemsN);
             break;
            case 13: // Shop Case
               shopopen(p, 730); // supply shop
                items = Engine.shops.supplyshop; // Declaring this from Shops.java
                itemsN = Engine.shops.supplyshopN; // Declaring this from Shops.java
                p.getActionSender().setItems(p, -1, 64209, 93, p.items, p.itemsN); 
                p.getActionSender().setItems(p, -1, 64271, 31, items, itemsN);
             break;
            case 14: // Shop Case
               shopopen(p, 730); // 4 looks shop
                items = Engine.shops.justforlooksshop; // Declaring this from Shops.java
                itemsN = Engine.shops.justforlooksshopN; // Declaring this from Shops.java
                p.getActionSender().setItems(p, -1, 64209, 93, p.items, p.itemsN); 
                p.getActionSender().setItems(p, -1, 64271, 31, items, itemsN);
             break;
            case 15: // Shop Case
               shopopen(p, 730); // tab shop
                items = Engine.shops.tabshop; // Declaring this from Shops.java
                itemsN = Engine.shops.tabshopN; // Declaring this from Shops.java
                p.getActionSender().setItems(p, -1, 64209, 93, p.items, p.itemsN); 
                p.getActionSender().setItems(p, -1, 64271, 31, items, itemsN);
             break;
        }
    }
}