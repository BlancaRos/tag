package upm.blanca.tfg.optimization.tool.interfaz.util;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import upm.blanca.tfg.optimization.tool.beans.CSVRowBean;
import upm.blanca.tfg.optimization.tool.constants.Constants;
import upm.blanca.tfg.optimization.tool.csv.CSVUtil;
import upm.blanca.tfg.optimization.tool.main.MainInterface;
import upm.blanca.tfg.optimization.tool.mysql.util.MySQLUtil;
import upm.blanca.tfg.optimization.tool.oracle.util.OracleDBUtil;

public class MensajeDialog implements ActionListener{


	public void actionPerformed (ActionEvent e){

	}

	/**
	 * Metodo que al confirmar la accion ejecuta la consulta y genera el CSV
	 */
	public static void messageDialogue() {
		int eleccion = JOptionPane.showConfirmDialog(null, Constants.SENT_QUERY_DIALOG, Constants.CONFIRM_DIALOG_TITLE, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		ResultSet resultSet = null;

		if( eleccion == JOptionPane.NO_OPTION){
			MainInterface.mainInterface.setSelectedIndex(MainInterface.mainInterface.indexOfComponent(MainInterface.panel2));			
		} else {

			// PARA MANTENER SELECCIONADA UNA PESTAÑA 
			MainInterface.mainInterface.setEnabledAt(MainInterface.mainInterface.indexOfComponent(MainInterface.panel3),true);
			MainInterface.mainInterface.setSelectedIndex(MainInterface.mainInterface.indexOfComponent(MainInterface.panel3));
			for(Component jc:MainInterface.panel3.getComponents()){
				if(jc instanceof JLabel && jc.getName().equals("id2_infoSQLQuerySelected")){					
					((JLabel) jc).setText(MainInterface.queryBean.getQueryString());
				}
				if(jc instanceof JLabel && jc.getName().equals("id2_infoDescriptQuerySelected")){					
					((JLabel) jc).setText(MainInterface.queryBean.getQueryDescription());
				}
			}
			try {
				Connection oracleConnection = OracleDBUtil.getConnectionOracle();

				if (oracleConnection != null){
					resultSet = OracleDBUtil.createQueryOracle(oracleConnection,MainInterface.queryBean);

					// Numero de columnas pedidas a iterar
					int numOfColums = resultSet.getMetaData().getColumnCount();
					String [] columns = new String[numOfColums];
					FileWriter writer;
					String csvFile =  Constants.CSV_PATH;
					writer = new FileWriter(csvFile);
					int numRows = 0;

					// Iterar los resultados de la query
					for (int i=0; i<numOfColums;i++){
						columns[i] = resultSet.getMetaData().getColumnName(i+1);
					}

					//Escribo en el csv
					CSVUtil.writeLine(writer, Arrays.asList(columns), ',');
					List<CSVRowBean> allLines =  new ArrayList<CSVRowBean>();
					while(resultSet.next())  {
						numRows++;
						for (int i=0; i<numOfColums;i++){
							//Guardo todos los valores sin importar el tipo
							columns[i] = String.valueOf(resultSet.getObject(i+1)).replaceAll(Constants.COMMA,Constants.BLANK);
						}
						//Escribo en el csv
						CSVRowBean line = CSVUtil.writeLine(writer, Arrays.asList(columns), ',');
						allLines.add(line);
					}

					MainInterface.queryBean.setStringCSV(allLines);
					MainInterface.queryBean.setNumRows(numRows);
					oracleConnection.close(); 
					MainInterface.queryBean.setCsv(writer.toString().getBytes());
					writer.flush();
					writer.close();

					try {
						MySQLUtil.populateDB(MainInterface.queryBean);
					} catch (SQLException e) {			
						e.printStackTrace();
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * Metodo para avanzar a la siguiente pestaña
	 */
	public static void nextStep(){
		MainInterface.mainInterface.setSelectedIndex(MainInterface.mainInterface.indexOfComponent(MainInterface.panel2));
	}

	/**
	 * Metodo que muestra un error en el cual se indica que se debe seleccionar una opción
	 */
	public static void messageSelectOption() {
		JOptionPane.showMessageDialog(null, Constants.ERROR_DIALOG, Constants.ERROR_DIALOG_TITLE, JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Metodo que muestra un error en el cual se indica que la sentencia SQL es incorrecta
	 */
	public static void messageSqlInfo() {
		JOptionPane.showMessageDialog(null, Constants.ERROR_SQL, Constants.ERROR_SQL_TITLE, JOptionPane.ERROR_MESSAGE);
		Util.resetApp();
	}

	/**
	 * Metodo que muestra una ventana que indica que la consulta ejecutada no devuelve nada
	 */
	public static void messageEmptyQuery() {
		JOptionPane.showMessageDialog(null, Constants.EMPTY_QUERY, Constants.EMPTY_QUERY_TITLE, JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Metodo que muestra un error en el cual se indica que no se puede conectar a la BBDD externa
	 */
	public static void messageDBInfo() {
		JOptionPane.showMessageDialog(null, Constants.ERROR_BBDD, Constants.ERROR_BBDD_TITLE, JOptionPane.ERROR_MESSAGE);
		Util.resetApp();
	}
}