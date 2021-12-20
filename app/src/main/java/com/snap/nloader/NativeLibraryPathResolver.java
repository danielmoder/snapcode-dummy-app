package com.snap.nloader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class NativeLibraryPathResolver {
    private static final String TAG = "NLOader";
    private final ClassLoader classLoader;
    private Method findLibrary;

    private NativeLibraryPathResolver(Class cls) {
        this.classLoader = cls.getClassLoader();
    }

    private String findLibraryWithClassLoader(String str) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        if (this.findLibrary == null) {
            this.findLibrary = this.classLoader.getClass().getMethod("findLibrary", String.class);
        }
        return (String) this.findLibrary.invoke(this.classLoader, str);
    }

    public static NativeLibraryPathResolver forClass(Class cls) {
        return new NativeLibraryPathResolver(cls);
    }

    public String resolve(String str) {
        try {
            String findLibraryWithClassLoader = findLibraryWithClassLoader(str);
            if (findLibraryWithClassLoader != null && !findLibraryWithClassLoader.isEmpty()) {
                return findLibraryWithClassLoader;
            }
        } catch (Exception unused) {
        }
        return System.mapLibraryName(str);
    }
}
