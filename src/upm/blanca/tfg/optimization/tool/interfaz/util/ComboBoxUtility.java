package upm.blanca.tfg.optimization.tool.interfaz.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import upm.blanca.tfg.optimization.tool.beans.CSVRowBean;
import upm.blanca.tfg.optimization.tool.beans.ReportBean;
import upm.blanca.tfg.optimization.tool.constants.Constants;
import upm.blanca.tfg.optimization.tool.main.MainInterface;
import upm.blanca.tfg.optimization.tool.mysql.util.MySQLUtil;

public class ComboBoxUtility {

	private static JTextArea desiredQuery;
	private static JTextArea descriptionQuery;
	private static JTextArea modifySQLQuery;

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
	private static JButton showReportButton; 
	private static JButton resetButton; 
	private static JButton showCsvButton;

	public static String sqlQuery = "";
	public static String textQuery = "";


	/**
	 * Metodo para limpiar el panel tras cambiar la opcion de tip de consulta.
	 * @param dropdown - desplegable del tipo de consulta
	 * @param panel - Panel a modificar
	 */
	@SuppressWarnings("rawtypes")
	public static void  cleanPanelQueryTypeListener(JComboBox dropDown, JPanel panel){
		dropDown.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
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
						textQuery = modifyQuery(panel);
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
					if(addQuery != null)
						panel.remove(addQuery);	
					Util.removeComponentFromPanel(MainInterface.panel2, "id2_descriptionSelectedQuery", "id2_scrollPaneSqlQueries");

					sqlQuery = designQuery(panel);
				}else{
					MensajeDialog.messageSelectOption();
				}
			}
		});
	}


	/**
	 * Metodo para realizar una nueva consulta
	 * @param panel - Panel a diseñar
	 * @return String sqlQuery sentencia diseñada
	 */
	public static String designQuery(JPanel panel) {
		panel.setLayout(null);


		labelQueryDescription = new JLabel();
		labelQueryDescription.setName("id2_descriptionText");
		labelQueryDescription.setText(Constants.DESCRIPTION_TEXT);
		labelQueryDescription.setBounds(55, 60, 400, 15);

		descriptionQuery = new JTextArea();
		descriptionQuery.setBounds(50, 90, 400, 80);
		descriptionQuery.setForeground(new Color(27,85,131));

		labelDesignQuery = new JLabel();
		labelDesignQuery.setName("id2_sqlText");
		labelDesignQuery.setText(Constants.SQL_TEXT);
		labelDesignQuery.setBounds(55, 200, 400, 15);

		desiredQuery = new JTextArea();
		desiredQuery.setBounds(50, 230, 400, 80);
		desiredQuery.setForeground(new Color(27,85,131));

		nextStep = new JButton();
		nextStep.setText(Constants.NEXT_BUTTON);
		nextStep.setBounds(351, 340, 100, 50);
		//OBTENER EL CONTENIDO DE LA CAJA Y GUARDARLO EN UNA VARIABLE
		nextStep.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				sqlQuery = desiredQuery.getText(); 
				textQuery = descriptionQuery.getText();

				MainInterface.queryBean.setQueryString(sqlQuery);
				MainInterface.queryBean.setQueryDescription(textQuery);

				MensajeDialog.messageDialogue();
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


	/**
	 * Metodo para modificar una consulta ya existente
	 * @param panel - Panel a diseñar
	 * @return String sqlQuery sentencia sql
	 * @throws SQLException sqle
	 */
	public static String modifyQuery(JPanel panel) throws SQLException{
		panel.setLayout(null);

		labelModifyDescriptionText = new JLabel();
		labelModifyDescriptionText.setName("id2_descriptionModify");
		labelModifyDescriptionText.setText(Constants.MODIFY_DESCRIPTION_TEXT);
		labelModifyDescriptionText.setBounds(50, 60, 450, 15);

		Connection connection = MySQLUtil.getConnectionMySQL();

		List<String> result = MySQLUtil.getDescriptions(connection);
		DescriptionQueries.selectDescriptionQuery(result);

		labelModifySQLQuery = new JLabel();
		labelModifySQLQuery.setName("id2_queryModify");
		labelModifySQLQuery.setText(Constants.MODIFY_SQL_TEXT);
		labelModifySQLQuery.setBounds(50, 130, 450, 15);

		nextStep = new JButton();
		nextStep.setText(Constants.NEXT_BUTTON);
		nextStep.setBounds(225, 320, 100, 50);
		//OBTENER EL CONTENIDO DE LA CAJA Y GUARDARLO EN UNA VARIABLE
		nextStep.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//Busco el scrollPane y añado en el bean la consulta seleccionada
				String selected = Util.searchScrollPaneInfo(MainInterface.panel2, "id2_scrollPaneSqlQueries");
				if(selected != null && !selected.equals(Constants.BLANK)){
					MainInterface.queryBean.setQueryString(selected);
					MainInterface.mainInterface.setEnabledAt(MainInterface.mainInterface.indexOfComponent(MainInterface.panel3),true);
					MainInterface.mainInterface.setSelectedIndex(MainInterface.mainInterface.indexOfComponent(MainInterface.panel3));
					Util.setLabelsInfoPanel3(MainInterface.panel3, "id2_infoSQLQuerySelected", "id2_infoDescriptQuerySelected");
				}
				else{
					MensajeDialog.messageSelectOption();
				}
			}
		});

		addQuery = new JButton();
		addQuery.setText(Constants.ADD_BUTTON);
		addQuery.setBounds(120, 320, 100, 50);

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


	/**
	 * Método para obetener los distintos informes
	 * @return JPanel panel Panel panel que contiene el resumen de la consulta
	 */
	public static JPanel reportQuery(){
		JPanel panel = new JPanel();
		panel.setLayout(null);

		Font font = new Font("Verdana",Font.ITALIC,12);

		labelInfoTextQuery = new JLabel();
		labelInfoTextQuery.setText(Constants.LABEL_DESCRIPTION_REPORT);
		labelInfoTextQuery.setBounds(50, 60, 450, 15);
		labelInfoTextQuery.setName("id2_infoDescriptQuery");

		labelTextSelected = new JLabel();
		labelTextSelected.setName("id2_infoDescriptQuerySelected");
		labelTextSelected.setFont(font);
		labelTextSelected.setForeground(new Color(27,85,131));
		labelTextSelected.setBounds(60, 80, 700, 50);

		labelInfoSQLQuery = new JLabel();
		labelInfoSQLQuery.setText(Constants.LABEL_SQL_REPORT);
		labelInfoSQLQuery.setBounds(50, 160, 450, 15);
		labelInfoSQLQuery.setName("id2_infoSQLQuery");     		

		labelSQLSelected = new JLabel();
		labelSQLSelected.setName("id2_infoSQLQuerySelected");
		labelSQLSelected.setFont(font);
		labelSQLSelected.setForeground(new Color(27,85,131));
		labelSQLSelected.setBounds(60, 180, 700, 50);

		showReportButton = new JButton();
		showReportButton.setName("id3_showReportButton");
		showReportButton.setText(Constants.SHOW_REPORT);
		showReportButton.setBounds(170, 320, 100, 50);

		showReportButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {

				if(MainInterface.queryBean.getNumRows() > 0){
					Calendar cal = Calendar.getInstance();
					int year = cal.get(Calendar.YEAR);
					int month = cal.get(Calendar.MONTH) + 1;
					int day = cal.get(Calendar.DAY_OF_MONTH);
					int hour = cal.get(Calendar.HOUR_OF_DAY);
					int minute = cal.get(Calendar.MINUTE);
					int second = cal.get(Calendar.SECOND);
					StringBuilder sb = new StringBuilder();

					sb.append(year);
					sb.append(month);
					sb.append(day);
					sb.append(Constants.LOW_BAR);
					sb.append(hour);
					sb.append(minute);				
					sb.append(second);

					Map<String, Object> parameters = new HashMap<String, Object>();

					parameters.put("description", MainInterface.queryBean.getQueryDescription());

					@SuppressWarnings("static-access")
					InputStream template = this.getClass().getClassLoader().getSystemResourceAsStream(Constants.INFORME);
					JasperReport jReport;
					try {
						jReport = JasperCompileManager.compileReport(template);

						List<ReportBean> listaReport = new ArrayList<ReportBean>();
						listaReport = MySQLUtil.getQueryToReport();
						JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(listaReport);

						parameters.put("csv",MainInterface.queryBean.getStringCSV());

						JasperPrint jPrint = JasperFillManager.fillReport(jReport, parameters, datasource);

						JasperExportManager.exportReportToPdfFile(jPrint, Constants.REPORT_PATH + sb.toString() + Constants.REPORT_EXTENSION);
						JasperViewer.viewReport(jPrint, false);

					}
					catch (JRException | SQLException e1) {
						MensajeDialog.messageEmptyQuery();
						e1.printStackTrace();
					}
				}else{
					MensajeDialog.messageEmptyQuery();
				}

			}

		});

		showCsvButton = new JButton();
		showCsvButton.setName("id3_showCsvButton");
		showCsvButton.setText(Constants.SHOW_CSV_REPORT);
		showCsvButton.setBounds(50, 320, 100, 50);

		showCsvButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {

				if(MainInterface.queryBean.getNumRows() > 0){
					Calendar cal = Calendar.getInstance();
					int year = cal.get(Calendar.YEAR);
					int month = cal.get(Calendar.MONTH) + 1;
					int day = cal.get(Calendar.DAY_OF_MONTH);
					int hour = cal.get(Calendar.HOUR_OF_DAY);
					int minute = cal.get(Calendar.MINUTE);
					int second = cal.get(Calendar.SECOND);
					StringBuilder sb = new StringBuilder();

					sb.append(year);
					sb.append(month);
					sb.append(day);
					sb.append(Constants.LOW_BAR);
					sb.append(hour);
					sb.append(minute);				
					sb.append(second);

					@SuppressWarnings("static-access")
					InputStream template = this.getClass().getClassLoader().getSystemResourceAsStream(Constants.CSVREPORT);
					JasperReport jReport;
					List<CSVRowBean> listaReport = new ArrayList<CSVRowBean>();
					listaReport = MainInterface.queryBean.getStringCSV();
					JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(listaReport);

					try {
						jReport = JasperCompileManager.compileReport(template);

						JasperPrint jPrint = JasperFillManager.fillReport(jReport, null, datasource);

						JasperExportManager.exportReportToPdfFile(jPrint, Constants.CSV_REPORT_PATH + sb.toString() + Constants.REPORT_EXTENSION);
						JasperViewer.viewReport(jPrint, false);

					} catch (JRException e1) {
						e1.printStackTrace();
					}
				}
				else{
					MensajeDialog.messageEmptyQuery();
				}
			}
		});

		resetButton = new JButton();
		resetButton.setText(Constants.RESET_BUTTON);
		resetButton.setBounds(290, 320, 100, 50);

		resetButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Util.resetApp();
			}
		});

		panel.add(labelInfoTextQuery);
		panel.add(labelTextSelected);
		panel.add(labelInfoSQLQuery);
		panel.add(labelSQLSelected);
		panel.add(showReportButton);
		panel.add(resetButton);	
		panel.add(showCsvButton);

		return panel;
	}
}
