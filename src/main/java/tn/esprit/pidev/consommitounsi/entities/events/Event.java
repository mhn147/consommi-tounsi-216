package tn.esprit.pidev.consommitounsi.entities.events;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Event implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private String location;
    @OneToMany
    private List<Donation> donationss;

    public Event() {

    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Donation> getDonationss() {
        return donationss;
    }

    public void setDonationss(List<Donation> donationss) {
        this.donationss = donationss;
    }

    public Event(long id, String name, String description, String location) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
    }

}