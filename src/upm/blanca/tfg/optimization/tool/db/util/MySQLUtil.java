package upm.blanca.tfg.optimization.tool.db.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import upm.blanca.tfg.optimization.tool.util.QueryBean;
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
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/BBDD_interna","root", "root");	

		return connection;

	}
	//	public static void createQueryMySQL(Connection connection) throws SQLException{
	//		Statement stmt = null;
	//
	//		//STEP 4: Execute a query
	//		System.out.println("Creating statement...");
	//		stmt = connection.createStatement();
	//		String sql;
	//		sql = "SELECT Matricula,Color FROM Vehiculos";
	//		ResultSet rs = stmt.executeQuery(sql);
	//
	//		//STEP 5: Extract data from result set
	//		while(rs.next()){
	//			//Retrieve by column name
	//			String id  = rs.getString("Matricula");
	//			String color = rs.getString("Color");
	//
	//			//Display values
	//			System.out.print("ID: " + id);
	//			System.out.print("COLOR: " + color);
	//			System.out.println();
	//		}
	//		//STEP 6: Clean-up environment
	//		rs.close();
	//		stmt.close();
	//		connection.close();
	//
	//		if (connection != null) {
	//			System.out.println("You made it, take control your database now!");
	//		} else {
	//			System.out.println("Failed to make connection!");
	//		}
	//	}

	public static void populateDB(QueryBean queryBean) throws SQLException{
		//hace conexion y redistribuye
		Connection connection = getConnectionMySQL();
		int idBBDD = insertIntoBBDD(connection, queryBean);
		//int idDescription = insertIntoQueryDescription(connection, queryBean);
		//insertIntoQuerySQL(connection, queryBean, idBBDD, idDescription);
		//insertIntoExecution(connection, queryBean);
	}

	public static int insertIntoBBDD(Connection connection, QueryBean queryBean) throws SQLException{
		System.out.println("%%%%% Entro");
		int claveGenerada = 0;
		String nameBBDD = "";
		if(connection != null){

			Statement s = connection.createStatement(); 
			ResultSet rs = s.executeQuery ("SELECT * FROM BBDD WHERE NombreBBDD = 'VEHICULOS'");

			//Guardo los valores del resultado de la consulta
			while (rs.next()) 
			{ 
				//System.out.println ("CREADA ANTERIORMENTE: " + rs.getInt (1) + " " + rs.getString (2)); 
				claveGenerada = rs.getInt(1);
				nameBBDD = rs.getString(2);
				System.out.println("CREADA ANTERIORMENTE: "+ claveGenerada + " " + nameBBDD);
			}

			if(!nameBBDD.equals("VEHICULOS")){
				System.out.println(":::::NO CREADA!!");
				// Realizar INSERT en cada una de las tablas (execution BBDD...) intentar parametrizar lo maximo posbile
				// (nombre BBDD...)
				PreparedStatement pstm = null;
				String query = "INSERT INTO BBDD (NombreBBDD) VALUES(?);";

				pstm = (PreparedStatement) connection.prepareStatement(query);
				pstm.setString(1, queryBean.getBbddName());
				pstm.execute();
			}
			else{
				System.out.println("BBDD ya creada!!!!");
			}

		}
		return claveGenerada;
	}
	public static int insertIntoQueryDescription(Connection connection, QueryBean queryBean) throws SQLException{
		int claveGenerada = 0;
		if(connection != null){

			PreparedStatement prepStat = null;
			Boolean result = false;
			String verificationQuery = null;
			verificationQuery = "SELECT * FROM QueryDescription WHERE Descripcion = '" + queryBean.getQueryDescription() + "'";
			prepStat = (PreparedStatement) connection.prepareStatement(verificationQuery);

			result = prepStat.execute();

			if(!result){
				// Realizar INSERT en cada una de las tablas (execution BBDD...) intentar parametrizar lo maximo posbile
				// (nombre BBDD...)
				PreparedStatement pstm = null;
				String query = "INSERT INTO QueryDescription (Descripcion) VALUES(null,?);";

				pstm = (PreparedStatement) connection.prepareStatement(query, pstm.RETURN_GENERATED_KEYS);
				pstm.setString(1, queryBean.getQueryDescription());
				pstm.execute();

				ResultSet rs = pstm.getGeneratedKeys();
				while (rs.next()) {
					claveGenerada = rs.getInt(1);
					System.out.println("Clave generada DESCRIPTION = " + claveGenerada);
				}
			}
			else{
				System.out.println("Descripcion ya creada!!!!");
			}
		}		
		return claveGenerada;
	}
	public static void insertIntoQuerySQL(Connection connection, QueryBean queryBean, int idBBDD, int idDescription) throws SQLException{
		if(connection != null){
			PreparedStatement prepStat = null;
			Boolean result = false;
			String verificationQuery = null;
			verificationQuery = "SELECT * FROM QuerySQL WHERE query = '"+ queryBean.getQueryString() + "'";
			prepStat = (PreparedStatement) connection.prepareStatement(verificationQuery);

			result = prepStat.execute();

			if(!result){
				PreparedStatement pstm = null;
				String query = "INSERT INTO querySQL (query, idBBDD, idQueryDescription) VALUES(?,?,?);";

				pstm = (PreparedStatement) connection.prepareStatement(query);

				pstm.setString(1, queryBean.getQueryString());
				pstm.setInt(2, idBBDD);
				pstm.setInt(3,idDescription);

				pstm.execute();	
			}
			else{
				System.out.println("QUERY ya creada!!");
			}
		}	
	}
	public static void insertIntoExecution(Connection connection, QueryBean queryBean) throws SQLException{
		if(connection != null){
			PreparedStatement prepStat = null;
			Boolean result = false;
			String verificationQuery = null;
			verificationQuery = "SELECT * FROM BBDD WHERE NombreBBDD = 'VEHICULOS'";
			prepStat = (PreparedStatement) connection.prepareStatement(verificationQuery);

			result = prepStat.execute();

			if(!result){
				PreparedStatement pstm = null;
				String query = "INSERT INTO queryDescription (Descripcion) VALUES(?);";

				pstm = (PreparedStatement) connection.prepareStatement(query);
				pstm.setString(1, queryBean.getQueryString());

				pstm.execute();	
			}
		}	
	}
	public static ArrayList getDescriptions(Connection connection) throws SQLException{

		ArrayList lista=new ArrayList();
		//String[] lista = null;

		if(connection != null){

			//Statement s = connection.createStatement(); 
			//ResultSet rs = s.executeQuery ("SELECT Descripcion FROM QueryDescription");
			int i = 1;

			java.sql.PreparedStatement consulta1 = connection.prepareStatement("SELECT Descripcion FROM QueryDescription");
			ResultSet result1 = consulta1.executeQuery();
			while(result1.next()){

				String nombre= result1.getString(1);

				//Creas un objeto del tipo que te estas trayendo de la bd
				String k=new String(nombre);//le mandas los parametros necesarios al constructor
				lista.add(k); //agregas ese objeto a la lista

			}
		}
		return lista;
	}

}