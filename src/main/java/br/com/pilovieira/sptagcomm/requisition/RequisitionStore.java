package br.com.pilovieira.sptagcomm.requisition;

import java.util.HashMap;
import java.util.Map;

import br.com.pilovieira.sptagcomm.Request;
import br.com.pilovieira.sptagcomm.Tag;

public class RequisitionStore {
	
	private static Map<Tag, Requisition<? extends Request>> 
	requisitions = new HashMap<Tag, Requisition<? extends Request>>(); 
	
	public static void register(Tag tag, Requisition<? extends Request> requisition) {
		requisitions.put(tag, requisition);
	}
	
	Requisition<? extends Request> getRequisition(Tag tag) {
		if (requisitions.containsKey(tag))
			return requisitions.get(tag);
		
		throw new RequisitionException("Requisition not found");
	}
}