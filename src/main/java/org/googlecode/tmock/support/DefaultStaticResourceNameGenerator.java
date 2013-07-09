package org.googlecode.tmock.support;

import java.lang.reflect.Method;

import org.googlecode.tmock.MockResourceNameGenerator;


/**
 * @author zhongfeng
 * 
 */
public class DefaultStaticResourceNameGenerator extends
		BaseTemplateResourceNameGenerator {

	public final static MockResourceNameGenerator INST = new DefaultStaticResourceNameGenerator();

	@Override
	protected String buildFileNameRelatedSeg(Method m, Object[] args) {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append(m.getDeclaringClass().getName());
		strBuilder.append(m.getName());
		for (Class<?> cls : m.getParameterTypes()) {
			strBuilder.append(cls.getName());
		}
		return strBuilder.toString();
	}
}
