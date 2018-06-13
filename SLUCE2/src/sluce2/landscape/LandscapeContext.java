package sluce2.landscape;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import repast.simphony.context.space.grid.GridFactory;
import repast.simphony.context.space.grid.GridFactoryFinder;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridBuilderParameters;
import repast.simphony.space.grid.RandomGridAdder;
import repast.simphony.util.collections.Pair;
import sluce2.GlobalConstants;
import sluce2.Model;
import sluce2.ModelComponent;
import sluce2.ModelContext;
import sluce2.consistancy.ChangeManager;
import sluce2.consistancy.Observer;
import sluce2.consistancy.Subject;
import sluce2.ecosystem.EcosystemContext;
import sluce2.landscape.component.Cell;
import sluce2.landscape.component.LandUnitFactory;
import sluce2.landscape.component.LandUnitType;
import sluce2.landscape.component.Parcel;
import sluce2.landscape.component.Subdivision;
import sluce2.management.template.Template;
import sluce2.management.template.TemplateManager;
import sluce2.utility.Coord;
import sluce2.utility.LogHelper;
import sluce2.utility.file.FileHandler;
import umn.geog.helia.landscape.LandscapeLayer;

/**
 * The LandscapeContext encapsulates the current landscape of the model.
 * <p>
 * The LandscapeContext maintains an internal RasterRepository representing the current landscape. 
 * The landscape is constructed from a landscape descriptor xml file. See: umn.geog.helia. 
 * Methods are included in the LandscapeContext to access and manipulate the internal landscape, 
 * ensuring encapsulation of the internal RasterRepository within the LandscapeContext.
 * <p>
 * The LandscapeContext also creates the visual grid that displays when the model runs in GUI mode. 
 * <p>
 * There is only one instance of the LandscapeContext at all times. 
 * 
 * @author Meghan Hutchins
 * @date 09/16/2010
 */
public class LandscapeContext extends ModelComponent implements Subject
{
    /** Log4j Logger used for debugging and code tracking **/
    public final static Logger logger;
        
    /** The single instance of the LandscapeContext in the model **/
    private static LandscapeContext instance; 
    
    /** 
     * The current landscape of the model.<p>
     * The LandscapeContext encapsulates the current landscape, 
     * providing functions for specific landscape access and manipulation.   
     **/
    private RasterRepository landscape; 
     
    /** 
     * Maps a LandUnit ID to a pair of Numbers representing old and new LandcoverTypes respectively.<p>
     * A temporary List holding the most recent changes to the landscape.
     * Used to inform the EcosystemComponent of any changes in landcover.
     */
    private Map<Integer, Pair<LandcoverType, LandcoverType>> changeMap; 
    
    
    /** A RepastS Grid Projection made up of Cells **/
    @SuppressWarnings("unused")
    private static Grid<Object> cellGrid;
    
  
    static
    {
    	logger = LogHelper.getConsoleLogger(LandscapeContext.class.getName());
    	logger.setLevel(GlobalConstants.LANDSCAPECONTEXT_LOGLEVEL);
    }
    
    /**
     * Creates and initializes a new LandscapeContext using the given Landscape Descriptor <p>
     * The landscape descriptor is an XML file that Helia uses to load 
     * raster files into the Java program.
     * @see hegis.umn.edu
     *
     * @param full path to the landscape descriptor XML
     * @return a fully loaded LandscapeContext 
     */
    private LandscapeContext(String pathToLandscapeDescriptor)
    {
    	super(GlobalConstants.LANDSCAPECONTEXT); // must match name in model.score
    	this.landscape = new RasterRepository(pathToLandscapeDescriptor);
    	this.changeMap = new HashMap<Integer, Pair<LandcoverType, LandcoverType>>();
    	
		// Build Grid Projection : Displayed in GUI Run
		this.buildCellGrid();
    }
    
    /**
     * Creates and initializes a new LandscapeContext using the given Landscape Descriptor <p>
     * The landscape descriptor is an XML file that Helia uses to load 
     * raster files into the Java program.
     * @see hegis.umn.edu
     *
     * @param full path to the landscape descriptor XML
     * @return a fully loaded LandscapeContext 
     */
    private LandscapeContext(String pathToLandscapeDescriptor, String layerGroupName)
    {
		super(GlobalConstants.LANDSCAPECONTEXT); // must match name in model.score
		this.landscape = new RasterRepository(pathToLandscapeDescriptor, layerGroupName);
		this.changeMap = new HashMap<Integer, Pair<LandcoverType, LandcoverType>>();
		
		// Build Grid Projection : Displayed in GUI Run
		this.buildCellGrid();
		
		LandscapeContext.logger.debug("LandscapeContext created using landscape descriptor: " + pathToLandscapeDescriptor + " with layer group: " + layerGroupName);
    }

