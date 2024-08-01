package com.android.coffee.activites;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.coffee.R;
import com.android.coffee.fragments.HomeFragment;
import com.android.coffee.adapters.MyRecyclerViewAdapter;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {

    Toolbar mToolbar;
    MyRecyclerViewAdapter adapter;

    private static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_dashboard);

        fragmentManager = getSupportFragmentManager();


        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Beverage Configuration");
        ((TextView) mToolbar.getChildAt(0)).setTextSize(18);

        ArrayList<String> names = new ArrayList<>();
        names.add("Americano");
        names.add("Black Coffee");
        names.add("Cappuccino");
        names.add("Cortado");
        names.add("Espresso");
        names.add("Flat White");
        names.add("Hot Water");
        names.add("Lattle");
        names.add("Lungo");

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rcv_listData);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, names);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        loadHomeFragment(names.get(0));
    }

    private void loadHomeFragment(String s) {

        Bundle bundle = new Bundle();
        bundle.putString("drinkName", s);
        Fragment fragment;
        fragment = new HomeFragment();
        fragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.fragment, fragment).commit();
    }


    @Override
    public void onItemClick(View view, int position) {
        loadHomeFragment(adapter.getItem(position));
    }
}