package test.sluce2.management;

import sluce2.ModelContext;
import sluce2.GlobalConstants;
import sluce2.landscape.RasterRepository;
import sluce2.management.template.Template;
import sluce2.management.template.TemplateManager;
import sluce2.utility.Coord;
import junit.framework.TestCase;

/**
 * Tests the functionality of the TemplateManager class.
 * 
 * @author Meghan Hutchins
 * @date 11/01/2010
 */
public class TestTemplateManager extends TestCase
{
    protected String pathToTemplatesDescriptor = ModelContext.model.getResource(GlobalConstants.DEVELOPMENT_TEMPLATE_DESCRIPTOR); 
    protected String[] templateNames = {"IEMSS-1", "IEMSS-Init"};
    
    //IEMSS-Init = put back to initial landscape
    // IEMSS-1 = Develop according to framework
    protected String applyThisTemplate = "IEMSS-Init"; 
    
    TemplateManager templateManager; 
    
    /**
     * 
     */
    protected void setUp() 
    {
    	templateManager = TemplateManager.instance();
    	templateManager.loadTemplates(pathToTemplatesDescriptor, templateNames);
    }
    
    /**
     * 
     */
    protected void tearDown() 
    {
    	templateManager = null;
    }
    
    /**
     * 
     */
    public void testLoadTemplates()
    {
		System.out.println("\n-------------------------------");
		System.out.println("TemplateManager: Load Templates");
		System.out.println("-------------------------------");
		
		templateManager.getNbrOfTemplates();
		assertEquals(templateManager.getNbrOfTemplates(), templateNames.length);
		System.out.println("Nbr of templates loaded: " + templateManager.getNbrOfTemplates());
		for(Object name : templateManager.getTemplateNames())
		{
		    System.out.println(name);
		}
		
		System.out.println("\n" + templateManager);
    }
    
    /**
     * 
     */
    public void testGetTemplate()
    {
		System.out.println("\n-------------------------------");
		System.out.println("TemplateManager: Get Template");
		System.out.println("-------------------------------");
		
		Template iemss1 = templateManager.getTemplate("IEMSS-1");
		assertNotNull(iemss1);
		System.out.println(iemss1);
    }
    
    /**
     * 
     */
    public void testApplyTemplate()
    {
		System.out.println("\n-------------------------------");
		System.out.println("TemplateManager: Apply Template");
		System.out.println("-------------------------------");
		
		// Create full landscape
		String pathToFullLandscapeDescriptor = ModelContext.model.getResource(GlobalConstants.LANDSCAPE_DESCRIPTOR); 
		RasterRepository fullLandscape = new RasterRepository(pathToFullLandscapeDescriptor);
		
		templateManager.applyTemplate(applyThisTemplate, fullLandscape, new Coord(0,0));
    }
    
    
    
}