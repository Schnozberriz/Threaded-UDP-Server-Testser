package handlers;
import java.sql.*;
public class SQLHandler {

	Connection conn = null;
	Statement stmt = null;
	
	public SQLHandler(String[] values)
	{
			
			
			try
			{
				try
				{
					Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
				}
				catch (Exception e)
				{
					System.out.println(e);
					e.printStackTrace();
				}
				conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/SINKLOG_DB","root","password");
				System.out.println("Connection Established");
				stmt = (Statement) conn.createStatement();
				String Query1 = "INSERT INTO devicelogs VALUES(";
				for(int i = 0 ; i < 9;i++)
					Query1 += values[i]+',';
				Query1+="now())";
				stmt.executeUpdate(Query1);
				System.out.println("Inserted Data Successfully");
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				try
				{
					if (stmt != null)
					conn.close();
				}
				catch (SQLException se) {}
				try
				{
					if (conn != null)
						conn.close();
				}
				catch (SQLException se)
				{
					se.printStackTrace();
				}
			}
			System.out.println("Data logged");
	}

}

