package com.tandt53.api;

import okhttp3.MediaType;

public class RequestBodyRaw extends RequestBody {
    private String body;
    private MediaType type;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public MediaType getType() {
        return type;
    }

    public void setType(MediaType type) {
        this.type = type;
    }

    @Override
    public okhttp3.RequestBody createBody() {
        return okhttp3.RequestBody.create(type, body);
    }
}
