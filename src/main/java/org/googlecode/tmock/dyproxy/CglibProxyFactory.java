package org.googlecode.tmock.dyproxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import net.sf.cglib.core.CodeGenerationException;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.Factory;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.NoOp;

import org.objenesis.ObjenesisStd;

/**
 * @author zhongfeng
 * 
 */
public class CglibProxyFactory {

	public static final CglibProxyFactory INSTANCE = new CglibProxyFactory();

	private CglibProxyFactory() {
	}

	private final static ObjenesisStd objenesis = new ObjenesisStd();

	public static boolean canImposterise(Class<?> type) {
		return !type.isPrimitive() && !Modifier.isFinal(type.getModifiers())
				&& !type.isAnonymousClass();
	}

	/**
	 * cglib 生成代理的工具类
	 * 
	 * @param <T>
	 * @param targetObj
	 * @param callbacks
	 * @param filter
	 * @return
	 */
	public static <T> T createProxy(T target, Callback[] callbacks,
			CallbackFilter filter) {
		return createProxy(target, null, callbacks, filter);
	}

	/**
	 * cglib 生成代理的工具类
	 * 
	 * @param <T>
	 * @param targetObj
	 * @param callbacks
	 * @param filter
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T createProxy(T target, Class<T> cls,
			Callback[] callbacks, CallbackFilter filter) {
		try {
			if (cls == null) {
				cls = (Class<T>) target.getClass();
			}
			if (!canImposterise(cls)) {
				throw new TMockException("cls:"+ cls +" isPrimitive or isFinal or isAnonymousClass");
			}
			setConstructorsAccessible(cls, true);
			Class<?> proxyClass = createProxyClass(target, cls, callbacks,
					filter);
			return cls.cast(createProxyInst(proxyClass, callbacks));
		} finally {
			setConstructorsAccessible(cls, false);
		}
	}

	private static void setConstructorsAccessible(Class<?> mockedType,
			boolean accessible) {
		if (mockedType == null) {
			return;
		}
		for (Constructor<?> constructor : mockedType.getDeclaredConstructors()) {
			constructor.setAccessible(accessible);
		}
	}

	private static Object createProxyInst(Class<?> proxyClass,
			Callback[] callbacks) {
		Factory proxy = (Factory) objenesis.newInstance(proxyClass);
		proxy.setCallbacks(callbacks);
		return proxy;
	}

	/**
	 * cglib 生成代理的工具类
	 * 
	 * @param <T>
	 * @param targetObj
	 * @param callbacks
	 * @param filter
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> Class<T> createProxyClass(T target, Class<T> cls,
			Callback[] callbacks, CallbackFilter filter) {
		Enhancer enhancer = new Enhancer();
		enhancer.setUseFactory(true);
		if (cls != null) {
			enhancer.setSuperclass(cls);
		} else {
			enhancer.setSuperclass(target.getClass());
		}
		enhancer.setCallbackTypes(new Class[] { MethodInterceptor.class,
				NoOp.class });
		// enhancer.setCallbacks(callbacks);
		if (filter != null)
			enhancer.setCallbackFilter(filter);
		try {
			return enhancer.createClass();
		} catch (CodeGenerationException e) {
			throw new TMockException(
					"\n"
							+ "TMock cannot mock this class: "
							+ cls
							+ "\n"
							+ "TMock can only mock visible & non-final classes."
							+ "\n"
							+ "If you're not sure why you're getting this error, please report to the mailing list.",
					e);
		}
	}

}
