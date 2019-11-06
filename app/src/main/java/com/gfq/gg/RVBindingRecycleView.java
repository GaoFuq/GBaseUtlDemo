package com.gfq.gg;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gfq.gbaseutl.databinding.RVBindingAdapter;
import com.gfq.gbaseutl.databinding.SuperBindingViewHolder;
import com.gfq.gbaseutl.util.recycleview_util.GridSpacingItemDecoration;
import com.gfq.gg.databinding.RvItemBinding;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class RVBindingRecycleView extends Fragment {


    private View view;

    public RVBindingRecycleView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_rvbinding_recycle_view, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       RecyclerView recyclerView =  view.findViewById(R.id.recycleView);

        RVBindingAdapter<String> adapter = new RVBindingAdapter<String>(view.getContext(),BR.data) {
            @Override
            public void setPresentor(SuperBindingViewHolder holder, int position) {
                String str = getDataList().get(position);
                //事件处理
//                holder.itemView
                RvItemBinding binding = (RvItemBinding) holder.getBinding();
                binding.text.setOnClickListener(v -> Toast.makeText(mContext, str, Toast.LENGTH_SHORT).show());
            }

            @Override
            public int getLayoutId() {
                return R.layout.rv_item;
            }
        };
        recyclerView.setAdapter(adapter);
        List<String> list = new ArrayList<>();
        for (int i = 0; i <100 ; i++) {
            list.add("haha"+i);
        }
        adapter.setDataList(list);

        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2,20,true));
        /**
         * <androidx.recyclerview.widget.RecyclerView
         *       android:id="@+id/recycleView"
         *       android:layout_width="match_parent"
         *       android:layout_height="match_parent"
         *       app:spanCount="2"
         *       android:orientation="vertical"
         *       app:layoutManager="androidx.recyclerview.widget.GridLayoutManager"/>
         */
    }


}
