package com.entity.leadpage;

import com.models.Mail;

import java.io.Serializable;

/**
 * @author Alexander Eveler
 */
public class LeadRow implements Serializable {

    private static final long serialVersionUID = -8257562792126081016L;

    private long id;
    private String name;
    private String mail;
    private double relevance;
    private String job;
    private String companyName;
    private String companyLink;

    public LeadRow(Mail mail) {
        this.id = mail.getId();
        this.name = mail.getName() + " " + mail.getSurname();
        this.mail = mail.getMail();
        this.relevance = mail.getRelevance();
        this.job = mail.getJob();
        this.companyName = mail.getCompany().getName();
        this.companyLink = mail.getCompany().getLink();
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

    @Override
    public String toString() {
        return "LeadRow{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                ", relevance=" + relevance +
                ", job='" + job + '\'' +
                ", companyName='" + companyName + '\'' +
                ", companyLink='" + companyLink + '\'' +
                '}';
    }
}
