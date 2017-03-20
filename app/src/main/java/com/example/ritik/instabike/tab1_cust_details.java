package com.example.ritik.instabike;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class tab1_cust_details extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.th_tab1_cust_det, container, false);

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



        trip_to.setText(t_history_details.trip_to_where);
        pick_up_date.setText(t_history_details.pickUpDate);
        drop_up_date.setText(t_history_details.dropUp);
        cust_name_th.setText(t_history_details.customerName);
        con_no.setText(t_history_details.contactNumber);
        email_id.setText(t_history_details.emailId);
        id_name.setText(t_history_details.idName);
        id_nu.setText(t_history_details.idNumber);
        dl.setText(t_history_details.dl_number);
        add.setText(t_history_details.address);


        return rootView;
    }


}
