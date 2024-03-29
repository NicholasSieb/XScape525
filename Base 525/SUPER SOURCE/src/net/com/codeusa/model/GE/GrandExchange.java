/*
 * Class GrandExchange
 *
 * Version 1.0
 *
 * Saturday 01 September 2009
 *
 * Created by Shahir
 */

package net.com.codeusa.model.GE;

import java.util.List;
import java.util.ArrayList;
import net.com.codeusa.model.Player;
import net.com.codeusa.model.items.PlayerItems;
import net.com.codeusa.util.Misc;
import net.com.codeusa.Server;
import net.com.codeusa.Engine;
import net.com.codeusa.model.GE.geList;

public class GrandExchange {

	public static List<Offer> offerList = new ArrayList<Offer>(2000);
		
	public Player p;
	 
	public int itemId = -1;
	
	public int minPrice = 0;
	public int basicPrice = 0;
	public int maxPrice = 0;
	
	public int itemPrice = 0;
	public int itemAmount = 0;
	
	public int offerType = -1;
	
	public PlayerItems PlayerItems = new PlayerItems();
	
	public static int i = -1;
	
	
	public GrandExchange(Player p) {
        this.p = p;
    }
	
	public void openMainInterface() {
		p.getActionSender().setConfig2(p, 563, 4194304);
		p.getActionSender().setConfig(p, 1112, -1);
		p.getActionSender().setConfig(p, 1113, -1);
		p.getActionSender().setConfig(p, 1109, -1);
		p.getActionSender().setConfig(p, 1110, 0);
		p.getActionSender().setConfig(p, 1111, 1);
		p.getActionSender().showInterface(p, 105);
		p.getActionSender().setItems(p, -1, -1327, 93, new int[] {}, new int[] {});
		p.getActionSender().restoreTabs(p);
		p.getActionSender().restoreInventory(p);
		p.getActionSender().setInterfaceConfig(p, 107, 0, true);
		p.getActionSender().setInterfaceConfig(p, 105, 0, false);
		p.getActionSender().setAccessMask(p, 6, 0, 105, -1, -1);
		//p.getActionSender().setItems(p, -1, -1757, 523, new int[] {995}, new int[] {135}); //the items if is in sell
		//p.getActionSender().setItems(p, -1, -1758, 524, new int[] {995}, new int[] {1359}); // if item if it is in buy
		//p.getActionSender().setItems(p, -1, -1759, 525, new int[] {995}, new int[] {13500});
		//p.getActionSender().setItems(p, -1, -1760, 526, new int[] {995}, new int[] {135000});
		//p.getActionSender().setItems(p, -1, -1761, 527, new int[] {995}, new int[] {1350000});
		//p.getActionSender().setItems(p, -1, -1762, 528, new int[] {995}, new int[] {13500000});
	}

