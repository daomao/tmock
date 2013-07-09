package org.googlecode.tmock.model;

import org.googlecode.tmock.persistent.Passenger;

/**
 * @author zhongfeng
 *
 */
public final class TicketRetFinal implements TicketRetInterface {

	@Override
	public Passenger getPassenger() {
		m2();
		return new Passenger("test", 21);
	}
	
	@Override
	public String m2()
	{
		return new Passenger("test", 21).toString();
	}


}
