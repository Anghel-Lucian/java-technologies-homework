package com.teams.utils;

public class Match {
	private String teamOne;
	private String teamTwo;
	private int week;
	
	public Match(String teamOne, String teamTwo, int week) {
		this.teamOne = teamOne;
		this.teamTwo = teamTwo;
		this.week = week;
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
