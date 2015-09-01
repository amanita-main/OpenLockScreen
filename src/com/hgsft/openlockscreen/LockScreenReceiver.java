package com.hgsft.openlockscreen;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class LockScreenReceiver extends BroadcastReceiver {
	
	@Override
	public void onReceive(Context context, Intent intent) {

		if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
			LockScreenService.showLockScreen(context, true);
		} 
		
		if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
			LockScreenService.showLockScreen(context);
		}
		
	}

}
