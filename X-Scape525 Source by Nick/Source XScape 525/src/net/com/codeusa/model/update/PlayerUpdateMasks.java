/*
 * Class PlayerUpdateMasks
 *
 * Version 1.0
 *
 * Sunday, August 17, 2008
 *
 * Created by Palidino76
 */

package net.com.codeusa.model.update;

import net.com.codeusa.Engine;
import net.com.codeusa.model.Player;
import net.com.codeusa.util.Misc;
import net.com.codeusa.util.ByteVector;
import net.com.codeusa.util.Vars;
import net.com.codeusa.net.packethandler.Equipment;

public class PlayerUpdateMasks {
    /**
     * Backwards and sideways default emotes.
     */
    private int[] otherEmotes = {0x337, 0x334, 0x335, 0x336};

    /**
     * Writes the update mask bits to the client.
     * @param str The stream to write the bytes to.
     * @param maskData The mask to tell the client what update masks to expect.
     */
    public void writeMask(ByteVector str, int maskData) {
        if (str == null) {
            return;
        }
        if (maskData >= 0x100) {
            maskData |= 0x10;
            str.writeByte(maskData & 0xFF);
            str.writeByte(maskData >> 8);
        } else {
            str.writeByte(maskData);
        }
    }

    /**
     * Write the chat text mask.
     * @param p The Player to update for.
     * @param str The stream to write the bytes to.
     */
    public void appendChatText(Player p, ByteVector str) {
        if (p == null || str == null) {
            return;
        }
        str.writeWordA(p.chatTextEffects);
	str.writeByteC(p.rights);
        ByteVector chatStr = new ByteVector(256, 256);
        chatStr.writeByte(p.chatText.length());
        chatStr.outOffset += Misc.encryptPlayerChat(chatStr.outBuffer, 0, chatStr.outOffset, p.chatText.length(), p.chatText.getBytes());
        str.writeByteC(chatStr.outOffset);
        str.writeBytes(chatStr.outBuffer, chatStr.outOffset, 0);
        chatStr = null;
    }

	public void appendHit1(Player p, ByteVector str) {
		if (p == null || str == null) {
			return;
		}
		str.writeByteS(p.hitDiff1);
		if (p.poisonHit1 == 0) {
			if (p.hitDiff1 > 0) {
				str.writeByteS(1);
			} else {
				str.writeByteS(0);
			}
		} else {
			str.writeByteS(2);
		}
		int hpRatio = p.skillLvl[3] * 255 / p.getLevelForXP(3);
		if (hpRatio > 255) {
			hpRatio = 255;
		}
		str.writeByteS(hpRatio);
	}
	public void appendHit2(Player p, ByteVector str) {
		if (p == null || str == null) {
			return;
		}
		str.writeByteS(p.hitDiff2);
		if (p.poisonHit2 == 0) {
			if (p.hitDiff2 > 0) {
				str.writeByteA(1);
			} else {
				str.writeByteA(0);
			}
		} else {
			str.writeByteA(2);
		}
	}

    /**
     * Forced chat mask.
     * @param p The Player to update for.
     * @param str The stream to write the bytes to.
     */
    public void appendPlayerForceChat(Player p, ByteVector str) {
        if (p == null || str == null) {
            return;
        }
        str.writeString(p.forceChat);
    }

    /**
     * Player animation mask.
     * @param p The Player to update for.
     * @param str The stream to write the bytes to.
     */
    public void appendPlayerAnim(Player p, ByteVector str) {
        if (p == null || str == null) {
            return;
        }
        str.writeWord(p.animReq);
        str.writeByteS(p.animDelay);
    }

    /**
     * GFX mask.
     * @param p The Player to update for.
     * @param str The stream to write the bytes to.
     */
    public void appendPlayerGFX(Player p, ByteVector str) {
        if (p == null || str == null) {
            return;
        }
        str.writeWord(p.gfxReq);
        str.writeDWord_v1(p.gfxDelay);
    }

    /**
     * Facing NPCs and players mask.
     * @param p The Player to update for.
     * @param str The stream to write the bytes to.
     */
    public void appendPlayerFaceTo(Player p, ByteVector str) {
        if (p == null || str == null) {
            return;
        }
        str.writeWord(p.faceToReq);
    }

