package leaves.library.view;
import java.awt.event.*;
import javax.swing.*;
import leaves.library.util.User;
import leaves.library.view.MainFrame;
import leaves.library.dao.UserDao;
public class Login extends JFrame
{ 
	private JLabel usernameLab;//用户名
	private JLabel passwordLab;//密码
	private JButton okBtn;//确认按钮
	private JButton resetBtn;//重置按钮
	private JTextField usernameTxt;//用户名文本框
	private JPasswordField passwordJPF;//密码文本框
	private UserDao userDao = new UserDao();//创建一个UserDao对象
	//构造函数
	public Login(String title)
	{
		init(title);
	}
	public void init(String title)
  	{
	//用户名和密码输入控件
		usernameLab = new JLabel("用户名");
		usernameLab.setBounds(100,60,100,30);
		add(usernameLab);	
		usernameTxt = new JTextField();
		usernameTxt.setBounds(200,60,200,30);
		add(usernameTxt);
		passwordLab = new JLabel("密码");
		passwordLab.setBounds(100,135,100,30);
		add(passwordLab);	
		passwordJPF = new JPasswordField();
		passwordJPF.setBounds(200,135,200,30);
		add(passwordJPF);	
	//布置确定登陆按钮并添加监听器
		okBtn = new JButton("确定");
		okBtn.setBounds(140,210,80,30);
		add(okBtn);
		okBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				loginActionEvent(evt);
			}
		});
	//布置重置按钮并添加监听器
		resetBtn = new JButton("重置");
		resetBtn.setBounds(280,210,80,30);
		add(resetBtn);
		resetBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				resetActionEvent(evt);
			}
		});
	//设置窗口属性
		setTitle(title);
		setSize(500,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);	
	}
	//登录事件处理
	public void loginActionEvent(ActionEvent evt)
	{
		String username = new String(this.usernameTxt.getText());
		String password = new String(this.passwordJPF.getPassword());
		//得到的用户名和密码放入user中
		User user = new User(username,password);
		//用户名为空时，设置提醒
		if(username.length()==0)
		{
			JOptionPane.showMessageDialog(null,"用户名不能为空");
		}
		//密码为空时，设置提醒
		if(password.length()==0)
		{
			JOptionPane.showMessageDialog(null,"密码不能为空");
		}
		try
		{
			String sql = "select * from [USER] where 用户名=? and 密码=?";
			User currentUser=userDao.login(sql,user);
			if(currentUser!=null)
			{
			    dispose();
			    new MainFrame("图书管理系统");
			}
			else
			{
				JOptionPane.showMessageDialog(null,"用户名或密码错误");
			}			
		}
		catch(Exception e1)
		{
			e1.printStackTrace();		
		}
	}
	//重置事件处理
	public void resetActionEvent(ActionEvent evt)
	{		
		this.usernameTxt.setText("");
		this.passwordJPF.setText("");		
	}
	public static void main(String[] args) 
	{
		Login frame = new Login("管理员登录");
		frame.setVisible(true);
	}
}

