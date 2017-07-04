package ro.teamnet.zerotohero.oop.graphicshape;
import static java.lang.Math.*;
/**
 * Created by Gianina.Carp on 7/4/2017.
 */
public class Circle extends Shape{
    private int xPos;
    private int yPos;
    private int radius;

    public Circle(){
        this.radius = 15;
        this.xPos = 10;
        this.yPos = 20;
    }

    public Circle(int radius){
        this.radius = radius;
        this.xPos = 10;
        this.yPos = 20;
    }

    public Circle(int radius, int xPos){
        this.radius = radius;
        this.xPos = xPos;
        this.yPos = 20;
    }

    public Circle(int radius, int xPos, int yPos){
        this.radius = radius;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public double area(){
        return Math.PI*this.radius;
    }

    public double fillColour() {
        return super.getColor();
    }

    public void fillColour(int color) {
        super.setColor(color);
        System.out.println("The circle color is now" + super.getColor());
    }

    public void fillColour(float saturation) {
        super.setSaturation(saturation);
        System.out.println("The circle saturation is now" + super.getSaturation());
    }

    public String toString() {
        return "center = (" + this.xPos + "," + this.yPos + ") and radius = " + this.radius;
    }
}
