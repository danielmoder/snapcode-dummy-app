package com.snap.nloader;

import com.snap.nloader.android.DefaultLoadComponentDelegate;

import java.util.HashSet;
import java.util.Set;

public class NLOader {
    private static final Set<String> initializedComponents = new HashSet();
    private static NativeComponentsLayout layout;
    private static DefaultLoadComponentDelegate defaultLoadComponentDelegate;

    public static synchronized void initializeNativeComponent(String str) {
        synchronized (NLOader.class) {
            NativeComponentsLayout nativeComponentsLayout = layout;
            if (nativeComponentsLayout == null) {
                throw new IllegalStateException("components layout is not set yet");
            } else if (defaultLoadComponentDelegate != null) {
                NativeComponentsLayout.ComponentHostInfo componentHostInfo = nativeComponentsLayout.getComponentHostInfo(str);
                if (componentHostInfo != null) {
                    Set<String> set = initializedComponents;
                    if (!set.contains(str)) {
                        DefaultLoadComponentDelegate defaultLoadComponentDelegate2 = defaultLoadComponentDelegate;
                        String str2 = componentHostInfo.hostLibraryName;
                        defaultLoadComponentDelegate2.initializeComponent(str, str2, layout.getRuntimeDependenciesOrdered(str2), componentHostInfo.nativeInitializerSymbol);
                        set.add(str);
                        return;
                    }
                    return;
                }
                throw new IllegalArgumentException("unknown component: " + str);
            } else {
                throw new IllegalStateException("load delegate is not set yet");
            }
        }
    }

    public static synchronized void setDefaultLoadComponentDelegate(DefaultLoadComponentDelegate defaultLoadComponentDelegate2) {
        synchronized (NLOader.class) {
            if (defaultLoadComponentDelegate2 == null) {
                throw new IllegalArgumentException("Delegate can not be null");
            } else if (defaultLoadComponentDelegate == null) {
                defaultLoadComponentDelegate = defaultLoadComponentDelegate2;
            } else {
                throw new IllegalStateException("Delegate was set already");
            }
        }
    }

    public static synchronized void setNativeComponentsLayout(NativeComponentsLayout nativeComponentsLayout) {
        synchronized (NLOader.class) {
            if (nativeComponentsLayout == null) {
                throw new IllegalArgumentException("Layout can not be null");
            } else if (layout == null) {
                layout = nativeComponentsLayout;
            } else {
                throw new IllegalStateException("components layout was set already");
            }
        }
    }
}