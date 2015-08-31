package com.hgsft.openlockscreen;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class LockScreenActivity extends Activity {


	private static LockScreenActivity instance = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        //REAL ON_TOP!
        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
        		WindowManager.LayoutParams.FLAG_FULLSCREEN,
        		WindowManager.LayoutParams.FLAG_FULLSCREEN,
        		WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
        		WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
        		PixelFormat.TRANSLUCENT);

		final WindowManager wm = (WindowManager) getApplicationContext()
		    .getSystemService(this.WINDOW_SERVICE);

		final ViewGroup mTopView = (ViewGroup) getLayoutInflater().inflate(R.layout.activity_lock_screen, null);
		getWindow().setAttributes(params);
		wm.addView(mTopView, params);
		
		
		Button btn = (Button)mTopView.findViewById(R.id.dummy_button);
		btn.setOnClickListener(new OnClickListener() {
			 public void onClick(View v) {
				 Log.d("TEST", "Click1");
				 mTopView.removeAllViews();
				 wm.removeView(mTopView);
				 if (LockScreenActivity.instance != null) LockScreenActivity.instance.finish();
			 }
		});
		
		LockScreenActivity.instance = this;

		//http://stackoverflow.com/questions/13610258/how-to-detect-when-a-user-plugs-headset-on-android-device-opposite-of-action-a
		//http://stackoverflow.com/questions/18800198/control-the-default-music-player-of-android-or-any-other-music-player
		//http://stackoverflow.com/questions/22601414/how-to-set-a-frequency-for-the-fm-radio-in-android
		//http://stackoverflow.com/questions/16955294/what-are-good-ways-to-implement-fm-radio-application-for-android-devices
        
        //setContentView(R.layout.activity_lock_screen);

        
        /*WindowManager mWindowManager = (WindowManager)this.getSystemService("window");
        View mStatusBarShowerView = new View(this);
        //android.view.WindowManager.LayoutParams layoutparams = new android.view.WindowManager.LayoutParams(-1, 100, 2010, 2088, -2);
        android.view.WindowManager.LayoutParams layoutparams = new android.view.WindowManager.LayoutParams(-1, 100, 2010, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_FULLSCREEN, -2);
        layoutparams.gravity = 49;
        mWindowManager.addView(mStatusBarShowerView, layoutparams);*/
        
        /*if (Build.VERSION.SDK_INT < 16) {
    	   getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_FULLSCREEN);
    	 } else {
    	     View decorView = getWindow().getDecorView();
    	      int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
    	      decorView.setSystemUiVisibility(uiOptions);
    	      ActionBar actionBar = getActionBar();
    	      actionBar.hide();
    	 }*/

    }

   
}
