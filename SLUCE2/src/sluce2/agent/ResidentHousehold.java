package sluce2.agent;

import repast.simphony.annotate.AgentAnnot;
import sluce2.landscape.LandscapeContext;
import sluce2.management.ManagementContext;
import sluce2.management.strategy.ManagementStrategy;


/**
 * Represents the ResidentHousehold agent. 
 * 
 * @author Meghan Hutchins
 * @date 09/16/2010
 */
@AgentAnnot(displayName = "ResidentHousehold")
public class ResidentHousehold extends LandOwnerAgent
{
	/** Budget **/
	private double budget;
	
	/** preference to proximity to urban areas [0-1] **/
	private double prefToUrbanCenter;
	
	/** Management Strategy **/
	private ManagementStrategy mgmtStrategy;
	
    /**
     * 
     */
    public ResidentHousehold()
    {
    	this.budget = 0d;
    	this.prefToUrbanCenter = 0d;
    	this.mgmtStrategy = null;
    	AgentContext.logger.debug("ResidentHousehold agent created (ID:" + this.getId() + ")");
    }
    
    /**
     * Creates a ResidentHousehold agent with the given management strategy, preference to urban center and budget.
     */
    public ResidentHousehold(double budget, double prefToUrbanCenter)
    {
    	this.budget = budget;
    	this.prefToUrbanCenter = prefToUrbanCenter;
    	this.mgmtStrategy = null;
    	AgentContext.logger.debug("ResidentHousehold agent created (ID:" + this.getId() + ")");
    }
    
    
    /**
     * ResidentHousehold step method.
     * @return void
     */
    @Override
    public void step() 
    {
    	AgentContext.logger.debug(this + " takes a step : does nothing."); 
    		
    }
    
    public void manageParcels()
    {
    	for(Integer parcelID : parcelIDList)
    	{
    		mgmtStrategy.doTaskList(parcelID);
    	}
    }
    
//------------------------
// Accessors
    


	public ManagementStrategy getMgmtStrategy()
	{
		return mgmtStrategy;
	}

	public void setMgmtStrategy(ManagementStrategy mgmtStrategy)
	{
		this.mgmtStrategy = mgmtStrategy;
	}

	public double getPrefToUrbanCenter()
	{
		return prefToUrbanCenter;
	}

	public void setPrefToUrbanCenter(double prefToUrbanCenter)
	{
		this.prefToUrbanCenter = prefToUrbanCenter;
	}

	public double getBudget()
	{
		return budget;
	}

	public void setBudget(double budget)
	{
		this.budget = budget;
	}

}