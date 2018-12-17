package com.example.shira.firebasetest;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Edit extends AppCompatActivity {
    EditText nameText, TNText;
    String id;
    DatabaseReference Ref;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit);

        nameText=(EditText) findViewById(R.id.nameText);
        TNText=(EditText) findViewById(R.id.TNText);
        Intent gt= getIntent();
        nameText.setText(""+gt.getStringExtra("name"));
        TNText.setText(""+gt.getStringExtra("team number"));
        id= gt.getStringExtra("id");

        Ref= FirebaseDatabase.getInstance().getReference("team");
    }


    public void save(View view) {
        finish();
    }

    public void cancel(View view) {
        teams Team= new teams(id, Integer.parseInt(TNText.getText().toString()), nameText.getText().toString());
        Ref.child(id).setValue(Team);
        finish();
    }
}

