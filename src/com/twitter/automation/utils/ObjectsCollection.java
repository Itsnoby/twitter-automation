package com.twitter.automation.utils;

import java.util.ArrayList;
import java.util.List;

public class ObjectsCollection<Y> {
    private  List<Y> collection = new ArrayList<Y>();

    @SuppressWarnings("unchecked")
    public <T extends Y> T getInstance(Class<T> clazz) {
        for (Y element : collection) {
            if (element.getClass().equals(clazz)) {
                return (T)element;
            }
        }

        try {
            T data = clazz.newInstance();
            collection.add(data);
            return data;
        } catch (Exception e) {
            return null;
        }
    }

    public void clear() {
        collection.clear();
    }

}
