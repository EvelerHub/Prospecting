package com.entity.newsletter;

import java.io.Serializable;

/**
 * @author Alexander Eveler
 */
public class Error implements Serializable {

    private static final long serialVersionUID = 6563533118291732361L;

    private String error;

    public Error() {
    }

    public Error(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Error error1 = (Error) o;

        return error != null ? error.equals(error1.error) : error1.error == null;
    }

    @Override
    public int hashCode() {
        return error != null ? error.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Error{" +
                "error='" + error + '\'' +
                '}';
    }
}
