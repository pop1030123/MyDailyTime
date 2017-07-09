package com.popfu.mydailytime.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.popfu.mydailytime.R;
import com.popfu.mydailytime.event.EventAddUnit;
import com.popfu.mydailytime.event.EventDeleteUnit;
import com.popfu.mydailytime.event.EventUpdateUnit;
import com.popfu.mydailytime.presenter.MainPresenter;

import static com.popfu.mydailytime.ui.TimeActivity.KEY_TIME_UNIT;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainAdapter.Callback {

    private RecyclerView mRecyclerView ;

    private MainAdapter mMainAdapter ;

    private MainPresenter mMainPresenter ;

    LinearLayoutManager mLayoutManager ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mLayoutManager = new LinearLayoutManager(this) ;
        mMainPresenter = new MainPresenter() ;
        mMainAdapter = new MainAdapter(this) ;
        mMainAdapter.setCallback(this);
        mMainAdapter.setTimeUnitList(mMainPresenter.getAllTimeUnits());
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view) ;
        mRecyclerView.setAdapter(mMainAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimeActivity_.intent(MainActivity.this)
                        .start() ;
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onItemClick(int position) {
        TimeActivity_.intent(this)
                .extra(KEY_TIME_UNIT ,mMainAdapter.getItem(position))
                .start() ;
    }

    public void onEvent(EventAddUnit eventAddUnit){
        mMainAdapter.addItem(eventAddUnit.getUnit());
    }
    public void onEvent(EventUpdateUnit eventUpdateUnit){
        mMainAdapter.updateItem(eventUpdateUnit.getUnit());
    }
    public void onEvent(EventDeleteUnit eventDeleteUnit){
        mMainAdapter.deleteItem(eventDeleteUnit.getUnit());
    }
}
