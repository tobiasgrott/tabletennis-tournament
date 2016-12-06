package com.grott.tabletennis.tournament.rest;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.grott.tabletennis.tournament.model.Player;
import com.grott.tabletennis.tournament.repository.PlayerRepository;

@RestController
@RequestMapping("/players")
public class PlayerRestController {
	@Autowired
	private PlayerRepository playerRepo;

	/**
	 * Creates a new Player
	 * 
	 * @param input
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Player> add(@RequestBody Player input) {
		Player retval = playerRepo.save(input);
		return new ResponseEntity<>(retval, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.GET)
	public Collection<Player> getAll() {
		return playerRepo.findAll();
	}

	/**
	 * Finds a player by id
	 * 
	 * @param id
	 * @eturn
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Player getOne(@PathVariable Long id) {
		return playerRepo.findOne(id);
	}

	/**
	 * Updates the data of a player
	 * 
	 * @param id
	 * @param input
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Player updatePlayer(@PathVariable Long id, @RequestBody Player input) {
		Player p = playerRepo.findOne(input.getId());
		p.setFirstname(input.getFirstname());
		p.setLastname(input.getLastname());
		p.setTeam(input.getTeam());
		p.setTtr(input.getTtr());
		p = playerRepo.save(p);
		return p;
	}

	/**
	 * removes a player from the tournament
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Boolean deletePlayer(@PathVariable Long id) {
		playerRepo.delete(id);
		return true;
	}
}
