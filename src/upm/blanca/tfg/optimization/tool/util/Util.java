package upm.blanca.tfg.optimization.tool.util;

import java.awt.Component;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import upm.blanca.tfg.optimization.tool.constants.Constants;
import upm.blanca.tfg.optimization.tool.db.util.MySQLUtil;
import upm.blanca.tfg.optimization.tool.main.MainInterface;

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
		JPanel panelito = null;
		JPanel panelito2 = null;
		JTabbedPane panelito3 = null;
		
		
		for(Component jc:mainInterface.getComponents()){
			if(jc instanceof JTabbedPane){
				((JTabbedPane) jc).getSelectedComponent();
				panelito3 = (JTabbedPane) jc;
			//////2	
				//for(Component jc:MainInterface.panel2.getComponents()){
//				for(Component j:mainInterface.getComponents()){
//					if(jc instanceof JPanel && j.getName().equals("panel2")){
//						panelito = (JPanel) j; 
//						//				for (Component c : panelito.getComponents()){
//						//					if (c instanceof JPanel && c.getName().equals("panel22")) {
//						//						panelito2 = (JPanel) c;
//						//						break;
//						//					}
//						//				}
//						break;
//					}
//				}
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
}
