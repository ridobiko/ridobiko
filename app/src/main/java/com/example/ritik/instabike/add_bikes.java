package com.example.ritik.instabike;


import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Calendar;

import static android.support.constraint.R.id.parent;

public class add_bikes extends AppCompatActivity  {
  String bike_names,bike_plate_no,bike_plate_type;
    String rent_per_day,rent_per_hour,service_renewal_month_data;
Button add_bike_butt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
              setContentView(R.layout.add_bikes);
        TextView insurance_renewal_data =(TextView)findViewById(R.id.select_insurance_renewal_date);
        TextView select_service_date_field =(TextView)findViewById(R.id.select_service_date);
        final EditText bike_name_field=(EditText)findViewById(R.id.bike_name);
        final EditText bike_plate_no_field=(EditText)findViewById(R.id.bike_plate_no);
        final EditText rent_per_day_field=(EditText)findViewById(R.id.bike_rent_per_day);
        final EditText rent_per_hour_field=(EditText)findViewById(R.id.bike_rent_per_hour);
        add_bike_butt =(Button)findViewById(R.id.add_bike_button);


        Spinner spinner_plate_type = (Spinner) findViewById(R.id.bike_plate_type_spinner);
        ArrayAdapter<CharSequence> plate_type_adapter = ArrayAdapter.createFromResource(this,
                R.array.bike_plate_type_resource, android.R.layout.simple_spinner_item);
        plate_type_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_plate_type.setAdapter(plate_type_adapter);

        spinner_plate_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                bike_plate_type= String.valueOf(adapterView.getItemAtPosition(i));
                Toast.makeText(add_bikes.this, ""+bike_plate_type, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

                Toast.makeText(add_bikes.this, "Nothing Selected", Toast.LENGTH_SHORT).show();
            }
        });


        Spinner spinner_service_renewal_month = (Spinner) findViewById(R.id.service_renewal_month_spinner);
        ArrayAdapter<CharSequence> service_renewal_month_adapter = ArrayAdapter.createFromResource(this,
                R.array.service_renewal_mon, android.R.layout.simple_spinner_item);
        service_renewal_month_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_service_renewal_month.setAdapter(service_renewal_month_adapter);


        spinner_service_renewal_month.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                service_renewal_month_data= String.valueOf(adapterView.getItemAtPosition(i));
                Toast.makeText(add_bikes.this, ""+service_renewal_month_data, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(add_bikes.this, "Nothing Selected", Toast.LENGTH_SHORT).show();
            }
        });


        add_bike_butt.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              bike_names= String.valueOf(bike_name_field.getText());
              bike_plate_no = String.valueOf(bike_plate_no_field.getText());
              rent_per_day=String.valueOf(rent_per_day_field.getText());
              rent_per_hour= String.valueOf(rent_per_hour_field.getText());

              Toast.makeText(add_bikes.this, "add_bike_button", Toast.LENGTH_SHORT).show();

          }
      });








        insurance_renewal_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Calendar now = Calendar.getInstance();
//                DatePickerDialog dpd = DatePickerDialog.newInstance((DatePickerDialog.OnDateSetListener),
//                        now.get(Calendar.YEAR),
//                        now.get(Calendar.MONTH),
//                        now.get(Calendar.DAY_OF_MONTH)
//                );
//
//                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });


    }







    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_out, R.anim.slide_in);

    }
//date picKER link
//    http://stackoverflow.com/questions/31407553/java-lang-classcastexception-listactivity-cannot-be-cast-to-datepickerdialog
}
