package me.edanel.fulishe;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import me.edanel.fulishe.Activity.About;
import me.edanel.fulishe.Adapter.FragmentAdapter;
import me.edanel.fulishe.CallBack.Init;
import me.edanel.fulishe.Fragment.ChiDu;
import me.edanel.fulishe.Fragment.Index;
import me.edanel.fulishe.Fragment.MeiTui;
import me.edanel.fulishe.Fragment.MeiTun;
import me.edanel.fulishe.Fragment.YouGou;


public class MainActivity extends ActionBarActivity implements Init {

    private Toolbar toolbar;
    private String[] mNavTitles;
    private DrawerLayout drawerLayout;
    private ListView listView;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private LinearLayout drawerContainer;

    private List<Fragment> fragments = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitViews();
        InitEvents();
        InitFragments();
    }


    @Override
    public void InitViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.app_name));
        setSupportActionBar(toolbar);

        mNavTitles = getResources().getStringArray(R.array.navi_titles);
        drawerContainer = (LinearLayout) findViewById(R.id.drawer_layout_container);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        listView = (ListView) findViewById(R.id.left_drawer);


    }

    @Override
    public void InitEvents() {
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mNavTitles));
//        listView.setOnItemClickListener(new mDrawerItemClickListener());
    }

    private void InitFragments(){
        fragments.add(new Index());
        fragments.add(new MeiTui());
        fragments.add(new MeiTun());
        fragments.add(new YouGou());
        fragments.add(new ChiDu());
//        fragments.add(new settingFragment());

        FragmentAdapter fragmentAdapter = new FragmentAdapter(this, fragments, R.id.content_frame, listView);
        //监听点击之后关闭滑动菜单
        fragmentAdapter.setmCallBack(new FragmentAdapter.openCallBack() {
            @Override
            public void onListen() {
                drawerLayout.closeDrawer(drawerContainer);
            }
        });
    }

    private class mDrawerItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            listView.setItemChecked(position, true);
            drawerLayout.closeDrawer(drawerContainer);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()){
            case R.id.action_about:
                startActivity(new Intent(MainActivity.this, About.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
