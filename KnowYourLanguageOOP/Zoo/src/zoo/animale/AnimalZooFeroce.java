package zoo.animale;
import zoo.angajati.AngajatZoo;
import zoo.exceptii.*;
/**
 * Created by Gianina.Carp on 7/4/2017.
 */
public class AnimalZooFeroce extends Animal {
    @Override
    public void mananca(Object mancare) throws AnimalManancaOmException, AnimalPeCaleDeDisparitieException, AnimalManancaAnimalException {
        if (mancare instanceof AngajatZoo){
            throw new AnimalManancaOmException("Animalele nu mananca oameni :-o");
        } else if (mancare instanceof Animal) {
            throw new AnimalManancaAnimalException("Animalele nu mananca animale :-w");
        } else if (mancare == null){
            throw new AnimalPeCaleDeDisparitieException("NULL nu e mancare");
        } else {
            System.out.println("AnimalZooRar mananca");
        }
    }

    @Override
    public void seJoaca() {
        System.out.println("AnimalulZooFeroce se joaca");
        super.doarme();
    }

    @Override
    public void facBaie() {
        System.out.println("AnimalulZooFeroce face baie");
    }

    @Override
    public void doarme(){
        super.doarme();
        System.out.println("AnimalulZooFeroc vaneaza");
    }
}
