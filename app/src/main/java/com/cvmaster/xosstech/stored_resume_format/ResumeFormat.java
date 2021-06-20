package com.cvmaster.xosstech.stored_resume_format;

import com.cvmaster.xosstech.model.EducationQualification_Model;
import com.cvmaster.xosstech.model.Reference_Model;
import com.cvmaster.xosstech.model.Skills_Model;
import com.cvmaster.xosstech.model.WorkExperience_Model;

import java.util.ArrayList;
import java.util.List;

public class ResumeFormat {

    private String name;
    private String contact_number;
    private String email;
    private String present_address;
    private String career_objective;
    private List<EducationQualification_Model> education_qualification = new ArrayList<>();
    private List<Skills_Model> skills = new ArrayList<>();
    private Language language;
    private Personal_Information personal_information;
    private List<WorkExperience_Model> work_experience = new ArrayList<>();
    private List<Reference_Model> reference = new ArrayList<>();

    public ResumeFormat() {
    }

    public ResumeFormat(String name, String contact_number, String email, String present_address, String career_objective, List<EducationQualification_Model> education_qualification, List<Skills_Model> skills, Language language, Personal_Information personal_information, List<WorkExperience_Model> work_experience, List<Reference_Model> reference) {
        this.name = name;
        this.contact_number = contact_number;
        this.email = email;
        this.present_address = present_address;
        this.career_objective = career_objective;
        this.education_qualification = education_qualification;
        this.skills = skills;
        this.language = language;
        this.personal_information = personal_information;
        this.work_experience = work_experience;
        this.reference = reference;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPresent_address(String present_address) {
        this.present_address = present_address;
    }

    public void setCareer_objective(String career_objective) {
        this.career_objective = career_objective;
    }

    public void setEducation_qualification(List<EducationQualification_Model> education_qualification) {
        this.education_qualification = education_qualification;
    }

    public void setSkills(List<Skills_Model> skills) {
        this.skills = skills;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public void setPersonal_information(Personal_Information personal_information) {
        this.personal_information = personal_information;
    }

    public void setWork_experience(List<WorkExperience_Model> work_experience) {
        this.work_experience = work_experience;
    }

    public void setReference(List<Reference_Model> reference) {
        this.reference = reference;
    }


    public String getName() {
        return name;
    }

    public String getContact_number() {
        return contact_number;
    }

    public String getEmail() {
        return email;
    }

    public String getPresent_address() {
        return present_address;
    }

    public String getCareer_objective() {
        return career_objective;
    }

    public List<EducationQualification_Model> getEducation_qualification() {
        return education_qualification;
    }

    public List<Skills_Model> getSkills() {
        return skills;
    }

    public Language getLanguage() {
        return language;
    }

    public Personal_Information getPersonal_information() {
        return personal_information;
    }

    public List<WorkExperience_Model> getWork_experience() {
        return work_experience;
    }

    public List<Reference_Model> getReference() {
        return reference;
    }
}
