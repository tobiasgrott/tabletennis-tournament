

package com.grott.tabletennis.tournament.service;

import java.util.List;
import java.util.Optional;

import com.grott.tabletennis.tournament.model.Game;
import com.grott.tabletennis.tournament.model.Player;

public interface TournamentServiceIF {

	Optional<Game> getGame(Long player1Id, Long player2Id);

	DtoFormattedGame formatGame(Long player1Id, Long player2Id);

	int getWins(Long playerId);

	int getBuchholz(Long playerId);

	double calculateProbability(long myTtr, long oppTtr);

	int getTtrChange(Long playerId);

	List<Player> getRanking(SortType sortType);

	int getOpenGames();

	List<Game> calculateNewRound();

	void generateNewRound();

}
