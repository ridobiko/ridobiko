package com.example.ritik.instabike;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class my_bikes extends AppCompatActivity {
    ListView list_bikes ;
    int activityFlag=0;
    ProgressDialog pd;
    String [] bike_name_array;
    String [] bike_plate_no_array;
    String bb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_bikes_activity);

      //  load_data();


      list_bikes=(ListView)findViewById(R.id.my_bikes_list);
    list_bikes.setAdapter(new ridobiko_adapter(this));



    }

    public void load_data(){
        class load_bike_data extends AsyncTask<Object, Object, String> {

            @Override
            protected String doInBackground(Object... strings) {



                Request_Handler rh = new Request_Handler();
                String res = rh.sendPostRequest(Config_Key_Value.URL_MY_BIKES,null);

                try
                {
                    JSONObject jsonRootObject = new JSONObject(res);
                    JSONArray jsonArray = jsonRootObject.optJSONArray("maintenece");
                    for(int i = 0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        bike_name_array[i] = jsonObject.optString("bike_name").toString();
                        bike_plate_no_array[i] = jsonObject.optString("bike_id").toString();

                    }
                    JSONObject jo=jsonArray.getJSONObject(0);
                    bb=jo.optString("bike_name").toString();
                   // Toast.makeText(getApplicationContext(),""+jo.optString("movie").toString(),Toast.LENGTH_SHORT);

                }catch (JSONException e){ e.printStackTrace();}


                return "";
            }

            @Override
            protected void onPreExecute() {
                pd = new ProgressDialog(my_bikes.this);
                pd.setMessage("Loading Data");
                pd.setCancelable(false);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        pd.show();
                    }
                }, 100);
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String aVoid) {
                pd.dismiss();
                 Toast.makeText(getApplicationContext(),""+bb,Toast.LENGTH_SHORT);
                super.onPostExecute(aVoid);
            }


        }
        load_bike_data ld=new load_bike_data();
        ld.execute();

    }







class single_row{
    String bike_name_row;
    String bike_plate_no_row;
    int image;

    public single_row(String s, String s1, int i) {
        this.bike_name_row=s;
        this.bike_plate_no_row=s1;
        this.image=i;


    }
}


class ridobiko_adapter extends BaseAdapter {
  ArrayList<single_row> list;
   Context context;
    ridobiko_adapter(Context c){
        context=c;
        list =new ArrayList<single_row>();
        Resources res=context.getResources();
        String[] bike=res.getStringArray(R.array.bikeName);
        String[] bikePlate=res.getStringArray(R.array.bikePlateNo);
        int[] image={R.drawable.bike_image,R.drawable.bike_image,R.drawable.bike_image,R.drawable.bike_image,R.drawable.bike_image
                ,R.drawable.bike_image,R.drawable.bike_image,R.drawable.bike_image,R.drawable.bike_image,R.drawable.bike_image};

        for (int i=0;i<10;i++){
             list.add(new single_row(bike[i],bikePlate[i],image[i]));
          }
    }






    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater li=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row= li.inflate(R.layout.custom_layout_my_bikes,viewGroup,false);

        TextView bike_name= (TextView)row.findViewById(R.id.bike_name_my_bikes);
        TextView bike_plate_no=(TextView)row.findViewById(R.id.bike_plate_no_my_bikes);
        ImageView bikeImage=(ImageView)row.findViewById(R.id.imageViewBikeImage);
        Button detailsButt=(Button)row.findViewById(R.id.bike_info_butt);
        Button deleteButt=(Button)row.findViewById(R.id.delete_bike_butt);

        single_row temp=list.get(i);
        bike_name.setText(temp.bike_name_row);
        bike_plate_no.setText(temp.bike_plate_no_row);
        bikeImage.setImageResource(temp.image);

        detailsButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityFlag=1;
                setContentView(R.layout.bike_details);
                overridePendingTransition(R.anim.slide_out, R.anim.slide_in);

                ImageView edit_details=(ImageView)findViewById(R.id.edit_button);
                edit_details.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(my_bikes.this,"Edit Bike",Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });


        deleteButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog1;
                dialog1=new Dialog(my_bikes.this);
                dialog1.setContentView(R.layout.delete_alert_layout);
                dialog1.setCancelable(true);
                dialog1.setTitle("delete");
               // Toast.makeText(my_bikes.this,"",Toast.LENGTH_SHORT).show();
                dialog1.show();

                Button cancel_del_butt=(Button)dialog1.findViewById(R.id.delete_cancel);
                Button confirm_del_butt=(Button)dialog1.findViewById(R.id.delete_confirm);

                cancel_del_butt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog1.dismiss();
                    }
                });
                confirm_del_butt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(my_bikes.this,"Bike Deleted",Toast.LENGTH_SHORT).show();
                        dialog1.dismiss();
                    }
                });



            }
        });




        return row;
    }
}








    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(activityFlag==0){
            overridePendingTransition(R.anim.slide_out, R.anim.slide_in);
           // Toast.makeText(my_bikes.this,""+activityFlag,Toast.LENGTH_SHORT).show();
        }

        else if(activityFlag==1){
            Intent intent = new Intent(getApplicationContext(), my_bikes.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_out, R.anim.slide_in);
           // Toast.makeText(my_bikes.this,""+activityFlag,Toast.LENGTH_SHORT).show();
            activityFlag=0;
        }

    }

}
