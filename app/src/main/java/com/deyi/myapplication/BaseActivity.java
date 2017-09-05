package com.deyi.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.deyi.myapplication.colorUi.util.ColorUiUtil;
import com.deyi.myapplication.colorUi.util.SharedPreferencesMgr;
import com.deyi.myapplication.fragment.ConstanceValue;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * 作者：20150324
 * 时间：2017/9/5 11:09
 */

public class BaseActivity  extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferencesMgr.init(this, "derson");
        EventBus.getDefault().register(this);
        if(SharedPreferencesMgr.getInt("theme", 0) == 1) {
            setTheme(R.style.Theme_Light);
        } else {
            setTheme(R.style.Theme_Night);
        }
    }

    @Subscribe
    public void onEventMainThread( Notice message){
        if (message.type == ConstanceValue.MSG_TYPE_CHANGE_THEME)
            ColorUiUtil.changeTheme(getWindow().getDecorView(), getTheme());

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
