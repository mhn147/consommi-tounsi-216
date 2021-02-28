package tn.esprit.pidev.consommitounsi.entities;

import javax.persistence.Id;
import java.io.Serializable;

public class Deliverer extends User {

    private float salary;
    private float bonuses;
    public Deliverer() {
        super();
    }

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
