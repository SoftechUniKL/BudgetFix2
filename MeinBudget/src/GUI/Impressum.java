package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Impressum extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Impressum frame = new Impressum();
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
	public Impressum() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 480, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel btnSchliessen = new JLabel("New label");
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
		btnSchliessen.setIcon(new ImageIcon(Impressum.class
				.getResource("/Design/Schliessen_Button.png")));
		btnSchliessen.setBounds(440, 15, 25, 25);
		contentPane.add(btnSchliessen);

		// Überschrift "Impressum"
		JLabel lblHinzuA = new JLabel("<html><u>Impressum</u></html>");
		lblHinzuA.setForeground(Color.WHITE);
		lblHinzuA.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblHinzuA.setHorizontalAlignment(SwingConstants.CENTER);
		lblHinzuA.setBounds(10, 11, 460, 38);
		contentPane.add(lblHinzuA);

		// btnZurueck
		JLabel btnZurueck = new JLabel("<html><u>Zur\u00FCck</u></html>");
		btnZurueck.addMouseListener(new MouseAdapter() {
			@Override
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
		});
		btnZurueck.setHorizontalAlignment(SwingConstants.CENTER);
		btnZurueck.setForeground(Color.WHITE);
		btnZurueck.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnZurueck.setBounds(170, 430, 144, 14);
		contentPane.add(btnZurueck);

		// Text für Impressum
		JLabel lblText = new JLabel(
				"<html><b>BudgetFix Version 1.2.0 Beta </b><p/><p/>"
						+ "Fachbereich Informatik am Lehrstuhl AG Softwaretechnik <p/>"
						+ "der Technischen Universiät Kaiserslautern <p/>"
						+ "Erwin-Schrödinger-Straße 1 <p/>"
						+ "67663 Kaiserslautern <p/><p/>"
						+ "Programmierprojekt SoSe 2015 - Betreuerin: Dr. Annette Bieniusa <p/>"
						+ "Programmier(in): Que Ly Dong, Patrick Drucks & Patric Kleine <p/>"
						+ "Copyright 2015<p/><p/><p/>"
						+ "<u>Haftung für Inhalte:</u>"
						+ " Die Inhalte des BudgetFix wurden mit <p/>"
						+ "größter Sorgfalt erstellt. Für die Richtigkeit,<p/>"
						+ " Vollständigkeit und Aktualität der Inhalte können<p/> "
						+ "wir jedoch keine Gewähr übernehmen.<p/><p/>"
						+ "<u>Urheberrecht:</u>"
						+ " Die durch die Betreiber erstellten Inhalte <p/>"
						+ "und Werke auf diesen Seiten unterliegen <p/>"
						+ "dem deutschen Urheberrecht.<p/>"
						+ "Beiträge Dritter sind als solche gekennzeichnet.<p/>"
						+ "Die Vervielfältigung, Bearbeitung, Verbreitung und <p/>"
						+ " jede Art der Verwertung außerhalb der Grenzen <p/> "
						+ "des Urheberrechtes bedürfen der schriftlichen<p/>"
						+ "Zustimmung des jeweiligen Erstellers.<p/><p/>"
						+ "<u>Datenschutz:</u>"
						+ " Die Betreiber dieser Seiten nehmen den Schutz Ihrer<p/> "
						+ "persönlichen Daten sehr ernst. Wir behandeln Ihre<p/>"
						+ "personenbezogenen Daten vertraulich und entsprechend <p/>"
						+ "der gesetzlichen Datenschutzvorschriften sowie <p/>"
						+ "dieser Datenschutzerklärung.</html>");
		lblText.setHorizontalAlignment(SwingConstants.CENTER);
		lblText.setForeground(Color.WHITE);
		lblText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblText.setBounds(40, 70, 400, 300);

		// JScrollPane für Impressum-Text
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setViewportBorder(null);
		scrollPane.getViewport().setBackground(new Color(27, 109, 220));
		scrollPane.setBounds(31, 70, 420, 330);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(lblText);

		// Hintergrund
		JLabel Hintergrund = new JLabel();
		Hintergrund.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Hintergrund.setHorizontalAlignment(SwingConstants.CENTER);
		Hintergrund.setForeground(Color.BLACK);
		Hintergrund.setIcon(new ImageIcon(KategorieAnlegen.class
				.getResource("/Design/GUI3.png")));
		Hintergrund.setBounds(0, 0, 480, 480);
		contentPane.add(Hintergrund);

		// Deaktivieren des Standard-JFrame Design und lege die Lage in Mitten
		// des Bildschirms
		setUndecorated(true);
		setLocationRelativeTo(null);

	}
}
