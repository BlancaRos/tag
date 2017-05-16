package upm.blanca.tfg.optimization.tool.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
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
	private static JLabel labelLoading;
	private static Icon icon;

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
					if(addQuery != null)
						panel.remove(addQuery);	
					Util.removeComponentFromPanel(MainInterface.panel2, "id2_descriptionModify", "id2_queryModify");

					sqlQuery = DesignQuery(panel);
				}else{
					MensajeDialog.MessageSelectOption();
				}
			}
		});
	}

	//CONSULTA NUEVA
	public static String DesignQuery(JPanel panel) {
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

				//GUARDO LA CONSULTA A REALIZAR (texto y sql)
				MainInterface.queryBean.setQueryString(sqlQuery);
				MainInterface.queryBean.setQueryDescription(textQuery);

				MensajeDialog.MessageDialogue();
			}
		});

////		try {
//			System.out.println("EEIII");
////			icon = new ImageIcon(ImageIO.read(ComboBoxUtility.class.getClassLoader().getResourceAsStream("loading.gif")));
////			labelLoading = new JLabel(icon);
//			labelLoading = new JLabel("Loading...");
//			labelLoading.setBounds(250, 340, 50, 50);
//			labelLoading.setVisible(true);
//			panel.add(labelLoading);
//
////		} catch (IOException e1) {
////			// TODO Auto-generated catch block
////			e1.printStackTrace();
////		}
		
//		JPanel panelito = Util.searchPane(MainInterface.mainInterface, "panel2");
//		panelito.add(labelLoading);
		/////////////////////
//		JLabel img = new JLabel(); 
//		ImageIcon image = new ImageIcon("/Users/admin/Desktop/TFG/Imágenes/cargando.gif"); 
//
//		//Propiedades de la etiqueta 
//		img.setIcon(image); 
//		img.setSize(100,100); 
//		img.setLocation(460,80); 
//		img.setVisible(true); 
//		
//		panel.add(img);


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
				MainInterface.queryBean.setQueryString(selected);
				MainInterface.mainInterface.setEnabledAt(MainInterface.mainInterface.indexOfComponent(MainInterface.panel3),true);
				MainInterface.mainInterface.setSelectedIndex(MainInterface.mainInterface.indexOfComponent(MainInterface.panel3));
				Util.setLabelsInfoPanel3(MainInterface.panel3, "id2_infoSQLQuerySelected", "id2_infoDescriptQuerySelected");
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

	public static JPanel ReportQuery(){
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

		sentQueryButton = new JButton();
		sentQueryButton.setText(Constants.SENT_QUERY);
		sentQueryButton.setBounds(110, 320, 100, 50);

		sentQueryButton.addActionListener(new ActionListener(){
			List<ReportBean> reportBean = new ArrayList<ReportBean>();

			public void actionPerformed(ActionEvent e) {

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

				List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
				Map<String, Object> parameters = new HashMap<String, Object>();

				parameters.put("description", MainInterface.queryBean.getQueryDescription());

				InputStream template = this.getClass().getClassLoader().getSystemResourceAsStream(Constants.INFORME);
				JasperReport jReport;
				try {
					//Añadir datos obtenidos
					jReport = JasperCompileManager.compileReport(template);
					List<ReportBean> listaReport = new ArrayList<ReportBean>();

					List<CSVReportBean> listCSV = new ArrayList<CSVReportBean>();
					CSVReportBean cv1 = new CSVReportBean();
					cv1.setCsv("mi row1");
					CSVReportBean cv2 = new CSVReportBean();
					cv2.setCsv("mi row2");
					listCSV.add(cv1);
					listCSV.add(cv2);

					listaReport = MySQLUtil.getQueryToReport();
					//listCSV = MySQLUtil.getCSVToReport();
					JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(listaReport);
//					JRBeanCollectionDataSource datasource2 = new JRBeanCollectionDataSource(listCSV);

					parameters.put("csv",cv1.getCsv()+"<br/>"+cv2.getCsv());
					//csv
//					JRBeanArrayDataSource datasource = new JRBeanArrayDataSource(beanArray);
					JasperPrint jPrint = JasperFillManager.fillReport(jReport, parameters, datasource);
					JasperExportManager.exportReportToPdfFile(jPrint, Constants.REPORT_PATH + sb.toString() + Constants.REPORT_EXTENSION);
					JasperViewer.viewReport(jPrint, false);
					
					//Añadir csv
					
				} catch (JRException | SQLException e1) {
					e1.printStackTrace();
				}

			}
		});

		resetButton = new JButton();
		resetButton.setText(Constants.RESET_BUTTON);
		resetButton.setBounds(225, 320, 100, 50);

		resetButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					Runtime.getRuntime().exec(Constants.APP);
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
