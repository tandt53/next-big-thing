package com.tandt53.api;

import okhttp3.FormBody;

public class RequestBodyForm extends RequestBody {

    private FormBody.Builder builder;


    public RequestBodyForm() {
        builder = new FormBody.Builder();
    }

    public void add(String name, String value){
        builder.add(name, value);
    }

    public void addEncoded(String name, String value){
        builder.addEncoded(name, name);
    }

    @Override
    public okhttp3.RequestBody createBody() {
        return builder.build();
    }
}
