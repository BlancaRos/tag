package upm.blanca.tfg.optimization.tool.util;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import upm.blanca.tfg.optimization.tool.constants.Constants;
import upm.blanca.tfg.optimization.tool.db.util.MySQLUtil;
import upm.blanca.tfg.optimization.tool.main.MainInterface;

public class CheckboxUtility {

	public static JButton nextButton;
	public static JButton newBbddButton;
	public static JRadioButton vehiclesButton;
	public static JRadioButton otherButton;
	public static String bbddName = Constants.BLANK;

	public static void Checkbox(JPanel panel) throws SQLException{	

		Connection connection = MySQLUtil.getConnectionMySQL();

		CheckboxUtility.obtenerBbdd(panel, connection);

		nextButton = new JButton();
		nextButton.setText(Constants.NEXT_BUTTON);
		nextButton.setBounds(215, 190, 100, 50);
		//OBTENER EL CONTENIDO DE LA CAJA Y GUARDARLO EN UNA VARIABLE
		nextButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String selected = Util.searchScrollPaneInfo(MainInterface.panel1, "id1_dataBasesCroll");
				if(Constants.BLANK.equals(selected)){
					MensajeDialog.MessageSelectOption();
				}
				else{
					try {
						MySQLUtil.getSelectedDataBase(selected);
						MensajeDialog.NextStep();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
//				try {
//					MySQLUtil.getSelectedDataBase(selected);
//				} catch (SQLException e1) {
////					MensajeDialog.MessageSelectOption();
//					e1.printStackTrace();
//				}
			}
		});

		newBbddButton = new JButton();
		newBbddButton.setText(Constants.ADD_BBDD_BUTTON);
		newBbddButton.setBounds(100, 190, 100, 50);
		//OBTENER EL CONTENIDO DE LA CAJA Y GUARDARLO EN UNA VARIABLE
		newBbddButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				WindowUtility.createWindowNewBbdd();
			}
		});

		panel.add(nextButton);
		panel.add(newBbddButton);

	} 

	public static void obtenerBbdd(JPanel panel, Connection connection) throws SQLException{

		List<String> result = MySQLUtil.getBbdd(connection);

		//creación del objeto lista 
		String[] bbdds = new String[result.size()];
		bbdds = (String[]) result.toArray(bbdds);
		JList lista = new JList(bbdds); 

		//se cambia la orientación de presentación y el ajuste 
		lista.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION); 
		//selecciona un elemento de la lista 
		Object seleccion = lista.getSelectedValue(); 
		//recoge el indice de los seleccionados 
		int[] indices = lista.getSelectedIndices(); 

		if(result.isEmpty()){
			JLabel bbddIsEmpty = new JLabel();
			bbddIsEmpty.setName("id1_BBDDEmpty");
			bbddIsEmpty.setText(Constants.LABEL_ADD_BBDD);
			bbddIsEmpty.setBounds(53, 70, 300, 40);
			panel.add(bbddIsEmpty);
		}
		else{
			// aquí se crea el objeto, es decir la barra de desplazamiento 
			JScrollPane barraDesplazamiento = new JScrollPane(lista); 
			barraDesplazamiento.setName("id1_dataBasesCroll");
			barraDesplazamiento.setBounds(53,70,320,100);

			panel.add(barraDesplazamiento);
		}
	}

	
}