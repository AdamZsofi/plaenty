package io.swagger.api;

import io.swagger.model.ModelConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-17T14:19:30.068Z[GMT]")
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

    public ResponseEntity<ModelConfiguration> activeConfiguration() {
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

    public ResponseEntity<ModelConfiguration> activeConfigurationIdPut(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("id") Integer id) {
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

}
