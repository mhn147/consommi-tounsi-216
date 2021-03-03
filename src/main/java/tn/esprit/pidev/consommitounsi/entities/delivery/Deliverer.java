package tn.esprit.pidev.consommitounsi.entities.delivery;

import tn.esprit.pidev.consommitounsi.entities.User;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Deliverer extends User implements Serializable {

    private float salary;
    private float bonuses;
    public Deliverer() { super(); }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public float getBonuses() {
        return bonuses;
    }

    public void setBonuses(float bonuses) {
        this.bonuses = bonuses;
    }

}
