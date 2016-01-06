package br.com.pilovieira.sptagcomm.p2p;

import br.com.pilovieira.sptagcomm.Message;

public interface Emitter {

	String getDestination();
	
	void emit(Message message);

}
