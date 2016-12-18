package com.acadgild.com.session3_assignment1;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.opengl.EGLExt;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnSearch, btnClear;
    private EditText txtSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtSearch = (EditText) findViewById(R.id.editTextSearch);
        btnSearch = (Button)findViewById(R.id.btnSearch);
        btnClear = (Button)findViewById(R.id.btnClear);
        btnSearch.setOnClickListener(this);
        btnClear.setOnClickListener(this);
     //   btnSearch.setOnClickListener(new View.OnClickListener() {

    }

   @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSearch:
                String itemSearch = txtSearch.getText().toString();
                boolean network_status = haveNetworkConnection();
                if(network_status) {
                    if (itemSearch.length() > 0) {
                        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                        intent.putExtra(SearchManager.QUERY, itemSearch);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Please enter some text!", Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please check your Network Connection",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btnClear:
                txtSearch.setText("");
                break;

            default:
                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
                break;

        }
    }

    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            else
                    haveConnectedWifi = false;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
            else
                    haveConnectedMobile = false;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

}