	public void handleButtons(int buttonId) {
		switch(buttonId) {
			case 30:
				setBuySetup(0);
			break;
			case 31:
				setSellSetup(0);
			break;
			case 46:
				setBuySetup(1);
			break;
			case 47:
				setSellSetup(1);
			break;
			case 62:
			    setBuySetup(2);
			    break;
			case 63:
			    setSellSetup(2);
			    break;
			case 81:
			    setBuySetup(3);
			    break;
			case 82:
			    setSellSetup(3);
			    break;
			case 100:
			    setBuySetup(4);
			    break;
			case 101:
			    setSellSetup(4);
			    break;
			case 119:
			    setBuySetup(5);
			    break;
			case 120:
			    setSellSetup(5);
			    break;
			case 127:
				openMainInterface();
			break;
			case 157:
			    if(itemAmount != 1) {
				itemAmount--;
			    }
			    p.getActionSender().setConfig(p, 1109, itemId);
			    p.getActionSender().setConfig(p, 1110, itemAmount);
			    break;
			case 159:
			    if(itemAmount < 2147483647) {
				itemAmount++;
			    }
			    p.getActionSender().setConfig(p, 1109, itemId);
			    p.getActionSender().setConfig(p, 1110, itemAmount);
			    break;
			case 162:
			    if(itemAmount < 2147483647) {
				itemAmount++;
			    }
			    if(offerType == 0) {
				itemAmount = 1;
			    }
			    p.getActionSender().setConfig(p, 1109, itemId);
			    p.getActionSender().setConfig(p, 1110, itemAmount);
			    break;
			case 164:
			    if(itemAmount < 2147483637) {
				itemAmount += 10;
			    }
			    if(offerType == 0) {
				itemAmount = 10;
			    }
			    p.getActionSender().setConfig(p, 1109, itemId);
			    p.getActionSender().setConfig(p, 1110, itemAmount);
			    break;
			case 166:
			    if(itemAmount < 2147483547) {
				itemAmount += 100;
			    }
			    if(offerType == 0) {
				itemAmount = 100;
			    }
			    p.getActionSender().setConfig(p, 1109, itemId);
			    p.getActionSender().setConfig(p, 1110, itemAmount);
			    break;
			case 168:
			    if(itemAmount < 2147482647) {
				itemAmount += 1000;
			    }
			    if(offerType == 0) {
				itemAmount = PlayerItems.invItemCount(p, itemId);
			    }
			    p.getActionSender().setConfig(p, 1109, itemId);
			    p.getActionSender().setConfig(p, 1110, itemAmount);
			    break;
			case 177:
				setItemPrice(minPrice);
			break;
			case 183:
				setItemPrice(getitemPrice(maxPrice));
			break;
			case 180:
				setItemPrice(getitemPrice(basicPrice));
			break;
			case 173:
				setItemPrice(getitemPrice(itemPrice + 1));
			break;
			case 171:
				setItemPrice(getitemPrice(itemPrice - 1));
			break;
			case 190:
				if (offerType == 0) {
					confirmSellOffer();
				} else {
					confirmBuyOffer();
				}
			break;
			case 18:
			    for(Offer Offer : offerList) {
				if(Offer == null || Offer.id != p.playerId || Offer.slot != 0) {
				    continue;
				}
				if(Offer.type == 0 && !Offer.aborted) {
				    p.getActionSender().setItemSlot(p, Offer.slot, 995, Offer.currentPrice);
				} else if(!Offer.aborted) {
				    p.getActionSender().setItemSlot(p, Offer.slot, Offer.item, Offer.currentPrice);
				}
			    }
			    p.currentSlot = 0;
			    p.getActionSender().setConfig(p, 1112, 0);
			    break;
			case 34:
			    for(Offer Offer : offerList) {
				if(Offer == null || Offer.id != p.playerId || Offer.slot != 1) {
				    continue;
				}
				if(Offer.type == 0 && !Offer.aborted) {
				    p.getActionSender().setItemSlot(p, Offer.slot, 995, Offer.currentPrice);
				} else if(!Offer.aborted) {
				    p.getActionSender().setItemSlot(p, Offer.slot, Offer.item, Offer.currentPrice);
				}
			    }
			    p.currentSlot = 1;
			    p.getActionSender().setConfig(p, 1112, 1);
			    break;
			case 50:
			    for(Offer Offer : offerList) {
				if(Offer == null || Offer.id != p.playerId || Offer.slot != 2) {
				    continue;
				}
				if(Offer.type == 0 && !Offer.aborted) {
				    p.getActionSender().setItemSlot(p, Offer.slot, 995, Offer.currentPrice);
				} else if(!Offer.aborted) {
				    p.getActionSender().setItemSlot(p, Offer.slot, Offer.item, Offer.currentPrice);
				}
			    }
			    p.currentSlot = 2;
			    p.getActionSender().setConfig(p, 1112, 2);
			    break;
			case 69:
			    for(Offer Offer : offerList) {
				if(Offer == null || Offer.id != p.playerId || Offer.slot != 3) {
				    continue;
				}
				if(Offer.type == 0 && !Offer.aborted) {
				    p.getActionSender().setItemSlot(p, Offer.slot, 995, Offer.currentPrice);
				} else if(!Offer.aborted) {
				    p.getActionSender().setItemSlot(p, Offer.slot, Offer.item, Offer.currentPrice);
				}
			    }
			    p.currentSlot = 3;
			    p.getActionSender().setConfig(p, 1112, 3);
			    break;
			case 88:
			    for(Offer Offer : offerList) {
				if(Offer == null || Offer.id != p.playerId || Offer.slot != 4) {
				    continue;
				}
				if(Offer.type == 0 && !Offer.aborted) {
				    p.getActionSender().setItemSlot(p, Offer.slot, 995, Offer.currentPrice);
				} else if(!Offer.aborted) {
				    p.getActionSender().setItemSlot(p, Offer.slot, Offer.item, Offer.currentPrice);
				}
			    }
			    p.currentSlot = 4;
			    p.getActionSender().setConfig(p, 1112, 4);
			    break;
			case 107:
			    for(Offer Offer : offerList) {
				if(Offer == null || Offer.id != p.playerId || Offer.slot != 5) {
				    continue;
				}
				if(Offer.type == 0 && !Offer.aborted) {
				    p.getActionSender().setItemSlot(p, Offer.slot, 995, Offer.currentPrice);
				} else if(!Offer.aborted) {
				    p.getActionSender().setItemSlot(p, Offer.slot, Offer.item, Offer.currentPrice);
				}
			    }
			    p.currentSlot = 5;
			    p.getActionSender().setConfig(p, 1112, 5);
			    break;
			case 209:
			    boolean bool = false;
			    int i = 0;
			    for(Offer Offer : offerList) {
				if(Offer == null || Offer.id != p.playerId || Offer.slot != p.currentSlot) {
		    		    continue;
				}
				if(Offer.type == 0 && !Offer.aborted) {
				    p.getActionSender().resetItemSlot(p, Offer.slot);
				    Engine.playerItems.addItem(p, 995, Offer.currentPrice);
				    Offer.updatePrice(Offer.currentPrice, false);
				    i = Offer.slot;
				} else if(!Offer.aborted) {
				    p.getActionSender().resetItemSlot(p, Offer.slot);
				    Engine.playerItems.addItem(p, Offer.item, Offer.currentPrice);
				    Offer.updatePrice(Offer.currentPrice, false);
				    i = Offer.slot;
				}
				if(Offer.aborted) {
				    i = Offer.slot;
				    bool = true;
				    if(Offer.type == 0) {
				        Engine.playerItems.addItem(p, Offer.item, Offer.currentAmount);
					p.getActionSender().resetItemSlot(p, Offer.slot);
					bool = true;
				    } else {
					Engine.playerItems.addItem(p, 995, Offer.currentAmount * Offer.price);
					p.getActionSender().resetItemSlot(p, Offer.slot);
					bool = true;
				    }
				    Offer.clear();
				}
				if(Offer.completed) {
				    bool = true;
				    Offer.clear();
				}
			    }
			    if(bool) {
			        p.getActionSender().resetGe(p, i);
			    }
			    break;
			case 203:
			    for(Offer Offer : offerList) {
				if(Offer == null || Offer.id != p.playerId || p.currentSlot != Offer.slot) {
		    		    continue;
				}
				Offer.aborted = true;
				if(Offer.type == 0) {
				    p.getActionSender().setGe(p, Offer.slot, -3, Offer.item, Offer.price, Offer.amount, Offer.amount-Offer.currentAmount);
				    p.getActionSender().setItemSlot(p, Offer.slot, Offer.item, Offer.currentAmount);
				} else {
				    p.getActionSender().setGe(p, Offer.slot, 5, Offer.item, Offer.price, Offer.amount, Offer.amount-Offer.currentAmount);
				    p.getActionSender().setItemSlot(p, Offer.slot, 995, Offer.currentAmount);
				}
			    }
			    break;
		}
	}
	
