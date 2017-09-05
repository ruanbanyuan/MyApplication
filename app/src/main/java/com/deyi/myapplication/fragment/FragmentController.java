package com.deyi.myapplication.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;


/**
 * 主界面Fragment控制器
 */
public class FragmentController {

    private int containerId;
    private FragmentManager fm;
    private List<Fragment> fragments;

    private static FragmentController controller;
    private static boolean isReload;
    public static final String PARAM_MAX_PIC_FOR_CHOOSE = "PARAM_MAX_PIC_FOR_CHOOSE";
    public static final String PARAM_MUM_PIC_FOR_CHOOSE = "PARAM_MUM_PIC_FOR_CHOOSE";
    public static FragmentController getInstance(FragmentActivity activity, int containerId, boolean isReload) {
        FragmentController.isReload = isReload;
        controller = new FragmentController(activity, containerId);
        return controller;
    }

    public static void onDestroy() {
        controller = null;
    }

    private FragmentController(FragmentActivity activity, int containerId) {
        this.containerId = containerId;
        fm = activity.getSupportFragmentManager();
        //   initFragment();
    }
    public int getFragmentSize(){
        return fragments.size();
    }
    public void initFragment(List<Fragment> fragmentsList) {
        this.fragments=fragmentsList;
        if (isReload) {
            FragmentTransaction ft = fm.beginTransaction();
            for (int i = 0; i < fragments.size(); i++) {
                ft.add(containerId, fragments.get(i),"tabs"+i);
            }
            ft.commitAllowingStateLoss();
        } else {
            for (int i = 0; i < fragmentsList.size(); i++) {
                fragments.add( fm.findFragmentByTag("tabs"+i));
            }
        }
    }
    public void initToFragment(Fragment fragment){
        if(fragments==null){
            fragments=new ArrayList<>();
        }
        if(isReload){
            FragmentTransaction ft = fm.beginTransaction();
            if(!fragment.isAdded()){
                fragments.add(fragment);
                ft.add(containerId, fragment,"tabs"+fragments.size());
                ft.commitAllowingStateLoss();
            }
        }else{
            for (int i = 0; i < fragments.size(); i++) {
                fragments.add( fm.findFragmentByTag("tabs"+i));
            }
        }

    }
    public void showFragment(int position) {
        hideFragments();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment fragment=  fragments.get(position);

        ft.show(fragment);
        ft.commitAllowingStateLoss();
    }

    public void hideFragments() {
        FragmentTransaction ft = fm.beginTransaction();
        for (Fragment fragment : fragments) {
            if (fragment != null) {
                ft.hide(fragment);
            }
        }
        ft.commitAllowingStateLoss();
    }

    public Fragment getFragment(int position) {
        return fragments.get(position);
    }
}