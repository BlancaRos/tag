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
	public static JPanel panelDataBase = null;	
	public static JPanel panelReport = null;
	public static JPanel panelQueryType = null;

	/**
	 * Metodo principal en el cual se crean los tres paneles de la aplicacion
	 * @throw SQLException sqle
	 */
	public MainInterface() throws SQLException {

		super( "TFG" );
		panelDataBase = PanelUtility.createPanelDataBase(labelDB);	
		panelQueryType = PanelUtility.createPanelQueryType(labelDefaultQuery);
		panelReport = PanelUtility.createPanelReport(labelReport);

		//PanelDataBase y meterlo en JTabbedPane
		String tab1 = Constants.TAB_DB;
		mainInterface.addTab(tab1, null, panelDataBase, Constants.FIRST_PANEL);
		mainInterface.setEnabledAt(0,false);
		//PanelQueryType y meterlo en JTabbedPane
		String tab2 = Constants.TAB_QUERY;
		mainInterface.addTab(tab2, null, panelQueryType, Constants.SECOND_PANEL); 
		mainInterface.setEnabledAt(1,false);

		//PanelReport y meterlo en JTabbedPane
		String tab3 = Constants.TAB_REPORT;
		mainInterface.addTab(tab3, null, panelReport, Constants.THIRD_PANEL); 
		mainInterface.setEnabledAt(2,false);

		getContentPane().add(mainInterface);

		setSize(1000, 500);
		setVisible(true);
	} 
}