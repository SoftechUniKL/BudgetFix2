package Sparkonto;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;
import javax.swing.JPasswordField;

import Registrierung.RegisterFenster;
import Anmelden.AnmeldeDatenbank;
import Anmelden.AnmeldenFenster;

import java.awt.Font;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SparzielEingabefenster extends JFrame {
	
	Connection connect = null;
	
	private JPanel contentPane;
	private JTextField TxtName;
	private JTextField TxtKategorie;
	private JTextField TxtSparziel;
	
	public static void main(String[] args) {
		

		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sparziele frame = new Sparziele();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SparzielEingabefenster() {
		
		connect = SparkontoDB.dbCon();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
//Button Fenster-Schliessen		
		final JLabel BtnSchliessen = new JLabel();
		BtnSchliessen.addMouseListener(new MouseAdapter() {
			//Schliessenbutton ist grau
			@Override
			public void mouseEntered(MouseEvent arg0) {
				BtnSchliessen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Design/schliessen_button2.png")));
			}
			
			//Schliessenbutton ist blau
			@Override
			public void mouseExited(MouseEvent arg0) {
				BtnSchliessen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Design/Schliessen_Button.png")));
			}
			
			//Schliesst das Fenster
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				dispose();
				
			}
		});
			
				
		BtnSchliessen.setIcon(new ImageIcon(SparzielEingabefenster.class.getResource("/Design/Schliessen_Button.png")));
		BtnSchliessen.setBounds(265, 10, 25, 25);
		contentPane.add(BtnSchliessen);
		
		JButton BtnSparzielAnlegen = new JButton("Sparziel anlegen");
		BtnSparzielAnlegen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				anlegenSparziel();
				GlobVariablen.ziele = GlobVariablen.ziele+1;
				
			}
		});
		BtnSparzielAnlegen.setBounds(78, 243, 145, 25);
		contentPane.add(BtnSparzielAnlegen);
		
		TxtKategorie = new JTextField();
		TxtKategorie.setText("Kategorie");
		TxtKategorie.setForeground(Color.GRAY);
		TxtKategorie.setFont(new Font("Tahoma", Font.BOLD, 16));
		TxtKategorie.setBorder(null);
		TxtKategorie.setBounds(81, 164, 136, 20);
		contentPane.add(TxtKategorie);
		TxtKategorie.setColumns(1);
		
		TxtKategorie.addFocusListener(new FocusListener(){
			 public void focusGained(FocusEvent arg0) {
	         TxtKategorie.selectAll();
			 }
	         public void focusLost(FocusEvent arg0) {
	                // TODO Auto-generated method stub             
	            
			 }
		});
		
		TxtName = new JTextField();
		TxtName.setText("Bezeichnung");
		TxtName.setBorder(null);
		TxtName.setForeground(Color.GRAY);
		TxtName.setFont(new Font("Tahoma", Font.BOLD, 16));
		TxtName.setBounds(81, 124, 136, 20);
		contentPane.add(TxtName);
		TxtName.setColumns(1);
		
		TxtName.addFocusListener(new FocusListener(){
			 public void focusGained(FocusEvent arg0) {
	         TxtName.selectAll();
			 }
	         public void focusLost(FocusEvent arg0) {
	                // TODO Auto-generated method stub             
	            
			 }
		});
		
		TxtSparziel = new JTextField();
		TxtSparziel.setText("Wert");
		TxtSparziel.setForeground(Color.GRAY);
		TxtSparziel.setFont(new Font("Tahoma", Font.BOLD, 16));
		TxtSparziel.setBorder(null);
		TxtSparziel.setBounds(81, 204, 136, 20);
		contentPane.add(TxtSparziel);
		TxtSparziel.setColumns(1);
		
		TxtSparziel.addFocusListener(new FocusListener(){
			 public void focusGained(FocusEvent arg0) {
	         TxtSparziel.selectAll();
			 }
	         public void focusLost(FocusEvent arg0) {
	                // TODO Auto-generated method stub             
	            
			 }
		});
		
//Textfeld Benutzername		
		JLabel LblBezeichnung = new JLabel();
		LblBezeichnung.setIcon(new ImageIcon(SparzielEingabefenster.class.getResource("/Design/txtfeld.png")));
		LblBezeichnung.setBounds(78, 120, 145, 30);
		contentPane.add(LblBezeichnung);
		
//Textfeld Passwort		
		JLabel LblKategorie = new JLabel();
		LblKategorie.setIcon(new ImageIcon(SparzielEingabefenster.class.getResource("/Design/txtfeld.png")));
		LblKategorie.setBounds(78, 160, 145, 30);
		contentPane.add(LblKategorie);
		
//Textfeld Betrag des Sparziels	
		JLabel LblSparziel = new JLabel();
		
		LblSparziel.setIcon(new ImageIcon(SparzielEingabefenster.class.getResource("/Design/txtfeld.png")));
		LblSparziel.setBounds(78, 200, 145, 30);
		contentPane.add(LblSparziel);
		
//Hintergrund		
		JLabel Hintergrund = new JLabel("New label");
		Hintergrund.setIcon(new ImageIcon(SparzielEingabefenster.class.getResource("/Design/login.png")));
		Hintergrund.setBounds(0, 0, 300, 300);
		contentPane.add(Hintergrund);
		
//Deaktivieren des Standard-JFrame Design und lege die Lage in Mitten des Bildschirms
				setUndecorated(true);
				setLocationRelativeTo(null);
	}
	
	//Anlegen eines Sparziels in der SQL-Tabelle
	
	public void anlegenSparziel() {
		
		   float num=0;
		   boolean pruef=false;
		     

		    try {
		        num = Float.parseFloat(TxtSparziel.getText());
		     		    
		        TxtSparziel.setText(String.valueOf(num));
		        
		        pruef = true;

		    } catch (NumberFormatException e) {
		      pruef=false;
		    }
		    
		    if (pruef== true) {
		    	
			try{		
				String sqlQuery = "INSERT INTO Sparkonto (Name,Kategorie,Sparziel,Eingezahlt) VALUES(?,?,?,?)";
				PreparedStatement stm = connect.prepareStatement(sqlQuery);
				stm.setString(1, TxtName.getText());
				stm.setString(2, TxtKategorie.getText());
				stm.setString(3, TxtSparziel.getText());	
				//ResultSet result = stm.executeQuery();
				stm.execute();
				
				JOptionPane.showMessageDialog(null,"Sparziel erfolgreich hinzugefügt!"); 
								
				dispose();
				
				
	} catch(Exception exc){
		exc.printStackTrace();
	}
}
		    else{
		    	 JOptionPane.showConfirmDialog(null, "Bitte geben Sie nur Zahlen als Wert ein!", "naughty", JOptionPane.CANCEL_OPTION);
		    }
}
}