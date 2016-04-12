package com.entity;

import com.models.Mail;

import java.io.Serializable;
import java.util.List;

/**
 * @author Alexander Eveler
 */
public class MailList implements Serializable {

    private static final long serialVersionUID = 266899101846142520L;

    private long total;
    private List<Mail> rows;

    public MailList() {
        total = 0;
    }

    public MailList(List<Mail> mails) {
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

        MailList mailList = (MailList) o;

        if (total != mailList.total) return false;
        return rows != null ? rows.equals(mailList.rows) : mailList.rows == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (total ^ (total >>> 32));
        result = 31 * result + (rows != null ? rows.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MailList{" +
                "total=" + total +
                ", rows=" + rows +
                '}';
    }
}
