package com.example.ritik.instabike;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import static android.R.attr.data;

public class Maintaince extends AppCompatActivity {
    ListView lv;
    //String Bike_Name[]={"A","B"};
    //String Bike_Id[]={"A","B"};


    ProgressDialog pg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintaince);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pg = new ProgressDialog(Maintaince.this);
        pg.setTitle("Loading");
        pg.setMessage("Please Wait..!!");


        ConnectivityManager cm =
                (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        if(isConnected)
        {
            viewdata();



        }
        else
        {
            Toast.makeText(Maintaince.this, "No Internet Connection", Toast.LENGTH_SHORT).show();

        }

    }
    public void viewdata()
    {


        class abc extends AsyncTask<Void,Void,String>
        {
            String Bike_Name[];
            String Bike_Id[];
            @Override
            protected String doInBackground(Void... v) {




                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_Maintenance);


                try
                {
                    JSONObject jsonRootObject = new JSONObject(res);
                    JSONArray jsonArray = jsonRootObject.optJSONArray("result");

                     Bike_Name=new String[jsonArray.length()];
                     Bike_Id=new String[jsonArray.length()];

                    if(jsonArray.length()>0) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            Bike_Id[i] = jsonObject.optString("id");
                            Bike_Name[i] = jsonObject.optString("name");


                        }

                        return "success";


                    }
                    else return "failure";

                }catch (JSONException e){ e.printStackTrace();}


                return res;
            }

            @Override
            protected void onPreExecute() {
                //pg.show();
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {



                MaintainceCustomListAdapter adapter=new MaintainceCustomListAdapter(Maintaince.this, Bike_Name, Bike_Id);
                lv=(ListView)findViewById(R.id.listView);
                assert lv != null;

                if(Bike_Name!=null && Bike_Name.length>0){
                    lv.setAdapter(adapter);
                }else{
                    // Some messages
                }


                pg.dismiss();
                Toast.makeText(Maintaince.this, s, Toast.LENGTH_SHORT).show();




                super.onPostExecute(s);
            }
        }
        abc  ae = new abc();
        ae.execute();


    }

}


class MaintainceCustomListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] Bike_Name;
    private final String[] Bike_Id;
    ProgressDialog pg;
    public MaintainceCustomListAdapter(Activity context, String[] Bike_Name, String[] Bike_Id) {
        super(context, R.layout.custom_layout_maintaince, Bike_Name);

        this.context=context;
        this.Bike_Name=Bike_Name;
        this.Bike_Id=Bike_Id;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.custom_layout_maintaince, null,true);

        TextView Name = (TextView) rowView.findViewById(R.id.item3);
        TextView Id = (TextView) rowView.findViewById(R.id.item4);

        Button Maintanance=(Button) rowView.findViewById(R.id.maintanance);
        Maintanance.setTag(position);


        Name.setText(Bike_Name[position]);
        Id.setText(Bike_Id[position]);


        Maintanance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final int position = (Integer) view.getTag();


                final Dialog dialog1 = new Dialog(getContext());
                dialog1.setContentView(R.layout.maintaince_dialog_1);
                dialog1.setCancelable(false);
                dialog1.setTitle("Maintenance");




                Button bt1= (Button) dialog1.findViewById(R.id.nextbutton5);
                Button bt2= (Button) dialog1.findViewById(R.id.nextbutton6);




                bt1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        dialog1.dismiss();

                    }
                });
                bt2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog1.dismiss();



                        final Dialog dialog2 = new Dialog(getContext());
                        dialog2.setContentView(R.layout.maintaince_dialog_yes_no);
                        dialog2.setCancelable(false);
                        dialog2.setTitle("Are You Sure ?");

                        Button bt3= (Button) dialog2.findViewById(R.id.nextbutton7);
                        Button bt4= (Button) dialog2.findViewById(R.id.nextbutton8);


                        bt3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog2.dismiss();
                                removeFromMaintenance(Bike_Name[position],Bike_Id[position]);
                                Intent intent=context.getIntent();
                                context.startActivity(intent);


                            }
                        });
                        bt4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view)
                            {
                                dialog2.dismiss();
                            }
                        });



                        dialog2.show();

                    }

                });


                dialog1.show();



            }
        });

        return rowView;

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

    public void removeFromMaintenance(final String bike_name, final String bike_Id)
    {


        class abc extends AsyncTask<Void,Void,String>
        {

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_BIKE_ID,bike_Id);
                params.put(Config.KEY_BIKE_NAME,bike_name);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_Maintenance, params);
                return res;
            }

            @Override
            protected void onPreExecute() {
                pg.show();
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                pg.dismiss();


            }
        }
        abc  ae = new abc();
        ae.execute();


    }

}
