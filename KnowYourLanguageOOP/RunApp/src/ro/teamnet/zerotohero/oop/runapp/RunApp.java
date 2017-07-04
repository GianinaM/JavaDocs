package ro.teamnet.zerotohero.oop.runapp;

import ro.teamnet.zerotohero.canvas.Canvas;
import ro.teamnet.zerotohero.exceptions.MyException;
import ro.teamnet.zerotohero.oop.MyObject;
import ro.teamnet.zerotohero.oop.graphicshape.*;
import java.io.*;
import java.util.Scanner;

import java.io.IOException;
import java.lang.AutoCloseable;
/**
 * Created by Gianina.Carp on 7/4/2017.
 */
public class RunApp {

    public static void main(String[] args) {
        Circles circles = new Circles();
        System.out.println(circles.getAreaPub());
        circles.getAreaDef();

        Canvas canvas = new Canvas();
        //canvas.paint();

        Shape shape = new Circle(10);
        System.out.println("Circle: " + shape.area());

        Shape square = new Square(10);
        System.out.println("Square: " + square.area());

        Object p1 = new Point(10, 20);
        Object p2 = new Point(50, 100);
        Object p3 = new Point(10, 20);

        System.out.println("p1 equals p2 is " + p1.equals(p2));
        System.out.println("p1 equals p3 is " + p1.equals(p3));

        MyObject myObject = new MyObject();
        try {
            int i = 8;
            myObject.calculate(i);
        } catch(MyException ex) {
            ex.printStackTrace();
            System.exit(1);
        } catch(IOException ex) {
            throw new MyException("Bla" + ex);
        } finally {

        }

        File f = new File("newfile.txt");
        if (f.exists()) {
            if (f.isFile() && f.canRead()) {
                Scanner input = null;
                try {
                    input = new Scanner(f);
                    while (input.hasNextLine()) {
                        String contents = input.nextLine();
                        System.out.println(contents);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    if (input != null) {
                        input.close();
                    }
                }
            } else if (f.isDirectory()) {
                try {
                    System.out.println("File "
                            + f.getCanonicalPath()
                            + " is a directory");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
