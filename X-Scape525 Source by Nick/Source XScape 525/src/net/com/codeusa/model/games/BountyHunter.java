package net.com.codeusa.model.games;

import java.util.ArrayList;
import java.util.List;
import net.com.codeusa.model.Player;
import net.com.codeusa.util.Misc;
import net.com.codeusa.Engine;
import net.com.codeusa.Server;

public class BountyHunter {

    public Engine en;

    public static List<Integer> lowCrator = new ArrayList<Integer>(200);
    public static List<Integer> midCrator = new ArrayList<Integer>(200);
    public static List<Integer> highCrator = new ArrayList<Integer>(200);

    public int getTargetLow(Player p) {
	//System.out.println(lowCrator.size());
	if(lowCrator.size() == 0) {
	    return 0;
	}
	int i = Misc.random(lowCrator.size());
	i--;
	if(i == -1) {
	    i++;
	}
	if(lowCrator.get(i) == p.playerId) {
	    for(int i2 = 0; i2 < lowCrator.size(); i2++) {
		if(lowCrator.get(i2) != p.playerId) {
		    return lowCrator.get(i2);
		}
	    }
	} else {
	    return lowCrator.get(i);
	}
	return 0;
    }
    public int getTargetMid(Player p) {
	//System.out.println(midCrator.size());
	if(midCrator.size() == 0) {
	    return 0;
	}
	int i = Misc.random(midCrator.size());
	i--;
	if(i == -1) {
	    i++;
	}
	if(midCrator.get(i) == p.playerId) {
	    for(int i2 = 0; i2 < midCrator.size(); i2++) {
		if(midCrator.get(i2) != p.playerId) {
		    return midCrator.get(i2);
		}
	    }
	} else {
	    return midCrator.get(i);
	}
	return 0;
    }

    public int getTargetHigh(Player p) {
    p.getActionSender().setHintIcon(p, 10, p.bhTarget, 1, -1);
	//System.out.println("There are currently "+highCrator.size() + " Players in bounty hunter");
	if(highCrator.size() == 0) {
	    return 0;
	}
	int i = Misc.random(highCrator.size());
	i--;
	if(i == -1) {
	    i++;
	}
	if(highCrator.get(i) == p.playerId) {
	    for(int i2 = 0; i2 < highCrator.size(); i2++) {
		if(highCrator.get(i2) != p.playerId) {
		    return highCrator.get(i2);
		}
	    }
	} else {
	    return highCrator.get(i);
	}
	return 0;
    }

    /*have to do it this way to prevent nullpointer*/
    public void removeLow(Player p) {
	for(int i = 0; i < lowCrator.size(); i++) {
	    if(lowCrator.get(i) == p.playerId) {
		lowCrator.remove(lowCrator.get(i));
	    }
	}
    }

    public void removeMid(Player p) {
	for(int i = 0; i < midCrator.size(); i++) {
	    if(midCrator.get(i) == p.playerId) {
		midCrator.remove(midCrator.get(i));
	    }
	}
    }

    public void removeHigh(Player p) {
	for(int i = 0; i < highCrator.size(); i++) {
	    if(highCrator.get(i) == p.playerId) {
		highCrator.remove(highCrator.get(i));
	    }
	}
    }

    public void enterLow(Player p) {
	//teleEnter(p, 0);
    //getBountyHeadIcon(p);
	//lowCrator.add(p.playerId);
    }

    public void enterMid(Player p) {
	//teleEnter(p, 4);
    //getBountyHeadIcon(p);
	//midCrator.add(p.playerId);
    }

    public void enterHigh(Player p) {
         Player p9 = Engine.players[p.bhTarget];
		    if(p9 != null) {
	    	   	p.getActionSender().setString(p, "" + p9.username , 653, 8);
		    } else {
	    		p.getActionSender().setString(p, "None" , 653, 8);
		    }
        p.InBounty = 1;
    p.isSkulled = true;
    getBountyHeadIcon(p);
	highCrator.add(p.playerId);
            p.getActionSender().setHintIcon(p, 10, p.bhTarget, 1, -1);
    }

