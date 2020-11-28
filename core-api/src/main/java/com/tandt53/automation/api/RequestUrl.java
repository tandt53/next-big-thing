package com.tandt53.automation.api;

import com.tandt53.automation.api.exceptions.UrlException;
import okhttp3.HttpUrl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestUrl {

    private HttpUrl.Builder builder;
    public static final int EMPTY_PORT = -1;

    public RequestUrl(String protocol, String host, int port) {
        builder = new HttpUrl.Builder();
        builder.scheme(protocol)
                .host(host);
        if (port != EMPTY_PORT)
            builder.port(port);
    }

    public RequestUrl(String fullHostString) throws UrlException {
        if (fullHostString == null || fullHostString.isEmpty()) {
            throw new UrlException("fullHostString should not be null or empty");
        }

        Pattern p1 = Pattern.compile("^(http.*)\\:\\/\\/(.+)\\:([0-9]+)"); // protocol, host and port
        Pattern p2 = p1.compile("^(http.*)\\:\\/\\/(.[^:]+)$"); // protocol, host, without port

        Matcher m1 = p1.matcher(fullHostString);
        Matcher m2 = p2.matcher(fullHostString);

        String protocol = "";
        String host = "";
        int port = -1;
        if (m1.find()) {
            protocol = m1.group(1);
            host = m1.group(2);
            port = Integer.parseInt(m1.group(3));
        } else if (m2.find()) {
            protocol = m2.group(1);
            host = m2.group(2);
        } else {
            throw new UrlException("fullHostString should contain protocol, host and optional port");
        }
        builder = new HttpUrl.Builder();
        builder.scheme(protocol);
        builder.host(host);
        if (port != -1) {
            builder.port(port);
        }

    }

    public void addQueryParameter(String key, String value, boolean isEncoded) {
        if (isEncoded) {
            builder.addEncodedQueryParameter(key, value);
        } else {
            builder.addQueryParameter(key, value);
        }
    }

    public void addUsername(String username, boolean isEncoded) {
        if (isEncoded)
            builder.encodedUsername(username);
        else
            builder.username(username);
    }

    public void addPassword(String password, boolean isEncoded) {
        if (isEncoded)
            builder.encodedPassword(password);
        else
            builder.password(password);
    }

    public void setQueryParameter(String key, String value, boolean isEncoded) {
        if (isEncoded)
            builder.setEncodedQueryParameter(key, value);
        else
            builder.setQueryParameter(key, value);
    }

    public void removeQueryParameter(String key, boolean isEncoded) {
        if (isEncoded)
            builder.removeAllEncodedQueryParameters(key);
        else
            builder.removeAllQueryParameters(key);
    }

    public HttpUrl createHttpUrl() {
        return builder.build();
    }

    public void addPath(String pathSegment, boolean isEncoded) {
        if(isEncoded)
            builder.addEncodedPathSegments(pathSegment);
        else
            builder.addPathSegments(pathSegment);
    }
}
