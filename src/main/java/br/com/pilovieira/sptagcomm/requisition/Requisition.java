package br.com.pilovieira.sptagcomm.requisition;

import android.content.Context;
import br.com.pilovieira.sptagcomm.Message;

public abstract class Requisition<T extends Message> {

	protected Context context;
	protected T request;
	protected Message callback;

	void setContext(Context context) {
		this.context = context;
	}

	@SuppressWarnings("unchecked")
	void setMessage(Message message) {
		this.request = (T) message;
	}
	
	protected void setCallback(T callback) {
		this.callback = callback;
	}

	Message getCallback() {
		return callback;
	}

	protected abstract void process();

}
