package ar.edu.unlp.dsa.controller;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import ar.edu.unlp.dsa.dto.ChallengeDTO;
import ar.edu.unlp.dsa.dto.HintDTO;
import ar.edu.unlp.dsa.model.*;
import ar.edu.unlp.dsa.repository.ConfigurationRepository;
import ar.edu.unlp.dsa.utils.DozerUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections.map.HashedMap;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ar.edu.unlp.dsa.Application;
import ar.edu.unlp.dsa.exception.PlayerNotFoundException;
import ar.edu.unlp.dsa.repository.ChallengeRepository;
import ar.edu.unlp.dsa.repository.PlayerRepository;

@RestController
@RequestMapping(Application.API_PREFIX + "/players")
public class PlayerRestController {
	private final ChallengeRepository challengeRepository;
	private final PlayerRepository playerRepository;
	private final ConfigurationRepository configurationRepository;
	private final Mapper mapper;

	@Autowired
	PlayerRestController(ChallengeRepository challengeRepository, PlayerRepository playerRepository, ConfigurationRepository configurationRepository, Mapper mapper) {
		this.challengeRepository = challengeRepository;
		this.playerRepository = playerRepository;
		this.configurationRepository = configurationRepository;
		this.mapper = mapper;
	}

	public ChallengeRepository getChallengeRepository() {
		return challengeRepository;
	}

	public PlayerRepository getPlayerRepository() {
		return playerRepository;
	}

	@RequestMapping(value = "/{playerId}/challenges", method = RequestMethod.GET)
	public Map<String, Object> getAvailableChallenges(@PathVariable Long playerId) throws JsonProcessingException {
		Player player = this.getPlayerRepository().findOne(playerId);
		if (player == null) {
			throw new PlayerNotFoundException(playerId);
		}
		//TODO build response JSON
		Map<String, Object> result = new HashedMap();
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
		Collection<ChallengeDTO> desafios = prepareDesafio(challenges, player.getTeam());
		result.put("desafios", desafios);
		System.out.println(new ObjectMapper().writeValueAsString(result));
		return result;
	}

	private Collection<ChallengeDTO> prepareDesafio(Collection<Challenge> challenges, Team team) {
		Collection<ChallengeDTO> desafios = DozerUtils.map(this.mapper,new ArrayList<>(challenges),ChallengeDTO.class);
		for (ChallengeDTO desafio : desafios) {
			HintDTO hint1 = desafio.getHint1();
			if (hint1 != null) {
				Hint hint = new Hint();
				hint.setId(hint1.getId_hint());
				hint1.setDisponible(!team.getUsedHints().contains(hint));
			}
			HintDTO hint2 = desafio.getHint2();
			if (hint2 != null) {
				hint2.setDisponible(team.getUsedHints().contains(hint2));
			}
		}
		return desafios;
	}
}
