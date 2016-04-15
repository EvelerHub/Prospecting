package com.entity.leadpage;

import com.models.Mail;

import java.io.Serializable;

/**
 * <p>Lead Row of Lead Table Entity for the corresponding transformation in JSON.<p/>
 *
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LeadRow leadRow = (LeadRow) o;

        if (id != leadRow.id) return false;
        if (Double.compare(leadRow.relevance, relevance) != 0) return false;
        if (name != null ? !name.equals(leadRow.name) : leadRow.name != null) return false;
        if (mail != null ? !mail.equals(leadRow.mail) : leadRow.mail != null) return false;
        if (job != null ? !job.equals(leadRow.job) : leadRow.job != null) return false;
        if (companyName != null ? !companyName.equals(leadRow.companyName) : leadRow.companyName != null) return false;

        return companyLink != null ? companyLink.equals(leadRow.companyLink) : leadRow.companyLink == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
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
