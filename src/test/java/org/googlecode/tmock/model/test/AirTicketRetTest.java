package org.googlecode.tmock.model.test;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.googlecode.tmock.MockFactory;
import org.googlecode.tmock.MockProcess;
import org.googlecode.tmock.MockTag;
import org.googlecode.tmock.MockSettings.MockSettingsBuilder;
import org.googlecode.tmock.model.AirTicketRet;
import org.googlecode.tmock.model.TicketRet;
import org.googlecode.tmock.persistent.Passenger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;



/**
 * @author zhongfeng
 *
 */
public class AirTicketRetTest {

	private AirTicketRet airTicketRetMock;

	@Before
	public void setUp() throws Exception {
		TicketRet ticketRetMcok = MockFactory.createMock(new TicketRet(),
				MockProcess.RECORD, MockSettingsBuilder.newBuilder(this
						.getClass()).build());
		airTicketRetMock = new AirTicketRet(ticketRetMcok);
	}

	@After
	public void tearDown() throws Exception {
		MockTag.clear();
	}

	@Test
	public void testGetPassenger() {
		MockTag.setTestCaseDirName("testGetPassenger");
		Passenger passenger = airTicketRetMock.getPassengerFromTicketRet();
	}
	
	@Test
	public void testGetPassenger2() {
		MockTag.setTestCaseDirName("testGetPassenger2");
		Passenger passenger = airTicketRetMock.getPassengerFromTicketRet();
	}
}
