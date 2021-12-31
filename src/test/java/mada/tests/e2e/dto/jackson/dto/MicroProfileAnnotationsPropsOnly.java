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

package mada.tests.e2e.dto.jackson.dto;

import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

/**
 * MicroProfileAnnotationsPropsOnly
 */
@JsonPropertyOrder({
  MicroProfileAnnotationsPropsOnly.JSON_PROPERTY_WITH_DESCRIPTION
})
@javax.annotation.Generated(value = "dk.mada.jaxrs.generator.DtoGenerator")
public class MicroProfileAnnotationsPropsOnly   {
  public static final String JSON_PROPERTY_WITH_DESCRIPTION = "withDescription";
  @JsonProperty(JSON_PROPERTY_WITH_DESCRIPTION)
  private String withDescription;

  public MicroProfileAnnotationsPropsOnly withDescription(String withDescription) {
    this.withDescription = withDescription;
    return this;
  }

  /**
   * property description
   * @return withDescription
   **/
  @ApiModelProperty(value = "property description")
  public String getWithDescription() {
    return withDescription;
  }

  public void setWithDescription(String withDescription) {
    this.withDescription = withDescription;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MicroProfileAnnotationsPropsOnly other = (MicroProfileAnnotationsPropsOnly) o;
    return Objects.equals(this.withDescription, other.withDescription);
  }

  @Override
  public int hashCode() {
    return Objects.hash(withDescription);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MicroProfileAnnotationsPropsOnly {\n");
    
    sb.append("    withDescription: ").append(toIndentedString(withDescription)).append("\n");
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

