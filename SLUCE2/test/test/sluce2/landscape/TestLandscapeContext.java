package test.sluce2.landscape;

import java.util.Set;

import sluce2.ModelContext;
import sluce2.GlobalConstants;
import sluce2.landscape.LandscapeContext;
import sluce2.landscape.LandscapeLayerType;
import sluce2.landscape.component.Cell;
import sluce2.landscape.component.LandUnit;
import sluce2.landscape.component.Parcel;
import sluce2.utility.Coord;
import junit.framework.TestCase;

/**
 * Tests the functionality of the LandscapeContext class.
 * 
 * @author Meghan Hutchins
 * @date 11/01/2010
 */
public class TestLandscapeContext extends TestCase
{
    protected String pathToFullLandscapeDescriptor = ModelContext.model.getResource(GlobalConstants.LANDSCAPE_DESCRIPTOR); 
    protected String pathToHistoryDirectory = ModelContext.model.getResource(GlobalConstants.LANDSCAPE_ARCHIVE_LOCATION);  
        
    /**
     * 
     */
    protected void setUp() 
    {
	LandscapeContext.createInstance(pathToFullLandscapeDescriptor);
    }
    
    /**
     * 
     */
    protected void tearDown() 
    {
	LandscapeContext.getInstance().destroy();
    }
    
    /**
     * 
     */
    public void testGetParcel()
    {
	System.out.println("\n---------------------------------");
	System.out.println("LandscapeContext: getParcel");
	System.out.println("-----------------------------------");
	
	int parcelID = 0; 
	Parcel parcel = LandscapeContext.getInstance().getParcel(parcelID);
	
    }
    
    /**
     * 
     */
    public void testUniqueFarmParcelIDs()
    {
	System.out.println("\n---------------------------------");
	System.out.println("LandscapeContext: farmParcelIDSet");
	System.out.println("-----------------------------------");
	
	Set<Number> farmParcelIDSet = LandscapeContext.getInstance().getUniqueFarmParcelIDs();
	System.out.println("Parcel IDs belonging to Farms:");
	for(Number id : farmParcelIDSet)
	{
	    System.out.println(id);
	}
    }
    
    
    /**
     * 
     */
    public void testGetValue()
    {
	System.out.println("\n--------------------------");
	System.out.println("LandscapeContext: Get Landscape Value");
	System.out.println("--------------------------");
	Number value = LandscapeContext.getInstance().getValue("Landuse", new Coord(0,0));
	assertNotNull(value);
	System.out.println("Returned landscape value: " + value);
    }
    
    /**
     * 
     */
    public void testPutValue()
    {
	int newValue = 2;
	boolean updateLayers = false;
	
	System.out.println("\n--------------------------");
	System.out.println("LandscapeContext: Put Landscape Value");
	System.out.println("--------------------------");
	Number value = LandscapeContext.getInstance().getValue("Landuse", new Coord(0,0));
	System.out.println("Returned landscape value: " + value);
	boolean success = LandscapeContext.getInstance().putValue("Landuse", new Coord(0,0), newValue);
	System.out.println("put successfull? " + success);
	value = LandscapeContext.getInstance().getValue("Landuse", new Coord(0,0));
	System.out.println("New landscape value: " + value);
	
	/*
	 * NB: In order for the "put" value to stick, you have to update the layers.
	 */
	if(updateLayers && LandscapeContext.getInstance().saveLandscape())
	{
	    System.out.println("Landscape updated with new value.");
	}
	else
	{
	    System.out.println("Landscape was NOT updated with new value."); 
	}
    }
    
    /**
     * 
     */
    public void testSaveLandscapeLayerToNewFile()
    {
	System.out.println("\n---------------------------------");
	System.out.println("LandscapeContext: Archive Landscape Layer");
	System.out.println("---------------------------------");
	/*
	 * NB: For testing, last parameter must be false because it indicates the "tick"
	 *     which we don't have here.
	 */
	boolean success = LandscapeContext.getInstance().archiveLayer("Landuse");
	assertTrue(success);
	System.out.println("layer archive successfull? " + success);
	System.out.println("* Check for new file in: " + this.pathToHistoryDirectory);
	
    }
    
    /**
     * 
     */
    public void testCreateCell()
    {
	System.out.println("\n---------------------------------");
	System.out.println("LandscapeContext: Create Cell");
	System.out.println("---------------------------------");

	int index = 0; 
	String layerName = "SoilDepth";
	Number newValueA = 37; 
	Number newValueB = 78;
	
	Cell cell = LandscapeContext.getInstance().getCell(new Coord(0,0));
	assertNotNull(cell);
	
	System.out.println("Created new cell:");
	cell.printDetails();
	
	System.out.println("Cell has "  + cell.getNbrOfLayers() + " layers.");
	System.out.println("Value at layer " + index + " is " + cell.getValue(index));
	System.out.println("Value at layer " + layerName + " is " + cell.getValue(layerName));
	
	
	System.out.println("Putting new value: " + newValueA + " at layer index: " + index);
	cell.putValue(0, newValueA);
	assertTrue(cell.getValue(index).equals(newValueA));
	System.out.println("Putting new value: " + newValueB + " at layer name: " + layerName);
	cell.putValue(layerName, newValueB);
	assertTrue(cell.getValue(layerName).equals(newValueB));
	
	System.out.println("Updated cell:");
	cell.printDetails();
	
	//cell.saveValues(); 

    }
    

    
    
}