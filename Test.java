
public class Test {
	
	public void test()
	{

		int minimum = 1111111100;
		int maximum = 1111111200;
		String makeA[] = {"Longsung","Motorola"};
		String modelA[] = {"U6100","E1000"};
		String capable3GA[] = {"yes","no"};		
		
	    int mdn = minimum + (int)( Math.random()*((maximum-minimum)+1) );
		String make = makeA[(int)(Math.round(Math.random()))];
		String model = modelA[(int)(Math.round(Math.random()))];
		String capable3G = capable3GA[(int)(Math.round(Math.random()))];
	    
	    System.out.println("MDN = " + mdn + "\n" 
	    				 + "MAKE = " + make + "\n"
	    				 +	"MODEL = " + model + "\n"
	    				 + "capable3G = " + capable3G);
		
		
	}
	
	
	public static void main(String args[])
	{
		
	}

}
