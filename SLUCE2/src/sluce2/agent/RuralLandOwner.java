package sluce2.agent;

import repast.simphony.annotate.AgentAnnot;


/**
 * Represents the RuralLandOwner agent. 
 *  
 * @author Meghan Hutchins
 * @date 09/16/2010
 */
@AgentAnnot(displayName = "RuralLandOwner")
public class RuralLandOwner extends LandOwnerAgent
{

    /**
     * 
     */
    public RuralLandOwner()
    {
    	AgentContext.logger.debug("RuralLandowner agent created(ID:" + this.getId() + ")");
	
    }
    
    /**
     * RuralLandOwner step method.
     * @return void
     */
    @Override
    public void step() 
    {
    	AgentContext.logger.debug(this + " takes a step : does nothing.");
    }
    
    
    
    
}