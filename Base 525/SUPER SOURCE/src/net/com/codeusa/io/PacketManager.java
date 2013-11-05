/*
 * Class PacketManager
 *
 * Version 1.0
 *
 * Sunday, August 17, 2008
 *
 * Created by Palidino76
 */

package net.com.codeusa.io;

import net.com.codeusa.Server;
import net.com.codeusa.Engine;
import net.com.codeusa.model.Player;
import net.com.codeusa.util.Misc;
import net.com.codeusa.net.packethandler.*;

public class PacketManager {
    /*
     * Create all the packets for use.
     */
    public Walking walk = new Walking();
    public PublicChat publicChat = new PublicChat();
    public Commands command = new Commands();
    public SwitchItems switchItems = new SwitchItems();
    public ActionButtons actionButtons = new ActionButtons();
    public Equipment equipment = new Equipment();
    public ItemOption1 itemOption1 = new ItemOption1();
    public ItemOption2 itemOption2 = new ItemOption2();
    public ItemOperate itemOperate = new ItemOperate();
    public ItemOnItem itemOnItem = new ItemOnItem();
    public DropItem dropItem = new DropItem();
    public PickupItem pickupItem = new PickupItem();
    public PlayerOption1 playerOption1 = new PlayerOption1();
    public PlayerOption2 playerOption2 = new PlayerOption2();
    public PlayerOption3 playerOption3 = new PlayerOption3();
    public NPCAttack npcAttack = new NPCAttack();
    public NPCOption1 npcOption1 = new NPCOption1();
    public NPCOption2 npcOption2 = new NPCOption2();
    public ItemSelect itemSelect = new ItemSelect();
    //public ItemSelect2 itemSelect2 = new ItemSelect2();
    public ObjectOption1 objectOption1 = new ObjectOption1();
    public ObjectOption2 objectOption2 = new ObjectOption2();
    public SwitchItems2 switchItems2 = new SwitchItems2();
    public ItemOnNPC itemOnNPC = new ItemOnNPC();
    public ItemOnObject itemOnObject = new ItemOnObject();

    public PacketManager() {
    }

