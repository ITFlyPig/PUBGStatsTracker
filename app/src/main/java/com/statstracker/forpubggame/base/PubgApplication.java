package com.statstracker.forpubggame.base;

import android.app.Application;

import com.google.android.gms.ads.MobileAds;
import com.lzy.okgo.OkGo;
import com.statstracker.forpubggame.R;
import com.umeng.analytics.MobclickAgent;

public class PubgApplication extends Application {

    private static PubgApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        initNetwork();
        MobclickAgent.startWithConfigure(new MobclickAgent.UMAnalyticsConfig(this, getString(R.string.umeng_key), "google", MobclickAgent.EScenarioType.E_UM_NORMAL, true));
        MobileAds.initialize(this);

    }


    /**
     * 初始化网络框架
     */
    private void initNetwork(){
        OkGo.getInstance().init(this);
    }


    public static PubgApplication getInstance() {
        return mInstance;
    }


}
