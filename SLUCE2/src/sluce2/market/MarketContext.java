package sluce2.market;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import sluce2.GlobalConstants;
import sluce2.Model;
import sluce2.ModelComponent;
import sluce2.ModelContext;
import sluce2.agent.AgentContext;
import sluce2.agent.Developer;
import sluce2.agent.LandBroker;
import sluce2.agent.RuralLandOwner;
import sluce2.landscape.LanduseType;
import sluce2.landscape.component.Parcel;
import sluce2.management.ManagementContext;
import sluce2.management.strategy.ManagementStrategyCreator;
import sluce2.management.strategy.ManagementStrategyType;
import sluce2.utility.LogHelper;
import sluce2.agent.ResidentHousehold;
import sluce2.ecosystem.EcosystemContext;

/**
 * The MarketContext encapsulates the market behaviors of the model.
 * <p>
 * There is only one instance of the MarketContext at all times. 
 * 
 * @author Meghan Hutchins
 * @date 09/16/2010
 */
public class MarketContext extends ModelComponent
{
    /** Log4j Logger used for debugging and code tracking **/
    protected final static Logger logger;
    
    /** The single instance of the CarveContext in the model **/
    private static MarketContext instance;
    
    static
    {
    	logger = LogHelper.getConsoleLogger(MarketContext.class.getName());
    	logger.setLevel(GlobalConstants.MARKETCONTEXT_LOGLEVEL);
    }
    
    /**
     * Default Constructor
     * Initializes Context.
     */
    private MarketContext()
    {
    	super(GlobalConstants.MARKETCONTEXT); // must match name in model.score
    }
    
    /**
     * @return the instance of MarketContext 
     */
    public static MarketContext createInstance()
    {
		if(instance == null)
		{
		    instance = new MarketContext();
		}
		return instance;
    }
    
    /**
     * Context step method.
     * @return void
     */
    public void step() 
    {
    	long start = System.currentTimeMillis();
		MarketContext.logger.debug("--- MarketComponent takes a step : tells agents to buy/sell land. ---");
		
		/*
		 * FRAMER DOES ITS MARKET THING
		 */
		Developer developer = AgentContext.getInstance().getDeveloperAgents().get(0);
		
		/** Hack so that we can see undeveloped land in first year. **/
		if(ModelContext.getCurrentTick() > 0)
		{
			
			for( RuralLandOwner farmer : AgentContext.getInstance().getRuralLandOwnerAgents() )
			{
				if(farmer.ownsParcels())
				{
					MarketContext.logger.debug(farmer + " participates in market. Sells " + farmer.getParcelIDList().get(0) + " to " + developer);
					farmer.sellParcel(farmer.getParcelIDList().get(0), developer, 0.0);
				}
			}
		}
		/*
		 * DEVELOPER DOES ITS MARKET THING
		 */
		if(developer.ownsParcels())
    	{
			List<Integer> currentParcelList = new ArrayList<Integer>(developer.getParcelIDList());
			
			int residentIDIndex = 3;
			for(Integer parcelID : currentParcelList)
    		{
				Parcel parcel = (Model.getInstance().getLandscapeComponent().getParcel(parcelID));
				
				if( LanduseType.DEVELOPED.equals(parcel.getLanduseType()) && !LandBroker.instance().isUpForSale(parcel.getId()) )
    			{
					MarketContext.logger.debug(developer + " participates in market. Sells " + parcel + " to " + (ResidentHousehold)AgentContext.getInstance().getAgent(residentIDIndex));
					developer.sellParcel(parcel.getId(), (ResidentHousehold)AgentContext.getInstance().getAgent(residentIDIndex), 0.0);
					// We'll also set the resident's management strategy here.
    			}
    			residentIDIndex++;
    		}
			
			/** SETTING MANAGEMENT STRATEGIES : HARD CODED HACK **/
			ResidentHousehold resident3 = ((ResidentHousehold)AgentContext.getInstance().getAgent(3));
			if(resident3.ownsParcels())
			{
				resident3.setMgmtStrategy(ManagementStrategyCreator.createStrategyProduct(ManagementStrategyType.IEMSS_1, resident3.getParcelIDList().get(0) ));
				ManagementContext.logger.debug(resident3 + " set to mgmtStrategy: " + ManagementStrategyType.IEMSS_1);
			}
			
			ResidentHousehold resident4 = ((ResidentHousehold)AgentContext.getInstance().getAgent(4));
			if(resident3.ownsParcels())
			{
				resident4.setMgmtStrategy(ManagementStrategyCreator.createStrategyProduct(ManagementStrategyType.IEMSS_2, resident4.getParcelIDList().get(0) ));
				ManagementContext.logger.debug(resident4 + " set to mgmtStrategy: " + ManagementStrategyType.IEMSS_2);
			}
			
			ResidentHousehold resident5 = ((ResidentHousehold)AgentContext.getInstance().getAgent(5));
			if(resident3.ownsParcels())
			{
				resident5.setMgmtStrategy(ManagementStrategyCreator.createStrategyProduct(ManagementStrategyType.IEMSS_3, resident5.getParcelIDList().get(0) ));
				ManagementContext.logger.debug(resident5 + " set to mgmtStrategy: " + ManagementStrategyType.IEMSS_3);
			}
			
			ResidentHousehold resident6 = ((ResidentHousehold)AgentContext.getInstance().getAgent(6));
			if(resident3.ownsParcels())
			{
				resident6.setMgmtStrategy(ManagementStrategyCreator.createStrategyProduct(ManagementStrategyType.IEMSS_4, resident6.getParcelIDList().get(0) ));
				ManagementContext.logger.debug(resident6 + " set to mgmtStrategy: " + ManagementStrategyType.IEMSS_4);
			}
    	}
		
		MarketContext.logger.debug("MarketComponent ran for " + (System.currentTimeMillis() - start) + " milliseconds.");
       
    }
    
    @Override
    public String toString()
    {
    	return this.getClass().getName();
    }

//----------------------
// Accessors
    
	public static MarketContext getInstance()
	{
		return instance;
	}

	public static void setInstance(MarketContext instance)
	{
		MarketContext.instance = instance;
	}

	@Override
	public void initialize()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy()
	{
		// TODO Auto-generated method stub
		
	}
 
}
