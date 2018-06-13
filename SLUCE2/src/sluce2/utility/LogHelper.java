package sluce2.utility;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import sluce2.GlobalConstants;

/**
 * A simple class to assist with log4j capabilities.
 * 
 * @author Meghan Hutchins
 * @date 09/16/2010
 */
public class LogHelper
{   
	
    /**
     * Returns the console logger associated with the given class name 
     * and uses the default pattern layout.
     * see:{@link GlobalConstants.DEFAULT_LOG4J_PATTERN} 
     *
     * @param String
     * @return Logger
     */
    public static Logger getConsoleLogger(String className)
    {
	Logger logger = Logger.getLogger(className);
	logger.addAppender(new ConsoleAppender(new PatternLayout(GlobalConstants.LOG4J_OUTPUT_PATTERN))); 
	
	return logger;
    }
    
    
    /**
     * Returns the console logger associated with the given class name 
     * and the given regex pattern layout.
     *
     * @param String, String
     * @return Logger
     */
    public static Logger getConsoleLogger(String className, String patternLayout)
    {
	Logger logger = Logger.getLogger(className);
	logger.addAppender(new ConsoleAppender(new PatternLayout(patternLayout))); 
	
	return logger;
    }
   	
}
