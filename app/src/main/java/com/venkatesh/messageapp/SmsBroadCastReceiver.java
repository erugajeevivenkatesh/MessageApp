package com.venkatesh.messageapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class SmsBroadCastReceiver extends BroadcastReceiver {
    public static final String SMS_BUNDLE = "pdus";
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle intentExtras = intent.getExtras();
            if(intentExtras!=null)
            {
                Object[] sms=(Object[]) intentExtras.get(SMS_BUNDLE);
                String smsMessagestr="";
                String Body="";
                String Address="";

                for (int i = 0; i < sms.length; ++i) {
                    String format = intentExtras.getString("format");
                    SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) sms[i], format);
                     Body = smsMessage.getMessageBody().toString();
                     Address = smsMessage.getOriginatingAddress();

                }
                MainActivity inst = MainActivity.instance();
                inst.updateInbox(Body,Address);

            }
    }
}
