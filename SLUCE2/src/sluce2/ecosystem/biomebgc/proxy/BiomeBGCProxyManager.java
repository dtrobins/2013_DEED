package sluce2.ecosystem.biomebgc.proxy;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import sluce2.GlobalConstants;
import sluce2.ModelContext;
import sluce2.ecosystem.EcosystemContext;
import sluce2.ecosystem.biomebgc.BiomeBGCProperties;
import sluce2.ecosystem.proxy.EcosystemProxyManager;
import sluce2.utility.file.BinaryFileHandler;
import sluce2.utility.file.FileHandler;
import sluce2.utility.file.TextFileHandler;
import sluce2.landscape.LandcoverType;

/**
 * Manages all Proxies related to BiomeBGC.<p>
 * 
 * The Proxies included in the BiomeBGCProxyManager provide a surrogate 
 * for the BiomeBGC files that both the ABM and BiomeBGC use for model 
 * parameterization and data-gathering.
 * 
 * @author Meghan Hutchins
 * @date 2011.02.14
 */
public class BiomeBGCProxyManager implements EcosystemProxyManager
{
	private String pathToRootDir; 
	
	private File currentWorkingDir; 
	
	private InitFileProxy initFileProxy;
	
	private RestartFileProxy restartInputFileProxy;
	
	private RestartFileProxy restartOutputFileProxy;
	
	private AnnualOutputFileProxy annualOutputFileProxy;
		
	/**
	 * 
	 */
	public BiomeBGCProxyManager()
	{
		this.pathToRootDir = null;
		this.currentWorkingDir = null;
		this.initFileProxy = new InitFileProxy();
		this.restartInputFileProxy = new RestartFileProxy();
		this.restartOutputFileProxy = new RestartFileProxy();
		this.annualOutputFileProxy = new AnnualOutputFileProxy();
	}
	
	/**
	 * 
	 */
	public BiomeBGCProxyManager(String pathToRootDir)
	{
		/** TODO: check to make sure relativePath ends with a '/'  or '\\' **/
		this.pathToRootDir = pathToRootDir;
		this.currentWorkingDir = null;
		this.initFileProxy = new InitFileProxy();
		this.restartInputFileProxy = new RestartFileProxy();
		this.restartOutputFileProxy = new RestartFileProxy();
		this.annualOutputFileProxy = new AnnualOutputFileProxy();
	}

	/**
	 * Set the current working directory using the given directory.<p>
	 * Example: "/home/meghan/Desktop/SLUCE2-Environment/SLUCE2-BGC-tmp/tick-0/lu.0/CROP"
	 * @param File to current working directory. 
	 */
	public void setCurrentWorkingDirectory(File currentWorkingDir)
	{
		if(currentWorkingDir.exists())
		{
			this.currentWorkingDir = currentWorkingDir;
		}
		else
		{
			EcosystemContext.logger.error(currentWorkingDir + " does not exist." );
		}
	}
	
	
	/**
	 * Set the current working directory using the given relative path from the root directory.
	 * @param relativePath
	 */
	/*
	@Deprecated
	public boolean setCurrentWorkingDir(String relativePath)
	{
		boolean success = false;
		
		if(null != relativePath && !relativePath.isEmpty())
		{
			this.currentWorkingDir = new File(this.pathToRootDir + relativePath);
			if(!this.currentWorkingDir.exists())
			{
				this.currentWorkingDir.mkdirs();
			}
			success = this.currentWorkingDir.exists();
		}
		
		return success;
	}
	*/
	
	/**
	 * Populates the InitFileProxy with a BiomeBGC initialization file given as a string.
	 * @param Name of BiomeBGC initialization file (a text file).
	 */
	public void populateInitFileProxy(String initFileName)
	{
		try
		{
			TextFileHandler fileHandler = new TextFileHandler(new File(this.getCurrentWorkingDir().getAbsolutePath(), initFileName));
			this.initFileProxy.readIn(fileHandler);
		}
		catch(FileNotFoundException fnfe)
		{
			EcosystemContext.logger.error(fnfe + " : " + (new File(this.getCurrentWorkingDir().getAbsolutePath(), initFileName).getAbsolutePath()) );
		}		
	}
	
	/**
	 * Populates the RestartFileProxy with a BiomeBGC restart output file given as a string.
	 * @param Name of BiomeBGC restart output file (a binary file).
	 */
	public void populateRestartInputFileProxy(String restartInputFileName)
	{
		try
		{
			BinaryFileHandler fileHandler = new BinaryFileHandler(new File(this.getCurrentWorkingDir().getAbsolutePath(), restartInputFileName), GlobalConstants.BGC_NBR_BYTES_IN_RESTART);
			this.restartInputFileProxy.readIn(fileHandler);
		}
		catch(FileNotFoundException fnfe)
		{
			EcosystemContext.logger.error(fnfe + " : " + (new File(this.getCurrentWorkingDir().getAbsolutePath(), restartInputFileName).getAbsolutePath()) );
		}
	}
	
