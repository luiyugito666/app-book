package com.example.demo2.model;

import jakarta.persistence.*;

import java.io.Serializable;
@Entity
@Table(name = "categories")
public class Category implements Serializable {
//    public static final long serialVersionUID=-2L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;



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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
