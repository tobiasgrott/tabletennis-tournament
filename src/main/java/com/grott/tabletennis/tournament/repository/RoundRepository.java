package com.grott.tabletennis.tournament.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grott.tabletennis.tournament.model.Round;

public interface RoundRepository extends JpaRepository<Round, Long> {
	/**
	 * Find the current Round
	 * 
	 * @return
	 */
	public Round findByValidTrue();

	/**
	 * Find the round by a given number
	 * 
	 * @param number
	 * @return
	 */
	public List<Round> findByNumber(Integer number);
}
