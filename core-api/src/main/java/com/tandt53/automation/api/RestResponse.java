package com.tandt53.automation.api;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.tandt53.automation.api.path.JsonPath;
import com.tandt53.automation.dataprovider.json.JsonParser;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.lang.reflect.Type;

public class RestResponse {
    private Response response;
    private ResponseBody responseBody;

    public RestResponse(Response response) {
        this.response = response;
        this.responseBody = response.body();

    }

    public int getStatusCode() {
        return response.code();
    }

    public <T> T get(String jsonPath, Class<T> t) throws IOException {
        return JsonParser.fromJsonStringToObject(responseBody.string(), jsonPath, t);
    }

    // save response body into a file
    public void save(String filePath){

    }

    public JsonPath jsonPath() throws IOException {
        return new JsonPath(responseBody.string());
    }

    public String body() throws IOException {
        return this.response.body().string();
    }

//    public <T> T convert(Type type) throws IOException {
//        Gson gson = new Gson();
//        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
//        JsonReader jsonReader = gson.newJsonReader(responseBody.charStream());
//        try {
//            T result = (T) adapter.read(jsonReader);
//            if (jsonReader.peek() != JsonToken.END_DOCUMENT) {
//                throw new JsonIOException("JSON document was not fully consumed.");
//            }
//            return result;
//        } finally {
//            responseBody.close();
//        }
//    }

}
