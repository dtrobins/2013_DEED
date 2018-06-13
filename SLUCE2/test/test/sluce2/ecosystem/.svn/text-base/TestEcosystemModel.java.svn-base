package test.sluce2.ecosystem;

import junit.framework.TestCase;
import sluce2.Model;
import sluce2.ModelContext;
import sluce2.GlobalConstants;
import sluce2.build.ModelBuilder;
import sluce2.build.SampleModelBuilder;
import sluce2.ecosystem.EcosystemModel;
import sluce2.ecosystem.biomebgc.BiomeBGCModel;
import sluce2.ecosystem.biomebgc.BiomeBGCProperties;
import sluce2.ecosystem.biomebgc.SimpleCarveRunner;
import sluce2.ecosystem.biomebgc.SimpleCellRunner;
import sluce2.landscape.component.Parcel;

public class TestEcosystemModel extends TestCase
{
	protected ModelBuilder builder;
	protected Model model;
	protected String pathToBiomeBGCDescriptor;
	protected BiomeBGCProperties bgcProperties; 
	
	/**
     * 
     */
    protected void setUp() 
    {
    	builder = new SampleModelBuilder(GlobalConstants.SLUCE2_RESOURCES);
    	model = builder.getModel();
    	
    	pathToBiomeBGCDescriptor = model.getResource(GlobalConstants.ECOSYSTEM_MODEL_DESCRIPTOR);
    	bgcProperties = new BiomeBGCProperties(this.pathToBiomeBGCDescriptor);
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
    public void testBiomeBGCRun()
    {
    	System.out.println("===================");
    	System.out.println(" = BiomeBGC Only = ");
    	System.out.println("===================");
    	
    	EcosystemModel ecosystemModel = new EcosystemModel(new BiomeBGCModel(bgcProperties));
    	
    	for(int i=0; i<1; i++)
    	{
    		ecosystemModel.run();
    	}
    }

}
