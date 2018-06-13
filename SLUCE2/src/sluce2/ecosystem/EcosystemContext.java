package sluce2.ecosystem;

import java.util.List;

import org.apache.log4j.Logger;

import sluce2.GlobalConstants;
import sluce2.Model;
import sluce2.ModelComponent;
import sluce2.consistancy.Observer;
import sluce2.consistancy.Subject;
import sluce2.ecosystem.biomebgc.BiomeBGCFileManager;
import sluce2.ecosystem.biomebgc.BiomeBGCProperties;
import sluce2.landscape.LandscapeContext;
import sluce2.landscape.component.Cell;
import sluce2.utility.LogHelper;


/**
 * 
 * @author Meghan Hutchins
 * @date 2011.02.14
 */
public class EcosystemContext extends ModelComponent implements Observer
{
	/** Log4j Logger used for debugging and code tracking **/
    public final static Logger logger;
    
    /** The single instance of the CarveContext in the model **/
    private static EcosystemContext instance;
    
    /** Ecosystem Model **/
    private EcosystemModel ecosystemModel;
    
    /** Ecosystem model properties **/
    private BiomeBGCProperties bgcProperties;
    
    /** Ecosystem File Manager **/
	private BiomeBGCFileManager bgcFileManager;
    
    static
    {
    	logger = LogHelper.getConsoleLogger(EcosystemContext.class.getName());
    	logger.setLevel(GlobalConstants.ECOSYSTEMCONTEXT_LOGLEVEL);
    }
    
   /**
    * @param pathToEcosystemModelDescriptor
    */
   private EcosystemContext(EcosystemModel ecosystemModel)
   {
	   super(GlobalConstants.ECOSYSTEMCONTEXT);  // must match name in model.score
	   this.ecosystemModel = ecosystemModel;
	  
	   String pathToBiomeBGCDescriptor = Model.getInstance().getResource(GlobalConstants.ECOSYSTEM_MODEL_DESCRIPTOR);
	   bgcProperties = new BiomeBGCProperties(pathToBiomeBGCDescriptor);
	   bgcFileManager = BiomeBGCFileManager.createInstance(bgcProperties);
   }
     
   /**
    * 
    */
   public static EcosystemContext createInstance(EcosystemModel ecosystemModel)
   {
		if(instance == null)
		{
		    instance = new EcosystemContext(ecosystemModel);
		}
		return instance;
   }
   
   @Override
	public void update(Subject subject)
	{
		// Update the ecosystemModel according to changes in the LandscapeContext.
		if(subject instanceof LandscapeContext)
		{
			ecosystemModel.updateLandcover(Model.getInstance().getLandscapeComponent().getChangeMap());
		}
	}
   
   /**
    * Context step method.
    * @return void
    */
   public void step() 
   {
	   long start = System.currentTimeMillis();
	   EcosystemContext.logger.debug("--- EcosystemComponent takes a step : Calls BiomeBGC ---");   
	   
	   List<Cell> cellList = LandscapeContext.getInstance().getCellList(); 
	   for(Cell cell : cellList)
	   {
		   ecosystemModel.run(cell);
	   }
	   // When all done, increment the tick directory.
	   bgcFileManager.incrementCurrentTickDirectory();
	   
	   EcosystemContext.logger.debug("EcosystemComponent ran for " + (System.currentTimeMillis() - start) + " milliseconds.");
	   
   }
   
   @Override
   public String toString()
   {
   		return this.getClass().getName();
   }
   
//------------------
// Accessors

	public static EcosystemContext getInstance()
	{
		return instance;
	}
	
	public static Logger getLogger()
	{
		return logger;
	}
	
	public EcosystemModel getEcosystemModel()
	{
		return ecosystemModel;
	}
	
	public BiomeBGCProperties getBgcProperties()
	{
		return bgcProperties;
	}

	public BiomeBGCFileManager getBgcFileManager()
	{
		return bgcFileManager;
	}

	public void setBgcFileManager(BiomeBGCFileManager bgcFileManager)
	{
		this.bgcFileManager = bgcFileManager;
	}

	public void setEcosystemModel(EcosystemModel ecosystemModel)
	{
		this.ecosystemModel = ecosystemModel;
	}

	public void setBgcProperties(BiomeBGCProperties bgcProperties)
	{
		this.bgcProperties = bgcProperties;
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
