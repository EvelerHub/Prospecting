package com.services.newsletter.realization;

import com.entity.newsletter.Newsletter;
import com.entity.ResponseMessage;
import com.services.newsletter.NewsletterService;
import org.springframework.stereotype.Service;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.JacksonConverter;
import retrofit.http.Body;

/**
 * @author Alexander Eveler
 */
@Service
public class NewsletterServiceImpl implements NewsletterService {

    private RequestInterceptor requestInterceptor = request -> {
        request.addHeader("authuser", "hello@prospecting.io");
        request.addHeader("authpass", "915-Sierra");
        request.addHeader("Content-Type", "application/json");
    };

    private NewsletterService newsletterService = new RestAdapter.Builder()
            .setEndpoint("https://api.turbo-smtp.com")
            .setConverter(new JacksonConverter())
            .setRequestInterceptor(requestInterceptor)
            .build()
            .create(NewsletterService.class);

    @Override
    public ResponseMessage sendMessage(@Body Newsletter newsletter) {

        return newsletterService.sendMessage(newsletter);
    }
}
