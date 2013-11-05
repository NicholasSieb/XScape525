// Decompiled by DJ v3.11.11.95 Copyright 2009 Atanas Neshkov  Date: 10/18/2009 9:04:15 AM
// Home Page: http://members.fortunecity.com/neshkov/dj.html http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   PlayerFollow.java

package net.com.codeusa.model;

import net.com.codeusa.Engine;

// Referenced classes of package net.com.codeusa.model:
//            Player

public class PlayerFollow
{

    public PlayerFollow()
    {
    }

    public void followPlayer(Player player)
    {
        Player player1 = Engine.players[player.followPlayer];
        if(player == null || player.isDead || player1 == null || player1.isDead)
        {
            player.followingPlayer = false;
            return;
        }
        int i = player1.absY - player.absY;
        if(player.freezeDelay == 0)
        {
            if(player1.absX - player.absX <= i * -1 && player1.absX - player.absX >= i - 1)
            {
                if(i < 0)
                    player.WalkingTo(player1.absX, player1.absY + 1);
                if(i > 0)
                    player.WalkingTo(player1.absX, player1.absY - 1);
            } else
            if(player1.absX - player.absX > 0)
                player.WalkingTo(player1.absX - 1, player1.absY);
            else
            if(player1.absX - player.absX < 0)
                player.WalkingTo(player1.absX + 1, player1.absY);
            if(player1.absX - player.absX == 0 && i < 0)
                player.WalkingTo(player1.absX, player1.absY + 1);
            if(player1.absX - player.absX == 0 && i >= 0)
                player.WalkingTo(player1.absX, player1.absY - 1);
        }
        if(player1.absX - player.absX > 12 || player.absX - player1.absX > 12 || player1.absY - player.absY > 12 || player.absY - player1.absY > 12)
            player.WalkingTo(player.absX, player.absY);
        player.requestFaceTo(player.followPlayer + 32768);
    }
}