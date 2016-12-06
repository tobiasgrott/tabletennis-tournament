package com.grott.tabletennis.tournament.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.grott.tabletennis.tournament.model.Player;
import com.grott.tabletennis.tournament.rest.dto.DtoCrosstable;
import com.grott.tabletennis.tournament.rest.dto.DtoRanking;
import com.grott.tabletennis.tournament.service.SortType;
import com.grott.tabletennis.tournament.service.TournamentServiceIF;

@RestController
@RequestMapping("/rankings")
public class RankingRestController {
	@Autowired
	private TournamentServiceIF tournamentService;

	@RequestMapping(value = "/ttr", method = RequestMethod.GET)
	public List<DtoRanking> getRankingTtr() {
		return getRanking(SortType.TTR);
	}

	@RequestMapping(value = "/wins", method = RequestMethod.GET)
	public List<DtoRanking> getRankingWins() {
		return getRanking(SortType.WINS);
	}

	@RequestMapping(value = "/crossTable", method = RequestMethod.GET)
	public List<DtoCrosstable> getCrossTable() {
		List<Player> l = tournamentService.getRanking(SortType.WINS);
		List<DtoCrosstable> lct = new ArrayList<>();
		for (Player p : l) {
			DtoCrosstable ct = new DtoCrosstable();
			ct.setName(p.getFirstname());
			ct.setResults(new ArrayList<>());
			for (Player p2 : l) {
				ct.getResults().add(tournamentService.formatGame(p.getId(), p2.getId()));
			}
			lct.add(ct);
		}
		return lct;
	}

	private DtoRanking getRankingFromPlayer(Player p) {
		DtoRanking dto = new DtoRanking();
		dto.setName(p.getFirstname() + " " + p.getLastname());
		dto.setWins(tournamentService.getWins(p.getId()));
		dto.setBuchholz(tournamentService.getBuchholz(p.getId()));
		dto.setTtr(p.getTtr());
		dto.setTtrChange(tournamentService.getTtrChange(p.getId()));
		return dto;
	}

	private List<DtoRanking> getRanking(SortType type) {
		List<Player> l = tournamentService.getRanking(type);
		List<DtoRanking> retval = new ArrayList<>();
		for (Player p : l) {
			retval.add(getRankingFromPlayer(p));
		}
		return retval;

	}

}
