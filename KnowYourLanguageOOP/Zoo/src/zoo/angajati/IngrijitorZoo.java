package zoo.angajati;

import zoo.animale.Animal;
import zoo.exceptii.AnimalManancaAnimalException;
import zoo.exceptii.AnimalManancaOmException;
import zoo.exceptii.AnimalPeCaleDeDisparitieException;

/**
 * Created by Gianina.Carp on 7/4/2017.
 */
public class IngrijitorZoo implements AngajatZoo{
    int valoareBonus = 0;
    @Override
    public void lucreaza(Animal animal) {
        System.out.println("Ingrijitorul intra in cusca animalului");
        calculeazaBonusSalarial();
    }

    @Override
    public void calculeazaBonusSalarial() {
        this.setValoareBonus(this.getValoareBonus() + this.valoareBonusPerAnimal * 3);
    }

    public void lucreaza(Animal animal, Object mancare) {
        this.lucreaza(animal);
        animal.doarme();
        animal.facBaie();
        animal.seJoaca();
        try {
            animal.mananca(mancare);
            calculeazaBonusSalarial();
        } catch (AnimalManancaOmException ex) {
            System.out.println(ex);
        } catch (AnimalPeCaleDeDisparitieException ex) {
            System.out.println(ex);
        } catch (AnimalManancaAnimalException ex) {
            System.out.println(ex);
        }
    }

    public Integer getValoareBonus() {
        return this.valoareBonus;
    }

    public void setValoareBonus(int valoareBonus) {
        this.valoareBonus = valoareBonus;
    }
}
