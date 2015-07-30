package Registrierung;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Anmelden.AnmeldeDatenbank;
import Anmelden.AnmeldenFenster;

public class RegisterFenster extends JFrame {

	Connection connect = null;

	private JPanel contentPane;
	private JTextField textBenutzername;
	private JPasswordField txtPasswort;
	private JPasswordField txtPasswortWdh;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					RegisterFenster frame = new RegisterFenster();
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
	public RegisterFenster() {

		connect = AnmeldeDatenbank.dbCon();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Button Fenster-Schliessen
		final JLabel btnSchliessen = new JLabel();
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

				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							AnmeldenFenster frame = new AnmeldenFenster();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});

		btnSchliessen.setIcon(new ImageIcon(RegisterFenster.class
				.getResource("/Design/Schliessen_Button.png")));
		btnSchliessen.setBounds(265, 10, 25, 25);
		contentPane.add(btnSchliessen);

		// Textfeld Benutzername
		JLabel BenutzerLabel = new JLabel();
		BenutzerLabel.setIcon(new ImageIcon(RegisterFenster.class
				.getResource("/Design/txtfeld.png")));
		BenutzerLabel.setBounds(78, 120, 145, 30);
		contentPane.add(BenutzerLabel);

		textBenutzername = new JTextField();
		textBenutzername.setBorder(null);
		textBenutzername.setForeground(Color.GRAY);
		textBenutzername.setFont(new Font("Tahoma", Font.BOLD, 16));
		textBenutzername.setBounds(81, 124, 136, 20);
		contentPane.add(textBenutzername);
		textBenutzername.setColumns(10);

		// Textfeld Passwort
		JLabel PasswortLabel = new JLabel();
		PasswortLabel.setIcon(new ImageIcon(RegisterFenster.class
				.getResource("/Design/txtfeld.png")));
		PasswortLabel.setBounds(78, 160, 145, 30);
		contentPane.add(PasswortLabel);

		txtPasswort = new JPasswordField();
		txtPasswort.setForeground(Color.GRAY);
		txtPasswort.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtPasswort.setBorder(null);
		txtPasswort.setBounds(81, 164, 136, 20);
		contentPane.add(txtPasswort);

		// Textfeld Passwort wiederholen
		JLabel PasswortWdhLabel = new JLabel();

		PasswortWdhLabel.setIcon(new ImageIcon(RegisterFenster.class
				.getResource("/Design/txtfeld.png")));
		PasswortWdhLabel.setBounds(78, 200, 145, 30);
		contentPane.add(PasswortWdhLabel);

		// Button Registrieren
		JLabel Registrieren = new JLabel();
		Registrieren.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Bei Mausklick auf Registrieren speichert er Daten in SQL ein
				registrierung();
			}
		});

		txtPasswortWdh = new JPasswordField();
		txtPasswortWdh.addKeyListener(new KeyAdapter() {
			// Registrieren über Enter
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					// Registrierung ausführen
					registrierung();
				}
			}
		});
		txtPasswortWdh.setAutoscrolls(true);
		txtPasswortWdh.setBorder(null);
		txtPasswortWdh.setForeground(Color.GRAY);
		txtPasswortWdh.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtPasswortWdh.setEnabled(true);
		txtPasswortWdh.setEditable(true);
		txtPasswortWdh.setText("");
		txtPasswortWdh.setBounds(81, 204, 136, 20);
		contentPane.add(txtPasswortWdh);
		Registrieren.setIcon(new ImageIcon(RegisterFenster.class
				.getResource("/Design/Registrieren.png")));
		Registrieren.setBounds(78, 240, 146, 38);
		contentPane.add(Registrieren);

		// Hintergrund
		JLabel Hintergrund = new JLabel("New label");
		Hintergrund.setIcon(new ImageIcon(RegisterFenster.class
				.getResource("/Design/login.png")));
		Hintergrund.setBounds(0, 0, 300, 300);
		contentPane.add(Hintergrund);

		// Deaktivieren des Standard-JFrame Design und lege die Lage in Mitten
		// des Bildschirms
		setUndecorated(true);
		setLocationRelativeTo(null);
	}

	// Registrierung
	public void registrierung() {
		try {

			char[] passInput = txtPasswort.getPassword();
			String passString = new String(passInput);

			char[] passInput2 = txtPasswortWdh.getPassword();
			String passString2 = new String(passInput2);

			if (passString.equals(passString2)) {
				System.out.println("Registrierung erfolgreich");
				JOptionPane.showMessageDialog(null,
						"Benutzerkonto erfolgreich erstellt!");
				String sqlQuery = "INSERT INTO Benutzer (Benutzername,Passwortkombination) VALUES(?,?)";
				PreparedStatement stm = connect.prepareStatement(sqlQuery);
				stm.setString(1, textBenutzername.getText());
				stm.setString(2, passString);

				// ResultSet result = stm.executeQuery();
				stm.execute();

				Anmelden.AnmeldenFenster frame = new AnmeldenFenster();
				frame.setVisible(true);

				// Fenster verschwindet
				dispose();
			} else {
				System.out.println("Passwörter stimmen nicht überein!");
				JOptionPane.showMessageDialog(null, "Überprüfe deine Eingabe!");
			}

		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
}