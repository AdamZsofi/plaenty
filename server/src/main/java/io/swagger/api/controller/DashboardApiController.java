package io.swagger.api.controller;

import io.swagger.api.interfaces.DashboardApi;
import io.swagger.model.generated.Systemstate;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-17T15:48:40.301Z[GMT]")
@RestController
public class DashboardApiController implements DashboardApi {

    private static final Logger log = LoggerFactory.getLogger(DashboardApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public DashboardApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Systemstate> sendDashboardData() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Systemstate>(objectMapper.readValue("{\n  \"id\" : 0,\n  \"sensorstate\" : [ {\n    \"data\" : {\n      \"time\" : \"2000-01-23T04:56:07.000+00:00\",\n      \"value\" : \"value\"\n    },\n    \"sensor\" : {\n      \"name\" : \"name\",\n      \"id\" : 5,\n      \"dimension\" : \"dimension\"\n    }\n  }, {\n    \"data\" : {\n      \"time\" : \"2000-01-23T04:56:07.000+00:00\",\n      \"value\" : \"value\"\n    },\n    \"sensor\" : {\n      \"name\" : \"name\",\n      \"id\" : 5,\n      \"dimension\" : \"dimension\"\n    }\n  } ],\n  \"activeconfiguration\" : {\n    \"phmin\" : \"phmin\",\n    \"pumpoff\" : 5,\n    \"lightrequired\" : \"low\",\n    \"pumpon\" : 1,\n    \"phmax\" : \"phmax\",\n    \"name\" : \"name\",\n    \"ecmin\" : \"ecmin\",\n    \"id\" : 6,\n    \"ecmax\" : \"ecmax\"\n  }\n}", Systemstate.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Systemstate>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Systemstate>(HttpStatus.NOT_IMPLEMENTED);
    }

}
