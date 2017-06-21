package upm.blanca.tfg.optimization.tool.interfaz.util;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import upm.blanca.tfg.optimization.tool.constants.Constants;
import upm.blanca.tfg.optimization.tool.main.MainInterface;

public class WindowUtility {

	private static JLabel labelBbddService;
	private static JLabel labelBbddName;
	private static JLabel labelBbddUser;
	private static JLabel labelBbddPass;
	private static JLabel labelAddQuery;
	private static JTextArea desiredBbddService;
	private static JTextArea desiredBbddName;
	private static JTextArea desiredBbddUser;
	private static JTextArea desiredBbddPass;
	private static JTextArea desiredAddQuery;

	private static JButton nextStep;
	public static String newBbddService = Constants.BLANK;
	public static String newBbddName = Constants.BLANK;
	public static String newBbddUser = Constants.BLANK;
	public static String newBbddPass = Constants.BLANK;
	public static String newSqlQuery = Constants.BLANK;

	/**
	 * Metodo que crea una ventana en la que aparece lo necesario para introducir una nueva BBDD externa 
	 */
	public static void createWindowNewBbdd (){
		JFrame ventanaBBDD = new JFrame(Constants.ADD_NEW_DB); 
		ventanaBBDD.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		ventanaBBDD.setBounds(200,150,400,500); 
		ventanaBBDD.setFocusable(true);
		ventanaBBDD.setLayout(new GridLayout()); 

		JPanel panelCreateBBDD = new JPanel(); 
		panelCreateBBDD.setLayout(null);

		//Servicio de la BBDD
		labelBbddService = new JLabel();
		labelBbddService.setName("id1_newServiceBBDD");
		labelBbddService.setText(Constants.INSERT_DB_SERVICE);
		labelBbddService.setBounds(50, 30, 400, 10);	
		desiredBbddService = new JTextArea();
		desiredBbddService.setBounds(70, 60, 250, 40);

		//Nombre BBDD
		labelBbddName = new JLabel();
		labelBbddName.setName("id1_newNameBBDD");
		labelBbddName.setText(Constants.INSERT_DB_NAME);
		labelBbddName.setBounds(50, 120, 400, 10);	
		desiredBbddName = new JTextArea();
		desiredBbddName.setBounds(70, 150, 250, 40);

		//Usuario de la BBDD
		labelBbddUser = new JLabel();
		labelBbddUser.setName("id1_newUserBBDD");
		labelBbddUser.setText(Constants.INSERT_DB_USER);
		labelBbddUser.setBounds(50, 210, 400, 10);
		desiredBbddUser = new JTextArea();
		desiredBbddUser.setBounds(70, 230, 250, 40);

		//Contraseña de la BBDD
		labelBbddPass = new JLabel();
		labelBbddPass.setName("id2_newPassBBDD");
		labelBbddPass.setText(Constants.INSERT_DB_PASS);
		labelBbddPass.setBounds(50, 300, 400, 10);
		desiredBbddPass = new JTextArea();
		desiredBbddPass.setBounds(70, 320, 250, 40);

		//Obtener los datos añadidos
		nextStep = new JButton();
		nextStep.setText(Constants.NEXT_BUTTON);
		nextStep.setBounds(220, 380, 100, 50);
		//OBTENER EL CONTENIDO DE LA CAJA Y GUARDARLO EN UNA VARIABLE
		nextStep.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				newBbddService = desiredBbddService.getText();
				MainInterface.queryBean.setBbddService(newBbddService);
				newBbddName = desiredBbddName.getText(); 
				MainInterface.queryBean.setBbddName(newBbddName);
				newBbddUser = desiredBbddUser.getText(); 
				MainInterface.queryBean.setBbddUser(newBbddUser);
				newBbddPass = desiredBbddPass.getText();
				MainInterface.queryBean.setBbddPass(newBbddPass);
				ventanaBBDD.setVisible(false); 

				MensajeDialog.nextStep();
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
		ventanaBBDD.add(panelCreateBBDD); 
		ventanaBBDD.setVisible(true); 
	}

	/**
	 * Metodo que crea una ventana en la que aparece lo necesario para crear una nueva consulta
	 */
	public static void createWindowAddQuery (){
		JFrame ventanaAddQuery = new JFrame(Constants.INSERT_NEW_QUERY); 
		ventanaAddQuery.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		ventanaAddQuery.setBounds(200,150,500,400); 
		ventanaAddQuery.setFocusable(true);
		ventanaAddQuery.setLayout(new GridLayout()); 

		JPanel panelCreateAddQuery = new JPanel(); 
		panelCreateAddQuery.setLayout(null);

		//Servicio de la BBDD
		labelAddQuery = new JLabel();
		labelAddQuery.setName("id2_addQuery");
		labelAddQuery.setText(Constants.INSERT_NEW_QUERY_SQL);
		labelAddQuery.setBounds(50, 30, 400, 15);	
		desiredAddQuery = new JTextArea();
		desiredAddQuery.setLineWrap(true);
		desiredAddQuery.setBounds(50, 60, 400, 180);
		desiredAddQuery.setForeground(new Color(6,57,113));

		//Obtener los datos añadidos
		nextStep = new JButton();
		nextStep.setText(Constants.NEXT_BUTTON);
		nextStep.setBounds(350, 250, 100, 50);
		//OBTENER EL CONTENIDO DE LA CAJA Y GUARDARLO EN UNA VARIABLE
		nextStep.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				newSqlQuery = desiredAddQuery.getText();
				MainInterface.queryBean.setQueryString(newSqlQuery);
				ventanaAddQuery.setVisible(false); 
				MensajeDialog.messageDialogue();
			}
		});

		panelCreateAddQuery.add(labelAddQuery);
		panelCreateAddQuery.add(desiredAddQuery);
		panelCreateAddQuery.add(nextStep);
		ventanaAddQuery.add(panelCreateAddQuery); 
		ventanaAddQuery.setVisible(true); 
	}
}