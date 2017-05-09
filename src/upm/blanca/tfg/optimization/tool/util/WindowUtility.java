package upm.blanca.tfg.optimization.tool.util;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import upm.blanca.tfg.optimization.tool.constants.Constants;
import upm.blanca.tfg.optimization.tool.main.MainInterface;

public class WindowUtility {

	private static JLabel labelBbddService;
	private static JLabel labelBbddName;
	private static JLabel labelBbddUser;
	private static JLabel labelBbddPass;
	private static JLabel labelAddQuery;
	private static JTextField desiredBbddService;
	private static JTextField desiredBbddName;
	private static JTextField desiredBbddUser;
	private static JTextField desiredBbddPass;
	private static JTextField desiredAddQuery;


	private static JButton nextStep;
	public static String newBbddService = "";
	public static String newBbddName = "";
	public static String newBbddUser = "";
	public static String newBbddPass = "";
	public static String newSqlQuery = "";

	public static void createWindowNewBbdd (){
		JFrame ventanaBBDD = new JFrame("Añadir una BBDD nueva"); 
		ventanaBBDD.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		ventanaBBDD.setBounds(200,150,500,500); 
		ventanaBBDD.setFocusable(true);
		ventanaBBDD.setLayout(new GridLayout()); 

		JPanel panelCreateBBDD = new JPanel(); 
		panelCreateBBDD.setLayout(null);

		//Servicio de la BBDD
		labelBbddService = new JLabel();
		labelBbddService.setName("id1_newServiceBBDD");
		labelBbddService.setText("1) Inserte el servicio de la BBDD:");
		labelBbddService.setBounds(50, 30, 400, 10);	
		desiredBbddService = new JTextField();
		desiredBbddService.setBounds(70, 60, 250, 40);

		//Nombre BBDD
		labelBbddName = new JLabel();
		labelBbddName.setName("id1_newNameBBDD");
		labelBbddName.setText("2) Inserte un nombre para el servicio:");
		labelBbddName.setBounds(50, 120, 400, 10);	
		desiredBbddName = new JTextField();
		desiredBbddName.setBounds(70, 150, 250, 40);

		//Usuario de la BBDD
		labelBbddUser = new JLabel();
		labelBbddUser.setName("id1_newUserBBDD");
		labelBbddUser.setText("2) Introduce el usuario de la BBDD");
		labelBbddUser.setBounds(50, 210, 400, 10);
		desiredBbddUser = new JTextField();
		desiredBbddUser.setBounds(70, 230, 250, 40);

		//Usuario de la BBDD
		labelBbddPass = new JLabel();
		labelBbddPass.setName("id2_newPassBBDD");
		labelBbddPass.setText("3) Introduce la contraseña de la BBDD");
		labelBbddPass.setBounds(50, 300, 400, 10);
		desiredBbddPass = new JTextField();
		desiredBbddPass.setBounds(70, 320, 250, 40);

		//Obtener los datos añadidos
		nextStep = new JButton();
		nextStep.setText(Constants.NEXT_BUTTON);
		nextStep.setBounds(250, 380, 100, 30);
		//OBTENER EL CONTENIDO DE LA CAJA Y GUARDARLO EN UNA VARIABLE
		nextStep.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				newBbddService = desiredBbddService.getText();
				MainInterface.queryBean.setBbddService(newBbddService);
				newBbddName = desiredBbddName.getText(); 
				MainInterface.queryBean.setBbddName(newBbddName);
				//MensajeDialog.MessageDialogue();
				newBbddUser = desiredBbddUser.getText(); 
				MainInterface.queryBean.setBbddUser(newBbddUser);
				newBbddPass = desiredBbddPass.getText();
				MainInterface.queryBean.setBbddPass(newBbddPass);
				System.out.println("----- "+ newBbddService + ":"+ newBbddName + " : " + newBbddUser + " - " + newBbddPass);
				ventanaBBDD.setVisible(false); 

				MensajeDialog.NextStep();
			}
		});

		panelCreateBBDD.add(labelBbddService);
		panelCreateBBDD.add(desiredBbddService);
		panelCreateBBDD.add(labelBbddName);
		panelCreateBBDD.add(desiredBbddName);
		panelCreateBBDD.add(labelBbddUser);
		panelCreateBBDD.add(desiredBbddUser);
		panelCreateBBDD.add(labelBbddPass);
		panelCreateBBDD.add(desiredBbddPass);
		panelCreateBBDD.add(nextStep);
		//Añadir nuevos componentes al panel
		//panelCreateBBDD.paintAll(panel.getGraphics());
		ventanaBBDD.add(panelCreateBBDD); 
		ventanaBBDD.setVisible(true); 
	}

	public static void createWindowAddQuery (){
		JFrame ventanaAddQuery = new JFrame("Añadir una consulta nueva"); 
		ventanaAddQuery.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		ventanaAddQuery.setBounds(200,150,500,500); 
		ventanaAddQuery.setFocusable(true);
		ventanaAddQuery.setLayout(new GridLayout()); 

		JPanel panelCreateAddQuery = new JPanel(); 
		panelCreateAddQuery.setLayout(null);

		//Servicio de la BBDD
		labelAddQuery = new JLabel();
		labelAddQuery.setName("id2_addQuery");
		labelAddQuery.setText("Inserte la consulta que desea añadir:");
		labelAddQuery.setBounds(50, 30, 400, 10);	
		desiredAddQuery = new JTextField();
		desiredAddQuery.setBounds(70, 60, 250, 100);

		//Obtener los datos añadidos
		nextStep = new JButton();
		nextStep.setText(Constants.NEXT_BUTTON);
		nextStep.setBounds(250, 380, 100, 30);
		//OBTENER EL CONTENIDO DE LA CAJA Y GUARDARLO EN UNA VARIABLE
		nextStep.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				newSqlQuery = desiredAddQuery.getText();
				MainInterface.queryBean.setQueryString(newSqlQuery);
				ventanaAddQuery.setVisible(false); 
				MensajeDialog.MessageDialogue();
			}
		});
		
		JButton buttonCancel = new JButton(); 
		buttonCancel.setText("CANCELAR");
		buttonCancel.setBounds(100, 380, 100, 30);
		ButtonUtility.addListenerClickCancelButtonDescription(buttonCancel, ventanaAddQuery);

		panelCreateAddQuery.add(labelAddQuery);
		panelCreateAddQuery.add(desiredAddQuery);
		panelCreateAddQuery.add(nextStep);
		ventanaAddQuery.add(panelCreateAddQuery); 
		ventanaAddQuery.setVisible(true); 
	}

}