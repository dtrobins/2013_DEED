package sluce2.utility.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

import sluce2.ecosystem.EcosystemContext;

/**
 * To safely start and run another "process" within this thread, the error and output
 * stream must be cleaned. If the streams are blocked, there might be deadlocks.<P>
 * 
 * check http://www.javaworld.com/javaworld/jw-12-2000/jw-1229-traps.html?page=4 <P>
 * 
 * "It is possible that the examples will deadlock if the subprocess generates enough output to overflow the system. A more robust solution requires draining the process stdout and stderr in separate threads."
 * @author Shipeng Sun
 */
public class StreamGobbler extends Thread
{
	private InputStream is;
	private OutputStream os;
	private String type;
    
	/**
	 * @param is
	 * @param type
	 */
	public StreamGobbler(InputStream is, String type)
	{
	    this(is, type, null);
	}
	    
	/**
	 * @param is
	 * @param type
	 * @param redirect
	 */
	public StreamGobbler(InputStream is, String type, OutputStream redirect)
	{
	    this.is = is;
	    this.type = type;
	    this.os = redirect;
	}
	    
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run()
	{
		try
	    {
			PrintWriter pw = null;
	        if (os != null)
	        {
	            pw = new PrintWriter(os);
	        }
	        
	        InputStreamReader isr = new InputStreamReader(is);
	        BufferedReader br = new BufferedReader(isr);
	        String line = null;
	            
	        while( (line = br.readLine()) != null)
	        {
	            if (pw != null)
	            {
	            	pw.println(line);
	            }

	        	EcosystemContext.logger.debug(type + " : " + line);    
	        }

	        if (pw != null)
	        {
	            pw.flush();
	        }
	    } 
	    catch (IOException ioe)
	    {
	    	ioe.printStackTrace();  
        }
	}
}