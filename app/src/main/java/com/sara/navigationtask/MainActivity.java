package com.sara.navigationtask;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.sara.navigationtask.Fragments.ContactUs;
import com.sara.navigationtask.Fragments.Home;
import com.sara.navigationtask.Fragments.Profile;
import com.sara.navigationtask.Fragments.Settings;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private LinearLayoutManager layoutManager;
    private ActionBarDrawerToggle toggle;

    private int[] icons = {R.drawable.ic_home ,
            R.drawable.ic_profile,
            R.drawable.ic_contactUs,
            R.drawable.ic_settings};
    private String[] names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       initViews();
    }

    private void initViews(){

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        recyclerView.setHasFixedSize(true);

        names = getResources().getStringArray(R.array.names);

        ArrayList<ItemModel> items = getData();
        adapter = new RecyclerAdapter(getApplicationContext() , items);
        recyclerView.setAdapter(adapter);

        adapter.setItemClickLister(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                replaceFragment(position);
            }
        });

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private ArrayList<ItemModel> getData(){

        ArrayList<ItemModel> rowItem = new ArrayList<>();
        for (int i = 0 ; i < names.length ; i++){
            ItemModel itemModel = new ItemModel();
            itemModel.setIconImage(icons[i]);
            itemModel.setItemText(names[i]);
            rowItem.add(itemModel);
        }
        return rowItem;
    }

    private void setFragment(Fragment fragment){
        FragmentTransaction frag = getSupportFragmentManager().beginTransaction();
        frag.replace(R.id.container, fragment);
        frag.commit();
    }

    private void replaceFragment(int position){
        switch (position){
            case 0:
                setFragment(new Home());
                break;
            case 1:
                setFragment(new Profile());
                break;
            case 2:
                setFragment(new ContactUs());
                break;
            case 3:
                setFragment(new Settings());
                break;
            default:
                break;
        }
    }

}
