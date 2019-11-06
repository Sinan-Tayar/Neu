package com.google.gwt.sample.stockwatcher.server.db;

import java.sql.Connection;
import java.sql.DriverManager;


/*Klasse um die Datenbank Verbindung herzustellen.
 * Diese Klasse wird nur einmal instanziiert
 * 
 */

public class DBConnection {

	/*
	 * Die folgende Variable speichert die einzige Instanz dieser Klasse.
	 * Sie ist nur einmal für sämtliche Instanzen vorhanden.
	 */
    private static Connection con = null;  

    /**
     * Über die folgende zwei URLs werden die Datenbanken angesprochen.
     * Eine lokale zum Testen
     * Eine in der Cloud (Google Cloud Plattform)
     */
 private static String localUrl = "jdbc:mysql://127.0.0.1:3306/Datenbank?user=root&password=trabzonspor61";
    
 /**
  * Singleton Eigenschaft wird sichergestellt indem nur eine Instanz
  * von DBConnection vorhanden ist.
  * 
  * DBConnection wird immer durch Aufruf dieser statischen Methode instanziiert
  * 
  * 
  * @return <code>DBConncetion</code>-Objekt.
  * @see con
  */
    
 public static Connection connection() {
	 // Wenn es bisher keine Conncetion zur DB gab, ... 
	 if(con ==null) {
		 String url=null;
		 try {
			 //Local MySQL instance to use during development
			 Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			 url=localUrl;
			 
		 con= DriverManager.getConnection(url);
	 }
  catch(Exception e) {
		 con = null;
		 e.printStackTrace();
		 throw new RuntimeException(e.getMessage());
	 }
 }
	//Zurückgeben der Verbindung
return con;
}
}