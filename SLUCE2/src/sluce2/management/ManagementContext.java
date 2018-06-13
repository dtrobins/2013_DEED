package sluce2.management;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import sluce2.GlobalConstants;
import sluce2.Model;
import sluce2.ModelComponent;
import sluce2.ModelContext;
import sluce2.agent.AgentContext;
import sluce2.agent.Developer;
import sluce2.agent.ResidentHousehold;
import sluce2.agent.RuralLandOwner;
import sluce2.ecosystem.EcosystemContext;
import sluce2.landscape.LandscapeContext;
import sluce2.landscape.LanduseType;
import sluce2.landscape.component.Parcel;
import sluce2.management.template.Template;
import sluce2.management.template.TemplateManager;
import sluce2.utility.Coord;
import sluce2.utility.LogHelper;

/**
 * The ManagementContext encapsulates the management types and strategies of various agents.
 * <p>
 * Management may include land-cover change, such as when a Developer developers subdivision 
 * or when a ResidentHousehold decides to plant trees. Management may also 
 * include the addition or removal of various nutrient pools.  
 * <p>
 * There is only one instance of the ManagementContext at all times. 
 * <p>
 * @author Meghan Hutchins
 * @date 09/16/2010
 */
public class ManagementContext extends ModelComponent
{
    /** Log4j Logger used for debugging and code tracking **/
    public final static Logger logger;
    
    /** The single instance of the ManagementContext in the model **/
    private static ManagementContext instance; 
    
    /** The single instance of the TemplateManager in the model **/
    private static TemplateManager templateManager;  
    
    static
    {
    	logger = LogHelper.getConsoleLogger(ManagementContext.class.getName());
    	logger.setLevel(GlobalConstants.MANAGEMENTCONTEXT_LOGLEVEL);
    }
    
    /**
     * 
     */
    private ManagementContext()
    {
    	super(GlobalConstants.MANAGEMENTCONTEXT); // must match name in model.score
    }
        
    /**
     * 
     */
    public static ManagementContext createInstance()
    {
    	if(instance == null)
    	{
    		instance = new ManagementContext();
    	}
    	return instance;
    }
    
    /**
     * @return void
     */
    public void step() 
    {
    	long start = System.currentTimeMillis();
    	ManagementContext.logger.debug("--- ManagementComponent takes a step : tells agents to manage. ---");
    	    	    	
    	//------------------------------------------
    	// Developers Manage by developing the land.
    	for( Developer developer : AgentContext.getInstance().getDeveloperAgents() )
    	{
    		if(developer.ownsParcels())
    		{
    			List<Integer> currentParcelList = new ArrayList<Integer>(developer.getParcelIDList());
    			
    			// If farmland, then develop, otherwise sell.
        		for(Integer parcelID : currentParcelList)
        		{
        			Parcel parcel = (Model.getInstance().getLandscapeComponent().getParcel(parcelID));
        			if( LanduseType.AGRICULTURE.equals(parcel.getLanduseType()) )
        			{
        				ManagementContext.logger.debug(developer + " manages by developing on farmland : (" + parcel +")");
        				
        				/** TODO: This was a quick hack. **/
        				Template iemss1 = TemplateManager.instance().getTemplate("IEMSS-1");
        				developer.developSubddivision(iemss1, new Coord(0,0));
        			}
        		}
    		}
    	}
    	
    	//-------------------------------------------------------------------
    	// ResidentHouseholds Manage by changing the nutrients on the land...
    	for( ResidentHousehold resident : AgentContext.getInstance().getResidentHouseholdAgents() )
    	{
    		if(resident.ownsParcels())
    		{
    			resident.manageParcels();	
    		}
    	}
    	
    	
    	ManagementContext.logger.debug("ManagementComponent ran for " + (System.currentTimeMillis() - start) + " milliseconds.");
    }
    
    /**
     * @return true of the development templates were successfully loaded, false otherwise. 
     */
    public void loadDevelopmentTemplates(String pathToTemplateFile, String... templateNames)
    {
    	templateManager = TemplateManager.instance();
    	templateManager.loadTemplates(pathToTemplateFile, templateNames);
    	LandscapeContext.logger.debug("Loaded " + templateManager.getNbrOfTemplates() + " Development Templates");
    }
    
//-------------------
// Accessors
    
    public static ManagementContext getInstance() {
		return instance;
	}

	public static void setInstance(ManagementContext instance) {
		ManagementContext.instance = instance;
	}

	public static TemplateManager getTemplateManager() {
		return templateManager;
	}

	public static void setTemplateManager(TemplateManager templateManager) {
		ManagementContext.templateManager = templateManager;
	}

	public static Logger getLogger() {
		return logger;
	}

	@Override
    public String toString()
    {
    	return this.getClass().getName();
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