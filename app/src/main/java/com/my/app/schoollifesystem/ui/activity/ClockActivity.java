package com.my.app.schoollifesystem.ui.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import com.my.app.schoollifesystem.R;

/**
 * Created by yf on 18-5-29.
 */

public class ClockActivity extends Activity {

    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        String s = i.getStringExtra("clock");
        if(i!=null && "yes".equals(s)){
            final AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setTitle(R.string.time_up).setMessage(R.string.time_table_ok).setNegativeButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface anInterface, int i) {

                        }
                    }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface anInterface, int i) {
                            startActivity(new Intent(ClockActivity.this,MainActivity.class));
                            ClockActivity.this.finish();
                            dialog.dismiss();
                        }
                    }).setCancelable(false);
            dialog = builder.show();

        }
    }
}
