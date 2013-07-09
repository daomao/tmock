package org.googlecode.tmock.support;

import java.lang.reflect.Method;

import org.googlecode.tmock.MockTag;


/**
 * @author zhongfeng
 * 
 */
public class MultiTagStaticResourceNameGenerator extends DefaultStaticResourceNameGenerator {

	public final static MultiTagStaticResourceNameGenerator INST = new MultiTagStaticResourceNameGenerator();

	@Override
	protected String buildFileNameRelatedSeg(Method m, Object[] args) {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append(super.buildFileNameRelatedSeg(m, args));
		sBuilder.append(MockTag.getMultiTag());
		return sBuilder.toString();
	}

}
