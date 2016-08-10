import java.io.*;
import java.sql.*;

public class DbCheck {
	
	public static void main(String args[]) throws ClassNotFoundException, IOException, SQLException {
		DbCheck dbconn = new DbCheck();
		  dbconn.vasCheck();
		  dbconn.wdsCheck();

		}
	
	public void dbsCheck()throws IOException, ClassNotFoundException{
		Connection connection = null;
		Statement stmt = null;
		  try {
		      // Load the JDBC driver

		     String driverName = "oracle.jdbc.driver.OracleDriver";
		     Class.forName(driverName);

		      connection = DriverManager.getConnection("jdbc:oracle:thin:@//server/db", "username", "password");
		      System.out.println("Connection successful: DB ");

		
		  } catch (SQLException e) {
		     System.out.println("Connection Fail : DB");
		  }
		  finally
		  {
			  try{
			  if (connection != null ) {connection.close();};
			  }
			  catch(Exception e){}
			  
		  }
		  
	}
	
	public void dbCheck()throws IOException, ClassNotFoundException{
		Connection connection = null;
		Statement stmt = null;
		  try {
		      // Load the JDBC driver

		     String driverName = "oracle.jdbc.driver.OracleDriver";
		     Class.forName(driverName);

		      connection = DriverManager.getConnection("jdbc:oracle:thin:@//server/db", "username", "password");
		      System.out.println("Connection successful: DB ");

		
		  } catch (SQLException e) {
		     System.out.println("Connection Fail : DB");
		  }
		  finally
		  {
			
				  try{
				  if (connection != null ) {connection.close();};
				  }
				  catch(Exception e){}
			  
		  }
	}

	

}
