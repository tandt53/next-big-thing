package com.tandt53.automation.api;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class RestBodyRaw extends RestBody {
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
    public RequestBody createBody() {
        return RequestBody.create(body, type);
    }
}
