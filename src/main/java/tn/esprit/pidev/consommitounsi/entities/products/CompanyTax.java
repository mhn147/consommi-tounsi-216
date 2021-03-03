package tn.esprit.pidev.consommitounsi.entities.products;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

public class CompanyTax implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private double taxvalue;
 public CompanyTax(){

 }
    public CompanyTax(long id, String name, double taxvalue) {
        this.id = id;
        this.name = name;
        this.taxvalue = taxvalue;
    }
}
