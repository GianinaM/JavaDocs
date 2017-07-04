package zoo.animale;

import zoo.angajati.AngajatZoo;
import zoo.exceptii.AnimalManancaAnimalException;
import zoo.exceptii.AnimalManancaOmException;

/**
 * Created by Gianina.Carp on 7/4/2017.
 */
public class AnimalZooRar extends Animal{
    private String numeAnimal;
    private String numeTaraDeOrigine;

    public AnimalZooRar() {
        this.setNumeAnimal("Tom");
        this.setNumeTaraDeOrigine("Romania");
    }

    public AnimalZooRar(String numeAnimal) {
        this.setNumeAnimal(numeAnimal);
        this.setNumeTaraDeOrigine("Romania");
    }

    public AnimalZooRar(String numeAnimal, String numeTaraDeOrigine) {
        this.setNumeAnimal(numeAnimal);
        this.setNumeTaraDeOrigine(numeTaraDeOrigine);
    }

    public void mananca(Object mancare) throws AnimalManancaOmException, AnimalManancaAnimalException {
        if (mancare instanceof AngajatZoo) {
            throw new AnimalManancaOmException("Animalele nu mananca oameni :-o");
        } else if (mancare instanceof Animal) {
            throw new AnimalManancaAnimalException("Animalele nu mananca animale :-w");
        } else {
            System.out.println("AnimalZooRar mananca");
        }
    }

    public void seJoaca(){
        System.out.println("AnimalulZooRor se joaca");
        super.doarme();
    }

    public void facBaie(){
        System.out.println("AnimalulRar face baie");
    }

    public String getNumeAnimal() {
        return numeAnimal;
    }

    public void setNumeAnimal(String numeAnimal) {
        this.numeAnimal = numeAnimal;
    }

    public String getNumeTaraDeOrigine() {
        return numeTaraDeOrigine;
    }

    public void setNumeTaraDeOrigine(String numeTaraDeOrigine) {
        this.numeTaraDeOrigine = numeTaraDeOrigine;
    }
}
