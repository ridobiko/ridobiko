package com.example.ritik.instabike;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class trip_history extends AppCompatActivity {
ListView list_th;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trip_history_layout);

         list_th=(ListView)findViewById(R.id.trip_history_list);
         list_th.setAdapter(new tripHistory_adapter(this));




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Filter here", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    }


class single_row {
    String trip_to;
    String pickUpDate;
    String dropUp;
    String customerName;
    String contactNumber;
    String emailId;
    String idName;
    String idNumber;
    String dl_number;
    String address;


    public single_row(String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8, String s9, String s10) {
        this.trip_to=s1;
        this.pickUpDate=s2;
        this.dropUp=s3;
        this.customerName=s4;
        this.contactNumber=s5;
        this.emailId=s6;
        this.idName=s7;
        this.idNumber=s8;
        this.dl_number=s9;
        this.address=s10;


    }
}


class tripHistory_adapter extends BaseAdapter{
         ArrayList<single_row> list;
         Context context;
         tripHistory_adapter(Context c){
             context=c;
            list =new ArrayList<single_row>();

             Resources res = context.getResources();
             String[] trip_to = res.getStringArray(R.array.customerName);
             String[] pickUp = res.getStringArray(R.array.pickUpDate);
             String[] dropUp = res.getStringArray(R.array.DropDate);
             String[] custName = res.getStringArray(R.array.customerName);
             String[] custNo = res.getStringArray(R.array.customerNo);
             String[] custEmail = res.getStringArray(R.array.noOFBIKES);
             String[] IdName = res.getStringArray(R.array.pickUpDate);
             String[] IdNumber = res.getStringArray(R.array.DropDate);
             String[] dl_no = res.getStringArray(R.array.noOFBIKES);
             String[] address = res.getStringArray(R.array.pickUpDate);

             for (int i = 0; i < 9; i++) {
                 list.add(new single_row(trip_to[i],pickUp[i],dropUp[i],custName[i],
                         custNo[i],custEmail[i],IdName[i],IdNumber[i],dl_no[i],address[i]));
             }
         }



         @Override
         public int getCount() {
           return  list.size();
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
         public View getView(final int i, View view, ViewGroup viewGroup) {
             LayoutInflater li=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
             View row= li.inflate(R.layout.custom_trip_history,viewGroup,false);

             LinearLayout th_Layout=(LinearLayout)row.findViewById(R.id.trip_history_layout);
             TextView cusName=(TextView)row.findViewById(R.id.cust_name_th);
             TextView custNo=(TextView)row.findViewById(R.id.cust_no_th);
             TextView noOfBikes=(TextView)row.findViewById(R.id.no_of_bikes_th);
             TextView pickUpDate=(TextView)row.findViewById(R.id.pickUp_th);
             TextView dropDate=(TextView)row.findViewById(R.id.dropUp_th);

             final single_row temp= list.get(i);
             cusName.setText(temp.customerName);
             custNo.setText(temp.contactNumber);
             noOfBikes.setText("10");
             pickUpDate.setText(temp.pickUpDate);
             dropDate.setText(temp.dropUp);


             th_Layout.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     Intent intent = new Intent(context, t_history_details.class);
                     intent.putExtra("TT",temp.trip_to);
                     intent.putExtra("PD",temp.pickUpDate);
                     intent.putExtra("DD",temp.dropUp);
                     intent.putExtra("CUSN",temp.customerName);
                     intent.putExtra("CONN",temp.contactNumber);
                     intent.putExtra("EI",temp.emailId);
                     intent.putExtra("INAME",temp.idName);
                     intent.putExtra("INUM",temp.idNumber);
                     intent.putExtra("DL",temp.dl_number);
                     intent.putExtra("ADD",temp.address);

                     context.startActivity(intent);

                     //Toast.makeText(context, "layout No"+i, Toast.LENGTH_SHORT).show();
                 }
             });




             return row;
         }
     }




