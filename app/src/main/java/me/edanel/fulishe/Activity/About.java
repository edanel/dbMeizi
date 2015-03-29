package me.edanel.fulishe.Activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import me.edanel.fulishe.CallBack.Init;
import me.edanel.fulishe.R;

/**
 * Created by Eggplant on 15-3-26.
 */
public class About extends ActionBarActivity implements Init{

    private Toolbar toolbar;
    private TextView mClearCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        InitViews();
        InitEvents();
    }

    @Override
    public void InitViews() {
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        mClearCache = (TextView) findViewById(R.id.about_clearcache);
    }

    @Override
    public void InitEvents() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mClearCache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageLoader.getInstance().clearMemoryCache();//清除本地缓存
                ImageLoader.getInstance().clearDiskCache();//清除内存缓存
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
