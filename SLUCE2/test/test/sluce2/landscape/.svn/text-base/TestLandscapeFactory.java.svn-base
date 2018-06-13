package test.sluce2.landscape;

import sluce2.ModelContext;
import sluce2.GlobalConstants;
import sluce2.landscape.LandscapeContext;
import sluce2.landscape.RasterRepository;
import sluce2.landscape.component.LandUnit;
import sluce2.landscape.component.LandUnitFactory;
import sluce2.landscape.component.LandUnitType;
import sluce2.landscape.component.LandscapeFactory;
import sluce2.utility.Coord;
import junit.framework.TestCase;

public class TestLandscapeFactory extends TestCase
{
    protected String pathToFullLandscapeDescriptor = ModelContext.model.getResource(GlobalConstants.LANDSCAPE_DESCRIPTOR);     
    protected RasterRepository fullLandscape; 
    
    /**
     * 
     */
    protected void setUp() 
    {
	 fullLandscape = new RasterRepository(pathToFullLandscapeDescriptor);
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
    public void testCreateCellFromId()
    {
	System.out.println("--------------------------------------");
	System.out.println("LandUnitFactory: Create Cell from ID");
	System.out.println("--------------------------------------");
	LandscapeFactory factory = LandUnitFactory.instance();
	LandUnit unit = factory.buildLandUnit(fullLandscape, LandUnitType.CELL, 0);
	if(unit != null)
	{
	    System.out.println(unit);
	}
    }
    
    /**
     * 
     */
    public void testCreateParcelFromId()
    {
	Number id = 3;
	System.out.println("--------------------------------------");
	System.out.println("LandUnitFactory: Create Parcel from ID");
	System.out.println("--------------------------------------");
	LandscapeFactory factory = LandUnitFactory.instance();
	LandUnit unit = factory.buildLandUnit(fullLandscape, LandUnitType.PARCEL, id);
	if(unit != null)
	{
	    System.out.println(unit + " has been built.");
	    unit.printDetails();
	}
    }
    
    /**
     * 
     */
    public void testCreateSubdivisionFromId()
    {
	Number id = 1;
	System.out.println("--------------------------");
	System.out.println("LandUnitFactory: Create Subdivision from Id");
	System.out.println("--------------------------");
	LandscapeFactory factory = LandUnitFactory.instance();
	LandUnit unit = factory.buildLandUnit(fullLandscape, LandUnitType.SUBDIVISION, id);
	if(unit != null)
	{
	    System.out.println(unit + " has been built.");
	    unit.printDetails();
	}
	
	
    }
    

    /* Creating LandUnits by Location */
    
    /**
     * 
     */
    public void testCreateCellFromLoc()
    {
	Coord coord = new Coord(4,0);
	System.out.println("------------------------------------------");
	System.out.println("LandUnitFactory: Create Cell from Loc");
	System.out.println("------------------------------------------");
	LandscapeFactory factory = LandUnitFactory.instance();
	LandUnit unit = factory.buildLandUnit(fullLandscape, LandUnitType.CELL, coord);
	if(unit != null)
	{
	    System.out.println(unit + " has been built.");
	    unit.printDetails();
	}
    }
    
    /**
     * 
     */
    public void testCreateParcelFromLoc()
    {
	Coord coord = new Coord(4,0);
	System.out.println("------------------------------------------");
	System.out.println("LandUnitFactory: Create Parcel from Loc");
	System.out.println("------------------------------------------");
	LandscapeFactory factory = LandUnitFactory.instance();
	LandUnit unit = factory.buildLandUnit(fullLandscape, LandUnitType.PARCEL, coord);
	if(unit != null)
	{
	    System.out.println(unit + " has been built.");
	    unit.printDetails();
	}
    }
    
    /**
     * 
     */
    public void testCreateSubdivisionFromLoc()
    {
	Coord coord = new Coord(0,5);
	System.out.println("---------------------------------------------");
	System.out.println("LandUnitFactory: Create Subdivision from Loc");
	System.out.println("---------------------------------------------");
	LandscapeFactory factory = LandUnitFactory.instance();
	LandUnit unit = factory.buildLandUnit(fullLandscape, LandUnitType.SUBDIVISION, coord);
	if(unit != null)
	{
	    System.out.println(unit + " has been built.");
	    unit.printDetails();
	}
    }
    
    
    
    
}