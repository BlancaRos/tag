package upm.blanca.tfg.optimization.tool.interfaz.util;

import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import upm.blanca.tfg.optimization.tool.constants.Constants;

public class PanelUtility {

	public static String message = Constants.BLANK;

	//PANEL 1
	public static JPanel createPanelDataBase(String text) throws SQLException {
		JPanel panel = new JPanel();
		panel.setName("panel1");
		JLabel label = new JLabel(text, SwingConstants.LEFT);
		label.setName("id1_labelDB");
		panel.setLayout(null); //Para poder poner los componentes donde quiero
		label.setBounds(55,30,400,25); 
		panel.add(label); 

		CheckboxUtility.Checkbox(panel);

		return panel;
	}

	//PANEL 2
	public static JPanel createPanelQueryType(String text){
		JPanel panel = new JPanel();
		panel.setName("panel2");
		JLabel label = new JLabel(text, SwingConstants.CENTER);
		label.setName("id2_labelQueryType");
		
		//Crear un desplegable
		DefaultComboBoxModel dropDown = new DefaultComboBoxModel();
		dropDown.addElement(Constants.DEFAULT_TYPE);
		dropDown.addElement(Constants.OLD_TYPE);
		dropDown.addElement(Constants.NEW_TYPE);
		
		JComboBox comboBox = new JComboBox(dropDown);
		
		panel.add(label);
		panel.add(comboBox);

		ComboBoxUtility.addListenerToDropDown(comboBox, panel);

		return panel;
	}

	//PANEL 3
	public static JPanel createPanelReport(String text) {
		JPanel panel = new JPanel();
		panel.setName("panel3");
		panel = ComboBoxUtility.ReportQuery();

		return panel;
	}
}