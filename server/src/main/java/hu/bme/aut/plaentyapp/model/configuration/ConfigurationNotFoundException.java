package hu.bme.aut.plaentyapp.model.configuration;

public class ConfigurationNotFoundException extends RuntimeException {
	public ConfigurationNotFoundException(String msg) {
		super(msg);
	}
}
