import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class DataSorceSample {

	public static void main(String args[])
	{
		try{
		Context ic = new InitialContext();
		DataSource ds = (DataSource) ic.lookup("jdbc/OracleDS");
		
		Connection conn = ds.getConnection();
		}
		catch(Exception e)
		{
			
		}
		
	}
}
