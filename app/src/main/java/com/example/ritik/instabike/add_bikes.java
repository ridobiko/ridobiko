package com.example.ritik.instabike;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class add_bikes extends AppCompatActivity implements com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener {
    String bike_names=null, bike_plate_no=null, bike_plate_type_data=null;
    String rent_per_day=null, rent_per_hour=null, service_renewal_month_data=null;
    Button add_bike_butt;
    private static final int PICK_IMAGE = 1;
    Uri imageUri=null;
    String in_date=null,se_date=null,image_base64_content=null;
    ImageView bike_image;
    TextView select_insurance_renewal_date,select_service_date_field;
    private  int flag =0;
    Bitmap bitmap_s=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_bikes);
         select_insurance_renewal_date = (TextView) findViewById(R.id.select_insurance_renewal_date);
         select_service_date_field = (TextView) findViewById(R.id.select_service_date);
        final EditText bike_name_field = (EditText) findViewById(R.id.bike_name);
        final EditText bike_plate_no_field = (EditText) findViewById(R.id.bike_plate_no);
        final EditText rent_per_day_field = (EditText) findViewById(R.id.bike_rent_per_day);
        final EditText rent_per_hour_field = (EditText) findViewById(R.id.bike_rent_per_hour);
        add_bike_butt = (Button) findViewById(R.id.add_bike_button);

        final Button upload_image = (Button) findViewById(R.id.upload_image);

        Spinner spinner_plate_type = (Spinner) findViewById(R.id.bike_plate_type_spinner);
        ArrayAdapter<CharSequence> plate_type_adapter = ArrayAdapter.createFromResource(this,
                R.array.bike_plate_type_resource, android.R.layout.simple_spinner_item);
        plate_type_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_plate_type.setAdapter(plate_type_adapter);

        spinner_plate_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                bike_plate_type_data = String.valueOf(adapterView.getItemAtPosition(i));
                Toast.makeText(add_bikes.this, "" + bike_plate_type_data, Toast.LENGTH_SHORT).show();
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
                service_renewal_month_data = String.valueOf(adapterView.getItemAtPosition(i));
                Toast.makeText(add_bikes.this, "" + service_renewal_month_data, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(add_bikes.this, "Nothing Selected", Toast.LENGTH_SHORT).show();
            }
        });




        upload_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bike_image = (ImageView) findViewById(R.id.bike_image);
                pickImageFromGallery();
            }
        });

        select_insurance_renewal_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar now = Calendar.getInstance();
                com.wdullaer.materialdatetimepicker.date.DatePickerDialog dpd = com.wdullaer.materialdatetimepicker.date.DatePickerDialog.newInstance(
                        add_bikes.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                   flag=0;
                dpd.show(getFragmentManager(), "InsuranceRenewalDate");
            }
        });

        select_service_date_field.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar now = Calendar.getInstance();
                com.wdullaer.materialdatetimepicker.date.DatePickerDialog dpd = com.wdullaer.materialdatetimepicker.date.DatePickerDialog.newInstance(
                        add_bikes.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                   flag=1;
                dpd.show(getFragmentManager(), "ServiceRenewalDate");
            }
        });


   // final submission
        add_bike_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bike_names = String.valueOf(bike_name_field.getText());
                bike_plate_no = String.valueOf(bike_plate_no_field.getText());
                rent_per_day = String.valueOf(rent_per_day_field.getText());
                rent_per_hour = String.valueOf(rent_per_hour_field.getText());
                //in_date
                //se_date
                //bike_plate_type_data;
               // service_renewal_month_data
               //base_64_content
                if(
                checkDataEntry( bike_names , bike_plate_no,rent_per_hour,rent_per_day,in_date,se_date,
                        bike_plate_type_data,service_renewal_month_data,image_base64_content)==true){
                    Toast.makeText(add_bikes.this,"Bike Added",Toast.LENGTH_SHORT).show();
                }


               // Toast.makeText(add_bikes.this,"Bike Added",Toast.LENGTH_SHORT).show();

            }
        });
    }

    private boolean checkDataEntry(String bn, String bpn, String rph,
                                String rpd, String id, String sd, String bptd,
                                String srmd, String imbase64) {
        if((bn==null||bn.isEmpty())||(bpn==null||bpn.isEmpty())||(rph==null||rph.isEmpty())||(rpd==null||rpd.isEmpty())||(id==null||id.isEmpty())
                ||(sd==null||sd.isEmpty())||(bptd==null||bptd.isEmpty())||(srmd==null||srmd.isEmpty())){
            Toast.makeText(add_bikes.this,"Please Fill all entries",Toast.LENGTH_SHORT).show();
        }

        if(imbase64==null)
            Toast.makeText(add_bikes.this,"Please Upload Image",Toast.LENGTH_SHORT).show();

        if(!(bn==null||bn.isEmpty())&&!(bpn==null||bpn.isEmpty())&&!(rph==null||rph.isEmpty())&&!(rpd==null||rpd.isEmpty())&&!(id==null||id.isEmpty())
                &&!(sd==null||sd.isEmpty())&&!(bptd==null||bptd.isEmpty())&&!(srmd==null||srmd.isEmpty())&&!(imbase64==null||imbase64.isEmpty())){
           // Toast.makeText(add_bikes.this,"All set",Toast.LENGTH_SHORT).show();
            return true;
        }
        else
            return false;
    }


    private void pickImageFromGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
             imageUri = data.getData();
            String[] filePath = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(imageUri, filePath, null, null, null);
            cursor.moveToFirst();
            String imagePath = cursor.getString(cursor.getColumnIndex(filePath[0]));
            bike_image.setImageBitmap(BitmapFactory.decodeFile(imagePath));
            try {
                     bitmap_s = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(bitmap_s!=null){
                new encode_image_to_base64().execute();
            }
            else
                Toast.makeText(add_bikes.this,"Can't Upload Image",Toast.LENGTH_SHORT).show();
            cursor.close();

        }
    }

    private class encode_image_to_base64 extends AsyncTask<Bitmap,Void,Void>{
        private ProgressDialog pdia;

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            pdia = new ProgressDialog(add_bikes.this);
            pdia.setMessage("Uploading Image");
            pdia.setCancelable(false);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    pdia.show();
                }
            }, 100);

        }
        @Override
        protected Void doInBackground(Bitmap... bitmaps) {
              if(bitmap_s!=null){
                  ByteArrayOutputStream stream = new ByteArrayOutputStream();
                  bitmap_s.compress(Bitmap.CompressFormat.PNG, 90, stream);
                  byte [] byte_arr = stream.toByteArray();
                  image_base64_content = Base64.encodeToString(byte_arr, Base64.DEFAULT);
              }
              else
                  Toast.makeText(add_bikes.this,"Image Error",Toast.LENGTH_SHORT).show();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
                     Toast.makeText(add_bikes.this,"Image Uploaded",Toast.LENGTH_SHORT).show();
            pdia.dismiss();
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_out, R.anim.slide_in);

    }


    @Override
    public void onDateSet(com.wdullaer.materialdatetimepicker.date.DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
      String  date = ""+dayOfMonth+"/"+(monthOfYear+1)+"/"+year;

        if(flag==0){
            in_date=date;
            select_insurance_renewal_date.setText(date);
        }else if(flag==1){
            se_date=date;
            select_service_date_field.setText(date);
        }
    }
}
