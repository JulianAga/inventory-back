package com.example.inventoryback.entities.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@ToString
public class ResponseStore {

  private Long id;

  @JsonProperty(value = "name")
  @NotEmpty
  private String name;

  @JsonProperty(value = "address")
  @NotEmpty
  private String address;

  @JsonProperty(value = "city")
  @NotEmpty
  private String city;

}
