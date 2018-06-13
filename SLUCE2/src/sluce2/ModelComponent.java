package sluce2;

import repast.simphony.context.DefaultContext;

public abstract class ModelComponent extends DefaultContext<Object> 
{
	public ModelComponent(String componentName)
	{
		super(componentName);
	}
	
	public abstract void initialize();
	public abstract void step();
	public abstract void destroy();
}

