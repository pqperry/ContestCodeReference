//# ignore
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

import javax.script.*;

import static java.lang.Math.*;


@SuppressWarnings({ "unused", "unchecked"})
public class JavaNotes {
	
	{
		System.out.println("This is an instance initializer"); 
		System.out.println("It runs when you create a new instance of this"); 
	}
	
	static {
		System.out.println("Likewise, a static initializer");
		String s = "do something here";
		String b = "you can even use this instead of main(), just call System.exit(0) at the end";
	}

	public static void main(String[] args) throws Exception {

		// Use SimpleDateFormat to parse dates
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-mm-dd kk:mm:ss");
		System.out.println(fmt.parse("2012-06-11 23:12:30"));
		
		//Creating a generic array
		List<Integer>[] listArray = new ArrayList[10];
		
		//BigInteger has a few useful discrete math methods
		BigInteger bi = BigInteger.valueOf(10);
		BigInteger bi2 = BigInteger.valueOf(7);
		bi.modInverse(bi2);
		bi.modPow(bi, bi2);
		
		//Useful built-in Eclipse templates:
		//syso -> System.out.println();
		//arraymerge -> merges two arrays;
		//for -> for loop
		
		//The source menu (Alt + Shift + S) has tons of useful features such as automatically generating constructor
		//from fields, constructor from superclass, hash code, equals, tostring.
		
		//You can import static methods of other classes (like the math class):
		//import static java.lang.Math.*;
		double someMath = abs(cos(PI));
		
		//You can "rename" a class with a long name by extending that class, eg:
		//class Pt extends Point2D.Double{}
		//This allows you to just type Pt instead of the longer Point2D.Double (mimics typedef).
		//Constructors are not automatically inherited, so click Source -> Generate Constructors From SuperClass
		
		//printing a list or array of numbers is often easier with Arrays.toString() or List.toString()
		List<Integer> list = new ArrayList<Integer>(); list.add(1); list.add(2);
		System.out.println(list.toString().replaceAll("[\\[\\],]", ""));
		int[] array = new int[]{1, 2, 3, 4, 5, 6};
		System.out.println(Arrays.toString(array).replaceAll("[\\[\\],]", ""));
		
		//Not sure if this is allowed, but there's a build in javascript engine that can be used to eval() expressions
		ScriptEngine se = new ScriptEngineManager().getEngineByName("js");
		Double evald = (Double)se.eval("(1  + 4*9)-(2*(9-2))");
		
		//Be careful of the following:
		double negzero = -0.0;
		System.out.println(negzero); //-0.0
		System.out.printf("%.3f\n", -0.000001); //-0.000
		//this fixes it:
		System.out.println(negzero == 0 ? 0 : negzero); //0.0
		System.out.printf("%.3f\n", -0.000001 + 1e-4); //0.000
		
		//Also be careful of the following:
		double d1 = 0.0;
		double d2 = -0.0;
		Double d3 = d1;
		Double d4 = d2;
		System.out.println(d1 == d2);
		System.out.println(d3.equals(d2));
		
		//Single line int swap:
		int a = 5, b = 77;
		a = (b ^= a ^= b) ^ a;
		
		//Math.abs(Integer.MIN_VALUE) == Integer.MIN_VALUE;
		
		//Java's relativeCCW methods do NOT behave as normal.
	}
	
}
