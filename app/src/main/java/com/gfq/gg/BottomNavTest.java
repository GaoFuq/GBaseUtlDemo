package com.gfq.gg;


import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gfq.gg.databinding.FragmentBottomNavTestBinding;
import com.shuyu.gsyvideoplayer.listener.VideoAllCallBack;
import com.shuyu.gsyvideoplayer.utils.GSYVideoType;


/**
 * A simple {@link Fragment} subclass.
 */
public class BottomNavTest extends Fragment {

    FragmentBottomNavTestBinding binding;

    public BottomNavTest() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       binding= DataBindingUtil.inflate(inflater,R.layout.fragment_bottom_nav_test, container, false) ;
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


         init();


        binding.button.setOnClickListener(v->{
           show();
        });


    }

  void  show(){
        binding.videoview.setUp("https://video.hibixin.com/video/standard/b71a0303-fc4e-41f3-8f30-a7f44c40a4d4.mp4",true,"xxx");
        binding.videoview.startPlayLogic();
    }

    private void init() {
            GSYVideoType.setShowType(GSYVideoType.SCREEN_TYPE_FULL);
            //设置全屏按键功能,这是使用的是选择屏幕，而不是全屏
            //是否可以滑动调整
            //设置返回按键功能

        binding.videoview.getFullscreenButton().setVisibility(View.VISIBLE);
        binding.videoview.getTitleTextView().setVisibility(View.VISIBLE);
        binding.videoview.getTitleTextView().setText("xxxxxx");

            binding.videoview.getBackButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        binding.videoview.setVideoAllCallBack(new VideoAllCallBack() {
                @Override
                public void onStartPrepared(String url, Object... objects) {

                }

                @Override
                public void onPrepared(String url, Object... objects) {

                }

                @Override
                public void onClickStartIcon(String url, Object... objects) {

                }

                @Override
                public void onClickStartError(String url, Object... objects) {

                }

                @Override
                public void onClickStop(String url, Object... objects) {

                }

                @Override
                public void onClickStopFullscreen(String url, Object... objects) {

                }

                @Override
                public void onClickResume(String url, Object... objects) {

                }

                @Override
                public void onClickResumeFullscreen(String url, Object... objects) {

                }

                @Override
                public void onClickSeekbar(String url, Object... objects) {

                }

                @Override
                public void onClickSeekbarFullscreen(String url, Object... objects) {

                }

                @Override
                public void onAutoComplete(String url, Object... objects) {
                    Toast.makeText(getActivity(), "over", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onEnterFullscreen(String url, Object... objects) {

                }

                @Override
                public void onQuitFullscreen(String url, Object... objects) {

                }

                @Override
                public void onQuitSmallWidget(String url, Object... objects) {

                }

                @Override
                public void onEnterSmallWidget(String url, Object... objects) {

                }

                @Override
                public void onTouchScreenSeekVolume(String url, Object... objects) {

                }

                @Override
                public void onTouchScreenSeekPosition(String url, Object... objects) {

                }

                @Override
                public void onTouchScreenSeekLight(String url, Object... objects) {

                }

                @Override
                public void onPlayError(String url, Object... objects) {
                    Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onClickStartThumb(String url, Object... objects) {

                }

                @Override
                public void onClickBlank(String url, Object... objects) {

                }

                @Override
                public void onClickBlankFullscreen(String url, Object... objects) {

                }
            });
    }


    @Override
    public void onPause() {
        super.onPause();
        if (binding.videoview != null) {
            binding.videoview.onVideoPause();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if ( binding.videoview != null) {
            binding.videoview.setVideoAllCallBack(null);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (binding.videoview != null ) {
          show();
        }
    }
}
