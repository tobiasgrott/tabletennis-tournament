package com.grott.tabletennis.tournament.rest.dto;

/**
 * Data transfer object which is neede to transfer a ranking entry
 * 
 */
public class DtoRanking {
	private String name;
	private int wins;
	private int buchholz;
	private Integer ttr;
	private Integer ttrChange;

	public Integer getTtrChange() {
		return ttrChange;
	}

	public void setTtrChange(Integer ttrChange) {
		this.ttrChange = ttrChange;
	}

	public Integer getTtr() {
		return ttr;
	}

	public void setTtr(Integer ttr) {
		this.ttr = ttr;
	}

	public int getBuchholz() {
		return buchholz;
	}

	public void setBuchholz(int buchholz) {
		this.buchholz = buchholz;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
