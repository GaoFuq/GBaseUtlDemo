package com.gfq.gg;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gfq.gbaseutl.views.drop_select_view.MyDropSelectView;
import com.gfq.gbaseutl.views.drop_select_view.MyListAdapter;
import com.gfq.gbaseutl.views.drop_select_view.SearchBean;
import com.gfq.gg.databinding.FragmentDropSelectBinding;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DropSelect extends Fragment {


    private FragmentDropSelectBinding binding;

    public DropSelect() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         binding = DataBindingUtil.inflate(inflater, R.layout.fragment_drop_select, container, false);
        return binding.getRoot();
    }

    private List<SearchBean> list = new ArrayList<>();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        for (int i = 0; i <5 ; i++) {
            list.add(new SearchBean().setName("XXX").setImgResId(R.mipmap.ic_launcher_round));
        }
        binding.drop1.setAdapter(new MyListAdapter(getActivity(),list));
        binding.drop1.setOnItemClick((content, position) -> {
            Toast.makeText(getActivity(), content + position, Toast.LENGTH_SHORT).show();
        });
    }
}
