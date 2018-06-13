package test.sluce2.ecosystem;

import sluce2.GlobalConstants;
import sluce2.Model;
import sluce2.build.ModelBuilder;
import sluce2.build.SampleModelBuilder;
import sluce2.ecosystem.biomebgc.BiomeBGCFileManager;
import sluce2.ecosystem.biomebgc.BiomeBGCProperties;
import junit.framework.TestCase;


public class TestBiomeBGCFileHandler extends TestCase
{
	protected String rootDirectory = "/home/meghan/Desktop/SLUCE2-Environment/SLUCE2-BGC-tmp/";

	protected ModelBuilder builder;
	protected Model model;
	protected String pathToBiomeBGCDescriptor;
	protected BiomeBGCFileManager bgcFileHandler;
	
	/**
     * 
     */
    protected void setUp() 
    {
    	builder = new SampleModelBuilder(GlobalConstants.SLUCE2_RESOURCES);
    	model = builder.getModel();
    	pathToBiomeBGCDescriptor = model.getResource(GlobalConstants.ECOSYSTEM_MODEL_DESCRIPTOR);
    	BiomeBGCProperties bgcProperties = new BiomeBGCProperties(this.pathToBiomeBGCDescriptor);
    	bgcFileHandler = BiomeBGCFileManager.createInstance(bgcProperties);
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
    public void testIncrementTick()
    {
    	System.out.println("------------------------");
    	System.out.println(" Increment Tick ");
    	System.out.println("------------------------");
    	
    	bgcFileHandler.incrementCurrentTickDirectory();
    	
    	
    }
    
}
