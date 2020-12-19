package com.tandt53.api;

import okhttp3.Request;

public class RestRequest {

    private Request.Builder builder;

    public Request createRequest() {
        return builder.build();
    }

    public RestRequest(RequestUrl httpUrl) {
        builder = new Request.Builder();
        builder.url(httpUrl.createHttpUrl());
    }

    public void header(RestHeader headers) {
        builder.headers(headers.createHeaders());
    }

    public void get() {
        builder.get();
    }

    public void delete() {
        builder.delete();
    }

    public void delete(RestBody body) {
        builder.delete(body.createBody());
    }

    public void post(RestBody body) {
        builder.post(body.createBody());
    }

    public void patch(RestBody body) {
        builder.patch(body.createBody());
    }

    public void put(RestBody body) {
        builder.put(body.createBody());
    }

}
