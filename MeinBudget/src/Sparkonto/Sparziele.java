package Sparkonto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import GUI.Charts;
import GUI.Einstellungen;
import GUI.Start;
import GUI.Tools;
import GUI.Wiederholung;
import Registrierung.RegisterFenster;

import java.awt.Panel;
import java.awt.ScrollPane;

import javax.swing.SwingConstants;

public class Sparziele extends JFrame {
	
	
	Connection connect = null;

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	
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
	public Sparziele() {
		
		//this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		connect = SparkontoDB.dbCon();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 985, 704);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		
		
//Button Schliessen		
		final JLabel btnSchliessen = new JLabel();
		btnSchliessen.setBounds(930, 15, 40, 40);
		btnSchliessen.setHorizontalAlignment(SwingConstants.CENTER);
		btnSchliessen.addMouseListener(new MouseAdapter() {
			@Override
			//Schliessenbutton in grau
			public void mouseEntered(MouseEvent arg0) {
				btnSchliessen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Design/schliessengross2.png")));
			}
			
			//Schliessenbutton ist blau
			@Override
			public void mouseExited(MouseEvent arg0) {
				btnSchliessen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Design/schliessengross.png")));
			}
			
			//Schliesst das Fenster
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
		contentPane.setLayout(null);
		btnSchliessen.setIcon(new ImageIcon(Sparziele.class.getResource("/Design/schliessengross.png")));
		contentPane.add(btnSchliessen);

//Button Menue		
		JLabel btnMenue = new JLabel();
		btnMenue.setBounds(50, 30, 66, 75);
		btnMenue.setIcon(new ImageIcon(Sparziele.class.getResource("/Design/Men\u00FC.png")));
		contentPane.add(btnMenue);
		
//lblMenue		
		JLabel txtMenue = new JLabel("Men\u00FC");
		txtMenue.setBounds(130, 50, 150, 34);
		txtMenue.setFont(new Font("Tahoma", Font.BOLD, 34));
		txtMenue.setForeground(Color.WHITE);
		contentPane.add(txtMenue);
		
//Tabelle für Gesamtübersicht		
		JTable tableGesamt = new JTable();			
		tableGesamt.setBounds(50, 107, 200, 200);
		tableGesamt.setForeground(Color.WHITE);
		tableGesamt.setFont(new Font("Tahoma", Font.BOLD, 11));
		tableGesamt.setBackground(new Color (27, 109, 220));	
		contentPane.add(tableGesamt);
		
//btnStart		
		JLabel btnStart = new JLabel();
		btnStart.setBounds(50, 320, 50, 50);
		btnStart.addMouseListener(new MouseAdapter() {
			@Override
			//Start öffnet sich					
			public void mouseClicked(MouseEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Start frame = new Start();
							frame.setVisible(true);
							dispose();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
			//Start-Icon wird blau bei drübergehen der Maus
			@Override
			public void mouseEntered(MouseEvent e) {
				btnStart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Design/StartBlau.png")));
			}
			//Start-Icon ist gelb
			@Override
			public void mouseExited(MouseEvent arg0) {
				btnStart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Design/Start.png")));
			}
		});
		
		JButton btnNewZiel = new JButton("Neues Sparziel anlegen");
		btnNewZiel.setBounds(465, 131, 174, 29);
		btnNewZiel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
		 	if (GlobVariablen.ziele<3){
				
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							SparzielEingabefenster frame = new SparzielEingabefenster();
							frame.setVisible(true);
							
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
			}
				else{
					JOptionPane.showMessageDialog(null,"Sie haben die maximale Anzahl an Sparzielen erreicht!\nNehmen Sie sich nicht zu viel auf einmal vor!" ); 
				}
			}
		});
		
		JButton btnAktualisieren = new JButton("Aktualisieren");
		btnAktualisieren.setBounds(465, 173, 118, 25);
		btnAktualisieren.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Auslesen();
				repaint();
				
			}
		});
		contentPane.add(btnAktualisieren);
		contentPane.add(btnNewZiel);
		btnStart.setIcon(new ImageIcon(Sparziele.class.getResource("/Design/Start.png")));
		contentPane.add(btnStart);
		
