import java.util.ResourceBundle;


public class InitProps {
	
	public static ResourceBundle bundle = null;
	public static String configFile="DbConfig";
	
	public static void init()
	{
		try{
			bundle = ResourceBundle.getBundle(configFile);
		}
		catch(Exception e)
		{
			System.out.println("Exception :: " + e.getMessage());	
		}
	}

}
