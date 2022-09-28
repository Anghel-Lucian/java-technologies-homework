
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringJoiner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
	private final String FILE_DIR = "lab01/resources/";
	private final String FILE_LOG = "lab01/resources/log.txt";
	private final String FILE_DICT = "lab01/resources/dict.txt";
	
       
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();

        String input = request.getParameter("input");
        int size = Integer.parseInt(request.getParameter("size"));
   
        String permutations = this.findPermutations(input, size);
        
        this.logToFile(request, permutations);
        
        pw.print(permutations);
        pw.close();
    }
    
    private String findPermutations(String input, int size) {
    	StringJoiner joiner = new StringJoiner("\n");
    	
	    try {
	      File file = new File(FILE_DICT);
	      Scanner scanner = new Scanner(file);
	      while (scanner.hasNextLine()) {
	        String word = scanner.nextLine();
	        
	        if (size > 0) {
	        	// find all words that are permutations of `input` and with length `size`
	        	if (this.stringContainsAllCharactersOfString(input, word) && word.length() == size) {
		        	joiner.add(word);
		        }
	        } else {
	        	// find all words that are permutations of `input`
	        	if (this.stringContainsAllCharactersOfString(input, word)) {
	        		joiner.add(word);
	        	}
	        }
	      }
	      
	      scanner.close();
	    } catch (FileNotFoundException e) {
	      System.out.println("Reading dictionary failed.");
	      e.printStackTrace();
	    }
	    
	    return joiner.toString();
    }
    
    // .contains() method doesn't suffice because characters can be in another order
    private boolean stringContainsAllCharactersOfString(String container, String contained) {
    	HashMap<Character, Integer> charMap = new HashMap<Character, Integer>();
    	
    	for (char c : container.toCharArray()) {
    		if (charMap.get(c) == null) {
    			charMap.put(c, 1);
    		} else {
    			charMap.put(c, charMap.get(c) + 1);
    		}
    	}
    	
    	for (char c : contained.toCharArray()) {
    		if (charMap.get(c) == null) {
    			return false;
    		} else if (charMap.get(c) >= 1) {
    			charMap.put(c, charMap.get(c) - 1);
    		} else if (charMap.get(c) == 0) {
    			return false;
    		}
    	}
    	
    	return true;
    }
    
    // write request details and result to a txt file
    public void logToFile(HttpServletRequest request, String response) throws IOException {
    	File dir = new File(FILE_DIR);
		File file = new File(FILE_LOG);
    	StringJoiner joiner = new StringJoiner("\n");
		
    	joiner.add("METHOD USED: " + request.getMethod());
    	joiner.add("IP: " + request.getLocalAddr());
    	joiner.add("USER AGENT: " + request.getHeader("User-Agent"));
    	joiner.add("LOCALE: " + request.getLocale().toString());
    	joiner.add("INPUT (param): " + request.getParameter("input"));
    	joiner.add("SIZE (param): " + Integer.parseInt(request.getParameter("size")));
    	joiner.add(response);
    	joiner.add("\n");
    	
		if(dir.mkdirs()) {
			file.createNewFile();
		}
		
		Files.write(Paths.get(FILE_LOG), joiner.toString().getBytes(), StandardOpenOption.APPEND);
    }
}
