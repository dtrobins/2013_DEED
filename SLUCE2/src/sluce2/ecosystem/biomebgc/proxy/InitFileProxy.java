package sluce2.ecosystem.biomebgc.proxy;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import sluce2.GlobalConstants;
import sluce2.ecosystem.EcosystemContext;
import sluce2.ecosystem.proxy.EcosystemFileProxy;
import sluce2.utility.file.FileHandler;

/**
 * A surrogate to the BiomeBGC RInitialization (.ini) File.<p>
 * Objects in the Sluce2 model may control the BiomeBGC Initialization File through the InitFileProxy.
 * 
 * @author Meghan Hutchins, Shipeng Sun
 * @date 01.31.2011
 */
public class InitFileProxy extends BiomeBGCFileProxy
{
	/** A Map representation of the BiomeBGC Initialization (.ini) File **/
	private Map<InitType, String> initMap;
	
	/**
	 * Creates an empty initFileFileProxy
	 */
	public InitFileProxy()
	{
		initMap = new EnumMap<InitType, String>(InitType.class);
	
		// Populate the initMap with keys and null values
		for(InitType key : InitType.values())
		{
			initMap.put(key, null);
		}
	}
	
	/**
	 * Populates the initFileFileProxy with the given values.
	 * NB: Values must be in the same order as the BiomeBGC initFile File variables.
	 * @param ArrayList of Init File values
	 */
	public void putValues(ArrayList<String> initValues)
	{	
		for(InitType name : initMap.keySet())
		{
			initMap.put(name, (initValues.get(name.getLineNbr()-1)).split(" ")[0] ); //.split(" ")[0]
		}
	}
	
	/**
	 * @param name
	 * @param value
	 */
	public void put(InitType name, String value)
	{
		initMap.put(name, value);
	}
	
	/**
	 * @param name
	 * @return value associated with the given InitType
	 */
	public String get(InitType name)
	{
		return initMap.get(name);
	}
	
	/**
	 * @return Set of InitFileProxy entries
	 */
	public Set<Map.Entry<InitType,String>> entrySet()
	{
		return initMap.entrySet();
	}
	
	/**
	 * @return boolean
	 */
	public void clearValues()
	{
		initMap.clear();
		initMap = null;
		initMap = new EnumMap<InitType, String>(InitType.class);
	}

	/**
	 * @return the number of elements in this InitFileProxy
	 */
	public int size()
	{
		return initMap.size();
	}
	
	/**
	 * @return true if this InitFileProxy is empty, false otherwise.
	 */
	public boolean isEmpty()
	{
		return initMap.isEmpty();
	}
	
	/**
	 * 
	 */
	public void print()
	{
		for(Map.Entry<InitType,String> entry : this.entrySet())
		{
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
	}
	
//----------------------------
// Read / Write Init File
	
	/**
	 * Reads in the BiomeBGC Init File data and populates the InitFileProxy.
	 * @param initFile
	 */
	@Override
	public void readIn(FileHandler initFile)
	{
		this.putValues(initFile.fileToArray());
	}
	
	/**
	 * Writes the InitFileProxy data to the BiomeBGC Init File
	 * @param initFile
	 * @return true if the BiomeBGC Init File was updated, false otherwise. 
	 */
	@Override
	public boolean writeOut(FileHandler initFile)
	{
		ArrayList<String> preparedDataArray = this.transformProxyDataToArray(GlobalConstants.BGC_INI_FILE_LENGTH);
		return initFile.writeArrayToFile(preparedDataArray);
	}
	
	/**
	 * Transforms the Initialization Proxy Data Map into an Array to be written back to the BiomeBGC Initialization File.
	 * @return ArrayList representing the full BiomeBGC Initialization File Structure.
	 */
	private ArrayList<String> transformProxyDataToArray(int lengthOfDataArray)
	{
		ArrayList<String> fileArray = new ArrayList<String>();
		for(int i=0; i<lengthOfDataArray; i++)
		{
			fileArray.add("");
		}

		for(Map.Entry<InitType, String> entry : this.initMap.entrySet())
		{
			fileArray.set((entry.getKey().getLineNbr())-1, entry.getValue());
		}
		return fileArray;
	}	
}
