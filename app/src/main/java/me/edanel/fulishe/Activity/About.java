package me.edanel.fulishe.Activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import me.edanel.fulishe.CallBack.Init;
import me.edanel.fulishe.R;

/**
 * Created by Eggplant on 15-3-26.
 */
public class About extends ActionBarActivity implements Init{

    private Toolbar toolbar;

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

    }

    @Override
    public void InitEvents() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        finish();
        return super.onOptionsItemSelected(item);
    }
}
