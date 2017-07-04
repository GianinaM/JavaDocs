package ro.teamnet.zerotohero.oop.graphicshape;

import org.jcp.xml.dsig.internal.dom.DOMXPathFilter2Transform;

/**
 * Created by Gianina.Carp on 7/4/2017.
 */
public class Circles {
    public double getAreaPub() {
        Circle c = new Circle();
        return c.area();
    }

    public void getAreaDef(){
        Circle circle = new Circle();
        circle.fillColour();
        circle.fillColour(2);
        circle.fillColour((float) 2.5);
    }
}
