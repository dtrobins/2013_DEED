package sluce2;

import java.util.LinkedHashSet;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;

import repast.simphony.context.Context;
import sluce2.agent.AgentContext;
import sluce2.ecosystem.EcosystemContext;
import sluce2.institution.InstitutionContext;
import sluce2.landscape.LandscapeContext;
import sluce2.management.ManagementContext;
import sluce2.market.MarketContext;

public class Model
{
	/** The only instance of Model. **/
	private static Model instance;
	
	/** Provides access to external model resources **/
	private ResourceBundle modelResources;
			
	/** The Repast Simphony Context this Model belongs to. **/
	private Context<Object> context;
	
	/** A Set of all sub-Contexts in this Model. Instantiated as LinkedHashSet to maintain insertion order. **/
	private Set<ModelComponent> componentSet;
	
	// Model Components
	private LandscapeContext landscapeComponent;
	private EcosystemContext ecosystemComponent;
	private AgentContext agentComponent;
	private ManagementContext managementComponent;
	private MarketContext marketComponent;
	private InstitutionContext institutionComponent;
	
	/** Psudo-Tick : Used for JUnit Testing Only. **/
    public int pseudoTick; 
	
	/**
	 * 
	 */
	private Model()
	{
		modelResources = null;
		context = null;
		componentSet = new LinkedHashSet<ModelComponent>();
		landscapeComponent = null;
		ecosystemComponent = null;
		agentComponent = null;
		managementComponent = null;
		marketComponent = null;
		institutionComponent = null;
		
		pseudoTick = 0;
		
		if(!ModelContext.hasRepastRuntime())
		{
			ModelContext.setModel(this);
			ModelContext.logger.warn("Repast Runtime Environment is Null: Using pseudoTick");
		}
	}
	
	/**
	 * @return Model
	 */
	public static Model createInstance()
	{
		if(instance == null)
		{
			instance = new Model();
		}
		return instance;
	}
	
	/**
	 * Adds all Model components to the given Context.
	 * @param context
	 */
	public void addToRepastContext(Context<Object> context)
	{
		for(Context<Object> component : this.componentSet)
		{
			context.addSubContext(component);
		}
	}
	
	/**
	 * 
	 */
	public void step()
	{
		// If the model is still running...
		//if( (schedule.getCurrentYear() != GlobalConstants.END_OF_MODEL_RUN) )
		{
			ModelContext.logger.debug("Model tells its components to step...");
			for(ModelComponent component : this.componentSet)
			{
				component.step();
			}
		}
		/*
		else
     	{
     		ModelContext.end();
     		System.exit(0);
     	}
     	*/
		this.pseudoTick++;
	}

	
	/**
	 * @param resourceName
	 * @return
	 */
	public String getResource(String resourceName)
	{
		String resource = null;
		
		try
		{
			resource = this.modelResources.getString(resourceName);
		}
		catch(MissingResourceException mre)
		{
			ModelContext.logger.error(mre + " : " + resourceName);
		}
		
		return resource;		
	}
	

	
	
//---------------------
// Accessors
	
	
	public Context<Object> getContext()
	{
		return context;
	}

	public void setContext(Context<Object> context)
	{
		this.context = context;
	}

	public static Model getInstance()
	{
		return instance;
	}

	public static void setInstance(Model instance)
	{
		Model.instance = instance;
	}

	public Set<ModelComponent> getComponentSet()
	{
		return componentSet;
	}

	public void setComponentSet(Set<ModelComponent> componentSet)
	{
		this.componentSet = componentSet;
	}

	public int getPseudoTick()
	{
		return pseudoTick;
	}

	public void setPseudoTick(int pseudoTick)
	{
		this.pseudoTick = pseudoTick;
	}

	public LandscapeContext getLandscapeComponent()
	{
		return landscapeComponent;
	}

	public void setLandscapeComponent(LandscapeContext landscapeComponent)
	{
		this.landscapeComponent = landscapeComponent;
		this.componentSet.add(this.landscapeComponent);
	}

	public EcosystemContext getEcosystemComponent()
	{
		return ecosystemComponent;
	}

	public void setEcosystemComponent(EcosystemContext ecosystemComponent)
	{
		this.ecosystemComponent = ecosystemComponent;
		this.componentSet.add(this.ecosystemComponent);
	}

	public AgentContext getAgentComponent()
	{
		return agentComponent;
	}

	public void setAgentComponent(AgentContext agentComponent)
	{
		this.agentComponent = agentComponent;
		this.componentSet.add(this.agentComponent);
	}
	
	public ManagementContext getManagementComponent() 
	{
		return managementComponent;
	}

	public void setManagementComponent(ManagementContext managementComponent) 
	{
		this.managementComponent = managementComponent;
		this.componentSet.add(this.managementComponent);
	}

	public MarketContext getMarketComponent()
	{
		return marketComponent;
	}

	public void setMarketComponent(MarketContext marketComponent)
	{
		this.marketComponent = marketComponent;
		this.componentSet.add(this.marketComponent);
	}

	public InstitutionContext getInstitutionComponent()
	{
		return institutionComponent;
	}

	public void setInstitutionComponent(InstitutionContext institutionComponent)
	{
		this.institutionComponent = institutionComponent;
		this.componentSet.add(this.institutionComponent);
	}

	public ResourceBundle getModelResources()
	{
		return modelResources;
	}

	public void setModelResources(ResourceBundle modelResources)
	{
		this.modelResources = modelResources;
	}
	
	public boolean hasLandscapeComponent()
	{
		return (this.landscapeComponent!=null);
	}
	
	public boolean hasEcosystemComponent()
	{
		return (this.ecosystemComponent!=null);
	}
	
	public boolean hasAgentComponent()
	{
		return (this.agentComponent!=null);
	}
	
	public boolean hasMarketComponent()
	{
		return (this.marketComponent!=null);
	}
	
	public boolean hasInstitutionComponent()
	{
		return (this.institutionComponent!=null);
	}
	
}
