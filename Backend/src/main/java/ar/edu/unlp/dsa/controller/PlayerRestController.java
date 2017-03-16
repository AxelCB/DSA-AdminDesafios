package ar.edu.unlp.dsa.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import ar.edu.unlp.dsa.model.*;
import ar.edu.unlp.dsa.repository.ConfigurationRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import ar.edu.unlp.dsa.Application;
import ar.edu.unlp.dsa.exception.PlayerNotFoundException;
import ar.edu.unlp.dsa.jsonview.View;
import ar.edu.unlp.dsa.repository.ChallengeRepository;
import ar.edu.unlp.dsa.repository.PlayerRepository;

@RestController
@RequestMapping(Application.API_PREFIX + "/players")
public class PlayerRestController {
	private final ChallengeRepository challengeRepository;
	private final PlayerRepository playerRepository;
	private final ConfigurationRepository configurationRepository;

	@Autowired
	PlayerRestController(ChallengeRepository challengeRepository, PlayerRepository playerRepository, ConfigurationRepository configurationRepository) {
		this.challengeRepository = challengeRepository;
		this.playerRepository = playerRepository;
		this.configurationRepository = configurationRepository;
	}

	public ChallengeRepository getChallengeRepository() {
		return challengeRepository;
	}

	public PlayerRepository getPlayerRepository() {
		return playerRepository;
	}

	@RequestMapping(value = "/{playerId}/challenges", method = RequestMethod.GET)
	@JsonView(View.UserAdmin.class)
	public Map<String, Object> getAvailableChallenges(@PathVariable Long playerId) throws JsonProcessingException {
		Player player = this.getPlayerRepository().findOne(playerId);
		if (player == null) {
			throw new PlayerNotFoundException(playerId);
		}
		//TODO build response JSON
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("date", new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		result.put("id_juego", configurationRepository.findByName("id_juego").getValue());
		result.put("id_equipo", player.getTeam().getId());
		result.put("id_usuario", player.getId());
		Collection<SolvedChallenge> solvedChallenges = player.getTeam().getSolvedChallenges();
		Collection<Challenge> challenges;
		if (solvedChallenges.size() == 0) {
			challenges = this.getChallengeRepository().findAll();
		} else {
			Collection<Long> challengeIds = solvedChallenges.stream()
					.map(solvedChallenge -> solvedChallenge.getChallenge().getId())
					.collect(Collectors.toList());
			challenges = this.getChallengeRepository().findByIdNotIn(challengeIds);
		}
		Collection<Desafio> desafios = prepareDesafio(challenges, player.getTeam());
		result.put("desafios", desafios);
		System.out.println(new ObjectMapper().writeValueAsString(result));
		return result;
	}

	private Collection<Desafio> prepareDesafio(Collection<Challenge> challenges, Team team) {
		Collection<Desafio> result = new ArrayList<Desafio>();
		for (Challenge challenge : challenges) {
			Hint hint1 = challenge.getHint1();
			Pista pista1 = null;
			if (hint1 != null) {
				pista1 = new Pista(hint1, team.getUsedHints().contains(hint1));
			}
			Hint hint2 = challenge.getHint2();
			Pista pista2 = null;
			if (hint2 != null) {
				pista2 = new Pista(hint2, team.getUsedHints().contains(hint2));
			}
			result.add(new Desafio(challenge, pista1, pista2));
		}
		return result;
	}
}
