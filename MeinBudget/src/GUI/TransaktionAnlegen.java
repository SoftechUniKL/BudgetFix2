package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

public class TransaktionAnlegen extends JFrame {

	private JPanel contentPane;
	private JTextField txtBezeichnung;
	private JTextField txtIntervall;
	private JTextField txtBetrag;
	private JTextField txtBemerkung;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TransaktionAnlegen frame = new TransaktionAnlegen();
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
	public TransaktionAnlegen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 480, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Schliessenbutton
		JLabel btnSchliessen = new JLabel();
		btnSchliessen.addMouseListener(new MouseAdapter() {
			// Schliessenbutton ist grau
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnSchliessen.setIcon(new javax.swing.ImageIcon(getClass()
						.getResource("/Design/schliessen_button2.png")));
			}

			// Schliessenbutton ist blau
			@Override
			public void mouseExited(MouseEvent arg0) {
				btnSchliessen.setIcon(new javax.swing.ImageIcon(getClass()
						.getResource("/Design/Schliessen_Button.png")));
			}

			// Schliesst das Fenster
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}

		});
		btnSchliessen.setIcon(new ImageIcon(TransaktionAnlegen.class
				.getResource("/Design/Schliessen_Button.png")));
		btnSchliessen.setBounds(440, 15, 25, 25);
		contentPane.add(btnSchliessen);

		// Überschrift "wiederholene Transaktionen Anlegen"
		JLabel lblTransaktionAnlegen = new JLabel(
				"<html><u>wiederholene Transaktionen anlegen</u></html>");
		lblTransaktionAnlegen.setForeground(Color.WHITE);
		lblTransaktionAnlegen.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTransaktionAnlegen.setHorizontalAlignment(SwingConstants.CENTER);
		lblTransaktionAnlegen.setBounds(10, 15, 460, 38);
		contentPane.add(lblTransaktionAnlegen);

		// Bezeichung
		JLabel Bezeichnung = new JLabel("Bezeichnung:");
		Bezeichnung.setForeground(Color.WHITE);
		Bezeichnung.setFont(new Font("Tahoma", Font.BOLD, 14));
		Bezeichnung.setBounds(75, 72, 118, 27);
		contentPane.add(Bezeichnung);

		// Textfeld Bezeichnung zum Eingeben, wie die Transaktion heissen soll
		txtBezeichnung = new JTextField();
		txtBezeichnung.setHorizontalAlignment(SwingConstants.CENTER);
		txtBezeichnung.setForeground(Color.GRAY);
		txtBezeichnung.setColumns(10);
		txtBezeichnung.setBorder(null);
		txtBezeichnung.setBackground(Color.WHITE);
		txtBezeichnung.setBounds(222, 70, 183, 30);
		contentPane.add(txtBezeichnung);

		// lblBezeichnung
		JLabel lblBezeichnung = new JLabel();
		lblBezeichnung.setIcon(new ImageIcon(TransaktionAnlegen.class
				.getResource("/Design/Textfeldgross.png")));
		lblBezeichnung.setBounds(222, 70, 184, 30);
		contentPane.add(lblBezeichnung);

		// Kategorie
		JLabel Kategorie = new JLabel("Kategorie:");
		Kategorie.setForeground(Color.WHITE);
		Kategorie.setFont(new Font("Tahoma", Font.BOLD, 14));
		Kategorie.setBounds(75, 122, 118, 27);
		contentPane.add(Kategorie);

		// Kategorie Combobox, die bereits angelegten Kategorien hier als
		// Auswahl wählen
		JComboBox cboKategorie = new JComboBox();
		cboKategorie.setBounds(222, 120, 183, 30);
		contentPane.add(cboKategorie);

		// Intervall
		JLabel lblIntervall = new JLabel("Intervall:");
		lblIntervall.setForeground(Color.WHITE);
		lblIntervall.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblIntervall.setBounds(75, 172, 118, 27);
		contentPane.add(lblIntervall);

		// Intervall-Feld --> Soll Zahlen eingeben können
		txtIntervall = new JTextField();
		txtIntervall.setHorizontalAlignment(SwingConstants.CENTER);
		txtIntervall.setForeground(Color.GRAY);
		txtIntervall.setColumns(10);
		txtIntervall.setBorder(null);
		txtIntervall.setBackground(Color.WHITE);
		txtIntervall.setBounds(222, 170, 54, 30);
		contentPane.add(txtIntervall);

		// lblIntervall
		JLabel lblIntervallEingeben = new JLabel();
		lblIntervallEingeben.setIcon(new ImageIcon(TransaktionAnlegen.class
				.getResource("/Design/Textfeldgross.png")));
		lblIntervallEingeben.setBounds(222, 170, 55, 30);
		contentPane.add(lblIntervallEingeben);

		// Hier kann man Auswählen zwischen Tag, Woche, Monat, Jahr
		JComboBox cboIntervall = new JComboBox();
		cboIntervall.setBounds(285, 170, 120, 30);
		contentPane.add(cboIntervall);

		// Nächste Fälligkeit --> Hier fehlt noch eine Kalenderfunktion
		JLabel lblNchsteFlligkeit = new JLabel("N\u00E4chste F\u00E4lligkeit:");
		lblNchsteFlligkeit.setForeground(Color.WHITE);
		lblNchsteFlligkeit.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNchsteFlligkeit.setBounds(75, 222, 135, 27);
		contentPane.add(lblNchsteFlligkeit);

		// Datum auswählen
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(222, 220, 183, 30);
		contentPane.add(dateChooser);

		// Betrag
		JLabel lblBetrag = new JLabel("Betrag:");
		lblBetrag.setForeground(Color.WHITE);
		lblBetrag.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBetrag.setBounds(75, 272, 118, 27);
		contentPane.add(lblBetrag);

		// Betrag-Textfeld --> Einnahmen/Ausgaben
		txtBetrag = new JTextField();
		txtBetrag.setHorizontalAlignment(SwingConstants.CENTER);
		txtBetrag.setForeground(Color.GRAY);
		txtBetrag.setColumns(10);
		txtBetrag.setBorder(null);
		txtBetrag.setBackground(Color.WHITE);
		txtBetrag.setBounds(222, 270, 183, 30);
		contentPane.add(txtBetrag);

		// lblBetrag
		JLabel Betrag = new JLabel();
		Betrag.setBounds(221, 270, 184, 30);
		contentPane.add(Betrag);

		// Bemerkung
		JLabel lblBemerkung = new JLabel("Bemerkung:");
		lblBemerkung.setForeground(Color.WHITE);
		lblBemerkung.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBemerkung.setBounds(75, 322, 118, 27);
		contentPane.add(lblBemerkung);

		// Bemerkung-Textfeld
		txtBemerkung = new JTextField();
		txtBemerkung.setHorizontalAlignment(SwingConstants.CENTER);
		txtBemerkung.setForeground(Color.GRAY);
		txtBemerkung.setColumns(10);
		txtBemerkung.setBorder(null);
		txtBemerkung.setBackground(Color.WHITE);
		txtBemerkung.setBounds(222, 320, 183, 30);
		contentPane.add(txtBemerkung);

		// lblBemerkung
		JLabel Bemerkung = new JLabel();
		Bemerkung.setBounds(222, 320, 184, 30);
		contentPane.add(Bemerkung);

		// btnSpeichern
		JLabel btnSpeichern = new JLabel();
		btnSpeichern.setIcon(new ImageIcon(TransaktionAnlegen.class
				.getResource("/Design/Speichern.png")));
		btnSpeichern.setBounds(175, 370, 144, 38);
		contentPane.add(btnSpeichern);

		// btnZurueck
		JLabel btnZurueck = new JLabel("<html><u>Zur\u00FCck</u></html>");
		btnZurueck.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Wiederholung frame = new Wiederholung(Start.id);
							frame.setVisible(true);
							dispose();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnZurueck.setHorizontalAlignment(SwingConstants.CENTER);
		btnZurueck.setForeground(Color.WHITE);
		btnZurueck.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnZurueck.setBounds(170, 410, 144, 14);
		contentPane.add(btnZurueck);

		// Hintergrund
		JLabel Hintergrund = new JLabel();
		Hintergrund.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Hintergrund.setHorizontalAlignment(SwingConstants.CENTER);
		Hintergrund.setForeground(Color.BLACK);
		Hintergrund.setIcon(new ImageIcon(TransaktionAnlegen.class
				.getResource("/Design/GUI3.png")));
		Hintergrund.setBounds(0, 0, 480, 480);
		contentPane.add(Hintergrund);

		// Deaktivieren des Standard-JFrame Design und lege die Lage in Mitten
		// des Bildschirms
		setUndecorated(true);
		setLocationRelativeTo(null);
	}
}
