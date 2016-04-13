package com.entity.responses;

import com.models.Mail;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Entity for the corresponding transformation in JSON.<p/>
 *
 * @author Alexander Eveler
 */
public class Mails implements Serializable {

    private static final long serialVersionUID = 266899101846142520L;

    private long total;
    private List<Mail> rows;

    public Mails() {
        total = 0;
    }

    public Mails(List<Mail> mails) {
        this.rows = mails;
        this.total = mails.size();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<Mail> getRows() {
        return rows;
    }

    public void setRows(List<Mail> rows) {
        this.rows = rows;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mails mails = (Mails) o;

        if (total != mails.total) return false;
        return rows != null ? rows.equals(mails.rows) : mails.rows == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (total ^ (total >>> 32));
        result = 31 * result + (rows != null ? rows.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Mails{" +
                "total=" + total +
                ", rows=" + rows +
                '}';
    }
}
