package com.services.newsletter;

import com.entity.newsletter.Newsletter;
import com.entity.ResponseMessage;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * <h1>Service for email newsletter </h1>
 * <p>Using via <a href="http://www.serversmtp.com/ru/ru_smtp-api">smtp-turbo API</a></p>
 *
 * @author Alexander Eveler
 */
public interface NewsletterService {

    @POST(value = "/api/mail/send")
    ResponseMessage sendMessage(@Body Newsletter newsletter);
}
