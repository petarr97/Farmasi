package main;

import java.sql.DriverManager;
import java.sql.SQLException;

import view.LogInView;

public class Main {

	public static void main(String[] args) {

		try {
			DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new LogInView().show();

	}
}
