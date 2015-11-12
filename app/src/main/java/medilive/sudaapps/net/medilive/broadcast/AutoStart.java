package medilive.sudaapps.net.medilive.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import medilive.sudaapps.net.medilive.services.AlarmService;

/**
 * Created by Adil on 09/10/15.
 */
public class AutoStart extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED))
        {
            context.startService(new Intent(context, AlarmService.class));
        }
    }
}
