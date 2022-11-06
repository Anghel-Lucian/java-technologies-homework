package com.teams.beans.ejb;

import java.util.ArrayList;

import javax.ejb.Stateful;
import javax.inject.Inject;

import com.teams.utils.Match;

@Stateful
public class SchedulerAssignerBean {
	
	private ArrayList<Match> matches;

	@Inject
	SchedulerMatchesBean smb;
	
	public void addMatch(Match match) {
		matches.add(match);
	}
	
	// TODO: continue working on the stateful bean
	public void assignMatches() {
		smb.assignMatches(matches);
	}
	
	public ArrayList<Match> getMatches() {
		return matches;
	}

	public void setMatches(ArrayList<Match> matches) {
		this.matches = matches;
	}
	
}
