import java.util.*;
import java.io.*;
public class WordLadder {

    private static LinkedList<String> dict;
    private static LinkedList<String> visited;
    private static String start, end;
	public static void main(String[] args) throws IOException{
		File dictfile = new File("dataFiles/dictionary.txt");
		File infile = new File("dataFiles/infile.txt");
		dict = new LinkedList<>();
		// load the dictionary
		try(
			Scanner in = new Scanner(dictfile);){
			while(in.hasNext()) {
				
				 dict.add(in.next());
			}
		}
		try(Scanner in = new Scanner(infile);) 
		{
			while(in.hasNext()) {
			
				
				start = in.next();
				
				end = in.next();
				
				//Check if basic requirements are met
				if(start.length()!=end.length() || !dict.contains(start) || !dict.contains(end) ){
					System.out.println("There is no word ladder between "+start+ " and "+end + "\n");
					continue;
				}
		
				findLadder(start,end);
			
			}
		}
		
	
	
	}
	
	@SuppressWarnings("unchecked")
	public static void findLadder(String start,String end) {
		
		Queue<Stack<String>> queue = new LinkedList<>();
		visited = new LinkedList<>();
		Stack<String> copiedStack = new Stack<>();
		copiedStack.push(start);
		
		LinkedList<String> copiedDict = new LinkedList<>();
		copiedDict = (LinkedList<String>) dict.clone();
		
		//Look for all edges to start
		copiedDict.remove(start);
		//Current string at the top of the stack
		String currString = new String(start);
		
		//Look for all edges, make stacks for each edge, enqueue each stack
		for (boolean ladderFinder = false; ladderFinder == false;) 
		{
			for (int i = 0; i < copiedDict.size(); i++) 
			{
				if (isAnEdge(currString, copiedDict.get(i)) == true)
				{
					copiedStack.push(copiedDict.get(i));
					copiedDict.remove(i);
					i--;
					Stack<String> tempStack = (Stack<String>)copiedStack.clone();
					queue.add(tempStack);
					copiedStack.pop();
				}
			} 
			
			if (queue.isEmpty())
			{
				System.out.println("There is no word ladder between " + start + " and " + end + "\n");
				return;
				
			}
			
			copiedStack = queue.poll();
			currString = copiedStack.peek();
			
			//if the end is reached, print out stack
			if (currString.equals(end))
			{
				System.out.println("Start: " + start + "   End: " + end);
				
				Object [] answer = copiedStack.toArray();
				for (int i = 0; i < answer.length; i++)
				{
					System.out.print(answer[i] + " ");
				}
				
				System.out.println("\n");
				ladderFinder = true;
			}
			
			
			
			
		}
		
		
		
	}	
   
   public static boolean isAnEdge(String w1, String w2) {
        
	   int k = 0;
	   int j = 0;
	   int count = 0;
	   
	   for (int i = 0; i < w1.length(); i++)
	   {
		   
		   if (w1.charAt(k) == w2.charAt(j))
		   {
			   k++;
		   	   j++;
			   continue;
		   }
		   else {
			   count++;
			   k++;
			   j++;
		   }
			   
		   if (count > 1) {
			   return false;
		   }
	   }
	   return true;
	   
    }

}
