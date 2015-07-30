package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Sparkonto.Sparziele;

public class Wiederholung extends JFrame {

	private JPanel contentPane;
	private JTable tableFix;

	static int id;
	private JTextField txtMonat;
	private JTextField txtVormonat;
	private JTextField txtJahr;
	private JTextField txtGesamt;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Wiederholung frame = new Wiederholung(id);
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
	public Wiederholung(int id) {
		
		this.id = id;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 985, 704);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

//Button Schliessen		
		final JLabel btnSchliessen = new JLabel();
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
		btnSchliessen.setIcon(new ImageIcon(Wiederholung.class.getResource("/Design/schliessengross.png")));
		btnSchliessen.setBounds(930, 15, 40, 40);
		contentPane.add(btnSchliessen);

//Button Menue		
		JLabel btnMenue = new JLabel();
		btnMenue.setIcon(new ImageIcon(Wiederholung.class.getResource("/Design/Men\u00FC.png")));
		btnMenue.setBounds(50, 30, 66, 75);
		contentPane.add(btnMenue);
		
//lblMenue		
		JLabel txtMenue = new JLabel("Men\u00FC");
		txtMenue.setFont(new Font("Tahoma", Font.BOLD, 34));
		txtMenue.setForeground(Color.WHITE);
		txtMenue.setBounds(130, 50, 150, 34);
		contentPane.add(txtMenue);
		
		//Gesamtübersicht
				JLabel lblUebersicht = new JLabel("<html><u>Gesamtbilanz:</u><html>");
				lblUebersicht.setForeground(Color.WHITE);
				lblUebersicht.setFont(new Font("Tahoma", Font.BOLD, 14));
				lblUebersicht.setBounds(50, 120, 108, 25);
				contentPane.add(lblUebersicht);
			
		//Aktueller Monat		
				JLabel lblMonat = new JLabel("Monat:");
				lblMonat.setForeground(Color.WHITE);
				lblMonat.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblMonat.setBounds(50, 160, 108, 25);
				contentPane.add(lblMonat);	
				
		//txtMonat		
				txtMonat = new JTextField();
				txtMonat.setHorizontalAlignment(SwingConstants.CENTER);
				txtMonat.setForeground(Color.WHITE);
				txtMonat.setFont(new Font("Tahoma", Font.BOLD, 12));
				txtMonat.setColumns(10);
				txtMonat.setBorder(null);
				txtMonat.setBackground(new Color (27, 109, 220));
				txtMonat.setBounds(157, 163, 103, 20);
				contentPane.add(txtMonat);		

		//Vormonat
				JLabel lblVormonat = new JLabel("Vormonat:");
				lblVormonat.setForeground(Color.WHITE);
				lblVormonat.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblVormonat.setBounds(50, 200, 108, 25);
				contentPane.add(lblVormonat);	

		//txtVormonat		
				txtVormonat = new JTextField();
				txtVormonat.setHorizontalAlignment(SwingConstants.CENTER);
				txtVormonat.setForeground(Color.WHITE);
				txtVormonat.setFont(new Font("Tahoma", Font.BOLD, 12));
				txtVormonat.setColumns(10);
				txtVormonat.setBorder(null);
				txtVormonat.setBackground(new Color (27, 109, 220));
				txtVormonat.setBounds(157, 203, 103, 20);
				contentPane.add(txtVormonat);	
				
		//Aktuelles Jahr
				JLabel lblJahr = new JLabel("Aktuelles Jahr:");
				lblJahr.setForeground(Color.WHITE);
				lblJahr.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblJahr.setBounds(50, 240, 108, 25);
				contentPane.add(lblJahr);			
				
		//txtJahr		
				txtJahr = new JTextField();
				txtJahr.setHorizontalAlignment(SwingConstants.CENTER);
				txtJahr.setBackground(new Color (27, 109, 220));
				txtJahr.setForeground(Color.WHITE);
				txtJahr.setFont(new Font("Tahoma", Font.BOLD, 12));
				txtJahr.setBorder(null);
				txtJahr.setBounds(157, 243, 103, 20);
				contentPane.add(txtJahr);
				txtJahr.setColumns(10);

