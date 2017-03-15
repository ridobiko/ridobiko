package com.example.ritik.instabike;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        ImageView openDrawer =(ImageView)findViewById(R.id.open_drawer);
        ImageView notification=(ImageView)findViewById(R.id.notification);
// Ridobiko
     final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);


        openDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.LEFT);
            }
        });


        ImageView createTrip =(ImageView)findViewById(R.id.create_trip);
        ImageView upcomingBooking =(ImageView)findViewById(R.id.upcoming_booking);
        ImageView onTripBikes =(ImageView)findViewById(R.id.onTrip);
        ImageView availableBikes =(ImageView)findViewById(R.id.available);
        ImageView maintenanceBikes =(ImageView)findViewById(R.id.maintenance);
        ImageView tripHistory =(ImageView)findViewById(R.id.trips);
        ImageView myAccount =(ImageView)findViewById(R.id.my_account_info);
        ImageView myBikes =(ImageView)findViewById(R.id.my_bikes);
        ImageView support =(ImageView)findViewById(R.id.get_support);
        ImageView addBikes =(ImageView)findViewById(R.id.add_bikes);
        ImageView profilePic =(ImageView)findViewById(R.id.profile_photo);
        ImageView addProfilePhoto=(ImageView)findViewById(R.id.profile_add);
        Button logout =(Button)findViewById(R.id.logOut);

        createTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "CreateTrip", Toast.LENGTH_SHORT).show();
            }
        });
        upcomingBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "BookedBikes", Toast.LENGTH_SHORT).show();
            }
        });
        onTripBikes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "OntripBikes", Toast.LENGTH_SHORT).show();
            }
        });
        availableBikes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "AvailableBikes", Toast.LENGTH_SHORT).show();
            }
        });
        maintenanceBikes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "MaintenanceBikes", Toast.LENGTH_SHORT).show();
            }
        });
        tripHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "TripHistory", Toast.LENGTH_SHORT).show();
            }
        });
        myAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "MyAccount", Toast.LENGTH_SHORT).show();
            }
        });
        myBikes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), my_bikes.class);
                startActivity(intent);
                overridePendingTransition( R.anim.slide_out, R.anim.slide_in);
                Toast.makeText(getApplicationContext(), "MyBikes", Toast.LENGTH_SHORT).show();
            }
        });
        addBikes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), add_bikes.class);
                startActivity(intent);
                overridePendingTransition( R.anim.slide_out, R.anim.slide_in);
                // Toast.makeText(getApplicationContext(), "Add Bikes", Toast.LENGTH_SHORT).show();
            }
        });
        support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Support", Toast.LENGTH_SHORT).show();
            }
        });
        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "MyPic", Toast.LENGTH_SHORT).show();
            }
        });
        addProfilePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "AddPic", Toast.LENGTH_SHORT).show();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "LogOut", Toast.LENGTH_SHORT).show();
            }
        });




        //Floating button

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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



}
