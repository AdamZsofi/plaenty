package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ModelConfiguration
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-17T15:48:40.301Z[GMT]")


public class ModelConfiguration   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("ecmin")
  private String ecmin = null;

  @JsonProperty("ecmax")
  private String ecmax = null;

  @JsonProperty("phmin")
  private String phmin = null;

  @JsonProperty("phmax")
  private String phmax = null;

  /**
   * Gets or Sets lightrequired
   */
  public enum LightrequiredEnum {
    LOW("low"),
    
    NORMAL("normal"),
    
    HIGH("high");

    private String value;

    LightrequiredEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static LightrequiredEnum fromValue(String text) {
      for (LightrequiredEnum b : LightrequiredEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("lightrequired")
  private LightrequiredEnum lightrequired = null;

  @JsonProperty("pumpon")
  private Integer pumpon = null;

  @JsonProperty("pumpoff")
  private Integer pumpoff = null;

  public ModelConfiguration id(Integer id) {
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

  public ModelConfiguration name(String name) {
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

  public ModelConfiguration ecmin(String ecmin) {
    this.ecmin = ecmin;
    return this;
  }

  /**
   * Get ecmin
   * @return ecmin
   **/
  @Schema(description = "")
  
    public String getEcmin() {
    return ecmin;
  }

  public void setEcmin(String ecmin) {
    this.ecmin = ecmin;
  }

  public ModelConfiguration ecmax(String ecmax) {
    this.ecmax = ecmax;
    return this;
  }

  /**
   * Get ecmax
   * @return ecmax
   **/
  @Schema(description = "")
  
    public String getEcmax() {
    return ecmax;
  }

  public void setEcmax(String ecmax) {
    this.ecmax = ecmax;
  }

  public ModelConfiguration phmin(String phmin) {
    this.phmin = phmin;
    return this;
  }

  /**
   * Get phmin
   * @return phmin
   **/
  @Schema(description = "")
  
    public String getPhmin() {
    return phmin;
  }

  public void setPhmin(String phmin) {
    this.phmin = phmin;
  }

  public ModelConfiguration phmax(String phmax) {
    this.phmax = phmax;
    return this;
  }

  /**
   * Get phmax
   * @return phmax
   **/
  @Schema(description = "")
  
    public String getPhmax() {
    return phmax;
  }

  public void setPhmax(String phmax) {
    this.phmax = phmax;
  }

  public ModelConfiguration lightrequired(LightrequiredEnum lightrequired) {
    this.lightrequired = lightrequired;
    return this;
  }

  /**
   * Get lightrequired
   * @return lightrequired
   **/
  @Schema(description = "")
  
    public LightrequiredEnum getLightrequired() {
    return lightrequired;
  }

  public void setLightrequired(LightrequiredEnum lightrequired) {
    this.lightrequired = lightrequired;
  }

  public ModelConfiguration pumpon(Integer pumpon) {
    this.pumpon = pumpon;
    return this;
  }

  /**
   * Get pumpon
   * @return pumpon
   **/
  @Schema(description = "")
  
    public Integer getPumpon() {
    return pumpon;
  }

  public void setPumpon(Integer pumpon) {
    this.pumpon = pumpon;
  }

  public ModelConfiguration pumpoff(Integer pumpoff) {
    this.pumpoff = pumpoff;
    return this;
  }

  /**
   * Get pumpoff
   * @return pumpoff
   **/
  @Schema(description = "")
  
    public Integer getPumpoff() {
    return pumpoff;
  }

  public void setPumpoff(Integer pumpoff) {
    this.pumpoff = pumpoff;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModelConfiguration _configuration = (ModelConfiguration) o;
    return Objects.equals(this.id, _configuration.id) &&
        Objects.equals(this.name, _configuration.name) &&
        Objects.equals(this.ecmin, _configuration.ecmin) &&
        Objects.equals(this.ecmax, _configuration.ecmax) &&
        Objects.equals(this.phmin, _configuration.phmin) &&
        Objects.equals(this.phmax, _configuration.phmax) &&
        Objects.equals(this.lightrequired, _configuration.lightrequired) &&
        Objects.equals(this.pumpon, _configuration.pumpon) &&
        Objects.equals(this.pumpoff, _configuration.pumpoff);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, ecmin, ecmax, phmin, phmax, lightrequired, pumpon, pumpoff);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModelConfiguration {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    ecmin: ").append(toIndentedString(ecmin)).append("\n");
    sb.append("    ecmax: ").append(toIndentedString(ecmax)).append("\n");
    sb.append("    phmin: ").append(toIndentedString(phmin)).append("\n");
    sb.append("    phmax: ").append(toIndentedString(phmax)).append("\n");
    sb.append("    lightrequired: ").append(toIndentedString(lightrequired)).append("\n");
    sb.append("    pumpon: ").append(toIndentedString(pumpon)).append("\n");
    sb.append("    pumpoff: ").append(toIndentedString(pumpoff)).append("\n");
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
