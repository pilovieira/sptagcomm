package br.com.pilovieira.sptagcomm;

import java.util.ArrayList;
import java.util.List;

public class Tag {
	
	private static List<Tag> tags = new ArrayList<Tag>();
	
	private static void register(Tag tag) {
		tags.add(tag);
	}
	
	public static Tag getInstance(String tagMessage) {
		for (Tag tag : tags)
			if (tagMessage.contains(tag.tag()))
				return tag;
		
		throw new TagException("invalid TAG");
	}

	public static Tag getInstance(Class<Tag> clazz) {
		for (Tag tag : tags)
			if (clazz == tag.getClass())
				return tag;
		
		throw new TagException("invalid TAG class");
	}
	
	protected Tag() {
		register(this);
	}

	@Override
	public String toString() {
		return tag();
	}
	
	public String tag() {
		return getTag(getClass());
	}
	
	private String getTag(Class<?> clazz) {
		if (clazz == Tag.class)
			return "";
		
		return getTag(clazz.getSuperclass()) + formattedToken(clazz);
	}
	
	private String formattedToken(Class<?> clazz) {
		String token = "";
		if (clazz.getSuperclass() != Tag.class)
			token = ".";
		return token + clazz.getSimpleName().toUpperCase();
	}
}
