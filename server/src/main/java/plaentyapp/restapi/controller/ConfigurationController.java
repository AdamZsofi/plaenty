package plaentyapp.restapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import plaentyapp.model.configuration.ConfigurationNotFoundException;
import plaentyapp.model.configuration.Configuration;
import plaentyapp.model.system.HydroponicSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/configuration")
public class ConfigurationController {
	@Autowired
	HydroponicSystem system;

	@GetMapping("{id}")
	public ResponseEntity<Configuration> configurationIdGet(@PathVariable long id) {
		try {
			return ResponseEntity.ok(system.getConfiguration(id));
		} catch (ConfigurationNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> configurationDelete(@PathVariable long id) {
		Configuration config = system.getConfiguration(id);
		if(config==null) {
			return ResponseEntity.notFound().build();
		}

		String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(!username.equals(config.getAuthor())) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // prevent anyone else but the author from deleting the config
		}

		try {
			if(system.deleteConfiguration(id)) {
				return ResponseEntity.ok().build();
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		} catch (ConfigurationNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping
	public ResponseEntity<Configuration> configurationIdPut(@Valid @RequestBody Configuration body) {
		String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Configuration oldConfiguration = system.getConfiguration(body.getId());
		if(oldConfiguration==null){
			return ResponseEntity.notFound().build();
		}

		if(!username.equals(oldConfiguration.getAuthor())) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // prevent anyone else but the author from editing the config
		}

		try {
			return ResponseEntity.ok(system.updateConfiguration(body));
		} catch (ConfigurationNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/list")
	public ResponseEntity<List<Configuration>> configurationListGet() {
		return ResponseEntity.ok(system.getConfigurationList());
	}

	@PostMapping
	public ResponseEntity<Configuration> configurationPost(@Valid @RequestBody Configuration body) {
		body.setId(null);
		String user = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		body.setAuthor(user);

		return ResponseEntity.ok(system.saveConfiguration(body));
	}
}
