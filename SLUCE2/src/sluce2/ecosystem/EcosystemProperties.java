package sluce2.ecosystem;

public abstract class EcosystemProperties
{
	protected String xmlPathname;
	
	public EcosystemProperties(String xmlPathname)
	{
		this.xmlPathname = xmlPathname;
	}
	
	public abstract void loadProperties();
	
	public String toString()
	{
		return xmlPathname;
	}
}
