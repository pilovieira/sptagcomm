package br.com.pilovieira.sptagcomm.p2p.sms;

import android.telephony.SmsManager;
import br.com.pilovieira.sptagcomm.Message;
import br.com.pilovieira.sptagcomm.p2p.Emitter;

public class SMSEmitter implements Emitter {
	
	private String destination;

	public SMSEmitter(String destination) {
		this.destination = destination;
	}
	
	@Override
	public void emit(Message message) {
		SmsManager.getDefault().sendTextMessage(getDestination(), null, message.getValue(), null, null);
	}

	@Override
	public String getDestination() {
		return destination;
	}
}
