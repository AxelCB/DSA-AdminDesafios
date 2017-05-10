package ar.edu.unlp.dsa.controller;

import java.util.Collection;

import ar.edu.unlp.dsa.repository.PlayerRepository;
import ar.edu.unlp.dsa.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.edu.unlp.dsa.Application;
import ar.edu.unlp.dsa.exception.ConfigurationNotFoundException;
import ar.edu.unlp.dsa.model.Configuration;
import ar.edu.unlp.dsa.repository.ConfigurationRepository;

@RestController
@RequestMapping(Application.API_PREFIX + "/configurations")
public class ConfigurationRestController {
	private final ConfigurationRepository configurationRepository;
	private final PlayerRepository playerRepository;

	@Autowired
	ConfigurationRestController(ConfigurationRepository configurationRepository, PlayerRepository playerRepository) {
		this.configurationRepository = configurationRepository;
		this.playerRepository = playerRepository;
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
		playerRepository.deleteAll();
		//TODO: call Users Module for new teams
		//TODO: save new teams and users
		this.getConfigurationRepository().save(configuration);
		return new ResponseEntity<>(null, null, HttpStatus.NO_CONTENT);
	}

	public ConfigurationRepository getConfigurationRepository() {
		return configurationRepository;
	}
}
