package com.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "plans")
public class Plan extends BaseEntity implements Serializable{

    // FIXME: 12.04.16 Entity must be serializable!

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private double price;

    @Column(name = "published")
    private boolean published;

    @Column(name = "billing_interval")
    private String billingInterval;

    @Column(name = "prospect_limit")
    private int prospectLimit;

    @Column(name = "found_limit")
    private int foundLimit;

    @Column(name = "searches_limit")
    private int searchesLimit;

    @Column(name = "trial_period")
    private int trialPeriod;

    @Column(name = "statement_descriptor")
    private String statementDescriptor;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", columnDefinition = "TIMESTAMP")
    private Date createTime;

    @Column(name = "currency")
    private String currency;

    @Column(name = "interval_count")
    private int intervalCount;

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<User> users;

    public Plan() {
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setIntervalCount(int intervalCount) {
        this.intervalCount = intervalCount;
    }

    public int getIntervalCount() {
        return intervalCount;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public String getBillingInterval() {
        return billingInterval;
    }

    public void setBillingInterval(String billingInterval) {
        this.billingInterval = billingInterval;
    }

    public int getProspectLimit() {
        return prospectLimit;
    }

    public void setProspectLimit(int prospectLimit) {
        this.prospectLimit = prospectLimit;
    }

    public int getFoundLimit() {
        return foundLimit;
    }

    public void setFoundLimit(int foundLimit) {
        this.foundLimit = foundLimit;
    }

    public int getSearchesLimit() {
        return searchesLimit;
    }

    public void setSearchesLimit(int searchesLimit) {
        this.searchesLimit = searchesLimit;
    }

    public int getTrialPeriod() {
        return trialPeriod;
    }

    public void setTrialPeriod(int trialPeriod) {
        this.trialPeriod = trialPeriod;
    }

    public String getStatementDescriptor() {
        return statementDescriptor;
    }

    public void setStatementDescriptor(String statementDescriptor) {
        this.statementDescriptor = statementDescriptor;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getLimitString() {
        String result;
        if (prospectLimit == 0)
            result = "∞";
        else
        result = String.valueOf(prospectLimit);

        return result;
    }

    public String getSearchesString() {
        String result;
        if (searchesLimit == 0)
            result = "∞";
        else
            result = String.valueOf(searchesLimit);

        return result;
    }

    public String getFoundEmailsString() {
        String result;
        if (foundLimit == 0)
            result = "∞";
        else
            result = String.valueOf(foundLimit);

        return result;
    }

    public String getPriceString() {
        String result;
        if (price == 0)
            result = "Free";
        else
            result = "USD " + price + " / " + billingInterval.toLowerCase();
        return result;
    }

    public String getTrialString() {
        String result;
        if (trialPeriod == 0)
            result = "None";
        else
            result = String.valueOf(trialPeriod);
        return result;
    }

    public String getPublishedString() {
        String result;
        if (published)
            result = "Yes";
        else
            result = "No";
        return result;
    }

    @Override
    public String toString() {
        return "Plan{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", published=" + published +
                ", billingInterval='" + billingInterval + '\'' +
                ", prospectLimit=" + prospectLimit +
                ", foundLimit=" + foundLimit +
                ", searchesLimit=" + searchesLimit +
                ", trialPeriod=" + trialPeriod +
                ", statementDescriptor='" + statementDescriptor + '\'' +
                ", createTime=" + createTime +
                ", currency='" + currency + '\'' +
                ", intervalCount=" + intervalCount +
                '}';
    }

}
