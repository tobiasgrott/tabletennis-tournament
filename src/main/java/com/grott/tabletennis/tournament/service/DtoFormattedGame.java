package com.grott.tabletennis.tournament.service;

public class DtoFormattedGame {
	private String style;
	private String text;

	/**
	 * Standardconstructor
	 */
	public DtoFormattedGame() {
		this.setStyle("");
		this.setText("");
	}

	/**
	 * constructs a Dto with all values
	 * 
	 * @param style
	 * @param text
	 */
	public DtoFormattedGame(String style, String text) {
		this.setStyle(style);
		this.setText(text);
	}

	/**
	 * creates a game where both players are the same
	 * 
	 * @return
	 */
	public static DtoFormattedGame selfGame() {
		return new DtoFormattedGame("own", "XXX");
	}

	/**
	 * creates an empty game
	 * 
	 * @return
	 */
	public static DtoFormattedGame emptyGame() {
		return new DtoFormattedGame("empty", "");
	}

	/**
	 * Creates a running game
	 * 
	 * @return
	 */
	public static DtoFormattedGame runningGame() {
		return new DtoFormattedGame("running", "0:0");
	}

	/**
	 * Creates a finished game
	 * 
	 * @param sets1
	 * @param sets2
	 * @return
	 */
	public static DtoFormattedGame finishedGame(int sets1, int sets2) {
		if (sets1 > sets2) {
			return new DtoFormattedGame("winner", sets1 + ":" + sets2);
		}
		return new DtoFormattedGame("looser", sets2 + ":" + sets1);
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
