package test.sluce2;

import java.util.List;

import junit.framework.TestCase;
import repast.simphony.context.DefaultContext;
import sluce2.GlobalConstants;
import sluce2.Model;
import sluce2.agent.SimpleAgent;
import sluce2.build.ModelBuilder;
import sluce2.build.SampleModelBuilder;
import sluce2.landscape.LandscapeLayerType;
import umn.geog.helia.landscape.LandscapeLayer;

public class TestInitialization extends TestCase
{
	protected String pathToModelResources = GlobalConstants.SLUCE2_RESOURCES;
	protected ModelBuilder builder;
	protected Model model;
	
	/**
     * 
     */
    protected void setUp() 
    {
    	// Define the type of ModelBuilder we will be using, and pass in the Model Parameters.
    	builder = new SampleModelBuilder(pathToModelResources);
    	
    	// Build all the components (or alternatively, you could build select components one-by-one)
    	builder.buildModelComponents();
    	
    	// Get the fully built model from the builder.
    	model = builder.getModel();
    	assertNotNull(model);
    }
    
    /**
     * 
     */
    protected void tearDown() 
    {
    	
    }
    
    /**
     * 
     */
    public void testModelInit()
    {
    	System.out.println();
    	System.out.println("--------------------");
    	System.out.println("  Model Components  ");
    	System.out.println("--------------------");
    	assertFalse((model.getComponentSet()).isEmpty());
    	for(DefaultContext<Object> component : model.getComponentSet())
    	{
    		System.out.println(component);
    	}
    	
    	if(model.hasLandscapeComponent())
    	{
    		this.checkLandscapeComponent();
    	}
    	
    	if(model.hasEcosystemComponent())
    	{
    		this.checkEcosystemComponent();
    	}
    	
    	if(model.hasAgentComponent())
    	{
    		this.checkAgentComponent();
    	}
    	
    	if(model.hasMarketComponent())
    	{
    		this.checkMarketComponent();
    	}
    	
    	if(model.hasInstitutionComponent())
    	{
    		this.checkInstitutionComponent();
    	}
    }
    
    
    private void checkLandscapeComponent()
    {
    	System.out.println();
    	System.out.println("-----------------------");
    	System.out.println("  Landscape Component  ");
    	System.out.println();
    	
    	assertTrue((model.getLandscapeComponent()).getNbrOfLayers() > 0);
    	System.out.println("Nbr of layers: " + (model.getLandscapeComponent()).getNbrOfLayers() );
    	
    	LandscapeLayer landcoverLayer = (model.getLandscapeComponent()).getLayer(LandscapeLayerType.LANDCOVER);
    	assertNotNull(landcoverLayer.getValueAt(0, 0));
    	System.out.println("Landcover Layer Value at (0,0): " + landcoverLayer.getValueAt(0, 0));
    }
    
    private void checkEcosystemComponent()
    {
    	System.out.println();
    	System.out.println("-----------------------");
    	System.out.println("  Ecosystem Component  ");
    	System.out.println();

    	assertNotNull(model.getEcosystemComponent().getEcosystemModel());
    	System.out.println("Ecosystem Model: " + model.getEcosystemComponent().getEcosystemModel());
    	
    	
    }
    
    private void checkAgentComponent()
    {
    	System.out.println();
    	System.out.println("-------------------");
    	System.out.println("  Agent Component  ");
    	System.out.println();
    	
    	//assertFalse((model.getAgentComponent().getAllAgents()).isEmpty());
    	/*
    	for(SimpleAgent agent : model.getAgentComponent().getAllAgents())
    	{
    		System.out.println(agent);
    	}
    	*/

    }
    
    private void checkMarketComponent()
    {
    	System.out.println();
    	System.out.println("--------------------");
    	System.out.println("  Market Component  ");
    	System.out.println();

    }
    
    private void checkInstitutionComponent()
    {
    	System.out.println();
    	System.out.println("-------------------------");
    	System.out.println("  Institution Component  ");
    	System.out.println();

    }
    
}
