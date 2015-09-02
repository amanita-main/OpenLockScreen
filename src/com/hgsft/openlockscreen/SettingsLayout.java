package com.hgsft.openlockscreen;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.ToggleButton;

public class SettingsLayout extends Activity {
	
	
	private static SettingsLayout instance = null;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.settings_layout);
		SettingsManager.loadSettings(this);
		this.UpdateComponents();
		SettingsLayout.instance = this;
	}
	

	
	private void UpdateComponents() {
		
		ToggleButton gState = (ToggleButton)this.findViewById(R.id.global_state);
		gState.setChecked(SettingsManager.getState());
		//gState.setActivated(this.state);
		gState.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				SettingsManager.setState(isChecked);				
			}		
		});

		ToggleButton pState = (ToggleButton)this.findViewById(R.id.player_switcher);
		pState.setChecked(SettingsManager.getPlayerState());
		pState.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				SettingsManager.setPlayerState(isChecked);				
			}		
		});
		
		ToggleButton rState = (ToggleButton)this.findViewById(R.id.radio_switcher);
		rState.setChecked(SettingsManager.getRadioState());
		rState.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				SettingsManager.setRadioState(isChecked);				
			}		
		});
		
		Button btnOk = (Button)this.findViewById(R.id.save_btn);		
		btnOk.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (SettingsLayout.instance != null) {
					SettingsManager.saveSettings(SettingsLayout.instance);
					if (SettingsManager.getState() == true) {
						LockScreenService.start(SettingsLayout.instance);
					}
					SettingsLayout.instance.finish(); 
				}
				
			}
		});
		
		Button btnClose = (Button)this.findViewById(R.id.close_settings);		
		btnClose.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//TODO: ask if changes?
				if (SettingsLayout.instance != null) SettingsLayout.instance.finish(); 
			}		
		});
		
		EditText pinCode = (EditText)this.findViewById(R.id.pin);
		pinCode.setOnEditorActionListener(new OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				SettingsManager.setPin(v.getText().toString());
				return false;
			}
			
		});
		
		pinCode.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				SettingsManager.setPin(((EditText)v).getText().toString());
				return false;
			}
			
		});
		
		
	}
	

	
}
