package GUI;

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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.jdbc.JDBCCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.data.xml.DatasetReader;

import Sparkonto.Sparziele;
import BudgetPlan.Posten;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Charts extends JFrame {
	Connection connection = null;
	Connection connec = null;
	Connection conn	= null;
	static int id;
	
	private JPanel contentPane;
	private JLabel btnZahlungsmittel;
	private JPanel DiagrammPanel;
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
					Charts frame = new Charts(id);
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
	public Charts(int id) {
		
		this.id = id;
		connection = BPDatenbank.dbCon();
		connec = BPDatenbank.dbCon();
		conn = BPDatenbank.dbCon();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 985, 707);
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
		btnSchliessen.setIcon(new ImageIcon(Charts.class.getResource("/Design/schliessengross.png")));
		btnSchliessen.setBounds(930, 15, 40, 40);
		contentPane.add(btnSchliessen);

//Button Menue		
		JLabel btnMenue = new JLabel();
		btnMenue.setIcon(new ImageIcon(Charts.class.getResource("/Design/Men\u00FC.png")));
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
		btnStart.setIcon(new ImageIcon(Charts.class.getResource("/Design/Start.png")));
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
		btnWiederholung.addMouseListener(new MouseAdapter() {
			@Override
			//Wiederholung öffnet sich	Bild			
			public void mouseClicked(MouseEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Wiederholung frame = new Wiederholung(Wiederholung.id);
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
		btnWiederholung.setIcon(new ImageIcon(Charts.class.getResource("/Design/wiederholung.png")));
		btnWiederholung.setBounds(50, 380, 50, 50);
		contentPane.add(btnWiederholung);
		
//lblWiederholung für fixe Beträge		
		JLabel lblWiederholung = new JLabel("Wiederholung");
		lblWiederholung.setForeground(Color.WHITE);
		lblWiederholung.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblWiederholung.setBounds(110, 392, 174, 25);
		contentPane.add(lblWiederholung);
		
//Button Charts		
		JLabel btnCharts = new JLabel();
		btnCharts.setIcon(new ImageIcon(Charts.class.getResource("/Design/ChartsBlau.png")));
		btnCharts.setBounds(50, 440, 50, 50);
		contentPane.add(btnCharts);
		
//lblCharts		
		JLabel lblCharts = new JLabel("Charts");
		lblCharts.setForeground(Color.BLUE);
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
		btnSparziele.setIcon(new ImageIcon(Charts.class.getResource("/Design/Sparziele.png")));
		btnSparziele.setBounds(50, 500, 50, 50);
		contentPane.add(btnSparziele);
		
//lblSparziele		
		JLabel lblSparziele = new JLabel("Sparziele");
		lblSparziele.setForeground(Color.WHITE);
		lblSparziele.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSparziele.setBounds(110, 512, 174, 25);
		contentPane.add(lblSparziele);
		
//Button Tools		
		JLabel btnTools = new JLabel();
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
		btnTools.setIcon(new ImageIcon(Charts.class.getResource("/Design/Tools.png")));
		btnTools.setBounds(50, 560, 50, 50);
		contentPane.add(btnTools);
		
//lblTools		
		JLabel lblTools = new JLabel("Tools");
		lblTools.setForeground(Color.WHITE);
		lblTools.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTools.setBounds(110, 572, 174, 25);
		contentPane.add(lblTools);
		
//Button Einstellungen		
		JLabel btnEinstellungen = new JLabel("New label");
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
		btnEinstellungen.setIcon(new ImageIcon(Charts.class.getResource("/Design/Einstellungen.png")));
		btnEinstellungen.setBounds(50, 620, 50, 50);
		contentPane.add(btnEinstellungen);
		
//lblEinstellungen		
		JLabel lblEinstellungen = new JLabel("Einstellungen");
		lblEinstellungen.setIcon(null);
		lblEinstellungen.setForeground(Color.WHITE);
		lblEinstellungen.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblEinstellungen.setBounds(110, 632, 174, 25);
		contentPane.add(lblEinstellungen);
		
//Haupt"klasse"- Charts
		JLabel lblHauptCharts = new JLabel("<html><u>CHARTS</u><HTML>");
		lblHauptCharts.setForeground(Color.WHITE);
		lblHauptCharts.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblHauptCharts.setBounds(510, 50, 150, 34);
		contentPane.add(lblHauptCharts);
		
//Haupt"klasse" Button Charts		
		JLabel btnHauptCharts = new JLabel();
		btnHauptCharts.setIcon(new ImageIcon(Charts.class.getResource("/Design/ChartsGross.png")));
		btnHauptCharts.setBounds(430, 30, 66, 75);
		contentPane.add(btnHauptCharts);
		
		//Button Kategorie im Detail		
		JLabel btnKategorieImDetail = new JLabel();
		btnKategorieImDetail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Kategorie();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnKategorieImDetail.setIcon(new ImageIcon(Charts.class.getResource("/Design/KategorienImDetail.png")));
		btnKategorieImDetail.setBounds(550, 140, 282, 38);
		contentPane.add(btnKategorieImDetail);
				
		//Button Monatsauswertung		
				JLabel btnMonatsauswertung = new JLabel();
				btnMonatsauswertung.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						Monat();
					}
				});
				btnMonatsauswertung.setIcon(new ImageIcon(Charts.class.getResource("/Design/Monatsauswertung.png")));
				btnMonatsauswertung.setBounds(550, 200, 282, 38);
				contentPane.add(btnMonatsauswertung);
				
		//Button Jahresauswertung		
				JLabel btnJahresauswertung = new JLabel();
				btnJahresauswertung.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						Jahr();
					}
				});
				btnJahresauswertung.setIcon(new ImageIcon(Charts.class.getResource("/Design/Jahresauswertung.png")));
				btnJahresauswertung.setBounds(550, 260, 282, 38);
				contentPane.add(btnJahresauswertung);
				
		//Button Ausgabeentwicklung		
				JLabel btnAusgabenentwicklung = new JLabel();
				btnAusgabenentwicklung.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						Ausgaben();
					}
				});
				btnAusgabenentwicklung.setIcon(new ImageIcon(Charts.class.getResource("/Design/Ausgabenentwicklung.png")));
				btnAusgabenentwicklung.setBounds(550, 320, 282, 38);
				contentPane.add(btnAusgabenentwicklung);
				
		//Button Einnahmenentwicklung		
				JLabel btnEinnahmenentwicklung = new JLabel();
				btnEinnahmenentwicklung.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						Einnahmen();
					}
				});
				btnEinnahmenentwicklung.setIcon(new ImageIcon(Charts.class.getResource("/Design/Einnahmenentwicklung.png")));
				btnEinnahmenentwicklung.setBounds(550, 380, 282, 38);
				contentPane.add(btnEinnahmenentwicklung);
				
		//Button Liquiditätsentwicklung		
				JLabel btnLiquidität = new JLabel();
				btnLiquidität.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						Liquiditaet();
					}
				});
				btnLiquidität.setIcon(new ImageIcon(Charts.class.getResource("/Design/Liquidit\u00E4tsentwicklung.png")));
				btnLiquidität.setBounds(550, 440, 282, 38);
				contentPane.add(btnLiquidität);
				
		//Button Ausgabeverteilung		
				JLabel btnAusgabeverteilung = new JLabel();
				btnAusgabeverteilung.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						AusV();
					}
				});
				btnAusgabeverteilung.setIcon(new ImageIcon(Charts.class.getResource("/Design/Ausgabenverteilung.png")));
				btnAusgabeverteilung.setBounds(550, 500, 282, 38);
				contentPane.add(btnAusgabeverteilung);
				
		//Button Einnahmenverteilung		
				JLabel btnEinnahmenverteilung = new JLabel();
				btnEinnahmenverteilung.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						EinV();
					}
				});
				btnEinnahmenverteilung.setIcon(new ImageIcon(Charts.class.getResource("/Design/Einnahmenverteilung.png")));
				btnEinnahmenverteilung.setBounds(550, 560, 282, 38);
				contentPane.add(btnEinnahmenverteilung);
				
		
