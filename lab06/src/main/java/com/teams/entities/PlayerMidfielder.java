package com.teams.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity(name="PlayerMidfielder")
@DiscriminatorValue("midfielder")
public class PlayerMidfielder extends Player {
	
	@Column(name="player_position")
	private String position;
	
}