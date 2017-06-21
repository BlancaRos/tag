package upm.blanca.tfg.optimization.tool.interfaz.util;

import java.awt.Component;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import upm.blanca.tfg.optimization.tool.constants.Constants;
import upm.blanca.tfg.optimization.tool.main.MainInterface;

public class Util {

	/**
	 * Metodo que devuelve la BBDD seleccionada por el usuario
	 * @param panel - panel en el cual está la informacion necesaria
	 * @param idName - id del desplegable
	 * @return String Valor - valor seleccionado de la lista.
	 */
	@SuppressWarnings("rawtypes")
	public static String searchScrollPaneInfo(JPanel panel, String idName) {
		String selected = Constants.BLANK;

		for(Component jc:panel.getComponents()){
			if(jc instanceof JScrollPane && jc.getName().equals(idName)){
				JScrollPane jsp = (JScrollPane) jc;
				JList listPanel = (JList) jsp.getViewport().getComponent(0);
				if(listPanel.getSelectedValue() != null){
					selected = listPanel.getSelectedValue().toString();	
				}
			}
		}
		return selected;
	}

	/**
	 * Metodo que elimina los componentes del panel recibido como parametro
	 * @param panel - panel del cual se quiere eliminar componentes
	 * @param idNameLabel - id de la etiqueta
	 * @param idNameScroll - id del desplegable a eliminar
	 */
	public static void removeComponentFromPanel(JPanel panel, String idNameLabel, String idNameScroll) {
		for(Component jc:panel.getComponents()){
			if(jc instanceof JLabel && jc.getName().equals(idNameLabel)){					
				panel.remove((JLabel) jc);
			}else if(jc instanceof JScrollPane && jc.getName().equals(idNameScroll)){
				panel.remove((JScrollPane) jc);
			}
		}
	}

	/**
	 * Metodo que introduce en el panel de los informes la informacion que desea el usuario
	 * @param panel - panel donde introducir la informacion
	 * @param idNameLabel1 - id de la etiqueta 1
	 * @param idNameLabel2 - id de la etiqueta 2
	 */
	public static void setLabelsInfoPanel3(JPanel panel, String idNameLabel1, String idNameLabel2) {
		for(Component jcomp:panel.getComponents()){
			if(jcomp instanceof JLabel && jcomp.getName().equals(idNameLabel1)){					
				((JLabel) jcomp).setText(MainInterface.queryBean.getQueryString());
			}
			if(jcomp instanceof JLabel && jcomp.getName().equals(idNameLabel2)){					
				((JLabel) jcomp).setText(MainInterface.queryBean.getQueryDescription());
			}
		}
	}
	
	/**
	 * Metodo que resetea la aplicacion
	 */
	public static void resetApp(){
		try {
			Runtime.getRuntime().exec(Constants.APP);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.exit(0);
	}
}