    /**
     * 
     */
    public static LandscapeContext createInstance(String pathToLandscapeDescriptor)
    {
		if(instance == null)
		{
		    instance = new LandscapeContext(pathToLandscapeDescriptor);
		}
		return instance;
    }
    
    /**
     * 
     */
    public static LandscapeContext createInstance(String pathToLandscapeDescriptor, String layerGroupName)
    {
		if(instance == null)
		{
		    instance = new LandscapeContext(pathToLandscapeDescriptor, layerGroupName);
		}
		return instance;
    }
    
    /**
     * 
     * @return true if the instance has been instantiated, false otherwise.
     */
    public static boolean hasInstance()
    {
    	return (instance != null);
    }
    
    /**
	 * Adds the given Observer to watch this Subject for changes.
	 */
	@Override
	public void attach(Observer observer)
	{
		ChangeManager.instance().register(this, observer);
	}
	
	/**
	 * Removes the given Observer from this Parcel.
	 */
	@Override
	public void detach(Observer observer)
	{
		ChangeManager.instance().unregister(this, observer);
	}
	
	/**
	 * Notify the attached Observers to update themselves.
	 */
	@Override
	public void notifyObservers()
	{
		ChangeManager.instance().notifyObservers(this);
	}
	
    /**
     * Context step method.
     * 
     * @return void
     */
    public void step() 
    {
    	long start = System.currentTimeMillis();
		LandscapeContext.logger.debug("--- LandscapeComponent takes a step : Saves current landscape and archives raster layers. ---");
		
		assert(this.saveLandscape());
		this.archiveAllLayers();
       
		LandscapeContext.logger.debug("LandscapeComponent ran for " + (System.currentTimeMillis() - start) + " milliseconds.");
    }
    
    /**
     * @return the width of the Landscape
     */
    public int getLandscapeWidth()
    {
    	return landscape.getWidth();
    }
    
    /**
     * @return the height of the Landscape
     */
    public int getLandscapeHeight()
    {
    	return landscape.getHeight();
    }
    
    @Override
    public String toString()
    {
    	return this.getClass().getName();
    }
    
//-------------------------
//  GUI Display       
    
	/**
	 * Builds the RepastS grid projection representing Sluce Landscape Cells.
	 */
	private void buildCellGrid()
	{
		GridBuilderParameters<Object> gridParams = 
			GridBuilderParameters.singleOccupancy2D(new RandomGridAdder<Object>(), 
				new repast.simphony.space.grid.StrictBorders(), 
				landscape.getWidth(), landscape.getHeight());
						
		GridFactory factory = GridFactoryFinder.createGridFactory(new HashMap<String,Object>());
		cellGrid = factory.createGrid("CellGrid", this, gridParams);
				
	}
    
//-------------------------------
// Landscape Component Accessors     

    /**
     * @return the landscape Cell at the given location 
     */
    public Cell getCell(Coord loc)
    {
	return new Cell(landscape, loc.getX(), loc.getY());
    }
    
    /**
     * @return a List of all Cells in the Landscape.
     */
    public List<Cell> getCellList()
    {
    	List<Cell> cellList = new ArrayList<Cell>();
    	
    	for(int i=0; i<landscape.getWidth(); i++)
    	{
    		for(int j=0; j<landscape.getHeight(); j++)
    		{
    			cellList.add(this.getCell(new Coord(i,j)));
    		}
    	}
    	
    	return cellList;
    }
    
    /**
     * @return the Parcel having the given id.
     */
    public Parcel getParcel(int id)
    {
    	return (Parcel)LandUnitFactory.instance().buildLandUnit(this.landscape, LandUnitType.PARCEL, id);
    }
    
    /**
     * @return the Subdivision having the given id.
     */
    public Subdivision getSubdivision(int id)
    {
		/** TODO: **/
		return new Subdivision();
    }
    
    /**
     * @return a set of unique farm Parcel IDs 
     */
    public Set<Number> getUniqueFarmParcelIDs()
    {
    	return LandUnitFactory.instance().getUniqueParcelIDs(landscape, LandscapeLayerType.LANDUSE, LanduseType.AGRICULTURE.getCode());
    }
    
//-------------------------
// Land-Change
    
    /**
     * Todo: Add orientation to this method
     * Apply the given template to the current landscape an notify any Observers.
     * @return true if the given template was applied, false otherwise. 
     */
    public void applyTemplate(Template template, Coord nwCorner)
    {
    	changeMap = TemplateManager.instance().applyTemplate(template, this.landscape, new Coord(0,0));
    	
    	// If we got here, without an assertion error, it means the template was applied successfully.
    	// Notify my observers (i.e. EcosystemComponent) of the recent change.
    	ChangeManager.instance().notifyObservers(this);
    }
    
//-------------------------
// Nutrient Change
    
