package ar.edu.unlp.dsa.controller;

import java.util.ArrayList;
import java.util.Collection;

import ar.edu.unlp.dsa.dto.EquipoDTO;
import ar.edu.unlp.dsa.dto.TeamListDTO;
import ar.edu.unlp.dsa.dto.UsuarioDTO;
import ar.edu.unlp.dsa.model.Player;
import ar.edu.unlp.dsa.model.Team;
import ar.edu.unlp.dsa.repository.PlayerRepository;
import ar.edu.unlp.dsa.repository.TeamRepository;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.edu.unlp.dsa.Application;
import ar.edu.unlp.dsa.exception.ConfigurationNotFoundException;
import ar.edu.unlp.dsa.model.Configuration;
import ar.edu.unlp.dsa.repository.ConfigurationRepository;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(Application.API_PREFIX + "/configurations")
public class ConfigurationRestController {
	private final ConfigurationRepository configurationRepository;
	private final PlayerRepository playerRepository;
	private final TeamRepository teamRepository;
	private final Mapper mapper;

	@Autowired
	ConfigurationRestController(ConfigurationRepository configurationRepository, PlayerRepository playerRepository, TeamRepository teamRepository, Mapper mapper) {
		this.configurationRepository = configurationRepository;
		this.playerRepository = playerRepository;
		this.teamRepository = teamRepository;
		this.mapper = mapper;
	}

	@CrossOrigin(origins = "http://localhost:"+Application.FRONTEND_PORT)
	@RequestMapping(value = "/{configurationId}", method = RequestMethod.GET)
	public Configuration getConfiguration(@PathVariable Long configurationId) {
		Configuration configuration = this.getConfigurationRepository().findOne(configurationId);
		if (configuration == null) {
			throw new ConfigurationNotFoundException(configurationId);
		}
		return configuration;
	}

	@CrossOrigin(origins = "http://localhost:"+Application.FRONTEND_PORT)
	@RequestMapping(method = RequestMethod.GET)
	public Collection<Configuration> listConfigurations() {
		return this.getConfigurationRepository().findAll();
	}

	@CrossOrigin(origins = "http://localhost:"+Application.FRONTEND_PORT)
	@RequestMapping(value = "/{configurationId}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateConfiguration(@RequestBody Configuration input, @PathVariable Long configurationId) {
		Configuration configuration = this.getConfigurationRepository().findOne(configurationId);
		if (configuration == null) {
			throw new ConfigurationNotFoundException(configurationId);
		}
		configuration.setValue(input.getValue());
		this.getConfigurationRepository().save(configuration);
		return new ResponseEntity<>(null, null, HttpStatus.NO_CONTENT);
	}

	@CrossOrigin(origins = "http://localhost:"+Application.CONTROL_PORT)
	@RequestMapping(value = "/game/{gameId}", method = RequestMethod.POST)
	public ResponseEntity<?> newGame(@PathVariable String gameId) {
		Configuration configuration = this.getConfigurationRepository().findByName("id_juego");
		if (configuration == null) {
			throw new ConfigurationNotFoundException(0L);
		}
		configuration.setValue(gameId);
		this.getPlayerRepository().deleteAll();
		RestTemplate restTemplate = new RestTemplate();
		TeamListDTO teams = restTemplate.getForObject("http://localhost/equipos.json", TeamListDTO.class);
		ArrayList<EquipoDTO> equipos = teams.getEquipos();
		for (EquipoDTO equipo: equipos) {
			Team team = this.getMapper().map(equipo, Team.class);
			team = this.getTeamRepository().save(team);
			for (UsuarioDTO usuario : equipo.getUsuarios()) {
				Player player = this.getMapper().map(usuario, Player.class);
				player.setTeam(team);
				this.getPlayerRepository().save(player);
			}
		}
		//fixme: as sequences don't reset with deletion, I can't match the team with its players by id.
		//should reset the sequences?
		this.getConfigurationRepository().save(configuration);
		return new ResponseEntity<>(null, null, HttpStatus.NO_CONTENT);
	}

	public ConfigurationRepository getConfigurationRepository() {
		return configurationRepository;
	}

	public PlayerRepository getPlayerRepository() {
		return playerRepository;
	}

	public TeamRepository getTeamRepository() {
		return teamRepository;
	}

	public Mapper getMapper() {
		return mapper;
	}
}
