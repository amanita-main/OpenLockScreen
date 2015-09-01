package com.hgsft.openlockscreen;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SettingsManager {

	private static final boolean defState = false;
	private static final boolean defPlayerState = false;
	private static final boolean defRadioState = false;
	private static final String defPin = "";
	
	private static final String stateKey = "state";
	private static final String radioStateKey = "radioState";
	private static final String playerStateKey = "playerState"	;
	private static final String pinKey = "pin";
	private static final String configName = "configName";
	
	private static boolean state = SettingsManager.defState;
	private static boolean playerState = SettingsManager.defPlayerState;
	private static boolean radioState = SettingsManager.defRadioState;
	private static String pin = SettingsManager.defPin;
	
	public static boolean getState() {
		return SettingsManager.state;
	}
	
	public static void setState(boolean value) {
		SettingsManager.state = value;
	}
	
	public static boolean getPlayerState() {
		return SettingsManager.playerState;
	}
	
	public static void setPlayerState(boolean value) {
		SettingsManager.playerState = value;
	}
	
	public static boolean getRadioState() {
		return SettingsManager.radioState;
	}
	
	public static void setRadioState(boolean value) {
		SettingsManager.radioState = value;
	}
	
	public static String getPin() {
		return SettingsManager.pin;
	}
	
	public static void setPin(String value) {
		SettingsManager.pin = value;
	}
	
	public static void loadSettings(Context ctx) {
		
		SharedPreferences prefs = ctx.getSharedPreferences(SettingsManager.configName, Context.MODE_PRIVATE);
		
		SettingsManager.state = prefs.getBoolean(SettingsManager.stateKey, SettingsManager.defState);		
		SettingsManager.playerState = prefs.getBoolean(SettingsManager.playerStateKey, SettingsManager.defPlayerState);
		SettingsManager.radioState = prefs.getBoolean(SettingsManager.radioStateKey, SettingsManager.defRadioState);
		SettingsManager.pin = prefs.getString(SettingsManager.pinKey, SettingsManager.defPin);

	}
	
	public static void saveSettings(Context ctx) {
		
		SharedPreferences prefs = ctx.getSharedPreferences(SettingsManager.configName, Context.MODE_PRIVATE);
		Editor editor = prefs.edit();		
		
		editor.putBoolean(SettingsManager.stateKey, SettingsManager.state);
		editor.putBoolean(SettingsManager.playerStateKey, SettingsManager.playerState);
		editor.putBoolean(SettingsManager.radioStateKey, SettingsManager.radioState);
		editor.putString(SettingsManager.pinKey, SettingsManager.pin);
		
		editor.commit();
		
	}
	
	
	private static boolean isCalling = false; 
	public static boolean isCalling(Context ctx) {
		return SettingsManager.isCalling;
	}
	
	public static void setCalling(boolean value, Context ctx) {
		 SettingsManager.isCalling = value;
	}
	
}
