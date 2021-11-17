package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.model.ModelConfiguration;
import io.swagger.model.SystemstateSensorstate;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Systemstate
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-17T15:48:40.301Z[GMT]")


public class Systemstate   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("activeconfiguration")
  private ModelConfiguration activeconfiguration = null;

  @JsonProperty("sensorstate")
  @Valid
  private List<SystemstateSensorstate> sensorstate = null;

  public Systemstate id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   **/
  @Schema(description = "")
  
    public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Systemstate name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   **/
  @Schema(description = "")
  
    public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Systemstate activeconfiguration(ModelConfiguration activeconfiguration) {
    this.activeconfiguration = activeconfiguration;
    return this;
  }

  /**
   * Get activeconfiguration
   * @return activeconfiguration
   **/
  @Schema(description = "")
  
    @Valid
    public ModelConfiguration getActiveconfiguration() {
    return activeconfiguration;
  }

  public void setActiveconfiguration(ModelConfiguration activeconfiguration) {
    this.activeconfiguration = activeconfiguration;
  }

  public Systemstate sensorstate(List<SystemstateSensorstate> sensorstate) {
    this.sensorstate = sensorstate;
    return this;
  }

  public Systemstate addSensorstateItem(SystemstateSensorstate sensorstateItem) {
    if (this.sensorstate == null) {
      this.sensorstate = new ArrayList<SystemstateSensorstate>();
    }
    this.sensorstate.add(sensorstateItem);
    return this;
  }

  /**
   * Get sensorstate
   * @return sensorstate
   **/
  @Schema(description = "")
      @Valid
    public List<SystemstateSensorstate> getSensorstate() {
    return sensorstate;
  }

  public void setSensorstate(List<SystemstateSensorstate> sensorstate) {
    this.sensorstate = sensorstate;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Systemstate systemstate = (Systemstate) o;
    return Objects.equals(this.id, systemstate.id) &&
        Objects.equals(this.name, systemstate.name) &&
        Objects.equals(this.activeconfiguration, systemstate.activeconfiguration) &&
        Objects.equals(this.sensorstate, systemstate.sensorstate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, activeconfiguration, sensorstate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Systemstate {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    activeconfiguration: ").append(toIndentedString(activeconfiguration)).append("\n");
    sb.append("    sensorstate: ").append(toIndentedString(sensorstate)).append("\n");
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
