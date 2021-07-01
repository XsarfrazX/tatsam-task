package com.sarfraz.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.sarfraz.model.AreaPriority;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * User
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-07-01T20:23:22.811265+05:30[Asia/Calcutta]")
public class User   {
  @JsonProperty("username")
  private String username;

  @JsonProperty("priorityAreas")
  @Valid
  private List<AreaPriority> priorityAreas = new ArrayList<>();

  public User username(String username) {
    this.username = username;
    return this;
  }

  /**
   * Get username
   * @return username
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public User priorityAreas(List<AreaPriority> priorityAreas) {
    this.priorityAreas = priorityAreas;
    return this;
  }

  public User addPriorityAreasItem(AreaPriority priorityAreasItem) {
    this.priorityAreas.add(priorityAreasItem);
    return this;
  }

  /**
   * Get priorityAreas
   * @return priorityAreas
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public List<AreaPriority> getPriorityAreas() {
    return priorityAreas;
  }

  public void setPriorityAreas(List<AreaPriority> priorityAreas) {
    this.priorityAreas = priorityAreas;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(this.username, user.username) &&
        Objects.equals(this.priorityAreas, user.priorityAreas);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, priorityAreas);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class User {\n");
    
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    priorityAreas: ").append(toIndentedString(priorityAreas)).append("\n");
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

