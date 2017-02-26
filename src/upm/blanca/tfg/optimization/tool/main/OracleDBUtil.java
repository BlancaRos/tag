package upm.blanca.tfg.optimization.tool.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import upm.blanca.tfg.optimization.tool.constants.Constants;
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
			connection = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521/orcl", "hr","oracle");
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
		}
		return connection;


	}
	public static void createQueryOracle(Connection connection, QueryBean queryBean) throws SQLException{
		if (connection != null) {
			System.out.println("You made it, take control your database now!");

			Statement stmt;
			try {
				stmt = connection.createStatement();
				//step4 execute query  
				//ResultSet rs=stmt.executeQuery("select * from personas"); 
				String finalQuery = Constants.BLANK;
				if (MainInterface.queryBean.getQueryString().contains(Constants.SEMICOLON)) {
					finalQuery = MainInterface.queryBean.getQueryString().replace(Constants.SEMICOLON,Constants.BLANK);
				} else {
					finalQuery = MainInterface.queryBean.getQueryString();
				}
				// Hacer loop de ejecuciones
				long startTime = System.currentTimeMillis();
				ResultSet rs=stmt.executeQuery(finalQuery);
				long endTime = System.currentTimeMillis();

				long totalTime = endTime- startTime;
				// Numero de columnas pedidas a iterar
				int numOfColums = rs.getMetaData().getColumnCount();

				// Iterar los resultados de la query
				while(rs.next())  {

					for (int i=1; i<=numOfColums;i++){
						System.out.println(rs.getObject(i));	 
					}
					//					System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
					//					System.out.println(rs.getObject("VEHICULOID"));
					//					System.out.println(rs.getObject("NUMEROBASTIDOR"));
					//					System.out.println(rs.getObject("MARCAMODELO"));
					//					System.out.println(rs.getObject("COLOR"));
					//					System.out.println(rs.getObject("ANIOFABRICACION"));
					//					System.out.println(rs.getObject("OBSERVACIONES"));
					System.out.println("-------------");
				}
				System.out.println("La consulta ha tardado: "  + totalTime + "ms");
				connection.close();  

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  

			//step5 close the connection object  
		} else {				
			System.out.println("Failed to make connection!");
		}
	}

}