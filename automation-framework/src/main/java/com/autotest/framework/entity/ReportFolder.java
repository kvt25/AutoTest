package com.autotest.framework.entity;

public class ReportFolder
{
    private String name;

    // Constructor
    public ReportFolder(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
