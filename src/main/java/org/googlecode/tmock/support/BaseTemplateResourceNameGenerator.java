package org.googlecode.tmock.support;

import java.lang.reflect.Method;

import org.apache.commons.codec.digest.DigestUtils;
import org.googlecode.tmock.MockResourceNameGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author zhongfeng
 *
 */
public abstract class BaseTemplateResourceNameGenerator implements MockResourceNameGenerator{

	private final static Logger log = LoggerFactory.getLogger(BaseTemplateResourceNameGenerator.class);
	
	/**
	 * 默认使用md5计算文件名
	 */
	@Override
	public String createFileName(Method m, Object[] args) {
		String fname = buildFileNameRelatedSeg(m,args);
		log.debug("resource related  name string :{}",fname);
		return DigestUtils.md5Hex(fname);
	}

	/**
	 * @param m
	 * @param args
	 * @return
	 */
	protected abstract String buildFileNameRelatedSeg(Method m, Object[] args);
}
