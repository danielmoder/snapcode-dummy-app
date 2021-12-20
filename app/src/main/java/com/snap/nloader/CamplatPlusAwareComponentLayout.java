package com.snap.nloader;

import java.util.Collections;
import java.util.List;

public final class CamplatPlusAwareComponentLayout implements NativeComponentsLayout {
    @Override
    public NativeComponentsLayout.ComponentHostInfo getComponentHostInfo(String str) {
        //str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -2085857262:
                if (str.equals("transcoding")) {
                    c = 0;
                    break;
                }
                break;
            case -1010579395:
                if (str.equals("opencv")) {
                    c = 2;
                    break;
                }
                break;
            case -832627141:
                if (str.equals("previewcv")) {
                    c = 4;
                    break;
                }
                break;
            case 46975609:
                if (str.equals("catalyst")) {
                    c = 5;
                    break;
                }
                break;
            case 284868935:
                if (str.equals("snapscan")) {
                    c = 6;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return new NativeComponentsLayout.ComponentHostInfo("camplat+.b3fb6e340e2db4f5dac03e8150783e4a2f0c33c6", "initialize_native_component_transcoding");
            case 1:
                return new NativeComponentsLayout.ComponentHostInfo("camplat+.b3fb6e340e2db4f5dac03e8150783e4a2f0c33c6", "initialize_native_component_labscv");
            case 2:
                return new NativeComponentsLayout.ComponentHostInfo("camplat+.b3fb6e340e2db4f5dac03e8150783e4a2f0c33c6", "");
            case 3:
                return new NativeComponentsLayout.ComponentHostInfo("camplat+.b3fb6e340e2db4f5dac03e8150783e4a2f0c33c6", "initialize_native_component_snapcv");
            case 4:
                return new NativeComponentsLayout.ComponentHostInfo("camplat+.b3fb6e340e2db4f5dac03e8150783e4a2f0c33c6", "");
            case 5:
                return new NativeComponentsLayout.ComponentHostInfo("camplat+.b3fb6e340e2db4f5dac03e8150783e4a2f0c33c6", "initialize_native_component_catalyst");
            case 6:
                return new NativeComponentsLayout.ComponentHostInfo("camplat+.b3fb6e340e2db4f5dac03e8150783e4a2f0c33c6", "initialize_native_component_snapscan");
            case 7:
                return new NativeComponentsLayout.ComponentHostInfo("camplat+.b3fb6e340e2db4f5dac03e8150783e4a2f0c33c6", "initialize_native_component_lenscore");
            default:
                throw new IllegalArgumentException("Unknown component name: "+ str);
        }
    }

    @Override // com.snap.nloader.android.NativeComponentsLayout
    public List<String> getRuntimeDependenciesOrdered(String str) {
        str.hashCode();
        if (str.equals("camplat+.b3fb6e340e2db4f5dac03e8150783e4a2f0c33c6")) {
            return Collections.emptyList();
        }
        throw new IllegalArgumentException("Unknown library name: "+ str);
    }
}