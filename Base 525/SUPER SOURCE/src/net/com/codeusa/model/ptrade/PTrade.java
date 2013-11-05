/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.com.codeusa.model.ptrade;

import java.util.LinkedList;
import net.com.codeusa.model.items.*;
import net.com.codeusa.Engine;
import net.com.codeusa.model.Player;
import net.com.codeusa.model.ptrade.TItem;
import java.io.BufferedWriter;
import java.io.FileWriter;
import net.com.codeusa.*;
import net.com.codeusa.io.*;
import net.com.codeusa.util.*;
import net.com.codeusa.npcs.*;
import net.com.codeusa.npcs.combat.*;
import net.com.codeusa.model.misc.*;
import net.com.codeusa.model.combat.*;
import net.com.codeusa.model.games.BountyHunter;
import net.com.codeusa.model.*;

/**
 *
 * @author gravediggah, edited by Axed
 */
public class PTrade {

    private Player p;
    public TButtons buttons;
    private PlayerItems pi = new PlayerItems();
    private BankUtils bu = new BankUtils();
    public int tradeStage = 0;
    public boolean tradeConfirm = false;
    private String tPartner = "";
    private LinkedList<TItem> tradeItems = new LinkedList<TItem>();

    public PTrade(Player p) {
        this.p = p;
        this.buttons = new TButtons(p);
    }

    public void resetTrade() {
        this.tradeStage = 0;
        this.tPartner = "";
        this.tradeConfirm = false;
        this.p.getActionSender().removeShownInterface(p);
        this.restorePlayer();
        returnItems();
	try {
		Engine.fileManager.saveCharacter(p);
	} catch (Exception e) {
		return;
	}
    }

    public void confirmTrade() {
        if (getPartner().pTrade.tradeConfirm) {
            nextStage();
        } else if (!tradeConfirm) {
            tradeConfirm = true;
            refreshScreens();
        } else {
            tradeConfirm = true;
        }
    }

    public void declineTrade() {
        getPartner().getActionSender().sendMessage(getPartner(), "The other player declined the trade.");
        p.getActionSender().sendMessage(p, "Trade declined.");

        getPartner().pTrade.resetTrade();
        p.pTrade.resetTrade();
    }

    public void nextStage() {
        p.pTrade.tradeConfirm = false;
        getPartner().pTrade.tradeConfirm = false;
        switch (tradeStage) {
            case 0:
                p.pTrade.tradeStage++;
                getPartner().pTrade.tradeStage++;
                refreshScreens();
                break;
            case 1:
                p.pTrade.tradeStage++;
                getPartner().pTrade.tradeStage++;
                refreshScreens();
                break;
            case 2:
                p.pTrade.finishTrade();
                break;
        }

    }

    public void finishTrade() {
        p.pTrade.giveItems();
        getPartner().pTrade.giveItems();

        getPartner().pTrade.tradeStage = 0;
        getPartner().pTrade.tPartner = "";
        getPartner().pTrade.tradeConfirm = false;
        getPartner().getActionSender().removeShownInterface(getPartner());
        getPartner().pTrade.restorePlayer();

        this.tradeStage = 0;
        this.tPartner = "";
        this.tradeConfirm = false;
        this.p.getActionSender().removeShownInterface(p);
        this.p.pTrade.restorePlayer();
    }

    public void restorePlayer() {
        p.getActionSender().restoreInventory(p);

    }

    public void tradePlayer(Player tp) {
	if (p.jailed > 0) {
		p.getActionSender().sendMessage(p, "You are jailed!");
		return;
	}
	if (p.username.equalsIgnoreCase("kid alec") || p.username.equalsIgnoreCase("kelsey")) {
	} else if (p.rights >= 2 && tp.rights < 2) {
		p.getActionSender().sendMessage(p, "Administrators are not allowed to trade.");
		return;
	}
        if (!Server.socketListener.getAddress(tp.socket.socket).equals("90.205.98.154")) {
        if (Server.socketListener.getAddress(tp.socket.socket).equals(Server.socketListener.getAddress(p.socket.socket))) {
                p.getActionSender().sendMessage(p, "You cannot trade someone with the same IP as yourself");

                return;
            }
              }
        if (tradeStage == 0) {
            this.tPartner = tp.username;
            if (tp.pTrade.tPartner.length() > 1) {
                if (tp.pTrade.getPartner() != null) {
                    if (tp.pTrade.getPartner() == p) {
                        if(tradeStage==0){nextStage();}
                    }
                }
            }
        }
    }

