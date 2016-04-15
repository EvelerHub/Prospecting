package com.entity.leadpage;

import com.models.Company;
import com.models.Mail;

import java.io.Serializable;

/**
 * <p>Lead Entity for the corresponding transformation form JSON to Lead.<p/>
 *
 * @author Alexander Eveler
 */
public class Lead implements Serializable {

    private static final long serialVersionUID = 2674328867783168464L;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lead lead = (Lead) o;

        if (id != lead.id) return false;
        if (Double.compare(lead.relevance, relevance) != 0) return false;
        if (name != null ? !name.equals(lead.name) : lead.name != null) return false;
        if (surname != null ? !surname.equals(lead.surname) : lead.surname != null) return false;
        if (mail != null ? !mail.equals(lead.mail) : lead.mail != null) return false;
        if (job != null ? !job.equals(lead.job) : lead.job != null) return false;
        if (companyName != null ? !companyName.equals(lead.companyName) : lead.companyName != null) return false;

        return companyLink != null ? companyLink.equals(lead.companyLink) : lead.companyLink == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        temp = Double.doubleToLongBits(relevance);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (job != null ? job.hashCode() : 0);
        result = 31 * result + (companyName != null ? companyName.hashCode() : 0);
        result = 31 * result + (companyLink != null ? companyLink.hashCode() : 0);

        return result;
    }

    @Override
    public String toString() {
        return "Lead{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", mail='" + mail + '\'' +
                ", relevance=" + relevance +
                ", job='" + job + '\'' +
                ", companyName='" + companyName + '\'' +
                ", companyLink='" + companyLink + '\'' +
                '}';
    }
}
