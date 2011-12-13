/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Lookup.java
 *
 * Created on Dec 11, 2011, 7:28:34 PM
 */
package com.gui;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import com.info.Skills;
import com.io.HiScore;



	/**
	 *
	 * @author Tim
	 */
	public class Lookup extends javax.swing.JPanel implements Runnable {

	    private Thread Xeq;

		/** Creates new form Lookup */
	    public Lookup() {
	        initComponents();
	        start();
		}
		public void start() {
			if (Xeq == null) {
				Xeq = new Thread(this);
				Xeq.start();
			}
		}
		BufferedImage[] icons = new BufferedImage[23];

		private void getIcons() {
			System.setProperty("http.agent", ""); 
			for (int i = 0; i < icons.length; i++) {
				try {
					icons[i] = ImageIO.read(new URL(
							"http://www.runescape.com/img/hiscore/compare/skills/"
									+ firstCap(Skills.SKILL_NAMES[i]) + ".png"));
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			jTextField1.setText("");
			jTextField1.setEnabled(true);
			jButton1.setEnabled(true);
		}

		public String firstCap(String s) {
			return Character.toUpperCase(s.charAt(0)) + s.substring(1);
		}
	    /** This method is called from within the constructor to
	     * initialize the form.
	     * WARNING: Do NOT modify this code. The content of this method is
	     * always regenerated by the Form Editor.
	     */
	    @SuppressWarnings("unchecked")
	    // <editor-fold defaultstate="collapsed" desc="Generated Code">
	    private void initComponents() {

	        jButton1 = new javax.swing.JButton();
	        jTextField1 = new javax.swing.JTextField();
	        jLabel1 = new javax.swing.JLabel();

	        jButton1.setText("Go");
			jTextField1.setText("Obtaining Icons...");
			jTextField1.setEnabled(false);
			jButton1.setEnabled(false);	
	        jButton1.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                jButton1ActionPerformed(evt);
	            }
	        });

	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
	        this.setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(layout.createSequentialGroup()
	                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
	                        .addGap(10, 10, 10))
	                    .addGroup(layout.createSequentialGroup()
	                        .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))))
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jButton1)
	                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 12, Short.MAX_VALUE))
	        );
	    }// </editor-fold>


		private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
			int[] lvlArray = new HiScore().getLevels(jTextField1.getText());
			jLabel1.setText("<html>");
			for (int i = 0; i < lvlArray.length; i++) {	
				jLabel1.setText(jLabel1.getText() +"<img src=\""+getImagePath(icons[i])+ "\">");
				if (i % 2 == 0) {
					jLabel1.setText(jLabel1.getText() + lvlArray[i] + "<br> ");
				} else {
					jLabel1.setText(jLabel1.getText() + lvlArray[i]
							+ "          ");
				}

			}
			jLabel1.setText(jLabel1.getText() + "</html>");
			invalidate();
		}
		public String getImagePath(BufferedImage icon) {
		    try {
		        File temp = File.createTempFile("image", ".png");
		        ImageIO.write(icon, "PNG", new FileOutputStream(temp));
		        return temp.getAbsolutePath();
		    } catch (IOException ex) {
		        ex.printStackTrace();
		    }
		    return null;
		}
                                     

	    // Variables declaration - do not modify
	    private javax.swing.JButton jButton1;
	    private javax.swing.JLabel jLabel1;
	    private javax.swing.JTextField jTextField1;
	    // End of variables declaration

		@Override
		public void run() {
			getIcons();
		}
	}

