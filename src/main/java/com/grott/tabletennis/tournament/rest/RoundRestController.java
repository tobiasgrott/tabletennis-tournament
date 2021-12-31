package com.grott.tabletennis.tournament.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.grott.tabletennis.tournament.model.Round;
import com.grott.tabletennis.tournament.repository.RoundRepository;
import com.grott.tabletennis.tournament.rest.dto.DtoRoundCreation;
import com.grott.tabletennis.tournament.service.TournamentServiceIF;

@RestController
@RequestMapping("/rounds")
public class RoundRestController {
	@Autowired
	private RoundRepository roundRepo;
	@Autowired
	private TournamentServiceIF tournamentService;

	/**
	 * creates a new round if possible
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public Round createRound() {
		Round r = null;
		if (tournamentService.getOpenGames() == 0) {
			tournamentService.generateNewRound();
			r = roundRepo.findByValidTrue();
		}
		return r;
	}

	/**
	 * getting metainformation if creation of a new round is possible
	 * 
	 * @return
	 */
	@RequestMapping(value = "/checkNewRound", method = RequestMethod.GET)
	public DtoRoundCreation checkNewRound() {
		DtoRoundCreation dto = new DtoRoundCreation();
		dto.setOpenGames(false);
		dto.setAdditionalRound(false);
		dto.setMaxRound(0);
		if (tournamentService.getOpenGames() > 0) {
			dto.setOpenGames(true);
		} else if (!tournamentService.calculateNewRound().isEmpty()) {
			dto.setAdditionalRound(true);
		}
		List<Round> lr = roundRepo.findAll(Sort.by(Direction.ASC, "id"));
		dto.setMaxRound(lr.size());
		return dto;
	}

	/**
	 * Rest Resource to get a specific round by number
	 * 
	 * @param nr
	 * @return
	 */
	@RequestMapping(value = "/{nr}", method = RequestMethod.GET)
	public Round getRound(@PathVariable Integer nr) {
		return roundRepo.findByNumber(nr);
	}

	/**
	 * Rest Resource to get the current round
	 * 
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public Round getCurrentRound() {
		return roundRepo.findByValidTrue();
	}
}
