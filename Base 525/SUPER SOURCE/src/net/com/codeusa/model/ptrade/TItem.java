/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.com.codeusa.model.ptrade;

/**
 *
 * @author gravediggah, edited by Axed
 */
public class TItem {
    private int itemId;
    private int itemAmount;

    public TItem(int itemId, int itemAmount) {
        this.itemId=itemId;
        this.itemAmount=itemAmount;
    }

    public int getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(int itemAmount) {
        this.itemAmount = itemAmount;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public void incAmount(int value) {
        this.itemAmount+=value;
    }

    public void decAmount(int value) {
        this.itemAmount-=value;
    }

    

}