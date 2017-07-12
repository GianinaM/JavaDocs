package ro.teamnet.zerotohero.reflection;



import java.lang.*;
import java.lang.reflect.*;
import java.util.ArrayList;

/**
 * TODO
 * in order to resolve the exercises below, you will have to create
 * all the needed clasees, with their members and methods
 * (in ro.teamnet.zerotohero.reflection.demoobjects package)
 */
public class ClassReflectionDemoMain {
    public enum my_enum {
        ONE ,
        TWO ,
    };

    public static Integer field_int = new Integer(20);
    public static String black = new String("BLACK");

    private static int private_int = 52;

    public ClassReflectionDemoMain() {
        System.out.println("Fara param");
    }

    public ClassReflectionDemoMain(int my_int) {
        System.out.println("Un param");
    }

    public ClassReflectionDemoMain(int my_int, int alt_int) {
        System.out.println("Doi param");
    }

    public static void myMethod() {
        System.out.println("Method ");
    }

    public static void newMeth() {

    }

    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        // get the class for a String object, and print it
		Class my_string = "my_string".getClass();
        System.out.println(my_string);

        // get the class of an Enum, and print it
        System.out.println(my_enum.class);

        // get the class of a collection, and print it
        ArrayList<String> arrayList = new ArrayList<String>();
        System.out.println(arrayList.getClass());

        // get the class of a primitive type, and print it
        int int_name = 5;
        System.out.println(Integer.valueOf(int_name).getClass());

        // get and print the class for a field of primitive type
        System.out.println(field_int.getClass().getDeclaredField("value"));

        // get and print the class for a primitive type, using the wrapper class
        Class primitive_class = Integer.TYPE;
        System.out.println(primitive_class);

        // get the class for a specified class name
        System.out.println(ClassReflectionDemoMain.class);
        

        // get the superclass of a class, and print it
        Class superclass = ClassReflectionDemoMain.class.getSuperclass();
        System.out.println(superclass);
        // get the superclass of the superclass above, and print it
        Class supersuperclass = superclass.getSuperclass();
        System.out.println(supersuperclass);

        // get and print the declared classes within some other class
        Class[] my_casses = ClassReflectionDemoMain.class.getDeclaredClasses();
        int nr_classes = my_casses.length;
        for (int  i = 0; i < nr_classes; i++){
            System.out.println(i + " " + my_casses[i]);
        }

        // print the number of constructors of a class
        int len = ClassReflectionDemoMain.class.getConstructors().length;
        System.out.println(len);


        // get and invoke a public constructor of a class
        Constructor constructor = ClassReflectionDemoMain.class.getDeclaredConstructor();
        System.out.println(constructor.newInstance().getClass());

        // get and print the class of one private field
        Field private_field = ClassReflectionDemoMain.class.getDeclaredField("private_int");
        private_field.setAccessible(true);
        System.out.println(private_field.get(private_int));
        System.out.println(private_field);

        // set and print the value of one private field for an object
        private_field.set(private_int, 99);
        System.out.println(private_field.get(private_int));

        // get and print only the public fields class
        Field[] fields_private = ClassReflectionDemoMain.class.getFields();
        int nr_fields = fields_private.length;
        for (int i = 0; i < nr_fields; i++) {
            System.out.println( i + " " + fields_private[i]);
        }

        // get and invoke one public method of a class
        Class[] cArgs = new Class[1];
        cArgs[0] = Integer.class;
        Method public_method = ClassReflectionDemoMain.class.getMethod("myMethod");
        System.out.println(public_method.invoke(new ClassReflectionDemoMain()));

        // get and invoke one inherited method of a class
        Method inherited_method = ClassReflectionDemoMain.class.getMethod("hashCode");
        System.out.println(inherited_method.invoke(new ClassReflectionDemoMain()));
		
		// invoke a method of a class the classic way for ten times, and print the timestamp (System.currentTimeMillis())
        Method new_method = ClassReflectionDemoMain.class.getMethod("newMeth");
        long start = 0;
        start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            ClassReflectionDemoMain.newMeth();
        }
        System.out.println(System.currentTimeMillis() - start);
        System.out.println("Next ->");
        start = System.currentTimeMillis();
        //TODO invoke a method of a class by Reflection for 100 times, and print the timestamp
        Class my_Class = ClassReflectionDemoMain.class;
        for (int i = 0; i < 100; i++){
            new_method.invoke(my_Class);
        }
        System.out.println(System.currentTimeMillis() - start);

        //what do you observe?
		
    }
}
