package async.oauth2.client;

import async.oauth2.util.Util;
import com.google.gson.reflect.TypeToken;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.web.client.RestOperations;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

/**
 * HTTP Client implementations for the rest api connection.
 *
 * @author Async
 */
public interface HttpClientRequester {

    /**
     * Get rest api mapping.
     * This is used to get the rest api mapping for the client.
     *
     * @param url          api url
     * @param responseType response type
     * @param <T>          type of response object
     * @return return the response type object
     */
    <T> CompletableFuture<T> get(String url, String token, Class<T> responseType);

    /**
     * Post rest api mapping.
     * This method is used for posting data to the server.
     *
     * @param url          api url
     * @param requestBody  post request body
     * @param responseType response type
     */
    <T> T post(RestOperations operations, String url, Object requestBody, Class<T> responseType);

    /**
     * Put rest api mapping.
     * This method is used for putting data to the server.
     *
     * @param url          api url
     * @param responseType response type
     * @param requestBody  put request body
     * @param <T>          type of response object
     * @return return the response type object
     */
    <T> T put(String url, Class<T> responseType, Object requestBody);

    /**
     * Put rest api mapping.
     * This method is used for putting data to the server.
     *
     * @param url          api url
     * @param responseType response type
     * @param <T>          type of response object
     * @return return the response type object
     */
    <T> T put(String url, Class<T> responseType);

    /**
     * Delete rest api mapping.
     * This method is used for deleting data from the server.
     *
     * @param url api url
     */
    void delete(String url);

    default int responseCount(Response response) {
        int result = 1;
        while ((response = response.priorResponse()) != null) {
            result++;
        }
        return result;
    }

    default <T> T response(OkHttpClient client, Request request, Class<T> clazz) {
        try (Response response = client.newCall(request).execute()) {
            var body = response.body().string();

            if (!response.isSuccessful()) {
                return null;
            }

            return Util.getGson().fromJson(body, clazz);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return null;
    }

    default <T> T response(OkHttpClient client, Request request, TypeToken<T> clazz) {
        try (Response response = client.newCall(request).execute()) {
            var body = response.body().string();

            if (!response.isSuccessful()) {
                return null;
            }

            return Util.getGson().fromJson(body, clazz.getType());
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return null;
    }

    default <T> CompletableFuture<T> asyncResponse(OkHttpClient client, Request request, Class<T> clazz) {
        return CompletableFuture.supplyAsync(() -> response(client, request, clazz));
    }

    default <T> CompletableFuture<T> asyncResponse(OkHttpClient client, Request request, TypeToken<T> clazz) {
        return CompletableFuture.supplyAsync(() -> response(client, request, clazz));
    }

}