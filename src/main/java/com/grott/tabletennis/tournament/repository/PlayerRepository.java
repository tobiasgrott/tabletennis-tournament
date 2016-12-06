package com.grott.tabletennis.tournament.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grott.tabletennis.tournament.model.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {

}
