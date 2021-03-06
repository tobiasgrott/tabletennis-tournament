package com.grott.tabletennis.tournament.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grott.tabletennis.tournament.model.Round;

public interface RoundRepository extends JpaRepository<Round, Long> {
	/**
	 * Find the current Round
	 * 
	 * @return
	 */
	Round findByValidTrue();

	/**
	 * Find the round by a given number
	 * 
	 * @param number
	 * @return
	 */
	Round findByNumber(Integer number);
}
