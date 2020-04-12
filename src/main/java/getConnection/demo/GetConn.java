package getConnection.demo;

import getConnection.ConfigManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetConn {
	

	//读取
	private String deriver_jdbc= ConfigManager.getInstance().getString("driver");
	private String src_jdbc=ConfigManager.getInstance().getString("url");
	private String count_jdbc=ConfigManager.getInstance().getString("username");
	private String password=ConfigManager.getInstance().getString("password");
	
	
	public Connection getCon(){
		Connection conn=null;
		try {
			Class.forName(deriver_jdbc);
			conn=DriverManager.getConnection(src_jdbc, count_jdbc, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return conn;
	}
}
