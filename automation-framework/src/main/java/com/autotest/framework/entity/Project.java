package com.autotest.framework.entity;

public class Project
{
    private String name;
    private String description;

    // Constructor
    public Project(String name) {
        setName(name);
    }

    /////////////////////////////////////////////
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    /////////////////////////////////////////////
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
