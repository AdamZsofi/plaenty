package plaentyapp.repository;

import plaentyapp.model.configuration.ConfigurationNotFoundException;
import plaentyapp.model.configuration.Configuration;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ConfigurationRepository {
	@PersistenceContext
	private EntityManager em;

	@Transactional
	public Configuration saveConfiguration(Configuration config) {
		return em.merge(config);
	}

	@Transactional
	public Configuration updateConfiguration(Configuration config) throws ConfigurationNotFoundException {
		Configuration existingConfig = em.find(Configuration.class, config.getId());
		if(existingConfig==null) {
			throw new ConfigurationNotFoundException("Configuration to update was not found");
		} else {
			return saveConfiguration(config);
		}
	}

	@Transactional
	public Configuration getConfiguration(long configId) throws ConfigurationNotFoundException {
		Configuration existingConfig = em.find(Configuration.class, configId);
		if(existingConfig==null) {
			throw new ConfigurationNotFoundException("Configuration requested was not found");
		} else {
			return existingConfig;
		}
	}

	@Transactional
	public List<Configuration> getConfigurationList() {
		return em.createQuery("SELECT c FROM Configuration c", Configuration.class)
				.getResultList();
	}

	@Transactional
	public boolean deleteConfiguration(long id) {
		Configuration config = getConfiguration(id);
		if(config==null) {
			return false;
		}

		em.remove(config);
		return true;
	}
}
