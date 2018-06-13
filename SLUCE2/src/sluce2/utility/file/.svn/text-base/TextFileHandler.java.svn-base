package sluce2.utility.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import sluce2.ModelContext;

/**
 * A wrapper class used for basic file I/O. 
 * 
 * @author Meghan Hutchins
 * @date 01.31.2011
 */
public class TextFileHandler extends FileHandler
{
	
	/** Reads input from the file **/
    private BufferedReader reader;
	/** Writes output to the file **/
    private BufferedWriter writer;
    
    
    /**
     * Creates a new FileHandler to act on the file having the given pathname.
     * @param pathname
     */
    public TextFileHandler(String pathname) throws FileNotFoundException
    {
    	super(pathname);
    }
    
    /**
     * Creates a new FileHandler to act on the file having the given file.
     * @param pathname
     */
    public TextFileHandler(File file) throws FileNotFoundException
    {
    	super(file);
    }
    
    /**
     * Returns an ordered ArrayList where each line of the file is represented by one element in the ArrayList.<p>
     * For example index [0] of the ArrayList represents the first line of the file.
     * @return the ArrayList representation of the file.
     */
    @Override
    public ArrayList<String> fileToArray()
    {
    	ArrayList<String> lineArray = new ArrayList<String>();
    	
    	try
    	{
	    	if(reader==null)
	    	{
	    		FileReader fileReader = new FileReader(file);
	    		reader = new BufferedReader(fileReader);
	    	}
	    	
	    	String line;
	    	while((line = reader.readLine()) != null)
	    	{
	    		lineArray.add(line); 
	    	}
	    	reader.close();
	    	reader=null;
    	}
    	catch(FileNotFoundException fnfe)
    	{
    		ModelContext.getLogger().error(fnfe.getMessage());
    	}
    	catch(IOException ioe)
    	{
    		ModelContext.getLogger().error(ioe.getMessage());
    	}
    	
    	return lineArray;
    }
        
    /**
     * Overwrites the file with the information in the given ArrayList<p>
     * Index [0] of the ArrayList corresponds to the first line that will be written to the file.
     * @return true if the ArrayList was successfully written to the file, false otherwise. 
     */
    @Override
    public boolean writeArrayToFile(ArrayList<String> lineArray)
    {
    	boolean success = false;    	
    	try
    	{
	    	if(writer==null)
	    	{
	    		//FileWriter fileWriter = new FileWriter(file);
	    		FileWriter fileWriter = new FileWriter(this.file);
	    		writer = new BufferedWriter(fileWriter);
	    	}
	    	
	    	for(String line : lineArray)
	    	{
	    		writer.write(line);
	    		writer.newLine();
	    	}
	    	writer.flush();
	    	writer.close();
	    	writer = null;
	    	success = true;
    	}
    	catch(FileNotFoundException fnfe)
    	{
    		ModelContext.getLogger().error(fnfe.getMessage());
    	}
    	catch(IOException ioe)
    	{
    		ModelContext.getLogger().error(ioe.getMessage());
    	}

    	return success; 
    }
    
    /**
     * @param lineNbr
     * @return the String at the given line number (line numbers start at 1)
     */
    public String getLine(int lineNbr)
    {
    	ArrayList<String> fileArray = this.fileToArray();
    	return fileArray.get(lineNbr-1);
    }
         
}
