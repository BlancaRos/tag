package upm.blanca.tfg.optimization.tool.main;

import java.sql.*;

import javax.swing.JOptionPane;

public class ConexionMySQL {

	public String db = "tfg_pruebas";
	public String url = "jdbc:mysql://localhost/"+db;
	public String user = "root";
	public String pass = "root";

	public Connection Conectar(){
		Connection link = null;

		try{
			Class.forName("org.gjt.mm.mysql.Driver");
			link = DriverManager.getConnection(this.url, this.user, this.pass);

		}catch(Exception ex){
			JOptionPane.showMessageDialog(null, ex);
		}

		return link;
	}
	public static void btnProbarconexionActionPerformed() throws SQLException { 
		//public void btnProbarconexionActionPerformed(java.awt.event.ActionEvent evt) { 

		ConexionMySQL mysql = new ConexionMySQL();
		java.sql.Connection cn= mysql.Conectar();
		PreparedStatement stmt;

		if(cn!=null){
			JOptionPane.showMessageDialog(null, "Conectado a la BBDD Interna");
			try{
				cn.close();
			}catch(SQLException ex){
				System.out.println("Error al desconectar "+ex);
			}
		}
		stmt = cn.prepareStatement("INSERT INTO VEHICULOS VALUES (?,?,?,?,?,?,?)");
		stmt.setInt(1, 1);
		stmt.setString(2, "HOLA");
		stmt.setString(3, "HOLA");
		stmt.setString(4, "HOLA");
		stmt.setString(5, "HOLA");
		stmt.setString(6, "HOLA");
		stmt.setString(7, "HOLA");

		stmt.executeUpdate();

	} 
}
