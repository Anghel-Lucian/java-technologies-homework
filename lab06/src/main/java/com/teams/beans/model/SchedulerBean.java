package com.teams.beans.model;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.teams.beans.ejb.SchedulerAssignerBean;
import com.teams.beans.ejb.SchedulerAvailabilityBean;
import com.teams.beans.ejb.SchedulerMatchesBean;
import com.teams.utils.Match;

@SuppressWarnings("serial")
@Named("scheduler")
@ViewScoped
public class SchedulerBean implements Serializable {
	
	private String teamOne;
	private String teamTwo;
	private int week;
	
	// Singleton (keeps all matches in-memory)
	@Inject
	SchedulerMatchesBean smb;
	
	// Stateless (checks week availability)
	@Inject
	SchedulerAvailabilityBean sab;
	
	// Stateful (TODO: ask how this should have been implemented, I don't quite get it rn)
	@Inject
	SchedulerAssignerBean sb;
	
	public void schedule() {
		if (!sab.weekAvailable(week)) {
			System.out.print("Match exists in week.");
			return;
		}
		
		Match match = new Match(teamOne, teamTwo, week);
		smb.addMatch(match);
		
		this.setTeamOne("");
		this.setTeamTwo("");
		this.setWeek(0);
	}
	
	public String getTeamOne() {
		return teamOne;
	}
	public void setTeamOne(String teamOne) {
		this.teamOne = teamOne;
	}
	public String getTeamTwo() {
		return teamTwo;
	}
	public void setTeamTwo(String teamTwo) {
		this.teamTwo = teamTwo;
	}
	public int getWeek() {
		return week;
	}
	public void setWeek(int week) {
		this.week = week;
	}

}
