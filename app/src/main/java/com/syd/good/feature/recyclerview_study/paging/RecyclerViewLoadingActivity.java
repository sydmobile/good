package com.syd.good.feature.recyclerview_study.paging;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.syd.good.databinding.MainItemCardBinding;
import com.syd.good.databinding.MainItemLoadingBinding;
import com.syd.good.databinding.RecyclerviewActivityLoadingBinding;
import com.syd.good.feature.recyclerview_study.paging.bean.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     @author Mobile
 *     e-mail : mobilesun516@gmail.com
 *     time   : 2021/11/24 15:13
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class RecyclerViewLoadingActivity extends AppCompatActivity {
    RecyclerviewActivityLoadingBinding mBinding;
    // 分页开始的索引（0是我们的第一页）
    private static final int PAGE_START = 0;
    //表示是否显示了页脚ProgressBar（即下一页正在加载）
    private boolean isLoading = false;
    // 如果当前页面是最后一页（页面加载后分页将停止）
    private boolean isLastPage = false;
    //总的页面加载数。初始加载为第0页，之后再加载2页。
    private int TOTAL_PAGES = 4;
    // 表示分页正在加载的页面。
    private int currentPage = PAGE_START;
    private PaginationAdapter mAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = RecyclerviewActivityLoadingBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        mAdapter = new PaginationAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mBinding.rlv.setLayoutManager(linearLayoutManager);
        mBinding.rlv.setItemAnimator(new DefaultItemAnimator());
        mBinding.rlv.setAdapter(mAdapter);
        mBinding.rlv.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += 1; //增加页面索引以加载下一个
//                loadNextPage();
                // 模拟1秒网络延迟
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadNextPage();
                    }
                }, 1000);
            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });

        loadFirstPage();

        mBinding.rfl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentPage = 0;
                loadFirstPage();
                mBinding.rfl.setRefreshing(false);
            }
        });


    }

    private void loadFirstPage() {
        ////获取虚拟数据
        List<Movie> movies = Movie.createMovies(4);

        mBinding.pr.setVisibility(View.GONE);
        mAdapter.clear();
        mAdapter.addAll(movies);
        if (currentPage <= TOTAL_PAGES){
            mAdapter.addLoadingFooter();
        }else {
            isLastPage = true;
        }

    }

    private void loadNextPage() {


        List<Movie> movies = Movie.createMovies(20);  // 1

        mAdapter.removeLoadingFooter();  // 2
        isLoading = false;   // 3

        mAdapter.addAll(movies);   // 4

        if (currentPage != TOTAL_PAGES) mAdapter.addLoadingFooter();  // 5
        else isLastPage = true;
    }


    class ItemViewHolder extends RecyclerView.ViewHolder{
        MainItemCardBinding mBinding;
        public ItemViewHolder(MainItemCardBinding mainItemCardBinding) {
            super(mainItemCardBinding.getRoot());
            this.mBinding = mainItemCardBinding;
        }
    }

    class LoadingViewHolder extends RecyclerView.ViewHolder{
       public  MainItemLoadingBinding mBinding;
        public LoadingViewHolder(MainItemLoadingBinding mainItemCardBinding) {
            super(mainItemCardBinding.getRoot());
            this.mBinding = mainItemCardBinding;
        }
    }

    class PaginationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private static final int LOADING = 100;
        private static final int ITEM = 200;
        Context mContext;
        List<Movie> movies = new ArrayList<>();
        public PaginationAdapter(Context context) {
            mContext = context;
        }

        // flag for footer ProgressBar
        private boolean isLoadingAdded = false;

        @Override
        public int getItemCount() {
            return movies == null ? 0 : movies.size();
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            switch (viewType){
                case LOADING:
                    MainItemLoadingBinding mainItemLoadingBinding = MainItemLoadingBinding.inflate(LayoutInflater.from(mContext),parent,false);
                    return new LoadingViewHolder(mainItemLoadingBinding);
                case ITEM:
                    MainItemCardBinding binding = MainItemCardBinding.inflate(LayoutInflater.from(mContext),parent,false);
                    return new ItemViewHolder(binding);
            }
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            if (position == movies.size() -1){

            }else {
                ((ItemViewHolder)(holder)).mBinding.tvContent.setText(movies.get(position).getName());
            }
        }

        @Override
        public int getItemViewType(int position) {
            return (position == movies.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
        }

        public void add(Movie mc) {
            movies.add(mc);
            notifyItemInserted(movies.size() - 1);
        }

        public void addAll(List<Movie> mcList) {
            for (Movie mc : mcList) {
                add(mc);
            }
        }

        public void remove(Movie city) {
            int position = movies.indexOf(city);
            if (position > -1) {
                movies.remove(position);
                notifyItemRemoved(position);
            }
        }

        public void clear() {
            isLoadingAdded = false;
            while (getItemCount() > 0) {
                remove(getItem(0));
            }
        }

        public boolean isEmpty() {
            return getItemCount() == 0;
        }

        public void addLoadingFooter() {
            isLoadingAdded = true;
            add(new Movie());
        }

        public void removeLoadingFooter() {
            isLoadingAdded = false;

            int position = movies.size() - 1;
            Movie item = getItem(position);

            if (item != null) {
                movies.remove(position);
                notifyItemRemoved(position);
            }
        }

        public Movie getItem(int position) {
            return movies.get(position);
        }
    }
}
