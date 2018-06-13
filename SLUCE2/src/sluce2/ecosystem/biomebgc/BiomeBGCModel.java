package sluce2.ecosystem.biomebgc;

import java.io.File;
import java.util.Map;
import java.util.Map.Entry;

import repast.simphony.util.collections.Pair;
import sluce2.GlobalConstants;
import sluce2.ecosystem.EcosystemComponent;
import sluce2.ecosystem.EcosystemContext;
import sluce2.ecosystem.biomebgc.proxy.BiomeBGCProxyManager;
import sluce2.landscape.LandcoverType;
import sluce2.landscape.LandscapeContext;
import sluce2.utility.file.StreamGobbler;

/**
 * Defines an ecosystem model on which additional responsibilities can be attached.<p>
 * The ConcreteComponent of the Decorator pattern.
 *  
 * @See: Gamma, Erich; Richard Helm, Ralph Johnson, and John Vlissides (1995). Design Patterns: Elements of Reusable Object-Oriented Software. Addison-Wesley. ISBN 0-201-63361-2.
 * 
 * @author Meghan Hutchins, Shipeng Sun
 * @date 02.01.2011
 */
public class BiomeBGCModel extends EcosystemComponent
{	
	/**
	 * Creates a BiomeBGCModel having the given properties.
	 * @param properties
	 */
	public BiomeBGCModel(BiomeBGCProperties properties)
	{
		super.setProperties(properties);
	}
	
	/**
	 * Run this BiomeBGCModel in the given directory, using the given BiomeBGC Initilaization file.
	 * 
	 * @param directory of where this BiomeBGCModel will run (output will go here).
	 * @param the BiomeBGC Initialization file that this BiomeBGCModel should use to run.
	 */
	@Override
	public void run(String pathToRunDirectory, String pathToIniFile)
	{		
		try
    	{            
            String osName = System.getProperty(GlobalConstants.OS_NAME );
            String[] cmd = new String[3];
            if( osName.equalsIgnoreCase(GlobalConstants.WINDOWS_XP) || osName.equalsIgnoreCase(GlobalConstants.WINDOWS_7) || osName.equalsIgnoreCase(GlobalConstants.WINDOWS_VISTA))
            {
                cmd[0] = "cmd.exe" ;
            }
            else if( osName.equals(GlobalConstants.LINUX) || osName.equals(GlobalConstants.UNIX))
            {
                cmd[0] = GlobalConstants.BASH ;
            }
            
            File folder = new File(pathToRunDirectory);
            
            //System.out.println(pathToIniFile);
            String ndepFlag = "-n";
            String pathToNdepFile = "/home/meghan/Desktop/SLUCE2-Environment/SLUCE2-BGC-Share/co2/ndep.txt";
            ProcessBuilder procBuilder = new ProcessBuilder(((BiomeBGCProperties)properties).getBgcExecutable(), ndepFlag, pathToNdepFile, pathToIniFile);
            //ProcessBuilder procBuilder = new ProcessBuilder(((BiomeBGCProperties)properties).getBgcExecutable(), pathToIniFile);
            procBuilder.directory(folder);
            
            //EcosystemContext.logger.debug("~~~ Start BiomeBGC Output ~~~");
            Process proc = procBuilder.start();
            
            // any error message?
            StreamGobbler errorGobbler = new StreamGobbler(proc.getErrorStream(), "ERROR");   
            
            // any output?
            StreamGobbler outputGobbler = new StreamGobbler(proc.getInputStream(), "BiomeBGC-Output");
                
            // kick them off
            errorGobbler.start();
            outputGobbler.start();
            
            // any error???
            int exitVal = proc.waitFor();
            
            if(exitVal != 0)
            {
            	EcosystemContext.logger.error("BiomeBGC ExitValue: " + exitVal);
            }
            
            //EcosystemContext.logger.debug("~~~  End BiomeBGC Output  ~~~");
        } 
       	catch (Throwable t)
        {
            t.printStackTrace();
        }
	}
	
	/**
	 * Run this BiomeBGCModel with the default settings.
	 */
	@Override
	public void run()
	{
		String pathToRunDirectory = ((BiomeBGCProperties)properties).getBgcDefaultFileDirectory();
		String pathToIniFile = ((BiomeBGCProperties)properties).getIniFile().getFilepath();
		this.run(pathToRunDirectory, pathToIniFile);	
	}
	
	/**
	 * Updates the landcover according to the provided changeMap<p>
	 * The changeMap is a mapping from the LandUnit ID to a Pair of LandcoverTypes.
	 * The first being the previous LandcoverType, the second being the new LandcoverType.<p>
	 * When Landcover changes, the LandscapeComponent sends out a notification to all its observers. 
	 * This method will be called as a result of the notification.
	 */
	public void updateLandcover(Map<Integer, Pair<LandcoverType, LandcoverType>> changeMap)
	{				
		for(Entry<Integer, Pair<LandcoverType, LandcoverType>> entry : LandscapeContext.getInstance().getChangeMap().entrySet())
		{
			BiomeBGCFileManager.getInstance().updateLandcoverType(entry.getKey(), entry.getValue().getFirst(), entry.getValue().getSecond());
		}
	}
		
	/**
	 * 
	 */
	@Override
	public String toString()
	{
		return this.getClass().getName();
	}

}
