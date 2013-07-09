package org.googlecode.tmock;


/**
 * @author zhongfeng
 *
 */
public interface MockResourcePathResolver {
	
	public String getRecordBaseRootPath(Class<?> testCaseClass, String testCaseName, String resourceFileName);
	
	public String getMockResourceFile(Class<?> testCaseClass, String testCaseName, String resourceFileName);
}
