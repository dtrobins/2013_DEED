package sluce2.ecosystem.biomebgc;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import sluce2.GlobalConstants;
import sluce2.ModelContext;
import sluce2.ecosystem.EcosystemContext;
import sluce2.ecosystem.EcosystemFileManager;
import sluce2.ecosystem.biomebgc.proxy.AnnualOutputFileProxy;
import sluce2.ecosystem.biomebgc.proxy.AnnualOutputType;
import sluce2.ecosystem.biomebgc.proxy.BiomeBGCProxyManager;
import sluce2.ecosystem.biomebgc.proxy.InitFileProxy;
import sluce2.ecosystem.biomebgc.proxy.InitType;
import sluce2.ecosystem.biomebgc.proxy.RestartFileProxy;
import sluce2.ecosystem.biomebgc.proxy.RestartType;
import sluce2.landscape.LandcoverType;
import sluce2.landscape.LandscapeLayerType;
import sluce2.landscape.component.Cell;
import sluce2.landscape.component.LandUnit;
import sluce2.utility.file.FileHandler;

/**
 * 
 * @author Meghan Hutchins
 * @date 03/04/2011
 */
public class BiomeBGCFileManager extends EcosystemFileManager
{
	/** A reference to the only instance of BiomeBGCFileManager **/
	private static BiomeBGCFileManager instance;
	
	/** Reference to the BiomeBGCProperties **/
	private BiomeBGCProperties bgcProperties;
	
	/** A reference to the BiomeBGC root directory  **/
	private File rootDirectory;
	
	/** 
	 * A reference to the current BiomeBGC tick directory located under the root directory.
	 * The tick directory name us updated to reflect the current model timestep (e.g. ../tick-1) 
	 * **/
	private File currentTickDirectory;
	
	/** A reference to the BiomeBGCProxyManager for reading and writing of Initialization and Restart files. **/
	private BiomeBGCProxyManager proxyManager;
	
	/** A mapping from LandcoverType to the appropriate BiomeBGC EPC file name. **/
	private Map<LandcoverType, String> landcoverEPCMap;
	
	/**
	 * 
	 */
	private BiomeBGCFileManager()
	{
		this.bgcProperties = null;
		this.rootDirectory = null;
		this.currentTickDirectory = null;
		this.proxyManager = new BiomeBGCProxyManager();
		this.landcoverEPCMap = new HashMap<LandcoverType, String>();
		this.populateLandcoverEPCMap();
	}
	
	/**
	 * @param absolute path to the root directory containing the tick folders. 
	 */
	private BiomeBGCFileManager(BiomeBGCProperties bgcProperties)
	{
		this.bgcProperties = bgcProperties;
		this.rootDirectory = new File(bgcProperties.getBgcTmpLocation());
		this.currentTickDirectory = new File(this.rootDirectory + GlobalConstants.FILE_SEPARATOR + GlobalConstants.tickDirectoryPrefix + 0);
		this.proxyManager = new BiomeBGCProxyManager(this.rootDirectory.getAbsolutePath());
		this.landcoverEPCMap = new HashMap<LandcoverType, String>();
		this.populateLandcoverEPCMap();
	}
	
	/**
	 * @param absolute path to the root directory containing the tick folders. 
	 */
	public static BiomeBGCFileManager createInstance(BiomeBGCProperties bgcProperties)
	{
		if(instance == null)
		{
			instance = new BiomeBGCFileManager(bgcProperties);
		}
		return instance;
	}
	
	/**
	 * Renames the tick folder to reflect the next tick.<p>
	 * Example: ../tick-0/ --> ../tick-1/
	 * @param landUnit
	 */
	public void incrementCurrentTickDirectory()
	{
		if( FileHandler.renameTo(this.getCurrentTickDirectory(), GlobalConstants.tickDirectoryPrefix + (ModelContext.getCurrentTick()+1)) )
		{
			this.currentTickDirectory = new File(this.rootDirectory + GlobalConstants.FILE_SEPARATOR + GlobalConstants.tickDirectoryPrefix + (ModelContext.getCurrentTick()+1));
		}
		else
		{	
			EcosystemContext.logger.error("BiomeBGC directory: " + this.getCurrentTickDirectory() + " could not be incremented to: " + GlobalConstants.tickDirectoryPrefix + (ModelContext.getCurrentTick()+1));
		}	
	}
	
