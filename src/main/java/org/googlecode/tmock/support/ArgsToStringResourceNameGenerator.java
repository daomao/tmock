package org.googlecode.tmock.support;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.googlecode.tmock.MockResourceNameGenerator;

/**
 * @author zhongfeng
 * 
 */
public class ArgsToStringResourceNameGenerator extends
		BaseTemplateResourceNameGenerator {

	public final static MockResourceNameGenerator INST = new ArgsToStringResourceNameGenerator();

	@Override
	protected String buildFileNameRelatedSeg(Method m, Object[] args) {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append(m.getDeclaringClass().getName());
		strBuilder.append(m.getName());
		for (Class<?> cls : m.getParameterTypes()) {
			strBuilder.append(cls.getName());
		}
		
		strBuilder.append(Arrays.deepToString(args));
		return strBuilder.toString();
	}
}
