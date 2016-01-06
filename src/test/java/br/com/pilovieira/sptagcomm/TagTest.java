package br.com.pilovieira.sptagcomm;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class TagTest {

	@Rule public ExpectedException thrown = ExpectedException.none();
	
	@BeforeClass
	public static void register() {
		@SuppressWarnings("unused")
		Tag temp = Status.INSTANCE;
	}
	
	@Test
	public void getStatus() {
		Tag message = Tag.getInstance("STATUS");
		
		assertEquals(Status.class, message.getClass());
	}

	@Test
	public void invalidMessage() {
		thrown.expect(TagException.class);
		thrown.expectMessage("invalid TAG");
		
		Tag.getInstance("BATATAS");
	}

	@Test
	public void invalidFunction() {
		thrown.expect(TagException.class);
		thrown.expectMessage("invalid TAG");
		
		Tag.getInstance("FUNCTION.BATATAS");
	}
}
