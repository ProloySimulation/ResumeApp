package com.cvmaster.xosstech.model;

public class Additional {
    private String skills,hobby,language,linkedin,github,twitter,behance;
    private int id;

    public Additional(String skills, String hobby, String language, String linkedin, String github,
                      String twitter, String behance, int id) {
        this.skills = skills;
        this.hobby = hobby;
        this.language = language;
        this.linkedin = linkedin;
        this.github = github;
        this.twitter = twitter;
        this.behance = behance;
        this.id = id;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getBehance() {
        return behance;
    }

    public void setBehance(String behance) {
        this.behance = behance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
