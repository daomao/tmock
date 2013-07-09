package org.googlecode.tmock.support;

import java.lang.reflect.Method;

import org.googlecode.tmock.MockResourceNameGenerator;
import org.googlecode.tmock.util.StringReflectionBuilder;

/**
 * @author zhongfeng
 * 
 */
public class DefaultResourceNameGenerator extends
		BaseTemplateResourceNameGenerator {

	public final static MockResourceNameGenerator INST = new DefaultResourceNameGenerator();

	@Override
	protected String buildFileNameRelatedSeg(Method m, Object[] args) {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append(m.getDeclaringClass().getName());
		strBuilder.append(m.getName());
		for (Class<?> cls : m.getParameterTypes()) {
			strBuilder.append(cls.getName());
		}
		for (Object arg : args) {
			strBuilder.append(StringReflectionBuilder.toString(arg));
		}
		// strBuilder.append(Arrays.deepToString(args));
		return strBuilder.toString();
	}
}
