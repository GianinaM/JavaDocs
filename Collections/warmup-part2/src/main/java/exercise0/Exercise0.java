package exercise0;

import java.lang.reflect.Array;
import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Radu.Hoaghe on 4/20/2015.
 *
 * Exercise 0: Iterate over the keys of a Map using keySet method (this method returns a Set of all the map keys) and
 *          print all its elements.
 */
public class Exercise0 {

    public Exercise0(){

    }

    public void iterateThroughMap(){

        //  Exercise #0 a) Create a Map (HashMap) and add elements to it (using put() method)
        //  Exercise #0 a) Hint: Don't forget to specify the types of the key and value when creating the Map
        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        hashMap.put("mere", 3);
        hashMap.put("pere", 2);
        hashMap.put("struguri", 1);
        hashMap.put("caise", 5);
        hashMap.put(null, 4);

        //  Exercise #0 b) Iterate over the Map using keySet() method and print all its elements
        //  Exercise #0 b) The elements could be printed like this: [key1=value1, key2=value2, ...
//        System.out.println(hashMap.keySet().getClass());
//        System.out.println(hashMap.get());
        display(hashMap.keySet().iterator(), hashMap);
    }

    public static void display(Iterator<String> it, HashMap<String, Integer> hashMap){
        while (it.hasNext()) {
            String keyList = it.next();
            System.out.println(hashMap.get(keyList));
        }
    }

    public static void main(String[] args) {
        Exercise0 exercise0 = new Exercise0();
        exercise0.iterateThroughMap();
    }
}
