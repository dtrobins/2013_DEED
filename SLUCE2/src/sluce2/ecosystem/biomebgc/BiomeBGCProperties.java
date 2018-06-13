package sluce2.ecosystem.biomebgc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.Map;
import java.util.Properties;

import sluce2.GlobalConstants;
import sluce2.ecosystem.EcosystemContext;
import sluce2.ecosystem.EcosystemProperties;
import sluce2.ecosystem.biomebgc.proxy.RestartFileProxy;
import sluce2.landscape.component.LandUnit;
import sluce2.utility.StringUtils;
import sluce2.utility.file.BinaryFileHandler;
import sluce2.utility.file.FileHandler;
import sluce2.utility.file.TextFileHandler;

/**
 * The BiomeBGCProperties class contains properties need to run the external BiomeBGC ecosystem model<p>
 * All properties are specific to the BiomeBGC 4.2 implementation.<p>
 * <p>
 * Information needed to run BiomeBGC is passed in using a BiomeBGC descriptor file (xml).<p>
 * Example:<p>
 * <pre> 
 * {@code  
 * <!DOCTYPE properties SYSTEM "http://java.sun.com/dtd/properties.dtd">
 * <properties>
 *   <entry key="bgcDirectory">/home/meghan/Applications/Biome-BGC/biomebgc-4.2/</entry>
 *   <entry key="bgcExecFile">/home/meghan/Applications/Biome-BGC/biomebgc-4.2/bgc</entry>
 *   <entry key="iniFileTemplate">/home/meghan/Applications/Biome-BGC/biomebgc-4.2/sluce2/exp0/iemss.ini</entry>
 *     <entry key="restartInputFileLineNum">11</entry>
 *     <entry key="restartOutputFileLineNum">11</entry>
 *     <entry key="meteroFileLineNum">4</entry>
 *     <entry key="epcFileLineNum">50</entry>
 *     <entry key="simulationYearLineNum">17</entry>
 *     <entry key="outputFileLineNum">74</entry>
 * </properties>
 * } 	 
 * </pre>
 * 
 * @author Meghan Hutchins, Shipeng Sun
 * @date 01.31.2011
 */
public class BiomeBGCProperties extends EcosystemProperties
{
	/** The properties needed to run the external BiomeBGC ecosystem model **/
	private Properties properties; 
	
	/** Path to the BiomeBGC executable. **/
	private String bgcExecutable;
	
	/** The default root directory BiomeBGC files. **/
	private String bgcDefaultFileDirectory;
	
	/** Root directory to the files common to all BiomeBGC runs (shared resources). **/
	private String bgcSharedFileLocation;
	
	/** Root directory to the individual LandUnit BiomeBGC files (resources unique to each individual LandUnit run. **/
	private String bgcTmpLocation;
	
	
    /** FileHandler for the BiomeBGC Initialization file **/
	private TextFileHandler iniFile; 
	/** FileHandler for the BiomeBGC Input Restart file **/
	private BinaryFileHandler restartInputFile;
	/** FileHandler for the BiomeBGC Output Restart file **/
	private BinaryFileHandler restartOutputFile;
	
	
	/** FileHandler for the BiomeBGC Meteorology file **/
	//private TextFileHandler meteorologyFile;
	/** FileHandler for the BiomeBGC Ecophysiological file **/
	//private TextFileHandler epcFile;
	
	/**
	 * Creates a BiomeBGCRunner with properties file located at the given pathname.<p>
	 * @param path to BiomeBGC descriptor file (xml).
	 */
	public BiomeBGCProperties(String xmlPathname)
	{
		
		super(xmlPathname);
		this.loadProperties();
		
		this.bgcExecutable = properties.getProperty(GlobalConstants.BGC_EXECUTABLE);
		this.bgcSharedFileLocation = properties.getProperty(GlobalConstants.BGC_SHARED_FILE_LOCATION);
		this.bgcTmpLocation = properties.getProperty(GlobalConstants.BGC_TMP_LOCATION);
		
		//this.bgcDefaultFileDirectory = properties.getProperty(GlobalConstants.BGC_DEFAULT_FILE_DIRECTORY);
		
		//try
		//{
			//this.iniFile = new TextFileHandler(properties.getProperty(GlobalConstants.BGC_DEFAULT_INI_FILE));
			//this.meteorologyFile = new TextFileHandler(this.bgcDefaultFileDirectory + StringUtils.getTokenAt(iniFile.getLine( Integer.valueOf(properties.getProperty(GlobalConstants.BGC_INI_FILE_METEOROLOGY_LINE))), 0));
			//this.epcFile = new TextFileHandler(this.bgcDefaultFileDirectory + StringUtils.getTokenAt(iniFile.getLine( Integer.valueOf(properties.getProperty(GlobalConstants.BGC_INI_FILE_EPC_LINE))), 0));
			//this.restartInputFile = new BinaryFileHandler(this.bgcDefaultFileDirectory + StringUtils.getTokenAt(iniFile.getLine( Integer.valueOf(properties.getProperty(GlobalConstants.BGC_INI_FILE_RESTART_INPUT_LINE))), 0), GlobalConstants.BGC_NBR_BYTES_IN_RESTART);
			//this.restartOutputFile = new BinaryFileHandler(this.bgcDefaultFileDirectory + StringUtils.getTokenAt(iniFile.getLine( Integer.valueOf(properties.getProperty(GlobalConstants.BGC_INI_FILE_RESTART_OUTPUT_LINE))), 0), GlobalConstants.BGC_NBR_BYTES_IN_RESTART);	
		//}
		//catch(FileNotFoundException fnfe)
		//{
			//EcosystemContext.logger.error(fnfe);
		//}
		
	}
	
