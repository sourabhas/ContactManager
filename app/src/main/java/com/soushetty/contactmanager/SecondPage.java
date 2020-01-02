package com.soushetty.contactmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;



/*Class to receive data from the Main Activity and implement the second activity*/
public class SecondPage extends AppCompatActivity {

    private TextView contactname,contactnumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page);
        contactname=findViewById(R.id.second_name);
        contactnumber=findViewById(R.id.second_phone_number);

        //receiving data from the first/main activity
        Bundle bundle=getIntent().getExtras();
        if ((bundle!=null)){
            contactname.setText(bundle.getString("name"));
            contactnumber.setText(bundle.getString("phone_number"));
        }

    }
}
