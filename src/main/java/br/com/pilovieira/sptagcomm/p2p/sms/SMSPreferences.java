package br.com.pilovieira.sptagcomm.p2p.sms;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SMSPreferences {
	
	public static final String KEY_ABORT_BROADCAST = "ABORT_BROADCAST";
	
	protected Context context;

	public SMSPreferences(Context context) {
		this.context = context;
	}
	
	public SharedPreferences getSharedPreferences() {
		return PreferenceManager.getDefaultSharedPreferences(context);
	}
	
	public boolean isAbortBroadcast() {
		return getSharedPreferences().getBoolean(KEY_ABORT_BROADCAST, false);
	}
	
}
