package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class ActivityTwo extends AppCompatActivity {

    EditText NameInput;
    String Name = "";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

        NameInput = findViewById(R.id.Name);
        // using the event handler, I can get the name only when either done or enter is pressed
        NameInput.setOnEditorActionListener((textView, actionId, keyEvent)-> {
            Name = NameInput.getText().toString().trim();
            // using the trim function, I can remove the leading and following spaces
            if (!Name.contains(" ")) { // if everything worked out well then there should be atleast
                                       // one space in the name which can used to figure out weather
                                       // the input was correct or wrong
                setResult(Activity.RESULT_CANCELED, null); // incase its not correct pass null and canceled
            } else {
                Intent Finish = new Intent();
                Finish.putExtra("Name", Name); // incase the input is right create an intent
                // with the name an dpass it to set result.
                setResult(Activity.RESULT_OK, Finish);
            }
            finish();
            return true;
        });
    }
}
