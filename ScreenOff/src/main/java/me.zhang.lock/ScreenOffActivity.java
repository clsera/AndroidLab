package me.zhang.lock;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import me.zhang.lock.util.DelayUtils;

/**
 * Main application activity
 */
public class ScreenOffActivity extends Activity {

    static final String LOG_TAG = "ScreenOffActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        turnScreenOffAndExit();
    }

    private void turnScreenOffAndExit() {
        // first lock screen
        turnScreenOff(getApplicationContext());
        // then provide feedback
        // ((Vibrator) getSystemService(Context.VIBRATOR_SERVICE)).vibrate(100);

        finish();
    }

    /**
     * Turns the screen off and locks the device, provided that proper rights
     * are given.
     *
     * @param context - The application context
     */
    static void turnScreenOff(final Context context) {
        final DevicePolicyManager policyManager = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
        ComponentName adminReceiver = new ComponentName(context, ScreenOffAdminReceiver.class);
        boolean admin = policyManager.isAdminActive(adminReceiver);
        if (admin) {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    policyManager.lockNow();
                    Log.i(LOG_TAG, "Going to sleep now.");
                }
            }, DelayUtils.getDelayValue(context));
        } else {
            Toast.makeText(context, R.string.device_admin_not_enabled, Toast.LENGTH_LONG).show();
            Log.i(LOG_TAG, "Not an admin.");
        }
    }

}