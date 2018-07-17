package leaves.library.view;
import java.awt.event.*;
import javax.swing.*;
import leaves.library.util.User;
import leaves.library.view.MainFrame;
import leaves.library.dao.UserDao;
public class Login extends JFrame
{ 
	private JLabel usernameLab;//�û���
	private JLabel passwordLab;//����
	private JButton okBtn;//ȷ�ϰ�ť
	private JButton resetBtn;//���ð�ť
	private JTextField usernameTxt;//�û����ı���
	private JPasswordField passwordJPF;//�����ı���
	private UserDao userDao = new UserDao();//����һ��UserDao����
	//���캯��
	public Login(String title)
	{
		init(title);
	}
	public void init(String title)
  	{
	//�û�������������ؼ�
		usernameLab = new JLabel("�û���");
		usernameLab.setBounds(100,60,100,30);
		add(usernameLab);	
		usernameTxt = new JTextField();
		usernameTxt.setBounds(200,60,200,30);
		add(usernameTxt);
		passwordLab = new JLabel("����");
		passwordLab.setBounds(100,135,100,30);
		add(passwordLab);	
		passwordJPF = new JPasswordField();
		passwordJPF.setBounds(200,135,200,30);
		add(passwordJPF);	
	//����ȷ����½��ť����Ӽ�����
		okBtn = new JButton("ȷ��");
		okBtn.setBounds(140,210,80,30);
		add(okBtn);
		okBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				loginActionEvent(evt);
			}
		});
	//�������ð�ť����Ӽ�����
		resetBtn = new JButton("����");
		resetBtn.setBounds(280,210,80,30);
		add(resetBtn);
		resetBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				resetActionEvent(evt);
			}
		});
	//���ô�������
		setTitle(title);
		setSize(500,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);	
	}
	//��¼�¼�����
	public void loginActionEvent(ActionEvent evt)
	{
		String username = new String(this.usernameTxt.getText());
		String password = new String(this.passwordJPF.getPassword());
		//�õ����û������������user��
		User user = new User(username,password);
		//�û���Ϊ��ʱ����������
		if(username.length()==0)
		{
			JOptionPane.showMessageDialog(null,"�û�������Ϊ��");
		}
		//����Ϊ��ʱ����������
		if(password.length()==0)
		{
			JOptionPane.showMessageDialog(null,"���벻��Ϊ��");
		}
		try
		{
			String sql = "select * from [USER] where �û���=? and ����=?";
			User currentUser=userDao.login(sql,user);
			if(currentUser!=null)
			{
			    dispose();
			    new MainFrame("ͼ�����ϵͳ");
			}
			else
			{
				JOptionPane.showMessageDialog(null,"�û������������");
			}			
		}
		catch(Exception e1)
		{
			e1.printStackTrace();		
		}
	}
	//�����¼�����
	public void resetActionEvent(ActionEvent evt)
	{		
		this.usernameTxt.setText("");
		this.passwordJPF.setText("");		
	}
	public static void main(String[] args) 
	{
		Login frame = new Login("����Ա��¼");
		frame.setVisible(true);
	}
}

