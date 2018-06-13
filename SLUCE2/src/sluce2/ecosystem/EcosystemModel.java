package sluce2.ecosystem;

import java.util.List;
import java.util.Map;

import sluce2.landscape.LandcoverType;
import sluce2.landscape.component.LandUnit;
import sluce2.utility.Coord;

import repast.simphony.util.collections.Pair;

/**
 * Defines and runs the ecosystem model.
 * 
 * @author Meghan Hutchins, Shipeng Sun
 * @date 02.01.2011
 */
public class EcosystemModel
{
	/** 
	 * modelContents my a series of nested EcosystemComponents
	 * @see GoF Decorator Pattern
	 *  **/
	private EcosystemComponent modelContents;

	/**
	 * Creates an EcosystemModel with the given modelContents.
	 * @param contents
	 */
	public EcosystemModel(EcosystemComponent modelContents)
	{
		this.modelContents = modelContents;
	}
	
	/**
	 * Updates the landcover in this ecosystem
	 * @param changeList
	 */
	public void updateLandcover(Map<Integer, Pair<LandcoverType, LandcoverType>> changeMap)
	{
		modelContents.updateLandcover(changeMap);
	}
	
	/**
	 * Run the Ecosystem Model
	 */
	public void run()
	{
		modelContents.run();
	}
	
	/**
	 * Run the Ecosystem Model with the given LandUnit
	 */
	public void run(LandUnit landUnit)
	{
		modelContents.setLandUnit(landUnit);
		modelContents.run();
	}
		
	@Override
	public String toString()
	{
		return this.getModelContents().toString();
	}
	
//--------------------------
// Accessors	

	public EcosystemComponent getModelContents()
	{
		return modelContents;
	}

	public void setModelContents(EcosystemComponent modelContents)
	{
		this.modelContents = modelContents;
	}

}
