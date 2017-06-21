package upm.blanca.tfg.optimization.tool.oracle.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import upm.blanca.tfg.optimization.tool.beans.ExecutionBean;
import upm.blanca.tfg.optimization.tool.beans.QueryBean;
import upm.blanca.tfg.optimization.tool.constants.Constants;
import upm.blanca.tfg.optimization.tool.interfaz.util.MensajeDialog;
import upm.blanca.tfg.optimization.tool.main.MainInterface;

public class OracleDBUtil {

	/**
	 * Método que realiza la conexión con la BBDD externa
	 * @return Connection connection - conexion para la BBDD externa
	 * @throws SQLException sqle
	 */
	public static Connection getConnectionOracle() throws SQLException{

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection connection = null;

		try {
			connection = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521/" + MainInterface.queryBean.getBbddService(), MainInterface.queryBean.getBbddUser(),MainInterface.queryBean.getBbddPass());

		} catch (SQLException e) {
			MensajeDialog.messageDBInfo();
			e.printStackTrace();
		}
		return connection;
	}


	/**
	 * Metodo que realiza la consulta seleccionada por el usuario a la BBDD externa
	 * @param connection - Conexion necesaria para realizar la consulta
	 * @param queryBean - Bean donde esta almacenada toda la informacion necesaria para realizar la consulta
	 * @return ResultSet resultSet - ??
	 * @throw SQLException sqle
	 * @throw IOException ioe
	 */
	public static ResultSet createQueryOracle(Connection connection, QueryBean queryBean) throws SQLException, IOException{
		ResultSet resultSet = null;
		List<ExecutionBean> executionBeanList = new ArrayList<ExecutionBean>();
		
		if (connection != null) {

			Statement stmt;
			try {
				stmt = connection.createStatement();
				String finalQuery = Constants.BLANK;
				
				if (MainInterface.queryBean.getQueryString().contains(Constants.SEMICOLON)) {
					finalQuery = MainInterface.queryBean.getQueryString().replace(Constants.SEMICOLON,Constants.BLANK);
				} else {
					finalQuery = MainInterface.queryBean.getQueryString();
				}

				ExecutionBean executionBean = null;
				double sumTotalTime = 0;
				double avgTotalTime = 0;
				
				for(int numEjecuciones = 0; numEjecuciones < 10; numEjecuciones++){
					executionBean = new ExecutionBean();

					long startTimeNano = System.nanoTime();
					Date currentDateMillis = new Date(System.currentTimeMillis());
					DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
					String dateString  = formatter.format(currentDateMillis);

					executionBean.setQueryDate(dateString);

					String startTime = new SimpleDateFormat("HH:mm:ss").format(new Date(System.currentTimeMillis()));
					executionBean.setQueryStartTime(startTime);

					resultSet=stmt.executeQuery(finalQuery);

					long endTimeNano = System.nanoTime();
					String endTime = new SimpleDateFormat("HH:mm:ss").format(new Date(System.currentTimeMillis()));
					executionBean.setQueryEndTime(endTime);
					long totalTime = endTimeNano - startTimeNano;
					System.out.println(endTimeNano + " - " + startTimeNano + " = " + totalTime);
					executionBean.setTotalTime((int) totalTime);
					if(numEjecuciones > 0 && numEjecuciones < 9){
						sumTotalTime = sumTotalTime + totalTime;
					}
					
					executionBeanList.add(executionBean);
				}
				
				MainInterface.queryBean.setExecutionBeanList(executionBeanList);
				avgTotalTime = sumTotalTime/8;
				MainInterface.queryBean.setAvgTime(String.valueOf(avgTotalTime));

			} catch (SQLException e) {
				MensajeDialog.messageSqlInfo();
				
			}  
			//			connection.close();
		}
		return resultSet;
	}
}