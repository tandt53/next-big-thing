package com.tandt53.automation.api;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class RestBodyForm extends RestBody {

    private FormBody.Builder builder;

    public RestBodyForm() {
        builder = new FormBody.Builder();
    }

    public void add(String name, String value){
        builder.add(name, value);
    }

    public void addEncoded(String name, String value){
        builder.addEncoded(name, name);
    }

    @Override
    public RequestBody createBody() {
        return builder.build();
    }
}
