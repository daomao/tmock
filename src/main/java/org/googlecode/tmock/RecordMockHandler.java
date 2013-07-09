package org.googlecode.tmock;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;

import net.sf.cglib.proxy.MethodProxy;

import org.googlecode.tmock.persistent.SerializationException;
import org.googlecode.tmock.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author zhongfeng
 * 
 */
class RecordMockHandler extends MockHandler {
	private static final Logger LOG = LoggerFactory
			.getLogger(RecordMockHandler.class);

	/**
	 * @param target
	 * @param mockSettings
	 */
	public RecordMockHandler(Object target, MockSettings mockSettings) {
		super(target, mockSettings);
	}

	@Override
	protected Object invoke(Object proxyObj, Method m, Object[] args,
			MethodProxy proxy) throws Throwable {
		try {
			LOG.debug("begin to record");
			LOG.info("Record Class:{},Method:{},Args:{}", new Object[] {
					m.getClass(), m.getName(), Arrays.deepToString(args) });
			Object result = proxy.invoke(target, args);
			record(m, args, result);
			return result;
		} catch (Throwable e) {
			// 记录异常
			record(m, args, e);
			throw e;
		}
	}

	/**
	 * @param args
	 * @param result
	 * @throws SerializationException
	 * @throws IOException
	 */
	public void record(Method m, Object[] args, Object result)
			throws SerializationException, IOException {
		String resourceFileName = mockSettings.getResourceNameGenerator()
				.createFileName(m, args);
		String recordPath = mockSettings.getResourcePathResolver()
				.getRecordBaseRootPath(mockSettings.getTestCaseClass(),
						MockTag.getTestCaseDirName(), resourceFileName);
		IOUtils.writeObjToFile(result, recordPath, mockSettings.getCoderType());
	}
}
