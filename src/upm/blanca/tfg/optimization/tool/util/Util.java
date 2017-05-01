package upm.blanca.tfg.optimization.tool.util;

import java.awt.Component;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import upm.blanca.tfg.optimization.tool.db.util.MySQLUtil;
import upm.blanca.tfg.optimization.tool.main.MainInterface;

public class Util {

	public static String searchScrollPaneInfo(JPanel panel, String idName) {
		String selected = "";
		for(Component jc:panel.getComponents()){

			if(jc instanceof JScrollPane && jc.getName().equals(idName)){
				JScrollPane jsp = (JScrollPane) jc;
				JList listPanel = (JList) jsp.getViewport().getComponent(0);
				selected = listPanel.getSelectedValue().toString();			
			}
		}
		return selected;
	}
	
	public static String searchJLabelInfo(JPanel panel, String idName) {
		String selected = "";
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
}
