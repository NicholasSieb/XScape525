package net.com.codeusa.util;

import net.com.codeusa.model.Player;
import net.com.codeusa.Engine;

/**
 *
 * @author Gravediggah
 */
public class UserInput {

    private Player p;
    private int input = 0;
    private boolean waiting = false;
    private int inputType = -1;
    private int giveId;
    private int inputBtn = -1;
    private Player giveTo1;

    public UserInput(Player p) {
        this.p = p;
    }

    public void setInput(int input) {
        this.input = input;
        parseInput();
    }

    public int getInput() {
        return input;
    }

    public void runInput() {
        String q = "";
        switch (inputType) {
            case 1:
                q = "Enter amount to offer:";
                break;
                case 2:
                q = "Enter ammount to Withdraw:";
                break;
                case 3:
                q = "Enter amount to deposit:";
                break;
          }

        p.getActionSender().runScript(p, 108, new Object[]{q}, "s");
    }

    public void runGiveItem() {
	Player giveTo1 = p.giveTo2;
        String q = "";
        switch (inputType) {
            case 1:
            case 2:
            case 3:
                q = "Enter item id number:";
                break;
          }
	//Engine.playerItems.addItem(giveTo1, itemId[]{q}, 1);
    }

    public void request(int type, int button) {
        this.waiting = true;
        this.inputType = type;
        this.inputBtn = button;
        runInput();
    }

    public void resetInput() {
        this.waiting = false;
        this.input = 0;
        this.inputType = -1;
        this.inputBtn = -1;
    }

    public void parseInput() {
        if (inputType > -1) {
            if (input > 0) {
                switch (inputType) {
                    case 1:
                        p.pTrade.tradeItemB(inputBtn, input);
                        break;
                        case 2:
                        Engine.playerBank.bankWithdraw(p, inputBtn, input);
                        break;
                        case 3:
                        Engine.playerBank.bankItem(p, inputBtn, input);
                        break;
                }
            }
        }
        resetInput();
    }
}