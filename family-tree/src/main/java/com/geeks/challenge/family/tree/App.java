package com.geeks.challenge.family.tree;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.geeks.challenge.family.tree.analyser.query.QueryAnalyserImpl;
import com.geeks.challenge.family.tree.processor.ProcessorResult;
import com.geeks.challenge.family.tree.processor.query.QueryAnalyser;
import com.geeks.challenge.family.tree.processor.query.QueryProcessor;

public class App 
{
	private static QueryAnalyser queryAnalyser = new QueryAnalyserImpl();
	private static QueryProcessor processor = new QueryProcessor();
	
    public static void main( String[] args ) {
//    		initialise();
//     		switch(args[0]) {
//	     		case "--query" :{
//	     			String query = "";
//	     			for (int i = 1; i<args.length; i++) {
//	     				query +=" ";
//	     				query += args[i];
//	     			}
//	     			ProcessorResult<String> result = (ProcessorResult<String>) processor.process(query.trim());
//	    			System.out.println(result.getResult());
//	    			break;
//	     		}
//	     		case "--file" : { 
	     			runWithFile(args[0]);
//	     					break;
//	     		}
//     		}
     	}
    
    private static void initialise() {
    	String family = "src/main/resource/query.txt";
     	runWithFile(family);
    }

	private static void runWithFile(String family) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(family));
			String line = reader.readLine();
			
			while(line != null && line != " ") {
				ProcessorResult result = processor.process(line);
				System.out.println(result.getResult());
				line = reader.readLine();		
			};
			reader.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found. Please Add the family file Under the resource folder");
		
		} catch (IOException e) {
			System.out.println("Unable to Read the File. Please check the permissions of the family file Under the resource folder");
		}
		
	}
}
