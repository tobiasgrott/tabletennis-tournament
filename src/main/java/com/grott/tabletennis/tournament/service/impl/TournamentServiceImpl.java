package com.grott.tabletennis.tournament.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grott.tabletennis.tournament.model.Game;
import com.grott.tabletennis.tournament.model.Player;
import com.grott.tabletennis.tournament.model.Round;
import com.grott.tabletennis.tournament.repository.GameRepository;
import com.grott.tabletennis.tournament.repository.PlayerRepository;
import com.grott.tabletennis.tournament.repository.RoundRepository;
import com.grott.tabletennis.tournament.service.DtoFormattedGame;
import com.grott.tabletennis.tournament.service.DtoTtrChange;
import com.grott.tabletennis.tournament.service.GameType;
import com.grott.tabletennis.tournament.service.SortType;
import com.grott.tabletennis.tournament.service.TournamentServiceIF;

@Service
public class TournamentServiceImpl implements TournamentServiceIF {

	@Autowired
	private GameRepository gameRepo;

	@Autowired
	private PlayerRepository playerRepo;

	@Autowired
	private RoundRepository roundRepo;

	/**
	 * Finds a game based on both players. Game is returned regardless if its a
	 * home or guest game
	 * 
	 * @param player1Id
	 * @param player2Id
	 * @return
	 */
	@Override
	public Optional<Game> getGame(Long player1Id, Long player2Id) {
		Optional<Game> g = gameRepo.findByPlayer1IdAndPlayer2Id(player1Id, player2Id);
		if (!g.isPresent()) {
			g = gameRepo.findByPlayer1IdAndPlayer2Id(player2Id, player1Id);
		}
		return g;
	}

	/**
	 * returns the formatted game and switches home and guest depending on the
	 * delivered player1 and player2
	 * 
	 * @param player1Id
	 * @param player2Id
	 * @return
	 */
	@Override
	public DtoFormattedGame formatGame(Long player1Id, Long player2Id) {
		if (Objects.equals(player1Id, player2Id)) {
			return DtoFormattedGame.selfGame();
		}
		Optional<Game> g = getGame(player1Id, player2Id);
		if (!g.isPresent()) {
			return DtoFormattedGame.emptyGame();
		} else {
			Game game = g.get();
			if (game.getSets1() == null && game.getSets2() == null) {
				return DtoFormattedGame.emptyGame();
			} else {
				return formatGame(player1Id, game);
			}
		}
	}

	private DtoFormattedGame formatGame(Long playerId, Game g) {
		if (Objects.equals(playerId, g.getPlayer1Id())) {
			return DtoFormattedGame.finishedGame(g.getSets1(), g.getSets2());
		} else {
			return DtoFormattedGame.finishedGame(g.getSets2(), g.getSets1());
		}
	}

	/**
	 * returns the number of games the passed in player has won
	 * 
	 * @param playerId
	 * @return
	 */
	@Override
	public int getWins(Long playerId) {
		int wins = 0;
		Player p = playerRepo.findOne(playerId);
		for (Game g : p.getHomeGames()) {
			if (g.getSets1() != null && g.getSets1() > g.getSets2()) {
				wins++;
			}
		}
		for (Game g : p.getGuestGames()) {
			if (g.getSets2() != null && g.getSets2() > g.getSets1()) {
				wins++;
			}
		}
		return wins;
	}

	/**
	 * Calculates the Buchholz Number for a given Player
	 * 
	 * @param playerId
	 * @return
	 */
	@Override
	public int getBuchholz(Long playerId) {
		int buchholz = 0;
		Player p = playerRepo.findOne(playerId);
		for (Game g : p.getHomeGames()) {
			buchholz += getWins(g.getPlayer2Id());
		}
		for (Game g : p.getGuestGames()) {
			buchholz += getWins(g.getPlayer1Id());
		}
		return buchholz;
	}

	/**
	 * Calculates the winning probability by two given TTR values
	 * 
	 * @param myttr
	 * @param oppTtr
	 * @return
	 */
	@Override
	public double calculateProbability(long myTtr, long oppTtr) {
		double zaehler = 1.0d;
		double ttrdiff = (double) oppTtr - myTtr;
		double ttrdiffFactor = ttrdiff / 150.0d;
		double divisor = zaehler + Math.pow(10.0d, ttrdiffFactor);
		return zaehler / divisor;
	}

	private DtoTtrChange getTtrChange(GameType type, List<Game> games) {
		DtoTtrChange retval = new DtoTtrChange();
		double probability = 0.0d;
		int wins = 0;
		for (Game g : games) {
			if (g.getPlayer1().getTtr() != null && g.getPlayer2().getTtr() != null && g.getSets1() != null
					&& g.getSets2() != null) {
				if (type == GameType.HOME) {
					probability += calculateProbability(g.getPlayer1().getTtr(), g.getPlayer2().getTtr());
				} else if (type == GameType.GUEST) {
					probability += calculateProbability(g.getPlayer2().getTtr(), g.getPlayer1().getTtr());
				}
				if (type == GameType.HOME && g.getSets1() > g.getSets2()) {
					wins++;
				} else if (type == GameType.GUEST && g.getSets2() > g.getSets1()) {
					wins++;
				}
			}
		}
		if (type == GameType.HOME) {
			retval.setProbability(probability);
			retval.setWins(wins);
		} else {
			retval.setWins(wins);
			retval.setProbability(probability);
		}
		return retval;
	}

