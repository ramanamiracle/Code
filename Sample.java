import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import oracle.sql.TIMESTAMP;

import org.apache.log4j.Logger;


public class Sample {

	private static final long MEGABYTE = 1024L * 1024L;
	private static Logger log = Logger.getLogger("sample");

	public static long bytesToMegabytes(long bytes) {
		return bytes / MEGABYTE;
	}

	void block() {
		/*
		 * List<String> list = new ArrayList<String>(); for (int i = 0; i <=
		 * 100000; i++) { list.add(new String("Jim")); } // Get the Java runtime
		 * Runtime runtime = Runtime.getRuntime(); // Run the garbage collector
		 * runtime.gc(); // Calculate the used memory long memory =
		 * runtime.totalMemory() - runtime.freeMemory();
		 * System.out.println("Used memory is bytes: " + memory);
		 * System.out.println("Used memory is megabytes: " +
		 * bytesToMegabytes(memory));
		 */
	}

	@Deprecated
	void block1() {
		/*
		 * Connection con; PreparedStatement pst; ResultSet rs;
		 * 
		 * try{ Class.forName("oracle.jdbc.driver.OracleDriver"); con=
		 * DriverManager
		 * .getConnection("jdbc:oracle:thin:@1server:1521:db"
		 * ,"wds_stg","wds_stg"); System.out.println("Connection Connected" );
		 * pst = con.prepareStatement(
		 * "select * from sample where rownum<=10");
		 * rs=pst.executeQuery(); while(rs.next()) {
		 * System.out.println(rs.getRow()); }
		 * 
		 * con.close();
		 * 
		 * rs.next(); //if (pst.isClosed() || rs.isClosed())
		 * //System.out.println("BOTH are Closed" );
		 * 
		 * rs.close(); pst.close();
		 * 
		 * System.out.println("Connection Closed" ); } catch(Exception e) {
		 * System.out.println("Exception :" +e.getMessage()); }
		 */
	}

	static void block3() {

		String urlResponse = null;
		int urlResponseCode = 0;
		String response = null;
		HttpURLConnection uc = null;
		URL url = null;
		int platformStatus = 0;
		int readtimeout = 0;
		int connecttimeout = 0;
		Date date;
		long start, end;
		long start1, end1;
		
		int minimum = 1111111100;
		int maximum = 1111111200;
		String makeA[] = {"Longsung","Motorola"};
		String modelA[] = {"U6100","E1000"};
		String capable3GA[] = {"yes","no"};		
		
		
	    int totalRequests = 1;
	    
	    
		try {
			start = new Date().getTime();

			for (int i = 0; i < totalRequests ; i++) {
							
			    int mdn = minimum + (int)( Math.random()*((maximum-minimum)+1) );
				String make = makeA[(int)(Math.round(Math.random()))];
				String model = modelA[(int)(Math.round(Math.random()))];
				String capable3G = capable3GA[(int)(Math.round(Math.random()))];
				
				
				String urlDet = "http://google.com;
				
				System.out.println(urlDet);
				log.info(urlDet);
				
				start1 = new Date().getTime();
				
				url = new URL(urlDet);
				uc = (HttpURLConnection) url.openConnection();
				
				uc.connect();
				
				if (uc != null) {
					
					//uc.setConnectTimeout(1000);
					// uc.setReadTimeout(10);
					// System.out.println("Read time out is ... "+
					// uc.getReadTimeout());
					
					System.out.println("Response :" + uc.getResponseMessage());

					try {
						BufferedReader in = new BufferedReader(
								new InputStreamReader(uc.getInputStream()));
						String inputLine;
						while ((inputLine = in.readLine()) != null)
							System.out.println(inputLine);
						in.close();

						System.out.println("urlResponse is : "+ uc.getResponseMessage() + "urlResponseCode is :" + uc.getResponseCode());
						log.info("urlResponse is : "+ uc.getResponseMessage() + "urlResponseCode is :" + uc.getResponseCode());
						
						end1 = new Date().getTime();
						log.info("Request for : " + urlDet + " ---> Time Taken : " + (end1 - start1)  );
						System.out.println("Request for : " + urlDet + " ---> Time Taken : " + (end1 - start1)  );
						
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("Exception :" + e.getMessage());
						log.error("Exception :" + e.getMessage());
					}

				} else
				{
				System.out.println("Connection couldnt be established...");
				log.error("Connection couldnt be established...");
				}
			}

			end = new Date().getTime();
			System.out.println("Total Time :" +totalRequests +" : " + (end - start));
			log.info("Total Time :" +totalRequests +" : " + (end - start));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			uc.disconnect();
			System.out.println("Disconnected from Http URL connection....");
			log.info("Disconnected from Http URL connection....");
		}
	}

	public static void main(String args[])
	{
		//int Low = 0, High = 1000;
		//Random r = new Random();
		
		//System.out.println((int)r.nextInt(High-Low) + Low);
		
		block3();
	}
	
	
	
	void block4()
	{
		try{
			
			  // using pattern with flags
	        Pattern pattern = Pattern.compile("ab", Pattern.CASE_INSENSITIVE);
	        Matcher matcher = pattern.matcher("ABcabdAb");
	        // using Matcher find(), group(), start() and end() methods
	        while (matcher.find()) {
	            System.out.println("Found the text \"" + matcher.group()
	                    + "\" starting at " + matcher.start()
	                    + " index and ending at index " + matcher.end());
	        }
	 
	        // using Pattern split() method
	        pattern = Pattern.compile("\\W");
	        String[] words = pattern.split("one@two#three:four$five");
	        for (String s : words) {
	            System.out.println("Split using Pattern.split(): " + s);
	        }
	 
	        // using Matcher.replaceFirst() and replaceAll() methods
	        pattern = Pattern.compile("1*2");
	        matcher = pattern.matcher("11234512678");
	        System.out.println("Using replaceAll: " + matcher.replaceAll("_"));
	        System.out.println("Using replaceFirst: " + matcher.replaceFirst("_"));
			  
			  

		    System.out.println("Done");
		    
		}catch(Exception e)
		{
			System.out.println("Exception : " + e.getMessage());
		}
	}

}
