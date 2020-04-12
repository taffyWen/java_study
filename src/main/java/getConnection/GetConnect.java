package getConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 使用JDBC连接数据库的五步骤：
 * @author wen
 *
 */
public class GetConnect {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//1. 加载驱动类
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//2. 获取数据连接
		Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:mable", "scott", "521037");
		//3. 创建执行对象	
		
		PreparedStatement stmt=null;
		String  empno = null;
		String sql="select * from emp e where e.ename=?";	
		stmt=conn.prepareStatement(sql);
		stmt.setString(1, "SMITH");		//设置参数
		//stmt.setString(2, pwd);
		ResultSet rs=stmt.executeQuery();//获取查询结果
		//rs=stmt.executeQuery();
		if(rs.next()){					//判断有没有数据
			//u=new Users(rs.getInt(1),rs.getString(2));	//获取查询结果的值
			empno = rs.getString(2); 
		}
		System.out.println(empno);
		
		
	}
}
