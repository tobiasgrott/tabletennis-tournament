package com.grott.tabletennis.tournament.rest;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.grott.tabletennis.tournament.repository.GameRepository;
import com.grott.tabletennis.tournament.repository.PlayerRepository;
import com.grott.tabletennis.tournament.repository.RoundRepository;

@RestController
@RequestMapping("/batch")
public class BatchRestContoller {
	@Autowired
	private PlayerRepository playerRepo;
	@Autowired
	private GameRepository gameRepo;
	@Autowired
	private RoundRepository roundRepo;
	
	/**
	 * Clear all Tournament data
	 * @return
	 */
	@Transactional
	@RequestMapping(value="/reset",method = RequestMethod.POST)
	public String resetData(){
		gameRepo.deleteAllInBatch();
		roundRepo.deleteAllInBatch();
		playerRepo.deleteAllInBatch();
		return "resetted";
	}
}
