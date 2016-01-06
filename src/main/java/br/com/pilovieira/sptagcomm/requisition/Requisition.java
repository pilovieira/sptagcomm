package br.com.pilovieira.sptagcomm.requisition;

import br.com.pilovieira.sptagcomm.Callback;
import br.com.pilovieira.sptagcomm.Request;
import android.content.Context;

public abstract class Requisition<REQUEST extends Request> {

	protected Context context;
	protected REQUEST request;
	protected Callback callback;

	protected void initialize(Context context, String tagMessage) {
		this.context = context;
	}

	Callback getCallback() {
		return callback;
	}

	protected abstract void process();
}
