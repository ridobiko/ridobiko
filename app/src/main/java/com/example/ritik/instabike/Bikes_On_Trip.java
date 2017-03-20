package com.example.ritik.instabike;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Bikes_On_Trip extends AppCompatActivity {
    ListView lv;
    String Bike_Name[]={"A","B"};
    String Bike_Id[]={"A","B"};
    String Name[]={"A","B"};
    String Id[]={"A","B"};
    ProgressDialog pg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bikes__on__trip);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        ConnectivityManager cm =
                (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        if(isConnected)
        {
            //viewdata();

            BikesOnTripCustomListAdapter adapter=new BikesOnTripCustomListAdapter(this, Bike_Name, Bike_Id,Name,Id);
            lv=(ListView)findViewById(R.id.listView);
            lv.setAdapter(adapter);

        }
        else
        {
            Toast.makeText(Bikes_On_Trip.this, "No INternet Connection", Toast.LENGTH_SHORT).show();

        }
    }
    public void viewdata()
    {


        class abc extends AsyncTask<Void,Void,String>
        {

            @Override
            protected String doInBackground(Void... v) {

                pg = new ProgressDialog(getApplicationContext());
                pg.setTitle("Loading");
                pg.setMessage("Please Wait..!!");
                pg.setCancelable(false);


                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_Bikes_On_Trip,null);

                try
                {
                    JSONObject jsonRootObject = new JSONObject(res);
                    JSONArray jsonArray = jsonRootObject.optJSONArray("Maintenance");

                    if(jsonArray.length()>0) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            Bike_Id[i] = jsonObject.optString("bike_id");
                            Bike_Name[i] = jsonObject.optString("bike_name");
                            Name[i]=jsonObject.optString("Name");
                            Id[i]=jsonObject.optString("Id");


                        }

                        return "success";


                    }
                    else return "failure";

                }catch (JSONException e){ e.printStackTrace();}


                return "failure";
            }

            @Override
            protected void onPreExecute() {
                pg.show();
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {


                pg.dismiss();


                super.onPostExecute(s);
            }
        }
        abc  ae = new abc();
        ae.execute();


    }

}
class BikesOnTripCustomListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] Bike_Name;
    private final String[] Bike_Id;

    private final String[] Name;
    private final String[] Id;
    ProgressDialog pg;

    public BikesOnTripCustomListAdapter(Activity context, String[] Bike_Name, String[] Bike_Id,String Name[],String Id[])
    {

        super(context, R.layout.custom_layout_bikes_on_trip, Bike_Name);

        this.context=context;
        this.Bike_Name=Bike_Name;
        this.Bike_Id=Bike_Id;
        this.Name=Name;
        this.Id=Id;
    }



    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.custom_layout_bikes_on_trip, null,true);

        TextView tBike_Name = (TextView) rowView.findViewById(R.id.item1);
        TextView tBike_Id = (TextView) rowView.findViewById(R.id.item2);
        TextView tName = (TextView) rowView.findViewById(R.id.item3);
        TextView tId = (TextView) rowView.findViewById(R.id.item4);
        Button Available=(Button) rowView.findViewById(R.id.available);
        Button Maintaince=(Button) rowView.findViewById(R.id.maintenance);

        Maintaince.setTag(position);
        Available.setTag(position);

        tBike_Name.setText(Bike_Name[position]);
        tBike_Id.setText(Bike_Id[position]);
        tName.setText(Name[position]);
        tId.setText(Id[position]);

        Available.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int position = (Integer) view.getTag();

                final Dialog dialog1;
                dialog1=new Dialog(getContext());
                dialog1.setContentView(R.layout.available_dialog);
                dialog1.setCancelable(false);
                dialog1.setTitle("Availability");

                Button bt1= (Button) dialog1.findViewById(R.id.nextbutton5);
                Button bt2= (Button) dialog1.findViewById(R.id.nextbutton6);


                bt1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Availabledata(Bike_Id[position]);
                        dialog1.dismiss();



                    }
                });
                bt2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        dialog1.dismiss();
                    }
                });

                dialog1.show();


            }
        });

        Maintaince.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog1;
                dialog1=new Dialog(getContext());
                dialog1.setContentView(R.layout.maintaince_dialog);
                dialog1.setCancelable(true);
                dialog1.setTitle("Maintaince");


                Button bt1= (Button) dialog1.findViewById(R.id.nextbutton7);



                bt1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Maintenancedata(Bike_Id[position]);

                        dialog1.dismiss();

                    }
                });


                dialog1.show();



            }
        });

        return rowView;

    }
    public void Maintenancedata(final String Id)
    {

        class abc extends AsyncTask<Void,Void,String>
        {

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_BIKE_ID,Id);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(com.example.ritik.instabike.Config.URL_LOGIN, params);
                return res;
            }

            @Override
            protected void onPreExecute() {
                pg.show();
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {

                pg.dismiss();
                super.onPostExecute(s);
            }
        }
        abc  ae = new abc();
        ae.execute();


    }

    public void Availabledata(final String Id)
    {


        class abc extends AsyncTask<Void,Void,String>
        {

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_BIKE_ID,Id);


                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(com.example.ritik.instabike.Config.URL_LOGIN, params);
                return res;
            }

            @Override
            protected void onPreExecute() {
                pg.show();
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {

                pg.dismiss();
               
                super.onPostExecute(s);
            }
        }
        abc  ae = new abc();
        ae.execute();


    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return Bike_Name.length;
    }

    @Override
    public String getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

}
