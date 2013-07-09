package org.googlecode.tmock.premock;

import junit.framework.Assert;

import org.googlecode.tmock.PreMock;
import org.googlecode.tmock.PreMockJUnit4ClassRunner;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;


@Ignore
@PreMock( { HardToMock.class, EasyToMock.class })
@RunWith(PreMockJUnit4ClassRunner.class)
// @RunWith(MockitoJUnitRunner.class)
public class ExampleTest {

	// @Mock
	HardToMock hardToMock;

	// @Mock
	EasyToMock easyToMock;

	@Test
	public void testIt() {
		// Mockito.when(hardToMock.finalMethod()).thenReturn(
		// "mock result from final method");
		// Mockito.when(hardToMock.nativeMethod()).thenReturn(
		// "mock result from native method");
		// Mockito.when(easyToMock.method()).thenReturn(
		// "PreMock does not get in the way");
		//
		Assert.assertEquals("mock result from final method", hardToMock
				.finalMethod());
		Assert.assertEquals("mock result from native method", hardToMock
				.nativeMethod());
		Assert.assertEquals("PreMock does not get in the way", easyToMock
				.method());
	}
}