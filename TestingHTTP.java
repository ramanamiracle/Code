import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import java.util.zip.DataFormatException;
import java.util.Set;
import java.util.TreeMap;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RefineryUtilities;


public class TestingHTTP implements Runnable{
	
	static int noOfThreads=10;
	static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
	static SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss.SSS");
	
	static TreeMap<Long, Integer> statsMap = new TreeMap<>();
	static TreeMap<Long, Integer> timeMap = new TreeMap<>();
	static HazelDao hazelDao;
	
	
public static void main(String[] args) {
	
	try{
		hazelDao = new HazelDao();
		Thread.sleep(2000);
	}
	catch(Exception e)
	{
		System.out.println("Exception :" + e.getMessage());
	}
	
	Thread  threads[]=new Thread[noOfThreads];

	//long startTime = System.currentTimeMillis();
	long startTime = System.currentTimeMillis();
	
	long endTime;
	
	 try{
     	for(int i=0;i<noOfThreads;i++)
     	{
     		TestingHTTP thread = new TestingHTTP();
     		
     		threads[i] = new Thread(thread, "Thread" + i);
     		
     		threads[i].start();
     	}
           //  connt.close();
         }catch(Exception e)
         {
          System.out.println("error in connecting to DB"+e);
         }
	 
	 
	 try{
		 for(int j=0; j<noOfThreads; j++){
			 threads[j].join();
			}
	 }
	 
	 catch(Exception e){}
	 
     endTime =  System.currentTimeMillis();
     
     System.out.println("-------------------------------Process Stats-------------------------------------");
     System.out.println("Process Time(S) : " + (endTime-startTime)/1000.00 );
     System.out.println("---------------------------------------------------------------------------------");
	 
	 System.out.println("-----------------------------------Map Stats--------------------------------------");
	 
	 Set<Entry<Long, Integer>> set = statsMap.entrySet();
	 Iterator<Entry<Long, Integer>> itr = set.iterator();
	 
	 Set<Entry<Long, Integer>> set1 =timeMap.entrySet();
	 Iterator<Entry<Long, Integer>> itr1 = set1.iterator();
	 
	 XYSeries wdsHits = new XYSeries( "HazelCast" );          
	
	 while(itr.hasNext())
	 {
		 Entry<Long, Integer> entity = itr.next();
		 System.out.println(entity.toString());
		 //wdsHits.add( entity.getKey(), entity.getValue() ); 
	 }
	 
	 while(itr1.hasNext())
	 {
		 
		 Entry<Long, Integer> entity = itr1.next();
		 System.out.println(entity.toString() + "=" +  dateFormat.format(new Date(entity.getKey())) );
		 
		 try {
			wdsHits.add( entity.getKey(), entity.getValue() );
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	 }
     
     System.out.println("-----------------------------------------------------------------------------------");
     
     System.out.println ("----------------------------------Drawing XY Chart---------------------------------");
     
     XYSeriesCollection dataset = new XYSeriesCollection( );
     dataset.addSeries( wdsHits );  
     
     
     JfreeChart chart = new JfreeChart("HazelCast", "HazelCast",dataset);
     chart.pack( );          
     RefineryUtilities.centerFrameOnScreen( chart );          
     chart.setVisible( true );
     
     System.out.println ("-----------------------------------------------------------------------------------");
      
}


public void run()
{
	
	long startTime = System.currentTimeMillis();
	long endTime;
	
	//checkMCA();

        try{
        	for(int i=0;i<1000;i++)
        	{
        	  TestingHTTP obj = new TestingHTTP();
        		
              int count= obj.checkWdsApi();
              
        	  //String result = obj.checkHazel();
        	  
              obj=null;
        	}
              //  connt.close();
            }catch(Exception e)
            {
             System.out.println("error in connecting to DB"+e);
            }
        
      endTime =  System.currentTimeMillis();
      
      System.out.println("--------------------------------Thread Stats--------------------------------------");
      System.out.println("Total Time for Threads (S) : " + Thread.currentThread().getName() + " : " + (endTime-startTime)/1000.00);
      System.out.println("---------------------------------------------------------------------------------");
	
}


public String checkHazel()
{
	String errMsg=null,mdn=null;
    long start,end,time,start1,end1; 
	 String result;
	 String oDataString;
	 int Low = 0, High = 1000;
	 Random r = new Random();
	
	 start = System.nanoTime();
	 start1 = System.currentTimeMillis();

	 mdn=hzMdn[r.nextInt(High-Low) + Low];

	oDataString = hazelDao.getMdnDetails(mdn);
	
	if(oDataString == null)
	errMsg = "MDN Details Not Present";

			
if(oDataString !=null)
result="Success" + "|" + oDataString;
else
result="Failure" + "|" + errMsg ;
		
System.out.println("Result : " + result );

end = System.nanoTime();
end1 = System.currentTimeMillis();
 
time=TimeUnit.NANOSECONDS.toMillis(end-start);
 
 System.out.println("Total Time for WDS (MS) for MDN :" + mdn + " : " +Thread.currentThread().getName() +" : startTIme : " + dateFormat1.format(new Date(start1)) + " : endTime : " + dateFormat1.format(new Date(end1)) + " : Time "+ time + "\n");
 
 if (statsMap.get(time) == null)
 {
	   statsMap.put(time,1);
 }
 else
 {
	   statsMap.put(time,statsMap.get(time)+1);
 }
 
 if (timeMap.get(end1/1000 * 1000) == null)
 {
	   timeMap.put(end1/1000 * 1000,1);
 }
 else
 {
	   timeMap.put(end1/1000 * 1000,timeMap.get(end1/1000 * 1000)+1);
 }


 return result;
}


 public int checkWdsApi()
  {
    int urlResponseCode = 0;

    HttpURLConnection uc = null;
    //URLConnection uc = null;
    URL url = null;
    String mdn1;

    long start,end,time,start1,end1;   
    int Low = 0, High = 1000;
	Random r = new Random();
	
	start = System.nanoTime();
	start1 = System.currentTimeMillis();

	mdn1=mdn[r.nextInt(High-Low) + Low];
	//mdn1=hzMdn[r.nextInt(High-Low) + Low];
	
	
    String urlDet ="google.com";
	
    
    try
    {
     
      url = new URL(urlDet);
      uc = (HttpURLConnection)url.openConnection();
      //uc = url.openConnection();
      
      uc.setRequestMethod("GET");
      uc.setDoOutput(true);
      uc.setDoInput(true);
      uc.setUseCaches(false);
      uc.setDefaultUseCaches(false);
      
      uc.setRequestProperty("Pragma", "no-cache");
      uc.setRequestProperty("Cache-Control", "no-cache");
      uc.setRequestProperty("Expires", "-1");
      uc.setRequestProperty("Content-type", "text/xml");     
      uc.setRequestProperty("Connection","Keep-Alive");
      
      uc.connect();
      
      if (uc != null)
      {
     //System.out.println("Opened Http URL connection ... ");
     uc.setConnectTimeout(1500);
     //System.out.println("Connection time out is set as 1500 ms... "+connecttimeout);
     //uc.setReadTimeout(10);
     //System.out.println("Read time out is ... "+  uc.getReadTimeout());

      //System.out.println("uc.getInputStream ... "+ uc.getInputStream());
      BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
      String inputLine;
      
      while ((inputLine = in.readLine()) != null) 
      System.out.println(inputLine);
      
      in.close();
      
     // System.out.println("urlResponse is ... "+ uc.getResponseMessage());
     // System.out.println("urlResponseCode is ... "+ uc.getResponseCode());
       
      
      end = System.nanoTime();
      end1 = System.currentTimeMillis();
       
      time=TimeUnit.NANOSECONDS.toMillis(end-start);
       
       System.out.println("Total Time for WDS (MS) for MDN :" + mdn1 + " : " +Thread.currentThread().getName() +" : startTIme : " + dateFormat1.format(new Date(start1)) + " : endTime : " + dateFormat1.format(new Date(end1)) + " : Time "+ time + "\n");
       
       if (statsMap.get(time) == null)
       {
    	   statsMap.put(time,1);
       }
       else
       {
    	   statsMap.put(time,statsMap.get(time)+1);
       }
       
       if (timeMap.get(end1/1000 * 1000) == null)
       {
    	   timeMap.put(end1/1000 * 1000,1);
       }
       else
       {
    	   timeMap.put(end1/1000 * 1000,timeMap.get(end1/1000 * 1000)+1);
       }
                        
       }
      else
       System.out.println("Connection couldnt be established...");
     
         
    }
   catch (Exception e)
    {
      e.printStackTrace();
    }
    finally
    {
        uc.disconnect();
    	uc = null;
      //System.out.println("Disconnected from Http URL connection....");
    }
    return urlResponseCode;
  }
 
 
static String mdn[] = {};
		
static String hzMdn[]={};

 
}
