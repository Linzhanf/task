package com.yuanqihudong.task.act;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.Nullable;

import com.dreamliner.lib.frame.base.BaseCompatActivity;
import com.yuanqihudong.task.R;
import com.yuanqihudong.task.fra.FragmentationFragment;

public class FragmentationActivity extends BaseCompatActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.act_fragmentation;
    }

    private Handler mHandler;

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        loadRootFragment(R.id.main_fl, FragmentationFragment.newInstance());
        mHandler = new Handler(Looper.getMainLooper());
        mHandler.postDelayed(() -> showToast(FragmentationActivity.class.getSimpleName()), 2000);
    }


}
