package com.grott.tabletennis.tournament.rest.dto;

/**
 * Data transfer object for current status about the actual round
 * 
 * @author Tobias
 *
 */
public class DtoRoundCreation {

	private Boolean openGames;
	private Boolean additionalRound;
	private int maxRound;

	public int getMaxRound() {
		return maxRound;
	}

	public void setMaxRound(int maxRound) {
		this.maxRound = maxRound;
	}

	public Boolean getAdditionalRound() {
		return additionalRound;
	}

	public void setAdditionalRound(Boolean additionalRound) {
		this.additionalRound = additionalRound;
	}

	public Boolean getOpenGames() {
		return openGames;
	}

	public void setOpenGames(Boolean openGames) {
		this.openGames = openGames;
	}
}
