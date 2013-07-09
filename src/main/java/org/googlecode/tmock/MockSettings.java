package org.googlecode.tmock;

import org.googlecode.tmock.matcher.MethodMatcher;
import org.googlecode.tmock.matcher.TrueMethodMatcher;
import org.googlecode.tmock.persistent.TransCoderFactory.CoderType;
import org.googlecode.tmock.support.DefaultMockResourcePathResolver;
import org.googlecode.tmock.support.DefaultResourceNameGenerator;

/**
 * @author zhongfeng
 * 
 */
public class MockSettings {

	/**
	 * TestCase 文件名生成策略
	 */
	private final MockResourceNameGenerator resourceNameGenerator;

	/**
	 * TestCase Resource 路径存储
	 */
	private final MockResourcePathResolver resourcePathResolver;

	/**
	 * mock 方法适配器
	 */
	private final MethodMatcher methodMatcher;

	/**
	 * Object 存储编码格式
	 */
	private final CoderType coderType;

	/**
	 * 当前测试用例类名
	 */
	private final Class<?> testCaseClass;

	/**
	 * @author zhongfeng
	 *
	 */
	public static class MockSettingsBuilder {

		/**
		 * 当前测试用例类名
		 */
		private Class<?> testCaseClass;

		private MockResourceNameGenerator resourceNameGenerator = DefaultResourceNameGenerator.INST;

		private MockResourcePathResolver resourcePathResolver = DefaultMockResourcePathResolver.INST;

		private MethodMatcher methodMatcher = TrueMethodMatcher.INSTANCE;

		private CoderType coderType = CoderType.jdk;

		private MockSettingsBuilder(Class<?> testCaseClass) {
			this.testCaseClass = testCaseClass;
		}

		public MockSettingsBuilder resourceNameGenerator(
				MockResourceNameGenerator resourceNameGenerator) {
			this.resourceNameGenerator = resourceNameGenerator;
			return this;
		}

		public MockSettingsBuilder resourcePathResolver(
				MockResourcePathResolver resourcePathResolver) {
			this.resourcePathResolver = resourcePathResolver;
			return this;
		}

		public MockSettingsBuilder methodMatcher(MethodMatcher methodMatcher) {
			this.methodMatcher = methodMatcher;
			return this;
		}

		public MockSettingsBuilder coderType(CoderType coderType) {
			this.coderType = coderType;
			return this;
		}

		public static MockSettingsBuilder newBuilder(Class<?> testCaseClass) {
			return new MockSettingsBuilder(testCaseClass);
		}

		public MockSettings build() {
			return new MockSettings(this);
		}

	}

	/**
	 * @param builder
	 */
	private MockSettings(MockSettingsBuilder builder) {
		this.resourceNameGenerator = builder.resourceNameGenerator;
		this.resourcePathResolver = builder.resourcePathResolver;
		this.methodMatcher = builder.methodMatcher;
		this.coderType = builder.coderType;
		this.testCaseClass = builder.testCaseClass;
	}

	public MockResourceNameGenerator getResourceNameGenerator() {
		return resourceNameGenerator;
	}

	public MockResourcePathResolver getResourcePathResolver() {
		return resourcePathResolver;
	}

	public MethodMatcher getMethodMatcher() {
		return methodMatcher;
	}

	public CoderType getCoderType() {
		return coderType;
	}

	public Class<?> getTestCaseClass() {
		return testCaseClass;
	}
}
