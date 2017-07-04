package zoo.angajati;
import zoo.animale.*;
/**
 * Created by Gianina.Carp on 7/4/2017.
 */
public interface AngajatZoo{
    Integer valoareBonusPerAnimal = 50;
    public void lucreaza(Animal animal);
    public void calculeazaBonusSalarial();
}
