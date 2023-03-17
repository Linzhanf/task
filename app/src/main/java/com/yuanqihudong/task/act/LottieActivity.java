package com.yuanqihudong.task.act;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import com.gyf.immersionbar.ImmersionBar;
import com.opensource.svgaplayer.SVGADrawable;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;
import com.yuanqihudong.task.R;
import com.yuanqihudong.task.base.BaseActivity;

import java.net.MalformedURLException;
import java.net.URL;

public class LottieActivity extends BaseActivity {

    boolean shortDra = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_lottie);

        ImmersionBar.with(this).transparentStatusBar().statusBarDarkFont(false).init();

        /*EditText editText = findViewById(R.id.editText);
        editText.setText("https://pic.hnchumeng.com/Fhtnm_1_dA5SvUnZkaoWnnI0X5lK?imageslim");
        findViewById(R.id.btn).setOnClickListener(v -> {
            remote(editText.getText().toString(), imageView);
        });*/


        LottieAnimationView imageView = findViewById(R.id.svga_mic);
        /*remote("https://pic.hnchumeng.com/Fq9vKt2sR0abg34554wahfSca7q6?imageslim", imageView);
        findViewById(R.id.svga_mic).setOnClickListener(v -> {
            shortDra = !shortDra;
            remote("https://pic.hnchumeng.com/Fq9vKt2sR0abg34554wahfSca7q6?imageslim", imageView);
        });*/
        local(imageView);

        //https://pic.hnchumeng.com/Fhtnm_1_dA5SvUnZkaoWnnI0X5lK?imageslim width=750.0height=1500.0
        //https://pic.hnchumeng.com/FjR1nzijtMKz2yRH4DMtfRx7jbNh?imageslim width=540.0height=960.0
        //https://pic.hnchumeng.com/Fvz3dk107nwQv8b4GpeIYz9aYzcM?imageslim width=750.0height=1624.0
        //https://pic.hnchumeng.com/Fq9vKt2sR0abg34554wahfSca7q6?imageslim width=750.0height=1334.0
    }

    private void remote(String urlStr, LottieAnimationView imageView) {

    }

    private void local(LottieAnimationView imageView) {
        //svga_head_line_start_limit_notice.svga
        imageView.setAnimation("svga_head_line_start_limit_notice.svga");
        imageView.playAnimation();
        imageView.setRepeatCount(ValueAnimator.INFINITE);
        imageView.setSpeed(1.5f);

    }

}