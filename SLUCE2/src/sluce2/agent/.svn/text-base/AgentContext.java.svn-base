package sluce2.agent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import repast.simphony.context.DefaultContext;

import sluce2.ModelComponent;
import sluce2.ModelContext;
import sluce2.GlobalConstants;
import sluce2.ecosystem.EcosystemContext;
import sluce2.landscape.LandscapeContext;
import sluce2.management.ManagementContext;
import sluce2.utility.LogHelper;

/**
 * The AgentContext maintains the different types of agents in the model. 
 * <p>
 * There is only one instance of the AgentContext at all times. 
 * 
 * @author Meghan Hutchins
 * @date 09/16/2010
 */
public class AgentContext extends ModelComponent 
{
	/** Log4j Logger used for debugging and code tracking **/
    public final static Logger logger;
 
    /** The single instance of the AgentContext in the model **/
    private static AgentContext instance; 
    
    /** A store of all agents in the model. **/
    private AgentRegistry agentRegistry;
    
    static
    {
    	logger = LogHelper.getConsoleLogger(AgentContext.class.getName());
    	logger.setLevel(GlobalConstants.AGENTCONTEXT_LOGLEVEL);
    }
     
    /**
     * Default Constructor
     * Initializes Context.
     */
    private AgentContext(AgentRegistry agentRegistry)
    {
    	super(GlobalConstants.AGENTCONTEXT); // must match name in model.score	
    	this.agentRegistry = agentRegistry;
    }
    
    /**
     * @return the instance of AgentContext 
     */
    public static AgentContext createInstance(AgentRegistry agentRegistry)
    {
    	if(instance == null)
    	{
    		instance = new AgentContext(agentRegistry);
    	}
    	return instance;
    }
        
    /**
     * Context step method.
     * @return void
     */
    public void step() 
    {
    	long start = System.currentTimeMillis();
    	AgentContext.logger.debug("--- AgentContext takes a step : tells other agents to shuffle. ---");
	 
		
		// RuralLandOwners take a step...
		List<RuralLandOwner> ruralLandOwnerListing = agentRegistry.getRuralLandOwnerListing();
		Collections.shuffle(ruralLandOwnerListing);
		/* Agents don't directly do anything. Components tell agents to do things.
		for(RuralLandOwner agent : ruralLandOwnerListing)
		{
			agent.step();
		}
		*/
		// Developers take a step...
    	List<Developer> developerListing = agentRegistry.getDeveloperListing();
    	Collections.shuffle(developerListing);
    	/* Agents don't directly do anything. Components tell agents to do things.
		for(Developer agent : developerListing)
		{
			agent.step();
		}
		*/
		// LandBrokers take a step...
		List<LandBroker> landBrokerListing = agentRegistry.getLandBrokerListing();
    	Collections.shuffle(landBrokerListing);
    	/* Agents don't directly do anything. Components tell agents to do things.
		for(LandBroker agent : landBrokerListing)
		{
			agent.step();
		}
		*/
		// ResidentHouseholds take a step...
		List<ResidentHousehold> residentHouseholdListing = agentRegistry.getResidentHouseholdListing();
    	Collections.shuffle(residentHouseholdListing);
    	/* Agents don't directly do anything. Components tell agents to do things.
		for(ResidentHousehold agent : residentHouseholdListing)
		{
			agent.step();
		}
		*/
    	AgentContext.logger.debug("AgentComponent ran for " + (System.currentTimeMillis() - start) + " milliseconds.");
    	
    }
    
    /**
     * 
     */
    @Override
    public String toString()
    {
    	return this.getClass().getName();
    }
    
 
//-------------------------
// Get Lists of Agents      
    
    /**
     * @return a List of all agents in this model.
     */
    public Iterator<SimpleAgent> getAllAgents()
    {
    	return this.agentRegistry.getAgentListIter();
    }
    
    /**
     * @return a List of RuralLandOwner agents in this model.
     */
    public List<RuralLandOwner> getRuralLandOwnerAgents()
    {
    	return this.agentRegistry.getRuralLandOwnerListing();
    }
    
    /**
     * @return a List of Developer agents in this model.
     */
    public List<Developer> getDeveloperAgents()
    {
    	return this.agentRegistry.getDeveloperListing();
    }
    
    /**
     * @return a List of LandBroker agents in this model.
     */
    public List<LandBroker> getLandBrokerAgents()
    {
    	return this.agentRegistry.getLandBrokerListing();
    }
    
    /**
     * @return a List of ResidentHousehold agents in this model.
     */
    public List<ResidentHousehold> getResidentHouseholdAgents()
    {
    	return this.agentRegistry.getResidentHouseholdListing();
    }
    
    /**
     * @param id
     * @return the agent having the given id.
     */
    public SimpleAgent getAgent(int id)
    {
    	SimpleAgent agent = null;

    	Iterator<SimpleAgent> iter = this.agentRegistry.getAgentListIter();
    	while(agent==null && iter.hasNext())
    	{
    		SimpleAgent thatAgent = iter.next();
    		if(thatAgent.getId()==id)
    		{
    			agent = thatAgent;
    		}
    	}
    	
    	return agent;
    }
    

//-------------------------
// Accessors    
    
	public static AgentContext getInstance()
	{
		return instance;
	}

	public static void setInstance(AgentContext instance)
	{
		AgentContext.instance = instance;
	}

	public Logger getLogger()
	{
		return logger;
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