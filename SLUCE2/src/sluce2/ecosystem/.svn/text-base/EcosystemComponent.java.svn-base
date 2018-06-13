package sluce2.ecosystem;

import java.util.List;
import java.util.Map;

import repast.simphony.util.collections.Pair;
import sluce2.GlobalConstants;
import sluce2.Model;
import sluce2.ModelContext;
import sluce2.ecosystem.biomebgc.proxy.InitType;
import sluce2.ecosystem.biomebgc.proxy.RestartType;
import sluce2.landscape.LandcoverType;
import sluce2.landscape.LandscapeLayerType;
import sluce2.landscape.component.Cell;
import sluce2.landscape.component.LandUnit;
import sluce2.utility.Coord;

/**
 * An EcosystemComponent defines an interface for ecosystem model objects that can have responsibilities added to them dynamically.<p> 
 * An EcosystemComponent may be either a concrete ecosystem model class (e.g. BiomeBGCModel), 
 * or it may be a ModelDecorator (e.g.SimpleCellRunner or SimpleCarveRunner).<p>
 * 
 * The EcosystemComponent is akin to the "Component" class in the GoF Decorator pattern. 
 * Decorator offers a pay-as-you-go approach to adding responsibilities - such as 
 * running an ecosystem model at a variety of different scales. Instead of trying 
 * to support all foreseeable features, Decorator allows you to define a simple class 
 * and add functionality incrementally. In this way, the application doesn't pay for 
 * features it doesn't use. It is also easy to define new kinds of Decorators independently 
 * in the case of unforeseen extensions.
 * 
 * @See: Gamma, Erich; Richard Helm, Ralph Johnson, and John Vlissides (1995). Design Patterns: Elements of Reusable Object-Oriented Software. Addison-Wesley. ISBN 0-201-63361-2.
 * 
 * @author Meghan Hutchins
 * @date 02.01.2011
 */
public abstract class EcosystemComponent
{
	/** The EcosystemProperties this EcosystemComponent uses to run. **/
	protected EcosystemProperties properties;

	/** The LandUnit this EcosystemComponent acts on. **/
	protected LandUnit landUnit;
	
	/**
	 *	Generic run method implemented by concrete EcosystemComponents.
	 */
	public abstract void run();
	
	/**
	 * EcosystemComponents may choose to override a method that takes in 
	 * a specific run directory and a specific initialization file.
	 * @param pathToRunDirectory
	 * @param pathToIniFile
	 */
	public void run(String pathToRunDirectory, String pathToIniFile)
	{
		
	}
	
	/**
	 * Programmers may choose to override this method.
	 * This method may be called if abrupt landcover changes occur
	 * which need to be taken account of in the EcosystemContext.
	 * (ex: Developers develop a subdivision on farmland)
	 * @param changeList
	 */
	public void updateLandcover(Map<Integer, Pair<LandcoverType, LandcoverType>> changeMap){}

	
//--------------------
// Accessors
	
	public void setProperties(EcosystemProperties properties)
	{
		this.properties = properties;
	}
	
	public EcosystemProperties getProperties()
	{
		return properties;
	}

	public LandUnit getLandUnit()
	{
		return landUnit;
	}

	public void setLandUnit(LandUnit landUnit)
	{   
		this.landUnit = landUnit;
	}
	
}
