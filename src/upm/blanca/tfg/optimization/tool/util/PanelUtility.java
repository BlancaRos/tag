package upm.blanca.tfg.optimization.tool.util;

import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import upm.blanca.tfg.optimization.tool.constants.Constants;

public class PanelUtility {

	public static String message = "";

	public static JPanel createPanelDataBase(String text) throws SQLException {
		//Crear primer panel y etiqueta correspondiente
		JPanel panel = new JPanel();
		JLabel label = new JLabel(text, SwingConstants.CENTER);
		label.setName("id1_labelDB");
		panel.setLayout(null);//Para poder poner los componentes donde quiero
		label.setBounds(50,10,400,25); //x, y, largo, ancho
		panel.add(label); 

		CheckboxUtility.Checkbox(panel);

		return panel;
	}

	public static JPanel createPanelQueryType(String text){
		//Crear segundo panel y etiqueta correspondiente
		JPanel panel = new JPanel();
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

	public static JPanel createPanelReport(String text) {
		//Crear tercer panel y etiqueta correspondiente
		JPanel panel = new JPanel();
		panel = ComboBoxUtility.ReportQuery();

		return panel;
	}

}