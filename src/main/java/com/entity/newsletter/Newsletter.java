package com.entity.newsletter;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @author Alexander Eveler
 */
public class Newsletter implements Serializable{

    private static final long serialVersionUID = 3566503100638231259L;

    private String from;
    private String to;
    private String content;
    private String subject;

    @JsonProperty("html_content")
    private String htmlСontent;

    public Newsletter() {
    }

    public Newsletter(String from, String to, String content, String subject, String htmlСontent) {
        this.from = from;
        this.to = to;
        this.content = content;
        this.subject = subject;
        this.htmlСontent = htmlСontent;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getHtmlСontent() {
        return htmlСontent;
    }

    public void setHtmlСontent(String htmlСontent) {
        this.htmlСontent = htmlСontent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Newsletter that = (Newsletter) o;

        if (from != null ? !from.equals(that.from) : that.from != null) return false;
        if (to != null ? !to.equals(that.to) : that.to != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (subject != null ? !subject.equals(that.subject) : that.subject != null) return false;

        return htmlСontent != null ? htmlСontent.equals(that.htmlСontent) : that.htmlСontent == null;
    }

    @Override
    public int hashCode() {
        int result = from != null ? from.hashCode() : 0;
        result = 31 * result + (to != null ? to.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (htmlСontent != null ? htmlСontent.hashCode() : 0);

        return result;
    }

    @Override
    public String toString() {
        return "Newsletter{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", content='" + content + '\'' +
                ", subject='" + subject + '\'' +
                ", htmlСontent='" + htmlСontent + '\'' +
                '}';
    }
}
