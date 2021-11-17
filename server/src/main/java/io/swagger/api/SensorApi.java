/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.29).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import org.threeten.bp.OffsetDateTime;
import io.swagger.model.Sensordatalist;
import io.swagger.model.Sensorlist;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CookieValue;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-17T14:19:30.068Z[GMT]")
@Validated
public interface SensorApi {

    @Operation(summary = "Returns the historical data measured by a sensor", description = "Returns the measurements taken on the given sensor from the given date and time", tags={ "sensor" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "The requested historical data on a single sensor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Sensordatalist.class))) })
    @RequestMapping(value = "/sensor/data/{sensorid}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Sensordatalist> sendSensorData(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("sensorid") Integer sensorid, @Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "from", required = false) OffsetDateTime from);


    @Operation(summary = "Returns the sensors of the system (name and id)", description = "", tags={ "sensor" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "The list of sensors (names with ids)", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Sensorlist.class))) })
    @RequestMapping(value = "/sensor/list/",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Sensorlist> sendSensorList();

}

