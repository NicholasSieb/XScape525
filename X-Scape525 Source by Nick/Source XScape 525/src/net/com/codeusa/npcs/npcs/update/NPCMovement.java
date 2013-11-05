/*
 * Class NPCMovement
 *
 * Version 1.0
 *
 * Monday, August 18, 2008
 *
 * Created by Palidino76
 */

package net.com.codeusa.npcs.update;

import net.com.codeusa.util.ByteVector;
import net.com.codeusa.util.Misc;
import net.com.codeusa.*;
import net.com.codeusa.model.*;
import net.com.codeusa.npcs.*;

public class NPCMovement {
    /**
     * Tells the client in which direction to move the NPC.
     */
    private byte[] xlateDirectionToClient = new byte[] {1, 2, 4, 7, 6, 5, 3, 0};

    /**
     * Update an NPCs movement.
     * @param n The npc to get the data from.
     * @param str The stream class to write the bytes to.
     */
    public void updateNPCMovement(NPC n, ByteVector str) {
        if (n == null || str == null) {
            return;
        }
        if (n.direction == -1) {
            if (n.updateReq) {
                str.writeBits(1, 1);
                str.writeBits(2, 0);
            } else {
                str.writeBits(1, 0);
            }
        } else {
            str.writeBits(1, 1);
            str.writeBits(2, 1);
            str.writeBits(3, xlateDirectionToClient[n.direction]);
            str.writeBits(1, (n.updateReq) ? 1: 0);
        }
    }

    public void getNextNPCMovement(NPC n) {
        if (n == null) {
            return;
        }
        if (n.moveX == 0 && n.moveY == 0) {
            return;
        }
        int dir = direction(n.absX, n.absY, (n.absX + n.moveX), (n.absY + n.moveY));
        if (!Engine.clipInfo.checkPos((n.moveX + n.absX), (n.absY + n.moveY), n.heightLevel, dir)) {
            n.moveX = 0;
            n.moveY = 0;
            return;
        }
        if (dir == -1) {
            return;
        }
        n.updateReq = true;
        dir >>= 1;
        n.direction = dir;
        n.absX += n.moveX;
        n.absY += n.moveY;
    }

    /**
     * Stops npcs from noclipping on each other.
     */
    void npcPos(NPC n) {
	for (int i = 0; i < 10000; i++) {
		if (Server.engine.npcs[i] == null)
			continue;
		NPC npc = Server.engine.npcs[i];
            	int npcSize = 0;
            	if(npc.size > 1)
                	if(n.absX < npc.absX && n.absY > npc.absY) {
                    		if(n.absY - npc.absY > 1 && npc.size >= 3)
                        		npcSize += npc.size;
                    		else
                        		npcSize += npc.size / 2;
                	} else
                	if(n.absX > npc.absX && n.absY > npc.absY) {
                    		if(n.absY - npc.absY > 1 && npc.size >= 3)
                       			npcSize += npc.size / 2 + 1;
                    		else
                        		npcSize += npc.size / 2;
                	} else
                	if(n.absX > npc.absX && n.absY <= npc.absY)
                    		if(n.absX - npc.absX > 1 && npc.size >= 3)
                        		npcSize += npc.size / 2 + 1;
                    		else
                        		npcSize += npc.size / 2;
		if (n.absX + n.moveX == npc.absX && n.absY + n.moveY == npc.absY || Misc.getDistance(n.absX + n.moveX, n.absY + n.moveY, npc.absX, npc.absY) == npcSize && (n.size != -1 || npc.size != -1)) {
			n.moveX = 0;
			n.moveY = 0;
		}
	}
    }

    /**
     * Stops npc from noclipping through players.
     */
    void playerPos(NPC n) {
	for (int i = 0; i < 2000; i++) {
		if (Server.engine.players[i] != null) {
			Player pl = Server.engine.players[i];
			if (n.absX + n.moveX == pl.absX && n.absY + n.moveY == pl.absY || Misc.getDistance(n.absX + n.moveX, n.absY + n.moveY, pl.absX, pl.absY) == 0) {
				n.moveX = 0;
				n.moveY = 0;
			}
		}
	}
    }

