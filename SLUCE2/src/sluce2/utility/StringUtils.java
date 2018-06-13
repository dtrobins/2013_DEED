package sluce2.utility;

import java.text.DateFormat;
import java.util.Scanner;

import sluce2.ModelContext;


/**
 * A simple class that handles various String manipulation functions.
 * 
 * @author Meghan Hutchins
 * @date 09/16/2010
 */
public class StringUtils
{
    
    /**
     * Default Constructor
     */
    public StringUtils()
    {
	// Nothing to do here
    }
    
    /**
     * Appends the current date to the end of the filename 
     * along with an optional current tick number. 
     * 
     * @param filename
     * @param format for timestamp
     * @param includeTickNbr
     * @return new filename
     */
    public static String addTimestampToFileName(String filename, DateFormat format, boolean includeTickNbr)
    {
	DateFormat dateFormat = format;
	String extension = null;
	
	if(filename.contains("."))
	{
	    extension = filename.substring(filename.indexOf("."), filename.length());
	    filename = filename.substring(0, filename.indexOf("."));
	}
	String timestamp = (dateFormat.format(new java.util.Date()));
	
	if(true == includeTickNbr)
	{
	    timestamp += (ModelContext.getCurrentTick());
	}
	
	return (filename + timestamp + extension);
    }
    
    /**
     * 
     * @param storagePath
     * @return the filename located at the end of the given storage path
     */
    public static String getFilename(String storagePath)
    {
	return storagePath.substring(storagePath.lastIndexOf("/")+1, storagePath.length());
    }
    
    /**
     * Uses default Tokenizer delimiter (a space)
     * @param str
     * @param tokenIndex
     * @return
     */
    public static String getTokenAt(String str, int tokenIndex)
    {    	
    	String token = null;
    	Scanner scanner = new Scanner(str);
    	
    	int i = 0;
    	while(i<=tokenIndex && scanner.hasNext())
    	{
    		token = scanner.next();
    		i++;
    	}
    	
    	return token;
    }
    
}
