package com.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Alexander Eveler
 */
@Entity
@Table(name = "mails")
public class Mail implements Serializable {

    private static final long serialVersionUID = 5413169710551157594L;

    @Id
    @GeneratedValue
    @Column(name = "id", length = 20, nullable = false)
    private Long id;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "mail", length = 255, nullable = false)
    private String mail;

    @Column(name = "relevance", length = 3, precision = 2, nullable = false)
    private Double relevance;

    @Column(name = "job", length = 3, precision = 2, nullable = false)
    private String job;

    @Column(name = "company_name", length = 3, precision = 2, nullable = false)
    private String companyName;
    
    @Column(name = "company_link", length = 3, precision = 2, nullable = false)
    private String companyLink;

    public Mail() {
    }

    public Mail(String name, String mail, double relevance, String job, String companyName, String companyLink) {
        this.name = name;
        this.mail = mail;
        this.relevance = relevance;
        this.job = job;
        this.companyName = companyName;
        this.companyLink = companyLink;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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
        return "Mail{" +
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
