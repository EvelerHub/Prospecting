package com.entity.leadpage;

import com.models.Company;
import com.models.Mail;

/**
 * @author Alexander Eveler
 */
public class Lead {

    private long id;
    private String name;
    private String surname;
    private String mail;
    private double relevance;
    private String job;
    private String companyName;
    private String companyLink;

    public Lead() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public double getRelevance() {
        return relevance;
    }

    public void setRelevance(double relevance) {
        this.relevance = relevance;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyLink() {
        return companyLink;
    }

    public void setCompanyLink(String companyLink) {
        this.companyLink = companyLink;
    }

    public Mail toMailEntity() {
        Company company = new Company(companyName, companyLink);

        return new Mail(name, surname, mail, relevance, job, company);
    }
}
