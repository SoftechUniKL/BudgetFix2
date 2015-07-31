package Sparkonto;

import java.sql.Connection;
import java.sql.DriverManager;

public class KategorienDB {

	static Connection conn = null;

	public static Connection dbCon() {
		try {
			Class.forName("org.sqlite.JDBC");
			String db = "jdbc:sqlite:src/Sparkonto/Kategorien";
			conn = DriverManager.getConnection(db);
			System.out.println("---> Verbindung zur Kategorien-Datenbank OK!");
			return conn;
		} catch (Exception e) {

			System.out
					.println("---> Verbindung zur Kategorien-Datenbank gescheitert!");
			System.out.println("Exception " + e);

			return null;
		}
	}
}
