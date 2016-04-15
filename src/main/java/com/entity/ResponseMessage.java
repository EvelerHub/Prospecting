package com.entity;

import com.entity.newsletter.Error;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

/**
 * @author Alexander Eveler
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseMessage implements Serializable {

    private static final long serialVersionUID = 3681448202170472747L;

    private String message;
    private List<Error> errors;

    public ResponseMessage() {
    }

    public ResponseMessage(String message, List<Error> errors) {
        this.message = message;
        this.errors = errors;
    }

    public ResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResponseMessage that = (ResponseMessage) o;

        if (message != null ? !message.equals(that.message) : that.message != null) return false;

        return errors != null ? errors.equals(that.errors) : that.errors == null;
    }

    @Override
    public int hashCode() {
        int result = message != null ? message.hashCode() : 0;
        result = 31 * result + (errors != null ? errors.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ResponseMessage{" +
                "message='" + message + '\'' +
                ", errors=" + errors +
                '}';
    }
}
