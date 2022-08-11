package com.cvmaster.xosstech.model;

public class Experience {
    private String company_name,position,start,end,work_summary,location;
    private int id;

    public Experience(String company_name, String position, String start,
                      String end, String work_summary, String location, int id) {
        this.company_name = company_name;
        this.position = position;
        this.start = start;
        this.end = end;
        this.work_summary = work_summary;
        this.location = location;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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

    public String getWork_summary() {
        return work_summary;
    }

    public void setWork_summary(String work_summary) {
        this.work_summary = work_summary;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
