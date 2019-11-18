package com.example.paraggelies.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.paraggelies.R;

public class contact extends AppCompatActivity {
    EditText editTextTo,editTextSubject,editTextMessage;
    Button buttonSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        editTextTo=findViewById(R.id.editextTo);
        editTextMessage=findViewById(R.id.editTextMesage);
        editTextSubject=findViewById(R.id.EditextSubject);

        buttonSend=findViewById(R.id.buttonSend);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:"+editTextTo.getText().toString()));
                intent.putExtra(Intent.EXTRA_SUBJECT,editTextSubject.getText().toString());
                intent.putExtra(Intent.EXTRA_TEXT,editTextMessage.getText().toString());
                startActivity(intent);

            }
        });
    }
}
