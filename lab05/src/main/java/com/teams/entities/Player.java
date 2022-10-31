package com.teams.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Entity
@Table(name="player")
public class Player implements Serializable {

	@Id
	@Column(name="player_id")
	private String id;

	@Column(name="player_name")
	private String name;
	
	@Column(name="player_age")
	private int age;
	
	@ManyToOne
	@JoinColumn(name="player_team", nullable=false)
	private Team team;
	
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
   
}
