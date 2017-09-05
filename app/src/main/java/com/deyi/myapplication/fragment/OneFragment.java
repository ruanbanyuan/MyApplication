package com.deyi.myapplication.fragment;


import android.animation.Animator;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.deyi.myapplication.Notice;
import com.deyi.myapplication.R;
import com.deyi.myapplication.colorUi.util.SharedPreferencesMgr;

import org.greenrobot.eventbus.EventBus;

/**
 * 作者：20150324
 * 时间：2017/9/5 09:58
 */

public class OneFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.activity_home, container, false);
        inflate.findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            /*    if (SharedPreferencesMgr.getInt(ConstanceValue.SP_THEME, ConstanceValue.THEME_LIGHT) == ConstanceValue.THEME_LIGHT) {
                    SharedPreferencesMgr.setInt(ConstanceValue.SP_THEME, ConstanceValue.THEME_NIGHT);
                    getActivity().setTheme(R.style.Theme_Light);
                } else {
                    SharedPreferencesMgr.setInt(ConstanceValue.SP_THEME, ConstanceValue.THEME_LIGHT);
                    getActivity().setTheme(R.style.Theme_Night);
                }*/
                if(SharedPreferencesMgr.getInt("theme", 0) == 1) {
                    SharedPreferencesMgr.setInt("theme", 0);
                    getActivity().setTheme(R.style.Theme_Night);
                } else {
                    SharedPreferencesMgr.setInt("theme", 1);
                    getActivity().setTheme(R.style.Theme_Light);
                }

                final View rootView = getActivity().getWindow().getDecorView();
                if (Build.VERSION.SDK_INT >= 14) {
                    rootView.setDrawingCacheEnabled(true);
                    rootView.buildDrawingCache(true);
                    final Bitmap localBitmap = Bitmap.createBitmap(rootView.getDrawingCache());
                    rootView.setDrawingCacheEnabled(false);
                    if (null != localBitmap && rootView instanceof ViewGroup) {
                        final View localView2 = new View(getActivity());
                        localView2.setBackgroundDrawable(new BitmapDrawable(getResources(), localBitmap));
                        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                        ((ViewGroup) rootView).addView(localView2, params);
                        localView2.animate().alpha(0).setDuration(400).setListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animation) {
                             //   post(new Notice(ConstanceValue.MSG_TYPE_CHANGE_THEME));
                               // ColorUiUtil.changeTheme(rootView, getActivity().getTheme());
                                EventBus.getDefault().post(new Notice(ConstanceValue.MSG_TYPE_CHANGE_THEME));


                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {
                                ((ViewGroup) rootView).removeView(localView2);
                                localBitmap.recycle();
                            }

                            @Override
                            public void onAnimationCancel(Animator animation) {
                            }

                            @Override
                            public void onAnimationRepeat(Animator animation) {
                            }
                        }).start();
                    }
                } else {
                  //  post(new Notice(ConstanceValue.MSG_TYPE_CHANGE_THEME));
                   // ColorUiUtil.changeTheme(rootView, getActivity().getTheme());
                    EventBus.getDefault().post(new Notice(ConstanceValue.MSG_TYPE_CHANGE_THEME));
                }
            }
        });

        return inflate;
    }

}
