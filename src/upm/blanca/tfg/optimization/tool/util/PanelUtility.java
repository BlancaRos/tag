package upm.blanca.tfg.optimization.tool.util;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import upm.blanca.tfg.optimization.tool.constants.Constants;

public class PanelUtility {

	//private static JTextField eco;
	public static String message = "";

	public static JPanel createPanelDataBase(String text) {
		//Crear primer panel y etiqueta correspondiente
		JPanel panel = new JPanel();
		JLabel label = new JLabel(text, SwingConstants.CENTER);
		label.setName("labelDB");
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
		//Crear un desplegable
		DefaultComboBoxModel dropDown = new DefaultComboBoxModel();
		dropDown.addElement(Constants.DEFAULT_TYPE);
		dropDown.addElement(Constants.PREDETERMINED_TYPE);
		dropDown.addElement(Constants.SQL_TYPE);
		JComboBox comboBox = new JComboBox(dropDown);
		panel.add(label);
		panel.add(comboBox);

		ComboBoxUtility.addListenerToDropDown(comboBox, panel);

		return panel;
	}

	public static JPanel createPanelReport(String text) {
		//Crear tercer panel y etiqueta correspondiente
		JPanel panel = new JPanel();
		//ventana.setLocationRelativeTo(null); // centramos la ventana en la pantalla
		//ventana.setResizable(false); // hacemos que la ventana no sea redimiensionable
		//JLabel label = new JLabel(text, SwingConstants.NORTH);

		panel = ComboBoxUtility.ReportQuery();

		return panel;
	}

	/*public static void addListenerClickPanel(JPanel panel){
		panel.addMouseListener(new MouseAdapter() { 
			public void mousePressed(MouseEvent me) { 
				System.out.println("Has hecho click sobre el panel"); 
				ColorController eventColor = new ColorController();
				eventColor.cambiarColor(panel);
			} 
		}); 
	}*/

	//	public static JPanel createPanelNaturalQuery(String text) {
	//	JPanel panel = new JPanel();
	//	JLabel label = new JLabel(text, SwingConstants.CENTER);
	//
	//	panel.setBackground(Color.GREEN);
	//	panel.setLayout(new BorderLayout());  
	//	panel.add(new JButton("Norte"), BorderLayout.NORTH);
	//	panel.add(new JButton("Oeste"), BorderLayout.WEST);
	//	panel.add(new JButton("Este"), BorderLayout.EAST);
	//	panel.add(new JButton("Sur"), BorderLayout.SOUTH);
	//	panel.add(label, BorderLayout.CENTER);
	//
	//	return panel;
	//}

	//public static JPanel createPanelSqlQuery(String text) {
	//	JPanel panel = new JPanel();
	//	JLabel label = new JLabel(text, SwingConstants.CENTER);
	//
	//	panel.setBackground(Color.ORANGE);
	//	panel.setLayout(new BorderLayout());  
	//	panel.add(new JButton("Cancelar"), BorderLayout.WEST);
	//	panel.add(new JButton("Siguiente"), BorderLayout.EAST);
	//	panel.add(label, BorderLayout.CENTER);
	//
	//	return panel;
	//}

}
