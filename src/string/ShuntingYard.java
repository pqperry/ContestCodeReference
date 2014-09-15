//#
package string;

import java.util.*;
import java.util.regex.Pattern;

public class ShuntingYard {
	
	/*
	 * Shunting-yard algorithm with tokenizer and rpn evaluator.
	 * Running time: O(n) [n = string length]
	 * Tested on: nothing
	 * Usage example: double result = rpnEval(toRPN(tokenize("round( (9 / 3) ^ max(1, 1 - 2 * cos(3.1415926) ) )")));
	 * Note: these algorithms all assume that the input is well-formated. Behaviour with un-parseable expressions is undefined
	 * Also, this does NOT handle unary operators such as -
	 */

	// Define binary operators here. The int denotes operator priority, where higher valued operators get evaluated first
	// A negative number means that the operator is right associative with priority abs(num)
	@SuppressWarnings("serial")
	static Map<String, Integer> ops = new HashMap<String, Integer>(){{
		put("+", 1);
		put("-", 1);
		put("*", 2);
		put("/", 2);
		put("^", -3);
	}};
	
	// Define function names here
	static Set<String> funs = new HashSet<String>(Arrays.asList("min", "max", "sin", "cos", "tan", "abs", "round"));
	
	static Set<String> delims = new HashSet<String>(Arrays.asList("(", ")", ","));
	static String numMatch = "\\d*(\\.\\d+)?";
	
	// implement this to handle operators and function applications
	static double handleOp(String op, Stack<Object> stack) {
		if(ops.containsKey(op)) {
			double b = (Double)stack.pop(); // note the ordering here. 
			double a = (Double)stack.pop(); // 1 - 2 is 1 2 - in rpn, so pop in reverse order
			
			if(op.equals("+")) return a + b;
			if(op.equals("-")) return a - b;
			if(op.equals("*")) return a * b;
			if(op.equals("/")) return a / b;
			if(op.equals("^")) return Math.pow(a, b);
		}
		else if(funs.contains(op)) {
			double arg1 = (Double)stack.pop();
			
			if(op.equals("min")) return Math.min((Double)stack.pop(), arg1);
			if(op.equals("max")) return Math.max((Double)stack.pop(), arg1);
			if(op.equals("sin")) return Math.sin(arg1);
			if(op.equals("cos")) return Math.cos(arg1);
			if(op.equals("tan")) return Math.tan(arg1);
			if(op.equals("abs")) return Math.abs(arg1);
			if(op.equals("round")) return Math.rint(arg1);
		}
		return 0;
	}
	
	// This takes a raw string and tokenizes it based on defined functions and operators
	public static String[] tokenize(String in) {
		List<String> tokens = new ArrayList<String>();
		String curr = "";
		boolean isNum = true;
		for(char c : in.replaceAll("\\s+", "").toCharArray()) {
			if((isNum && !Character.isDigit(c) && c != '.') 
					|| ops.containsKey(curr) || funs.contains(curr) || delims.contains(curr)) {
				if(!curr.isEmpty()) tokens.add(curr);
				curr = "";
				isNum = Character.isDigit(c) || c == '.';
			}
			curr += c;
		}
		if(!curr.isEmpty()) tokens.add(curr);
		return tokens.toArray(new String[0]);
	}
	
	// This takes a list of tokens in infix notation and converts it to postfix (a.k.a reverse polish notation)
	public static String[] toRPN(String[] tokens) {
		List<String> out = new ArrayList<String>();
		Stack<String> stack = new Stack<String>();
		for(String s : tokens) {
			if(Pattern.matches(numMatch, s)) out.add(s);
			else if(funs.contains(s)) stack.push(s);
			else if(s.equals(",")) 
				while(!stack.peek().equals("(")) out.add(stack.pop());
			else if(ops.containsKey(s)) {
				while(!stack.isEmpty() && ops.containsKey(stack.peek()) &&
						Math.abs(ops.get(s)) < Math.abs(ops.get(stack.peek())) + (ops.get(s) < 0 ? 0 : 1)) out.add(stack.pop());
				stack.push(s);
			}
			else if(s.equals("(")) stack.push(s);
			else if(s.equals(")")) {
				while(!stack.peek().equals("(")) out.add(stack.pop());
				stack.pop();
				if(!stack.isEmpty() && funs.contains(stack.peek())) out.add(stack.pop());
			}
		}
		while(!stack.isEmpty()) out.add(stack.pop());
		return out.toArray(new String[0]);
	}
	
	// This evaluates rpn using the defined handleOp method
	public static double rpnEval(String[] in) {
		Stack<Object> stack = new Stack<Object>(); 
		for(String token : in) {
			if(!ops.containsKey(token) && !funs.contains(token)) stack.push(Double.parseDouble(token));
			else stack.push(handleOp(token, stack));
		}
		return (Double)stack.pop();
	}
	
}