	/**
	 * Updates a particular LandcoverType of the given LandUnit.
	 * @param LandUnit
	 * @param Old LandcoverType
	 * @param New LandcoverType
	 */
	public void updateLandcoverType(Integer landUnitID, LandcoverType oldLandcover, LandcoverType newLandcoverType)
	{
		File oldFile = new File(this.getCurrentTickDirectory() + GlobalConstants.FILE_SEPARATOR + GlobalConstants.landUnitDirectoryPrefix + landUnitID + GlobalConstants.FILE_SEPARATOR + oldLandcover);
		
		if(oldFile.exists())
		{
			if( FileHandler.renameTo(oldFile, newLandcoverType.toString()) )
			{
				//EcosystemContext.logger.debug("Landcover folder successfully renamed: from " + oldFile.getName() + " to " + newLandcoverType);
				
				/* If this Landcover will be processed in BiomeBGC,
				 * set the appropriate EPC file location in the initialization file
				 * and clear any litter and debris.
				 */
				if(this.isLandcoverToBeProcessed(newLandcoverType))
				{
					// Set appropriate EPC file variable in Initialization file...
					InitFileProxy initProxy = this.getInitFileProxy(landUnitID, newLandcoverType);
					initProxy.put(InitType.epcFileLoc, this.landcoverEPCMap.get(newLandcoverType) );
					this.writeInitFileProxy(initProxy, landUnitID, newLandcoverType);
					
					// Set appropriate Restart input file values...
					RestartFileProxy restartInputProxy = this.getRestartInputFileProxy(landUnitID, newLandcoverType);
					restartInputProxy.clearAllLitterAndDebrisValues();
					this.writeRestartInputProxy(restartInputProxy, landUnitID, newLandcoverType);
				}
			}
			else
			{
				EcosystemContext.logger.error("BiomeBGC directory: " + oldFile + " could not be renamed to " + newLandcoverType);
			}
		}
		else
		{
			EcosystemContext.logger.warn("BiomeBGC directory: " + oldFile + " does not exist.");
		}
		
		
	}
	
	/**
	 * TODO: Should this go here?
	 * @param initProxy
	 * @param restartOutputProxy
	 */
	public void transferDataToLandscapeRaster(LandUnit landUnit, InitFileProxy initProxy, RestartFileProxy restartOutputProxy)
	{
		if(landUnit instanceof Cell)
		{
			//((Cell)landUnit).putValue( LandscapeLayerType.LITTER, Double.valueOf(restartOutputProxy.get(RestartType.litr1c)) );
			//((Cell)landUnit).putValue( LandscapeLayerType.COARSE_WOODY_LITTER, Double.valueOf(restartOutputProxy.get(RestartType.cwdc)) );
			//((Cell)landUnit).saveValues();
		}
	}
	
	public void transferDataToLandscapeRaster(LandUnit landUnit, AnnualOutputFileProxy annualOutputProxy)
	{
		if(landUnit instanceof Cell)
		{
			//annualOutputProxy.print();
			
			((Cell)landUnit).putValue( LandscapeLayerType.CWDC, Double.valueOf(annualOutputProxy.get(AnnualOutputType.cwdc)) );
			((Cell)landUnit).putValue( LandscapeLayerType.LITRC, Double.valueOf(annualOutputProxy.get(AnnualOutputType.litrc)) );
			((Cell)landUnit).putValue( LandscapeLayerType.NEE, Double.valueOf(annualOutputProxy.get(AnnualOutputType.cum_nee)) );
			((Cell)landUnit).putValue( LandscapeLayerType.NEP, Double.valueOf(annualOutputProxy.get(AnnualOutputType.cum_nep)) );
			((Cell)landUnit).putValue( LandscapeLayerType.NPP, Double.valueOf(annualOutputProxy.get(AnnualOutputType.cum_npp)) );
			((Cell)landUnit).putValue( LandscapeLayerType.SOILC, Double.valueOf(annualOutputProxy.get(AnnualOutputType.soilc)) );
			((Cell)landUnit).putValue( LandscapeLayerType.TOTALC, Double.valueOf(annualOutputProxy.get(AnnualOutputType.totalc)) );
			((Cell)landUnit).putValue( LandscapeLayerType.VEGC, Double.valueOf(annualOutputProxy.get(AnnualOutputType.vegc)) );
			
			((Cell)landUnit).saveValues();
			//EcosystemContext.logger.debug("Trasfering AnnualOutputData to Raster Files.");
			//((Cell)landUnit).putValue( LandscapeLayerType.LITTER, Double.valueOf(restartOutputProxy.get(RestartType.litr1c)) );
			//((Cell)landUnit).putValue( LandscapeLayerType.COARSE_WOODY_LITTER, Double.valueOf(restartOutputProxy.get(RestartType.cwdc)) );
			//((Cell)landUnit).saveValues();
		}
	}
	
	
	/**
	 * Prepares the given LandUnit-LandcoverType for the next BiomeBGC run.<p>
	 * To prepare for the next model run, the Restart Output file becomes the input (i.e. Restart Input file)
	 * and the Initialization file is updated with the proper year and Meteorological tick.
	 */
	public void prepareForNextBiomeBGCRun(InitFileProxy initProxy, RestartFileProxy restartOutputProxy, Integer landUnitID, LandcoverType landcover)
	{		
		/*
		 * initProxy is updated with the value of next year.
		 * restartOutputProxy is updated with the next meteorological year index (tick).
		 */
		initProxy.put(InitType.startYear, String.valueOf( (ModelContext.getCurrentYear()+1) ));
		restartOutputProxy.put(RestartType.metyr, String.valueOf( (ModelContext.getCurrentTick()+1) ));
		
		// Write the proxy data to file.
		this.writeInitFileProxy(initProxy, landUnitID, landcover);
		this.writeRestartInputProxy(restartOutputProxy, landUnitID, landcover);
	}
	
