package upm.blanca.tfg.optimization.tool.main;

import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import upm.blanca.tfg.optimization.tool.constants.Constants;
import upm.blanca.tfg.optimization.tool.util.PanelUtility;
import upm.blanca.tfg.optimization.tool.util.QueryBean;

public class MainInterface extends JFrame  {

	
	public static QueryBean queryBean = new QueryBean();
	public static JTabbedPane mainInterface = new JTabbedPane();
	public static String labelDB = Constants.LABEL_DB;
	public static String labelReport = Constants.LABEL_REPORT;
	public static String labelDefaultQuery = Constants.LABEL_QUERY;
	public static JPanel panel1 = PanelUtility.createPanelDataBase(labelDB);	
	public static JPanel panel3 = PanelUtility.createPanelReport(labelReport);
	public static JPanel panel2 = PanelUtility.createPanelQueryType(labelDefaultQuery);


	
	boolean valor = false;

	//Configurar GUI
	public MainInterface() throws SQLException {

		super( "TFG" );
	 
		//Crear objeto JTabbedPane 
		//JTabbedPane mainInterface = new JTabbedPane();

		//Establecer panel1 y agregarlo al objeto JTabbedPane
		//String labelDB = Constants.LABEL_DB;
		String tab1 = Constants.TAB_DB;
		//JPanel panel1 = PanelUtility.createPanelDataBase(labelDB);	
		mainInterface.addTab(tab1, null, panel1, Constants.FIRST_PANEL);
		mainInterface.setEnabledAt(0,false);
		//Establecer panel2 y agregarlo al objeto JTabbedPane
		//String labelDefaultQuery = Constants.LABEL_QUERY;
		String tab2 = Constants.TAB_QUERY;
		//JPanel panel2 = PanelUtility.createPanelQueryType(labelDefaultQuery);
		mainInterface.addTab(tab2, null, panel2, Constants.SECOND_PANEL); 
		mainInterface.setEnabledAt(1,false);

		//PanelUtility.addListenerClickPanel(panel2);

		//Establecer panel3 y agregarlo al objeto JTabbedPane
		//String labelReport = Constants.LABEL_REPORT;
		String tab3 = Constants.TAB_REPORT;
		//JPanel panel3 = PanelUtility.createPanelReport(labelReport);
		mainInterface.addTab(tab3, null, panel3, Constants.THIRD_PANEL); 
		mainInterface.setEnabledAt(2,false);

		// agregar objeto JTabbedPane al contenedor
		getContentPane().add(mainInterface);

		setSize(550, 400);
		setVisible(true);
		//panel1.setVisible(true);
		//panel2.setVisible(false);
		//panel3.setVisible(false);
		
		
		//MySQLUtil.ConexionBBDDMySQL();
	} 
	  
	
}
