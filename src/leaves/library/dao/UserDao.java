package leaves.library.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import leaves.library.util.DbUtil;
import leaves.library.util.User;
public class UserDao 
{
	private Connection con;
	//µÇÂ½ÑéÖ¤
	public User login(String sql,User user)throws Exception 
	{
		DbUtil  dbutil = new DbUtil();
		con = dbutil.getCon();
		PreparedStatement pstmt = null;
		User resultUser = null;
		try 
		{
			pstmt = (PreparedStatement) con.prepareStatement(sql);
			pstmt.setString(1,user.getUserName());
			pstmt.setString(2,user.getPassword());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
			{
				resultUser = new User();
				resultUser.serUserName(rs.getString(1));
				resultUser.setPassword(rs.getString(2));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return resultUser;
	}
}