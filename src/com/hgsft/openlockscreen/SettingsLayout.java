package com.hgsft.openlockscreen;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
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
	
	private static final boolean defState = false;
	private static final boolean defPlayerState = false;
	private static final boolean defRadioState = false;
	private static final String defPin = "";
	
	private static final String stateKey = "state";
	private static final String radioStateKey = "radioState";
	private static final String playerStateKey = "playerState";
	private static final String pinKey = "pin";
	
	private static boolean state = SettingsLayout.defState;
	private static boolean playerState = SettingsLayout.defPlayerState;
	private static boolean radioState = SettingsLayout.defRadioState;
	private static String pin = SettingsLayout.defPin;
	
	private static SettingsLayout instance = null;
	
	/*private ToggleButton gState = null;
	private ToggleButton pState = null;
	private ToggleButton rState = null;*/
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.settings_layout);
		this.LoadSettings();
		this.UpdateComponents();
		SettingsLayout.instance = this;
	}
	
	private void LoadSettings() {
		
		SharedPreferences prefs = this.getPreferences(Context.MODE_PRIVATE);
		
		SettingsLayout.state = prefs.getBoolean(SettingsLayout.stateKey, SettingsLayout.defState);		
		SettingsLayout.playerState = prefs.getBoolean(SettingsLayout.playerStateKey, SettingsLayout.defPlayerState);
		SettingsLayout.radioState = prefs.getBoolean(SettingsLayout.radioStateKey, SettingsLayout.defRadioState);
		SettingsLayout.pin = prefs.getString(SettingsLayout.pinKey, SettingsLayout.defPin);

	}
	
	private void UpdateComponents() {
		
		ToggleButton gState = (ToggleButton)this.findViewById(R.id.global_state);
		gState.setChecked(SettingsLayout.state);
		//gState.setActivated(this.state);
		gState.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				SettingsLayout.state = isChecked;				
			}		
		});

		ToggleButton pState = (ToggleButton)this.findViewById(R.id.player_switcher);
		pState.setChecked(SettingsLayout.playerState);
		pState.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				SettingsLayout.playerState = isChecked;				
			}		
		});
		
		ToggleButton rState = (ToggleButton)this.findViewById(R.id.radio_switcher);
		rState.setChecked(SettingsLayout.radioState);
		rState.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				SettingsLayout.radioState = isChecked;				
			}		
		});
		
		Button btnOk = (Button)this.findViewById(R.id.save_btn);		
		btnOk.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (SettingsLayout.instance != null) {
					SettingsLayout.instance.SaveSettings();
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
				SettingsLayout.pin = v.getText().toString();
				return false;
			}
			
		});
		
		pinCode.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				SettingsLayout.pin = ((EditText)v).getText().toString();
				return false;
			}
			
		});
		
		
	}
	
	private void SaveSettings() {
		
		SharedPreferences prefs = this.getPreferences(Context.MODE_PRIVATE);
		Editor editor = prefs.edit();		
		
		editor.putBoolean(SettingsLayout.stateKey, SettingsLayout.state);
		editor.putBoolean(SettingsLayout.playerStateKey, SettingsLayout.playerState);
		editor.putBoolean(SettingsLayout.radioStateKey, SettingsLayout.radioState);
		editor.putString(SettingsLayout.pinKey, SettingsLayout.pin);
		
		editor.commit();
		
	}
	
}