//lblStart
		JLabel lblStart = new JLabel("Start");
		lblStart.setBounds(110, 332, 80, 25);
		lblStart.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblStart.setForeground(Color.WHITE);
		contentPane.add(lblStart);
		
//Button Wiederholung		
		JLabel btnWiederholung = new JLabel();
		btnWiederholung.setBounds(50, 380, 50, 50);
		btnWiederholung.addMouseListener(new MouseAdapter() {
			@Override
			//Wiederholung öffnet sich	Bild			
			public void mouseClicked(MouseEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Wiederholung frame = new Wiederholung();
							frame.setVisible(true);
							dispose();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
			//Wiederholung-Icon ist blau bei drübergehen der Maus
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnWiederholung.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Design/WiederholungenBlau.png")));
			}	
			//Wiederholung-Icon ist gelb
			@Override
			public void mouseExited(MouseEvent arg0) {
				btnWiederholung.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Design/wiederholung.png")));
			}
		});
		btnWiederholung.setIcon(new ImageIcon(Sparziele.class.getResource("/Design/wiederholung.png")));
		contentPane.add(btnWiederholung);
		
//lblWiederholung für fixe Beträge		
		JLabel lblWiederholung = new JLabel("Wiederholung");
		lblWiederholung.setBounds(110, 392, 174, 25);
		lblWiederholung.setForeground(Color.WHITE);
		lblWiederholung.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblWiederholung);
		
//Button Charts		
		JLabel btnCharts = new JLabel();
		btnCharts.setBounds(50, 440, 50, 50);
		btnCharts.addMouseListener(new MouseAdapter() {
			@Override
			//Charts öffnet sich
			public void mouseClicked(MouseEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Charts frame = new Charts();
							frame.setVisible(true);
							dispose();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}					
			//Charts-Icon wird blau bei drübergehen der Maus
			@Override
			public void mouseEntered(MouseEvent e) {
				btnCharts.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Design/ChartsBlau.png")));
			}
			//Charts-Icon ist gelb
			@Override
			public void mouseExited(MouseEvent arg0) {
				btnCharts.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Design/Charts.png")));
			}
		});
		btnCharts.setIcon(new ImageIcon(Sparziele.class.getResource("/Design/Charts.png")));
		contentPane.add(btnCharts);
		
//lblCharts		
		JLabel lblCharts = new JLabel("Charts");
		lblCharts.setBounds(110, 452, 174, 25);
		lblCharts.setForeground(Color.WHITE);
		lblCharts.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblCharts);
		
//Button Sparziele		
		JLabel btnSparziele = new JLabel();
		btnSparziele.setBounds(50, 500, 50, 50);
		btnSparziele.setIcon(new ImageIcon(Sparziele.class.getResource("/Design/SparzieleBlau.png")));
		contentPane.add(btnSparziele);
		
//lblSparziele		
		JLabel lblSparziele = new JLabel("Sparziele");
		lblSparziele.setBounds(110, 512, 174, 25);
		lblSparziele.setForeground(Color.BLUE);
		lblSparziele.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblSparziele);
		
//Button Tools		
		JLabel btnTools = new JLabel();
		btnTools.setBounds(50, 560, 50, 50);
		btnTools.addMouseListener(new MouseAdapter() {
		@Override
			//Tools öffnet sich	Bild			
			public void mouseClicked(MouseEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Tools frame = new Tools();
							frame.setVisible(true);
							dispose();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
			//Tools-Icon wird blau bei drübergehen der Maus
			@Override
			public void mouseEntered(MouseEvent e) {
				btnTools.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Design/ToolsBlau.png")));
			}
			//Tools-Icon ist gelb
			@Override
			public void mouseExited(MouseEvent arg0) {
				btnTools.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Design/Tools.png")));
			}
		});
		btnTools.setIcon(new ImageIcon(Sparziele.class.getResource("/Design/Tools.png")));
		contentPane.add(btnTools);
		
//lblTools		
		JLabel lblTools = new JLabel("Tools");
		lblTools.setBounds(110, 572, 174, 25);
		lblTools.setForeground(Color.WHITE);
		lblTools.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblTools);
		
