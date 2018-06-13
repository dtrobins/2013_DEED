package sluce2.agent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Level;

import bsh.This;

import repast.simphony.annotate.AgentAnnot;
import sluce2.landscape.component.Parcel;
import sluce2.market.SaleRecord;


/**
 * Represents the LandBroker agent. 
 * 
 * @author Meghan Hutchins
 * @date 09/16/2010
 */
@AgentAnnot(displayName = "LandBroker")
public class LandBroker extends SimpleAgent
{
	private static LandBroker instance; 
	
	private List<SaleRecord> forSaleList; 
    
    /**
     * 
     */
    private LandBroker()
    {
    	forSaleList = new ArrayList<SaleRecord>();
    	AgentContext.logger.debug("LandBroker agent created (ID:" + this.getId() + ")");
    }
    
    /**
     * @return the instance of LandBroker
     */
    public static LandBroker instance()
    {
    	if(instance == null)
    	{
    		instance = new LandBroker();
    	}
    	return instance;
    }
    
    /**
     * LandBroker step method.
     * @return void
     */
    @Override
    public void step() 
    {
    	AgentContext.logger.debug(this + " takes a step : does nothing.");
    	
    	if(AgentContext.logger.isDebugEnabled())
    	{
    		this.printListing();
    	}
    }
    
    /**
     * Method to put a parcel up for sale using a sale record.
     * @param saleRecord
     * @return true if the saleRecord was successfully listed, false otherwise.
     */
    public boolean list(SaleRecord saleRecord)
    {
    	return forSaleList.add(saleRecord);
    }
    
    /** 
     * @param parcel
     * @return true if this parcel is up for sale, false otherwise.
     */
    public boolean isUpForSale(int parcelID)
    {
    	boolean isUpForSale = false;
    	Iterator<SaleRecord> iter = forSaleList.iterator();
    	
    	while(!isUpForSale && iter.hasNext())
    	{
    		isUpForSale = (iter.next()).contains(parcelID);
    	}

    	return isUpForSale;
    }

	
    /**
     * Method for debugging only.
     */
    public void printListing()
    {
    	//System.out.println("LandBroker ForSale Listing:");
    	for(SaleRecord saleRecord : this.forSaleList)
    	{
    		//System.out.println(saleRecord);
    	}
    }
    
    
//-----------------
// Accessors    
  

    public List<SaleRecord> getForSaleList()
	{
		return forSaleList;
	}

	public void setForSaleList(List<SaleRecord> forSaleList)
	{
		this.forSaleList = forSaleList;
	}
    
    
    
}