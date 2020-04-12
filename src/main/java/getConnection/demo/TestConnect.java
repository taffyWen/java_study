package getConnection.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestConnect {

	public static void main(String[] args) throws SQLException {
		GetConn getConn = new GetConn();
		Connection conn = getConn.getCon();
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
			empno = rs.getString(3); 
		}
		System.out.println(empno);
		
	}
}
