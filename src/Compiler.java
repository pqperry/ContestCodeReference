//# ignore
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Compiler 
{
	
	public static void main(String[] args) 
	{
		File file = new File("./src");
		boolean useSrcDir = true;
		if(!file.exists())
		{
			file = new File("./");
			useSrcDir = false;
		}
		if(!file.exists())
		{
			System.err.println("Can't file target folder");
			return;
		}
		Stack<File> stack = new Stack<File>();
		stack.push(file);
		StringBuilder sb = new StringBuilder();
		
		StringBuilder toc = new StringBuilder(
				"**************************************************\n" +
				"***************  TABLE OF CONTENTS  **************\n" +
				"**************************************************\n");
		
		boolean hasError = false;
		while(!stack.isEmpty()) 
		{
			List<File> dirs = new ArrayList<File>();
			File curr = stack.pop();
			toc.append("\n");
			toc.append(curr.getName());
			toc.append("\n");
			List<File> files = new ArrayList<File>();
			List<File> folders = new ArrayList<File>();
			for(File f : curr.listFiles())
			{
				if(f.isDirectory())
				{
					folders.add(f);
				}
				else
				{
					files.add(f);
				}
			}
			Collections.sort(files);
			Collections.sort(folders);
			Collections.reverse(folders);
			for(File f : folders)
			{
				stack.push(f);
			}
			
			for(File f : files) 
			{
				if(f.isDirectory()) 
				{
					dirs.add(f);
				}
				else 
				{
					try
					{
					   String fileResult = handleFile(f);
					   if(fileResult != null)
					   {
						   sb.append(handleFile(f));
						   toc.append("\t\t");
						   toc.append(f.getName());
						   toc.append('\n');
					   }
					}
					catch(Exception e) 
					{
						hasError = true;
						System.err.println(e.getMessage());
					}
				}
			}
			
			Collections.reverse(dirs);
			for(File f : dirs) 
			{
				stack.push(f);
			}
		}
		
		if(!hasError) 
		{
			try
			{
				File out = useSrcDir ? new File("./src/contestcodearchive.txt") : new File("./contestcodearchive.txt");
				out.createNewFile();
				PrintWriter pw = new PrintWriter(out);
				pw.println("//# ignore");
				pw.println("Generated " + new Date().toString() + "\n");
				pw.println(toc.toString());
				pw.println(sb.toString());
				pw.close();
				System.out.println("\nSUCCESS!");
			}
			catch(Exception e)
			{
				System.err.println("Error while writing output");
			}
		}
	}
	
	private static String handleFile(File f) throws Exception 
	{
	   if(f.getName().contains(".class"))
	   {
	      System.out.println("Ignoring " + f.getName());
	      return null;
	   }
		Scanner sc = new Scanner(f);

		String[] header = sc.nextLine().split("\\s+");
		if(!header[0].equals("//#")) 
		{
			throw new Exception("No header for file " + f.getPath());
		}
		
		boolean keepclass = false;
		for(int i = 1; i < header.length; i++) 
		{
			String flag = header[i];
			if(flag.equals("ignore")) 
			{
				System.out.println("Ignoring " + f.getName());
				return null;
			}
			else if(flag.equals("keepclass"))
			{
				keepclass = true;
			}
			else 
			{
				throw new Exception("Invalid flag, " + flag + ", in file " + f.getPath());
			}
		}
		
		
		Deque<String> lines = new ArrayDeque<String>();
		while(sc.hasNext())
		{
			lines.add(sc.nextLine());
		}
		
		Iterator<String> it = lines.iterator();
		while(it.hasNext())
		{
			String line = it.next();
			String[] words = line.trim().split("\\s+");
			if(words[0].equals("import") || words[0].equals("package") || line.trim().isEmpty())
			{
				it.remove();
			}
			else if(!keepclass && line.contains("public class")) 
			{
				it.remove();
			}
			else
			{
				break;
			}
			
		}
		
		boolean seenEnd = false;
		while(true) 
		{
			if(lines.peekLast().trim().isEmpty()) 
			{
				lines.removeLast();
			}
			else if(!keepclass && !seenEnd && lines.peekLast().trim().charAt(0) == '}')
			{
				seenEnd = true;
				lines.removeLast();
			}
			else 
			{
				break;
			}
		}
		
		StringBuilder sb = new StringBuilder("\n-------------------- " + f.getName() + " --------------------\n\n");
		for(String line : lines)
		{
			if(!keepclass && line.length() > 0 && line.charAt(0) == '\t')
			{
				sb.append(line.substring(1));
			}
			else
			{
				sb.append(line);
			}
			sb.append('\n');
		}
		
		return sb.toString();
	}
}
