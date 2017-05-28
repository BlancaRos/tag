package upm.blanca.tfg.optimization.tool.db.util;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.Const;

import org.apache.commons.lang.StringEscapeUtils;

import upm.blanca.tfg.optimization.tool.constants.Constants;
import upm.blanca.tfg.optimization.tool.main.MainInterface;
import upm.blanca.tfg.optimization.tool.util.CSVReportBean;
import upm.blanca.tfg.optimization.tool.util.ExecutionBean;
import upm.blanca.tfg.optimization.tool.util.QueryBean;
import upm.blanca.tfg.optimization.tool.util.ReportBean;

import com.mysql.jdbc.PreparedStatement;
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

	public static void populateDB(QueryBean queryBean) throws SQLException{
		//hace conexion y redistribuye
		Connection connection = getConnectionMySQL();
		int idBBDD = insertIntoBBDD(connection, queryBean);
		int idDescription = insertIntoQueryDescription(connection, queryBean);
		int idSQLQuery = insertIntoQuerySQL(connection, queryBean, idBBDD, idDescription);
		insertIntoExecution(connection, queryBean, idSQLQuery);
		connection.close();
	}

	public static int insertIntoBBDD(Connection connection, QueryBean queryBean) throws SQLException{
		int claveGenerada = 0;
		long idResult = 0L;
		String nameBBDD = "";
		String userBBDD = "";
		String passBBDD = "";
		String servicioBBDD = "";

		if(connection != null){

			Statement s = connection.createStatement(); 
			ResultSet rs = s.executeQuery ((Constants.SELECT_BBDD) + queryBean.getBbddService() + (Constants.CLOSE_QUERY));

			//Guardo los valores del resultado de la consulta
			while (rs.next()){ 
				claveGenerada = rs.getInt(1);
				nameBBDD = rs.getString(2);
				userBBDD = rs.getString(3);
				passBBDD = rs.getString(4);
				servicioBBDD = rs.getString(5);

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
	public static int insertIntoQueryDescription(Connection connection, QueryBean queryBean) throws SQLException{
		int claveGenerada = 0;
		String description = "";
		long idResult = 0L;

		if(connection != null){

			Statement s = connection.createStatement(); 
			ResultSet rs = s.executeQuery (Constants.SELECT_DESCRIPTION + queryBean.getQueryDescription() + (Constants.CLOSE_QUERY));

			//Guardo los valores del resultado de la consulta
			while (rs.next()){ 
				claveGenerada = rs.getInt(1);
				description = rs.getString(2);
			}

			if(!description.equals(queryBean.getQueryDescription())){
				PreparedStatement pstm = null;
				String query = Constants.INSERT_DESCRIPTION;

				pstm = (PreparedStatement) connection.prepareStatement(query);
				pstm.setString(1, queryBean.getQueryDescription());
				pstm.setBlob(2, new ByteArrayInputStream(MainInterface.queryBean.getCsv()));
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
	 * 
	 * @param connection
	 * @param queryBean
	 * @param idBBDD
	 * @param idDescription
	 * @return int the primary key
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

			}
			else{
				idResult = claveGenerada;
			}

		}		
		return (int) idResult;
	}
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
	public static List<String> getDescriptions(Connection connection) throws SQLException{

		List<String> lista=new ArrayList<String>();

		if(connection != null){

			java.sql.PreparedStatement consulta1 = connection.prepareStatement(Constants.SELECT_ALL_DESCRIPTION);
			ResultSet result1 = consulta1.executeQuery();
			while(result1.next()){

				String nombre= result1.getString(1);

				//Creas un objeto del tipo que te estas trayendo de la bd
				lista.add(nombre); //agregas ese objeto a la lista
			}
		}
		return lista;
	}

	public static List<String> getBbdd (Connection connection) throws SQLException{

		List<String> lista=new ArrayList<String>();

		if(connection != null){

			java.sql.PreparedStatement consulta1 = connection.prepareStatement(Constants.SELECT_ALL_BBDD_NAME);
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

	public static List<String> getSelectedSqlQuery(String descriptionSelected) throws SQLException {

		Connection connection = MySQLUtil.getConnectionMySQL();
		List<String> lista=new ArrayList<String>();

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
	public static void getSelectedDataBase(String descriptionSelected) throws SQLException {

		String service;
		String user;
		String pass;

		Connection connection = MySQLUtil.getConnectionMySQL();

		if(connection != null){

			java.sql.PreparedStatement consulta1 = connection.prepareStatement(Constants.SELECT_ALL_DB + descriptionSelected + Constants.CLOSE_QUERY);
			ResultSet result1 = consulta1.executeQuery();
			while(result1.next()){
				user = result1.getString(Constants.USER_BBDD);
				pass = result1.getString(Constants.PASS_BBDD);
				service = result1.getString(Constants.SERVICE_BBDD);
				MainInterface.queryBean.setBbddService(service);
				MainInterface.queryBean.setBbddUser(user);
				MainInterface.queryBean.setBbddPass(pass);
			}
		}

	}

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
				//creo bean, guardo valores y lo mando a la lista de beans
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

	public static List<CSVReportBean> getCSVToReport() throws SQLException{
		Connection connection = MySQLUtil.getConnectionMySQL();
		try {
			connection = MySQLUtil.getConnectionMySQL();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		List<CSVReportBean> listaCsvReport=new ArrayList<CSVReportBean>();

		if(connection != null){

			//OBTENER LOS CSV DE LA TABLA DESCRIPCION!!!!!
			java.sql.PreparedStatement consulta1 = connection.prepareStatement(Constants.CSV_VALUES + MainInterface.queryBean.getQueryDescription() + Constants.CLOSE_QUERY);
			ResultSet result1 = consulta1.executeQuery();
			while(result1.next()){
				//creo bean, guardo valores y lo mando a la lista de beans
				CSVReportBean csvReportBean = new CSVReportBean();
				String csvQuery= result1.getString(1);
				csvReportBean.setCsv(csvQuery);
				listaCsvReport.add(csvReportBean);
			}

		}
		return listaCsvReport;

	}
}