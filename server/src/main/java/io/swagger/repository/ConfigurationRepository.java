package io.swagger.repository;

import io.swagger.model.Configuration;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ConfigurationRepository {
	@PersistenceContext
	private EntityManager em;

	@Transactional
	public Configuration saveConfiguration(Configuration config) {
		return em.merge(config);
	}

	public Configuration updateConfiguration(Configuration config) throws RuntimeException {
		Configuration existingConfig = em.find(Configuration.class, config.getId());
		if(existingConfig==null) {
			throw new RuntimeException("Configuration to update was not found");
		} else {
			return saveConfiguration(config);
		}
	}
}
