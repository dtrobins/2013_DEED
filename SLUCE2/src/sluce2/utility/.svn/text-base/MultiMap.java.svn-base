package sluce2.utility;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An implementation of a Map that allows duplicate keys.
 * 
 * @author Meghan Hutchins
 * @date 02.13.2011
 *
 * @param <E>
 * @param <T>
 */
public class MultiMap<E, T>
{
	private Map<E, List<T>> multiMap; 
	
	/**
	 * 
	 */
	public MultiMap()
	{
		multiMap = new HashMap<E, List<T>>();
	}
	
	/**
	 * 
	 */
	public boolean put(E key, T value)
	{
		boolean success = false;
		List<T> valueList = null;
		if(multiMap.containsKey(key))
		{
			valueList = this.get(key);
			valueList.add(value);
			multiMap.put(key, valueList);
			success = true;
		}
		else
		{
			valueList = new ArrayList<T>();
			valueList.add(value);
			multiMap.put(key, valueList);
			success = true;
		}
		return success;
	}
	
	/**
	 * Removes the particular value associated with the given key.
	 * @return the value that was removed.
	 */
	public boolean remove(E key, T value)
	{
		boolean success = false;
		if(this.contains(key))
		{
			List<T> valueList = this.get(key);
			success = valueList.remove(value);
		}
		return success;
	}
	
	/**
	 * @return true if this MultiMap contains the given key, false otherwise.
	 */
	public boolean contains(E key)
	{
		return multiMap.containsKey(key);
	}
	
	/**
	 * @return true if this MultiMap contains the given key, false otherwise.
	 */
	public boolean contains(E key, T value)
	{
		boolean contains = false;
		
		if(this.contains(key))
		{
			contains = this.get(key).contains(value);
		}
		return contains;
	}
	
	/**
	 * @param key
	 * @return the List of values associated with the given key.
	 */
	public List<T> get(E key)
	{
		List<T> valueList = new ArrayList<T>();
		if(this.contains(key))
		{
			valueList = multiMap.get(key);
		}
		return valueList;
	}
	
	/**
	 * @return the set of keys in this MultiMap
	 */
	public Set<E> keySet()
	{
		return multiMap.keySet();
	}
	
	/**
	 * @return the collection of value lists in this MultiMap.
	 */
	public Collection<List<T>> values()
	{
		return multiMap.values();
	}
	
	/**
	 * @param key
	 * @return the number of values associated with the given key.
	 */
	public int nbrOfValues(E key)
	{
		int nbrOfValues = 0;
		if(this.contains(key))
		{
			nbrOfValues = this.get(key).size();
		}
		return nbrOfValues;
	}
	
	/**
	 * @return true if this MultiMap is empty, false otherwise.
	 */
	public boolean isEmpty()
	{
		return multiMap.isEmpty();
	}
	
	/**
	 * Removes all of the elements in this map.
	 * The MultiMap will be empty after this method is called.
	 */
	public void clear()
	{
		multiMap.clear();
	}
	
	/**
	 * 
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		
		for(E key : this.keySet())
		{
			sb.append(key + "(" + this.nbrOfValues(key) + ")  ");
		}
		return sb.toString();
	}
	
}

