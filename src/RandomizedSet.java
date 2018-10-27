import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RandomizedSet {

	Map<Integer, Integer> map;
	List<Integer> list;
	Random random;
	
    /** Initialize your data structure here. */
    public RandomizedSet() {
        this.map = new HashMap<>();
        this.list = new ArrayList<>();
        this.random = new Random(System.currentTimeMillis());
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (map.containsKey(val)) return false;
        map.put(val, list.size());
        list.add(val);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
    	int pos = map.getOrDefault(val, -1);
        if (pos != -1) {
        	map.remove(val);
        	int next = list.get(list.size() - 1);
        	list.remove(list.size() - 1);
        	if (list.size() > 0 && pos < list.size()) {
        		list.set(pos, next);
        		map.put(next, pos);
        	}
        	return true;
        }
        return false;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        if (list.size() > 0) {
        	return list.get(random.nextInt(list.size()));
        }
        return -1;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
