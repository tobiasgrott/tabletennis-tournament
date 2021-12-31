package com.grott.tabletennis.tournament.rest;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	@PostMapping
	public ResponseEntity<Player> add(@RequestBody Player input) {
		Player retval = playerRepo.save(input);
		return new ResponseEntity<>(retval, HttpStatus.CREATED);
	}

	@GetMapping
	public Collection<Player> getAll() {
		return playerRepo.findAll();
	}

	/**
	 * Finds a player by id
	 * 
	 * @param id
	 * @eturn
	 */
	@GetMapping(value = "/{id}")
	public Player getOne(@PathVariable Long id) {
		return playerRepo.getById(id);
	}

	/**
	 * Updates the data of a player
	 * 
	 * @param id
	 * @param input
	 * @return
	 */
	@PutMapping(value = "/{id}")
	public Player updatePlayer(@PathVariable Long id, @RequestBody Player input) {
		Player p = playerRepo.getById(input.getId());
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
	@DeleteMapping(value = "/{id}")
	public Boolean deletePlayer(@PathVariable Long id) {
		playerRepo.deleteById(id);
		return true;
	}
}
