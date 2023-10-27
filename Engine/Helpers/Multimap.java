package Engine.Helpers;


import java.util.ArrayList;
import java.util.HashMap;

public class Multimap<K, T> {

    public Multimap() {
        multimap = new HashMap<>();
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

    public MultimapIterator Add(K key, T obj) {
        ArrayList<T> currentList = multimap.get(key);
        if (currentList == null) {
            currentList = new ArrayList<T>();
            multimap.put(key, currentList);
        }
        currentList.add(obj);

        return new MultimapIterator(key, obj);
    }

    public void Remove(MultimapIterator iter) {

        ArrayList<T> currentList = multimap.get(iter.key);
        currentList.remove(iter.obj);
        if (currentList.size() == 0) {
            multimap.remove(iter.key);
        }
    }

    public ArrayList<T> GetList(K key) {
        return multimap.get(key);
    }

    public interface MultimapLambda<K, T> {
        void multimapLambda(K key, ArrayList<T> obj);
    }


    public void forEach(MultimapLambda func) {
        multimap.forEach((k, tb) -> {
            func.multimapLambda(k, tb);
        });
    }
}
