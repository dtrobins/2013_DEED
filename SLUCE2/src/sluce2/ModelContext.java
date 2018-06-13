package sluce2;

import org.apache.log4j.Logger;

import repast.simphony.context.Context;
import repast.simphony.dataLoader.ContextBuilder;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.schedule.ISchedule;
import repast.simphony.engine.schedule.ScheduleParameters;
import repast.simphony.parameter.Parameter;
import repast.simphony.parameter.Parameters;
import sluce2.build.ModelBuilder;
import sluce2.build.SampleModelBuilder;
import sluce2.utility.LogHelper;


/**
 * The ContextCreator is the core of the model. 
 * <p>
 * The ContextCreator initializes the model, provides access to all model components (through sub-contexts),
 * schedules when the model takes a step, maintains access to the current tick, 
 * and keeps track of model start and end times. 
 * <p>
 * There is only one instance of the ContextCreator at all times. 
 * 
 * @author Meghan Hutchins
 * @date 09/16/2010
 */
public class ModelContext implements ContextBuilder<Object>
{
    /** Log4j Logger used for debugging and code tracking **/
    public final static Logger logger;
    
	/** Provides access to values in parameters.xml file **/
    public static Parameters params; 
    
    /** The instance of the Model **/
    public static Model model; 
    
    static
    {
    	logger = LogHelper.getConsoleLogger(ModelContext.class.getName());
    	logger.setLevel(GlobalConstants.MAINCONTEXT_LOGLEVEL);
    } 
    
    
    /** Reference to the main context */
    public static Context<Object> mainContext;
    /** Model start time in milliseconds */
    private static long startTime;
    /** Model end time in milliseconds */
    private static long endTime;
    
    //--------------------------
    // Model Parameters
    //--------------------------
    /** Specific random seed **/
    //private static int param_randomSeed;
    /** If true, use specific random seed, otherwise use system-based random seed **/
    //private static boolean param_useThisRandomSeed;
    /** Number of ticks the model will run (batch-mode only) **/
    //private static double param_runLength;
    /** Number of parcels in the x-dimension of the subdivision grid */
    //private static int param_xdim;
    /** Number of parcels in the y-dimension of the subdivision grid */
    //private static int param_ydim; 
    
   
//--------------------------
// Initialization     
    
    /**
     * Builds and returns the main context.
     * @return Context<Object>
     */
    @SuppressWarnings("unchecked")
    public Context<Object> build(Context<Object> context) 
    { 
    	ModelContext.mainContext = context;
    	params = RunEnvironment.getInstance().getParameters();
	
    	/** 
    	 * TODO: Dynamically determine which type of model to build from user input.
    	 */
    	ModelBuilder builder = new SampleModelBuilder(GlobalConstants.SLUCE2_RESOURCES);
    	builder.buildModelComponents();
    	model = builder.getModel();
    	model.addToRepastContext(context);
    	
    
		/* ********************************************************************
		 *  Schedule what happens at: start of run | each tick | end of run
		 **********************************************************************/
		ISchedule schedule = RunEnvironment.getInstance().getCurrentSchedule();
		// Start of run
		ScheduleParameters start = ScheduleParameters.createOneTime(0.0, ScheduleParameters.FIRST_PRIORITY);
		schedule.schedule(start, this, "start");
		// For each tick
		ScheduleParameters params = ScheduleParameters.createRepeating(0, 1);
		schedule.schedule(params, this, "step");
		// End of run
		ScheduleParameters stop = ScheduleParameters.createAtEnd(ScheduleParameters.LAST_PRIORITY);
		schedule.schedule(stop, this, "end");
		
		/* ********************************************************************
		 *  If running in batch mode, tell the model when to end each run.
		 **********************************************************************/
		if (RunEnvironment.getInstance().isBatch())
		{
			double endAt = ((ModelContext.getParameterDouble(ModelParam.runLength)-1));
			RunEnvironment.getInstance().endAt(endAt);
		}
	
		ModelContext.logger.debug("-------------------------------------------");
		ModelContext.logger.debug(" Model Initialization Complete ");
		ModelContext.logger.debug("-------------------------------------------");
		return context;
    }
    
    
    /**
     * Global step method.
     * @return void
     */
    public void step() 
    {
		ModelContext.logger.debug("- - - - - - - - - - - - - -");
		ModelContext.logger.debug("Begin Tick:" + ModelContext.getCurrentTick() + " (year: " + ModelContext.getCurrentYear() + ")");
		
		ModelContext.model.step();
    }
    
//--------------------------
// Basic Model Logistics  
    
    /**
     * Executes at start of model run.
     * @return double
     */
    public static void start() 
    {
		startTime = System.currentTimeMillis();
		
		ModelContext.logger.info("===========================================");
		ModelContext.logger.info(" MODEL RUN STARTED AT TICK: " + ModelContext.getCurrentTick() + " (YEAR: " + ModelContext.getCurrentYear() + ")");
		ModelContext.logger.info("===========================================");
    }
    
    /**
     * Executes at end of model run.
     * @return double
     */
    public static void end() 
    {		
    	endTime = System.currentTimeMillis();
    	
		ModelContext.logger.info("===========================================");;
		ModelContext.logger.info("== MODEL RUN ENDED AT TICK: " + ModelContext.getCurrentTick() + " (YEAR: " + ModelContext.getCurrentYear() + ")");
		ModelContext.logger.info("== Model took: " + (endTime - startTime) + "ms to run");
		ModelContext.logger.info("===========================================");
		
		System.exit(0);
    }
    
    /**
     * @return true if the Repast Runtime Environment is available, false otherwise.
     */
    public static boolean hasRepastRuntime()
    {
  	  return (RunEnvironment.getInstance() != null);
    }
    
