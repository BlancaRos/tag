package upm.blanca.tfg.optimization.tool.util;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class DescriptionQueries {
	
	public static void selectDescriptionQuery(List<String> result){ 
		//Creación de la ventana 
		JFrame ventana = new JFrame("Consultas texto disponibles"); 
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		ventana.setBounds(200,250,500,400); 
		ventana.setLayout(new GridLayout()); 

		//Creación del panel, que contendra JList 
		JPanel panel = new JPanel(); 
		panel.setLayout(null); 
		
		//creación del objeto lista 
		String[] descriptions = new String[result.size()];
		descriptions = (String[]) result.toArray(descriptions);
		JList lista = new JList(descriptions); 
		
		//se cambia la orientación de presentación y el ajuste 
		lista.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION); 
		//selecciona un elemento de la lista 
		Object seleccion = lista.getSelectedValue(); 
		//recoge el indice de los seleccionados 
		int[] indices = lista.getSelectedIndices(); 

		// aquí se crea el objeto, es decir la barra de desplazamiento 
		JScrollPane barraDesplazamiento = new JScrollPane(lista); 
		barraDesplazamiento.setBounds(10,30,450,100); 
		//Agrega la barra de desplazamiento al panel 
		
		JButton buttonCancel = new JButton(); 
		buttonCancel.setText("CANCELAR");
		buttonCancel.setBounds(100, 260, 100, 30);
		ButtonUtility.addListenerClickCancelButtonDescription(buttonCancel, ventana);

		JButton buttonSelect = new JButton(); 
		buttonSelect.setText("OK");
		buttonSelect.setBounds(230, 260, 100, 30);
		ButtonUtility.addListenerClickOkButtonDescription(buttonSelect, ventana, lista);

		panel.add(buttonCancel);
		panel.add(buttonSelect);		
		panel.add(barraDesplazamiento); 

		ventana.add(panel); 
		ventana.setVisible(true); 
		
	} 

}
