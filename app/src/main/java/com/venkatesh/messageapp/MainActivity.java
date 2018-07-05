package com.venkatesh.messageapp;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Telephony;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

   List<Reciverdata> recivemessages=new ArrayList<>();
    RecyclerView messages;
    EditText input;
    SmsManager smsManager = SmsManager.getDefault();
    private static MainActivity inst;
    public InformationAdapter informationAdapter;
    private static final int READ_SMS_PERMISSIONS_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        messages=findViewById(R.id.Messages);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            RequestPermission();
        } else {
            refreshSmsInbox();
        }
        informationAdapter=new InformationAdapter(recivemessages);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        messages.setLayoutManager(layoutManager);
        messages.setItemAnimator(new DefaultItemAnimator());
        messages.setAdapter(informationAdapter);
    }

    public void RequestPermission()
    {
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.READ_CONTACTS)!=PackageManager.PERMISSION_GRANTED)
        {
            if(shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS))
            {
                Toast.makeText(this, "Please Allow Read cantacts Permissions", Toast.LENGTH_SHORT).show();
            }
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},1);

        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (shouldShowRequestPermissionRationale(
                    Manifest.permission.READ_SMS)) {
                Toast.makeText(this, "Please allow permission!", Toast.LENGTH_SHORT).show();
            }
            requestPermissions(new String[]{Manifest.permission.READ_SMS},
                    READ_SMS_PERMISSIONS_REQUEST);
        }
    }


   public void  updateInbox(String Address,String body)
    {
        Reciverdata reciverdata=new Reciverdata(body,Address,"hi");
        recivemessages.add(reciverdata);
        informationAdapter.notifyDataSetChanged();
    }

    public static MainActivity instance() {
        return inst;
    }
    @Override
    protected void onStart() {
        super.onStart();
        inst=this;
    }

    public void refreshSmsInbox() {
        ContentResolver contentResolver = getContentResolver();
        Cursor smsInboxCursor = contentResolver.query(Uri.parse("content://sms/inbox"),
                null, null, null, null);
        int indexBody = smsInboxCursor.getColumnIndex("body");
        int indexAddress = smsInboxCursor.getColumnIndex("address");
        int indexdateAddress=smsInboxCursor.getColumnIndex("date");
        if (indexBody < 0 || !smsInboxCursor.moveToFirst()) return;
        recivemessages.clear();
        do {
//            String str = "SMS From: " +  +
//                    "\n" +  + "\n";
            Reciverdata reciverdata=new Reciverdata(smsInboxCursor.getString(indexAddress),
                    smsInboxCursor.getString(indexBody),
                    "jodsp");
            //Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
            recivemessages.add(reciverdata);
        } while (smsInboxCursor.moveToNext());
//messages.setSelection(arrayAdapter.getCount() - 1);
    }

    public void Addcontacts(View view) {

    }
}
