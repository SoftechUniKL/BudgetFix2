package BudgetPlan;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

/**
 * Datenmodell des Budgetplaners
 * 
 * Die Daten werden in der Datei data/budget.csv abgespeichert als CSV-Datei.
 * 
 */
public class BudgetPlanModel {
	List<Posten> ausgaben;

	public BudgetPlanModel() {
		this.ausgaben = new ArrayList<Posten>();
		try {
			// Zeilenweises Einlesen der Daten
			CSVReader reader = new CSVReader(new FileReader("data/budget.csv"));
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.GERMAN);
				Date datum = df.parse(nextLine[0]);
				String bezeichnung = nextLine[1];
				String kategorie = nextLine[3];
				String art = nextLine[4];
				double betrag = Double.parseDouble(nextLine[2]);
				ausgaben.add(new Posten(datum, bezeichnung, betrag, kategorie, art));
			}
			reader.close();

		} catch (FileNotFoundException e) {
			System.err
					.println("Die Datei data/budget.csv wurde nicht gefunden!");
			System.exit(1);
		} catch (IOException e) {
			System.err
					.println("Probleme beim Oeffnen der Datei data/budget.csv!");
			System.exit(1);
		} catch (ParseException e) {
			System.err
					.println("Formatfehler: Die Datei konnte nicht eingelesen werden!");
			System.exit(1);
		}
	//}
/*/Neue Ausgaben 

	//public Schreiben(){
		//this.ausgaben = new ArrayList<Posten>();
	//	try {
		// Zeilenweises Einschreiben der Daten
		CSVWriter writer = new CSVWriter(new FileWriter("data/budget.csv"));
		String[] nextLine;
		for (int i=0, i ((nextLine = writer.writeNext()) = null) {
			DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.GERMAN);
			Date datum = df.parse(nextLine[0]);
			String bezeichnung = nextLine[1];
			String kategorie = nextLine[3];
			String art = nextLine[4];
			double betrag = Double.parseDouble(nextLine[2]);
			ausgaben.add(new Posten(datum, bezeichnung, betrag, kategorie, art));
		}
		writer.close();

	} catch (FileNotFoundException e) {
		System.err
				.println("Die Datei data/budget.csv wurde nicht gefunden!");
		System.exit(1);
	} catch (IOException e) {
		System.err
				.println("Probleme beim Oeffnen der Datei data/budget.csv!");
		System.exit(1);
	} catch (ParseException e) {
		System.err
				.println("Formatfehler: Die Datei konnte nicht eingelesen werden!");
		System.exit(1);
	*/}
}
