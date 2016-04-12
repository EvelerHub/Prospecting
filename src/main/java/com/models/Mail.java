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
    private long id;

    @NotNull
    @NotEmpty
    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @NotNull
    @NotEmpty
    @Column(name = "surname", length = 255, nullable = false)
    private String surname;

    @NotNull
    @NotEmpty
    @Column(name = "mail", length = 255, nullable = false)
    private String mail;

    @NotNull
    @NotEmpty
    @Column(name = "relevance", length = 3, precision = 2, nullable = false)
    private double relevance;

    public Mail() {
    }

    public Mail(String name, String surname, String mail, double relevance) {
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.relevance = relevance;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mail mail1 = (Mail) o;

        if (id != mail1.id) return false;
        if (Double.compare(mail1.relevance, relevance) != 0) return false;
        if (name != null ? !name.equals(mail1.name) : mail1.name != null) return false;
        if (surname != null ? !surname.equals(mail1.surname) : mail1.surname != null) return false;
        return mail != null ? mail.equals(mail1.mail) : mail1.mail == null;

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
        return result;
    }

    @Override
    public String toString() {
        return "Mail{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", mail='" + mail + '\'' +
                ", relevance=" + relevance +
                '}';
    }
}
