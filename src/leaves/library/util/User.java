package leaves.library.util;

public class User 
{ 
	private String username;
	private String password;
	//�޲ι��캯��
	public User()
	{
		 
	}
	//�вι��캯��
	public User(String username,String password)
	{
		this.username = username;
		this.password = password;
	}
	//�û�����get��set��
	public void serUserName(String username)
	{
		this.username = username;
	}
	public String getUserName()
	{
		return username;
	}
	//�����get��set��
	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getPassword()
	{
		return password;
	}
}
