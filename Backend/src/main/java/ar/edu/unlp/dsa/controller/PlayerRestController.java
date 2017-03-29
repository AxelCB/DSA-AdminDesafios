package ar.edu.unlp.dsa.controller;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import ar.edu.unlp.dsa.dto.ChallengeDTO;
import ar.edu.unlp.dsa.dto.HintDTO;
import ar.edu.unlp.dsa.exception.ChallengeNotFoundException;
import ar.edu.unlp.dsa.model.*;
import ar.edu.unlp.dsa.repository.ConfigurationRepository;
import ar.edu.unlp.dsa.repository.SolvedChallengeRepository;
import ar.edu.unlp.dsa.utils.DozerUtils;
import org.apache.commons.collections.map.HashedMap;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ar.edu.unlp.dsa.Application;
import ar.edu.unlp.dsa.exception.PlayerNotFoundException;
import ar.edu.unlp.dsa.repository.ChallengeRepository;
import ar.edu.unlp.dsa.repository.PlayerRepository;

@RestController
@RequestMapping(Application.API_PREFIX + "/players")
public class PlayerRestController {
	private final ChallengeRepository challengeRepository;
	private final SolvedChallengeRepository solvedChallengeRepository;
	private final PlayerRepository playerRepository;
	private final ConfigurationRepository configurationRepository;
	private final Mapper mapper;

	@Autowired
	PlayerRestController(ChallengeRepository challengeRepository, PlayerRepository playerRepository, ConfigurationRepository configurationRepository, Mapper mapper, SolvedChallengeRepository solvedChallengeRepository) {
		this.challengeRepository = challengeRepository;
		this.solvedChallengeRepository = solvedChallengeRepository;
		this.playerRepository = playerRepository;
		this.configurationRepository = configurationRepository;
		this.mapper = mapper;
	}

	public ChallengeRepository getChallengeRepository() {
		return challengeRepository;
	}

	public SolvedChallengeRepository getSolvedChallengeRepository() {
		return solvedChallengeRepository;
	}

	public PlayerRepository getPlayerRepository() {
		return playerRepository;
	}

	public ConfigurationRepository getConfigurationRepository() {
		return configurationRepository;
	}

	public Mapper getMapper() {
		return mapper;
	}

