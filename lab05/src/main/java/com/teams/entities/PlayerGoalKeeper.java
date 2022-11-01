package com.teams.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity(name="PlayerGoalKeeper")
@DiscriminatorValue("goal keeper")
public class PlayerGoalKeeper extends Player {
	
	@Column(name="player_position")
	private String position;
	
	@Column(name="player_goals_defended")
	private int goalsDefended;

	public int getGoalsDefended() {
		return goalsDefended;
	}

	public void setPosition(int goalsDefended) {
		this.goalsDefended = goalsDefended;
	}
}
