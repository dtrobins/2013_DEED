package sluce2.agent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.MultiMap;
import org.apache.commons.collections.map.MultiValueMap;

import sluce2.Model;
import sluce2.ModelParam;
import sluce2.ModelContext;
import sluce2.landscape.LandscapeContext;
import sluce2.management.strategy.ManagementStrategyCreator;
import sluce2.management.strategy.ManagementStrategyType;

/**
 * An AgentRegistry is a store of all agents in the model.<p>
 * This class acts as an associated store that returns the agent matching a given key. 
 * It has operations for registering an agent under a key and for unregistering it. 
 * Clients can change or browse the registry at run-time.
 * 
 * @author Meghan Hutchins
 * @date 01.13.2011
 */
public class AgentRegistry 
{
	/** A Map that holds all agents in the registry **/
    private MultiMap agentMap; 
    
    /**
     * Creates an AgentRegistry based on the given Model.
     * @param Model
     */
    public AgentRegistry(Model model)
    {
    	agentMap = new MultiValueMap();
    	this.loadRegistry(model);
    }
    
    public SimpleAgent createAgent(AgentType type)
    {
    	SimpleAgent newAgent = (((SimpleAgent[])agentMap.get(type))[0]).clone();
    	agentMap.put(type, newAgent);
    	return newAgent;
    }
    
    /**
     * Creates the initial set of agents in this registry for the given Model.
     * @param Model
     */
    private void loadRegistry(Model model)
    {    	
    	this.createRuralLandOwnerAgents();
    	this.createDeveloperAgents( (ModelContext.getParameterInteger(ModelParam.initNbrOfDeveloperAgents)) );
    	this.createLandBrokerAgents( (ModelContext.getParameterInteger(ModelParam.initNbrOfLandBrokerAgents)) );
    	this.createResidentHouseholdAgents( (ModelContext.getParameterInteger(ModelParam.initNbrOfResidentHouseholdAgents)) );
    }
    
    /**
	 * Creates as many RuralLandOwner agents as there are farms in the current landscape.
	 * @return void
	 */
    private void createRuralLandOwnerAgents()
    {	
    	if(LandscapeContext.hasInstance())
		{
		    for(Number id :(Set<Number>)LandscapeContext.getInstance().getUniqueFarmParcelIDs())
		    {
		    	RuralLandOwner agent = new RuralLandOwner();
		    	agent.buyParcelID((Integer)id, 0.00);
		    	agentMap.put(AgentType.RURALLANDOWNER, agent);
		    }
		}
    }
    
    /**
	 * Creates the given number of Developer agents.
	 * @return void
	 */
    private void createDeveloperAgents(int nbrOfAgents)
    {
    	for(int i=0; i<nbrOfAgents; i++)
    	{
    		Developer agent = new Developer();
    		/** TODO: Set template types */
    		agentMap.put(AgentType.DEVELOPER, agent);
    	}
    }
    
    /**
	 * Creates the given number of LandBroker agents.
	 * @return void
	 */
    private void createLandBrokerAgents(int nbrOfAgents)
    {
    	for(int i=0; i<nbrOfAgents; i++)
    	{
    		LandBroker agent = LandBroker.instance();
    		
    		/** TODO: Set template types */
    		
    		agentMap.put(AgentType.LANDBROKER, agent);
    	}
    }
    
    /**
	 * Creates the given number of ResidentHousehold agents.
	 * @return void
	 */
    private void createResidentHouseholdAgents(int nbrOfAgents)
    {   	
    	for(int i=0; i<nbrOfAgents; i++)
    	{
    		//ResidentHousehold agent = new ResidentHousehold();
    		ResidentHousehold agent = new ResidentHousehold(100.00, 0.25);
    		
    		/** TODO: Set budget **/
    		/** TODO: Set prefToUrbanAreas
    		/** TODO: Set managementRegime */
    		
    		agentMap.put(AgentType.RESIDENTHOUSEHOLD, agent);
    	}
    }
    
    
//-----------------------
// Get Lists of Agents
    
    /**
     * @return returns a List of the ResidentHousehold agents in the AgentRegistry.
     */
    @SuppressWarnings("unchecked")
	public Iterator<SimpleAgent> getAgentListIter()
    {
		return agentMap.values().iterator();
    }
    
    /**
     * @return returns a List of the RuralLandOwner agents in the AgentRegistry.
     */
    @SuppressWarnings("unchecked")
	public List<RuralLandOwner> getRuralLandOwnerListing()
    {
		return (List<RuralLandOwner>)agentMap.get(AgentType.RURALLANDOWNER);
    }
    
    /**
     * @return returns a List of the Developer agents in the AgentRegistry.
     */
    @SuppressWarnings("unchecked")
	public List<Developer> getDeveloperListing()
    {
		return (List<Developer>)agentMap.get(AgentType.DEVELOPER);
    }
    
    /**
     * @return returns a List of the LandBroker agents in the AgentRegistry.
     */
    @SuppressWarnings("unchecked")
	public List<LandBroker> getLandBrokerListing()
    {
		return (List<LandBroker>)agentMap.get(AgentType.LANDBROKER);
    }
    
    /**
     * @return returns a List of the ResidentHousehold agents in the AgentRegistry.
     */
    @SuppressWarnings("unchecked")
	public List<ResidentHousehold> getResidentHouseholdListing()
    {
		return (List<ResidentHousehold>)agentMap.get(AgentType.RESIDENTHOUSEHOLD);
    }
      
}
