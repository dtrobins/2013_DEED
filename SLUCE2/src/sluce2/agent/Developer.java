package sluce2.agent;

import java.util.ArrayList;
import java.util.List;

import repast.simphony.annotate.AgentAnnot;
import sluce2.Model;
import sluce2.landscape.LandscapeLayerType;
import sluce2.landscape.LanduseType;
import sluce2.landscape.component.Parcel;
import sluce2.management.template.Template;
import sluce2.management.template.TemplateManager;
import sluce2.market.SaleRecord;
import sluce2.utility.Coord;


/**
 * Represents the Developer agent. 
 * 
 * @author Meghan Hutchins
 * @date 09/16/2010
 */
@AgentAnnot(displayName = "Developer")
public class Developer extends LandOwnerAgent
{
	/** TODO: Maybe not a List? **/
	private List<Template> templateList;
    
    /**
     * 
     */
    public Developer()
    {
    	AgentContext.logger.debug("Developer agent created (ID:" + this.getId() + ")");
    	templateList = new ArrayList<Template>();
    }
    
    /**
     * @return void
     */
    @Override
    public void step() 
    {
    	AgentContext.logger.debug(this + " takes a step : does nothing.");
    	
    	/** TODO: separate into management and market steps() **/
    	/*
    	AgentContext.logger.debug("Developer takes a step");
    	List<Integer> currentParcelList = new ArrayList<Integer>(this.getParcelIDList());
    	
    	if(this.ownsParcels())
    	{
    		// If farmland, then develop, otherwise sell.
    		int residentIDIndex = 3;
    		for(Integer parcelID : currentParcelList)
    		{
    			Parcel parcel = (Model.getInstance().getLandscapeComponent().getParcel(parcelID));
    	
    			if( LanduseType.AGRICULTURE.equals(parcel.getLanduseType()) )
    			{
    				AgentContext.logger.debug(parcel + " is Agro so I'll develop...");
    				/** TODO: This was a quick hack. **/
    	/*
    				Template iemss1 = TemplateManager.instance().getTemplate("IEMSS-1");
    				this.developSubddivision(iemss1, new Coord(0,0));
    			}
    			if( LanduseType.DEVELOPED.equals(parcel.getLanduseType()) && !LandBroker.instance().isUpForSale(parcel.getId()) )
    			{
    				AgentContext.logger.debug(this + " owns " + parcel + " and its Developed - so I'll sell it to " + AgentContext.getInstance().getAgent(residentIDIndex));
    				
    				AgentContext.logger.debug("sold? " + this.sellParcel(parcel.getId(), (ResidentHousehold)AgentContext.getInstance().getAgent(residentIDIndex), 0.0));
    				
    				/** TODO: This was a quick hack. **/
    				//LandBroker.instance().list(new SaleRecord(this, parcel.getId(), 100.00));
    	/*
    			}
    			
    			residentIDIndex++;
    		}
    		
    		
    	}
 		else
		{
			AgentContext.logger.debug(this + " does not own any parcels.");
		}
 		*/
    }
    
    /**
     * 
     * @param template
     * @return
     */
    public void developSubddivision(Template template, Coord nwCorner)
    {
    	this.removeParcelID((Integer)Model.getInstance().getLandscapeComponent().getCell(nwCorner).getValue(LandscapeLayerType.PARCEL));
    	Model.getInstance().getLandscapeComponent().applyTemplate(template, nwCorner);
    	
    	/** TODO: Hack for now - perhaps use some sort of observer to update the parcel ids here. **/
    	List<Integer> newParcelList = new ArrayList<Integer>();
    	newParcelList.add(1);
    	newParcelList.add(2);
    	newParcelList.add(3);
    	newParcelList.add(4);
    	this.setParcelIDList(newParcelList);
    }
    
}