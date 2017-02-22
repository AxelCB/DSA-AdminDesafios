package ar.edu.unlp.dsa.repository;

import ar.edu.unlp.dsa.model.Configuration;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigurationRepository extends JpaRepository<Configuration,Long> {
}
