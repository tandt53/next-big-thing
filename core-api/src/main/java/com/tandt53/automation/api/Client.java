package com.tandt53.automation.api;

import okhttp3.*;

import javax.net.ssl.*;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Client {
    private static final int DEFAULT_TIMEOUT = -1;
    private static final int DEFAULT_CALL_TIMEOUT = 30;
    private final TimeUnit TIME_UNIT = TimeUnit.MILLISECONDS;

    private OkHttpClient.Builder builder;
    private OkHttpClient client;

    private boolean isFollowRedirects = true;
    private boolean isAllowInsecure = false;
    public int connectTimeout = DEFAULT_TIMEOUT;
    private int readTimeout = DEFAULT_TIMEOUT;
    private long callTimeout = DEFAULT_CALL_TIMEOUT;

    public Client() {
        this.builder = new OkHttpClient.Builder();
        builder.callTimeout(callTimeout, TIME_UNIT);
    }

    public void setInterceptor() {

    }

    public void createClient() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {

        builder.followRedirects(isFollowRedirects);
        if (connectTimeout != DEFAULT_TIMEOUT) {
            builder.connectTimeout(connectTimeout, TIME_UNIT);
        }

        if (readTimeout != DEFAULT_TIMEOUT) {
            builder.readTimeout(readTimeout, TIME_UNIT);
        }

        if (isAllowInsecure) {
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init((KeyStore) null);
            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
            if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
                throw new IllegalStateException("Unexpected default trust managers:"
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
        client = builder.build();
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

    private static SSLSocketFactory createInsecureSslSocketFactory() {
        try {
            SSLContext context = SSLContext.getInstance("TLS");
            TrustManager permissive = new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType)
                        throws CertificateException {
                }
                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType)
                        throws CertificateException {
                }
                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };
            context.init(null, new TrustManager[] { permissive }, null);
            return context.getSocketFactory();
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }

    public RestResponse send(RestRequest request) throws IOException {
        return new RestResponse(client.newCall(request.createRequest()).execute());
    }

}
