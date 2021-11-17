package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.model.Sensor;
import io.swagger.model.Sensordata;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * SystemstateSensorstate
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-17T14:19:30.068Z[GMT]")


public class SystemstateSensorstate   {
  @JsonProperty("sensor")
  private Sensor sensor = null;

  @JsonProperty("data")
  private Sensordata data = null;

  public SystemstateSensorstate sensor(Sensor sensor) {
    this.sensor = sensor;
    return this;
  }

  /**
   * Get sensor
   * @return sensor
   **/
  @Schema(description = "")
  
    @Valid
    public Sensor getSensor() {
    return sensor;
  }

  public void setSensor(Sensor sensor) {
    this.sensor = sensor;
  }

  public SystemstateSensorstate data(Sensordata data) {
    this.data = data;
    return this;
  }

  /**
   * Get data
   * @return data
   **/
  @Schema(description = "")
  
    @Valid
    public Sensordata getData() {
    return data;
  }

  public void setData(Sensordata data) {
    this.data = data;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SystemstateSensorstate systemstateSensorstate = (SystemstateSensorstate) o;
    return Objects.equals(this.sensor, systemstateSensorstate.sensor) &&
        Objects.equals(this.data, systemstateSensorstate.data);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sensor, data);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SystemstateSensorstate {\n");
    
    sb.append("    sensor: ").append(toIndentedString(sensor)).append("\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
