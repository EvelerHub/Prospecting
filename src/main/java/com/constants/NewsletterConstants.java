package com.constants;

/**
 * <p>Constants for newsletter email newsletter</p>
 *
 * @author Alexander Eveler
 */
public class NewsletterConstants {

    public static final String MAIL = "hello@prospecting.io";

    public static final String SUBJECT = "ProspectingIO";

    public class Contents{

        public static final String ACTIVE_ACCOUNT_HTML = "<HTML><body>" +
                "<a href=\"http://5.34.183.10:8080/activation/account?key=123\">activation</a>" +
                "</body></HTML>";

        public static final String CHANGE_PASSWORD_HTML = "<HTML><body>" +
                "<a href=\"http://5.34.183.10:8080/activation/password?key=123\">change password</a>" +
                "</body></HTML>";

        public static final String CHANGE_MAIL_HTML = "<HTML><body>" +
                "<a href=\"http://5.34.183.10:8080/activation/mail?key=123\">change e-mail</a>" +
                "</body></HTML>";

        public static final String FORGET_PASSWORD_HTML = "<HTML><body>" +
                "<a href=\"http://5.34.183.10:8080/activation/newpassword?key=123\">change e-mail</a>" +
                "</body></HTML>";
    }


}
