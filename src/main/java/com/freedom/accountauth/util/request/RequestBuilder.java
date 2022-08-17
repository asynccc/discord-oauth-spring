package com.freedom.accountauth.util.request;

import lombok.Builder;
import lombok.Data;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.util.Map;

@Builder(setterPrefix = "with")
@Data
public class RequestBuilder {

    private String url;
    private HttpType type;
    private Map<String, String> headers;

    public String execute() {
        var request = new Request.Builder()
                .url(url);

        headers.forEach(request::header);

        switch (type) {
            case GET:
                request.get();
            case DELETE:
                request.delete();
        }

        try (var response = new OkHttpClient().newCall(request.build()).execute()) {
            return response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
