package net.com.codeusa.world.mapdata;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import net.com.codeusa.util.*;


public class MapData {
	
        private static HashMap<Integer, int[]> mapRegions;
        private static int mapId;

	public int mapData[] = new int[4];

	public int[] getMapData(int region) {
		return mapRegions.get(region);
	}

public void loadRegions() {
        try {
            DataInputStream in = new DataInputStream(new FileInputStream("./data/mapdata/Mapdata.dat"));
            while (in.available() != 0) {
                mapId = in.readShort();
                for (int data = 0; data < 4; data++) {
                    mapData[data] = in.readInt();
                }
                mapRegions.put(mapId, new int[]{mapData[0], mapData[1], mapData[2], mapData[3]});
            }
            in.close();
            in = null;
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

	static {
		mapId = 0;
		mapRegions = new HashMap<Integer, int[]>();
	}

}