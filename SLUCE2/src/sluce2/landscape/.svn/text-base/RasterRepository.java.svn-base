package sluce2.landscape;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

import javax.naming.NameNotFoundException;

import sluce2.GlobalConstants;
import sluce2.exception.RasterLoadException;
import sluce2.utility.Coord;
import sluce2.utility.StringUtils;
import umn.geog.helia.gis.HeliaDataLoader;
import umn.geog.helia.landscape.LandscapeLayer;
import umn.geog.helia.landscape.LandscapeLayerGroup;
import umn.geog.helia.landscape.LandscapeLoader;
import umn.geog.helia.landscape.RasterLayer;

/**
 * A repository for raster data.
 * <p> 
 * This class encapsulates a raster layer group, made up of 0 or more sub-groups. 
 * The HELIA GIS java library is used to load, access and manipulate raster data layers. @see hegis.umn.edu 
 * A rasters are loaded through a raster descriptor xml file. An optional layer group name may be specified.
 * 
 * @author Meghan Hutchins
 * @date 10/10/2010
 */
public class RasterRepository
{
    /** Path to helia landscape descriptor xml file **/
    private String pathToDescFile;
    
    /** The layer group within the descriptor xml file that the Raster Repository is created from **/
    private String layerGroupName;
    
    /** The root landscape layer group object. Shared among all agents. */
    private LandscapeLayerGroup layerGroup;
    
    /** A class used to load raster data */
    private HeliaDataLoader heliaDataLoader;
     
    
    /**
     * 
     */
    public RasterRepository()
    {
	this.pathToDescFile = null;
	this.layerGroupName = null;
	this.layerGroup = new LandscapeLayerGroup();
	this.heliaDataLoader = new HeliaDataLoader();
    }
    
    /**
     * 
     */
    public RasterRepository(String pathToDescFile)
    {
	this.pathToDescFile = pathToDescFile;
	this.layerGroupName = null;
	this.layerGroup = new LandscapeLayerGroup();
	this.heliaDataLoader = new HeliaDataLoader();
	
	try
	{
	    this.loadLayers(pathToDescFile);
	}
	catch(RasterLoadException rle)
	{
	    LandscapeContext.logger.error("Unable to load raster files descriptor file: " + pathToDescFile);
	}
    }     
    
    /**
     * 
     */
    public RasterRepository(String pathToDescFile, String layerGroupName)
    {
	this(pathToDescFile);
	this.layerGroupName = layerGroupName;
	this.layerGroup = this.getLayerGroup(layerGroupName);
    } 
     
     /**he landscape is constructed from a landscape
      * Load raster layer data from the given xml file.
      * Helia specific method.
      * @param landscapeXMLPath
      * @return true if the entire landscape was loaded, false otherwise.
      */
     private boolean loadLayers(String landscapeXMLPath) throws RasterLoadException
     {
 	boolean success = false;
 	
 	LandscapeLoader loader = new LandscapeLoader(landscapeXMLPath, layerGroup);

 	/*
 	 * NB: would be helpful if this threw a FileNotFoundException.
 	 * Looks like it just handles it internally by dumping info.
 	 * For example, perhaps if we can't find the xml file, we can tell it to look elsewhere.
 	 */
 	 if(loader.loadLandscapeLayers())
 	 {
 	     /*
 	      * NB: Same thing here, only this time we can't find the actual raster files.
 	      * would be helpful if this threw a FileNotFoundException, or better yet, 
 	      * a RasterLoadException.
 	      * Looks like it just handles it internally by dumping info.
 	      * For example, Perhaps if we can't find the Raster file, we can tell it to look elsewhere.
 	      */
 	    success = heliaDataLoader.loadLandscapeGroupData(layerGroup);
 	    if(!success)
 	    {
 		throw new RasterLoadException();
 	    }
 	    else
 	    {
 		//LandscapeContext.logger.debug("Raster layers loaded from: " + landscapeXMLPath);
 	    }
 	 }

 	return success;
     }
     
     /**
      * Clear all data from the LandscapeManager
      */
     public void clear()
     {
 	layerGroup.clear();		
     }
     
     /**
      * @return the x dimension of the rasters contained in this repository
      */
     public int getWidth()
     {
	 return ((RasterLayer<?>)layerGroup.getLandscapeLayerByIndex(0)).getCols();
	 //return (int)layerGroup.getLandscapeLayerByIndex(0).getExtent()[2];
     }
     
     /**
      * @return the y dimension of the rasters contained in this repository
      */
     public int getHeight()
     {
	 return ((RasterLayer<?>)layerGroup.getLandscapeLayerByIndex(0)).getRows();
	 //return (int)layerGroup.getLandscapeLayerByIndex(0).getExtent()[3];
     }
 
