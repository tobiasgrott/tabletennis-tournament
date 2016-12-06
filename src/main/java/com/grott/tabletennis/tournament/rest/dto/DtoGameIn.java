package com.grott.tabletennis.tournament.rest.dto;

public class DtoGameIn {
	private Long id;
	private Integer sets1;
	private Integer sets2;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getSets1() {
		return sets1;
	}
	public void setSets1(Integer sets1) {
		this.sets1 = sets1;
	}
	public Integer getSets2() {
		return sets2;
	}
	public void setSets2(Integer sets2) {
		this.sets2 = sets2;
	}
}
