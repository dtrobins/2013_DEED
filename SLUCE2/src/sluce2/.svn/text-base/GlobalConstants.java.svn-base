package sluce2;

import java.nio.ByteOrder;

import org.apache.log4j.Level;


/**
 * A simple class containing global constants.
 * 
 * @author Meghan Hutchins
 * @date 09/16/2010
 */
public class GlobalConstants
{
    // ** Log4j Levels for specific contexts [OFF | FATAL | ERROR | WARN | INFO | DEBUG | TRACE] ** //
    public static Level MAINCONTEXT_LOGLEVEL = Level.DEBUG; 
    public static Level LANDSCAPECONTEXT_LOGLEVEL = Level.DEBUG;
    public static Level ECOSYSTEMCONTEXT_LOGLEVEL = Level.DEBUG;
    public static Level AGENTCONTEXT_LOGLEVEL = Level.DEBUG;
    public static Level INSTITUTIONCONTEXT_LOGLEVEL = Level.DEBUG;
    public static Level MARKETCONTEXT_LOGLEVEL = Level.DEBUG;
    public static Level MANAGEMENTCONTEXT_LOGLEVEL = Level.DEBUG;
    
    // ** String representation of Contexts; must match name in model.score ** //
    public static String MAINCONTEXT = "MainContext";
    public static String AGENTCONTEXT = "AgentContext";
    public static String INSTITUTIONCONTEXT = "InstituteContext";
    public static String MARKETCONTEXT = "MarketContext";
    public static String MANAGEMENTCONTEXT = "ManagementContext";
    public static String LANDSCAPECONTEXT = "LandscapeContext";
    public static String CARVECONTEXT = "CarveContext";
    public static String ECOSYSTEMCONTEXT = "EcosystemContext";
        
    // ** Landscape Layer Group Name. See: sluce2.resources.landscape ** //
    public static String LANDSCAPE_LAYERGROUP_NAME = "LandscapeLayerGroup";
    
    // ** Path to Resource Files ** //
    public static String SLUCE2_RESOURCES = "sluce2.resources.sluce2";
    
    // ** Names of various xml descriptors. See: sluce2.properties file ** //
    public static String LANDSCAPE_DESCRIPTOR = "LANDSCAPE_DESCRIPTOR";
    
//public static String LANDSCAPE_DESCRIPTOR = "IEMSS_LANDSCAPE";	// "TEST_LANDSCAPE"; //
    public static String DEVELOPMENT_TEMPLATE_DESCRIPTOR = "DEVELOPMENT_TEMPLATE_DESCRIPTOR";
    public static String BIOMEBGC_DESCRIPTOR = "BIOME_BGC";
    public static String ECOSYSTEM_MODEL_DESCRIPTOR = "ECOSYSTEM_MODEL_DESCRIPTOR";
    
    // ** BiomeBGC descriptor property names ** //
    public static String BGC_EXECUTABLE = "bgcExecutable";    
    
    public static String BGC_SHARED_FILE_LOCATION = "bgcSharedFileLocation";    
    public static String BGC_TMP_LOCATION = "bgcTmpLocation";
    
    public static String BGC_INIT_FILENAME = "bgcInitFileName";
    public static String BGC_RESTART_INPUT_FILENAME = "bgcRestartInputFileName";
    public static String BGC_RESTART_OUTPUT_FILENAME = "bgcRestartOutputFileName";
    public static String BGC_ANNUAL_OUTPUT_FILENAME = "bgcAnnualOutputFileName";
    
    public static String BGC_IRRIGATION_NONE = "bgcDefaultMeteorologyFileName";
    public static String BGC_IRRIGATION_SCHEDULED = "bgcIrrigateOnScheduleMeteorologyFileName";
    public static String BGC_IRRIGATION_DRY = "bgcIrrigateWhenDryMeteorologyFileName";
    
    public static String BGC_TURFGRASS_FILENAME = "bgcTurfGrassEPCFileName";
    public static String BGC_DECIDUOUSFOREST_FILENAME = "bgcDeciduousForestEPCFileName";
    public static String BGC_SHRUB_FILENAME = "bgcShrubEPCFileName";
    public static String BGC_PRAIRIE_FILENAME = "bgcPrairieEPCFileName";
    
 // **  Number of lines BiomeBGC initialization file ** //
    public static int BGC_INI_FILE_LENGTH = 146;
    
    // **  Number of bytes being read in BiomeBGC Restart file ** //
    public static int BGC_NBR_BYTES_IN_RESTART = 146;
    public static int BGC_NBR_BYTES_IN_ANNUALOUTPUT = 144;
    
    // ** Byte order for reading and writing binary files (i.e. the BiomeBGC Restart file ** //
    public static ByteOrder BYTE_ORDER = ByteOrder.LITTLE_ENDIAN;
    public static String OS_NAME = "os.name";
    public static String WINDOWS_XP = "Windows XP";
    public static String WINDOWS_7 = "Windows 7";
    public static String WINDOWS_VISTA = "Windows Vista";
    public static String CMD_EXE = "cmd.exe";
    public static String LINUX = "Linux";
    public static String UNIX = "Unix";
    public static String BASH = "bash";
    
    // ** Names of various folder locations. See: sluce2.properties file ** //
    public static String LANDSCAPE_ARCHIVE_LOCATION = "LANDSCAPE_ARCHIVE_LOCATION";
    
    // ** BiomeBGC directories ** //
    public static String tickDirectoryPrefix = "tick-";
    public static String landUnitDirectoryPrefix = "lu.";
    public static String FILE_SEPARATOR = "/";
    
    // ** Default time vars **//
    public static int DEFAULT_TICK = -99;
    public static int DEFAULT_YEAR = -9999;
    
    // ** BiomeBGC file naming conventions ** //
    public static String CELL_RESTART_FILE_NAME = "_cell_out.endpoint";
    
    // ** Optional items to append to output filenames ** //
    public static boolean APPEND_TIMESTAMP_TO_FILENAME = true;
    public static boolean APPEND_TICK_TO_FILENAME = true;
    
    // ** Time-stamp formats ** //
    public static String TIMESTAMP_FORMAT_REPORT = "_yyyyMMdd-HH.mm.ss";
    public static String TIMESTAMP_FORMAT_RASTER = "_yyyyMMdd-HH.mm.ss";
    
    // ** Log message format ** // 
    public static String LOG4J_OUTPUT_PATTERN = "%5p %d{MM/dd/yy HH:mm} (%C:%L) - %m%n";
    
}