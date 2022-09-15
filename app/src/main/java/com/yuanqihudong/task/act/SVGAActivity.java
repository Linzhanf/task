package com.yuanqihudong.task.act;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

import androidx.appcompat.app.AppCompatActivity;

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

import com.gyf.immersionbar.ImmersionBar;
import com.opensource.svgaplayer.SVGADrawable;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;
import com.yuanqihudong.task.R;

import java.net.MalformedURLException;
import java.net.URL;

public class SVGAActivity extends AppCompatActivity {

    boolean shortDra = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImmersionBar.with(this).transparentStatusBar().statusBarDarkFont(false).init();

        /*EditText editText = findViewById(R.id.editText);
        editText.setText("https://pic.hnchumeng.com/Fhtnm_1_dA5SvUnZkaoWnnI0X5lK?imageslim");
        findViewById(R.id.btn).setOnClickListener(v -> {
            remote(editText.getText().toString(), imageView);
        });*/


        SVGAImageView imageView = findViewById(R.id.svga_mic);
        remote("https://pic.hnchumeng.com/Fq9vKt2sR0abg34554wahfSca7q6?imageslim", imageView);
        findViewById(R.id.svga_mic).setOnClickListener(v -> {
            shortDra = !shortDra;
            remote("https://pic.hnchumeng.com/Fq9vKt2sR0abg34554wahfSca7q6?imageslim", imageView);
        });
        //local(imageView);

        //https://pic.hnchumeng.com/Fhtnm_1_dA5SvUnZkaoWnnI0X5lK?imageslim width=750.0height=1500.0
        //https://pic.hnchumeng.com/FjR1nzijtMKz2yRH4DMtfRx7jbNh?imageslim width=540.0height=960.0
        //https://pic.hnchumeng.com/Fvz3dk107nwQv8b4GpeIYz9aYzcM?imageslim width=750.0height=1624.0
        //https://pic.hnchumeng.com/Fq9vKt2sR0abg34554wahfSca7q6?imageslim width=750.0height=1334.0
    }

    private void remote(String urlStr, SVGAImageView imageView) {
        try {
            SVGAParser parser = new SVGAParser(SVGAActivity.this);
            URL url = new URL(urlStr);
            parser.parse(url, new SVGAParser.ParseCompletion() {
                @Override
                public void onComplete(SVGAVideoEntity svgaVideoEntity) {
                    Log.i("drawSvgaEffect", "onComplete: " + url + "width=" +
                            svgaVideoEntity.getVideoSize().getWidth() + "height=" + svgaVideoEntity.getVideoSize().getHeight());
                    SVGADrawable drawable = new SVGADrawable(svgaVideoEntity);
                    imageView.setImageDrawable(drawable);
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
                    if (shortDra) {
                        layoutParams.width = (int) svgaVideoEntity.getVideoSize().getWidth();
                        layoutParams.height = (int) svgaVideoEntity.getVideoSize().getHeight();
                    } else {
                        layoutParams.width = MATCH_PARENT;
                        layoutParams.height = MATCH_PARENT;
                    }
                    imageView.setLayoutParams(layoutParams);
                    imageView.startAnimation();
                }

                @Override
                public void onError() {

                }
            });
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private void local(SVGAImageView imageView) {
        SVGAParser parser = new SVGAParser(SVGAActivity.this);
        parser.decodeFromAssets("svga_head_line_start_limit_notice.svga", new SVGAParser.ParseCompletion() {
            @Override
            public void onComplete(SVGAVideoEntity svgaVideoEntity) {
                SVGADrawable drawable = new SVGADrawable(svgaVideoEntity);
                imageView.setImageDrawable(drawable);
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imageView.getLayoutParams();
                layoutParams.width = (int) svgaVideoEntity.getVideoSize().getWidth();
                layoutParams.height = (int) svgaVideoEntity.getVideoSize().getHeight();
                imageView.setLayoutParams(layoutParams);
                imageView.startAnimation();
            }

            @Override
            public void onError() {

            }
        }, null);
    }

    private void animation(ImageView imageView) {
        //创建一个旋转动画
        RotateAnimation rotateAnimation = new RotateAnimation(0f, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(3000);
        rotateAnimation.setFillAfter(true);
        //创建一个灰度动画
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.1f);
        alphaAnimation.setDuration(3000);
        alphaAnimation.setFillAfter(true);
        //创建一个平移动画
        TranslateAnimation translateAnimation = new TranslateAnimation(1.0f, -100, 1.0f, 1.0f);
        translateAnimation.setDuration(3000);
        translateAnimation.setFillAfter(true);
        //创建一个缩放动画
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.0f, 1.0f, 0.5f);
        scaleAnimation.setDuration(3000);
        scaleAnimation.setFillAfter(true);
        //创建一个集合动画
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(rotateAnimation);
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(scaleAnimation);
        animationSet.setFillAfter(true);
        imageView.startAnimation(animationSet);
    }
}