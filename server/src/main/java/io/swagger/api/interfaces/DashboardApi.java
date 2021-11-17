/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.29).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api.interfaces;

import io.swagger.model.generated.Systemstate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-17T15:48:40.301Z[GMT]")
@Validated
public interface DashboardApi {

    @Operation(summary = "Sends the present state of the system", description = "Returns if the system is functional and the value of sensors, actuators, active configuration, etc.", tags={ "dashboard" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "The present state of the system", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Systemstate.class))),
        
        @ApiResponse(responseCode = "404", description = "Not found: Cannot get system status") })
    @RequestMapping(value = "/dashboard",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Systemstate> sendDashboardData();

}

