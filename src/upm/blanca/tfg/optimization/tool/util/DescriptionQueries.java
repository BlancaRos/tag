package upm.blanca.tfg.optimization.tool.util;

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
	
	public static void selectDescriptionQuery(List<String> result){ 
		//Creación de la ventana 
		JFrame ventana = new JFrame(Constants.LABEL_WINDOW_DESCRIPTIONS); 
		ventana.setBounds(200,250,500,370); 
		ventana.setLayout(new GridLayout()); 

		//Creación del panel, que contendra JList 
		JPanel panel = new JPanel(); 
		panel.setLayout(null); 
		
		JLabel labelToSelectDescription = new JLabel();
		labelToSelectDescription.setName("id2_scrollDescription");
		labelToSelectDescription.setText(Constants.LABEL_DESCRIPTIONS);
		labelToSelectDescription.setBounds(20, 30, 400, 15);

		//creación del objeto lista 
		String[] descriptions = new String[result.size()];
		descriptions = (String[]) result.toArray(descriptions);
		JList lista = new JList(descriptions); 
		
		//se cambia la orientación de presentación y el ajuste 
		lista.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION); 
		
		// aquí se crea el objeto, es decir la barra de desplazamiento 
		JScrollPane barraDesplazamiento = new JScrollPane(lista); 
		barraDesplazamiento.setBounds(20,55,450,150); 
		//Agrega la barra de desplazamiento al panel 
		
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
