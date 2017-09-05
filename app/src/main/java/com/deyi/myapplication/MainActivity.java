package com.deyi.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.deyi.myapplication.fragment.FourFragment;
import com.deyi.myapplication.fragment.FragmentController;
import com.deyi.myapplication.fragment.OneFragment;
import com.deyi.myapplication.fragment.ThreeFragment;
import com.deyi.myapplication.fragment.TwoFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends   BaseActivity {

    @BindView(R.id.ll_tab)
    LinearLayout llTab;
    @BindView(R.id.iv_home)
    ImageView ivHomeTab;
    private FragmentController instance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initFragment();
    }
    private void initFragment() {
        List<Fragment> mListFragment = new ArrayList<>();
        mListFragment.add(new OneFragment());
        mListFragment.add(new TwoFragment());
        mListFragment.add(new ThreeFragment());
        mListFragment.add(new FourFragment());
        instance = FragmentController.getInstance(this, R.id.tab_content, true);
        instance.initFragment(mListFragment);
        instance.showFragment(0);
        setSelectIcon(ivHomeTab);
    }
    private View lastSelectedIcon;
    @OnClick({R.id.rl_home,R.id.rl_message,R.id.rl_pop,R.id.rl_per,R.id.rl_more})
    public  void showFragment(View view){
        switch (view.getId()){
            case R.id.rl_home:
                setShowFragmentPosition(view,0);
                break;
            case R.id.rl_message:
                setShowFragmentPosition(view,1);
                break;
            case R.id.rl_pop:
                setShowFragmentPosition(view,2);
                break;
            case R.id.rl_per:
                setShowFragmentPosition(view,3);
                break;
            case R.id.rl_more:  //推荐app

                break;
        }
    }

    public void setShowFragmentPosition(View view, int position){
        if (lastSelectedIcon != null) lastSelectedIcon.setSelected(false);
        RelativeLayout rl = (RelativeLayout) view;
        ImageView icon = (ImageView) rl.getChildAt(0);
        instance.showFragment(position);
        setSelectIcon(icon);

    }
    private void setSelectIcon(ImageView iv) {
        iv.setSelected(true);
        lastSelectedIcon = iv;
    }

}
