package tn.esprit.pidev.consommitounsi.entities.events;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class Event implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private String location;
    private int number;
    private int Maxnumber;
    @Temporal(TemporalType.TIMESTAMP)
    private Date eventDate;
    private float sumCollect;
    private float maxCollect;
    private String picture;

    @Enumerated(EnumType.STRING)
    private EventType eventType;
    @OneToMany
    private List<Donation> Events_donations;
    @OneToMany
    private List<Participation> participations;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getMaxnumber() {
        return Maxnumber;
    }

    public void setMaxnumber(int maxnumber) {
        Maxnumber = maxnumber;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public float getMaxCollect() {
        return maxCollect;
    }

    public void setMaxCollect(float maxCollect) {
        this.maxCollect = maxCollect;
    }

    public float getSumCollect() {
        return sumCollect;
    }

    public void setSumCollect(float sumCollect) {
        this.sumCollect = sumCollect;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public List<Donation> getEvents_donations() {
        return Events_donations;
    }

    public void setEvents_donations(List<Donation> Events_donations) {
        this.Events_donations = Events_donations;
    }

    public List<Participation> getParticipations() {
        return participations;
    }

    public void setParticipations(List<Participation> participations) {
        this.participations = participations;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Event(long id, String name, String description, String location, int number, int maxnumber, Date eventDate, float sumCollect, float maxCollect, String picture, EventType eventType) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.number = number;
        Maxnumber = maxnumber;
        this.eventDate = eventDate;
        this.sumCollect = sumCollect;
        this.maxCollect = maxCollect;
        this.picture = picture;
        this.eventType = eventType;
    }
}