package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import com.toedter.calendar.JDateChooser;

public class BearbeitenEinnahmen extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField txtBetrag;
	private JTextField txtBemerkung;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BearbeitenEinnahmen frame = new BearbeitenEinnahmen();
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
	public BearbeitenEinnahmen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 480, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

// btnSchliessen
		JLabel btnSchliessen = new JLabel();
		btnSchliessen.setIcon(new ImageIcon(BearbeitenEinnahmen.class
				.getResource("/Design/Schliessen_Button.png")));
		btnSchliessen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
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
		btnSchliessen.setIcon(new ImageIcon(BearbeitenEinnahmen.class
				.getResource("/Design/Schliessen_Button.png")));
		btnSchliessen.setBounds(440, 15, 25, 25);
		contentPane.add(btnSchliessen);

// Überschrift "Bearbeiten der Einnahmen"
		JLabel lblHinzuE = new JLabel(
				"<html><u>Einnahmen bearbeiten</u></html>");
		lblHinzuE.setForeground(Color.WHITE);
		lblHinzuE.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblHinzuE.setHorizontalAlignment(SwingConstants.CENTER);
		lblHinzuE.setBounds(10, 11, 460, 38);
		contentPane.add(lblHinzuE);
		
		// lblBezeichnung
				JLabel lblBezeichnung = new JLabel("Bezeichnung:");
				lblBezeichnung.setForeground(Color.WHITE);
				lblBezeichnung.setFont(new Font("Tahoma", Font.BOLD, 14));
				lblBezeichnung.setBounds(100, 70, 118, 27);
				contentPane.add(lblBezeichnung);
		
		JComboBox cboBezeichnung = new JComboBox();
		cboBezeichnung.setForeground(Color.GRAY);
		cboBezeichnung.setBounds(222, 70, 145, 30);
		contentPane.add(cboBezeichnung);

// Betrag
		JLabel lblBetrag = new JLabel("Betrag:");
		lblBetrag.setForeground(Color.WHITE);
		lblBetrag.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBetrag.setBounds(100, 120, 86, 27);
		contentPane.add(lblBetrag);


// txtBetrag		
		txtBetrag = new JTextField();
		txtBetrag.setHorizontalAlignment(SwingConstants.CENTER);
		txtBetrag.setBackground(Color.WHITE);
		txtBetrag.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtBetrag.setForeground(Color.GRAY);
		txtBetrag.setBorder(null);
		txtBetrag.setBounds(222, 120, 144, 30);
		txtBetrag.setColumns(10);
		contentPane.add(txtBetrag);
		
// Datum
		JLabel lblDatum = new JLabel("Datum:");
		lblDatum.setForeground(Color.WHITE);
		lblDatum.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDatum.setBounds(100, 170, 81, 27);
		contentPane.add(lblDatum);

// Datum auswählen
		JDateChooser Datum = new JDateChooser();
		Datum.setDateFormatString("yyyy-MM-dd");
		Datum.setForeground(Color.GRAY);
		Datum.getCalendarButton().setForeground(Color.GRAY);
		Datum.setBounds(222, 170, 145, 30);
		contentPane.add(Datum);

// Kategorie
		JLabel Kategorie = new JLabel("Kategorie:");
		Kategorie.setForeground(Color.WHITE);
		Kategorie.setFont(new Font("Tahoma", Font.BOLD, 14));
		Kategorie.setBounds(100, 220, 118, 27);
		contentPane.add(Kategorie);

// Kategorie Combobox, die bereits angelegten Kategorien hier als
// Auswahl wählen
		JComboBox cboKategorie = new JComboBox();
		cboKategorie.setBounds(222, 220, 145, 30);
		contentPane.add(cboKategorie);


		// Bemerkung
		JLabel lblBemerkung = new JLabel("Bemerkung:");
		lblBemerkung.setForeground(Color.WHITE);
		lblBemerkung.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBemerkung.setBounds(100, 270, 118, 27);
		contentPane.add(lblBemerkung);

// txtBemerkung
		txtBemerkung = new JTextField();
		txtBemerkung.addKeyListener(new KeyAdapter() {
			// speichern über Enter
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					// speichern ausführen

				}
			}
		});
		txtBemerkung.setHorizontalAlignment(SwingConstants.CENTER);
		txtBemerkung.setForeground(Color.GRAY);
		txtBemerkung.setColumns(10);
		txtBemerkung.setBorder(null);
		txtBemerkung.setBackground(Color.WHITE);
		txtBemerkung.setBounds(222, 270, 144, 30);
		contentPane.add(txtBemerkung);

		// btnSpeichern
		JLabel btnSpeichern = new JLabel();
		btnSpeichern.setIcon(new ImageIcon(HinzufuegenEinnahmen.class
				.getResource("/Design/Speichern.png")));
		btnSpeichern.setBounds(175, 330, 144, 38);
		contentPane.add(btnSpeichern);

// btnZurueck
		JLabel btnZurueck = new JLabel("<html><u>Zur\u00FCck</u></html>");
		btnZurueck.addMouseListener(new MouseAdapter() {
			@Override
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
		});
		
		JLabel btnLoeschen = new JLabel();
		btnLoeschen.setIcon(new ImageIcon(BearbeitenEinnahmen.class.getResource("/Design/Loeschen.png")));
		btnLoeschen.setBounds(175, 379, 144, 38);
		contentPane.add(btnLoeschen);
		btnZurueck.setHorizontalAlignment(SwingConstants.CENTER);
		btnZurueck.setForeground(Color.WHITE);
		btnZurueck.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnZurueck.setBounds(170, 420, 144, 14);
		contentPane.add(btnZurueck);
		

		
// Hintergrund
		JLabel Hintergrund = new JLabel();
		Hintergrund.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Hintergrund.setHorizontalAlignment(SwingConstants.CENTER);
		Hintergrund.setForeground(Color.BLACK);
		Hintergrund.setIcon(new ImageIcon(HinzufuegenEinnahmen.class
				.getResource("/Design/GUI3.png")));
		Hintergrund.setBounds(0, 0, 480, 480);
		contentPane.add(Hintergrund);		
		
// Deaktivieren des Standard-JFrame Design und lege die Lage in Mitten
// des Bildschirms
		setUndecorated(true);
		setLocationRelativeTo(null);
	}
}
