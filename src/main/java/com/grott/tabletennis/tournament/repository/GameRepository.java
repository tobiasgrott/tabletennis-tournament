package com.grott.tabletennis.tournament.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grott.tabletennis.tournament.model.Game;

public interface GameRepository extends JpaRepository<Game, Long> {
	/**
	 * Returns a game by two given players
	 * 
	 * @param player1Id
	 * @param player2Id
	 * @return
	 */
	Optional<Game> findByPlayer1IdAndPlayer2Id(Long player1Id, Long player2Id);
}
