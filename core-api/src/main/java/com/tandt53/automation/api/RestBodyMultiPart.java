package com.tandt53.automation.api;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import java.io.File;

public class RestBodyMultiPart extends RestBody {
    private final String DEFAULT_MEDIA_TYPE = "multipart/form-data";
    private String type = DEFAULT_MEDIA_TYPE;
    MultipartBody.Builder builder;

    public RestBodyMultiPart() {
        builder = new MultipartBody.Builder();
    }

    public void addPart(String key, String value) {
        builder.addFormDataPart(key, value);
    }

    public void addPart(String key, String value, String filePath) {
        addPart(key, value, filePath, "application/octet-stream");
    }

    public void addPart(String key, String value, String filePath, String mediaType) {
        builder.addFormDataPart(key, value, RequestBody.create(new File(filePath), MediaType.parse(mediaType)));
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public RequestBody createBody() {
        builder.setType(MediaType.parse(type));
        return builder.build();
    }
}