    public void getBountyHeadIcon(Player p) {
        p.appearanceUpdateReq = true;
        p.updateReq = true;
	if (p == null) {
	    return;
	}
	int totalPrice1 = en.items.getItemValue(p.equipment[0]) + en.items.getItemValue(p.equipment[1]) + en.items.getItemValue(p.equipment[2]) + en.items.getItemValue(p.equipment[3]) + en.items.getItemValue(p.equipment[4]) + en.items.getItemValue(p.equipment[5]) + en.items.getItemValue(p.equipment[6]) + en.items.getItemValue(p.equipment[7]) + en.items.getItemValue(p.equipment[8]) + en.items.getItemValue(p.equipment[9]) + en.items.getItemValue(p.equipment[10] + en.items.getItemValue(p.equipment[11]) + en.items.getItemValue(p.equipment[12]) + en.items.getItemValue(p.equipment[13]));
	int totalPrice2 = en.items.getItemValue(p.items[0]) * p.itemsN[0] + en.items.getItemValue(p.items[1]) * p.itemsN[1] + en.items.getItemValue(p.items[2]) * p.itemsN[2] + en.items.getItemValue(p.items[3]) * p.itemsN[3] + en.items.getItemValue(p.items[4]) * p.itemsN[4] + en.items.getItemValue(p.items[5]) * p.itemsN[5] + en.items.getItemValue(p.items[6]) * p.itemsN[6] + en.items.getItemValue(p.items[7]) * p.itemsN[7] + en.items.getItemValue(p.items[8]) * p.itemsN[8] + en.items.getItemValue(p.items[9]) * p.itemsN[9] + en.items.getItemValue(p.items[10]) * p.itemsN[10] + en.items.getItemValue(p.items[11]) * p.itemsN[11] + en.items.getItemValue(p.items[12]) * p.itemsN[12] + en.items.getItemValue(p.items[13]) * p.itemsN[13] + en.items.getItemValue(p.items[14]) * p.itemsN[14] + en.items.getItemValue(p.items[15]) * p.itemsN[15] + en.items.getItemValue(p.items[16]) * p.itemsN[16] + en.items.getItemValue(p.items[17]) * p.itemsN[17] + en.items.getItemValue(p.items[18]) * p.itemsN[18] + en.items.getItemValue(p.items[19]) * p.itemsN[19] + en.items.getItemValue(p.items[20]) * p.itemsN[20] + en.items.getItemValue(p.items[21]) * p.itemsN[21] + en.items.getItemValue(p.items[22]) * p.itemsN[22] + en.items.getItemValue(p.items[23]) * p.itemsN[23];
	int finalPrice = totalPrice1 + totalPrice2;
	if (finalPrice  < 10000) {
	    p.headIconSkull = 6;
	} else if (finalPrice  < 100000) {
	    p.headIconSkull = 5;
	} else if (finalPrice  < 500000) {
	    p.headIconSkull = 4;
	} else if (finalPrice  < 1000000) {
	    p.headIconSkull = 3;
	} else if (finalPrice  > 20000000) {
	    p.headIconSkull = 2;
        p.appearanceUpdateReq = true;
    p.updateReq = true;
	}

	p.headIconSkull = p.headIconSkull;
	p.updateReq = p.appearanceUpdateReq = true;
    }