//Hintergrund		
		JLabel Hintergrund = new JLabel();
		Hintergrund.setIcon(new ImageIcon(Charts.class.getResource("/Design/GUI4.png")));
		Hintergrund.setBounds(0, -31, 1381, 767);
		contentPane.add(Hintergrund);

//Deaktivieren des Standard-JFrame Design und lege die Lage in Mitten des Bildschirms
		setUndecorated(true);
		setLocationRelativeTo(null);		
	}
//Kategorie
	private void Kategorie() throws Exception{
			DefaultPieDataset PieDataset = new DefaultPieDataset();
			Statement stmt;
			//fülle Daten aus der Tabelle zu JFreeChart
			try {
				stmt = connec.createStatement();
				ResultSet queryKat = stmt.executeQuery("SELECT Kategorie,Betrag FROM BenutzerAufwendungen WHERE (BenutzerID='"+this.id+"')");
				
				//Prüfung
				ResultSetMetaData rsmd = queryKat.getMetaData();
			    System.out.println("querying SELECT * FROM XXX");
			    int columnsNumber = rsmd.getColumnCount();
			    while (queryKat.next()) {
			        for (int i = 1; i <= columnsNumber; i++) {
			            if (i > 1) System.out.print(",  ");
			            String columnValue = queryKat.getString(i);
			            System.out.print(columnValue + " " + rsmd.getColumnName(i));
			        }
			        System.out.println("");
			    }
				
				
				while (queryKat.next()) {
					String Kategorie = queryKat.getString("Kategorie");
					int Betrag = queryKat.getInt("Betrag");
					PieDataset.setValue(Kategorie, Betrag); // Konvertiere Datenquelle von Tabelle zu PieChart Datasource
				}
				JFreeChart PieChart = ChartFactory.createPieChart("Kategorie im Detail", PieDataset);
				PiePlot p = (PiePlot) PieChart.getPlot();
				p.setForegroundAlpha(TOP_ALIGNMENT);
				ChartFrame frame = new ChartFrame("Kategorie im Detail", PieChart);
				frame.setVisible(true);
				frame.setSize(450,600);
				queryKat.close();
				stmt.close();
				connec.close();

				//Abmessungen und Qualitätsfaktor für PieChart
				int width = 400;
				int height = 600;
				float quality=1;
				
				File BarChart=new File("SQL2PieChart.png");              
                ChartUtilities.saveChartAsJPEG(BarChart, quality, PieChart, width, height); 
				}
				catch (Exception i)
                 {
                         System.out.println(i);
                 
                }
			}
        
				
	
			/*
			JFreeChart PieChart = ChartFactory.createPieChart("Kategorie im Detail",pieDatasetKat, true, true, true);
			PiePlot p = (PiePlot) PieChart.getPlot();
			p.setForegroundAlpha(TOP_ALIGNMENT);
			ChartFrame frame = new ChartFrame("Kategorie im Detail", PieChart);
			frame.setVisible(true);
			frame.setSize(450,600);
			
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
	}*/

