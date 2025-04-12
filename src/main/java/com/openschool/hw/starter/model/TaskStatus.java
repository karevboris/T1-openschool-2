package com.openschool.hw.starter.model;

public enum TaskStatus {

    CREATED("Created"),
    IN_PROGRESS("In Progress"),
    COMPLETED("Completed"),
    FAILED("Failed");

    private final String name;

    TaskStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
