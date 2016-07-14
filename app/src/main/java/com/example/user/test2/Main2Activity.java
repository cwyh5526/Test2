package com.example.user.test2;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

public class Main2Activity extends AppCompatActivity {
    private static final String TAG = "Main2Activity";
    private SlidingUpPanelLayout mLayout;
    private Button btn_sliding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);
        mLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                Log.i(TAG, "onPanelSlide, offset " + slideOffset);

            }
            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {
                Log.i(TAG, "onPanelStateChanged " + newState);

                // 슬라이드창 올라와있으면 아래화살표, 슬라이드창 내려가있으면 위화살표로 그림 변겅
                if(mLayout.getPanelState()==SlidingUpPanelLayout.PanelState.EXPANDED){
                    btn_sliding.setBackgroundResource(R.drawable.icon_slide_down);
                }else if(mLayout.getPanelState()==SlidingUpPanelLayout.PanelState.COLLAPSED){
                    btn_sliding.setBackgroundResource(R.drawable.icon_slide_up);
                }

            }
        });
        mLayout.setFadeOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
            }
        });

        if(savedInstanceState==null){
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            CameraPreviewFragment fragment = new CameraPreviewFragment();
//            RuntimePermissionsFragment fragment = new RuntimePermissionsFragment();
            transaction.replace(R.id.camera_fragment2, fragment);
            transaction.commit();
        }


        btn_sliding = (Button) findViewById(R.id.btn_sliding);

    }


    @Override
    public void onBackPressed() {
        if (mLayout != null &&
                (mLayout.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED || mLayout.getPanelState() == SlidingUpPanelLayout.PanelState.ANCHORED)) {
            mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
        } else {
            super.onBackPressed();
        }
    }
}
