package com.hgsft.openlockscreen;

import com.android.utils.ResourcesHelper;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class LockScreenActivity extends Activity {

	private static final String btnNames = "unlock_btn_";

	private static LockScreenActivity instance = null;
	
	private String pin = "";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SettingsManager.loadSettings(this);
        
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
		    .getSystemService(Context.WINDOW_SERVICE);

		final ViewGroup mTopView = (ViewGroup) this.getLayoutInflater().inflate(R.layout.activity_lock_screen, null);
		
		getWindow().setAttributes(params);
		wm.addView(mTopView, params);
		
		
		ImageButton btnOk = (ImageButton)mTopView.findViewById(R.id.unlock_btn_ok);
		btnOk.setOnClickListener(new OnClickListener() {
			 public void onClick(View v) {
				 
				 if (LockScreenActivity.instance.pin.equals(SettingsManager.getPin()) == false) return;
						 
				 mTopView.removeAllViews();
				 wm.removeView(mTopView);
				 if (LockScreenActivity.instance != null) LockScreenActivity.instance.finish();
				 
			 }
		});
		
		ImageButton btnClear = (ImageButton)mTopView.findViewById(R.id.unlock_btn_clear);
		btnClear.setOnClickListener(new OnClickListener() {
			 public void onClick(View v) {
				 
				 LockScreenActivity.instance.pin = "";
				
			 }
		});
		
		this.setButtons(mTopView);
		
		LockScreenActivity.instance = this;

		//http://stackoverflow.com/questions/13610258/how-to-detect-when-a-user-plugs-headset-on-android-device-opposite-of-action-a
		//http://stackoverflow.com/questions/18800198/control-the-default-music-player-of-android-or-any-other-music-player
		//http://stackoverflow.com/questions/22601414/how-to-set-a-frequency-for-the-fm-radio-in-android
		//http://stackoverflow.com/questions/16955294/what-are-good-ways-to-implement-fm-radio-application-for-android-devices
        
    }
    
    private void setButtons(ViewGroup parent) {
    	
    	for (int i = 0; i < 10; ++i) {
    		int id = ResourcesHelper.getResourceId(this, "id", LockScreenActivity.btnNames + i);
    		ImageButton btn = (ImageButton)parent.findViewById(id);
    		this.setBtnOnClickListener(btn, i);
    	}
    	
    }
    
    private void setBtnOnClickListener(ImageButton btn, final int buttonId) {
		
    	btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				if (LockScreenActivity.instance != null) LockScreenActivity.instance.pin += String.valueOf(buttonId);
			}
			
		});
    	
    }
    
   
}
