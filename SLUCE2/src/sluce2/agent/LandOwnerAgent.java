package sluce2.agent;

import java.util.ArrayList;
import java.util.List;

import sluce2.landscape.component.Parcel;
import sluce2.market.buystrategy.BuyStrategyProduct;
import sluce2.market.sellstrategy.SellStrategyProduct;


/**
 * A LandOwnerAgent is a special agent that is able to buy, sell, own and manage land.
 * 
 * @author Meghan Hutchins
 * @date 12/10/2010
 */
public abstract class LandOwnerAgent extends SimpleAgent
{
    /** A LandOwnerAgent has a certain budget **/
    protected double budget; 
    
    /** A LandOwnerAgent keeps a list of parcel ids that it owns. **/ 
    protected List<Integer> parcelIDList;
    
    /** ? Not sure if we'll be using buy/sell strategies **/
    protected BuyStrategyProduct buyStrategy;
    
    /** ? Not sure if we'll be using buy/sell strategies **/
    protected SellStrategyProduct sellStrategy;
    
    
//-------------------------
//  Creation     
    
    /**
     * Creates a LandOwnerAgent with a budget of $0.00. 
     */
    public LandOwnerAgent()
    {
		this.budget = 0d; 
		parcelIDList = new ArrayList<Integer>();
    }
    
    /**
     * Creates a LandOwnerAgent with the given budget.
     */
    public LandOwnerAgent(double budget)
    {
		this.budget = budget; 
		this.parcelIDList = new ArrayList<Integer>();
    }
    
    /**
     * LandOwnerAgent step method.
     * @return void
     */
    @Override
    public void step() 
    {
    	// TODO: Step code here.
    }
    
    /**
     * @return true if this agent actually owns one or more Parcels, false otherwise.
     */
    public boolean ownsParcels()
    {
    	return ( null!=this.parcelIDList && !this.parcelIDList.isEmpty() );
    }
    
    /**
     * 
     */
    @Override
    public String toString()
    {
		StringBuffer sb = new StringBuffer(this.getClass().getSimpleName() + "-" + this.getId() + " budget:" + budget + " parcels:" );
		
		if(!this.parcelIDList.isEmpty())
		{
			for(Integer id : this.parcelIDList)
			{
				sb.append("["+id+"]");
			}
		}
		else
		{
			sb.append("[Owns zero parcels]");
		}
		return sb.toString();
    }

//-------------------------
//  Buy    
   
    /**
     * 
     */
    public void placeBid(Parcel parcel)
    {
	
    }
    
//-------------------------
//  Sell    
    
    /**
     * Currently In Use in MarketContext.step();
     */
    public boolean sellParcel(int parcelID, LandOwnerAgent buyer, double price)
    {
    	boolean success = false;
    	buyer.addParcelID(parcelID);
    	this.removeParcelID(parcelID);
    	success = (buyer.ownsParcel(parcelID))? true : false;
    	/*
    	 * Also: add price to this agent budget
    	 *       subtract price from buyer agent budget
    	 *       Do we need to notify any observers?
    	 */

    	return success;
    }
    
    /**
     * 
     */
    public void determineWinner()
    {
	
    }
    
//-------------------------
//  Budget
    
    /**
     * @return true if the Agent's budget is greater-than or equal-to the given amount, false otherwise.
     */
    public boolean hasEnoughFunds(double amount)
    {
    	return (this.budget >= amount);
    }
    
    /**
     * Adds the given amount to the Agent's current budget.
     * @return new budget amount
     */
    public double incrementBudget(double amount)
    {
    	return budget+= amount;
    }
    
    /**
     * Subtracts the given amount from the Agent's current budget.
     * @return new budget amount
     */
    public double decrimentBudget(double amount)
    {
	if(amount >= budget)
	{
	    budget-= amount;
	}
	else
	{
	    AgentContext.logger.warn("Attempting to decriment budget of Agent (" + this.getId() + "). Agent does not have enough funds."); 
	}

	return budget;		
    }    
    
//-------------------------
//  Parcel Acquisition    

    /**
     * A LandOwnerAgent keeps a list of parcel ids that it owns. 
     * Adds a parcel id to this agent's list. 
     *
     * @param parcelId
     */
    public void buyParcelID(Integer parcelID, double price)
    {
		this.decrimentBudget(price);
		this.addParcelID(parcelID);
    }
    
    /**
     * A LandOwnerAgent keeps a list of parcel ids that it owns. 
     * Adds a parcel id to this agent's list. 
     *
     * @param parcelId
     */
    public void sellParcelID(Integer parcelID, double price)
    {
		this.incrementBudget(price);
		this.removeParcelID(parcelID);
    }
    
    /**
     * A LandOwnerAgent keeps a list of parcel IDs that it owns. 
     * Adds a parcel id to this agent's list.<p>
     * Agents are required to aquire parcels through purchase. 
     * @see buyParcel(Integer parcelID, Double price)
     *
     * @param parcelId
     */
    private void addParcelID(Integer parcelID)
    {
		if(!parcelIDList.contains(parcelID))
		{
		    parcelIDList.add(parcelID);
		}
		else
		{
		    AgentContext.logger.warn("Attempting to set parcel ID (" + parcelID + ") to Agent (" + this.getId() + ") who already owns it.");
		}
    }
    
    /**
     * A LandOwnerAgent keeps a list of parcel IDs that it owns. 
     * Adds a parcel id to this agent's list.<p>
     * Agents are required to aquire parcels through purchase. 
     * @see buyParcel(Integer parcelID, Double price)
     *
     * @param parcelId
     */
    protected boolean removeParcelID(Integer parcelID)
    {
    	boolean success = false;
    	
		if(parcelIDList.contains(parcelID))
		{
		    parcelIDList.remove(parcelID);
		    success = true;
		}
		else
		{
		    AgentContext.logger.warn("Attempting to remove parcel ID (" + parcelID + ") from Agent (" + this.getId() + ") who doesn't even own it.");
		}
		
		return success;
    }
    
    /**
     * @return true if this agent owns the given Parcel id, false otherwise.
     */
    public boolean ownsParcel(int parcelID)
    {
    	return this.getParcelIDList().contains(parcelID);
    }
       
//-------------------------
//  Management Behaviors    
    

    
//-------------------------
//  Accessors   

    public double getBudget()
    {
        return budget;
    }

    public void setBudget(double budget)
    {
        this.budget = budget;
    }

    public List<Integer> getParcelIDList()
    {
        return parcelIDList;
    }

    public void setParcelIDList(List<Integer> parcelIDList)
    {
        this.parcelIDList = parcelIDList;
    }

  
}