package leaves.library.util;
public class Bookinfo 
{
	private String isbn,bookname,author,press,price,language;
	private int page;
	//�޲ι��캯��
	public Bookinfo()	
	{
		 
	}
	//�вι��캯��
	public Bookinfo(String isbn,String bookname,String author,String press,String price,String language,int page)
	{
		this.isbn = isbn; 
		this.bookname = bookname;
		this.author = author;
		this.press = press;
		this.price = price;
		this.language = language;
		this.page = page;
	}
	//ISBN��get��set��
	public void setISBN(String isbn)
	{
		this.isbn = isbn;
	}
	public String getISBN()
	{
		return isbn;
	}
	//������get��set��
	public void setBookName(String bookname)
	{
		this.bookname = bookname;
	}
	public String getBookName()
	{
		return bookname;
	}
	//���ߵ�get��set��
	public void setAuthor(String author)
	{
		this.author = author;
	}
	public String getAuthor()
	{
		return author;
	}
	//�������get��set��
	public void setPress(String press)
	{
		this.press = press;
	}
	public String getPress()
	{
		return press;
	}
	//�۸��get��set��
	public void setPrice(String price)
	{
		this.price = price;
	}
	public String getPrice()
	{
		return price;
	}
	//���Ե�get��set��
	public void setLanguage(String language)
	{
		this.language = language;
	}
	public String getLanguage()
	{
		return language;
	}
	//ҳ����get��set��
	public void setPage(int page)
	{
		this.page = page;
	}
	public int getPage()
	{
		return page;
	}
}