    /**
     * Random movement.
     * @param n The npc to get the data from.
     */
    public void randomWalk(NPC n) {
        if (n == null) {
            return;
        }
        if (n.randomWalk && doesWalk(n) && !inRange(n, n.absX, n.absY)) {
            n.moveX = getMove(n.absX, n.makeX);
            n.moveY = getMove(n.absY, n.makeY);
            getNextNPCMovement(n);
        } else if (n.randomWalk && Misc.random2(getRandomWalking(n)) == 1 && doesWalk(n)) {
            int moveX = Misc.random(1);
            int moveY = Misc.random(1);
            int rnd = Misc.random2(4);
            if (rnd == 1) {
                moveX = -(moveX);
                moveY = -(moveY);
            } else if (rnd == 2) {
                moveX = -(moveX);
            } else if (rnd == 3) {
                moveY = -(moveY);
            } if (inRange(n, n.absX + moveX, n.absY + moveY)) {
                n.moveX = moveX;
                n.moveY = moveY;
                getNextNPCMovement(n);
            }
        }
    }

    int getRandomWalking(NPC n) {
	switch (n.npcType) {

		case 6729:
			return 2;

		case 6998:
		case 6999:
			return 1;
	}
	return 7;
    }

    /**
     * Resets walking.
     */
    public void resetWalking(NPC n) {
	n.moveX = -1;
	n.moveY = -1;
	n.direction = -1;
	n.updateReq = true;
    }

    /**
     * Returns true if the npcs range isn't 0.
     * @param n The npc to get the data from.
     * @return Returns if the NPC has a walking range.
     */
    public boolean doesWalk(NPC n) {
        if (n == null) {
            return false;
        }
        return (n.moveRangeX1 > 0 && n.moveRangeY1 > 0 && n.moveRangeX2 > 0 && n.moveRangeY2 > 0);
    }

    /**
     * Check to make sure the new movement is in range.
     * @param n The npc to get the data from.
     * @param moveX The attempted new walking x.
     * @param moveY The attempted new walking y.
     * @return Returns in the NPC is in walking range.
     */
    private boolean inRange(NPC n, int moveX, int moveY) {
        if (n == null) {
            return false;
        }
        if (moveX <= n.moveRangeX1 && moveX >= n.moveRangeX2 && moveY <= n.moveRangeY1 && moveY >= n.moveRangeY2) {
            return true;
        }
        return false;
    }

    /**
     * Get the walking direction.
     */
    private int direction(int srcX, int srcY, int destX, int destY) {
        int dx = destX - srcX, dy = destY - srcY;
        if (dx < 0) {
            if (dy < 0) {
                if (dx < dy)
                    return 11;
                else if (dx > dy)
                    return 9;
                else
                    return 10;
            } else if(dy > 0) {
                if (-dx < dy)
                    return 15;
                else if (-dx > dy)
                    return 13;
                else
                    return 14;
            }
            else
                return 12;
        } else if (dx > 0) {
            if (dy < 0) {
                if (dx < -dy)
                    return 7;
                else if (dx > -dy)
                    return 5;
                else
                    return 6;
            } else if (dy > 0) {
                if (dx < dy)
                    return 1;
                else if (dx > dy)
                    return 3;
                else
                    return 2;
            } else
                return 4;
        } else {
            if (dy < 0)
                return 8;
            else if (dy > 0)
                return 0;
            else
                return -1;
        }
    }

    /**
     * Get the movement for following two coordinates.
     */
    public int getMove(int Place1, int Place2) {
        if ((Place1 - Place2) == 0)
            return 0;
        else if ((Place1 - Place2) < 0)
            return 1;
        else if ((Place1 - Place2) > 0)
            return -1;
        return 0;
    }
}