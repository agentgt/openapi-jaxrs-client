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



/**
 * Gets or Sets 
 */
public enum ExternalEnum {
  
  E("E"),
  
  F("F");

  private String value;

  ExternalEnum(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}

