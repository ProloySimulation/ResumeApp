package com.cvmaster.xosstech.model;

public class Project {

    private String project_name,start,end,project_summary;
    private int id;

    public Project(String project_name, String start, String end, String project_summary, int id) {
        this.project_name = project_name;
        this.start = start;
        this.end = end;
        this.project_summary = project_summary;
        this.id = id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getProject_summary() {
        return project_summary;
    }

    public void setProject_summary(String project_summary) {
        this.project_summary = project_summary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
