package com.yuanqihudong.task.act;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.dreamliner.lib.frame.base.BaseCompatActivity;
import com.yuanqihudong.task.R;
import com.yuanqihudong.task.fra.FragmentationFragment;
import com.yuanqihudong.task.utils.SingleInstanceTest;
import com.yuanqihudong.task.utils.UtilsKt;

public class FragmentationActivity extends BaseCompatActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.act_fragmentation;
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        if (findFragment(FragmentationFragment.class) != null) {
            loadRootFragment(R.id.main_fl, FragmentationFragment.newInstance());
        }
        showToast(String.valueOf(UtilsKt.screenHeight(this)));
        SingleInstanceTest.Companion.get().toastSingle(() -> {
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                Log.i("TAG", "initViews: " + Thread.currentThread().getName());
                showToast("initViews: " + Thread.currentThread().getName());
            }, 5000);
            return null;
        });
    }
}
