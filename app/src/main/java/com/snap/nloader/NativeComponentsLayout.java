package com.snap.nloader;

import java.util.List;

public interface NativeComponentsLayout {

    public static final class ComponentHostInfo {
        public final String hostLibraryName;
        public final String nativeInitializerSymbol;

        public ComponentHostInfo(String str, String str2) {
            this.hostLibraryName = str;
            if (!"JNI_OnLoad".equals(str2)) {
                this.nativeInitializerSymbol = str2;
                return;
            }
            throw new IllegalArgumentException("JNI_OnLoad is a global initializer and will be called anyway upon library load. If there is no other init function for your component - use \"\" as an init symbol name");
        }
    }

    ComponentHostInfo getComponentHostInfo(String str);

    List<String> getRuntimeDependenciesOrdered(String str);
}
