package leaves.library.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;
import leaves.library.util.Bookinfo;
import leaves.library.util.DbUtil;
public class BookinfoDao extends AbstractTableModel
{
	private Connection con;
	private Vector columnName;
	private Vector rowData;
	public BookinfoDao()
	{
		
	}
	public ResultSet Query(String sql,String[] paras)throws Exception
	{
		DbUtil  dbutil = new DbUtil();
		con = dbutil.getCon();
		PreparedStatement pstmt = null;
		try
		{
			pstmt =(PreparedStatement) con.prepareStatement(sql);
			if(paras!=null)
			{
				for(int i = 0;i<paras.length;i++)
				{
					pstmt.setString(i+1,paras[i]);
				}
			}
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		return pstmt.executeQuery();
	}
	//查询全部信息，初始化表格
	public void QueryAll(String sql,String[] paras)
	{
		//初始化表格信息
		columnName = new Vector();
		rowData = new Vector();
		columnName.add("ISBN");columnName.add("书名");columnName.add("作者");
		columnName.add("出版社");columnName.add("价格");
		columnName.add("语言");columnName.add("页数");
		try
		{
			ResultSet rs = Query(sql,paras);
			while(rs.next())
			{
				Vector row = new Vector();
				row.add(rs.getString(1));
				row.add(rs.getString(2));
				row.add(rs.getString(3));
				row.add(rs.getString(4));
				row.add(rs.getString(5));
				row.add(rs.getString(6));
				row.add(rs.getInt(7));
				rowData.add(row);
			}		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	//添加图书信息
	public int Add(String sql,Bookinfo bookinfo)throws Exception
	{
		DbUtil  dbutil = new DbUtil();
		con = dbutil.getCon();
		PreparedStatement pstmt = null;
		try
		{
			pstmt =(PreparedStatement) con.prepareStatement(sql);
			pstmt.setString(1, bookinfo.getISBN());
			pstmt.setString(2, bookinfo.getBookName());
			pstmt.setString(3, bookinfo.getAuthor());
			pstmt.setString(4, bookinfo.getPress());
			pstmt.setString(5, bookinfo.getPrice());
			pstmt.setString(6, bookinfo.getLanguage());
			pstmt.setInt(7, bookinfo.getPage());
			
			//String sql="insert into BOOKINFO values(?,?,?,?,?,?,?)";
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return pstmt.executeUpdate();
	}
	//修改图书信息
	public int Update(String sql,Bookinfo bookinfo)throws Exception
	{
		DbUtil  dbutil = new DbUtil();
		con = dbutil.getCon();
		PreparedStatement pstmt = null;
		try
		{
			pstmt = (PreparedStatement) con.prepareStatement(sql);
			pstmt.setString(1, bookinfo.getBookName());
			pstmt.setString(2, bookinfo.getAuthor());
			pstmt.setString(3, bookinfo.getPress());
			pstmt.setString(4, bookinfo.getPrice());
			pstmt.setString(5, bookinfo.getLanguage());
			pstmt.setInt(6, bookinfo.getPage());
			pstmt.setString(7, bookinfo.getISBN());	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return pstmt.executeUpdate();
	}
	public int Delete(String sql,String isbn)throws Exception
	{
		DbUtil  dbutil = new DbUtil();;
		con = dbutil.getCon();
		PreparedStatement pstmt=(PreparedStatement) con.prepareStatement(sql);
		pstmt.setString(1,isbn);
		return pstmt.executeUpdate();
	}
	@Override
	public int getColumnCount()
	{
		return this.columnName.size(); 
	}
	public int getRowCount()
	{
		return this.rowData.size();
	}
	public Object getValueAt(int row, int col)
	{
		if(!rowData.isEmpty())
			return ((Vector)this.rowData.get(row)).get(col);
		else
			System.out.println("数据为空");
		return null;
	} 
	public String getColumnName(int column)
	{
		return (String)this.columnName.get(column);
	}
}
