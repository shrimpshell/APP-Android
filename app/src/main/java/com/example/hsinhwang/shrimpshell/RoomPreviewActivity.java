package com.example.hsinhwang.shrimpshell;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

public class RoomPreviewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_preview);
        initialization();
    }

    private void initialization() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }
}
