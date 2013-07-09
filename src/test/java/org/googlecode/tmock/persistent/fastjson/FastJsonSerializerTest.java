/**
 * 
 */
package org.googlecode.tmock.persistent.fastjson;

import org.googlecode.tmock.persistent.Passenger;
import org.googlecode.tmock.persistent.Serializer;
import org.googlecode.tmock.persistent.fastjson.FastJsonSerializer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * @author zhongfeng
 *
 */
public class FastJsonSerializerTest {

	private Serializer serializer;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		serializer = FastJsonSerializer.getInstance();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link org.googlecode.tmock.persistent.fastjson.FastJsonSerializer#deserializeFromString(java.lang.String)}.
	 */
	@Test
	public void testDeserializeFromString() {
	}

	/**
	 * Test method for {@link org.googlecode.tmock.persistent.fastjson.FastJsonSerializer#serializeToString(java.lang.Object)}.
	 */
	@Test
	public void testSerializeToString() {
		Passenger u = new Passenger("zhongfeng", 29);
		System.out.println(serializer.serialize(u));
		System.out.println(serializer.deserialize(serializer.serialize(u)));
	}

}
