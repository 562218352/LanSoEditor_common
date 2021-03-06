package com.lansosdk.videoeditor;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/**
 * 杭州蓝松科技有限公司
 * www.lansongtech.com
 */
public class LanSoEditor {


    private static boolean isLoaded = false;

    public static void initSDK(Context context, String str) {
        loadLibraries();
        LanSoEditor.initSo(context, str);
        VideoEditor.logEnable(context);  //使能;
    }


    private static synchronized void loadLibraries() {
        if (isLoaded)
            return;



        System.loadLibrary("LanSongffmpeg");
        System.loadLibrary("LanSongdisplay");
        System.loadLibrary("LanSongplayer");

        Log.d("LanSongSDK", "loaded libraries.isQiLinSoc:"+ VideoEditor.isQiLinSoc());

        isLoaded = true;
    }

    public static void initSo(Context context, String argv) {
        nativeInit(context, context.getAssets(), argv);
    }

    public static void unInitSo() {
        nativeUninit();
    }

    public static native void nativeInit(Context ctx, AssetManager ass, String filename);

    public static native void nativeUninit();


}
