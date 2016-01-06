package com.nianzhen.soundrecording;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.ViewConfiguration;
import android.view.Window;
import android.view.WindowManager;

import java.lang.reflect.Field;

/**
 * Created by nianzhen on 2016/1/5.
 */
public class BaseActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Window window = getWindow();
//        window.setFlags(0x04000000 /*WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS*/, 0x04000000 /*WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS*/);
//        window.setFlags(0x08000000 /*WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION*/, 0x08000000 /*WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION*/);
        mHelper.onCreateHelp(savedInstanceState);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        mHelper.onAttachedToWindow();
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mHelper.onDetachedFromWindow();
    }

    /**
     * 将菜单显示在actionbar上，而不是在底部
     */
    ActivityHelper mHelper = new ActivityHelper(this);

    protected void requestActionBarMenu() {
        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class
                    .getDeclaredField("sHasPermanentMenuKey");

            if (menuKeyField != null) {
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(config, false);
            }
        } catch (Exception e) {
            // presumably, not relevant
        }
    }

    /**
     * 就是标题栏
     *
     * @param on
     */
    @TargetApi(19)
    public void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

}
