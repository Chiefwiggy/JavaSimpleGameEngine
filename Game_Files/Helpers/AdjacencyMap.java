package Game_Files.Helpers;

import java.util.ArrayList;
import java.util.HashMap;

public class AdjacencyMap<K, T> {

    public AdjacencyMap() {
        adjacencyMap = new HashMap<>();
        keyList = new ArrayList<>();
    }

    private final HashMap<K, ArrayList<T>> adjacencyMap;
    private final ArrayList<K> keyList;

    public void Add(K key, T obj) {
        ArrayList<T> currentList = adjacencyMap.get(key);
        if (currentList == null) {
            currentList = new ArrayList<>();
            adjacencyMap.put(key, currentList);
            keyList.add(key);
        }
        currentList.add(obj);
    }

    public void Remove(K key, T obj) {
        ArrayList<T> currentList = adjacencyMap.get(key);
        currentList.remove(obj);
        if (currentList.isEmpty()) {
            adjacencyMap.remove(key);
            keyList.remove(key);
        }
    }

    public ArrayList<T> GetList(K key) {
        return adjacencyMap.get(key);
    }

    public ArrayList<K> GetKeys() {
        return keyList;
    }
}
