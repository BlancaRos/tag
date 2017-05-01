package upm.blanca.tfg.optimization.tool.db.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import upm.blanca.tfg.optimization.tool.constants.Constants;
import upm.blanca.tfg.optimization.tool.main.MainInterface;
import upm.blanca.tfg.optimization.tool.util.QueryBean;

public class OracleDBUtil {

	public static Connection getConnectionOracle() throws SQLException{

		System.out.println("-------- Oracle JDBC Connection Testing ------");

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your Oracle JDBC Driver?");
			e.printStackTrace();
		}

		System.out.println("Oracle JDBC Driver Registered!");

		Connection connection = null;

		try {
			//connection = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521/orcl", "hr","oracle");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521/" + MainInterface.queryBean.getBbddService(), MainInterface.queryBean.getBbddUser(),MainInterface.queryBean.getBbddPass());

		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
		}
		return connection;
	}

	public static ResultSet createQueryOracle(Connection connection, QueryBean queryBean) throws SQLException{
		ResultSet resultSet = null;
		if (connection != null) {
			System.out.println("You made it, take control your database now!");

			Statement stmt;
			try {
				stmt = connection.createStatement();
				String finalQuery = Constants.BLANK;
				if (MainInterface.queryBean.getQueryString().contains(Constants.SEMICOLON)) {
					finalQuery = MainInterface.queryBean.getQueryString().replace(Constants.SEMICOLON,Constants.BLANK);
				} else {
					finalQuery = MainInterface.queryBean.getQueryString();
				}

				long startTimeMillis = System.currentTimeMillis();

				Date currentDateMillis = new Date(startTimeMillis);
				DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				String dateString  = formatter.format(currentDateMillis);

				MainInterface.queryBean.setQueryDate(dateString);

				String startTime = new SimpleDateFormat("HH:mm:ss").format(new Date(startTimeMillis));
				MainInterface.queryBean.setQueryStartTime(startTime);

				resultSet=stmt.executeQuery(finalQuery);

				long endTimeMillis = System.currentTimeMillis();
				String endTime = new SimpleDateFormat("HH:mm:ss").format(new Date(endTimeMillis));
				MainInterface.queryBean.setQueryEndTime(endTime);

				long totalTime = endTimeMillis- startTimeMillis;
				MainInterface.queryBean.setTotalTime((int) totalTime);


			} catch (SQLException e) {
				e.printStackTrace();
			}  
//			connection.close();
		} else {				
			System.out.println("Failed to make connection!");
		}
		return resultSet;
	}
}