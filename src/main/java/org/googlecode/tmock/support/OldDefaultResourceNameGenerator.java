package org.googlecode.tmock.support;

import java.io.Serializable;
import java.lang.reflect.Method;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.googlecode.tmock.MockResourceNameGenerator;
import org.googlecode.tmock.persistent.jdk.JDKSerializer;
import org.googlecode.tmock.util.StringReflectionBuilder;

/**
 * @author zhongfeng
 * 
 */
public class OldDefaultResourceNameGenerator extends
		BaseTemplateResourceNameGenerator {

	public final static MockResourceNameGenerator INST = new OldDefaultResourceNameGenerator();

	@Override
	protected String buildFileNameRelatedSeg(Method m, Object[] args) {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append(m.getDeclaringClass().getName());
		strBuilder.append(m.getName());
		for (Class<?> cls : m.getParameterTypes()) {
			strBuilder.append(cls.getName());
		}
		for (Object arg : args) {
			strBuilder.append(ReflectionToStringBuilder.toString(arg));
		}
		// strBuilder.append(Arrays.deepToString(args));
		return strBuilder.toString();
	}
}
