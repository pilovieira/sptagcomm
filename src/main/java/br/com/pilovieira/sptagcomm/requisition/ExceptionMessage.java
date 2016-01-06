package br.com.pilovieira.sptagcomm.requisition;

import br.com.pilovieira.sptagcomm.CallbackImpl;
import br.com.pilovieira.sptagcomm.GeneralError;

public class ExceptionMessage extends CallbackImpl<GeneralError> {

	private RuntimeException ex;

	public ExceptionMessage(String tagMessage) {
		ex = new RuntimeException(tagMessage.replace(GeneralError.INSTANCE.tag(), ""));
	}

	public ExceptionMessage(RuntimeException ex) {
		this.ex = ex;
	}

	@Override
	protected String buildResultMessage() {
		return ex.getMessage();
	}
	
	public String getMessage() {
		return ex.getMessage();
	}
}