	/**
	 * Populates the RestartFileProxy with a BiomeBGC restart output file given as a string.
	 * @param Name of BiomeBGC restart output file (a binary file).
	 */
	public void populateRestartOutputFileProxy(String restartOutputFileName)
	{
		try
		{
			BinaryFileHandler fileHandler = new BinaryFileHandler(new File(this.getCurrentWorkingDir().getAbsolutePath(), restartOutputFileName), GlobalConstants.BGC_NBR_BYTES_IN_RESTART);
			this.restartOutputFileProxy.readIn(fileHandler);
		}
		catch(FileNotFoundException fnfe)
		{
			EcosystemContext.logger.error(fnfe + " : " + (new File(this.getCurrentWorkingDir().getAbsolutePath(), restartOutputFileName).getAbsolutePath()) );
		}
	}
	
	/**
	 * Populates the AnnualOutputFileProxy with a BiomeBGC annual output file given as a string.
	 * @param Name of BiomeBGC annual output file (a binary file).
	 */
	public void populateAnnualOutputFileProxy(String annualOutputFileName)
	{
		try
		{
			// the type of values in output files is four-byte float
			//int nbrBytesInOutput = (int)outputFile.length()/4;
			//int nbrBytesInOutput = (int)outputFile.length();
			//System.out.println("======="  + nbrBytesInOutput + "======="); // outputFile.length() = 580 | actual file size = 144?
			//int nbrBytesInOutput = 144;
			//BinaryFileHandler fileHandler = new BinaryFileHandler(outputFile, nbrBytesInOutput);
			BinaryFileHandler fileHandler = new BinaryFileHandler(new File(this.getCurrentWorkingDir().getAbsolutePath(), annualOutputFileName), GlobalConstants.BGC_NBR_BYTES_IN_ANNUALOUTPUT);
			this.annualOutputFileProxy.readIn(fileHandler);
		}
		catch(FileNotFoundException fnfe)
		{
			EcosystemContext.logger.error(fnfe + " : " + (new File(this.getCurrentWorkingDir().getAbsolutePath(), annualOutputFileName).getAbsolutePath()) );
		}
	}
	
	/**
	 * Populates the RestartFileProxy with a BiomeBGC restart output file given as a string.
	 * @param Name of BiomeBGC restart output file (a binary file).
	 */
	public void populateRestartInputFileProxyWithRestartOutput(String restartOutputFileName)
	{
		try
		{
			BinaryFileHandler fileHandler = new BinaryFileHandler(new File(this.getCurrentWorkingDir().getAbsolutePath(), restartOutputFileName), GlobalConstants.BGC_NBR_BYTES_IN_RESTART);
			//this.restartFileProxy.readIn(fileHandler);
		}
		catch(FileNotFoundException fnfe)
		{
			EcosystemContext.logger.error(fnfe + " : " + (new File(this.getCurrentWorkingDir().getAbsolutePath(), restartOutputFileName).getAbsolutePath()) );
		}
	}
	
	/**
	 * Overwrites the given BiomeBGC initialization file with the contents of the InitFileProxy.
	 * @param Name of BiomeBGC initialization file (a text file).
	 */
	public void writeInitProxyToFile(String fileName)
	{
		File newFile = new File(this.getCurrentWorkingDir().getAbsolutePath(), fileName);
		
		if(newFile.exists())
		{
			newFile.delete();
		}
		FileHandler.createFile(newFile);
		
		try
		{
			TextFileHandler fileHandler = new TextFileHandler(newFile);
			this.initFileProxy.writeOut(fileHandler);
		}
		catch(FileNotFoundException fnfe)
		{
			EcosystemContext.logger.error(fnfe + " : " + (new File(this.getCurrentWorkingDir().getAbsolutePath(), fileName).getAbsolutePath()) );
		}	
	}
	
	/**
	 * Overwrites the given BiomeBGC restart input file with the contents of the RestartFileProxy.
	 * @param Name of BiomeBGC initialization file (a text file).
	 */
	public void writeRestartInputProxyToFile(String fileName)
	{
		File newFile = new File(this.getCurrentWorkingDir().getAbsolutePath(), fileName);
		
		if(newFile.exists())
		{
			newFile.delete();
		}
		FileHandler.createFile(newFile);
		
		try
		{
			BinaryFileHandler fileHandler = new BinaryFileHandler(new File(this.getCurrentWorkingDir().getAbsolutePath(), fileName), GlobalConstants.BGC_NBR_BYTES_IN_RESTART);
			this.restartInputFileProxy.writeOut(fileHandler);
		}
		catch(FileNotFoundException fnfe)
		{
			EcosystemContext.logger.error(fnfe + " : " + (new File(this.getCurrentWorkingDir().getAbsolutePath(), fileName).getAbsolutePath()) );
		}
	}
	
