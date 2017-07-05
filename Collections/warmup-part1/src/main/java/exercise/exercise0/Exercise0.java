package exercise.exercise0;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Created by Radu.Hoaghe on 4/20/2015.
 *
 * Exercise 0: Create a List (ArrayList or LinkedList), add elements to it and print all of them using ListIterator
 *             for loop and foreach loop.
 *
 */
public class Exercise0 {

    public Exercise0(){

    }

    public void iterateThroughList(){

        // TODO Exercise #0 a) Create a list (ArrayList or LinkedList) and add elements to it
        // TODO Exercise #0 a) Don't forget to specify the type of the list (Integer, String etc.)
        ArrayList <Integer> arrayListInteger = new ArrayList<Integer>();
        arrayListInteger.add(2);
        arrayListInteger.add(5);
        arrayListInteger.add(2);
        arrayListInteger.add(8);
        arrayListInteger.add(9);

        // TODO Exercise #0 b) Iterate through the list using ListIterator and print all its elements
        ListIterator<Integer> it = arrayListInteger.listIterator();
        while (it.hasNext())
            System.out.println("Value: " + it.next() + ", nextIndex:" + it.nextIndex() + ", previousIndex: " + it.previousIndex() + ";" );
        // TODO Exercise #0 c) Iterate through the list using classic for loop and print all its elements
        System.out.print("For Loop: ");
        for (int i = 0; i < arrayListInteger.size(); i++) {
            System.out.print(arrayListInteger.get(i) + " ");
        }
        System.out.println();
        // TODO Exercise #0 d) Iterate through the list using foreach loop and print all its elements
        System.out.print("Foreach Loop: ");
        for (Integer item : arrayListInteger) {
            System.out.print(item + " ");
        }
    }

    public static void main(String[] args) {
        // TODO Exercise #0 e) Create a new instance of Exercise0 class and call the iterateThroughList() method
        Exercise0 exercise0 = new Exercise0();
        exercise0.iterateThroughList();
    }
}
