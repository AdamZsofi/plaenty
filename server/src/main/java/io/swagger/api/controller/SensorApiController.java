package io.swagger.api.controller;

import io.swagger.api.interfaces.SensorApi;
import org.threeten.bp.OffsetDateTime;
import io.swagger.model.generated.Sensordatalist;
import io.swagger.model.generated.Sensorlist;
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
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-17T15:48:40.301Z[GMT]")
@RestController
public class SensorApiController implements SensorApi {

    private static final Logger log = LoggerFactory.getLogger(SensorApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public SensorApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Sensordatalist> sendSensorData(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("sensorid") Integer sensorid,@Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "from", required = false) OffsetDateTime from) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Sensordatalist>(objectMapper.readValue("[ \"\", \"\" ]", Sensordatalist.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Sensordatalist>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Sensordatalist>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Sensorlist> sendSensorList() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Sensorlist>(objectMapper.readValue("[ \"\", \"\" ]", Sensorlist.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Sensorlist>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Sensorlist>(HttpStatus.NOT_IMPLEMENTED);
    }

}
