package upm.blanca.tfg.optimization.tool.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import upm.blanca.tfg.optimization.tool.constants.Constants;
import upm.blanca.tfg.optimization.tool.db.util.MySQLUtil;
import upm.blanca.tfg.optimization.tool.main.MainInterface;

public class ComboBoxUtility {

	private static JTextField desiredQuery;
	private static JTextField descriptionQuery;
	private static JTextField modifyDescriptionQuery;
	private static JTextField modifySQLQuery;

	private static JTextField eco;
	private static JLabel labelDesignQuery;
	private static JLabel labelQueryDescription;
	private static JLabel labelSelectedQuery;
	private static JLabel labelQueryDatabase;
	private static JLabel predeterminedText;
	
	private static JLabel labelModifyDescriptionText;
	private static JLabel labelModifySQLQuery;

	private static JButton showContent; 
	private static JButton nextStep;
	private static JButton sentQueryButton; 
	private static DefaultComboBoxModel<String> dropDown;
	private static JComboBox comboBox;
	//private static String[] optionsComboBox={Constants.DEFAULT_TYPE, Constants.TYPE_SELECT, Constants.TYPE_DELETE, Constants.TYPE_UPDATE, Constants.TYPE_INSERT};
	public static String newQuery = "";
	public static String oldQuery = "";
	public static String message;
	

	public static void addListenerToDropDown(JComboBox dropDown, JPanel panel){
		dropDown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				JComboBox<String> combo = (JComboBox<String>) event.getSource();
				String selectedBook = (String) combo.getSelectedItem();
//				MainInterface.queryBean.setQueryType(selectedBook);

				if (selectedBook.equals("Modificar consulta creada")) {
					
					//Si se ha seleccionado SQL antes, eliminar el panel
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
						oldQuery = ModifyQuery(panel);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//ChooseQuery(panel);
				}else if (selectedBook.equals("Consulta nueva")) {

					//Si se ha seleccionado Predeterminado antes, eliminar el panel
					if(labelModifyDescriptionText != null)
						panel.remove(labelModifyDescriptionText);
					if(modifyDescriptionQuery != null)
						panel.remove(modifyDescriptionQuery);
					if(labelModifySQLQuery != null)
						panel.remove(labelModifySQLQuery);
					if(modifySQLQuery != null)
						panel.remove(modifySQLQuery);
					if(comboBox != null)
						panel.remove(comboBox);
					newQuery = DesignQuery(panel);
				}else{
					MensajeDialog.MessageSelectOption();
				}
			}
		});
	}

	public static String DesignQuery(JPanel panel){
		panel.setLayout(null);

		labelQueryDescription = new JLabel();
		labelQueryDescription.setName("id2_descriptionText");
		labelQueryDescription.setText(Constants.DESCRIPTION_TEXT);
		labelQueryDescription.setBounds(50, 60, 400, 10);
		
		descriptionQuery = new JTextField();
		descriptionQuery.setBounds(50, 80, 400, 80);
		
		labelDesignQuery = new JLabel();
		labelDesignQuery.setName("id2_sqlText");
		labelDesignQuery.setText(Constants.SQL_TEXT);
		labelDesignQuery.setBounds(50, 160, 400, 10);

		desiredQuery = new JTextField();
		desiredQuery.setBounds(50, 180, 400, 80);

		nextStep = new JButton();
		nextStep.setText(Constants.NEXT_BUTTON);
		nextStep.setBounds(350, 260, 100, 30);
		//OBTENER EL CONTENIDO DE LA CAJA Y GUARDARLO EN UNA VARIABLE
		nextStep.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				newQuery = desiredQuery.getText(); 

				//GUARDO LA CONSULTA A REALIZAR
				MainInterface.queryBean.setQueryString(newQuery);
				
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
		return newQuery;
	}

	public static String ModifyQuery(JPanel panel) throws SQLException{
		panel.setLayout(null);

		labelModifyDescriptionText = new JLabel();
		labelModifyDescriptionText.setName("id2_descriptionModify");
		labelModifyDescriptionText.setText(Constants.MODIFY_DESCRIPTION_TEXT);
		labelModifyDescriptionText.setBounds(50, 60, 400, 10);
		
		Connection connection = MySQLUtil.getConnectionMySQL();
		
		ArrayList result = MySQLUtil.getDescriptions(connection);
		DescriptionQueries.selectDescriptionQuery(result);
		
//		modifyDescriptionQuery = new JTextField();
//		modifyDescriptionQuery.setBounds(50, 80, 400, 80);
		
		labelModifySQLQuery = new JLabel();
		labelModifySQLQuery.setName("id2_queryModify");
		labelModifySQLQuery.setText(Constants.MODIFY_SQL_TEXT);
		labelModifySQLQuery.setBounds(50, 160, 400, 10);

		//BUSCAR EN BBDD LAS DESCRIPCIONES EXISTENTES
		modifySQLQuery = new JTextField();
		modifySQLQuery.setBounds(50, 180, 400, 80);

		nextStep = new JButton();
		nextStep.setText(Constants.NEXT_BUTTON);
		nextStep.setBounds(350, 260, 100, 30);
		//OBTENER EL CONTENIDO DE LA CAJA Y GUARDARLO EN UNA VARIABLE
		nextStep.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				newQuery = desiredQuery.getText(); 

				//GUARDO LA CONSULTA A REALIZAR
				MainInterface.queryBean.setQueryString(newQuery);
				
				MensajeDialog.MessageDialogue();
			}
		});

		panel.add(labelModifyDescriptionText);
		//panel.add(modifyDescriptionQuery);
		panel.add(labelModifySQLQuery);
		panel.add(modifySQLQuery);
		panel.add(nextStep);
		//Añadir nuevos componentes al panel
		panel.paintAll(panel.getGraphics());
		return newQuery;
	}
	
	/*public static void ChooseQuery(JPanel panel){
		panel.setLayout(null);
		predeterminedText = new JLabel();
		predeterminedText.setText(Constants.OPERATION_TYPE);
		predeterminedText.setBounds(10, 80, 400, 25);
		comboBox = new JComboBox(optionsComboBox);
		comboBox.setBounds(300, 80, 200, 25);
		panel.add(predeterminedText);
		panel.add(comboBox);
		panel.paintAll(panel.getGraphics());
		addListenerToChooseQuery(comboBox, panel);
	}*/
	
	
	public static JPanel ReportQuery(){
		JPanel panel = new JPanel();
		panel.setLayout(null);
		labelQueryDatabase = new JLabel();
		labelQueryDatabase.setText(Constants.LABEL_REPORT);
		labelQueryDatabase.setBounds(10, 10, 400, 25);
		labelQueryDatabase.setName("id2_infoSelectConsult");
		labelSelectedQuery = new JLabel();
		labelSelectedQuery.setName("id2_designQuery");     
        //Introducir la consulta elegida
		
		//labelSelectedQuery.setText(MainInterface.queryBean.getQueryString());
        
		labelSelectedQuery.setBounds(50, 100, 200, 30);
		labelSelectedQuery.setText("Texto a mano");
        sentQueryButton = new JButton();
		sentQueryButton.setText(Constants.SENT_QUERY);
		sentQueryButton.setBounds(50, 200, 200, 30);
        
		panel.add(labelQueryDatabase);
		panel.add(labelSelectedQuery);
		panel.add(sentQueryButton);

		return panel;
	}
	
	public static JComponent addTextField() {
		//Se crea el campo de texto donde poner el eco
		eco = new JTextField("Introduce sentencia...");
		return eco; 
	}
}
