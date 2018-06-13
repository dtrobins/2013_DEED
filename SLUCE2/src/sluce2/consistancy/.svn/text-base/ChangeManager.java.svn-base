package sluce2.consistancy;

import sluce2.landscape.LandscapeContext;
import sluce2.utility.MultiMap;


public class ChangeManager
{
	/** The instance of ChangeManger **/
	private static ChangeManager instance;
	
	/** Maps a Subject to its Observer **/
	private MultiMap<Subject, Observer> changeMap;
	
	/**
	 * 
	 */
	private ChangeManager()
	{
		changeMap = new MultiMap<Subject, Observer>();
	}
	
	/**
	 * @return the ChangeManager instance.
	 */
	public static ChangeManager instance()
	{
		if(instance == null)
		{
			instance = new ChangeManager();
		}
		return instance;
	}
	
	/**
	 * Register the given Subject with the given Observer.
	 * @param subject
	 * @param observer
	 */
	public void register(Subject subject, Observer observer)
	{
		changeMap.put(subject, observer);
	}
	
	/**
	 * Unregister the given Subject with the given Observer.
	 * @param subject
	 * @param observer
	 */
	public void unregister(Subject subject, Observer observer)
	{
		changeMap.remove(subject, observer);
	}
	
	/**
	 * Notify the Observers registered with the given Subject
	 * @param subject
	 */
	public void notifyObservers(Subject subject)
	{
		for(Observer observer : changeMap.get(subject))
		{
			LandscapeContext.logger.debug(subject.getClass().getSimpleName() + " notified " + observer.getClass().getSimpleName() );
			observer.update(subject);
		}
	}

}
