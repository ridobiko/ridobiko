package com.example.ritik.instabike;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class add_bikes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.setContentView(R.layout.add_bikes_activity);
              setContentView(R.layout.add_bikes);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_out, R.anim.slide_in);

    }
}
