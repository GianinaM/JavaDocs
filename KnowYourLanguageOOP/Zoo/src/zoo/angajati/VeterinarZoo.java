package zoo.angajati;

import zoo.animale.Animal;
import zoo.animale.AnimalZooFeroce;

/**
 * Created by Gianina.Carp on 7/4/2017.
 */
public class VeterinarZoo implements AngajatZoo {

    int valoareBonus = 0;

    @Override
    public void calculeazaBonusSalarial() {
        this.setValoareBonus(this.getValoareBonus() + this.valoareBonusPerAnimal * 2);
    }

    @Override
    public void lucreaza(Animal animal) {
        System.out.println("Veterinarul are grija de animal");
        if (animal instanceof AnimalZooFeroce) {
            animal.facBaie();
        }
        calculeazaBonusSalarial();
    }

    public Integer getValoareBonus() {
        return valoareBonus;
    }

    public void setValoareBonus(int valoareBonus) {
        this.valoareBonus = valoareBonus;
    }
}
