package com.sarfraz.priority.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.sarfraz.priority.model.Area;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * AreaPriority
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-07-01T20:49:10.499858+05:30[Asia/Calcutta]")
public class AreaPriority   {
  @JsonProperty("area")
  private Area area;

  @JsonProperty("priority")
  @Size(min = 1)
  private Integer priority;

  /**
   * Gets or Sets statisfaction
   */
  public enum StatisfactionEnum {
    NUMBER_1(1),
    
    NUMBER_2(2),
    
    NUMBER_3(3),
    
    NUMBER_4(4),
    
    NUMBER_5(5);

    private Integer value;

    StatisfactionEnum(Integer value) {
      this.value = value;
    }

    @JsonValue
    public Integer getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StatisfactionEnum fromValue(Integer value) {
      for (StatisfactionEnum b : StatisfactionEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("statisfaction")
  private StatisfactionEnum statisfaction;

  public AreaPriority area(Area area) {
    this.area = area;
    return this;
  }

  /**
   * Get area
   * @return area
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public Area getArea() {
    return area;
  }

  public void setArea(Area area) {
    this.area = area;
  }

  public AreaPriority priority(Integer priority) {
    this.priority = priority;
    return this;
  }

  /**
   * Get priority
   * @return priority
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Integer getPriority() {
    return priority;
  }

  public void setPriority(Integer priority) {
    this.priority = priority;
  }

  public AreaPriority statisfaction(StatisfactionEnum statisfaction) {
    this.statisfaction = statisfaction;
    return this;
  }

  /**
   * Get statisfaction
   * @return statisfaction
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public StatisfactionEnum getStatisfaction() {
    return statisfaction;
  }

  public void setStatisfaction(StatisfactionEnum statisfaction) {
    this.statisfaction = statisfaction;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AreaPriority areaPriority = (AreaPriority) o;
    return Objects.equals(this.area, areaPriority.area) &&
        Objects.equals(this.priority, areaPriority.priority) &&
        Objects.equals(this.statisfaction, areaPriority.statisfaction);
  }

  @Override
  public int hashCode() {
    return Objects.hash(area, priority, statisfaction);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AreaPriority {\n");
    
    sb.append("    area: ").append(toIndentedString(area)).append("\n");
    sb.append("    priority: ").append(toIndentedString(priority)).append("\n");
    sb.append("    statisfaction: ").append(toIndentedString(statisfaction)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

