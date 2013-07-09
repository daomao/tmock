package org.googlecode.tmock;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;

import net.sf.cglib.proxy.MethodProxy;

import org.apache.commons.codec.binary.Base64;
import org.googlecode.tmock.persistent.SerializationException;
import org.googlecode.tmock.persistent.TransCoderFactory.CoderType;
import org.googlecode.tmock.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author zhongfeng
 */
class ReplayMockHandler extends MockHandler {
	private static final Logger LOG = LoggerFactory
			.getLogger(ReplayMockHandler.class);

	/**
	 * @param target
	 * @param mockSettings
	 */
	public ReplayMockHandler(Object target, MockSettings mockSettings) {
		super(target, mockSettings);
	}

	@Override
	protected Object invoke(Object proxyObj, Method m, Object[] args,
			MethodProxy proxy) throws Throwable {
		LOG.debug("begin to replay");
		LOG.info("Replay Class:{},Method:{},Args:{}", new Object[] {
				m.getClass(), m.getName(), Arrays.deepToString(args) });
		Object result = replay(m, args);

		// fastjson bug,byte[]类型不能处理
		if (CoderType.fastjson.equals(mockSettings.getCoderType())
				&& m.getReturnType().equals(byte[].class))
			return Base64.decodeBase64(result.toString());
		if (result instanceof Throwable)// 复原异常
			throw (Throwable) result;
		return result;
	}

	public Object replay(Method m, Object[] args)
			throws SerializationException, IOException {
		String resourceFileName = mockSettings.getResourceNameGenerator()
				.createFileName(m, args);
		String absolutePathName = mockSettings.getResourcePathResolver()
				.getMockResourceFile(mockSettings.getTestCaseClass(),
						MockTag.getTestCaseDirName(),resourceFileName);
		LOG.info("Replay From {}", absolutePathName);
		return IOUtils.readObjFromFile(absolutePathName, mockSettings
				.getCoderType());
	}

}
