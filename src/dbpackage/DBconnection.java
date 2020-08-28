package dbpackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBconnection {

	public static Connection conn = null;
	public static DBconnection instance = null;
	public static String dbURL = "jdbc:sqlserver://DESKTOP-KG2I6N9;Database=Narudžbaa";
	public static String user = "test";
	public static String password = "test1234";

	public static DBconnection getInstance() {
		if (instance == null) {
			instance = new DBconnection();
		}
		return instance;
	}

	public static Connection createConnection() {
		try {
			if (conn == null)
				conn = DriverManager.getConnection(dbURL, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;

	}

	public static ResultSet getResultSet(String Query) {
		Statement stm;
		try {
			stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(Query);
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
