package br.com.pilovieira.sptagcomm.requisition;

import static java.util.Arrays.asList;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import android.content.Context;
import br.com.pilovieira.sptagcomm.Callback;
import br.com.pilovieira.sptagcomm.Status;
import br.com.pilovieira.sptagcomm.p2p.Emitter;

@RunWith(MockitoJUnitRunner.class)
public class RequisitionProcessorTest {
	
	private static String TAG_LOCALIZATION = Status.INSTANCE.tag();

	@Mock private Emitter emitter;
	@SuppressWarnings("rawtypes")
	@Mock private Requisition requisition;
	@Mock private Callback callback;
	@Mock private RequisitionException requisitionException;
	@Mock private RequisitionStore store;
	@Mock private Context context;

	@Rule public ExpectedException thrown = ExpectedException.none();
	
	private RequisitionProcessor subject;

	
	@SuppressWarnings("unchecked")
	@Before
	public void setUp() {
		when(store.getRequisition(any(Status.class))).thenReturn(requisition);
		when(requisition.getCallback()).thenReturn(callback);
		
		subject = new RequisitionProcessor(store, asList(emitter));
	}

	@Test
	public void processRequisition() {
		subject.process(context, TAG_LOCALIZATION);
		
		verify(store).getRequisition(Status.INSTANCE);
		verify(requisition).process();
		verify(requisition).getCallback();
		verify(emitter).emit(callback);
	}
	
	@Test
	public void emitTagError() {
		subject.process(context, "BATATAS");
		
		verify(emitter).emit(Matchers.any(ExceptionMessage.class));
	}

	@Test
	public void emitRequisitionError() {
		doThrow(new RequisitionException("")).when(requisition).process();
		subject.process(context, TAG_LOCALIZATION);
		
		verify(emitter).emit(Matchers.any(ExceptionMessage.class));
	}
	
	@Test
	public void processRequisitionWithoutCallback() {
		when(requisition.getCallback()).thenReturn(null);
		
		subject.process(context, TAG_LOCALIZATION);
		
		verify(store).getRequisition(Status.INSTANCE);
		verify(requisition).process();
		verify(emitter, never()).emit(any(Callback.class));
	}

}
