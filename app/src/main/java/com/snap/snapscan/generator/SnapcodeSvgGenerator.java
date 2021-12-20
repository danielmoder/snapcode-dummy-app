package com.snap.snapscan.generator;

import com.snap.snapscan.CodeType;

public final class SnapcodeSvgGenerator {
    private volatile long nativeGenerator;

    private static native void nativeDestroy(long j);

    private static native String nativeGenerateBitmojiStyleWithVersion(long j, int i, byte[] bArr);

    private static native String nativeGenerateDotsOnlyWithVersion(long j, int i, byte[] bArr);

    private static native String nativeGenerateGhostOnly(long j);

    private static native String nativeGenerateWithVersion(long j, int i, byte[] bArr);

    private static native long nativeInstantiate(int i, int i2);

    private static native byte[] nativeMaskData10by10Only(long j, byte[] bArr);

    private static native void nativeSetBorderSize(long j, double d);

    private static native void nativeSetGhostInteriorColor(long j, int i);

    private static native void nativeSetSize(long j, int i);

    public synchronized void destroy() {
        if (this.nativeGenerator != 0) {
            nativeDestroy(this.nativeGenerator);
            this.nativeGenerator = 0;
        }
    }

    public synchronized String generate(int i, byte[] bArr) {
        if (this.nativeGenerator == 0) {
            return "";
        }
        return nativeGenerateWithVersion(this.nativeGenerator, i, bArr);
    }

    public synchronized String generateDotsOnly(int i, byte[] bArr) {
        if (!(this.nativeGenerator == 0 || bArr == null || i < 0)) {
            if (i <= 15) {
                return nativeGenerateDotsOnlyWithVersion(this.nativeGenerator, i, bArr);
            }
        }
        return "";
    }

    public synchronized String generateForBitmoji(int i, byte[] bArr) {
        if (this.nativeGenerator == 0) {
            return "";
        }
        return nativeGenerateBitmojiStyleWithVersion(this.nativeGenerator, i, bArr);
    }

    public synchronized String generateGhostOnly() {
        if (this.nativeGenerator == 0) {
            return "";
        }
        return nativeGenerateGhostOnly(this.nativeGenerator);
    }

    public synchronized byte[] maskData10by10Only(byte[] bArr) {
        if (this.nativeGenerator != 0) {
            if (bArr != null) {
                return nativeMaskData10by10Only(this.nativeGenerator, bArr);
            }
        }
        return new byte[0];
    }

    public synchronized void setBorderSize(double d) {
        if (this.nativeGenerator != 0) {
            nativeSetBorderSize(this.nativeGenerator, d);
        }
    }

    public synchronized void setGhostInteriorColor(int i) {
        if (this.nativeGenerator != 0) {
            nativeSetGhostInteriorColor(this.nativeGenerator, i);
        }
    }

    public synchronized void setSize(int i) {
        if (this.nativeGenerator != 0) {
            nativeSetSize(this.nativeGenerator, i);
        }
    }

    public synchronized void setUp(int i, CodeType codeType) {
        this.nativeGenerator = nativeInstantiate(i, codeType.ordinal());
        /*if (SnapScanNativeLibraries.checkAreLoaded()) {
            destroy();
            this.nativeGenerator = nativeInstantiate(i, codeType.ordinal());
        } else {
            throw new SnapscanSetupError("Failed to load native code: ", SnapScanNativeLibraries.getLoadingError());
        }*/
    }
}
