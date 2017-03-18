package com.example.ritik.instabike;

import android.content.Context;
import android.content.res.Resources;
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
        setContentView(R.layout.activity_trip_history);

         list_th=(ListView)findViewById(R.id.trip_history_list);
         list_th.setAdapter(new tripHistory_adapter(this));




    }

    class single_row{
        String cust_name;
        String cust_no;
        String no_of_bikes;
        String pick_up;
        String drop_date;


        public single_row(String s, String s1, String s2, String s3,String s4) {
           this.cust_name=s;
            this.cust_no=s1;
            this.no_of_bikes=s2;
            this.pick_up=s3;
            this.drop_date=s4;



        }
    }

     class tripHistory_adapter extends BaseAdapter{
         ArrayList<single_row> list;
         Context context;
         tripHistory_adapter(Context c){
             context=c;
            list =new ArrayList<single_row>();
        Resources res=context.getResources();
        String[] custName=res.getStringArray(R.array.customerName);
        String[] custNo=res.getStringArray(R.array.customerNo);
        String[] noOfBikes=res.getStringArray(R.array.noOFBIKES);
        String[] pickUp=res.getStringArray(R.array.pickUpDate);
        String[] dropUp=res.getStringArray(R.array.DropDate);

             for (int i=0;i<9;i++){
                 list.add(new single_row(custName[i],custNo[i],noOfBikes[i],pickUp[i],dropUp[i]));
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

             single_row temp= list.get(i);
             cusName.setText(temp.cust_name);
             custNo.setText(temp.cust_no);
             noOfBikes.setText(temp.no_of_bikes);
             pickUpDate.setText(temp.pick_up);
             dropDate.setText(temp.drop_date);


             th_Layout.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     Toast.makeText(trip_history.this, "layout No"+i, Toast.LENGTH_SHORT).show();
                 }
             });




             return row;
         }
     }



}