	/**
	 * Calculates the complete ttr change of a given player
	 * 
	 * @param playerId
	 * @return
	 */
	@Override
	public int getTtrChange(Long playerId) {
		Player p = playerRepo.findOne(playerId);
		final int factor = 16;

		DtoTtrChange ttrChangeHome = getTtrChange(GameType.HOME, p.getHomeGames());
		DtoTtrChange ttrChangeGuest = getTtrChange(GameType.GUEST, p.getGuestGames());

		double probability = ttrChangeHome.getProbability() + ttrChangeGuest.getProbability();
		int wins = ttrChangeHome.getWins() + ttrChangeGuest.getWins();

		BigDecimal value = BigDecimal.valueOf(wins);
		value = value.subtract(BigDecimal.valueOf(probability)).multiply(BigDecimal.valueOf(factor)).setScale(0,RoundingMode.HALF_UP);
		return value.intValue();
	}

	/**
	 * Get the Ranking from the tournament by the sortType
	 * 
	 * @param sortType
	 * @return
	 */
	@Override
	public List<Player> getRanking(SortType sortType) {
		List<Player> l = playerRepo.findAll();
		if (SortType.WINS == sortType) {
			l.sort((p1, p2) -> {
				int wins = getWins(p2.getId()) - getWins(p1.getId());
				int games = p2.getGuestGames().size() - p1.getGuestGames().size() + p2.getHomeGames().size()
						- p1.getGuestGames().size();
				if (wins != 0) {
					return wins;
				} else if (games != 0) {
					return -games;
				} else {
					int buchholz = getBuchholz(p2.getId()) - getBuchholz(p1.getId());
					if (buchholz != 0) {
						return buchholz;
					} else {
						Random r = new Random();
						return r.nextInt(2);
					}
				}
			});
		} else if (SortType.TTR == sortType) {
			l.sort((p1, p2) -> getTtrChange(p2.getId()) - getTtrChange(p1.getId()));
		}
		return l;
	}

	@Override
	public int getOpenGames() {
		int retval = 0;
		Round round = roundRepo.findByValidTrue();
		if (round != null) {
			for (Game g : round.getGames()) {
				if (g.getSets1() == 0 && g.getSets2() == 0) {
					retval++;
				}
			}
		}
		return retval;
	}

	/**
	 * calculates the next round
	 * 
	 * @return
	 */
	@Override
	public List<Game> calculateNewRound() {
		List<Player> players = getRanking(SortType.WINS);
		List<Game> retval = new ArrayList<>();
		while (players.size() > 1) {
			boolean found = false;
			for (int i = 1; i < players.size(); i++) {
				Optional<Game> existingGame = gameRepo.findByPlayer1IdAndPlayer2Id(players.get(0).getId(),
						players.get(i).getId());
				Optional<Game> existingGame2 = gameRepo.findByPlayer1IdAndPlayer2Id(players.get(i).getId(),
						players.get(0).getId());
				if (!existingGame.isPresent() && !existingGame2.isPresent()) {
					Game g = new Game();
					g.setPlayer1(players.get(0));
					g.setPlayer1Id(players.get(0).getId());
					g.setPlayer2(players.get(i));
					g.setPlayer2Id(players.get(i).getId());
					g.setSets1(0);
					g.setSets2(0);
					retval.add(g);
					found = true;
					players.remove(players.get(i));
					players.remove(players.get(0));
					break;
				}
			}
			if (!found) {
				players.remove(players.get(0));
			}
		}
		int countGames = players.size() / 2;
		if (countGames > retval.size()) {
			return new ArrayList<>();
		} else {
			return retval;
		}
	}

	/**
	 * generates the next round
	 */
	@Transactional
	@Override
	public void generateNewRound() {
		List<Game> games = new ArrayList<>();
		if (this.getOpenGames() == 0) {
			games = calculateNewRound();
		}
		if (!games.isEmpty()) {
			Round r = roundRepo.findByValidTrue();
			Round newRound = new Round();
			newRound.setValid(true);
			newRound.setGames(new ArrayList<Game>());
			if (r != null) {
				r.setValid(false);
				roundRepo.save(r);
				newRound.setNumber(r.getNumber() + 1);
			} else {
				newRound.setNumber(1);
			}
			newRound = roundRepo.save(newRound);
			for (Game g : games) {
				g.setRound(newRound);
				g.setRoundId(newRound.getId());
				gameRepo.save(g);
				newRound.getGames().add(g);
			}
		}
	}
}
