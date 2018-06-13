package test.sluce2.utility.file;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import sluce2.GlobalConstants;
import sluce2.utility.file.BinaryFileHandler;
import sluce2.utility.file.FileHandler;
import sluce2.utility.file.TextFileHandler;
import junit.framework.TestCase;

public class TestBinaryFileIO extends TestCase
{
	String filePath = "/home/meghan/JUnitTests/sluce2/test/oth3.endpoint";
	FileHandler fileHandler; 
	
    /**
     * 
     */
    protected void setUp() 
    {
    	try
    	{
    		fileHandler = new BinaryFileHandler(filePath, GlobalConstants.BGC_NBR_BYTES_IN_RESTART); 
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
    	lineArray.add("0.2");
    	lineArray.add("0.4");
    	
    	fileHandler.writeArrayToFile(lineArray);
    	
    	fileHandler.printFile();
    }
    
}
