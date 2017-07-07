package exercise3;

import java.math.BigDecimal;
import java.util.*;
/**
 * Created by Gianina.Carp on 7/7/2017.
 */

//Now create another class that suits the main() method where we create 4 MAPS where the KEY will be of
//        type STUDENT and the VALUE will be a BigDecimal representing the age (or whatever).
//        In the first Map, the key will be an object from point (a), in the second map it will be an object from point (b),
//        in the third map it will be an object from point (c) and in the last map it be an object from point (d).
//        Think and set the proper properties for the objects in order to observe what can go wrong with our dictionaries.
//
//        Give suggestive names for the classes and print the content of the dictionaries using Iterator!
//        Do this exercises inside the exercise3 package.

public class Main_Student {
    public static void main(String[] args) {
        HashMap<Student_1, BigDecimal> studentMap_1 = new HashMap<Student_1, BigDecimal>();
        HashMap<Student_2, BigDecimal> studentMap_2 = new HashMap<Student_2, BigDecimal>();
        HashMap<Student_3, BigDecimal> studentMap_3 = new HashMap<Student_3, BigDecimal>();
        HashMap<Student_4, BigDecimal> studentMap_4 = new HashMap<Student_4, BigDecimal>();


//        studentMap_1
        studentMap_1.put(new Student_1("Georgescu", "Alina"), BigDecimal.TEN);
        studentMap_1.put(new Student_1("Georgescu", "Alinaaa"), BigDecimal.TEN);
        studentMap_1.put(new Student_1("Popescu", "George"), BigDecimal.TEN);
        studentMap_1.put(new Student_1("Georgescuu", "George"), BigDecimal.TEN);
        System.out.println(studentMap_1);

        Iterator<Student_1> it = studentMap_1.keySet().iterator();
        Student_1 my_it = it.next();
        while (it.hasNext()){
            Student_1 new_it = it.next();
            System.out.println(my_it.equals(new_it));
            my_it = new_it;
        }

//        studentMap_2
        studentMap_2.put(new Student_2("Georgescu", "Alina"), BigDecimal.TEN);
        studentMap_2.put(new Student_2("Georgescu", "Alinaaa"), BigDecimal.TEN);
        studentMap_2.put(new Student_2("Popescu", "George"), BigDecimal.TEN);
        studentMap_2.put(new Student_2("Georgescuu", "George"), BigDecimal.TEN);
        System.out.println(studentMap_2);

        Iterator<Student_2> it_2 = studentMap_2.keySet().iterator();
        Student_2 my_it_2 = it_2.next();
        Student_2 new_it_2;
        while (it_2.hasNext()){
            new_it_2 = it_2.next();
            System.out.println(my_it_2.equals(new_it_2));
            my_it_2 = new_it_2;
        }

        //        studentMap_3
        studentMap_3.put(new Student_3("Georgescu", "Alina"), BigDecimal.TEN);
        studentMap_3.put(new Student_3("Georgescu", "Alinaaa"), BigDecimal.TEN);
        studentMap_3.put(new Student_3("Popescu", "George"), BigDecimal.TEN);
        studentMap_3.put(new Student_3("Georgescuu", "George"), BigDecimal.TEN);
        System.out.println(studentMap_3);

        Iterator<Student_3> it_3 = studentMap_3.keySet().iterator();
        Student_3 my_it_3 = it_3.next();
        Student_3 new_it_3;
        while (it_3.hasNext()){
            new_it_3 = it_3.next();
            System.out.println(my_it_3.equals(new_it_3));
            my_it_3 = new_it_3;
        }

        //        studentMap_4
        studentMap_4.put(new Student_4("Georgescu", "Alina"), BigDecimal.TEN);
        studentMap_4.put(new Student_4("Georgescu", "Alinaaa"), BigDecimal.TEN);
        studentMap_4.put(new Student_4("Popescu", "George"), BigDecimal.TEN);
        studentMap_4.put(new Student_4("Georgescuu", "George"), BigDecimal.TEN);
        System.out.println(studentMap_4);

        Iterator<Student_4> it_4 = studentMap_4.keySet().iterator();
        Student_4 my_it_4 = it_4.next();
        Student_4 new_it_4;
        while (it_4.hasNext()){
            new_it_4 = it_4.next();
            System.out.println(my_it_4.equals(new_it_4) + " hs " + my_it_4.hashCode() + " - " + new_it_4.hashCode());
            my_it_4 = new_it_4;
        }
    }
}
