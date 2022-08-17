package async.oauth2.client;

import async.oauth2.DiscordAuthentication;
import async.oauth2.Settings;

import com.google.gson.reflect.TypeToken;
import lombok.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestOperations;

import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
public class Client implements HttpClientRequester {

    @NonNull private DiscordAuthentication authentication;
    private OkHttpClient client;

    public void build() {
        client = new OkHttpClient();
    }

    @SneakyThrows
    @Override
    public <T> CompletableFuture<T> get(String url, String token, Class<T> responseType) {
        var request = new Request.Builder().url(url)
                .header("Authorization", "Bearer " + token)
                .get()
                .build();

        return asyncResponse(client, request, responseType);
    }

    @SneakyThrows
    public <T> CompletableFuture<T> get(String url, TypeToken<T> responseType) {
        var request = new Request.Builder().url(url)
                .header("Authorization", "Bot <token>")
                .get()
                .build();

        return asyncResponse(client, request, responseType);
    }

    @SneakyThrows
    public <T> CompletableFuture<T> get(String url, String token, TypeToken<T> responseType) {
        var request = new Request.Builder().url(url)
                .header("Authorization", "Bearer " + token)
                .get()
                .build();

        return asyncResponse(client, request, responseType);
    }

    @Override
    public <T> T post(RestOperations operations, String url, Object requestBody, Class<T> responseType) {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add(HttpHeaders.USER_AGENT, Settings.DEFAULT_USER_AGENT);

        var response = operations.exchange(url, HttpMethod.POST, new HttpEntity<>(requestBody, headers), responseType);

        return response.getBody();
    }

    @Override
    public <T> T put(String url, Class<T> responseType, Object requestBody) {
        return null;
    }

    @Override
    public <T> T put(String url, Class<T> responseType) {
        return null;
    }

    @Override
    public void delete(String url) {

    }
}
