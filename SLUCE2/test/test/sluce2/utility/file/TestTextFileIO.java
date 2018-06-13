package test.sluce2.utility.file;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import sluce2.utility.file.FileHandler;
import sluce2.utility.file.TextFileHandler;
import junit.framework.TestCase;

public class TestTextFileIO extends TestCase
{
	String filePath = "/home/meghan/JUnitTests/sluce2/test/testTextFile.txt";
	FileHandler fileHandler; 
	
    /**
     * 
     */
    protected void setUp() 
    {
    	try
    	{
    		fileHandler = new TextFileHandler(filePath); 
    	}
		catch(FileNotFoundException fnfe)
		{
			System.out.println(fnfe);
		}
    }
    
    /**
     * 
     */
    protected void tearDown() 
    {
    	fileHandler = null;
    }
    
    /**
     * 
     */
    public void testReadFile()
    {
    	fileHandler.printFile();
    }
    
    /**
     * 
     */
    public void testWriteFile()
    {
    	System.out.println("------------");
    	
    	ArrayList<String> lineArray = new ArrayList<String>();
    	lineArray.add("How now ");
    	lineArray.add("  brown Cow .");
    	
    	fileHandler.writeArrayToFile(lineArray);
    	
    	fileHandler.printFile();
    }
    
}
