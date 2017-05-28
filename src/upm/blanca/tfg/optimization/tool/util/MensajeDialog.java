package upm.blanca.tfg.optimization.tool.util;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import upm.blanca.tfg.optimization.tool.constants.Constants;
import upm.blanca.tfg.optimization.tool.csv.CSVUtil;
import upm.blanca.tfg.optimization.tool.db.util.MySQLUtil;
import upm.blanca.tfg.optimization.tool.db.util.OracleDBUtil;
import upm.blanca.tfg.optimization.tool.main.MainInterface;

public class MensajeDialog implements ActionListener{

	public void actionPerformed (ActionEvent e){
		// Aqui el código que queremos que se ejecute cuando tiene lugar la acción.
		// la pulsación del botón, el <INTRO> en el JTextField, elección en el JComboBox, etc.
	}

	public static void MessageDialogue() {
		int eleccion = JOptionPane.showConfirmDialog(null, Constants.CONFIRM_DIALOG, Constants.CONFIRM_DIALOG_TITLE, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		ResultSet resultSet = null;
		
		if( eleccion == JOptionPane.NO_OPTION){
			System.exit(0);
		} else {

			//PARA IR DIRECTAMENTE A LA PESTAÑA FINAL
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
					boolean first = true;
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

					while(resultSet.next())  {
						numRows++;
						for (int i=0; i<numOfColums;i++){
							//Guardo todos los valores sin importar el tipo
							columns[i] = String.valueOf(resultSet.getObject(i+1)).replaceAll(Constants.COMMA,Constants.BLANK);
						}
						//Escribo en el csv
						CSVUtil.writeLine(writer, Arrays.asList(columns), ',');
						first = false;
					}
					MainInterface.queryBean.setNumRows(numRows);
					oracleConnection.close(); 
					System.out.println(writer.toString());
					InputStream bo = new ByteArrayInputStream(writer.toString().getBytes());
					MainInterface.queryBean.setCsv(writer.toString().getBytes());
					//					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					//					byte[] tmp = new byte[4096];
					//					int ret = 0;
					//
					//					while((ret = inputStream.read(tmp)) > 0)
					//					{
					//					    bos.write(tmp, 0, ret);
					//					}
					//
					//					byte[] myArray = bos.toByteArray();
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

	public static void NextStep(){
		//PARA IR DIRECTAMENTE A LA PESTAÑA DE SELECCIONAR CONSULTA
		MainInterface.mainInterface.setSelectedIndex(MainInterface.mainInterface.indexOfComponent(MainInterface.panel2));
	}

	public static void MessageSelectOption() {
		JOptionPane.showMessageDialog(null, Constants.ERROR_DIALOG, Constants.ERROR_DIALOG_TITLE, JOptionPane.ERROR_MESSAGE);
	}

	public static void MessageSqlInfo() {
		JOptionPane.showMessageDialog(null, "Error en la sentencia SQL", "ERROR SQL", JOptionPane.ERROR_MESSAGE);
	}
	
	public static void MessageDBInfo() {
		JOptionPane.showMessageDialog(null, "No es posible conectarse a la BBDD Interna", "ERROR BBDD", JOptionPane.ERROR_MESSAGE);
		//MainInterface.mainInterface.setSelectedIndex(MainInterface.mainInterface.indexOfComponent(MainInterface.panel1));
		try {
			Runtime.getRuntime().exec(Constants.APP);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.exit(0);
	}
}
