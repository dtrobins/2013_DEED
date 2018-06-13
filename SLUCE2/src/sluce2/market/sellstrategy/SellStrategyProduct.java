package sluce2.market.sellstrategy;

import java.util.List;

import sluce2.market.Bid;

/**
 * An abstract SellStrategyProduct contains a collection of Bids. 
 * Its up to the concrete SellStrategyProducts to determine which Bid "wins". 
 * 
 * @author Meghan Hutchins
 *
 */
public abstract class SellStrategyProduct
{      
    /**
     * @return the winning Bid based on the concrete strategy implementation.
     */
    /* NB:  Had some issues with generics here - possibly a bug in Eclipse Helia? 
     * See: http://stackoverflow.com/questions/239645/overriding-a-method-with-generic-parameters-in-java
     * */
    public abstract Bid determineWinner(List candidateBidList);

}
