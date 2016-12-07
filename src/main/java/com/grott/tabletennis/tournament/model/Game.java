package com.grott.tabletennis.tournament.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Game {

	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long id;

	@Column(name = "sets1")
	private Integer sets1;

	@Column(name = "sets2")
	private Integer sets2;

	@ManyToOne
	@JoinColumn(name = "player1Id", insertable = false, updatable = false)
	private Player player1;

	@Column(name = "player1Id")
	private Long player1Id;

	@ManyToOne
	@JoinColumn(name = "player2Id", insertable = false, updatable = false)
	private Player player2;

	@Column(name = "player2Id")
	private Long player2Id;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "roundId", insertable = false, updatable = false)
	private Round round;

	@Column(name = "roundId")
	private Long roundId;

	public Long getRoundId() {
		return roundId;
	}

	public void setRoundId(Long roundId) {
		this.roundId = roundId;
	}

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

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Long getPlayer1Id() {
		return player1Id;
	}

	public void setPlayer1Id(Long player1Id) {
		this.player1Id = player1Id;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	public Long getPlayer2Id() {
		return player2Id;
	}

	public void setPlayer2Id(Long player2Id) {
		this.player2Id = player2Id;
	}

	public Round getRound() {
		return round;
	}

	public void setRound(Round round) {
		this.round = round;
	}

}
