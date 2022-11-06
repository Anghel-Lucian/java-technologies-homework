package com.teams.beans.ejb;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class SchedulerAvailabilityBean {

	@Inject
	SchedulerMatchesBean sab;
	
	public boolean weekAvailable(int week) {
		for (int i = 0; i < sab.getMatches().size(); i++) {
			if (sab.getMatches().get(i).getWeek() == week) {
				return false;
			}
		}
		return true;
	}
	
}
