package com.tandt53.automation.api;

import okhttp3.Headers;

import java.util.Map;

public class RestHeader {

    private Headers.Builder builder;

    public RestHeader() {
        builder = new Headers.Builder();
    }

    public void add(String key, String value){
        builder.add(key, value);
    }

    public void add(String line){
        builder.add(line);
    }

    public void addAll(Map<String, String> headers){
        builder.addAll(Headers.of(headers));
    }

    public Headers createHeaders(){
        return builder.build();
    }

    public void remove(String key){
        builder.removeAll(key);
    }
}
