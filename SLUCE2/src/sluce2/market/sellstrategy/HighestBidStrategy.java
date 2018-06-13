package sluce2.market.sellstrategy;


import java.util.List;

import sluce2.market.Bid;

/**
 * This concrete SellStrategeyProduct chooses the highest bid price from the candidate bid list. 
 * In the case of a tie, it randomly chooses among the highest bidders.
 * 
 * @author Meghan Hutchins
 *
 */
public class HighestBidStrategy extends SellStrategyProduct
{
    
    /**
     * 
     */
    public HighestBidStrategy()
    {
	
    } 
    
    /**
     * @return the winning Bid based on this SellStrategyProduct's algorithm.
     */
    /* NB:  Had some issues with generics here - possibly a bug in Eclipse Helia? 
     * See: http://stackoverflow.com/questions/239645/overriding-a-method-with-generic-parameters-in-java
     * */
    @Override
    public Bid determineWinner(@SuppressWarnings("rawtypes") List candidateBidList)
    {
	Bid winningBid = null;
	
	for(int i=0; i<candidateBidList.size(); i++)
	{
	    if(candidateBidList.get(i) instanceof Bid)
	    {
		Bid candidateBid = (Bid)candidateBidList.get(i);
		if(winningBid == null)
		{
		    winningBid = candidateBid;
		}
		else if(winningBid.getBidPrice() < candidateBid.getBidPrice())
		{
		    winningBid = candidateBid;
		}
	    }
	}
	
	return winningBid;
    }
    
}
