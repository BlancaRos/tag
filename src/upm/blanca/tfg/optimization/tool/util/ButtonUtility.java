package upm.blanca.tfg.optimization.tool.util;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;

import upm.blanca.tfg.optimization.tool.main.MainInterface;


public class ButtonUtility {

	/**
	 * Method to add a listener to the button of descriptions
	 * @param button The button clicked
	 * @param ventana The windows where the button is
	 */
	public static void addListenerClickCancelButtonDescription(JButton button, JFrame ventana) {

		button.addActionListener (new ActionListener(){

			public void actionPerformed (ActionEvent e){
				ventana.setVisible(false);
			}
		});
	}

	/**
	 * 
	 * @param button
	 * @param ventana
	 * @param list
	 */
	public static void addListenerClickOkButtonDescription(JButton button, JFrame ventana, JList list) {

		button.addActionListener (new ActionListener(){
			public void actionPerformed (ActionEvent e){
				JLabel labelDescriptionQuery = new JLabel();
				labelDescriptionQuery.setName("id2_descriptionQuery");
				labelDescriptionQuery.setText(list.getSelectedValue().toString());
				labelDescriptionQuery.setBounds(50, 70, 500, 30);

				MainInterface.panel2.add(labelDescriptionQuery);

				ventana.setVisible(false);
			}
		});
	}
}
