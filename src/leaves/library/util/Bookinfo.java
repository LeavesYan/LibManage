package leaves.library.util;
public class Bookinfo 
{
	private String isbn,bookname,author,press,price,language;
	private int page;
	//无参构造函数
	public Bookinfo()	
	{
		 
	}
	//有参构造函数
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
	//ISBN的get、set器
	public void setISBN(String isbn)
	{
		this.isbn = isbn;
	}
	public String getISBN()
	{
		return isbn;
	}
	//书名的get、set器
	public void setBookName(String bookname)
	{
		this.bookname = bookname;
	}
	public String getBookName()
	{
		return bookname;
	}
	//作者的get、set器
	public void setAuthor(String author)
	{
		this.author = author;
	}
	public String getAuthor()
	{
		return author;
	}
	//出版社的get、set器
	public void setPress(String press)
	{
		this.press = press;
	}
	public String getPress()
	{
		return press;
	}
	//价格的get、set器
	public void setPrice(String price)
	{
		this.price = price;
	}
	public String getPrice()
	{
		return price;
	}
	//语言的get、set器
	public void setLanguage(String language)
	{
		this.language = language;
	}
	public String getLanguage()
	{
		return language;
	}
	//页数的get、set器
	public void setPage(int page)
	{
		this.page = page;
	}
	public int getPage()
	{
		return page;
	}
}

