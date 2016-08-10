
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.ucp.jdbc.JDBCConnectionPoolStatistics;
import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;

public class UCPPool {
	
	private static PoolDataSource pds = null;
	
	public UCPPool() {
		
		try{
		InitProps.init();	
		
		pds = PoolDataSourceFactory.getPoolDataSource();
			
		pds.setConnectionFactoryClassName("oracle.jdbc.pool.OracleDataSource");
		pds.setURL(InitProps.bundle.getString("DB_URL_SIT"));
		pds.setUser(InitProps.bundle.getString("DB_USER_SIT"));
		pds.setPassword(InitProps.bundle.getString("DB_PASSWORD_SIT"));
		pds.setConnectionPoolName(InitProps.bundle.getString("UCP_POOL_SIT"));
		
		pds.setMinPoolSize(Integer.valueOf(InitProps.bundle.getString("UCP_POOL_MIN")));
		pds.setMaxPoolSize(Integer.valueOf(InitProps.bundle.getString("UCP_POOL_MAX")));
		pds.setConnectionWaitTimeout(Integer.valueOf(InitProps.bundle.getString("UCP_POOL_TIMEOUT")));
		pds.setInitialPoolSize(Integer.valueOf(InitProps.bundle.getString("UCP_POOL_INITIAL")));

		Connection con = pds.getConnection();
		
		con.close();
		}
		catch(Exception e)
		{
			System.out.println("Exception :: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void main(String args[])
	{
		try{
		
		UCPPool pool = new UCPPool();
		
	    Connection conn = pds.getConnection();
	    System.out.println(conn);
	    Statement stmt = conn.createStatement();  // fixed
	    ResultSet rs = null;
		
		while (true) {
		      try {

		    	 rs = stmt.executeQuery("select * from v$version");
		        while (rs.next()) {
		          System.out.println("Instance name: " + rs.getString(1));
		        }
		        
		          JDBCConnectionPoolStatistics stats = pds.getStatistics();
		          System.out.println("FCF Activ " +  stats.getBorrowedConnectionsCount());
		          System.out.println("FCF Avail " +  stats.getAvailableConnectionsCount()+"\n");
		        
		      }
		      catch (SQLException sqle) {
		          try
		          {
		            conn.close();
		          }
		          catch (Exception closeExc)
		          {
		            System.out.println("Exception :: " + sqle.getMessage());
		          }
		    }
		      Thread.sleep(1000);  	
	}

	}
	catch (Exception e)
    {
    System.out.println("Exception :: " + e.getMessage());
    }

	}
}
