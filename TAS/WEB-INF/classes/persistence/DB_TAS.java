package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe en charge de cr�er la connexion au SGBD
 */
public class DB_TAS {
	
	private static Connection connection;
	
	/**
	 * R�alise la connexion si elle n'existe pas encore
	 * 
	 * @return La connexion courante
	 * @throws ClassNotFoundException si JDBC n'est pas trouv�
	 * @throws SQLException si les identifiants de connexion sont incorrects
	 */
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		
		if(connection != null)
			return connection;
		
	//	Class.forName("oracle.jdbc.OracleDriver");

	//	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	//	String user = "system";
	//	String pass = "michel";
	
		Class.forName("com.mysql.jdbc.Driver");
		
		String url = "jdbc:mysql://127.0.0.1/TAS";
		String user = "benharka";
		String pass = "benharka";
		
		connection = DriverManager.getConnection(url, user, pass);
		
		return connection; 
	}
}
