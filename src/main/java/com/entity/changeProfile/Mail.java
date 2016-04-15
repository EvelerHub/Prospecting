package com.entity.changeProfile;

import java.io.Serializable;

/**
 * @author Alexander Eveler
 */
public class Mail implements Serializable{

    private static final long serialVersionUID = -1694591068013684341L;

    private String mail;

    public Mail() {
    }

    public Mail(String mail) {
        this.mail = mail;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mail mail1 = (Mail) o;

        return mail != null ? mail.equals(mail1.mail) : mail1.mail == null;
    }


    @Override
    public int hashCode() {
        return mail != null ? mail.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Mail{" +
                "mail='" + mail + '\'' +
                '}';
    }
}