		//Gesamt seit Benutzung		
				JLabel lblBenutzung = new JLabel("Gesamt:");
				lblBenutzung.setForeground(Color.WHITE);
				lblBenutzung.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblBenutzung.setBounds(50, 280, 108, 25);
				contentPane.add(lblBenutzung);			
				
		//TxtGesamt		
				txtGesamt = new JTextField();
				txtGesamt.setHorizontalAlignment(SwingConstants.CENTER);
				txtGesamt.setForeground(Color.WHITE);
				txtGesamt.setFont(new Font("Tahoma", Font.BOLD, 12));
				txtGesamt.setColumns(10);
				txtGesamt.setBorder(null);
				txtGesamt.setBackground(new Color (27, 109, 220));
				txtGesamt.setBounds(157, 283, 103, 20);
				contentPane.add(txtGesamt);
		
//btnStart		
		JLabel btnStart = new JLabel();
		btnStart.addMouseListener(new MouseAdapter() {
			@Override
			//Start öffnet sich					
			public void mouseClicked(MouseEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Start frame = new Start(Start.id); 
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
		btnStart.setIcon(new ImageIcon(Wiederholung.class.getResource("/Design/Start.png")));
		btnStart.setBounds(50, 320, 50, 50);
		contentPane.add(btnStart);
		
//lblStart
		JLabel lblStart = new JLabel("Start");
		lblStart.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblStart.setForeground(Color.WHITE);
		lblStart.setBounds(110, 332, 80, 25);
		contentPane.add(lblStart);
		
//Button Wiederholung		
		JLabel btnWiederholung = new JLabel();
		btnWiederholung.setIcon(new ImageIcon(Wiederholung.class.getResource("/Design/WiederholungenBlau.png")));
		btnWiederholung.setBounds(50, 380, 50, 50);
		contentPane.add(btnWiederholung);
		
//lblWiederholung für fixe Beträge		
		JLabel lblWiederholung = new JLabel("Wiederholung");
		lblWiederholung.setForeground(Color.BLUE);
		lblWiederholung.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblWiederholung.setBounds(110, 392, 174, 25);
		contentPane.add(lblWiederholung);
		
//Button Charts		
		JLabel btnCharts = new JLabel();
		btnCharts.addMouseListener(new MouseAdapter() {
			@Override
			//Charts öffnet sich
			public void mouseClicked(MouseEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Charts frame = new Charts(id);
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
		btnCharts.setIcon(new ImageIcon(Wiederholung.class.getResource("/Design/Charts.png")));
		btnCharts.setBounds(50, 440, 50, 50);
		contentPane.add(btnCharts);
		
//lblCharts		
		JLabel lblCharts = new JLabel("Charts");
		lblCharts.setForeground(Color.WHITE);
		lblCharts.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCharts.setBounds(110, 452, 174, 25);
		contentPane.add(lblCharts);
		
//Button Sparziele		
		JLabel btnSparziele = new JLabel();
		btnSparziele.addMouseListener(new MouseAdapter() {
			//Sparziele öffnet sich
			@Override
			public void mouseClicked(MouseEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Sparziele frame = new Sparziele();
							frame.setVisible(true);
							dispose();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
			//Sparziele-Icon wird blau bei drübergehen der Maus
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSparziele.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Design/SparzieleBlau.png")));
			}
			//Sparziele-Icon ist gelb
			@Override
			public void mouseExited(MouseEvent arg0) {
				btnSparziele.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Design/Sparziele.png")));
			}
		});
		btnSparziele.setIcon(new ImageIcon(Wiederholung.class.getResource("/Design/Sparziele.png")));
		btnSparziele.setBounds(50, 500, 50, 50);
		contentPane.add(btnSparziele);
		
//lblSparziele		
		JLabel lblSparziele = new JLabel("Sparziele");
		lblSparziele.setForeground(Color.WHITE);
		lblSparziele.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSparziele.setBounds(110, 512, 174, 25);
		contentPane.add(lblSparziele);
		
//Button Tools		
		JLabel btnTools = new JLabel("New label");
		btnTools.addMouseListener(new MouseAdapter() {
			@Override
			//Tools öffnet sich	Bild			
			public void mouseClicked(MouseEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Tools frame = new Tools(Tools.id);
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
		btnTools.setIcon(new ImageIcon(Wiederholung.class.getResource("/Design/Tools.png")));
		btnTools.setBounds(50, 560, 50, 50);
		contentPane.add(btnTools);
		
//lblTools		
		JLabel lblTools = new JLabel("Tools");
		lblTools.setForeground(Color.WHITE);
		lblTools.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTools.setBounds(110, 572, 174, 25);
		contentPane.add(lblTools);
		
//Button Einstellungen		
		JLabel btnEinstellungen = new JLabel();
		btnEinstellungen.addMouseListener(new MouseAdapter() {
			@Override
			//Einstellungen öffnet sich	Bild			
			public void mouseClicked(MouseEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Einstellungen frame = new Einstellungen(Einstellungen.id);
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
		btnEinstellungen.setIcon(new ImageIcon(Wiederholung.class.getResource("/Design/Einstellungen.png")));
		btnEinstellungen.setBounds(50, 620, 50, 50);
		contentPane.add(btnEinstellungen);
		
//lblEinstellungen		
		JLabel lblEinstellungen = new JLabel("Einstellungen");
		lblEinstellungen.setIcon(null);
		lblEinstellungen.setForeground(Color.WHITE);
		lblEinstellungen.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblEinstellungen.setBounds(110, 632, 174, 25);
		contentPane.add(lblEinstellungen);
		
		
//Haupt"klasse"- Wiederholung	
		JLabel lblHauptWiederholung = new JLabel("<html><u>WIEDERHOLUNG</u><HTML>");
		lblHauptWiederholung.setForeground(Color.WHITE);
		lblHauptWiederholung.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblHauptWiederholung.setBounds(510, 50, 322, 34);
		contentPane.add(lblHauptWiederholung);
		
//Haupt"klasse" Button Wiederholung		
		JLabel btnHauptWiederholung = new JLabel();
		btnHauptWiederholung.setIcon(new ImageIcon(Wiederholung.class.getResource("/Design/WiederholungGross.png")));
		btnHauptWiederholung.setBounds(430, 30, 66, 75);
		contentPane.add(btnHauptWiederholung);
		
//lblFixeTransaktion		
		JLabel lblFixeTransaktion = new JLabel("neue wiederholende Transaktion");
		lblFixeTransaktion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFixeTransaktion.setBounds(580, 149, 199, 35);
		contentPane.add(lblFixeTransaktion);
		
//Button FixeTransaktion		
		JLabel btnFixeTransaktion = new JLabel();
		btnFixeTransaktion.addMouseListener(new MouseAdapter() {
			//Plusbutton wird grau bei drübergehen der Maus
			@Override
			public void mouseEntered(MouseEvent e) {
					btnFixeTransaktion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Design/Plus2.png")));
				}
			//Plusbutton ist blau
			@Override
			public void mouseExited(MouseEvent arg0) {
					btnFixeTransaktion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Design/Plus.png")));
				}
			@Override
			public void mouseClicked(MouseEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							TransaktionAnlegen frame = new TransaktionAnlegen();
							frame.setVisible(true);
							dispose();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnFixeTransaktion.setIcon(new ImageIcon(Wiederholung.class.getResource("/Design/Plus.png")));
		btnFixeTransaktion.setBounds(538, 156, 25, 25);
		contentPane.add(btnFixeTransaktion);
		
//FixeTransaktion		
		JLabel FixeTransaktion = new JLabel();
		FixeTransaktion.setIcon(new ImageIcon(Wiederholung.class.getResource("/Design/Textfeldgross3.png")));
		FixeTransaktion.setBounds(510, 150, 291, 35);
		contentPane.add(FixeTransaktion);
		
//Übersicht einer Tabelle der fixen Beiträge		
		tableFix = new JTable();
		tableFix.setBounds(510, 220, 291, 366);
		contentPane.add(tableFix);
		
//Hintergrund		
		JLabel Hintergrund = new JLabel();
		Hintergrund.setIcon(new ImageIcon(Wiederholung.class.getResource("/Design/GUI4.png")));
		Hintergrund.setBounds(0, -52, 985, 807);
		contentPane.add(Hintergrund);


//Deaktivieren des Standard-JFrame Design und lege die Lage in Mitten des Bildschirms
		setUndecorated(true);
		setLocationRelativeTo(null);
	}

}
