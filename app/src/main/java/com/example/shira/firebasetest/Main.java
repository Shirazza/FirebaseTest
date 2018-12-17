package com.example.shira.firebasetest;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Log;

public class  Main extends AppCompatActivity {
    private static final String TAG= Main.class.getSimpleName();

    EditText thephoneNum, theCode, thenewP;
    String phoneNum, code, codeEntered, newP, P;
    AlertDialog.Builder adb;
    Intent goStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        thephoneNum= (EditText) findViewById(R.id.thephoneNum);
        theCode= (EditText) findViewById(R.id.theCode);
        thenewP= (EditText) findViewById(R.id.thenewP);

    }

    public void sendM(View view) {
        try {
            phoneNum=thephoneNum.getText().toString();
            code=""+((int)(Math.random()*900)+100);
            SmsManager smsManager=SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNum, null, code, null, null);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "the error is in the sending");
        }
    }

    public void checkC(View view) {
        try {
            codeEntered=theCode.getText().toString();
            if (codeEntered.equals(code))
                Toast.makeText(this, "the code you entered is correct", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(this, "the code you entered is not correct", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "the error is in the code checking");
        }
    }

    public void backtoStart(View view) {
        try {
            newP=thenewP.getText().toString();
            adb= new AlertDialog.Builder(this);
            adb.setTitle("Are you sure you want to continue?");
            adb.setMessage("Are you sure this is the new password you want: "+newP+ " ?");
            adb.setIcon(R.drawable.qmark);
            adb.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    goStart=new Intent(Main.this, openingActivity.class);
                    goStart.putExtra("new password", newP);
                    startActivityForResult(goStart, 1);
                }
            });
            adb.setNegativeButton("no", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();;
                }
            });
            AlertDialog ad=adb.create();
            ad.show();
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "the error is in the next");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data!=null){
            P=data.getStringExtra("back password");
        }
    }

    public void back(View view) {
        try {
            Intent g2= new Intent(this, openingActivity.class);
            startActivity(g2);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "the error is in the back");
        }
    }
}
