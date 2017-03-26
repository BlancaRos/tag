package upm.blanca.tfg.optimization.tool.util;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;


public class ButtonUtility {

	static boolean isClicked = false;

	public static void addListenerClickButton(JButton boton) {
		final int var=3;

		boton.addActionListener (new ActionListener(){
			public void actionPerformed (ActionEvent e){
				isClicked = true;
				pulsedButton(isClicked); // añadir logica necesaria
				// Aquí está accesible unaVariable
				System.out.println(var);
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