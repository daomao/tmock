package org.googlecode.tmock.model;

import org.googlecode.tmock.persistent.Passenger;

/**
 * @author zhongfeng
 *
 */
public class MultiAirTicketRet {

	private TicketRet ticketRet;
	
	
	/**
	 * @param b2
	 */
	public MultiAirTicketRet(TicketRet ticketRet) {
		this.ticketRet = ticketRet;
	}

	public Passenger getPassenger()
	{
		ticketRet.m2();
		ticketRet.m2();
		ticketRet.m2();
		return ticketRet.getPassenger();
	}
	
}
