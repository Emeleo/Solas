package com.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class HiScore implements Runnable {
	int[] levels = new int[23];
	long[] exp = new long[23];
	int count = 0;
	private Thread Xeq;

	public void start() {
		if (Xeq == null) {
			Xeq = new Thread(this);
			Xeq.start();
		}
	}

	public HiScore() {
		start();
	}

	public int[] getLevels(String user) {
		try {
			final URL url = new URL(
					"http://services.runescape.com/m=hiscore/g=runescape/compare.ws?user1="
							+ user);
			final BufferedReader br = new BufferedReader(new InputStreamReader(
					url.openStream()));
			String input;
			while ((input = br.readLine()) != null) {
				if (count < 23) {
					if (input.equals("<span class=\"columnLevel\">")) {
						String temp;
						if ((temp = br.readLine().trim()).charAt(0) == '<') {
							levels[count] = 0;
						} else {
							levels[count] = Integer.parseInt(temp.replace(",",
									""));
							count++;
						}
					}
				}
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		count = 0;
		return levels;
	}

	public long[] getExp(String user) {
		try {
			final URL url = new URL(
					"http://services.runescape.com/m=hiscore/g=runescape/compare.ws?user1="
							+ user);
			final BufferedReader br = new BufferedReader(new InputStreamReader(
					url.openStream()));
			String input;
			while ((input = br.readLine()) != null) {
				if (count < 23) {
					if (input.equals("<span class=\"columnTotal\">")) {
						String temp;
						if ((temp = br.readLine().trim()).charAt(0) == '<') {
							exp[count] = 0;
						} else {
							exp[count] = Long.parseLong(temp.replace(",", ""));
							count++;
						}
					}
				}
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		count = 0;
		return exp;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}
}
