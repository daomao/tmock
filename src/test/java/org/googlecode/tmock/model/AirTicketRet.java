package org.googlecode.tmock.model;

import org.googlecode.tmock.persistent.Passenger;


/**
 * @author zhongfeng
 *
 */
public class AirTicketRet {

	private TicketRet ticketRet;
	
	
	/**
	 * @param b2
	 */
	public AirTicketRet(TicketRet ticketRet) {
		this.ticketRet = ticketRet;
	}

	public Passenger getPassengerFromTicketRet()
	{
		ticketRet.m2();
		return ticketRet.getPassenger();
	}
	
}