//Button Einstellungen		
		JLabel btnEinstellungen = new JLabel();
		btnEinstellungen.setBounds(50, 620, 50, 50);
		btnEinstellungen.addMouseListener(new MouseAdapter() {
			@Override
			//Einstellungen öffnet sich	Bild			
			public void mouseClicked(MouseEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Einstellungen frame = new Einstellungen();
							frame.setVisible(true);
							dispose();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
			//Einstellungen-Icon wird blau bei drübergehen der Maus			
			@Override
			public void mouseEntered(MouseEvent e) {
				btnEinstellungen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Design/EinstellungenBlau.png")));
			}
			//Einstellungen-Icon ist gelb
			@Override
			public void mouseExited(MouseEvent arg0) {
				btnEinstellungen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Design/Einstellungen.png")));
			}
		});
		btnEinstellungen.setIcon(new ImageIcon(Sparziele.class.getResource("/Design/Einstellungen.png")));
		contentPane.add(btnEinstellungen);
		
//lblEinstellungen		
		JLabel lblEinstellungen = new JLabel("Einstellungen");
		lblEinstellungen.setBounds(110, 632, 174, 25);
		lblEinstellungen.setIcon(null);
		lblEinstellungen.setForeground(Color.WHITE);
		lblEinstellungen.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblEinstellungen);
		
//Haupt"klasse"- Sparziele		
		JLabel lblHauptSparziele = new JLabel("<html><u>SPARZIELE</u><HTML>");
		lblHauptSparziele.setBounds(510, 50, 249, 34);
		lblHauptSparziele.setForeground(Color.WHITE);
		lblHauptSparziele.setFont(new Font("Tahoma", Font.BOLD, 34));
		contentPane.add(lblHauptSparziele);
		
//Haupt"klasse" Button Sparziele		
		JLabel btnHauptSparziele = new JLabel();
		btnHauptSparziele.setBounds(430, 30, 66, 75);
		btnHauptSparziele.setIcon(new ImageIcon(Sparziele.class.getResource("/Design/SparzieleGross.png")));
		contentPane.add(btnHauptSparziele);
		
		//Hintergrund		
				JLabel Hintergrund = new JLabel();
				Hintergrund.setBounds(0, -52, 985, 807);
				//Hintergrund.setIcon(new ImageIcon(Start.class.getResource("/Design/GUI4.png")));
				contentPane.add(Hintergrund);
		
		


//Deaktivieren des Standard-JFrame Design und lege die Lage in Mitten des Bildschirms
		setUndecorated(true);
		setLocationRelativeTo(null);

	}
	
		
		//Dies ist die Methode zum Auslesen der Sparkonto-Datenbank und der Erstellung der grafischen Darstellung in FOrm von JPanels

		public void Auslesen() {
			
			try {
				
				
				String sqlQuery = "select * from Sparkonto";
				
				Statement stm = connect.createStatement();

				ResultSet rs = stm.executeQuery(sqlQuery);
				//deletion
				Component[] components = contentPane.getComponents();
				for (Component component : components) {
				    if (component instanceof SparzielPanel) {
				        contentPane.remove(component);
				    	
				    }
				}
				int top = 200, left = 400;
				try {
					while (rs.next()) {
						top = top + 100;
						SparzielPanel newgui = new SparzielPanel(rs.getInt("ID"),
								rs.getString("Name"),
								rs.getString("Kategorie"),
								rs.getInt("Sparziel"),
								rs.getInt("Eingezahlt"),
								top, left);
						
						contentPane.add(newgui);
						
					}
				} finally {
					try {
						rs.close();
					} catch (Throwable ignore) { 
					}

					stm.close();

				}
			} catch (Exception exc) {
				exc.printStackTrace();
			}

			// setUndecorated(true);
		
		
	}
}

