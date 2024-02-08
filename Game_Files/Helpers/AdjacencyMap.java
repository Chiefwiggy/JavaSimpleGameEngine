package Game_Files.Helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class AdjacencyMap<K, V>
{

    private final HashMap<K, ArrayList<V>> adjacencyMap;

    private final ArrayList<K> keyList;

    private final Random random = new Random();

    public AdjacencyMap()
    {
        adjacencyMap = new HashMap<>();
        keyList = new ArrayList<>();
    }

    public void Add(K key, V value)
    {
        ArrayList<V> currentList = adjacencyMap.get(key);
        if (currentList == null)
        {
            currentList = new ArrayList<>();
            adjacencyMap.put(key, currentList);
            keyList.add(key);
        }
        currentList.add(value);
    }

    public void Remove(K key, V value)
    {
        ArrayList<V> currentList = adjacencyMap.get(key);
        currentList.remove(value);
        if (currentList.isEmpty())
        {
            adjacencyMap.remove(key);
            keyList.remove(key);
        }
    }

    public Object[] GetRandom()
    {
        if (keyList.isEmpty()) { return new Object[] { null, null }; }
        K key = keyList.get(random.nextInt(keyList.size()));
        ArrayList<V> list = adjacencyMap.get(key);
        V value = list.get(random.nextInt(list.size()));
        return new Object[] { key, value };
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for (K key : keyList)
        {
            sb.append("\n").append(key).append(": ");
            for (V value : adjacencyMap.get(key))
            {
                sb.append(value).append(", ");
            }
        }
        return sb.toString();
    }

}