	public void setSellSetup(int offerId) {
		itemId = -1;
		minPrice = 0;
		basicPrice = 0;
		maxPrice = 0;
		itemPrice = 0;
		itemAmount = 0;
		p.currentSlot = offerId;
		p.getActionSender().setConfig(p, 1109, -1);
		p.getActionSender().setConfig(p, 1110, 0);
		p.getActionSender().setConfig(p, 1111, 0);
		p.getActionSender().setConfig(p, 1112, -1);
		p.getActionSender().setConfig(p, 1113, 1);
		p.getActionSender().setConfig(p, 1112, offerId);
		p.getActionSender().setInventory(p, 107);
		Object[] o2 = new Object[]{"", "", "", "", "Offer", -1, 0, 7, 4, 93, 7012370};
		p.getActionSender().runScript(p, 149, o2, "IviiiIsssss");
		p.getActionSender().setAccessMask(p, 1026, 18, 107, 0, 27);
		p.getActionSender().setItems(p, -1, -1327, 93, p.items, p.itemsN);
		p.getActionSender().setInterfaceConfig(p, 107, 0, false);
		offerType = 0;
	}
	
	public void setBuySetup(int offerId) {
		itemId = -1;
		minPrice = 0;
		basicPrice = 0;
		maxPrice = 0;
		itemPrice = 0;
		itemAmount = 0;
		p.currentSlot = offerId;
		p.getActionSender().setConfig(p, 1109, -1);
		p.getActionSender().setConfig(p, 1110, 0);
		p.getActionSender().setConfig(p, 1111, 0);
		p.getActionSender().setConfig(p, 1113, 1);
		p.getActionSender().setConfig(p, 1113, 0);
		p.getActionSender().setConfig(p, 1112, -1);
		p.getActionSender().setConfig(p, 1112, offerId);
		offerType = 1;
		Object[] o = new Object[]{"Grand Exchange Item Search"};
		p.getActionSender().setGeSearch(p, o);
	}
	
	public void offerItem(int itemIndex) {
		itemId = p.items[itemIndex];
		itemAmount = PlayerItems.invItemCount(p, itemId);
		p.getActionSender().setConfig(p, 1109, itemId);
		p.getActionSender().setConfig(p, 1110, itemAmount);
		setPrices();
	}
	
