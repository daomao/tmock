package org.googlecode.tmock.support;

import java.io.IOException;

import org.googlecode.tmock.persistent.Passenger;
import org.googlecode.tmock.persistent.TransCoderFactory.CoderType;
import org.googlecode.tmock.support.DefaultMockResourcePathResolver;
import org.googlecode.tmock.util.IOUtils;
import org.googlecode.tmock.util.sresource.resource.PathMatchingResourcePatternResolver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * @author zhongfeng
 *
 */
public class DefaultMockResourcePathResolverTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetRecordBaseRootPath() throws Exception, IOException {
		String fileName = null;
		System.out.println(System.getProperty("java.class.path"));
		System.out.println(fileName = DefaultMockResourcePathResolver.INST
				.getRecordBaseRootPath(
						DefaultMockResourcePathResolverTest.class,
						"testGetRecordBaseRootPath", "aaa"));

		IOUtils.writeObjToFile(new Passenger(), fileName, CoderType.jdk
				);
		System.out.println(IOUtils.readObjFromFile(fileName, CoderType.jdk));
	}
	
	@Test
	public void testGetMockResourceFile() throws Exception, IOException {
		testGetRecordBaseRootPath();
		PathMatchingResourcePatternResolver RESOLVER = new PathMatchingResourcePatternResolver();
		System.out.println("dd"+RESOLVER.getResources("classpath*:mock_resources/**/aaa")[0].getURL());
         System.out.println(IOUtils.readObjFromFile(DefaultMockResourcePathResolver.INST
        		.getMockResourceFile(DefaultMockResourcePathResolverTest.class,
						"testGetRecordBaseRootPath","aaa"), CoderType.jdk));
	}

}
