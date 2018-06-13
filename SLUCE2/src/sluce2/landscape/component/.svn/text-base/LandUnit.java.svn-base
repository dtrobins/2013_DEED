package sluce2.landscape.component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import sluce2.exception.CodeNotFoundException;
import sluce2.landscape.LandscapeContext;
import sluce2.landscape.LandscapeLayerType;
import sluce2.landscape.LanduseType;


/**
 * A LandUnit is a generic object used to track the proportion of land-cover types 
 * (e.g. tree cover, impervious, grass) within the boundary of the particular land-unit object.
 * <p>
 * LandUnits act as a container for geographic and biophysical location-based state variables 
 * as well as the natural processes that it may undergo. Each LandUnit is an independent 
 * automation in the model.
 * 
 * @author Meghan Hutchins
 * @date 09/16/2010
 */
public abstract class LandUnit
{
    /** ID of this LandUnit **/
    protected int id; 
    
    /** The id of next brand new LandUnit **/
    private static int nextId; 
    
    /** Set of Cells that make up this LandUnit **/
    protected Set<Cell> cellSet;
    
    static
    {
    	/** TODO: count up the total number of current parcels (highest parcel id), add 1, then assign this number to nextId **/
    	nextId = 0;
    }
    
    /**
     * 
     */
    public LandUnit()
    {
    	id = nextId++;
    	cellSet = new HashSet<Cell>();
    }
    
    /**
     * 
     */
    public LandUnit(int id)
    {
    	this.id = id;
    	cellSet = new HashSet<Cell>();
    }
    
    /**
     * @return the LanduseType for this LandUnit.
     */
    public LanduseType getLanduseType()
    {
    	LanduseType type = null;
    	
    	Integer landuseTypeCode = null;
    	try
    	{
	    	// Ensure that each cell in the cellSet is of the same LanduseType.
	    	for(Cell cell : this.cellSet)
	    	{
	    		landuseTypeCode = (Integer)cell.getValue(LandscapeLayerType.LANDUSE);
	    		type = (null==type || type.equals(LanduseType.getLanduseType(landuseTypeCode) )? LanduseType.getLanduseType(landuseTypeCode) : LanduseType.NA);
	    	}
	    	assert(null==type || type.equals(LanduseType.NA)) : "Landuse Type may be null or mixed for LandUnit: " + this;
    	}
    	catch(CodeNotFoundException cnfe)
    	{
    		LandscapeContext.logger.error("Could not find LanduseType code: " + landuseTypeCode );
    	}
    	return type;
    }

    /**
     * @return return true if this LandUnit is equal to the given LandUnit
     */
    public boolean equals(Object that)
    {
		boolean isEqual = false;
		
		/** TODO: **/
		
		return isEqual;
    }
    
    /**
     * @return String representation of a Parcel
     */
    public String toString()
    {
    	return (this.getClass().getSimpleName() + "[" + id + "]");
    }
    
    /**
     * Prints the details of the LandUnit
     */
    public void printDetails()
    {
    	System.out.println(this);
    }

//-----------------------
// Accessors
    
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}   

    public Set<Cell> getCellSet()
    {
        return cellSet;
    }
    
    public List<Integer> getCellSetIDList()
    {
    	List<Integer> idList = new ArrayList<Integer>();
    	for(Cell cell : cellSet)
    	{
    		idList.add(cell.getId());
    	}
    	return idList;
    }

    public void setCellSet(Set<Cell> cellSet)
    {
        this.cellSet = cellSet;
    }
   
}