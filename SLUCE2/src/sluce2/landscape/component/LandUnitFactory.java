package sluce2.landscape.component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import sluce2.agent.AgentContext;
import sluce2.landscape.LandscapeContext;
import sluce2.landscape.LandscapeLayerType;
import sluce2.landscape.RasterRepository;
import sluce2.management.template.TemplateManager;
import sluce2.utility.Coord;
import umn.geog.helia.landscape.LandscapeLayer;

public class LandUnitFactory implements LandscapeFactory
{
    /** The single instance of the LandUnitFactory in the model **/
    private static LandUnitFactory instance; 
    
    /**
     * 
     */
    private LandUnitFactory()
    {
	
    }
    
    /**
     * Returns the one-and-only instance of the LandscapeFactory.
     */
    public static LandUnitFactory instance()
    {
		if(instance == null)
		{
		    instance = new LandUnitFactory();
		}
		return instance;
    }
    
    /**
     * Builds the requested LandUnit according to specifications provided.
     * 
     * @param RasterRepository containing the requested LandUnit
     * @param Type of LandUnit requested
     * @param Specific ID of the LandUnit
     * @return the specified LandUnit
     */
    public LandUnit buildLandUnit(RasterRepository repository, LandUnitType type, Number id)
    {
		LandUnit landUnit = null;
		
		switch(type)
		{
		    case PARCEL:
			
				List<Cell> parcelCellList = this.findCells(repository, LandscapeLayerType.PARCEL, id);	
				if(!parcelCellList.isEmpty())
				{
				    landUnit = new Parcel(id.intValue(), parcelCellList);
				}
				else
				{
				    LandscapeContext.logger.warn("Parcel ID " + id + " not found. Unable to build Parcel.");
				}
				break;
		    
		    case SUBDIVISION:
		
				Set<Number> parcelIdSet = this.getUniqueValues(repository, LandscapeLayerType.SUBDIVISION, (Number)id, LandscapeLayerType.PARCEL);
		
				if(!parcelIdSet.isEmpty())
				{
				    landUnit = new Subdivision(id.intValue(), parcelIdSet);
				}
				else
				{
				    LandscapeContext.logger.warn("Subdivision ID " + id + " not found. Unable to build Subdivision.");
				}
				break;
			
		    default:
			
				LandscapeContext.logger.warn("LandUnitFactory could not create LandUnit of type " + type + " having id " + id);
				break;
		}
		
		return landUnit;
    }

    /**
     * Builds the requested LandUnit according to specifications provided.
     * 
     * @param RasterRepository containing the requested LandUnit
     * @param Type of LandUnit requested
     * @param Coordinates contained anywhere within the requested LandUnit
     * @return the specified LandUnit
     */
    public LandUnit buildLandUnit(RasterRepository repository, LandUnitType type, Coord loc)
    {
		LandUnit landUnit = null;
		
		switch(type)
		{
		    case CELL:
			
		    	landUnit = new Cell(repository, loc.getX(), loc.getY());
		    	break;
		    
		    case PARCEL:
			
				Cell parcelCell = (Cell)this.buildLandUnit(repository, LandUnitType.CELL, loc);
				landUnit = buildLandUnit(repository, LandUnitType.PARCEL, parcelCell.getValue(LandscapeLayerType.PARCEL));
				break;	
			
		    case SUBDIVISION:
			
				Cell subdivisionCell = (Cell)this.buildLandUnit(repository, LandUnitType.CELL, loc);
				landUnit = buildLandUnit(repository, LandUnitType.SUBDIVISION, subdivisionCell.getValue(LandscapeLayerType.SUBDIVISION));	
				break;
				
		    default:
			
		    	LandscapeContext.logger.warn("LandUnitFactory could not create LandUnit using type=" + type + " and coord=" + loc + " : Returning null");
			
			break;
		}
		
		return landUnit;
	
    }
    
