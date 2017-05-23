package util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class MySQLConnector {
	
	
	public Connection con;
	public ResultSet rs;
	public Statement st;
	public PreparedStatement ps;
	
	final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	final String PROTOCOL = "jdbc:mysql";
	final String HOST = "localhost";
	final String PORT = "3306";
	final String DATABASE_NAME = "agemows";
	final String USERNAME = "root";
	final String PASSWORD = "";
	
	public MySQLConnector() {
		try{
			Class.forName(DRIVER_NAME);
			
			final String CONNECTION_STRING = PROTOCOL + "://" + HOST + ":" + PORT + "/";
			
			con = DriverManager.getConnection(CONNECTION_STRING + DATABASE_NAME, USERNAME, PASSWORD);
			st = con.createStatement(1004, 1008);
			JOptionPane.showMessageDialog(null, "CONNECT TO DATABASE SUCCESS");
	
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "FAIL TO CONNECT: " + e.getMessage());
		}
	}
	
}