	public void setBuyItem(int itemId) {
		this.itemId = itemId;
		itemAmount = 1;
		p.getActionSender().setConfig(p, 1109, itemId);
		p.getActionSender().setConfig(p, 1110, itemAmount);
		setPrices();
	}
	
	public void setItemPrice(int price) {
		p.getActionSender().setConfig(p, 1111, price);
		itemPrice = price;
	}
	
	public void setPrices() {
		minPrice = 0;
		basicPrice = 0;
		maxPrice = 0;
		for(geList list : Engine.geLoader.geList) {
		    if(list == null) {
			continue;
		    }
		    if(list.itemId == itemId) {
			minPrice = list.low;
			maxPrice = list.high;
			basicPrice = (minPrice + maxPrice)/ 2;
		    }
		}
		System.out.println("low: " + minPrice + "; mid: " + basicPrice + "; max: " + maxPrice);
		itemPrice = basicPrice;
		p.getActionSender().setConfig(p, 1115, minPrice);
		p.getActionSender().setConfig(p, 1114, basicPrice);
		p.getActionSender().setConfig(p, 1116, maxPrice);
		setItemPrice(itemPrice);
	}
	
	public int getitemPrice(int price) {
		if (price >= maxPrice) {
			return maxPrice;
		}
		if (price <= minPrice) {
			return minPrice;
		}
		return price;
	}
	
	public void confirmSellOffer() {
		if (itemAmount <= PlayerItems.invItemCount(p, itemId)) {
			offerList.add(new Offer(p.playerId, itemId, itemAmount, itemAmount, itemPrice, 0, p.username, false, p.currentSlot));
			openMainInterface();
			PlayerItems.deleteItem(p, itemId, itemAmount);
			p.getActionSender().setGe(p, p.currentSlot, -1, itemId, itemPrice, itemAmount, 0);
			updateOffers();
		} else {
			p.getActionSender().sendMessage(p, "You don't have enough items in your inventory to complete this offer.");
		}
	}
	
	public void confirmBuyOffer() {
		if ((itemAmount * itemPrice) <= PlayerItems.invItemCount(p, 995)) {
			offerList.add(new Offer(p.playerId, itemId, itemAmount, itemAmount, itemPrice, 1, p.username, false, p.currentSlot));
			openMainInterface();
			PlayerItems.deleteItem(p, 995, (itemAmount * itemPrice));
			p.getActionSender().setGe(p, p.currentSlot, 3, itemId, itemPrice, itemAmount, 0);
			updateOffers();
		} else {
			p.getActionSender().sendMessage(p, "You don't have enough coins to complete this offer.");
		}
	}
	
	public void updateOffers() {
	    for(Offer Offer : offerList) {
		if(Offer == null || Offer.type != 0 || Offer.completed || Offer.aborted) {
		    continue;
		}
		for(Offer Offer2 : offerList) {
		    if(Offer2 == null || Offer2.type != 1 || Offer2.completed || Offer2.aborted) {
			continue;
		    }
		    if(Offer2.item == Offer.item && Offer2.price >= Offer.price) {
			Player p2 = Server.engine.players[Server.engine.getIdFromName(Offer2.owner)];
			Player p3 = Server.engine.players[Server.engine.getIdFromName(Offer.owner)];
			int id = Offer.item;
			int amount = Offer2.currentAmount;
			if(Offer.currentAmount < Offer2.currentAmount) {
				amount = Offer.currentAmount;
			}
			if(p2 != null || !p2.disconnected[0]) {
		            p2.getActionSender().sendMessage(p2, "Theres been a update on your offer!");
			}
			if(p3 != null || !p3.disconnected[0]) {
			    p3.getActionSender().sendMessage(p3, "Theres been a update on your offer!");
			}
			Offer2.currentAmount -= amount;
			Offer.currentAmount -= amount;
			Offer.updatePrice(amount, true);
			Offer2.updatePrice(amount, true);
			p3.getActionSender().setGe(p3, Offer.slot, -1, Offer.item, Offer.price, Offer.amount, Offer.amount-Offer.currentAmount);
			p2.getActionSender().setGe(p2, Offer2.slot, 3, Offer2.item, Offer2.price, Offer2.amount, Offer2.amount-Offer2.currentAmount);
			if(Offer.currentAmount == 0) {
			    p3.getActionSender().setGe(p3, Offer.slot, -3, Offer.item, Offer.price, Offer.amount, Offer.amount-Offer.currentAmount);
			    Offer.completed = true;
			}
			if(Offer2.currentAmount == 0) {
			    p2.getActionSender().setGe(p2, Offer2.slot, 5, Offer2.item, Offer2.price, Offer2.amount, Offer2.amount-Offer2.currentAmount);
			    Offer2.completed = true;
			}
		    }
		}
	    }
	}
}