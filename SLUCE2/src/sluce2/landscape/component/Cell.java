package sluce2.landscape.component;

import javax.naming.NameNotFoundException;

import sluce2.agent.AgentContext;
import sluce2.exception.CodeNotFoundException;
import sluce2.landscape.LandcoverType;
import sluce2.landscape.LandscapeContext;
import sluce2.landscape.LandscapeLayerType;
import sluce2.landscape.LanduseType;
import sluce2.landscape.RasterRepository;
import sluce2.utility.Coord;
import umn.geog.helia.landscape.CompoundCell;
import umn.geog.helia.landscape.RasterLayer;

/**
 * A Cell a wrapper class for umn.geog.helia.landscape.CompoundCell. 
 * <p>
 * Cells are created and destroyed in the model on an as-needed basis.
 * Cells are created from a given {@link RasterRepository} and x, y location. 
 * A Cell is composed of as many layers as the given RasterRepository. 
 * Methods provided include getting a value from a particular layer and  
 * putting new value into particular layer, and saving Cell contents to 
 * the underlying RasterRepository. 
 * <p>
 * NB:Putting a new value into a particular Cell layer will NOT automatically 
 * update the underlying raster data. The "save" method must be called before 
 * new data is permanently written to the actual raster file. 
 * 
 * @author Meghan Hutchins
 * @date 09/16/2010
 */
public class Cell extends LandUnit
{
    /** x-coordinate of cell in raster group **/
    private int xLoc; 
    
    /** y-coordinate of cell in raster group **/
    private int yLoc;
    
    /** Underlying object encapsulated in the Cell.<p> See: umn.geog.helia.landscape.CompoundCell **/
    private CompoundCell compoundCell; 
    
    /** RasterRepository form which this Cell was created **/
    private RasterRepository rasterRepository;
    
    
//-------------------------
// Construction    
    
    /**
     * 
     */
    public Cell()
    {
	rasterRepository = null;
	xLoc = 0; 
	yLoc = 0; 
	compoundCell = null;
    }
    
    /**
     * Constructs a cell based on the location of a cell in the given RasterRepository.
     */
    public Cell(RasterRepository rasterRepository, int xLoc, int yLoc)
    {
		this.rasterRepository = rasterRepository;
		this.xLoc = xLoc;
		this.yLoc = yLoc;
		
		int nbrOfLayers = rasterRepository.getNbrOfLayers();
		this.compoundCell = new CompoundCell((short)nbrOfLayers);
		
		for(int i = 0; i < nbrOfLayers; i++)
		{
		    RasterLayer<?> rasterLayer = (RasterLayer<?>)rasterRepository.getLayer(i);
		    if(rasterLayer != null)
		    {
		    	compoundCell.put(i, rasterLayer.getValueAt(xLoc, yLoc));
		    }
		}
    }

//-------------------------
// Get Values   
    
    @Override
    public int getId()
	{
		return ((this.getyLoc()*LandscapeContext.getInstance().getLandscapeWidth())+this.getxLoc());
	}
    
    /**
     * @return the id of the Cell having the given coordinate.
     */
    public static int getId(Coord coord)
	{
		return ((coord.getY()*LandscapeContext.getInstance().getLandscapeWidth())+coord.getX());
	}
    
    /**
     * @return number of layers in this cell
     */
    public int getNbrOfLayers()
    {
	return compoundCell.size();
    }
    
    /**
     * @param Landscape Layer Type
     * @return the value of the raster at the given layer index
     */
    public Number getValue(String layerName)
    {
	Number value = null;
	
	try
	{
	    value = this.getValue(rasterRepository.getLayerIndex(layerName));
	}
	catch(NameNotFoundException nnfe)
	{
	    LandscapeContext.logger.error("Landscape layer name: " + layerName + " could not be found.");
	}
	
	return value;
    }
    
    /**
     * @param Landscape Layer Type
     * @return the value of the raster at the given layer index
     */
    public Number getValue(LandscapeLayerType type)
    {
	return this.getValue(type.getName());
    }
    
    
    /**
     * @param layer index
     * @return the value of the raster at the given layer index
     */
    public Number getValue(int layerIndex)
    {
	return compoundCell.get(layerIndex);
    }
      
//-------------------------
// Put Values    
    
    /**
     * 
     * @param Landscape Layer Type
     * @param new value 
     * @return the value of the raster at the given layer index
     */
    public void putValue(LandscapeLayerType type, Number newValue)
    {
		try
		{
		    this.putValue(rasterRepository.getLayerIndex(type.getName()), newValue);
		}
		catch(NameNotFoundException nnfe)
		{
		    LandscapeContext.logger.error("Landscape Layer Type: " + type + " could not be found.");
		}
    }
    
    /**
     * @param layer index
     * @param new value 
     * @return the value of the raster at the given layer index
     */
    public void putValue(int layerIndex, Number newValue)
    {
		/*
		 * NB: CompoundCells are decoupled from the landscapeLayers for more efficient manipulation.
		 *     The result is if you want to save this value to a new raster file, 
		 *     the value must be saved to the layer itself, not just the CompoundCell. 
		 *     
		 *     (?) Will we need to create another method that saves a value to a CompoundCell?
		 *         ex: putTempValue(int layerIndex, Number newValue)
		 */
		compoundCell.put(layerIndex, newValue);
    }
    
    /**
     * 
     * @param Landscape Layer Type
     * @param new value 
     * @return the value of the raster at the given layer index
     */
    public void putValue(String layerName, Number newValue)
    {
		try
		{
		    this.putValue(rasterRepository.getLayerIndex(layerName), newValue);
		}
		catch(NameNotFoundException nnfe)
		{
		    LandscapeContext.logger.error("Landscape Layer Name: " + layerName + " could not be found.");
		}
    }
    
//-------------------------
// Save Values       
    
    /**
     * @return return true if Cell values were saved to raster data, false otherwise
     */
    public boolean saveValues()
    {
    	boolean success = false;
	
		for(int i=0; i<this.getNbrOfLayers(); i++)
		{
		    rasterRepository.putValue(i, new Coord(this.getxLoc(), this.getyLoc()), this.getValue(i));
		}
		rasterRepository.saveAllLayers();
		
		return success;
    }
       
    /**
     * @return true if this Cell is in the same location as that Cell.
     */
    @Override
    public boolean equals(Object that)
    {
    	boolean isEqual = false;
    	if(that instanceof Cell)
    	{
    		isEqual = ((Cell) that).getCoord().equals(this.getCoord());
    	}
    	return isEqual;
    }
//-------------------------
// Output
    
    /**
     * @return String representation of a Cell
     */
    @Override
    public String toString()
    {
	return("Cell(" + xLoc + "," + yLoc + ")");
    }
    
    /**
     * Prints the contents of the Cell layers to the console.
     */
    @Override
    public void printDetails()
    {
	System.out.println("Cell("+xLoc+","+yLoc+"):");
	for(int i=0; i<this.getNbrOfLayers(); i++)
	{
	    System.out.println("  " + rasterRepository.getLayer(i).getLayerName() + "(" + i + ") = " + this.getValue(i));
	}
    }

//-------------------------
// Accessors    
    
    public Coord getCoord()
    {
    	return new Coord(this.xLoc, this.yLoc);
    }
    
    public int getxLoc()
    {
        return xLoc;
    }

    public void setxLoc(int xLoc)
    {
        this.xLoc = xLoc;
    }

    public int getyLoc()
    {
        return yLoc;
    }

    public void setyLoc(int yLoc)
    {
        this.yLoc = yLoc;
    }
 
}