package upm.blanca.tfg.optimization.tool.interfaz.util;

import java.awt.Component;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import upm.blanca.tfg.optimization.tool.constants.Constants;
import upm.blanca.tfg.optimization.tool.main.MainInterface;
import upm.blanca.tfg.optimization.tool.mysql.util.MySQLUtil;

public class Util {

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
	public static JTabbedPane searchPane(JTabbedPane mainInterface, String idName) {
		JTabbedPane panelito3 = null;
		
		for(Component jc:mainInterface.getComponents()){
			if(jc instanceof JTabbedPane){
				((JTabbedPane) jc).getSelectedComponent();
				panelito3 = (JTabbedPane) jc;
			}
		}
		return panelito3;
	}

	public static String searchJLabelInfo(JPanel panel, String idName) {
		String selected = Constants.BLANK;
		for(Component jc:panel.getComponents()){

			if(jc instanceof JScrollPane && jc.getName().equals(idName)){
				JScrollPane jsp = (JScrollPane) jc;
				JList listPanel = (JList) jsp.getViewport().getComponent(0);
				selected = listPanel.getSelectedValue().toString();			
			}
		}
		return selected;
	}

	public static void removeComponentFromPanel(JPanel panel, String idNameLabel, String idNameScroll) {
		for(Component jc:panel.getComponents()){
			if(jc instanceof JLabel && jc.getName().equals(idNameLabel)){					
				panel.remove((JLabel) jc);
			}else if(jc instanceof JScrollPane && jc.getName().equals(idNameScroll)){
				panel.remove((JScrollPane) jc);
			}
		}
	}

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
	
	public static void resetApp(){
		try {
			Runtime.getRuntime().exec(Constants.APP);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.exit(0);
	}
}