/**
 * 
 */
package org.googlecode.tmock.support;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.commons.lang.StringUtils;
import org.googlecode.tmock.MockResourcePathResolver;
import org.googlecode.tmock.util.sresource.resource.PathMatchingResourcePatternResolver;
import org.googlecode.tmock.util.sresource.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author zhongfeng
 * 
 */
public class DefaultMockResourcePathResolver implements
		MockResourcePathResolver {
	private final static Logger logger = LoggerFactory
			.getLogger(DefaultMockResourcePathResolver.class);

	/**
	 * 
	 */
	public static MockResourcePathResolver INST = new DefaultMockResourcePathResolver();

	/**
	 * 
	 */
	public final static String RESOURCE_PATH_END = "mock_resources";

	/**
	 * 
	 */
	private final static PathMatchingResourcePatternResolver RESOLVER = new PathMatchingResourcePatternResolver();

	/**
	 * 
	 */
	private DefaultMockResourcePathResolver() {
	}

	@Override
	public String getMockResourceFile(Class<?> testCaseClass,
			String testCaseName, String resourceFileName) {
		String filePattern = "classpath*:"
				+ getSubPath(testCaseClass, testCaseName, resourceFileName);
		try {
			Resource[] resources = RESOLVER.getResources(filePattern);
			if ((resources == null) || (resources.length == 0))
				throw new FileNotFoundException(resourceFileName);
			if (resources.length > 1) {
				StringBuilder strBuild = new StringBuilder();
				for (Resource resource : resources)
					strBuild.append(resource.getURL().toString() + ";");
				logger.warn("Mutil source found :{}", strBuild.toString());
			}
			return resources[0].getFile().getAbsolutePath();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String getRecordBaseRootPath(Class<?> testCaseClass,
			String testCaseName, String resourceFileName) {
		String rootPath = "";
		try {
			rootPath = Thread.currentThread().getContextClassLoader()
					.getResource("").toURI().getPath();
		} catch (URISyntaxException e) {
			logger.error("URI",e);
		}
		String subFullPath = getSubPath(testCaseClass, testCaseName,
				resourceFileName);
		return StringUtils.join(new Object[] { rootPath, subFullPath },
				File.separator);

	}

	private String getSubPath(Class<?> testCaseClass, String testCaseName,
			String resourceFileName) {
		String subPath = StringUtils.join(testCaseClass.getName().split("\\."),
				File.separator);
		String subFullPath = null;
		if (StringUtils.isEmpty(testCaseName)) {
			subFullPath = StringUtils.join(new Object[] { RESOURCE_PATH_END,
					subPath, resourceFileName }, File.separator);
		} else {
			subFullPath = StringUtils.join(new Object[] { RESOURCE_PATH_END,
					subPath, testCaseName, resourceFileName }, File.separator);
		}
		return subFullPath;
	}

}
