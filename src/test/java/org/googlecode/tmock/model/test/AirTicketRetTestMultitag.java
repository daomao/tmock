package org.googlecode.tmock.model.test;

import java.util.ArrayList;

import org.googlecode.tmock.MockFactory;
import org.googlecode.tmock.MockProcess;
import org.googlecode.tmock.MockSettings;
import org.googlecode.tmock.MockTag;
import org.googlecode.tmock.MockSettings.MockSettingsBuilder;
import org.googlecode.tmock.matcher.support.NameMatchMethodMatcher;
import org.googlecode.tmock.model.MultiAirTicketRet;
import org.googlecode.tmock.model.TicketRet;
import org.googlecode.tmock.persistent.Passenger;
import org.googlecode.tmock.support.MultiTagResourceNameGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * @author zhongfeng
 * 
 */
public class AirTicketRetTestMultitag {

	private MultiAirTicketRet multiAirTicketRet;

	@Before
	public void setUp() throws Exception {
		MockSettings mockSettings = MockSettingsBuilder.newBuilder(this
				.getClass()).resourceNameGenerator(MultiTagResourceNameGenerator.INST).build();
		TicketRet b2 = MockFactory.createMock(new TicketRet(),
				MockProcess.RECORD, mockSettings);
		multiAirTicketRet = new MultiAirTicketRet(b2);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetPassenger() {

		MockTag.setTestCaseDirName("testGetPassenger");
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("1");
		tags.add("2");
		tags.add("3");
		MockTag.setMultiTags(tags);
		Passenger passenger = multiAirTicketRet.getPassenger();
		MockTag.clear();
	}

}
