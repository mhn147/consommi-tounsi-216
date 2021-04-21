package tn.esprit.pidev.consommitounsi.entities.delivery;

import tn.esprit.pidev.consommitounsi.entities.payment.Invoice;
import tn.esprit.pidev.consommitounsi.entities.user.User;
import tn.esprit.pidev.consommitounsi.entities.payment.OrderDelivery;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Entity
public class Deliverer extends User implements Serializable {

    private float salary;
    private float bonuses;

    @OneToMany
    private List<Invoice> invoices;

    @OneToMany
    private List<OrderDelivery> ordersDeliveries;

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


    @Override
    public List<Invoice> getInvoices() {
        return invoices;
    }

    @Override
    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    public List<OrderDelivery> getOrdersDeliveries() {
        return ordersDeliveries;
    }

    public void setOrdersDeliveries(List<OrderDelivery> ordersDeliveries) {
        this.ordersDeliveries = ordersDeliveries;
    }
}
