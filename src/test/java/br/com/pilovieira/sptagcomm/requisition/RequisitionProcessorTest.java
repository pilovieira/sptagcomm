package br.com.pilovieira.sptagcomm.requisition;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import android.content.Context;
import br.com.pilovieira.sptagcomm.Message;
import br.com.pilovieira.sptagcomm.MessageImpl;
import br.com.pilovieira.sptagcomm.p2p.Emitter;

@RunWith(MockitoJUnitRunner.class)
public class RequisitionProcessorTest {
	
	private static final String FAIL_MESSAGE = "You Fail";
	
	@Mock private Message message;
	@Mock private Emitter emitter;
	@SuppressWarnings("rawtypes")
	@Mock private Requisition requisition;
	@Mock private Message callback;
	@Mock private RequisitionStore store;
	@Mock private Context context;

	@Rule public ExpectedException thrown = ExpectedException.none();
	
	private RequisitionProcessor subject;

	
	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws InstantiationException, IllegalAccessException {
		when(store.getRequisition(message)).thenReturn(requisition);
		when(requisition.process()).thenReturn(callback);
		
		subject = new RequisitionProcessor(store, asList(emitter));
	}

	@Test
	public void processRequisitionWithCallback() throws InstantiationException, IllegalAccessException {
		subject.process(context, message);
		
		verify(store).getRequisition(message);
		verify(requisition).setContext(context);
		verify(requisition).setMessage(message);
		verify(requisition).process();
		verify(emitter).emit(callback);
	}

	@Test
	public void processRequisitionWithoutCallback() throws InstantiationException, IllegalAccessException {
		when(requisition.process()).thenReturn(null);

		subject.process(context, message);
		
		verify(store).getRequisition(message);
		verify(requisition).setContext(context);
		verify(requisition).setMessage(message);
		verify(requisition).process();
		verify(emitter, never()).emit(callback);
	}
	
	@Test
	public void emitWhenException() throws InstantiationException, IllegalAccessException {
		doThrow(new RuntimeException(FAIL_MESSAGE)).when(requisition).process();
		subject.process(context, message);
		
		verify(store).getRequisition(message);
		verify(requisition).setContext(context);
		verify(requisition).setMessage(message);
		verify(requisition).process();
		verify(emitter).emit(Mockito.argThat(new ArgumentMatcher<MessageImpl>() {
			@Override
			public boolean matches(Object argument) {
				return ((MessageImpl) argument).getValue().equals(FAIL_MESSAGE);
			}
		}));
	}

}
