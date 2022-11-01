package com.teams.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity(name="PlayerForward")
@DiscriminatorValue("forward")
public class PlayerForward extends Player {
	
	@Column(name="player_position")
	private String position;
	
}