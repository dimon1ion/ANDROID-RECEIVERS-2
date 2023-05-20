package com.my.mw_receivers2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getExtras() != null) {
            String msg = intent.getStringExtra("message");
            EditText editText = MainActivity.editText;
            if (!msg.equals(editText.getText().toString())) {
                editText.setText(msg);
            }
        }
    }
}