package org.googlecode.tmock.support;

import static org.junit.Assert.*;

import java.lang.reflect.Method;

import org.googlecode.tmock.support.DefaultResourceNameGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author zhongfeng
 *
 */
public class DefaultResourceNameGeneratorTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBuildFileNameRelatedSeg() throws Exception, Exception {
		Method m = DefaultResourceNameGenerator.class.getDeclaredMethod(
				"buildFileNameRelatedSeg", new Class<?>[] { Method.class,
						Object[].class });
		m.setAccessible(true);
		System.out.println(m.invoke(DefaultResourceNameGenerator.INST, new Object[] { m,
				new Class<?>[] { Method.class, Object[].class } }));
	}
}
