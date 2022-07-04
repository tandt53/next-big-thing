package onboarding.api;

import okhttp3.MediaType;
import okhttp3.MultipartBody;

import java.io.File;

public class RequestBodyMultiPart extends RequestBody {
    private static final MediaType MEDIA_TYPE = MultipartBody.FORM;
    private MediaType type = MEDIA_TYPE;
    private MultipartBody.Builder builder;

    public RequestBodyMultiPart() {
        builder = new MultipartBody.Builder();
    }

    public void addPart(String key, String value) {
        builder.addFormDataPart(key, value);
    }

    public void addPart(String key, String value, String filePath) {
        addPart(key, value, filePath, "application/octet-stream");
    }

    public void addPart(String key, String value, String filePath, String mediaType) {
        builder.addFormDataPart(key, value, okhttp3.RequestBody.create(MediaType.parse(mediaType), new File(filePath)));
    }

    public void setType(String type) {
        this.type = MediaType.parse(type);
    }

    public void setType(MediaType type){
        this.type = type;
    }

    @Override
    public okhttp3.RequestBody createBody() {
        builder.setType(type);
        return builder.build();
    }
}
