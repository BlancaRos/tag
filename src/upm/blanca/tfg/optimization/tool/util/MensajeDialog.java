package upm.blanca.tfg.optimization.tool.util;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import upm.blanca.tfg.optimization.tool.constants.Constants;
import upm.blanca.tfg.optimization.tool.db.util.OracleDBUtil;
import upm.blanca.tfg.optimization.tool.main.MainInterface;

public class MensajeDialog implements ActionListener{
	public void actionPerformed (ActionEvent e){
		// Aqui el código que queremos que se ejecute cuando tiene lugar la acción.
		// la pulsación del botón, el <INTRO> en el JTextField, elección en el JComboBox, etc.
	}
	public static void MessageDialogue() {
		int eleccion = JOptionPane.showConfirmDialog(null, Constants.CONFIRM_DIALOG, Constants.CONFIRM_DIALOG_TITLE, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		if( eleccion == JOptionPane.NO_OPTION){
			System.exit(0);
		} else {
			//System.out.println(">> " + MainInterface.queryBean.getBbddName() + " - " + MainInterface.queryBean.getQueryType() + " - " + MainInterface.queryBean.getQueryString());
			System.out.println(">> " + MainInterface.queryBean.getBbddName() + " - " + MainInterface.queryBean.getQueryString());

			//PARA IR DIRECTAMENTE A LA PESTAÑA FINAL
			MainInterface.mainInterface.setEnabledAt(MainInterface.mainInterface.indexOfComponent(MainInterface.panel3),true);
			MainInterface.mainInterface.setSelectedIndex(MainInterface.mainInterface.indexOfComponent(MainInterface.panel3));
			for(Component jc:MainInterface.panel3.getComponents()){
				if(jc instanceof JLabel && jc.getName().equals("id2_designQuery")){					
					//VER COMO DIFERENCIAR LOS DOS JLABEL PARA QUE NO ME MODIFIQUE LOS DOS
					((JLabel) jc).setText(MainInterface.queryBean.getQueryString());
				}
			}
			Component c = MainInterface.panel3.getComponent(0);

			try {
				Connection oracleConnection = OracleDBUtil.getConnectionOracle();

				if (oracleConnection != null){
					OracleDBUtil.createQueryOracle(oracleConnection,MainInterface.queryBean);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public static void NextStep(){
		//PARA IR DIRECTAMENTE A LA PESTAÑA DE SELECCIONAR CONSULTA
		//MainInterface.mainInterface.setEnabledAt(MainInterface.mainInterface.indexOfComponent(MainInterface.panel2),true);
		MainInterface.mainInterface.setSelectedIndex(MainInterface.mainInterface.indexOfComponent(MainInterface.panel2));
	}

	public static void MessageSelectOption() {
		JOptionPane.showMessageDialog(null, Constants.ERROR_DIALOG, Constants.ERROR_DIALOG_TITLE, JOptionPane.ERROR_MESSAGE);
	}
}