//Monat
	private void Monat(){
		try{		
			String queryEin = "SELECT Datum,Betrag FROM BenutzerAufwendungen WHERE (BenutzerID='"+this.id+"')";
			PreparedStatement pst = conn.prepareStatement(queryEin);
			ResultSet res = pst.executeQuery();
			
			//Prüfung
			ResultSetMetaData rsmd = res.getMetaData();
		    System.out.println("querying SELECT * FROM XXX");
		    int columnsNumber = rsmd.getColumnCount();
		    while (res.next()) {
		        for (int i = 1; i <= columnsNumber; i++) {
		            if (i > 1) System.out.print(",  ");
		            String columnValue = res.getString(i);
		            System.out.print(columnValue + " " + rsmd.getColumnName(i));
		        }
		        System.out.println("");
		    }
			
			JDBCCategoryDataset dataEin = new JDBCCategoryDataset (connection);
			dataEin.executeQuery(queryEin);
			CategoryDataset datasetEin = dataEin;
			
			 JFreeChart BarChart = ChartFactory.createBarChart("Zahlungsmittelauswertung", "Datum", "Betrag", datasetEin, PlotOrientation.VERTICAL, false, true, true);
				CategoryPlot plot = BarChart.getCategoryPlot();
				BarRenderer renderer = null;
				renderer = new BarRenderer();
				ChartFrame  frame = new ChartFrame("", BarChart);
				frame.setVisible(true);
				frame.setSize(400,650);
			
			
			
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		
		}
	}
	
