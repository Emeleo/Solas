package com;

import java.applet.*;
import java.awt.*;
import java.net.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

import org.pushingpixels.substance.api.SubstanceConstants.FocusKind;
import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.api.skin.CremeCoffeeSkin;
import org.pushingpixels.substance.api.skin.GraphiteGlassSkin;
import org.pushingpixels.substance.api.skin.NebulaBrickWallSkin;
import org.pushingpixels.substance.api.skin.RavenSkin;
import org.pushingpixels.substance.api.skin.SubstanceCremeCoffeeLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceNebulaBrickWallLookAndFeel;

import com.gui.Exchange;
import com.gui.LeftTab;
import com.gui.Logger;
import com.gui.Lookup;
import com.gui.Music;
import com.gui.RightTab;
import com.gui.Tabs;
import com.io.HiScore;

import java.util.regex.*;

/**
 * @author Active
 */
public class RSLoader extends JFrame implements AppletStub {
	private static final HashMap<String, String> params = new HashMap<String, String>();
	final String baseLink = "http://world2.runescape.com/";
	String HTML = null;
	String URL = null;
	RightTab rt;
	LeftTab lt;
	Tabs tabbed;
	public RSLoader() {
		try {
			setIconImage(Toolkit.getDefaultToolkit().getImage("./res/img/mainicon.png"));
			setTitle("Solas - Assisting You");
			initComponents();
			parseParams();
			System.out
					.println("---------------Downloading runescape client----------");
			System.out.println(URL);
			downloadFile(URL);
			System.out
					.println("*********************Running runescape****************");

			Applet loader = (Applet) new URLClassLoader(new URL[] { new File(
					"runescape.jar").toURL() }).loadClass("Rs2Applet")
					.newInstance();
			loader.setStub(this);
			loader.init();
			loader.start();
			System.out.println("Runescape client loaded from runescape.jar");
			loader.setSize(756, 503);
			getContentPane().setLayout(new BorderLayout());
			getContentPane().add(loader, BorderLayout.CENTER);
			tabbed = new Tabs();
			getContentPane().add(tabbed, BorderLayout.SOUTH);
			tabbed.setVisible(true);			
			rt = new RightTab();
			getContentPane().add(rt, BorderLayout.EAST);
			rt.setVisible(true);
			setSize(1200, 800);
			setResizable(false);
			lt = new LeftTab();
			getContentPane().add(lt, BorderLayout.WEST);
			lt.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void appletResize(int width, int height) {
	}

	public final URL getCodeBase() {
		try {
			return new URL(baseLink);
		} catch (Exception e) {
			return null;
		}
	}

	public final URL getDocumentBase() {
		try {
			return new URL(baseLink);
		} catch (Exception e) {
			return null;
		}
	}

	public final String getParameter(String name) {
		return params.get(name);
	}

	public final AppletContext getAppletContext() {
		return null;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				SubstanceLookAndFeel.setSkin( new RavenSkin());
			UIManager.put(SubstanceLookAndFeel.FOCUS_KIND, FocusKind.NONE);
			}
		});
		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {

			public void run() {
				new RSLoader().setVisible(true);
			}
		});
	}

	String getContent(String link) {
		try {
			URL url = new URL(link);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					url.openStream()));
			String myparams = null;
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				myparams += inputLine;
			}
			in.close();
			return myparams;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	String getUrl() throws Exception {
		return baseLink + ext("archive=", " ", HTML);
	}

	void downloadFile(final String url) {
		try {
			BufferedInputStream in = new BufferedInputStream(
					new URL(url).openStream());
			FileOutputStream fos = new FileOutputStream("runescape.jar");
			BufferedOutputStream bout = new BufferedOutputStream(fos, 1024);
			byte[] data = new byte[1024];
			int x = 0;
			while ((x = in.read(data, 0, 1024)) >= 0) {
				bout.write(data, 0, x);
			}
			bout.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	String ext(String from, String to, String str1) {
		int p = 0;
		p = str1.indexOf(from, p) + from.length();
		return str1.substring(p, str1.indexOf(to, p));
	}

	void parseParams() {
		try {
			HTML = getContent(baseLink);
			Pattern regex = Pattern.compile(
					"<param name=\"?([^\\s]+)\"?\\s+value=\"?([^>]*)\"?>",
					Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
			Matcher regexMatcher = regex.matcher(HTML);
			while (regexMatcher.find())
				if (!params.containsKey(regexMatcher.group(1))) {
					params.put(remove(regexMatcher.group(1)),
							remove(regexMatcher.group(2)));
				}
			System.out
					.println("--------------------------------------------------------------------------------");
			System.out
					.println("----------------------Succesfully parsed parameters.----------------------------");
			URL = getUrl();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	String remove(String str) {
		return str.replaceAll("\"", "");
	}

	private void initComponents() {

		jMenuBar2 = new javax.swing.JMenuBar();
		jMenu3 = new javax.swing.JMenu();
		jMenu4 = new javax.swing.JMenu();
		jMenuBar3 = new javax.swing.JMenuBar();
		jMenu6 = new javax.swing.JMenu();
		jMenu7 = new javax.swing.JMenu();
		jMenuBar1 = new javax.swing.JMenuBar();
		jMenu1 = new javax.swing.JMenu();
		updateMenu = new javax.swing.JMenuItem();
		exitMenu = new javax.swing.JMenuItem();
		jMenu2 = new javax.swing.JMenu();
		mOpenMenu = new javax.swing.JMenuItem();
		jMenu13 = new javax.swing.JMenu();
		pauseMenu = new javax.swing.JMenuItem();
		nextMenu = new javax.swing.JMenuItem();
		preMenu = new javax.swing.JMenuItem();
		jMenu8 = new javax.swing.JMenu();
		currentMenu = new javax.swing.JMenuItem();
		historyMenu = new javax.swing.JMenuItem();
		statsMenu = new javax.swing.JMenuItem();
		merchMenu = new javax.swing.JMenuItem();
		jMenu9 = new javax.swing.JMenu();
		webMenu = new javax.swing.JMenuItem();
		abtMenu = new javax.swing.JMenuItem();

		jMenu3.setText("File");
		jMenuBar2.add(jMenu3);

		jMenu4.setText("Edit");
		jMenuBar2.add(jMenu4);

		jMenu6.setText("File");
		jMenuBar3.add(jMenu6);

		jMenu7.setText("Edit");
		jMenuBar3.add(jMenu7);

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jMenu1.setText("File");

		updateMenu.setText("Update");
		updateMenu.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				updateMenuActionPerformed(evt);
			}
		});
		jMenu1.add(updateMenu);

		exitMenu.setText("Exit");
		exitMenu.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				exitMenuActionPerformed(evt);
			}
		});
		jMenu1.add(exitMenu);

		jMenuBar1.add(jMenu1);

		jMenu2.setText("Music");

		mOpenMenu.setText("Open");
		mOpenMenu.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				mOpenMenuActionPerformed(evt);
			}
		});
		jMenu2.add(mOpenMenu);

		jMenu13.setText("Quick Style");

		pauseMenu.setText("Pause");
		pauseMenu.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				pauseMenuActionPerformed(evt);
			}
		});
		jMenu13.add(pauseMenu);

		nextMenu.setText("Next");
		nextMenu.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				nextMenuActionPerformed(evt);
			}
		});
		jMenu13.add(nextMenu);

		preMenu.setText("Previous");
		preMenu.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				preMenuActionPerformed(evt);
			}
		});
		jMenu13.add(preMenu);

		jMenu2.add(jMenu13);

		jMenuBar1.add(jMenu2);

		jMenu8.setText("Exchange");

		currentMenu.setText("Currently");
		currentMenu.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				currentMenuActionPerformed(evt);
			}
		});
		jMenu8.add(currentMenu);

		historyMenu.setText("History");
		historyMenu.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				historyMenuActionPerformed(evt);
			}
		});
		jMenu8.add(historyMenu);

		statsMenu.setText("Stats");
		statsMenu.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				statsMenuActionPerformed(evt);
			}
		});
		jMenu8.add(statsMenu);

		merchMenu.setText("Merch Mode");
		merchMenu.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				merchMenuActionPerformed(evt);
			}
		});
		jMenu8.add(merchMenu);

		jMenuBar1.add(jMenu8);

		jMenu9.setText("Help");

		webMenu.setText("View Website");
		webMenu.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				webMenuActionPerformed(evt);
			}
		});
		jMenu9.add(webMenu);

		abtMenu.setText("About");
		abtMenu.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				abtMenuActionPerformed(evt);
			}
		});
		jMenu9.add(abtMenu);

		jMenuBar1.add(jMenu9);

		setJMenuBar(jMenuBar1);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 813,
				Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 520,
				Short.MAX_VALUE));

		pack();
	}

	private void currentMenuActionPerformed(java.awt.event.ActionEvent evt) {
		new Exchange().run();
	}

	private void historyMenuActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void statsMenuActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void merchMenuActionPerformed(java.awt.event.ActionEvent evt) {

	}

	private void mOpenMenuActionPerformed(java.awt.event.ActionEvent evt) {
		new Music().run();
	}

	private void pauseMenuActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void nextMenuActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void preMenuActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void webMenuActionPerformed(java.awt.event.ActionEvent evt) {
		java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
		try {
			desktop.browse(new URI("www.oursite.com"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void abtMenuActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void updateMenuActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void exitMenuActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private JMenuItem abtMenu;
	private JMenuItem currentMenu;
	private javax.swing.JMenuItem exitMenu;
	private JMenuItem historyMenu;
	private javax.swing.JMenu jMenu1;
	private JMenu jMenu13;
	private JMenu jMenu2;
	private javax.swing.JMenu jMenu3;
	private javax.swing.JMenu jMenu4;
	private javax.swing.JMenu jMenu6;
	private javax.swing.JMenu jMenu7;
	private JMenu jMenu8;
	private JMenu jMenu9;
	private javax.swing.JMenuBar jMenuBar1;
	private javax.swing.JMenuBar jMenuBar2;
	private javax.swing.JMenuBar jMenuBar3;
	private JMenuItem mOpenMenu;
	private javax.swing.JMenuItem merchMenu;
	private javax.swing.JMenuItem nextMenu;
	private javax.swing.JMenuItem pauseMenu;
	private javax.swing.JMenuItem preMenu;
	private javax.swing.JMenuItem statsMenu;
	private JMenuItem updateMenu;
	private javax.swing.JMenuItem webMenu;
	// End of variables declaration
}
