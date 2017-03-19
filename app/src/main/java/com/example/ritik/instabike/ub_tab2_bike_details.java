package com.example.ritik.instabike;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class ub_tab2_bike_details extends Fragment{
  ListView lv ;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.ub_tab2_bike_details, container, false);

          lv=(ListView)rootView.findViewById(R.id.ub_cust_bikes);
          lv.setAdapter(new ub_bike_adapter(container.getContext()));


        return rootView;
    }
    class single_row_element{
        String bike_name_row;
        String bike_plate_no_row;
        int image;
        String rent_pd;
        String rent_ph;
        public single_row_element(String s, String s1, int i, String s2, String s3) {
            this.bike_name_row=s;
            this.bike_plate_no_row=s1;
            this.image=i;
            this.rent_pd=s2;
            this.rent_ph=s3;


        }
    }


    public class ub_bike_adapter extends BaseAdapter{
        ArrayList<single_row_element> list;

           Context con;
        ub_bike_adapter(Context c){
              con=c;
            list =new ArrayList<single_row_element>();
            Resources res=con.getResources();
            String[] bike=res.getStringArray(R.array.bikeName);
            String[] bikePlate=res.getStringArray(R.array.bikePlateNo);
            int[] image={R.drawable.bike_image,R.drawable.bike_image,R.drawable.bike_image,R.drawable.bike_image,R.drawable.bike_image
                    ,R.drawable.bike_image,R.drawable.bike_image,R.drawable.bike_image,R.drawable.bike_image,R.drawable.bike_image};
            String[] rent_pd=res.getStringArray(R.array.bikePlateNo);
            String[] rent_ph=res.getStringArray(R.array.bikePlateNo);
            for (int i=0;i<9;i++){
                list.add(new single_row_element(bike[i],bikePlate[i],image[i],rent_pd[i],rent_ph[i]));
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
            LayoutInflater li = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = li.inflate(R.layout.custon_ub_bike, viewGroup, false);

            ImageView bike_image=(ImageView)row.findViewById(R.id.imageViewBikeImage);
            TextView bike_name=(TextView)row.findViewById(R.id.bike_name_th);
            TextView bike_plate_no=(TextView)row.findViewById(R.id.bike_plate_no_th);
            TextView bike_rent_per_day=(TextView)row.findViewById(R.id.th_rent_pd);
            TextView bike_rent_per_hour=(TextView)row.findViewById(R.id.th_rent_ph);

            single_row_element temp =list.get(i);
            bike_image.setImageResource(temp.image);
            bike_name.setText(temp.bike_name_row);
            bike_plate_no.setText(temp.bike_plate_no_row);
            bike_rent_per_day.setText(temp.rent_pd);
            bike_rent_per_hour.setText(temp.rent_ph);
            return row;
        }
    }


}
