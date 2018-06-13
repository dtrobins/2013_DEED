package test.sluce2.landscape;

import javax.naming.NameNotFoundException;

import sluce2.ModelContext;
import sluce2.GlobalConstants;
import sluce2.landscape.RasterRepository;
import sluce2.management.template.Template;
import sluce2.utility.Coord;
import umn.geog.helia.landscape.LandscapeLayer;
import junit.framework.TestCase;

/**
 * Tests the functionality of the RasterRepository class.
 * 
 * @author Meghan Hutchins
 * @date 11/01/2010
 */
public class TestRasterRepository extends TestCase
{   
    protected String pathToFullLandscapeDescriptor = ModelContext.model.getResource(GlobalConstants.LANDSCAPE_DESCRIPTOR); 
    protected String pathToTemplatesDescriptor = ModelContext.model.getResource(GlobalConstants.DEVELOPMENT_TEMPLATE_DESCRIPTOR); 
    protected String pathToHistoryDirectory = ModelContext.model.getResource(GlobalConstants.LANDSCAPE_ARCHIVE_LOCATION);
    protected String templateName = "IEMSS-1";
    
    protected RasterRepository fullLandscape; 
    protected Template template; 
    
    /**
     * 
     */
    protected void setUp() 
    {
	fullLandscape = new RasterRepository(pathToFullLandscapeDescriptor);
	template = new Template(pathToTemplatesDescriptor, templateName);
    }
    
    /**
     * 
     */
    protected void tearDown() 
    {
	fullLandscape = null;
    }
    
    /**
     * 
     */
    public void testLoadFullLandscape()
    {
	System.out.println("--------------------------");
	System.out.println("RasterRepository: Load Full Landscape");
	System.out.println("--------------------------");
	int nbrOfLayers = fullLandscape.getNbrOfLayers();
	assertTrue(nbrOfLayers > 0);
	System.out.println(fullLandscape);
    }
    
    /**
     * 
     */
    public void testLoadTemplate()
    {
	System.out.println("--------------------------");
	System.out.println("RasterRepository: Load Template");
	System.out.println("--------------------------");
	int nbrOfLayers = template.getNbrOfLayers();
	assertTrue(nbrOfLayers > 0);
	System.out.println(template);
    }
    
    /**
     * 
     */
    public void testGetLandscapeLayer()
    {
	System.out.println("--------------------------");
	System.out.println("RasterRepository: Get Landscape Layer");
	System.out.println("--------------------------");
	LandscapeLayer layer = fullLandscape.getLayer("Landuse");
	System.out.println("Storage path of this Landscape Layer: " + layer.getStoragePath());
    }
    
    /**
     * 
     */
    public void testGetTemplateLayer()
    {
	System.out.println("\n--------------------------");
	System.out.println("RasterRepository: Get Layer");
	System.out.println("--------------------------");
	LandscapeLayer layer = template.getLayer("Landcover");
	System.out.println("Storage path of this Template Layer: " + layer.getStoragePath());
    }
    
    /**
     * 
     */
    public void testGetLandscapeValue()
    {
	System.out.println("\n--------------------------");
	System.out.println("RasterRepository: Get Landscape Value");
	System.out.println("--------------------------");
	Number value = fullLandscape.getValue("Landuse", new Coord(0,0));
	System.out.println("Returned landscape value: " + value);
    }
    
    /**
     * 
     */
    public void testGetTemplateValue()
    {
	System.out.println("\n--------------------------");
	System.out.println("RasterRepository: Get Template Value");
	System.out.println("--------------------------");
	Number value = template.getValue("Landcover", new Coord(0,0));
	System.out.println("Returned template value: " + value);
    }
    
    /**
     * 
     */
    public void testGetLayerIndex()
    {
	System.out.println("\n--------------------------");
	System.out.println("Get Layer By Index");
	System.out.println("--------------------------");
	
	String layerName = "SecondaryRoad";
	
	Integer index = null;
	try
	{
	    index = template.getLayerIndex(layerName);
	}
	catch(NameNotFoundException nnfe)
	{
	    System.out.println("Could not find the name specified (" + layerName + ")");
	}
	
	System.out.println(layerName + " = index: " + index);
    }
    
    /**
     * 
     */
    public void testPutLandscapeValue()
    {
	int newValue = 2;
	boolean updateLayers = false;
	
	System.out.println("\n--------------------------");
	System.out.println("RasterRepository: Put Landscape Value");
	System.out.println("--------------------------");
	Number value = fullLandscape.getValue("Landuse", new Coord(0,0));
	System.out.println("Returned landscape value: " + value);
	Boolean success = fullLandscape.putValue("Landuse", new Coord(0,0), newValue);
	assertTrue(success);
	System.out.println("put successfull? " + success);
	value = fullLandscape.getValue("Landuse", new Coord(0,0));
	System.out.println("New landscape value: " + value);
	
	/*
	 * NB: In order for the "put" value to stick, you have to update the layers.
	 */
	if(updateLayers && fullLandscape.saveAllLayers())
	{
	    System.out.println("Layer updated with new value.");
	}
	else
	{
	    System.out.println("Layer was NOT updated with new value."); 
	}
    }
    
    /**
     * 
     */
    public void testPutTemplateValue()
    {
	int newValue = 4;
	boolean updateLayers = false;
	
	System.out.println("\n--------------------------");
	System.out.println("RasterRepository: Put Template Value");
	System.out.println("--------------------------");
	Number value = template.getValue("Landcover", new Coord(0,0));
	System.out.println("Returned template value: " + value);
	Boolean success = template.putValue("Landcover", new Coord(0,0), newValue);
	assertTrue(success);
	System.out.println("put successfull? " + success);
	value = template.getValue("Landcover", new Coord(0,0));
	System.out.println("New template value: " + value);
	
	/*
	 * NB: In order for the "put" value to stick, you have to update the layers
	 */
	if(updateLayers && template.saveAllLayers())
	{
	    System.out.println("Layer updated with new value.");
	}
	else
	{
	    System.out.println("Layer was NOT updated with new value."); 
	}
	
    }
    
    /**
     * 
     */
    public void testSaveOneLandscapeLayer()
    {
	System.out.println("\n---------------------------------");
	System.out.println("RasterRepository: Save One Landscape Layer");
	System.out.println("---------------------------------");
	
	Number valueA = 999; 
	Number valueB = 555;
	
	
	fullLandscape.putValue(0, new Coord(0,0), valueA);
	fullLandscape.putValue(1, new Coord(0,0), valueB);
	
	fullLandscape.saveLayer(1);
	System.out.println("* Check for new file in: " + this.pathToHistoryDirectory);

	assertTrue(fullLandscape.getValue(1, new Coord(0,0)).equals(valueB));
    }
    /**
     * 
     */
    public void testSaveLandscapeLayerToNewFile()
    {
	System.out.println("\n---------------------------------");
	System.out.println("RasterRepository: Save Landscape Layer to New File");
	System.out.println("---------------------------------");
	/*
	 * NB: For testing, last parameter must be false because it indicates the "tick"
	 *     which we don't have here.
	 */
	fullLandscape.putValue(0, new Coord(0,0), 777);
	
	boolean success = fullLandscape.saveLayerTo("Landuse", pathToHistoryDirectory, true, true);
	assertTrue(success);
	System.out.println("saved to history folder successfull? " + success);
	System.out.println("* Check for new file in: " + this.pathToHistoryDirectory);
	
    }
    
}