package org.googlecode.tmock.model;

import org.googlecode.tmock.persistent.Passenger;


/**
 * @author zhongfeng
 *
 */
public final class FinalClassAirTicketRet{

	private TicketRetInterface ticketRet;
	
	
	/**
	 * @param b2
	 */
	public FinalClassAirTicketRet(TicketRetInterface ticketRet) {
		this.ticketRet = ticketRet;
	}

	/* (non-Javadoc)
	 * @see com.travelsky.tmock.model.Bussiness4#getUserFromB2()
	 */
	public Passenger getPassenger()
	{
		ticketRet.m2();
		return ticketRet.getPassenger();
	}
	
}
