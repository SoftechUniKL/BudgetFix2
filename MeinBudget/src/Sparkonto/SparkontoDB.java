package Sparkonto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*; 

public class SparkontoDB {


		static Connection conne = null;
		
		public static Connection dbCon(){
			try{
				Class.forName("org.sqlite.JDBC");
				String db = "jdbc:sqlite:src/Sparkonto/Sparkonto";
				conne = DriverManager.getConnection(db);
				System.out.println("---> Verbindung zur Sparkonto-Datenbank OK!");
				return conne;
			}catch(Exception e){
				
				System.out.println("---> Verbindung zur Sparkonto-Datenbank gescheitert!");
				System.out.println("Exception " + e);
				
				return null;
			}
		}
	}