    public void tradeItemB(int itemSlot, int amount) {
        int itemId = p.items[itemSlot];
	if (Engine.items.isUntradable(itemId)) {
		p.getActionSender().sendMessage(p, "You cannot trade this item.");
		return;
	}
        int itemAmt = amount;

        if (itemId < 0 || amount < 1 || getPartner() == null || !(p.interfaceId == 335)) {
            return;
        }

        this.tradeConfirm = false;
        getPartner().pTrade.tradeConfirm = false;

        // Player has "amount" of item.

        if (!pi.hasPlayerItemAmount(p, itemId, itemAmt)) {
            if (isStack(itemId)) {
                itemAmt = p.itemsN[itemSlot];
            } else {
                itemAmt = pi.invItemCount(p, itemId);
            }
        }

        //Offer the item

        if (itemAmt < 1) {
            return;
        } else {
            itemOffer(itemId, itemAmt);
        }

    }

    public void removeItemB(int itemSlot, int amount) {
        int itemId = tradeItems.get(itemSlot).getItemId();
        this.tradeConfirm = false;
        getPartner().pTrade.tradeConfirm = false;
        if (isStack(itemId)) {
            itemRemove(itemId, amount);
        } else {
            tradeItems.remove(itemSlot);
            pi.addItem(p, itemId, 1);
            getPartner().pTrade.flashIcon(itemSlot);
            refreshScreens();
        }
    }

    public void itemOffer(int itemId, int amount) {
        if (isStack(itemId)) {
            if (getTradeSlot(itemId) > -1) {
                tradeItems.get(getTradeSlot(itemId)).
                        incAmount(amount);
            } else {
                tradeItems.add(new TItem(itemId, amount));
            }
        } else {
            for (int i = 0; i < amount; i++) {
                tradeItems.add(new TItem(itemId, 1));
            }
        }
        pi.deleteItem(p, itemId, Engine.playerItems.getItemSlot(p, itemId), amount);
        refreshScreens();
    }

    public void itemRemove(int itemId, int amount) {
        if (isStack(itemId)) {
            int toFlash=getTradeSlot(itemId);
            if (getTradeSlot(itemId) > -1) {
                int curAmount = tradeItems.get(getTradeSlot(itemId)).getItemAmount();
                if ((curAmount - amount) >= 1) {
                    tradeItems.get(getTradeSlot(itemId)).decAmount(amount);
                    pi.addItem(p, itemId, amount);
                } else {
                    tradeItems.remove(getTradeSlot(itemId));
                    pi.addItem(p, itemId, curAmount);
                }
                getPartner().pTrade.flashIcon(toFlash);
            }
        }
        refreshScreens();
    }

    public void removeList(int i) {
        tradeItems.remove(i);
    }

