package com.example.ritik.instabike;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class LogIn extends AppCompatActivity {

    EditText et1,et2;
    Button login;
    ProgressDialog pg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        et1 = (EditText) findViewById(R.id.editText3);
        et2 = (EditText) findViewById(R.id.editText4);
        login = (Button) findViewById(R.id.button3);

        pg = new ProgressDialog(LogIn.this);
        pg.setTitle("Loading");
        pg.setMessage("Please Wait..!!");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s1 = et1.getText().toString();
                String s2 = et2.getText().toString();
                if(s1.equals("") ||s2.equals(""))
                {
                    Toast.makeText(LogIn.this, "fields cannot be left blank", Toast.LENGTH_SHORT).show();
                    return;
                }

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
                    Toast.makeText(LogIn.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
    public void viewdata()
    {
        final String usnm = et1.getText().toString();
        final String pswdd = et2.getText().toString();

        class abc extends AsyncTask<Void,Void,String>
        {

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(com.example.ritik.instabike.Config.KEY_EMP_USNAME,usnm);
                params.put(com.example.ritik.instabike.Config.KEY_EMP_PASS,pswdd);

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
                if(s.equalsIgnoreCase("success"))
                {
                    Toast.makeText(LogIn.this, "Login Success", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LogIn.this,MainPage.class);
                    i.putExtra("User Name",usnm);

                    startActivity(i);
                }
                else
                {
                    Toast.makeText(LogIn.this, "Login failed", Toast.LENGTH_SHORT).show();

                }

                super.onPostExecute(s);
            }
        }
        abc  ae = new abc();
        ae.execute();


    }

}
