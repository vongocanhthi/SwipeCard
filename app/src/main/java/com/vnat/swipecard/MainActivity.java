package com.vnat.swipecard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.swipe)
    SwipeFlingAdapterView swipe;

    List<String> stringList;
    ArrayAdapter<String> stringAdapter;

    int j = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        init();
        event();
    }

    private void event() {

        swipe.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                stringList.remove(0);
                stringAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object o) {
                Toast.makeText(MainActivity.this, "Left", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCardExit(Object o) {
                Toast.makeText(MainActivity.this, "Right", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdapterAboutToEmpty(int i) {
                stringList.add("Item " + j);
                stringAdapter.notifyDataSetChanged();
                j++;
            }

            @Override
            public void onScroll(float v) {

            }
        });

        swipe.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int i, Object o) {
                Toast.makeText(MainActivity.this, "Click " + i, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init() {
        stringList = new ArrayList<>();
        stringList.add("php");
        stringList.add("c");
        stringList.add("python");
        stringList.add("java");
        stringList.add("html");
        stringList.add("c++");
        stringList.add("css");
        stringList.add("javascript");

        stringAdapter = new ArrayAdapter<>(this, R.layout.item, R.id.txtName, stringList);
        swipe.setAdapter(stringAdapter);
    }
}