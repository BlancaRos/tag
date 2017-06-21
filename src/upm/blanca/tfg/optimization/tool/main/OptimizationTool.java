 package upm.blanca.tfg.optimization.tool.main;
import java.sql.SQLException;

import javax.swing.JFrame;


public class OptimizationTool {

	/**
	 * Metodo de arranque de la aplicacion
	 * @throw SQLException sqle
	 */
	public static void main(String[] args) throws SQLException {

		  MainInterface panelPpal = new MainInterface();
		  panelPpal.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}
}