	@CrossOrigin(origins = "http://localhost:"+Application.USER_ADMIN_PORT)
	@RequestMapping(value = "/{playerId}/challenges", method = RequestMethod.GET)
	public Map<String, Object> getAvailableChallenges(@PathVariable Long playerId) {
		Player player = this.getPlayerRepository().findOne(playerId);
		if (player == null) {
			throw new PlayerNotFoundException(playerId);
		}
		Map<String, Object> result = new HashedMap();
		result.put("date", new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		result.put("id_juego", this.getConfigurationRepository().findByName("id_juego").getValue());
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
		return result;
	}

	@CrossOrigin(origins = "http://localhost:"+Application.USER_ADMIN_PORT)
	@RequestMapping(value = "/{playerId}/challenges/{challengeId}", method = RequestMethod.GET)
	public Map<String, Object> getChallengeStatus(@PathVariable Long playerId, @PathVariable Long challengeId) {
		Player player = this.getPlayerRepository().findOne(playerId);
		if (player == null) {
			throw new PlayerNotFoundException(playerId);
		}
		Challenge challenge = this.getChallengeRepository().findOne(challengeId);
		if (challenge == null){
			throw new ChallengeNotFoundException(challengeId);
		}
		Map<String, Object> result = new HashedMap();
		result.put("date", new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		result.put("id_juego", this.getConfigurationRepository().findByName("id_juego").getValue());
		result.put("id_equipo", player.getTeam().getId());
		result.put("id_usuario", player.getId());
		result.put("id_desafio", challenge.getId());
		result.put("titulo", challenge.getTitle());
		result.put("categoria", challenge.getCategory().getName());
		result.put("puntos", challenge.getPoints());
		result.put("descripcion", challenge.getDescription());
		Collection<SolvedChallenge> solvedChallenges = player.getTeam().getSolvedChallenges();
		SolvedChallenge solved = null;
		for (SolvedChallenge solvedChallenge: solvedChallenges) {
			if(solvedChallenge.getChallenge().equals(challenge)){
				solved = solvedChallenge;
				break;
			}
		}
		if (solved != null) {
			result.put("estado","resuelto");
			result.put("quien_resolvio", solved.getSolver().getId());
			result.put("puntaje_obtenido",solved.getObtainedScore());
		} else {
			Hint hint1 = challenge.getHint1();
			if (hint1 != null){
				result.put("hint1", this.prepareHint(hint1, player.getTeam()));
			}
			Hint hint2 = challenge.getHint1();
			if (hint2 != null){
				result.put("hint2", this.prepareHint(hint2, player.getTeam()));
			}
			result.put("adjunto", challenge.getAttachedFileUrl());
			result.put("estado","pendiente");
		}
		return result;
	}

	@CrossOrigin(origins = "http://localhost:"+Application.USER_ADMIN_PORT)
	@RequestMapping(value = "/{playerId}/challenges/{challengeId}/{answer}", method = RequestMethod.POST)
	ResponseEntity<?> checkAnswer(@PathVariable Long playerId, @PathVariable Long challengeId, @PathVariable String answer) {
		Player player = this.getPlayerRepository().findOne(playerId);
		if (player == null) {
			throw new PlayerNotFoundException(playerId);
		}
		Challenge challenge = this.getChallengeRepository().findOne(challengeId);
		if (challenge == null){
			throw new ChallengeNotFoundException(challengeId);
		}
		HttpHeaders httpHeaders = new HttpHeaders();
		Map<String, Object> result = new HashedMap();
		result.put("date", new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		result.put("id_juego", this.getConfigurationRepository().findByName("id_juego").getValue());
		result.put("id_equipo", player.getTeam().getId());
		result.put("id_usuario", player.getId());
		result.put("id_desafio", challenge.getId());
		if(challenge.getValidAnswer().equals(answer)){
			Collection<SolvedChallenge> solvedChallenges = player.getTeam().getSolvedChallenges();
			SolvedChallenge solved = null;
			for (SolvedChallenge solvedChallenge: solvedChallenges) {
				if(solvedChallenge.getChallenge().equals(challenge)){
					solved = solvedChallenge;
					break;
				}
			}
			result.put("resultado", true);
			result.put("descripcion", challenge.getAnswerDescription());
			if (solved != null) {
				result.put("puntaje", solved.getObtainedScore());
				return new ResponseEntity<>(result, httpHeaders, HttpStatus.OK); //which code?
			} else {
				Long obtainedScore = challenge.getPoints();
				Hint hint1 = challenge.getHint1();
				if ((hint1 != null) && (player.getTeam().getUsedHints().contains(hint1))) {
					obtainedScore -= challenge.getPoints() * hint1.getPointsPercentageCost() / 100;
				}
				Hint hint2 = challenge.getHint2();
				if ((hint2 != null) && (player.getTeam().getUsedHints().contains(hint2))) {
					obtainedScore -= challenge.getPoints() * hint2.getPointsPercentageCost();
				}
				SolvedChallenge solvedChallenge = new SolvedChallenge();
				solvedChallenge.setChallenge(challenge);
				solvedChallenge.setObtainedScore(obtainedScore);
				solvedChallenge.setSolver(player);
				this.getSolvedChallengeRepository().save(solvedChallenge);
				player.getTeam().getSolvedChallenges().add(solvedChallenge);
				this.getPlayerRepository().save(player); //or maybe team repository?
				result.put("puntaje", obtainedScore);
				if (challenge.getNextChallenge() != null) {
					httpHeaders.add("Link",
							"<http://localhost:" + Application.BACKEND_PORT + "/player/" + playerId + "/challenges/"
									+ challenge.getNextChallenge().getId() + ">;rel=\"next\"");
				}
				return new ResponseEntity<>(result, httpHeaders, HttpStatus.CREATED); //which code?
			}
		} else {
			result.put("resultado", false);
			result.put("descripcion", "NOTOK");
			result.put("puntaje", 0);
			return new ResponseEntity<>(result, httpHeaders, HttpStatus.OK);
		}
	}

	private Collection<ChallengeDTO> prepareDesafio(Collection<Challenge> challenges, Team team) {
		Collection<ChallengeDTO> desafios = DozerUtils.map(this.getMapper(),new ArrayList<>(challenges),ChallengeDTO.class);
		for (ChallengeDTO desafio : desafios) {
			HintDTO hint1 = desafio.getHint1();
			if (hint1 != null) {
				Hint hint = new Hint();
				hint.setId(hint1.getId_hint());
				hint1.setDisponible(team.getUsedHints().contains(hint));
			}
			HintDTO hint2 = desafio.getHint2();
			if (hint2 != null) {
				Hint hint = new Hint();
				hint.setId(hint2.getId_hint());
				hint2.setDisponible(team.getUsedHints().contains(hint));
			}
		}
		return desafios;
	}

	private HintDTO prepareHint(Hint hint, Team team) {
		HintDTO hintDTO = new HintDTO();
		hintDTO.setId_hint(hint.getId());
		hintDTO.setPorcentaje(hint.getPointsPercentageCost());
		hintDTO.setDisponible(team.getUsedHints().contains(hint));
		return  hintDTO;
	}
}
