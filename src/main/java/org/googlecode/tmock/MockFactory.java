package org.googlecode.tmock;

import org.googlecode.tmock.dyproxy.CglibProxyFactory;
import org.googlecode.tmock.matcher.MethodMatcher;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.NoOp;


/**
 * 
 * @author zhongfeng
 * 
 */
public class MockFactory {

	/**
	 * 
	 * @param <T>
	 * @param target
	 *            目标对象
	 * @param mp
	 *            Replay or Record
	 * @return MockObject
	 */
	public final static <T> T createMock(T target, MockProcess mp,
			MockSettings mockSettings) {
		return createMock(target, null, mp, mockSettings);
	}

	/**
	 * @param <T>
	 * 
	 * @param target
	 *            目标对象
	 * @param icls
	 *            接口 or class类型]\
	 * 
	 * @param mp
	 *            Replay or Record
	 * @param mockSettings
	 * 
	 * @return mock object
	 */
	public final static <T> T createMock(T target, Class<T> icls,
			MockProcess mp, MockSettings mockSettings) {
		MockTag.clear();
		if (mp.equals(MockProcess.RECORD)) {
			return CglibProxyFactory.createProxy(target, icls,
					createRecordHandlers(target, mockSettings),
					createCallbackFilter(mockSettings.getMethodMatcher()));
		} else {
			return CglibProxyFactory.createProxy(target, icls,
					createReplayHandlers(target, mockSettings),
					createCallbackFilter(mockSettings.getMethodMatcher()));
		}
	}

	private static CallbackFilter createCallbackFilter(
			MethodMatcher methodMatcher) {
		return MockFilter.newInstance(methodMatcher);
	}

	private static Callback[] createRecordHandlers(Object target,
			MockSettings mockSettings) {
		return new Callback[] { new RecordMockHandler(target, mockSettings),
				NoOp.INSTANCE };
	}

	private static Callback[] createReplayHandlers(Object target,
			MockSettings mockSettings) {
		return new Callback[] { new ReplayMockHandler(target, mockSettings),
				NoOp.INSTANCE };
	}

}