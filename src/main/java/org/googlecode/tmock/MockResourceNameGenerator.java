package org.googlecode.tmock;

import java.lang.reflect.Method;

/**
 * @author zhongfeng
 * 
 */
public interface MockResourceNameGenerator {

	/**
	 * 根据 method args 计算一个唯一的标示，用以标记生成的测试用例资源
	 * @param m method
	 * @param args 参数列表
	 * @return 唯一标示的串
	 */
	public String createFileName(Method m, Object[] args);
}
