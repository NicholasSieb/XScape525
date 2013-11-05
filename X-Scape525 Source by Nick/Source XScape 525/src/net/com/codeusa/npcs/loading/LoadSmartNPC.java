package net.com.codeusa.npcs.loading;

import java.io.*;
import net.com.codeusa.Engine;

public class LoadSmartNPC {

	public LoadSmartNPC() {
		String directory = "./data/npcs/SmartNPC.cfg";
		try {
			BufferedReader file = new BufferedReader(new FileReader(directory));
			String line, s1, s2, s3 = "";
			String[] values = new String[11];
			line = file.readLine().trim();
			while (line != null) {
				if (line.equals("[END]")) {
					file.close();
					break;
				} else {
					int index = line.indexOf("=");
					s1 = line.substring(0, index).trim();
					s2 = line.substring(index + 1).trim();
					s3 = s2.replaceAll("\t\t", "\t");
					values = s3.split("\t");
					int[] args = new int[values.length];
					for (int i = 0; i < values.length; i++) {
						args[i] = Integer.parseInt(values[i]);
					} 
					Engine.newSmartNPC(args);
				}
			}
		} catch (Exception e) {
			System.out.println("Error loading SmartNPC, printing trace...");
			e.printStackTrace();
		}
	}

}