    public void removeLitterFromTurfGrass()
    {
    	
    }
    
//-------------------------
// Raster Accessors   
    
    /**
     * @return the value in the given layer at the given location.
     */
    public int getNbrOfLayers()
    {
	return landscape.getNbrOfLayers();
    }
    
    /**
     * 
     * @param type
     * @return the requested landscape layer
     */
    public LandscapeLayer getLayer(LandscapeLayerType type)
    {
    	return landscape.getLayer(type);
    }
    
    /**
     * @return the value in the given layer at the given location.
     */
    public Number getValue(String layerName, Coord loc)
    {
    	return landscape.getValue(layerName, loc);
    }
    
    public Number getValue(LandscapeLayerType type, Coord loc)
    {
    	return this.getValue(type.toString(), loc);
    }
    
    /**
     * This method does not write the new value to the raster layer, it only saves the value in memory.
     * To write the new value permanently to the raster layer, execute the updateLandscape method in this class. 
     * @return true if the new value was put in the layer and the specified location, false otherwise
     */
    public boolean putValue(String layerName, Coord loc, Number newValue)
    {
    	return landscape.putValue(layerName, loc, newValue);
    }
    
    /**
     * 
     * @param type
     * @param loc
     * @param newValue
     * @return true if the new value was put in the layer and the specified location, false otherwise
     */
    public boolean putValue(LandscapeLayerType type, Coord loc, Number newValue)
    {
    	return this.putValue(type.getName(), loc, newValue);
    }
    
//-------------------------
// Saving Raster Data      
    
    /**
     * This method writes over the current landscape value.
     * @return true if landscape has been updated, false otherwise
     */
    public boolean saveLandscape()
    {
    	LandscapeContext.logger.debug("Saving current landscape");
    	return landscape.saveAllLayers();
    }
    
    /**
     * This method does not write over the current landscape value, but saves the layer to a new file in the folder 
     * indicated by the LANDSCAPE_ARCHIVE_DIRECTORY in the {@link GlobalConstants} class.
     * @param layername
     * @return true if layer has been archived, false otherwise
     */
    public boolean archiveLayer(String layername)
    {
    	return landscape.saveLayerTo(layername, (GlobalConstants.LANDSCAPE_ARCHIVE_LOCATION), GlobalConstants.APPEND_TIMESTAMP_TO_FILENAME, GlobalConstants.APPEND_TICK_TO_FILENAME);
    }
    
    /**
     * Archives all the layers in the current landscape
     * @see GlobalConstants.LANDSCAPE_ARRCHIVE_LOCATION
     */
    public void archiveAllLayers()
    {
    	/** TODO : This could be more elegant **/
    	String archiveDirectory = (Model.getInstance().getResource(GlobalConstants.LANDSCAPE_ARCHIVE_LOCATION).trim())+"year-"+ModelContext.getCurrentYear()+"/";
		LandscapeContext.logger.debug("Archiving all landscape layers to: " + archiveDirectory); 
		FileHandler.createDirectory(archiveDirectory);
    	
    	for(int layerIndex=0; layerIndex<this.landscape.getNbrOfLayers(); layerIndex++)
    	{
    		boolean success = landscape.saveLayerTo(layerIndex, archiveDirectory, GlobalConstants.APPEND_TIMESTAMP_TO_FILENAME, GlobalConstants.APPEND_TICK_TO_FILENAME);
    		assert success==true : "Unable to archive landscape layer index: " + layerIndex + " (" + landscape.getLayer(layerIndex).getLayerName() + ")";
    	}	
    }
    
    /**
     * @return a List of all RasterLayers in the current landscape.
     */
    public List<LandscapeLayer> getAllLayers()
    {
    	List<LandscapeLayer> layerList = new ArrayList<LandscapeLayer>();
    	for(int i=0; i<this.landscape.getNbrOfLayers(); i++)
    	{
    		layerList.add(landscape.getLayer(i));
    	}
    	return layerList;	
    }
    
    public static LandscapeContext getInstance()
    {
    	return LandscapeContext.instance;
    }

	public Map<Integer, Pair<LandcoverType, LandcoverType>> getChangeMap()
	{
		return changeMap;
	}

	public void setChangeMap(
			Map<Integer, Pair<LandcoverType, LandcoverType>> changeMap)
	{
		this.changeMap = changeMap;
	}

	@Override
	public void initialize()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy()
	{
		instance = null;
	}
    
}