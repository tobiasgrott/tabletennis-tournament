package com.grott.tabletennis.tournament.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Player {
	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long id;

	@Column(name = "firstname")
	private String firstname;

	@Column(name = "lastname")
	private String lastname;

	@Column(name = "team")
	private String team;

	@OneToMany(mappedBy = "player1")
	private List<Game> homeGames;

	@OneToMany(mappedBy = "player2")
	private List<Game> guestGames;

	public List<Game> getGuestGames() {
		return guestGames;
	}

	public void setGuestGames(List<Game> guestGames) {
		this.guestGames = guestGames;
	}

	public List<Game> getHomeGames() {
		return homeGames;
	}

	public void setHomeGames(List<Game> homeGames) {
		this.homeGames = homeGames;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
