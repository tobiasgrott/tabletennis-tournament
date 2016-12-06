package com.grott.tabletennis.tournament.rest.dto;

import java.math.BigDecimal;

/**
 * Data Transfer Object which stores the ttr information about a game
 *
 */
public class DtoGame {
	private String opponent;
	private Integer ttr;
	private BigDecimal probability;
	private Boolean win;
	private Long ttrChange;

	public String getOpponent() {
		return opponent;
	}

	public void setOpponent(String opponent) {
		this.opponent = opponent;
	}

	public Integer getTtr() {
		return ttr;
	}

	public void setTtr(Integer ttr) {
		this.ttr = ttr;
	}

	public BigDecimal getProbability() {
		return probability;
	}

	public void setProbability(BigDecimal probability) {
		this.probability = probability;
	}

	public Boolean getWin() {
		return win;
	}

	public void setWin(Boolean win) {
		this.win = win;
	}

	public Long getTtrChange() {
		return ttrChange;
	}

	public void setTtrChange(Long ttrChange) {
		this.ttrChange = ttrChange;
	}
}