	/**
	 * Overwrites the given BiomeBGC restart input file with the contents of the RestartFileProxy.
	 * @param Name of BiomeBGC initialization file (a text file).
	 */
	public void writeRestartOutputProxyToFile(String fileName)
	{
		File newFile = new File(this.getCurrentWorkingDir().getAbsolutePath(), fileName);
		
		if(newFile.exists())
		{
			newFile.delete();
		}
		FileHandler.createFile(newFile);
		
		try
		{
			BinaryFileHandler fileHandler = new BinaryFileHandler(new File(this.getCurrentWorkingDir().getAbsolutePath(), fileName), GlobalConstants.BGC_NBR_BYTES_IN_RESTART);
			this.restartOutputFileProxy.writeOut(fileHandler);
		}
		catch(FileNotFoundException fnfe)
		{
			EcosystemContext.logger.error(fnfe + " : " + (new File(this.getCurrentWorkingDir().getAbsolutePath(), fileName).getAbsolutePath()) );
		}
	}
	
	/**
	 * Overwrites the given BiomeBGC annual output file with the contents of the AnnualOutputFileProxy.
	 * @param Name of BiomeBGC initialization file (a text file).
	 */
	public void writeAnnualOutputProxyToFile(String fileName)
	{
		File newFile = new File(this.getCurrentWorkingDir().getAbsolutePath(), fileName);
		
		if(newFile.exists())
		{
			newFile.delete();
		}
		FileHandler.createFile(newFile);
		
		try
		{
			File outputFile = new File(this.getCurrentWorkingDir().getAbsolutePath(), fileName);
			// the type of values in output files is four-byte float
			//int nbrBytesInOutput = (int)outputFile.length()/4;
			//int nbrBytesInOutput = (int)outputFile.length();
			//int nbrBytesInOutput = 144;
			//BinaryFileHandler fileHandler = new BinaryFileHandler(outputFile, nbrBytesInOutput);
			BinaryFileHandler fileHandler = new BinaryFileHandler(new File(this.getCurrentWorkingDir().getAbsolutePath(), fileName), GlobalConstants.BGC_NBR_BYTES_IN_ANNUALOUTPUT);
			this.annualOutputFileProxy.writeOut(fileHandler);
		}
		catch(FileNotFoundException fnfe)
		{
			EcosystemContext.logger.error(fnfe + " : " + (new File(this.getCurrentWorkingDir().getAbsolutePath(), fileName).getAbsolutePath()) );
		}
	}
	
//--------------------
	/*
	public boolean cleanup()
	{
		/** TOOO: Rename instead??? **/
		/** TODO: Hardcoded - fix this later **/
		//int removeTick = (ModelContext.getCurrentTick()-1);
		//return FileHandler.delete( this.pathToRootDir+"tick-"+removeTick );
	//}

	
	/**
	 * Rename this Landcover directory to the given LandcoverType name.
	 * @param LandcoverType
	 * @return true if the directory was successfully renamed, false otherwise. 
	 */
	public boolean renameLandcoverDir(LandcoverType type)
	{
		File newDirName = new File(this.currentWorkingDir.getParent() + "/" + type);
		return this.currentWorkingDir.renameTo(newDirName);
	}
//--------------------	
	
	
	

//---------------------
// Accessors
		
	public String getPathToRootDir()
	{
		return pathToRootDir;
	}

	public void setPathToRootDir(String pathToRootDir)
	{
		this.pathToRootDir = pathToRootDir;
	}

	public File getCurrentWorkingDir()
	{
		return currentWorkingDir;
	}

	public InitFileProxy getInitFileProxy()
	{
		return initFileProxy;
	}

	public void setInitFileProxy(InitFileProxy initFileProxy)
	{
		this.initFileProxy = initFileProxy;
	}

	public RestartFileProxy getRestartInputFileProxy()
	{
		return restartInputFileProxy;
	}

	public void setRestartInputFileProxy(RestartFileProxy restartInputFileProxy)
	{
		this.restartInputFileProxy = restartInputFileProxy;
	}

	public RestartFileProxy getRestartOutputFileProxy()
	{
		return restartOutputFileProxy;
	}

	public void setRestartOutputFileProxy(RestartFileProxy restartOutputFileProxy)
	{
		this.restartOutputFileProxy = restartOutputFileProxy;
	}

	public AnnualOutputFileProxy getAnnualOutputFileProxy()
	{
		return annualOutputFileProxy;
	}

	public void setAnnualOutputFileProxy(AnnualOutputFileProxy annualOutputFileProxy)
	{
		this.annualOutputFileProxy = annualOutputFileProxy;
	}

}
