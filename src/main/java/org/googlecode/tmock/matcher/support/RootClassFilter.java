

package org.googlecode.tmock.matcher.support;

import java.io.Serializable;

import org.googlecode.tmock.matcher.ClassFilter;


/**
 * @author zhongfeng
 *
 */
@SuppressWarnings("serial")
public class RootClassFilter implements ClassFilter, Serializable {

	private Class clazz;

	// TODO inheritance

	public RootClassFilter(Class clazz) {
		this.clazz = clazz;
	}

	public boolean matches(Class candidate) {
		return clazz.isAssignableFrom(candidate);
	}

}
