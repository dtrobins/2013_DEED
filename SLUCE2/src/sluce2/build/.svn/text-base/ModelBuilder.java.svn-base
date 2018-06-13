package sluce2.build;

import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;


import sluce2.Model;


/**
 * The ModelBuilder specifies an abstract interface for creating parts of a Model object.
 * The empty, non-abstract methods are intended to allow subclasses to have the option to implement these methods.
 * If subclasses choose not to implement these methods, then there is no action.
 * 
 * @See: Builder Pattern in: Gamma, Erich; Richard Helm, Ralph Johnson, and John Vlissides (1995). Design Patterns: Elements of Reusable Object-Oriented Software. Addison-Wesley. ISBN 0-201-63361-2.
 *
 * @author Meghan Hutchins
 * @date 02.04.2011
 */
public abstract class ModelBuilder
{
	/** The resources the model is built from **/
	protected ResourceBundle modelResources;
	
	/**
	 * Builds a Model from the given properties file and adds the model to the given Repast Simphony context.
	 * @param pathToModelProperties
	 * @param repastContext
	 * @throws MissingResourceException
	 */
	public ModelBuilder(String pathToModelProperties) throws MissingResourceException
	{
		this.modelResources = PropertyResourceBundle.getBundle(pathToModelProperties);
	}
	
	public void buildLandscapeComponent(){}
	public void buildEcosystemComponent(){}
	public void buildAgentComponent(){}
	public void buildManagementComponent(){}
	public void buildMarketComponent(){}
	public void buildInstitutionComponent(){}

	/**
	 * Builds all model components in a particular order.
     * <p>
     * Model components will also step in this order.
     * A subclass should override this method if model components 
     * should execute in a different order or if some model components 
     * are not necessary for a particular Model implementation.
     * 
     * @return void
	 */
	public void buildModelComponents()
	{
		this.buildEcosystemComponent(); // Must come before LandscapeComponent when using Observers for model consistency.
		this.buildMarketComponent();
		this.buildManagementComponent();
		this.buildInstitutionComponent();
		this.buildLandscapeComponent(); // Must come before AgentComponent
		this.buildAgentComponent();
	}
	
	public Model getModel()
	{ 
		return null;
	}

}
