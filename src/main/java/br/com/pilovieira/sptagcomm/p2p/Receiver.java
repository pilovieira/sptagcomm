package br.com.pilovieira.sptagcomm.p2p;

import android.content.Context;

public interface Receiver {
	
	void onReceive(Context context, String sender, String message);

}
