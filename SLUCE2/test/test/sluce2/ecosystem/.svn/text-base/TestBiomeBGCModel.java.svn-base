package test.sluce2.ecosystem;

import junit.framework.TestCase;
import sluce2.Model;
import sluce2.ModelContext;
import sluce2.GlobalConstants;
import sluce2.build.ModelBuilder;
import sluce2.build.SampleModelBuilder;
import sluce2.ecosystem.biomebgc.BiomeBGCModel;
import sluce2.ecosystem.biomebgc.BiomeBGCProperties;

/**
 * 
 * @author Meghan Hutchins
 */
public class TestBiomeBGCModel extends TestCase
{
	protected ModelBuilder builder;
	protected Model model;
	
	protected String pathToBiomeBGCDescriptor;
	protected BiomeBGCProperties bgcProperties; 
	protected BiomeBGCModel biomeBGC;
	
	/**
     * 
     */
    protected void setUp() 
    {
    	builder = new SampleModelBuilder(GlobalConstants.SLUCE2_RESOURCES);
    	model = builder.getModel();
    	
    	pathToBiomeBGCDescriptor = model.getResource(GlobalConstants.ECOSYSTEM_MODEL_DESCRIPTOR);
    	bgcProperties = new BiomeBGCProperties(this.pathToBiomeBGCDescriptor);
    	biomeBGC = new BiomeBGCModel(bgcProperties);
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
    public void testCreation()
    {
    	for(int i=0; i<5; i++)
    	{
    		biomeBGC.run();
    	}
    }
}
