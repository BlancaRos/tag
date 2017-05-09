package upm.blanca.tfg.optimization.tool.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import upm.blanca.tfg.optimization.tool.constants.Constants;
import upm.blanca.tfg.optimization.tool.db.util.MySQLUtil;
import upm.blanca.tfg.optimization.tool.main.MainInterface;

public class ComboBoxUtility {

	private static JTextField desiredQuery;
	private static JTextField descriptionQuery;
	private static JTextField modifySQLQuery;

	private static JLabel labelDesignQuery;
	private static JLabel labelQueryDescription;
	private static JLabel labelInfoSQLQuery;
	private static JLabel labelInfoTextQuery;
	private static JLabel labelSQLSelected;
	private static JLabel labelTextSelected;
	
	private static JLabel labelModifyDescriptionText;
	private static JLabel labelModifySQLQuery;

	private static JButton showContent; 
	private static JButton nextStep;
	private static JButton addQuery;
	private static JButton sentQueryButton; 
	private static JButton resetButton; 

	public static String sqlQuery = "";
	public static String textQuery = "";
	public static String message;
	

	public static void addListenerToDropDown(JComboBox dropDown, JPanel panel){
		dropDown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				JComboBox<String> typeOfAction = (JComboBox<String>) event.getSource();
				String actionSelected = (String) typeOfAction.getSelectedItem();

				if (actionSelected.equals("Modificar consulta creada")) {
					
					//Si se ha seleccionado "Consulta nueva" antes, eliminar el panel
					if(labelQueryDescription != null)
						panel.remove(labelQueryDescription);
					if(descriptionQuery != null)
						panel.remove(descriptionQuery);
					if(labelDesignQuery != null)
						panel.remove(labelDesignQuery);
					if(desiredQuery != null)
						panel.remove(desiredQuery);
					if(showContent != null)
						panel.remove(showContent);
					if(nextStep != null)
						panel.remove(nextStep);	
					try {
						textQuery = ModifyQuery(panel);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}else if (actionSelected.equals("Consulta nueva")) {

					//Si se ha seleccionado "Modificar consulta creada" antes, eliminar el panel
					if(labelModifyDescriptionText != null)
						panel.remove(labelModifyDescriptionText);
					if(labelModifySQLQuery != null)
						panel.remove(labelModifySQLQuery);
					if(modifySQLQuery != null)
						panel.remove(modifySQLQuery);
					if(nextStep != null)
						panel.remove(nextStep);	
					Util.removeComponentFromPanel(MainInterface.panel2, "id2_descriptionQuery", "id2_scrollPaneSqlQueries");

					sqlQuery = DesignQuery(panel);
				}else{
					MensajeDialog.MessageSelectOption();
				}
			}
		});
	}

	//CONSULTA NUEVA
	public static String DesignQuery(JPanel panel){
		panel.setLayout(null);

		labelQueryDescription = new JLabel();
		labelQueryDescription.setName("id2_descriptionText");
		labelQueryDescription.setText(Constants.DESCRIPTION_TEXT);
		labelQueryDescription.setBounds(50, 60, 400, 10);
		
		descriptionQuery = new JTextField();
		descriptionQuery.setBounds(50, 100, 400, 80);
		
		labelDesignQuery = new JLabel();
		labelDesignQuery.setName("id2_sqlText");
		labelDesignQuery.setText(Constants.SQL_TEXT);
		labelDesignQuery.setBounds(50, 200, 400, 10);

		desiredQuery = new JTextField();
		desiredQuery.setBounds(50, 240, 400, 80);

		nextStep = new JButton();
		nextStep.setText(Constants.NEXT_BUTTON);
		nextStep.setBounds(400, 350, 100, 30);
		//OBTENER EL CONTENIDO DE LA CAJA Y GUARDARLO EN UNA VARIABLE
		nextStep.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				sqlQuery = desiredQuery.getText(); 
				textQuery = descriptionQuery.getText();

				//GUARDO LA CONSULTA A REALIZAR (texto y sql)
				MainInterface.queryBean.setQueryString(sqlQuery);
				MainInterface.queryBean.setQueryDescription(textQuery);
				
				MensajeDialog.MessageDialogue();
			}
		});

		panel.add(labelQueryDescription);
		panel.add(descriptionQuery);
		panel.add(labelDesignQuery);
		panel.add(desiredQuery);
		panel.add(nextStep);
		//Añadir nuevos componentes al panel
		panel.paintAll(panel.getGraphics());
		return sqlQuery;
	}

	//MODIFICAR CONSULTA
	public static String ModifyQuery(JPanel panel) throws SQLException{
		panel.setLayout(null);

		labelModifyDescriptionText = new JLabel();
		labelModifyDescriptionText.setName("id2_descriptionModify");
		labelModifyDescriptionText.setText(Constants.MODIFY_DESCRIPTION_TEXT);
		labelModifyDescriptionText.setBounds(50, 60, 400, 10);
		
		Connection connection = MySQLUtil.getConnectionMySQL();
		
		List<String> result = MySQLUtil.getDescriptions(connection);
		DescriptionQueries.selectDescriptionQuery(result);
		
		labelModifySQLQuery = new JLabel();
		labelModifySQLQuery.setName("id2_queryModify");
		labelModifySQLQuery.setText(Constants.MODIFY_SQL_TEXT);
		labelModifySQLQuery.setBounds(50, 160, 400, 10);

		nextStep = new JButton();
		nextStep.setText(Constants.NEXT_BUTTON);
		nextStep.setBounds(350, 320, 100, 30);
		//OBTENER EL CONTENIDO DE LA CAJA Y GUARDARLO EN UNA VARIABLE
		nextStep.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//Busco el scrollPane y añado en el bean la consulta seleccionada
				String selected = Util.searchScrollPaneInfo(MainInterface.panel2, "id2_scrollPaneSqlQueries");
				MainInterface.queryBean.setQueryString(selected);
				MainInterface.mainInterface.setEnabledAt(MainInterface.mainInterface.indexOfComponent(MainInterface.panel3),true);
				MainInterface.mainInterface.setSelectedIndex(MainInterface.mainInterface.indexOfComponent(MainInterface.panel3));
				Util.setLabelsInfoPanel3(MainInterface.panel3, "id2_infoSQLQuerySelected", "id2_infoDescriptQuerySelected");
			}
		});

		addQuery = new JButton();
		addQuery.setText(Constants.ADD_BUTTON);
		addQuery.setBounds(150, 320, 100, 30);

		addQuery.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				WindowUtility.createWindowAddQuery();
			}
		});
		
		panel.add(labelModifyDescriptionText);
		panel.add(labelModifySQLQuery);
		panel.add(addQuery);
		panel.add(nextStep);
		//Añadir nuevos componentes al panel
		panel.paintAll(panel.getGraphics());
		return sqlQuery;
	}
	
	public static JPanel ReportQuery(){
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		labelInfoTextQuery = new JLabel();
		labelInfoTextQuery.setText(Constants.LABEL_DESCRIPTION_REPORT);
		labelInfoTextQuery.setBounds(10, 10, 400, 30);
		labelInfoTextQuery.setName("id2_infoDescriptQuery");
		
		labelTextSelected = new JLabel();
		labelTextSelected.setName("id2_infoDescriptQuerySelected");
		labelTextSelected.setText("Aquí vendrá la descripción seleccionada");
		labelTextSelected.setBounds(30, 40, 400, 30);
		
		labelInfoSQLQuery = new JLabel();
		labelInfoSQLQuery.setText(Constants.LABEL_SQL_REPORT);
		labelInfoSQLQuery.setBounds(10, 150, 400, 30);
		labelInfoSQLQuery.setName("id2_infoSQLQuery");     		
		
		labelSQLSelected = new JLabel();
		labelSQLSelected.setName("id2_infoSQLQuerySelected");
		labelSQLSelected.setText("Aquí vendrá el SQL seleccionado");
		labelSQLSelected.setBounds(30, 190, 400, 30);
		
        sentQueryButton = new JButton();
		sentQueryButton.setText(Constants.SENT_QUERY);
		sentQueryButton.setBounds(100, 360, 200, 30);
        
		sentQueryButton.addActionListener(new ActionListener(){
			List<ReportBean> reportBean = new ArrayList<ReportBean>();

			public void actionPerformed(ActionEvent e) {

				List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
			     Map<String, Object> parameters = new HashMap<String, Object>();
			        
			          parameters.put("description", MainInterface.queryBean.getQueryDescription());
			          
//			          ReportBean obj1 = new ReportBean();
//			          ReportBean obj2 = new ReportBean();
//			          ReportBean obj3 = new ReportBean();
//			          obj1.setQuery("Select1");
//			          obj1.setAvgTime("1.1");
//			          obj1.setRows("30");
//			          obj2.setQuery("Select2");
//			          obj2.setAvgTime("2.1");
//			          obj2.setRows("40");
//			          obj3.setQuery("Select3");
//			          obj3.setAvgTime("3.1");
//			          obj3.setRows("50");
//			          reportBean.add(obj1);
//			          reportBean.add(obj2);
//			          reportBean.add(obj3);

			          InputStream template = this.getClass().getClassLoader().getSystemResourceAsStream("Informe.jrxml");
			          JasperReport jReport;
					try {
						jReport = JasperCompileManager.compileReport(template);
						//JasperPrint jPrint = JasperFillManager.fillReport(jReport, parameters, new JREmptyDataSource());
				        //jasperPrintList.add(jPrint);
						List<ReportBean> listaReport = new ArrayList<ReportBean>();
						listaReport = MySQLUtil.getQueryToReport();
						JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(listaReport);
					    JasperPrint jPrint = JasperFillManager.fillReport(jReport, parameters, datasource);
						JasperExportManager.exportReportToPdfFile(jPrint, "/Users/admin/Desktop/TFG/Reports/pdf1.pdf");
						JasperViewer.viewReport(jPrint, false);
					} catch (JRException | SQLException e1) {
						e1.printStackTrace();
					}
			         
			}
		});
		
		resetButton = new JButton();
		resetButton.setText(Constants.RESET_BUTTON);
		resetButton.setBounds(280, 360, 200, 30);
	
		resetButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					Runtime.getRuntime().exec("java -jar miapp.jar");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				System.exit(0);
			}
		});
		
		panel.add(labelInfoTextQuery);
		panel.add(labelTextSelected);
		panel.add(labelInfoSQLQuery);
		panel.add(labelSQLSelected);
		panel.add(sentQueryButton);
		panel.add(resetButton);	
		
		return panel;
	}
}
