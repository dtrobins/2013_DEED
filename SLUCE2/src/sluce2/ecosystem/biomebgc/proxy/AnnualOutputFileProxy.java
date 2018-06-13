package sluce2.ecosystem.biomebgc.proxy;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

import sluce2.utility.file.BinaryFileHandler;
import sluce2.utility.file.BinaryFileHandler.BinaryType;
import sluce2.utility.file.BinaryFileHandler.ByteNumber;
import sluce2.utility.file.FileHandler;

/**
 * A surrogate to the BiomeBGC Restart File.<p>
 * Objects in the Sluce2 model may control the BiomeBGC Restart File through the RestartFileProxy.
 * 
 * @author Meghan Hutchins, Shipeng Sun
 * @date 01.31.2011
 */
public class AnnualOutputFileProxy extends BiomeBGCFileProxy
{
	/** A Map representation of the BiomeBGC Restart File **/
	private Map<AnnualOutputType, String> varMap;
	
	/**
	 * Creates an empty RestartFileProxy
	 */
	public AnnualOutputFileProxy()
	{
		varMap = new EnumMap<AnnualOutputType, String>(AnnualOutputType.class);
	
		// Populate the restartMap with keys and null values
		for(AnnualOutputType key : AnnualOutputType.values())
		{
			varMap.put(key, null);
		}
	}
	
	/**
	 * Populates the AnnualOutputFileProxy with the given values.
	 * NB: Values must be in the same order as the BiomeBGC Restart File variables.
	 * @param ArrayList of Restart File values
	 */
	public void putValues(ArrayList<String> varValues)
	{	
		int i=0;
		for(AnnualOutputType name : varMap.keySet())
		{
			varMap.put(name, varValues.get(i));
			i++;
		}
	}
	
	/**
	 * @param name
	 * @param value
	 */
	public void put(AnnualOutputType name, String value)
	{
		varMap.put(name, value);
	}
	
	/**
	 * @param name
	 * @return value associated with the given AnnualOutputType
	 */
	public String get(AnnualOutputType name)
	{
		return varMap.get(name);
	}
	
	/**
	 * @return Set of AnnualOutputFileProxy entries
	 */
	public Set<Map.Entry<AnnualOutputType,String>> entrySet()
	{
		return varMap.entrySet();
	}
	
	/**
	 * @return boolean
	 */
	public void clearValues()
	{
		varMap.clear();
		varMap = null;
		varMap = new EnumMap<AnnualOutputType, String>(AnnualOutputType.class);
	}

	/**
	 * @return the number of data in this AnnualOutputFileProxy
	 */
	public int size()
	{
		return varMap.size();
	}
	
	/**
	 * @return true if this AnnualOutputFileProxy is empty, false otherwise.
	 */
	public boolean isEmpty()
	{
		return varMap.isEmpty();
	}
	
	/**
	 * 
	 */
	public void print()
	{
		for(Map.Entry<AnnualOutputType,String> entry : this.entrySet())
		{
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
	}
	
//----------------------------
// Read / Write Restart File
	
	/**
	 * Reads in the BiomeBGC Restart File data and populates the AnnualOutputFileProxy.
	 * @param restartFile
	 */
	public void readIn(BinaryFileHandler annualOutputFile)
	{
		this.putValues(annualOutputFile.fileToArray(BinaryType.FLOAT, ByteNumber.FOUR));
	}
	
	/**
	 * Writes the RestartFileProxy data to the BiomeBGC annualOutputFile
	 * @param restartFile
	 * @return true if the BiomeBGC Annual Output File was updated, false otherwise. 
	 */
	public boolean writeOut(BinaryFileHandler annualOutputFile)
	{
		return annualOutputFile.writeArrayToFile( new ArrayList<String>(varMap.values()), BinaryType.FLOAT, ByteNumber.FOUR );
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
