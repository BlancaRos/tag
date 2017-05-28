package upm.blanca.tfg.optimization.tool.util;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import upm.blanca.tfg.optimization.tool.db.util.MySQLUtil;
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
				labelDescriptionQuery.setName("id2_descriptionSelectedQuery");
				
				String descriptionSelected = list.getSelectedValue().toString();
				labelDescriptionQuery.setText(descriptionSelected);
				labelDescriptionQuery.setBounds(50, 70, 500, 30);
				labelDescriptionQuery.setForeground(new Color(27,85,131));

				MainInterface.queryBean.setQueryDescription(descriptionSelected);
				MainInterface.panel2.add(labelDescriptionQuery);
				
				try {
					List<String> sqlList = MySQLUtil.getSelectedSqlQuery(descriptionSelected);
					//MySQLUtil.getReportValuesQuery(descriptionSelected);
					String[] sqlQueries = new String[sqlList.size()];
					sqlQueries = (String[]) sqlList.toArray(sqlQueries);
					JList lista = new JList(sqlQueries);
					lista.setName("id2_sqlSelectedFromScroll");
					
					JScrollPane barraDesplazamiento = new JScrollPane(lista); 
					barraDesplazamiento.setName("id2_scrollPaneSqlQueries");
					barraDesplazamiento.setBounds(50,190,400,100);
					MainInterface.panel2.add(barraDesplazamiento);
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				ventana.setVisible(false);
			}
		});
	}
}
