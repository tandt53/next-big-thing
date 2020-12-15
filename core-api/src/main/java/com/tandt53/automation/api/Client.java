package com.tandt53.automation.api;

import okhttp3.Authenticator;
import okhttp3.ConnectionPool;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

import javax.net.ssl.*;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Client {
    private static final int _TIMEOUT = -1;
    private static final int _CALL_TIMEOUT = 30000;
    private final TimeUnit TIME_UNIT = TimeUnit.MILLISECONDS;

    private OkHttpClient.Builder builder;
    private OkHttpClient client;

    private boolean isFollowRedirects = true;
    private boolean isAllowInsecure = false;
    public int connectTimeout = _TIMEOUT;
    private int readTimeout = _TIMEOUT;
    private long callTimeout = _CALL_TIMEOUT;

    public Client() {
        this.builder = new OkHttpClient.Builder();
        builder.callTimeout(callTimeout, TIME_UNIT);
    }

    public OkHttpClient createClient() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {

        builder.followRedirects(isFollowRedirects);
        if (connectTimeout != _TIMEOUT) {
            builder.connectTimeout(connectTimeout, TIME_UNIT);
        }

        if (readTimeout != _TIMEOUT) {
            builder.readTimeout(readTimeout, TIME_UNIT);
        }

        if (isAllowInsecure) {
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init((KeyStore) null);
            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
            if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
                throw new IllegalStateException("Unexpected  trust managers:"
                        + Arrays.toString(trustManagers));
            }
            X509TrustManager trustManager = (X509TrustManager) trustManagers[0];

            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[] { trustManager }, null);
            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            builder.sslSocketFactory(sslSocketFactory, trustManager);
        }
        // If we don't set this reference, there's no way to clean shutdown persistent connections.
        builder.connectionPool(new ConnectionPool());
        return builder.build();
    }

    public void addInterceptor(Interceptor interceptor) {
        builder.addInterceptor(interceptor);
    }

    public void addNetworkInterceptor(Interceptor interceptor) {
        builder.addNetworkInterceptor(interceptor);
    }

    public void authenticator(Authenticator authenticator) {
        builder.authenticator(authenticator);
    }

    public RestResponse send(RestRequest request) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        return new RestResponse(createClient().newCall(request.createRequest()).execute());
    }

}
