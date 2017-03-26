package upm.blanca.tfg.optimization.tool.db.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
public class MySQLUtil {

	public static Connection getConnectionMySQL() throws SQLException{

		System.out.println("-------- MySQL JDBC Connection Testing ------------");

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return null;
		}

		System.out.println("MySQL JDBC Driver Registered!");
		Connection connection = null;
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tfg_pruebas","root", "root");	

		return connection;

	}
	public static void createQueryMySQL(Connection connection) throws SQLException{
		Statement stmt = null;

		//STEP 4: Execute a query
		System.out.println("Creating statement...");
		stmt = connection.createStatement();
		String sql;
		sql = "SELECT Matricula,Color FROM Vehiculos";
		ResultSet rs = stmt.executeQuery(sql);

		//STEP 5: Extract data from result set
		while(rs.next()){
			//Retrieve by column name
			String id  = rs.getString("Matricula");
			String color = rs.getString("Color");

			//Display values
			System.out.print("ID: " + id);
			System.out.print("COLOR: " + color);
			System.out.println();
		}
		//STEP 6: Clean-up environment
		rs.close();
		stmt.close();
		connection.close();

		if (connection != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}
	}
}