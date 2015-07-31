package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
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

public class HinzufuegenAusgaben extends JFrame {

	Connection connection = null;
	Connection conn = null;
	static int id;

	private JPanel contentPane;
	private JTextField txtBetrag;
	private JTextField txtBemerkung;
	private JTextField txtBezeichnung;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HinzufuegenAusgaben frame = new HinzufuegenAusgaben(id);
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
	public HinzufuegenAusgaben(int id) {

		this.id = id;

		// Verbindung zur BPDatenbank - Erträge und Aufwendungen
		connection = BPDatenbank.dbCon();
		// Verbindung zur BPDB - Kategorien
		conn = BPDatenbank.dbCon();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 480, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// btnSchliessen
		JLabel btnSchliessen = new JLabel();
		btnSchliessen.setIcon(new ImageIcon(HinzufuegenAusgaben.class
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
		btnSchliessen.setIcon(new ImageIcon(HinzufuegenAusgaben.class
				.getResource("/Design/Schliessen_Button.png")));
		btnSchliessen.setBounds(440, 15, 25, 25);
		contentPane.add(btnSchliessen);

		// Überschrift "Ausgaben hinzufügen"
		JLabel lblHinzuA = new JLabel("<html><u>Ausgaben hinzufügen</u></html>");
		lblHinzuA.setForeground(Color.WHITE);
		lblHinzuA.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblHinzuA.setHorizontalAlignment(SwingConstants.CENTER);
		lblHinzuA.setBounds(10, 11, 460, 38);
		contentPane.add(lblHinzuA);

		// Betrag
		JLabel lblBetrag = new JLabel("Betrag:");
		lblBetrag.setForeground(Color.WHITE);
		lblBetrag.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBetrag.setBounds(100, 70, 86, 27);
		contentPane.add(lblBetrag);

		// txtBetrag
		txtBetrag = new JTextField();
		txtBetrag.setHorizontalAlignment(SwingConstants.CENTER);
		txtBetrag.setForeground(Color.GRAY);
		txtBetrag.setColumns(10);
		txtBetrag.setBorder(null);
		txtBetrag.setBackground(Color.WHITE);
		txtBetrag.setBounds(223, 70, 144, 30);
		contentPane.add(txtBetrag);

		// lblBezeichnung
		JLabel lblBezeichnung = new JLabel("Bezeichnung:");
		lblBezeichnung.setForeground(Color.WHITE);
		lblBezeichnung.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBezeichnung.setBounds(100, 130, 100, 27);
		contentPane.add(lblBezeichnung);

		// Bezeichnung
		txtBezeichnung = new JTextField();
		txtBezeichnung.setHorizontalAlignment(SwingConstants.CENTER);
		txtBezeichnung.setForeground(Color.GRAY);
		txtBezeichnung.setColumns(10);
		txtBezeichnung.setBorder(null);
		txtBezeichnung.setBackground(Color.WHITE);
		txtBezeichnung.setBounds(223, 130, 144, 30);
		contentPane.add(txtBezeichnung);

		// Datum
		JLabel lblDatum = new JLabel("Datum:");
		lblDatum.setForeground(Color.WHITE);
		lblDatum.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDatum.setBounds(100, 190, 81, 27);
		contentPane.add(lblDatum);

		// Datum auswählen
		JDateChooser Datum = new JDateChooser();
		Datum.setDateFormatString("yyyy-MM-dd");
		Datum.setForeground(Color.GRAY);
		Datum.getCalendarButton().setForeground(Color.GRAY);
		Datum.setBounds(222, 190, 145, 30);
		contentPane.add(Datum);

		String datum1 = ((JTextField) Datum.getDateEditor().getUiComponent())
				.getText().toString();
		System.out.println(datum1);

		// Kategorie
		JLabel Kategorie = new JLabel("Kategorie:");
		Kategorie.setForeground(Color.WHITE);
		Kategorie.setFont(new Font("Tahoma", Font.BOLD, 14));
		Kategorie.setBounds(100, 250, 118, 27);
		contentPane.add(Kategorie);

		// Kategorie Combobox, die bereits angelegten Kategorien hier als
		// Auswahl wählen
		JComboBox cboKategorie = new JComboBox();
		cboKategorie.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
			}

			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				String selectedItem = (String) cboKategorie.getSelectedItem();
				System.out.println(selectedItem);
			}

			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
			}
		});
		cboKategorie.setBounds(222, 250, 145, 30);
		contentPane.add(cboKategorie);

		try {
			String sql = "SELECT * FROM BenutzerKategorien WHERE Typ='Ausgaben' ";
			PreparedStatement stm = conn.prepareStatement(sql);
			ResultSet result = stm.executeQuery();

			while (result.next()) {
				String kategorie = result.getString("Kategorie");
				cboKategorie.addItem(kategorie);
			}
			result.close();
			stm.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		//String ausgewaelteKategorie = cboKategorie.getSelectedItem().toString();

		// Bemerkung
		JLabel lblBemerkung = new JLabel("Bemerkung:");
		lblBemerkung.setForeground(Color.WHITE);
		lblBemerkung.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBemerkung.setBounds(100, 310, 118, 27);
		contentPane.add(lblBemerkung);

		// txtBemerkung
		txtBemerkung = new JTextField();
		txtBemerkung.setHorizontalAlignment(SwingConstants.CENTER);
		txtBemerkung.setForeground(Color.GRAY);
		txtBemerkung.setColumns(10);
		txtBemerkung.setBorder(null);
		txtBemerkung.setBackground(Color.WHITE);
		txtBemerkung.setBounds(222, 310, 144, 30);
		contentPane.add(txtBemerkung);

		// btnSpeichern
		JLabel btnSpeichern = new JLabel();
		btnSpeichern.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				try {

					String sqlQuery = "INSERT INTO BenutzerAufwendungen (Datum,Bezeichnung,Kategorie,Art,Betrag,BenutzerID,Bemerkung) VALUES(?,?,?,?,?,?,?) ";
					PreparedStatement pst = connection
							.prepareStatement(sqlQuery);

					// Datum
					pst.setString(1, ((JTextField) Datum.getDateEditor()
							.getUiComponent()).getText());

					// Bezeichnung
					pst.setString(2, txtBezeichnung.getText());

					// Kategorie
					String ausgewaelteKategorie = cboKategorie
							.getSelectedItem().toString();
					pst.setString(3, ausgewaelteKategorie);

					// Art
					pst.setString(4, "variabel");

					// Betrag
					pst.setString(5, txtBetrag.getText());

					// BenutzerID
					pst.setLong(6, id);

					// Bemerkung
					pst.setString(7, txtBemerkung.getText());

					pst.execute();

					JOptionPane.showMessageDialog(null,
							"Erfolgreich gespeichert!");

				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		});
		btnSpeichern.setIcon(new ImageIcon(HinzufuegenAusgaben.class
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
		Hintergrund.setIcon(new ImageIcon(HinzufuegenAusgaben.class
				.getResource("/Design/GUI3.png")));
		Hintergrund.setBounds(0, 0, 480, 480);
		contentPane.add(Hintergrund);

		// Deaktivieren des Standard-JFrame Design und lege die Lage in Mitten
		// des Bildschirms
		setUndecorated(true);
		setLocationRelativeTo(null);

	}

	/*
	 * public void neueAusgaben(String ausgewaelteKategorie, String datum1) {
	 * 
	 * 
	 * try{
	 * 
	 * String sqlQuery =
	 * "INSERT INTO BenutzerAufwendungen (Datum,Bezeichnung,Kategorie,Art,Betrag,BenutzerID,Bemerkung) VALUES(?,?,?,?,?,?,?) "
	 * ; PreparedStatement pst = connection.prepareStatement(sqlQuery);
	 * 
	 * //Datum pst.setString(1, datum1);
	 * 
	 * //Bezeichnung pst.setString(2, txtBezeichnung.getText());
	 * 
	 * //Kategorie
	 * 
	 * pst.setString(3, ausgewaelteKategorie);
	 * 
	 * //Art pst.setString(4, "variabel");
	 * 
	 * //Betrag pst.setString(5, txtBetrag.getText());
	 * 
	 * //BenutzerID pst.setLong(6, id);
	 * 
	 * //Bemerkung pst.setString(7, txtBemerkung.getText() );
	 * 
	 * 
	 * pst.execute();
	 * 
	 * JOptionPane.showMessageDialog(null, "Erfolgreich gespeichert!");
	 * 
	 * 
	 * }catch(Exception ex){ ex.printStackTrace(); }
	 * 
	 * 
	 * }
	 */

}
