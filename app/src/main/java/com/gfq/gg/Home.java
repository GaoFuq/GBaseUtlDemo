package com.gfq.gg;


import android.Manifest;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gfq.gbaseutl.address_piker.AddressPicker;
import com.gfq.gbaseutl.statusbar.StatusBarUtils;
import com.gfq.gbaseutl.util.FileUtil;
import com.gfq.gbaseutl.util.Permission;
import com.gfq.gbaseutl.util.ScreenShotUtil;
import com.gfq.gbaseutl.views.bottom_dialog.bottom_calender_dialog.BottomCalenderDialog;
import com.gfq.gbaseutl.views.bottom_dialog.bottom_choose_dialog.BottomChooseDialog;
import com.gfq.gbaseutl.views.round_dialog.RoundDialog;
import com.gfq.gg.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {

    FragmentHomeBinding binding;
    AddressPicker addressPicker;
    BottomChooseDialog<String> dialog;
    BottomCalenderDialog calenderDialog;
    private boolean xxx=false;

    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initPermission();
        NavController controller = NavHostFragment.findNavController(this);
        //地址选择器
        binding.btnAddress.setOnClickListener(v->{
            if(addressPicker==null){
                addressPicker = new AddressPicker(getActivity());
                addressPicker.setAddressListener((province, city, district) -> Toast.makeText(getActivity(), province+city+district, Toast.LENGTH_SHORT).show());
            }
            addressPicker.show();
        });


        binding.recycleView.setOnClickListener(v -> controller.navigate(R.id.action_home_to_RVBindingRecycleView));

        binding.btnSelect.setOnClickListener(v -> controller.navigate(R.id.action_home_to_dropSelect));


        binding.btnBottomCalendar.setOnClickListener(v -> {
            if(calenderDialog==null) {
                calenderDialog = new BottomCalenderDialog(getActivity());
                calenderDialog.setOnCalenderSelectListener((year, month, day) -> {
                    Toast.makeText(getActivity(), year + "年" + month + "月" + day + "日", Toast.LENGTH_SHORT).show();
                });
            }
            calenderDialog.show();
        });

        List<String> list = new ArrayList<>();
        for (int i = 0; i <5 ; i++) {
            list.add("test"+i);
        }
        binding.btnBottomDialog1.setOnClickListener(v -> {
            if(dialog==null) {
                dialog = new BottomChooseDialog<String>(getActivity()) {
                    @Override
                    protected String getTitle() {
                        return "标题";
                    }

                    @Override
                    public List<String> getDataList() {
                        return list;
                    }

                    @Override
                    protected void onConfirmClicked(String content) {
                        Toast.makeText(getActivity(), content, Toast.LENGTH_SHORT).show();
                    }
                };
            }
            dialog.show();
        });

        binding.btnRoundDialog.setOnClickListener(v -> {
            new RoundDialog(getActivity()).setTitle("标题").setContent("内容").show();
        });

        binding.btnScreenshot.setOnClickListener(v -> {
                ScreenShotUtil.shot(getActivity(), "ABCDEFG", System.currentTimeMillis() + "_haha");
        });

        binding.btnStatusBar.setOnClickListener(v -> {
            StatusBarUtils.setStatusBarColor(getActivity(), Color.RED);
        });


        binding.btnWriteText2File.setOnClickListener(v -> {
                FileUtil.writeTxtToFile("fdwafnuhfhjpfafj648646", Environment.getExternalStorageDirectory().getPath()+"/ABCDEFG/", "abc.txt");
            Toast.makeText(getActivity(),"/ABCDEFG/abc.txt" , Toast.LENGTH_LONG).show();

        });
        binding.btnBottomNav.setOnClickListener(v->{
            controller.navigate(R.id.action_home_to_bottomNavTest);
        });


    }



       private void initPermission() {
              String[] permissions = {Manifest.permission.INTERNET, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
              Permission.requestPermissions(getActivity(), 101, permissions, new Permission.OnPermissionListener() {
                  @Override
                  public void onPermissionGranted() {
                  }

                  @Override
                  public void onPermissionDenied() {
                      Permission.showTipsDialog(getActivity());
                  }
              });

          }
}