	/**
	 * @param landUnit
	 * @param landcover
	 * @return the initialization file proxy associated with the given landUnit and landcover type.
	 */
	public InitFileProxy getInitFileProxy(Integer landUnitID, LandcoverType landcover)
	{
		proxyManager.setCurrentWorkingDirectory(this.getCurrentDirectory(landUnitID, landcover));
		proxyManager.populateInitFileProxy(bgcProperties.getProperty(GlobalConstants.BGC_INIT_FILENAME));
		return proxyManager.getInitFileProxy();
	}
	
	/**
	 * @param landUnit
	 * @param landcover
	 * @return the restart input file proxy associated with the given landUnit and landcover type.
	 */
	public RestartFileProxy getRestartInputFileProxy(Integer landUnitID, LandcoverType landcover)
	{
		proxyManager.setCurrentWorkingDirectory(this.getCurrentDirectory(landUnitID, landcover));
		proxyManager.populateRestartInputFileProxy(bgcProperties.getProperty(GlobalConstants.BGC_RESTART_INPUT_FILENAME));
		return proxyManager.getRestartInputFileProxy();
	}
	
	/**
	 * @param landUnit
	 * @param landcover
	 * @return the restart output file proxy associated with the given landUnit and landcover type.
	 */
	public RestartFileProxy getRestartOutputFileProxy(Integer landUnitID, LandcoverType landcover)
	{
		proxyManager.setCurrentWorkingDirectory(this.getCurrentDirectory(landUnitID, landcover));
		proxyManager.populateRestartOutputFileProxy(bgcProperties.getProperty(GlobalConstants.BGC_RESTART_OUTPUT_FILENAME));
		return proxyManager.getRestartOutputFileProxy();
	}
	
	/**
	 * @param landUnit
	 * @param landcover
	 * @return the restart output file proxy associated with the given landUnit and landcover type.
	 */
	public AnnualOutputFileProxy getAnnualOutputFileProxy(Integer landUnitID, LandcoverType landcover)
	{
		proxyManager.setCurrentWorkingDirectory(this.getCurrentDirectory(landUnitID, landcover));
		proxyManager.populateAnnualOutputFileProxy(bgcProperties.getProperty(GlobalConstants.BGC_ANNUAL_OUTPUT_FILENAME));
		return proxyManager.getAnnualOutputFileProxy();
	}
	
	/**
	 * @param InitFileProxy to be written to file
	 * @param landUnitID
	 * @param landcover
	 * @return
	 */
	public void writeInitFileProxy(InitFileProxy initFileproxy, Integer landUnitID, LandcoverType landcover)
	{
		proxyManager.setInitFileProxy(initFileproxy);
		proxyManager.setCurrentWorkingDirectory(this.getCurrentDirectory(landUnitID, landcover));
		proxyManager.writeInitProxyToFile(bgcProperties.getProperty(GlobalConstants.BGC_INIT_FILENAME));
	}
	
	/**
	 * @param InitFileProxy to be written to file
	 * @param landUnitID
	 * @param landcover
	 * @return
	 */
	public void writeRestartInputProxy(RestartFileProxy restartProxy, Integer landUnitID, LandcoverType landcover)
	{
		proxyManager.setRestartInputFileProxy(restartProxy);
		proxyManager.setCurrentWorkingDirectory(this.getCurrentDirectory(landUnitID, landcover));
		proxyManager.writeRestartInputProxyToFile(bgcProperties.getProperty(GlobalConstants.BGC_RESTART_INPUT_FILENAME));
	}
	
	/**
	 * @param InitFileProxy to be written to file
	 * @param landUnitID
	 * @param landcover
	 * @return
	 */
	public void writeRestartOutputProxy(RestartFileProxy restartProxy, Integer landUnitID, LandcoverType landcover)
	{
		proxyManager.setRestartOutputFileProxy(restartProxy);
		proxyManager.setCurrentWorkingDirectory(this.getCurrentDirectory(landUnitID, landcover));
		proxyManager.writeRestartInputProxyToFile(bgcProperties.getProperty(GlobalConstants.BGC_RESTART_OUTPUT_FILENAME));
	}
	

//---------------------
// EPC Map
	
