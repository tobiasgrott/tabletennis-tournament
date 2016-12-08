package com.grott.tabletennis.tournament.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Round {
	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long id;

	@OneToMany(mappedBy = "round")
	private List<Game> games;

	@Column(name = "valid")
	private Boolean valid;

	@Column(name = "number")
	private Integer number;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}
}
