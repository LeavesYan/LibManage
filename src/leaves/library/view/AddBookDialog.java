package leaves.library.view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import leaves.library.dao.BookinfoDao;
import leaves.library.util.Bookinfo;
public class AddBookDialog extends JDialog
{ 
	//左侧信息标题
	private JLabel ISBNLab,BookNameLab,AuthorLab,PressLab,PriceLab,LanguageLab,PageLab;
	//中间信息填写栏
	private JTextField ISBNTxt,BookNameTxt,AuthorTxt,PressTxt,PriceTxt,PageTxt;
	private JComboBox<String> language;
	//底部按钮
	private JButton addBtn,resetBtn;
	//布局控件
	private JPanel left,center,bottom;
	private BookinfoDao bookinfodao = new BookinfoDao();
	//构造函数
	public AddBookDialog(Frame owner,String title,boolean modal)
	{
	//重写父类方法
		super(owner,title,modal);
	//左侧信息标题
		ISBNLab = new JLabel("  ISBN");
		BookNameLab = new JLabel("  书名");
		AuthorLab = new JLabel("  作者");
		PressLab = new JLabel("  出版社");
		PriceLab = new JLabel("  价格");
		LanguageLab = new JLabel("  语言");
		PageLab = new JLabel("  页数");
	//中间信息填写栏
		ISBNTxt = new JTextField(18);
		BookNameTxt = new JTextField(18);
		AuthorTxt = new JTextField(18);
		PressTxt = new JTextField(18);
		PriceTxt = new JTextField(18);
		PageTxt = new JTextField(18);
		String[] comboMsg = {"中文","英文"};
		language = new JComboBox<String>(comboMsg);
	//底部按钮
		addBtn = new JButton("添加");
		addBtn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt) 
			{
				AddActionEvent(evt);
			}
		});
		resetBtn = new JButton("重置");
		resetBtn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt) 
			{
				ResetActionEvent(evt);
			}
		});
	//左侧布局
		left = new JPanel();
		left.setLayout(new GridLayout(7,1,0,10));
		left.add(ISBNLab);
		left.add(BookNameLab);
		left.add(AuthorLab);
		left.add(PressLab);
		left.add(PriceLab);
		left.add(LanguageLab);
		left.add(PageLab);
	//中间布局
		center = new JPanel();
		center.setLayout(new GridLayout(7,1,0,10));
		center.add(ISBNTxt);
		center.add(BookNameTxt);
		center.add(AuthorTxt);
		center.add(PressTxt);
		center.add(PriceTxt);
		center.add(language);
		center.add(PageTxt);
	//底部按钮布局
		bottom = new JPanel();
		bottom.add(addBtn);
		bottom.add(resetBtn);
	//整体布局
		this.add(left,BorderLayout.WEST);
		this.add(center,BorderLayout.CENTER);
		this.add(bottom,BorderLayout.SOUTH);
	//设置窗口属性
		this.setTitle(title);
		this.setSize(300, 350);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	//增加图书事件处理
	public void AddActionEvent(ActionEvent evt)
	{
		String isbn = this.ISBNTxt.getText();
		String bookname = this.BookNameTxt.getText();
		String author = this.AuthorTxt.getText();
		String press = this.PressTxt.getText();
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
			JOptionPane.showMessageDialog(null,"ISBN不能为空");
		}
		if(bookname==null)
		{
			JOptionPane.showMessageDialog(null,"图书名称不能为空");
		}
		if(author==null)
		{
			JOptionPane.showMessageDialog(null,"图书作者名称不能为空");
		}
		Bookinfo bookinfo = new Bookinfo(isbn,bookname,author,press,price,lang,Integer.parseInt(page));
		try
		{
			String sql="insert into BOOKINFO values(?,?,?,?,?,?,?)";
			int addNum = bookinfodao.Add(sql, bookinfo);
			if(addNum == 1)
			{
				JOptionPane.showMessageDialog(null,"图书信息添加成功");
			}
			else
			{
				JOptionPane.showMessageDialog(null,"图书信息添加失败");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"图书信息添加失败");
		}
	}
	//重置增加图书表单事件处理
	public void ResetActionEvent(ActionEvent evt)
	{
		this.ISBNTxt.setText("");
		this.BookNameTxt.setText("");
		this.AuthorTxt.setText("");
		this.PressTxt.setText("");
		this.PriceTxt.setText("");
		this.PageTxt.setText("");
		if(this.language.getItemCount()>0)
		{
			this.language.setSelectedIndex(0);
		}
	}
}