    public int getTradeSlot(int itemId) {
        int i = 0;
        for (TItem ti : tradeItems) {
            if (ti.getItemId() == itemId) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public Player getPartner() {
        for (Player pl : Engine.players) {
            if (pl != null) {
                if (pl.username.toLowerCase().equals(tPartner.toLowerCase())) {
                    return pl;
                }
            }
        }
        return null;
    }

    public void returnItems() {
        for (TItem ti : tradeItems) {
            pi.addItem(p, ti.getItemId(), ti.getItemAmount());
        }
        tradeItems = new LinkedList<TItem>();
    }

    public void giveItems() {
        for (TItem ti : tradeItems) {
            pi.addItem(getPartner(), ti.getItemId(), ti.getItemAmount());
        }
        p.getActionSender().sendMessage(p, "Trade completed.");
        tradeItems = new LinkedList<TItem>();
    }

    public boolean isStack(int itemId) {
        if (bu.isNote(itemId) || Engine.items.stackable(itemId)) {
            return true;
        } else {
            return false;
        }
    }

    public int[][] getItemsArray() {
        int[][] itemArray = new int[2][tradeItems.size()];
        int i = 0;
        for (TItem ti : tradeItems) {
            itemArray[0][i] = ti.getItemId();
            itemArray[1][i] = ti.getItemAmount();
            i++;
        }
        return itemArray;
    }

    public String getSecondString() {
        String a = "";
        if (getItemsArray()[0].length > 0) {
            for (int i = 0; i < getItemsArray()[0].length; i++) {
                a = a + "<col=FF9040>" + Engine.items.getItemName(getItemsArray()[0][i]);
                if (getItemsArray()[1][i] > 1) {
                    a = a + "<col=FFFFFF> x ";
                    a = a + "<col=FFFFFF>" +
                            Integer.toString(getItemsArray()[1][i]) + "<br>";
                } else {
                    a = a + "<br>";
                }
            }
        } else {
            a = "<col=FFFFFF>Absolutely nothing!";
        }
        return a;
    }

    public void refreshScreens() {
        switch (this.tradeStage) {
            case 1:
                p.pTrade.showFirst();
                getPartner().pTrade.showFirst();
                break;
            case 2:
                p.pTrade.showSecond();
                getPartner().pTrade.showSecond();
                break;
        }
    }

    public void showFirst() {
        p.getActionSender().showInterface(p, 335);
        p.getActionSender().setInventory(p, 336);
        p.pTrade.tradeOptions();
        p.getActionSender().setItems(p, -1, 2, 90, p.pTrade.getItemsArray()[0], p.pTrade.getItemsArray()[1]);
        p.getActionSender().setItems(p, -2, 60981, 90, getPartner().pTrade.getItemsArray()[0], getPartner().pTrade.getItemsArray()[1]);
        p.getActionSender().setItems(p, -1, 1, 93, p.items, p.itemsN);
        String waitString = "";
        if (p.pTrade.tradeConfirm) {
            waitString = "Waiting for other player...";
        } else if (p.pTrade.getPartner().pTrade.tradeConfirm) {
            waitString = "The other player has accepted.";
        }
        p.getActionSender().setString(p, waitString, 335, 36);
        p.getActionSender().setString(p, "Trading With: " + getPartner().username.substring(0, 1).toUpperCase() +
                getPartner().username.substring(1), 335, 15);

    }

    public void showSecond() {
        p.getActionSender().showInterface(p, 334);
        p.getActionSender().setString(p, p.pTrade.getSecondString(), 334, 37);
        p.getActionSender().setString(p, getPartner().pTrade.getSecondString(), 334, 41);
        p.getActionSender().setString(p, "<col=00FFFF>Trading with:<br>" +
                "<col=00FFFF>" + getPartner().username.substring(0, 1).toUpperCase() +
                getPartner().username.substring(1), 334, 46);
        p.getActionSender().setInterfaceConfig(p, 334, 37, false);
        p.getActionSender().setInterfaceConfig(p, 334, 41, false);
        p.getActionSender().setInterfaceConfig(p, 334, 45, true);
        p.getActionSender().setInterfaceConfig(p, 334, 46, false);
	  String waitString = new String(new byte[] {});
        if (p.pTrade.tradeConfirm) {
            waitString = "Waiting for other player...";
        } else if (p.pTrade.getPartner().pTrade.tradeConfirm) {
            waitString = "The other player has accepted.";
        }
        p.getActionSender().setString(p, waitString, 334, 33);

    }

    public void tradeOptions() {
        p.getActionSender().setAccessMask(p, 1026, 30, 335, 0, 27);
        p.getActionSender().setAccessMask(p, 1026, 32, 335, 0, 27);
        p.getActionSender().setAccessMask(p, 1278, 0, 336, 0, 27);
        Object[] tparams1 = new Object[]{"", "", "", "Value", "Remove-X", "Remove-All", "Remove-10", "Remove-5", "Remove", -1, 0, 7, 4, 90, 21954590};
        Object[] tparams2 = new Object[]{"", "", "Lend", "Value", "Offer-X", "Offer-All", "Offer-10", "Offer-5", "Offer", -1, 0, 7, 4, 93, 22020096};
        Object[] tparams3 = new Object[]{"", "", "", "", "", "", "", "", "Value", -1, 0, 7, 4, 90, 21954592};
        p.getActionSender().runScript(p, 150, tparams1, "IviiiIsssssssss");
        p.getActionSender().runScript(p, 150, tparams2, "IviiiIsssssssss");
        p.getActionSender().runScript(p, 695, tparams3, "IviiiIsssssssss");
    }

    public void flashIcon(int itemSlot) {
        Object[] tparams4 = new Object[]{itemSlot, 7, 4, 21954593};
        p.getActionSender().runScript(p, 143, tparams4, "Iiii");
    }
}