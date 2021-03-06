package Registrierung;


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

import Sparkonto.Sparziele;
import Anmelden.AnmeldeDatenbank;
import Anmelden.AnmeldenFenster;

import java.awt.Font;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class KategorienFenster extends JFrame {
	
	Connection connect = null;
	
	private JPanel contentPane;
	private JTextField TxtKategorie;
	
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
	public KategorienFenster() {
		
		connect = KategorienDB.dbCon();
		
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
			
				
		BtnSchliessen.setIcon(new ImageIcon(KategorienFenster.class.getResource("/Design/Schliessen_Button.png")));
		BtnSchliessen.setBounds(265, 10, 25, 25);
		contentPane.add(BtnSchliessen);
		
		JButton BtnKategorie = new JButton("");
		BtnKategorie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					
				try {						
							String sqlQuery = "INSERT INTO KategorienDB (Kategorie) VALUES(?)";
							PreparedStatement stm = connect.prepareStatement(sqlQuery);
							stm.setString(1, TxtKategorie.getText());
								
							
							
														
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				});
				
					
			
		BtnKategorie.setIcon(new ImageIcon(KategorienFenster.class.getResource("/Design/Speichern.png")));
		BtnKategorie.setBounds(78, 243, 145, 25);
		contentPane.add(BtnKategorie);
		
		TxtKategorie = new JTextField();
		TxtKategorie.setText("Kategorie");
		TxtKategorie.setBorder(null);
		TxtKategorie.setForeground(Color.GRAY);
		TxtKategorie.setFont(new Font("Tahoma", Font.BOLD, 16));
		TxtKategorie.setBounds(81, 159, 136, 20);
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
		
//Textfeld Benutzername		
		JLabel LblEinzahlung = new JLabel();
		LblEinzahlung.setIcon(new ImageIcon(KategorienFenster.class.getResource("/Design/txtfeld.png")));
		LblEinzahlung.setBounds(78, 155, 145, 30);
		contentPane.add(LblEinzahlung);
		
//Hintergrund		
		JLabel Hintergrund = new JLabel("New label");
		Hintergrund.setIcon(new ImageIcon(KategorienFenster.class.getResource("/Design/login.png")));
		Hintergrund.setBounds(0, 0, 300, 300);
		contentPane.add(Hintergrund);
		
//Deaktivieren des Standard-JFrame Design und lege die Lage in Mitten des Bildschirms
				setUndecorated(true);
				setLocationRelativeTo(null);
	}
	
	//Anlegen eines Sparziels in der SQL-Tabelle
	
	
}