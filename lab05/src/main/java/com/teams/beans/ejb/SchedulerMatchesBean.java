package com.teams.beans.ejb;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;

import com.teams.utils.Match;

@Singleton
public class SchedulerMatchesBean {
	
	private ArrayList<Match> matches;
	
	@PostConstruct
	public void init() {
		this.matches = new ArrayList<Match>();
	}

	public void addMatch(Match match) {
		matches.add(match);
		this.listMatches();
	}
	
	public void assignMatches(ArrayList<Match> matches) {
		for (int i = 0; i < matches.size(); i++) {
			this.matches.add(matches.get(i));
		}
	}
	
	public ArrayList<Match> getMatches() {
		return matches;
	}

	public void setMatches(ArrayList<Match> matches) {
		this.matches = matches;
	}
	
	private void listMatches() {
		for (int i = 0; i < matches.size(); i++) {
			System.out.print("TeamOne: " + matches.get(i).getTeamOne() + "; TeamTwo: " + matches.get(i).getTeamTwo() + "; Week: " + matches.get(i).getWeek() + "; \n");
		}
	}
}
