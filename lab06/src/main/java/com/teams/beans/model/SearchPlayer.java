package com.teams.beans.model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.teams.entities.Player;
import com.teams.repository.PlayerRepository;

@Named("searchPlayer")
@ViewScoped
public class SearchPlayer implements Serializable {
	private static final long serialVersionUID = 1L;
	private ArrayList<Player> players;
	private boolean showName;
	private boolean showAgeInterval;
	private boolean showTeam;
	private String name = "";
	private int minimumAge = 0;
	private int maximumAge = 0;
	
	@Inject
	PlayerRepository pp;
	
	public void searchPlayer() {
		setPlayers(pp.findWithCriteria(name, minimumAge, maximumAge));
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}
	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	public boolean isShowName() {
		return showName;
	}
	public void setShowName(boolean showName) {
		this.showName = showName;
	}
	public boolean isShowAgeInterval() {
		return showAgeInterval;
	}
	public void setShowAgeInterval(boolean showAgeInterval) {
		this.showAgeInterval = showAgeInterval;
	}
	public boolean isShowTeam() {
		return showTeam;
	}
	public void setShowTeam(boolean showTeam) {
		this.showTeam = showTeam;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMinimumAge() {
		return minimumAge;
	}
	public void setMinimumAge(int minimumAge) {
		this.minimumAge = minimumAge;
	}
	public int getMaximumAge() {
		return maximumAge;
	}
	public void setMaximumAge(int maximumAge) {
		this.maximumAge = maximumAge;
	}
}