      /**
       * @return total number of layer groups associated with this Raster Repository
       */
      public int getNbrOfLayerGroups()
      {
	  return layerGroup.getSubLayerGroupNum();
      }
     
     /**
      * @return total number of layers associated with this Raster Repository
      */
     public int getNbrOfLayers()
     {
 	return layerGroup.getTotalLayersNum();
     }
     
     /**
      * 
      */
    public String toString()
    {
	StringBuffer buffer = new StringBuffer("nbrOfLayerGroups: " + this.getNbrOfLayerGroups() + "\n");
	buffer.append("nbrOfLayers: " + this.getNbrOfLayers() + "\n");
	buffer.append("layer dimension: " + this.getWidth() + "x" + this.getHeight() + "\n");
	
	for(int i=0; i<this.getNbrOfLayers(); i++)
	{
	    buffer.append(i + ") " + this.getLayer(i).getLayerName() + "\n");
	}
	return buffer.toString();
    }
    
//-------------------------------
// Get Specific Layer Group Info 
     
     /**
      * @return The particular landscape layer group associated with the given name
      */
     public LandscapeLayerGroup getLayerGroup(String layerGroupName)
     {
	 return layerGroup.getLandscapeLayerSubGroup(layerGroupName);
     }
     
//-------------------------
// Get Specific Layer Info       
     
     /**
      * 
      * @param Landscape Layer Type
      * @return Storage path of given Landscape Layer Type
      */
     public String getStoragePath(String layerGroupName, String layerName)
     {
 	return getLayer(layerGroupName, layerName).getStoragePath();
     }
     
     /**
      * @return The particular landscape layer associated with the given layer group name
      */
     public LandscapeLayer getLayer(String layerGroupName, String layerName)
     {
	 return layerGroup.getLandscapeLayerSubGroup(layerGroupName).getLandscapeLayer(layerName);
     }
     
     /**
      * Note: Some files may have multiple layers with the same name, but nested within different groups. 
      * Use this method if you are certain that there is only one layer with the given name. 
      * @return The particular landscape layer associated with the given layer group name. 
      */
     public LandscapeLayer getLayer(String layerName)
     {
	 return layerGroup.getLandscapeLayer(layerName);
     }
     
     /**
      * Note: Some files may have multiple layers with the same name, but nested within different groups. 
      * Use this method if you are certain that there is only one layer with the given name. 
      * @return The particular landscape layer associated with the given layer group name. 
      */
     public LandscapeLayer getLayer(LandscapeLayerType type)
     {
	 return layerGroup.getLandscapeLayer(type.toString());
     }
     
     /**
      * NB: This method is mainly used for creating Cell objects
      * @return The index associated with this layer. 
      */
     public int getLayerIndex(String layerName) throws NameNotFoundException
     {
	 int index = -99;
	 
	 boolean found = false;
	 int i=0;
	 while(!found && i<this.getNbrOfLayers())
	 {
	     if( (this.getLayer(i).getLayerName()).equals(layerName) )
	     {
		 found = true;
		 index = i; 
	     }
	     
	     i++;
	 }
	 
	 if(index==-99)
	 {
	     throw new NameNotFoundException("Unable to find index for layer name: " + layerName);
	 }
	 
	 return index;
     }
     
     /**
      * @return Landscape layer located at the given index
      */
     public LandscapeLayer getLayer(int layerIndex)
     {
 	return layerGroup.getLandscapeLayerByIndex(layerIndex);
     }

     /**
      * 
      */
     public Set<String> getUniqueLayerNames()
     {
		 Set<String> nameSet = new HashSet<String>();
		 
		 for(int i=0; i<this.getNbrOfLayers(); i++)
		 {
		     nameSet.add(this.getLayer(i).getLayerName());
		     //System.out.println(this.getLayer(i).getLayerName());
		 }
		 
		 return nameSet;
     }
     
//---------------------------
// Get Layer Values
     
     /**
      * 
      */
     public Number getValue(LandscapeLayer layer, Coord loc)
     {
	 return layer.getValueAt(loc.getX(), loc.getY()); 
     }
     
     /**
      * 
      * @param string
      * @param loc
      * @return
      */
     public Number getValue(String layerName, Coord loc)
     {
 	return this.getValue(this.getLayer(layerName), loc);
     }
     
     /**
      * 
      * @param string
      * @param loc
      * @return
      */
     public Number getValue(int layerIndex, Coord loc)
     {
 	return this.getValue(this.getLayer(layerIndex), loc);
     }
     
