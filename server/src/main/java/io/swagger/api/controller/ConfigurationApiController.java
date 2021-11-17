package io.swagger.api.controller;

import io.swagger.api.interfaces.ConfigurationApi;
import io.swagger.model.generated.ModelConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-17T15:48:40.301Z[GMT]")
@RestController
public class ConfigurationApiController implements ConfigurationApi {

    private static final Logger log = LoggerFactory.getLogger(ConfigurationApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public ConfigurationApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<ModelConfiguration> configurationIdGet(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("id") Integer id) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ModelConfiguration>(objectMapper.readValue("{\n  \"phmin\" : \"phmin\",\n  \"pumpoff\" : 5,\n  \"lightrequired\" : \"low\",\n  \"pumpon\" : 1,\n  \"phmax\" : \"phmax\",\n  \"name\" : \"name\",\n  \"ecmin\" : \"ecmin\",\n  \"id\" : 6,\n  \"ecmax\" : \"ecmax\"\n}", ModelConfiguration.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ModelConfiguration>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ModelConfiguration>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ModelConfiguration> configurationIdPut(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("id") Integer id) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ModelConfiguration>(objectMapper.readValue("{\n  \"phmin\" : \"phmin\",\n  \"pumpoff\" : 5,\n  \"lightrequired\" : \"low\",\n  \"pumpon\" : 1,\n  \"phmax\" : \"phmax\",\n  \"name\" : \"name\",\n  \"ecmin\" : \"ecmin\",\n  \"id\" : 6,\n  \"ecmax\" : \"ecmax\"\n}", ModelConfiguration.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ModelConfiguration>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ModelConfiguration>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ModelConfiguration> configurationListGet() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ModelConfiguration>(objectMapper.readValue("{\n  \"phmin\" : \"phmin\",\n  \"pumpoff\" : 5,\n  \"lightrequired\" : \"low\",\n  \"pumpon\" : 1,\n  \"phmax\" : \"phmax\",\n  \"name\" : \"name\",\n  \"ecmin\" : \"ecmin\",\n  \"id\" : 6,\n  \"ecmax\" : \"ecmax\"\n}", ModelConfiguration.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ModelConfiguration>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ModelConfiguration>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> configurationPost(@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody ModelConfiguration body) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

}
