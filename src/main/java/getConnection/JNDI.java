package getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class JNDI {
	
	public Connection getCon(){
		Connection conn=null;
		PreparedStatement ps=null;
		//初始化上下文
		try {
			Context ct= new InitialContext();
			DataSource ds=(DataSource) ct.lookup("java:comp/env/jdbc/mable");//前缀规定java:comp/env/name
			conn=ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void main(String[] args) {
		Connection conn=null;
		PreparedStatement ps=null;
		//初始化上下文
		//因为通过JNDI获取连接，程序必须通过Tomcat容器来加载连接，即通过web.xml来建立连接，如果只是在Java代码里面测试，是无法获取到连接的
		try {
			Context ct= new InitialContext();
			DataSource ds=(DataSource) ct.lookup("java:comp/env/jdbc/mable");//前缀规定java:comp/env/name
			conn=ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(conn);
	}
}
