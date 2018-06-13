package sluce2.ecosystem;

/**
 * The EcosystemDecorator decorates the EcosystemComponent it references (i.e. modelComponent)
 *  by defining a default implementation of the EcosystemComponent's run() operation 
 * that passes the request onto the modelComponent. 
 * 
 * @See: Decorator Pattern in: Gamma, Erich; Richard Helm, Ralph Johnson, and John Vlissides (1995). Design Patterns: Elements of Reusable Object-Oriented Software. Addison-Wesley. ISBN 0-201-63361-2.
 * 
 * @author Meghan Hutchins
 * @date 02.01.2011
 */
public abstract class EcosystemDecorator extends EcosystemComponent
{
	/** The EcosystemComponent that is decorated. **/ 
	protected EcosystemComponent modelComponent;
	
	/**
	 * @param modelComponent
	 */
	public EcosystemDecorator(EcosystemComponent modelComponent)
	{
		this.modelComponent = modelComponent;
	}
	
}
