package com.teams.beans.model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.teams.entities.Player;
import com.teams.repository.PlayerRepository;
import com.teams.utils.DataTableColumn;

@Named("searchPlayer")
@ViewScoped
public class SearchPlayer implements Serializable {
	// TODO: find a way to render the team's name
	// TODO: implement all the criterias in pp.findWithCriteria
	// TODO: add inheritance mapping to the app
	private static final long serialVersionUID = 1L;
	private DataTableColumn[] columns = {
			new DataTableColumn("name", "Name"),
			new DataTableColumn("age", "Age"),
			new DataTableColumn("team", "Team")
		};
	private ArrayList<Player> players;
	private boolean showName;
	private boolean showAgeInterval;
	private boolean showTeam;
	private String name = "";
	private int minimumAge = 0;
	private int maximumAge = 0;
	private String team = "";
	
	@Inject
	PlayerRepository pp;
	
	public void searchPlayer() {
		setPlayers(pp.findWithCriteria(name, minimumAge, maximumAge, team));
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
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}

	public DataTableColumn[] getColumns() {
		return columns;
	}

	public void setColumns(DataTableColumn[] columns) {
		this.columns = columns;
	}
}
