package com.grott.tabletennis.tournament.service;

public class DtoTtrChange {
	private int wins;
	private double probability;

	public double getProbability() {
		return probability;
	}

	public void setProbability(double probability) {
		this.probability = probability;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}
}
