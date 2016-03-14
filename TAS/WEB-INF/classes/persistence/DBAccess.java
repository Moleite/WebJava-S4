package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe en charge de créer la connexion au SGBD
 */
public class DBAccess {
	
	private static Connection connection;
	
	/**
	 * Réalise la connexion si elle n'existe pas encore
	 * 
	 * @return La connexion courante
	 * @throws ClassNotFoundException si JDBC n'est pas trouvé
	 * @throws SQLException si les identifiants de connexion sont incorrects
	 */
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		
		if(connection != null)
			return connection;
		
		Class.forName("oracle.jdbc.OracleDriver");

		String url = "jdbc:oracle:thin:@vs-oracle2:1521:ORCL";
		String user = "brette";
		String pass = "brette";
	
		//Class.forName("com.mysql.jdbc.Driver");
		
		//String url = "jdbc:mysql://127.0.0.1/webjava";
		//String user = "benharka";
		//String pass = "benharka";
		
		connection = DriverManager.getConnection(url, user, pass);
		
		return connection; 
	}
}
