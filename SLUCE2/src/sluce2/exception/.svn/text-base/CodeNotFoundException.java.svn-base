package sluce2.exception;

/**
 * Simple exception thrown when the associated object cannot be located given a particular code.
 * 
 * @author Meghan Hutchins
 * @date 09/16/2010
 */
public class CodeNotFoundException extends Exception
{
    private static final long serialVersionUID = 1L;
    
    private int code; 
    
    /**
     * 
     */
    public CodeNotFoundException()
    {
	@SuppressWarnings("unused")
	int code = 0;
    }
    
    /**
     * 
     * @param code
     */
    public CodeNotFoundException(int code)
    {
	this.code = code;
    }

    /**
     * 
     */
    public String getMessage()
    {
	return this.toString();
    }

    /**
     * 
     */
    public String toString()
    {
	String message = "Unable to find Landuse code";
	return(code!=0)?(message + ": " + code):(message);
    }
    
}