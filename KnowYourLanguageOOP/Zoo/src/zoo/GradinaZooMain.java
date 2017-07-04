package zoo;
import zoo.angajati.*;
import zoo.animale.*;
import zoo.exceptii.AnimalManancaAnimalException;

/**
 * Created by Gianina.Carp on 7/4/2017.
 */
public class GradinaZooMain {
    public static void main(String[] args) {
        AnimalZooRar animal1 = new AnimalZooRar("Pinguin");
        AnimalZooRar animal2 = new AnimalZooRar("Elefant", "Africa");
        AnimalZooRar animal3 = new AnimalZooRar();

        AnimalZooFeroce animalFeroce = new AnimalZooFeroce();

        AngajatZoo angajat1 = new IngrijitorZoo();
        IngrijitorZoo angajat2 = new IngrijitorZoo();

        AngajatZoo angajat3 = new VeterinarZoo();
        VeterinarZoo angajat4 = new VeterinarZoo();

//aici se vor afisa informatiile despre animal1 si animal2 (nume si tara)
        System.out.println(animal1.getNumeAnimal());

//apelul metodelor
        angajat3.lucreaza(animal1);
        angajat3.lucreaza(animal2);
        angajat3.lucreaza(animal3);
        angajat4.lucreaza(animal1);
        angajat4.lucreaza(animal2);
        angajat4.lucreaza(animal3);

        angajat1.lucreaza(animal1);
        angajat1.lucreaza(animal2);
        angajat1.lucreaza(animal3);

        angajat2.lucreaza(animal1);
        angajat2.lucreaza(animal2);
        angajat2.lucreaza(animal3);

        angajat2.lucreaza(animal1,null);
        angajat2.lucreaza(animal1,angajat1);
        angajat2.lucreaza(animal1,new String("Mancare"));

        angajat2.lucreaza(animalFeroce);
        angajat2.lucreaza(animalFeroce,null);
        angajat2.lucreaza(animalFeroce, new String("Mancare"));

        System.out.println("Finish!");

        angajat2.lucreaza(animal1, animal1);

        try {
            animal1.mananca(new AnimalZooFeroce());
        } catch (AnimalManancaAnimalException ex) {
            System.out.println(ex);
        }

       // GradinaZoo gradinaZoo = new GradinaZoo();


        System.out.println("Angajat4: " + angajat4.getValoareBonus());
        System.out.println("Angajat2: " + angajat2.getValoareBonus());

    }
}
