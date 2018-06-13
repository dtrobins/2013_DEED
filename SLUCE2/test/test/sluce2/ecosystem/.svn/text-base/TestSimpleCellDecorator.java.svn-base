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
import sluce2.ecosystem.biomebgc.SimpleCellRunner;
import sluce2.landscape.LandscapeContext;
import sluce2.landscape.component.Cell;
import sluce2.utility.Coord;

public class TestSimpleCellDecorator  extends TestCase
{
	protected ModelBuilder builder;
	protected Model model;
	protected String pathToBiomeBGCDescriptor;
	
	protected String pathToFullLandscapeDescriptor;
	
	protected BiomeBGCProperties bgcProperties; 
	
	/**
     * 
     */
    protected void setUp() 
    {
    	builder = new SampleModelBuilder(GlobalConstants.SLUCE2_RESOURCES);
    	model = builder.getModel();
    	pathToBiomeBGCDescriptor = model.getResource(GlobalConstants.ECOSYSTEM_MODEL_DESCRIPTOR);
    	pathToFullLandscapeDescriptor = model.getResource(GlobalConstants.LANDSCAPE_DESCRIPTOR); 
    	
    	LandscapeContext.createInstance(pathToFullLandscapeDescriptor);
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
    public void testBiomeBGCWithSimpleCellDecorator()
    {
    	System.out.println("=====================================");
    	System.out.println(" = BiomeBGC w SimpleCell Decorator = ");
    	System.out.println("=====================================");
    	    	
    	EcosystemModel ecosystemModel = new EcosystemModel(new SimpleCellRunner(new BiomeBGCModel(bgcProperties)));  	
    	Cell cell = LandscapeContext.getInstance().getCell(new Coord(0,0));
    	Cell cellBefore = cell;
  
    	System.out.println("-----------------------");
    	System.out.println("Restart Before BiomeBGC");
    	System.out.println("-----------------------");
    	bgcProperties.printContentsOfRestartFile();
    	
    	for(int i=0; i<1; i++)
    	{
    		ecosystemModel.run(cell);
    	}

    	System.out.println("----------------------");
    	System.out.println("Restart After BiomeBGC");
    	System.out.println("----------------------");
    	bgcProperties.printContentsOfRestartFile();
    	
    	
    	System.out.println("Cell Before BiomeBGC Run:");
    	cellBefore.printDetails();
    	System.out.println("Cell After BiomeBGC Run:");
    	cell.printDetails();
    	
    }

}
