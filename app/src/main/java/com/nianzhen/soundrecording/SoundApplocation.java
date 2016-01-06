package com.nianzhen.soundrecording;

import android.app.Application;

import com.nianzhen.soundrecording.utils.CrashHandler;

/**
 * Created by Administrator on 2015/12/11.
 */
public class SoundApplocation extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        setupDatabase();
        initCrashHandle();
    }

    private void setupDatabase() {
    }

    private void initCrashHandle() {
        CrashHandler crashHandler = CrashHandler.getInstatance();
        crashHandler.init(this);
    }

}