//Jahr	
	private void Jahr(){
		try{		
			String queryEin = "SELECT Datum,Betrag FROM BenutzerAufwendungen WHERE (BenutzerID='"+this.id+"')";
			PreparedStatement pst = conn.prepareStatement(queryEin);
			ResultSet res = pst.executeQuery();
			
			//Prüfung
			ResultSetMetaData rsmd = res.getMetaData();
		    System.out.println("querying SELECT * FROM XXX");
		    int columnsNumber = rsmd.getColumnCount();
		    while (res.next()) {
		        for (int i = 1; i <= columnsNumber; i++) {
		            if (i > 1) System.out.print(",  ");
		            String columnValue = res.getString(i);
		            System.out.print(columnValue + " " + rsmd.getColumnName(i));
		        }
		        System.out.println("");
		    }
			
			JDBCCategoryDataset dataEin = new JDBCCategoryDataset (connection);
			dataEin.executeQuery(queryEin);
			CategoryDataset datasetEin = dataEin;
			
			 JFreeChart BarChart = ChartFactory.createBarChart("Zahlungsmittelauswertung", "Datum", "Betrag", datasetEin, PlotOrientation.VERTICAL, false, true, true);
				CategoryPlot plot = BarChart.getCategoryPlot();
				BarRenderer renderer = null;
				renderer = new BarRenderer();
				ChartFrame  frame = new ChartFrame("", BarChart);
				frame.setVisible(true);
				frame.setSize(400,650);
			
			
			
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		
		}
	}	
	
//Einnahmenentwicklung
	private void Einnahmen(){
		try{		
			String queryEin = "SELECT Datum,Betrag FROM BenutzerAufwendungen WHERE (BenutzerID='"+this.id+"')";
			PreparedStatement pst = conn.prepareStatement(queryEin);
			ResultSet res = pst.executeQuery();
			
			//Prüfung
			ResultSetMetaData rsmd = res.getMetaData();
		    System.out.println("querying SELECT * FROM XXX");
		    int columnsNumber = rsmd.getColumnCount();
		    while (res.next()) {
		        for (int i = 1; i <= columnsNumber; i++) {
		            if (i > 1) System.out.print(",  ");
		            String columnValue = res.getString(i);
		            System.out.print(columnValue + " " + rsmd.getColumnName(i));
		        }
		        System.out.println("");
		    }
			
			JDBCCategoryDataset dataEin = new JDBCCategoryDataset (connection);
			dataEin.executeQuery(queryEin);
			CategoryDataset datasetEin = dataEin;
			
			 JFreeChart BarChart = ChartFactory.createBarChart("Zahlungsmittelauswertung", "Datum", "Betrag", datasetEin, PlotOrientation.VERTICAL, false, true, true);
				CategoryPlot plot = BarChart.getCategoryPlot();
				BarRenderer renderer = null;
				renderer = new BarRenderer();
				ChartFrame  frame = new ChartFrame("", BarChart);
				frame.setVisible(true);
				frame.setSize(400,650);
			
			
			
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		
		}
	}
	
