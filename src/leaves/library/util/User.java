package leaves.library.util;

public class User 
{ 
	private String username;
	private String password;
	//无参构造函数
	public User()
	{
		 
	}
	//有参构造函数
	public User(String username,String password)
	{
		this.username = username;
		this.password = password;
	}
	//用户名的get、set器
	public void serUserName(String username)
	{
		this.username = username;
	}
	public String getUserName()
	{
		return username;
	}
	//密码的get、set器
	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getPassword()
	{
		return password;
	}
}
