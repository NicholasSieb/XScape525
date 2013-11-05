package net.com.codeusa.util;

import java.io.File;
import java.util.LinkedList;

import net.com.codeusa.Engine;
import net.com.codeusa.model.Player;

/**
 *
 * @author Gravediggah
 */
public class Search implements Runnable{
	Thread t;
	Player p;
	int itemId;
	int finalCount = 0;

    public Search() {
    	t = new Thread(this);
    }
    
    public void perform(Player p, int itemId) {
    	this.p = p;
    	this.itemId = itemId;
    	t.start();
    }

    public int checkItem(Player p, String username, int itemId) {
        int itemCount=0;
        for (String s : getPlayer(username)) {
            if (s.startsWith("item") || s.startsWith("bankitem") || s.startsWith("equipment")) {
                if (Integer.parseInt(s.split(":")[1].split(",")[0]) == itemId) {
                    itemCount+=Integer.parseInt(s.split(":")[1].split(",")[1]);
                }
            }
        }
        if(itemCount>0) {
            p.getActionSender().sendMessage(p, "#   "+ Misc.upperFirst(username) + " has: " + itemCount);
        }
        return itemCount;
    }

    public String[] getPlayer(String username) {
        LinkedList<String> f = Misc.readFile("./data/characters/mainsave/" + username + ".dat");
        if (f.size() > 0) {
        	return f.get(0).split(String.valueOf((char) 0));
        } else {
            return new String[]{"", ""};
        }
    }

	@Override
	public void run() {
		p.getActionSender().sendMessage(p, "#   Checking item: " + Engine.items.getItemName(this.itemId) + "... (ID:" + this.itemId + ")");
        
        File dir = new File("./data/characters/mainsave");

        String[] children = dir.list();

        if (children != null) {
            if (children.length > 0) {
                for (String u : children) {
                    if (u != null) {
                        if (u.contains("dat")) {
                            String[] params = u.split("dat");
                            if (params.length >= 1) {
                                if (params[0].length() > 0) {
                                    this.finalCount+=checkItem(p, params[0].substring(0, params[0].length() - 1), itemId);
                                }
                            }
                        }
                    }
                }
            }
        }
        p.getActionSender().sendMessage(p, "#   Total in-game: " + this.finalCount);
	}
}