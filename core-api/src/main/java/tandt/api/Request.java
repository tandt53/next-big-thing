package tandt.api;

public class Request {

    private okhttp3.Request.Builder builder;

    public okhttp3.Request createRequest() {
        return builder.build();
    }

    public Request(RequestUrl httpUrl) {
        builder = new okhttp3.Request.Builder();
        builder.url(httpUrl.createHttpUrl());
    }

    public void header(RequestHeader headers) {
        builder.headers(headers.createHeaders());
    }

    public void get() {
        builder.get();
    }

    public void delete() {
        builder.delete();
    }

    public void delete(RequestBody body) {
        builder.delete(body.createBody());
    }

    public void post(RequestBody body) {
        builder.post(body.createBody());
    }

    public void patch(RequestBody body) {
        builder.patch(body.createBody());
    }

    public void put(RequestBody body) {
        builder.put(body.createBody());
    }

}
