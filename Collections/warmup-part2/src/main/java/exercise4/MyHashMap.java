package exercise4;

import java.util.*;

/**
 * Exercise 3. Implement a HashMap from scratch. In order to pass all the tests
 * you need to implement all the methods defined below. The key-value pair will
 * be encapsulated in the MyHashMap.MyEntry object defined below.
 *
 * The buckets list in which each MyEntry object will be stored is stored in "buckets" object.
 * The hash function that you will use in order to store a pair in a specific bucket will be
 * the one presented earlier: (hashcode value) modulo (bucket array size)
 */
public class MyHashMap {

    private ArrayList<LinkedList<MyEntry>> buckets;

    private final int BUCKET_ARRAY_SIZE = 16;

    public int bucketSize = 0;

    public MyHashMap() {

        //  Initialize buckets list
        this.buckets = new ArrayList<LinkedList<MyEntry>>();
        for (int i = 0; i < this.BUCKET_ARRAY_SIZE; i++) {
            this.buckets.add(i, new LinkedList<MyEntry>());
        }

    }

    public String get(String key) {

        int my_hashKey = 0;
        if (key != null) my_hashKey = this.getHash(key);

        if (buckets.get(my_hashKey).isEmpty()) return null;

        for (MyEntry myEntry : buckets.get(my_hashKey)) {
            if ((myEntry.getKey() == null && key == null)|| (myEntry.getKey().equals(key))) {
                return myEntry.getValue();
            }
        }
        return null;
    }

    public int getHash(String key) {
        return Math.abs(key.hashCode()%this.BUCKET_ARRAY_SIZE);
    }

    public void put(String key, String value) {
        int my_hashCode = 0;
        if (key != null) {
            my_hashCode = this.getHash(key);
        }

        if (!this.containsKey(key)) {
            buckets.get(my_hashCode).add(new MyEntry(key, value));
            this.bucketSize++;
        } else {
            for (MyEntry myEntry : buckets.get(my_hashCode)){
                if (myEntry.getKey() == null && key == null){
                    myEntry.setValue(value);
                } else if (myEntry.getKey().equals(key)){
                    myEntry.setValue(value);
                }
            }
        }
    }

    public Set<String> keySet() {
        Set<String> keys = new HashSet<String>();
        for (LinkedList<MyEntry> list : this.buckets){
            if (!list.isEmpty()) {
                for (MyEntry myEntry : list) {
                    keys.add(myEntry.getKey());
                }
            }
        }
        return keys;
    }

    public Collection<String> values() {
        Collection<String> values = new ArrayList<String>();
        for (LinkedList<MyEntry> list : this.buckets){
            if (!list.isEmpty()) {
                for (MyEntry myEntry : list) {
                    values.add(myEntry.getValue());
                }
            }
        }
        return values;
    }

    public String remove(String key) {
        //  Returns the value associated with the key removed from the map or null if the key wasn't found
        for (LinkedList<MyEntry> list : this.buckets){
            if (!list.isEmpty()) {
                for (MyEntry myEntry : list) {
                    if ((myEntry.getKey() == null && key == null)|| (myEntry.getKey().equals(key))) {
                        String returned = myEntry.getValue();
                        list.remove(myEntry);
                        this.bucketSize--;
                        return returned;
                    }
                }
            }
        }
        return null;
    }

    public boolean containsKey(String key) {
        for (LinkedList<MyEntry> list : this.buckets){
            if (!list.isEmpty()) {
                for (MyEntry myEntry : list) {
                    if (myEntry.getKey() == null && key == null) return true;
                    if (myEntry.getKey().equals(key)) return true;
                }
            }
        }
        return false;
    }

    public boolean containsValue(String value) {
        for (LinkedList<MyEntry> list : this.buckets){
            if (!list.isEmpty()) {
                for (MyEntry myEntry : list) {
                    if (myEntry.getValue().equals(value)) return true;
                }
            }
        }
        return false;
    }

    public int size() {
        //  Return the number of the Entry objects stored in all the buckets
        return this.bucketSize;
    }

    public void clear() {
        //  Remove all the Entry objects from the bucket list
        this.buckets = new ArrayList<LinkedList<MyEntry>>();
        for (int i = 0; i < this.BUCKET_ARRAY_SIZE; i++) {
            this.buckets.add(i, new LinkedList<MyEntry>());
        }
        this.bucketSize = 0;
    }

    public Set<MyEntry> entrySet() {
        //  Return a Set containing all the Entry objects
        Set<MyEntry> entries = new HashSet<MyEntry>();
        for (LinkedList<MyEntry> list : this.buckets){
            if (!list.isEmpty()) {
                for (MyEntry myEntry : list) {
                    entries.add(myEntry);
                }
            }
        }
        return entries;
    }

    public boolean isEmpty() {
        for (LinkedList<MyEntry> list : this.buckets){
            if (!list.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public static class MyEntry {
        private String key;
        private String value;

        public MyEntry(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
