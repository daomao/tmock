package org.googlecode.tmock.matcher;

import java.io.Serializable;

/**
 * Canonical ClassFilter instance that matches all classes.
 *
 */
@SuppressWarnings("serial")
class TrueClassFilter implements ClassFilter, Serializable {

	public static final TrueClassFilter INSTANCE = new TrueClassFilter();

	/**
	 * Enforce Singleton pattern.
	 */
	private TrueClassFilter() {
	}

	public boolean matches(Class<?> clazz) {
		return true;
	}

	/**
	 * Required to support serialization. Replaces with canonical
	 * instance on deserialization, protecting Singleton pattern.
	 * Alternative to overriding {@code equals()}.
	 */
	private Object readResolve() {
		return INSTANCE;
	}

	@Override
	public String toString() {
		return "ClassFilter.TRUE";
	}

}
