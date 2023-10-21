package com.abiskar.crudApplication.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Destination")
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "destination_id")
    private int id;

    @Column(name = "name")
    private String name;
    @Column(name = "location")
    private String location;
    @Column(name = "imageURL")
    private String imageURL;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
