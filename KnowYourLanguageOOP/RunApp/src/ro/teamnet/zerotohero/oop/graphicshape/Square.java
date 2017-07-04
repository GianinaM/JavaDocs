package ro.teamnet.zerotohero.oop.graphicshape;
import static java.lang.Math.*;
/**
 * Created by Gianina.Carp on 7/4/2017.
 */
public class Square extends Shape {
    private int side;

    public Square(int side) {
        this.side = side;
    }

    public double area() {
        return Math.pow(side, 2);
    }

}
