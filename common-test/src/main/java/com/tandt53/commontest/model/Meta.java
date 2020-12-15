package com.tandt53.commontest.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class Meta<T extends Meta<?>> {

    private Map<String, T> metas;

    public void add(String key, T value) {
        metas.put(key, value);
    }

    public T get(String key){
        return metas.get(key);
    }

}
