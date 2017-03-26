package upm.blanca.tfg.optimization.tool.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import upm.blanca.tfg.optimization.tool.constants.Constants;
import upm.blanca.tfg.optimization.tool.main.MainInterface;

public class CheckboxUtility {

	public static JButton nextButton;
	public static JRadioButton vehiclesButton;
	public static JRadioButton otherButton;
	public static String bbddName = Constants.BLANK;

	public static void Checkbox(JPanel panel){
		//Crear un grupo para añadir los buttons y así solo poder seleccionar uno (no multiple)
		ButtonGroup group = new ButtonGroup();

		//crear button y CheckBox y se inicializaran los Checkbox en falso
		nextButton = new JButton(Constants.NEXT_BUTTON);
		vehiclesButton = new JRadioButton(Constants.VEHICLE_DATABASE, false);
		otherButton = new JRadioButton(Constants.OTHER_DATABASE, false);
		
		vehiclesButton.setBounds(100, 50, 100, 50);
		otherButton.setBounds(100, 100, 100, 50);
		nextButton.setBounds(300, 200, 100, 50);
		
		group.add(vehiclesButton);
		group.add(otherButton);
		
		panel.add(vehiclesButton);
		panel.add(otherButton);
		panel.add(nextButton);
		
		//OBTENER EL CONTENIDO DE LA CAJA Y GUARDARLO EN UNA VARIABLE
		nextButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {				
				MensajeDialog.NextStep();
			}
		});
		
		eventHandler();
	}

	public static void eventHandler(){ //eventHandler = manejador de eventos????
		Integer numBbdd = 0;
		nextButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if(vehiclesButton.isSelected()){
					System.out.println("PULSASTE VEHICULOS");
					//Guardo la BBDD en una variable
					bbddName = "VEHICULOS";
					MainInterface.queryBean.setBbddName(bbddName);
				}
				else if(otherButton.isSelected()){
					System.out.println("PULSASTE OTROS");
					bbddName = "OTROS";
					MainInterface.queryBean.setBbddName(bbddName);
				}
				else{
					MensajeDialog.MessageSelectOption();
				}
			}
		});		
	}
}