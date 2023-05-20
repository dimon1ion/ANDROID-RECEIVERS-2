package com.my.mw_receivers2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    static final String MESSAGE_ACTION = "com.my.example.MESSAGE_ONE";

    public static EditText editText;

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Intent messageIntent = new Intent();
            messageIntent.setAction(MESSAGE_ACTION);
            messageIntent.putExtra("message", editText.getText().toString());
            sendBroadcast(messageIntent);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.edit_text);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            MyReceiver receiver = new MyReceiver();
            IntentFilter intentFilter = new IntentFilter("com.my.example.MESSAGE_TWO");
            registerReceiver(receiver, intentFilter);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        editText.addTextChangedListener(textWatcher);
    }

    @Override
    protected void onPause() {
        super.onPause();
        editText.removeTextChangedListener(textWatcher);
    }
}