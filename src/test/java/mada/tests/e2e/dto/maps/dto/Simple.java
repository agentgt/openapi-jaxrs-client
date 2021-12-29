/*
 * openapi-examples API
 * 
 *
 * The version of the OpenAPI document: 1.0.0-SNAPSHOT
 * 
 *
 * This class was auto generated by openapi-jaxrs-client (https://github.com/jskov/openapi-jaxrs-client).
 * Do not edit the class manually.
 */

package mada.tests.e2e.dto.maps.dto;

import java.util.Objects;

/**
 * Simple
 */
@javax.annotation.Generated(value = "dk.mada.jaxrs.generator.DtoGenerator")
public class Simple   {
  private boolean aBoolean;

  public Simple aBoolean(boolean aBoolean) {
    this.aBoolean = aBoolean;
    return this;
  }

  /**
   * Get aBoolean
   * @return aBoolean
   **/
  public boolean getABoolean() {
    return aBoolean;
  }

  public void setABoolean(boolean aBoolean) {
    this.aBoolean = aBoolean;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Simple other = (Simple) o;
    return Objects.equals(this.aBoolean, other.aBoolean);
  }

  @Override
  public int hashCode() {
    return Objects.hash(aBoolean);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Simple {\n");
    
    sb.append("    aBoolean: ").append(toIndentedString(aBoolean)).append("\n");
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

