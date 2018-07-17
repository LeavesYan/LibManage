package leaves.library.view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import leaves.library.dao.BookinfoDao;
import leaves.library.util.Bookinfo;
public class AddBookDialog extends JDialog
{ 
	//�����Ϣ����
	private JLabel ISBNLab,BookNameLab,AuthorLab,PressLab,PriceLab,LanguageLab,PageLab;
	//�м���Ϣ��д��
	private JTextField ISBNTxt,BookNameTxt,AuthorTxt,PressTxt,PriceTxt,PageTxt;
	private JComboBox<String> language;
	//�ײ���ť
	private JButton addBtn,resetBtn;
	//���ֿؼ�
	private JPanel left,center,bottom;
	private BookinfoDao bookinfodao = new BookinfoDao();
	//���캯��
	public AddBookDialog(Frame owner,String title,boolean modal)
	{
	//��д���෽��
		super(owner,title,modal);
	//�����Ϣ����
		ISBNLab = new JLabel("  ISBN");
		BookNameLab = new JLabel("  ����");
		AuthorLab = new JLabel("  ����");
		PressLab = new JLabel("  ������");
		PriceLab = new JLabel("  �۸�");
		LanguageLab = new JLabel("  ����");
		PageLab = new JLabel("  ҳ��");
	//�м���Ϣ��д��
		ISBNTxt = new JTextField(18);
		BookNameTxt = new JTextField(18);
		AuthorTxt = new JTextField(18);
		PressTxt = new JTextField(18);
		PriceTxt = new JTextField(18);
		PageTxt = new JTextField(18);
		String[] comboMsg = {"����","Ӣ��"};
		language = new JComboBox<String>(comboMsg);
	//�ײ���ť
		addBtn = new JButton("���");
		addBtn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt) 
			{
				AddActionEvent(evt);
			}
		});
		resetBtn = new JButton("����");
		resetBtn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt) 
			{
				ResetActionEvent(evt);
			}
		});
	//��಼��
		left = new JPanel();
		left.setLayout(new GridLayout(7,1,0,10));
		left.add(ISBNLab);
		left.add(BookNameLab);
		left.add(AuthorLab);
		left.add(PressLab);
		left.add(PriceLab);
		left.add(LanguageLab);
		left.add(PageLab);
	//�м䲼��
		center = new JPanel();
		center.setLayout(new GridLayout(7,1,0,10));
		center.add(ISBNTxt);
		center.add(BookNameTxt);
		center.add(AuthorTxt);
		center.add(PressTxt);
		center.add(PriceTxt);
		center.add(language);
		center.add(PageTxt);
	//�ײ���ť����
		bottom = new JPanel();
		bottom.add(addBtn);
		bottom.add(resetBtn);
	//���岼��
		this.add(left,BorderLayout.WEST);
		this.add(center,BorderLayout.CENTER);
		this.add(bottom,BorderLayout.SOUTH);
	//���ô�������
		this.setTitle(title);
		this.setSize(300, 350);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	//����ͼ���¼�����
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
			lang = "����";
		}
		else
		{
			lang = "Ӣ��";
		}
		if(isbn==null)
		{
			JOptionPane.showMessageDialog(null,"ISBN����Ϊ��");
		}
		if(bookname==null)
		{
			JOptionPane.showMessageDialog(null,"ͼ�����Ʋ���Ϊ��");
		}
		if(author==null)
		{
			JOptionPane.showMessageDialog(null,"ͼ���������Ʋ���Ϊ��");
		}
		Bookinfo bookinfo = new Bookinfo(isbn,bookname,author,press,price,lang,Integer.parseInt(page));
		try
		{
			String sql="insert into BOOKINFO values(?,?,?,?,?,?,?)";
			int addNum = bookinfodao.Add(sql, bookinfo);
			if(addNum == 1)
			{
				JOptionPane.showMessageDialog(null,"ͼ����Ϣ��ӳɹ�");
			}
			else
			{
				JOptionPane.showMessageDialog(null,"ͼ����Ϣ���ʧ��");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"ͼ����Ϣ���ʧ��");
		}
	}
	//��������ͼ����¼�����
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
