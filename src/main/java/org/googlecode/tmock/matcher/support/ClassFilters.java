
package org.googlecode.tmock.matcher.support;

import java.io.Serializable;

import org.googlecode.tmock.matcher.ClassFilter;



/**
 * @author zhongfeng
 *
 */
public abstract class ClassFilters {

	/**
	 * Match all classes that <i>either</i> (or both) of the given ClassFilters matches.
	 * @param cf1 the first ClassFilter
	 * @param cf2 the second ClassFilter
	 * @return a distinct ClassFilter that matches all classes that either
	 * of the given ClassFilter matches
	 */
	public static ClassFilter union(ClassFilter cf1, ClassFilter cf2) {
		
		return new UnionClassFilter(new ClassFilter[] {cf1, cf2});
	}

	/**
	 * Match all classes that <i>either</i> (or all) of the given ClassFilters matches.
	 * @param classFilters the ClassFilters to match
	 * @return a distinct ClassFilter that matches all classes that either
	 * of the given ClassFilter matches
	 */
	public static ClassFilter union(ClassFilter[] classFilters) {
		
		return new UnionClassFilter(classFilters);
	}

	/**
	 * Match all classes that <i>both</i> of the given ClassFilters match.
	 * @param cf1 the first ClassFilter
	 * @param cf2 the second ClassFilter
	 * @return a distinct ClassFilter that matches all classes that both
	 * of the given ClassFilter match
	 */
	public static ClassFilter intersection(ClassFilter cf1, ClassFilter cf2) {
		
		return new IntersectionClassFilter(new ClassFilter[] {cf1, cf2});
	}

	/**
	 * Match all classes that <i>all</i> of the given ClassFilters match.
	 * @param classFilters the ClassFilters to match
	 * @return a distinct ClassFilter that matches all classes that both
	 * of the given ClassFilter match
	 */
	public static ClassFilter intersection(ClassFilter[] classFilters) {
		return new IntersectionClassFilter(classFilters);
	}


	/**
	 * ClassFilter implementation for a union of the given ClassFilters.
	 */
	@SuppressWarnings("serial")
	private static class UnionClassFilter implements ClassFilter, Serializable {

		private ClassFilter[] filters;

		public UnionClassFilter(ClassFilter[] filters) {
			this.filters = filters;
		}

		public boolean matches(Class clazz) {
			for (int i = 0; i < this.filters.length; i++) {
				if (this.filters[i].matches(clazz)) {
					return true;
				}
			}
			return false;
		}

		@Override
		public boolean equals(Object other) {
			return (this == other || (other instanceof UnionClassFilter &&
					ObjectUtils.nullSafeEquals(this.filters, ((UnionClassFilter) other).filters)));
		}

		@Override
		public int hashCode() {
			return ObjectUtils.nullSafeHashCode(this.filters);
		}
	}


	/**
	 * ClassFilter implementation for an intersection of the given ClassFilters.
	 */
	@SuppressWarnings("serial")
	private static class IntersectionClassFilter implements ClassFilter, Serializable {

		private ClassFilter[] filters;

		public IntersectionClassFilter(ClassFilter[] filters) {
			this.filters = filters;
		}

		public boolean matches(Class clazz) {
			for (int i = 0; i < this.filters.length; i++) {
				if (!this.filters[i].matches(clazz)) {
					return false;
				}
			}
			return true;
		}

		@Override
		public boolean equals(Object other) {
			return (this == other || (other instanceof IntersectionClassFilter &&
					ObjectUtils.nullSafeEquals(this.filters, ((IntersectionClassFilter) other).filters)));
		}

		@Override
		public int hashCode() {
			return ObjectUtils.nullSafeHashCode(this.filters);
		}
	}

}
