package upm.blanca.tfg.optimization.tool.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import upm.blanca.tfg.optimization.tool.constants.Constants;
import upm.blanca.tfg.optimization.tool.main.MainInterface;

public class ComboBoxUtility {

	private static JTextField desiredQuery;
	private static JTextField eco;
	private static JLabel labelDesignQuery;
	private static JLabel labelSelectedQuery;
	private static JLabel labelQueryDatabase;
	private static JLabel predeterminedText;
	private static JButton showContent; 
	private static JButton nextStep;
	private static JButton sentQueryButton; 
	private static DefaultComboBoxModel<String> dropDown;
	private static JComboBox comboBox;
	private static String[] optionsComboBox={Constants.DEFAULT_TYPE, Constants.TYPE_SELECT, Constants.TYPE_DELETE, Constants.TYPE_UPDATE, Constants.TYPE_INSERT};
	public static String query = "";
	public static String message;
	

	public static void addListenerToDropDown(JComboBox dropDown, JPanel panel){
		dropDown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				JComboBox<String> combo = (JComboBox<String>) event.getSource();
				String selectedBook = (String) combo.getSelectedItem();
//				MainInterface.queryBean.setQueryType(selectedBook);

				if (selectedBook.equals("Predeterminado")) {
					System.out.println("Good choice!Has elegido predeterminado!");
					
					//Si se ha seleccionado SQL antes, eliminar el panel
					if(labelDesignQuery != null)
						panel.remove(labelDesignQuery);
					if(desiredQuery != null)
						panel.remove(desiredQuery);
					if(showContent != null)
						panel.remove(showContent);
					if(nextStep != null)
						panel.remove(nextStep);					
					//ChooseQuery(panel);
				}else if (selectedBook.equals("SQL")) {
					System.out.println("Nice pick, too!Buen programador!!!");
					//Si se ha seleccionado Predeterminado antes, eliminar el panel
					if(predeterminedText != null)
						panel.remove(predeterminedText);
					if(comboBox != null)
						panel.remove(comboBox);
					query = DesignQuery(panel);
				}else{
					MensajeDialog.MessageSelectOption();
				}
			}
		});
	}

	public static String DesignQuery(JPanel panel){
		panel.setLayout(null);

		labelDesignQuery = new JLabel();
		labelDesignQuery.setText(Constants.SQL_TEXT);
		labelDesignQuery.setBounds(50, 80, 400, 25);

		desiredQuery = new JTextField();
		desiredQuery.setBounds(50, 110, 400, 100);

		showContent = new JButton();
		showContent.setText("Mostrar Consulta");
		showContent.setBounds(50, 220, 200, 30);

		nextStep = new JButton();
		nextStep.setText(Constants.NEXT_BUTTON);
		nextStep.setBounds(350, 220, 100, 30);
		//OBTENER EL CONTENIDO DE LA CAJA Y GUARDARLO EN UNA VARIABLE
		nextStep.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				query = desiredQuery.getText(); 

				//GUARDO LA CONSULTA A REALIZAR
				MainInterface.queryBean.setQueryString(query);
				
				MensajeDialog.MessageDialogue();
			}
		});

		panel.add(labelDesignQuery);
		panel.add(desiredQuery);
		panel.add(showContent);
		panel.add(nextStep);
		//Añadir nuevos componentes al panel
		panel.paintAll(panel.getGraphics());
		return query;
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
	}
	
	public static void addListenerToChooseQuery(JComboBox dropDown, JPanel panel){
		dropDown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				JComboBox<String> combo = (JComboBox<String>) event.getSource();
				String selectedBook = (String) combo.getSelectedItem();

				if (selectedBook.equals("SELECT")) {
					System.out.println("-- Elegiste Select");
				}else if (selectedBook.equals("DELETE")) {
					System.out.println("-- Elegiste Delete");	
				}else if (selectedBook.equals("UPDATE")) {
					System.out.println("-- Elegiste Update");	
				}else if (selectedBook.equals("INSERT")) {
					System.out.println("-- Elegiste Insert");	
				}else if (selectedBook.equals("Seleccione uno...")){
					MensajeDialog.MessageSelectOption();
				}
			}
		});
	}*/
	
	public static JPanel ReportQuery(){
		JPanel panel = new JPanel();
		panel.setLayout(null);
		labelQueryDatabase = new JLabel();
		labelQueryDatabase.setText(Constants.LABEL_REPORT);
		labelQueryDatabase.setBounds(10, 10, 400, 25);
		labelQueryDatabase.setName("infoSelectConsult");
		labelSelectedQuery = new JLabel();
		labelSelectedQuery.setName("designQuery");     
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
