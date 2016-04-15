package com.entity.leadpage;

import java.io.Serializable;

/**
 * <p>Lead ID Entity for the corresponding transformation in JSON.<p/>
 *
 * @author Alexander Eveler
 */
public class LeadId implements Serializable {

    private static final long serialVersionUID = 1741471547450309269L;

    private Long id;

    public LeadId() {
    }

    public LeadId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LeadId leadId = (LeadId) o;

        return id != null ? id.equals(leadId.id) : leadId.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "LeadId{" +
                "id=" + id +
                '}';
    }
}
