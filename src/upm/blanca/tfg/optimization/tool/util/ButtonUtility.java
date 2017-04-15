package upm.blanca.tfg.optimization.tool.util;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;

import upm.blanca.tfg.optimization.tool.main.MainInterface;


public class ButtonUtility {

	static boolean isClicked = false;

	public static void addListenerClickButton(JButton boton) {
		
		boton.addActionListener (new ActionListener(){
			public void actionPerformed (ActionEvent e){
				isClicked = true;
				pulsedButton(isClicked); // añadir logica necesaria
			}
		});
	}

	public static void addListenerClickCancelButtonDescription(JButton button, JFrame ventana) {

		button.addActionListener (new ActionListener(){

			public void actionPerformed (ActionEvent e){
				System.out.println("aaaaaaaaa");
				ventana.setVisible(false);
			}
		});
	}

	public static void addListenerClickOkButtonDescription(JButton button, JFrame ventana, JList list) {

		button.addActionListener (new ActionListener(){
			public void actionPerformed (ActionEvent e){
				System.out.println("ooooo " + list.getSelectedValue().toString());
				JLabel labelDescriptionQuery = new JLabel();
				labelDescriptionQuery.setName("id2_descriptionQuery");
				labelDescriptionQuery.setText(list.getSelectedValue().toString());
				labelDescriptionQuery.setBounds(50, 70, 500, 30);
				
				MainInterface.panel2.add(labelDescriptionQuery);
			
				ventana.setVisible(false);
			}
		});
	}

	/*public static JComponent creaBotonesPanel4(JPanel panel) { 
		//Se crean los botones ...
		JButton button1 = new JButton("CANCELAR"); 
		addListenerClickButton(button1);

		JButton button2 = new JButton("OK"); 
		//panel3.add( new JButton("Norte"), BorderLayout.NORTH );
		panel.add(button1);
		panel.add(button2);
		return panel;
	}*/

	public static void pulsedButton(boolean isClicked){
		if(isClicked){
			System.out.println("Botón cancelado.");
			MensajeDialog.MessageDialogue();
		}
	}
}
