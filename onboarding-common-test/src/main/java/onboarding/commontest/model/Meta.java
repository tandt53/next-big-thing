package onboarding.commontest.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class Meta<T extends Meta<?>> {

    private Map<String, T> meta;

    public void add(String key, T value) {
        meta.put(key, value);
    }

    public T get(String key){
        return meta.get(key);
    }

}
