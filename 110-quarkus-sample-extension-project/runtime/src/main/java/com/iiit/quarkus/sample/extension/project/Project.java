package com.iiit.quarkus.sample.extension.project;

public class Project {

    public Integer id;
    public String name;
    public String description;
    public boolean state = true;

    public Project() {
    }

    public Project(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Project(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        // this.price = price;
    }
}
