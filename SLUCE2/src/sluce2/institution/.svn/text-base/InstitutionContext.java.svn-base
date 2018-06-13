package sluce2.institution;

import org.apache.log4j.Logger;

import repast.simphony.context.DefaultContext;
import sluce2.GlobalConstants;
import sluce2.ModelComponent;
import sluce2.utility.LogHelper;

/**
 * The InstitutionContext encapsulates the exogenous institutions and policies in the model.
 * <p>
 * There is only one instance of the InstitutionContext at all times. 
 * 
 * @author Meghan Hutchins
 * @date 09/16/2010
 */
public class InstitutionContext extends ModelComponent 
{
    /** Log4j Logger used for debugging and code tracking **/
    protected final static Logger logger;
    
    /** The single instance of the CarveContext in the model **/
    private static InstitutionContext instance; 
    
    static
    {
	logger = LogHelper.getConsoleLogger(InstitutionContext.class.getName());
	logger.setLevel(GlobalConstants.INSTITUTIONCONTEXT_LOGLEVEL);
    }
    
    /**
     * Default Constructor
     * Initializes Context.
     */
    private InstitutionContext()
    {
	super(GlobalConstants.INSTITUTIONCONTEXT); // must match name in model.score
	
    }
    
    /**
     * @return the instance of InstitutionContext 
     */
    public static InstitutionContext createInstance()
    {
	if(instance == null)
	{
	    instance = new InstitutionContext();
	}
	
	return instance;
    }
    
    /**
     * Context step method.
     * @return void
     */
    public void step() 
    {
	/** TODO: specific context step code here. **/
	
	InstitutionContext.logger.debug("InstitutionContext takes a step");
       
    }
    
    @Override
    public String toString()
    {
    	return this.getClass().getName();
    }

//----------------------
// Accessors    
    
	public static InstitutionContext getInstance()
	{
		return instance;
	}

	public static void setInstance(InstitutionContext instance)
	{
		InstitutionContext.instance = instance;
	}

	@Override
	public void initialize()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy()
	{
		// TODO Auto-generated method stub
		
	}
    
    
    
}
