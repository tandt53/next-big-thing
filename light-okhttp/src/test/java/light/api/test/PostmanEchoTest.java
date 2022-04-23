package light.api.test;

import light.api.Client;
import light.api.Request;
import light.api.RequestUrl;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import org.junit.Test;

public class PostmanEchoTest {

  @Test
    public void sampleTest() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        okhttp3.Request.Builder requestBuilder = new okhttp3.Request.Builder();
        HttpUrl.Builder urlBuilder = new HttpUrl.Builder();

        urlBuilder.scheme("https").host("postman-echo.com");
        urlBuilder.addPathSegments("get");
        urlBuilder.addQueryParameter("foo1", "bar");
        urlBuilder.addQueryParameter("foo2", "bar");

        requestBuilder.url(urlBuilder.build());
        System.out.println(builder.build().newCall(requestBuilder.build()).execute().body().string());

        Client client = new Client();
        RequestUrl url = new RequestUrl("https", "postman-echo.com", RequestUrl.EMPTY_PORT);
        url.addPath("get");
        url.addQueryParameter("foo1", "bar");
        url.addQueryParameter("foo2", "bar2");

        Request request = new Request(url);
        System.out.println(client.send(request).body());

    }


}
