package br.com.pilovieira.sptagcomm.p2p.sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;
import br.com.pilovieira.sptagcomm.p2p.Receiver;

public abstract class SMSReceiver extends BroadcastReceiver implements Receiver {

	private SMSPreferences preferences;

	@Override
	public void onReceive(Context context, Intent intent) {
		try {
			this.preferences = new SMSPreferences(context);
			
			Bundle extras = intent.getExtras();
			if (extras == null)
				return;
			
			SmsMessage sms = SmsMessage.createFromPdu((byte[]) ((Object[]) extras.get("pdus"))[0]);
			
			onReceive(context, sms.getOriginatingAddress(), sms.getMessageBody());
			
			if (isAbortBroadcast())
				abortBroadcast();
		} catch (Exception e) {
			Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG);
		}
	}

	private boolean isAbortBroadcast() {
		return preferences.isAbortBroadcast();
	}
}
