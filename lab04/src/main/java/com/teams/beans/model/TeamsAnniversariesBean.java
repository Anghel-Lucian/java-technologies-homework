package com.teams.beans.model;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.teams.entities.Team;
import com.teams.utils.DBManager;
import com.teams.utils.DataTableColumn;

// used by active.xhtml composite component. Fetches teams and adds them to activeTeams field
// if the current team's anniversary is today
@Named("activeTeams")
@RequestScoped
public class TeamsAnniversariesBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private ArrayList<Team> activeTeams = new ArrayList<Team>();
	private DataTableColumn[] columns = {
		new DataTableColumn("name", "Name"), 
		new DataTableColumn("foundingDate", "Founding Date"), 
		new DataTableColumn("cityName", "City")
	};
	
	public void updateActiveTeams() throws ClassNotFoundException, SQLException, ParseException {
		DBManager dbm = new DBManager();
		ArrayList<Team> teams = dbm.getTeams();
		
		for(int i = 0; i < teams.size(); i++) {
			String teamFoundingDate = teams.get(i).getFoundingDate();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String currentDate = LocalDate.now().format(formatter);
			
			String[] teamFoundingDateSplit = teamFoundingDate.split("-");
			String[] currentDateSplit = currentDate.split("-");
			
			if (teamFoundingDateSplit[1].equals(currentDateSplit[1]) &&
					teamFoundingDateSplit[2].equals(currentDateSplit[2])) {
				activeTeams.add(teams.get(i));
			}
		
		}
	}
	
	public ArrayList<Team> getActiveTeams() {
		return activeTeams;
	}
	
	public void setActiveTeams(ArrayList<Team> activeTeams) {
		this.activeTeams = activeTeams;
	}

	public DataTableColumn[] getColumns() {
		return columns;
	}

	public void setColumns(DataTableColumn[] columns) {
		this.columns = columns;
	}
}