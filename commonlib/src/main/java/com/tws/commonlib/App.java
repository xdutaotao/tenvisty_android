package com.tws.commonlib;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.tws.commonlib.base.MyConfig;
import com.tws.commonlib.base.TwsTools;
import com.tws.commonlib.bean.MyCamera;
import com.tws.commonlib.bean.TwsDataValue;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengCallback;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.entity.UMessage;

/**
 * 搴旂敤鍚姩鏃堕鍏堟墽琛岃Application锛屽鍏ㄥ眬鎬х殑涓滆タ杩涜鎺у埗
 *
 * @author Administrator
 */
public class App extends Application {
    private static Context mContext;
    private static App instance;

    public static App GetApp() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("==App==", "App onCreate");
        mContext = this;
        instance = this;
        //浠ヤ笅涓ゅ彞璐熻矗杞欢宕╂簝淇℃伅鐨勮褰曪紝鍙戝竷鐗堟湰鏃堕渶娉ㄩ噴鎺夛紙鍥藉鐨勭増鏈湪Googleplay涓婃湁缁熻锛屽浗鍐呯殑鍙繚鐣欙級

        CustomCrashHandler mCustomCrashHandler = CustomCrashHandler.getInstance();
        mCustomCrashHandler.setCustomCrashHanler(getApplicationContext());

        //注册推送
        MyCamera.initPushSDK(this);
        MyCamera.initP2P();
    }

    public static Context getContext() {
        return mContext;
    }

    public static String getResourceString(int id) {
        return mContext.getResources().getString(id);
    }

    public interface GlobalConfigDelegate {
        void OnInit();
    }

    private GlobalConfigDelegate globalCofDelegate;

    public void setGlobalCofDelegate(GlobalConfigDelegate globalCofDelegate) {
        this.globalCofDelegate = globalCofDelegate;
    }

    public GlobalConfigDelegate getGlobalCofDelegate() {
        return this.globalCofDelegate;
    }
}