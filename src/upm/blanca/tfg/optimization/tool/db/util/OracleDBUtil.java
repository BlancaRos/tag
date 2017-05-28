package upm.blanca.tfg.optimization.tool.db.util;

import java.awt.Component;
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

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import upm.blanca.tfg.optimization.tool.constants.Constants;
import upm.blanca.tfg.optimization.tool.main.MainInterface;
import upm.blanca.tfg.optimization.tool.util.ExecutionBean;
import upm.blanca.tfg.optimization.tool.util.MensajeDialog;
import upm.blanca.tfg.optimization.tool.util.QueryBean;
import upm.blanca.tfg.optimization.tool.util.Util;

public class OracleDBUtil {

	private static JLabel labelLoading;
	private static JPanel panel;

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

		for(Component jc:MainInterface.panel2.getComponents()){
			if(jc instanceof JLabel && jc.getName().equals("id2_loading")){	
				System.out.println("FOTI");
				((JLabel) jc).setVisible(true);
			}
		}

		try {
			//			Icon icon = new ImageIcon(ImageIO.read(OracleDBUtil.class.getClassLoader().getResourceAsStream("loading.gif")));
			//			JLabel labelLoading = new JLabel("holaaaa");
			//			labelLoading.setBounds(460, 340, 50, 50);
			//			labelLoading.setVisible(true);
			//			JPanel panelito = Util.searchPane(MainInterface.mainInterface, "panel2");
			//			panelito.add(labelLoading);
			//			MainInterface.panel2.add(labelLoading);

			//			System.out.println("EEIII");
			//			labelLoading = new JLabel("Loading...");
			//			labelLoading.setBounds(200, 340, 50, 50);
			//			labelLoading.setVisible(true);
			//			//JPanel panel = Util.searchPane(MainInterface.mainInterface, "panel2");
			//			panel = Util.searchPane(MainInterface.mainInterface, "panel2");
			//			panel.add(labelLoading);




			//JPanel panelito = Util.searchPane(MainInterface.mainInterface, Constants.TAB_QUERY);

			//JTabbedPane panelito = Util.searchPane(MainInterface.mainInterface, "panel2");

			/////////////// 2
			//			JLabel img = new JLabel(); 
			//			ImageIcon image = new ImageIcon("/Users/admin/Desktop/TFG/Imágenes/cargando.gif"); 
			//			//Propiedades de la etiqueta 
			//			img.setIcon(image); 
			//			img.setSize(100,100); 
			//			img.setLocation(460,80); 
			//			img.setVisible(true); 
			//			
			//			//panelito.add(img);
			//			MainInterface.mainInterface.add(img);

			///////////////// 3
			//			for(Component jc:MainInterface.panel2.getComponents()){
			//				if(jc instanceof JLabel && jc.getName().equals("id2_loading")){	
			//					System.out.println("FOTI");
			//					((JLabel) jc).setVisible(true);
			//				}
			//			}


			//connection = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521/orcl", "hr","oracle");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521/" + MainInterface.queryBean.getBbddService(), MainInterface.queryBean.getBbddUser(),MainInterface.queryBean.getBbddPass());

		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			MensajeDialog.MessageDBInfo();
			e.printStackTrace();
		}
		return connection;
	}

	public static ResultSet createQueryOracle(Connection connection, QueryBean queryBean) throws SQLException, IOException{
		ResultSet resultSet = null;
		List<ExecutionBean> executionBeanList = new ArrayList<ExecutionBean>();
		
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

				ExecutionBean executionBean = null;
				double sumTotalTime = 0;
				double avgTotalTime = 0;
				
				for(int numEjecuciones = 0; numEjecuciones < 10; numEjecuciones++){
					executionBean = new ExecutionBean();

					long startTimeMillis = System.nanoTime();
					Date currentDateMillis = new Date(startTimeMillis);
					DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
					String dateString  = formatter.format(currentDateMillis);

					executionBean.setQueryDate(dateString);

					String startTime = new SimpleDateFormat("HH:mm:ss").format(new Date(startTimeMillis));
					executionBean.setQueryStartTime(startTime);

					resultSet=stmt.executeQuery(finalQuery);

					long endTimeMillis = System.nanoTime();
					String endTime = new SimpleDateFormat("HH:mm:ss").format(new Date(endTimeMillis));
					executionBean.setQueryEndTime(endTime);
					long totalTime = endTimeMillis - startTimeMillis;
					System.out.println(endTimeMillis + " - " + startTimeMillis + " = " + totalTime);
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
				MensajeDialog.MessageSqlInfo();
				e.printStackTrace();
			}  
			//			connection.close();
		} else {				
			System.out.println("Failed to make connection!");
		}
		return resultSet;
	}
}