    /**
     * Return a set of unique Parcel IDs where a particular LandscapeLayer matches a particular value.
     * 
     * @param type
     * @param value
     */
    public Set<Number> getUniqueParcelIDs(RasterRepository repository, LandscapeLayerType type, Number value)
    {
    	return this.getUniqueValues(repository, type, value, LandscapeLayerType.PARCEL);
    }
    
//---------------------------
// Helper Methods
    
    /**
     * Finds the Cells matching the given value, 
     * within the given LandscapeLayerType, 
     * within the given RasterRepository. 
     * <p>
     * TODO: Not sure how well this method will perform with large raster datasets. 
     * 
     * @return List of Cells matching the given criteria. 
     */
    public List<Cell> findCells(RasterRepository repository, LandscapeLayerType type, Number value)
    {
		List<Cell> cellList = new ArrayList<Cell>();
		LandscapeLayer layer = repository.getLayer(type);
		
		//System.out.println("Finding cells of value " + value + " in " + layer.getStoragePath());
		
		/*
		 * Loop through the cells for the given LandscapeLayerType.
		 * Search for the given value and return a list of matching Cells.
		 */
		
		int i=0;
		int j=0;
		do
		{
		    do
		    {
				if(value.equals(layer.getValueAt(i, j)))
				{
				    cellList.add( new Cell(repository, i, j) );
				}
				//System.out.println("(" + i + "," + j + ") = " + layer.getValueAt(i, j));
				j++;
		    }
		    while(j < repository.getHeight());
		    
		    j=0;
		    i++;
		}
		while(i < repository.getWidth());
		
		/* DEBUG *
		for(Cell cell : cellList)
		{
		    System.out.println(cell);
		}
		/*/
		
		return cellList; 
    }
    
    /**
     * Finds the unique layer values within a repository
     * where another layer has values of a particular known value.  
     * <p>
     * For example: Use this method if you want to find all the unique parcel IDs belonging to only farms. 
     * getUniqueValues(landscape, LandscapeLayerType.LANDUSE, LanduseType.AGRICULTURE.getCode(), LandscapeLayerType.PARCEL)
     * <p>
     * TODO: Not sure how well this method will perform with large raster datasets. 
     * 
     * @return Set of unique values of the requested layer 
     */
    private Set<Number> getUniqueValues(RasterRepository repository, LandscapeLayerType knownLayer, Number knownValue, LandscapeLayerType requestedLayer)
    {
		Set<Number> requestedIDList = new HashSet<Number>();
		LandscapeLayer known = repository.getLayer(knownLayer);
		LandscapeLayer requested = repository.getLayer(requestedLayer);
		
		/*
		 * Loop through the cells for the given LandscapeLayerType.
		 * Search for the given value and return a list of matching Cells.
		 * ** TODO: Maybe use threads for this? 
		 */
		
		int i=0;
		int j=0;
		do
		{
		    do
		    {
			if(knownValue.equals(known.getValueAt(i, j)))
			{
			    requestedIDList.add(requested.getValueAt(i, j));
			}
			j++;
		    }
		    while(j < repository.getHeight());
		    j=0;
		    i++;
		}
		while(i < repository.getWidth());
	
		return requestedIDList;
    }
    
    
    /**
     * Finds the unique Parcels belonging to the Cells in the given cell list, 
     * within the given RasterRepository. 
     * <p>
     * TODO: Not sure how well this method will perform with large raster datasets. 
     * 
     * @return List of unique parcels that contain the Cells in the given cell list.
     */
    private List<Parcel> findParcels(RasterRepository repository, List<Cell> cellList)
    {
		List<Parcel> parcelList = new ArrayList<Parcel>();
		
		// Collect the parcel ids
		Set<Number> parcelIdSet = new HashSet<Number>();
		for(Cell cell : cellList)
		{
		    parcelIdSet.add( cell.getValue(LandscapeLayerType.PARCEL) );
		}
		
		for(Number parcelID : parcelIdSet)
		{
		    parcelList.add( (Parcel)buildLandUnit(repository, LandUnitType.PARCEL, parcelID) );
		}
		
		return parcelList;
    }
    
}