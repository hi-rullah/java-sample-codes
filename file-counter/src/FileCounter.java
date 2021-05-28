import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileCounter {
	
	static String FolderPath=".\\input";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub	
		
	  //Creating the Logger object
	  Logger logger = LoggerFactory.getLogger("Logger");
	  logger.info("Logger started");
	  
	  //Logging the information		  	  
	  /*
	  logger.debug("Hello this is a debug message");
	  logger.info("Hello this is an info message");
	  logger.warn("Hello this is a warn message");
	  logger.error("Hello this is an error message");
	  logger.trace("Hello this is a trace message");
      */
	    
	  writeToFile("File created");
	  logger.info("Text File, testout.txt, created on .\\output" );
	  
	  listFilesInTheFolder();
	  
	  try {
		ReadFile(".\\input\\info.txt");
		logger.info("Read File on the path \\input\\info.txt" );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
		  
		  
		/*  
	    try {
			ReadFile(".\\input\\info.txt");
			logger.info("Read File on the path \\input\\info.txt" );
			
			writeToFile("File created");
			logger.info("Text File, testout.txt, created on .\\output" );
			
			//appendStrToFile(".\\output\\testout.txt", "\nfirst append");
			//appendStrToFile(".\\output\\testout.txt", "\nsecond append");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	    
	    /*
	    final File folder = new File(FolderPath);
	    logger.info("folder path taken from the input file as " + FolderPath);    
	    
	    
	    int count = 0;
		try {
			System.out.println("lenght: " + folder.list().length);
			count = new File(FolderPath).list().length;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    System.out.println("Number of file : " + count);	 
	    */   
	    
	    //listFilesForFolder(folder);	  	    
	    //appendStrToFile(".\\output\\testout.txt", "\nNumber of file : " + count );
	    
	    
	    //logger.info("Number of file written to file: " + count);	    
	    
	    
	    
	    
	}
	
	//listing of files in a specific folder
	public static void listFilesForFolder(final File folder) {			 
		
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            listFilesForFolder(fileEntry);
	        } else {	           
	        	appendStrToFile(".\\output\\testout.txt", "\n" + fileEntry.getName());
	        }
	    }
	}
	
	//listing of files in a specific folder
	public static void listFilesInTheFolder() {			 
		File f = null;
		String[] paths;	
		int count = 0;
		  try {   
		         // create new file
		         f = new File(FolderPath);
		                                 
		         // array of files and directory
		         paths = f.list();
		         count = paths.length;
		         System.out.println("file count in the directory: " + count);
		         
		         
		         //writeToFile("\nNumber of file : " + count );
		            
		         // for each name in the path array
		         for(String path:paths) {
		         
		            // prints filename and directory name
		            System.out.println(path);
		            appendStrToFile(".\\output\\testout.txt", "\n" + path );
		         }
		         appendStrToFile(".\\output\\testout.txt", "\nNumber of file : " + count );
		      } catch(Exception e) {
		         // if any error occurs
		         e.printStackTrace();
		      }
		  
		  
		  
	}
	
	//new file creation
	public static void writeToFile(String s) {
		try {
			FileWriter fw=new FileWriter(".\\output\\testout.txt");
			fw.write(s);
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  		  
	}
	
	//append to existing file
	public static void appendStrToFile(String fileName, String str) 
	{ 
		try { 

			File file = new File(fileName);
			FileWriter fr = new FileWriter(file, true);
			BufferedWriter br = new BufferedWriter(fr);
			br.write(str);
			
			br.close();
			fr.close();

			
			
			/*
			// Open given file in append mode. 
			BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true)); 
			out.write(str); 
			out.close(); 
			*/
		} catch (IOException e) { 
			System.out.println("exception occoured" + e); 
		} 
	} 
	
	//read from a file
	public static void ReadFile(String fileName) throws IOException
	{
		Path path = Paths.get(fileName);
		Scanner scanner = new Scanner(path);
		System.out.println("Read text file using Scanner");
		//logger.info("Read text file using Scanner");
		int ntoken = 0;
		//read line by line
		while(scanner.hasNextLine()){
		    //process each line
			ntoken++;
			String line = scanner.nextLine();
			if(ntoken==1) {
				FolderPath = line;
			}
		    System.out.println(line);
		}
		scanner.close();	
	}
	
	//read from a file
	public static void ReadFileParagraph(String fileName) throws IOException
	{
		Path path = Paths.get(fileName);
		Scanner scanner = new Scanner(path);
		System.out.println("Read text file using Scanner as pragraphs");
		//read line by line
		scanner.useDelimiter("(?m:^$)");
		int ntoken = 0;
		while(scanner.hasNextLine()){
		    //process each line
			String token = scanner.next();
			ntoken++;
			System.out.printf("%3d) %s%n", ntoken, token);		    
		}
		scanner.close();	
	}

}
