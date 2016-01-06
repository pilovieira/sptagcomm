package br.com.pilovieira.sptagcomm.requisition;

import java.util.List;

import android.content.Context;
import br.com.pilovieira.sptagcomm.Callback;
import br.com.pilovieira.sptagcomm.Message;
import br.com.pilovieira.sptagcomm.Request;
import br.com.pilovieira.sptagcomm.Tag;
import br.com.pilovieira.sptagcomm.TagException;
import br.com.pilovieira.sptagcomm.p2p.Emitter;

public class RequisitionProcessor {
	
	private RequisitionStore store;
	private List<Emitter> emitters;
	
	public RequisitionProcessor(List<Emitter> emitters) {
		this(new RequisitionStore(), emitters);
	}
	
	public RequisitionProcessor(RequisitionStore store, List<Emitter> emitters) {
		this.store = store;
		this.emitters = emitters;
	}
	
	public void process(Context context, String tagMessage) {
		try {
			Tag tag = Tag.getInstance(tagMessage);
			Requisition<? extends Request> requisition = store.getRequisition(tag);
			
			requisition.initialize(context, tagMessage);
			requisition.process();
			Callback callback = requisition.getCallback();
			
			if (callback != null)
				emit(callback);
		} catch (TagException | RequisitionException ex) {
			emit(new ExceptionMessage(ex));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void emit(Message message) {
		for (Emitter emitter : emitters)
			emitter.emit(message);
	}
}
