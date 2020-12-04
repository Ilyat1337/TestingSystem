package com.ilya.utils;

import java.io.InputStream;

public class ResourceUtils {
    public static InputStream getResourceInputStream(String resourceName) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        return classLoader.getResourceAsStream(resourceName);
    }
}
