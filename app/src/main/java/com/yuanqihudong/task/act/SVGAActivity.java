package com.yuanqihudong.task.act;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

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
import com.opensource.svgaplayer.SVGACallback;
import com.opensource.svgaplayer.SVGADrawable;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGASoundManager;
import com.opensource.svgaplayer.SVGAVideoEntity;
import com.yuanqihudong.task.R;
import com.yuanqihudong.task.base.BaseActivity;
import com.yuanqihudong.task.utils.ScreenUtil;

import java.net.MalformedURLException;
import java.net.URL;

public class SVGAActivity extends BaseActivity {

    boolean shortDra = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImmersionBar.with(this).transparentStatusBar().statusBarDarkFont(false).init();
        SVGAImageView imageView = findViewById(R.id.svga_mic);
        imageView.setOnClickListener(view ->
                remote("https://res.hnchumeng.com/peach-sg/2023/10/19/101ee406abce514b23a3b90b2b9e7e1002", imageView));
//        imageView.setOnClickListener(v -> local(imageView));
    }

    private void remote(String urlStr, SVGAImageView imageView) {
        try {
            URL url = new URL(urlStr);
            SVGAParser.Companion.shareParser().parse(url, new SVGAParser.ParseCompletion() {
                @Override
                public void onComplete(SVGAVideoEntity svgaVideoEntity) {
                    Log.i("drawSvgaEffect", "onComplete: " + url + "width=" +
                            svgaVideoEntity.getVideoSize().getWidth() + "height=" + svgaVideoEntity.getVideoSize().getHeight());
                    SVGADrawable drawable = new SVGADrawable(svgaVideoEntity);
                    imageView.setImageDrawable(drawable);
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
                    if (shortDra) {

                        int videoWidth = (int) svgaVideoEntity.getVideoSize().getWidth();
                        int videoHeight = (int) svgaVideoEntity.getVideoSize().getHeight();
                        int width = ScreenUtil.getScreenWidth(imageView.getContext());
                        int height = ScreenUtil.getScreenHeight(imageView.getContext());

                        layoutParams.width = width;
                        layoutParams.height = width * videoHeight / videoWidth;

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
            imageView.setCallback(new SVGACallback() {

                @Override
                public void onFinished() {
                    SVGASoundManager.INSTANCE.release();
                }

                @Override
                public void onStep(int frame, double percentage) {
                }

                @Override
                public void onRepeat() {
                }

                @Override
                public void onPause() {
                }
            });
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private void local(SVGAImageView imageView) {
        SVGAParser.Companion.shareParser().decodeFromAssets("test1314.svga", new SVGAParser.ParseCompletion() {
            @Override
            public void onComplete(SVGAVideoEntity svgaVideoEntity) {
                /*SVGADrawable drawable = new SVGADrawable(svgaVideoEntity);
                imageView.setImageDrawable(drawable);
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imageView.getLayoutParams();
                layoutParams.width = (int) svgaVideoEntity.getVideoSize().getWidth();
                layoutParams.height = (int) svgaVideoEntity.getVideoSize().getHeight();
                imageView.setLayoutParams(layoutParams);
                imageView.startAnimation();*/
                imageView.setVideoItem(svgaVideoEntity);
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