package com.example.yogeshbhosage.mysmsreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class SmsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bndl=intent.getExtras();
        if(bndl!=null){
            Object[] sms=(Object[])bndl.get("pdus");
            String smsmsg="";
            SmsMessage smsMessage=SmsMessage.createFromPdu((byte[])sms[0]);
            smsmsg=smsMessage.getMessageBody();
            String phno=smsMessage.getOriginatingAddress();

            FileOutputStream fout;
            try {
                fout=context.openFileOutput("msg",Context.MODE_PRIVATE);
                OutputStreamWriter myout=new OutputStreamWriter(fout);
                fout.write(smsmsg.getBytes());
                Toast.makeText(context,"msg saved",Toast.LENGTH_LONG).show();
                myout.append("\n")
;                //fout.write("\n".getBytes());
                fout.write(phno.getBytes());
                //myout.append("\n");
                //fout.write("\n".getBytes());
                Toast.makeText(context,"phno saved",Toast.LENGTH_LONG).show();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
}