//Ausgabenentwicklung
	private void Ausgaben(){
		try{		
			String queryAus = "SELECT Datum,Betrag FROM BenutzerErträge WHERE (BenutzerID='"+this.id+"')";
			PreparedStatement pst = conn.prepareStatement(queryAus);
			ResultSet res = pst.executeQuery();
			
			//Prüfung
			ResultSetMetaData rsmd = res.getMetaData();
		    System.out.println("querying SELECT * FROM XXX");
		    int columnsNumber = rsmd.getColumnCount();
		    while (res.next()) {
		        for (int i = 1; i <= columnsNumber; i++) {
		            if (i > 1) System.out.print(",  ");
		            String columnValue = res.getString(i);
		            System.out.print(columnValue + " " + rsmd.getColumnName(i));
		        }
		        System.out.println("");
		    }
			
			JDBCCategoryDataset dataAus = new JDBCCategoryDataset (connection);
			dataAus.executeQuery(queryAus);
			CategoryDataset datasetAus = dataAus;
			
			 JFreeChart BarChart = ChartFactory.createBarChart("Zahlungsmittelauswertung", "Datum", "Betrag", datasetAus, PlotOrientation.VERTICAL, false, true, true);
				CategoryPlot plot = BarChart.getCategoryPlot();
				BarRenderer renderer = null;
				renderer = new BarRenderer();
				ChartFrame  frame = new ChartFrame("", BarChart);
				frame.setVisible(true);
				frame.setSize(400,650);
			
			
			
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		
		}
	}	
	
	
//Liquiditätsentwicklung	
		private void Liquiditaet(){
			try {
			String queryLid = 	"SELECT Datum,Betrag FROM BenutzerAufwendungen WHERE (BenutzerID='"+this.id+"') UNION SELECT Datum,Betrag FROM BenutzerErträge WHERE (BenutzerID='"+this.id+"')";
			PreparedStatement stm = connection.prepareStatement(queryLid);
			ResultSet result = stm.executeQuery();
			
			/*String queryAus	=	"SELECT Datum,Betrag "
								+ "FROM BenutzerErträge WHERE (BenutzerID='"+this.id+"')"; 
			PreparedStatement pstmt = connec.prepareStatement(queryAus);
			ResultSet rs = pstmt.executeQuery();
			*/
			
		    //Prüfung
			ResultSetMetaData rsmd = result.getMetaData();
		    System.out.println("querying SELECT * FROM XXX");
		    int columnsNumber = rsmd.getColumnCount();
		    while (result.next()) {
		        for (int i = 1; i <= columnsNumber; i++) {
		            if (i > 1) System.out.print(",  ");
		            String columnValue = result.getString(i);
		            System.out.print(columnValue + " " + rsmd.getColumnName(i));
		        }
		        System.out.println("");
		    }
			
		    //Einnahmen Barchart
		    JDBCCategoryDataset data = new JDBCCategoryDataset (connection);
			data.executeQuery(queryLid);
		    CategoryDataset dataset = data;
		    
		    //Ausgaben Barchart
		    /*JDBCCategoryDataset data2 = new JDBCCategoryDataset (connec);
			data2.executeQuery(queryAus);
		    CategoryDataset dataset2 = data2;*/
	
		    /*
		    //Ausgaben und Einnahmen in ein Barchart
		    final CategoryItemRenderer renderer = new LineAndShapeRenderer();
		    renderer.setItemLabelsVisible(false);
		    
		    final CategoryPlot plot = new CategoryPlot();
	        plot.setDataset(dataset);
	        plot.setRenderer(renderer);
	       
	        plot.setDomainAxis(new CategoryAxis("Datum"));
	        plot.setRangeAxis(new NumberAxis("Betrag"));
	        
	        plot.setOrientation(PlotOrientation.VERTICAL);
	        plot.setRangeGridlinesVisible(true);
	        plot.setDomainGridlinesVisible(true);
	        
	        final CategoryItemRenderer renderer2 = new LineAndShapeRenderer();
	        plot.setDataset(1, dataset2);
	        plot.setRenderer(1, renderer2);
	        
	        plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
	        plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.UP_45);
	        final JFreeChart Barchart = new JFreeChart(plot);
	        Barchart.setTitle("Zahlungsmittelauswertung");
		    */
		    
		    
			//JDBCCategoryDataset dataset = new JDBCCategoryDataset(BPDatenbank.dbCon(), queryLid);
			//dataset.setTranspose();
			
		    JFreeChart LineChart = ChartFactory.createLineChart("Zahlungsmittelauswertung", "Datum", "Betrag", dataset, PlotOrientation.VERTICAL, false, true, true);
			CategoryPlot plot = LineChart.getCategoryPlot();
			BarRenderer renderer = null;
			renderer = new BarRenderer();
			ChartFrame  frame = new ChartFrame("", LineChart);
			frame.setVisible(true);
			frame.setSize(400,650);
			
			
			//ChartPanel barPanel = new ChartPanel (BarChart);
			//DiagrammPanel.removeAll();
			//DiagrammPanel.add(barPanel);
			//DiagrammPanel.validate();
			
			
			
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1);
		}
		
	}

