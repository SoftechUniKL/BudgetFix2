 package BudgetPlan;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import com.opencsv.CSVReader;

/**
 * Posten in der Budgetplanung
 */
public class Posten {
	/**
	 * Datum, wann der Posten verbucht wurde
	 */
	private Date datum;
	/**
	 * Kurze Beschreibung
	 */
	private String bezeichnung;
	/**
	 * Hoehe des Postens
	 */
	
	private String kategorie;
	/**
	 * Zuordnung der Kategorie
	 */
	
	private String art;
	/**
	 * Fix oder Variabel
	 */
	
	private double betrag;

	/**
	 * Konstruktor
	 * 
	 * @param datum
	 *            Datum, wann der Posten verbucht wurde
	 * @param bezeichnung
	 *            Kurze Beschreibung
	 * @param betrag
	 *            Hoehe des Postens
	 * @param kategoie
	 * 			  Gruppierung
	 * @param art
	 * 			  Fixkosten oder Variable Kosten
	 */
	public Posten(Date datum, String bezeichnung, double betrag, String kategorie, String art) {
		this.bezeichnung = bezeichnung;
		this.datum = datum;
		this.betrag = betrag;
		this.kategorie = kategorie;
		this.art = art;
	}
	
	public double getBetrag() {
		return betrag;
	}

	public Date getDatum() {
		return datum;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}

	public String getKategorie() {
		return kategorie;
	}
	
	public String getArt() {
		return art;
}
	
	
}

