package leaves.library.view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import leaves.library.dao.BookinfoDao;
import leaves.library.util.Bookinfo;
public class MainFrame extends JFrame
{
	//面板控制
	private JLabel queryLab = null;
	private JTextField queryTxt = null;
	private JButton queryBtn = null;
	private JButton queryAllBtn = null;											  
	private JTable resultTab ;
	private JScrollPane JSP = null;
	private JButton addBtn = null;
	private JButton delBtn = null;
	private JButton updateBtn = null;
	private JPanel top = null;
	private JPanel bottom = null; 
	private JPanel panel1,panel2;
	private BookinfoDao bookinfodao = new BookinfoDao();
	//底部操作信息标题
	private JLabel ISBNLab,BookNameLab,AuthorLab,PressLab,PriceLab,LanguageLab,PageLab;
	//底部操作信息填写栏
	private JTextField ISBNTxt,BookNameTxt,AuthorTxt,PressTxt,PriceTxt,PageTxt;
	private JComboBox<String> language;
	//构造函数
	public MainFrame(String title)
	{
	//顶部查询栏控件
		queryLab = new JLabel("请输入书名");
		queryTxt = new JTextField(20);
		//布置查询按钮并添加监听器
		queryBtn = new JButton("查询");
		queryBtn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				QueryActionEvent(e);
			}
		});
		//布置查询全部按钮并添加监听器
		queryAllBtn = new JButton("全部");
		queryAllBtn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt) 
			{
				QueryAllActionEvent(evt);
			}
		});
	//布置顶部查询栏
		top = new JPanel();
		top.add(queryLab);
		top.add(queryTxt);
		top.add(queryBtn);
		top.add(queryAllBtn);				
	//中间层显示栏
		bookinfodao = new BookinfoDao();
		String sql = "select * from BOOKINFO";
		bookinfodao.QueryAll(sql,null);
		//布置确定登陆按钮并添加监听器
		resultTab = new JTable(bookinfodao);
		resultTab.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent evt)
			{
				resultTabMousePressed(evt);
			}
		});
		JSP = new JScrollPane(resultTab);
	//底部操作栏控件
		//信息标题
		ISBNLab = new JLabel("ISBN");
		BookNameLab = new JLabel("书名");
		AuthorLab = new JLabel("作者");
		PressLab = new JLabel("出版社");
		PriceLab = new JLabel("价格");
		LanguageLab = new JLabel("语言");
		PageLab = new JLabel("页数");
		//信息填写栏
		ISBNTxt = new JTextField(20);
		ISBNTxt.setEditable(false);
		BookNameTxt = new JTextField(25);
		AuthorTxt = new JTextField(10);
		PressTxt = new JTextField(15);
		PriceTxt = new JTextField(6);
		PageTxt = new JTextField(6);
		String[] comboMsg = {"中文","英文"}; 
		language = new JComboBox<String>(comboMsg);		
		//底部按钮
		//创建添加按钮并添加监听器
		addBtn = new JButton("增加信息");
		addBtn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt) 
			{
				AddBookActionEvent(evt);
			}
		});
		//创建删除按钮并添加监听器
		delBtn = new JButton("删除信息");
		delBtn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt) 
			{
				DelBookActionEvent(evt);
			}
		});
		//创建更新按钮并添加监听器
		updateBtn = new JButton("修改信息");
		updateBtn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt) 
			{
				UpdateBookActionPerformed(evt);
			}
		});
	//布置底部操作栏
		bottom = new JPanel();
		bottom.setLayout(new GridLayout(2,1,6,6));		
		panel1 = new JPanel();
		panel1.add(ISBNLab);panel1.add(ISBNTxt);panel1.add(BookNameLab);panel1.add(BookNameTxt);
		panel1.add(AuthorLab);panel1.add(AuthorTxt);panel1.add(PriceLab);panel1.add(PriceTxt);
		panel1.add(LanguageLab);panel1.add(language);panel1.add(PageLab);panel1.add(PageTxt);
		panel1.add(PressLab);panel1.add(PressTxt);
		panel2 = new JPanel();
		panel2.add(addBtn);panel2.add(delBtn);panel2.add(updateBtn);				
		bottom.add(panel1);
		bottom.add(panel2);
	//全局布置
		this.add(top,BorderLayout.NORTH);
		this.add(JSP, BorderLayout.CENTER);
		this.add(bottom, BorderLayout.SOUTH);
	//设置窗口属性
		this.setTitle(title);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());//将窗口大小设置为全屏
		this.setVisible(true);
	}
	//图书查询事件处理
	public void QueryActionEvent(ActionEvent evt)
	{
		String bookname = new String(this.queryTxt.getText());
		try
		{
			if(bookname.length()!=0)
			{
				//姓名输入有效时，执行查询
				String sql = "select * from BOOKINFO where 书名=?";
				String[] paras = {bookname};
				ResultTableUpdate(sql,paras);
			}
			else
			{
				//姓名为空时，设置提醒
				JOptionPane.showMessageDialog(this,"姓名输入不能为空");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	//查询全部图书事件处理
	public void QueryAllActionEvent(ActionEvent evt)
	{
		try
		{
			String sql = "select * from BOOKINFO";
			ResultTableUpdate(sql,null);
			this.queryTxt.setText("");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	//表格行点击事件处理
	public void resultTabMousePressed(MouseEvent evt)
	{
		int row=this.resultTab.getSelectedRow();
		this.ISBNTxt.setText((String)resultTab.getValueAt(row, 0));
		this.BookNameTxt.setText((String)resultTab.getValueAt(row, 1));
		this.AuthorTxt.setText((String)resultTab.getValueAt(row, 2));
		this.PressTxt.setText((String)resultTab.getValueAt(row, 3));
		this.PriceTxt.setText((String)resultTab.getValueAt(row, 4)+"");
		String lang=(String)this.resultTab.getValueAt(row, 5);
		int n=this.language.getItemCount();
		for(int i=0;i<n;i++)
		{
			String item=this.language.getItemAt(i);
			if(item.equals(lang))
			{
				this.language.setSelectedIndex(i);
			}
		}
		this.PageTxt.setText((int)resultTab.getValueAt(row, 6)+"");
	}
	//添加图书事件处理
	public void AddBookActionEvent(ActionEvent evt)
	{
		try
		{
			//实例化AddBookDialog类
			new AddBookDialog(this,"添加学生信息",true);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
	}
	//修改图书事件处理
	public void UpdateBookActionPerformed(ActionEvent evt)
	{
		try 
		{
			String isbn = this.ISBNTxt.getText();
			String bookname = this.BookNameTxt.getText();
			String author = this.AuthorTxt.getText();
			String press = this.PriceTxt.getText();
			String price = this.PriceTxt.getText();
			String page = this.PageTxt.getText();
			String lang = null;
			int langIndex=this.language.getSelectedIndex();
			if(langIndex==0)
			{
				lang = "中文";
			}
			else
			{
				lang = "英文";
			}
			if(isbn==null)
			{
				//ISBN为空时，设置提醒
				JOptionPane.showMessageDialog(null, "ISBN不能为空");
			}
			if(bookname==null)
			{
				//图书名称为空时，设置提醒
				JOptionPane.showMessageDialog(null, "图书名称不能为空");
			}			
			Bookinfo bookinfo=new Bookinfo(isbn,bookname,author,press,price,lang,Integer.parseInt(page));		
			try
			{
				String sql="update BOOKINFO set 书名=?,作者=?,出版社=?,价格=?,语言=?,页数=? where ISBN=?";
				int addNum = bookinfodao.Update(sql,bookinfo);
				if(addNum==1)
				{
					String sql1 = "select * from BOOKINFO";
					JOptionPane.showMessageDialog(null, "修改成功");
					resetValue();
					ResultTableUpdate(sql1,null);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "修改失败");
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "修改失败");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
	}
	//删除图书事件处理
	public void DelBookActionEvent(ActionEvent evt)
	{
		try
		{
			String isbn = this.ISBNTxt.getText();
			int n=JOptionPane.showConfirmDialog(null, "确定要删除这条记录么");
			if(n==0)
			{
				try
				{
					String sql="delete from BOOKINFO where ISBN=?";
					int deleteNum=bookinfodao.Delete(sql,isbn);
					if(deleteNum==1)
					{
						String sql1 = "select * from BOOKINFO";
						JOptionPane.showMessageDialog(null, "删除成功");
						this.resetValue();
						ResultTableUpdate(sql1,null);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "删除失败");
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "删除失败");
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace(); 
		}		
	}
	//重置表单
	private void resetValue()
	{
		this.ISBNTxt.setText("");
		this.BookNameTxt.setText("");
		this.AuthorTxt.setText("");
		this.PriceTxt.setText("");
		this.PriceTxt.setText("");
		this.PageTxt.setText("");
		if(this.language.getItemCount()>0)
		{
			this.language.setSelectedIndex(0);
		}
	}
	//更新表格信息
	public void ResultTableUpdate(String sql,String[] paras)
	{
		try
		{
			bookinfodao.QueryAll(sql,paras);
			resultTab.setModel(bookinfodao);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
