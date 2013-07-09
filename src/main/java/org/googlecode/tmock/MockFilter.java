package org.googlecode.tmock;

import java.lang.reflect.Method;

import org.googlecode.tmock.matcher.MethodMatcher;
import org.googlecode.tmock.matcher.TrueMethodMatcher;


import net.sf.cglib.proxy.CallbackFilter;

/**
 * @author zhongfeng
 * 
 */
class MockFilter implements CallbackFilter {

	private MethodMatcher methodMatcher;

	public final static String METHOD_NAME = "finalize";

	public MockFilter() {
		this(TrueMethodMatcher.INSTANCE);
	}

	/**
	 * @param methodMatcher
	 */
	public MockFilter(MethodMatcher methodMatcher) {
		this.methodMatcher = methodMatcher;
	}

	@Override
	public int accept(Method method) {
		//bug fix
		if (method.getName().equalsIgnoreCase(METHOD_NAME))
			return 1;
		if (methodMatcher.matches(method, method.getDeclaringClass()))
			return 0;
		return 1;
	}

	public static CallbackFilter newInstance(MethodMatcher methodMatcher) {
		return new MockFilter(methodMatcher);
	}

	public static CallbackFilter newInstance() {
		return new MockFilter();
	}

	public MethodMatcher getMethodMatcher() {
		return methodMatcher;
	}

	public void setMethodMatcher(MethodMatcher methodMatcher) {
		this.methodMatcher = methodMatcher;
	}
}
