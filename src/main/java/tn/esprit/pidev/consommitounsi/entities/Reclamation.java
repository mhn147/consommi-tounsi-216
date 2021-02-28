package tn.esprit.pidev.consommitounsi.entities;

import javax.persistence.*;
import java.io.Serializable;

public class Reclamation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String description;
    private String images;
    @Enumerated(EnumType.STRING)
    private ReclamationDecision decision;
    public Reclamation() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public ReclamationDecision getDecision() {
        return decision;
    }

    public void setDecision(ReclamationDecision decision) {
        this.decision = decision;
    }

}
