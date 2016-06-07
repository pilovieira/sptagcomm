package br.com.pilovieira.sptagcomm.requisition;

import java.util.List;

import android.content.Context;
import br.com.pilovieira.sptagcomm.Message;
import br.com.pilovieira.sptagcomm.MessageImpl;
import br.com.pilovieira.sptagcomm.p2p.Emitter;

public class RequisitionProcessor {
	
	private RequisitionStore store;
	private List<Emitter> emitters;
	
	public RequisitionProcessor(List<Emitter> emitters) {
		this(new RequisitionStore(), emitters);
	}
	
	RequisitionProcessor(RequisitionStore store, List<Emitter> emitters) {
		this.store = store;
		this.emitters = emitters;
	}
	
	public void process(Context context, Message message) {
		try {
			Requisition<? extends Message> requisition = store.getRequisition(message);
			requisition.setContext(context);
			requisition.setMessage(message);
			
			Message callback = requisition.process();
			
			if (callback != null)
				emit(callback);
		} catch (Exception ex) {
			MessageImpl callback = new MessageImpl(ex.getMessage());
			emit(callback);
			ex.printStackTrace();
		}
	}

	private void emit(Message message) {
		for (Emitter emitter : emitters)
			emitter.emit(message);
	}
}
