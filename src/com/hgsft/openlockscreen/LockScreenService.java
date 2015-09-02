package com.hgsft.openlockscreen;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
//import android.support.v4.content.LocalBroadcastManager;
//import android.util.Log;
//import android.app.KeyguardManager;

public class LockScreenService extends Service {

    public static final String EXTRA_CLOSE_LOCK_SCREEN = "com.lockscreen.close_lock_screen";
    public static final String EXTRA_CLOSE_LOCK_SCREEN_SWIPE_UP = "com.lockscreen.close_lock_screen_swipe_top";
    public static final String EXTRA_HIDE_LOCK_SCREEN = "com.lockscreen.hide_lock_screen";
    public static final String EXTRA_INCOMING_SMS = "com.lockscreen.incoming_sms";
    public static final String EXTRA_MISSED_CALL = "com.lockscreen.missed_call";
    public static final String EXTRA_SCREEN_OFF = "com.lockscreen.screen_off";
    public static final String EXTRA_SHOW_LOCK_SCREEN = "com.lockscreen.show_lock_screen";
	
    private LockScreenReceiver mReceiver = null;

    
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	public static void start(Context ctx) {
		ctx.startService(new Intent(ctx, LockScreenService.class));
		
	}
	
	/*android.app.KeyguardManager.KeyguardLock mKeyGuardLock;
	
	public void killDefaultLockService() {
		if (mKeyGuardLock == null) {
			mKeyGuardLock = ((KeyguardManager)getSystemService("keyguard")).newKeyguardLock("iCard LockScreen");
		}
		mKeyGuardLock.disableKeyguard();
	}*/
	
	@Override
	public void onCreate() {
		 
		super.onCreate();

		//killDefaultLockService();
		
		IntentFilter intentfilter = new IntentFilter(Intent.ACTION_SCREEN_ON);
		intentfilter.addAction(Intent.ACTION_SCREEN_OFF);
		this.mReceiver = new LockScreenReceiver();
		this.registerReceiver(this.mReceiver, intentfilter);
	    
		/*intentfilter = new IntentFilter("com.lockscreen.registered_first");
		intentfilter.addAction("com.lockscreen.registered");
		intentfilter.addAction("com.lockscreen.cards");
		intentfilter.addAction("com.lockscreen.image_loaded");
		intentfilter.addAction("com.lockscreen.stats");
		intentfilter.addAction("com.lockscreen.global_stats");
		LocalBroadcastManager.getInstance(this).registerReceiver(mServerBroadcastReceiver, intentfilter);*/
		
	}
	
	@Override
	public void onDestroy() {
		
		this.unregisterReceiver(this.mReceiver);
	    /*LocalBroadcastManager.getInstance(this).unregisterReceiver(mServerBroadcastReceiver);*/
	    super.onDestroy();
	}
	
	 public static void showLockScreen(Context context) {
		 showLockScreen(context, false);
	 }

	 public static void showLockScreen(Context context, boolean flag) {
		 
		 //LockScreenPrefsHelper.saveLockScreenShown(true, context);

		 if (!SettingsManager.isCalling(context) && SettingsManager.getState() == true && flag == false) {

			 /*Intent intent = new Intent(context, LockScreenService.class);
			 
			 if (flag) {
				 intent.putExtra(LockScreenService.EXTRA_SCREEN_OFF, true);
			 }
			 
			 intent.putExtra(LockScreenService.EXTRA_SHOW_LOCK_SCREEN, true);
			 context.startService(intent);*/
			 
			 Intent intent = new Intent(context, LockScreenActivity.class);
			 intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			 context.startActivity(intent);
			 
		 }
		 
	 }

}
