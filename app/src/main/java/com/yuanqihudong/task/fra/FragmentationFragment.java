package com.yuanqihudong.task.fra;

import android.os.Bundle;
import android.view.View;

import com.dreamliner.lib.frame.base.BaseCompatFragment;
import com.yuanqihudong.task.R;

public class FragmentationFragment extends BaseCompatFragment {

    public static FragmentationFragment newInstance() {
        Bundle args = new Bundle();
        FragmentationFragment fragment = new FragmentationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fra_fragmentation;
    }

    @Override
    protected void initViews(View view) {

    }
}
