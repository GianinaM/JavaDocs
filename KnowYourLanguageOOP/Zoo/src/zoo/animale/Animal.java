package zoo.animale;
import zoo.exceptii.*;
/**
 * Created by Gianina.Carp on 7/4/2017.
 */
public abstract class Animal {

    public Animal(){
        System.out.println("Animal nou");
    }

    public abstract void mananca(Object mancare) throws AnimalManancaOmException, AnimalPeCaleDeDisparitieException, AnimalManancaAnimalException;
    public abstract void seJoaca();
    public abstract void facBaie();
    public void doarme(){
        System.out.println("Animalul doarme");
    }

}
