
package org.googlecode.tmock.matcher.support;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;


/**
 * @author zhongfeng
 *
 */
public class AnnotationMethodMatcher extends StaticMethodMatcher {

	private final Class<? extends Annotation> annotationType;

	/**
	 * Create a new AnnotationClassFilter for the given annotation type.
	 * 
	 * @param annotationType
	 *            the annotation type to look for
	 */
	public AnnotationMethodMatcher(Class<? extends Annotation> annotationType) {
		// Assert.notNull(annotationType, "Annotation type must not be null");
		this.annotationType = annotationType;
	}

	public boolean matches(Method method, Class targetClass) {
		if (method.isAnnotationPresent(this.annotationType)) {
			return true;
		}
		// The method may be on an interface, so let's check on the target class
		// as well.
		// Method specificMethod = AopUtils.getMostSpecificMethod(method,
		// targetClass);
		// return (specificMethod != method &&
		// specificMethod.isAnnotationPresent(this.annotationType));
		return false;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AnnotationMethodMatcher)) {
			return false;
		}
		AnnotationMethodMatcher otherMm = (AnnotationMethodMatcher) other;
		return this.annotationType.equals(otherMm.annotationType);
	}

	@Override
	public int hashCode() {
		return this.annotationType.hashCode();
	}

}
