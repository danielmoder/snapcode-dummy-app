package com.snap.nloader.android;
import android.os.Trace;

import com.snap.nloader.LoadComponentDelegate;
import com.snap.nloader.NLOader;
import com.snap.nloader.NativeLibraryPathResolver;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DefaultLoadComponentDelegate implements LoadComponentDelegate {
    private static final String TAG = "NLOader";
    private final NativeLibraryPathResolver libraryPathResolver = NativeLibraryPathResolver.forClass(NLOader.class);
    private final Set<String> loadedLibraries = new HashSet();

    private void loadLibraryCached(String str) {
        if (!this.loadedLibraries.contains(str)) {
            Trace.beginSection("NLOader: loading " + str);
            try {
                loadLibrary(str);
                Trace.endSection();
                this.loadedLibraries.add(str);
            } catch (Throwable th) {
                Trace.endSection();
                throw th;
            }
        }
    }

    private native void nativeInvokeNativeInitializer(String str, String str2);

    @Override
    public void initializeComponent(String str, String str2, List<String> list, String str3) {
        loadComponentLibrariesIfNeeded(str2, list);
        invokeNativeInitializer(str2, str3);
    }

    public final void invokeNativeInitializer(String str, String str2) {
        if (str2 != null && !str2.isEmpty() && !str2.equals("JNI_OnLoad")) {
            loadLibraryCached("nloader");
            nativeInvokeNativeInitializer(this.libraryPathResolver.resolve(str), str2);
        }
    }

    public final void loadComponentLibrariesIfNeeded(String str, List<String> list) {
        for (String str2 : list) {
            loadLibraryCached(str2);
        }
        loadLibraryCached(str);
    }

    @Override
    public void loadLibrary(String str) {
        System.loadLibrary(str);
    }
}
