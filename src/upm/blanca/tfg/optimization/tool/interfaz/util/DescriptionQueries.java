package upm.blanca.tfg.optimization.tool.interfaz.util;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import upm.blanca.tfg.optimization.tool.constants.Constants;

public class DescriptionQueries {
	
	/**
	 * Metodo para crear una ventana en la que se insertaran las descripciones almacenadas en la BBDD
	 * @param result - lista de descripciones existentes
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void selectDescriptionQuery(List<String> result){ 
		//Ventana 
		JFrame ventana = new JFrame(Constants.LABEL_WINDOW_DESCRIPTIONS); 
		ventana.setBounds(200,250,500,370); 
		ventana.setLayout(new GridLayout()); 

		//Panel 
		JPanel panel = new JPanel(); 
		panel.setLayout(null); 
		
		JLabel labelToSelectDescription = new JLabel();
		labelToSelectDescription.setName("id2_scrollDescription");
		labelToSelectDescription.setText(Constants.LABEL_DESCRIPTIONS);
		labelToSelectDescription.setBounds(20, 30, 400, 15);

		//Objeto lista 
		String[] descriptions = new String[result.size()];
		descriptions = (String[]) result.toArray(descriptions);
		JList lista = new JList(descriptions); 
		
		//Cambio de la orientación de presentación y su ajuste 
		lista.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION); 
		
		//Barra de desplazamiento 
		JScrollPane barraDesplazamiento = new JScrollPane(lista); 
		barraDesplazamiento.setBounds(20,55,450,150); 
		
		JButton buttonCancel = new JButton(); 
		buttonCancel.setText(Constants.CANCEL_BUTTON);
		buttonCancel.setBounds(130, 230, 100, 50);
		ButtonUtility.addListenerClickCancelButtonDescription(buttonCancel, ventana);

		JButton buttonSelect = new JButton(); 
		buttonSelect.setText(Constants.NEXT_BUTTON);
		buttonSelect.setBounds(245, 230, 100, 50);
		ButtonUtility.addListenerClickOkButtonDescription(buttonSelect, ventana, lista);

		panel.add(labelToSelectDescription);
		panel.add(buttonCancel);
		panel.add(buttonSelect);		
		panel.add(barraDesplazamiento); 

		ventana.add(panel); 
		ventana.setVisible(true); 
	} 
}
