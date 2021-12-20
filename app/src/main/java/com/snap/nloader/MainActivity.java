package com.snap.nloader;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.snap.nloader.android.DefaultLoadComponentDelegate;
import com.snap.snapscan.CodeType;
import com.snap.snapscan.SnapScanType;
import com.snap.snapscan.generator.SnapcodeSvgGenerator;
import com.snap.snapscan.scanner.ScanTask;
import com.snap.snapscan.scanner.ScannerResult;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private static native String nativeGenerateDotsOnlyWithVersion (long j, int i, byte[] bArr);
    private static native long nativeInstantiate(int i, int i2);


    static {
        /*try {
            System.loadLibrary("camplat+.b3fb6e340e2db4f5dac03e8150783e4a2f0c33c6");
            //System.loadLibrary("nloader");
            System.out.println("Loaded camplat library");
        } catch (UnsatisfiedLinkError e){
            System.out.println("Caught error:\n" + e);
        }*/

        try {
            CamplatPlusAwareComponentLayout layout = new CamplatPlusAwareComponentLayout();
            DefaultLoadComponentDelegate delegate = new DefaultLoadComponentDelegate();

            NLOader.setNativeComponentsLayout(layout);
            NLOader.setDefaultLoadComponentDelegate(delegate);

            NLOader.initializeNativeComponent("snapscan");
            System.out.println("Loaded snapscan successfully");
        } catch (Exception e2) {
            System.out.println("Caught error:\n" + e2);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int snapcodeVersion = 0;

        try {
            SnapcodeSvgGenerator generator = new SnapcodeSvgGenerator();
            generator.setUp(256, CodeType.SNAPCODE_18x18);
            StringBuilder out = new StringBuilder();

            byte[] bytes;
            byte[] values = new byte[]{(byte)1, (byte) 2, (byte) 4, (byte)8, (byte)16, (byte)32, (byte)64, (byte)128};

            // Result will be the concatenation of 8 snapcode SVG strings with underlying byte arrays containing all 1s, all 2s, ..., all 128s
            // This will be used to determine the ordering of bits within 8-bit groupings
            for (byte val : values){
                bytes = new byte[16];
                Arrays.fill(bytes, val);
                String svg = generator.generate(snapcodeVersion, bytes);
                out.append(svg);
            }

            ClipboardManager manager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText("Copied text", out.toString());
            manager.setPrimaryClip(clipData);

        } catch (Exception e) {
            System.out.println("Error generating dots:\n" + e);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private static byte[] hexStringToByteArray(String str) {
        if (str.isEmpty() || str.length() % 2 != 0) {
            System.out.println("BAD STRING: String is empty or of odd length");
            return null;
        }
        byte[] bArr = new byte[(str.length() / 2)];
        for (int i = 0; i < str.length(); i += 2) {
            bArr[i / 2] = (byte) (Character.digit(str.charAt(i + 1), 16) + (Character.digit(str.charAt(i), 16) << 4));
        }

        System.out.println("byteArray has length: " + bArr.length);
        return bArr;
    }
}