package com.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.engine.spi.Mapping;

import javax.persistence.*;
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

    @Column(name = "surname", length = 255, nullable = false)
    private String surname;

    @Column(name = "mail", length = 255, nullable = false)
    private String mail;

    @Column(name = "relevance", length = 3, precision = 2, nullable = false)
    private Double relevance;

    @Column(name = "job", length = 3, precision = 2, nullable = false)
    private String job;

    @OneToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    public Mail() {
    }

    public Mail(String name, String surname, String mail,
                Double relevance, String job, Company company, User user) {
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.relevance = relevance;
        this.job = job;
        this.company = company;
        this.user = user;
    }

    public Mail(String name, String surname, String mail,
                Double relevance, String job, Company company) {
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.relevance = relevance;
        this.job = job;
        this.company = company;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Double getRelevance() {
        return relevance;
    }

    public void setRelevance(Double relevance) {
        this.relevance = relevance;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mail mail1 = (Mail) o;

        if (id != null ? !id.equals(mail1.id) : mail1.id != null) return false;
        if (name != null ? !name.equals(mail1.name) : mail1.name != null) return false;
        if (surname != null ? !surname.equals(mail1.surname) : mail1.surname != null) return false;
        if (mail != null ? !mail.equals(mail1.mail) : mail1.mail != null) return false;
        if (relevance != null ? !relevance.equals(mail1.relevance) : mail1.relevance != null) return false;
        if (job != null ? !job.equals(mail1.job) : mail1.job != null) return false;
        if (company != null ? !company.equals(mail1.company) : mail1.company != null) return false;
        return user != null ? user.equals(mail1.user) : mail1.user == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        result = 31 * result + (relevance != null ? relevance.hashCode() : 0);
        result = 31 * result + (job != null ? job.hashCode() : 0);
        result = 31 * result + (company != null ? company.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Mail{" +
                "user=" + user +
                ", company=" + company +
                ", job='" + job + '\'' +
                ", relevance=" + relevance +
                ", mail='" + mail + '\'' +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
