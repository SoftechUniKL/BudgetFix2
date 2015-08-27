package Sparkonto;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;


public class SparzielPanel extends JPanel {
	
		Connection connect = null;
	
	//Eigenschaften der Klasse
		String _name;
		String _kategorie;
		int 	_ID;
		float _sparziel;
		float _eingezahlt;
		
	//Konstruktor
	public SparzielPanel(int ID,String name,String kategorie,float sparziel
			,float eingezahlt,  int top, int left) {
		this.setBounds(left, top , 658,97);
		this.setLayout(null);
		Color colr = new Color(30,144,255);
		this.setBackground(colr);
		this._ID=ID;
		this._name=name;
		this._kategorie=kategorie;
		this._sparziel=sparziel;
		this._eingezahlt= eingezahlt;
				
		JLabel LblName = new JLabel(_name);
		LblName.setBounds(0, 40, 110, 33);
		this.add(LblName);
		
		JLabel LblKategorie = new JLabel(_kategorie);
		LblKategorie.setBounds(120, 40, 110, 33);
		this.add(LblKategorie);
		
		JLabel LblWert = new JLabel(Float.toString(sparziel));
		LblWert.setBounds(240, 40, 110, 33);
		this.add(LblWert);
		
		JButton BtnLoeschen = new JButton("X");
		BtnLoeschen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				loeschenSparziel();
				repaint();
			}
		});
		
		BtnLoeschen.setBounds(0, 0, 42, 25);
		this.add(BtnLoeschen);
		
		JProgressBar progressBar = new JProgressBar();
		
		progressBar.setValue((int)((float)_eingezahlt/_sparziel * 100));
		progressBar.setBounds(333, 40, 100, 33);
		this.add(progressBar);
		
		JButton BtnEinzahlen = new JButton("+");
		BtnEinzahlen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							EinzahlungFenster frame = new EinzahlungFenster();
							frame.setVisible(true);
							
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
			}				
				
		});
		
		BtnEinzahlen.setBounds(450, 44, 41, 25);
		this.add(BtnEinzahlen);
	}
	
	//Dies ist die Methode zum Löschen eines Sparziels
	
		public void loeschenSparziel() {
			
			
			String eingezahlt = null;
			
			
			int reply = JOptionPane.showConfirmDialog(
		            null,
		            "Möchten Sie das Sparziel wirklich löschen?", 
		            "Bestätigung",
		            JOptionPane.YES_NO_OPTION);

		        
				if(reply == JOptionPane.YES_OPTION){       
		
			
			try{		
				connect = SparkontoDB.dbCon();
				
				
								
				String sqlQuery1 = "SELECT Eingezahlt FROM Sparkonto WHERE ID=" +(this._ID);
				Statement stm1 = connect.createStatement();

				ResultSet rs = stm1.executeQuery(sqlQuery1);
				

	            while (rs.next())
	                eingezahlt = rs.getString("Eingezahlt");
			
				
				String sqlQuery2 = "DELETE FROM Sparkonto WHERE ID=" +(this._ID);
				PreparedStatement stm2 = connect.prepareStatement(sqlQuery2);
				stm2.execute();
				
											
				JOptionPane.showMessageDialog(null,"Sparziel entfernt!\n"
						+ "Der Betrag von: " + eingezahlt + " € wird auf Ihrem \nHauptkonto gutgeschrieben!\n"
								+ "Klicken Sie auf 'Aktualisieren' um die Änderungen sichtbar zu machen!" ); 
				
				GlobVariablen.ziele=GlobVariablen.ziele-1;
				
												
			}		
				
			catch(Exception exc){
				exc.printStackTrace();
			}
		        }
		        
		      else{
		    	  JOptionPane.showMessageDialog(null,"Das Sparziel wurde nicht entfernt!");
		    	  
		      }
		}
		
		
		
		
		
}
			
			
			
		
	
			
	

