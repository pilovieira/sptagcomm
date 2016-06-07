package br.com.pilovieira.sptagcomm.requisition;

import android.content.Context;
import br.com.pilovieira.sptagcomm.Message;

public abstract class Requisition<T extends Message> {

	protected Context context;
	protected T request;

	void setContext(Context context) {
		this.context = context;
	}

	@SuppressWarnings("unchecked")
	void setMessage(Message message) {
		this.request = (T) message;
	}
	
	protected abstract Message process();

}
