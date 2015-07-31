package Sparkonto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;


public class SparzielPanel extends JPanel {
	
	Connection connect = null;
	
	//Eigenschaften der Klasse
		String _name;
		String _kategorie;
		int 	_ID;
		float _sparziel;
		float _eingezahlt;
		
	//Konstruktor
	public SparzielPanel(int ID,String name,String kategorie,float sparziel,float eingezahlt,  int top, int left) {
		this.setBounds(left, top , 658,97);
		this.setLayout(null);
		this._ID=ID;
		this._name=name;
		this._kategorie=kategorie;
		this._sparziel=sparziel;
		this._eingezahlt= eingezahlt;
				
		JLabel LblName = new JLabel(_name);
		LblName.setBounds(10, 40, 102, 33);
		this.add(LblName);
		
		JLabel LblKategorie = new JLabel(_kategorie);
		LblKategorie.setBounds(124, 40, 114, 33);
		this.add(LblKategorie);
		
		JLabel LblWert = new JLabel(Float.toString(sparziel));
		LblWert.setBounds(235, 40, 114, 33);
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
		progressBar.setBounds(333, 40, 208, 33);
		this.add(progressBar);
		
		JButton BtnEinzahlen = new JButton("+");
		BtnEinzahlen.setBounds(570, 44, 41, 25);
		this.add(BtnEinzahlen);
	}
	
		public void loeschenSparziel() {
			
			
			String wert = null;
			
			
			int reply = JOptionPane.showConfirmDialog(
		            null,
		            "Möchten Sie das Sparziel wirklich löschen?", 
		            "Bestätigen",
		            JOptionPane.YES_NO_OPTION);

		        
				if(reply == JOptionPane.YES_OPTION){       
		
			
			try{		
				connect = SparkontoDB.dbCon();
				
				
								
				String sqlQuery1 = "SELECT Sparziel FROM Sparkonto WHERE ID=" +(this._ID);
				Statement stm1 = connect.createStatement();

				ResultSet rs = stm1.executeQuery(sqlQuery1);
				

	            while (rs.next())
	                wert = rs.toString();
			
				
				String sqlQuery2 = "DELETE FROM Sparkonto WHERE ID=" +(this._ID);
				PreparedStatement stm2 = connect.prepareStatement(sqlQuery2);
				stm2.execute();
				
											
				JOptionPane.showMessageDialog(null,"Sparziel entfernt!\n"
						+ "Der Betrag von: " + wert + "wird auf Ihrem Konto gutgeschrieben!" ); 
				
												
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
			
			
			
		
	
			
	

