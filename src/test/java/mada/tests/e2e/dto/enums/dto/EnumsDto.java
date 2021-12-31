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

package mada.tests.e2e.dto.enums.dto;

import java.util.Objects;

/**
 * EnumsDto
 */
@javax.annotation.Generated(value = "dk.mada.jaxrs.generator.DtoGenerator")
public class EnumsDto   {
  private InnerEnum inner;

  private ExternalEnum external;

  public EnumsDto inner(InnerEnum inner) {
    this.inner = inner;
    return this;
  }

  /**
   * Get inner
   * @return inner
   **/
  public InnerEnum getInner() {
    return inner;
  }

  public void setInner(InnerEnum inner) {
    this.inner = inner;
  }

  public EnumsDto external(ExternalEnum external) {
    this.external = external;
    return this;
  }

  /**
   * Get external
   * @return external
   **/
  public ExternalEnum getExternal() {
    return external;
  }

  public void setExternal(ExternalEnum external) {
    this.external = external;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EnumsDto other = (EnumsDto) o;
    return Objects.equals(this.inner, other.inner) &&
        Objects.equals(this.external, other.external);
  }

  @Override
  public int hashCode() {
    return Objects.hash(inner, external);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EnumsDto {");
    sb.append("\n    inner: ").append(toIndentedString(inner));
    sb.append("\n    external: ").append(toIndentedString(external));
    sb.append("\n}");
    return sb.toString();
  }

  private String toIndentedString(Object o) {
    return Objects.toString(o).replace("\n", "\n    ");
  }
}

