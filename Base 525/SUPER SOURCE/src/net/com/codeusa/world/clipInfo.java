package net.com.codeusa.world;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import net.com.codeusa.Server;

public class clipInfo {

    //Height - X - Y
    public static byte coords[][][] = new byte[4][4000][4000];

    public boolean checkPos(int absX, int absY, int height, int dir) {
        //System.out.println("x: "+absX+" y: "+absY+" height: "+height+" dir: "+dir);
        int type = coords[height][absX][absY];
        
        //Check to walking to north are possible/not posibble...
        if (dir == 0 && (type == 1 || type == 4 || type == 6 || type == 7 || type == 9 || type == 11 || type == 13 || type == 14)) {
            return false;
        }
        //Check to walking to east are possible/not posibble...
        if (dir == 4 && (type == 1 || type == 7 || type == 15 || type == 10 || type == 11 || type == 12 || type == 14 || type == 5)) {
            return false;
        }
        //Check to walking to south are possible/not posibble...
        if (dir == 8 && (type == 1 || type == 2 || type == 3 || type == 4 || type == 5 || type == 6 || type == 7 || type == 12)) {
            return false;
        }
        //Check to walking to west are possible/not posibble...
        if (dir == 12 && (type == 1 || type == 3 || type == 6 || type == 9 || type == 10 || type == 11 || type == 12 || type == 8)) {
            return false;
        }
        return true;
    }

    public clipInfo() {
        System.out.println("Noclip Loading!");
        String line = "", token = "", token2 = "", token3[] = new String[500];
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader("./data/noclip.cfg"));
            line = in.readLine().trim();
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (line != null) {
            int spot = line.indexOf("=");
            if (spot > -1) {
                token = line.substring(0, spot).trim();
                token2 = line.substring(spot + 1).trim();
                token2 = token2.replaceAll("\t\t", "\t");
                token2 = token2.replaceAll("\t\t", "\t");
                token3 = token2.split("\t");
                if (token.equals("tile")) {
                    coords[Integer.parseInt(token3[2])][Integer.parseInt(token3[0])][Integer.parseInt(token3[1])] = (byte) Integer.parseInt(token3[3]);
                }
            } else {
                if (line.equals("[EOF]")) {
                    try {
                        in.close();
                    } catch (IOException ioexception) {
                    }
                    in = null;
                    return;
                }
            }
            try {
                line = in.readLine().trim();
            } catch (IOException ioexception1) {
                try {
                    in.close();
                } catch (IOException ioexception) {
                }
                in = null;
                return;
            }
        }
        return;
    }
}