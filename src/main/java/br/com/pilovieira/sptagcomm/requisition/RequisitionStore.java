package br.com.pilovieira.sptagcomm.requisition;

import java.util.HashMap;
import java.util.Map;

import br.com.pilovieira.sptagcomm.Message;

public class RequisitionStore {
	
	private static final Map<Class<? extends Message>, Class<? extends Requisition<? extends Message>>> requisitions = new HashMap<>(); 
	
	public static void register(Class<? extends Message> message, Class<? extends Requisition<? extends Message>> requisition) {
		requisitions.put(message, requisition);
	}
	
	Requisition<? extends Message> getRequisition(Message message) throws InstantiationException, IllegalAccessException {
		if (!requisitions.containsKey(message.getClass()))
			throw new RuntimeException("Requisition not found");
		
		Requisition<? extends Message> instance = requisitions.get(message.getClass()).newInstance();
		return instance;
	}
}