package com.snap.nloader;

import java.util.List;

public interface LoadComponentDelegate {
    void initializeComponent(String str, String str2, List<String> list, String str3);

    void loadLibrary(String str);
}