	/**
	 * Creates the mapping from a LandcoverType to an EPC file. 
	 */
	private void populateLandcoverEPCMap()
	{
		/************************************************/
		/** TODO: ADD MORE LANDCOVER-EPC MAPPINGS HERE **/
		landcoverEPCMap.put(LandcoverType.TURFGRASS, bgcProperties.getProperty(GlobalConstants.BGC_SHARED_FILE_LOCATION)+bgcProperties.getProperty(GlobalConstants.BGC_TURFGRASS_FILENAME));
		landcoverEPCMap.put(LandcoverType.TREECOVER, bgcProperties.getProperty(GlobalConstants.BGC_SHARED_FILE_LOCATION)+bgcProperties.getProperty(GlobalConstants.BGC_DECIDUOUSFOREST_FILENAME));
	}
	
	/**
	 * @param LandcoverType
	 * @return true if the given LandcoverType will be processed by BiomeBGC, false otherwise.
	 */
	public boolean isLandcoverToBeProcessed(LandcoverType type)
	{
		return landcoverEPCMap.containsKey(type);
	}
	
//---------------------
// Accessors
	
	public static BiomeBGCFileManager getInstance()
	{
		return instance;
	}

	public File getRootDirectory()
	{
		return rootDirectory;
	}

	public void setRootDirectory(File rootDirectory)
	{
		this.rootDirectory = rootDirectory;
	}

	public File getCurrentTickDirectory()
	{
		return currentTickDirectory;
	}

	public void setCurrentTickDirectory(File currentTickDirectory)
	{
		this.currentTickDirectory = currentTickDirectory;
	}

	public Map<LandcoverType, String> getLandcoverEPCMap()
	{
		return landcoverEPCMap;
	}

	public void setLandcoverEPCMap(Map<LandcoverType, String> landcoverEPCMap)
	{
		this.landcoverEPCMap = landcoverEPCMap;
	}
	
	/**
	 * @param landUnitID
	 * @param landcover
	 * @return the name of the current directory for the given LandUnit ID and LandcoverType.
	 */
	public String getCurrentDirectoryName(Integer landUnitID, LandcoverType landcover)
	{
		return (this.getCurrentTickDirectory() + GlobalConstants.FILE_SEPARATOR + GlobalConstants.landUnitDirectoryPrefix + landUnitID + GlobalConstants.FILE_SEPARATOR + landcover);
	}
	
	/**
	 * @param landUnitID
	 * @param landcover
	 * @return the current directory file for the given LandUnit ID and LandcoverType.
	 */
	public File getCurrentDirectory(Integer landUnitID, LandcoverType landcover)
	{
		return new File(this.getCurrentDirectoryName(landUnitID, landcover));
	}
	
	/**
	 * @param landUnitID
	 * @param landcover
	 * @return the path to the current Initialization file for the given LandUnit ID and LandcoverType.
	 */
	public String getCurrentInitFileName(Integer landUnitID, LandcoverType landcover)
	{
		return(getCurrentDirectoryName(landUnitID, landcover)+GlobalConstants.FILE_SEPARATOR+bgcProperties.getProperty(GlobalConstants.BGC_INIT_FILENAME));
	}
	
	/**
	 * @param landUnitID
	 * @param landcover
	 * @return the path to the current Restart Input file for the given LandUnit ID and LandcoverType.
	 */
	public String getCurrentRestartInputFileName(Integer landUnitID, LandcoverType landcover)
	{
		return(getCurrentDirectoryName(landUnitID, landcover)+GlobalConstants.FILE_SEPARATOR+bgcProperties.getProperty(GlobalConstants.BGC_RESTART_INPUT_FILENAME));
	}
	
	/**
	 * @param landUnitID
	 * @param landcover
	 * @return the current Restart Input file for the given LandUnit ID and LandcoverType.
	 */
	public File getCurrentRestartInputFile(Integer landUnitID, LandcoverType landcover)
	{
		return new File(this.getCurrentRestartInputFileName(landUnitID, landcover));
	}
	
	/**
	 * @param landUnitID
	 * @param landcover
	 * @return the path to the current Restart Output file for the given LandUnit ID and LandcoverType.
	 */
	public String getCurrentRestartOutputFileName(Integer landUnitID, LandcoverType landcover)
	{
		return(getCurrentDirectoryName(landUnitID, landcover)+GlobalConstants.FILE_SEPARATOR+bgcProperties.getProperty(GlobalConstants.BGC_RESTART_OUTPUT_FILENAME));
	}
	
	/**
	 * @param landUnitID
	 * @param landcover
	 * @return the current Restart Output file for the given LandUnit ID and LandcoverType.
	 */
	public File getCurrentRestartOutputFile(Integer landUnitID, LandcoverType landcover)
	{
		return new File(this.getCurrentRestartInputFileName(landUnitID, landcover));
	}
	
	
	
	
}