    /**
     * @return the current tick number
     */
    public static int getCurrentTick()
    {
		double tick = GlobalConstants.DEFAULT_TICK;
		try
		{
		    tick = RunEnvironment.getInstance().getCurrentSchedule().getTickCount();
		}
		catch(NullPointerException npe)
		{
		    // NB: This may occur during JUnit testing when we don't have any ticks.
		    ModelContext.logger.warn("Tick Count is null, using -99");
		}
		return (int)tick;
    }
    
    /**
     * @return the year represented by the current tick
     * TODO: this code looks a bit off - did I do this for a good reason, maybe to help with Unit tests?
     */
    public static int getCurrentYear()
    {
    	int year = GlobalConstants.DEFAULT_YEAR;
    	if(ModelContext.getCurrentTick() != GlobalConstants.DEFAULT_TICK)
    	{
    		ModelContext.getCurrentTick();
    		year = ( (ModelContext.getParameterInteger(ModelParam.startYear).intValue()) + ModelContext.getCurrentTick() );
    	}
    	return year;
    }

    
	/**
	 * Returns the Integer associated with this parameter name in the parameters.xml file.
	 * @see: sluce2.rs/parameters.xml
	 * @param name
	 * @return Integer
	 */
	public static Integer getParameterInteger(ModelParam name)
	{
		Integer value = null;
		try
		{
			value = Integer.valueOf( ModelContext.params.getValueAsString(name.toString().trim()) );
			assert value!=null : "Parameter value for: " + name + " returns null";
		}
		catch(NumberFormatException nfe)
		{
			ModelContext.getLogger().error("ModelParam: " + name + " : " + nfe);
		}
		return value;
	}
	
	/**
	 * Returns the Double associated with this parameter name in the parameters.xml file.
	 * @see: sluce2.rs/parameters.xml
	 * @param name
	 * @return Double
	 */
	public static Double getParameterDouble(ModelParam name)
	{
		Double value = null;
		try
		{
			value = Double.valueOf( ModelContext.params.getValueAsString(name.toString().trim()) );
			assert value!=null : "Parameter value for: " + name + " returns null";
		}
		catch(NumberFormatException nfe)
		{
			ModelContext.getLogger().error("ModelParam: " + name + " : " + nfe);
		}
		return value;
	}
	
	/**
	 * Returns the String associated with this parameter name in the parameters.xml file.
	 * @see: sluce2.rs/parameters.xml
	 * @param name
	 * @return String
	 */
	public static String getParameterString(ModelParam name)
	{
		String value = null;
		value = String.valueOf( ModelContext.params.getValueAsString(name.toString().trim()) );
		assert value!=null : "Parameter value for: " + name + " returns null";
		return value;
	}
	
    /**
     * Initializes model with user-defined parameters. 
     * @return void
     */
   // private void initWithEnvParams()
    //{
	/** TODO: Add additional params here... **/
	
	/*
	param_useThisRandomSeed = (Boolean)ContextCreator.getParamValue("useRandomSeed");
	param_randomSeed = (Integer)ContextCreator.getParamValue("randomSeed");
	// If using myRandomSeed, then set this seed in the RandomHelper
	if(param_useThisRandomSeed)
	{
		RandomHelper.setSeed(param_randomSeed);
	}
	*/
   // }
    
    /**
     * Returns the Object specified by the model parameter.
     * 
     * @return Object
     */
    /**
    public static Object getParamValue(String paramName)
    {
    	Object value = null;
		
    	try
    	{
    	    value = envParams.getValue(paramName);
    	}
    	catch(Exception e)
    	{
    	    Sluce2.logger.error("The parameter you specified (" + paramName + ") could not be found.");
    	}
    	
    	return value;
    }
    */

//--------------------------
// Accessors  

    public static Context<Object> getMainContext()
    {
        return mainContext;
    }
    
    public static long getStartTime()
    {
        return startTime;
    }

    public static void setStartTime(long startTime)
    {
        ModelContext.startTime = startTime;
    }

    public static long getEndTime()
    {
        return endTime;
    }

    public static void setEndTime(long endTime)
    {
        ModelContext.endTime = endTime;
    }

    /*
    @Parameter(usageName="randomSeed", displayName="Random Seed")
    public static int getParam_randomSeed()
    {
        return param_randomSeed;
    }

    public static void setParam_randomSeed(int param_randomSeed)
    {
        ModelContext.param_randomSeed = param_randomSeed;
    }

    @Parameter(usageName="useThisRandomSeed", displayName="Use the specified random seed", defaultValue="false")
    public static boolean isParam_useThisRandomSeed()
    {
        return param_useThisRandomSeed;
    }

    @Parameter(usageName="useThisRandomSeed", displayName="Use the specified random seed")
    public static void setParam_useThisRandomSeed(boolean param_useThisRandomSeed)
    {
        ModelContext.param_useThisRandomSeed = param_useThisRandomSeed;
    }


    public static double getParam_runLength()
    {
        return param_runLength;
    }


    public static void setParam_runLength(double param_runLength)
    {
        ModelContext.param_runLength = param_runLength;
    }

    public static int getParam_xdim()
    {
        return param_xdim;
    }

    public static void setParam_xdim(int param_xdim)
    {
        ModelContext.param_xdim = param_xdim;
    }

    public static int getParam_ydim()
    {
        return param_ydim;
    }

    public static void setParam_ydim(int param_ydim)
    {
        ModelContext.param_ydim = param_ydim;
    }
    */

    public static void setMainContext(Context<Object> mainContext)
    {
        ModelContext.mainContext = mainContext;
    }
    
    public static Logger getLogger()
	{
		return logger;
	}

	public static Model getModel()
	{
		return model;
	}

	public static void setModel(Model model)
	{
		ModelContext.model = model;
	}
  	
}