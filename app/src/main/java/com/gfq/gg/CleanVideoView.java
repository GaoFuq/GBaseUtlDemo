package com.gfq.gg;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

public class CleanVideoView extends StandardGSYVideoPlayer {
    public CleanVideoView(Context context, Boolean fullFlag) {
        super(context, fullFlag);
    }

    public CleanVideoView(Context context) {
        super(context);
    }

    public CleanVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void setViewShowState(View view, int visibility) {
        view.setVisibility(INVISIBLE);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    @Override
    public boolean performClick() {
        return true;
    }
}
