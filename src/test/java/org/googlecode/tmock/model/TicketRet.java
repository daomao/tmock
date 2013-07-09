package org.googlecode.tmock.model;

import org.googlecode.tmock.persistent.Passenger;


/**
 * @author zhongfeng
 *
 */
public class TicketRet{

	public Passenger getPassenger() {
		m2();
		return new Passenger("test", 21);
	}
	
	public String m2()
	{
		return new Passenger("test", 21).toString();
	}
}
