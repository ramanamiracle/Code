
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class URLConnection1 {
	
	public static void main(String args[])
	{
		try{
			
			 URL oracle = new URL("http://google.com");
		        URLConnection yc = oracle.openConnection();
		        BufferedReader in = new BufferedReader(new InputStreamReader(
		                                    yc.getInputStream()));
		        String inputLine;
		        while ((inputLine = in.readLine()) != null) 
		            System.out.println(inputLine);
		        in.close();
	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}

}
