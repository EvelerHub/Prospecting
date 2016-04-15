package com.entity.changeProfile;

import java.io.Serializable;

/**
 * @author Alexander Eveler
 */
public class Passwords implements Serializable {

    private static final long serialVersionUID = 1401864125833004263L;

    private String newPassword;
    private String oldPassword;

    public Passwords() {
    }

    public Passwords(String newPassword, String oldPassword) {
        this.newPassword = newPassword;
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Passwords passwords = (Passwords) o;

        if (newPassword != null ? !newPassword.equals(passwords.newPassword) : passwords.newPassword != null)
            return false;

        return oldPassword != null ? oldPassword.equals(passwords.oldPassword) : passwords.oldPassword == null;
    }

    @Override
    public int hashCode() {
        int result = newPassword != null ? newPassword.hashCode() : 0;
        result = 31 * result + (oldPassword != null ? oldPassword.hashCode() : 0);

        return result;
    }

    @Override
    public String toString() {
        return "Passwords{" +
                "newPassword='" + newPassword + '\'' +
                ", oldPassword='" + oldPassword + '\'' +
                '}';
    }
}
