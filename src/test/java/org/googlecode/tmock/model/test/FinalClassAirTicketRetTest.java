package org.googlecode.tmock.model.test;

import org.googlecode.tmock.MockFactory;
import org.googlecode.tmock.MockProcess;
import org.googlecode.tmock.MockTag;
import org.googlecode.tmock.PreMock;
import org.googlecode.tmock.PreMockJUnit4ClassRunner;
import org.googlecode.tmock.MockSettings.MockSettingsBuilder;
import org.googlecode.tmock.model.FinalClassAirTicketRet;
import org.googlecode.tmock.model.TicketRetFinal;
import org.googlecode.tmock.model.TicketRetInterface;
import org.googlecode.tmock.persistent.Passenger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


/**
 * @author zhongfeng
 * 
 */
@PreMock( { TicketRetFinal.class })
@RunWith(PreMockJUnit4ClassRunner.class)
public class FinalClassAirTicketRetTest {

	private FinalClassAirTicketRet finalClassAirTicketRet;

	@Before
	public void setUp() throws Exception {
		TicketRetInterface ticketRetInterface = MockFactory.createMock(new TicketRetFinal(),
				MockProcess.RECORD, MockSettingsBuilder.newBuilder(this
						.getClass()).build());
		finalClassAirTicketRet = new FinalClassAirTicketRet(ticketRetInterface);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetPassenger() {
		MockTag.setTestCaseDirName("testGetPassenger");
		Passenger passenger = finalClassAirTicketRet.getPassenger();
		MockTag.clear();
	}

}
