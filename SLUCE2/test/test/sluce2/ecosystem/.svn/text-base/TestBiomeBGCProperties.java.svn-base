package test.sluce2.ecosystem;


import junit.framework.TestCase;
import sluce2.Model;
import sluce2.ModelContext;
import sluce2.GlobalConstants;
import sluce2.build.IEMSSModelBuilder;
import sluce2.build.ModelBuilder;
import sluce2.build.SampleModelBuilder;
import sluce2.ecosystem.biomebgc.BiomeBGCProperties;

public class TestBiomeBGCProperties extends TestCase
{
	protected ModelBuilder builder;
	protected Model model;
	protected String pathToBiomeBGCDescriptor;
	
	/**
     * 
     */
    protected void setUp() 
    {
    	builder = new SampleModelBuilder(GlobalConstants.SLUCE2_RESOURCES);
    	model = builder.getModel();
    	pathToBiomeBGCDescriptor = model.getResource(GlobalConstants.ECOSYSTEM_MODEL_DESCRIPTOR);
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
    	BiomeBGCProperties bgcProperties = new BiomeBGCProperties(this.pathToBiomeBGCDescriptor);
    	
    	System.out.println(bgcProperties.getProperty("bgcLandUnitFileLocation"));
    	
    	// Not Binary
    	//System.out.println("----------");
    	//System.out.println(bgcProperties.getIniFile());
    	//bgcProperties.getIniFile().printFile();
    	
    	// Binary
    	//System.out.println("\n----------");
    	//System.out.println(bgcProperties.getRestartInputFile());
    	//bgcProperties.getRestartInputFile().printFile();
    	
    	// Binary
    	//System.out.println("\n----------");
    	//System.out.println(bgcProperties.getRestartOutputFile());
    	//bgcProperties.getRestartOutputFile().printFile();
    	
    	// NOT binary
    	//System.out.println("\n----------");
    	//System.out.println(bgcProperties.getMeteorologyFile());
    	//bgcProperties.getMeteorologyFile().printFile();
    	
    	// NOT binary
    	//System.out.println("\n----------");
    	//System.out.println(bgcProperties.getEpcFile());
    	//bgcProperties.getEpcFile().printFile();

    }
	
}
