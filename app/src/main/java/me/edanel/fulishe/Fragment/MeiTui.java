package me.edanel.fulishe.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.edanel.fulishe.Adapter.ImagesAdapter;
import me.edanel.fulishe.Bean.CateAllBean;
import me.edanel.fulishe.CallBack.Init;
import me.edanel.fulishe.CallBack.ResultListener;
import me.edanel.fulishe.Config.Url;
import me.edanel.fulishe.Imp.ConnectedImp;
import me.edanel.fulishe.R;

/**
 * Created by Eggplant on 15-3-26.
 */
public class MeiTui extends Fragment implements Init {
    private View view;

    private RecyclerView recyclerView;
    private ImagesAdapter mImagesAdapter;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;

    private String mPages = "";
    private int[] mSpan = new int[2];

    private int pastVisibleItems;
    private int visibleItemCount;
    private int totalItemCount;
    private boolean loading = true;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    if (mImagesAdapter == null){
                        mImagesAdapter = new ImagesAdapter(getActivity(),CateAllBean.getInstance().getImgs());
                        recyclerView.setAdapter(mImagesAdapter);
                    }
                    mImagesAdapter.setImages(CateAllBean.getInstance().getImgs());
                    mImagesAdapter.notifyDataSetChanged();
                    swipeRefreshLayout.setRefreshing(false);
                    break;
                case 2:
                    mImagesAdapter.addImages(CateAllBean.getInstance().getImgs());
                    mImagesAdapter.notifyDataSetChanged();
                    swipeRefreshLayout.setRefreshing(false);
                    loading = true;
                    break;
            }
        }
    };
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_meitui, container, false);
        InitViews();
        InitEvents();
        return view;
    }

    @Override
    public void InitViews() {
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_layout_meitui);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_meitui);
    }

    @Override
    public void InitEvents() {
        swipeRefreshLayout.setColorSchemeResources(R.color.main_color);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPages = "";
                getImages();
            }
        });

//        recyclerView.setHasFixedSize(true);//??

        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);

//        mImagesAdapter = new ImagesAdapter();
//        recyclerView.setAdapter(mImagesAdapter);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getImages();
                swipeRefreshLayout.setRefreshing(true);
            }
        }, 100);

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleItemCount = staggeredGridLayoutManager.getChildCount();
                totalItemCount = staggeredGridLayoutManager.getItemCount();
                pastVisibleItems = staggeredGridLayoutManager.findFirstVisibleItemPositions(mSpan)[0];
//                Log.i("edanel","visibleItemCount --> " + visibleItemCount);
//                Log.i("edanel","totalItemCount --> " + totalItemCount);
//                Log.i("edanel","pastVisibleItems --> " + pastVisibleItems);
                if(loading){
                    if ((visibleItemCount + pastVisibleItems) >= totalItemCount){
                        loading = false;
                        mPages = "?maxid="+mImagesAdapter.getmList().get(totalItemCount-1).getId();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                getImages();
                                swipeRefreshLayout.setRefreshing(true);
                            }
                        }, 100);
                    }
                }


            }
        });
    }

    private void getImages() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final ConnectedImp connectedImp = new ConnectedImp();
                connectedImp.getImageALL(Url.HOST_MEITUI + mPages, new ResultListener() {
                    @Override
                    public void onSuccess() {
                        if (mPages.isEmpty()) {
                            handler.sendEmptyMessage(1);
                        } else {
                            handler.sendEmptyMessage(2);
                        }


                    }
                });
            }
        }).start();
    }
}
