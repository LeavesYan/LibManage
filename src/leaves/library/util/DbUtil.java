package leaves.library.util;
import java.sql.*;
public class DbUtil
{ 
	private String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=library";
	private String userName="sa";
	private String userPwd="123";
	//打开数据库连接
	public Connection getCon()throws Exception
	{
		Class.forName(driverName);
		Connection con = (Connection) DriverManager.getConnection(dbURL, userName, userPwd);
		return con;		
	}	
	//关闭数据库连接
	public void closeCon (java.sql.Connection con)throws Exception 
	{
		if(con!=null)
		{
			con.close();
		}
	}
}
