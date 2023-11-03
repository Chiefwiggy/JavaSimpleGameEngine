package Game_Files.Helpers;

import java.util.ArrayList;
import java.util.HashMap;

public class Multimap<K, T> {

    public Multimap() {
        multimap = new HashMap<>();
        keyList = new ArrayList<>();
    }

    public class MultimapIterator<K, T> {
        private K key;
        private T obj;

        private MultimapIterator(K k, T t) {
            this.key = k;
            this.obj = t;
        }
    }

    private HashMap<K, ArrayList<T>> multimap;
    private ArrayList<K> keyList;

    public MultimapIterator<K, T> Add(K key, T obj) {
        ArrayList<T> currentList = multimap.get(key);
        if (currentList == null) {
            currentList = new ArrayList<T>();
            multimap.put(key, currentList);
            keyList.add(key);
        }
        currentList.add(obj);

        return new MultimapIterator<>(key, obj);
    }

    public void Remove(K key, T obj) {
        ArrayList<T> currentList = multimap.get(key);
        currentList.remove(obj);
        if (currentList.size() == 0) {
            multimap.remove(key);
            keyList.remove(key);
        }
    }

    public ArrayList<T> GetList(K key) {
        return multimap.get(key);
    }

    public ArrayList<K> GetKeys() {
        return keyList;
    }

    public interface MultimapLambda<K, T> {
        void multimapLambda(K key, ArrayList<T> obj);
    }

    public void forEach(MultimapLambda<K, T> func) {
        multimap.forEach((k, tb) -> {
            func.multimapLambda(k, tb);
        });
    }
}
