package com.grott.tabletennis.tournament.rest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.grott.tabletennis.tournament.model.Game;
import com.grott.tabletennis.tournament.model.Player;
import com.grott.tabletennis.tournament.rest.dto.DtoGame;
import com.grott.tabletennis.tournament.rest.dto.DtoPlayer;
import com.grott.tabletennis.tournament.service.SortType;
import com.grott.tabletennis.tournament.service.TournamentServiceIF;

@RestController
@RequestMapping("/ttr-overview")
public class TtrRestController {
	@Autowired
	private TournamentServiceIF tournamentService;

	@Transactional
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<DtoPlayer> getOverviewPlayer() {
		List<Player> l = tournamentService.getRanking(SortType.TTR);
		List<DtoPlayer> retval = new ArrayList<>();
		for (Player p : l) {
			DtoPlayer dtoPlayer = new DtoPlayer();
			dtoPlayer.setName(p.getFirstname() + " " + p.getLastname());
			dtoPlayer.setTtr(p.getTtr());
			dtoPlayer.setGames(new ArrayList<>());
			List<Game> games = p.getHomeGames();
			for (Game g : games) {
				DtoGame dto = new DtoGame();
				dto.setOpponent(g.getPlayer2().getFirstname() + " " + g.getPlayer2().getLastname());
				dto.setTtr(g.getPlayer2().getTtr());
				if (p.getTtr() != null && g.getPlayer2().getTtr() != null) {
					double probDouble = tournamentService.calculateProbability(p.getTtr(), g.getPlayer2().getTtr());
					BigDecimal prob = BigDecimal.valueOf(probDouble);
					dto.setProbability(prob.setScale(3, RoundingMode.HALF_UP));
					dto.setTtrChange(
							prob.multiply(BigDecimal.valueOf(16)).setScale(0, RoundingMode.HALF_UP).longValue());
				}
				dto.setWin(g.getSets1() > g.getSets2());
				dtoPlayer.getGames().add(dto);
			}
			games = p.getGuestGames();
			for (Game g : games) {
				DtoGame dto = new DtoGame();
				dto.setOpponent(g.getPlayer1().getFirstname() + " " + g.getPlayer1().getLastname());
				dto.setTtr(g.getPlayer1().getTtr());
				if (p.getTtr() != null && g.getPlayer1().getTtr() != null) {
					double probDouble = tournamentService.calculateProbability(p.getTtr(), g.getPlayer1().getTtr());
					BigDecimal prob = BigDecimal.valueOf(probDouble);
					dto.setProbability(prob.setScale(3, RoundingMode.HALF_UP));
					dto.setTtrChange(
							prob.multiply(BigDecimal.valueOf(16)).setScale(0, RoundingMode.HALF_UP).longValue());
				}
				dto.setWin(g.getSets2() > g.getSets1());
				dtoPlayer.getGames().add(dto);
			}
			retval.add(dtoPlayer);
		}
		return retval;
	}

}