    public void enter(Player p, int i) {
	if(i == 1) {
	    p.bhTarget = getTargetLow(p);
	    Player p2 = Engine.players[p.bhTarget];
	    enterLow(p);
	} else if(i == 2) {
	    p.bhTarget = getTargetMid(p);
	    Player p2 = Engine.players[p.bhTarget];
	    enterMid(p);
	} else {
	    p.bhTarget = getTargetHigh(p);
	    Player p2 = Engine.players[p.bhTarget];
	    enterHigh(p);
        getBountyHeadIcon(p);
	}
    p.getActionSender().setHintIcon(p, 10, p.bhTarget, 1, -1);
    getBountyHeadIcon(p);
	p.updateReq = p.appearanceUpdateReq = true;
	p.getActionSender().setOverlay(p, 653);
	p.getActionSender().setInterfaceConfig(p, 653, 9, true);
	p.inBounty = true;
	Player p2 = Engine.players[p.bhTarget];
	if(p2 != null) {
	    p.getActionSender().setString(p, "" + p2.username , 653, 8);
	} else {
	    p.getActionSender().setString(p, "None" , 653, 8);
	}
    }

    public void teleEnter(Player p, int height) {
	int randomEnter = Misc.random(19);
	if(randomEnter == 0) {
	    p.setCoords(3135, 3758, 0);
	} else if(randomEnter == 1) {
	    p.setCoords(3121, 3754, 0);
	} else if(randomEnter == 2) {
	    p.setCoords(3110, 3747, 0);
	} else if(randomEnter == 3) {
	    p.setCoords(3091, 3735, 0);
	} else if(randomEnter == 4) {
	    p.setCoords(3086, 3717, 0);
	} else if(randomEnter == 5) {
	    p.setCoords(3091, 3706, 0);
	} else if(randomEnter == 6) {
	    p.setCoords(3096, 3692, 0);
	} else if(randomEnter == 7) {
	    p.setCoords(3101, 3682, 0);
	} else if(randomEnter == 8) {
	    p.setCoords(3108, 3670, 0);
	} else if(randomEnter == 9) {
	    p.setCoords(3124, 3665, 0);
	} else if(randomEnter == 10) {
	    p.setCoords(3138, 3669, 0);
	} else if(randomEnter == 11) {
	    p.setCoords(3146, 3681, 0);
	} else if(randomEnter == 12) {
	    p.setCoords(3163, 3696, 0);
	} else if(randomEnter == 13) {
	    p.setCoords(3171, 3701, 0);
	} else if(randomEnter == 14) {
	    p.setCoords(3180, 3708, 0);
	} else if(randomEnter == 15) {
	    p.setCoords(3181, 3720, 0);
	} else if(randomEnter == 16) {
	    p.setCoords(3171, 3737, 0);
	} else if(randomEnter == 17) {
	    p.setCoords(3170, 3706, 0);
	} else if(randomEnter == 18) {
	    p.setCoords(3163, 3753, 0);
	} else if(randomEnter == 19) {
	    p.setCoords(3147, 3758, 0);
	}
    }

    public void exit(Player p, int i) {
         p.getActionSender().setHintIcon(p, 0, p.bhTarget, 1, -1);
	    p.setCoords(3166, 3674, 4);
        p.pickedUp = 0;
        p.headIconSkull = -1;
        p.InBounty = 0;
	    removeHigh(p);
	    for(Player p2 : Engine.players) {
		if(p2 == null || !p2.inBounty) {
		    continue;
		}
		if(p2.bhTarget == p.playerId) {
		    p2.bhTarget = getTargetHigh(p2);
            p2.getActionSender().setHintIcon(p2, 10, p2.bhTarget, 1, -1);
		    p2.getActionSender().sendMessage(p2, "Your target has left the crator.");
		    Player p3 = Engine.players[p2.bhTarget];
		    if(p3 != null) {
	    	   	p2.getActionSender().setString(p2, "" + p3.username , 653, 8);
		    } else {
	    		p2.getActionSender().setString(p2, "None" , 653, 8);
		    }
        }
        }
	p.inBounty = false;
    p.InBounty = 0;
	p.getActionSender().removeOverlay(p);
	p.headIconSkull = -1;
	p.updateReq = p.appearanceUpdateReq = true;
    }
}