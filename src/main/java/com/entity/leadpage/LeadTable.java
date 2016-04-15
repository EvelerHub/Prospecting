package com.entity.leadpage;

import com.models.Mail;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>Lead Table Entity for the corresponding transformation in JSON.<p/>
 *
 * @author Alexander Eveler
 */
public class LeadTable implements Serializable {

    private static final long serialVersionUID = 266899101846142520L;

    private long total;
    private List<LeadRow> rows;

    public LeadTable() {
        total = 0;
    }

    public LeadTable(List<Mail> mails) {
        List<LeadRow> leads = mails.stream().map(LeadRow::new).collect(Collectors.toList());
        this.rows = leads;
        this.total = leads.size();
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<LeadRow> getRows() {
        return rows;
    }

    public void setRows(List<LeadRow> leads) {
        this.rows = rows;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LeadTable leadTable = (LeadTable) o;

        if (total != leadTable.total) return false;

        return rows != null ? rows.equals(leadTable.rows) : leadTable.rows == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (total ^ (total >>> 32));
        result = 31 * result + (rows != null ? rows.hashCode() : 0);

        return result;
    }

    @Override
    public String toString() {
        return "Leads{" +
                "total=" + total +
                ", rows=" + rows +
                '}';
    }
}
