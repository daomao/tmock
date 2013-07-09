package org.googlecode.tmock;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhongfeng
 *
 */
abstract class MockHandler implements MethodInterceptor {

	private static final Logger log = LoggerFactory.getLogger(MockHandler.class);

	/**
	 * 
	 */
	protected MockSettings mockSettings;
	
	/**
	 * proxy obj 
	 */
	protected Object target;
	

	/**
	 * @param target
	 * @param mockSettings
	 */
	public MockHandler(Object target,MockSettings mockSettings) {
		this.target = target;
		this.mockSettings = mockSettings;
	}

	public Object intercept(Object obj, Method m, Object[] args,
			MethodProxy proxy) throws Throwable {
		Object result = null;
		try {
			result = invoke(obj, m, args, proxy);
		} catch (InvocationTargetException e) {
			log.error("InvocationTargetException error ", e);
			throw e;
		} catch (Throwable e) {
			log.error("invoke unknown error ", e);
			throw e;
		} finally {
			log.info("Method:{},Args:{},Return:{}", new Object[] { m.getName(),
					Arrays.deepToString(args), result });
		}
		return result;
	}
	
	/**
	 * 拦截器方法，用于控制replay,record的行为
	 * @param proxyObj Dynamic proxy object
	 * @param m method
	 * @param args method args
	 * @param proxy 
	 * @return
	 * @throws Throwable
	 */
	protected abstract Object invoke(Object proxyObj, Method m, Object[] args,
			MethodProxy proxy) throws Throwable;

	public MockSettings getMockSettings() {
		return mockSettings;
	}

	public void setMockSettings(MockSettings mockSettings) {
		this.mockSettings = mockSettings;
	}

	public Object getTarget() {
		return target;
	}

	public void setTarget(Object target) {
		this.target = target;
	}

}
