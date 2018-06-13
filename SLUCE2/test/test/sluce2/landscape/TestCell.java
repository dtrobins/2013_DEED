package test.sluce2.landscape;

import sluce2.Model;
import sluce2.GlobalConstants;
import sluce2.build.ModelBuilder;
import sluce2.build.SampleModelBuilder;
import sluce2.landscape.LandscapeContext;
import sluce2.landscape.LandscapeLayerType;
import sluce2.landscape.component.Cell;
import sluce2.management.template.Template;
import sluce2.management.template.TemplateManager;
import sluce2.utility.Coord;
import junit.framework.TestCase;

/**
 * Tests the functionality of the Cell class.
 * 
 * @author Meghan Hutchins
 * @date 11/01/2010
 */
public class TestCell extends TestCase
{    
	
	protected ModelBuilder builder;
	protected Model model;
	
    /*
     * We'll use a Template RasterRepsitory to test Cell functionality 
     */
    protected String pathToLandscapeDescr = "/home/meghan/Applications/RepastS-Eclipse/eclipse/workspace/SLUCE2/src/sluce2/resources/landscape/sandbox/Sandbox-RasterDesc.xml";
    protected String[] landscapeGroups = {"Sluce2Landscape"};
    protected String landscapeLayerName = "Landcover";
    protected Template template; 
    
    /**
     * 
     */
    protected void setUp() 
    {    	
    	builder = new SampleModelBuilder(GlobalConstants.SLUCE2_RESOURCES);
    	model = builder.getModel();
    	LandscapeContext.createInstance(pathToLandscapeDescr);
    	//pathToTemplatesDescriptor = model.getResource(GlobalConstants.DEVELOPMENT_RASTER_TEMPLATE_DESCRIPTOR); 
		//TemplateManager.instance();
		//TemplateManager.loadTemplates(pathToLandscapeDescr, templateNames);
		//template = TemplateManager.instance().getTemplate(templateName);
    }
    
    /**
     * 
     */
    protected void tearDown() 
    {
    	//LandscapeContext.getInstance().destroy();
    }
    
    /**
     * 
     */
    public void testGetCell()
    {
		System.out.println("--------------------------");
		System.out.println("GET CELL AND LAYER INFO");
		System.out.println("--------------------------");
		Cell cell = LandscapeContext.getInstance().getCell(new Coord(0,0));
		//Cell cell = new Cell(template, 0, 0);
		cell.printDetails();
    }
    
    /**
     * 
     */
    public void testCellEquals()
    {
		System.out.println("--------------------------");
		System.out.println("This Cell Equals That Cell");
		System.out.println("--------------------------");
		Cell cellA = LandscapeContext.getInstance().getCell(new Coord(0,0));
		Cell cellB = LandscapeContext.getInstance().getCell(new Coord(0,0));
		Cell cellC = LandscapeContext.getInstance().getCell(new Coord(1,0));
		
		System.out.println("A: " + cellA);
		System.out.println("B: " + cellB);
		System.out.println("C: " + cellC);
		
		assertTrue(cellA.equals(cellB));
		assertFalse(cellA.equals(cellC));
    }
    
    /**
     * 
     */
    public void testPutValueAtCellLayerIndex()
    {
		System.out.println("--------------------------");
		System.out.println("SET CELL LAYER BY INDEX");
		System.out.println("--------------------------");
		
		int valueA = 37; 
		
		//Cell cell = new Cell(template, 0,0);
		Cell cell = LandscapeContext.getInstance().getCell(new Coord(0,0));
		System.out.println("Before:");
		cell.printDetails();
		
		System.out.println("Changing index 0 to " + valueA);
		cell.putValue(0, valueA);
		System.out.println("After:");
		cell.printDetails();
			
    }
    
    /**
     * 
     */
    public void testPutValueAtCellLayerName()
    {
		System.out.println("--------------------------");
		System.out.println("SET CELL LAYER BY NAME");
		System.out.println("--------------------------");
		
		//Cell cell = new Cell(template, 0,0);
		Cell cell = LandscapeContext.getInstance().getCell(new Coord(0,0));
		int valueA = 55; 
	
		System.out.println("Before:");
		cell.printDetails();
		
		System.out.println("Changing \"Landcover\" to " + valueA);
		cell.putValue("Landcover", valueA);
		System.out.println("After:");
		cell.printDetails();
    }
  
    /**
     * 
     */
    public void testGetCellLandcover()
    {
		System.out.println("--------------------------");
		System.out.println("GET LANDCOVER");
		System.out.println("--------------------------");
		
		//Cell cell = new Cell(template, 0,1);
		Cell cell = LandscapeContext.getInstance().getCell(new Coord(0,1));
		System.out.println(cell.getValue(LandscapeLayerType.LANDCOVER.getName()));
    }
    
    /**
     * 
     */
    public void testSaveCellValues()
    {
		System.out.println("--------------------------");
		System.out.println("SAVE CELL VALUES");
		System.out.println("--------------------------");
	    
		//Cell cell = new Cell(template, 0,0);
		Cell cell = LandscapeContext.getInstance().getCell(new Coord(0,0));
		Number valueA = 0.076; 
		//String layerName = "Litter";
		//LandscapeLayerType layerType = LandscapeLayerType.PRCNTSILT;
		
		System.out.println("Before:");
		cell.printDetails();
		
		//System.out.println("Changing \"" + layerType + "\" to " + valueA);
		//cell.putValue(layerType, valueA);
		System.out.println("After:");
		cell.printDetails();
		
		cell.saveValues(); 
		System.out.println("* Check raster files for new saved values.");
		
		//Cell cell2 = new Cell(template, 0,0);
		Cell cell2 = LandscapeContext.getInstance().getCell(new Coord(0,0));
		//System.out.println(cell2.getValue(layerType) + " == " + valueA);
		//assertTrue( (cell2.getValue(layerType).toString()).equals((valueA).toString()) );
    }
    
	
    
    
}