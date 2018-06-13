package sluce2.agent;

import java.util.List;

import repast.simphony.annotate.AgentAnnot;
import sluce2.ModelContext;


/**
 * Represents a generic agent
 * 
 * @author Meghan Hutchins
 * @date 09/16/2010
 */
@AgentAnnot(displayName = "Agent")
public abstract class SimpleAgent
{
	/** The id of the SimpleAgent **/
	private int id; 
	
	/** The id of next SimpleAgent **/
	private static int nextId; 
	
	static
	{
		nextId = 0;
	}
	
	/**
	 * 
	 */
	public SimpleAgent()
	{
		this.id = nextId;
		nextId++;
	}
	
	/**
	 * Creates a "deep copy" of this agent.
	 * @return SimpleAgent
	 */
	public SimpleAgent clone()
	{
		SimpleAgent clonedAgent = null;
		try
		{
			clonedAgent = (SimpleAgent)super.clone();
		}
		catch(CloneNotSupportedException cnse)
		{
			ModelContext.model.getAgentComponent().getLogger().error("Error cloning agent: " + this + " - " + cnse);
		}
		
		return clonedAgent;
	}
	
	/**
	 * SimpleAgent step method.
	 * @return void
	 */
	public void step() 
	{
		// Generic agent step code here. 
	}
	
	/**
	 * 
	 */
	public String toString()
	{
		return (this.getClass() + " #" + id);
	}
    
//-------------------------
//  Accessors   
            
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
    
}