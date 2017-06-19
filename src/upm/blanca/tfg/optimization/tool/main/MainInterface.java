package upm.blanca.tfg.optimization.tool.main;

import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import upm.blanca.tfg.optimization.tool.beans.QueryBean;
import upm.blanca.tfg.optimization.tool.constants.Constants;
import upm.blanca.tfg.optimization.tool.interfaz.util.PanelUtility;

public class MainInterface extends JFrame  {

	private static final long serialVersionUID = -809150699758926335L;
	public static QueryBean queryBean = new QueryBean();
	public static JTabbedPane mainInterface = new JTabbedPane();
	public static String labelDB = Constants.LABEL_DB;
	public static String labelReport = Constants.LABEL_SQL_REPORT;
	public static String labelDefaultQuery = Constants.LABEL_QUERY;
	public static JPanel panel1 = null;	
	public static JPanel panel3 = null;
	public static JPanel panel2 = null;

	/**
	 * Metodo principal en el cual se crean los tres paneles de la aplicacion
	 * @throw SQLException sqle
	 */
	public MainInterface() throws SQLException {

		super( "TFG" );
		panel1 = PanelUtility.createPanelDataBase(labelDB);	
		panel2 = PanelUtility.createPanelQueryType(labelDefaultQuery);
		panel3 = PanelUtility.createPanelReport(labelReport);

		//Panel1 y meterlo en JTabbedPane
		String tab1 = Constants.TAB_DB;
		mainInterface.addTab(tab1, null, panel1, Constants.FIRST_PANEL);
		mainInterface.setEnabledAt(0,false);
		//Panel2 y meterlo en JTabbedPane
		String tab2 = Constants.TAB_QUERY;
		mainInterface.addTab(tab2, null, panel2, Constants.SECOND_PANEL); 
		mainInterface.setEnabledAt(1,false);

		//Panel3 y meterlo en JTabbedPane
		String tab3 = Constants.TAB_REPORT;
		mainInterface.addTab(tab3, null, panel3, Constants.THIRD_PANEL); 
		mainInterface.setEnabledAt(2,false);

		getContentPane().add(mainInterface);

		setSize(1000, 500);
		setVisible(true);
	} 
}