//Ausgabenverteilung
		private void AusV(){
			try{		
				String queryEin = "SELECT Datum,Betrag FROM BenutzerAufwendungen WHERE (BenutzerID='"+this.id+"')";
				PreparedStatement pst = conn.prepareStatement(queryEin);
				ResultSet res = pst.executeQuery();
				
				//Prüfung
				ResultSetMetaData rsmd = res.getMetaData();
			    System.out.println("querying SELECT * FROM XXX");
			    int columnsNumber = rsmd.getColumnCount();
			    while (res.next()) {
			        for (int i = 1; i <= columnsNumber; i++) {
			            if (i > 1) System.out.print(",  ");
			            String columnValue = res.getString(i);
			            System.out.print(columnValue + " " + rsmd.getColumnName(i));
			        }
			        System.out.println("");
			    }
				
				JDBCCategoryDataset dataEin = new JDBCCategoryDataset (connection);
				dataEin.executeQuery(queryEin);
				CategoryDataset datasetEin = dataEin;
				
				 JFreeChart BarChart = ChartFactory.createBarChart("Zahlungsmittelauswertung", "Datum", "Betrag", datasetEin, PlotOrientation.VERTICAL, false, true, true);
					CategoryPlot plot = BarChart.getCategoryPlot();
					BarRenderer renderer = null;
					renderer = new BarRenderer();
					ChartFrame  frame = new ChartFrame("", BarChart);
					frame.setVisible(true);
					frame.setSize(400,650);
				
				
				
			}catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			
			}
		}

//Einnahmenverteilung
		private void EinV(){
			try{		
				String queryEin = "SELECT Datum,Betrag FROM BenutzerAufwendungen WHERE (BenutzerID='"+this.id+"')";
				PreparedStatement pst = conn.prepareStatement(queryEin);
				ResultSet res = pst.executeQuery();
				
				//Prüfung
				ResultSetMetaData rsmd = res.getMetaData();
			    System.out.println("querying SELECT * FROM XXX");
			    int columnsNumber = rsmd.getColumnCount();
			    while (res.next()) {
			        for (int i = 1; i <= columnsNumber; i++) {
			            if (i > 1) System.out.print(",  ");
			            String columnValue = res.getString(i);
			            System.out.print(columnValue + " " + rsmd.getColumnName(i));
			        }
			        System.out.println("");
			    }
				
				JDBCCategoryDataset dataEin = new JDBCCategoryDataset (connection);
				dataEin.executeQuery(queryEin);
				CategoryDataset datasetEin = dataEin;
				
				 JFreeChart BarChart = ChartFactory.createBarChart("Zahlungsmittelauswertung", "Datum", "Betrag", datasetEin, PlotOrientation.VERTICAL, false, true, true);
					CategoryPlot plot = BarChart.getCategoryPlot();
					BarRenderer renderer = null;
					renderer = new BarRenderer();
					ChartFrame  frame = new ChartFrame("", BarChart);
					frame.setVisible(true);
					frame.setSize(400,650);
				
				
				
			}catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			
			}
		}
}
