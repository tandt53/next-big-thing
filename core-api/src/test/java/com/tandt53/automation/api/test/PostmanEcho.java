package com.tandt53.automation.api.test;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;

public class PostmanEcho {

    public static void main(String[] args) throws IOException {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        Request.Builder requestBuilder = new Request.Builder();
        HttpUrl.Builder urlBuilder = new HttpUrl.Builder();

        urlBuilder.scheme("https").host("postman-echo.com");
        urlBuilder.addPathSegment("get");
        urlBuilder.addQueryParameter("foo1", "bar");
        urlBuilder.addQueryParameter("foo2", "bar");

        requestBuilder.url(urlBuilder.build());
        System.out.println(builder.build().newCall(requestBuilder.build()).execute().body().string());
    }
}
