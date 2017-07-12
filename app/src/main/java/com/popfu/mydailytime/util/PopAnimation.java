package com.popfu.mydailytime.util;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.Interpolator;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by pengfu on 12/07/2017.
 */

public final class PopAnimation {


    private static PopAnimation instance ;

    private PopAnimation(){
        list = new ArrayList<Properties>() ;
    }

    public static PopAnimation init(){
        if(instance == null){
            instance = new PopAnimation() ;
        }
        instance.list.clear();
        return instance ;
    }

    private List<Properties> list ;

    public PopAnimation view(View view){
        Properties properties = new Properties() ;
        properties.setView(view);
        instance.list.add(properties);
        return instance ;
    }

    public PopAnimation then(View view){
        Properties properties = new Properties() ;
        properties.setView(view);
        instance.list.add(properties);
        return instance ;
    }

    public PopAnimation with(View view){
        return instance ;
    }

    public PopAnimation duration(long durationInMillis){
        instance.list.get(instance.list.size()-1).setDuration(durationInMillis) ;
        return instance ;
    }

    public PopAnimation repeat(int repeatCount){
        instance.list.get(instance.list.size()-1).setRepeatCount(repeatCount); ;
        return instance ;
    }

    public PopAnimation showAfterAnim(boolean showView){
        instance.list.get(instance.list.size() - 1).setShowViewAfterAnim(showView);
        return instance ;
    }

    public PopAnimation interpolator(Interpolator interpolator){
        instance.list.get(instance.list.size()-1).setInterpolator(interpolator);
        return instance ;
    }

    public PopAnimation scale(float from ,float to){
        instance.list.get(instance.list.size()-1).setFrom(from);
        instance.list.get(instance.list.size()-1).setTo(to);
        return instance ;
    }

    public void start(){
        instance.nextAnim();
    } ;

    private void nextAnim(){
        L.d("nextAnim");
        if(instance.list.size()>0){
            final Properties properties = instance.list.remove(0) ;
            ValueAnimator mAnimator = ValueAnimator.ofFloat(properties.getFrom() ,properties.getTo()) ;
            if(properties.getInterpolator() != null){
                mAnimator.setInterpolator(properties.getInterpolator());
            }
            mAnimator.setDuration(properties.getDuration()) ;
            mAnimator.setRepeatCount(properties.getRepeatCount());
            mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float rate = Float.parseFloat(animation.getAnimatedValue().toString()) ;
                    properties.getView().setScaleX(rate);
                    properties.getView().setScaleY(rate);
                }
            });
            mAnimator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    properties.getView().setVisibility(VISIBLE);
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    L.d("onAnimationEnd");
                    properties.getView().setVisibility(properties.getShowViewAfterAnim()? VISIBLE: GONE);
                    nextAnim();
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            L.d("start anim..");
            mAnimator.start();
        }
    }


    class Properties {
        private View view ;
        private float from ,to ;
        private Interpolator interpolator ;
        private long duration ;
        private int repeatCount ;
        private boolean showViewAfterAnim;

        public View getView() {
            return view;
        }

        public void setView(View view) {
            this.view = view;
        }

        public float getFrom() {
            return from;
        }

        public void setFrom(float from) {
            this.from = from;
        }

        public float getTo() {
            return to;
        }

        public void setTo(float to) {
            this.to = to;
        }

        public Interpolator getInterpolator() {
            return interpolator;
        }

        public void setInterpolator(Interpolator interpolator) {
            this.interpolator = interpolator;
        }

        public long getDuration() {
            return duration;
        }

        public void setDuration(long duration) {
            this.duration = duration;
        }

        public int getRepeatCount() {
            return repeatCount;
        }

        public void setRepeatCount(int repeatCount) {
            this.repeatCount = repeatCount;
        }

        public boolean getShowViewAfterAnim() {
            return showViewAfterAnim;
        }

        public void setShowViewAfterAnim(boolean showViewAfterAnim) {
            this.showViewAfterAnim = showViewAfterAnim;
        }
    }
}
