package upm.blanca.tfg.optimization.tool.mysql.util;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;

import upm.blanca.tfg.optimization.tool.beans.CSVReportBean;
import upm.blanca.tfg.optimization.tool.beans.ExecutionBean;
import upm.blanca.tfg.optimization.tool.beans.QueryBean;
import upm.blanca.tfg.optimization.tool.beans.ReportBean;
import upm.blanca.tfg.optimization.tool.constants.Constants;
import upm.blanca.tfg.optimization.tool.main.MainInterface;

import com.mysql.jdbc.PreparedStatement;

public class MySQLUtil {

	/**
	 * Metodo para la conexion con la BBDD interna
	 * @return Connection connection - conexion necesaria para conectar con la BBDD interna
	 * @throw SQLException sqle
	 */
	public static Connection getConnectionMySQL() throws SQLException{

		System.out.println("-------- MySQL JDBC Connection Testing ------------");

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}

		Connection connection = null;
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/BBDD_interna","root", "root");	

		return connection;
	}

	/**
	 * Metodo principal encargado de llamar a otros para almacenar informacion en la BBDD interna
	 * @param queryBean - bean en el cual se encuentra la informacion necesaria para almacenar
	 * @throw SQLException sqle
	 */
	public static void populateDB(QueryBean queryBean) throws SQLException{

		Connection connection = getConnectionMySQL();
		int idBBDD = insertIntoBBDD(connection, queryBean);
		int idDescription = insertIntoQueryDescription(connection, queryBean);
		int idSQLQuery = insertIntoQuerySQL(connection, queryBean, idBBDD, idDescription);
		insertIntoExecution(connection, queryBean, idSQLQuery);

		connection.close();
	}

	/**
	 * Metodo que inserta informacion concreta en la tabla BBDD de la BBDD interna
	 * @param connection - conexion necesaria para conectarse a la BBDD interna
	 * @param queryBean - bean en el cual se encuentra la informacion necesaria para almacenar
	 * @return int idResult - id de la BBDD asignado
	 * @throw SQLException sqle
	 */
	public static int insertIntoBBDD(Connection connection, QueryBean queryBean) throws SQLException{
		int claveGenerada = 0;
		long idResult = 0L;
		String nameBBDD = "";

		if(connection != null){

			Statement s = connection.createStatement(); 
			ResultSet rs = s.executeQuery ((Constants.SELECT_BBDD) + queryBean.getBbddService() + (Constants.CLOSE_QUERY));

			while (rs.next()){ 
				claveGenerada = rs.getInt(1);
				nameBBDD = rs.getString(2);
			}

			if(nameBBDD.equals("")){
				PreparedStatement pstm = null;
				String query = Constants.INSERT_BBDD;

				pstm = (PreparedStatement) connection.prepareStatement(query);
				pstm.setString(1, queryBean.getBbddName());
				pstm.setString(2, queryBean.getBbddUser());
				pstm.setString(3, queryBean.getBbddPass());
				pstm.setString(4, queryBean.getBbddService());
				pstm.execute();
				idResult = pstm.getLastInsertID();
			}
			else{
				idResult = claveGenerada;
			}
		}
		return (int) idResult;
	}

	/**
	 * Metodo que inserta informacion concreta en la tabla QueryDescription de la BBDD interna
	 * @param connection - conexion necesaria para conectarse a la BBDD interna
	 * @param queryBean - bean en el cual se encuentra la informacion necesaria para almacenar
	 * @return int idResult - id de la descripcion generada
	 * @throw SQLException sqle
	 */
	public static int insertIntoQueryDescription(Connection connection, QueryBean queryBean) throws SQLException{
		int claveGenerada = 0;
		String description = "";
		long idResult = 0L;

		if(connection != null){

			Statement s = connection.createStatement(); 
			ResultSet rs = s.executeQuery (Constants.SELECT_DESCRIPTION + queryBean.getQueryDescription() + (Constants.CLOSE_QUERY));

			while (rs.next()){ 
				claveGenerada = rs.getInt(1);
				description = rs.getString(2);
			}

			if(!description.equals(queryBean.getQueryDescription())){
				PreparedStatement pstm = null;
				String query = Constants.INSERT_DESCRIPTION;

				pstm = (PreparedStatement) connection.prepareStatement(query);
				pstm.setInt(1, queryBean.getIdBBDD());
				pstm.setString(2, queryBean.getQueryDescription());
				pstm.setBlob(3, new ByteArrayInputStream(MainInterface.queryBean.getCsv()));
				pstm.execute();
				idResult = pstm.getLastInsertID();

			}
			else{
				idResult = claveGenerada;
			}
		}		
		return (int) idResult;
	}

	/**
	 * Metodo que inserta informacion concreta en la tabla QuerySQL de la BBDD interna
	 * @param connection - conexion necesaria para conectarse a la BBDD interna
	 * @param queryBean - bean en el cual se encuentra la informacion necesaria para almacenar
	 * @param idBBDD - id de la BBDD
	 * @param idDescription - id de la descripcion
	 * @return int idResult - id de la sentencia generada
	 * @throws SQLException sqle
	 */
	public static int insertIntoQuerySQL(Connection connection, QueryBean queryBean, int idBBDD, int idDescription) throws SQLException{
		int claveGenerada = 0;
		String querySQL = "";
		long idResult = 0L;

		if(connection != null){

			Statement s = connection.createStatement(); 
			ResultSet rs = s.executeQuery (Constants.SELECT_SQL + StringEscapeUtils.escapeSql(queryBean.getQueryString()) + (Constants.CLOSE_QUERY));

			//Guardo los valores del resultado de la consulta
			while (rs.next()){ 
				claveGenerada = rs.getInt(1);
				querySQL = rs.getString(2);
			}

			if(!querySQL.equals(queryBean.getQueryString())){
				PreparedStatement pstm = null;
				String query = Constants.INSERT_SQL;

				pstm = (PreparedStatement) connection.prepareStatement(query);
				pstm.setString(1, queryBean.getQueryString());
				pstm.setInt(2, idBBDD);
				pstm.setInt(3, idDescription);
				pstm.setString(4, queryBean.getAvgTime());
				pstm.setInt(5, queryBean.getNumRows());
				pstm.execute();
				idResult = pstm.getLastInsertID();
			}else{
				idResult = claveGenerada;
			}
		}		
		return (int) idResult;
	}

	/**
	 * Metodo que inserta informacion concreta en la tabla Execution de la BBDD interna
	 * @param connection - conexion necesaria para conectarse a la BBDD interna
	 * @param queryBean - bean en el cual se encuentra la informacion necesaria para almacenar
	 * @param idSQLQuery - id de la sentencia
	 * @return int idResult - id de la ejecucion
	 * @throws SQLException sqle
	 */
	public static void insertIntoExecution(Connection connection, QueryBean queryBean, int idSQLQuery) throws SQLException{

		if(connection != null){

			PreparedStatement pstm = null;
			String query = Constants.INSERT_EXECUTION;

			for(ExecutionBean eb : queryBean.getExecutionBeanList()){
				pstm = (PreparedStatement) connection.prepareStatement(query);
				pstm.setString(1, eb.getQueryDate());
				pstm.setString(2, eb.getQueryStartTime());
				pstm.setString(3, eb.getQueryEndTime());
				pstm.setInt(4, (eb.getTotalTime()));
				pstm.setInt(5, idSQLQuery);
				pstm.setInt(6, queryBean.getNumRows());
				pstm.execute();
			}
		}		
	}

	/**
	 * Metodo que obtiene todas las descripciones almacenadas en la BBDD interna
	 * @param connection - conexion necesaria para conectarse a la BBDD interna
	 * @return List<String> lista - lista de las descripciones almacenadas
	 * @throws SQLException sqle
	 */
	public static List<String> getDescriptions(Connection connection) throws SQLException{

		List<String> lista = new ArrayList<String>();

		if(connection != null){

			java.sql.PreparedStatement consulta1 = connection.prepareStatement(Constants.SELECT_ALL_DESCRIPTION + MainInterface.queryBean.getIdBBDD() + Constants.CLOSE_QUERY);
			ResultSet result1 = consulta1.executeQuery();
System.out.println(Constants.SELECT_ALL_DESCRIPTION + MainInterface.queryBean.getIdBBDD() + Constants.CLOSE_QUERY);
			while(result1.next()){
				String nombre= result1.getString(1);
				lista.add(nombre); 
			}
		}
		return lista;
	}

	/**
	 * Metodo que obtiene todas las BBDD almacenadas en la BBDD interna
	 * @param connection - conexion necesaria para conectarse a la BBDD interna
	 * @return List<String> lista - lista de las BBDD almacenadas
	 * @throws SQLException sqle
	 */
	public static List<String> getBbdd (Connection connection) throws SQLException{

		List<String> lista=new ArrayList<String>();

		if(connection != null){

			java.sql.PreparedStatement consulta1 = connection.prepareStatement(Constants.SELECT_ALL_BBDD_NAME);
			ResultSet result1 = consulta1.executeQuery();

			while(result1.next()){
				String nombre= result1.getString(1);
				String k = new String(nombre);
				lista.add(k);
			}
		}
		return lista;
	}

	/**
	 * Metodo que obtiene la sentencia SQL seleccionada por el usuario
	 * @param descriptionSelected - descripcion elegida por el usuario
	 * @return List<String> lista - lista de las sentencias almacenadas
	 * @throws SQLException sqle
	 */
	public static List<String> getSelectedSqlQuery(String descriptionSelected) throws SQLException {

		Connection connection = MySQLUtil.getConnectionMySQL();
		List<String> lista = new ArrayList<String>();

		if(connection != null){

			java.sql.PreparedStatement consulta1 = connection.prepareStatement(Constants.SELECT_SQL_FROM_DESCRIPTION + descriptionSelected + Constants.CLOSE_QUERY_WITH_SUBQUERY);
			ResultSet result1 = consulta1.executeQuery();

			while(result1.next()){
				String querySql= result1.getString(1);
				lista.add(querySql);
			}
		}
		return lista;
	}

	/**
	 * Metodo que obtiene todas la BBDD seleccionada por el usuario
	 * @param descriptionSelected - descripcion elegida por el usuario
	 * @throws SQLException sqle
	 */
	public static void getSelectedDataBase(String descriptionSelected) throws SQLException {

		String service;
		String user;
		String pass;
		int idDB = 0;
		Connection connection = MySQLUtil.getConnectionMySQL();

		if(connection != null){

			java.sql.PreparedStatement consulta1 = connection.prepareStatement(Constants.SELECT_ALL_DB + descriptionSelected + Constants.CLOSE_QUERY);
			ResultSet result1 = consulta1.executeQuery();

			while(result1.next()){
				user = result1.getString(Constants.USER_BBDD);
				pass = result1.getString(Constants.PASS_BBDD);
				service = result1.getString(Constants.SERVICE_BBDD);
				idDB = result1.getInt(Constants.IDDB);
				MainInterface.queryBean.setBbddService(service);
				MainInterface.queryBean.setBbddUser(user);
				MainInterface.queryBean.setBbddPass(pass);
				MainInterface.queryBean.setIdBBDD(idDB);
				
			}
		}
	}

	/**
	 * Metodo que obtiene el informe de la consulta realizada
	 * @param descriptionSelected - descripcion elegida por el usuario
	 * @return List<ReportBean> listaReport - Lista con los beans para generar el PDF
	 * @throws SQLException sqle
	 */
	public static List<ReportBean> getQueryToReport() throws SQLException{
		Connection connection = MySQLUtil.getConnectionMySQL();
		try {
			connection = MySQLUtil.getConnectionMySQL();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		List<ReportBean> listaReport=new ArrayList<ReportBean>();

		if(connection != null){

			java.sql.PreparedStatement consulta1 = connection.prepareStatement(Constants.REPORT_VALUES + MainInterface.queryBean.getQueryDescription() + Constants.CLOSE_QUERY_WITH_SUBQUERY);
			ResultSet result1 = consulta1.executeQuery();

			while(result1.next()){
				ReportBean reportBean = new ReportBean();
				String querySql= result1.getString(1);
				String bbdd= result1.getString(2);
				String avgTime= result1.getString(3);
				String rows= result1.getString(4);
				reportBean.setQuery(querySql);
				reportBean.setBbdd(bbdd);
				reportBean.setAvgTime(avgTime);
				reportBean.setRows(rows);
				listaReport.add(reportBean);
			}
		}
		return listaReport;
	}

	/**
	 * Metodo que obtiene el resultado de la consulta realizada
	 * @param descriptionSelected - descripcion elegida por el usuario
	 * @return List<CSVReportBean> listaCsvReport - Lista con los beans para generar el PDF
	 * @throws SQLException sqle
	 */
	public static List<CSVReportBean> getCSVToReport() throws SQLException{

		Connection connection = MySQLUtil.getConnectionMySQL();
		try {
			connection = MySQLUtil.getConnectionMySQL();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		List<CSVReportBean> listaCsvReport=new ArrayList<CSVReportBean>();

		if(connection != null){

			java.sql.PreparedStatement consulta1 = connection.prepareStatement(Constants.CSV_VALUES + MainInterface.queryBean.getQueryDescription() + Constants.CLOSE_QUERY);
			ResultSet result1 = consulta1.executeQuery();

			while(result1.next()){
				CSVReportBean csvReportBean = new CSVReportBean();
				String csvQuery= result1.getString(1);
				csvReportBean.setCsv(csvQuery);
				listaCsvReport.add(csvReportBean);
			}
		}
		return listaCsvReport;
	}
}