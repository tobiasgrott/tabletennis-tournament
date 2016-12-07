package com.grott.tabletennis.tournament.rest;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.grott.tabletennis.tournament.model.Game;
import com.grott.tabletennis.tournament.repository.GameRepository;
import com.grott.tabletennis.tournament.rest.dto.DtoGameIn;

@RestController
@RequestMapping("/games")
public class GameRestController {
	@Autowired
	private GameRepository gameRepo;

	/**
	 * Stores the game result
	 * 
	 * @param id
	 * @param dtoGameIn
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@Transactional
	public Game setResult(@PathVariable Long id, @RequestBody DtoGameIn gameIn) {
		Game g = gameRepo.findOne(id);
		g.setSets1(gameIn.getSets1());
		g.setSets2(gameIn.getSets2());
		g = gameRepo.save(g);
		return g;
	}
}
