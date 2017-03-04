package ar.edu.unlp.dsa.controller;

import java.util.Collection;
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

	@Autowired
	ConfigurationRestController(ConfigurationRepository configurationRepository) {
		this.configurationRepository = configurationRepository;
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

	public ConfigurationRepository getConfigurationRepository() {
		return configurationRepository;
	}
}
