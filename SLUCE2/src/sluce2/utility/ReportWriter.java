package sluce2.utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import sluce2.ModelContext;
import sluce2.GlobalConstants;
import sluce2.agent.Developer;


/**
 * A simple class that writes to a report file.
 * 
 * @author Meghan Hutchins
 * @date 09/16/2010
 */
@Deprecated
public class ReportWriter
{
    
    /** Log4j Logger used for debugging and code tracking **/
    private final static Logger logger;
 
    /** A test developer **/
    Developer devTest; 
    
    static
    {
	logger = LogHelper.getConsoleLogger(ReportWriter.class.getName());
	logger.setLevel(Level.ERROR);
    }
    
    
    /** Writes output to a report file, buffering characters so as to provide for more efficient writing **/
    private BufferedWriter bufferedWriter;
    
    /** Full path to report file **/
    private File file;
    
    
    /**
     * Default Constructor
     */
    public ReportWriter()
    {
	// Nothing to do here
    }
    
    /**
     * Construct ReportWriter from directory, file name and optional time-stamp
     * @param String, String, boolean
     */
    public ReportWriter(String directory, String filename, boolean addTimeStamp)
    {
	String filePath = null;
	
	// Construct file path
	if(addTimeStamp)
	{
	    DateFormat format = new SimpleDateFormat(GlobalConstants.TIMESTAMP_FORMAT_REPORT);
            filePath = directory + StringUtils.addTimestampToFileName(filename, format, false);
	}
	
	// Create new file
	try
	{
	    this.file = new File(filePath);
	
	    if(!file.exists()) 
	    {
		file.createNewFile();
	    }
	}
	catch(IOException ioe)
	{
	    logger.error(ioe + ": The file path: " + file + " was not found. Please check this path and try again.");
	}
	
    }
    
    
    /**
     * Writes the given entry to the {@link file}.
     * This method will flush the entry to the file right away.
     * @return void
     */
    public void write(String entry)
    {
	String fullEntry = ModelContext.getCurrentTick() + ", " + entry;
	
	try
	{
	    if(this.bufferedWriter == null)
	    {
		FileWriter fileWriter = new FileWriter(file);
		bufferedWriter = new BufferedWriter(fileWriter);
		logger.debug("created new buffered writer for: " + file);
	    }

	    bufferedWriter.write(fullEntry);
	    bufferedWriter.newLine();
	    bufferedWriter.flush();
	    
	}
	catch(FileNotFoundException fnfe)
	{
	    logger.error(fnfe + ": The file path: " + file + " was not found. Please check this path and try again.");
	}
	catch (IOException ioe)
	{
	    logger.error(ioe.getMessage());
	}
    }
    
    /**
     * Flushes the report entries out to the file
     * @return void
     */
    public void flush()
    {
	try
	{
	    bufferedWriter.flush();
	}
	catch (IOException ioe)
	{
	    ioe.printStackTrace();
	}
    }
    
    /**
     * Closes this ReportWriter, freeing OS resources.
     * @return void
     */
    public void close()
    {
	try
	{
	    this.flush();
	    bufferedWriter.close();
    	}
	catch (IOException ioe)
	{
	    ioe.printStackTrace();
	}
    }

}
