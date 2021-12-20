package com.snap.snapscan.scanner;

import android.graphics.Bitmap;
import com.snap.snapscan.CodeType;
import com.snap.snapscan.ImageFormat;

public final class ScanTask {
    private final Bitmap bitmap;
    private int[] codeTypes;
    private boolean enableContourEnhancement;
    private boolean enableFalseAlarmCheck;
    private int maxDimension;
    private final RawImage rawImage;
    private boolean withDebugView;

    public static final class RawImage {
        public final byte[] data;
        public final ImageFormat format;
        public final int height;
        public final int width;

        public RawImage(byte[] bArr, ImageFormat imageFormat, int i, int i2) {
            this.data = bArr;
            this.format = imageFormat;
            this.width = i;
            this.height = i2;
        }
    }

    public ScanTask(Bitmap bitmap2) {
        this.withDebugView = false;
        this.maxDimension = 0;
        this.enableFalseAlarmCheck = false;
        this.enableContourEnhancement = false;
        this.codeTypes = new int[0];
        this.bitmap = bitmap2;
        this.rawImage = null;
    }

    public ScanTask(ImageFormat imageFormat, byte[] bArr, int i, int i2) {
        this.withDebugView = false;
        this.maxDimension = 0;
        this.enableFalseAlarmCheck = false;
        this.enableContourEnhancement = false;
        this.codeTypes = new int[0];
        this.bitmap = null;
        this.rawImage = new RawImage(bArr, imageFormat, i, i2);
    }

    private native ScannerResult nativeScanBitmap(Bitmap bitmap2, int i, int[] iArr, boolean z, boolean z2, boolean z3);

    private native ScannerResult nativeScanRawImage(byte[] bArr, int i, int i2, int i3, int i4, int[] iArr, boolean z, boolean z2, boolean z3);

    public ScanTask maxDimension(int i) {
        this.maxDimension = i;
        return this;
    }

    public ScannerResult scan() throws Exception {
        byte[] bArr;
        ImageFormat imageFormat;
        if ( true /*SnapScanNativeLibraries.checkAreLoaded()*/) {
            Bitmap bitmap2 = this.bitmap;
            if (bitmap2 != null) {
                return nativeScanBitmap(bitmap2, this.maxDimension, this.codeTypes, this.enableFalseAlarmCheck, this.enableContourEnhancement, this.withDebugView);
            }
            RawImage rawImage2 = this.rawImage;
            if (rawImage2 == null || (bArr = rawImage2.data) == null || (imageFormat = rawImage2.format) == null) {
                throw new IllegalStateException("Have neither bitmap nor valid raw image to scan");
            }
            int ordinal = imageFormat.ordinal();
            RawImage rawImage3 = this.rawImage;
            return nativeScanRawImage(bArr, ordinal, rawImage3.width, rawImage3.height, this.maxDimension, this.codeTypes, this.enableFalseAlarmCheck, this.enableContourEnhancement, this.withDebugView);
        }
        throw new Exception("failed to load native code: ");
    }

    public ScanTask withCodeTypes(CodeType... codeTypeArr) {
        if (codeTypeArr != null) {
            this.codeTypes = new int[codeTypeArr.length];
            for (int i = 0; i < codeTypeArr.length; i++) {
                this.codeTypes[i] = codeTypeArr[i].ordinal();
            }
            return this;
        }
        throw new IllegalArgumentException("bad code types list");
    }

    public ScanTask withContourEnhancement() {
        this.enableContourEnhancement = true;
        return this;
    }

    public ScanTask withDebugView() {
        this.withDebugView = true;
        return this;
    }

    public ScanTask withFalseAlarmCheck() {
        this.enableFalseAlarmCheck = true;
        return this;
    }
}