     /**
      * 
      * @param string
      * @param string
      * @param loc
      * @return
      */
     public Number getValue(String layerGroupName, String layerName, Coord loc)
     {
 	return this.getValue(this.getLayer(layerGroupName, layerName), loc);
     }
    
//---------------------------
// Put Layer Values     
     
     /**
      * 
      * @param layer
      * @param loc
      * @param newValue
      * @return
      */
     public boolean putValue(LandscapeLayer layer, Coord loc, Number newValue)
     {
	 return layer.putValueAt(loc.getX(), loc.getY(), newValue);
     }
     
     /**
      * 
      * @param layerName
      * @param loc
      * @param newValue
      * @return
      */
     public boolean putValue(String layerName, Coord loc, Number newValue)
     {
 	return this.putValue(this.getLayer(layerName), loc, newValue);
     }
     
     /**
      * 
      * @param layerName
      * @param loc
      * @param newValue
      * @return
      */
     public boolean putValue(int layerIndex, Coord loc, Number newValue)
     {
 	return this.putValue(this.getLayer(layerIndex), loc, newValue);
     }
     
//---------------------------
// Saving Layers
     
     /**
      * Save and replace all raster layers that have been updated using the putValueAt(...) methods in this class.
      * @return true if all landscape layers were saved, false otherwise
      */
     public boolean saveAllLayers()
     {
 	return heliaDataLoader.saveLandscapeGroupData(layerGroup);
     }
     
     /**
      * @return true if the given landscape layers was saved, false otherwise
      */
     public boolean saveLayer(LandscapeLayer layer)
     {
	 return heliaDataLoader.saveLandscapeLayerData(layer);
     }
     
     /**
      * @return true if the given landscape layers was saved, false otherwise
      */
     public boolean saveLayer(String layerName)
     {
	 return heliaDataLoader.saveLandscapeLayerData(this.getLayer(layerName));
     }
     
     /**
      * @return true if the given landscape layers was saved, false otherwise
      */
     public boolean saveLayer(int layerIndex)
     {
	 return heliaDataLoader.saveLandscapeLayerData(this.getLayer(layerIndex));
     }
     
     /**
      * Save the given raster layer to a new file.  
      * Optional timestamp and/or tick number may be included in the name of the saved file.
      * 
      * @param dataLayer The raster layer object
      * @param includeTimestamp True if the time-stamp should be included in the filename, false otherwise
      * @param includeTickNbr True if the tick number should be included in the filename, false otherwise
      * @return Success or failure. If the layer has not been changed, it will not be saved. 
      */
     public boolean saveLayerTo(LandscapeLayer layer, String pathToFolder, boolean includeTimestamp, boolean includeTickNbr)
     {
 	String filename = StringUtils.getFilename(layer.getStoragePath());
 	
 	if(includeTimestamp || includeTickNbr)
 	{
 	    DateFormat format = new SimpleDateFormat(GlobalConstants.TIMESTAMP_FORMAT_RASTER);
 	    filename = StringUtils.addTimestampToFileName(filename, format, includeTickNbr);
 	}

 	return (heliaDataLoader.saveLandscapeLayerData(layer, pathToFolder+filename));
     }
     
     /**
      * 
      * @param string
      * @param pathToHistoryDirectory
      * @param includeTimestamp
      * @param includeTickNbr
      * @return Success or failure. If the layer has not been changed, it will not be saved. 
      */
     public boolean saveLayerTo(String layerName, String pathToFolder, boolean includeTimestamp, boolean includeTickNbr)
     {
	 return this.saveLayerTo(this.getLayer(layerName), pathToFolder, includeTimestamp, includeTickNbr);
     }
     
     /**
      * 
      * @param string
      * @param pathToHistoryDirectory
      * @param includeTimestamp
      * @param includeTickNbr
      * @return Success or failure. If the layer has not been changed, it will not be saved. 
      */
     public boolean saveLayerTo(int layerIndex, String pathToFolder, boolean includeTimestamp, boolean includeTickNbr)
     {
	 return this.saveLayerTo(this.getLayer(layerIndex), pathToFolder, includeTimestamp, includeTickNbr);
     }
     
//---------------------------
// Accessors
     
     public String getPathToDescFile()
     {
         return pathToDescFile;
     }

     public void setPathToDescFile(String pathToDescFile)
     {
         this.pathToDescFile = pathToDescFile;
     }

     public String getLayerGroupName()
     {
         return layerGroupName;
     }

     public void setLayerGroupName(String layerGroupName)
     {
         this.layerGroupName = layerGroupName;
     }
     
}