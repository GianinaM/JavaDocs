package ro.teamnet.zerotohero.oop;

import ro.teamnet.zerotohero.exceptions.MyException;

import java.io.IOException;

/**
 * Created by Gianina.Carp on 7/4/2017.
 */
public class MyObject {

    public void calculate(int i) throws IOException, MyException {
        System.out.println("This is : " + i);
    }
}
