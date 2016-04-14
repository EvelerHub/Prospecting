package com.entity.leadpage;

import java.io.Serializable;

/**
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
    public String toString() {
        return "LeadId{" +
                "id=" + id +
                '}';
    }
}
