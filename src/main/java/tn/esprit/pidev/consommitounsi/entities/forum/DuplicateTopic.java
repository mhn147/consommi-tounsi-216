package tn.esprit.pidev.consommitounsi.entities.forum;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class DuplicateTopic implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToMany
    private List<Topic> originals;
    @OneToOne
    private Topic duplicate;

    public DuplicateTopic() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Topic> getOriginals() {
        return originals;
    }

    public void setOriginals(List<Topic> originals) {
        this.originals = originals;
    }

    public Topic getDuplicate() {
        return duplicate;
    }

    public void setDuplicate(Topic duplicate) {
        this.duplicate = duplicate;
    }
}
