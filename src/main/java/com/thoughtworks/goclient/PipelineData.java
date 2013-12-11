package com.thoughtworks.goclient;

public class PipelineData {

    public static final String SUCCESS = "Success";
    public static final String BUILDING = "Building";
    public static final String SLEEPING = "Sleeping";
    public static final String FAIL = "Failure";

    public final String name;
    public final String lastBuildStatus;
    public final String activity;

    public static final PipelineData FAIL_TO_CREATE = new PipelineData("Error", FAIL, SLEEPING);

    public PipelineData(String name, String lastBuildStatus, String activity) {
        this.name = name;
        this.lastBuildStatus = lastBuildStatus;
        this.activity = activity;
    }

    public String status() {
        return activity.equals(SLEEPING) ? lastBuildStatus : BUILDING;
    }
}
