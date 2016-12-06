package com.grott.tabletennis.tournament.rest.dto;

import java.util.List;

import com.grott.tabletennis.tournament.service.DtoFormattedGame;

public class DtoCrosstable {
	private String name;
	private List<DtoFormattedGame> results;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<DtoFormattedGame> getResults() {
		return results;
	}
	public void setResults(List<DtoFormattedGame> results) {
		this.results = results;
	}
}
