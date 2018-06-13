package sluce2.ecosystem.biomebgc.proxy;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

import sluce2.ecosystem.proxy.EcosystemFileProxy;
import sluce2.utility.file.BinaryFileHandler;
import sluce2.utility.file.FileHandler;
import sluce2.utility.file.BinaryFileHandler.BinaryType;
import sluce2.utility.file.BinaryFileHandler.ByteNumber;

/**
 * A surrogate to the BiomeBGC Restart File.<p>
 * Objects in the Sluce2 model may control the BiomeBGC Restart File through the RestartFileProxy.
 * 
 * @author Meghan Hutchins, Shipeng Sun
 * @date 01.31.2011
 */
public class RestartFileProxy extends BiomeBGCFileProxy
{
	/** A Map representation of the BiomeBGC Restart File **/
	private Map<RestartType, String> restartMap;
	
	/**
	 * Creates an empty RestartFileProxy
	 */
	public RestartFileProxy()
	{
		restartMap = new EnumMap<RestartType, String>(RestartType.class);
	
		// Populate the restartMap with keys and null values
		for(RestartType key : RestartType.values())
		{
			restartMap.put(key, "0.0");
		}
	}
	
	/**
	 * Populates the RestartFileProxy with the given values.
	 * NB: Values must be in the same order as the BiomeBGC Restart File variables.
	 * @param ArrayList of Restart File values
	 */
	public void putValues(ArrayList<String> restartValues)
	{	
		int i=0;
		for(RestartType name : restartMap.keySet())
		{
			restartMap.put(name, restartValues.get(i));
			i++;
		}
	}
	
	/**
	 * @param name
	 * @param value
	 */
	public void put(RestartType name, String value)
	{
		restartMap.put(name, value);
	}
	
	/**
	 * @param name
	 * @return value associated with the given RestartVarType
	 */
	public String get(RestartType name)
	{
		return restartMap.get(name);
	}
	
	/**
	 * @return Set of RestartFileProxy entries
	 */
	public Set<Map.Entry<RestartType,String>> entrySet()
	{
		return restartMap.entrySet();
	}
	
	/**
	 * @return boolean
	 */
	public void clearValues()
	{
		restartMap.clear();
		restartMap = null;
		restartMap = new EnumMap<RestartType, String>(RestartType.class);
	}

	/**
	 * @return the number of data in this RestartFileProxy
	 */
	public int size()
	{
		return restartMap.size();
	}
	
	/**
	 * @return true if this RestartFileProxy is empty, false otherwise.
	 */
	public boolean isEmpty()
	{
		return restartMap.isEmpty();
	}
	
	/**
	 * 
	 */
	public void print()
	{
		for(Map.Entry<RestartType,String> entry : this.entrySet())
		{
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
	}
	
//----------------------------
// Read / Write Restart File
	
	/**
	 * Reads in the BiomeBGC Restart File data and populates the RestartFileProxy.
	 * @param restartFile
	 */
	public void readIn(BinaryFileHandler restartFile)
	{
		this.putValues(restartFile.fileToArray(BinaryType.DOUBLE, ByteNumber.EIGHT));
		//this.putValues(restartFile.fileToArray());
	}
	
	/**
	 * Writes the RestartFileProxy data to the BiomeBGC Restart File
	 * @param restartFile
	 * @return true if the BiomeBGC Restart File was updated, false otherwise. 
	 */
	public boolean writeOut(BinaryFileHandler restartFile)
	{
		return restartFile.writeArrayToFile( new ArrayList<String>(restartMap.values()) , BinaryType.DOUBLE, ByteNumber.EIGHT );
	}
	
	/**
	 * Sets all Litter values to zero.
	 */
	public void clearAllLitterAndDebrisValues()
	{
		// litter
		restartMap.put(RestartType.litr1c, "0.0");
		restartMap.put(RestartType.litr2c, "0.0");
		restartMap.put(RestartType.litr3c, "0.0");
		restartMap.put(RestartType.litr4c, "0.0");
		restartMap.put(RestartType.litr1n, "0.0");
		restartMap.put(RestartType.litr1n, "0.0");
		restartMap.put(RestartType.litr1n, "0.0");
		restartMap.put(RestartType.litr1n, "0.0");
		
		// debris
		restartMap.put(RestartType.cwdc, "0.0");
		restartMap.put(RestartType.cwdn, "0.0");
	}

	@Override
	public void readIn(FileHandler file)
	{
		// TODO Auto-generated method stub
	}

	@Override
	public boolean writeOut(FileHandler file)
	{
		// TODO Auto-generated method stub
		return false;
	}
}
