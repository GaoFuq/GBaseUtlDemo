package com.gfq.gg.refreshview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gfq.gbaseutl.databinding.RVBindingAdapter;
import com.gfq.gbaseutl.databinding.SuperBindingViewHolder;
import com.gfq.gg.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

/**
 * create by 高富强
 * on {2019/11/5} {10:30}
 * desctapion:
 */
public class RefreshView extends FrameLayout{

    private Context context;

    private int currentPage=1;//当前页
    private int pageSize=10;//每页数据条数
    private int totalPage=100;//总页数
    private int totalCount=1000;//数据总量
    private RecyclerView recyclerView;
    private SmartRefreshLayout smartRefreshLayout;
    private RVBindingAdapter adapter;
    private int layoutResId;
    private int br_id;


    public RefreshView(@NonNull Context context) {
        this(context,null);
    }

    public RefreshView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RefreshView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        initThis();
    }

    private void initThis() {
       View view = inflate(context, R.layout.refreshview,this);
        smartRefreshLayout = view.findViewById(R.id.smartrefresh);
        recyclerView = view.findViewById(R.id.recycleView);

       smartRefreshLayout.setRefreshHeader(new ClassicsHeader(context));
       smartRefreshLayout.setRefreshFooter(new ClassicsFooter(context));

       smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
           @Override
           public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if(refreshViewListener!=null){
                    currentPage++;
                    if(currentPage>totalPage){
                        currentPage=totalPage;
                        refreshLayout.finishLoadMoreWithNoMoreData();
                        return;
                    }
                    List list = refreshViewListener.requestLoadMore(currentPage, pageSize, refreshLayout);
                    adapter.addAll(list);
                }
           }

           @Override
           public void onRefresh(@NonNull RefreshLayout refreshLayout) {
               if(refreshViewListener!=null){
                   currentPage++;
                   if(currentPage>totalPage){
                       currentPage=totalPage;
                       refreshLayout.finishRefreshWithNoMoreData();
                       return;
                   }
                   List list = refreshViewListener.requestRefresh(currentPage, pageSize, refreshLayout);
                   adapter.clear();
                   adapter.setDataList(list);
               }
           }
       });
       smartRefreshLayout.autoRefresh();
    }



    public void setAdapter(RecyclerView.Adapter<RecyclerView.ViewHolder> adapter){
        if(adapter instanceof RVBindingAdapter) {
            this.adapter = (RVBindingAdapter) adapter;
        }
        recyclerView.setAdapter(adapter);
    }

    public void setLayoutManager(RecyclerView.LayoutManager manager){
        recyclerView.setLayoutManager(manager);
    }



    public interface RefreshViewListener<T>{
        List<T> requestLoadMore(int currentPage,int pageSize,RefreshLayout layout);
        List<T> requestRefresh(int currentPage,int pageSize,RefreshLayout layout);
    }

    private RefreshViewListener refreshViewListener;

    public void setRefreshViewListener(RefreshViewListener refreshViewListener) {
        this.refreshViewListener = refreshViewListener;
    }

}
