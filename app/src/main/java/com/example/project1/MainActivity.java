package com.example.project1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String Name = "";
    int NameCheck = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // setting the orientation of the device accordigly with the .xml file
        super.onCreate(savedInstanceState);
        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.activity_main);
        } else {
            setContentView(R.layout.activity_main_l);
        }
    }

    // first button, when clicked creates an intent and starts activity two with Namecheck
    // which will determine weather the name is in correct format.
    public void ButtonOneOnClick(View v) {
        NameCheck = 0;
        Intent ActivityTwo = new Intent(MainActivity.this, ActivityTwo.class);
        startActivityForResult(ActivityTwo,NameCheck);
    }

    // button two, when clicked, if the user input is wrong will Toast the messege with error,
    // and name. If its correctly formatted then it will go to Contact with the given name.
    public void ButtonTwoOnClick(View v) {
        if (NameCheck == -1) {
            Toast.makeText(getApplicationContext(), "Incorrect Name or Missing First/Last Name: " + Name, Toast.LENGTH_LONG).show();
        } else if (NameCheck == 1) {
            Intent Contact = new Intent(ContactsContract.Intents.Insert.ACTION);
            Contact.setType(ContactsContract.RawContacts.CONTENT_TYPE);
            Contact.putExtra(ContactsContract.Intents.Insert.NAME, Name);
            startActivity(Contact);
        }
    }

    // When the activity returns the results they will be stored in the Namecheck, and Name.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Name = data.getStringExtra("Name");
            NameCheck = 1;
        } else {
            NameCheck = -1;
        }
    }
}