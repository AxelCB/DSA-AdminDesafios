package ar.edu.unlp.dsa.controller;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.unlp.dsa.Application;
import ar.edu.unlp.dsa.exception.PlayerNotFoundException;
import ar.edu.unlp.dsa.model.Challenge;
import ar.edu.unlp.dsa.model.Player;
import ar.edu.unlp.dsa.model.SolvedChallenge;
import ar.edu.unlp.dsa.repository.ChallengeRepository;
import ar.edu.unlp.dsa.repository.PlayerRepository;

@RestController
@RequestMapping(Application.API_PREFIX + "/players")
public class PlayerRestController {
	private final ChallengeRepository challengeRepository;
	private final PlayerRepository playerRepository;

	@Autowired
	PlayerRestController(ChallengeRepository challengeRepository, PlayerRepository playerRepository) {
		this.challengeRepository = challengeRepository;
		this.playerRepository = playerRepository;
	}

	public ChallengeRepository getChallengeRepository() {
		return challengeRepository;
	}

	public PlayerRepository getPlayerRepository() {
		return playerRepository;
	}

	@RequestMapping(value = "/{playerId}/challenges", method = RequestMethod.GET)
	public Collection<Challenge> getAvailableChallenges(@PathVariable Long playerId) {
		Player player = this.getPlayerRepository().findOne(playerId);
		if (player == null) {
			throw new PlayerNotFoundException(playerId);
		}
		//TODO build response JSON
		Collection<SolvedChallenge> solvedChallenges = player.getTeam().getSolvedChallenges();
		if (solvedChallenges.size() == 0) {
			return this.getChallengeRepository().findAll();
		} else {
			Collection<Long> challengeIds = solvedChallenges.stream()
					.map(solvedChallenge -> solvedChallenge.getChallenge().getId())
					.collect(Collectors.toList());
			return this.getChallengeRepository().findByIdNotIn(challengeIds);
		}
	}
}
