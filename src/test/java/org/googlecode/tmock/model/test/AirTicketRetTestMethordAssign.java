package org.googlecode.tmock.model.test;

import org.googlecode.tmock.MockFactory;
import org.googlecode.tmock.MockProcess;
import org.googlecode.tmock.MockSettings;
import org.googlecode.tmock.MockTag;
import org.googlecode.tmock.MockSettings.MockSettingsBuilder;
import org.googlecode.tmock.matcher.support.NameMatchMethodMatcher;
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
public class AirTicketRetTestMethordAssign {

	private AirTicketRet airTicketRet;

	@Before
	public void setUp() throws Exception {
		MockSettings mockSettings = MockSettingsBuilder.newBuilder(this
				.getClass()).methodMatcher(new NameMatchMethodMatcher().addMethodName("m2")).build();
		TicketRet ticketRet = MockFactory.createMock(new TicketRet(),
				MockProcess.RECORD, mockSettings);
		airTicketRet = new AirTicketRet(ticketRet);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetPassenger() {
		MockTag.setTestCaseDirName("testGetPassenger");
		Passenger passenger = airTicketRet.getPassengerFromTicketRet();
	}

}
