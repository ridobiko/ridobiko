package com.example.ritik.instabike;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ub_tab1_cust_details extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.ub_tab1_cust_details, container, false);

        TextView trip_to=(TextView)rootView.findViewById(R.id.trip_to);
        TextView pick_up_date=(TextView)rootView.findViewById(R.id.pick_up_);
        TextView drop_up_date=(TextView)rootView.findViewById(R.id.drop_up);
        TextView cust_name_th=(TextView)rootView.findViewById(R.id.cust_name);
        TextView con_no=(TextView)rootView.findViewById(R.id.contact_no);
        TextView email_id=(TextView)rootView.findViewById(R.id.cust_email);
        TextView id_name=(TextView)rootView.findViewById(R.id.id_name);
        TextView id_nu=(TextView)rootView.findViewById(R.id.id_number);
        TextView dl=(TextView)rootView.findViewById(R.id.dl_number);
        TextView add=(TextView)rootView.findViewById(R.id.address);



        trip_to.setText(upcoming_booking_details.trip_to_where);
        pick_up_date.setText(upcoming_booking_details.pickUpDate);
        drop_up_date.setText(upcoming_booking_details.dropUp);
        cust_name_th.setText(upcoming_booking_details.customerName);
        con_no.setText(upcoming_booking_details.contactNumber);
        email_id.setText(upcoming_booking_details.emailId);
        id_name.setText(upcoming_booking_details.idName);
        id_nu.setText(upcoming_booking_details.idNumber);
        dl.setText(upcoming_booking_details.dl_number);
        add.setText(upcoming_booking_details.address);


        return rootView;
    }


}
