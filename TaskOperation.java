package com.soltan;

import java.sql.*;  
import java.util.ArrayList;  
import java.util.List;  
import com.soltan.Task;

public class TaskOperation {

	
	public static Connection getConnection(){  
	    Connection con=null;  
	    try{  
	        Class.forName("com.mysql.jdbc.Driver");  
	        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/taskdb","root","00950096./joe./");  
	    }catch(Exception e){System.out.println(e);}  
	    return con;  
	}  
	



	public static int save(Task u) {
		int status = 0;
		try {
			Connection con = getConnection();

			PreparedStatement ps = con.prepareStatement("insert into tasktbl(Name) values(?)");
			ps.setString(1, u.getName());

			status = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}  



public static List<Task> getAllRecords(){  
    List<Task> list=new ArrayList<Task>();  
      
    try{  
        Connection con=getConnection();  
        PreparedStatement ps=con.prepareStatement("select * from tasktbl");  
        ResultSet rs=ps.executeQuery();  
        while(rs.next()){  
            Task u=new Task();  
            u.setId(rs.getInt("id")); 
            u.setName(rs.getString("name"));  
      
            list.add(u);  
        }  
    }catch(Exception e){System.out.println(e);}  
    return list;  
 }

public static int delete(Task u)
{
	int status = 0;
	
	try
	{
		Connection con=getConnection();  
		PreparedStatement ps=con.prepareStatement("delete from tasktbl where Id=?");
		ps.setInt(1,u.getId());
		status = ps.executeUpdate();
		
		
	}catch(Exception e)
	{
		System.out.println(e);
	}
	return status;
	
}


public static int update(Task up)
{

	int status=0;
	
	try
	{
		
		Connection  con=getConnection();
		PreparedStatement ps = con.prepareStatement("update tasktbl set Name=? where Id=?");
		ps.setString(1,up.getName());
		ps.setInt(2,up.getId());
		
		status = ps.executeUpdate();
	}
	catch(Exception e)
	{
		System.out.println(e);
	}
	
	return status;
	
}



public static Task getRecordById(int id){  
    Task u=null;  
    try{  
        Connection con=getConnection();  
        PreparedStatement ps=con.prepareStatement("select * from tasktbl where id=?");  
        ps.setInt(1,id);  
        ResultSet rs=ps.executeQuery();  
        while(rs.next()){  
            u=new Task();  
            u.setId(rs.getInt("Id"));  
            u.setName(rs.getString("Name"));  
          
        }  
    }catch(Exception e){System.out.println(e);}  
    return u;  
}  


}