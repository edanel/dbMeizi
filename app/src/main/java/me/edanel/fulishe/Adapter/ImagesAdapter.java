package me.edanel.fulishe.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import me.edanel.fulishe.Bean.imagesBean;
import me.edanel.fulishe.Config.Url;
import me.edanel.fulishe.R;
import me.edanel.fulishe.Utils.ImageLoadOptions;
import me.edanel.fulishe.Utils.ImagePagerActivity;

/**
 * Created by Eggplant on 15-3-26.
 */
public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ViewHolder> {

    private List<imagesBean> mList;
    private Context mContext;

    public ImagesAdapter(Context context,List<imagesBean> list) {
        mContext = context;
        mList = new ArrayList<>();
        mList = list;
    }

    public void addImages(List<imagesBean> list){
        mList.addAll(list);
    }
    public void setImages(List<imagesBean> list){
        mList = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView ig;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recy_item_images, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        holder.ig = (ImageView) view.findViewById(R.id.recy_item_ig);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        ImageLoader.getInstance().displayImage(Url.HOST_PIC + mList.get(i).getPath(),viewHolder.ig, ImageLoadOptions.getImagesOptions());

        viewHolder.ig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,
                        ImagePagerActivity.class);
                Log.i("edanel", "pic add --> " + Url.HOST_PIC + mList.get(i).getPath());
                intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_URLS,
                        new String[]{Url.HOST_PIC + mList.get(i).getPath()});
                intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_INDEX, 0);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public List<imagesBean> getmList() {
        return mList;
    }
}
