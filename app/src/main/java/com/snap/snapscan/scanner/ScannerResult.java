package com.snap.snapscan.scanner;

import com.snap.snapscan.CodeType;

public final class ScannerResult {
    private final CodeType codeType;
    private final int codeTypeMeta;
    private final String data;
    private final DebugView debugView;
    private final byte[] rawData;

    public static class DebugView {
        private final byte[] argb8888;
        private final int imageHeight;
        private final int imageWidth;

        private DebugView(byte[] bArr, int i, int i2) {
            this.argb8888 = bArr;
            this.imageWidth = i;
            this.imageHeight = i2;
        }

        public byte[] getArgb8888() {
            return this.argb8888;
        }

        public int getImageHeight() {
            return this.imageHeight;
        }

        public int getImageWidth() {
            return this.imageWidth;
        }

        public String toString() {
            StringBuilder b2 = new StringBuilder("DebugView{ argb8888=[");
            b2.append(this.argb8888.length);
            b2.append(" bytes] width=");
            b2.append(this.imageWidth);
            b2.append(" height=");
            return b2.append(this.imageHeight + "}").toString();
        }
    }

    private ScannerResult(int i, int i2, String str, byte[] bArr, byte[] bArr2, int i3, int i4) {
        if (i >= 0) {
            CodeType.values();
            if (i < 7) {
                this.codeType = CodeType.values()[i];
                this.codeTypeMeta = i2;
                this.data = str == null ? "" : str;
                this.rawData = bArr == null ? new byte[0] : bArr;
                this.debugView = bArr2 != null ? new DebugView(bArr2, i3, i4) : null;
                return;
            }
        }
        throw new IllegalArgumentException("bad code type: " + i);
    }

    public CodeType getCodeType() {
        return this.codeType;
    }

    public int getCodeTypeMeta() {
        return this.codeTypeMeta;
    }

    public String getData() {
        return this.data;
    }

    public DebugView getDebugView() {
        return this.debugView;
    }

    public byte[] getRawData() {
        return this.rawData;
    }

    public String toString() {
        StringBuilder b2 = new StringBuilder("ScannerResult{ type=");
        b2.append(this.codeType);
        b2.append(" meta=");
        b2.append(this.codeTypeMeta);
        b2.append(" data=");
        b2.append(this.data);
        b2.append(" debugView=");
        DebugView debugView2 = this.debugView;
        return b2.append(debugView2 != null ? debugView2.toString() : "<null>").toString();
    }
}