    /**
     * Handle any packets.
     * <p>Handles all packets within the range of 0 - 255. Not every
     * packet has been setup, you'll have to do some yourself.
     * @param p The Player which the frame should be created for.
     * @param packetId The packet id to handle.
     * @param packetSize The number of bytes the packet contains.
     */
    public void parsePacket(Player p, int packetId, int packetSize) {
        if (p == null) {
            return;
        }
        if (p.clickDelay > 0 && packetId != 222) {
            /*
             * When delayed only allow chatting.
             */
            return;
        }
        switch (packetId) {
	case 224:
	    /**
	     * Item on Object
 	     */
	    itemOnObject.createPacket(p);
	    break;
        case 115:
            /*
             * This packet is constantly sent almost like a ping to verify the still exists.
             */
            break;
        case 22:
            /*
             * Recieved every time updateReq is set to true.
             */
            p.stream.readDWord(); // Junk? Same value every time.
            break;
	case 12:
	    /**
	     * Item on NPC
	     */
	    itemOnNPC.addItemOnNPCEvent(p);
	    break;
	case 60:
	p.objects();
	break;

     case 253:
                int playerId = p.stream.readUnsignedWord();
                playerId -= 33024;
                playerId = playerId / 256;
                playerId++;
                if (playerId < 0 || playerId >= Engine.players.length || Engine.players[playerId] == null) {
                    return;
                }
                if (Engine.players[playerId].pTrade.getPartner() == p) {
                p.pTrade.tradePlayer(Engine.players[playerId]);
                }

                break;
        case 99:
            /*
             * Unknown.
             */
            p.stream.readUnsignedWordBigEndianA();
            p.stream.readUnsignedWordA();
            break;
        case 117:
        case 248:
        case 247: // Possibly a packet sent if the login  is successful.
            /*
             * Unknown.
             */
            break;
        case 59:
            /*
             * Send every time you click your mouse.
             */
            p.stream.readUnsignedWord();
            p.stream.readDWord_v1();
            break;
        case 49:
            /*
             * Main map walking.
             */
case 119:
          case 138:
            walk.handlePacket(p, packetId, packetSize);
            break;
        case 222:
            /*
             * Public chatting.
             */
            publicChat.handlePacket(p, packetId, packetSize);
            break;
            case 42:
                long chatName = p.stream.readQWord();
                if (p.clanRoom.length() > 0) {
                    Engine.clanChat.leave(p);
                } else {
                    Engine.clanChat.join(p, Misc.longToString(chatName));
                }
                break;
            case 107:
            /*
             * When you type text starting with ::, its sent with this packet Id rather than as 
             * Normal chat.
             */
            command.handlePacket(p, packetId, packetSize);
            break;

            case 30:
	    long name = p.getByteVector().readQWord();
	    if(p.friends.size() >= 200) {
                p.getActionSender().sendMessage(p, "Your friends list is full.");
		break;
	    }
	    if(p.friends.contains((Long) name)) {
                p.getActionSender().sendMessage(p, "Already on your friends list.");
		break;
	    }
	    p.friends.add((Long) name);
	    p.getActionSender().sendFriend(p, name, p.getWorld(name));
	    break;

	case 61:
	    name = p.getByteVector().readQWord();
	    if(p.ignores.size() >= 100) {
                p.getActionSender().sendMessage(p, "Your ignore list is full.");
		break;
	    }
	    if(p.ignores.contains((Long) name)) {
                p.getActionSender().sendMessage(p, "Already on your ignore list.");
		break;
	    }
	    p.ignores.add((Long) name);
	    break;

	case 132:
	    name = p.getByteVector().readQWord();
	    p.friends.remove((Long) name);
	    break;

            case 195:
                    int itemId = p.getByteVector().readUnsignedWord();
		    System.out.println(itemId);
		    p.GrandExchange.setBuyItem(itemId);
		    break;

	case 2:
	    name = p.getByteVector().readQWord();
	    p.ignores.remove((Long) name);
	    break;

        case 178:
	    name = p.getByteVector().readQWord();
	    int numChars = p.getByteVector().readUnsignedByte();
	    String text = Misc.decryptPlayerChat(p.getByteVector(), numChars);
		if(p != null && p.online) {
	    for(Player client : Engine.players) {
        if(client != null && client.online) {
        if(Misc.stringToLong(client.username) == name) {
        client.getActionSender().sendReceivedPrivateMessage(client, Misc.stringToLong(p.username), p.rights, text);
		p.getActionSender().sendSentPrivateMessage(p, name, text);
		return;
		}
		}
	    }
		}
	    p.getActionSender().sendMessage(p, "This player is offline");
	    break;
        
        case 167:
            /*
             * Switching items on interfaces.
             */
            switchItems.handlePacket(p, packetId, packetSize);
            break;
        case 233:
        case 113:
        case 21:
        case 169:
        case 232:
		case 214:
		case 90:
		case 173:
		case 133:
		case 226:
		case 102:
            /*
             * When you click an interface button this packet is sent.
             */
            actionButtons.handlePacket(p, packetId, packetSize);
            break;
        case 3:
            /*
             * Equipping an item.
             */
             equipment.handlePacket(p, packetId, packetSize);
             break;
        case 203:
            /*
             * Item options 1.
             */
            itemOption1.handlePacket(p, packetId, packetSize);
            break;
        case 186:
            /*
             * Item operating.
             */
            itemOperate.handlePacket(p, packetId, packetSize);
            break;
        case 211:
            /*
             * Dropping an item.
             */
            dropItem.handlePacket(p, packetId, packetSize);
            break;
        case 201:
            /*
             * Picking an item up.
             */
            pickupItem.handlePacket(p, packetId, packetSize);
            break;
        case 160:
            /*
             * First option on a player.
             */
            playerOption1.handlePacket(p, packetId, packetSize);
            break;
        case 37:
            /*
             * Second option on a player.
             */
          	 playerOption2.handlePacket(p, packetId, packetSize);
           	 break;
        case 227:
            /*
             * Third option on a player.
             */
            playerOption3.handlePacket(p, packetId, packetSize);
            break;
        case 123:
            /*
             * NPC attack option.
             */
            npcAttack.handlePacket(p, packetId, packetSize);
            break;
        case 7:
            /*
             * NPC first option.
             */
            npcOption1.handlePacket(p, packetId, packetSize);
            break;
        case 220:
            /* 
             * Item eating, drinking, etc.
             */
            itemSelect.handlePacket(p, packetId, packetSize);
            break;
             //case 152:
            //    /* 
           //     *  Enchanted amulets, rings, necklaces, etc.
          //     */
         //    itemSelect2.handlePacket(p, packetId, packetSize);
        //    break;
        case 158:
            /*
             * Object first option.
             */
            objectOption1.handlePacket(p, packetId, packetSize);
            break;
        case 165:
            /*
             * Settings buttons, such as music volume.
             */
            p.stream.readDWord_v2();
            break;
        case 108:
            /*
             * Remove open interfaces.
             */
            p.getActionSender().removeShownInterface(p);
            break;
        case 228:
            /*
             * Second object option.
             */
            objectOption2.handlePacket(p, packetId, packetSize);
            break;
        case 52:
            /*
             * Second NPC option.
             */
            npcOption2.handlePacket(p, packetId, packetSize);
            break;
        case 38:
            /*
             * Item examining.
             */
            p.getActionSender().sendMessage(p, Engine.items.getItemDescription(p.stream.readUnsignedWordBigEndianA()));
            break;
        case 88:
            /*
             * NPC examining.
             */
            p.getActionSender().sendMessage(p, Server.engine.getNPCDescription(p.stream.readUnsignedWord()));
            break;
        case 84:
            /*
             * Object examining.
             */
            int objectId = p.stream.readUnsignedWordA();
            p.getActionSender().sendMessage(p, "An object.");
            break;
        case 47:
            /*
             * Idle packet.
             */
            break;

	case 70: //Magic
		new MagicOnPlayer(p);
	break;

	case 134:
		itemOption2.handlePacket(p, packetId, packetSize);
	break;
	case 40:
	    /**
	     * Item on item packet
	     */
	    itemOnItem.handlePacket(p, packetId, packetSize);
	break;
        case 179:
            /*
             * Switching items on interfaces.
             */
            switchItems2.handlePacket(p, packetId, packetSize);
            break;
	case 63:
	    /**
	     * First option on options.
	     */
	    new OptionClicking(p);
	break;
        default:
            Misc.println("[" + p.username + "] Unhandled packet: " + packetId);
            break;
        }
    }
}