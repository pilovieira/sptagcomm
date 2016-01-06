package br.com.pilovieira.sptagcomm;

import java.lang.reflect.ParameterizedType;

public abstract class CallbackImpl<TAG extends Tag> implements Callback {

	Class<Tag> tagClass;

	@SuppressWarnings("unchecked")
	protected CallbackImpl() {
		this.tagClass = (Class<Tag>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		try {
			tagClass.getFields()[0].get(tagClass);
		} catch (Exception e) {}
	}
	
	@Override
	public String getValue() {
		return String.format("%s %s", getTag(), buildResultMessage());
	}
	
	private Tag getTag() {
		return Tag.getInstance(tagClass);
	}

	protected abstract String buildResultMessage();
}
