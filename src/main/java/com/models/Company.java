package com.models;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Alexander eveler
 */
@Entity
@Table(name = "companies")
public class Company implements Serializable {

    private static final long serialVersionUID = 7049594321182567179L;

    @Id
    @GeneratedValue
    @Column(name = "id", length = 20, nullable = false)
    private long id;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "link", length = 255, nullable = false)
    private String link;

    public Company() {
    }

    public Company(String link, String name) {
        this.link = link;
        this.name = name;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;

        if (id != company.id) return false;
        if (name != null ? !name.equals(company.name) : company.name != null) return false;
        return link != null ? link.equals(company.link) : company.link == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (link != null ? link.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