    /**
     * Setting appearace mask.
     * @param p The Player to update for.
     * @param str The stream to write the bytes to.
     */
    public void appendPlayerAppearance(Player p, ByteVector str) {
        if (p == null || str == null) {
            return;
        }
        Equipment e = new Equipment();
        ByteVector playerProps = new ByteVector(1, 100);
        playerProps.writeByte(p.gender);
        if ((p.gender & 0x2) == 2) {
            playerProps.writeByte(0);
            playerProps.writeByte(0);
        }
        playerProps.writeByte(p.headIconSkull);
        playerProps.writeByte(p.headIconPrayer);
        if (p.npcType < 0) {
            for (int i = 0; i < 4; i++) {
                if (p.equipment[i] > 0) {
                    playerProps.writeWord(32768 + getRealId(p.equipment[i]));
                } else {
                    playerProps.writeByte(0);
                }
            }
            if (p.equipment[Vars.chest] > 0) {
                    playerProps.writeWord(32768 + getRealId(p.equipment[Vars.chest]));
            } else {
                playerProps.writeWord(0x100 + p.look[2]); //Torso
            }
            if (p.equipment[Vars.shield] > 0) {
                playerProps.writeWord(32768 + getRealId(p.equipment[Vars.shield]));
            } else {
                playerProps.writeByte(0);
            }
            if (!e.isFullbody(p.equipment[Vars.chest])) {
                playerProps.writeWord(0x100 + p.look[3]); //pArms
            } else {
                playerProps.writeByte(0);
            }
            if (p.equipment[Vars.legs] > 0) {
                playerProps.writeWord(32768 + getRealId(p.equipment[Vars.legs]));
            } else {
                playerProps.writeWord(0x100 + p.look[5]); //pLegs
            }
            if (!e.isFullhat(p.equipment[Vars.hat]) && !e.isFullmask(p.equipment[Vars.hat])) {
                playerProps.writeWord(0x100 + p.look[0]); //pHead
            } else {
                playerProps.writeByte(0);
            }
            if (p.equipment[Vars.hands] > 0) {
                playerProps.writeWord(32768 + getRealId(p.equipment[Vars.hands]));
            } else {
                playerProps.writeWord(0x100 + p.look[4]); //pHands
            }
            if (p.equipment[Vars.feet] > 0) {
                playerProps.writeWord(32768 + getRealId(p.equipment[Vars.feet]));
            } else {
                playerProps.writeWord(0x100 + p.look[6]); //pFeet
            }
            if (!e.isFullmask(p.equipment[Vars.hat]))
            {
                playerProps.writeWord(0x100 + p.look[1]); //pBeard
            } else {
                playerProps.writeByte(0);
            }
        } else {
            playerProps.writeWord(-1);
            playerProps.writeWord(p.npcType);
        }
        for (int j = 0; j < 5; j++)
            playerProps.writeByte(p.color[j]);
        playerProps.writeWord(p.standEmote);
        playerProps.writeWord(otherEmotes[0]);
        playerProps.writeWord(p.walkEmote);
        playerProps.writeWord(otherEmotes[1]);
        playerProps.writeWord(otherEmotes[2]);
        playerProps.writeWord(otherEmotes[3]);
        playerProps.writeWord(p.runEmote);
        playerProps.writeQWord(Misc.stringToLong(p.username));
        calculateCombat(p);
        playerProps.writeByte(p.combatLevel);
        playerProps.writeWord(0);
        str.writeByte(playerProps.outOffset);
        str.writeBytes(playerProps.outBuffer, playerProps.outOffset, 0);
        playerProps = null;
        e = null;
    }

    /**
     * Calculate combat level.
     * @param p The Player to update for.
     */
    /**
     * Calculate combat level.
     * @param p The Player to update for.
     */

	public void calculateCombat(Player p) {
		p.combatLevel = ((int)Math.floor(combat(p)));
	}

	public double combat(Player p) {

		//Stat variables

		//Universals
		double hitpoints = p.getLevelForXP(3);
		double defence = p.getLevelForXP(1);
		double prayer = p.getLevelForXP(5);
		double summoning = p.getLevelForXP(23);

		//Melee
		double attack = p.getLevelForXP(0);
		double strength = p.getLevelForXP(2);

		//Range
		double range = p.getLevelForXP(4);

		//Magic
		double magic = p.getLevelForXP(6);

		//Equations

		double meleeFormula = (0.25 * ((1.30 * (attack + strength)) + defence + hitpoints + (0.50 * prayer) + (0.50 * summoning)));
		double rangeFormula = (0.25 * ((1.30 * (1.50 * range)) + defence + hitpoints + (0.50 * prayer) + (0.50 * summoning)));
		double magicFormula = (0.25 * ((1.30 * (1.50 * magic)) + defence + hitpoints + (0.50 * prayer) + (0.50 * summoning)));
		
		//Setting combat

		double combat = 0;
		if ((meleeFormula >= magicFormula) && (meleeFormula >= rangeFormula)) {
			combat = meleeFormula;
		}
		if ((rangeFormula >= meleeFormula) && (rangeFormula >= magicFormula)) {
			combat = rangeFormula;
		}
		if ((magicFormula >= meleeFormula) && (magicFormula >= rangeFormula)) {
			combat = magicFormula;
		}

		//Administrator modifications

	//	if (p.rights >= 2) {
        //  if (!p.username.equalsIgnoreCase("shahir")) {
	//		combat = 0;
	//	}
        //}

		//Returning

		return combat;
	}

    /**
     * Get the id for the item.
     * @param item the item to check.
     * @return Returns the id that should be used for equipping.
     */
    private int getRealId(int item) {
	return Engine.items.getEquipId(item);
    }
}