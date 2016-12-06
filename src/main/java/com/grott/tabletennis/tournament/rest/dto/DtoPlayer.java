package com.grott.tabletennis.tournament.rest.dto;

import java.util.List;

/**
 * Data transfer object for ttr ranking entry
 *
 */
public class DtoPlayer {
	private String name;
	private List<DtoGame> games;
	private Integer ttr;

	public Integer getTtr() {
		return ttr;
	}

	public void setTtr(Integer ttr) {
		this.ttr = ttr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<DtoGame> getGames() {
		return games;
	}

	public void setGames(List<DtoGame> games) {
		this.games = games;
	}
}
