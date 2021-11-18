package io.swagger.api.generatedcontroller;

import io.swagger.api.interfaces.ActiveConfigurationApi;
import io.swagger.model.generated.Configuration;
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

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-17T15:48:40.301Z[GMT]")
@RestController
public class ActiveConfigurationApiController implements ActiveConfigurationApi {

    private static final Logger log = LoggerFactory.getLogger(ActiveConfigurationApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public ActiveConfigurationApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Configuration> activeConfiguration() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Configuration>(objectMapper.readValue("{\n  \"phmin\" : \"phmin\",\n  \"pumpoff\" : 5,\n  \"lightrequired\" : \"low\",\n  \"pumpon\" : 1,\n  \"phmax\" : \"phmax\",\n  \"name\" : \"name\",\n  \"ecmin\" : \"ecmin\",\n  \"id\" : 6,\n  \"ecmax\" : \"ecmax\"\n}", Configuration.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Configuration>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Configuration>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Configuration> activeConfigurationIdPut(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("id") Integer id) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Configuration>(objectMapper.readValue("{\n  \"phmin\" : \"phmin\",\n  \"pumpoff\" : 5,\n  \"lightrequired\" : \"low\",\n  \"pumpon\" : 1,\n  \"phmax\" : \"phmax\",\n  \"name\" : \"name\",\n  \"ecmin\" : \"ecmin\",\n  \"id\" : 6,\n  \"ecmax\" : \"ecmax\"\n}", Configuration.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Configuration>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Configuration>(HttpStatus.NOT_IMPLEMENTED);
    }

}