package org.googlecode.tmock.persistent.hessian2;

import org.googlecode.tmock.persistent.Passenger;
import org.googlecode.tmock.persistent.TransCoderFactory;
import org.googlecode.tmock.persistent.Transcoder;
import org.googlecode.tmock.persistent.TransCoderFactory.CoderType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * @author zhongfeng
 *
 */
public class Hessian2TransCoderTest {

	private Transcoder coder = TransCoderFactory.getTranscoder(CoderType.hessian2);
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDecode() {

	}

	@Test
	public void testEncode() {
		Passenger u = new Passenger("zhongfeng", 29);
		System.out.println(coder.decode(coder.encode(u)).getClass());
	}

}
