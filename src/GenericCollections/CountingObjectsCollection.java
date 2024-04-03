package GenericCollections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CountingObjectsCollection<K> {
	private HashMap<K, Integer> map;

	public CountingObjectsCollection() {
		map = new HashMap<>();
	}

	public CountingObjectsCollection(CountingObjectsCollection<K> old) {
		// implement in one line of code
		map = new HashMap<>(old.map);
		// What goes inside the brackets is the map! Think a string of keys that point to our values!

	}

	public void insert(K obj) {

		// object is our key, to which a value is mapped to.
		// If this key has yet to be registered, the key should be mapped to 1
		//If the key has already been registered, the mapped value is returned with getOrDefault and then increased by 1
		map.put(obj, map.getOrDefault(obj, 0) + 1);
	}

	public int getObjectCount(K obj) {
		// You return the mapped value for the obj key, which is increased every time obj is added to the map -> COUNTER!
		// if obj has not yet been added to the "list", the defaultValue 0 is returned, since there is no mapped value
		return map.getOrDefault(obj, 0);
	}

	public int getTotalObjectCount() {

		int count = 0;
		for(K key : map.keySet()){
			count += map.get(key);
		}

		return count;

		/*
		* for(K value : map.values()){
		* 	count += value;
		* }
		*/
	}

	public List<K> getKeyList() {
		/*List<K> keys = new ArrayList<>();

		for(K key : map.keySet()){
			keys.add(key);
		}

		return keys;*/

		return new ArrayList<>(map.keySet());

		// ArrayList<>(Collection or Iterable) -> list is initialized with elements of this collection/iterable
		// Since Sets are a subclass of Collections, they also serve as a Collection
		// and may hence be used as one in order to initialize the list. So basically you say
		// "get me an arraylist with elements that correspond to this set, meaning a list with all my keys. Thanks"
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		CountingObjectsCollection<K> other = (CountingObjectsCollection<K>) obj;
		// Assure that both maps are equal without using map.equals
		return map.size() == other.map.size() && map.entrySet().containsAll(other.map.entrySet());
		//entrySet() returns a Set view of the mappings contained in this map. Since sets are Collections,
		// they can be iterated. Sets have a containsAll() method which checks if all elements
		// contained in another set are also contained in THIS set, basically saying if the other set is a subset or not
	}
}
