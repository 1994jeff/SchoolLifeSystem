package com.my.app.schoollifesystem.util;

import android.app.AlertDialog;
import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.PowerManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.my.app.schoollifesystem.R;
import com.my.app.schoollifesystem.ui.activity.ClockActivity;
import com.my.app.schoollifesystem.ui.activity.MainActivity;

public class MyReceiver extends BroadcastReceiver {

    final String ACTION = "com.string.alarm.start";

    @Override
    public void onReceive(Context context, Intent intent) {
        if(ACTION.equals(intent.getAction())){

            Log.i("jeff","receiver colck");

            Intent i = new Intent(context, ClockActivity.class);
            i.putExtra("clock","yes");
            context.startActivity(i);

            PowerManager mPowerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            PowerManager.WakeLock mWakeLock = mPowerManager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "tag");
            mWakeLock.acquire(10000);

            KeyguardManager km= (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
            KeyguardManager.KeyguardLock kl = km.newKeyguardLock("unLock");
            //解锁
            kl.disableKeyguard();

        }
    }
}