	/**
	 * @param descriptorPathname
	 * @return
	 */
	public void loadProperties()
	{
		if(properties == null)
		{
			properties = new Properties();
		}
		
		try
		{
			FileInputStream in = new FileInputStream(new File(xmlPathname));
			properties.loadFromXML(in);
			in.close();	
		}
		catch(FileNotFoundException fnfe)
		{
			EcosystemContext.logger.error(fnfe + " : " + xmlPathname);
		}
		catch(InvalidPropertiesFormatException ipfe)
		{
			EcosystemContext.logger.error(ipfe + " : " + xmlPathname);
		}
		catch(IOException ioe)
		{
			EcosystemContext.logger.error(ioe + " : " + xmlPathname);
		}
		
		assert(!properties.isEmpty());
	}
	
	/**
	 * @param property
	 * @return
	 */
	public String getProperty(String property)
	{
		return properties.get(property).toString();
	}
	
	/**
	 * 
	 * @param landUnit
	 */
	public void updatePropertiesFor(LandUnit landUnit)
	{
		
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<String> getInitializationData()
	{
		return this.iniFile.fileToArray();
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<String> getRestartDataArray()
	{
		return this.restartInputFile.fileToArray();
	}
	
	/**
	 * 
	 * @return
	 */
	/*
	public ArrayList<String> getMeteorologyData()
	{
		return this.meteorologyFile.fileToArray();
	}
	*/
	
	/**
	 * 
	 * @return
	 */
	/*
	public ArrayList<String> getEpcData()
	{
		return this.epcFile.fileToArray();
	}
	*/
	
	/**
	 * 
	 * @return
	 */
	public void printContentsOfRestartFile()
	{
		RestartFileProxy proxy = new RestartFileProxy();
		proxy.putValues(getRestartDataArray());
		proxy.print();
	}

	
//--------------------------
// Accessors		
			
	
	public Properties getProperties()
	{
		return properties;
	}

	public void setProperties(Properties properties)
	{
		this.properties = properties;
	}

	public String getBgcExecutable()
	{
		return bgcExecutable;
	}

	public void setBgcExecutable(String bgcExecutable)
	{
		this.bgcExecutable = bgcExecutable;
	}

	public String getBgcDefaultFileDirectory()
	{
		return bgcDefaultFileDirectory;
	}

	public void setBgcDefaultFileDirectory(String bgcDefaultFileDirectory)
	{
		this.bgcDefaultFileDirectory = bgcDefaultFileDirectory;
	}

	public String getBgcSharedFileLocation()
	{
		return bgcSharedFileLocation;
	}

	public void setBgcSharedFileLocation(String bgcSharedFileLocation)
	{
		this.bgcSharedFileLocation = bgcSharedFileLocation;
	}

	/*
	public String getBgcLandUnitFileLocation()
	{
		return bgcLandUnitFileLocation;
	}

	public void setBgcLandUnitFileLocation(String bgcLandUnitFileLocation)
	{
		this.bgcLandUnitFileLocation = bgcLandUnitFileLocation;
	}
	*/

	public TextFileHandler getIniFile()
	{
		return iniFile;
	}

	public void setIniFile(TextFileHandler iniFile)
	{
		this.iniFile = iniFile;
	}

	public BinaryFileHandler getRestartInputFile()
	{
		return restartInputFile;
	}

	public void setRestartInputFile(BinaryFileHandler restartInputFile)
	{
		this.restartInputFile = restartInputFile;
	}

	public BinaryFileHandler getRestartOutputFile()
	{
		return restartOutputFile;
	}

	public void setRestartOutputFile(BinaryFileHandler restartOutputFile)
	{
		this.restartOutputFile = restartOutputFile;
	}

	public String getBgcTmpLocation()
	{
		return bgcTmpLocation;
	}

	public void setBgcTmpLocation(String bgcTmpLocation)
	{
		this.bgcTmpLocation = bgcTmpLocation;
	}

	

	
	
	
	/*
	public Properties getProperties()
	{
		return properties;
	}

	public void setProperties(Properties properties)
	{
		this.properties = properties;
	}

	public String getBgcRootDir()
	{
		return bgcSharedFileLocation;
	}

	public void setBgcRootDir(String bgcRootDir)
	{
		this.bgcSharedFileLocation = bgcRootDir;
	}

	public String getBgcExecutable()
	{
		return bgcExecutable;
	}

	public void setBgcExecutable(String bgcExecutable)
	{
		this.bgcExecutable = bgcExecutable;
	}

	public TextFileHandler getIniFile()
	{
		return iniFile;
	}

	public void setIniFile(TextFileHandler iniFile)
	{
		this.iniFile = iniFile;
	}

	public BinaryFileHandler getRestartInputFile()
	{
		return restartInputFile;
	}

	public void setRestartInputFile(BinaryFileHandler restartInputFile)
	{
		this.restartInputFile = restartInputFile;
	}

	public BinaryFileHandler getRestartOutputFile()
	{
		return restartOutputFile;
	}

	public void setRestartOutputFile(BinaryFileHandler restartOutputFile)
	{
		this.restartOutputFile = restartOutputFile;
	}
*/
	/*
	public TextFileHandler getMeteorologyFile()
	{
		return meteorologyFile;
	}
	 
	public void setMeteorologyFile(TextFileHandler meteorologyFile)
	{
		this.meteorologyFile = meteorologyFile;
	}

	public TextFileHandler getEpcFile()
	{
		return epcFile;
	}

	public void setEpcFile(TextFileHandler epcFile)
	{
		this.epcFile = epcFile;
	}
	*/
	
}
