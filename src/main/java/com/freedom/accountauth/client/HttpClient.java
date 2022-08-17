package com.freedom.accountauth.client;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class HttpClient {

    @Bean
    public OkHttpClient getClient() {
        return new OkHttpClient();
    }

}
